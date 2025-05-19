package com.xc.common;

import com.xc.utils.DateTimeUtil;

import java.util.Date;

public class PayConst {

    public static void main(String[] args) {

        String s=System.currentTimeMillis()/1000+"";
        Date date = DateTimeUtil.addDay(new Date(Long.parseLong(s) * 1000), 2);
        System.out.println(DateTimeUtil.dateToStr(date,DateTimeUtil.YMD_FORMAT)+" 23:59 前缴费");

    }
}
