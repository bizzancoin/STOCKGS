package com.xc.pojo;

import java.math.BigDecimal;


public class SiteFuturesSetting {
    private Integer id;
    private BigDecimal buyMaxPercent;
    private BigDecimal forceSellPercent;
    private Integer buySameTimes;
    private Integer buySameNums;
    private Integer buyNumTimes;
    private Integer buyNumLots;
    /*强平提醒比例*/
    private BigDecimal forceStopRemindRatio;

    public SiteFuturesSetting(Integer id, BigDecimal buyMaxPercent, BigDecimal forceSellPercent, Integer buySameTimes, Integer buySameNums, Integer buyNumTimes, Integer buyNumLots, BigDecimal forceStopRemindRatio) {
        this.id = id;
        this.buyMaxPercent = buyMaxPercent;
        this.forceSellPercent = forceSellPercent;
        this.buySameTimes = buySameTimes;
        this.buySameNums = buySameNums;
        this.buyNumTimes = buyNumTimes;
        this.buyNumLots = buyNumLots;
        this.forceStopRemindRatio = forceStopRemindRatio;
    }


    public SiteFuturesSetting() {
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

    public Integer getBuySameTimes() {
        return this.buySameTimes;
    }

    public void setBuySameTimes(Integer buySameTimes) {
        this.buySameTimes = buySameTimes;
    }

    public Integer getBuySameNums() {
        return this.buySameNums;
    }

    public void setBuySameNums(Integer buySameNums) {
        this.buySameNums = buySameNums;
    }

    public Integer getBuyNumTimes() {
        return this.buyNumTimes;
    }

    public void setBuyNumTimes(Integer buyNumTimes) {
        this.buyNumTimes = buyNumTimes;
    }

    public Integer getBuyNumLots() {
        return this.buyNumLots;
    }

    public void setBuyNumLots(Integer buyNumLots) {
        this.buyNumLots = buyNumLots;
    }

    public BigDecimal getForceStopRemindRatio() {
        return forceStopRemindRatio;
    }

    public void setForceStopRemindRatio(BigDecimal forceStopRemindRatio) {
        this.forceStopRemindRatio = forceStopRemindRatio;
    }
}
