//package com.xc.service.impl;
//
//
//import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
//import com.xc.common.ServerResponse;
//import com.xc.dao.SiteSmsLogMapper;
//import com.xc.pojo.SiteSmsLog;
//import com.xc.service.ISmsService;
//import com.xc.utils.DateTimeUtil;
//import com.xc.utils.redis.RedisShardedPoolUtils;
//import com.xc.utils.sms.ali.AliyunSms;
//import com.xc.utils.smsUtil.smsUtil;
//import org.apache.commons.lang3.RandomStringUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//
//@Service("iSmsService")
//public class SmsServiceImpl implements ISmsService {
//    private static final Logger log = LoggerFactory.getLogger(SmsServiceImpl.class);
//
//
//    @Autowired
//    SiteSmsLogMapper siteSmsLogMapper;
//
////    public ServerResponse sendAliyunSMS(String phoneNum, String ali_template) {
////        System.out.println(phoneNum);
////        if (StringUtils.isBlank(phoneNum)) return ServerResponse.createByErrorMsg("发送失败，手机号不能为空");
////
////        String yzmCode = RandomStringUtils.randomNumeric(4);
////        System.out.println("验证码：" + yzmCode);
////
////        SendSmsResponse response = null;
////        try {
////            response = AliyunSms.sendSms(phoneNum, "鸿鹄", ali_template, yzmCode);
////        } catch (Exception e) {
////            log.error("发送短信异常：{}", e);
////        }
////        System.out.println("短信接口返回的数据----------------");
////        System.out.println("Code=" + response.getCode());
////        System.out.println("Message=" + response.getMessage());
////        System.out.println("RequestId=" + response.getRequestId());
////        System.out.println("BizId=" + response.getBizId());
////        if (response.getCode() != null && response.getCode().equals("OK")) {
////            String keys = "AliyunSmsCode:" + phoneNum;
////            RedisShardedPoolUtils.setEx(keys, yzmCode, 5400);
////            SiteSmsLog siteSmsLog = new SiteSmsLog();
////            siteSmsLog.setSmsPhone(phoneNum);
////            siteSmsLog.setSmsTitle("注册验证码");
////            siteSmsLog.setSmsCnt(yzmCode);
////            siteSmsLog.setSmsTemplate(ali_template);
////            siteSmsLog.setSmsStatus(Integer.valueOf(0));
////            siteSmsLog.setAddTime(DateTimeUtil.getCurrentDate());
////            this.siteSmsLogMapper.insert(siteSmsLog);
////            return ServerResponse.createBySuccessMsg("发送成功");
////        }
////        return ServerResponse.createByErrorMsg("短信发送失败，请重试");
////    }
//
////    public ServerResponse sendAliyunSMS(String phoneNum, String ali_template) {
////        System.out.println(phoneNum);
////        if (StringUtils.isBlank(phoneNum)) return ServerResponse.createByErrorMsg("发送失败，手机号不能为空");
////
////        String yzmCode = RandomStringUtils.randomNumeric(4);
////        log.info("验证码：" + yzmCode);
////        smsUtil smsUtil = new smsUtil();
////        try {
////            smsUtil.sendSMS()
////            response = AliyunSms.sendSms(phoneNum, "鸿鹄", ali_template, yzmCode);
////        } catch (Exception e) {
////            log.error("发送短信异常：{}", e);
////        }
////        System.out.println("短信接口返回的数据----------------");
////        System.out.println("Code=" + response.getCode());
////        System.out.println("Message=" + response.getMessage());
////        System.out.println("RequestId=" + response.getRequestId());
////        System.out.println("BizId=" + response.getBizId());
////        if (response.getCode() != null && response.getCode().equals("OK")) {
////            String keys = "AliyunSmsCode:" + phoneNum;
////            RedisShardedPoolUtils.setEx(keys, yzmCode, 5400);
////            SiteSmsLog siteSmsLog = new SiteSmsLog();
////            siteSmsLog.setSmsPhone(phoneNum);
////            siteSmsLog.setSmsTitle("注册验证码");
////            siteSmsLog.setSmsCnt(yzmCode);
////            siteSmsLog.setSmsTemplate(ali_template);
////            siteSmsLog.setSmsStatus(Integer.valueOf(0));
////            siteSmsLog.setAddTime(DateTimeUtil.getCurrentDate());
////            this.siteSmsLogMapper.insert(siteSmsLog);
////            return ServerResponse.createBySuccessMsg("发送成功");
////        }
////        return ServerResponse.createByErrorMsg("短信发送失败，请重试");
////    }
//}
