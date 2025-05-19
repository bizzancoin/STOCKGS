package com.xc.service.impl;


import com.xc.dao.UserRechargeMapper;
import com.xc.service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xc.common.ServerResponse;
import com.xc.dao.AgentUserMapper;
import com.xc.dao.UserCashDetailMapper;
import com.xc.dao.UserMapper;
import com.xc.pojo.AgentUser;
import com.xc.pojo.SiteInfo;
import com.xc.pojo.SiteSetting;
import com.xc.pojo.User;
import com.xc.pojo.UserCashDetail;
import com.xc.pojo.UserRecharge;
import com.xc.service.*;
import com.xc.utils.DateTimeUtil;
import com.xc.utils.KeyUtils;
import com.xc.utils.email.SendHTMLMail;
import com.xc.utils.redis.RedisShardedPoolUtils;

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

@Service("iUserRechargeService")
public class UserRechargeServiceImpl implements IUserRechargeService {
    private static final Logger log = LoggerFactory.getLogger(UserRechargeServiceImpl.class);

    @Autowired
    UserRechargeMapper userRechargeMapper;

    @Autowired
    IUserService iUserService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    IAgentUserService iAgentUserService;
    @Autowired
    AgentUserMapper agentUserMapper;
    @Autowired
    ISiteSettingService iSiteSettingService;
    @Autowired
    UserCashDetailMapper userCashDetailMapper;
    @Autowired
    ISiteInfoService iSiteInfoService;

    @Autowired
    LocaleMessageSourceService messageSourceService;

    @Autowired
    private LocaleMessageSourceService localeMessageSourceService;

