package com.xc.utils.pay;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.map.LinkedMap;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class CmcPayOuterRequestUtil {

    public static String sendGet(String url) {
        String result = "";
        BufferedReader in = null;
        try {

            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();

            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();

            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    public static String sendGet(String url, LinkedMap params) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = getUrlString(url, params);
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();

            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    public static String getUrlString(String url, LinkedMap params) throws UnsupportedEncodingException {
        return url + "?" + getParamsString(params);
    }

    /**
     * 封装HTTP POST方法
     * @param
     * @param （如JSON串）
     * @return
     * @throws ClientProtocolException
     * @throws java.io.IOException
     */
    public static String post(String url, Map<String, String> paramMap) throws ClientProtocolException, IOException {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> formparams = setHttpParams(paramMap);
        UrlEncodedFormEntity param = new UrlEncodedFormEntity(formparams, "utf-8");
        httpPost.setEntity(param);
        HttpResponse response = httpClient.execute(httpPost);
        String httpEntityContent = getHttpEntityContent(response);
        httpPost.abort();
        return httpEntityContent;
    }

    /**
     * 设置请求参数
     * @param
     * @return
     */
    private static List<NameValuePair> setHttpParams(Map<String, String> paramMap) {
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        Set<Map.Entry<String, String>> set = paramMap.entrySet();
        for (Map.Entry<String, String> entry : set) {
            formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return formparams;
    }

    /**
     * 获得响应HTTP实体内容
     * @param response
     * @return
     * @throws java.io.IOException
     * @throws java.io.UnsupportedEncodingException
     */
    private static String getHttpEntityContent(HttpResponse response) throws IOException, UnsupportedEncodingException {
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            InputStream is = entity.getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line = br.readLine();
            StringBuilder sb = new StringBuilder();
            while (line != null) {
                sb.append(line + "\n");
                line = br.readLine();
            }
            return sb.toString();
        }
        return "";
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url    发送请求的 URL
     * @param params 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, LinkedMap params) throws IOException {
        // Post请求的url，与get不同的是不需要带参数
//        URL postUrl = new URL("http://127.0.0.1:8088/mall/user/test");
        String result = "";
        URL postUrl = new URL(url);
        // 打开连接
        HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();

        // 设置是否向connection输出，因为这个是post请求，参数要放在
        // http正文内，因此需要设为true
        connection.setDoOutput(true);
        // Read from the connection. Default is true.
        connection.setDoInput(true);
        // 默认是 GET方式
        connection.setRequestMethod("POST");

        // Post 请求不能使用缓存
        connection.setUseCaches(false);

        connection.setInstanceFollowRedirects(true);

        // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
        // 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode
        // 进行编码
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
        // 要注意的是connection.getOutputStream会隐含的进行connect。


        connection.connect();
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        // The URL-encoded contend
        // 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
//        String content = "account=" + URLEncoder.encode("一个大肥人", "UTF-8");
//        content +="&pswd="+URLEncoder.encode("两个个大肥人", "UTF-8");
//        // DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写到流里面
//        out.writeBytes(content);
        String content = getParamsString(params);
        out.writeBytes(content);
        out.flush();
        out.close();

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            result += line;
        }

        reader.close();
        connection.disconnect();
        return result;
    }

    public static String getParamsString(LinkedMap params) throws UnsupportedEncodingException {
        String content = new String();
        Set<String> keySet = params.keySet();
        String[] keys = keySet.toArray(new String[keySet.size()]);
        for (String key : keys) {
            String value = (String)params.get(key);
            if (value == null) continue;
            if (content.length() != 0) content += "&";
            content += key + "=" + URLEncoder.encode(value, "UTF-8");
        }
        return content;
    }

    private static final String SIGN_KEY = "sign";

    /** 密钥属性名key**/
    private static final String SECRET_KEY = "key";
    /**
     * 计算签名
     *
     * @param jsonObj
     *            要参与签名的json数据
     * @param md5Key
     *            密钥
     * @return 签名
     */
    public static String getSign(LinkedMap jsonObj, String md5Key) {
        if (jsonObj == null || jsonObj.isEmpty()) {
            return null;
        }
        String str2Sign = buildParam4Sign(jsonObj, SIGN_KEY, md5Key);
        try {
            byte[] data = str2Sign.getBytes("utf-8");
            String result = DigestUtils.md5Hex(data);
            return result;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 拼接用于签名的参数
     * @param jsonObj
     * @return
     */
    private static String buildParam4Sign(LinkedMap jsonObj, String signKey, String md5Key) {
        Set<String> keySet = jsonObj.keySet();
        StringBuilder param = new StringBuilder(20 * keySet.size());
        String[] keys = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keys, String.CASE_INSENSITIVE_ORDER);
        /*for (String key : keys) {
            // 排除sign
            if (signKey.equals(key)) {
                continue;
            }
            Object value = jsonObj.get(key);
            // 排除值为null的情况
            param.append(key).append("=").append(value).append("&");
            //param.append(key).append("=").append(value);
        }
        param.deleteCharAt(param.length()-1);
        param.append(md5Key);*/
        param.append(jsonObj.get("merchantid"));
        param.append(jsonObj.get("orderno"));
        param.append(jsonObj.get("orderamount"));
        param.append(jsonObj.get("serverbackurl"));
        param.append(jsonObj.get("callbackurl"));
        param.append(md5Key);
        System.out.println(param.toString());
        return param.toString();
    }

    public static String getSignH5(LinkedMap jsonObj, String md5Key)  throws Exception{
        if (jsonObj == null || jsonObj.isEmpty()) {
            return null;
        }
        StringBuilder param = new StringBuilder();
        param.append("appid="+jsonObj.get("appid"));
        param.append("&data="+jsonObj.get("data"));
        param.append("&money="+jsonObj.get("money"));
        param.append("&type="+jsonObj.get("type"));
        param.append("&uip="+jsonObj.get("uip"));
        param.append("&appkey="+md5Key);
        String str2Sign = param.toString().toLowerCase();
        try {
            /*byte[] data = str2Sign.getBytes("utf-8");
            String result = DigestUtils.md5Hex(data);*/
            String result = encryption(str2Sign);
            return result;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * md5加密 32位 小写
     * @param plainText
     * @return
     */
    public static String encryption(String plainText)  throws Exception{
        String re_md5 = new String();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }

            re_md5 = buf.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return re_md5;
    }


}
