package com.xc.controller.agent;


import com.xc.common.ServerResponse;
import com.xc.service.IUserCashDetailService;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/agent/cash/"})
public class AgentCashDetailController {
    private static final Logger log = LoggerFactory.getLogger(AgentCashDetailController.class);
    @Autowired
    IUserCashDetailService iUserCashDetailService;

    //分页查询所有资金明细信息及模糊查询
    @RequestMapping({"list.do"})
    @ResponseBody
    public ServerResponse list(HttpServletRequest request, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, @RequestParam(value = "userId", required = false) Integer userId, @RequestParam(value = "userName", required = false) String userName, @RequestParam(value = "agentId", required = false) Integer agentId, @RequestParam(value = "positionId", required = false) Integer positionId) {
        return this.iUserCashDetailService.listByAgent(userId, userName, agentId, positionId, request, pageNum, pageSize);
    }
}
