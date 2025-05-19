
package com.xc.pojo;

import java.beans.ConstructorProperties;
import java.math.BigDecimal;
import java.util.Date;

public class StockMarketsDay {

    private Integer id;

    private Integer stockId;

    private String stockName;

    private String stockCode;

    private String stockGid;

    private String ymd;

    private String hms;


    public void setId(Integer id) {
        this.id = id;
    }

    private BigDecimal nowPrice;
    private BigDecimal creaseRate;
    private String openPx;
    private String closePx;
    private String businessBalance;
    private String businessAmount;
    private Date addTime;
    private String addTimeStr;

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public void setStockGid(String stockGid) {
        this.stockGid = stockGid;
    }

    public void setYmd(String ymd) {
        this.ymd = ymd;
    }

    public void setHms(String hms) {
        this.hms = hms;
    }

    public void setNowPrice(BigDecimal nowPrice) {
        this.nowPrice = nowPrice;
    }

    public void setCreaseRate(BigDecimal creaseRate) {
        this.creaseRate = creaseRate;
    }

    public void setOpenPx(String openPx) {
        this.openPx = openPx;
    }

    public void setClosePx(String closePx) {
        this.closePx = closePx;
    }

    public void setBusinessBalance(String businessBalance) {
        this.businessBalance = businessBalance;
    }

