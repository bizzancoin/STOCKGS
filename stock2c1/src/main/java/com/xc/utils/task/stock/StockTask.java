package com.xc.utils.task.stock;

import com.xc.common.ServerResponse;
import com.xc.common.StockIndexConst;
import com.xc.service.IStockIndexService;
import com.xc.service.IStockService;
import com.xc.utils.DateTimeUtil;
import com.xc.utils.PropertiesUtil;
import com.xc.utils.stock.BuyAndSellUtils;
import java.util.Date;
import java.util.List;

import com.xc.utils.stock.lide.LiDeDataUtils;
import com.xc.utils.stock.lide.LideDataVo;
import com.xc.vo.stock.MarketVOResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * 实时抓取深圳证券股票分时数据
 */
@Component
public class StockTask {
    @Autowired
    IStockService stockService;

    @Autowired
    IStockIndexService iStockIndexService;
    private static final Logger log = LoggerFactory.getLogger(StockTask.class);


    public void time(boolean am, boolean pm) {
        am = false;
        pm = false;
        try {
            am = BuyAndSellUtils.isTransTime("9:29", "11:31");
            pm = BuyAndSellUtils.isTransTime("12:59", "15:00");
        } catch (Exception e) {
            log.error("= {}", e);
        }
        log.info("am = {}  pm = {}", Boolean.valueOf(am), Boolean.valueOf(pm));
    }

//    /*股票走势图定时任务-15*/
//    @Scheduled(cron = "0 0/1 9-15 * * ?")
//    public void z1() {
//        boolean am = false;
//        boolean pm = false;
//        try {
//            am = BuyAndSellUtils.isTransTime("9:29", "11:31");
//            pm = BuyAndSellUtils.isTransTime("12:59", "15:00");
//        } catch (Exception e) {
//            log.error("= {}", e);
//        }
//        log.info("am = {}  pm = {}", Boolean.valueOf(am), Boolean.valueOf(pm));
//        if (am || pm) {
//            log.info("=====z1={} =====", DateTimeUtil.dateToStr(new Date()));
//            this.stockService.z1();
//            log.info("=====z1={} =====", DateTimeUtil.dateToStr(new Date()));
//        }
//    }
//
//    @Scheduled(cron = "0 0/1 9-15 * * ?")
//    public void z11() {
//        boolean am = false;
//        boolean pm = false;
//        try {
//            am = BuyAndSellUtils.isTransTime("9:29", "11:31");
//            pm = BuyAndSellUtils.isTransTime("12:59", "15:00");
//        } catch (Exception e) {
//            log.error("= {}", e);
//        }
//        log.info(" am = {}  pm = {}", Boolean.valueOf(am), Boolean.valueOf(pm));
//        if (am || pm) {
//            log.info("====z11={} =====", DateTimeUtil.dateToStr(new Date()));
//            this.stockService.z11();
//            log.info("====z11={} =====", DateTimeUtil.dateToStr(new Date()));
//        }
//    }
//    @Scheduled(cron = "0 0/1 9-15 * * ?")
//    public void z12() {
//        boolean am = false;
//        boolean pm = false;
//        try {
//            am = BuyAndSellUtils.isTransTime("9:29", "11:31");
//            pm = BuyAndSellUtils.isTransTime("12:59", "15:00");
//        } catch (Exception e) {
//            log.error("= {}", e);
//        }
//        log.info("z12 am = {}  pm = {}", Boolean.valueOf(am), Boolean.valueOf(pm));
//        if (am || pm) {
//            log.info("====z12={} =====", DateTimeUtil.dateToStr(new Date()));
//            this.stockService.z12();
//            log.info("====z12={} =====", DateTimeUtil.dateToStr(new Date()));
//        }
//    }
//
//    @Scheduled(cron = "0 0/1 9-15 * * MON-FRI")
//    public void z2() {
//        boolean am = false;
//        boolean pm = false;
//        try {
//            am = BuyAndSellUtils.isTransTime("9:29", "11:31");
//            pm = BuyAndSellUtils.isTransTime("12:59", "15:00");
//        } catch (Exception e) {
//            log.error("= {}", e);
//        }
//        log.info("am = {}  pm = {}", Boolean.valueOf(am), Boolean.valueOf(pm));
//        if (am || pm) {
//            log.info("====={} =====", DateTimeUtil.dateToStr(new Date()));
//            this.stockService.z2();
//            log.info("====={} =====", DateTimeUtil.dateToStr(new Date()));
//        }
//    }
//
//    @Scheduled(cron = "0 0/1 9-15 * * MON-FRI")
//    public void z21() {
//        boolean am = false;
//        boolean pm = false;
//        try {
//            am = BuyAndSellUtils.isTransTime("9:29", "11:31");
//            pm = BuyAndSellUtils.isTransTime("12:59", "15:00");
//        } catch (Exception e) {
//            log.error("= {}", e);
//        }
//        log.info("z21-am = {}  pm = {}", Boolean.valueOf(am), Boolean.valueOf(pm));
//        if (am || pm) {
//            log.info("====={} =====", DateTimeUtil.dateToStr(new Date()));
//            this.stockService.z21();
//            log.info("====={} =====", DateTimeUtil.dateToStr(new Date()));
//        }
//    }
//    @Scheduled(cron = "0 0/1 9-15 * * MON-FRI")
//    public void z22() {
//        boolean am = false;
//        boolean pm = false;
//        try {
//            am = BuyAndSellUtils.isTransTime("9:29", "11:31");
//            pm = BuyAndSellUtils.isTransTime("12:59", "15:00");
//        } catch (Exception e) {
//            log.error("= {}", e);
//        }
//        log.info("z22-am = {}  pm = {}", Boolean.valueOf(am), Boolean.valueOf(pm));
//        if (am || pm) {
//            log.info("====={} =====", DateTimeUtil.dateToStr(new Date()));
//            this.stockService.z22();
//            log.info("====={} =====", DateTimeUtil.dateToStr(new Date()));
//        }
//    }
//
//    @Scheduled(cron = "0 0/1 9-15 * * MON-FRI")
//    public void z3() {
//        boolean am = false;
//        boolean pm = false;
//        try {
//            am = BuyAndSellUtils.isTransTime("9:29", "11:31");
//            pm = BuyAndSellUtils.isTransTime("12:59", "15:00");
//        } catch (Exception e) {
//            log.error("= {}", e);
//        }
//        log.info("am = {}  pm = {}", Boolean.valueOf(am), Boolean.valueOf(pm));
//        if (am || pm) {
//            log.info("====={} =====", DateTimeUtil.dateToStr(new Date()));
//            this.stockService.z3();
//            log.info("====={} =====", DateTimeUtil.dateToStr(new Date()));
//        }
//    }
//    @Scheduled(cron = "0 0/1 9-15 * * MON-FRI")
//    public void z31() {
//        boolean am = false;
//        boolean pm = false;
//        try {
//            am = BuyAndSellUtils.isTransTime("9:29", "11:31");
//            pm = BuyAndSellUtils.isTransTime("12:59", "15:00");
//        } catch (Exception e) {
//            log.error("= {}", e);
//        }
//        log.info("z31-am = {}  pm = {}", Boolean.valueOf(am), Boolean.valueOf(pm));
//        if (am || pm) {
//            log.info("====={} =====", DateTimeUtil.dateToStr(new Date()));
//            this.stockService.z31();
//            log.info("====={} =====", DateTimeUtil.dateToStr(new Date()));
//        }
//    }
//    @Scheduled(cron = "0 0/1 9-15 * * MON-FRI")
//    public void z32() {
//        boolean am = false;
//        boolean pm = false;
//        try {
//            am = BuyAndSellUtils.isTransTime("9:29", "11:31");
//            pm = BuyAndSellUtils.isTransTime("12:59", "15:00");
//        } catch (Exception e) {
//            log.error("= {}", e);
//        }
//        log.info("z32-am = {}  pm = {}", Boolean.valueOf(am), Boolean.valueOf(pm));
//        if (am || pm) {
//            log.info("====={} =====", DateTimeUtil.dateToStr(new Date()));
//            this.stockService.z32();
//            log.info("====={} =====", DateTimeUtil.dateToStr(new Date()));
//        }
//    }
//
//    @Scheduled(cron = "0 0/1 9-15 * * MON-FRI")
//    public void z4() {
//        boolean am = false;
//        boolean pm = false;
//        try {
//            am = BuyAndSellUtils.isTransTime("9:29", "11:31");
//            pm = BuyAndSellUtils.isTransTime("12:59", "15:00");
//        } catch (Exception e) {
//            log.error("= {}", e);
//        }
//        log.info("am = {}  pm = {}", Boolean.valueOf(am), Boolean.valueOf(pm));
//        if (am || pm) {
//            log.info("====={} =====", DateTimeUtil.dateToStr(new Date()));
//            this.stockService.z4();
//            log.info("====={} =====", DateTimeUtil.dateToStr(new Date()));
//        }
//    }
//    @Scheduled(cron = "0 0/1 9-15 * * MON-FRI")
//    public void z41() {
//        boolean am = false;
//        boolean pm = false;
//        try {
//            am = BuyAndSellUtils.isTransTime("9:29", "11:31");
//            pm = BuyAndSellUtils.isTransTime("12:59", "15:00");
//        } catch (Exception e) {
//            log.error("= {}", e);
//        }
//        log.info("z41-am = {}  pm = {}", Boolean.valueOf(am), Boolean.valueOf(pm));
//        if (am || pm) {
//            log.info("====={} =====", DateTimeUtil.dateToStr(new Date()));
//            this.stockService.z41();
//            log.info("====={} =====", DateTimeUtil.dateToStr(new Date()));
//        }
//    }
//    @Scheduled(cron = "0 0/1 9-15 * * MON-FRI")
//    public void z42() {
//        boolean am = false;
//        boolean pm = false;
//        try {
//            am = BuyAndSellUtils.isTransTime("9:29", "11:31");
//            pm = BuyAndSellUtils.isTransTime("12:59", "15:00");
//        } catch (Exception e) {
//            log.error("= {}", e);
//        }
//        log.info("z42-am = {}  pm = {}", Boolean.valueOf(am), Boolean.valueOf(pm));
//        if (am || pm) {
//            log.info("====={} =====", DateTimeUtil.dateToStr(new Date()));
//            this.stockService.z42();
//            log.info("====={} =====", DateTimeUtil.dateToStr(new Date()));
//        }
//    }
//    @Scheduled(cron = "0 0/1 9-15 * * MON-FRI")
//    public void z43() {
//        boolean am = false;
//        boolean pm = false;
//        try {
//            am = BuyAndSellUtils.isTransTime("9:29", "11:31");
//            pm = BuyAndSellUtils.isTransTime("12:59", "15:00");
//        } catch (Exception e) {
//            log.error("= {}", e);
//        }
//        log.info("z42-am = {}  pm = {}", Boolean.valueOf(am), Boolean.valueOf(pm));
//        if (am || pm) {
//            log.info("====={} =====", DateTimeUtil.dateToStr(new Date()));
//            this.stockService.z43();
//            log.info("====={} =====", DateTimeUtil.dateToStr(new Date()));
//        }
//    }
//    @Scheduled(cron = "0 0/1 9-15 * * MON-FRI")
//    public void z44() {
//        boolean am = false;
//        boolean pm = false;
//        try {
//            am = BuyAndSellUtils.isTransTime("9:29", "11:31");
//            pm = BuyAndSellUtils.isTransTime("12:59", "15:00");
//        } catch (Exception e) {
//            log.error("= {}", e);
//        }
//        log.info("z42-am = {}  pm = {}", Boolean.valueOf(am), Boolean.valueOf(pm));
//        if (am || pm) {
//            log.info("====={} =====", DateTimeUtil.dateToStr(new Date()));
//            this.stockService.z44();
//            log.info("====={} =====", DateTimeUtil.dateToStr(new Date()));
//        }
//    }
//    @Scheduled(cron = "0 0/1 9-15 * * MON-FRI")
//    public void z45() {
//        boolean am = false;
//        boolean pm = false;
//        try {
//            am = BuyAndSellUtils.isTransTime("9:29", "11:31");
//            pm = BuyAndSellUtils.isTransTime("12:59", "15:00");
//        } catch (Exception e) {
//            log.error("= {}", e);
//        }
//        log.info("z42-am = {}  pm = {}", Boolean.valueOf(am), Boolean.valueOf(pm));
//        if (am || pm) {
//            log.info("====={} =====", DateTimeUtil.dateToStr(new Date()));
//            this.stockService.z45();
//            log.info("====={} =====", DateTimeUtil.dateToStr(new Date()));
//        }
//    }
//    @Scheduled(cron = "0 0/1 9-15 * * MON-FRI")
//    public void z46() {
//        boolean am = false;
//        boolean pm = false;
//        try {
//            am = BuyAndSellUtils.isTransTime("9:29", "11:31");
//            pm = BuyAndSellUtils.isTransTime("12:59", "15:00");
//        } catch (Exception e) {
//            log.error("= {}", e);
//        }
//        log.info("z42-am = {}  pm = {}", Boolean.valueOf(am), Boolean.valueOf(pm));
//        if (am || pm) {
//            log.info("====={} =====", DateTimeUtil.dateToStr(new Date()));
//            this.stockService.z46();
//            log.info("====={} =====", DateTimeUtil.dateToStr(new Date()));
//        }
//    }

