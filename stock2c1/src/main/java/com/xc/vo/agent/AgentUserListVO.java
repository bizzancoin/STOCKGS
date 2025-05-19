
package com.xc.vo.agent;

import java.math.BigDecimal;

public class AgentUserListVO {
    private Integer id;
    private Integer agentId;
    private String agentName;
    private String phone;

    private String realName;

    private String idCard;

    private Integer accountType;

    private Integer isLock;

    private Integer isLogin;

    private String regAddress;

    private Integer isActive;

    private String bankName;

    private String bankNo;

    private String bankAddress;


    public void setId(Integer id) {
        this.id = id;
    }

    private BigDecimal userAmt;
    private BigDecimal enableAmt;
    private BigDecimal forceLine;
    private BigDecimal allProfitAndLose;
    private BigDecimal allFreezAmt;
    private BigDecimal userIndexAmt;
    private BigDecimal enableIndexAmt;
    private BigDecimal indexForceLine;
    private BigDecimal allIndexFreezAmt;
    private BigDecimal allIndexProfitAndLose;
    private BigDecimal userFuturesAmt;
    private BigDecimal enableFuturesAmt;
    private BigDecimal futuresForceLine;
    private BigDecimal allFuturesFreezAmt;
    private BigDecimal allFuturesProfitAndLose;

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public void setIsLock(Integer isLock) {
        this.isLock = isLock;
    }

    public void setIsLogin(Integer isLogin) {
        this.isLogin = isLogin;
    }

    public void setRegAddress(String regAddress) {
        this.regAddress = regAddress;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }

    public void setUserAmt(BigDecimal userAmt) {
        this.userAmt = userAmt;
    }

    public void setEnableAmt(BigDecimal enableAmt) {
        this.enableAmt = enableAmt;
    }

    public void setForceLine(BigDecimal forceLine) {
        this.forceLine = forceLine;
    }

    public void setAllProfitAndLose(BigDecimal allProfitAndLose) {
        this.allProfitAndLose = allProfitAndLose;
    }

    public void setAllFreezAmt(BigDecimal allFreezAmt) {
        this.allFreezAmt = allFreezAmt;
    }

    public void setUserIndexAmt(BigDecimal userIndexAmt) {
        this.userIndexAmt = userIndexAmt;
    }

    public void setEnableIndexAmt(BigDecimal enableIndexAmt) {
        this.enableIndexAmt = enableIndexAmt;
    }

    public void setIndexForceLine(BigDecimal indexForceLine) {
        this.indexForceLine = indexForceLine;
    }

    public void setAllIndexFreezAmt(BigDecimal allIndexFreezAmt) {
        this.allIndexFreezAmt = allIndexFreezAmt;
    }

    public void setAllIndexProfitAndLose(BigDecimal allIndexProfitAndLose) {
        this.allIndexProfitAndLose = allIndexProfitAndLose;
    }

    public void setUserFuturesAmt(BigDecimal userFuturesAmt) {
        this.userFuturesAmt = userFuturesAmt;
    }

    public void setEnableFuturesAmt(BigDecimal enableFuturesAmt) {
        this.enableFuturesAmt = enableFuturesAmt;
    }

    public void setFuturesForceLine(BigDecimal futuresForceLine) {
        this.futuresForceLine = futuresForceLine;
    }

    public void setAllFuturesFreezAmt(BigDecimal allFuturesFreezAmt) {
        this.allFuturesFreezAmt = allFuturesFreezAmt;
    }

