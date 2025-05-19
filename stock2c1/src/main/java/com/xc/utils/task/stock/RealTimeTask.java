package com.xc.utils.task.stock;

import com.xc.service.RealTimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RealTimeTask {
    @Autowired
    RealTimeService realTimeService;

    private static final Logger log = LoggerFactory.getLogger(RealTimeTask.class);

    /*每天9点定时删除股票k线数据*/
    @Scheduled(cron = "0 00 9 * * MON-FRI")
    public void deleteStockCode() {
        log.info("每天9点定时删除股票k线数据");
        this.realTimeService.deleteRealTime();
    }

    /*每天0点定时删除期货k线数据*/
    @Scheduled(cron = "0 00 0 * * MON-FRI")
    public void deleteStockFuturesCode() {
        log.info("每天0点定时删除期货k线数据");
        this.realTimeService.deleteFuturesRealTime();
    }
}