    /**
     * 获取同花顺涨跌幅榜
     */
    @Scheduled(cron = "30 0/1 9-15 * * MON-FRI")
    public void zdfb() {
        boolean am = false;
        boolean pm = false;
        try {
            am = BuyAndSellUtils.isTransTime("9:25", "11:31");
            pm = BuyAndSellUtils.isTransTime("12:59", "15:00");
        } catch (Exception e) {
            log.error("= {}", e);
        }
        log.info("z42-am = {}  pm = {}", Boolean.valueOf(am), Boolean.valueOf(pm));
        if (am || pm) {
            log.info("同花顺涨跌幅获取开始====={} =====", DateTimeUtil.dateToStr(new Date()));
            ServerResponse zdfb = this.stockService.getZdfb();
            StockIndexConst.ZDFBMap.put("zdfb",zdfb);
            log.info("同花顺涨跌幅获取结束====={} =====", DateTimeUtil.dateToStr(new Date()));
        }
    }


//    /**
//     * 第二页获取中小板指数
//     */
//    @Scheduled(cron = "55 0/1 9-15 * * MON-FRI")
//    public void zs2Task() {
//        boolean am = false;
//        boolean pm = false;
//        try {
//            am = BuyAndSellUtils.isTransTime("9:29", "11:31");
//            pm = BuyAndSellUtils.isTransTime("12:59", "15:00");
//        } catch (Exception e) {
//            log.error("= {}", e);
//        }
//        log.info("z42-am = {}  pm = {}", Boolean.valueOf(am), Boolean.valueOf(pm));
//        if (am || pm) {
//            log.info("中小板指数开始====={} =====", DateTimeUtil.dateToStr(new Date()));
//            ServerResponse zs = this.stockService.getMarket();
//            StockIndexConst.ZDFBMap.put("zs",zs);
//            ServerResponse homeIndex= this.iStockIndexService.queryHomeIndex();
//            StockIndexConst.ZDFBMap.put("homeIndex",homeIndex);
//            log.info("中小板指数结束====={} =====", DateTimeUtil.dateToStr(new Date()));
//        }
//    }