    public ServerResponse checkInMoney(int maxOrder, Integer userId) {
        int count = this.userRechargeMapper.checkInMoney(0, userId);

        if (count > maxOrder) {
            return ServerResponse.createByErrorMsg(messageSourceService.getMessage("ERR26",new Object[]{maxOrder}));
        }
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse inMoneyByAdmin(String amt, String payType, Integer uid) {
        if (StringUtils.isBlank(amt) || StringUtils.isBlank(payType)) {
            return ServerResponse.createByErrorMsg("参数不能为空");
        }
        User user= (User) iUserService.findByUserId(uid).getData();

        UserRecharge userRecharge = new UserRecharge();

        userRecharge.setUserId(user.getId());
        userRecharge.setNickName(user.getRealName());
        userRecharge.setAgentId(user.getAgentId());

        String ordersn = KeyUtils.getRechargeOrderSn();
        userRecharge.setOrderSn(ordersn);

        userRecharge.setPayChannel(payType);
        userRecharge.setPayAmt(new BigDecimal(amt));
        userRecharge.setOrderStatus(Integer.valueOf(1));
        userRecharge.setAddTime(new Date());

        int insertCount = this.userRechargeMapper.insert(userRecharge);
        if (insertCount > 0) {

            String email_token = KeyUtils.getUniqueKey();

            String redisSetExResult = RedisShardedPoolUtils.setEx(email_token, email_token, 300);

            log.info("用户充值，保存redis token成功，redisSetExResult = {}", redisSetExResult);

            /*SendHTMLMail.send(user, userRecharge, email_token, siteInfo
                    .getSiteHost(), siteInfo.getSiteEmailTo());
            log.info("用户充值，发送审核邮件成功");*/
            return ServerResponse.createBySuccessMsg("创建支付订单成功！");
        }
        return ServerResponse.createByErrorMsg("创建支付订单失败");
    }


    public ServerResponse inMoney(String amt, String payType, HttpServletRequest request) {
        if (StringUtils.isBlank(amt) || StringUtils.isBlank(payType)) {
            return ServerResponse.createByErrorMsg(messageSourceService.getMessage("ERRALL"));
        }

        SiteSetting siteSetting = this.iSiteSettingService.getSiteSetting();
        if (siteSetting == null) {
            return ServerResponse.createByErrorMsg(messageSourceService.getMessage("ERR29"));
        }
        if ((new BigDecimal(siteSetting.getChargeMinAmt() + "")).compareTo(new BigDecimal(amt)) == 1) {
            return ServerResponse.createByErrorMsg(messageSourceService.getMessage("ERR30",new Object[]{siteSetting.getChargeMinAmt()}));
        }


        SiteInfo siteInfo = null;
        ServerResponse serverResponseInfo = this.iSiteInfoService.getInfo();
        if (serverResponseInfo.isSuccess()) {
            siteInfo = (SiteInfo) serverResponseInfo.getData();
            /*if (StringUtils.isBlank(siteInfo.getSiteHost()) ||
                    StringUtils.isBlank(siteInfo.getSiteEmailTo())) {
                return ServerResponse.createByErrorMsg("请先设置Host and ToEmail");
            }*/
        } else {
            return serverResponseInfo;
        }

        User user = this.iUserService.getCurrentRefreshUser(request);
        if (user.getIsActive().intValue() != 2) {
            return ServerResponse.createByErrorMsg(messageSourceService.getMessage("ERR31"));
        }


        ServerResponse serverResponse = checkInMoney(10, user.getId());
        if (!serverResponse.isSuccess()) {
            return serverResponse;
        }


        UserRecharge userRecharge = new UserRecharge();

        userRecharge.setUserId(user.getId());
        userRecharge.setNickName(user.getRealName());
        userRecharge.setAgentId(user.getAgentId());

        String ordersn = KeyUtils.getRechargeOrderSn();
        userRecharge.setOrderSn(ordersn);

        userRecharge.setPayChannel(payType);
        userRecharge.setPayAmt(new BigDecimal(amt));
        userRecharge.setOrderStatus(Integer.valueOf(0));
        userRecharge.setAddTime(new Date());

        int insertCount = this.userRechargeMapper.insert(userRecharge);
        if (insertCount > 0) {

            String email_token = KeyUtils.getUniqueKey();

            String redisSetExResult = RedisShardedPoolUtils.setEx(email_token, email_token, 300);

            log.info("用户充值，保存redis token成功，redisSetExResult = {}", redisSetExResult);

            /*SendHTMLMail.send(user, userRecharge, email_token, siteInfo
                    .getSiteHost(), siteInfo.getSiteEmailTo());
            log.info("用户充值，发送审核邮件成功");*/
            return ServerResponse.createBySuccessMsg(messageSourceService.getMessage("ERR27"));
        }
        return ServerResponse.createByErrorMsg(messageSourceService.getMessage("ERR28"));
    }


    public ServerResponse findUserRechargeByOrderSn(String orderSn) {
        UserRecharge userRecharge = this.userRechargeMapper.findUserRechargeByOrderSn(orderSn);
        if (userRecharge != null) {
            return ServerResponse.createBySuccess(userRecharge);
        }
        return ServerResponse.createByErrorMsg("找不到充值订单");
    }


    @Transactional
    public ServerResponse chargeSuccess(UserRecharge userRecharge) throws Exception {
        log.info("充值订单 确认成功操作 id = {}", userRecharge.getId());

        if (userRecharge.getOrderStatus().intValue() != 0) {
            return ServerResponse.createByErrorMsg("订单状态不能重复修改");
        }


        User user = this.userMapper.selectByPrimaryKey(userRecharge.getUserId());
        if (user == null) {
            return ServerResponse.createByErrorMsg("用户不存在");
        }
        BigDecimal userAmt_before = user.getUserAmt();
        BigDecimal enableAmt_before = user.getEnableAmt();
        user.setUserAmt(userAmt_before.add(userRecharge.getPayAmt()));
        user.setEnableAmt(enableAmt_before.add(userRecharge.getPayAmt()));
        int updateCount = this.userMapper.updateByPrimaryKeySelective(user);
        if (updateCount > 0) {
            log.info("1.修改用户资金成功");
        } else {
            return ServerResponse.createByErrorMsg("失败，修改用户资金失败");
        }


        userRecharge.setOrderStatus(Integer.valueOf(1));
        userRecharge.setPayTime(new Date());
        int updateCCount = this.userRechargeMapper.updateByPrimaryKeySelective(userRecharge);
        if (updateCCount > 0) {
            log.info("2.修改订单状态成功");
        } else {
            throw new Exception("2. 修改订单状态失败!");
        }


        UserCashDetail ucd = new UserCashDetail();
        ucd.setAgentId(user.getAgentId());
        ucd.setAgentName(user.getAgentName());
        ucd.setUserId(user.getId());
        ucd.setUserName(user.getRealName());
        ucd.setDeType("用户充值");
        ucd.setDeAmt(userRecharge.getPayAmt());
        ucd.setDeSummary("用户充值成功，充值前总金额:" + userAmt_before + ",充值后总金额:" + user.getUserAmt() + ",充值前可用:" + enableAmt_before + ",充值后可用:" + user
                .getEnableAmt());

        ucd.setAddTime(new Date());
        ucd.setIsRead(Integer.valueOf(0));
        int insertCount = this.userCashDetailMapper.insert(ucd);
        if (insertCount > 0) {
            return ServerResponse.createBySuccessMsg("充值成功！");
        }
        return ServerResponse.createByErrorMsg("充值失败");
    }


    public ServerResponse chargeFail(UserRecharge userRecharge) throws Exception {
        if (userRecharge.getOrderStatus().intValue() != 0) {
            return ServerResponse.createByErrorMsg("订单状态不能重复修改");
        }

        userRecharge.setOrderStatus(Integer.valueOf(2));
        int updateCCount = this.userRechargeMapper.updateByPrimaryKeySelective(userRecharge);
        if (updateCCount > 0) {
            return ServerResponse.createBySuccessMsg("订单已修改为失败");
        }
        return ServerResponse.createByErrorMsg("修改出现异常");
    }


    public ServerResponse chargeCancel(UserRecharge userRecharge) throws Exception {
        if (userRecharge.getOrderStatus().intValue() != 0) {
            return ServerResponse.createByErrorMsg("订单状态不能重复修改");
        }

        userRecharge.setOrderStatus(Integer.valueOf(3));
        int updateCCount = this.userRechargeMapper.updateByPrimaryKeySelective(userRecharge);
        if (updateCCount > 0) {
            return ServerResponse.createBySuccessMsg("订单取消成功");
        }
        return ServerResponse.createByErrorMsg("订单取消出现异常");
    }


    public ServerResponse<PageInfo> findUserChargeList(String payChannel, String orderStatus, HttpServletRequest request, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        User user = this.iUserService.getCurrentUser(request);



        List<UserRecharge> userRecharges = this.userRechargeMapper.findUserChargeList(user.getId(), payChannel, orderStatus);

        log.info("充值列表，增加用户 {} ，payChannel = {} , orderStatus = {}， 数量 = {}", new Object[]{user.getId(), payChannel, orderStatus, userRecharges.size()});

        PageInfo pageInfo = new PageInfo(userRecharges);

        return ServerResponse.createBySuccess(pageInfo);
    }


    public ServerResponse<PageInfo> listByAgent(Integer agentId, String realName, String payChannel, Integer state, HttpServletRequest request, int pageNum, int pageSize) {
        AgentUser currentAgent = this.iAgentUserService.getCurrentAgent(request);


        if (agentId != null) {
            AgentUser agentUser = this.agentUserMapper.selectByPrimaryKey(agentId);
            if (!agentUser.getParentId().equals(currentAgent.getId())) {
                return ServerResponse.createByErrorMsg("不能查询非下级代理记录");
            }
        }
        Integer searchId = null;
        if (agentId == null) {
            searchId = currentAgent.getId();
        } else {
            searchId = agentId;
        }


        PageHelper.startPage(pageNum, pageSize);


        List<UserRecharge> userRecharges = this.userRechargeMapper.listByAgent(searchId, realName, payChannel, state);

        PageInfo pageInfo = new PageInfo(userRecharges);

        return ServerResponse.createBySuccess(pageInfo);
    }


    public ServerResponse listByAdmin(Integer agentId, Integer userId, String realName, Integer state, String beginTime, String endTime, HttpServletRequest request, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);


        Timestamp begin_time = null;
        if (StringUtils.isNotBlank(beginTime)) {
            begin_time = DateTimeUtil.searchStrToTimestamp(beginTime);
        }
        Timestamp end_time = null;
        if (StringUtils.isNotBlank(endTime)) {
            end_time = DateTimeUtil.searchStrToTimestamp(endTime);
        }


        List<UserRecharge> userRecharges = this.userRechargeMapper.listByAdmin(agentId, userId, realName, state, begin_time, end_time);

        PageInfo pageInfo = new PageInfo(userRecharges);

        return ServerResponse.createBySuccess(pageInfo);
    }


