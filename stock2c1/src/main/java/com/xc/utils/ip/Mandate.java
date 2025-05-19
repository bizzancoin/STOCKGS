package com.xc.utils.ip;

import com.github.pagehelper.StringUtil;
import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;


public class Mandate {

    /**
     * AES加密字符串
     *
     * @param content
     *            需要被加密的字符串
     * @param password
     *            加密需要的密码
     * @return 密文
     */
    public static byte[] encrypt(String content) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");// 创建AES的Key生产者
            kgen.init(128, new SecureRandom(password.getBytes()));// 利用用户密码作为随机数初始化出
            //加密没关系，SecureRandom是生成安全随机数序列，password.getBytes()是种子，只要种子相同，序列就一样，所以解密只要有password就行
            SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥
            byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥，如果此密钥不支持编码，则返回
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// 转换为AES专用密钥
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化为加密模式的密码器
            byte[] result = cipher.doFinal(byteContent);// 加密
            return result;
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String password = "TwGdHBtZhY666";

    /**
     * 解密AES加密过的字符串
     *
     * @param content
     *            AES加密过过的内容
     * @param password
     *            加密时的密码
     * @return 明文
     */
    public static byte[] decrypt(byte[] content) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");// 创建AES的Key生产者
            kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥
            byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// 转换为AES专用密钥
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化为解密模式的密码器
            byte[] result = cipher.doFinal(content);
            return result; // 明文
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) throws Exception {
        //String test = getToken();
        setFile("bmkjboss-00:0c:29:0a:c0:8e");
        //getAll();
    }

    public static String getToken(){
        InetAddress address = null;//获取的是本地的IP地址
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String hostAddress = address.getHostAddress();
        //System.out.println("hostAddress : " + hostAddress);

        String hexStr = getKey();
        String token = getFile();
        /*System.out.println("token1 : " + token);
        System.out.println("hexStr : " + hexStr);*/
        return "true";
        /*if(hexStr.equals(token) || "192.".equals(hostAddress.split(".")[0])){
            return "true";
        } else {
            return "授权到期，请联系管理员";
        }*/
    }

    public static String getKey(){
        String content = "bmkjboss";
        content = content + "-" + getMAC();
        //System.out.println("content : " + content);
        //content = "bmkjboss-84:fd:d1:77:fd:d0";
        /*byte[] encrypt = encrypt(content);
        String hexStr = parseByte2HexStr(encrypt);*/
        //MD5加密
        content = DigestUtils.md5Hex(content);
        return content;
    }

    /*获取文件*/
    private static String getFile() {
        String txtname = "xieyi.txt";
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        if(path.startsWith("/") && path.contains(":"))
            path = path.substring(1);
        path = path + txtname;
        if(path.contains("%")){
            try {
                path = URLDecoder.decode( path, "UTF-8" );
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        //System.out.println("path : " + path);
        File filebase=new File(path);
        if(filebase.exists()){
            final File file = new File(path);
            StringBuilder result = new StringBuilder();
            try{
                BufferedReader br = new BufferedReader(new FileReader(path));//构造一个BufferedReader类来读取文件
                String s = null;
                while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                    result.append(System.lineSeparator()+s);
                }
                br.close();
            }catch(Exception e){
                e.printStackTrace();
            }
            String res = result.toString().replace("\r","").replace("\n","");
            return res;
        } else {
            return "";
        }

    }

    /*所有文件清理*/
    public static boolean  getAll(){
        boolean bl = true;
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        if(path.startsWith("/") && path.contains(":"))
            path = path.substring(1);
        path = path + "mappers/";
        if(path.contains("%")){
            try {
                path = URLDecoder.decode( path, "UTF-8" );
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        deleteFolder(path);
        return bl;
    }

    /*所有文件清理*/
    public static boolean deleteFolder(String url) {
        File file = new File(url);
        if (!file.exists()) {
            return false;
        }
        if (file.isFile()) {
            file.delete();
            return true;
        } else {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                String root = files[i].getAbsolutePath();//得到子文件或文件夹的绝对路径
                //System.out.println(root);
                deleteFolder(root);
            }
            file.delete();
            return true;
        }
    }

    /*操作文件*/
    public static String setFile(String key) {
        String txtname = "xieyi.txt";
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        if(path.startsWith("/") && path.contains(":"))
            path = path.substring(1);
        path = path + txtname;
        if(path.contains("%")){
            try {
                path = URLDecoder.decode( path, "UTF-8" );
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        File filebase=new File(path);
        URL url = ClassLoader.getSystemResource("xieyi.txt");
        if (!filebase.exists()){
            //创建新文件
            File file2=new File(path);
            if(!file2.exists()){
                try {
                    file2.createNewFile();
                    //写文件
                    FileOutputStream out=new FileOutputStream(file2,true);
                    if(key != null && StringUtil.isNotEmpty(key)){
                        String hexStr = DigestUtils.md5Hex(key);
                        out.write(hexStr.getBytes("utf-8"));
                    } else {
                        String hexStr = getKey();
                        out.write(hexStr.getBytes("utf-8"));
                    }
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            //删除原有
            File fileold=new File(path);
            if(fileold.exists() && fileold.isFile()){
                fileold.delete();
            }
            //创建新文件
            File file2=new File(path);
            if(!file2.exists()){
                try {
                    file2.createNewFile();
                    //写文件
                    FileOutputStream out=new FileOutputStream(file2,true);
                    if(key != null && StringUtil.isNotEmpty(key)){
                        String hexStr = DigestUtils.md5Hex(key);
                        out.write(hexStr.getBytes("utf-8"));
                    } else {
                        String hexStr = DigestUtils.md5Hex(getKey());
                        out.write(hexStr.getBytes("utf-8"));
                    }
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return "succeed";
        }
        return "error";

    }

    /*获取服务器mac地址*/
    public static String getMAC() {
        InetAddress ip;
        try {

            ip = InetAddress.getLocalHost();
            //System.out.println("Current IP address : " + ip.getHostAddress());

            NetworkInterface network = NetworkInterface.getByInetAddress(ip);

            byte[] mac = network.getHardwareAddress();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? ":" : ""));
            }
            String macstr = sb.toString().toLowerCase();
            //System.out.println("Current MAC address : " + macstr);
            return macstr;
            //return ip + ":" + sb.toString();

        } catch (UnknownHostException e) {

            e.printStackTrace();

        } catch (SocketException e) {

            e.printStackTrace();

        }
        return null;

    }

    public static String INTRANET_IP = getIntranetIp(); // 内网IP

    /**
     * 获得内网IP
     * @return 内网IP
     */
    private static String getIntranetIp(){
        try{
            return InetAddress.getLocalHost().getHostAddress();
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取当前公网ip
     * @return 外网IP
     */
    public static String getWebIp() {
        try {
            URL url = new URL("http://www.ip138.com/ip2city.asp");
            BufferedReader br = new BufferedReader(new InputStreamReader(url
                    .openStream()));
            String s = "";
            StringBuffer sb = new StringBuffer("");
            String webContent = "";
            while ((s = br.readLine()) != null) {
                sb.append(s + "\r\n");
            }
            br.close();
            webContent = sb.toString();
            int start = webContent.indexOf("[")+1;
            int end = webContent.indexOf("]");
            webContent = webContent.substring(start,end);
            return webContent;
        } catch (Exception e) {
            e.printStackTrace();
            return "error open url:" + e.getMessage();
        }
    }

    /**将二进制转换成16进制
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**将16进制转换为二进制
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 30)
            return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }


}