    /**
     * 获取指数
     */
    @Scheduled(cron = "0 0/1 9-15 * * MON-FRI")
    public void zsTask() {
        boolean am = false;
        boolean pm = false;
        try {
            am = BuyAndSellUtils.isTransTime("9:25", "11:31");
            pm = BuyAndSellUtils.isTransTime("12:59", "15:00");
        } catch (Exception e) {
            log.error("= {}", e);
        }
        log.info("z42-am = {}  pm = {}", Boolean.valueOf(am), Boolean.valueOf(pm));
        if (am || pm) {
            log.info("指数获取开始====={} =====", DateTimeUtil.dateToStr(new Date()));
            ServerResponse zs = this.stockService.getMarket();
            if(null!=zs&&zs.getData() instanceof MarketVOResult) {
                MarketVOResult marketVOResult = (MarketVOResult) zs.getData();
                if (!CollectionUtils.isEmpty(marketVOResult.getMarket())) {
                    StockIndexConst.ZDFBMap.put("zs",zs);
                    ServerResponse homeIndex= this.iStockIndexService.queryHomeIndex();
                    StockIndexConst.ZDFBMap.put("homeIndex",homeIndex);
                }
            }else {
                StockIndexConst.ZDFBMap.put("zs",null);
            }
            log.info("指数获取结束====={} =====", DateTimeUtil.dateToStr(new Date()));
        }
    }





