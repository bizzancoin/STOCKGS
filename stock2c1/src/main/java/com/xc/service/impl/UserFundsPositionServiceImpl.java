package com.xc.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.xc.common.ServerResponse;
import com.xc.dao.UserCashDetailMapper;
import com.xc.dao.UserFundsPositionMapper;
import com.xc.dao.UserMapper;
import com.xc.pojo.*;
import com.xc.service.*;
import com.xc.utils.DateTimeUtil;
import com.xc.utils.KeyUtils;
import com.xc.utils.stock.BuyAndSellUtils;
import com.xc.utils.stock.GeneratePosition;
import com.xc.utils.stock.sina.SinaStockApi;
import com.xc.vo.position.PositionProfitVO;
import com.xc.vo.position.UserPositionVO;
import com.xc.vo.stock.StockListVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 分仓交易
 * @author lr
 * @date 2020/07/24
 */
@Service("IUserFundsPositionService")
public class UserFundsPositionServiceImpl implements IUserFundsPositionService {
    private static final Logger log = LoggerFactory.getLogger(UserPositionServiceImpl.class);

    @Resource
    private UserFundsPositionMapper userFundsPositionMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserCashDetailMapper userCashDetailMapper;

    @Autowired
    ISiteProductService iSiteProductService;
    @Autowired
    IUserService iUserService;
    @Autowired
    ISiteSettingService iSiteSettingService;
    @Autowired
    IStockService iStockService;
    @Autowired
    ISiteSpreadService iSiteSpreadService;
    @Autowired
    IAgentAgencyFeeService iAgentAgencyFeeService;


    @Override
    public ServerResponse insert(UserFundsPosition model, HttpServletRequest request) {
        int ret = 0;
        if (model == null) {
            return ServerResponse.createByErrorMsg("下单异常，请稍后再试！");
        }
        ret = userFundsPositionMapper.insert(model);
        if(ret>0){
            return ServerResponse.createBySuccessMsg("下单成功！");
        } else {
            return ServerResponse.createByErrorMsg("下单失败，请稍后再试！");
        }
    }

    @Override
    public int update(UserFundsPosition model) {
        int ret = userFundsPositionMapper.update(model);
        return ret>0 ? ret: 0;
    }

    /**
     * 分仓交易-保存
     */
    @Override
    public ServerResponse save(UserFundsPosition model) {
        int ret = 0;
        if(model!=null && model.getId()>0){
            ret = userFundsPositionMapper.update(model);
        } else{
            ret = userFundsPositionMapper.insert(model);
        }
        if(ret>0){
            return ServerResponse.createBySuccessMsg("操作成功");
        }
        return ServerResponse.createByErrorMsg("操作失败");
    }

    /*分仓交易-查询列表*/
    @Override
    public ServerResponse<PageInfo> getList(int pageNum, int pageSize, String keyword, HttpServletRequest request){
        PageHelper.startPage(pageNum, pageSize);
        List<UserFundsPosition> listData = this.userFundsPositionMapper.pageList(pageNum, pageSize, keyword);
        PageInfo pageInfo = new PageInfo(listData);
        pageInfo.setList(listData);
        return ServerResponse.createBySuccess(pageInfo);
    }

    /*分仓交易-查询详情*/
    @Override
    public ServerResponse getDetail(int id) {
        return ServerResponse.createBySuccess(this.userFundsPositionMapper.load(id));
    }


    private boolean isTopBoard(Stock stock){
        //判断是否是一字板股票
        String stockPlate = stock.getStockPlate();
        boolean isTopBoard=false;
        if(!StringUtils.isEmpty(stockPlate)){
            if(stockPlate.equals("一字板")){
                isTopBoard=true;
            }
        }
        return isTopBoard;
    }

