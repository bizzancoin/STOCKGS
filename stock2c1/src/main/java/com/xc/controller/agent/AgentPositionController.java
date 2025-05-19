package com.xc.controller.agent;


import com.xc.common.ServerResponse;

import com.xc.service.IUserPositionService;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping({"/agent/position/"})

public class AgentPositionController {
    private static final Logger log = LoggerFactory.getLogger(AgentPositionController.class);


    @Autowired
    IUserPositionService iUserPositionService;

    //分页查询持仓管理 所有融资平仓单信息及模糊查询
    @RequestMapping({"list.do"})
    @ResponseBody
    public ServerResponse list(HttpServletRequest request, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, @RequestParam(value = "positionType", required = false) Integer positionType, @RequestParam(value = "state", required = false) Integer state, @RequestParam(value = "userId", required = false) Integer userId, @RequestParam(value = "agentId", required = false) Integer agentId, @RequestParam(value = "positionSn", required = false) String positionSn, @RequestParam(value = "beginTime", required = false) String beginTime, @RequestParam(value = "endTime", required = false) String endTime) {
        return this.iUserPositionService.listByAgent(positionType, state, userId, agentId, positionSn, beginTime, endTime, request, pageNum, pageSize);
    }

}

