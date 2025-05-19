package com.xc.vo.user;

import java.math.BigDecimal;
import java.util.Date;

public class UserInfoVO {
    private Integer id;
    private Integer agentId;
    private String agentName;
    private String phone;
    private String nickName;
    private String realName;

    private String idCard;

    private Integer accountType;

    private String recomPhone;

    private Integer isLock;

    private Date regTime;

    private String regIp;

    private String regAddress;

    private String img1Key;

    private String img2Key;

    public void setId(Integer id) {
        this.id = id;
    }

    private String img3Key;
    private Integer isActive;
    private String authMsg;
    private BigDecimal userAmt;
    private BigDecimal enableAmt;
    private BigDecimal userIndexAmt;
    private BigDecimal enableIndexAmt;
    private BigDecimal userFuturesAmt;
    private BigDecimal enableFuturesAmt;
    private BigDecimal allProfitAndLose;
    private BigDecimal allPositionMoney;
    private BigDecimal allFreezAmt;
    private BigDecimal allIndexProfitAndLose;
    private BigDecimal allIndexFreezAmt;
    private BigDecimal allFuturesProfitAndLose;
    private BigDecimal allFuturesFreezAmt;
    /**
     * 杠杆倍数,多个用/分割
     */
    private String siteLever;

    /*操盘金额*/
    private BigDecimal tradingAmount;

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    public void setRecomPhone(String recomPhone) {
        this.recomPhone = recomPhone;
    }

