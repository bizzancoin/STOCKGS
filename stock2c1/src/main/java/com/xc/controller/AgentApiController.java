package com.xc.controller;


import com.xc.common.ServerResponse;

import com.xc.service.IAgentUserService;

import com.xc.utils.PropertiesUtil;

import com.xc.utils.redis.CookieUtils;

import com.xc.utils.redis.JsonUtil;

import com.xc.utils.redis.RedisConst;

import com.xc.utils.redis.RedisShardedPoolUtils;

import com.xc.vo.agent.AgentLoginResultVO;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping({"/api/agent/"})
public class AgentApiController {
    private static final Logger log = LoggerFactory.getLogger(AgentApiController.class);

    @Autowired
    IAgentUserService iAgentUserService;

    //代理后台登录
    @RequestMapping({"login.do"})
    @ResponseBody
    public ServerResponse login(@RequestParam("agentPhone") String agentPhone, @RequestParam("agentPwd") String agentPwd, @RequestParam(value = "verifyCode", required = false, defaultValue = "") String verifyCode, HttpSession httpSession, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        ServerResponse serverResponse = this.iAgentUserService.login(agentPhone, agentPwd, verifyCode, httpServletRequest);
        String agent_cookie_name = PropertiesUtil.getProperty("agent.cookie.name");
        String token = RedisConst.getAgentRedisKey(httpSession.getId());
        if (serverResponse.isSuccess()) {
            CookieUtils.writeLoginToken(httpServletResponse, token, agent_cookie_name);
            String redisSetExResult = RedisShardedPoolUtils.setEx(token,
                    JsonUtil.obj2String(serverResponse.getData()), 5400);
            log.info("redis setex agent result : {}", redisSetExResult);
            AgentLoginResultVO resultVO = new AgentLoginResultVO();
            resultVO.setKey(agent_cookie_name);
            resultVO.setToken(token);
            return ServerResponse.createBySuccess("登陆成功", resultVO);
        }
        return serverResponse;
    }

    //代理后台退出登录
    @RequestMapping({"logout.do"})
    @ResponseBody
    public ServerResponse logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String cookie_name = PropertiesUtil.getProperty("agent.cookie.name");
        String logintoken = CookieUtils.readLoginToken(httpServletRequest, cookie_name);
        log.info("代理 token = {} ,退出登陆", logintoken);
        RedisShardedPoolUtils.del(logintoken);
        CookieUtils.delLoginToken(httpServletRequest, httpServletResponse, cookie_name);
        return ServerResponse.createBySuccess();
    }
}