    /**
     * 分仓交易-入仓
     */
    @Transactional
    public ServerResponse buyFunds(Integer stockId, Integer buyNum, Integer buyType, Integer lever, Integer subaccountNumber, HttpServletRequest request)  throws Exception {
        // 判断周末不能买
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        int weekday = c.get(Calendar.DAY_OF_WEEK);
        /*实名认证开关开启*/
        SiteProduct siteProduct = iSiteProductService.getProductSetting();
        User user = this.iUserService.getCurrentRefreshUser(request);
        if (siteProduct.getRealNameDisplay() && (StringUtils.isBlank(user.getRealName()) || StringUtils.isBlank(user.getIdCard()))) {
            return ServerResponse.createByErrorMsg("下单失败，请先实名认证");
        }
        if(siteProduct.getHolidayDisplay()){
            return ServerResponse.createByErrorMsg("周末或节假日不能交易！");
        }
        BigDecimal user_enable_amt = user.getEnableAmt();
        log.info("用户 {} 下单，股票id = {} ，数量 = {} , 方向 = {} , 杠杆 = {}", new Object[]{user
                .getId(), stockId, buyNum, buyType, lever});
        if (siteProduct.getRealNameDisplay() && user.getIsLock().intValue() == 1) {
            return ServerResponse.createByErrorMsg("下单失败，账户已被锁定");
        }

        SiteSetting siteSetting = this.iSiteSettingService.getSiteSetting();
        if (siteSetting == null) {
            log.error("下单出错，网站设置表不存在");
            return ServerResponse.createByErrorMsg("下单失败，系统设置错误");
        }

        String am_begin = siteSetting.getTransAmBegin();
        String am_end = siteSetting.getTransAmEnd();
        String pm_begin = siteSetting.getTransPmBegin();
        String pm_end = siteSetting.getTransPmEnd();
        boolean am_flag = BuyAndSellUtils.isTransTime(am_begin, am_end);
        boolean pm_flag = BuyAndSellUtils.isTransTime(pm_begin, pm_end);
        log.info("是否在上午交易时间 = {} 是否在下午交易时间 = {}", Boolean.valueOf(am_flag), Boolean.valueOf(pm_flag));

        if (!am_flag && !pm_flag) {
            return ServerResponse.createByErrorMsg("下单失败，不在交易时段内");
        }

        Stock stock = null;
        ServerResponse stock_res = this.iStockService.findStockById(stockId);
        if (!stock_res.isSuccess()) {
            return ServerResponse.createByErrorMsg("下单失败，股票代码错误");
        }
        stock = (Stock) stock_res.getData();

        if (stock.getIsLock().intValue() != 0) {
            return ServerResponse.createByErrorMsg("下单失败，当前股票不能交易");
        }

        /*List dbPosition = findPositionByStockCodeAndTimes(siteSetting.getBuySameTimes().intValue(), stock
                .getStockCode(), user.getId());
        if (dbPosition.size() >= siteSetting.getBuySameNums().intValue()) {
            return ServerResponse.createByErrorMsg("频繁交易," + siteSetting.getBuySameTimes() + "分钟内同一股票持仓不得超过" + siteSetting
                    .getBuySameNums() + "条");
        }

        Integer transNum = findPositionNumByTimes(siteSetting.getBuyNumTimes().intValue(), user.getId());
        if (transNum.intValue() / 100 >= siteSetting.getBuyNumLots().intValue()) {
            return ServerResponse.createByErrorMsg("频繁交易," + siteSetting
                    .getBuyNumTimes() + "分钟内不能超过" + siteSetting.getBuyNumLots() + "手");
        }*/

        if (buyNum.intValue() < siteSetting.getBuyMinNum().intValue()) {
            return ServerResponse.createByErrorMsg("下单失败，购买数量小于" + siteSetting
                    .getBuyMinNum() + "股");
        }
        if (buyNum.intValue() > siteSetting.getBuyMaxNum().intValue()) {
            return ServerResponse.createByErrorMsg("下单失败，购买数量大于" + siteSetting
                    .getBuyMaxNum() + "股");
        }


        StockListVO stockListVO = SinaStockApi.assembleStockListVO(SinaStockApi.getSinaStock(stock.getStockGid()));
        BigDecimal now_price = new BigDecimal(stockListVO.getNowPrice());

        if (now_price.compareTo(new BigDecimal("0")) == 0) {
            return ServerResponse.createByErrorMsg("报价0，请稍后再试");
        }


        double stock_crease = stockListVO.getHcrate().doubleValue();


        BigDecimal maxRisePercent = new BigDecimal("0");
        if (stock.getStockPlate() != null||stock.getStockCode().startsWith("688")) {
            maxRisePercent = new BigDecimal("0.2");
            log.info("【科创股票】");
        } else {
            maxRisePercent = new BigDecimal("0.1");
            log.info("【普通A股】");
        }
        BigDecimal zsPrice = new BigDecimal(stockListVO.getPreclose_px());

        BigDecimal ztPrice = zsPrice.multiply(maxRisePercent).add(zsPrice);
        ztPrice = ztPrice.setScale(2, 4);
        BigDecimal chaPrice = ztPrice.subtract(zsPrice);

        BigDecimal ztRate = chaPrice.multiply(new BigDecimal("100")).divide(zsPrice, 2, 4);

        log.info("当前涨跌幅 = {} % , 涨停幅度 = {} %", Double.valueOf(stock_crease), ztRate);




        if ((new BigDecimal(String.valueOf(stock_crease))).compareTo(ztRate) == 0 && buyType
                .intValue() == 0&&!isTopBoard(stock)) {
            return ServerResponse.createByErrorMsg("当前股票已涨停不能买涨");
        }


        if (stock.getStockPlate() == null) {

            int maxcrease = siteSetting.getCreaseMaxPercent().intValue();
            if (stock_crease > 0.0D &&
                    stock_crease >= maxcrease) {
                return ServerResponse.createByErrorMsg("下单失败，股票当前涨幅:" + stock_crease + ",大于最大涨幅:" + maxcrease);
            }


            if (stock_crease < 0.0D &&
                    Math.abs(stock_crease) > maxcrease) {
                return ServerResponse.createByErrorMsg("下单失败，股票当前跌幅:" + stock_crease + ",大于最大跌幅:" + maxcrease);

            }

        } else {

            int maxcrease = siteSetting.getKcCreaseMaxPercent().intValue();
            if (stock_crease > 0.0D &&
                    stock_crease >= maxcrease) {
                return ServerResponse.createByErrorMsg("下单失败，科创股当前涨幅:" + stock_crease + ",大于最大涨幅:" + maxcrease);
            }


            if (stock_crease < 0.0D &&
                    Math.abs(stock_crease) > maxcrease) {
                return ServerResponse.createByErrorMsg("下单失败，科创股当前跌幅:" + stock_crease + ",大于最大跌幅:" + maxcrease);
            }
        }


        ServerResponse serverResponse = this.iStockService.selectRateByDaysAndStockCode(stock
                .getStockCode(), siteSetting.getStockDays().intValue());
        if (!serverResponse.isSuccess()) {
            return serverResponse;
        }
        BigDecimal daysRate = (BigDecimal) serverResponse.getData();
        log.info("股票 {} ， {} 天内 涨幅 {} , 设置的涨幅 = {}", new Object[]{stock.getStockCode(), siteSetting
                .getStockDays(), daysRate, siteSetting.getStockRate()});

        if (daysRate != null &&
                siteSetting.getStockRate().compareTo(daysRate) == -1) {
            return serverResponse.createByErrorMsg(siteSetting.getStockDays() + "天内涨幅超过 " + siteSetting
                    .getStockRate() + "不能交易");
        }


        BigDecimal buy_amt = now_price.multiply(new BigDecimal(buyNum.intValue())).divide(new BigDecimal(lever.intValue())).setScale(2, 4);


        BigDecimal buy_amt_autual = now_price.multiply(new BigDecimal(buyNum.intValue())).divide(new BigDecimal(lever.intValue()), 2, 4);


        int compareInt = buy_amt_autual.compareTo(new BigDecimal(siteSetting.getBuyMinAmt().intValue()));
        if (compareInt == -1) {
            return ServerResponse.createByErrorMsg("下单失败，购买金额小于" + siteSetting
                    .getBuyMinAmt() + "元");
        }


        BigDecimal max_buy_amt = user_enable_amt.multiply(siteSetting.getBuyMaxAmtPercent());
        int compareCwInt = buy_amt_autual.compareTo(max_buy_amt);
        if (compareCwInt == 1) {
            return ServerResponse.createByErrorMsg("下单失败，不能超过可用资金的" + siteSetting
                    .getBuyMaxAmtPercent().multiply(new BigDecimal("100")) + "%");
        }


        int compareUserAmtInt = user_enable_amt.compareTo(buy_amt_autual);
        log.info("用户可用金额 = {}  实际购买金额 =  {}", user_enable_amt, buy_amt_autual);
        log.info("比较 用户金额 和 实际 购买金额 =  {}", Integer.valueOf(compareUserAmtInt));
        if (compareUserAmtInt == -1) {
            return ServerResponse.createByErrorMsg("下单失败，融资可用金额小于" + buy_amt_autual + "元");
        }

        if (user.getUserIndexAmt().compareTo(new BigDecimal("0")) == -1) {
            return ServerResponse.createByErrorMsg("失败，指数总资金小于0");
        }
        if (user.getUserFutAmt().compareTo(new BigDecimal("0")) == -1) {
            return ServerResponse.createByErrorMsg("失败，期货总资金小于0");
        }

        UserFundsPosition userPosition = new UserFundsPosition();
        userPosition.setPositionType(user.getAccountType());
        userPosition.setPositionSn(KeyUtils.getUniqueKey());
        userPosition.setUserId(user.getId());
        userPosition.setNickName(user.getRealName());
        userPosition.setAgentId(user.getAgentId());
        userPosition.setStockId(stock.getId());
        userPosition.setStockCode(stock.getStockCode());
        userPosition.setStockName(stock.getStockName());
        userPosition.setStockGid(stock.getStockGid());
        userPosition.setStockSpell(stock.getStockSpell());
        userPosition.setBuyOrderId(GeneratePosition.getPositionId());
        userPosition.setBuyOrderTime(new Date());
        userPosition.setBuyOrderPrice(now_price);
        userPosition.setOrderDirection((buyType.intValue() == 0) ? "买涨" : "买跌");
        userPosition.setOrderNum(buyNum);
        userPosition.setSubaccountNumber(subaccountNumber);

        if (stock.getStockPlate() != null) {
            userPosition.setStockPlate(stock.getStockPlate());
        }
        userPosition.setIsLock(Integer.valueOf(0));
        userPosition.setOrderLever(lever);
        userPosition.setOrderTotalPrice(buy_amt);
        BigDecimal buy_fee_amt = buy_amt.multiply(siteSetting.getBuyFee()).setScale(2, 4);
        log.info("用户购买手续费（配资后总资金 * 百分比） = {}", buy_fee_amt);
        userPosition.setOrderFee(buy_fee_amt);


        BigDecimal buy_yhs_amt = buy_amt.multiply(siteSetting.getDutyFee()).setScale(2, 4);
        log.info("用户购买印花税（配资后总资金 * 百分比） = {}", buy_yhs_amt);
        userPosition.setOrderSpread(buy_yhs_amt);

        SiteSpread siteSpread = iSiteSpreadService.findSpreadRateOne(new BigDecimal(stock_crease), buy_amt, stock.getStockCode(), now_price);
        BigDecimal spread_rate_amt = buy_amt.multiply(siteSpread.getSpreadRate()).setScale(2, 4);
        log.info("用户购买点差费（配资后总资金 * 百分比{}） = {}", siteSpread.getSpreadRate(), spread_rate_amt);
        userPosition.setSpreadRatePrice(spread_rate_amt);


        BigDecimal profit_and_lose = new BigDecimal("0");
        userPosition.setProfitAndLose(profit_and_lose);


        BigDecimal all_profit_and_lose = profit_and_lose.subtract(buy_fee_amt).subtract(buy_yhs_amt).subtract(spread_rate_amt);
        userPosition.setAllProfitAndLose(all_profit_and_lose);


        userPosition.setOrderStayDays(Integer.valueOf(0));
        userPosition.setOrderStayFee(new BigDecimal("0"));

        int insertPositionCount = 0;
        this.userFundsPositionMapper.insert(userPosition);
        insertPositionCount = userPosition.getId();
        if (insertPositionCount > 0) {
            //修改用户可用余额= 当前余额-下单金额-买入手续费-印花税-点差费
            BigDecimal reckon_enable = user_enable_amt.subtract(buy_amt_autual).subtract(buy_fee_amt).subtract(buy_yhs_amt).subtract(spread_rate_amt);
            user.setEnableAmt(reckon_enable);
            /*int updateUserCount = this.userMapper.updateByPrimaryKeySelective(user);
            if (updateUserCount > 0) {
                log.info("【用户交易下单】修改用户金额成功");
            } else {
                log.error("用户交易下单】修改用户金额出错");
                throw new Exception("用户交易下单】修改用户金额出错");
            }*/
            //核算代理收入-入仓手续费
            //iAgentAgencyFeeService.AgencyFeeIncome(1,userPosition.getPositionSn());
            log.info("【用户交易下单】保存持仓记录成功");
        } else {
            log.error("用户交易下单】保存持仓记录出错");
            throw new Exception("用户交易下单】保存持仓记录出错");
        }

        return ServerResponse.createBySuccess("下单成功");
    }

