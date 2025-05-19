package com.xc.common;

import com.xc.utils.PropertiesUtil;

/**
 * Created by xieyuxing on 2017/9/18.
 */
public class CmcPayConfig {

    //商户后台密匙
    public static final String KEY="1ee6fa81e2471cacdc14714f704bf5ec11";
    //商户后台appid
    public static final String UID="LXHKVG11";
    //同步返回地址
    public static final String RETURN_URL= PropertiesUtil.getProperty("website.domain.url") + "/homes/#/rechargelist";
    //异步回调地址
    public static final String NOTIFY_URL= PropertiesUtil.getProperty("website.domain.url") + "/api/pay/juhenewpayNotify.do";
    //接口服务器地址
    public static final String URL="https://cz.lbzjgp.com/";
    // 商户网页的编码方式【请根据实际情况自行配置】
    public static final String CHARSET="utf-8";
    public static final String TOKEN="ABCDEFG";


    /*H5配置*/
    //商户后台appid
    public static final String H5UID="318543172911";
    //商户后台密匙
    public static final String H5KEY="0aa9ac8194025b7721648cdf541e4e0b11";
    //public static final String H5URL="https://open.yunmianqian.com/api/pay";
    public static final String H5URL="http://yunpay.waa.cn/";
    //异步回调地址
    public static final String H5NOTIFY_URL="http://www.huijuwang888.com/api/pay/juheh5payNotify.do";

}
