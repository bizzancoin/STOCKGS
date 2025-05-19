
package com.xc.vo.stockfutures;

import java.math.BigDecimal;
import java.util.Date;

public class StockFuturesListVO {
    private Integer id;

    private String futuresName;

    private String futuresCode;

    private String futuresGid;

    private String futuresUnit;

    private Integer futuresStandard;

    private String coinCode;

    private Integer homeShow;

    private Integer listShow;

    private Integer depositAmt;

    private Integer transFee;

    /*是否添加自选：1、添加自选，0、未添加自选*/
    private String isOption;



    public void setId(Integer id) {
        this.id = id;
    }

    private Integer minNum;
    private Integer maxNum;
    private Integer transState;
    private String transAmBegin;
    private String transAmEnd;
    private String transPmBegin;
    private String transPmEnd;
    private Date addTime;
    private String tDesc;
    private String nowPrice;
    private BigDecimal coinRate;

    public void setFuturesName(String futuresName) {
        this.futuresName = futuresName;
    }

    public void setFuturesCode(String futuresCode) {
        this.futuresCode = futuresCode;
    }

    public void setFuturesGid(String futuresGid) {
        this.futuresGid = futuresGid;
    }

    public void setFuturesUnit(String futuresUnit) {
        this.futuresUnit = futuresUnit;
    }

    public void setFuturesStandard(Integer futuresStandard) {
        this.futuresStandard = futuresStandard;
    }

    public void setCoinCode(String coinCode) {
        this.coinCode = coinCode;
    }

    public void setHomeShow(Integer homeShow) {
        this.homeShow = homeShow;
    }

    public void setListShow(Integer listShow) {
        this.listShow = listShow;
    }

    public void setDepositAmt(Integer depositAmt) {
        this.depositAmt = depositAmt;
    }

    public void setTransFee(Integer transFee) {
        this.transFee = transFee;
    }

