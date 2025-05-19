/*     */ package com.xc.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/*     */ public class FundsAppend implements Serializable { private static final long serialVersionUID = 1L; private Integer id; private Integer appendType;
    /*     */   private Integer applyId;
    /*     */   private Integer userId;
    /*     */   private String userName;
    /*     */   private String userPhone;
    /*     */   private Integer fundsType;
    /*     */   private BigDecimal margin;
    /*     */   private BigDecimal fundsAmount;
    /*     */   private Integer tradersCycle;
    /*     */   private Integer lever;
    /*     */   private BigDecimal manageFee;
    /*     */
    /*  14 */   public void setId(Integer id) { this.id = id; } private BigDecimal totalTradingAmount; private Integer appendCycle; private BigDecimal appendServiceFee; private BigDecimal appendMargin; private Integer status; private Date addTime; private Date updateTime; private Date auditTime; private Date endTime; private BigDecimal lineWarning; private BigDecimal lineUnwind; private BigDecimal ratioWarning; private BigDecimal ratioUnwind; private BigDecimal payAmount; public void setAppendType(Integer appendType) { this.appendType = appendType; } public void setApplyId(Integer applyId) { this.applyId = applyId; } public void setUserId(Integer userId) { this.userId = userId; } public void setUserName(String userName) { this.userName = userName; } public void setUserPhone(String userPhone) { this.userPhone = userPhone; } public void setFundsType(Integer fundsType) { this.fundsType = fundsType; } public void setMargin(BigDecimal margin) { this.margin = margin; } public void setFundsAmount(BigDecimal fundsAmount) { this.fundsAmount = fundsAmount; } public void setTradersCycle(Integer tradersCycle) { this.tradersCycle = tradersCycle; } public void setLever(Integer lever) { this.lever = lever; } public void setManageFee(BigDecimal manageFee) { this.manageFee = manageFee; } public void setTotalTradingAmount(BigDecimal totalTradingAmount) { this.totalTradingAmount = totalTradingAmount; } public void setAppendCycle(Integer appendCycle) { this.appendCycle = appendCycle; } public void setAppendServiceFee(BigDecimal appendServiceFee) { this.appendServiceFee = appendServiceFee; } public void setAppendMargin(BigDecimal appendMargin) { this.appendMargin = appendMargin; } public void setStatus(Integer status) { this.status = status; } public void setAddTime(Date addTime) { this.addTime = addTime; } public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; } public void setAuditTime(Date auditTime) { this.auditTime = auditTime; } public void setEndTime(Date endTime) { this.endTime = endTime; } public void setLineWarning(BigDecimal lineWarning) { this.lineWarning = lineWarning; } public void setLineUnwind(BigDecimal lineUnwind) { this.lineUnwind = lineUnwind; } public void setRatioWarning(BigDecimal ratioWarning) { this.ratioWarning = ratioWarning; } public void setRatioUnwind(BigDecimal ratioUnwind) { this.ratioUnwind = ratioUnwind; } public void setPayAmount(BigDecimal payAmount) { this.payAmount = payAmount; } public boolean equals(Object o) { if (o == this) return true;  if (!(o instanceof FundsAppend)) return false;  FundsAppend other = (FundsAppend)o; if (!other.canEqual(this)) return false;  Object this$id = getId(), other$id = other.getId(); if ((this$id == null) ? (other$id != null) : !this$id.equals(other$id)) return false;  Object this$appendType = getAppendType(), other$appendType = other.getAppendType(); if ((this$appendType == null) ? (other$appendType != null) : !this$appendType.equals(other$appendType)) return false;  Object this$applyId = getApplyId(), other$applyId = other.getApplyId(); if ((this$applyId == null) ? (other$applyId != null) : !this$applyId.equals(other$applyId)) return false;  Object this$userId = getUserId(), other$userId = other.getUserId(); if ((this$userId == null) ? (other$userId != null) : !this$userId.equals(other$userId)) return false;  Object this$userName = getUserName(), other$userName = other.getUserName(); if ((this$userName == null) ? (other$userName != null) : !this$userName.equals(other$userName)) return false;  Object this$userPhone = getUserPhone(), other$userPhone = other.getUserPhone(); if ((this$userPhone == null) ? (other$userPhone != null) : !this$userPhone.equals(other$userPhone)) return false;  Object this$fundsType = getFundsType(), other$fundsType = other.getFundsType(); if ((this$fundsType == null) ? (other$fundsType != null) : !this$fundsType.equals(other$fundsType)) return false;  Object this$margin = getMargin(), other$margin = other.getMargin(); if ((this$margin == null) ? (other$margin != null) : !this$margin.equals(other$margin)) return false;  Object this$fundsAmount = getFundsAmount(), other$fundsAmount = other.getFundsAmount(); if ((this$fundsAmount == null) ? (other$fundsAmount != null) : !this$fundsAmount.equals(other$fundsAmount)) return false;  Object this$tradersCycle = getTradersCycle(), other$tradersCycle = other.getTradersCycle(); if ((this$tradersCycle == null) ? (other$tradersCycle != null) : !this$tradersCycle.equals(other$tradersCycle)) return false;  Object this$lever = getLever(), other$lever = other.getLever(); if ((this$lever == null) ? (other$lever != null) : !this$lever.equals(other$lever)) return false;  Object this$manageFee = getManageFee(), other$manageFee = other.getManageFee(); if ((this$manageFee == null) ? (other$manageFee != null) : !this$manageFee.equals(other$manageFee)) return false;  Object this$totalTradingAmount = getTotalTradingAmount(), other$totalTradingAmount = other.getTotalTradingAmount(); if ((this$totalTradingAmount == null) ? (other$totalTradingAmount != null) : !this$totalTradingAmount.equals(other$totalTradingAmount)) return false;  Object this$appendCycle = getAppendCycle(), other$appendCycle = other.getAppendCycle(); if ((this$appendCycle == null) ? (other$appendCycle != null) : !this$appendCycle.equals(other$appendCycle)) return false;  Object this$appendServiceFee = getAppendServiceFee(), other$appendServiceFee = other.getAppendServiceFee(); if ((this$appendServiceFee == null) ? (other$appendServiceFee != null) : !this$appendServiceFee.equals(other$appendServiceFee)) return false;  Object this$appendMargin = getAppendMargin(), other$appendMargin = other.getAppendMargin(); if ((this$appendMargin == null) ? (other$appendMargin != null) : !this$appendMargin.equals(other$appendMargin)) return false;  Object this$status = getStatus(), other$status = other.getStatus(); if ((this$status == null) ? (other$status != null) : !this$status.equals(other$status)) return false;  Object this$addTime = getAddTime(), other$addTime = other.getAddTime(); if ((this$addTime == null) ? (other$addTime != null) : !this$addTime.equals(other$addTime)) return false;  Object this$updateTime = getUpdateTime(), other$updateTime = other.getUpdateTime(); if ((this$updateTime == null) ? (other$updateTime != null) : !this$updateTime.equals(other$updateTime)) return false;  Object this$auditTime = getAuditTime(), other$auditTime = other.getAuditTime(); if ((this$auditTime == null) ? (other$auditTime != null) : !this$auditTime.equals(other$auditTime)) return false;  Object this$endTime = getEndTime(), other$endTime = other.getEndTime(); if ((this$endTime == null) ? (other$endTime != null) : !this$endTime.equals(other$endTime)) return false;  Object this$lineWarning = getLineWarning(), other$lineWarning = other.getLineWarning(); if ((this$lineWarning == null) ? (other$lineWarning != null) : !this$lineWarning.equals(other$lineWarning)) return false;  Object this$lineUnwind = getLineUnwind(), other$lineUnwind = other.getLineUnwind(); if ((this$lineUnwind == null) ? (other$lineUnwind != null) : !this$lineUnwind.equals(other$lineUnwind)) return false;  Object this$ratioWarning = getRatioWarning(), other$ratioWarning = other.getRatioWarning(); if ((this$ratioWarning == null) ? (other$ratioWarning != null) : !this$ratioWarning.equals(other$ratioWarning)) return false;  Object this$ratioUnwind = getRatioUnwind(), other$ratioUnwind = other.getRatioUnwind(); if ((this$ratioUnwind == null) ? (other$ratioUnwind != null) : !this$ratioUnwind.equals(other$ratioUnwind)) return false;  Object this$payAmount = getPayAmount(), other$payAmount = other.getPayAmount(); return !((this$payAmount == null) ? (other$payAmount != null) : !this$payAmount.equals(other$payAmount)); } protected boolean canEqual(Object other) { return other instanceof FundsAppend; } public int hashCode() { int PRIME = 59;
        int result = 1;
        Object $id = getId(); result = result * 59 + (($id == null) ? 43 : $id.hashCode()); Object $appendType = getAppendType(); result = result * 59 + (($appendType == null) ? 43 : $appendType.hashCode()); Object $applyId = getApplyId(); result = result * 59 + (($applyId == null) ? 43 : $applyId.hashCode()); Object $userId = getUserId(); result = result * 59 + (($userId == null) ? 43 : $userId.hashCode()); Object $userName = getUserName(); result = result * 59 + (($userName == null) ? 43 : $userName.hashCode()); Object $userPhone = getUserPhone(); result = result * 59 + (($userPhone == null) ? 43 : $userPhone.hashCode()); Object $fundsType = getFundsType(); result = result * 59 + (($fundsType == null) ? 43 : $fundsType.hashCode()); Object $margin = getMargin(); result = result * 59 + (($margin == null) ? 43 : $margin.hashCode()); Object $fundsAmount = getFundsAmount(); result = result * 59 + (($fundsAmount == null) ? 43 : $fundsAmount.hashCode()); Object $tradersCycle = getTradersCycle(); result = result * 59 + (($tradersCycle == null) ? 43 : $tradersCycle.hashCode()); Object $lever = getLever(); result = result * 59 + (($lever == null) ? 43 : $lever.hashCode()); Object $manageFee = getManageFee(); result = result * 59 + (($manageFee == null) ? 43 : $manageFee.hashCode()); Object $totalTradingAmount = getTotalTradingAmount(); result = result * 59 + (($totalTradingAmount == null) ? 43 : $totalTradingAmount.hashCode()); Object $appendCycle = getAppendCycle(); result = result * 59 + (($appendCycle == null) ? 43 : $appendCycle.hashCode()); Object $appendServiceFee = getAppendServiceFee(); result = result * 59 + (($appendServiceFee == null) ? 43 : $appendServiceFee.hashCode()); Object $appendMargin = getAppendMargin(); result = result * 59 + (($appendMargin == null) ? 43 : $appendMargin.hashCode()); Object $status = getStatus(); result = result * 59 + (($status == null) ? 43 : $status.hashCode()); Object $addTime = getAddTime(); result = result * 59 + (($addTime == null) ? 43 : $addTime.hashCode()); Object $updateTime = getUpdateTime(); result = result * 59 + (($updateTime == null) ? 43 : $updateTime.hashCode()); Object $auditTime = getAuditTime(); result = result * 59 + (($auditTime == null) ? 43 : $auditTime.hashCode()); Object $endTime = getEndTime(); result = result * 59 + (($endTime == null) ? 43 : $endTime.hashCode()); Object $lineWarning = getLineWarning(); result = result * 59 + (($lineWarning == null) ? 43 : $lineWarning.hashCode()); Object $lineUnwind = getLineUnwind(); result = result * 59 + (($lineUnwind == null) ? 43 : $lineUnwind.hashCode()); Object $ratioWarning = getRatioWarning(); result = result * 59 + (($ratioWarning == null) ? 43 : $ratioWarning.hashCode()); Object $ratioUnwind = getRatioUnwind(); result = result * 59 + (($ratioUnwind == null) ? 43 : $ratioUnwind.hashCode()); Object $payAmount = getPayAmount(); return result * 59 + (($payAmount == null) ? 43 : $payAmount.hashCode()); } public String toString() { return "FundsAppend(id=" + getId() + ", appendType=" + getAppendType() + ", applyId=" + getApplyId() + ", userId=" + getUserId() + ", userName=" + getUserName() + ", userPhone=" + getUserPhone() + ", fundsType=" + getFundsType() + ", margin=" + getMargin() + ", fundsAmount=" + getFundsAmount() + ", tradersCycle=" + getTradersCycle() + ", lever=" + getLever() + ", manageFee=" + getManageFee() + ", totalTradingAmount=" + getTotalTradingAmount() + ", appendCycle=" + getAppendCycle() + ", appendServiceFee=" + getAppendServiceFee() + ", appendMargin=" + getAppendMargin() + ", status=" + getStatus() + ", addTime=" + getAddTime() + ", updateTime=" + getUpdateTime() + ", auditTime=" + getAuditTime() + ", endTime=" + getEndTime() + ", lineWarning=" + getLineWarning() + ", lineUnwind=" + getLineUnwind() + ", ratioWarning=" + getRatioWarning() + ", ratioUnwind=" + getRatioUnwind() + ", payAmount=" + getPayAmount() + ")"; }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */   public Integer getId() {
        /*  22 */     return this.id;
        /*     */   }
    /*     */
    /*     */
    /*     */   public Integer getAppendType() {
        /*  27 */     return this.appendType;
        /*     */   }
    /*     */
    /*     */
    /*     */   public Integer getApplyId() {
        /*  32 */     return this.applyId;
        /*     */   }
    /*     */
    /*     */
    /*     */   public Integer getUserId() {
        /*  37 */     return this.userId;
        /*     */   }
    /*     */
    /*     */
    /*     */   public String getUserName() {
        /*  42 */     return this.userName;
        /*     */   }
    /*     */
    /*     */
    /*     */   public String getUserPhone() {
        /*  47 */     return this.userPhone;
        /*     */   }
    /*     */
    /*     */
    /*     */   public Integer getFundsType() {
        /*  52 */     return this.fundsType;
        /*     */   }
    /*     */
    /*     */
    /*     */   public BigDecimal getMargin() {
        /*  57 */     return this.margin;
        /*     */   }
    /*     */
    /*     */
    /*     */   public BigDecimal getFundsAmount() {
        /*  62 */     return this.fundsAmount;
        /*     */   }
    /*     */
    /*     */
    /*     */   public Integer getTradersCycle() {
        /*  67 */     return this.tradersCycle;
        /*     */   }
    /*     */
    /*     */
    /*     */   public Integer getLever() {
        /*  72 */     return this.lever;
        /*     */   }
    /*     */
    /*     */
    /*     */   public BigDecimal getManageFee() {
        /*  77 */     return this.manageFee;
        /*     */   }
    /*     */
    /*     */
    /*     */   public BigDecimal getTotalTradingAmount() {
        /*  82 */     return this.totalTradingAmount;
        /*     */   }
    /*     */
    /*     */
    /*     */   public Integer getAppendCycle() {
        /*  87 */     return this.appendCycle;
        /*     */   }
    /*     */
    /*     */
    /*     */   public BigDecimal getAppendServiceFee() {
        /*  92 */     return this.appendServiceFee;
        /*     */   }
    /*     */
    /*     */
    /*     */   public BigDecimal getAppendMargin() {
        /*  97 */     return this.appendMargin;
        /*     */   }
    /*     */
    /*     */
    /*     */   public Integer getStatus() {
        /* 102 */     return this.status;
        /*     */   }
    /*     */
    /*     */
    /*     */   public Date getAddTime() {
        /* 107 */     return this.addTime;
        /*     */   }
    /*     */
    /*     */
    /*     */   public Date getUpdateTime() {
        /* 112 */     return this.updateTime;
        /*     */   }
    /*     */
    /*     */
    /*     */   public Date getAuditTime() {
        /* 117 */     return this.auditTime;
        /*     */   }
    /*     */
    /*     */
    /*     */   public Date getEndTime() {
        /* 122 */     return this.endTime;
        /*     */   }
    /*     */
    /*     */
    /*     */   public BigDecimal getLineWarning() {
        /* 127 */     return this.lineWarning;
        /*     */   }
    /*     */
    /*     */
    /*     */   public BigDecimal getLineUnwind() {
        /* 132 */     return this.lineUnwind;
        /*     */   }
    /*     */
    /*     */
    /*     */   public BigDecimal getRatioWarning() {
        /* 137 */     return this.ratioWarning;
        /*     */   }
    /*     */
    /*     */
    /*     */   public BigDecimal getRatioUnwind() {
        /* 142 */     return this.ratioUnwind;
        /*     */   }
    /*     */
    /*     */
    /*     */   public BigDecimal getPayAmount() {
        /* 147 */     return this.payAmount;
        /*     */   } }


/* Location:              F:\ROOT\WEB-INF\classes\!\com\xc\pojo\FundsAppend.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */