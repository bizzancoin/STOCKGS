package com.xc.utils.task.stock;


import com.xc.service.IUserService;

import com.xc.utils.DateTimeUtil;

import com.xc.utils.stock.BuyAndSellUtils;

import java.util.Date;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.scheduling.annotation.Scheduled;

import org.springframework.stereotype.Component;


@Component
public class ForceSellTask {

    private static final Logger log = LoggerFactory.getLogger(ForceSellTask.class);


    @Autowired
    IUserService iUserService;


    private static final String am_begin = "9:30";


    private static final String am_end = "11:30";


    private static final String pm_begin = "13:00";


    private static final String pm_end = "15:00";


    /*用户持仓单-用户总金额-强平定时*/
    @Scheduled(cron = "0 0/3 9-15 ? * MON-FRI")
    public void banlanceUserPositionTaskV1() {

        boolean am = false;

        boolean pm = false;

        try {

            am = BuyAndSellUtils.isTransTime("9:30", "11:30");

            pm = BuyAndSellUtils.isTransTime("13:00", "15:00");

        } catch (Exception e) {

            log.error("执行定时任务出错，e = {}", e);

        }


        log.info("当前 am = {}  pm = {}", Boolean.valueOf(am), Boolean.valueOf(pm));

        if (am || pm) {

            log.info("=====扫描用户持仓执行，当前时间 {} =====", DateTimeUtil.dateToStr(new Date()));

            dotask();

            log.info("=====扫描用户持仓结束，当前时间 {} =====", DateTimeUtil.dateToStr(new Date()));

        } else {

            log.info("当前时间不为周一至周五，或者不在交易时间内，不执行（强平）定时任务");

        }

    }


    public void dotask() {
        this.iUserService.ForceSellTask();
    }

    /*用户持仓单-单支股票盈亏-强平定时*/
    @Scheduled(cron = "0 0/1 9-15 ? * MON-FRI")
    public void stockProfitLossOneTask() {
        boolean am = false;
        boolean pm = false;
        try {
            am = BuyAndSellUtils.isTransTime("9:30", "11:30");
            pm = BuyAndSellUtils.isTransTime("13:00", "15:00");
        } catch (Exception e) {
            log.error("执行定时任务出错，e = {}", e);
        }

        log.info("当前 am = {}  pm = {}", Boolean.valueOf(am), Boolean.valueOf(pm));
        if (am || pm) {
            log.info("=====扫描单支股票盈亏执行，当前时间 {} =====", DateTimeUtil.dateToStr(new Date()));
            this.iUserService.ForceSellOneStockTask();
            log.info("=====扫描单支股票盈亏结束，当前时间 {} =====", DateTimeUtil.dateToStr(new Date()));

        } else {
            log.info("当前时间不为周一至周五，或者不在交易时间内，不执行（强平）单支股票盈亏定时任务");
        }

    }

    /*用户股票持仓单-强平提醒推送消息定时*/
    @Scheduled(cron = "0 0/3 9-15 ? * MON-FRI")
    public void banlanceUserPositionMessage() {

        boolean am = false;

        boolean pm = false;

        try {

            am = BuyAndSellUtils.isTransTime("9:30", "11:30");

            pm = BuyAndSellUtils.isTransTime("13:00", "15:00");

        } catch (Exception e) {

            log.error("执行定时任务出错，e = {}", e);

        }


        log.info("当前 am = {}  pm = {}", Boolean.valueOf(am), Boolean.valueOf(pm));

        if (am || pm) {

            log.info("=====扫描用户持仓执行，当前时间 {} =====", DateTimeUtil.dateToStr(new Date()));

            this.iUserService.ForceSellMessageTask();

            log.info("=====扫描用户持仓结束，当前时间 {} =====", DateTimeUtil.dateToStr(new Date()));

        } else {

            log.info("当前时间不为周一至周五，或者不在交易时间内，不执行（强平）提醒推送消息任务");

        }

    }

}
