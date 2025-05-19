package com.xc.pojo;

import java.beans.ConstructorProperties;
import java.math.BigDecimal;

public class SiteSetting {
    private Integer id;
    private BigDecimal buyFee;
    private BigDecimal sellFee;
    private BigDecimal stayFee;
    private BigDecimal dutyFee;
    private Integer stayMaxDays;
    private Integer buyMinAmt;
    private Integer chargeMinAmt;
    private Integer buyMinNum;

    private BigDecimal forceStopFee;

    private BigDecimal buyMaxAmtPercent;

    private BigDecimal forceStopPercent;

    private BigDecimal hightAndLow;

    private Integer withMinAmt;

    private BigDecimal creaseMaxPercent;

    private Integer buyMaxNum;


    public void setId(Integer id) {
        this.id = id;
    }

    private Integer withTimeBegin;
    private Integer withTimeEnd;
    private String transAmBegin;
    private String transAmEnd;
    private String transPmBegin;
    private String transPmEnd;
    private Integer withFeeSingle;
    private BigDecimal withFeePercent;
    private String siteLever;
    private Integer buySameTimes;
    private Integer buySameNums;
    private Integer buyNumTimes;
    private Integer buyNumLots;
    private Integer cantSellTimes;
    private BigDecimal kcCreaseMaxPercent;
    private Integer stockDays;
    private BigDecimal stockRate;
    /*强平提醒比例*/
    private BigDecimal forceStopRemindRatio;
    /*创业涨跌比例*/
    private BigDecimal cyCreaseMaxPercent;

    public void setBuyFee(BigDecimal buyFee) {
        this.buyFee = buyFee;
    }

    public void setSellFee(BigDecimal sellFee) {
        this.sellFee = sellFee;
    }

    public void setStayFee(BigDecimal stayFee) {
        this.stayFee = stayFee;
    }

    public void setDutyFee(BigDecimal dutyFee) {
        this.dutyFee = dutyFee;
    }

    public void setStayMaxDays(Integer stayMaxDays) {
        this.stayMaxDays = stayMaxDays;
    }

    public void setBuyMinAmt(Integer buyMinAmt) {
        this.buyMinAmt = buyMinAmt;
    }

    public void setChargeMinAmt(Integer chargeMinAmt) {
        this.chargeMinAmt = chargeMinAmt;
    }

    public void setBuyMinNum(Integer buyMinNum) {
        this.buyMinNum = buyMinNum;
    }

    public void setForceStopFee(BigDecimal forceStopFee) {
        this.forceStopFee = forceStopFee;
    }

    public void setBuyMaxAmtPercent(BigDecimal buyMaxAmtPercent) {
        this.buyMaxAmtPercent = buyMaxAmtPercent;
    }

    public void setForceStopPercent(BigDecimal forceStopPercent) {
        this.forceStopPercent = forceStopPercent;
    }

    public void setHightAndLow(BigDecimal hightAndLow) {
        this.hightAndLow = hightAndLow;
    }

    public void setWithMinAmt(Integer withMinAmt) {
        this.withMinAmt = withMinAmt;
    }

    public void setCreaseMaxPercent(BigDecimal creaseMaxPercent) {
        this.creaseMaxPercent = creaseMaxPercent;
    }

    public void setBuyMaxNum(Integer buyMaxNum) {
        this.buyMaxNum = buyMaxNum;
    }

    public void setWithTimeBegin(Integer withTimeBegin) {
        this.withTimeBegin = withTimeBegin;
    }

