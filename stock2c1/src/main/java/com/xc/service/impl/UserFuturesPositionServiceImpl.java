package com.xc.service.impl;


import com.xc.dao.*;
import com.xc.pojo.*;
import com.xc.service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.xc.common.ServerResponse;
import com.xc.service.*;
import com.xc.utils.DateTimeUtil;
import com.xc.utils.KeyUtils;
import com.xc.utils.stock.BuyAndSellUtils;
import com.xc.vo.agent.AgentIncomeVO;
import com.xc.vo.futuresposition.AdminFuturesPositionVO;
import com.xc.vo.futuresposition.AgentFuturesPositionVO;
import com.xc.vo.futuresposition.FuturesPositionProfitVO;
import com.xc.vo.futuresposition.FuturesPositionVO;
import com.xc.vo.futuresposition.UserFuturesPositionVO;
import com.xc.vo.position.UserPositionVO;
import com.xc.vo.stockfutures.FuturesVO;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("iUserFuturesPositionService")
public class UserFuturesPositionServiceImpl implements IUserFuturesPositionService {
    private static final Logger log = LoggerFactory.getLogger(UserFuturesPositionServiceImpl.class);

    @Autowired
    UserFuturesPositionMapper userFuturesPositionMapper;

    @Autowired
    IUserService iUserService;

    @Autowired
    IStockFuturesService iStockFuturesService;

    @Autowired
    IAgentUserService iAgentUserService;

    @Autowired
    AgentUserMapper agentUserMapper;

    @Autowired
    IStockCoinService iStockCoinService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserCashDetailMapper userCashDetailMapper;
    @Autowired
    ISiteFuturesSettingService iSiteFuturesSettingService;
    @Autowired
    StockFuturesMapper stockFuturesMapper;
    @Autowired
    ISiteProductService iSiteProductService;

    @Transactional
    public ServerResponse buyFutures(Integer futuresId, Integer buyNum, Integer buyType, Integer lever, HttpServletRequest request) throws Exception {
        if (futuresId == null || buyNum == null || buyType == null) {
            return ServerResponse.createByErrorMsg("参数不能为空");
        }
        /*实名认证开关开启*/
        SiteProduct siteProduct = iSiteProductService.getProductSetting();
        User user = this.iUserService.getCurrentRefreshUser(request);
        if (siteProduct.getRealNameDisplay() && (StringUtils.isBlank(user.getRealName()) || StringUtils.isBlank(user.getIdCard()))) {
            return ServerResponse.createByErrorMsg("下单失败，请先实名认证");
        }

        if(siteProduct.getHolidayDisplay()){
            return ServerResponse.createByErrorMsg("周末或节假日不能交易！");
        }

        if (siteProduct.getRealNameDisplay() && user.getIsLock().intValue() == 1) {
            return ServerResponse.createByErrorMsg("下单失败，账户已被锁定");
        }

        StockFutures stockFutures = this.iStockFuturesService.selectFuturesById(futuresId);
        if (stockFutures == null) {
            return ServerResponse.createByErrorMsg("产品不存在");
        }
        if (1 != stockFutures.getTransState().intValue()) {
            return ServerResponse.createByErrorMsg("该产品不能交易");
        }
        if (buyNum.intValue() < stockFutures.getMinNum().intValue()) {
            return ServerResponse.createByErrorMsg("最低不能低于" + stockFutures.getMinNum() + "手");
        }
        if (buyNum.intValue() > stockFutures.getMaxNum().intValue()) {
            return ServerResponse.createByErrorMsg("最多不能高于" + stockFutures.getMaxNum() + "手");
        }
        log.info("用户 {} 下单, 期货产品 = {} ，数量 = {} 手 , 方向 = {} ， 杠杆倍数 = {}", new Object[]{user
                .getId(), stockFutures.getFuturesName(), buyNum, buyType, lever});


        String am_begin = stockFutures.getTransAmBegin();
        String am_end = stockFutures.getTransAmEnd();
        String pm_begin = stockFutures.getTransPmBegin();
        String pm_end = stockFutures.getTransPmEnd();
        String pm_begin2 = stockFutures.getTransPmBegin2();
        String pm_end2 = stockFutures.getTransPmEnd2();
        boolean am_flag = BuyAndSellUtils.isTransTime(am_begin, am_end);
        boolean pm_flag = BuyAndSellUtils.isTransTime(pm_begin, pm_end);
        boolean pm_flag2 = BuyAndSellUtils.isTransTime(pm_begin2, pm_end2);
        log.info("futures 是否在上午交易时间 = {} 是否在下午交易时间 = {}是否在下午交易时间2 = {}", Boolean.valueOf(am_flag), Boolean.valueOf(pm_flag), Boolean.valueOf(pm_flag2));
        if (!am_flag && !pm_flag && !pm_flag2) {
            return ServerResponse.createByErrorMsg("下单失败，不在交易时段内");
        }


        SiteFuturesSetting siteFuturesSetting = this.iSiteFuturesSettingService.getSetting();
        if (siteFuturesSetting == null) {
            log.error("下单出错，网站设置表不存在");
            return ServerResponse.createByErrorMsg("下单失败，系统设置错误");
        }

        List dbPosition = findPositionByFuturesCodeAndTimes(siteFuturesSetting.getBuySameTimes().intValue(), stockFutures
                .getFuturesCode(), user.getId());
        if (dbPosition.size() >= siteFuturesSetting.getBuySameNums().intValue()) {
            return ServerResponse.createByErrorMsg("频繁交易," + siteFuturesSetting.getBuySameTimes() + "分钟内同一产品持仓不得超过" + siteFuturesSetting
                    .getBuySameNums() + "条");
        }

        Integer transNum = findPositionNumByTimes(siteFuturesSetting.getBuyNumTimes().intValue(), user.getId());
        if (transNum.intValue() >= siteFuturesSetting.getBuyNumLots().intValue()) {
            return ServerResponse.createByErrorMsg("频繁交易," + siteFuturesSetting.getBuyNumTimes() + "分钟内不能超过" + siteFuturesSetting
                    .getBuyNumLots() + "手");
        }


        // 保证金= 期货保证金*数量/杠杆倍数
        BigDecimal all_deposit_amt = (new BigDecimal(stockFutures.getDepositAmt().intValue())).multiply(new BigDecimal(buyNum.intValue())).divide(new BigDecimal(lever)).setScale(4,2);


        BigDecimal all_order_fee = (new BigDecimal(stockFutures.getTransFee().intValue())).multiply(new BigDecimal(buyNum.intValue()));


        ServerResponse exchangeRateResponse = this.iStockFuturesService.getExchangeRate(stockFutures.getCoinCode());
        if (!exchangeRateResponse.isSuccess()) {
            return ServerResponse.createByErrorMsg(exchangeRateResponse.getMsg());
        }
        BigDecimal exchangeRate = (BigDecimal) exchangeRateResponse.getData();
        log.info("期货产品 {} , 基币 {} , 汇率模版采用 {} ", new Object[]{stockFutures.getFuturesName(), stockFutures
                .getCoinCode(), exchangeRate});

        BigDecimal all_usd_amt = all_deposit_amt;
        log.info("需要支付的总资金（保证金） = {}  未计算汇率", all_usd_amt);

        all_usd_amt = all_usd_amt.multiply(exchangeRate);
        log.info("计算汇率后 人民币 = {} ", all_usd_amt);


        if (user.getEnableFutAmt().compareTo(all_usd_amt) == -1) {
            return ServerResponse.createByErrorMsg("账户资金不足");
        }
        if (user.getUserAmt().compareTo(new BigDecimal("0")) == -1) {
            return ServerResponse.createByErrorMsg("失败，融资总资金小于0");
        }
        if (user.getUserIndexAmt().compareTo(new BigDecimal("0")) == -1) {
            return ServerResponse.createByErrorMsg("失败，指数总资金小于0");
        }


        BigDecimal max_buy_amt = user.getEnableFutAmt().multiply(siteFuturesSetting.getBuyMaxPercent());
        if (max_buy_amt.compareTo(all_usd_amt) == -1) {
            return ServerResponse.createByErrorMsg("不能超过最大购买比例");
        }


        FuturesVO futuresVO = this.iStockFuturesService.querySingleMarket(stockFutures.getFuturesGid());
        log.info("期货下单 {} , 价格 = {}", futuresVO.getName(), futuresVO.getNowPrice());


        BigDecimal reckon_enable = user.getEnableFutAmt().subtract(all_usd_amt);
        user.setEnableFutAmt(reckon_enable);
        int updateUserCount = this.userMapper.updateByPrimaryKeySelective(user);
        if (updateUserCount > 0) {
            log.info("【用户交易 期货 下单】修改用户金额成功");
        } else {
            log.error("【用户交易 期货 下单】修改用户金额出错");
            throw new Exception("【用户交易 期货 下单】修改用户金额出错");
        }


        UserFuturesPosition userFuturesPosition = new UserFuturesPosition();
        userFuturesPosition.setPositionType(user.getAccountType());
        userFuturesPosition.setPositionSn(KeyUtils.getUniqueKey());
        userFuturesPosition.setUserId(user.getId());
        userFuturesPosition.setRealName(user.getRealName());
        userFuturesPosition.setAgentId(user.getAgentId());
        userFuturesPosition.setFuturesName(stockFutures.getFuturesName());
        userFuturesPosition.setFuturesCode(stockFutures.getFuturesCode());
        userFuturesPosition.setFuturesGid(stockFutures.getFuturesGid());
        userFuturesPosition.setBuyOrderTime(new Date());
        userFuturesPosition.setBuyOrderPrice(new BigDecimal(futuresVO.getNowPrice()));
        userFuturesPosition.setOrderDirection((buyType.intValue() == 0) ? "买涨" : "买跌");

        userFuturesPosition.setOrderNum(buyNum);
        userFuturesPosition.setFuturesStandard(stockFutures.getFuturesStandard());
        userFuturesPosition.setFuturesUnit(stockFutures.getFuturesUnit());

        userFuturesPosition.setAllDepositAmt(all_usd_amt);
        userFuturesPosition.setOrderFee(all_order_fee);
        userFuturesPosition.setIsLock(Integer.valueOf(0));
        userFuturesPosition.setProfitAndLose(new BigDecimal("0"));
        userFuturesPosition.setAllProfitAndLose(new BigDecimal("0"));
        userFuturesPosition.setBuyRate(exchangeRate);

        userFuturesPosition.setCoinCode(stockFutures.getCoinCode());
        userFuturesPosition.setOrderLever(lever);


        int insertPositionCount = this.userFuturesPositionMapper.insert(userFuturesPosition);
        if (insertPositionCount > 0) {
            log.info("【用户交易 期货 下单】保存持仓记录成功");
        } else {
            log.error("用户交易 期货 下单】保存持仓记录出错");
            throw new Exception("用户交易 期货 下单】保存持仓记录出错");
        }

        return ServerResponse.createBySuccess("下单成功");
    }

