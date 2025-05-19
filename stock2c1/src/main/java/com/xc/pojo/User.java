package com.xc.pojo;

import java.beans.ConstructorProperties;
import java.math.BigDecimal;
import java.util.Date;

public class User {
    private Integer id;
    private Integer agentId;
    //机构名字
    private String agentName;
    private String phone;
    //密码
    private String userPwd;
    //资金密码
    private String withPwd;
    //网名
    private String nickName;
    //实名认证名字
    private String realName;
    //身份号
    private String idCard;


    //账户类型 0正常用户  1模拟用户
    private Integer accountType;
    //沪深账户总资产
    private BigDecimal userAmt;
    //沪深账户可用资产
    private BigDecimal enableAmt;

    private BigDecimal sumChargeAmt;

    private BigDecimal sumBuyAmt;


    public void setId(Integer id) {
        this.id = id;
    }

    private String recomPhone;
    private Integer isLock;
    private Integer isLogin;
    private Date regTime;
    private String regIp;
    private String regAddress;
    private String img1Key;
    private String img2Key;
    private String img3Key;
    private Integer isActive;
    private String authMsg;
    private BigDecimal userIndexAmt;
    private BigDecimal enableIndexAmt;
    private BigDecimal userFutAmt;
    private BigDecimal enableFutAmt;
    private String withdrawalPwd;
    /*总操盘金额*/
    private BigDecimal tradingAmount;

    //申购资金
    private String sgzj;
    //冻结资金
    private String djzj;

    public String getWithdrawalPwd() {
        return withdrawalPwd;
    }