    public void setWithTimeEnd(Integer withTimeEnd) {
        this.withTimeEnd = withTimeEnd;
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

    public void setWithFeeSingle(Integer withFeeSingle) {
        this.withFeeSingle = withFeeSingle;
    }

    public void setWithFeePercent(BigDecimal withFeePercent) {
        this.withFeePercent = withFeePercent;
    }

    public void setSiteLever(String siteLever) {
        this.siteLever = siteLever;
    }

    public void setBuySameTimes(Integer buySameTimes) {
        this.buySameTimes = buySameTimes;
    }

    public void setBuySameNums(Integer buySameNums) {
        this.buySameNums = buySameNums;
    }

    public void setBuyNumTimes(Integer buyNumTimes) {
        this.buyNumTimes = buyNumTimes;
    }

    public void setBuyNumLots(Integer buyNumLots) {
        this.buyNumLots = buyNumLots;
    }

    public void setCantSellTimes(Integer cantSellTimes) {
        this.cantSellTimes = cantSellTimes;
    }

    public void setKcCreaseMaxPercent(BigDecimal kcCreaseMaxPercent) {
        this.kcCreaseMaxPercent = kcCreaseMaxPercent;
    }

    public void setStockDays(Integer stockDays) {
        this.stockDays = stockDays;
    }

    public void setStockRate(BigDecimal stockRate) {
        this.stockRate = stockRate;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof SiteSetting)) return false;
        SiteSetting other = (SiteSetting) o;
        if (!other.canEqual(this)) return false;
        Object this$id = getId(), other$id = other.getId();
        if ((this$id == null) ? (other$id != null) : !this$id.equals(other$id)) return false;
        Object this$buyFee = getBuyFee(), other$buyFee = other.getBuyFee();
        if ((this$buyFee == null) ? (other$buyFee != null) : !this$buyFee.equals(other$buyFee)) return false;
        Object this$sellFee = getSellFee(), other$sellFee = other.getSellFee();
        if ((this$sellFee == null) ? (other$sellFee != null) : !this$sellFee.equals(other$sellFee)) return false;
        Object this$stayFee = getStayFee(), other$stayFee = other.getStayFee();
        if ((this$stayFee == null) ? (other$stayFee != null) : !this$stayFee.equals(other$stayFee)) return false;
        Object this$dutyFee = getDutyFee(), other$dutyFee = other.getDutyFee();
        if ((this$dutyFee == null) ? (other$dutyFee != null) : !this$dutyFee.equals(other$dutyFee)) return false;
        Object this$stayMaxDays = getStayMaxDays(), other$stayMaxDays = other.getStayMaxDays();
        if ((this$stayMaxDays == null) ? (other$stayMaxDays != null) : !this$stayMaxDays.equals(other$stayMaxDays))
            return false;
        Object this$buyMinAmt = getBuyMinAmt(), other$buyMinAmt = other.getBuyMinAmt();
        if ((this$buyMinAmt == null) ? (other$buyMinAmt != null) : !this$buyMinAmt.equals(other$buyMinAmt))
            return false;
        Object this$chargeMinAmt = getChargeMinAmt(), other$chargeMinAmt = other.getChargeMinAmt();
        if ((this$chargeMinAmt == null) ? (other$chargeMinAmt != null) : !this$chargeMinAmt.equals(other$chargeMinAmt))
            return false;
        Object this$buyMinNum = getBuyMinNum(), other$buyMinNum = other.getBuyMinNum();
        if ((this$buyMinNum == null) ? (other$buyMinNum != null) : !this$buyMinNum.equals(other$buyMinNum))
            return false;
        Object this$forceStopFee = getForceStopFee(), other$forceStopFee = other.getForceStopFee();
        if ((this$forceStopFee == null) ? (other$forceStopFee != null) : !this$forceStopFee.equals(other$forceStopFee))
            return false;
        Object this$buyMaxAmtPercent = getBuyMaxAmtPercent(), other$buyMaxAmtPercent = other.getBuyMaxAmtPercent();
        if ((this$buyMaxAmtPercent == null) ? (other$buyMaxAmtPercent != null) : !this$buyMaxAmtPercent.equals(other$buyMaxAmtPercent))
            return false;
        Object this$forceStopPercent = getForceStopPercent(), other$forceStopPercent = other.getForceStopPercent();
        if ((this$forceStopPercent == null) ? (other$forceStopPercent != null) : !this$forceStopPercent.equals(other$forceStopPercent))
            return false;
        Object this$hightAndLow = getHightAndLow(), other$hightAndLow = other.getHightAndLow();
        if ((this$hightAndLow == null) ? (other$hightAndLow != null) : !this$hightAndLow.equals(other$hightAndLow))
            return false;
        Object this$withMinAmt = getWithMinAmt(), other$withMinAmt = other.getWithMinAmt();
        if ((this$withMinAmt == null) ? (other$withMinAmt != null) : !this$withMinAmt.equals(other$withMinAmt))
            return false;
        Object this$creaseMaxPercent = getCreaseMaxPercent(), other$creaseMaxPercent = other.getCreaseMaxPercent();
        if ((this$creaseMaxPercent == null) ? (other$creaseMaxPercent != null) : !this$creaseMaxPercent.equals(other$creaseMaxPercent))
            return false;
        Object this$buyMaxNum = getBuyMaxNum(), other$buyMaxNum = other.getBuyMaxNum();
        if ((this$buyMaxNum == null) ? (other$buyMaxNum != null) : !this$buyMaxNum.equals(other$buyMaxNum))
            return false;
        Object this$withTimeBegin = getWithTimeBegin(), other$withTimeBegin = other.getWithTimeBegin();
        if ((this$withTimeBegin == null) ? (other$withTimeBegin != null) : !this$withTimeBegin.equals(other$withTimeBegin))
            return false;
        Object this$withTimeEnd = getWithTimeEnd(), other$withTimeEnd = other.getWithTimeEnd();
        if ((this$withTimeEnd == null) ? (other$withTimeEnd != null) : !this$withTimeEnd.equals(other$withTimeEnd))
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
        Object this$withFeeSingle = getWithFeeSingle(), other$withFeeSingle = other.getWithFeeSingle();
        if ((this$withFeeSingle == null) ? (other$withFeeSingle != null) : !this$withFeeSingle.equals(other$withFeeSingle))
            return false;
        Object this$withFeePercent = getWithFeePercent(), other$withFeePercent = other.getWithFeePercent();
        if ((this$withFeePercent == null) ? (other$withFeePercent != null) : !this$withFeePercent.equals(other$withFeePercent))
            return false;
        Object this$siteLever = getSiteLever(), other$siteLever = other.getSiteLever();
        if ((this$siteLever == null) ? (other$siteLever != null) : !this$siteLever.equals(other$siteLever))
            return false;
        Object this$buySameTimes = getBuySameTimes(), other$buySameTimes = other.getBuySameTimes();
        if ((this$buySameTimes == null) ? (other$buySameTimes != null) : !this$buySameTimes.equals(other$buySameTimes))
            return false;
        Object this$buySameNums = getBuySameNums(), other$buySameNums = other.getBuySameNums();
        if ((this$buySameNums == null) ? (other$buySameNums != null) : !this$buySameNums.equals(other$buySameNums))
            return false;
        Object this$buyNumTimes = getBuyNumTimes(), other$buyNumTimes = other.getBuyNumTimes();
        if ((this$buyNumTimes == null) ? (other$buyNumTimes != null) : !this$buyNumTimes.equals(other$buyNumTimes))
            return false;
        Object this$buyNumLots = getBuyNumLots(), other$buyNumLots = other.getBuyNumLots();
        if ((this$buyNumLots == null) ? (other$buyNumLots != null) : !this$buyNumLots.equals(other$buyNumLots))
            return false;
        Object this$cantSellTimes = getCantSellTimes(), other$cantSellTimes = other.getCantSellTimes();
        if ((this$cantSellTimes == null) ? (other$cantSellTimes != null) : !this$cantSellTimes.equals(other$cantSellTimes))
            return false;
        Object this$kcCreaseMaxPercent = getKcCreaseMaxPercent(), other$kcCreaseMaxPercent = other.getKcCreaseMaxPercent();
        if ((this$kcCreaseMaxPercent == null) ? (other$kcCreaseMaxPercent != null) : !this$kcCreaseMaxPercent.equals(other$kcCreaseMaxPercent))
            return false;
        Object this$stockDays = getStockDays(), other$stockDays = other.getStockDays();
        if ((this$stockDays == null) ? (other$stockDays != null) : !this$stockDays.equals(other$stockDays))
            return false;
        Object this$stockRate = getStockRate(), other$stockRate = other.getStockRate();
        return !((this$stockRate == null) ? (other$stockRate != null) : !this$stockRate.equals(other$stockRate));
    }

    protected boolean canEqual(Object other) {
        return other instanceof SiteSetting;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $id = getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        Object $buyFee = getBuyFee();
        result = result * 59 + (($buyFee == null) ? 43 : $buyFee.hashCode());
        Object $sellFee = getSellFee();
        result = result * 59 + (($sellFee == null) ? 43 : $sellFee.hashCode());
        Object $stayFee = getStayFee();
        result = result * 59 + (($stayFee == null) ? 43 : $stayFee.hashCode());
        Object $dutyFee = getDutyFee();
        result = result * 59 + (($dutyFee == null) ? 43 : $dutyFee.hashCode());
        Object $stayMaxDays = getStayMaxDays();
        result = result * 59 + (($stayMaxDays == null) ? 43 : $stayMaxDays.hashCode());
        Object $buyMinAmt = getBuyMinAmt();
        result = result * 59 + (($buyMinAmt == null) ? 43 : $buyMinAmt.hashCode());
        Object $chargeMinAmt = getChargeMinAmt();
        result = result * 59 + (($chargeMinAmt == null) ? 43 : $chargeMinAmt.hashCode());
        Object $buyMinNum = getBuyMinNum();
        result = result * 59 + (($buyMinNum == null) ? 43 : $buyMinNum.hashCode());
        Object $forceStopFee = getForceStopFee();
        result = result * 59 + (($forceStopFee == null) ? 43 : $forceStopFee.hashCode());
        Object $buyMaxAmtPercent = getBuyMaxAmtPercent();
        result = result * 59 + (($buyMaxAmtPercent == null) ? 43 : $buyMaxAmtPercent.hashCode());
        Object $forceStopPercent = getForceStopPercent();
        result = result * 59 + (($forceStopPercent == null) ? 43 : $forceStopPercent.hashCode());
        Object $hightAndLow = getHightAndLow();
        result = result * 59 + (($hightAndLow == null) ? 43 : $hightAndLow.hashCode());
        Object $withMinAmt = getWithMinAmt();
        result = result * 59 + (($withMinAmt == null) ? 43 : $withMinAmt.hashCode());
        Object $creaseMaxPercent = getCreaseMaxPercent();
        result = result * 59 + (($creaseMaxPercent == null) ? 43 : $creaseMaxPercent.hashCode());
        Object $buyMaxNum = getBuyMaxNum();
        result = result * 59 + (($buyMaxNum == null) ? 43 : $buyMaxNum.hashCode());
        Object $withTimeBegin = getWithTimeBegin();
        result = result * 59 + (($withTimeBegin == null) ? 43 : $withTimeBegin.hashCode());
        Object $withTimeEnd = getWithTimeEnd();
        result = result * 59 + (($withTimeEnd == null) ? 43 : $withTimeEnd.hashCode());
        Object $transAmBegin = getTransAmBegin();
        result = result * 59 + (($transAmBegin == null) ? 43 : $transAmBegin.hashCode());
        Object $transAmEnd = getTransAmEnd();
        result = result * 59 + (($transAmEnd == null) ? 43 : $transAmEnd.hashCode());
        Object $transPmBegin = getTransPmBegin();
        result = result * 59 + (($transPmBegin == null) ? 43 : $transPmBegin.hashCode());
        Object $transPmEnd = getTransPmEnd();
        result = result * 59 + (($transPmEnd == null) ? 43 : $transPmEnd.hashCode());
        Object $withFeeSingle = getWithFeeSingle();
        result = result * 59 + (($withFeeSingle == null) ? 43 : $withFeeSingle.hashCode());
        Object $withFeePercent = getWithFeePercent();
        result = result * 59 + (($withFeePercent == null) ? 43 : $withFeePercent.hashCode());
        Object $siteLever = getSiteLever();
        result = result * 59 + (($siteLever == null) ? 43 : $siteLever.hashCode());
        Object $buySameTimes = getBuySameTimes();
        result = result * 59 + (($buySameTimes == null) ? 43 : $buySameTimes.hashCode());
        Object $buySameNums = getBuySameNums();
        result = result * 59 + (($buySameNums == null) ? 43 : $buySameNums.hashCode());
        Object $buyNumTimes = getBuyNumTimes();
        result = result * 59 + (($buyNumTimes == null) ? 43 : $buyNumTimes.hashCode());
        Object $buyNumLots = getBuyNumLots();
        result = result * 59 + (($buyNumLots == null) ? 43 : $buyNumLots.hashCode());
        Object $cantSellTimes = getCantSellTimes();
        result = result * 59 + (($cantSellTimes == null) ? 43 : $cantSellTimes.hashCode());
        Object $kcCreaseMaxPercent = getKcCreaseMaxPercent();
        result = result * 59 + (($kcCreaseMaxPercent == null) ? 43 : $kcCreaseMaxPercent.hashCode());
        Object $stockDays = getStockDays();
        result = result * 59 + (($stockDays == null) ? 43 : $stockDays.hashCode());
        Object $stockRate = getStockRate();
        return result * 59 + (($stockRate == null) ? 43 : $stockRate.hashCode());
    }

    public String toString() {
        return "SiteSetting(id=" + getId() + ", buyFee=" + getBuyFee() + ", sellFee=" + getSellFee() + ", stayFee=" + getStayFee() + ", dutyFee=" + getDutyFee() + ", stayMaxDays=" + getStayMaxDays() + ", buyMinAmt=" + getBuyMinAmt() + ", chargeMinAmt=" + getChargeMinAmt() + ", buyMinNum=" + getBuyMinNum() + ", forceStopFee=" + getForceStopFee() + ", buyMaxAmtPercent=" + getBuyMaxAmtPercent() + ", forceStopPercent=" + getForceStopPercent() + ", hightAndLow=" + getHightAndLow() + ", withMinAmt=" + getWithMinAmt() + ", creaseMaxPercent=" + getCreaseMaxPercent() + ", buyMaxNum=" + getBuyMaxNum() + ", withTimeBegin=" + getWithTimeBegin() + ", withTimeEnd=" + getWithTimeEnd() + ", transAmBegin=" + getTransAmBegin() + ", transAmEnd=" + getTransAmEnd() + ", transPmBegin=" + getTransPmBegin() + ", transPmEnd=" + getTransPmEnd() + ", withFeeSingle=" + getWithFeeSingle() + ", withFeePercent=" + getWithFeePercent() + ", siteLever=" + getSiteLever() + ", buySameTimes=" + getBuySameTimes() + ", buySameNums=" + getBuySameNums() + ", buyNumTimes=" + getBuyNumTimes() + ", buyNumLots=" + getBuyNumLots() + ", cantSellTimes=" + getCantSellTimes() + ", kcCreaseMaxPercent=" + getKcCreaseMaxPercent() + ", stockDays=" + getStockDays() + ", stockRate=" + getStockRate() + ", forceStopRemindRatio=" + getForceStopRemindRatio()+ ", cyCreaseMaxPercent=" + getCyCreaseMaxPercent()+ ")";
    }

    public SiteSetting() {
    }

    @ConstructorProperties({"id", "buyFee", "sellFee", "stayFee", "dutyFee", "stayMaxDays", "buyMinAmt", "chargeMinAmt", "buyMinNum", "forceStopFee", "buyMaxAmtPercent", "forceStopPercent", "hightAndLow", "withMinAmt", "creaseMaxPercent", "buyMaxNum", "withTimeBegin", "withTimeEnd", "transAmBegin", "transAmEnd", "transPmBegin", "transPmEnd", "withFeeSingle", "withFeePercent", "siteLever", "buySameTimes", "buySameNums", "buyNumTimes", "buyNumLots", "cantSellTimes", "kcCreaseMaxPercent", "stockDays", "stockRate","forceStopRemindRatio", "cyCreaseMaxPercent"})

    public SiteSetting(Integer id, BigDecimal buyFee, BigDecimal sellFee, BigDecimal stayFee, BigDecimal dutyFee, Integer stayMaxDays, Integer buyMinAmt, Integer chargeMinAmt, Integer buyMinNum, BigDecimal forceStopFee, BigDecimal buyMaxAmtPercent, BigDecimal forceStopPercent, BigDecimal hightAndLow, Integer withMinAmt, BigDecimal creaseMaxPercent, Integer buyMaxNum, Integer withTimeBegin, Integer withTimeEnd, String transAmBegin, String transAmEnd, String transPmBegin, String transPmEnd, Integer withFeeSingle, BigDecimal withFeePercent, String siteLever, Integer buySameTimes, Integer buySameNums, Integer buyNumTimes, Integer buyNumLots, Integer cantSellTimes, BigDecimal kcCreaseMaxPercent, Integer stockDays, BigDecimal stockRate, BigDecimal forceStopRemindRatio, BigDecimal cyCreaseMaxPercent) {
        this.id = id;
        this.buyFee = buyFee;
        this.sellFee = sellFee;
        this.stayFee = stayFee;
        this.dutyFee = dutyFee;
        this.stayMaxDays = stayMaxDays;
        this.buyMinAmt = buyMinAmt;
        this.chargeMinAmt = chargeMinAmt;
        this.buyMinNum = buyMinNum;
        this.forceStopFee = forceStopFee;
        this.buyMaxAmtPercent = buyMaxAmtPercent;
        this.forceStopPercent = forceStopPercent;
        this.hightAndLow = hightAndLow;
        this.withMinAmt = withMinAmt;
        this.creaseMaxPercent = creaseMaxPercent;
        this.buyMaxNum = buyMaxNum;
        this.withTimeBegin = withTimeBegin;
        this.withTimeEnd = withTimeEnd;
        this.transAmBegin = transAmBegin;
        this.transAmEnd = transAmEnd;
        this.transPmBegin = transPmBegin;
        this.transPmEnd = transPmEnd;
        this.withFeeSingle = withFeeSingle;
        this.withFeePercent = withFeePercent;
        this.siteLever = siteLever;
        this.buySameTimes = buySameTimes;
        this.buySameNums = buySameNums;
        this.buyNumTimes = buyNumTimes;
        this.buyNumLots = buyNumLots;
        this.cantSellTimes = cantSellTimes;
        this.kcCreaseMaxPercent = kcCreaseMaxPercent;
        this.stockDays = stockDays;
        this.stockRate = stockRate;
        this.forceStopRemindRatio= forceStopRemindRatio;
        this.cyCreaseMaxPercent = cyCreaseMaxPercent;
    }


    public Integer getId() {
        return this.id;
    }


    public BigDecimal getBuyFee() {
        return this.buyFee;
    }


    public BigDecimal getSellFee() {
        return this.sellFee;
    }


    public BigDecimal getStayFee() {
        return this.stayFee;
    }


    public BigDecimal getDutyFee() {
        return this.dutyFee;
    }


    public Integer getStayMaxDays() {
        return this.stayMaxDays;
    }


    public Integer getBuyMinAmt() {
        return this.buyMinAmt;
    }


    public Integer getChargeMinAmt() {
        return this.chargeMinAmt;
    }


    public Integer getBuyMinNum() {
        return this.buyMinNum;
    }


    public BigDecimal getForceStopFee() {
        return this.forceStopFee;
    }


    public BigDecimal getBuyMaxAmtPercent() {
        return this.buyMaxAmtPercent;
    }


    public BigDecimal getForceStopPercent() {
        return this.forceStopPercent;
    }


    public BigDecimal getHightAndLow() {
        return this.hightAndLow;
    }


    public Integer getWithMinAmt() {
        return this.withMinAmt;
    }


    public BigDecimal getCreaseMaxPercent() {
        return this.creaseMaxPercent;
    }


    public Integer getBuyMaxNum() {
        return this.buyMaxNum;
    }


    public Integer getWithTimeBegin() {
        return this.withTimeBegin;
    }


    public Integer getWithTimeEnd() {
        return this.withTimeEnd;
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


    public Integer getWithFeeSingle() {
        return this.withFeeSingle;
    }


    public BigDecimal getWithFeePercent() {
        return this.withFeePercent;
    }


    public String getSiteLever() {
        return this.siteLever;
    }


    public Integer getBuySameTimes() {
        return this.buySameTimes;
    }

    public Integer getBuySameNums() {
        return this.buySameNums;
    }

    public Integer getBuyNumTimes() {
        return this.buyNumTimes;
    }

    public Integer getBuyNumLots() {
        return this.buyNumLots;
    }

    public Integer getCantSellTimes() {
        return this.cantSellTimes;
    }


    public BigDecimal getKcCreaseMaxPercent() {
        return this.kcCreaseMaxPercent;
    }


    public Integer getStockDays() {
        return this.stockDays;
    }


    public BigDecimal getStockRate() {
        return this.stockRate;
    }

    public BigDecimal getForceStopRemindRatio() {
        return forceStopRemindRatio;
    }

    public void setForceStopRemindRatio(BigDecimal forceStopRemindRatio) {
        this.forceStopRemindRatio = forceStopRemindRatio;
    }

    public BigDecimal getCyCreaseMaxPercent() {
        return cyCreaseMaxPercent;
    }

    public void setCyCreaseMaxPercent(BigDecimal cyCreaseMaxPercent) {
        this.cyCreaseMaxPercent = cyCreaseMaxPercent;
    }
}
