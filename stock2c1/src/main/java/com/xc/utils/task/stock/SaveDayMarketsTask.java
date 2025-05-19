package com.xc.utils.task.stock;


import com.xc.service.IStockMarketsDayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class    SaveDayMarketsTask {
    private static final Logger log = LoggerFactory.getLogger(SaveDayMarketsTask.class);

    @Autowired
    IStockMarketsDayService iStockMarketsDayService;

    /*每天16点股票数据定时存入数据库（股票走势图数据存储）*/
    @Scheduled(cron = "0 0 16 ? * MON-FRI")
    public void banlanceUserPositionTaskV1() {
        dotask();
    }

    public void dotask() {
        this.iStockMarketsDayService.saveStockMarketDay();
    }

    /*每天1点同步节假日开关*/
    @Scheduled(cron = "0 0 1 * * ?")
    public void holidayTask() {
        this.iStockMarketsDayService.saveHoliday();
    }


}
