package com.xc.utils.smsUtil.support;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.xc.common.ServerResponse;
import com.xc.utils.smsUtil.SMSProvider;

public class AliyunSMSProvider implements SMSProvider {
    @Override
    public ServerResponse sendSingleMessage(String mobile, String content) throws Exception {
        return null;
    }

    @Override
    public ServerResponse sendMessageByTempId(String mobile, String content, String templateId) throws Exception {
        return null;
    }

    @Override
    public ServerResponse sendVerifyMessage(String mobile, String verifyCode) throws Exception {
        //String smsContent = "【" + this.sign + "】您的验证码为" + verifyCode + "，在10分钟内有效。";
        String smsContent = "Your verification code is "+verifyCode+"，at 10 Valid in minutes。";
        return  msgSender(mobile,smsContent);
    }

    @Override
    public String formatVerifyCode(String code) {
        return null;
    }

    @Override
    public ServerResponse sendInternationalMessage(String content, String phone) throws Exception {
        String smsContent = "Your verification code is "+content+"，at 10 Valid in minutes。";
        return  msgSender(phone,smsContent);
    }


    @Override
    public ServerResponse sendCustomMessage(String mobile, String content) throws Exception {
        return null;
    }
    private ServerResponse msgSender(String mobile,String smsContent){
        DefaultProfile profile = DefaultProfile.getProfile("ap-southeast-1", "", "");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.ap-southeast-1.aliyuncs.com");
        request.setSysVersion("2018-05-01");
        request.setSysAction("SendMessageToGlobe");
        request.putQueryParameter("RegionId", "ap-southeast-1");
        request.putQueryParameter("To", mobile);
        request.putQueryParameter("Message", smsContent);
        try {
            CommonResponse response = client.getCommonResponse(request);
            String data = response.getData();
            JSONObject jsonObject = JSON.parseObject(data);

            ServerResponse serverResponse = ServerResponse.createByErrorMsg("系统错误");


            System.out.println(response.getData());
            if(jsonObject.getString("ResponseCode").equals("OK")) {
                serverResponse=ServerResponse.createBySuccessMsg("短信发送成功！");
            }else{
                serverResponse = ServerResponse.createByErrorMsg("短信发送失败，请联系平台处理！");
            }
            return serverResponse;
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        AliyunSMSProvider aliyunSMSProvider = new AliyunSMSProvider();
        try {

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