    public void setMinNum(Integer minNum) {
        this.minNum = minNum;
    }

    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
    }

    public void setTransState(Integer transState) {
        this.transState = transState;
    }

    public void setTransAmBegin(String transAmBegin) {
        this.transAmBegin = transAmBegin;
    }

    public void setTransAmEnd(String transAmEnd) {
        this.transAmEnd = transAmEnd;
    }

    public void setTransPmBegin(String transPmBegin) {
        this.transPmBegin = transPmBegin;
    }

    public void setTransPmEnd(String transPmEnd) {
        this.transPmEnd = transPmEnd;
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

    public void setCoinRate(BigDecimal coinRate) {
        this.coinRate = coinRate;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof StockFuturesListVO)) return false;
        StockFuturesListVO other = (StockFuturesListVO) o;
        if (!other.canEqual(this)) return false;
        Object this$id = getId(), other$id = other.getId();
        if ((this$id == null) ? (other$id != null) : !this$id.equals(other$id)) return false;
        Object this$futuresName = getFuturesName(), other$futuresName = other.getFuturesName();
        if ((this$futuresName == null) ? (other$futuresName != null) : !this$futuresName.equals(other$futuresName))
            return false;
        Object this$futuresCode = getFuturesCode(), other$futuresCode = other.getFuturesCode();
        if ((this$futuresCode == null) ? (other$futuresCode != null) : !this$futuresCode.equals(other$futuresCode))
            return false;
        Object this$futuresGid = getFuturesGid(), other$futuresGid = other.getFuturesGid();
        if ((this$futuresGid == null) ? (other$futuresGid != null) : !this$futuresGid.equals(other$futuresGid))
            return false;
        Object this$futuresUnit = getFuturesUnit(), other$futuresUnit = other.getFuturesUnit();
        if ((this$futuresUnit == null) ? (other$futuresUnit != null) : !this$futuresUnit.equals(other$futuresUnit))
            return false;
        Object this$futuresStandard = getFuturesStandard(), other$futuresStandard = other.getFuturesStandard();
        if ((this$futuresStandard == null) ? (other$futuresStandard != null) : !this$futuresStandard.equals(other$futuresStandard))
            return false;
        Object this$coinCode = getCoinCode(), other$coinCode = other.getCoinCode();
        if ((this$coinCode == null) ? (other$coinCode != null) : !this$coinCode.equals(other$coinCode)) return false;
        Object this$homeShow = getHomeShow(), other$homeShow = other.getHomeShow();
        if ((this$homeShow == null) ? (other$homeShow != null) : !this$homeShow.equals(other$homeShow)) return false;
        Object this$listShow = getListShow(), other$listShow = other.getListShow();
        if ((this$listShow == null) ? (other$listShow != null) : !this$listShow.equals(other$listShow)) return false;
        Object this$depositAmt = getDepositAmt(), other$depositAmt = other.getDepositAmt();
        if ((this$depositAmt == null) ? (other$depositAmt != null) : !this$depositAmt.equals(other$depositAmt))
            return false;
        Object this$transFee = getTransFee(), other$transFee = other.getTransFee();
        if ((this$transFee == null) ? (other$transFee != null) : !this$transFee.equals(other$transFee)) return false;
        Object this$minNum = getMinNum(), other$minNum = other.getMinNum();
        if ((this$minNum == null) ? (other$minNum != null) : !this$minNum.equals(other$minNum)) return false;
        Object this$maxNum = getMaxNum(), other$maxNum = other.getMaxNum();
        if ((this$maxNum == null) ? (other$maxNum != null) : !this$maxNum.equals(other$maxNum)) return false;
        Object this$transState = getTransState(), other$transState = other.getTransState();
        if ((this$transState == null) ? (other$transState != null) : !this$transState.equals(other$transState))
            return false;
        Object this$transAmBegin = getTransAmBegin(), other$transAmBegin = other.getTransAmBegin();
        if ((this$transAmBegin == null) ? (other$transAmBegin != null) : !this$transAmBegin.equals(other$transAmBegin))
            return false;
        Object this$transAmEnd = getTransAmEnd(), other$transAmEnd = other.getTransAmEnd();
        if ((this$transAmEnd == null) ? (other$transAmEnd != null) : !this$transAmEnd.equals(other$transAmEnd))
            return false;
        Object this$transPmBegin = getTransPmBegin(), other$transPmBegin = other.getTransPmBegin();
        if ((this$transPmBegin == null) ? (other$transPmBegin != null) : !this$transPmBegin.equals(other$transPmBegin))
            return false;
        Object this$transPmEnd = getTransPmEnd(), other$transPmEnd = other.getTransPmEnd();
        if ((this$transPmEnd == null) ? (other$transPmEnd != null) : !this$transPmEnd.equals(other$transPmEnd))
            return false;
        Object this$addTime = getAddTime(), other$addTime = other.getAddTime();
        if ((this$addTime == null) ? (other$addTime != null) : !this$addTime.equals(other$addTime)) return false;
        Object this$tDesc = getTDesc(), other$tDesc = other.getTDesc();
        if ((this$tDesc == null) ? (other$tDesc != null) : !this$tDesc.equals(other$tDesc)) return false;
        Object this$nowPrice = getNowPrice(), other$nowPrice = other.getNowPrice();
        if ((this$nowPrice == null) ? (other$nowPrice != null) : !this$nowPrice.equals(other$nowPrice)) return false;
        Object this$coinRate = getCoinRate(), other$coinRate = other.getCoinRate();
        return !((this$coinRate == null) ? (other$coinRate != null) : !this$coinRate.equals(other$coinRate));
    }

    protected boolean canEqual(Object other) {
        return other instanceof StockFuturesListVO;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $id = getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        Object $futuresName = getFuturesName();
        result = result * 59 + (($futuresName == null) ? 43 : $futuresName.hashCode());
        Object $futuresCode = getFuturesCode();
        result = result * 59 + (($futuresCode == null) ? 43 : $futuresCode.hashCode());
        Object $futuresGid = getFuturesGid();
        result = result * 59 + (($futuresGid == null) ? 43 : $futuresGid.hashCode());
        Object $futuresUnit = getFuturesUnit();
        result = result * 59 + (($futuresUnit == null) ? 43 : $futuresUnit.hashCode());
        Object $futuresStandard = getFuturesStandard();
        result = result * 59 + (($futuresStandard == null) ? 43 : $futuresStandard.hashCode());
        Object $coinCode = getCoinCode();
        result = result * 59 + (($coinCode == null) ? 43 : $coinCode.hashCode());
        Object $homeShow = getHomeShow();
        result = result * 59 + (($homeShow == null) ? 43 : $homeShow.hashCode());
        Object $listShow = getListShow();
        result = result * 59 + (($listShow == null) ? 43 : $listShow.hashCode());
        Object $depositAmt = getDepositAmt();
        result = result * 59 + (($depositAmt == null) ? 43 : $depositAmt.hashCode());
        Object $transFee = getTransFee();
        result = result * 59 + (($transFee == null) ? 43 : $transFee.hashCode());
        Object $minNum = getMinNum();
        result = result * 59 + (($minNum == null) ? 43 : $minNum.hashCode());
        Object $maxNum = getMaxNum();
        result = result * 59 + (($maxNum == null) ? 43 : $maxNum.hashCode());
        Object $transState = getTransState();
        result = result * 59 + (($transState == null) ? 43 : $transState.hashCode());
        Object $transAmBegin = getTransAmBegin();
        result = result * 59 + (($transAmBegin == null) ? 43 : $transAmBegin.hashCode());
        Object $transAmEnd = getTransAmEnd();
        result = result * 59 + (($transAmEnd == null) ? 43 : $transAmEnd.hashCode());
        Object $transPmBegin = getTransPmBegin();
        result = result * 59 + (($transPmBegin == null) ? 43 : $transPmBegin.hashCode());
        Object $transPmEnd = getTransPmEnd();
        result = result * 59 + (($transPmEnd == null) ? 43 : $transPmEnd.hashCode());
        Object $addTime = getAddTime();
        result = result * 59 + (($addTime == null) ? 43 : $addTime.hashCode());
        Object $tDesc = getTDesc();
        result = result * 59 + (($tDesc == null) ? 43 : $tDesc.hashCode());
        Object $nowPrice = getNowPrice();
        result = result * 59 + (($nowPrice == null) ? 43 : $nowPrice.hashCode());
        Object $coinRate = getCoinRate();
        return result * 59 + (($coinRate == null) ? 43 : $coinRate.hashCode());
    }

    public String toString() {
        return "StockFuturesListVO(id=" + getId() + ", futuresName=" + getFuturesName() + ", futuresCode=" + getFuturesCode() + ", futuresGid=" + getFuturesGid() + ", futuresUnit=" + getFuturesUnit() + ", futuresStandard=" + getFuturesStandard() + ", coinCode=" + getCoinCode() + ", homeShow=" + getHomeShow() + ", listShow=" + getListShow() + ", depositAmt=" + getDepositAmt() + ", transFee=" + getTransFee() + ", minNum=" + getMinNum() + ", maxNum=" + getMaxNum() + ", transState=" + getTransState() + ", transAmBegin=" + getTransAmBegin() + ", transAmEnd=" + getTransAmEnd() + ", transPmBegin=" + getTransPmBegin() + ", transPmEnd=" + getTransPmEnd() + ", addTime=" + getAddTime() + ", tDesc=" + getTDesc() + ", nowPrice=" + getNowPrice() + ", coinRate=" + getCoinRate() + ")";
    }


    public Integer getId() {
        return this.id;
    }


    public String getFuturesName() {
        return this.futuresName;
    }


    public String getFuturesCode() {
        return this.futuresCode;
    }


    public String getFuturesGid() {
        return this.futuresGid;
    }


    public String getFuturesUnit() {
        return this.futuresUnit;
    }


    public Integer getFuturesStandard() {
        return this.futuresStandard;
    }


    public String getCoinCode() {
        return this.coinCode;
    }


    public Integer getHomeShow() {
        return this.homeShow;
    }


    public Integer getListShow() {
        return this.listShow;
    }


    public Integer getDepositAmt() {
        return this.depositAmt;
    }


    public Integer getTransFee() {
        return this.transFee;
    }


    public Integer getMinNum() {
        return this.minNum;
    }


    public Integer getMaxNum() {
        return this.maxNum;
    }


    public Integer getTransState() {
        return this.transState;
    }


    public String getTransAmBegin() {
        return this.transAmBegin;
    }


    public String getTransAmEnd() {
        return this.transAmEnd;
    }


    public String getTransPmBegin() {
        return this.transPmBegin;
    }


    public String getTransPmEnd() {
        return this.transPmEnd;
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


    public BigDecimal getCoinRate() {
        return this.coinRate;
    }

    public String getIsOption() {
        return isOption;
    }

    public void setIsOption(String isOption) {
        this.isOption = isOption;
    }
}

