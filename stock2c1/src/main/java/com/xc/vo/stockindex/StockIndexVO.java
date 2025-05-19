package com.xc.vo.stockindex;

import com.github.pagehelper.StringUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

public class StockIndexVO {
    private Integer id;
    private String indexName;
    private String indexCode;
    private String indexGid;
    private Integer homeShow;
    private Integer listShow;
    private Integer transState;
    private Integer depositAmt;

    public void setId(Integer id) {
        this.id = id;
    }

    private Integer transFee;
    private Integer eachPoint;
    private Integer minNum;
    private Integer maxNum;
    private Date addTime;
    private String tDesc;
    private String currentPoint;
    private String floatPoint;
    private String floatRate;
    /*是否添加自选：1、添加自选，0、未添加自选*/
    private String isOption;


    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public void setIndexCode(String indexCode) {
        this.indexCode = indexCode;
    }

    public void setIndexGid(String indexGid) {
        this.indexGid = indexGid;
    }

    public void setHomeShow(Integer homeShow) {
        this.homeShow = homeShow;
    }

    public void setListShow(Integer listShow) {
        this.listShow = listShow;
    }

    public void setTransState(Integer transState) {
        this.transState = transState;
    }

    public void setDepositAmt(Integer depositAmt) {
        this.depositAmt = depositAmt;
    }

    public void setTransFee(Integer transFee) {
        this.transFee = transFee;
    }

    public void setEachPoint(Integer eachPoint) {
        this.eachPoint = eachPoint;
    }

    public void setMinNum(Integer minNum) {
        this.minNum = minNum;
    }

    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public void setTDesc(String tDesc) {
        this.tDesc = tDesc;
    }

    public void setCurrentPoint(String currentPoint) {
        if(StringUtil.isEmpty(currentPoint)){
            this.currentPoint="0.00";
        }else {
            this.currentPoint = new BigDecimal(currentPoint).setScale(2, RoundingMode.DOWN).toPlainString();
        }
    }

    public void setFloatPoint(String floatPoint) {
        this.floatPoint = floatPoint;
    }