    /**
     * 拉取立德全量数据
     */
    @Scheduled(cron = "0/10 * 9-15 * * MON-FRI")
    public void pullLideDataTask() {
//        String property = PropertiesUtil.getProperty("lide.stop");
//        if(property.equals("0")){
//            return;
//        }
        boolean am = false;
        boolean pm = false;
        try {
            am = BuyAndSellUtils.isTransTime("9:25", "11:31");
            pm = BuyAndSellUtils.isTransTime("12:59", "15:00");
            log.info("z42-am = {}  pm = {}", Boolean.valueOf(am), Boolean.valueOf(pm));
            if (am || pm) {
                log.info("立德数据拉取====={} =====", DateTimeUtil.dateToStr(new Date()));
                LiDeDataUtils.pullData();
                log.info("立德数据拉取====={} =====", DateTimeUtil.dateToStr(new Date()));
            }
        } catch (Exception e) {
            log.error("= {}", e);
        }
    }

    /**
     * 拉取立德盘口数据
     */
    @Scheduled(cron = "0/15 * 9-15 * * MON-FRI")
    public void pullLideDataPanTask() {
//        String property = PropertiesUtil.getProperty("lide.stop");
//        if(property.equals("0")){
//            return;
//        }
        boolean am = false;
        boolean pm = false;
        try {
            am = BuyAndSellUtils.isTransTime("9:25", "11:31");
            pm = BuyAndSellUtils.isTransTime("12:59", "15:00");
            log.info("z42-am = {}  pm = {}", Boolean.valueOf(am), Boolean.valueOf(pm));
            if (am || pm) {
                log.info("立德盘口数据拉取====={} =====", DateTimeUtil.dateToStr(new Date()));
                LiDeDataUtils.pullPankouData();
                log.info("立德盘口数据拉取====={} =====", DateTimeUtil.dateToStr(new Date()));
            }
        } catch (Exception e) {
            log.error("= {}", e);
        }
    }

