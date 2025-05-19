package com.xc.utils.ip;


import com.xc.utils.HttpRequest;
import com.xc.utils.PropertiesUtil;
import com.xc.utils.ip.juhe.AddressResultsVo;
import com.xc.utils.redis.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JuheIpApi {
    private static final Logger log = LoggerFactory.getLogger(JuheIpApi.class);


    private static final String ip_url = "http://apis.juhe.cn/ip/ip2addr";


    public static final String APPKEY = PropertiesUtil.getProperty("juhe.ip.key");


    public static String ip2Add(String ips) {
        String params = "?ip=" + ips + "&key=" + APPKEY;

        String retStr = "";
        String address = "查不到此IP";
        if (!"0:0:0:0:0:0:0:1".equals(ips)) {
            try {
                retStr = HttpRequest.doGet("http://apis.juhe.cn/ip/ip2addr", params);


                AddressResultsVo addressResultsVo = (AddressResultsVo) JsonUtil.string2Obj(retStr, AddressResultsVo.class);
                if (addressResultsVo.getResult() != null) {
                    address = addressResultsVo.getResult().getArea();
                }
            } catch (Exception e) {
                log.error("ip转换成地址发生异常,e={}", e);
            }
        }
        return address;
    }
}