    /*
    * 分仓交易-用户平仓操作
    * */
    @Transactional
    public ServerResponse sellFunds(String positionSn, int doType) throws Exception {
        log.info("【用户交易平仓】 positionSn = {} ， dotype = {}", positionSn, Integer.valueOf(doType));

        SiteSetting siteSetting = this.iSiteSettingService.getSiteSetting();
        if (siteSetting == null) {
            log.error("平仓出错，网站设置表不存在");
            return ServerResponse.createByErrorMsg("下单失败，系统设置错误");
        }

        if (doType != 0) {
            String am_begin = siteSetting.getTransAmBegin();
            String am_end = siteSetting.getTransAmEnd();
            String pm_begin = siteSetting.getTransPmBegin();
            String pm_end = siteSetting.getTransPmEnd();
            boolean am_flag = BuyAndSellUtils.isTransTime(am_begin, am_end);
            boolean pm_flag = BuyAndSellUtils.isTransTime(pm_begin, pm_end);
            log.info("是否在上午交易时间 = {} 是否在下午交易时间 = {}", Boolean.valueOf(am_flag), Boolean.valueOf(pm_flag));
            if (!am_flag && !pm_flag) {
                return ServerResponse.createByErrorMsg("平仓失败，不在交易时段内");
            }
        }

        UserFundsPosition userPosition = this.userFundsPositionMapper.findPositionBySn(positionSn);
        if (userPosition == null) {
            return ServerResponse.createByErrorMsg("平仓失败，订单不存在");
        }

        User user = this.userMapper.selectByPrimaryKey(userPosition.getUserId());
        /*实名认证开关开启*/
        SiteProduct siteProduct = iSiteProductService.getProductSetting();
        if (siteProduct.getRealNameDisplay() && user.getIsLock().intValue() == 1) {
            return ServerResponse.createByErrorMsg("平仓失败，用户已被锁定");
        }
        if(siteProduct.getHolidayDisplay()){
            return ServerResponse.createByErrorMsg("周末或节假日不能交易！");
        }

        if (userPosition.getSellOrderId() != null) {
            return ServerResponse.createByErrorMsg("平仓失败，此订单已平仓");
        }

        if (1 == userPosition.getIsLock().intValue()) {
            return ServerResponse.createByErrorMsg("平仓失败 " + userPosition.getLockMsg());
        }

        if (!DateTimeUtil.isCanSell(userPosition.getBuyOrderTime(), siteSetting.getCantSellTimes().intValue())) {
            return ServerResponse.createByErrorMsg(siteSetting.getCantSellTimes() + "分钟内不能平仓");
        }

        StockListVO stockListVO = SinaStockApi.assembleStockListVO(SinaStockApi.getSinaStock(userPosition.getStockGid()));

        BigDecimal now_price = new BigDecimal(stockListVO.getNowPrice());
        if (now_price.compareTo(new BigDecimal("0")) != 1) {
            log.error("股票 = {} 收到报价 = {}", userPosition.getStockName(), now_price);
            return ServerResponse.createByErrorMsg("报价0，平仓失败，请稍后再试");
        }

        double stock_crease = stockListVO.getHcrate().doubleValue();

        BigDecimal zsPrice = new BigDecimal(stockListVO.getPreclose_px());

        BigDecimal ztPrice = zsPrice.multiply(new BigDecimal("0.1")).add(zsPrice);
        ztPrice = ztPrice.setScale(2, 4);
        BigDecimal chaPrice = ztPrice.subtract(zsPrice);

        BigDecimal ztRate = chaPrice.multiply(new BigDecimal("100")).divide(zsPrice, 2, 4);

        ztRate = ztRate.negate();
        log.info("股票当前涨跌幅 = {} 跌停幅度 = {}", Double.valueOf(stock_crease), ztRate);
        if ((new BigDecimal(String.valueOf(stock_crease))).compareTo(ztRate) == 0 && "买涨"
                .equals(userPosition.getOrderDirection())) {
            return ServerResponse.createByErrorMsg("当前股票已跌停不能卖出");
        }

        Integer buy_num = userPosition.getOrderNum();

        BigDecimal all_buy_amt = userPosition.getOrderTotalPrice();
        BigDecimal all_sell_amt = now_price.multiply(new BigDecimal(buy_num.intValue()));

        BigDecimal profitLoss = new BigDecimal("0");
        if ("买涨".equals(userPosition.getOrderDirection())) {
            log.info("买卖方向：{}", "涨");

            profitLoss = all_sell_amt.subtract(all_buy_amt);
        } else {
            log.info("买卖方向：{}", "跌");
            profitLoss = all_buy_amt.subtract(all_sell_amt);
        }
        log.info("买入总金额 = {} , 卖出总金额 = {} , 盈亏 = {}", new Object[]{all_buy_amt, all_sell_amt, profitLoss});

        BigDecimal user_all_amt = user.getUserAmt();
        BigDecimal user_enable_amt = user.getEnableAmt();
        log.info("用户原本总资金 = {} , 可用 = {}", user_all_amt, user_enable_amt);

        BigDecimal buy_fee_amt = userPosition.getOrderFee();
        log.info("买入手续费 = {}", buy_fee_amt);

        BigDecimal orderSpread = userPosition.getOrderSpread();
        log.info("印花税 = {}", orderSpread);

        BigDecimal orderStayFee = userPosition.getOrderStayFee();
        log.info("留仓费 = {}", orderStayFee);

        BigDecimal spreadRatePrice = userPosition.getSpreadRatePrice();
        log.info("点差费 = {}", spreadRatePrice);

        BigDecimal sell_fee_amt = all_sell_amt.multiply(siteSetting.getSellFee()).setScale(2, 4);
        log.info("卖出手续费 = {}", sell_fee_amt);

        BigDecimal all_fee_amt = buy_fee_amt.add(sell_fee_amt).add(orderSpread).add(orderStayFee).add(spreadRatePrice);
        log.info("总的手续费费用 = {}", all_fee_amt);

        userPosition.setSellOrderId(GeneratePosition.getPositionId());
        userPosition.setSellOrderPrice(now_price);
        userPosition.setSellOrderTime(new Date());

        BigDecimal order_fee_all = buy_fee_amt.add(sell_fee_amt);
        userPosition.setOrderFee(order_fee_all);

        userPosition.setProfitAndLose(profitLoss);

        BigDecimal all_profit = profitLoss.subtract(all_fee_amt);
        userPosition.setAllProfitAndLose(all_profit);

        int updatePositionCount = this.userFundsPositionMapper.update(userPosition);
        if (updatePositionCount > 0) {
            log.info("【用户平仓】修改浮动盈亏记录成功");
        } else {
            log.error("用户平仓】修改浮动盈亏记录出错");
            throw new Exception("用户平仓】修改浮动盈亏记录出错");
        }

        BigDecimal freez_amt = all_buy_amt.divide(new BigDecimal(userPosition.getOrderLever().intValue()), 2, 4);

        BigDecimal reckon_all = user_all_amt.add(all_profit);
        BigDecimal reckon_enable = user_enable_amt.add(all_profit).add(freez_amt);

        log.info("用户平仓后的总资金  = {} , 可用资金 = {}", reckon_all, reckon_enable);
        user.setUserAmt(reckon_all);
        user.setEnableAmt(reckon_enable);
        /*int updateUserCount = this.userMapper.updateByPrimaryKeySelective(user);
        if (updateUserCount > 0) {
            log.info("【用户平仓】修改用户金额成功");
        } else {
            log.error("用户平仓】修改用户金额出错");
            throw new Exception("用户平仓】修改用户金额出错");
        }*/

        UserCashDetail ucd = new UserCashDetail();
        ucd.setPositionId(userPosition.getId());
        ucd.setAgentId(user.getAgentId());
        ucd.setAgentName(user.getAgentName());
        ucd.setUserId(user.getId());
        ucd.setUserName(user.getRealName());
        ucd.setDeType("配资总盈亏");
        ucd.setDeAmt(all_profit);
        ucd.setDeSummary("卖出股票，" + userPosition.getStockCode() + "/" + userPosition.getStockName() + ",占用本金：" + freez_amt + ",总手续费：" + all_fee_amt + ",留仓费：" + orderStayFee+ ",印花税：" + orderSpread + ",点差费：" + spreadRatePrice + ",盈亏：" + profitLoss + "，总盈亏：" + all_profit);

        ucd.setAddTime(new Date());
        ucd.setIsRead(Integer.valueOf(0));

        int insertSxfCount = this.userCashDetailMapper.insert(ucd);
        if (insertSxfCount > 0) {
            /*//核算代理收入-平仓手续费
            iAgentAgencyFeeService.AgencyFeeIncome(2,userPosition.getPositionSn());
            //核算代理收入-分红
            iAgentAgencyFeeService.AgencyFeeIncome(4,userPosition.getPositionSn());*/
            log.info("【用户平仓】保存明细记录成功");
        } else {
            log.error("用户平仓】保存明细记录出错");
            throw new Exception("用户平仓】保存明细记录出错");
        }

        return ServerResponse.createBySuccessMsg("平仓成功！");
    }


