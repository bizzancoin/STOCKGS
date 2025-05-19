package com.xc.utils;


import java.util.Random;


public class KeyUtils {
    public static String getUniqueKey() {
        Random random = new Random();
        Integer number = Integer.valueOf(random.nextInt(900) + 100);

        return System.currentTimeMillis() + String.valueOf(number);
    }


    public static String getAgentUniqueKey() {
        Random random = new Random();
        Integer number = Integer.valueOf(random.nextInt(900000) + 100000);
        return PropertiesUtil.getProperty("agent.key.prefix") + String.valueOf(number);
    }


    public static String getFiveUnique() {
        Random random = new Random();
        Integer number = Integer.valueOf(random.nextInt(90000) + 10000);
        return String.valueOf(number);
    }


    public static String getRechargeOrderSn() {
        return "C" + getUniqueKey();
    }


    public static String getWithdrawOrderSn() {
        return "W" + getUniqueKey();
    }


    public static void main(String[] args) {
        String s1 = "A4058C23D071BF034EDA3E1181BC1EE8";
        String s2 = "A4058C23D071BF034EDA3E1181BC1EE8";

        System.out.println(s1.equals(s2));


        System.out.println(getUniqueKey());
        System.out.println(getAgentUniqueKey());
        System.out.println(getFiveUnique());
    }
}
