package com.xc.controller.protol;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.xc.common.ServerResponse;
import com.xc.pojo.User;
import com.xc.service.IUserService;
import com.xc.service.IUserWithdrawService;

import javax.servlet.http.HttpServletRequest;

import com.xc.service.impl.LocaleMessageSourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping({"/user/withdraw/"})
@Api("用户提现")
public class UserWithdrawController {
    private static final Logger log = LoggerFactory.getLogger(UserWithdrawController.class);

    @Autowired
    IUserWithdrawService iUserWithdrawService;

    @Autowired
    IUserService iUserService;

    @Autowired
    LocaleMessageSourceService messageSourceService;

    //分页查询所有提现记录
    @PostMapping({"list.do"})
    @ResponseBody
    @ApiOperation("分页查询所有提现记录")
    public ServerResponse list(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "8") int pageSize, @RequestParam(value = "withStatus", required = false) String withStatus, HttpServletRequest request) {
        return this.iUserWithdrawService.findUserWithList(withStatus, request, pageNum, pageSize);
    }

    //用户提现
    @PostMapping({"outMoney.do"})
    @ResponseBody
    @ApiOperation("用户提现")
    public ServerResponse outMoney(String amt, HttpServletRequest request) {
        ServerResponse serverResponse = null;
        User user = this.iUserService.getCurrentRefreshUser(request);
        try {
            serverResponse = this.iUserWithdrawService.outMoney(amt, user.getWithPwd(), request);
        } catch (Exception e) {
            log.error("出金异常 e = {}", e);
            serverResponse = ServerResponse.createByErrorMsg(messageSourceService.getMessage("ERR32"));
        }
        return serverResponse;
    }

    @PostMapping({"cancel.do"})
    @ResponseBody
    @ApiOperation("取消提现")
    public ServerResponse userCancel(Integer withId) {
        return this.iUserWithdrawService.userCancel(withId);
    }
}