    /**
     * 拉取立德新三板全量数据
     */
    @Scheduled(cron = "0/12 * 9-15 * * MON-FRI")
    public void pullLideXSBDataTask() {
//        String property = PropertiesUtil.getProperty("lide.stop");
//        if(property.equals("0")){
//            return;
//        }
        boolean am = false;
        boolean pm = false;
        try {
            am = BuyAndSellUtils.isTransTime("9:25", "11:31");
            pm = BuyAndSellUtils.isTransTime("12:59", "15:00");
            log.info("z42-am = {}  pm = {}", Boolean.valueOf(am), Boolean.valueOf(pm));
            if (am || pm) {
                log.info("立德数据拉取====={} =====", DateTimeUtil.dateToStr(new Date()));
                LiDeDataUtils.pullXSBData();
                log.info("立德数据拉取====={} =====", DateTimeUtil.dateToStr(new Date()));
            }
        } catch (Exception e) {
            log.error("= {}", e);
        }
    }

    /**
     * 拉取立德新三板盘口数据
     */
    @Scheduled(cron = "0/18 * 9-15 * * MON-FRI")
    public void pullLideDataXSBPanTask() {
//        String property = PropertiesUtil.getProperty("lide.stop");
//        if(property.equals("0")){
//            return;
//        }
        boolean am = false;
        boolean pm = false;
        try {
            am = BuyAndSellUtils.isTransTime("9:25", "11:31");
            pm = BuyAndSellUtils.isTransTime("12:59", "15:00");
            log.info("z42-am = {}  pm = {}", Boolean.valueOf(am), Boolean.valueOf(pm));
            if (am || pm) {
                log.info("立德盘口数据拉取====={} =====", DateTimeUtil.dateToStr(new Date()));
                LiDeDataUtils.pullXSBPankouData();
                log.info("立德盘口数据拉取====={} =====", DateTimeUtil.dateToStr(new Date()));
            }
        } catch (Exception e) {
            log.error("= {}", e);
        }

    }

}
