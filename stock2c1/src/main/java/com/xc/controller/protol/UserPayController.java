package com.xc.controller.protol;


import com.xc.common.ServerResponse;
import com.xc.service.IPayService;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping({"/user/pay/"})
public class UserPayController {
    private static final Logger log = LoggerFactory.getLogger(UserPayController.class);

    @Autowired
    IPayService iPayService;

    @RequestMapping({"juhe1.do"})
    @ResponseBody
    public ServerResponse juhe1(@RequestParam("payType") String payType, @RequestParam("payAmt") String payAmt, HttpServletRequest request) throws Exception {
        log.info("发起线上支付 payType = {} payAmt = {}", payType, payAmt);
        return this.iPayService.juhenewpay(payType, payAmt, request);
    }

    @RequestMapping({"juheh5.do"})
    @ResponseBody
    public ServerResponse juheh5(@RequestParam("payType") String payType, @RequestParam("payAmt") String payAmt, HttpServletRequest request) throws Exception {
        log.info("发起线上支付 payType = {} payAmt = {}", payType, payAmt);
        return this.iPayService.juheh5pay(payType, payAmt, request);
    }

    @RequestMapping({"juhenewpayNotify.do"})
    public String juhenewpayNotify(HttpServletRequest request) throws Exception {
        iPayService.juhe1Notify(request);
        return "success";
    }

    @RequestMapping({"flyPay.do"})
    @ResponseBody
    public ServerResponse flyPay(@RequestParam("payType") String payType, @RequestParam("payAmt") String payAmt, @RequestParam("currency") String currency, HttpServletRequest request) {
        log.info("发起 fly 线上支付 payType = {} payAmt = {} currency = {}", new Object[]{payType, payAmt, currency});
        return this.iPayService.flyPay(payType, payAmt, currency, request);
    }

}
