package com.xc.pojo;

import com.xc.pojo.SiteProduct;


public class SiteProduct {
    private Integer id;
    private Boolean stockDisplay;
    private Boolean kcStockDisplay;
    private Boolean indexDisplay;
    private Boolean futuresDisplay;
    /*实名认证开关：1、开启，0、关闭*/
    private Boolean realNameDisplay;
    /**
     * 分仓配资总开关
     */
    private Boolean fundsDisplay;

    /**
     * 分仓配资续期审核开关
     */
    private Boolean delayDisplay;

    /**
     * 分仓扩大配资审核开关
     */
    private Boolean expandDisplay;

    /**
     * 分仓追加保证金审核开关
     */
    private Boolean marginDisplay;

    /**
     * 分仓终止操盘审核开关
     */
    private Boolean endDisplay;

    /**
     * 股票追加保证金开关
     */
    private Boolean stockMarginDisplay;

    /**
     * 节假日开关：1、开启，0、关闭
     */
    private Boolean holidayDisplay;

    public SiteProduct(Integer id, Boolean stockDisplay, Boolean kcStockDisplay, Boolean indexDisplay, Boolean futuresDisplay, Boolean realNameDisplay, Boolean fundsDisplay, Boolean delayDisplay, Boolean expandDisplay, Boolean marginDisplay, Boolean endDisplay, Boolean stockMarginDisplay, Boolean holidayDisplay) {
        this.id = id;
        this.stockDisplay = stockDisplay;
        this.kcStockDisplay = kcStockDisplay;
        this.indexDisplay = indexDisplay;
        this.futuresDisplay = futuresDisplay;
        this.realNameDisplay = realNameDisplay;
        this.fundsDisplay = fundsDisplay;
        this.delayDisplay = delayDisplay;
        this.expandDisplay = expandDisplay;
        this.marginDisplay = marginDisplay;
        this.endDisplay = endDisplay;
        this.stockMarginDisplay = stockMarginDisplay;
        this.holidayDisplay = holidayDisplay;
    }



    public SiteProduct() {}


    public Integer getId() { return this.id; }



    public void setId(Integer id) { this.id = id; }



    public Boolean getStockDisplay() { return this.stockDisplay; }



    public void setStockDisplay(Boolean stockDisplay) { this.stockDisplay = stockDisplay; }



    public Boolean getKcStockDisplay() { return this.kcStockDisplay; }



    public void setKcStockDisplay(Boolean kcStockDisplay) { this.kcStockDisplay = kcStockDisplay; }



    public Boolean getIndexDisplay() { return this.indexDisplay; }



    public void setIndexDisplay(Boolean indexDisplay) { this.indexDisplay = indexDisplay; }



    public Boolean getFuturesDisplay() { return this.futuresDisplay; }



    public void setFuturesDisplay(Boolean futuresDisplay) { this.futuresDisplay = futuresDisplay; }

    public Boolean getRealNameDisplay() {
        return realNameDisplay;
    }


    public Boolean getStockMarginDisplay() {
        return stockMarginDisplay;
    }

    public void setStockMarginDisplay(Boolean stockMarginDisplay) {
        this.stockMarginDisplay = stockMarginDisplay;
    }

    public Boolean getFundsDisplay() {
        return fundsDisplay;
    }

    public void setFundsDisplay(Boolean fundsDisplay) {
        this.fundsDisplay = fundsDisplay;
    }

    public Boolean getDelayDisplay() {
        return delayDisplay;
    }

    public void setDelayDisplay(Boolean delayDisplay) {
        this.delayDisplay = delayDisplay;
    }

    public Boolean getExpandDisplay() {
        return expandDisplay;
    }

    public void setExpandDisplay(Boolean expandDisplay) {
        this.expandDisplay = expandDisplay;
    }

    public Boolean getMarginDisplay() {
        return marginDisplay;
    }

    public void setMarginDisplay(Boolean marginDisplay) {
        this.marginDisplay = marginDisplay;
    }

    public Boolean getEndDisplay() {
        return endDisplay;
    }

    public void setEndDisplay(Boolean endDisplay) {
        this.endDisplay = endDisplay;
    }

    public Boolean getHolidayDisplay() {
        return holidayDisplay;
    }

    public void setHolidayDisplay(Boolean holidayDisplay) {
        this.holidayDisplay = holidayDisplay;
    }
}
