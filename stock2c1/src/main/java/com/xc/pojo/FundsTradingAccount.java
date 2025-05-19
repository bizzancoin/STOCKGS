/*     */ package com.xc.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/*     */
/*     */ public class FundsTradingAccount implements Serializable {
    /*     */   private static final long serialVersionUID = 1L;
    /*     */   private Integer id;
    /*     */   private Integer dealerInstitutionsId;
    /*     */   private String dealerInstitutionsName;
    /*     */   private String accountName;
    /*     */   private Integer subaccountNumber;
    /*     */   private Integer accountMode;
    /*     */   private boolean automaticUnwindSwitch;
    /*     */   private boolean banUnwindSwitch;
    /*     */
    /*  14 */   public void setId(Integer id) { this.id = id; } private boolean automaticRenewalSwitch; private boolean banLevite; private BigDecimal warningLine; private BigDecimal unwindLine; private BigDecimal singleHoldingRatio; private Integer status; private Date addTime; private Date updateTime; private String remarks; public void setDealerInstitutionsId(Integer dealerInstitutionsId) { this.dealerInstitutionsId = dealerInstitutionsId; } public void setDealerInstitutionsName(String dealerInstitutionsName) { this.dealerInstitutionsName = dealerInstitutionsName; } public void setAccountName(String accountName) { this.accountName = accountName; } public void setSubaccountNumber(Integer subaccountNumber) { this.subaccountNumber = subaccountNumber; } public void setAccountMode(Integer accountMode) { this.accountMode = accountMode; } public void setAutomaticUnwindSwitch(boolean automaticUnwindSwitch) { this.automaticUnwindSwitch = automaticUnwindSwitch; } public void setBanUnwindSwitch(boolean banUnwindSwitch) { this.banUnwindSwitch = banUnwindSwitch; } public void setAutomaticRenewalSwitch(boolean automaticRenewalSwitch) { this.automaticRenewalSwitch = automaticRenewalSwitch; } public void setBanLevite(boolean banLevite) { this.banLevite = banLevite; } public void setWarningLine(BigDecimal warningLine) { this.warningLine = warningLine; } public void setUnwindLine(BigDecimal unwindLine) { this.unwindLine = unwindLine; } public void setSingleHoldingRatio(BigDecimal singleHoldingRatio) { this.singleHoldingRatio = singleHoldingRatio; } public void setStatus(Integer status) { this.status = status; } public void setAddTime(Date addTime) { this.addTime = addTime; } public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; } public void setRemarks(String remarks) { this.remarks = remarks; } public boolean equals(Object o) { if (o == this) return true;  if (!(o instanceof FundsTradingAccount)) return false;  FundsTradingAccount other = (FundsTradingAccount)o; if (!other.canEqual(this)) return false;  Object this$id = getId(), other$id = other.getId(); if ((this$id == null) ? (other$id != null) : !this$id.equals(other$id)) return false;  Object this$dealerInstitutionsId = getDealerInstitutionsId(), other$dealerInstitutionsId = other.getDealerInstitutionsId(); if ((this$dealerInstitutionsId == null) ? (other$dealerInstitutionsId != null) : !this$dealerInstitutionsId.equals(other$dealerInstitutionsId)) return false;  Object this$dealerInstitutionsName = getDealerInstitutionsName(), other$dealerInstitutionsName = other.getDealerInstitutionsName(); if ((this$dealerInstitutionsName == null) ? (other$dealerInstitutionsName != null) : !this$dealerInstitutionsName.equals(other$dealerInstitutionsName)) return false;  Object this$accountName = getAccountName(), other$accountName = other.getAccountName(); if ((this$accountName == null) ? (other$accountName != null) : !this$accountName.equals(other$accountName)) return false;  Object this$subaccountNumber = getSubaccountNumber(), other$subaccountNumber = other.getSubaccountNumber(); if ((this$subaccountNumber == null) ? (other$subaccountNumber != null) : !this$subaccountNumber.equals(other$subaccountNumber)) return false;  Object this$accountMode = getAccountMode(), other$accountMode = other.getAccountMode(); if ((this$accountMode == null) ? (other$accountMode != null) : !this$accountMode.equals(other$accountMode)) return false;  if (isAutomaticUnwindSwitch() != other.isAutomaticUnwindSwitch()) return false;  if (isBanUnwindSwitch() != other.isBanUnwindSwitch()) return false;  if (isAutomaticRenewalSwitch() != other.isAutomaticRenewalSwitch()) return false;  if (isBanLevite() != other.isBanLevite()) return false;  Object this$warningLine = getWarningLine(), other$warningLine = other.getWarningLine(); if ((this$warningLine == null) ? (other$warningLine != null) : !this$warningLine.equals(other$warningLine)) return false;  Object this$unwindLine = getUnwindLine(), other$unwindLine = other.getUnwindLine(); if ((this$unwindLine == null) ? (other$unwindLine != null) : !this$unwindLine.equals(other$unwindLine)) return false;  Object this$singleHoldingRatio = getSingleHoldingRatio(), other$singleHoldingRatio = other.getSingleHoldingRatio(); if ((this$singleHoldingRatio == null) ? (other$singleHoldingRatio != null) : !this$singleHoldingRatio.equals(other$singleHoldingRatio)) return false;  Object this$status = getStatus(), other$status = other.getStatus(); if ((this$status == null) ? (other$status != null) : !this$status.equals(other$status)) return false;  Object this$addTime = getAddTime(), other$addTime = other.getAddTime(); if ((this$addTime == null) ? (other$addTime != null) : !this$addTime.equals(other$addTime)) return false;  Object this$updateTime = getUpdateTime(), other$updateTime = other.getUpdateTime(); if ((this$updateTime == null) ? (other$updateTime != null) : !this$updateTime.equals(other$updateTime)) return false;  Object this$remarks = getRemarks(), other$remarks = other.getRemarks(); return !((this$remarks == null) ? (other$remarks != null) : !this$remarks.equals(other$remarks)); } protected boolean canEqual(Object other) { return other instanceof FundsTradingAccount; } public int hashCode() { int PRIME = 59;
        int result = 1;
        Object $id = getId(); result = result * 59 + (($id == null) ? 43 : $id.hashCode()); Object $dealerInstitutionsId = getDealerInstitutionsId(); result = result * 59 + (($dealerInstitutionsId == null) ? 43 : $dealerInstitutionsId.hashCode()); Object $dealerInstitutionsName = getDealerInstitutionsName(); result = result * 59 + (($dealerInstitutionsName == null) ? 43 : $dealerInstitutionsName.hashCode()); Object $accountName = getAccountName(); result = result * 59 + (($accountName == null) ? 43 : $accountName.hashCode()); Object $subaccountNumber = getSubaccountNumber(); result = result * 59 + (($subaccountNumber == null) ? 43 : $subaccountNumber.hashCode()); Object $accountMode = getAccountMode(); result = result * 59 + (($accountMode == null) ? 43 : $accountMode.hashCode()); result = result * 59 + (isAutomaticUnwindSwitch() ? 79 : 97); result = result * 59 + (isBanUnwindSwitch() ? 79 : 97); result = result * 59 + (isAutomaticRenewalSwitch() ? 79 : 97); result = result * 59 + (isBanLevite() ? 79 : 97); Object $warningLine = getWarningLine(); result = result * 59 + (($warningLine == null) ? 43 : $warningLine.hashCode()); Object $unwindLine = getUnwindLine(); result = result * 59 + (($unwindLine == null) ? 43 : $unwindLine.hashCode()); Object $singleHoldingRatio = getSingleHoldingRatio(); result = result * 59 + (($singleHoldingRatio == null) ? 43 : $singleHoldingRatio.hashCode()); Object $status = getStatus(); result = result * 59 + (($status == null) ? 43 : $status.hashCode()); Object $addTime = getAddTime(); result = result * 59 + (($addTime == null) ? 43 : $addTime.hashCode()); Object $updateTime = getUpdateTime(); result = result * 59 + (($updateTime == null) ? 43 : $updateTime.hashCode()); Object $remarks = getRemarks(); return result * 59 + (($remarks == null) ? 43 : $remarks.hashCode()); } public String toString() { return "FundsTradingAccount(id=" + getId() + ", dealerInstitutionsId=" + getDealerInstitutionsId() + ", dealerInstitutionsName=" + getDealerInstitutionsName() + ", accountName=" + getAccountName() + ", subaccountNumber=" + getSubaccountNumber() + ", accountMode=" + getAccountMode() + ", automaticUnwindSwitch=" + isAutomaticUnwindSwitch() + ", banUnwindSwitch=" + isBanUnwindSwitch() + ", automaticRenewalSwitch=" + isAutomaticRenewalSwitch() + ", banLevite=" + isBanLevite() + ", warningLine=" + getWarningLine() + ", unwindLine=" + getUnwindLine() + ", singleHoldingRatio=" + getSingleHoldingRatio() + ", status=" + getStatus() + ", addTime=" + getAddTime() + ", updateTime=" + getUpdateTime() + ", remarks=" + getRemarks() + ")"; }
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
    /*     */   public Integer getDealerInstitutionsId() {
        /*  27 */     return this.dealerInstitutionsId;
        /*     */   }
    /*     */
    /*     */
    /*     */   public String getDealerInstitutionsName() {
        /*  32 */     return this.dealerInstitutionsName;
        /*     */   }
    /*     */
    /*     */
    /*     */   public String getAccountName() {
        /*  37 */     return this.accountName;
        /*     */   }
    /*     */
    /*     */
    /*     */   public Integer getSubaccountNumber() {
        /*  42 */     return this.subaccountNumber;
        /*     */   }
    /*     */
    /*     */
    /*     */   public Integer getAccountMode() {
        /*  47 */     return this.accountMode;
        /*     */   }
    /*     */
    /*     */
    /*     */   public boolean isAutomaticUnwindSwitch() {
        /*  52 */     return this.automaticUnwindSwitch;
        /*     */   }
    /*     */
    /*     */
    /*     */   public boolean isBanUnwindSwitch() {
        /*  57 */     return this.banUnwindSwitch;
        /*     */   }
    /*     */
    /*     */
    /*     */   public boolean isAutomaticRenewalSwitch() {
        /*  62 */     return this.automaticRenewalSwitch;
        /*     */   }
    /*     */
    /*     */
    /*     */   public boolean isBanLevite() {
        /*  67 */     return this.banLevite;
        /*     */   }
    /*     */
    /*     */
    /*     */   public BigDecimal getWarningLine() {
        /*  72 */     return this.warningLine;
        /*     */   }
    /*     */
    /*     */
    /*     */   public BigDecimal getUnwindLine() {
        /*  77 */     return this.unwindLine;
        /*     */   }
    /*     */
    /*     */
    /*     */   public BigDecimal getSingleHoldingRatio() {
        /*  82 */     return this.singleHoldingRatio;
        /*     */   }
    /*     */
    /*     */
    /*     */   public Integer getStatus() {
        /*  87 */     return this.status;
        /*     */   }
    /*     */
    /*     */
    /*     */   public Date getAddTime() {
        /*  92 */     return this.addTime;
        /*     */   }
    /*     */
    /*     */
    /*     */   public Date getUpdateTime() {
        /*  97 */     return this.updateTime;
        /*     */   }
    /*     */
    /*     */
    /*     */   public String getRemarks() {
        /* 102 */     return this.remarks;
        /*     */   }
    /*     */ }


/* Location:              F:\ROOT\WEB-INF\classes\!\com\xc\pojo\FundsTradingAccount.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */