package com.xc.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.xc.common.ServerResponse;
import com.xc.dao.AgentUserMapper;
import com.xc.dao.SiteTaskLogMapper;
import com.xc.pojo.*;
import com.xc.service.IAgentAgencyFeeService;
import com.xc.service.IAgentDistributionUserService;
import com.xc.service.IAgentUserService;
import com.xc.service.ISiteInfoService;
import com.xc.utils.KeyUtils;
import com.xc.utils.PropertiesUtil;
import com.xc.utils.redis.CookieUtils;
import com.xc.utils.redis.JsonUtil;
import com.xc.utils.redis.RedisShardedPoolUtils;
import com.xc.vo.agent.AgentInfoVO;
import com.xc.vo.agent.AgentSecondInfoVO;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;


@Service("iAgentUserService")
public class AgentUserServiceImpl implements IAgentUserService {
    private static final Logger log = LoggerFactory.getLogger(AgentUserServiceImpl.class);


    @Autowired
    AgentUserMapper agentUserMapper;
    @Autowired
    SiteTaskLogMapper siteTaskLogMapper;


    @Autowired
    ISiteInfoService iSiteInfoService;

    @Autowired
    IAgentDistributionUserService iAgentDistributionUserService;

    @Autowired
    IAgentAgencyFeeService iAgentAgencyFeeService;


    public AgentUser getCurrentAgent(HttpServletRequest request) {
        String loginToken = CookieUtils.readLoginToken(request, PropertiesUtil.getProperty("agent.cookie.name"));
        String agentJson = RedisShardedPoolUtils.get(loginToken);
        AgentUser agentUser = (AgentUser) JsonUtil.string2Obj(agentJson, AgentUser.class);
        return this.agentUserMapper.selectByPrimaryKey(agentUser.getId());
    }


    public AgentUser findByCode(String agentCode) {
        return this.agentUserMapper.findByCode(agentCode);
    }


    public ServerResponse login(String agentPhone, String agentPwd, String verifyCode, HttpServletRequest request) {
//        if (StringUtils.isBlank(verifyCode)) {
//            return ServerResponse.createByErrorMsg("验证码不能为空");
//        }
        String original = (String) request.getSession().getAttribute("KAPTCHA_SESSION_KEY");

//        if (!verifyCode.equalsIgnoreCase(original)) {
//            return ServerResponse.createByErrorMsg("验证码错误");
//        }

        if (StringUtils.isBlank(agentPhone) || StringUtils.isBlank(agentPwd)) {
            return ServerResponse.createByErrorMsg("参数不能为空");
        }

        AgentUser agentUser = this.agentUserMapper.login(agentPhone, agentPwd);
        if (agentUser == null) {
            return ServerResponse.createByErrorMsg("用户密码不正确");
        }

        if (agentUser.getIsLock().intValue() == 1) {
            return ServerResponse.createByErrorMsg("登陆失败，您的账号已被锁定！");
        }

        return ServerResponse.createBySuccess(agentUser);
    }


    public ServerResponse getAgentInfo(HttpServletRequest request) {
        String host = "";
        ServerResponse serverResponse = this.iSiteInfoService.getInfo();
        if (serverResponse.isSuccess()) {
            SiteInfo siteInfo = (SiteInfo) serverResponse.getData();
            if (StringUtils.isBlank(siteInfo.getSiteHost())) {
                return ServerResponse.createByErrorMsg("info host未设置");
            }
            host = siteInfo.getSiteHost();
        }
        String loginToken = CookieUtils.readLoginToken(request, PropertiesUtil.getProperty("agent.cookie.name"));
        String agentJson = RedisShardedPoolUtils.get(loginToken);
        AgentUser agentUser = (AgentUser) JsonUtil.string2Obj(agentJson, AgentUser.class);
        AgentUser dbuser = this.agentUserMapper.selectByPrimaryKey(agentUser.getId());
        AgentInfoVO agentInfoVO = assembleAgentInfoVO(dbuser, host);
        return ServerResponse.createBySuccess(agentInfoVO);
    }


