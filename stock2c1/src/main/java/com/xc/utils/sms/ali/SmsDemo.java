//package com.xc.utils.sms.ali;
//
//import com.aliyuncs.DefaultAcsClient;
//import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
//import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
//import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
//import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
//import com.aliyuncs.exceptions.ClientException;
//import com.aliyuncs.profile.DefaultProfile;
//import com.xc.utils.PropertiesUtil;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//
//public class SmsDemo {
//    static final String product = "Dysmsapi";
//    static final String domain = "dysmsapi.aliyuncs.com";
//    static final String accessKeyId = PropertiesUtil.getProperty("sms.aliyun.accessKeyId");
//
//    static final String accessKeySecret = PropertiesUtil.getProperty("sms.aliyun.accessKeySecret");
//
//
//    public static SendSmsResponse sendSms() throws ClientException {
//        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
//        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
//
//
//        DefaultProfile defaultProfile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
//        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Dysmsapi", "dysmsapi.aliyuncs.com");
//        DefaultAcsClient defaultAcsClient = new DefaultAcsClient(defaultProfile);
//
//
//        SendSmsRequest request = new SendSmsRequest();
//
//        request.setPhoneNumbers("");
//
//        request.setSignName("金瑞财经");
//
//        request.setTemplateCode("SMS_128055046");
//
//        request.setTemplateParam("{\"name\":\"xiongcan\", \"code\":\"123\"}");
//
//
//        request.setOutId("yourOutId");
//
//
//        return (SendSmsResponse) defaultAcsClient.getAcsResponse(request);
//    }
//
//
//    public static QuerySendDetailsResponse querySendDetails(String bizId) throws ClientException {
//        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
//        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
//
//
//        DefaultProfile defaultProfile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
//        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Dysmsapi", "dysmsapi.aliyuncs.com");
//        DefaultAcsClient defaultAcsClient = new DefaultAcsClient(defaultProfile);
//
//
//        QuerySendDetailsRequest request = new QuerySendDetailsRequest();
//
//        request.setPhoneNumber("15000000000");
//
//        request.setBizId(bizId);
//
//        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
//        request.setSendDate(ft.format(new Date()));
//
//        request.setPageSize(Long.valueOf(10L));
//
//        request.setCurrentPage(Long.valueOf(1L));
//
//
//        return (QuerySendDetailsResponse) defaultAcsClient.getAcsResponse(request);
//    }
//
//
//    public static void main(String[] args) throws ClientException, InterruptedException {
//        SendSmsResponse response = sendSms();
//        System.out.println("短信接口返回的数据----------------");
//        System.out.println("Code=" + response.getCode());
//        System.out.println("Message=" + response.getMessage());
//        System.out.println("RequestId=" + response.getRequestId());
//        System.out.println("BizId=" + response.getBizId());
//
//        Thread.sleep(3000L);
//
//
//        if (response.getCode() != null && response.getCode().equals("OK")) {
//            QuerySendDetailsResponse querySendDetailsResponse = querySendDetails(response.getBizId());
//            System.out.println("短信明细查询接口返回数据----------------");
//            System.out.println("Code=" + querySendDetailsResponse.getCode());
//            System.out.println("Message=" + querySendDetailsResponse.getMessage());
//            int i = 0;
//            for (QuerySendDetailsResponse.SmsSendDetailDTO smsSendDetailDTO : querySendDetailsResponse.getSmsSendDetailDTOs()) {
//
//                System.out.println("SmsSendDetailDTO[" + i + "]:");
//                System.out.println("Content=" + smsSendDetailDTO.getContent());
//                System.out.println("ErrCode=" + smsSendDetailDTO.getErrCode());
//                System.out.println("OutId=" + smsSendDetailDTO.getOutId());
//                System.out.println("PhoneNum=" + smsSendDetailDTO.getPhoneNum());
//                System.out.println("ReceiveDate=" + smsSendDetailDTO.getReceiveDate());
//                System.out.println("SendDate=" + smsSendDetailDTO.getSendDate());
//                System.out.println("SendStatus=" + smsSendDetailDTO.getSendStatus());
//                System.out.println("Template=" + smsSendDetailDTO.getTemplateCode());
//            }
//            System.out.println("TotalCount=" + querySendDetailsResponse.getTotalCount());
//            System.out.println("RequestId=" + querySendDetailsResponse.getRequestId());
//        }
//    }
//}
