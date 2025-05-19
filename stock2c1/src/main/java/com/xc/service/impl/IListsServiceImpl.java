package com.xc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.xc.common.ServerResponse;
import com.xc.dao.*;
import com.xc.pojo.*;
import com.xc.service.*;
import com.xc.utils.DateTimeUtil;
import com.xc.utils.KeyUtils;
import com.xc.utils.stock.GeneratePosition;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IListsServiceImpl implements IListsService {


    private static final Logger log = LoggerFactory.getLogger(IListsServiceImpl.class);

    @Autowired
    private ListsMapper listsMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ISiteMessageService iSiteMessageService;

    @Autowired
    IUserService iUserService;

    @Autowired
    UserPositionMapper userPositionMapper;

    @Autowired
    IStockService iStockService;

    @Autowired
    INewListService iNewListService;

    @Autowired
    UserCashDetailMapper userCashDetailMapper;

    @Autowired
    IAgentUserService iAgentUserService;

    @Autowired
    LocaleMessageSourceService messageSourceService;

    @Autowired
    StockMapper stockMapper;


    @Override
    public Lists save(Lists newStockApply) {
        listsMapper.insertSelective(newStockApply);
        return newStockApply;
    }

    @Override
    public void update(Lists newStockApply) {
        listsMapper.updateByPrimaryKeySelective(newStockApply);
    }

    @Override
    public void deleteById(Lists lists) {
        listsMapper.deleteByPrimaryKey(lists.getListsId());
    }

    @Override
    public PageInfo listByQueryWordsByPage(String agent, String zname, String phone, String xgname, String codes, String bzj, Short zts, String mrsj_start, String mrsj_end, String nums, Integer pageNo, Integer pageSize) {
        ListsExample listsExample = new ListsExample();
        ListsExample.Criteria criteria = listsExample.createCriteria();
        if (StringUtil.isNotEmpty(agent)) {
            criteria.andAgentEqualTo(agent);
        }
        if (StringUtil.isNotEmpty(zname)) {
            criteria.andZnameEqualTo(zname);
        }
        if (StringUtil.isNotEmpty(phone)) {
            criteria.andPhoneEqualTo(phone);
        }
        if (StringUtil.isNotEmpty(xgname)) {
            criteria.andXgnameEqualTo(xgname);
        }
        if (StringUtil.isNotEmpty(codes)) {
            criteria.andCodesEqualTo(codes);
        }
        if (StringUtil.isNotEmpty(bzj)) {
            criteria.andBzjEqualTo(bzj);
        }
        if (!ObjectUtils.isEmpty(zts)) {
            criteria.andZtsEqualTo(zts);
        }

        if (StringUtil.isNotEmpty(mrsj_start)) {
            criteria.andMrsjGreaterThanOrEqualTo(mrsj_start);
        }
        if (StringUtil.isNotEmpty(mrsj_end)) {
            criteria.andMrsjLessThanOrEqualTo(mrsj_end);
        }

        if (StringUtil.isNotEmpty(nums)) {
            criteria.andNumsEqualTo(nums);
        }
        listsExample.setOrderByClause("lists_id DESC");
        PageHelper.startPage(pageNo, pageSize);
        return new PageInfo(listsMapper.selectByExample(listsExample));
    }


    @Override
    public PageInfo listAgentByQueryWordsByPage(String agent, String zname, String phone, String xgname, String codes, String bzj, Short zts, String mrsj_start, String mrsj_end, String nums, Integer pageNo, Integer pageSize, HttpServletRequest request) {

        AgentUser currentAgent = this.iAgentUserService.getCurrentAgent(request);
        Integer agentId=currentAgent.getId();
        //查询当前代理的下级用户数据
        PageHelper.startPage(pageNo, pageSize);
        List<User> users = this.userMapper.listByAgent(zname, phone, agentId, null);
        List<String> userPhoneList = users.stream().map(u -> u.getPhone()).collect(Collectors.toList());

        ListsExample listsExample = new ListsExample();
        ListsExample.Criteria criteria = listsExample.createCriteria();
        if (StringUtil.isNotEmpty(agent)) {
            criteria.andAgentEqualTo(agent);
        }
        if (StringUtil.isNotEmpty(zname)) {
            criteria.andZnameEqualTo(zname);
        }
        if (StringUtil.isNotEmpty(phone)) {
            criteria.andPhoneEqualTo(phone);
        }
        if (StringUtil.isNotEmpty(xgname)) {
            criteria.andXgnameEqualTo(xgname);
        }
        if (StringUtil.isNotEmpty(codes)) {
            criteria.andCodesEqualTo(codes);
        }
        if (StringUtil.isNotEmpty(bzj)) {
            criteria.andBzjEqualTo(bzj);
        }
        if (!ObjectUtils.isEmpty(zts)) {
            criteria.andZtsEqualTo(zts);
        }
        if (StringUtil.isNotEmpty(mrsj_start)) {
            criteria.andMrsjGreaterThanOrEqualTo(mrsj_start);
        }
        if (StringUtil.isNotEmpty(mrsj_end)) {
            criteria.andMrsjLessThanOrEqualTo(mrsj_end);
        }

        if (StringUtil.isNotEmpty(nums)) {
            criteria.andNumsEqualTo(nums);
        }
        if(!CollectionUtils.isEmpty(userPhoneList)){
            criteria.andPhoneIn(userPhoneList);
            listsExample.setOrderByClause("lists_id DESC");
            return new PageInfo(listsMapper.selectByExample(listsExample));
        }
        //没有数据则返回空数据
        return new PageInfo(new ArrayList());
    }

    @Override
    public Lists getById(Lists lists) {
        return listsMapper.selectByPrimaryKey(lists.getListsId());
    }

    @Override
    public List<Lists> getMyWinNewStockByPhone(String phone) {
        ListsExample listsExample = new ListsExample();
        ListsExample.Criteria criteria = listsExample.createCriteria();
        criteria.andZtsEqualTo(Short.valueOf("1"));
        criteria.andPhoneEqualTo(phone);
        return listsMapper.selectByExample(listsExample);
    }


    @Override
    public void applyVerify(Lists lists) {
        User user = userMapper.findByPhone(lists.getPhone());
        if (lists.getZts().equals(Short.valueOf("1"))) {
            //给达到消息强平提醒用户推送消息
            SiteMessage siteMessage = new SiteMessage();
            siteMessage.setUserId(user.getId());
            siteMessage.setUserName(StringUtil.isEmpty(lists.getZname()) ? user.getPhone() : lists.getZname());
            siteMessage.setTypeName("新股申購");
            siteMessage.setStatus(1);
            String mrsj = lists.getMrsj();
           // Date date = DateTimeUtil.addDay(new Date(Long.parseLong(mrsj) * 1000), 2);
            String time=DateTimeUtil.dateToStr(new Date(),DateTimeUtil.YMD_FORMAT)+" 23:59前，請保證賬戶余額充足，請註意繳費時間，及時繳費。";
            siteMessage.setContent("【新股申購中簽】恭喜您，新股" + lists.getXgname() + " 申購中簽成功，中簽股數：" + lists.getWinums() + "，繳費金額："+lists.getBzj()+", 繳費時間："+time);
            siteMessage.setAddTime(DateTimeUtil.getCurrentDate());
            iSiteMessageService.insert(siteMessage);
        } else {
            SiteMessage siteMessage = new SiteMessage();
            siteMessage.setUserId(user.getId());
            siteMessage.setUserName(StringUtil.isEmpty(lists.getZname()) ? user.getPhone() : lists.getZname());
            siteMessage.setTypeName("新股申購");
            siteMessage.setStatus(1);
            siteMessage.setContent("【新股申購未中簽】很遺憾，您的新股 " + lists.getXgname() + " 申購本次未簽，申購金額：" + lists.getBzj() + "。");
            siteMessage.setAddTime(DateTimeUtil.getCurrentDate());
            iSiteMessageService.insert(siteMessage);
        }
        listsMapper.updateByPrimaryKeySelective(lists);
    }

    @Override
    public List<Lists> getMyApplyList(String phone) {
        ListsExample eaxmple = new ListsExample();
        ListsExample.Criteria criteria = eaxmple.createCriteria();
        criteria.andPhoneEqualTo(phone);
        return listsMapper.selectByExample(eaxmple);
    }

    @Override
    public List<Lists> getNewStockByCodeAndZts(String code, Short zts) {
        ListsExample eaxmple = new ListsExample();
        ListsExample.Criteria criteria = eaxmple.createCriteria();
        criteria.andCodesEqualTo(code);
        criteria.andZtsEqualTo(zts);
        return listsMapper.selectByExample(eaxmple);
    }

    @Override
    @Transactional
    public ServerResponse doFinshWinNewStock(Integer listsId, User user)  throws Exception {
        Lists lists1 = new Lists();
        lists1.setListsId(listsId);
        lists1 = this.getById(lists1);
        if (ObjectUtils.isEmpty(lists1)) {
            return ServerResponse.createByErrorMsg(messageSourceService.getMessage("ERR87"));
        }
        //判断股票是否上市
        String codes = lists1.getCodes();
        Stock stock = stockMapper.findStockByCode(codes);
        if(!ObjectUtils.isEmpty(stock)){
            return ServerResponse.createByErrorMsg("股票已經上市不能認購");
        }
        //若当前用户余额小于等于保证金
        log.info("保证金：" + lists1.getZts());
        BigDecimal bzj = new BigDecimal(lists1.getBzj());
        if (user.getEnableAmt().compareTo(bzj) < 0) {
            return ServerResponse.createByErrorMsg(messageSourceService.getMessage("ERR88"));
        }
        //判断是否为中签状态
        log.info(lists1 + "||" + lists1.getZts().equals(Short.valueOf("1")));
        if (lists1.getZts().equals(Short.valueOf("1"))) {
            lists1.setZts(Short.valueOf("4"));
            //判断股票是否保存在股票数据列表中
//            Stock stock = iStockService.findStockByCode(lists1.getCodes()).getData();
//            if (ObjectUtils.isEmpty(stock)) {
//                String codes = lists1.getCodes();
//                String plateType = "sh";
//                if (codes.startsWith("00") || codes.startsWith("30")) {
//                    plateType = "sz";
//                } else if (codes.startsWith("8") || codes.startsWith("4")) {
//                    plateType = "bj";
//                }
//                stock = (Stock) this.iStockService.addStock(lists1.getXgname(), lists1.getCodes()
//                        , plateType, "cc", 0, 0).getData();
//            }
            NewList newList = iNewListService.getByCode(lists1.getCodes());
//            //第二步骤加入用户持仓
//            UserPosition userPosition = new UserPosition();
//            userPosition.setPositionType(user.getAccountType());
//            userPosition.setPositionSn(KeyUtils.getUniqueKey());
//            userPosition.setUserId(user.getId());
//            userPosition.setNickName(user.getRealName());
//            userPosition.setAgentId(user.getAgentId());
//            userPosition.setStockCode(stock.getStockCode());
//            userPosition.setStockName(stock.getStockName());
//            userPosition.setStockGid(stock.getStockGid());
//            userPosition.setStockSpell(stock.getStockSpell());
//            userPosition.setBuyOrderId(GeneratePosition.getPositionId());
//            userPosition.setBuyOrderTime(new Date());
//            userPosition.setBuyOrderPrice(new BigDecimal(newList.getPrice()));
//            userPosition.setOrderDirection("买涨");
//            userPosition.setStockPlate(stock.getStockPlate());
//            userPosition.setOrderNum(Integer.parseInt(lists1.getWinums()));
//            //锁定股票
//            userPosition.setIsLock(1);
//            userPosition.setLockMsg("新股暂未上市，不可出售！");
//            userPosition.setOrderLever(1);
//            userPosition.setOrderTotalPrice(new BigDecimal(lists1.getBzj()));
//            userPosition.setOrderStayFee(BigDecimal.ZERO);
//            //设置手续费
//            userPosition.setOrderFee(BigDecimal.ZERO);
//            //设置印花税
//            userPosition.setOrderSpread(BigDecimal.ZERO);
//            //点差费
//            userPosition.setSpreadRatePrice(BigDecimal.ZERO);
//            //设置收益
//            userPosition.setProfitAndLose(BigDecimal.ZERO);
//            userPosition.setAllProfitAndLose(BigDecimal.ZERO);
//            userPosition.setOrderStayDays(Integer.valueOf(0));
//            this.userPositionMapper.insert(userPosition);
//            int insertPositionCount = userPosition.getId();
            //更新中签状态
            this.update(lists1);
            BigDecimal enableAmt_before = user.getEnableAmt();
            BigDecimal useAmt_before=user.getUserAmt();
            BigDecimal bzjb = new BigDecimal(lists1.getBzj());
            //减少用户可用资金
            user.setEnableAmt(enableAmt_before.subtract(bzjb));
            //减少总资产资金
            user.setUserAmt(useAmt_before.subtract(bzjb));
            int updateCount = this.userMapper.updateByPrimaryKeySelective(user);
            if (updateCount > 0) {
                log.info("1.修改用户资金成功");
            } else {
                throw  new Exception("失败，修改用户资金失败");
            }
            UserCashDetail ucd = new UserCashDetail();
            ucd.setPositionId(0);
            ucd.setAgentId(user.getAgentId());
            ucd.setAgentName(user.getAgentName());
            ucd.setUserId(user.getId());
            ucd.setUserName(StringUtil.isEmpty(user.getRealName()) ? user.getPhone() : user.getRealName());
            ucd.setDeType(messageSourceService.getMessage("ERR91"));
            ucd.setDeAmt(bzjb.multiply(new BigDecimal("-1")));
            ucd.setDeSummary(messageSourceService.getMessage("ERR92",new Object[]{lists1.getWinums(),bzjb.multiply(new BigDecimal("-1"))}));
            ucd.setAddTime(new Date());
            ucd.setIsRead(Integer.valueOf(0));
            int insertSxfCount = this.userCashDetailMapper.insertSelective(ucd);
            if (insertSxfCount > 0) {
                log.info("【新股申购】申请成功");
            }
            return ServerResponse.createBySuccess(messageSourceService.getMessage("ERR89"), null);
        } else {
            return ServerResponse.createByErrorMsg(messageSourceService.getMessage("ERR90"));
        }
    }


}
