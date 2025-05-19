package com.xc.pojo;

import java.beans.ConstructorProperties;
import java.math.BigDecimal;

public class SitePay {
    private Integer id;
    private Integer cType;
    private String formUrl;
    private String formCode;
    private String channelType;
    private String channelName;

    public void setId(Integer id) {
        this.id = id;
    }

    private String channelDesc;
    private String channelAccount;
    private String channelImg;
    private Integer channelMinLimit;
    private Integer channelMaxLimit;
    private Integer isShow;
    private Integer isLock;
    /*累计充值金额*/
    private BigDecimal totalPrice;

    public void setCType(Integer cType) {
        this.cType = cType;
    }

    public void setFormUrl(String formUrl) {
        this.formUrl = formUrl;
    }

    public void setFormCode(String formCode) {
        this.formCode = formCode;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public void setChannelDesc(String channelDesc) {
        this.channelDesc = channelDesc;
    }

    public void setChannelAccount(String channelAccount) {
        this.channelAccount = channelAccount;
    }

    public void setChannelImg(String channelImg) {
        this.channelImg = channelImg;
    }

    public void setChannelMinLimit(Integer channelMinLimit) {
        this.channelMinLimit = channelMinLimit;
    }

    public void setChannelMaxLimit(Integer channelMaxLimit) {
        this.channelMaxLimit = channelMaxLimit;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public void setIsLock(Integer isLock) {
        this.isLock = isLock;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof SitePay)) return false;
        SitePay other = (SitePay) o;
        if (!other.canEqual(this)) return false;
        Object this$id = getId(), other$id = other.getId();
        if ((this$id == null) ? (other$id != null) : !this$id.equals(other$id)) return false;
        Object this$cType = getCType(), other$cType = other.getCType();
        if ((this$cType == null) ? (other$cType != null) : !this$cType.equals(other$cType)) return false;
        Object this$formUrl = getFormUrl(), other$formUrl = other.getFormUrl();
        if ((this$formUrl == null) ? (other$formUrl != null) : !this$formUrl.equals(other$formUrl)) return false;
        Object this$formCode = getFormCode(), other$formCode = other.getFormCode();
        if ((this$formCode == null) ? (other$formCode != null) : !this$formCode.equals(other$formCode)) return false;
        Object this$channelType = getChannelType(), other$channelType = other.getChannelType();
        if ((this$channelType == null) ? (other$channelType != null) : !this$channelType.equals(other$channelType))
            return false;
        Object this$channelName = getChannelName(), other$channelName = other.getChannelName();
        if ((this$channelName == null) ? (other$channelName != null) : !this$channelName.equals(other$channelName))
            return false;
        Object this$channelDesc = getChannelDesc(), other$channelDesc = other.getChannelDesc();
        if ((this$channelDesc == null) ? (other$channelDesc != null) : !this$channelDesc.equals(other$channelDesc))
            return false;
        Object this$channelAccount = getChannelAccount(), other$channelAccount = other.getChannelAccount();
        if ((this$channelAccount == null) ? (other$channelAccount != null) : !this$channelAccount.equals(other$channelAccount))
            return false;
        Object this$channelImg = getChannelImg(), other$channelImg = other.getChannelImg();
        if ((this$channelImg == null) ? (other$channelImg != null) : !this$channelImg.equals(other$channelImg))
            return false;
        Object this$channelMinLimit = getChannelMinLimit(), other$channelMinLimit = other.getChannelMinLimit();
        if ((this$channelMinLimit == null) ? (other$channelMinLimit != null) : !this$channelMinLimit.equals(other$channelMinLimit))
            return false;
        Object this$channelMaxLimit = getChannelMaxLimit(), other$channelMaxLimit = other.getChannelMaxLimit();
        if ((this$channelMaxLimit == null) ? (other$channelMaxLimit != null) : !this$channelMaxLimit.equals(other$channelMaxLimit))
            return false;
        Object this$isShow = getIsShow(), other$isShow = other.getIsShow();
        if ((this$isShow == null) ? (other$isShow != null) : !this$isShow.equals(other$isShow)) return false;
        Object this$isLock = getIsLock(), other$isLock = other.getIsLock();
        return !((this$isLock == null) ? (other$isLock != null) : !this$isLock.equals(other$isLock));
    }

