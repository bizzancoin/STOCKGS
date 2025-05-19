package com.xc.utils.smsUtil;


import com.xc.common.ServerResponse;

public interface SMSProvider {
    /**
     * 发送单条短信
     *
     * @param mobile  手机号
     * @param content 短信内容
     * @return
     * @throws Exception
     */
    ServerResponse sendSingleMessage(String mobile, String content) throws Exception;

    /**
     * 发送单条短信
     *
     * @param mobile  手机号
     * @param content 短信内容
     * @return
     * @throws Exception
     */
    ServerResponse sendMessageByTempId(String mobile, String content,String templateId) throws Exception;

    /**
     * 发送自定义短信
     * @param mobile
     * @param content
     * @return
     * @throws Exception
     */
    ServerResponse sendCustomMessage(String mobile, String content) throws Exception;

    /**
     * 发送验证码短信
     *
     * @param mobile     手机号
     * @param verifyCode 验证码
     * @return
     * @throws Exception
     */
    default ServerResponse sendVerifyMessage(String mobile, String verifyCode) throws Exception {
        return sendSingleMessage(mobile, formatVerifyCode(verifyCode));
    }

    /**
     * 获取验证码信息格式
     *
     * @param code
     * @return
     */
    default String formatVerifyCode(String code) {
        return String.format("%s", code);
    }

    /**
     * 发送国际短信
     *
     * @param content
     * @param phone
     * @return
     */
    ServerResponse sendInternationalMessage(String content,  String phone) throws Exception;
}