    public void setBusinessAmount(String businessAmount) {
        this.businessAmount = businessAmount;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public void setAddTimeStr(String addTimeStr) {
        this.addTimeStr = addTimeStr;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof StockMarketsDay)) return false;
        StockMarketsDay other = (StockMarketsDay) o;
        if (!other.canEqual(this)) return false;
        Object this$id = getId(), other$id = other.getId();
        if ((this$id == null) ? (other$id != null) : !this$id.equals(other$id)) return false;
        Object this$stockId = getStockId(), other$stockId = other.getStockId();
        if ((this$stockId == null) ? (other$stockId != null) : !this$stockId.equals(other$stockId)) return false;
        Object this$stockName = getStockName(), other$stockName = other.getStockName();
        if ((this$stockName == null) ? (other$stockName != null) : !this$stockName.equals(other$stockName))
            return false;
        Object this$stockCode = getStockCode(), other$stockCode = other.getStockCode();
        if ((this$stockCode == null) ? (other$stockCode != null) : !this$stockCode.equals(other$stockCode))
            return false;
        Object this$stockGid = getStockGid(), other$stockGid = other.getStockGid();
        if ((this$stockGid == null) ? (other$stockGid != null) : !this$stockGid.equals(other$stockGid)) return false;
        Object this$ymd = getYmd(), other$ymd = other.getYmd();
        if ((this$ymd == null) ? (other$ymd != null) : !this$ymd.equals(other$ymd)) return false;
        Object this$hms = getHms(), other$hms = other.getHms();
        if ((this$hms == null) ? (other$hms != null) : !this$hms.equals(other$hms)) return false;
        Object this$nowPrice = getNowPrice(), other$nowPrice = other.getNowPrice();
        if ((this$nowPrice == null) ? (other$nowPrice != null) : !this$nowPrice.equals(other$nowPrice)) return false;
        Object this$creaseRate = getCreaseRate(), other$creaseRate = other.getCreaseRate();
        if ((this$creaseRate == null) ? (other$creaseRate != null) : !this$creaseRate.equals(other$creaseRate))
            return false;
        Object this$openPx = getOpenPx(), other$openPx = other.getOpenPx();
        if ((this$openPx == null) ? (other$openPx != null) : !this$openPx.equals(other$openPx)) return false;
        Object this$closePx = getClosePx(), other$closePx = other.getClosePx();
        if ((this$closePx == null) ? (other$closePx != null) : !this$closePx.equals(other$closePx)) return false;
        Object this$businessBalance = getBusinessBalance(), other$businessBalance = other.getBusinessBalance();
        if ((this$businessBalance == null) ? (other$businessBalance != null) : !this$businessBalance.equals(other$businessBalance))
            return false;
        Object this$businessAmount = getBusinessAmount(), other$businessAmount = other.getBusinessAmount();
        if ((this$businessAmount == null) ? (other$businessAmount != null) : !this$businessAmount.equals(other$businessAmount))
            return false;
        Object this$addTime = getAddTime(), other$addTime = other.getAddTime();
        if ((this$addTime == null) ? (other$addTime != null) : !this$addTime.equals(other$addTime)) return false;
        Object this$addTimeStr = getAddTimeStr(), other$addTimeStr = other.getAddTimeStr();
        return !((this$addTimeStr == null) ? (other$addTimeStr != null) : !this$addTimeStr.equals(other$addTimeStr));
    }

    protected boolean canEqual(Object other) {
        return other instanceof StockMarketsDay;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $id = getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        Object $stockId = getStockId();
        result = result * 59 + (($stockId == null) ? 43 : $stockId.hashCode());
        Object $stockName = getStockName();
        result = result * 59 + (($stockName == null) ? 43 : $stockName.hashCode());
        Object $stockCode = getStockCode();
        result = result * 59 + (($stockCode == null) ? 43 : $stockCode.hashCode());
        Object $stockGid = getStockGid();
        result = result * 59 + (($stockGid == null) ? 43 : $stockGid.hashCode());
        Object $ymd = getYmd();
        result = result * 59 + (($ymd == null) ? 43 : $ymd.hashCode());
        Object $hms = getHms();
        result = result * 59 + (($hms == null) ? 43 : $hms.hashCode());
        Object $nowPrice = getNowPrice();
        result = result * 59 + (($nowPrice == null) ? 43 : $nowPrice.hashCode());
        Object $creaseRate = getCreaseRate();
        result = result * 59 + (($creaseRate == null) ? 43 : $creaseRate.hashCode());
        Object $openPx = getOpenPx();
        result = result * 59 + (($openPx == null) ? 43 : $openPx.hashCode());
        Object $closePx = getClosePx();
        result = result * 59 + (($closePx == null) ? 43 : $closePx.hashCode());
        Object $businessBalance = getBusinessBalance();
        result = result * 59 + (($businessBalance == null) ? 43 : $businessBalance.hashCode());
        Object $businessAmount = getBusinessAmount();
        result = result * 59 + (($businessAmount == null) ? 43 : $businessAmount.hashCode());
        Object $addTime = getAddTime();
        result = result * 59 + (($addTime == null) ? 43 : $addTime.hashCode());
        Object $addTimeStr = getAddTimeStr();
        return result * 59 + (($addTimeStr == null) ? 43 : $addTimeStr.hashCode());
    }

    public String toString() {
        return "StockMarketsDay(id=" + getId() + ", stockId=" + getStockId() + ", stockName=" + getStockName() + ", stockCode=" + getStockCode() + ", stockGid=" + getStockGid() + ", ymd=" + getYmd() + ", hms=" + getHms() + ", nowPrice=" + getNowPrice() + ", creaseRate=" + getCreaseRate() + ", openPx=" + getOpenPx() + ", closePx=" + getClosePx() + ", businessBalance=" + getBusinessBalance() + ", businessAmount=" + getBusinessAmount() + ", addTime=" + getAddTime() + ", addTimeStr=" + getAddTimeStr() + ")";
    }

    public StockMarketsDay() {
    }

    @ConstructorProperties({"id", "stockId", "stockName", "stockCode", "stockGid", "ymd", "hms", "nowPrice", "creaseRate", "openPx", "closePx", "businessBalance", "businessAmount", "addTime", "addTimeStr"})

    public StockMarketsDay(Integer id, Integer stockId, String stockName, String stockCode, String stockGid, String ymd, String hms, BigDecimal nowPrice, BigDecimal creaseRate, String openPx, String closePx, String businessBalance, String businessAmount, Date addTime, String addTimeStr) {
        this.id = id;
        this.stockId = stockId;
        this.stockName = stockName;
        this.stockCode = stockCode;
        this.stockGid = stockGid;
        this.ymd = ymd;
        this.hms = hms;
        this.nowPrice = nowPrice;
        this.creaseRate = creaseRate;
        this.openPx = openPx;
        this.closePx = closePx;
        this.businessBalance = businessBalance;
        this.businessAmount = businessAmount;
        this.addTime = addTime;
        this.addTimeStr = addTimeStr;
    }


    public Integer getId() {
        return this.id;
    }


    public Integer getStockId() {
        return this.stockId;
    }


    public String getStockName() {
        return this.stockName;
    }


    public String getStockCode() {
        return this.stockCode;
    }


    public String getStockGid() {
        return this.stockGid;
    }


    public String getYmd() {
        return this.ymd;
    }


    public String getHms() {
        return this.hms;
    }


    public BigDecimal getNowPrice() {
        return this.nowPrice;
    }


    public BigDecimal getCreaseRate() {
        return this.creaseRate;
    }


    public String getOpenPx() {
        return this.openPx;
    }


    public String getClosePx() {
        return this.closePx;
    }


    public String getBusinessBalance() {
        return this.businessBalance;
    }


    public String getBusinessAmount() {
        return this.businessAmount;
    }


    public Date getAddTime() {
        return this.addTime;
    }


    public String getAddTimeStr() {
        return this.addTimeStr;
    }

}
