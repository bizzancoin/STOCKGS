package com.xc.utils;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.StandardHttpRequestRetryHandler;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServletRequest;


public class HttpClientRequest {


    private static CloseableHttpClient httpClient = null;

    static {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        // 总连接池数量
        connectionManager.setMaxTotal(150);
        // 可为每个域名设置单独的连接池数量
        //connectionManager.setMaxPerRoute(new HttpRoute(new HttpHost("xx.xx.xx.xx")), 80);
        // setConnectTimeout：设置建立连接的超时时间
        // setConnectionRequestTimeout：从连接池中拿连接的等待超时时间
        // setSocketTimeout：发出请求后等待对端应答的超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(1000)
                .setConnectionRequestTimeout(2000)
                .setSocketTimeout(3000)
                .build();
        // 重试处理器，StandardHttpRequestRetryHandler
        HttpRequestRetryHandler retryHandler = new StandardHttpRequestRetryHandler();

        httpClient = HttpClients.custom().setConnectionManager(connectionManager).setDefaultRequestConfig(requestConfig)
                .setRetryHandler(retryHandler).build();
    }

        public static String doGet(String url) {
        //CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = "";
        HttpGet httpGet = null;
        try {
           // httpClient = HttpClients.createDefault();

            httpGet = new HttpGet(url);

            httpGet.setHeader("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");
            httpGet.setHeader("Referer","https://finance.sina.com.cn/stock/");
            httpGet.setHeader("hexin-v","A9c3oOLeeBaBqPya4SZnuaMDZkAkHKo7hfAv8ikE86YNWPk-Mew7zpXAv0c6");
            //把代理设置到请求配置        代理IP     端口
            //HttpHost proxy = new HttpHost(new HttpHost("58.253.159.12",9999));
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000).setConnectionRequestTimeout(35000).setSocketTimeout(60000).build();

            httpGet.setConfig(requestConfig);

            response = httpClient.execute(httpGet);

            HttpEntity entity = response.getEntity();

            result = EntityUtils.toString(entity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
//            if (null != httpClient) {
//                try {
//                    httpClient.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
            if(null!=httpGet){
                httpGet.releaseConnection();
            }

        }
        return result;
    }

    public static String doPost(String url, Map<String, Object> paramMap) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        String result = "";

        httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(url);


        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000).setConnectionRequestTimeout(35000).setSocketTimeout(60000).build();

        httpPost.setConfig(requestConfig);

        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");

        if (null != paramMap && paramMap.size() > 0) {
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();

            Set<Map.Entry<String, Object>> entrySet = paramMap.entrySet();

            Iterator<Map.Entry<String, Object>> iterator = entrySet.iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> mapEntry = (Map.Entry) iterator.next();
                nvps.add(new BasicNameValuePair((String) mapEntry.getKey(), mapEntry.getValue().toString()));
            }


            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        try {
            httpResponse = httpClient.execute(httpPost);

            HttpEntity entity = httpResponse.getEntity();
            result = EntityUtils.toString(entity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (null != httpResponse) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
//        for (int i=0 ;i<100;i++){
//            String s = HttpClientRequest.doGet("http://qt.gtimg.cn/q=s_sh600519");
//            System.out.println(s);
//        }
//        long e=System.currentTimeMillis();
//        System.out.println("耗时："+(e-l)/1000.0);
        String s = HttpClientRequest.doGet("https://hq.sinajs.cn/rn=1520407404627&list=s_sh000001,s_sz399001,s_sz399006,s_sz399300,s_sz399005,s_sz399673,s_sz399106,s_sz399004,s_sz399100");
        System.out.println(s);
    }

}