    public void setAllFuturesProfitAndLose(BigDecimal allFuturesProfitAndLose) {
        this.allFuturesProfitAndLose = allFuturesProfitAndLose;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof AgentUserListVO)) return false;
        AgentUserListVO other = (AgentUserListVO) o;
        if (!other.canEqual(this)) return false;
        Object this$id = getId(), other$id = other.getId();
        if ((this$id == null) ? (other$id != null) : !this$id.equals(other$id)) return false;
        Object this$agentId = getAgentId(), other$agentId = other.getAgentId();
        if ((this$agentId == null) ? (other$agentId != null) : !this$agentId.equals(other$agentId)) return false;
        Object this$agentName = getAgentName(), other$agentName = other.getAgentName();
        if ((this$agentName == null) ? (other$agentName != null) : !this$agentName.equals(other$agentName))
            return false;
        Object this$phone = getPhone(), other$phone = other.getPhone();
        if ((this$phone == null) ? (other$phone != null) : !this$phone.equals(other$phone)) return false;
        Object this$realName = getRealName(), other$realName = other.getRealName();
        if ((this$realName == null) ? (other$realName != null) : !this$realName.equals(other$realName)) return false;
        Object this$idCard = getIdCard(), other$idCard = other.getIdCard();
        if ((this$idCard == null) ? (other$idCard != null) : !this$idCard.equals(other$idCard)) return false;
        Object this$accountType = getAccountType(), other$accountType = other.getAccountType();
        if ((this$accountType == null) ? (other$accountType != null) : !this$accountType.equals(other$accountType))
            return false;
        Object this$isLock = getIsLock(), other$isLock = other.getIsLock();
        if ((this$isLock == null) ? (other$isLock != null) : !this$isLock.equals(other$isLock)) return false;
        Object this$isLogin = getIsLogin(), other$isLogin = other.getIsLogin();
        if ((this$isLogin == null) ? (other$isLogin != null) : !this$isLogin.equals(other$isLogin)) return false;
        Object this$regAddress = getRegAddress(), other$regAddress = other.getRegAddress();
        if ((this$regAddress == null) ? (other$regAddress != null) : !this$regAddress.equals(other$regAddress))
            return false;
        Object this$isActive = getIsActive(), other$isActive = other.getIsActive();
        if ((this$isActive == null) ? (other$isActive != null) : !this$isActive.equals(other$isActive)) return false;
        Object this$bankName = getBankName(), other$bankName = other.getBankName();
        if ((this$bankName == null) ? (other$bankName != null) : !this$bankName.equals(other$bankName)) return false;
        Object this$bankNo = getBankNo(), other$bankNo = other.getBankNo();
        if ((this$bankNo == null) ? (other$bankNo != null) : !this$bankNo.equals(other$bankNo)) return false;
        Object this$bankAddress = getBankAddress(), other$bankAddress = other.getBankAddress();
        if ((this$bankAddress == null) ? (other$bankAddress != null) : !this$bankAddress.equals(other$bankAddress))
            return false;
        Object this$userAmt = getUserAmt(), other$userAmt = other.getUserAmt();
        if ((this$userAmt == null) ? (other$userAmt != null) : !this$userAmt.equals(other$userAmt)) return false;
        Object this$enableAmt = getEnableAmt(), other$enableAmt = other.getEnableAmt();
        if ((this$enableAmt == null) ? (other$enableAmt != null) : !this$enableAmt.equals(other$enableAmt))
            return false;
        Object this$forceLine = getForceLine(), other$forceLine = other.getForceLine();
        if ((this$forceLine == null) ? (other$forceLine != null) : !this$forceLine.equals(other$forceLine))
            return false;
        Object this$allProfitAndLose = getAllProfitAndLose(), other$allProfitAndLose = other.getAllProfitAndLose();
        if ((this$allProfitAndLose == null) ? (other$allProfitAndLose != null) : !this$allProfitAndLose.equals(other$allProfitAndLose))
            return false;
        Object this$allFreezAmt = getAllFreezAmt(), other$allFreezAmt = other.getAllFreezAmt();
        if ((this$allFreezAmt == null) ? (other$allFreezAmt != null) : !this$allFreezAmt.equals(other$allFreezAmt))
            return false;
        Object this$userIndexAmt = getUserIndexAmt(), other$userIndexAmt = other.getUserIndexAmt();
        if ((this$userIndexAmt == null) ? (other$userIndexAmt != null) : !this$userIndexAmt.equals(other$userIndexAmt))
            return false;
        Object this$enableIndexAmt = getEnableIndexAmt(), other$enableIndexAmt = other.getEnableIndexAmt();
        if ((this$enableIndexAmt == null) ? (other$enableIndexAmt != null) : !this$enableIndexAmt.equals(other$enableIndexAmt))
            return false;
        Object this$indexForceLine = getIndexForceLine(), other$indexForceLine = other.getIndexForceLine();
        if ((this$indexForceLine == null) ? (other$indexForceLine != null) : !this$indexForceLine.equals(other$indexForceLine))
            return false;
        Object this$allIndexFreezAmt = getAllIndexFreezAmt(), other$allIndexFreezAmt = other.getAllIndexFreezAmt();
        if ((this$allIndexFreezAmt == null) ? (other$allIndexFreezAmt != null) : !this$allIndexFreezAmt.equals(other$allIndexFreezAmt))
            return false;
        Object this$allIndexProfitAndLose = getAllIndexProfitAndLose(), other$allIndexProfitAndLose = other.getAllIndexProfitAndLose();
        if ((this$allIndexProfitAndLose == null) ? (other$allIndexProfitAndLose != null) : !this$allIndexProfitAndLose.equals(other$allIndexProfitAndLose))
            return false;
        Object this$userFuturesAmt = getUserFuturesAmt(), other$userFuturesAmt = other.getUserFuturesAmt();
        if ((this$userFuturesAmt == null) ? (other$userFuturesAmt != null) : !this$userFuturesAmt.equals(other$userFuturesAmt))
            return false;
        Object this$enableFuturesAmt = getEnableFuturesAmt(), other$enableFuturesAmt = other.getEnableFuturesAmt();
        if ((this$enableFuturesAmt == null) ? (other$enableFuturesAmt != null) : !this$enableFuturesAmt.equals(other$enableFuturesAmt))
            return false;
        Object this$futuresForceLine = getFuturesForceLine(), other$futuresForceLine = other.getFuturesForceLine();
        if ((this$futuresForceLine == null) ? (other$futuresForceLine != null) : !this$futuresForceLine.equals(other$futuresForceLine))
            return false;
        Object this$allFuturesFreezAmt = getAllFuturesFreezAmt(), other$allFuturesFreezAmt = other.getAllFuturesFreezAmt();
        if ((this$allFuturesFreezAmt == null) ? (other$allFuturesFreezAmt != null) : !this$allFuturesFreezAmt.equals(other$allFuturesFreezAmt))
            return false;
        Object this$allFuturesProfitAndLose = getAllFuturesProfitAndLose(), other$allFuturesProfitAndLose = other.getAllFuturesProfitAndLose();
        return !((this$allFuturesProfitAndLose == null) ? (other$allFuturesProfitAndLose != null) : !this$allFuturesProfitAndLose.equals(other$allFuturesProfitAndLose));
    }

    protected boolean canEqual(Object other) {
        return other instanceof AgentUserListVO;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $id = getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        Object $agentId = getAgentId();
        result = result * 59 + (($agentId == null) ? 43 : $agentId.hashCode());
        Object $agentName = getAgentName();
        result = result * 59 + (($agentName == null) ? 43 : $agentName.hashCode());
        Object $phone = getPhone();
        result = result * 59 + (($phone == null) ? 43 : $phone.hashCode());
        Object $realName = getRealName();
        result = result * 59 + (($realName == null) ? 43 : $realName.hashCode());
        Object $idCard = getIdCard();
        result = result * 59 + (($idCard == null) ? 43 : $idCard.hashCode());
        Object $accountType = getAccountType();
        result = result * 59 + (($accountType == null) ? 43 : $accountType.hashCode());
        Object $isLock = getIsLock();
        result = result * 59 + (($isLock == null) ? 43 : $isLock.hashCode());
        Object $isLogin = getIsLogin();
        result = result * 59 + (($isLogin == null) ? 43 : $isLogin.hashCode());
        Object $regAddress = getRegAddress();
        result = result * 59 + (($regAddress == null) ? 43 : $regAddress.hashCode());
        Object $isActive = getIsActive();
        result = result * 59 + (($isActive == null) ? 43 : $isActive.hashCode());
        Object $bankName = getBankName();
        result = result * 59 + (($bankName == null) ? 43 : $bankName.hashCode());
        Object $bankNo = getBankNo();
        result = result * 59 + (($bankNo == null) ? 43 : $bankNo.hashCode());
        Object $bankAddress = getBankAddress();
        result = result * 59 + (($bankAddress == null) ? 43 : $bankAddress.hashCode());
        Object $userAmt = getUserAmt();
        result = result * 59 + (($userAmt == null) ? 43 : $userAmt.hashCode());
        Object $enableAmt = getEnableAmt();
        result = result * 59 + (($enableAmt == null) ? 43 : $enableAmt.hashCode());
        Object $forceLine = getForceLine();
        result = result * 59 + (($forceLine == null) ? 43 : $forceLine.hashCode());
        Object $allProfitAndLose = getAllProfitAndLose();
        result = result * 59 + (($allProfitAndLose == null) ? 43 : $allProfitAndLose.hashCode());
        Object $allFreezAmt = getAllFreezAmt();
        result = result * 59 + (($allFreezAmt == null) ? 43 : $allFreezAmt.hashCode());
        Object $userIndexAmt = getUserIndexAmt();
        result = result * 59 + (($userIndexAmt == null) ? 43 : $userIndexAmt.hashCode());
        Object $enableIndexAmt = getEnableIndexAmt();
        result = result * 59 + (($enableIndexAmt == null) ? 43 : $enableIndexAmt.hashCode());
        Object $indexForceLine = getIndexForceLine();
        result = result * 59 + (($indexForceLine == null) ? 43 : $indexForceLine.hashCode());
        Object $allIndexFreezAmt = getAllIndexFreezAmt();
        result = result * 59 + (($allIndexFreezAmt == null) ? 43 : $allIndexFreezAmt.hashCode());
        Object $allIndexProfitAndLose = getAllIndexProfitAndLose();
        result = result * 59 + (($allIndexProfitAndLose == null) ? 43 : $allIndexProfitAndLose.hashCode());
        Object $userFuturesAmt = getUserFuturesAmt();
        result = result * 59 + (($userFuturesAmt == null) ? 43 : $userFuturesAmt.hashCode());
        Object $enableFuturesAmt = getEnableFuturesAmt();
        result = result * 59 + (($enableFuturesAmt == null) ? 43 : $enableFuturesAmt.hashCode());
        Object $futuresForceLine = getFuturesForceLine();
        result = result * 59 + (($futuresForceLine == null) ? 43 : $futuresForceLine.hashCode());
        Object $allFuturesFreezAmt = getAllFuturesFreezAmt();
        result = result * 59 + (($allFuturesFreezAmt == null) ? 43 : $allFuturesFreezAmt.hashCode());
        Object $allFuturesProfitAndLose = getAllFuturesProfitAndLose();
        return result * 59 + (($allFuturesProfitAndLose == null) ? 43 : $allFuturesProfitAndLose.hashCode());
    }

    public String toString() {
        return "AgentUserListVO(id=" + getId() + ", agentId=" + getAgentId() + ", agentName=" + getAgentName() + ", phone=" + getPhone() + ", realName=" + getRealName() + ", idCard=" + getIdCard() + ", accountType=" + getAccountType() + ", isLock=" + getIsLock() + ", isLogin=" + getIsLogin() + ", regAddress=" + getRegAddress() + ", isActive=" + getIsActive() + ", bankName=" + getBankName() + ", bankNo=" + getBankNo() + ", bankAddress=" + getBankAddress() + ", userAmt=" + getUserAmt() + ", enableAmt=" + getEnableAmt() + ", forceLine=" + getForceLine() + ", allProfitAndLose=" + getAllProfitAndLose() + ", allFreezAmt=" + getAllFreezAmt() + ", userIndexAmt=" + getUserIndexAmt() + ", enableIndexAmt=" + getEnableIndexAmt() + ", indexForceLine=" + getIndexForceLine() + ", allIndexFreezAmt=" + getAllIndexFreezAmt() + ", allIndexProfitAndLose=" + getAllIndexProfitAndLose() + ", userFuturesAmt=" + getUserFuturesAmt() + ", enableFuturesAmt=" + getEnableFuturesAmt() + ", futuresForceLine=" + getFuturesForceLine() + ", allFuturesFreezAmt=" + getAllFuturesFreezAmt() + ", allFuturesProfitAndLose=" + getAllFuturesProfitAndLose() + ")";
    }


    public Integer getId() {
        return this.id;
    }

    public Integer getAgentId() {
        return this.agentId;
    }

    public String getAgentName() {
        return this.agentName;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getRealName() {
        return this.realName;
    }

    public String getIdCard() {
        return this.idCard;
    }

    public Integer getAccountType() {
        return this.accountType;
    }

    public Integer getIsLock() {
        return this.isLock;
    }

    public Integer getIsLogin() {
        return this.isLogin;
    }

    public String getRegAddress() {
        return this.regAddress;
    }

    public Integer getIsActive() {
        return this.isActive;
    }

    public String getBankName() {
        return this.bankName;
    }

    public String getBankNo() {
        return this.bankNo;
    }

    public String getBankAddress() {
        return this.bankAddress;
    }


    public BigDecimal getUserAmt() {
        return this.userAmt;
    }

    public BigDecimal getEnableAmt() {
        return this.enableAmt;
    }

    public BigDecimal getForceLine() {
        return this.forceLine;
    }


    public BigDecimal getAllProfitAndLose() {
        return this.allProfitAndLose;
    }


    public BigDecimal getAllFreezAmt() {
        return this.allFreezAmt;
    }


    public BigDecimal getUserIndexAmt() {
        return this.userIndexAmt;
    }

    public BigDecimal getEnableIndexAmt() {
        return this.enableIndexAmt;
    }

    public BigDecimal getIndexForceLine() {
        return this.indexForceLine;
    }


    public BigDecimal getAllIndexFreezAmt() {
        return this.allIndexFreezAmt;
    }


    public BigDecimal getAllIndexProfitAndLose() {
        return this.allIndexProfitAndLose;
    }


    public BigDecimal getUserFuturesAmt() {
        return this.userFuturesAmt;
    }

    public BigDecimal getEnableFuturesAmt() {
        return this.enableFuturesAmt;
    }

    public BigDecimal getFuturesForceLine() {
        return this.futuresForceLine;
    }


    public BigDecimal getAllFuturesFreezAmt() {
        return this.allFuturesFreezAmt;
    }


    public BigDecimal getAllFuturesProfitAndLose() {
        return this.allFuturesProfitAndLose;
    }
}
