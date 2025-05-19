package com.xc.pojo;

import java.beans.ConstructorProperties;
import java.util.Date;

public class StockIndex {
    private Integer id;
    private String indexName;
    private String indexCode;
    private String indexGid;
    private Integer homeShow;
    private Integer listShow;
    private Integer transState;

    public void setId(Integer id) {
        this.id = id;
    }

    private Integer depositAmt;
    private Integer transFee;
    private Integer eachPoint;
    private Integer minNum;
    private Integer maxNum;
    private Date addTime;
    private String tDesc;

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

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof StockIndex)) return false;
        StockIndex other = (StockIndex) o;
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
        return !((this$tDesc == null) ? (other$tDesc != null) : !this$tDesc.equals(other$tDesc));
    }

    protected boolean canEqual(Object other) {
        return other instanceof StockIndex;
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
        return result * 59 + (($tDesc == null) ? 43 : $tDesc.hashCode());
    }

    public String toString() {
        return "StockIndex(id=" + getId() + ", indexName=" + getIndexName() + ", indexCode=" + getIndexCode() + ", indexGid=" + getIndexGid() + ", homeShow=" + getHomeShow() + ", listShow=" + getListShow() + ", transState=" + getTransState() + ", depositAmt=" + getDepositAmt() + ", transFee=" + getTransFee() + ", eachPoint=" + getEachPoint() + ", minNum=" + getMinNum() + ", maxNum=" + getMaxNum() + ", addTime=" + getAddTime() + ", tDesc=" + getTDesc() + ")";
    }

    public StockIndex() {
    }

    @ConstructorProperties({"id", "indexName", "indexCode", "indexGid", "homeShow", "listShow", "transState", "depositAmt", "transFee", "eachPoint", "minNum", "maxNum", "addTime", "tDesc"})
    public StockIndex(Integer id, String indexName, String indexCode, String indexGid, Integer homeShow, Integer listShow, Integer transState, Integer depositAmt, Integer transFee, Integer eachPoint, Integer minNum, Integer maxNum, Date addTime, String tDesc) {
        this.id = id;
        this.indexName = indexName;
        this.indexCode = indexCode;
        this.indexGid = indexGid;
        this.homeShow = homeShow;
        this.listShow = listShow;
        this.transState = transState;
        this.depositAmt = depositAmt;
        this.transFee = transFee;
        this.eachPoint = eachPoint;
        this.minNum = minNum;
        this.maxNum = maxNum;
        this.addTime = addTime;
        this.tDesc = tDesc;
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
}