    public void setFloatRate(String floatRate) {
        this.floatRate = floatRate;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof StockIndexVO)) return false;
        StockIndexVO other = (StockIndexVO) o;
        if (!other.canEqual(this)) return false;
        Object this$id = getId(), other$id = other.getId();
        if ((this$id == null) ? (other$id != null) : !this$id.equals(other$id)) return false;
        Object this$indexName = getIndexName(), other$indexName = other.getIndexName();
        if ((this$indexName == null) ? (other$indexName != null) : !this$indexName.equals(other$indexName))
            return false;
        Object this$indexCode = getIndexCode(), other$indexCode = other.getIndexCode();
        if ((this$indexCode == null) ? (other$indexCode != null) : !this$indexCode.equals(other$indexCode))
            return false;
        Object this$indexGid = getIndexGid(), other$indexGid = other.getIndexGid();
        if ((this$indexGid == null) ? (other$indexGid != null) : !this$indexGid.equals(other$indexGid)) return false;
        Object this$homeShow = getHomeShow(), other$homeShow = other.getHomeShow();
        if ((this$homeShow == null) ? (other$homeShow != null) : !this$homeShow.equals(other$homeShow)) return false;
        Object this$listShow = getListShow(), other$listShow = other.getListShow();
        if ((this$listShow == null) ? (other$listShow != null) : !this$listShow.equals(other$listShow)) return false;
        Object this$transState = getTransState(), other$transState = other.getTransState();
        if ((this$transState == null) ? (other$transState != null) : !this$transState.equals(other$transState))
            return false;
        Object this$depositAmt = getDepositAmt(), other$depositAmt = other.getDepositAmt();
        if ((this$depositAmt == null) ? (other$depositAmt != null) : !this$depositAmt.equals(other$depositAmt))
            return false;
        Object this$transFee = getTransFee(), other$transFee = other.getTransFee();
        if ((this$transFee == null) ? (other$transFee != null) : !this$transFee.equals(other$transFee)) return false;
        Object this$eachPoint = getEachPoint(), other$eachPoint = other.getEachPoint();
        if ((this$eachPoint == null) ? (other$eachPoint != null) : !this$eachPoint.equals(other$eachPoint))
            return false;
        Object this$minNum = getMinNum(), other$minNum = other.getMinNum();
        if ((this$minNum == null) ? (other$minNum != null) : !this$minNum.equals(other$minNum)) return false;
        Object this$maxNum = getMaxNum(), other$maxNum = other.getMaxNum();
        if ((this$maxNum == null) ? (other$maxNum != null) : !this$maxNum.equals(other$maxNum)) return false;
        Object this$addTime = getAddTime(), other$addTime = other.getAddTime();
        if ((this$addTime == null) ? (other$addTime != null) : !this$addTime.equals(other$addTime)) return false;
        Object this$tDesc = getTDesc(), other$tDesc = other.getTDesc();
        if ((this$tDesc == null) ? (other$tDesc != null) : !this$tDesc.equals(other$tDesc)) return false;
        Object this$currentPoint = getCurrentPoint(), other$currentPoint = other.getCurrentPoint();
        if ((this$currentPoint == null) ? (other$currentPoint != null) : !this$currentPoint.equals(other$currentPoint))
            return false;
        Object this$floatPoint = getFloatPoint(), other$floatPoint = other.getFloatPoint();
        if ((this$floatPoint == null) ? (other$floatPoint != null) : !this$floatPoint.equals(other$floatPoint))
            return false;
        Object this$floatRate = getFloatRate(), other$floatRate = other.getFloatRate();
        return !((this$floatRate == null) ? (other$floatRate != null) : !this$floatRate.equals(other$floatRate));
    }

    protected boolean canEqual(Object other) {
        return other instanceof StockIndexVO;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $id = getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        Object $indexName = getIndexName();
        result = result * 59 + (($indexName == null) ? 43 : $indexName.hashCode());
        Object $indexCode = getIndexCode();
        result = result * 59 + (($indexCode == null) ? 43 : $indexCode.hashCode());
        Object $indexGid = getIndexGid();
        result = result * 59 + (($indexGid == null) ? 43 : $indexGid.hashCode());
        Object $homeShow = getHomeShow();
        result = result * 59 + (($homeShow == null) ? 43 : $homeShow.hashCode());
        Object $listShow = getListShow();
        result = result * 59 + (($listShow == null) ? 43 : $listShow.hashCode());
        Object $transState = getTransState();
        result = result * 59 + (($transState == null) ? 43 : $transState.hashCode());
        Object $depositAmt = getDepositAmt();
        result = result * 59 + (($depositAmt == null) ? 43 : $depositAmt.hashCode());
        Object $transFee = getTransFee();
        result = result * 59 + (($transFee == null) ? 43 : $transFee.hashCode());
        Object $eachPoint = getEachPoint();
        result = result * 59 + (($eachPoint == null) ? 43 : $eachPoint.hashCode());
        Object $minNum = getMinNum();
        result = result * 59 + (($minNum == null) ? 43 : $minNum.hashCode());
        Object $maxNum = getMaxNum();
        result = result * 59 + (($maxNum == null) ? 43 : $maxNum.hashCode());
        Object $addTime = getAddTime();
        result = result * 59 + (($addTime == null) ? 43 : $addTime.hashCode());
        Object $tDesc = getTDesc();
        result = result * 59 + (($tDesc == null) ? 43 : $tDesc.hashCode());
        Object $currentPoint = getCurrentPoint();
        result = result * 59 + (($currentPoint == null) ? 43 : $currentPoint.hashCode());
        Object $floatPoint = getFloatPoint();
        result = result * 59 + (($floatPoint == null) ? 43 : $floatPoint.hashCode());
        Object $floatRate = getFloatRate();
        return result * 59 + (($floatRate == null) ? 43 : $floatRate.hashCode());
    }

    public String toString() {
        return "StockIndexVO(id=" + getId() + ", indexName=" + getIndexName() + ", indexCode=" + getIndexCode() + ", indexGid=" + getIndexGid() + ", homeShow=" + getHomeShow() + ", listShow=" + getListShow() + ", transState=" + getTransState() + ", depositAmt=" + getDepositAmt() + ", transFee=" + getTransFee() + ", eachPoint=" + getEachPoint() + ", minNum=" + getMinNum() + ", maxNum=" + getMaxNum() + ", addTime=" + getAddTime() + ", tDesc=" + getTDesc() + ", currentPoint=" + getCurrentPoint() + ", floatPoint=" + getFloatPoint() + ", floatRate=" + getFloatRate() + ")";
    }


    public Integer getId() {
        return this.id;
    }

    public String getIndexName() {
        return this.indexName;
    }

    public String getIndexCode() {
        return this.indexCode;
    }

    public String getIndexGid() {
        return this.indexGid;
    }

    public Integer getHomeShow() {
        return this.homeShow;
    }

    public Integer getListShow() {
        return this.listShow;
    }

    public Integer getTransState() {
        return this.transState;
    }

    public Integer getDepositAmt() {
        return this.depositAmt;
    }

    public Integer getTransFee() {
        return this.transFee;
    }

    public Integer getEachPoint() {
        return this.eachPoint;
    }

    public Integer getMinNum() {
        return this.minNum;
    }

    public Integer getMaxNum() {
        return this.maxNum;
    }

    public Date getAddTime() {
        return this.addTime;
    }

    public String getTDesc() {
        return this.tDesc;
    }


    public String getCurrentPoint() {
        return this.currentPoint;
    }

    public String getFloatPoint() {
        return this.floatPoint;
    }

    public String getFloatRate() {
        return this.floatRate;
    }

    public String getIsOption() {
        return isOption;
    }

    public void setIsOption(String isOption) {
        this.isOption = isOption;
    }
}

