package com.xc.controller.agent;

import com.xc.common.ServerResponse;
import com.xc.pojo.AgentUser;
import com.xc.service.*;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/agent/"})
public class AgentController {
    private static final Logger log = LoggerFactory.getLogger(AgentController.class);
    @Autowired
    IAgentUserService iAgentUserService;
    @Autowired
    IUserService iUserService;
    @Autowired
    IUserPositionService iUserPositionService;
    @Autowired
    IUserIndexPositionService iUserIndexPositionService;
    @Autowired
    IUserFuturesPositionService iUserFuturesPositionService;
    @Autowired
    IAgentAgencyFeeService iAgentAgencyFeeService;

    //查询指定代理信息
    @RequestMapping({"getAgentInfo.do"})
    @ResponseBody
    public ServerResponse getAgentInfo(HttpServletRequest request) {
        return this.iAgentUserService.getAgentInfo(request);
    }

    //修改代理用户密码
    @RequestMapping({"updatePwd.do"})
    @ResponseBody
    public ServerResponse updatePwd(@RequestParam("oldPwd") String oldPwd, @RequestParam("newPwd") String newPwd, HttpServletRequest request) {
        return this.iAgentUserService.updatePwd(oldPwd, newPwd, request);
    }


    //添加代理用户
    @RequestMapping({"addAgent.do"})
    @ResponseBody
    public ServerResponse addAgent(HttpServletRequest request, @RequestParam("agentName") String agentName, @RequestParam("agentPwd") String agentPwd, @RequestParam("agentPhone") String agentPhone, @RequestParam("agentRealName") String agentRealName, @RequestParam("parentId") Integer parentId, @RequestParam("poundageScale") String poundageScale, @RequestParam("deferredFeesScale") String deferredFeesScale, @RequestParam("receiveDividendsScale") String receiveDividendsScale) {
        return this.iAgentUserService.addAgentUser(agentName, agentPwd, agentRealName, agentPhone, parentId, poundageScale, deferredFeesScale, receiveDividendsScale, request);
    }

    //查询所有代理信息
    @RequestMapping({"getSecondAgent.do"})
    @ResponseBody
    public ServerResponse getSecondAgent(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "12") int pageSize,HttpServletRequest request) {
        return this.iAgentUserService.getSecondAgent(pageNum, pageSize,request);
    }

    //查询代理利润明细
    @RequestMapping({"getAgentAgencyFeeList.do"})
    @ResponseBody
    public ServerResponse getAgentAgencyFeeList(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "12") int pageSize,HttpServletRequest request) {
        return this.iAgentAgencyFeeService.getAgentAgencyFeeList(pageNum, pageSize,request);
    }

    //添加用户管理 账户信息
    @RequestMapping({"addSimulatedAccount.do"})
    @ResponseBody
    public ServerResponse addSimulatedAccount(HttpServletRequest request, @RequestParam("phone") String phone, @RequestParam("amt") String amt, @RequestParam("accountType") Integer accountType, @RequestParam("pwd") String pwd) {
        AgentUser agentUser = this.iAgentUserService.getCurrentAgent(request);
        return this.iUserService.addSimulatedAccount(agentUser.getId(), phone, pwd, amt, accountType, request);
    }

    //查询股票统计指定时间内，代理客户（已平仓）的交易盈亏
    @RequestMapping({"getIncome.do"})
    @ResponseBody
    public ServerResponse getIncome(@RequestParam(value = "agentId", required = false) Integer agentId, @RequestParam(value = "positionType", required = false) Integer positionType, @RequestParam(value = "beginTime", required = false) String beginTime, @RequestParam(value = "endTime", required = false) String endTime, HttpServletRequest request) {
        if (agentId == null) {
            AgentUser agentUser = this.iAgentUserService.getCurrentAgent(request);
            agentId = agentUser.getId();
        }
        return this.iUserPositionService.getIncome(agentId, positionType, beginTime, endTime);
    }

    //查询指数统计指定时间内，代理客户（已平仓）的交易盈亏
    @RequestMapping({"getIndexIncome.do"})
    @ResponseBody
    public ServerResponse getIndexIncome(@RequestParam(value = "agentId", required = false) Integer agentId, @RequestParam(value = "positionType", required = false) Integer positionType, @RequestParam(value = "beginTime", required = false) String beginTime, @RequestParam(value = "endTime", required = false) String endTime, HttpServletRequest request) {
        if (agentId == null) {
            AgentUser agentUser = this.iAgentUserService.getCurrentAgent(request);
            agentId = agentUser.getId();
        }
        return this.iUserIndexPositionService.getIndexIncome(agentId, positionType, beginTime, endTime);
    }

    //查询期货统计指定时间内，代理客户（已平仓）的交易盈亏
    @RequestMapping({"getFuturesIncome.do"})
    @ResponseBody
    public ServerResponse getFuturesIncome(@RequestParam(value = "agentId", required = false) Integer agentId, @RequestParam(value = "positionType", required = false) Integer positionType, @RequestParam(value = "beginTime", required = false) String beginTime, @RequestParam(value = "endTime", required = false) String endTime, HttpServletRequest request) {
        if (agentId == null) {
            AgentUser agentUser = this.iAgentUserService.getCurrentAgent(request);
            agentId = agentUser.getId();
        }
        return this.iUserFuturesPositionService.getFuturesIncome(agentId, positionType, beginTime, endTime);
    }
}
