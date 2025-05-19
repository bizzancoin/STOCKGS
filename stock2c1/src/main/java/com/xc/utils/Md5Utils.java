package com.xc.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Md5Utils {
    public static String getMD5(String str) throws Exception {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            return (new BigInteger(1, md.digest())).toString(16);
        } catch (Exception e) {
            throw new Exception();
        }
    }


    public static String md5_32(String plainText) throws Exception {
        String re_md5 = new String();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte[] b = md.digest();

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                int i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }

            re_md5 = buf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return re_md5;
    }


    public static void main(String[] args) throws Exception {
        System.out.println(md5_32("SLPNXGC157080458540512750000http://serverback.comhttp://serverback.comea40b08d39a043b882ab197c6c9c7699"));
    }
}
