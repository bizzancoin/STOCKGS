/*     */ package com.xc.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

/*     */ public class FundsSetting implements Serializable { private static final long serialVersionUID = 1L; private Integer id; private Integer marginMin; private Integer marginMax;
    /*     */   private Integer interestFreeLever;
    /*     */   private Integer interestFreeDays;
    /*     */   private BigDecimal interestFreeProfit;
    /*     */   private BigDecimal interestFreeWarning;
    /*     */   private BigDecimal interestFreeUnwind;
    /*     */   private BigDecimal daysWarning;
    /*     */   private BigDecimal daysUnwind;
    /*     */
    /*  11 */   public void setId(Integer id) { this.id = id; } private BigDecimal weeksWarning; private BigDecimal weeksUnwind; private BigDecimal monthWarning; private BigDecimal monthUnwind; private String daysUsePeriod; private String weeksUsePeriod; private String monthUsePeriod; private BigDecimal earlyTerminationInterest; private BigDecimal tradingCommissionRate; private Integer stampDutyRate; private BigDecimal profitSharingRatio; public void setMarginMin(Integer marginMin) { this.marginMin = marginMin; } public void setMarginMax(Integer marginMax) { this.marginMax = marginMax; } public void setInterestFreeLever(Integer interestFreeLever) { this.interestFreeLever = interestFreeLever; } public void setInterestFreeDays(Integer interestFreeDays) { this.interestFreeDays = interestFreeDays; } public void setInterestFreeProfit(BigDecimal interestFreeProfit) { this.interestFreeProfit = interestFreeProfit; } public void setInterestFreeWarning(BigDecimal interestFreeWarning) { this.interestFreeWarning = interestFreeWarning; } public void setInterestFreeUnwind(BigDecimal interestFreeUnwind) { this.interestFreeUnwind = interestFreeUnwind; } public void setDaysWarning(BigDecimal daysWarning) { this.daysWarning = daysWarning; } public void setDaysUnwind(BigDecimal daysUnwind) { this.daysUnwind = daysUnwind; } public void setWeeksWarning(BigDecimal weeksWarning) { this.weeksWarning = weeksWarning; } public void setWeeksUnwind(BigDecimal weeksUnwind) { this.weeksUnwind = weeksUnwind; } public void setMonthWarning(BigDecimal monthWarning) { this.monthWarning = monthWarning; } public void setMonthUnwind(BigDecimal monthUnwind) { this.monthUnwind = monthUnwind; } public void setDaysUsePeriod(String daysUsePeriod) { this.daysUsePeriod = daysUsePeriod; } public void setWeeksUsePeriod(String weeksUsePeriod) { this.weeksUsePeriod = weeksUsePeriod; } public void setMonthUsePeriod(String monthUsePeriod) { this.monthUsePeriod = monthUsePeriod; } public void setEarlyTerminationInterest(BigDecimal earlyTerminationInterest) { this.earlyTerminationInterest = earlyTerminationInterest; } public void setTradingCommissionRate(BigDecimal tradingCommissionRate) { this.tradingCommissionRate = tradingCommissionRate; } public void setStampDutyRate(Integer stampDutyRate) { this.stampDutyRate = stampDutyRate; } public void setProfitSharingRatio(BigDecimal profitSharingRatio) { this.profitSharingRatio = profitSharingRatio; } public boolean equals(Object o) { if (o == this) return true;  if (!(o instanceof FundsSetting)) return false;  FundsSetting other = (FundsSetting)o; if (!other.canEqual(this)) return false;  Object this$id = getId(), other$id = other.getId(); if ((this$id == null) ? (other$id != null) : !this$id.equals(other$id)) return false;  Object this$marginMin = getMarginMin(), other$marginMin = other.getMarginMin(); if ((this$marginMin == null) ? (other$marginMin != null) : !this$marginMin.equals(other$marginMin)) return false;  Object this$marginMax = getMarginMax(), other$marginMax = other.getMarginMax(); if ((this$marginMax == null) ? (other$marginMax != null) : !this$marginMax.equals(other$marginMax)) return false;  Object this$interestFreeLever = getInterestFreeLever(), other$interestFreeLever = other.getInterestFreeLever(); if ((this$interestFreeLever == null) ? (other$interestFreeLever != null) : !this$interestFreeLever.equals(other$interestFreeLever)) return false;  Object this$interestFreeDays = getInterestFreeDays(), other$interestFreeDays = other.getInterestFreeDays(); if ((this$interestFreeDays == null) ? (other$interestFreeDays != null) : !this$interestFreeDays.equals(other$interestFreeDays)) return false;  Object this$interestFreeProfit = getInterestFreeProfit(), other$interestFreeProfit = other.getInterestFreeProfit(); if ((this$interestFreeProfit == null) ? (other$interestFreeProfit != null) : !this$interestFreeProfit.equals(other$interestFreeProfit)) return false;  Object this$interestFreeWarning = getInterestFreeWarning(), other$interestFreeWarning = other.getInterestFreeWarning(); if ((this$interestFreeWarning == null) ? (other$interestFreeWarning != null) : !this$interestFreeWarning.equals(other$interestFreeWarning)) return false;  Object this$interestFreeUnwind = getInterestFreeUnwind(), other$interestFreeUnwind = other.getInterestFreeUnwind(); if ((this$interestFreeUnwind == null) ? (other$interestFreeUnwind != null) : !this$interestFreeUnwind.equals(other$interestFreeUnwind)) return false;  Object this$daysWarning = getDaysWarning(), other$daysWarning = other.getDaysWarning(); if ((this$daysWarning == null) ? (other$daysWarning != null) : !this$daysWarning.equals(other$daysWarning)) return false;  Object this$daysUnwind = getDaysUnwind(), other$daysUnwind = other.getDaysUnwind(); if ((this$daysUnwind == null) ? (other$daysUnwind != null) : !this$daysUnwind.equals(other$daysUnwind)) return false;  Object this$weeksWarning = getWeeksWarning(), other$weeksWarning = other.getWeeksWarning(); if ((this$weeksWarning == null) ? (other$weeksWarning != null) : !this$weeksWarning.equals(other$weeksWarning)) return false;  Object this$weeksUnwind = getWeeksUnwind(), other$weeksUnwind = other.getWeeksUnwind(); if ((this$weeksUnwind == null) ? (other$weeksUnwind != null) : !this$weeksUnwind.equals(other$weeksUnwind)) return false;  Object this$monthWarning = getMonthWarning(), other$monthWarning = other.getMonthWarning(); if ((this$monthWarning == null) ? (other$monthWarning != null) : !this$monthWarning.equals(other$monthWarning)) return false;  Object this$monthUnwind = getMonthUnwind(), other$monthUnwind = other.getMonthUnwind(); if ((this$monthUnwind == null) ? (other$monthUnwind != null) : !this$monthUnwind.equals(other$monthUnwind)) return false;  Object this$daysUsePeriod = getDaysUsePeriod(), other$daysUsePeriod = other.getDaysUsePeriod(); if ((this$daysUsePeriod == null) ? (other$daysUsePeriod != null) : !this$daysUsePeriod.equals(other$daysUsePeriod)) return false;  Object this$weeksUsePeriod = getWeeksUsePeriod(), other$weeksUsePeriod = other.getWeeksUsePeriod(); if ((this$weeksUsePeriod == null) ? (other$weeksUsePeriod != null) : !this$weeksUsePeriod.equals(other$weeksUsePeriod)) return false;  Object this$monthUsePeriod = getMonthUsePeriod(), other$monthUsePeriod = other.getMonthUsePeriod(); if ((this$monthUsePeriod == null) ? (other$monthUsePeriod != null) : !this$monthUsePeriod.equals(other$monthUsePeriod)) return false;  Object this$earlyTerminationInterest = getEarlyTerminationInterest(), other$earlyTerminationInterest = other.getEarlyTerminationInterest(); if ((this$earlyTerminationInterest == null) ? (other$earlyTerminationInterest != null) : !this$earlyTerminationInterest.equals(other$earlyTerminationInterest)) return false;  Object this$tradingCommissionRate = getTradingCommissionRate(), other$tradingCommissionRate = other.getTradingCommissionRate(); if ((this$tradingCommissionRate == null) ? (other$tradingCommissionRate != null) : !this$tradingCommissionRate.equals(other$tradingCommissionRate)) return false;  Object this$stampDutyRate = getStampDutyRate(), other$stampDutyRate = other.getStampDutyRate(); if ((this$stampDutyRate == null) ? (other$stampDutyRate != null) : !this$stampDutyRate.equals(other$stampDutyRate)) return false;  Object this$profitSharingRatio = getProfitSharingRatio(), other$profitSharingRatio = other.getProfitSharingRatio(); return !((this$profitSharingRatio == null) ? (other$profitSharingRatio != null) : !this$profitSharingRatio.equals(other$profitSharingRatio)); } protected boolean canEqual(Object other) { return other instanceof FundsSetting; } public int hashCode() { int PRIME = 59;
        int result = 1;
        Object $id = getId(); result = result * 59 + (($id == null) ? 43 : $id.hashCode()); Object $marginMin = getMarginMin(); result = result * 59 + (($marginMin == null) ? 43 : $marginMin.hashCode()); Object $marginMax = getMarginMax(); result = result * 59 + (($marginMax == null) ? 43 : $marginMax.hashCode()); Object $interestFreeLever = getInterestFreeLever(); result = result * 59 + (($interestFreeLever == null) ? 43 : $interestFreeLever.hashCode()); Object $interestFreeDays = getInterestFreeDays(); result = result * 59 + (($interestFreeDays == null) ? 43 : $interestFreeDays.hashCode()); Object $interestFreeProfit = getInterestFreeProfit(); result = result * 59 + (($interestFreeProfit == null) ? 43 : $interestFreeProfit.hashCode()); Object $interestFreeWarning = getInterestFreeWarning(); result = result * 59 + (($interestFreeWarning == null) ? 43 : $interestFreeWarning.hashCode()); Object $interestFreeUnwind = getInterestFreeUnwind(); result = result * 59 + (($interestFreeUnwind == null) ? 43 : $interestFreeUnwind.hashCode()); Object $daysWarning = getDaysWarning(); result = result * 59 + (($daysWarning == null) ? 43 : $daysWarning.hashCode()); Object $daysUnwind = getDaysUnwind(); result = result * 59 + (($daysUnwind == null) ? 43 : $daysUnwind.hashCode()); Object $weeksWarning = getWeeksWarning(); result = result * 59 + (($weeksWarning == null) ? 43 : $weeksWarning.hashCode()); Object $weeksUnwind = getWeeksUnwind(); result = result * 59 + (($weeksUnwind == null) ? 43 : $weeksUnwind.hashCode()); Object $monthWarning = getMonthWarning(); result = result * 59 + (($monthWarning == null) ? 43 : $monthWarning.hashCode()); Object $monthUnwind = getMonthUnwind(); result = result * 59 + (($monthUnwind == null) ? 43 : $monthUnwind.hashCode()); Object $daysUsePeriod = getDaysUsePeriod(); result = result * 59 + (($daysUsePeriod == null) ? 43 : $daysUsePeriod.hashCode()); Object $weeksUsePeriod = getWeeksUsePeriod(); result = result * 59 + (($weeksUsePeriod == null) ? 43 : $weeksUsePeriod.hashCode()); Object $monthUsePeriod = getMonthUsePeriod(); result = result * 59 + (($monthUsePeriod == null) ? 43 : $monthUsePeriod.hashCode()); Object $earlyTerminationInterest = getEarlyTerminationInterest(); result = result * 59 + (($earlyTerminationInterest == null) ? 43 : $earlyTerminationInterest.hashCode()); Object $tradingCommissionRate = getTradingCommissionRate(); result = result * 59 + (($tradingCommissionRate == null) ? 43 : $tradingCommissionRate.hashCode()); Object $stampDutyRate = getStampDutyRate(); result = result * 59 + (($stampDutyRate == null) ? 43 : $stampDutyRate.hashCode()); Object $profitSharingRatio = getProfitSharingRatio(); return result * 59 + (($profitSharingRatio == null) ? 43 : $profitSharingRatio.hashCode()); } public String toString() { return "FundsSetting(id=" + getId() + ", marginMin=" + getMarginMin() + ", marginMax=" + getMarginMax() + ", interestFreeLever=" + getInterestFreeLever() + ", interestFreeDays=" + getInterestFreeDays() + ", interestFreeProfit=" + getInterestFreeProfit() + ", interestFreeWarning=" + getInterestFreeWarning() + ", interestFreeUnwind=" + getInterestFreeUnwind() + ", daysWarning=" + getDaysWarning() + ", daysUnwind=" + getDaysUnwind() + ", weeksWarning=" + getWeeksWarning() + ", weeksUnwind=" + getWeeksUnwind() + ", monthWarning=" + getMonthWarning() + ", monthUnwind=" + getMonthUnwind() + ", daysUsePeriod=" + getDaysUsePeriod() + ", weeksUsePeriod=" + getWeeksUsePeriod() + ", monthUsePeriod=" + getMonthUsePeriod() + ", earlyTerminationInterest=" + getEarlyTerminationInterest() + ", tradingCommissionRate=" + getTradingCommissionRate() + ", stampDutyRate=" + getStampDutyRate() + ", profitSharingRatio=" + getProfitSharingRatio() + ")"; }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */   public Integer getId() {
        /*  19 */     return this.id;
        /*     */   }
    /*     */
    /*     */
    /*     */   public Integer getMarginMin() {
        /*  24 */     return this.marginMin;
        /*     */   }
    /*     */
    /*     */
    /*     */   public Integer getMarginMax() {
        /*  29 */     return this.marginMax;
        /*     */   }
    /*     */
    /*     */
    /*     */   public Integer getInterestFreeLever() {
        /*  34 */     return this.interestFreeLever;
        /*     */   }
    /*     */
    /*     */
    /*     */   public Integer getInterestFreeDays() {
        /*  39 */     return this.interestFreeDays;
        /*     */   }
    /*     */
    /*     */
    /*     */   public BigDecimal getInterestFreeProfit() {
        /*  44 */     return this.interestFreeProfit;
        /*     */   }
    /*     */
    /*     */
    /*     */   public BigDecimal getInterestFreeWarning() {
        /*  49 */     return this.interestFreeWarning;
        /*     */   }
    /*     */
    /*     */
    /*     */   public BigDecimal getInterestFreeUnwind() {
        /*  54 */     return this.interestFreeUnwind;
        /*     */   }
    /*     */
    /*     */
    /*     */   public BigDecimal getDaysWarning() {
        /*  59 */     return this.daysWarning;
        /*     */   }
    /*     */
    /*     */
    /*     */   public BigDecimal getDaysUnwind() {
        /*  64 */     return this.daysUnwind;
        /*     */   }
    /*     */
    /*     */
    /*     */   public BigDecimal getWeeksWarning() {
        /*  69 */     return this.weeksWarning;
        /*     */   }
    /*     */
    /*     */
    /*     */   public BigDecimal getWeeksUnwind() {
        /*  74 */     return this.weeksUnwind;
        /*     */   }
    /*     */
    /*     */
    /*     */   public BigDecimal getMonthWarning() {
        /*  79 */     return this.monthWarning;
        /*     */   }
    /*     */
    /*     */
    /*     */   public BigDecimal getMonthUnwind() {
        /*  84 */     return this.monthUnwind;
        /*     */   }
    /*     */
    /*     */
    /*     */   public String getDaysUsePeriod() {
        /*  89 */     return this.daysUsePeriod;
        /*     */   }
    /*     */
    /*     */
    /*     */   public String getWeeksUsePeriod() {
        /*  94 */     return this.weeksUsePeriod;
        /*     */   }
    /*     */
    /*     */
    /*     */   public String getMonthUsePeriod() {
        /*  99 */     return this.monthUsePeriod;
        /*     */   }
    /*     */
    /*     */
    /*     */   public BigDecimal getEarlyTerminationInterest() {
        /* 104 */     return this.earlyTerminationInterest;
        /*     */   }
    /*     */
    /*     */
    /*     */   public BigDecimal getTradingCommissionRate() {
        /* 109 */     return this.tradingCommissionRate;
        /*     */   }
    /*     */
    /*     */
    /*     */   public Integer getStampDutyRate() {
        /* 114 */     return this.stampDutyRate;
        /*     */   }
    /*     */
    /*     */
    /*     */   public BigDecimal getProfitSharingRatio() {
        /* 119 */     return this.profitSharingRatio;
        /*     */   } }


/* Location:              F:\ROOT\WEB-INF\classes\!\com\xc\pojo\FundsSetting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */