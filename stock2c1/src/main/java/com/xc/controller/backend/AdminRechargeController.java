package com.xc.controller.backend;


import com.github.pagehelper.PageInfo;

import com.xc.common.ServerResponse;

import com.xc.service.IUserRechargeService;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Controller
@RequestMapping({"/admin/recharge/"})
public class AdminRechargeController {

    private static final Logger log = LoggerFactory.getLogger(AdminRechargeController.class);
    @Autowired

    IUserRechargeService iUserRechargeService;

    //分页查询资金管理 充值列表信息及模糊查询
    @RequestMapping({"list.do"})
    @ResponseBody
    public ServerResponse<PageInfo> list(@RequestParam(value = "agentId", required = false) Integer agentId, @RequestParam(value = "userId", required = false) Integer userId, @RequestParam(value = "realName", required = false) String realName, @RequestParam(value = "state", required = false) Integer state, @RequestParam(value = "beginTime", required = false) String beginTime, @RequestParam(value = "endTime", required = false) String endTime, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, HttpServletRequest request) {
        return this.iUserRechargeService.listByAdmin(agentId, userId, realName, state, beginTime, endTime, request, pageNum, pageSize);
    }

    //修改资金管理 充值列表订单状态
    @RequestMapping({"updateState.do"})
    @ResponseBody
    public ServerResponse updateState(@RequestParam(value = "chargeId", required = false) Integer chargeId, @RequestParam(value = "state", required = false) Integer state) {
        ServerResponse serverResponse = null;
        try {
            serverResponse = this.iUserRechargeService.updateState(chargeId, state);
        } catch (Exception e) {
            log.error("admin修改充值订单状态出错 ，异常 = {}", e);
        }
        return serverResponse;
    }

    //创建资金管理 充值订单
    @RequestMapping({"createOrder.do"})
    @ResponseBody
    public ServerResponse createOrder(@RequestParam(value = "userId", required = false) Integer userId, @RequestParam(value = "state", required = false) Integer state, @RequestParam(value = "amt", required = false) BigDecimal amt, @RequestParam(value = "payChannel", required = false) String payChannel) {
        if(!ObjectUtils.isEmpty(amt)){
            amt.setScale(2, RoundingMode.DOWN);
        }
        return this.iUserRechargeService.createOrder(userId, state, amt, payChannel);
    }

    //删除资金管理 充值列表订单信息
    @RequestMapping({"del.do"})
    @ResponseBody
    public ServerResponse del(@RequestParam("cId") Integer cId) {
        return this.iUserRechargeService.del(cId);
    }
}
