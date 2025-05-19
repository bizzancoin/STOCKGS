package com.xc.controller.agent;


import com.xc.common.ServerResponse;

import com.xc.service.IUserWithdrawService;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping({"/agent/withdraw/"})
public class AgentWithdrawController {

    private static final Logger log = LoggerFactory.getLogger(AgentWithdrawController.class);

    @Autowired
    IUserWithdrawService iUserWithdrawService;


    //分页查询所有出金记录信息及模糊查询
    @RequestMapping({"list.do"})
    @ResponseBody
    public ServerResponse list(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "8") int pageSize, @RequestParam(value = "realName", required = false) String realName, @RequestParam(value = "state", required = false) Integer state, @RequestParam(value = "agentId", required = false) Integer agentId, HttpServletRequest request) {
        return this.iUserWithdrawService.listByAgent(agentId, realName, state, request, pageNum, pageSize);
    }
}
