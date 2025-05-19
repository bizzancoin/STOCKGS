package com.xc.utils;

import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;
import java.util.Set;

/***
 * http链接池工具
 */
public class HttpPoolUtils {
    private static final CloseableHttpClient httpClient;
    public static final String CHARSET_UTF8 = "UTF-8";
    // 将最大连接数增加到
    public static final int MAX_TOTAL = 600;
    // 将每个路由基础的连接增加到
    public static final int MAX_ROUTE_TOTAL = 600;
    public static final int SOCKET_TIME = 60 * 1000;
    public static final int CONNECT_TIME = 60 * 1000;
    public static final int CONNECTION_REQUEST_TIMEOUT = 60 * 1000;

    private static Logger LOGGER = LoggerFactory.getLogger(HttpPoolUtils.class);

    static {
        PoolingHttpClientConnectionManager httpClientConnectionManager = new PoolingHttpClientConnectionManager();
        httpClientConnectionManager.setMaxTotal(MAX_TOTAL);
        httpClientConnectionManager.setDefaultMaxPerRoute(MAX_ROUTE_TOTAL);
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT).setConnectTimeout(CONNECT_TIME).setSocketTimeout(SOCKET_TIME).build();
        //设置重定向策略
        LaxRedirectStrategy redirectStrategy = new LaxRedirectStrategy();

        httpClient = HttpClients.custom().setConnectionManager(httpClientConnectionManager).setDefaultRequestConfig(requestConfig).setRedirectStrategy(redirectStrategy).build();
    }

    /**
     * 将map参数转化为url参数  Set<String> exclude 这个参数具体根据业务需求增加的，过滤某一个或者某几个字段不进行http参数构造
     */
    public static String getUrlParam(Object param, Set<String> exclude) {

        JSONObject jsonObject = JSONObject.fromObject(param);

        LOGGER.info("json is " + jsonObject);
        StringBuffer url = new StringBuffer();
        // 组装参数
        if (null != jsonObject) {
            url.append("?");
            for (Object key : jsonObject.keySet()) {
                if (CollectionUtils.isNotEmpty(exclude) && exclude.contains(key.toString())) {
                    continue;
                }

                try {
                    url.append(key.toString() + "=" + URLEncoder.encode(jsonObject.get(key).toString(), "UTF-8") + "&");
                } catch (Exception e) {
                    LOGGER.error("getUrlParam", e);
                }

            }
            url.deleteCharAt(url.length() - 1);
        }

        return url.toString();
    }
}