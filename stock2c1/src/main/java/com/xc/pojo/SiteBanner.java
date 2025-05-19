package com.xc.pojo;

import java.beans.ConstructorProperties;
import java.util.Date;

public class SiteBanner {
    private Integer id;
    private String bannerUrl;
    private Integer isOrder;
    private Integer isPc;

    public void setId(Integer id) {
        this.id = id;
    }

    private Integer isM;
    private Date addTime;
    private String banTitle;
    private String banDesc;
    private String targetUrl;

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public void setIsOrder(Integer isOrder) {
        this.isOrder = isOrder;
    }

    public void setIsPc(Integer isPc) {
        this.isPc = isPc;
    }

    public void setIsM(Integer isM) {
        this.isM = isM;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public void setBanTitle(String banTitle) {
        this.banTitle = banTitle;
    }

    public void setBanDesc(String banDesc) {
        this.banDesc = banDesc;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof SiteBanner)) return false;
        SiteBanner other = (SiteBanner) o;
        if (!other.canEqual(this)) return false;
        Object this$id = getId(), other$id = other.getId();
        if ((this$id == null) ? (other$id != null) : !this$id.equals(other$id)) return false;
        Object this$bannerUrl = getBannerUrl(), other$bannerUrl = other.getBannerUrl();
        if ((this$bannerUrl == null) ? (other$bannerUrl != null) : !this$bannerUrl.equals(other$bannerUrl))
            return false;
        Object this$isOrder = getIsOrder(), other$isOrder = other.getIsOrder();
        if ((this$isOrder == null) ? (other$isOrder != null) : !this$isOrder.equals(other$isOrder)) return false;
        Object this$isPc = getIsPc(), other$isPc = other.getIsPc();
        if ((this$isPc == null) ? (other$isPc != null) : !this$isPc.equals(other$isPc)) return false;
        Object this$isM = getIsM(), other$isM = other.getIsM();
        if ((this$isM == null) ? (other$isM != null) : !this$isM.equals(other$isM)) return false;
        Object this$addTime = getAddTime(), other$addTime = other.getAddTime();
        if ((this$addTime == null) ? (other$addTime != null) : !this$addTime.equals(other$addTime)) return false;
        Object this$banTitle = getBanTitle(), other$banTitle = other.getBanTitle();
        if ((this$banTitle == null) ? (other$banTitle != null) : !this$banTitle.equals(other$banTitle)) return false;
        Object this$banDesc = getBanDesc(), other$banDesc = other.getBanDesc();
        if ((this$banDesc == null) ? (other$banDesc != null) : !this$banDesc.equals(other$banDesc)) return false;
        Object this$targetUrl = getTargetUrl(), other$targetUrl = other.getTargetUrl();
        return !((this$targetUrl == null) ? (other$targetUrl != null) : !this$targetUrl.equals(other$targetUrl));
    }

    protected boolean canEqual(Object other) {
        return other instanceof SiteBanner;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $id = getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        Object $bannerUrl = getBannerUrl();
        result = result * 59 + (($bannerUrl == null) ? 43 : $bannerUrl.hashCode());
        Object $isOrder = getIsOrder();
        result = result * 59 + (($isOrder == null) ? 43 : $isOrder.hashCode());
        Object $isPc = getIsPc();
        result = result * 59 + (($isPc == null) ? 43 : $isPc.hashCode());
        Object $isM = getIsM();
        result = result * 59 + (($isM == null) ? 43 : $isM.hashCode());
        Object $addTime = getAddTime();
        result = result * 59 + (($addTime == null) ? 43 : $addTime.hashCode());
        Object $banTitle = getBanTitle();
        result = result * 59 + (($banTitle == null) ? 43 : $banTitle.hashCode());
        Object $banDesc = getBanDesc();
        result = result * 59 + (($banDesc == null) ? 43 : $banDesc.hashCode());
        Object $targetUrl = getTargetUrl();
        return result * 59 + (($targetUrl == null) ? 43 : $targetUrl.hashCode());
    }

    public String toString() {
        return "SiteBanner(id=" + getId() + ", bannerUrl=" + getBannerUrl() + ", isOrder=" + getIsOrder() + ", isPc=" + getIsPc() + ", isM=" + getIsM() + ", addTime=" + getAddTime() + ", banTitle=" + getBanTitle() + ", banDesc=" + getBanDesc() + ", targetUrl=" + getTargetUrl() + ")";
    }

    public SiteBanner() {
    }

    @ConstructorProperties({"id", "bannerUrl", "isOrder", "isPc", "isM", "addTime", "banTitle", "banDesc", "targetUrl"})
    public SiteBanner(Integer id, String bannerUrl, Integer isOrder, Integer isPc, Integer isM, Date addTime, String banTitle, String banDesc, String targetUrl) {
        this.id = id;
        this.bannerUrl = bannerUrl;
        this.isOrder = isOrder;
        this.isPc = isPc;
        this.isM = isM;
        this.addTime = addTime;
        this.banTitle = banTitle;
        this.banDesc = banDesc;
        this.targetUrl = targetUrl;
    }

    public Integer getId() {
        return this.id;
    }

    public String getBannerUrl() {
        return this.bannerUrl;
    }

    public Integer getIsOrder() {
        return this.isOrder;
    }

    public Integer getIsPc() {
        return this.isPc;
    }

    public Integer getIsM() {
        return this.isM;
    }

    public Date getAddTime() {
        return this.addTime;
    }

    public String getBanTitle() {
        return this.banTitle;
    }

    public String getBanDesc() {
        return this.banDesc;
    }

    public String getTargetUrl() {
        return this.targetUrl;
    }
}
