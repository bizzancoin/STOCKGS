package com.xc.pojo;

import java.math.BigDecimal;

public class SiteIndexSetting {

    private Integer id;

    private BigDecimal buyMaxPercent;

    private BigDecimal forceSellPercent;

    private String transAmBegin;

    private String transAmEnd;

    private String transPmBegin;

    private String transPmEnd;

    private BigDecimal downLimit;

    private BigDecimal riseLimit;

    /*强平提醒比例*/
    private BigDecimal forceStopRemindRatio;


    public SiteIndexSetting(Integer id, BigDecimal buyMaxPercent, BigDecimal forceSellPercent, String transAmBegin, String transAmEnd, String transPmBegin, String transPmEnd, BigDecimal downLimit, BigDecimal riseLimit, BigDecimal forceStopRemindRatio) {

        this.id = id;

        this.buyMaxPercent = buyMaxPercent;

        this.forceSellPercent = forceSellPercent;

        this.transAmBegin = transAmBegin;

        this.transAmEnd = transAmEnd;

        this.transPmBegin = transPmBegin;

        this.transPmEnd = transPmEnd;

        this.downLimit = downLimit;

        this.riseLimit = riseLimit;

        this.forceStopRemindRatio = forceStopRemindRatio;

    }


    public SiteIndexSetting() {
    }


    public Integer getId() {
        return this.id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public BigDecimal getBuyMaxPercent() {
        return this.buyMaxPercent;
    }


    public void setBuyMaxPercent(BigDecimal buyMaxPercent) {
        this.buyMaxPercent = buyMaxPercent;
    }


    public BigDecimal getForceSellPercent() {
        return this.forceSellPercent;
    }


    public void setForceSellPercent(BigDecimal forceSellPercent) {
        this.forceSellPercent = forceSellPercent;
    }


    public String getTransAmBegin() {
        return this.transAmBegin;
    }


    public void setTransAmBegin(String transAmBegin) {
        this.transAmBegin = (transAmBegin == null) ? null : transAmBegin.trim();
    }


    public String getTransAmEnd() {
        return this.transAmEnd;
    }


    public void setTransAmEnd(String transAmEnd) {
        this.transAmEnd = (transAmEnd == null) ? null : transAmEnd.trim();
    }


    public String getTransPmBegin() {
        return this.transPmBegin;
    }


    public void setTransPmBegin(String transPmBegin) {
        this.transPmBegin = (transPmBegin == null) ? null : transPmBegin.trim();
    }


    public String getTransPmEnd() {
        return this.transPmEnd;
    }


    public void setTransPmEnd(String transPmEnd) {
        this.transPmEnd = (transPmEnd == null) ? null : transPmEnd.trim();
    }


    public BigDecimal getDownLimit() {
        return this.downLimit;
    }


    public void setDownLimit(BigDecimal downLimit) {
        this.downLimit = downLimit;
    }


    public BigDecimal getRiseLimit() {
        return this.riseLimit;
    }


    public void setRiseLimit(BigDecimal riseLimit) {
        this.riseLimit = riseLimit;
    }

    public BigDecimal getForceStopRemindRatio() {
        return forceStopRemindRatio;
    }

    public void setForceStopRemindRatio(BigDecimal forceStopRemindRatio) {
        this.forceStopRemindRatio = forceStopRemindRatio;
    }
}
