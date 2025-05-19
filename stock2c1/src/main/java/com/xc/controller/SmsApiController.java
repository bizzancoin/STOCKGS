package com.xc.controller;


import com.github.pagehelper.StringUtil;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.xc.common.ServerResponse;

import com.xc.pojo.SiteSmsLog;
import com.xc.service.ISiteSmsLogService;
import com.xc.service.ISmsService;

import com.xc.utils.DateTimeUtil;
import com.xc.utils.email.AliyunEmailAPI;
import com.xc.utils.redis.RedisShardedPoolUtils;
import com.xc.utils.smsUtil.smsUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping({"/api/sms/"})
@Api("短信或邮箱发送接口")
public class SmsApiController {
    private static final Logger log = LoggerFactory.getLogger(SmsApiController.class);

//    @Autowired
//    ISmsService iSmsService;

    @Autowired
    ISiteSmsLogService iSiteSmsLogService;

    @Autowired
    private AliyunEmailAPI aliyunEmailAPI;

    //註冊用戶 短信發送
    @ApiOperation("短信发送")
    @PostMapping({"sendRegSms.do"})
    @ResponseBody
    public ServerResponse sendRegSms(String areaCode,String phoneNum) {
        if (StringUtils.isBlank(phoneNum)) {
            return ServerResponse.createByErrorMsg("發送失敗，手機號不能為空");
        }
        if(StringUtil.isEmpty(areaCode)){
            return ServerResponse.createByErrorMsg("發送失敗，區號不能為空");
        }
        //如果是邮箱
        if(phoneNum.contains("@")){
            return sendRegEmailSms(phoneNum);
        }
        smsUtil smsUtil = new smsUtil();
        log.info("smsphone"+areaCode+phoneNum);
        String code = smsUtil.sendSMS(areaCode,phoneNum);
        if (!StringUtils.isEmpty(code)) {
            SiteSmsLog siteSmsLog = new SiteSmsLog();
            siteSmsLog.setSmsPhone(phoneNum);
            siteSmsLog.setSmsTitle("註冊驗證碼");
            siteSmsLog.setSmsCnt(code);
            siteSmsLog.setSmsStatus(Integer.valueOf(0));
            siteSmsLog.setSmsTemplate("字段無用");
            siteSmsLog.setAddTime(DateTimeUtil.getCurrentDate());
            iSiteSmsLogService.addData(siteSmsLog);
            return ServerResponse.createBySuccessMsg("發送成功");
        } else {
            return ServerResponse.createByErrorMsg("短信發送失敗，請重試");
        }
    }


    //註冊用戶 郵箱發送
    @ApiOperation("邮箱发送")
    @PostMapping({"sendRegEmailSms.do"})
    @ResponseBody
    public ServerResponse sendRegEmailSms(String email) {
        if (StringUtils.isBlank(email)) {
            return ServerResponse.createByErrorMsg("發送失敗，郵箱號不能為空");
        }
        String code = RandomStringUtils.randomNumeric(4);
        String html = AliyunEmailAPI.HtmlBody.replace("${code}", code);
        log.info("email:"+email+"  code:"+code);
        if (aliyunEmailAPI.sendSingleSendMail(email,"EMAIL REGISTER CODE",html)) {
            SiteSmsLog siteSmsLog = new SiteSmsLog();
            siteSmsLog.setSmsPhone(email);
            siteSmsLog.setSmsTitle("註冊驗證碼");
            siteSmsLog.setSmsCnt(code);
            siteSmsLog.setSmsStatus(Integer.valueOf(0));
            siteSmsLog.setSmsTemplate("");
            siteSmsLog.setAddTime(DateTimeUtil.getCurrentDate());
            iSiteSmsLogService.addData(siteSmsLog);
            String keys = "AliyunSmsCode:" + email;
            RedisShardedPoolUtils.setEx(keys, code, 5400);
            return ServerResponse.createBySuccessMsg("發送成功");
        } else {
            return ServerResponse.createByErrorMsg("短信發送失敗，請重試");
        }
    }

    //找回密碼 短信/邮箱發送
    @PostMapping({"sendForgetSms.do"})
    @ResponseBody
    public ServerResponse sendForgetSms(String areaCode,String phoneNum) {
        //return this.iSmsService.sendAliyunSMS(phoneNum, "SMS_174915941");
        return sendRegSms(areaCode,phoneNum);
    }

    public static void main(String[] args) {
        smsUtil smsUtil = new smsUtil();
        String code = smsUtil.sendSMS("855","6666088889");
    }
}