    @Override
    public ServerResponse del(Integer positionId) {
        if (positionId == null) {
            return ServerResponse.createByErrorMsg("id不能为空");
        }
        UserFuturesPosition position = this.userFuturesPositionMapper.selectByPrimaryKey(positionId);
        if (position == null) {
            ServerResponse.createByErrorMsg("该持仓不存在");
        }

        int updateCount = this.userFuturesPositionMapper.deleteByPrimaryKey(positionId);
        if (updateCount > 0) {
            return ServerResponse.createBySuccessMsg("删除成功");
        }
        return ServerResponse.createByErrorMsg("删除失败");
    }


    public ServerResponse sellFutures(String positionSn, int doType) throws Exception {
        log.info("【 用户交易平仓 期货 】 positionSn = {} ， dotype = {}", positionSn, Integer.valueOf(doType));


        UserFuturesPosition userFuturesPosition = this.userFuturesPositionMapper.selectPositionBySn(positionSn);
        if (userFuturesPosition == null) {
            return ServerResponse.createByErrorMsg("操作失败，找不到订单");
        }


        StockFutures stockFutures = this.iStockFuturesService.selectFuturesByCode(userFuturesPosition.getFuturesCode());
        if (stockFutures == null) {
            return ServerResponse.createByErrorMsg("操作失败，产品不存在或已被删除");
        }


        if (doType != 0) {
            String am_begin = stockFutures.getTransAmBegin();
            String am_end = stockFutures.getTransAmEnd();
            String pm_begin = stockFutures.getTransPmBegin();
            String pm_end = stockFutures.getTransPmEnd();
            String pm_begin2 = stockFutures.getTransPmBegin2();
            String pm_end2 = stockFutures.getTransPmEnd2();
            boolean am_flag = BuyAndSellUtils.isTransTime(am_begin, am_end);
            boolean pm_flag = BuyAndSellUtils.isTransTime(pm_begin, pm_end);
            boolean pm_flag2 = BuyAndSellUtils.isTransTime(pm_begin2, pm_end2);
            log.info("【 期货 】是否在上午交易时间 = {} 是否在下午交易时间 = {}", Boolean.valueOf(am_flag), Boolean.valueOf(pm_flag));
            if (!am_flag && !pm_flag && !pm_flag2) {
                return ServerResponse.createByErrorMsg("操作失败，不在交易时段内");
            }
        }


        User user = this.userMapper.selectByPrimaryKey(userFuturesPosition.getUserId());
        /*实名认证开关开启*/
        SiteProduct siteProduct = iSiteProductService.getProductSetting();
        if (siteProduct.getRealNameDisplay() && user.getIsLock().intValue() == 1) {
            return ServerResponse.createByErrorMsg("操作失败，用户已被锁定");
        }
        if(siteProduct.getHolidayDisplay()){
            return ServerResponse.createByErrorMsg("周末或节假日不能交易！");
        }

        if (userFuturesPosition.getSellOrderPrice() != null) {
            return ServerResponse.createByErrorMsg("操作失败，此订单已平仓");
        }

        if (1 == userFuturesPosition.getIsLock().intValue()) {
            return ServerResponse.createByErrorMsg("平仓失败 " + userFuturesPosition.getLockMsg());
        }


        FuturesVO futuresVO = this.iStockFuturesService.querySingleMarket(stockFutures.getFuturesGid());
        log.info("平仓 期货订单 {} ，平仓价格为 {}", userFuturesPosition.getId(), futuresVO.getNowPrice());


        userFuturesPosition.setSellOrderPrice(new BigDecimal(futuresVO.getNowPrice()));
        userFuturesPosition.setSellOrderTime(new Date());

        BigDecimal point_sub = userFuturesPosition.getSellOrderPrice().setScale(2,4).subtract(userFuturesPosition.getBuyOrderPrice().setScale(2,4));

        //每手浮动价格
        BigDecimal eachPoint = new BigDecimal("1");
        if(stockFutures!=null && stockFutures.getEachPoint() != null){
            eachPoint = stockFutures.getEachPoint();
        }
        //BigDecimal profit_and_lose = point_sub.multiply(new BigDecimal(userFuturesPosition.getFuturesStandard().intValue())).multiply(new BigDecimal(userFuturesPosition.getOrderNum().intValue()));
        BigDecimal profit_and_lose = point_sub.multiply(eachPoint).multiply(new BigDecimal(userFuturesPosition.getOrderNum().intValue()));
        if ("买跌".equals(userFuturesPosition.getOrderDirection())) {
            profit_and_lose = profit_and_lose.negate();
        }
        BigDecimal all_profit = profit_and_lose.subtract(userFuturesPosition.getOrderFee());


        ServerResponse exchangeRateResponse = this.iStockFuturesService.getExchangeRate(stockFutures.getCoinCode());
        if (!exchangeRateResponse.isSuccess()) {
            return ServerResponse.createByErrorMsg(exchangeRateResponse.getMsg());
        }
        BigDecimal exchangeRate = (BigDecimal) exchangeRateResponse.getData();
        exchangeRate = exchangeRate.setScale(2, 4);
        log.info("【平仓】期货产品 {} , 基币 {} , 汇率模版采用 {} ", new Object[]{stockFutures.getFuturesName(), stockFutures
                .getCoinCode(), exchangeRate});

        userFuturesPosition.setSellRate(exchangeRate);


        profit_and_lose = profit_and_lose.multiply(exchangeRate);
        userFuturesPosition.setProfitAndLose(profit_and_lose);
        log.info("【平仓】USD总盈亏 {} ", all_profit);
        all_profit = all_profit.multiply(exchangeRate);
        userFuturesPosition.setAllProfitAndLose(all_profit);


        int updateFuturesPositionCount = this.userFuturesPositionMapper.updateByPrimaryKeySelective(userFuturesPosition);
        if (updateFuturesPositionCount > 0) {
            log.info("【用户平仓 期货 】修改浮动盈亏记录成功");
        } else {
            log.error("【用户平仓 期货 】修改浮动盈亏记录出错");
            throw new Exception("用户平仓 期货 】修改浮动盈亏记录出错");
        }


        BigDecimal before_user_amt = user.getUserFutAmt();
        BigDecimal before_enable_amt = user.getEnableFutAmt();
        log.info("用户平仓之前 的 总资金  = {} , 可用资金 = {}", before_user_amt, before_enable_amt);

        BigDecimal user_futures_amt = before_user_amt.add(all_profit);

        BigDecimal enable_futures_amt = before_enable_amt.add(all_profit).add(userFuturesPosition.getAllDepositAmt());

        log.info("用户平仓后的总资金  = {} , 可用资金 = {}", user_futures_amt, enable_futures_amt);
        user.setUserFutAmt(user_futures_amt);
        user.setEnableFutAmt(enable_futures_amt);

        int updateUserCount = this.userMapper.updateByPrimaryKeySelective(user);
        if (updateUserCount > 0) {
            log.info("【用户平仓 期货 】修改用户金额成功");
        } else {
            log.error("【用户平仓 期货 】修改用户金额出错");
            throw new Exception("【用户平仓 期货 】修改用户金额出错");
        }


        UserCashDetail ucd = new UserCashDetail();
        ucd.setPositionId(userFuturesPosition.getId());
        ucd.setAgentId(user.getAgentId());
        ucd.setAgentName(user.getAgentName());
        ucd.setUserId(user.getId());
        ucd.setUserName(user.getRealName());
        ucd.setDeType("期货盈亏");
        ucd.setDeAmt(all_profit);
        String deSummary = "卖出期货，" + userFuturesPosition.getFuturesName() + "/" + userFuturesPosition.getFuturesCode() + "，总盈亏：" + all_profit;
        ucd.setDeSummary(deSummary);
        log.info("卖出期货资金明细：{}", deSummary);


        ucd.setAddTime(new Date());
        ucd.setIsRead(Integer.valueOf(0));

        int insertSxfCount = this.userCashDetailMapper.insert(ucd);
        if (insertSxfCount > 0) {
            log.info("【用户平仓 期货 】保存明细记录成功");
        } else {
            log.error("【用户平仓 期货 】保存明细记录出错");
            throw new Exception("【用户平仓 期货 】保存明细记录出错");
        }

        return ServerResponse.createBySuccessMsg("平仓成功！");
    }