    public void setIsLock(Integer isLock) {
        this.isLock = isLock;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public void setRegIp(String regIp) {
        this.regIp = regIp;
    }

    public void setRegAddress(String regAddress) {
        this.regAddress = regAddress;
    }

    public void setImg1Key(String img1Key) {
        this.img1Key = img1Key;
    }

    public void setImg2Key(String img2Key) {
        this.img2Key = img2Key;
    }

    public void setImg3Key(String img3Key) {
        this.img3Key = img3Key;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public void setAuthMsg(String authMsg) {
        this.authMsg = authMsg;
    }

    public void setUserAmt(BigDecimal userAmt) {
        this.userAmt = userAmt;
    }

    public void setEnableAmt(BigDecimal enableAmt) {
        this.enableAmt = enableAmt;
    }

    public void setUserIndexAmt(BigDecimal userIndexAmt) {
        this.userIndexAmt = userIndexAmt;
    }

    public void setEnableIndexAmt(BigDecimal enableIndexAmt) {
        this.enableIndexAmt = enableIndexAmt;
    }

    public void setUserFuturesAmt(BigDecimal userFuturesAmt) {
        this.userFuturesAmt = userFuturesAmt;
    }

    public void setEnableFuturesAmt(BigDecimal enableFuturesAmt) {
        this.enableFuturesAmt = enableFuturesAmt;
    }

    public void setAllProfitAndLose(BigDecimal allProfitAndLose) {
        this.allProfitAndLose = allProfitAndLose;
    }

    public void setAllFreezAmt(BigDecimal allFreezAmt) {
        this.allFreezAmt = allFreezAmt;
    }

    public void setAllIndexProfitAndLose(BigDecimal allIndexProfitAndLose) {
        this.allIndexProfitAndLose = allIndexProfitAndLose;
    }

    public void setAllIndexFreezAmt(BigDecimal allIndexFreezAmt) {
        this.allIndexFreezAmt = allIndexFreezAmt;
    }

    public void setAllFuturesProfitAndLose(BigDecimal allFuturesProfitAndLose) {
        this.allFuturesProfitAndLose = allFuturesProfitAndLose;
    }

    public BigDecimal getAllPositionMoney() {
        return allPositionMoney;
    }

    public void setAllPositionMoney(BigDecimal allPositionMoney) {
        this.allPositionMoney = allPositionMoney;
    }

    public void setAllFuturesFreezAmt(BigDecimal allFuturesFreezAmt) {
        this.allFuturesFreezAmt = allFuturesFreezAmt;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof UserInfoVO)) return false;
        UserInfoVO other = (UserInfoVO) o;
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
        Object this$nickName = getNickName(), other$nickName = other.getNickName();
        if ((this$nickName == null) ? (other$nickName != null) : !this$nickName.equals(other$nickName)) return false;
        Object this$realName = getRealName(), other$realName = other.getRealName();
        if ((this$realName == null) ? (other$realName != null) : !this$realName.equals(other$realName)) return false;
        Object this$idCard = getIdCard(), other$idCard = other.getIdCard();
        if ((this$idCard == null) ? (other$idCard != null) : !this$idCard.equals(other$idCard)) return false;
        Object this$accountType = getAccountType(), other$accountType = other.getAccountType();
        if ((this$accountType == null) ? (other$accountType != null) : !this$accountType.equals(other$accountType))
            return false;
        Object this$recomPhone = getRecomPhone(), other$recomPhone = other.getRecomPhone();
        if ((this$recomPhone == null) ? (other$recomPhone != null) : !this$recomPhone.equals(other$recomPhone))
            return false;
        Object this$isLock = getIsLock(), other$isLock = other.getIsLock();
        if ((this$isLock == null) ? (other$isLock != null) : !this$isLock.equals(other$isLock)) return false;
        Object this$regTime = getRegTime(), other$regTime = other.getRegTime();
        if ((this$regTime == null) ? (other$regTime != null) : !this$regTime.equals(other$regTime)) return false;
        Object this$regIp = getRegIp(), other$regIp = other.getRegIp();
        if ((this$regIp == null) ? (other$regIp != null) : !this$regIp.equals(other$regIp)) return false;
        Object this$regAddress = getRegAddress(), other$regAddress = other.getRegAddress();
        if ((this$regAddress == null) ? (other$regAddress != null) : !this$regAddress.equals(other$regAddress))
            return false;
        Object this$img1Key = getImg1Key(), other$img1Key = other.getImg1Key();
        if ((this$img1Key == null) ? (other$img1Key != null) : !this$img1Key.equals(other$img1Key)) return false;
        Object this$img2Key = getImg2Key(), other$img2Key = other.getImg2Key();
        if ((this$img2Key == null) ? (other$img2Key != null) : !this$img2Key.equals(other$img2Key)) return false;
        Object this$img3Key = getImg3Key(), other$img3Key = other.getImg3Key();
        if ((this$img3Key == null) ? (other$img3Key != null) : !this$img3Key.equals(other$img3Key)) return false;
        Object this$isActive = getIsActive(), other$isActive = other.getIsActive();
        if ((this$isActive == null) ? (other$isActive != null) : !this$isActive.equals(other$isActive)) return false;
        Object this$authMsg = getAuthMsg(), other$authMsg = other.getAuthMsg();
        if ((this$authMsg == null) ? (other$authMsg != null) : !this$authMsg.equals(other$authMsg)) return false;
        Object this$userAmt = getUserAmt(), other$userAmt = other.getUserAmt();
        if ((this$userAmt == null) ? (other$userAmt != null) : !this$userAmt.equals(other$userAmt)) return false;
        Object this$enableAmt = getEnableAmt(), other$enableAmt = other.getEnableAmt();
        if ((this$enableAmt == null) ? (other$enableAmt != null) : !this$enableAmt.equals(other$enableAmt))
            return false;
        Object this$userIndexAmt = getUserIndexAmt(), other$userIndexAmt = other.getUserIndexAmt();
        if ((this$userIndexAmt == null) ? (other$userIndexAmt != null) : !this$userIndexAmt.equals(other$userIndexAmt))
            return false;
        Object this$enableIndexAmt = getEnableIndexAmt(), other$enableIndexAmt = other.getEnableIndexAmt();
        if ((this$enableIndexAmt == null) ? (other$enableIndexAmt != null) : !this$enableIndexAmt.equals(other$enableIndexAmt))
            return false;
        Object this$userFuturesAmt = getUserFuturesAmt(), other$userFuturesAmt = other.getUserFuturesAmt();
        if ((this$userFuturesAmt == null) ? (other$userFuturesAmt != null) : !this$userFuturesAmt.equals(other$userFuturesAmt))
            return false;
        Object this$enableFuturesAmt = getEnableFuturesAmt(), other$enableFuturesAmt = other.getEnableFuturesAmt();
        if ((this$enableFuturesAmt == null) ? (other$enableFuturesAmt != null) : !this$enableFuturesAmt.equals(other$enableFuturesAmt))
            return false;
        Object this$allProfitAndLose = getAllProfitAndLose(), other$allProfitAndLose = other.getAllProfitAndLose();
        if ((this$allProfitAndLose == null) ? (other$allProfitAndLose != null) : !this$allProfitAndLose.equals(other$allProfitAndLose))
            return false;
        Object this$allFreezAmt = getAllFreezAmt(), other$allFreezAmt = other.getAllFreezAmt();
        if ((this$allFreezAmt == null) ? (other$allFreezAmt != null) : !this$allFreezAmt.equals(other$allFreezAmt))
            return false;
        Object this$allIndexProfitAndLose = getAllIndexProfitAndLose(), other$allIndexProfitAndLose = other.getAllIndexProfitAndLose();
        if ((this$allIndexProfitAndLose == null) ? (other$allIndexProfitAndLose != null) : !this$allIndexProfitAndLose.equals(other$allIndexProfitAndLose))
            return false;
        Object this$allIndexFreezAmt = getAllIndexFreezAmt(), other$allIndexFreezAmt = other.getAllIndexFreezAmt();
        if ((this$allIndexFreezAmt == null) ? (other$allIndexFreezAmt != null) : !this$allIndexFreezAmt.equals(other$allIndexFreezAmt))
            return false;
        Object this$allFuturesProfitAndLose = getAllFuturesProfitAndLose(), other$allFuturesProfitAndLose = other.getAllFuturesProfitAndLose();
        if ((this$allFuturesProfitAndLose == null) ? (other$allFuturesProfitAndLose != null) : !this$allFuturesProfitAndLose.equals(other$allFuturesProfitAndLose))
            return false;
        Object this$allFuturesFreezAmt = getAllFuturesFreezAmt(), other$allFuturesFreezAmt = other.getAllFuturesFreezAmt();
        return !((this$allFuturesFreezAmt == null) ? (other$allFuturesFreezAmt != null) : !this$allFuturesFreezAmt.equals(other$allFuturesFreezAmt));
    }