    public ServerResponse updatePwd(String oldPwd, String newPwd, HttpServletRequest request) {
        if (StringUtils.isBlank(oldPwd) || StringUtils.isBlank(newPwd)) {
            return ServerResponse.createByErrorMsg("参数不能为空");
        }

        AgentUser agentUser = getCurrentAgent(request);
        if (!oldPwd.equals(agentUser.getAgentPwd())) {
            return ServerResponse.createByErrorMsg("密码错误");
        }

        agentUser.setAgentPwd(newPwd);
        int updateCount = this.agentUserMapper.updateByPrimaryKeySelective(agentUser);

        if (updateCount > 0) {
            return ServerResponse.createBySuccessMsg("修改成功");
        }
        return ServerResponse.createByErrorMsg("修改失败");
    }


    public ServerResponse addAgentUser(String agentName, String agentPwd, String agentRealName, String agentPhone, Integer parentId, String poundageScale,  String deferredFeesScale, String receiveDividendsScale, HttpServletRequest request) {
        if (StringUtils.isBlank(agentName) ||
                StringUtils.isBlank(agentPwd) ||
                StringUtils.isBlank(agentRealName) ||
                StringUtils.isBlank(agentPhone)) {
            return ServerResponse.createByErrorMsg("添加失败，参数不能为空");
        }

        AgentUser dbuser = this.agentUserMapper.findByName(agentName);
        if (dbuser != null) {
            return ServerResponse.createByErrorMsg("添加失败，代理名已存在");
        }

        AgentUser dbuser2 = this.agentUserMapper.findByPhone(agentPhone);
        if (dbuser2 != null) {
            return ServerResponse.createByErrorMsg("添加失败，手机号已存在");
        }

        AgentUser agentUser = new AgentUser();
        agentUser.setAgentName(agentName);
        agentUser.setAgentPwd(agentPwd);
        agentUser.setAgentCode(KeyUtils.getAgentUniqueKey());
        agentUser.setAgentRealName(agentRealName);
        agentUser.setAgentPhone(agentPhone);
        agentUser.setAddTime(new Date());
        agentUser.setIsLock(Integer.valueOf(0));
        agentUser.setPoundageScale(new BigDecimal(poundageScale));
        agentUser.setDeferredFeesScale(new BigDecimal(deferredFeesScale));
        agentUser.setReceiveDividendsScale(new BigDecimal(receiveDividendsScale));
        agentUser.setTotalMoney(new BigDecimal(0));
        /*AgentUser currentAgent = getCurrentAgent(request);
        agentUser.setParentId(currentAgent.getId());
        agentUser.setParentName(currentAgent.getAgentName());*/
        AgentUser parentAgent = this.agentUserMapper.selectByPrimaryKey(parentId);
        if (parentId != null && parentId>0) {
            if (parentAgent != null) {
                if(parentAgent.getAgentLevel()>=6){
                    return ServerResponse.createByErrorMsg("六级代理不能添加下级");
                }
                agentUser.setParentId(parentAgent.getId());
                agentUser.setParentName(parentAgent.getAgentName());
                agentUser.setAgentLevel(parentAgent.getAgentLevel()+1);
            } else {
                //总代理默认0级
                agentUser.setAgentLevel(Integer.valueOf(0));
                agentUser.setParentId(Integer.valueOf(0));
            }
        } else {
            //总代理默认0级
            agentUser.setAgentLevel(Integer.valueOf(0));
            agentUser.setParentId(Integer.valueOf(0));
        }

        int insertCount = 0;
        try {
            this.agentUserMapper.insert(agentUser);
            insertCount = agentUser.getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (insertCount > 0) {
            if (parentAgent != null) {
                //分销用户数据处理
                recursiveSaveAgentDistributionUser(insertCount,parentId,parentAgent.getAgentLevel());
            }
            return ServerResponse.createBySuccessMsg("保存代理用户成功");
        }
        return ServerResponse.createByErrorMsg("添加失败，请重试");
    }

    //分销代理数据：循环添加
    public int recursiveSaveAgentDistributionUser(int agentId, int parentId,int level){
        int k = 0;
        int pId = parentId;
        for (int i = level; i >= 0; i--){
            AgentUser parentAgent = this.agentUserMapper.selectByPrimaryKey(pId);
            System.out.print("分销代理数据level="+i+"===parentid="+parentAgent.getParentId());
            //分销用户数据处理
            AgentDistributionUser agentDistributionUser = new AgentDistributionUser();
            agentDistributionUser.setAgentId(agentId);
            agentDistributionUser.setParentId(parentAgent.getId());
            agentDistributionUser.setParentLevel(parentAgent.getAgentLevel());
            iAgentDistributionUserService.insert(agentDistributionUser);
            pId =  parentAgent.getParentId().intValue();
            k++;
        }
        return k;
    }


    public ServerResponse<PageInfo> getSecondAgent(int pageNum, int pageSize, HttpServletRequest request) {
        Page<AgentSecondInfoVO> page = PageHelper.startPage(pageNum, pageSize);
        AgentUser agentUser = getCurrentAgent(request);
        //log.info("======test=======" + agentUser.getId().toString());
        List<AgentUser> agentUsers = this.agentUserMapper.getSecondAgent(agentUser.getId());
        /*
        List<AgentSecondInfoVO> agentSecondInfoVOS = Lists.newArrayList();
        for (AgentUser agentUser1 : agentUsers) {
            AgentSecondInfoVO agentSecondInfoVO = assembleAgentSecondInfoVO(agentUser1);
            agentSecondInfoVOS.add(agentSecondInfoVO);
        }*/
        PageInfo pageInfo = new PageInfo(page);
        pageInfo.setList(agentUsers);
        return ServerResponse.createBySuccess(pageInfo);
    }

    /*查询代理所有上级*/
    @Override
    public  List<AgentUser> getAgentSuperiorList(int agentId){
        return  this.agentUserMapper.getAgentSuperiorList(agentId);
    }

    public ServerResponse<PageInfo> listByAdmin(String realName, String phone, int pageNum, int pageSize, int id, HttpServletRequest request) {
        Page<AgentUser> page = PageHelper.startPage(pageNum, pageSize);

        this.agentUserMapper.listByAdmin(realName, phone, id);
        PageInfo pageInfo = new PageInfo(page);

        return ServerResponse.createBySuccess(pageInfo);
    }

    public ServerResponse add(AgentUser agentUser, HttpServletRequest request) {
        if (StringUtils.isBlank(agentUser.getAgentName()) ||
                StringUtils.isBlank(agentUser.getAgentPhone()) ||
                StringUtils.isBlank(agentUser.getAgentRealName()) ||
                StringUtils.isBlank(agentUser.getAgentPwd())) {
            return ServerResponse.createByErrorMsg("参数不能为空");
        }

        AgentUser pAgent = this.agentUserMapper.findByPhone(agentUser.getAgentPhone());
        if (pAgent != null) {
            return ServerResponse.createByErrorMsg("手机号已存在");
        }

        AgentUser nameAgent = this.agentUserMapper.findByName(agentUser.getAgentName());
        if (nameAgent != null) {
            return ServerResponse.createByErrorMsg("代理名已存在");
        }

        AgentUser dbAgent = new AgentUser();
        dbAgent.setAgentName(agentUser.getAgentName());
        dbAgent.setAgentPwd(agentUser.getAgentPwd());
        dbAgent.setAgentPhone(agentUser.getAgentPhone());
        dbAgent.setAgentRealName(agentUser.getAgentRealName());
        dbAgent.setAddTime(new Date());
        dbAgent.setIsLock(Integer.valueOf(0));
        dbAgent.setAgentCode(KeyUtils.getAgentUniqueKey());
        //AgentUser loginAgent = getCurrentAgent(request);
        if (agentUser.getParentId() != null) {
            AgentUser parentAgent = this.agentUserMapper.selectByPrimaryKey(agentUser.getParentId());
            if (parentAgent != null) {
                dbAgent.setParentId(parentAgent.getId());
                dbAgent.setParentName(parentAgent.getAgentName());
                dbAgent.setAgentLevel(parentAgent.getAgentLevel()+1);
            } else {
                //总代理默认0级
                dbAgent.setAgentLevel(Integer.valueOf(0));
                dbAgent.setParentId(Integer.valueOf(0));
            }
        }

        int insertCount = this.agentUserMapper.insert(dbAgent);
        if (insertCount > 0) {
            return ServerResponse.createBySuccessMsg("添加代理成功");
        }
        return ServerResponse.createByErrorMsg("添加代理失败");
    }


    public ServerResponse update(AgentUser agentUser) {
        AgentUser dbAgent = new AgentUser();

        if (StringUtils.isNotBlank(agentUser.getAgentName())) {
            return ServerResponse.createByErrorMsg("公司名不能变更");
        }

        dbAgent.setId(agentUser.getId());
        if (StringUtils.isNotBlank(agentUser.getAgentPwd())) {
            dbAgent.setAgentPwd(agentUser.getAgentPwd());
        }
        if (StringUtils.isNotBlank(agentUser.getAgentRealName())) {
            dbAgent.setAgentRealName(agentUser.getAgentRealName());
        }
        if (StringUtils.isNotBlank(agentUser.getSiteLever())) {
            dbAgent.setSiteLever(agentUser.getSiteLever());
        }
        if (StringUtils.isNotBlank(agentUser.getAgentPhone())) {
            AgentUser phoneAgent = this.agentUserMapper.findByPhone(agentUser.getAgentPhone());

            if (phoneAgent == null || phoneAgent.getId() == agentUser.getId()) {
                dbAgent.setAgentPhone(agentUser.getAgentPhone());
            } else {
                return ServerResponse.createByErrorMsg("手机号已存在，请更换手机");
            }
        }
        if (agentUser.getIsLock() != null) {
            dbAgent.setIsLock(agentUser.getIsLock());
        }

        if (agentUser.getParentId() != null) {

            AgentUser parentAgent = this.agentUserMapper.selectByPrimaryKey(agentUser.getParentId());
            if (parentAgent != null) {
                dbAgent.setParentId(parentAgent.getId());
                dbAgent.setParentName(parentAgent.getAgentName());
            }
        }

        int updateCount = this.agentUserMapper.updateByPrimaryKeySelective(dbAgent);
        if (updateCount > 0) {
            return ServerResponse.createBySuccessMsg("修改代理成功");
        }
        return ServerResponse.createByErrorMsg("修改代理失败");
    }

    public int CountAgentNum() {
        return this.agentUserMapper.CountAgentNum();
    }


    private AgentSecondInfoVO assembleAgentSecondInfoVO(AgentUser agentUser) {
        AgentSecondInfoVO agentSecondInfoVO = new AgentSecondInfoVO();
        agentSecondInfoVO.setId(agentUser.getId());
        agentSecondInfoVO.setAgentCode(agentUser.getAgentCode());
        agentSecondInfoVO.setAgentName(agentUser.getAgentName());
        agentSecondInfoVO.setAgentPhone(agentUser.getAgentPhone());
        agentSecondInfoVO.setAgentRealName(agentUser.getAgentRealName());
        return agentSecondInfoVO;
    }

    private AgentInfoVO assembleAgentInfoVO(AgentUser agentUser, String host) {
        AgentInfoVO agentInfoVO = new AgentInfoVO();
        agentInfoVO.setId(agentUser.getId());
        agentInfoVO.setAgentName(agentUser.getAgentName());
        agentInfoVO.setAgentRealName(agentUser.getAgentRealName());
        agentInfoVO.setAgentPhone(agentUser.getAgentPhone());
        agentInfoVO.setAgentCode(agentUser.getAgentCode());
        agentInfoVO.setAddTime(agentUser.getAddTime());
        agentInfoVO.setIsLock(agentUser.getIsLock());
        agentInfoVO.setParentId(agentUser.getParentId());
        agentInfoVO.setParentName(agentUser.getParentName());
        agentInfoVO.setTotalMoney(agentUser.getTotalMoney());

        String pcUrl = host + PropertiesUtil.getProperty("site.pc.reg.url") + agentUser.getAgentCode();
        agentInfoVO.setPcUrl(pcUrl);
        String mUrl = host + PropertiesUtil.getProperty("site.m.reg.url") + agentUser.getAgentCode();
        agentInfoVO.setMUrl(mUrl);
        return agentInfoVO;
    }

    /*代理账户扣款*/
    @Transactional
    public ServerResponse updateAgentAmt(Integer agentId, Integer amt, Integer direction) {
        if (agentId == null || amt == null || direction == null) {
            return ServerResponse.createByErrorMsg("参数不能为空");
        }

        AgentUser agentUser = this.agentUserMapper.selectByPrimaryKey(agentId);
        if (agentUser == null) {
            return ServerResponse.createByErrorMsg("代理不存在");
        }

        BigDecimal totalMoney = agentUser.getTotalMoney();//扣除前金额
        BigDecimal back_amt = agentUser.getTotalMoney();//扣除后余额
        BigDecimal deduct_amt = new BigDecimal(amt);//扣除金额

        if (direction.intValue() == 0) {

        } else if (direction.intValue() == 1) {
            if (totalMoney.compareTo(new BigDecimal(amt.intValue())) == -1) {
                return ServerResponse.createByErrorMsg("扣款失败, 总资金不足");
            }
            deduct_amt = deduct_amt.multiply(new BigDecimal(-1));
            back_amt = back_amt.subtract(new BigDecimal(amt));
        } else {
            return ServerResponse.createByErrorMsg("不存在此操作");
        }

        //修改代理账户余额
        AgentUser user = new AgentUser();
        user.setId(agentUser.getId());
        user.setTotalMoney(deduct_amt);
        agentUserMapper.updateTotalMoney(user);
        //利润明细增加
        AgentAgencyFee agentAgencyFee = new AgentAgencyFee();
        agentAgencyFee.setAgentId(agentUser.getId());
        agentAgencyFee.setStatus(1);
        agentAgencyFee.setAymentType(2);
        agentAgencyFee.setBusinessId(0);
        agentAgencyFee.setFeeType(0);
        agentAgencyFee.setTotalMoney(deduct_amt);
        agentAgencyFee.setProfitMoney(new BigDecimal(amt.intValue()));
        agentAgencyFee.setRemarks("【提现支出】提现金额："+amt);
        iAgentAgencyFeeService.insert(agentAgencyFee);


        SiteTaskLog siteTaskLog = new SiteTaskLog();
        siteTaskLog.setTaskType("管理员修改代理金额");
        StringBuffer cnt = new StringBuffer();
        cnt.append("管理员修改代理金额 - ")
                .append((direction.intValue() == 0) ? "入款" : "扣款")
                .append(amt).append("元");
        siteTaskLog.setTaskCnt(cnt.toString());

        StringBuffer target = new StringBuffer();
        target.append("代理id : ").append(agentUser.getId())
                .append("修改前 总资金 = ").append(totalMoney)
                .append("修改后 总资金 = ").append(back_amt);
        siteTaskLog.setTaskTarget(target.toString());

        siteTaskLog.setIsSuccess(Integer.valueOf(0));
        siteTaskLog.setAddTime(new Date());

        int insertCount = this.siteTaskLogMapper.insert(siteTaskLog);
        if (insertCount > 0) {
            return ServerResponse.createBySuccessMsg("修改资金成功");
        }
        return ServerResponse.createByErrorMsg("修改资金失败");
    }

    /*删除代理*/
    public ServerResponse delAgent(Integer agentId) {
        AgentUser dbAgent = this.agentUserMapper.selectByPrimaryKey(agentId);

        if (dbAgent == null) {
            return ServerResponse.createByErrorMsg("代理不存在，请正常操作！");
        }

        int updateCount = this.agentUserMapper.deleteByPrimaryKey(agentId);
        if (updateCount > 0) {
            return ServerResponse.createBySuccessMsg("删除代理成功");
        }
        return ServerResponse.createByErrorMsg("删除代理失败");
    }
}

