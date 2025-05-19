//package com.xc.utils.sms.ali;
//import com.aliyuncs.DefaultAcsClient;
//import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
//import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
//import com.aliyuncs.exceptions.ClientException;
//import com.aliyuncs.profile.DefaultProfile;
//import com.xc.utils.PropertiesUtil;
//
//
//
//public class AliyunSms {
//    static final String product = "Dysmsapi";
//    static final String domain = "dysmsapi.aliyuncs.com";
//    static final String accessKeyId = PropertiesUtil.getProperty("sms.aliyun.accessKeyId");
//
//    static final String accessKeySecret = PropertiesUtil.getProperty("sms.aliyun.accessKeySecret");
//
//
//
//    public static SendSmsResponse sendSms(String phoneNum, String signName, String templateCode, String yzmCode) throws ClientException {
//        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
//        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
//
//        DefaultProfile defaultProfile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
//        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Dysmsapi", "dysmsapi.aliyuncs.com");
//        DefaultAcsClient defaultAcsClient = new DefaultAcsClient(defaultProfile);
//
//
//        SendSmsRequest request = new SendSmsRequest();
//
//        request.setPhoneNumbers(phoneNum);
//
//        request.setSignName(signName);
//
//        request.setTemplateCode(templateCode);
//
//        request.setTemplateParam("{\"code\":\"" + yzmCode + "\"}");
//
//        request.setOutId("yourOutId");
//
//        return (SendSmsResponse)defaultAcsClient.getAcsResponse(request);
//    }
//
//
//
//    public static SendSmsResponse sendSmsTZ(String phoneNum, String signName, String templateCode, String stockname) throws ClientException {
//        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
//        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
//
//        DefaultProfile defaultProfile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
//        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Dysmsapi", "dysmsapi.aliyuncs.com");
//        DefaultAcsClient defaultAcsClient = new DefaultAcsClient(defaultProfile);
//
//        SendSmsRequest request = new SendSmsRequest();
//
//        request.setPhoneNumbers(phoneNum);
//
//        request.setSignName(signName);
//
//        request.setTemplateCode(templateCode);
//
//        request.setTemplateParam("{\"name\":\"" + stockname + "\"}");
//
//        request.setOutId("yourOutId");
//
//        return (SendSmsResponse)defaultAcsClient.getAcsResponse(request);
//    }
//}
