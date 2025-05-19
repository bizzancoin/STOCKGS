package com.xc.vo.stock;

import java.math.BigDecimal;
import java.util.Date;

public class StockAdminListVO {
    private Integer id;
    private String stockName;
    private String stockCode;
    private String stockSpell;
    private String stockType;
    private String stockGid;

    public void setId(Integer id) {
        this.id = id;
    }

    private String stockPlate;
    private Integer isLock;
    private Integer isShow;
    private Date addTime;
    private String nowPrice;
    private BigDecimal hcrate;
    private BigDecimal day3Rate;
    /*点差费率*/
    private BigDecimal spreadRate;

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public void setStockSpell(String stockSpell) {
        this.stockSpell = stockSpell;
    }

    public void setStockType(String stockType) {
        this.stockType = stockType;
    }

    public void setStockGid(String stockGid) {
        this.stockGid = stockGid;
    }

    public void setStockPlate(String stockPlate) {
        this.stockPlate = stockPlate;
    }

    public void setIsLock(Integer isLock) {
        this.isLock = isLock;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public void setNowPrice(String nowPrice) {
        this.nowPrice = nowPrice;
    }

    public void setHcrate(BigDecimal hcrate) {
        this.hcrate = hcrate;
    }

    public void setDay3Rate(BigDecimal day3Rate) {
        this.day3Rate = day3Rate;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof StockAdminListVO)) return false;
        StockAdminListVO other = (StockAdminListVO) o;
        if (!other.canEqual(this)) return false;
        Object this$id = getId(), other$id = other.getId();
        if ((this$id == null) ? (other$id != null) : !this$id.equals(other$id)) return false;
        Object this$stockName = getStockName(), other$stockName = other.getStockName();
        if ((this$stockName == null) ? (other$stockName != null) : !this$stockName.equals(other$stockName))
            return false;
        Object this$stockCode = getStockCode(), other$stockCode = other.getStockCode();
        if ((this$stockCode == null) ? (other$stockCode != null) : !this$stockCode.equals(other$stockCode))
            return false;
        Object this$stockSpell = getStockSpell(), other$stockSpell = other.getStockSpell();
        if ((this$stockSpell == null) ? (other$stockSpell != null) : !this$stockSpell.equals(other$stockSpell))
            return false;
        Object this$stockType = getStockType(), other$stockType = other.getStockType();
        if ((this$stockType == null) ? (other$stockType != null) : !this$stockType.equals(other$stockType))
            return false;
        Object this$stockGid = getStockGid(), other$stockGid = other.getStockGid();
        if ((this$stockGid == null) ? (other$stockGid != null) : !this$stockGid.equals(other$stockGid)) return false;
        Object this$stockPlate = getStockPlate(), other$stockPlate = other.getStockPlate();
        if ((this$stockPlate == null) ? (other$stockPlate != null) : !this$stockPlate.equals(other$stockPlate))
            return false;
        Object this$isLock = getIsLock(), other$isLock = other.getIsLock();
        if ((this$isLock == null) ? (other$isLock != null) : !this$isLock.equals(other$isLock)) return false;
        Object this$isShow = getIsShow(), other$isShow = other.getIsShow();
        if ((this$isShow == null) ? (other$isShow != null) : !this$isShow.equals(other$isShow)) return false;
        Object this$addTime = getAddTime(), other$addTime = other.getAddTime();
        if ((this$addTime == null) ? (other$addTime != null) : !this$addTime.equals(other$addTime)) return false;
        Object this$nowPrice = getNowPrice(), other$nowPrice = other.getNowPrice();
        if ((this$nowPrice == null) ? (other$nowPrice != null) : !this$nowPrice.equals(other$nowPrice)) return false;
        Object this$hcrate = getHcrate(), other$hcrate = other.getHcrate();
        if ((this$hcrate == null) ? (other$hcrate != null) : !this$hcrate.equals(other$hcrate)) return false;
        Object this$day3Rate = getDay3Rate(), other$day3Rate = other.getDay3Rate();
        return !((this$day3Rate == null) ? (other$day3Rate != null) : !this$day3Rate.equals(other$day3Rate));
    }

    protected boolean canEqual(Object other) {
        return other instanceof StockAdminListVO;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $id = getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        Object $stockName = getStockName();
        result = result * 59 + (($stockName == null) ? 43 : $stockName.hashCode());
        Object $stockCode = getStockCode();
        result = result * 59 + (($stockCode == null) ? 43 : $stockCode.hashCode());
        Object $stockSpell = getStockSpell();
        result = result * 59 + (($stockSpell == null) ? 43 : $stockSpell.hashCode());
        Object $stockType = getStockType();
        result = result * 59 + (($stockType == null) ? 43 : $stockType.hashCode());
        Object $stockGid = getStockGid();
        result = result * 59 + (($stockGid == null) ? 43 : $stockGid.hashCode());
        Object $stockPlate = getStockPlate();
        result = result * 59 + (($stockPlate == null) ? 43 : $stockPlate.hashCode());
        Object $isLock = getIsLock();
        result = result * 59 + (($isLock == null) ? 43 : $isLock.hashCode());
        Object $isShow = getIsShow();
        result = result * 59 + (($isShow == null) ? 43 : $isShow.hashCode());
        Object $addTime = getAddTime();
        result = result * 59 + (($addTime == null) ? 43 : $addTime.hashCode());
        Object $nowPrice = getNowPrice();
        result = result * 59 + (($nowPrice == null) ? 43 : $nowPrice.hashCode());
        Object $hcrate = getHcrate();
        result = result * 59 + (($hcrate == null) ? 43 : $hcrate.hashCode());
        Object $day3Rate = getDay3Rate();
        return result * 59 + (($day3Rate == null) ? 43 : $day3Rate.hashCode());
    }

    public String toString() {
        return "StockAdminListVO(id=" + getId() + ", stockName=" + getStockName() + ", stockCode=" + getStockCode() + ", stockSpell=" + getStockSpell() + ", stockType=" + getStockType() + ", stockGid=" + getStockGid() + ", stockPlate=" + getStockPlate() + ", isLock=" + getIsLock() + ", isShow=" + getIsShow() + ", addTime=" + getAddTime() + ", nowPrice=" + getNowPrice() + ", hcrate=" + getHcrate() + ", day3Rate=" + getDay3Rate() + getHcrate() + ", spreadRate=" + getSpreadRate()+ ")";
    }


    public Integer getId() {
        return this.id;
    }

    public String getStockName() {
        return this.stockName;
    }

    public String getStockCode() {
        return this.stockCode;
    }

    public String getStockSpell() {
        return this.stockSpell;
    }

    public String getStockType() {
        return this.stockType;
    }

    public String getStockGid() {
        return this.stockGid;
    }

    public String getStockPlate() {
        return this.stockPlate;
    }

    public Integer getIsLock() {
        return this.isLock;
    }

    public Integer getIsShow() {
        return this.isShow;
    }

    public Date getAddTime() {
        return this.addTime;
    }


    public String getNowPrice() {
        return this.nowPrice;
    }

    public BigDecimal getHcrate() {
        return this.hcrate;
    }


    public BigDecimal getDay3Rate() {
        return this.day3Rate;
    }

    public BigDecimal getSpreadRate() {
        return spreadRate;
    }

    public void setSpreadRate(BigDecimal spreadRate) {
        this.spreadRate = spreadRate;
    }
}