    @Transactional
    public ServerResponse updateState(Integer chargeId, Integer state) throws Exception {
        UserRecharge userRecharge = this.userRechargeMapper.selectByPrimaryKey(chargeId);

        if (userRecharge == null) {
            return ServerResponse.createByErrorMsg("充值订单不存在");
        }
        if (userRecharge.getOrderStatus().intValue() != 0) {
            return ServerResponse.createByErrorMsg("订单状态不是下单状态不能更改");
        }


        if (state.intValue() == 1) {

            User user = this.userMapper.selectByPrimaryKey(userRecharge.getUserId());
            if (user == null) {
                return ServerResponse.createByErrorMsg("用户不存在");
            }
            BigDecimal user_amt = user.getUserAmt().add(userRecharge.getPayAmt());
            log.info("管理员确认订单成功，增加用户 {} 总资金，原金额 = {} , 增加后 = {}", new Object[]{user.getId(), user.getUserAmt(), user_amt});
            user.setUserAmt(user_amt);
            BigDecimal user_enable_amt = user.getEnableAmt().add(userRecharge.getPayAmt());
            log.info("管理员确认订单成功，增加用户 {} 可用资金，原金额 = {} , 增加后 = {}", new Object[]{user.getId(), user.getEnableAmt(), user_enable_amt});
            user.setEnableAmt(user_enable_amt);

            int updateCount = this.userMapper.updateByPrimaryKeySelective(user);
            if (updateCount > 0) {
                log.info("修改用户资金成功！");
            } else {
                log.error("修改用户资金出错，抛出异常");
                throw new Exception("修改用户资金出错，抛出异常");
            }
        }


        userRecharge.setOrderStatus(Integer.valueOf((state.intValue() == 1) ? 1 : 2));


        userRecharge.setPayTime(new Date());
        int updateCount = this.userRechargeMapper.updateByPrimaryKeySelective(userRecharge);
        if (updateCount > 0) {
            return ServerResponse.createBySuccessMsg("修改订单状态成功！");
        }
        return ServerResponse.createByErrorMsg("修改订单状态失败！");
    }



