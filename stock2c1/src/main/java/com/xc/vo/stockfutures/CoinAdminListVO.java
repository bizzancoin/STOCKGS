package com.xc.vo.stockfutures;


import java.math.BigDecimal;
import java.util.Date;

public class CoinAdminListVO {
    private Integer id;
    private String coinName;
    private String coinCode;
    private String coinGid;
    private Integer dynamicRate;

    public void setId(Integer id) {
        this.id = id;
    }

    private BigDecimal defaultRate;
    private Integer isUse;
    private Date addTime;
    private String tDesc;
    private String nowPrice;

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public void setCoinCode(String coinCode) {
        this.coinCode = coinCode;
    }

    public void setCoinGid(String coinGid) {
        this.coinGid = coinGid;
    }

    public void setDynamicRate(Integer dynamicRate) {
        this.dynamicRate = dynamicRate;
    }

    public void setDefaultRate(BigDecimal defaultRate) {
        this.defaultRate = defaultRate;
    }

    public void setIsUse(Integer isUse) {
        this.isUse = isUse;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public void setTDesc(String tDesc) {
        this.tDesc = tDesc;
    }

    public void setNowPrice(String nowPrice) {
        this.nowPrice = nowPrice;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof CoinAdminListVO)) return false;
        CoinAdminListVO other = (CoinAdminListVO) o;
        if (!other.canEqual(this)) return false;
        Object this$id = getId(), other$id = other.getId();
        if ((this$id == null) ? (other$id != null) : !this$id.equals(other$id)) return false;
        Object this$coinName = getCoinName(), other$coinName = other.getCoinName();
        if ((this$coinName == null) ? (other$coinName != null) : !this$coinName.equals(other$coinName)) return false;
        Object this$coinCode = getCoinCode(), other$coinCode = other.getCoinCode();
        if ((this$coinCode == null) ? (other$coinCode != null) : !this$coinCode.equals(other$coinCode)) return false;
        Object this$coinGid = getCoinGid(), other$coinGid = other.getCoinGid();
        if ((this$coinGid == null) ? (other$coinGid != null) : !this$coinGid.equals(other$coinGid)) return false;
        Object this$dynamicRate = getDynamicRate(), other$dynamicRate = other.getDynamicRate();
        if ((this$dynamicRate == null) ? (other$dynamicRate != null) : !this$dynamicRate.equals(other$dynamicRate))
            return false;
        Object this$defaultRate = getDefaultRate(), other$defaultRate = other.getDefaultRate();
        if ((this$defaultRate == null) ? (other$defaultRate != null) : !this$defaultRate.equals(other$defaultRate))
            return false;
        Object this$isUse = getIsUse(), other$isUse = other.getIsUse();
        if ((this$isUse == null) ? (other$isUse != null) : !this$isUse.equals(other$isUse)) return false;
        Object this$addTime = getAddTime(), other$addTime = other.getAddTime();
        if ((this$addTime == null) ? (other$addTime != null) : !this$addTime.equals(other$addTime)) return false;
        Object this$tDesc = getTDesc(), other$tDesc = other.getTDesc();
        if ((this$tDesc == null) ? (other$tDesc != null) : !this$tDesc.equals(other$tDesc)) return false;
        Object this$nowPrice = getNowPrice(), other$nowPrice = other.getNowPrice();
        return !((this$nowPrice == null) ? (other$nowPrice != null) : !this$nowPrice.equals(other$nowPrice));
    }

    protected boolean canEqual(Object other) {
        return other instanceof CoinAdminListVO;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $id = getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        Object $coinName = getCoinName();
        result = result * 59 + (($coinName == null) ? 43 : $coinName.hashCode());
        Object $coinCode = getCoinCode();
        result = result * 59 + (($coinCode == null) ? 43 : $coinCode.hashCode());
        Object $coinGid = getCoinGid();
        result = result * 59 + (($coinGid == null) ? 43 : $coinGid.hashCode());
        Object $dynamicRate = getDynamicRate();
        result = result * 59 + (($dynamicRate == null) ? 43 : $dynamicRate.hashCode());
        Object $defaultRate = getDefaultRate();
        result = result * 59 + (($defaultRate == null) ? 43 : $defaultRate.hashCode());
        Object $isUse = getIsUse();
        result = result * 59 + (($isUse == null) ? 43 : $isUse.hashCode());
        Object $addTime = getAddTime();
        result = result * 59 + (($addTime == null) ? 43 : $addTime.hashCode());
        Object $tDesc = getTDesc();
        result = result * 59 + (($tDesc == null) ? 43 : $tDesc.hashCode());
        Object $nowPrice = getNowPrice();
        return result * 59 + (($nowPrice == null) ? 43 : $nowPrice.hashCode());
    }

    public String toString() {
        return "CoinAdminListVO(id=" + getId() + ", coinName=" + getCoinName() + ", coinCode=" + getCoinCode() + ", coinGid=" + getCoinGid() + ", dynamicRate=" + getDynamicRate() + ", defaultRate=" + getDefaultRate() + ", isUse=" + getIsUse() + ", addTime=" + getAddTime() + ", tDesc=" + getTDesc() + ", nowPrice=" + getNowPrice() + ")";
    }


    public Integer getId() {
        return this.id;
    }

    public String getCoinName() {
        return this.coinName;
    }

    public String getCoinCode() {
        return this.coinCode;
    }

    public String getCoinGid() {
        return this.coinGid;
    }

    public Integer getDynamicRate() {
        return this.dynamicRate;
    }

    public BigDecimal getDefaultRate() {
        return this.defaultRate;
    }

    public Integer getIsUse() {
        return this.isUse;
    }

    public Date getAddTime() {
        return this.addTime;
    }

    public String getTDesc() {
        return this.tDesc;
    }


    public String getNowPrice() {
        return this.nowPrice;
    }
}
