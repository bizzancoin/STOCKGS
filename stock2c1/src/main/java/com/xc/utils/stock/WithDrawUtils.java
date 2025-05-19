package com.xc.utils.stock;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class WithDrawUtils {
    private static final Logger log = LoggerFactory.getLogger(WithDrawUtils.class);


    public static boolean checkIsWithTime(int beginTime, int endTime) {
        Calendar c = Calendar.getInstance();
        int currentHour = c.get(11);
        log.info("当前小时 = {}", Integer.valueOf(currentHour));

        if (currentHour >= beginTime && currentHour < endTime) {
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        System.out.println(checkIsWithTime(19, 20));
    }
}