    public ServerResponse createOrder(Integer userId, Integer state, BigDecimal amt, String payChannel) {
        if (userId == null || state == null || amt == null) {
            return ServerResponse.createByErrorMsg("参数不能为空");
        }

        User user = this.userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            return ServerResponse.createByErrorMsg("找不到用户");
        }

        UserRecharge userRecharge = new UserRecharge();
        userRecharge.setUserId(user.getId());
        userRecharge.setNickName(user.getRealName());
        userRecharge.setAgentId(user.getAgentId());

        String ordersn = KeyUtils.getRechargeOrderSn();
        userRecharge.setOrderSn(ordersn);

        userRecharge.setPayChannel(payChannel);
        userRecharge.setPayAmt(amt);
        userRecharge.setAddTime(new Date());
        userRecharge.setPayTime(new Date());

        if (state.intValue() == 0) {
            userRecharge.setOrderStatus(Integer.valueOf(0));
        } else if (state.intValue() == 1) {
            userRecharge.setOrderSn(payChannel);
            userRecharge.setPayChannel("2");
            userRecharge.setOrderStatus(Integer.valueOf(1));

            user.setUserAmt(user.getUserAmt().add(amt));
            user.setEnableAmt(user.getEnableAmt().add(amt));
            this.userMapper.updateByPrimaryKeySelective(user);
        } else if (state.intValue() == 2) {
            userRecharge.setOrderStatus(Integer.valueOf(2));
        } else {
            return ServerResponse.createByErrorMsg("订单状态不正确");
        }

