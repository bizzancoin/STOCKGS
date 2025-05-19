package com.xc.pojo;


import java.math.BigDecimal;
import java.util.Date;


public class Stock {
    private Integer id;
    private String stockName;
    private String stockCode;
    private String stockSpell;
    private String stockType;
    private String stockGid;
    private String stockPlate;
    private Integer isLock;
    private Integer isShow;
    private Date addTime;
    /*点差费率*/
    private BigDecimal spreadRate;

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", stockName='" + stockName + '\'' +
                ", stockCode='" + stockCode + '\'' +
                ", stockSpell='" + stockSpell + '\'' +
                ", stockType='" + stockType + '\'' +
                ", stockGid='" + stockGid + '\'' +
                ", stockPlate='" + stockPlate + '\'' +
                ", isLock=" + isLock +
                ", isShow=" + isShow +
                ", addTime=" + addTime +
                ", spreadRate=" + spreadRate +
                '}';
    }

    public Stock(Integer id, String stockName, String stockCode, String stockSpell, String stockType, String stockGid, String stockPlate, Integer isLock, Integer isShow, Date addTime,BigDecimal spreadRate) {
        this.id = id;
        this.stockName = stockName;
        this.stockCode = stockCode;
        this.stockSpell = stockSpell;
        this.stockType = stockType;
        this.stockGid = stockGid;
        this.stockPlate = stockPlate;
        this.isLock = isLock;
        this.isShow = isShow;
        this.addTime = addTime;
        this.spreadRate = spreadRate;
    }


    public Stock() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockSpell() {
        return stockSpell;
    }

    public void setStockSpell(String stockSpell) {
        this.stockSpell = stockSpell;
    }

    public String getStockType() {
        return stockType;
    }

    public void setStockType(String stockType) {
        this.stockType = stockType;
    }

    public String getStockGid() {
        return stockGid;
    }

    public void setStockGid(String stockGid) {
        this.stockGid = stockGid;
    }

    public String getStockPlate() {
        return stockPlate;
    }

    public void setStockPlate(String stockPlate) {
        this.stockPlate = stockPlate;
    }

    public Integer getIsLock() {
        return isLock;
    }

    public void setIsLock(Integer isLock) {
        this.isLock = isLock;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public BigDecimal getSpreadRate() {
        return spreadRate;
    }

    public void setSpreadRate(BigDecimal spreadRate) {
        this.spreadRate = spreadRate;
    }
}