    protected boolean canEqual(Object other) {
        return other instanceof SitePay;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $id = getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        Object $cType = getCType();
        result = result * 59 + (($cType == null) ? 43 : $cType.hashCode());
        Object $formUrl = getFormUrl();
        result = result * 59 + (($formUrl == null) ? 43 : $formUrl.hashCode());
        Object $formCode = getFormCode();
        result = result * 59 + (($formCode == null) ? 43 : $formCode.hashCode());
        Object $channelType = getChannelType();
        result = result * 59 + (($channelType == null) ? 43 : $channelType.hashCode());
        Object $channelName = getChannelName();
        result = result * 59 + (($channelName == null) ? 43 : $channelName.hashCode());
        Object $channelDesc = getChannelDesc();
        result = result * 59 + (($channelDesc == null) ? 43 : $channelDesc.hashCode());
        Object $channelAccount = getChannelAccount();
        result = result * 59 + (($channelAccount == null) ? 43 : $channelAccount.hashCode());
        Object $channelImg = getChannelImg();
        result = result * 59 + (($channelImg == null) ? 43 : $channelImg.hashCode());
        Object $channelMinLimit = getChannelMinLimit();
        result = result * 59 + (($channelMinLimit == null) ? 43 : $channelMinLimit.hashCode());
        Object $channelMaxLimit = getChannelMaxLimit();
        result = result * 59 + (($channelMaxLimit == null) ? 43 : $channelMaxLimit.hashCode());
        Object $isShow = getIsShow();
        result = result * 59 + (($isShow == null) ? 43 : $isShow.hashCode());
        Object $isLock = getIsLock();
        return result * 59 + (($isLock == null) ? 43 : $isLock.hashCode());
    }

    public String toString() {
        return "SitePay(id=" + getId() + ", cType=" + getCType() + ", formUrl=" + getFormUrl() + ", formCode=" + getFormCode() + ", channelType=" + getChannelType() + ", channelName=" + getChannelName() + ", channelDesc=" + getChannelDesc() + ", channelAccount=" + getChannelAccount() + ", channelImg=" + getChannelImg() + ", channelMinLimit=" + getChannelMinLimit() + ", channelMaxLimit=" + getChannelMaxLimit() + ", isShow=" + getIsShow() + ", isLock=" + getIsLock()+ ", totalPrice=" + getTotalPrice() + ")";
    }

    public SitePay() {
    }

    @ConstructorProperties({"id", "cType", "formUrl", "formCode", "channelType", "channelName", "channelDesc", "channelAccount", "channelImg", "channelMinLimit", "channelMaxLimit", "isShow", "isLock","totalPrice"})
    public SitePay(Integer id, Integer cType, String formUrl, String formCode, String channelType, String channelName, String channelDesc, String channelAccount, String channelImg, Integer channelMinLimit, Integer channelMaxLimit, Integer isShow, Integer isLock, BigDecimal totalPrice) {
        this.id = id;
        this.cType = cType;
        this.formUrl = formUrl;
        this.formCode = formCode;
        this.channelType = channelType;
        this.channelName = channelName;
        this.channelDesc = channelDesc;
        this.channelAccount = channelAccount;
        this.channelImg = channelImg;
        this.channelMinLimit = channelMinLimit;
        this.channelMaxLimit = channelMaxLimit;
        this.isShow = isShow;
        this.isLock = isLock;
        this.totalPrice = totalPrice;
    }


    public Integer getId() {
        return this.id;
    }

    public Integer getCType() {
        return this.cType;
    }


    public String getFormUrl() {
        return this.formUrl;
    }


    public String getFormCode() {
        return this.formCode;
    }


    public String getChannelType() {
        return this.channelType;
    }


    public String getChannelName() {
        return this.channelName;
    }


    public String getChannelDesc() {
        return this.channelDesc;
    }


    public String getChannelAccount() {
        return this.channelAccount;
    }


    public String getChannelImg() {
        return this.channelImg;
    }

    public Integer getChannelMinLimit() {
        return this.channelMinLimit;
    }

    public Integer getChannelMaxLimit() {
        return this.channelMaxLimit;
    }

    public Integer getIsShow() {
        return this.isShow;
    }

    public Integer getIsLock() {
        return this.isLock;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}