    public ServerResponse listByAdmin(Integer agentId, Integer positionType, Integer state, Integer userId, String positionSn, String beginTime, String endTime, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);


        Timestamp begin_time = null;
        if (StringUtils.isNotBlank(beginTime)) {
            begin_time = DateTimeUtil.searchStrToTimestamp(beginTime);
        }
        Timestamp end_time = null;
        if (StringUtils.isNotBlank(endTime)) {
            end_time = DateTimeUtil.searchStrToTimestamp(endTime);
        }


        List<UserFuturesPosition> userFuturesPositions = this.userFuturesPositionMapper.listByAdmin(positionType, state, userId, agentId, positionSn, begin_time, end_time);


        List<AdminFuturesPositionVO> adminFuturesPositionVOS = Lists.newArrayList();
        for (UserFuturesPosition userFuturesPosition : userFuturesPositions) {

            AdminFuturesPositionVO adminFuturesPositionVO = assembleAdminFuturesPositionVO(userFuturesPosition);
            adminFuturesPositionVOS.add(adminFuturesPositionVO);
        }

        PageInfo pageInfo = new PageInfo(userFuturesPositions);
        pageInfo.setList(adminFuturesPositionVOS);

        return ServerResponse.createBySuccess(pageInfo);
    }


    public ServerResponse lock(Integer positionId, Integer state, String lockMsg) {
        if (positionId == null || state == null) {
            return ServerResponse.createByErrorMsg("参数不能为空");
        }

        UserFuturesPosition position = this.userFuturesPositionMapper.selectByPrimaryKey(positionId);
        if (position == null) {
            return ServerResponse.createByErrorMsg("持仓不存在");
        }

        if (position.getSellOrderPrice() != null) {
            return ServerResponse.createByErrorMsg("平仓单不能锁仓");
        }

        if (state.intValue() == 1 &&
                StringUtils.isBlank(lockMsg)) {
            return ServerResponse.createByErrorMsg("锁仓提示信息必填");
        }


        if (state.intValue() == 1) {
            position.setIsLock(Integer.valueOf(1));
            position.setLockMsg(lockMsg);
        } else {
            position.setIsLock(Integer.valueOf(0));
        }

        int updateCount = this.userFuturesPositionMapper.updateByPrimaryKeySelective(position);
        if (updateCount > 0) {
            return ServerResponse.createBySuccessMsg("操作成功");
        }
        return ServerResponse.createByErrorMsg("操作失败");
    }


    public ServerResponse findMyFuturesPositionByNameAndCode(String fuName, String fuCode, Integer state, HttpServletRequest request, int pageNum, int pageSize) {
        User user = this.iUserService.getCurrentUser(request);

        PageHelper.startPage(pageNum, pageSize);


        List<UserFuturesPosition> userFuturesPositions = this.userFuturesPositionMapper.findMyFuturesPositionByNameAndCode(user.getId(), fuName, fuCode, state);

        List<UserFuturesPositionVO> userFuturesPositionVOS = Lists.newArrayList();
        if (userFuturesPositions.size() > 0) {
            for (UserFuturesPosition userFuturesPosition : userFuturesPositions) {

                UserFuturesPositionVO userFuturesPositionVO = assembleUserFuturesPositionVO(userFuturesPosition);
                userFuturesPositionVOS.add(userFuturesPositionVO);
            }
        }
        PageInfo pageInfo = new PageInfo(userFuturesPositions);
        pageInfo.setList(userFuturesPositionVOS);
        return ServerResponse.createBySuccess(pageInfo);
    }

    /*根据期货代码查询用户最早入仓股票*/
    public ServerResponse findUserFuturesPositionByCode(HttpServletRequest request, String futuresGid) {
        User user = this.iUserService.getCurrentRefreshUser(request);
        UserFuturesPosition position = this.userFuturesPositionMapper.findUserFuturesPositionByCode(user.getId(), futuresGid);

        List<UserFuturesPositionVO> userPositionVOS = Lists.newArrayList();
        UserFuturesPositionVO userPositionVO = null;
        if(position != null){
            userPositionVO = assembleUserFuturesPositionVO(position);
        }
        userPositionVOS.add(userPositionVO);

        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(userPositionVOS);

        return ServerResponse.createBySuccess(pageInfo);
    }


    public ServerResponse listByAgent(Integer positionType, Integer state, Integer userId, Integer agentId, String positionSn, String beginTime, String endTime, HttpServletRequest request, int pageNum, int pageSize) {
        AgentUser currentAgent = this.iAgentUserService.getCurrentAgent(request);


        if (agentId != null) {
            AgentUser agentUser = this.agentUserMapper.selectByPrimaryKey(agentId);
            if (agentUser.getParentId() != currentAgent.getId()) {
                return ServerResponse.createByErrorMsg("不能查询非下级代理用户持仓");
            }
        }

        Integer searchId = null;
        if (agentId == null) {
            searchId = currentAgent.getId();
        } else {
            searchId = agentId;
        }


        Timestamp begin_time = null;
        if (StringUtils.isNotBlank(beginTime)) {
            begin_time = DateTimeUtil.searchStrToTimestamp(beginTime);
        }
        Timestamp end_time = null;
        if (StringUtils.isNotBlank(endTime)) {
            end_time = DateTimeUtil.searchStrToTimestamp(endTime);
        }

        PageHelper.startPage(pageNum, pageSize);

        List<UserFuturesPosition> userFuturesPositions = this.userFuturesPositionMapper.listByAgent(positionType, state, userId, searchId, positionSn, begin_time, end_time);

        List<AgentFuturesPositionVO> agentFuturesPositionVOS = Lists.newArrayList();
        for (UserFuturesPosition userFuturesPosition : userFuturesPositions) {

            AgentFuturesPositionVO agentFuturesPositionVO = assembleAgentFuturesPositionVO(userFuturesPosition);
            agentFuturesPositionVOS.add(agentFuturesPositionVO);
        }

        PageInfo pageInfo = new PageInfo(userFuturesPositions);
        pageInfo.setList(agentFuturesPositionVOS);

        return ServerResponse.createBySuccess(pageInfo);
    }


    public List<UserFuturesPosition> findPositionByFuturesCodeAndTimes(int minuteTimes, String futuresCode, Integer userId) {
        Date paramTimes = null;
        paramTimes = DateTimeUtil.parseToDateByMinute(minuteTimes);
        return this.userFuturesPositionMapper.findPositionByFuturesCodeAndTimes(paramTimes, futuresCode, userId);
    }


    public Integer findPositionNumByTimes(int minuteTimes, Integer userId) {
        Date beginDate = DateTimeUtil.parseToDateByMinute(minuteTimes);

        Integer transNum = this.userFuturesPositionMapper.findPositionNumByTimes(beginDate, userId);
        log.info("用户 {} 在 {} 分钟之内 交易手数 {}", new Object[]{userId, Integer.valueOf(minuteTimes), transNum});
        return transNum;
    }


    public List<Integer> findDistinctUserIdList() {
        return this.userFuturesPositionMapper.findDistinctUserIdList();
    }


    public List<UserFuturesPosition> findFuturesPositionByUserIdAndSellPriceIsNull(Integer userId) {
        return this.userFuturesPositionMapper.findFuturesPositionByUserIdAndSellPriceIsNull(userId);
    }


    public FuturesPositionVO findUserFuturesPositionAllProfitAndLose(Integer userId) {
        List<UserFuturesPosition> userFuturesPositions = this.userFuturesPositionMapper.findFuturesPositionByUserIdAndSellPriceIsNull(userId);

        BigDecimal allProfitAndLose = new BigDecimal("0");
        BigDecimal allDepositAmt = new BigDecimal("0");
        for (UserFuturesPosition userFuturesPosition : userFuturesPositions) {


            FuturesVO futuresVO = this.iStockFuturesService.querySingleMarket(userFuturesPosition.getFuturesGid());
            BigDecimal nowPrice = new BigDecimal(futuresVO.getNowPrice());


            if (nowPrice.compareTo(new BigDecimal("0")) != 0) {


                FuturesPositionProfitVO futuresPositionProfitVO = getFuturesPositionProfitVO(userFuturesPosition);


                allDepositAmt = allDepositAmt.add(userFuturesPosition.getAllDepositAmt());


                BigDecimal usd_profit = futuresPositionProfitVO.getAllProfitAndLose();

                ServerResponse serverResponse = this.iStockFuturesService.getExchangeRate(userFuturesPosition.getCoinCode());
                BigDecimal coinRate = new BigDecimal("0");
                if (serverResponse.isSuccess()) {
                    coinRate = (BigDecimal) serverResponse.getData();
                }


                usd_profit = usd_profit.multiply(coinRate);

                allProfitAndLose = allProfitAndLose.add(usd_profit);
                continue;
            }
            log.info("查询所有持仓单的总盈亏，现价返回0，当前为集合竞价");
        }


        FuturesPositionVO futuresPositionVO = new FuturesPositionVO();
        futuresPositionVO.setAllFuturesProfitAndLose(allProfitAndLose);
        futuresPositionVO.setAllFuturesDepositAmt(allDepositAmt);
        return futuresPositionVO;
    }


    public ServerResponse getFuturesIncome(Integer agentId, Integer positionType, String beginTime, String endTime) {
        if (StringUtils.isBlank(beginTime) || StringUtils.isBlank(endTime)) {
            return ServerResponse.createByErrorMsg("时间不能为空");
        }

        Timestamp begin_time = null;
        if (StringUtils.isNotBlank(beginTime)) {
            begin_time = DateTimeUtil.searchStrToTimestamp(beginTime);
        }
        Timestamp end_time = null;
        if (StringUtils.isNotBlank(endTime)) {
            end_time = DateTimeUtil.searchStrToTimestamp(endTime);
        }


        List<UserFuturesPosition> userFuturesPositions = this.userFuturesPositionMapper.listByAdmin(positionType, Integer.valueOf(1), null, agentId, null, begin_time, end_time);
        log.info("查询到反佣周期内 futures 订单数：{}", Integer.valueOf(userFuturesPositions.size()));

        BigDecimal order_fee_amt = new BigDecimal("0");
        BigDecimal order_profit_and_lose = new BigDecimal("0");
        BigDecimal order_profit_and_lose_all = new BigDecimal("0");

        for (UserFuturesPosition userFuturesPosition : userFuturesPositions) {
            order_fee_amt = order_fee_amt.add(userFuturesPosition.getOrderFee());

            order_profit_and_lose = order_profit_and_lose.add(userFuturesPosition.getProfitAndLose());

            order_profit_and_lose_all = order_profit_and_lose_all.add(userFuturesPosition.getAllProfitAndLose());
        }

        AgentIncomeVO agentIncomeVO = new AgentIncomeVO();
        agentIncomeVO.setOrderSize(Integer.valueOf(userFuturesPositions.size()));
        agentIncomeVO.setOrderFeeAmt(order_fee_amt);
        agentIncomeVO.setOrderProfitAndLose(order_profit_and_lose);
        agentIncomeVO.setOrderAllAmt(order_profit_and_lose_all);

        return ServerResponse.createBySuccess(agentIncomeVO);
    }


    private UserFuturesPositionVO assembleUserFuturesPositionVO(UserFuturesPosition userFuturesPosition) {
        UserFuturesPositionVO userFuturesPositionVO = new UserFuturesPositionVO();

        BigDecimal eachPoint = new BigDecimal("1");
        StockFutures stockFutures = stockFuturesMapper.selectFuturesByCode(userFuturesPosition.getFuturesCode());
        if(stockFutures != null){
            eachPoint = stockFutures.getEachPoint();
        }

        userFuturesPositionVO.setId(userFuturesPosition.getId());
        userFuturesPositionVO.setPositionType(userFuturesPosition.getPositionType());
        userFuturesPositionVO.setPositionSn(userFuturesPosition.getPositionSn());
        userFuturesPositionVO.setUserId(userFuturesPosition.getUserId());
        userFuturesPositionVO.setRealName(userFuturesPosition.getRealName());
        userFuturesPositionVO.setAgentId(userFuturesPosition.getAgentId());
        userFuturesPositionVO.setFuturesName(userFuturesPosition.getFuturesName());
        userFuturesPositionVO.setFuturesCode(userFuturesPosition.getFuturesCode());
        userFuturesPositionVO.setFuturesGid(userFuturesPosition.getFuturesGid());
        userFuturesPositionVO.setBuyOrderTime(userFuturesPosition.getBuyOrderTime());
        userFuturesPositionVO.setBuyOrderPrice(userFuturesPosition.getBuyOrderPrice());
        userFuturesPositionVO.setSellOrderTime(userFuturesPosition.getSellOrderTime());
        userFuturesPositionVO.setSellOrderPrice(userFuturesPosition.getSellOrderPrice());
        userFuturesPositionVO.setOrderDirection(userFuturesPosition.getOrderDirection());
        userFuturesPositionVO.setOrderNum(userFuturesPosition.getOrderNum());
        userFuturesPositionVO.setFuturesStandard(userFuturesPosition.getFuturesStandard());
        userFuturesPositionVO.setFuturesUnit(userFuturesPosition.getFuturesUnit());
        userFuturesPositionVO.setAllDepositAmt(userFuturesPosition.getAllDepositAmt());
        userFuturesPositionVO.setOrderFee(userFuturesPosition.getOrderFee());
        userFuturesPositionVO.setLockMsg(userFuturesPosition.getLockMsg());
        userFuturesPositionVO.setIsLock(userFuturesPosition.getIsLock());

        userFuturesPositionVO.setCoinCode(userFuturesPosition.getCoinCode());
        userFuturesPositionVO.setBuyRate(userFuturesPosition.getBuyRate());
        userFuturesPositionVO.setSellRate(userFuturesPosition.getSellRate());


        FuturesPositionProfitVO futuresPositionProfitVO = getFuturesPositionProfitVO(userFuturesPosition);

        userFuturesPositionVO.setAllProfitAndLose(futuresPositionProfitVO.getAllProfitAndLose());
        userFuturesPositionVO.setProfitAndLose(futuresPositionProfitVO.getProfitAndLose());

        userFuturesPositionVO.setNowPrice(futuresPositionProfitVO.getNowPrice());

        userFuturesPositionVO.setCoinRate(futuresPositionProfitVO.getCoinRate());
        userFuturesPositionVO.setEachPoint(eachPoint);

        return userFuturesPositionVO;
    }

    private AgentFuturesPositionVO assembleAgentFuturesPositionVO(UserFuturesPosition userFuturesPosition) {
        AgentFuturesPositionVO agentFuturesPositionVO = new AgentFuturesPositionVO();

        agentFuturesPositionVO.setId(userFuturesPosition.getId());
        agentFuturesPositionVO.setPositionType(userFuturesPosition.getPositionType());
        agentFuturesPositionVO.setPositionSn(userFuturesPosition.getPositionSn());
        agentFuturesPositionVO.setUserId(userFuturesPosition.getUserId());
        agentFuturesPositionVO.setRealName(userFuturesPosition.getRealName());
        agentFuturesPositionVO.setAgentId(userFuturesPosition.getAgentId());
        agentFuturesPositionVO.setFuturesName(userFuturesPosition.getFuturesName());
        agentFuturesPositionVO.setFuturesCode(userFuturesPosition.getFuturesCode());
        agentFuturesPositionVO.setFuturesGid(userFuturesPosition.getFuturesGid());
        agentFuturesPositionVO.setBuyOrderTime(userFuturesPosition.getBuyOrderTime());
        agentFuturesPositionVO.setBuyOrderPrice(userFuturesPosition.getBuyOrderPrice());
        agentFuturesPositionVO.setSellOrderTime(userFuturesPosition.getSellOrderTime());
        agentFuturesPositionVO.setSellOrderPrice(userFuturesPosition.getSellOrderPrice());
        agentFuturesPositionVO.setOrderDirection(userFuturesPosition.getOrderDirection());
        agentFuturesPositionVO.setOrderNum(userFuturesPosition.getOrderNum());
        agentFuturesPositionVO.setFuturesStandard(userFuturesPosition.getFuturesStandard());
        agentFuturesPositionVO.setFuturesUnit(userFuturesPosition.getFuturesUnit());
        agentFuturesPositionVO.setAllDepositAmt(userFuturesPosition.getAllDepositAmt());
        agentFuturesPositionVO.setOrderFee(userFuturesPosition.getOrderFee());
        agentFuturesPositionVO.setLockMsg(userFuturesPosition.getLockMsg());
        agentFuturesPositionVO.setIsLock(userFuturesPosition.getIsLock());

        agentFuturesPositionVO.setCoinCode(userFuturesPosition.getCoinCode());
        agentFuturesPositionVO.setBuyRate(userFuturesPosition.getBuyRate());
        agentFuturesPositionVO.setSellRate(userFuturesPosition.getSellRate());

        FuturesPositionProfitVO futuresPositionProfitVO = getFuturesPositionProfitVO(userFuturesPosition);

        agentFuturesPositionVO.setAllProfitAndLose(futuresPositionProfitVO.getAllProfitAndLose());
        agentFuturesPositionVO.setProfitAndLose(futuresPositionProfitVO.getProfitAndLose());

        agentFuturesPositionVO.setNowPrice(futuresPositionProfitVO.getNowPrice());
        agentFuturesPositionVO.setCoinRate(futuresPositionProfitVO.getCoinRate());

        return agentFuturesPositionVO;
    }

    private AdminFuturesPositionVO assembleAdminFuturesPositionVO(UserFuturesPosition userFuturesPosition) {
        AdminFuturesPositionVO adminFuturesPositionVO = new AdminFuturesPositionVO();

        adminFuturesPositionVO.setId(userFuturesPosition.getId());
        adminFuturesPositionVO.setPositionType(userFuturesPosition.getPositionType());
        adminFuturesPositionVO.setPositionSn(userFuturesPosition.getPositionSn());
        adminFuturesPositionVO.setUserId(userFuturesPosition.getUserId());
        adminFuturesPositionVO.setRealName(userFuturesPosition.getRealName());
        adminFuturesPositionVO.setAgentId(userFuturesPosition.getAgentId());
        adminFuturesPositionVO.setFuturesName(userFuturesPosition.getFuturesName());
        adminFuturesPositionVO.setFuturesCode(userFuturesPosition.getFuturesCode());
        adminFuturesPositionVO.setFuturesGid(userFuturesPosition.getFuturesGid());
        adminFuturesPositionVO.setBuyOrderTime(userFuturesPosition.getBuyOrderTime());
        adminFuturesPositionVO.setBuyOrderPrice(userFuturesPosition.getBuyOrderPrice());
        adminFuturesPositionVO.setSellOrderTime(userFuturesPosition.getSellOrderTime());
        adminFuturesPositionVO.setSellOrderPrice(userFuturesPosition.getSellOrderPrice());
        adminFuturesPositionVO.setOrderDirection(userFuturesPosition.getOrderDirection());
        adminFuturesPositionVO.setOrderNum(userFuturesPosition.getOrderNum());
        adminFuturesPositionVO.setFuturesStandard(userFuturesPosition.getFuturesStandard());
        adminFuturesPositionVO.setFuturesUnit(userFuturesPosition.getFuturesUnit());
        adminFuturesPositionVO.setAllDepositAmt(userFuturesPosition.getAllDepositAmt());
        adminFuturesPositionVO.setOrderFee(userFuturesPosition.getOrderFee());
        adminFuturesPositionVO.setLockMsg(userFuturesPosition.getLockMsg());
        adminFuturesPositionVO.setIsLock(userFuturesPosition.getIsLock());

        adminFuturesPositionVO.setCoinCode(userFuturesPosition.getCoinCode());
        adminFuturesPositionVO.setBuyRate(userFuturesPosition.getBuyRate());
        adminFuturesPositionVO.setSellRate(userFuturesPosition.getSellRate());


        FuturesPositionProfitVO futuresPositionProfitVO = getFuturesPositionProfitVO(userFuturesPosition);

        adminFuturesPositionVO.setAllProfitAndLose(futuresPositionProfitVO.getAllProfitAndLose());
        adminFuturesPositionVO.setProfitAndLose(futuresPositionProfitVO.getProfitAndLose());

        adminFuturesPositionVO.setNowPrice(futuresPositionProfitVO.getNowPrice());

        adminFuturesPositionVO.setCoinRate(futuresPositionProfitVO.getCoinRate());

        return adminFuturesPositionVO;
    }


    private FuturesPositionProfitVO getFuturesPositionProfitVO(UserFuturesPosition userFuturesPosition) {
        BigDecimal profitAndLose = new BigDecimal("0");
        BigDecimal allProfitAndLose = new BigDecimal("0");
        String nowPrice = "";
        BigDecimal coinRate = new BigDecimal("0");

        //每手浮动价格
        StockFutures stockFutures = stockFuturesMapper.selectFuturesByCode(userFuturesPosition.getFuturesCode());
        BigDecimal eachPoint = new BigDecimal("1");
        if(stockFutures!=null && stockFutures.getEachPoint() != null){
            eachPoint = stockFutures.getEachPoint();
        }

        if (userFuturesPosition.getSellOrderPrice() != null) {

            BigDecimal subPrice = userFuturesPosition.getSellOrderPrice().subtract(userFuturesPosition.getBuyOrderPrice());

            profitAndLose = subPrice.multiply(eachPoint).multiply(new BigDecimal(userFuturesPosition.getOrderNum().intValue()));
            if ("买跌".equals(userFuturesPosition.getOrderDirection())) {
                profitAndLose = profitAndLose.negate();
            }

            allProfitAndLose = profitAndLose.subtract(userFuturesPosition.getOrderFee());

            coinRate = userFuturesPosition.getSellRate();

        } else {

            FuturesVO futuresVO = this.iStockFuturesService.querySingleMarket(userFuturesPosition.getFuturesGid());
            nowPrice = futuresVO.getNowPrice();

            BigDecimal subPrice = (new BigDecimal(nowPrice)).subtract(userFuturesPosition.getBuyOrderPrice());

            profitAndLose = subPrice.multiply(eachPoint).multiply(new BigDecimal(userFuturesPosition.getOrderNum().intValue()));
            if ("买跌".equals(userFuturesPosition.getOrderDirection())) {
                profitAndLose = profitAndLose.negate();
            }

            allProfitAndLose = profitAndLose.subtract(userFuturesPosition.getOrderFee());


            ServerResponse serverResponse = this.iStockFuturesService.getExchangeRate(userFuturesPosition.getCoinCode());
            if (serverResponse.isSuccess()) {
                coinRate = (BigDecimal) serverResponse.getData();
            }
        }


        FuturesPositionProfitVO futuresPositionProfitVO = new FuturesPositionProfitVO();
        futuresPositionProfitVO.setProfitAndLose(profitAndLose);
        futuresPositionProfitVO.setAllProfitAndLose(allProfitAndLose);
        futuresPositionProfitVO.setNowPrice(nowPrice);
        futuresPositionProfitVO.setCoinRate(coinRate);

        return futuresPositionProfitVO;
    }



}
