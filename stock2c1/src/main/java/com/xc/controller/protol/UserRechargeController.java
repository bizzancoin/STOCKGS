package com.xc.controller.protol;


import com.github.pagehelper.PageInfo;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.xc.common.ServerResponse;
import com.xc.service.IUserRechargeService;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping({"/user/recharge/"})
@Api("用户充值")
public class UserRechargeController {
    private static final Logger log = LoggerFactory.getLogger(UserRechargeController.class);

    @Autowired
    IUserRechargeService iUserRechargeService;

    //分页查询所有充值记录
    @ApiOperation("分页查询所有充值记录")
    @PostMapping({"list.do"})
    @ResponseBody
    public ServerResponse<PageInfo> list(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, @RequestParam(value = "payChannel", required = false) String payChannel, @RequestParam(value = "orderStatus", required = false) String orderStatus, HttpServletRequest request) {
        return this.iUserRechargeService.findUserChargeList(payChannel, orderStatus, request, pageNum, pageSize);
    }

    //账户线下充值转账 创建充值订单
    @ApiOperation("充值操作")
    @PostMapping({"inMoney.do"})
    @ResponseBody
    public ServerResponse inMoney(String amt, String payType, HttpServletRequest request) {
        return this.iUserRechargeService.inMoney(amt, payType, request);
    }
}

