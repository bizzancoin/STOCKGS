
package com.xc.pojo;

import java.beans.ConstructorProperties;

public class SiteInfo {
    private Integer id;
    private String siteName;
    private String siteHost;
    private String siteKeywords;
    private String siteDescription;
    private String siteLogo;
    private String siteLogoSm;

    private String siteQq;

    private String sitePhone;

    private String siteAndroidImg;

    private String siteAndroidUrl;

    private String siteIosImg;


    public void setId(Integer id) {
        this.id = id;
    }

    private String siteIosUrl;
    private String siteEmailFrom;
    private String siteEmailTo;
    private String siteLanguage;
    private String siteVersionInfo;
    private String siteIntro;
    private String riskNotice;
    private String companyInfo;
    private String certImg1;
    private String certImg2;
    private String regAgree;
    private String tradeAgree;
    private String tradeAgreeText;
    private String siteColor;
    private String regAgreeText;
    /*在线客服*/
    private String onlineService;
    /*默认代理编号*/
    private String agentCode;

    /*融资融券标题*/
    private String tradeAgreeTitle;
    /*是否验证短信开关：1、验证，0、不验证*/
    private Boolean smsDisplay;

    public String getTradeAgreeText() {
        return tradeAgreeText;
    }

    public void setTradeAgreeText(String tradeAgreeText) {
        this.tradeAgreeText = tradeAgreeText;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public void setSiteHost(String siteHost) {
        this.siteHost = siteHost;
    }

    public void setSiteKeywords(String siteKeywords) {
        this.siteKeywords = siteKeywords;
    }

    public void setSiteDescription(String siteDescription) {
        this.siteDescription = siteDescription;
    }

    public void setSiteLogo(String siteLogo) {
        this.siteLogo = siteLogo;
    }

    public void setSiteLogoSm(String siteLogoSm) {
        this.siteLogoSm = siteLogoSm;
    }

    public void setSiteQq(String siteQq) {
        this.siteQq = siteQq;
    }

    public void setSitePhone(String sitePhone) {
        this.sitePhone = sitePhone;
    }

    public void setSiteAndroidImg(String siteAndroidImg) {
        this.siteAndroidImg = siteAndroidImg;
    }

    public void setSiteAndroidUrl(String siteAndroidUrl) {
        this.siteAndroidUrl = siteAndroidUrl;
    }

    public void setSiteIosImg(String siteIosImg) {
        this.siteIosImg = siteIosImg;
    }

    public void setSiteIosUrl(String siteIosUrl) {
        this.siteIosUrl = siteIosUrl;
    }

    public void setSiteEmailFrom(String siteEmailFrom) {
        this.siteEmailFrom = siteEmailFrom;
    }

    public void setSiteEmailTo(String siteEmailTo) {
        this.siteEmailTo = siteEmailTo;
    }

    public void setSiteLanguage(String siteLanguage) {
        this.siteLanguage = siteLanguage;
    }

    public void setSiteVersionInfo(String siteVersionInfo) {
        this.siteVersionInfo = siteVersionInfo;
    }

    public void setSiteIntro(String siteIntro) {
        this.siteIntro = siteIntro;
    }

    public void setRiskNotice(String riskNotice) {
        this.riskNotice = riskNotice;
    }

    public void setCompanyInfo(String companyInfo) {
        this.companyInfo = companyInfo;
    }

    public void setCertImg1(String certImg1) {
        this.certImg1 = certImg1;
    }

    public void setCertImg2(String certImg2) {
        this.certImg2 = certImg2;
    }

    public void setRegAgree(String regAgree) {
        this.regAgree = regAgree;
    }

    public void setTradeAgree(String tradeAgree) {
        this.tradeAgree = tradeAgree;
    }

    public void setSiteColor(String siteColor) {
        this.siteColor = siteColor;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof SiteInfo)) return false;
        SiteInfo other = (SiteInfo) o;
        if (!other.canEqual(this)) return false;
        Object this$id = getId(), other$id = other.getId();
        if ((this$id == null) ? (other$id != null) : !this$id.equals(other$id)) return false;
        Object this$siteName = getSiteName(), other$siteName = other.getSiteName();
        if ((this$siteName == null) ? (other$siteName != null) : !this$siteName.equals(other$siteName)) return false;
        Object this$siteHost = getSiteHost(), other$siteHost = other.getSiteHost();
        if ((this$siteHost == null) ? (other$siteHost != null) : !this$siteHost.equals(other$siteHost)) return false;
        Object this$siteKeywords = getSiteKeywords(), other$siteKeywords = other.getSiteKeywords();
        if ((this$siteKeywords == null) ? (other$siteKeywords != null) : !this$siteKeywords.equals(other$siteKeywords))
            return false;
        Object this$siteDescription = getSiteDescription(), other$siteDescription = other.getSiteDescription();
        if ((this$siteDescription == null) ? (other$siteDescription != null) : !this$siteDescription.equals(other$siteDescription))
            return false;
        Object this$siteLogo = getSiteLogo(), other$siteLogo = other.getSiteLogo();
        if ((this$siteLogo == null) ? (other$siteLogo != null) : !this$siteLogo.equals(other$siteLogo)) return false;
        Object this$siteLogoSm = getSiteLogoSm(), other$siteLogoSm = other.getSiteLogoSm();
        if ((this$siteLogoSm == null) ? (other$siteLogoSm != null) : !this$siteLogoSm.equals(other$siteLogoSm))
            return false;
        Object this$siteQq = getSiteQq(), other$siteQq = other.getSiteQq();
        if ((this$siteQq == null) ? (other$siteQq != null) : !this$siteQq.equals(other$siteQq)) return false;
        Object this$sitePhone = getSitePhone(), other$sitePhone = other.getSitePhone();
        if ((this$sitePhone == null) ? (other$sitePhone != null) : !this$sitePhone.equals(other$sitePhone))
            return false;
        Object this$siteAndroidImg = getSiteAndroidImg(), other$siteAndroidImg = other.getSiteAndroidImg();
        if ((this$siteAndroidImg == null) ? (other$siteAndroidImg != null) : !this$siteAndroidImg.equals(other$siteAndroidImg))
            return false;
        Object this$siteAndroidUrl = getSiteAndroidUrl(), other$siteAndroidUrl = other.getSiteAndroidUrl();
        if ((this$siteAndroidUrl == null) ? (other$siteAndroidUrl != null) : !this$siteAndroidUrl.equals(other$siteAndroidUrl))
            return false;
        Object this$siteIosImg = getSiteIosImg(), other$siteIosImg = other.getSiteIosImg();
        if ((this$siteIosImg == null) ? (other$siteIosImg != null) : !this$siteIosImg.equals(other$siteIosImg))
            return false;
        Object this$siteIosUrl = getSiteIosUrl(), other$siteIosUrl = other.getSiteIosUrl();
        if ((this$siteIosUrl == null) ? (other$siteIosUrl != null) : !this$siteIosUrl.equals(other$siteIosUrl))
            return false;
        Object this$siteEmailFrom = getSiteEmailFrom(), other$siteEmailFrom = other.getSiteEmailFrom();
        if ((this$siteEmailFrom == null) ? (other$siteEmailFrom != null) : !this$siteEmailFrom.equals(other$siteEmailFrom))
            return false;
        Object this$siteEmailTo = getSiteEmailTo(), other$siteEmailTo = other.getSiteEmailTo();
        if ((this$siteEmailTo == null) ? (other$siteEmailTo != null) : !this$siteEmailTo.equals(other$siteEmailTo))
            return false;
        Object this$siteLanguage = getSiteLanguage(), other$siteLanguage = other.getSiteLanguage();
        if ((this$siteLanguage == null) ? (other$siteLanguage != null) : !this$siteLanguage.equals(other$siteLanguage))
            return false;
        Object this$siteVersionInfo = getSiteVersionInfo(), other$siteVersionInfo = other.getSiteVersionInfo();
        if ((this$siteVersionInfo == null) ? (other$siteVersionInfo != null) : !this$siteVersionInfo.equals(other$siteVersionInfo))
            return false;
        Object this$siteIntro = getSiteIntro(), other$siteIntro = other.getSiteIntro();
        if ((this$siteIntro == null) ? (other$siteIntro != null) : !this$siteIntro.equals(other$siteIntro))
            return false;
        Object this$riskNotice = getRiskNotice(), other$riskNotice = other.getRiskNotice();
        if ((this$riskNotice == null) ? (other$riskNotice != null) : !this$riskNotice.equals(other$riskNotice))
            return false;
        Object this$companyInfo = getCompanyInfo(), other$companyInfo = other.getCompanyInfo();
        if ((this$companyInfo == null) ? (other$companyInfo != null) : !this$companyInfo.equals(other$companyInfo))
            return false;
        Object this$certImg1 = getCertImg1(), other$certImg1 = other.getCertImg1();
        if ((this$certImg1 == null) ? (other$certImg1 != null) : !this$certImg1.equals(other$certImg1)) return false;
        Object this$certImg2 = getCertImg2(), other$certImg2 = other.getCertImg2();
        if ((this$certImg2 == null) ? (other$certImg2 != null) : !this$certImg2.equals(other$certImg2)) return false;
        Object this$regAgree = getRegAgree(), other$regAgree = other.getRegAgree();
        if ((this$regAgree == null) ? (other$regAgree != null) : !this$regAgree.equals(other$regAgree)) return false;
        Object this$tradeAgree = getTradeAgree(), other$tradeAgree = other.getTradeAgree();
        if ((this$tradeAgree == null) ? (other$tradeAgree != null) : !this$tradeAgree.equals(other$tradeAgree))
            return false;
        Object this$siteColor = getSiteColor(), other$siteColor = other.getSiteColor();
        return !((this$siteColor == null) ? (other$siteColor != null) : !this$siteColor.equals(other$siteColor));
    }

    protected boolean canEqual(Object other) {
        return other instanceof SiteInfo;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $id = getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        Object $siteName = getSiteName();
        result = result * 59 + (($siteName == null) ? 43 : $siteName.hashCode());
        Object $siteHost = getSiteHost();
        result = result * 59 + (($siteHost == null) ? 43 : $siteHost.hashCode());
        Object $siteKeywords = getSiteKeywords();
        result = result * 59 + (($siteKeywords == null) ? 43 : $siteKeywords.hashCode());
        Object $siteDescription = getSiteDescription();
        result = result * 59 + (($siteDescription == null) ? 43 : $siteDescription.hashCode());
        Object $siteLogo = getSiteLogo();
        result = result * 59 + (($siteLogo == null) ? 43 : $siteLogo.hashCode());
        Object $siteLogoSm = getSiteLogoSm();
        result = result * 59 + (($siteLogoSm == null) ? 43 : $siteLogoSm.hashCode());
        Object $siteQq = getSiteQq();
        result = result * 59 + (($siteQq == null) ? 43 : $siteQq.hashCode());
        Object $sitePhone = getSitePhone();
        result = result * 59 + (($sitePhone == null) ? 43 : $sitePhone.hashCode());
        Object $siteAndroidImg = getSiteAndroidImg();
        result = result * 59 + (($siteAndroidImg == null) ? 43 : $siteAndroidImg.hashCode());
        Object $siteAndroidUrl = getSiteAndroidUrl();
        result = result * 59 + (($siteAndroidUrl == null) ? 43 : $siteAndroidUrl.hashCode());
        Object $siteIosImg = getSiteIosImg();
        result = result * 59 + (($siteIosImg == null) ? 43 : $siteIosImg.hashCode());
        Object $siteIosUrl = getSiteIosUrl();
        result = result * 59 + (($siteIosUrl == null) ? 43 : $siteIosUrl.hashCode());
        Object $siteEmailFrom = getSiteEmailFrom();
        result = result * 59 + (($siteEmailFrom == null) ? 43 : $siteEmailFrom.hashCode());
        Object $siteEmailTo = getSiteEmailTo();
        result = result * 59 + (($siteEmailTo == null) ? 43 : $siteEmailTo.hashCode());
        Object $siteLanguage = getSiteLanguage();
        result = result * 59 + (($siteLanguage == null) ? 43 : $siteLanguage.hashCode());
        Object $siteVersionInfo = getSiteVersionInfo();
        result = result * 59 + (($siteVersionInfo == null) ? 43 : $siteVersionInfo.hashCode());
        Object $siteIntro = getSiteIntro();
        result = result * 59 + (($siteIntro == null) ? 43 : $siteIntro.hashCode());
        Object $riskNotice = getRiskNotice();
        result = result * 59 + (($riskNotice == null) ? 43 : $riskNotice.hashCode());
        Object $companyInfo = getCompanyInfo();
        result = result * 59 + (($companyInfo == null) ? 43 : $companyInfo.hashCode());
        Object $certImg1 = getCertImg1();
        result = result * 59 + (($certImg1 == null) ? 43 : $certImg1.hashCode());
        Object $certImg2 = getCertImg2();
        result = result * 59 + (($certImg2 == null) ? 43 : $certImg2.hashCode());
        Object $regAgree = getRegAgree();
        result = result * 59 + (($regAgree == null) ? 43 : $regAgree.hashCode());
        Object $tradeAgree = getTradeAgree();
        result = result * 59 + (($tradeAgree == null) ? 43 : $tradeAgree.hashCode());
        Object $siteColor = getSiteColor();
        return result * 59 + (($siteColor == null) ? 43 : $siteColor.hashCode());
    }

    public String toString() {
        return "SiteInfo(id=" + getId() + ", siteName=" + getSiteName() + ", siteHost=" + getSiteHost() + ", siteKeywords=" + getSiteKeywords() + ", siteDescription=" + getSiteDescription() + ", siteLogo=" + getSiteLogo() + ", siteLogoSm=" + getSiteLogoSm() + ", siteQq=" + getSiteQq() + ", sitePhone=" + getSitePhone() + ", siteAndroidImg=" + getSiteAndroidImg() + ", siteAndroidUrl=" + getSiteAndroidUrl() + ", siteIosImg=" + getSiteIosImg() + ", siteIosUrl=" + getSiteIosUrl() + ", siteEmailFrom=" + getSiteEmailFrom() + ", siteEmailTo=" + getSiteEmailTo() + ", siteLanguage=" + getSiteLanguage() + ", siteVersionInfo=" + getSiteVersionInfo() + ", siteIntro=" + getSiteIntro() + ", riskNotice=" + getRiskNotice() + ", companyInfo=" + getCompanyInfo() + ", certImg1=" + getCertImg1() + ", certImg2=" + getCertImg2() + ", regAgree=" + getRegAgree() + ", tradeAgree=" + getTradeAgree() + ", siteColor=" + getSiteColor()  + ", smsDisplay=" + getSmsDisplay() + ")";
    }

    public SiteInfo() {
    }

    @ConstructorProperties({"id", "siteName", "siteHost", "siteKeywords", "siteDescription", "siteLogo", "siteLogoSm", "siteQq", "sitePhone", "siteAndroidImg", "siteAndroidUrl", "siteIosImg", "siteIosUrl", "siteEmailFrom", "siteEmailTo", "siteLanguage", "siteVersionInfo", "siteIntro", "riskNotice", "companyInfo", "certImg1", "certImg2", "regAgree", "tradeAgree", "siteColor", "tradeAgreeText", "regAgreeText","onlineService","tradeAgreeTitle","smsDisplay"})
    public SiteInfo(Integer id, String siteName, String siteHost, String siteKeywords, String siteDescription, String siteLogo, String siteLogoSm, String siteQq, String sitePhone, String siteAndroidImg, String siteAndroidUrl, String siteIosImg, String siteIosUrl, String siteEmailFrom, String siteEmailTo, String siteLanguage, String siteVersionInfo, String siteIntro, String riskNotice, String companyInfo, String certImg1, String certImg2, String regAgree, String tradeAgree, String siteColor,String tradeAgreeText, String regAgreeText, String onlineService, String tradeAgreeTitle, Boolean smsDisplay) {
        this.id = id;
        this.siteName = siteName;
        this.siteHost = siteHost;
        this.siteKeywords = siteKeywords;
        this.siteDescription = siteDescription;
        this.siteLogo = siteLogo;
        this.siteLogoSm = siteLogoSm;
        this.siteQq = siteQq;
        this.sitePhone = sitePhone;
        this.siteAndroidImg = siteAndroidImg;
        this.siteAndroidUrl = siteAndroidUrl;
        this.siteIosImg = siteIosImg;
        this.siteIosUrl = siteIosUrl;
        this.siteEmailFrom = siteEmailFrom;
        this.siteEmailTo = siteEmailTo;
        this.siteLanguage = siteLanguage;
        this.siteVersionInfo = siteVersionInfo;
        this.siteIntro = siteIntro;
        this.riskNotice = riskNotice;
        this.companyInfo = companyInfo;
        this.certImg1 = certImg1;
        this.certImg2 = certImg2;
        this.regAgree = regAgree;
        this.tradeAgree = tradeAgree;
        this.siteColor = siteColor;
        this.tradeAgreeText = tradeAgreeText;
        this.regAgreeText = regAgreeText;
        this.onlineService = onlineService;
        this.tradeAgreeTitle = tradeAgreeTitle;
        this.smsDisplay = smsDisplay;
    }


    public Integer getId() {
        return this.id;
    }


    public String getSiteName() {
        return this.siteName;
    }

    public String getSiteHost() {
        return this.siteHost;
    }


    public String getSiteKeywords() {
        return this.siteKeywords;
    }


    public String getSiteDescription() {
        return this.siteDescription;
    }


    public String getSiteLogo() {
        return this.siteLogo;
    }


    public String getSiteLogoSm() {
        return this.siteLogoSm;
    }


    public String getSiteQq() {
        return this.siteQq;
    }


    public String getSitePhone() {
        return this.sitePhone;
    }


    public String getSiteAndroidImg() {
        return this.siteAndroidImg;
    }


    public String getSiteAndroidUrl() {
        return this.siteAndroidUrl;
    }

    public String getSiteIosImg() {
        return this.siteIosImg;
    }


    public String getSiteIosUrl() {
        return this.siteIosUrl;
    }


    public String getSiteEmailFrom() {
        return this.siteEmailFrom;
    }


    public String getSiteEmailTo() {
        return this.siteEmailTo;
    }


    public String getSiteLanguage() {
        return this.siteLanguage;
    }


    public String getSiteVersionInfo() {
        return this.siteVersionInfo;
    }


    public String getSiteIntro() {
        return this.siteIntro;
    }


    public String getRiskNotice() {
        return this.riskNotice;
    }


    public String getCompanyInfo() {
        return this.companyInfo;
    }


    public String getCertImg1() {
        return this.certImg1;
    }


    public String getCertImg2() {
        return this.certImg2;
    }


    public String getRegAgree() {
        return this.regAgree;
    }


    public String getTradeAgree() {
        return this.tradeAgree;
    }


    public String getSiteColor() {
        return this.siteColor;
    }

    public String getRegAgreeText() {
        return regAgreeText;
    }

    public void setRegAgreeText(String regAgreeText) {
        this.regAgreeText = regAgreeText;
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public String getOnlineService() {
        return onlineService;
    }

    public void setOnlineService(String onlineService) {
        this.onlineService = onlineService;
    }


    public Boolean getSmsDisplay() {
        return smsDisplay;
    }

    public void setSmsDisplay(Boolean smsDisplay) {
        this.smsDisplay = smsDisplay;
    }

    public String getTradeAgreeTitle() {
        return tradeAgreeTitle;
    }

    public void setTradeAgreeTitle(String tradeAgreeTitle) {
        this.tradeAgreeTitle = tradeAgreeTitle;
    }
}

