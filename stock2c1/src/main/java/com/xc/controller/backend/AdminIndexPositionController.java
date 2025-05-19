package com.xc.controller.backend;


import com.xc.common.ServerResponse;


import com.xc.service.IUserIndexPositionService;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping({"/admin/index/position/"})
public class AdminIndexPositionController {
    private static final Logger log = LoggerFactory.getLogger(AdminIndexPositionController.class);


    @Autowired
    IUserIndexPositionService iUserIndexPositionService;

    //分页查询持仓管理 所有的指数持仓单/平仓单 及模糊查询
    @RequestMapping({"list.do"})
    @ResponseBody
    public ServerResponse list(@RequestParam(value = "agentId", required = false) Integer agentId, @RequestParam(value = "positionType", required = false) Integer positionType, @RequestParam(value = "state", required = false) Integer state, @RequestParam(value = "userId", required = false) Integer userId, @RequestParam(value = "positionSn", required = false) String positionSn, @RequestParam(value = "beginTime", required = false) String beginTime, @RequestParam(value = "endTime", required = false) String endTime, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "12") int pageSize, HttpServletRequest request) {
        return this.iUserIndexPositionService.listByAdmin(agentId, positionType, state, userId, positionSn, beginTime, endTime, pageNum, pageSize);
    }

    @RequestMapping({"sell.do"})
    @ResponseBody
    public ServerResponse sell(String positionSn) throws Exception {
        ServerResponse serverResponse = null;
        try {
            serverResponse = this.iUserIndexPositionService.sellIndex(positionSn, 0);
        } catch (Exception e) {
            log.error("强制平仓 指数 异常信息 = {}", e);
        }
        return serverResponse;
    }

    @RequestMapping({"del.do"})
    @ResponseBody
    public ServerResponse del(@RequestParam("positionId") Integer positionId) {
        return this.iUserIndexPositionService.del(positionId);
    }

    @RequestMapping({"lock.do"})
    @ResponseBody
    public ServerResponse lock(@RequestParam("positionId") Integer positionId, @RequestParam("state") Integer state, @RequestParam(value = "lockMsg", required = false) String lockMsg) {
        return this.iUserIndexPositionService.lock(positionId, state, lockMsg);
    }
}