    /*
     * 分仓交易-查询所有平仓/持仓信息
     * */
    public ServerResponse findMyPositionByCodeAndSpell(String stockCode, String stockSpell, Integer state, HttpServletRequest request, int pageNum, int pageSize) {
        User user = this.iUserService.getCurrentUser(request);

        PageHelper.startPage(pageNum, pageSize);


        List<UserFundsPosition> userPositions = this.userFundsPositionMapper.findMyPositionByCodeAndSpell(user.getId(), stockCode, stockSpell, state);

        List<UserPositionVO> userPositionVOS = Lists.newArrayList();
        if (userPositions.size() > 0) {
            for (UserFundsPosition position : userPositions) {

                UserPositionVO userPositionVO = assembleUserPositionVO(position);
                userPositionVOS.add(userPositionVO);
            }
        }

        PageInfo pageInfo = new PageInfo(userPositions);
        pageInfo.setList(userPositionVOS);

        return ServerResponse.createBySuccess(pageInfo);
    }

    /*根据分仓配资代码查询用户最早入仓股票*/
    public ServerResponse findUserFundsPositionByCode(HttpServletRequest request, String fundsCode) {
        User user = this.iUserService.getCurrentRefreshUser(request);
        UserFundsPosition position = this.userFundsPositionMapper.findUserFundsPositionByCode(user.getId(), fundsCode);

        List<UserPositionVO> userPositionVOS = Lists.newArrayList();
        UserPositionVO userPositionVO = null;
        if(position != null){
            userPositionVO = assembleUserPositionVO(position);
        }
        userPositionVOS.add(userPositionVO);

        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(userPositionVOS);

        return ServerResponse.createBySuccess(pageInfo);
    }

