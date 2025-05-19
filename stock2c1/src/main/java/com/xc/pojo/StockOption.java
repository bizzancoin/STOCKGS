package com.xc.pojo;
import java.util.Date;

public class StockOption {
    private Integer id;
    private Integer userId;
    private Integer stockId;
    private Date addTime;
    private String stockCode;
    private String stockName;
    private String stockGid;
    private Integer isLock;

    public StockOption(Integer id, Integer userId, Integer stockId, Date addTime, String stockCode, String stockName, String stockGid, Integer isLock) {
        this.id = id;
        this.userId = userId;
        this.stockId = stockId;
        this.addTime = addTime;
        this.stockCode = stockCode;
        this.stockName = stockName;
        this.stockGid = stockGid;
        this.isLock = isLock;
    }


    public StockOption() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockGid() {
        return stockGid;
    }

    public void setStockGid(String stockGid) {
        this.stockGid = stockGid;
    }

    public Integer getIsLock() {
        return isLock;
    }

    public void setIsLock(Integer isLock) {
        this.isLock = isLock;
    }
}