    public void setWithdrawalPwd(String withdrawalPwd) {
        this.withdrawalPwd = withdrawalPwd;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public void setWithPwd(String withPwd) {
        this.withPwd = withPwd;
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

    public void setUserAmt(BigDecimal userAmt) {
        this.userAmt = userAmt;
    }

    public void setEnableAmt(BigDecimal enableAmt) {
        this.enableAmt = enableAmt;
    }

    public void setSumChargeAmt(BigDecimal sumChargeAmt) {
        this.sumChargeAmt = sumChargeAmt;
    }

    public void setSumBuyAmt(BigDecimal sumBuyAmt) {
        this.sumBuyAmt = sumBuyAmt;
    }

    public void setRecomPhone(String recomPhone) {
        this.recomPhone = recomPhone;
    }

    public void setIsLock(Integer isLock) {
        this.isLock = isLock;
    }

    public void setIsLogin(Integer isLogin) {
        this.isLogin = isLogin;
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

    public void setUserIndexAmt(BigDecimal userIndexAmt) {
        this.userIndexAmt = userIndexAmt;
    }

    public void setEnableIndexAmt(BigDecimal enableIndexAmt) {
        this.enableIndexAmt = enableIndexAmt;
    }

    public String getSgzj() {
        return sgzj;
    }

    public void setSgzj(String sgzj) {
        this.sgzj = sgzj;
    }

    public String getDjzj() {
        return djzj;
    }

    public void setDjzj(String djzj) {
        this.djzj = djzj;
    }

    public void setUserFutAmt(BigDecimal userFutAmt) {
        this.userFutAmt = userFutAmt;
    }

    public void setEnableFutAmt(BigDecimal enableFutAmt) {
        this.enableFutAmt = enableFutAmt;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof User)) return false;
        User other = (User) o;
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
        Object this$userPwd = getUserPwd(), other$userPwd = other.getUserPwd();
        if ((this$userPwd == null) ? (other$userPwd != null) : !this$userPwd.equals(other$userPwd)) return false;
        Object this$withPwd = getWithPwd(), other$withPwd = other.getWithPwd();
        if ((this$withPwd == null) ? (other$withPwd != null) : !this$withPwd.equals(other$withPwd)) return false;
        Object this$nickName = getNickName(), other$nickName = other.getNickName();
        if ((this$nickName == null) ? (other$nickName != null) : !this$nickName.equals(other$nickName)) return false;
        Object this$realName = getRealName(), other$realName = other.getRealName();
        if ((this$realName == null) ? (other$realName != null) : !this$realName.equals(other$realName)) return false;
        Object this$idCard = getIdCard(), other$idCard = other.getIdCard();
        if ((this$idCard == null) ? (other$idCard != null) : !this$idCard.equals(other$idCard)) return false;
        Object this$accountType = getAccountType(), other$accountType = other.getAccountType();
        if ((this$accountType == null) ? (other$accountType != null) : !this$accountType.equals(other$accountType))
            return false;
        Object this$userAmt = getUserAmt(), other$userAmt = other.getUserAmt();
        if ((this$userAmt == null) ? (other$userAmt != null) : !this$userAmt.equals(other$userAmt)) return false;
        Object this$enableAmt = getEnableAmt(), other$enableAmt = other.getEnableAmt();
        if ((this$enableAmt == null) ? (other$enableAmt != null) : !this$enableAmt.equals(other$enableAmt))
            return false;
        Object this$sumChargeAmt = getSumChargeAmt(), other$sumChargeAmt = other.getSumChargeAmt();
        if ((this$sumChargeAmt == null) ? (other$sumChargeAmt != null) : !this$sumChargeAmt.equals(other$sumChargeAmt))
            return false;
        Object this$sumBuyAmt = getSumBuyAmt(), other$sumBuyAmt = other.getSumBuyAmt();
        if ((this$sumBuyAmt == null) ? (other$sumBuyAmt != null) : !this$sumBuyAmt.equals(other$sumBuyAmt))
            return false;
        Object this$recomPhone = getRecomPhone(), other$recomPhone = other.getRecomPhone();
        if ((this$recomPhone == null) ? (other$recomPhone != null) : !this$recomPhone.equals(other$recomPhone))
            return false;
        Object this$isLock = getIsLock(), other$isLock = other.getIsLock();
        if ((this$isLock == null) ? (other$isLock != null) : !this$isLock.equals(other$isLock)) return false;
        Object this$isLogin = getIsLogin(), other$isLogin = other.getIsLogin();
        if ((this$isLogin == null) ? (other$isLogin != null) : !this$isLogin.equals(other$isLogin)) return false;
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
        Object this$userIndexAmt = getUserIndexAmt(), other$userIndexAmt = other.getUserIndexAmt();
        if ((this$userIndexAmt == null) ? (other$userIndexAmt != null) : !this$userIndexAmt.equals(other$userIndexAmt))
            return false;
        Object this$enableIndexAmt = getEnableIndexAmt(), other$enableIndexAmt = other.getEnableIndexAmt();
        if ((this$enableIndexAmt == null) ? (other$enableIndexAmt != null) : !this$enableIndexAmt.equals(other$enableIndexAmt))
            return false;
        Object this$userFutAmt = getUserFutAmt(), other$userFutAmt = other.getUserFutAmt();
        if ((this$userFutAmt == null) ? (other$userFutAmt != null) : !this$userFutAmt.equals(other$userFutAmt))
            return false;

        Object this$enableFutAmt = getEnableFutAmt(), other$enableFutAmt = other.getEnableFutAmt();
         if ((this$enableFutAmt == null) ? (other$enableFutAmt != null) : !this$enableFutAmt.equals(other$enableFutAmt))
             return false;

        Object this$withdrawalPwd = getWithdrawalPwd(), other$withdrawalPwd = other.getWithdrawalPwd();
        return !((this$withdrawalPwd == null) ? (other$withdrawalPwd != null) : !this$withdrawalPwd.equals(other$withdrawalPwd));

    }

    protected boolean canEqual(Object other) {
        return other instanceof User;
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
        Object $userPwd = getUserPwd();
        result = result * 59 + (($userPwd == null) ? 43 : $userPwd.hashCode());
        Object $withPwd = getWithPwd();
        result = result * 59 + (($withPwd == null) ? 43 : $withPwd.hashCode());
        Object $nickName = getNickName();
        result = result * 59 + (($nickName == null) ? 43 : $nickName.hashCode());
        Object $realName = getRealName();
        result = result * 59 + (($realName == null) ? 43 : $realName.hashCode());
        Object $idCard = getIdCard();
        result = result * 59 + (($idCard == null) ? 43 : $idCard.hashCode());
        Object $accountType = getAccountType();
        result = result * 59 + (($accountType == null) ? 43 : $accountType.hashCode());
        Object $userAmt = getUserAmt();
        result = result * 59 + (($userAmt == null) ? 43 : $userAmt.hashCode());
        Object $enableAmt = getEnableAmt();
        result = result * 59 + (($enableAmt == null) ? 43 : $enableAmt.hashCode());
        Object $sumChargeAmt = getSumChargeAmt();
        result = result * 59 + (($sumChargeAmt == null) ? 43 : $sumChargeAmt.hashCode());
        Object $sumBuyAmt = getSumBuyAmt();
        result = result * 59 + (($sumBuyAmt == null) ? 43 : $sumBuyAmt.hashCode());
        Object $recomPhone = getRecomPhone();
        result = result * 59 + (($recomPhone == null) ? 43 : $recomPhone.hashCode());
        Object $isLock = getIsLock();
        result = result * 59 + (($isLock == null) ? 43 : $isLock.hashCode());
        Object $isLogin = getIsLogin();
        result = result * 59 + (($isLogin == null) ? 43 : $isLogin.hashCode());
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
        Object $userIndexAmt = getUserIndexAmt();
        result = result * 59 + (($userIndexAmt == null) ? 43 : $userIndexAmt.hashCode());
        Object $enableIndexAmt = getEnableIndexAmt();
        result = result * 59 + (($enableIndexAmt == null) ? 43 : $enableIndexAmt.hashCode());
        Object $userFutAmt = getUserFutAmt();
        result = result * 59 + (($userFutAmt == null) ? 43 : $userFutAmt.hashCode());
        Object $enableFutAmt = getEnableFutAmt();
        result= result * 59 + (($enableFutAmt == null) ? 43 : $enableFutAmt.hashCode());

        Object $withdrawalPwd = getWithdrawalPwd();
        return result * 59 + (($withdrawalPwd == null) ? 43 : $withdrawalPwd.hashCode());
    }

    public String toString() {
        return "User(id=" + getId() + ", agentId=" + getAgentId() + ", agentName=" + getAgentName() + ", phone=" + getPhone() + ", userPwd=" + getUserPwd() + ", withPwd=" + getWithPwd() + ", nickName=" + getNickName() + ", realName=" + getRealName() + ", idCard=" + getIdCard() + ", accountType=" + getAccountType() + ", userAmt=" + getUserAmt() + ", enableAmt=" + getEnableAmt() + ", sumChargeAmt=" + getSumChargeAmt() + ", sumBuyAmt=" + getSumBuyAmt() + ", recomPhone=" + getRecomPhone() + ", isLock=" + getIsLock() + ", isLogin=" + getIsLogin() + ", regTime=" + getRegTime() + ", regIp=" + getRegIp() + ", regAddress=" + getRegAddress() + ", img1Key=" + getImg1Key() + ", img2Key=" + getImg2Key() + ", img3Key=" + getImg3Key() + ", isActive=" + getIsActive() + ", authMsg=" + getAuthMsg() + ", userIndexAmt=" + getUserIndexAmt() + ", enableIndexAmt=" + getEnableIndexAmt() + ", userFutAmt=" + getUserFutAmt() + ", enableFutAmt=" + getEnableFutAmt() +", withdrawalPwd=" + getWithdrawalPwd() +", tradingAmount=" + getTradingAmount() + ")";
    }

    public User() {
    }

    @ConstructorProperties({"id", "agentId", "agentName", "phone", "userPwd", "withPwd", "nickName", "realName", "idCard", "accountType", "userAmt", "enableAmt", "sumChargeAmt", "sumBuyAmt", "recomPhone", "isLock", "isLogin", "regTime", "regIp", "regAddress", "img1Key", "img2Key", "img3Key", "isActive", "authMsg", "userIndexAmt", "enableIndexAmt", "userFutAmt", "enableFutAmt", "withdrawalPwd","tradingAmount","djzj","sgzj"})
    public User(Integer id, Integer agentId, String agentName, String phone, String userPwd, String withPwd, String nickName, String realName, String idCard, Integer accountType, BigDecimal userAmt, BigDecimal enableAmt, BigDecimal sumChargeAmt, BigDecimal sumBuyAmt, String recomPhone, Integer isLock, Integer isLogin, Date regTime, String regIp, String regAddress, String img1Key, String img2Key, String img3Key, Integer isActive, String authMsg, BigDecimal userIndexAmt, BigDecimal enableIndexAmt, BigDecimal userFutAmt, BigDecimal enableFutAmt, String withdrawalPwd, BigDecimal tradingAmount,String djzj,String sgzj) {
        this.id = id;
        this.agentId = agentId;
        this.agentName = agentName;
        this.phone = phone;
        this.userPwd = userPwd;
        this.withPwd = withPwd;
        this.nickName = nickName;
        this.realName = realName;
        this.idCard = idCard;
        this.accountType = accountType;
        this.userAmt = userAmt;
        this.enableAmt = enableAmt;
        this.sumChargeAmt = sumChargeAmt;
        this.sumBuyAmt = sumBuyAmt;
        this.recomPhone = recomPhone;
        this.isLock = isLock;
        this.isLogin = isLogin;
        this.regTime = regTime;
        this.regIp = regIp;
        this.regAddress = regAddress;
        this.img1Key = img1Key;
        this.img2Key = img2Key;
        this.img3Key = img3Key;
        this.isActive = isActive;
        this.authMsg = authMsg;
        this.userIndexAmt = userIndexAmt;
        this.enableIndexAmt = enableIndexAmt;
        this.userFutAmt = userFutAmt;
        this.enableFutAmt = enableFutAmt;
        this.withdrawalPwd= withdrawalPwd;
        this.tradingAmount = tradingAmount;
        this.djzj=djzj;
        this.sgzj=sgzj;
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


    public String getUserPwd() {
        return this.userPwd;
    }


    public String getWithPwd() {
        return this.withPwd;
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


    public BigDecimal getUserAmt() {
        return this.userAmt;
    }


    public BigDecimal getEnableAmt() {
        return this.enableAmt;
    }


    public BigDecimal getSumChargeAmt() {
        return this.sumChargeAmt;
    }


    public BigDecimal getSumBuyAmt() {
        return this.sumBuyAmt;
    }


    public String getRecomPhone() {
        return this.recomPhone;
    }


    public Integer getIsLock() {
        return this.isLock;
    }


    public Integer getIsLogin() {
        return this.isLogin;
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


    public BigDecimal getUserIndexAmt() {
        return this.userIndexAmt;
    }


    public BigDecimal getEnableIndexAmt() {
        return this.enableIndexAmt;
    }


    public BigDecimal getUserFutAmt() {
        return this.userFutAmt;
    }


    public BigDecimal getEnableFutAmt() {
        return this.enableFutAmt;
    }

    public BigDecimal getTradingAmount() {
        return tradingAmount;
    }

    public void setTradingAmount(BigDecimal tradingAmount) {
        this.tradingAmount = tradingAmount;
    }
}
