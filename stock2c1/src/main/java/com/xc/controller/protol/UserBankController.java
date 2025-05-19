package com.xc.controller.protol;


import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.xc.common.ServerResponse;
import com.xc.pojo.UserBank;
import com.xc.service.IUserBankService;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/user/bank/"})
@Api("用户银行卡信息")
public class UserBankController {
    private static final Logger log = LoggerFactory.getLogger(UserBankController.class);

    @Autowired
    IUserBankService iUserBankService;

    @ApiOperation("添加银行卡")
    @PostMapping({"add.do"})
    @ResponseBody
    public ServerResponse add(UserBank bank, HttpServletRequest request) {
        return this.iUserBankService.addBank(bank, request);
    }

    //修改银行卡信息
    @ApiOperation("修改银行卡信息")
    @PostMapping({"update.do"})
    @ResponseBody
    public ServerResponse update(UserBank bank, HttpServletRequest request) {
        return this.iUserBankService.updateBank(bank, request);
    }

    //查询用户银行卡信息
    @ApiOperation("查询用户银行卡信息")
    @PostMapping({"getBankInfo.do"})
    @ResponseBody
    public ServerResponse getBankInfo(HttpServletRequest request) {
        return this.iUserBankService.getBankInfo(request);
    }


}