    protected boolean canEqual(Object other) {
        return other instanceof UserInfoVO;
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
        Object $nickName = getNickName();
        result = result * 59 + (($nickName == null) ? 43 : $nickName.hashCode());
        Object $realName = getRealName();
        result = result * 59 + (($realName == null) ? 43 : $realName.hashCode());
        Object $idCard = getIdCard();
        result = result * 59 + (($idCard == null) ? 43 : $idCard.hashCode());
        Object $accountType = getAccountType();
        result = result * 59 + (($accountType == null) ? 43 : $accountType.hashCode());
        Object $recomPhone = getRecomPhone();
        result = result * 59 + (($recomPhone == null) ? 43 : $recomPhone.hashCode());
        Object $isLock = getIsLock();
        result = result * 59 + (($isLock == null) ? 43 : $isLock.hashCode());
        Object $regTime = getRegTime();
        result = result * 59 + (($regTime == null) ? 43 : $regTime.hashCode());
        Object $regIp = getRegIp();
        result = result * 59 + (($regIp == null) ? 43 : $regIp.hashCode());
        Object $regAddress = getRegAddress();
        result = result * 59 + (($regAddress == null) ? 43 : $regAddress.hashCode());
        Object $img1Key = getImg1Key();
        result = result * 59 + (($img1Key == null) ? 43 : $img1Key.hashCode());
        Object $img2Key = getImg2Key();
        result = result * 59 + (($img2Key == null) ? 43 : $img2Key.hashCode());
        Object $img3Key = getImg3Key();
        result = result * 59 + (($img3Key == null) ? 43 : $img3Key.hashCode());
        Object $isActive = getIsActive();
        result = result * 59 + (($isActive == null) ? 43 : $isActive.hashCode());
        Object $authMsg = getAuthMsg();
        result = result * 59 + (($authMsg == null) ? 43 : $authMsg.hashCode());
        Object $userAmt = getUserAmt();
        result = result * 59 + (($userAmt == null) ? 43 : $userAmt.hashCode());
        Object $enableAmt = getEnableAmt();
        result = result * 59 + (($enableAmt == null) ? 43 : $enableAmt.hashCode());
        Object $userIndexAmt = getUserIndexAmt();
        result = result * 59 + (($userIndexAmt == null) ? 43 : $userIndexAmt.hashCode());
        Object $enableIndexAmt = getEnableIndexAmt();
        result = result * 59 + (($enableIndexAmt == null) ? 43 : $enableIndexAmt.hashCode());
        Object $userFuturesAmt = getUserFuturesAmt();
        result = result * 59 + (($userFuturesAmt == null) ? 43 : $userFuturesAmt.hashCode());
        Object $enableFuturesAmt = getEnableFuturesAmt();
        result = result * 59 + (($enableFuturesAmt == null) ? 43 : $enableFuturesAmt.hashCode());
        Object $allProfitAndLose = getAllProfitAndLose();
        result = result * 59 + (($allProfitAndLose == null) ? 43 : $allProfitAndLose.hashCode());
        Object $allFreezAmt = getAllFreezAmt();
        result = result * 59 + (($allFreezAmt == null) ? 43 : $allFreezAmt.hashCode());
        Object $allIndexProfitAndLose = getAllIndexProfitAndLose();
        result = result * 59 + (($allIndexProfitAndLose == null) ? 43 : $allIndexProfitAndLose.hashCode());
        Object $allIndexFreezAmt = getAllIndexFreezAmt();
        result = result * 59 + (($allIndexFreezAmt == null) ? 43 : $allIndexFreezAmt.hashCode());
        Object $allFuturesProfitAndLose = getAllFuturesProfitAndLose();
        result = result * 59 + (($allFuturesProfitAndLose == null) ? 43 : $allFuturesProfitAndLose.hashCode());
        Object $allFuturesFreezAmt = getAllFuturesFreezAmt();
        return result * 59 + (($allFuturesFreezAmt == null) ? 43 : $allFuturesFreezAmt.hashCode());
    }