    private UserPositionVO assembleUserPositionVO(UserFundsPosition position) {
        UserPositionVO userPositionVO = new UserPositionVO();

        userPositionVO.setId(position.getId());
        userPositionVO.setPositionType(position.getPositionType());
        userPositionVO.setPositionSn(position.getPositionSn());
        userPositionVO.setUserId(position.getUserId());
        userPositionVO.setNickName(position.getNickName());
        userPositionVO.setAgentId(position.getAgentId());
        userPositionVO.setStockName(position.getStockName());
        userPositionVO.setStockCode(position.getStockCode());
        userPositionVO.setStockGid(position.getStockGid());
        userPositionVO.setStockSpell(position.getStockSpell());
        userPositionVO.setBuyOrderId(position.getBuyOrderId());
        userPositionVO.setBuyOrderTime(position.getBuyOrderTime());
        userPositionVO.setBuyOrderPrice(position.getBuyOrderPrice());
        userPositionVO.setSellOrderId(position.getSellOrderId());
        userPositionVO.setSellOrderTime(position.getSellOrderTime());
        userPositionVO.setSellOrderPrice(position.getSellOrderPrice());
        /*userPositionVO.setProfitTargetPrice(position.getProfitTargetPrice());
        userPositionVO.setStopTargetPrice(position.getStopTargetPrice());*/
        userPositionVO.setOrderDirection(position.getOrderDirection());
        userPositionVO.setOrderNum(position.getOrderNum());
        userPositionVO.setOrderLever(position.getOrderLever());
        userPositionVO.setOrderTotalPrice(position.getOrderTotalPrice());
        userPositionVO.setOrderFee(position.getOrderFee());
        userPositionVO.setOrderSpread(position.getOrderSpread());
        userPositionVO.setOrderStayFee(position.getOrderStayFee());
        userPositionVO.setOrderStayDays(position.getOrderStayDays());

        userPositionVO.setStockPlate(position.getStockPlate());
        userPositionVO.setSpreadRatePrice(position.getSpreadRatePrice());

        PositionProfitVO positionProfitVO = getPositionProfitVO(position);
        userPositionVO.setProfitAndLose(positionProfitVO.getProfitAndLose());
        userPositionVO.setAllProfitAndLose(positionProfitVO.getAllProfitAndLose());
        userPositionVO.setNow_price(positionProfitVO.getNowPrice());


        return userPositionVO;
    }