        int insertCount = this.userRechargeMapper.insert(userRecharge);
        if (insertCount > 0) {
            return ServerResponse.createBySuccessMsg("生成订单成功！");
        }
        return ServerResponse.createByErrorMsg("生成订单失败，请重试");
    }

    public ServerResponse createOrder(Integer userId, Integer state, Integer amt, String payChannel) {
        if (userId == null || state == null || amt == null) {
            return ServerResponse.createByErrorMsg("参数不能为空");
        }

        User user = this.userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            return ServerResponse.createByErrorMsg("找不到用户");
        }

        UserRecharge userRecharge = new UserRecharge();
        userRecharge.setUserId(user.getId());
        userRecharge.setNickName(user.getRealName());
        userRecharge.setAgentId(user.getAgentId());

        String ordersn = KeyUtils.getRechargeOrderSn();
        userRecharge.setOrderSn(ordersn);

        userRecharge.setPayChannel(payChannel);
        userRecharge.setPayAmt(new BigDecimal(amt.intValue()));
        userRecharge.setAddTime(new Date());
        userRecharge.setPayTime(new Date());

        if (state.intValue() == 0) {
            userRecharge.setOrderStatus(Integer.valueOf(0));
        } else if (state.intValue() == 1) {
            userRecharge.setOrderSn(payChannel);
            userRecharge.setPayChannel("2");
            userRecharge.setOrderStatus(Integer.valueOf(1));

            user.setUserAmt(user.getUserAmt().add(new BigDecimal(amt.intValue())));
            user.setEnableAmt(user.getEnableAmt().add(new BigDecimal(amt.intValue())));
            this.userMapper.updateByPrimaryKeySelective(user);
        } else if (state.intValue() == 2) {
            userRecharge.setOrderStatus(Integer.valueOf(2));
        } else {
            return ServerResponse.createByErrorMsg("订单状态不正确");
        }

        int insertCount = this.userRechargeMapper.insert(userRecharge);
        if (insertCount > 0) {
            return ServerResponse.createBySuccessMsg("生成订单成功！");
        }
        return ServerResponse.createByErrorMsg("生成订单失败，请重试");
    }


    public ServerResponse del(Integer cId) {
        if (cId == null) {
            return ServerResponse.createByErrorMsg("id不能为空");
        }
        int updateCount = this.userRechargeMapper.deleteByPrimaryKey(cId);
        if (updateCount > 0) {
            return ServerResponse.createBySuccessMsg("删除成功");
        }
        return ServerResponse.createByErrorMsg("删除失败");
    }


    public int deleteByUserId(Integer userId) {
        return this.userRechargeMapper.deleteByUserId(userId);
    }


    public BigDecimal CountChargeSumAmt(Integer chargeState) {
        return this.userRechargeMapper.CountChargeSumAmt(chargeState);
    }

    public BigDecimal CountTotalRechargeAmountByTime(Integer chargeState) {
        return this.userRechargeMapper.CountTotalRechargeAmountByTime(chargeState);
    }
}