    public String toString() {
        return "UserInfoVO(id=" + getId() + ", agentId=" + getAgentId() + ", agentName=" + getAgentName() + ", phone=" + getPhone() + ", nickName=" + getNickName() + ", realName=" + getRealName() + ", idCard=" + getIdCard() + ", accountType=" + getAccountType() + ", recomPhone=" + getRecomPhone() + ", isLock=" + getIsLock() + ", regTime=" + getRegTime() + ", regIp=" + getRegIp() + ", regAddress=" + getRegAddress() + ", img1Key=" + getImg1Key() + ", img2Key=" + getImg2Key() + ", img3Key=" + getImg3Key() + ", isActive=" + getIsActive() + ", authMsg=" + getAuthMsg() + ", userAmt=" + getUserAmt() + ", enableAmt=" + getEnableAmt() + ", userIndexAmt=" + getUserIndexAmt() + ", enableIndexAmt=" + getEnableIndexAmt() + ", userFuturesAmt=" + getUserFuturesAmt() + ", enableFuturesAmt=" + getEnableFuturesAmt() + ", allProfitAndLose=" + getAllProfitAndLose() + ", allFreezAmt=" + getAllFreezAmt() + ", allIndexProfitAndLose=" + getAllIndexProfitAndLose() + ", allIndexFreezAmt=" + getAllIndexFreezAmt() + ", allFuturesProfitAndLose=" + getAllFuturesProfitAndLose() + ", allFuturesFreezAmt=" + getAllFuturesFreezAmt() + ",siteLever=" +getSiteLever()+ ",tradingAmount="+ getTradingAmount() + ")";
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


    public String getNickName() {
        return this.nickName;
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


    public String getRecomPhone() {
        return this.recomPhone;
    }


    public Integer getIsLock() {
        return this.isLock;
    }


    public Date getRegTime() {
        return this.regTime;
    }


    public String getRegIp() {
        return this.regIp;
    }


    public String getRegAddress() {
        return this.regAddress;
    }


    public String getImg1Key() {
        return this.img1Key;
    }


    public String getImg2Key() {
        return this.img2Key;
    }


    public String getImg3Key() {
        return this.img3Key;
    }


    public Integer getIsActive() {
        return this.isActive;
    }


    public String getAuthMsg() {
        return this.authMsg;
    }


    public BigDecimal getUserAmt() {
        return this.userAmt;
    }


    public BigDecimal getEnableAmt() {
        return this.enableAmt;
    }


    public BigDecimal getUserIndexAmt() {
        return this.userIndexAmt;
    }


    public BigDecimal getEnableIndexAmt() {
        return this.enableIndexAmt;
    }


    public BigDecimal getUserFuturesAmt() {
        return this.userFuturesAmt;
    }


    public BigDecimal getEnableFuturesAmt() {
        return this.enableFuturesAmt;
    }


    public BigDecimal getAllProfitAndLose() {
        return this.allProfitAndLose;
    }


    public BigDecimal getAllFreezAmt() {
        return this.allFreezAmt;
    }


    public BigDecimal getAllIndexProfitAndLose() {
        return this.allIndexProfitAndLose;
    }


    public BigDecimal getAllIndexFreezAmt() {
        return this.allIndexFreezAmt;
    }


    public BigDecimal getAllFuturesProfitAndLose() {
        return this.allFuturesProfitAndLose;
    }


    public BigDecimal getAllFuturesFreezAmt() {
        return this.allFuturesFreezAmt;
    }

    public String getSiteLever() {
        return siteLever;
    }

    public void setSiteLever(String siteLever) {
        this.siteLever = siteLever;
    }

    public BigDecimal getTradingAmount() {
        return tradingAmount;
    }

    public void setTradingAmount(BigDecimal tradingAmount) {
        this.tradingAmount = tradingAmount;
    }
}