    private PositionProfitVO getPositionProfitVO(UserFundsPosition position) {
        BigDecimal profitAndLose = new BigDecimal("0");
        BigDecimal allProfitAndLose = new BigDecimal("0");
        String nowPrice = "";

        if (position.getSellOrderId() != null) {

            BigDecimal subPrice = position.getSellOrderPrice().subtract(position.getBuyOrderPrice());
            profitAndLose = subPrice.multiply(new BigDecimal(position.getOrderNum().intValue()));
            if ("买跌".equals(position.getOrderDirection())) {
                profitAndLose = profitAndLose.negate();
            }


            allProfitAndLose = profitAndLose.subtract(position.getOrderFee()).subtract(position.getOrderSpread()).subtract(position.getOrderStayFee()).subtract(position.getSpreadRatePrice());
        } else {

            StockListVO stockListVO = SinaStockApi.assembleStockListVO(
                    SinaStockApi.getSinaStock(position.getStockGid()));
            nowPrice = stockListVO.getNowPrice();


            BigDecimal subPrice = (new BigDecimal(nowPrice)).subtract(position.getBuyOrderPrice());
            profitAndLose = subPrice.multiply(new BigDecimal(position.getOrderNum().intValue()));
            if ("买跌".equals(position.getOrderDirection())) {
                profitAndLose = profitAndLose.negate();
            }

            //总盈亏= 浮动盈亏 – 手续费 – 印花税 – 留仓费 – 点差费
            allProfitAndLose = profitAndLose.subtract(position.getOrderFee()).subtract(position.getOrderSpread()).subtract(position.getOrderStayFee()).subtract(position.getSpreadRatePrice());
        }
        PositionProfitVO positionProfitVO = new PositionProfitVO();
        positionProfitVO.setProfitAndLose(profitAndLose);
        positionProfitVO.setAllProfitAndLose(allProfitAndLose);
        positionProfitVO.setNowPrice(nowPrice);

        return positionProfitVO;
    }


}
