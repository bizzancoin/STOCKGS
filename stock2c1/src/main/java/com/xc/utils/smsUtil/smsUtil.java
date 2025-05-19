package com.xc.utils.smsUtil;

import com.xc.common.ServerResponse;
import com.xc.controller.SmsApiController;
import com.xc.dao.SiteSmsLogMapper;
import com.xc.pojo.SiteSmsLog;
import com.xc.service.ISiteSmsLogService;
import com.xc.service.impl.SiteSmsLogServiceImpl;
import com.xc.utils.DateTimeUtil;
import com.xc.utils.PropertiesUtil;
import com.xc.utils.pay.CmcPayOuterRequestUtil;
import com.xc.utils.redis.RedisShardedPoolUtils;
import com.xc.utils.smsUtil.support.AliyunSMSProvider;
import com.xc.utils.smsUtil.support.TecentYunSMSProvider;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class smsUtil {
    private static final Logger log = LoggerFactory.getLogger(SmsApiController.class);

    private static final SMSProvider SMS_PROVIDER=new TecentYunSMSProvider();

    public String sendSMS(String areaCode,String telephone) {
        String code = RandomStringUtils.randomNumeric(4);
        try {
            ServerResponse serverResponse = SMS_PROVIDER.sendInternationalMessage(code, areaCode+telephone);
            if(serverResponse.isSuccess()){
                String keys = "AliyunSmsCode:" + telephone;
                RedisShardedPoolUtils.setEx(keys, code, 5400);
                log.info("smsresult="+telephone+"==code="+code);
                return code;
            }else{
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}
