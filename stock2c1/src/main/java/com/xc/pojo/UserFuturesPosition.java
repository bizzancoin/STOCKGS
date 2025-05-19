
package com.xc.pojo;

import java.beans.ConstructorProperties;
import java.math.BigDecimal;
import java.util.Date;

public class UserFuturesPosition {
    private Integer id;
    private Integer positionType;
    private String positionSn;
    private Integer userId;
    private String realName;
    private Integer agentId;

    private String futuresName;

    private String futuresCode;

    private String futuresGid;

    private Date buyOrderTime;

    private BigDecimal buyOrderPrice;

    private Date sellOrderTime;

    private BigDecimal sellOrderPrice;


    public void setId(Integer id) {
        this.id = id;
    }

    private String orderDirection;
    private Integer orderNum;
    private Integer futuresStandard;
    private String futuresUnit;
    private BigDecimal allDepositAmt;
    private BigDecimal orderFee;
    private String lockMsg;
    private Integer isLock;
    private BigDecimal buyRate;
    private BigDecimal sellRate;
    private BigDecimal allProfitAndLose;
    private BigDecimal profitAndLose;
    private String coinCode;
    /*杠杆倍数*/
    private Integer orderLever;

    public void setPositionType(Integer positionType) {
        this.positionType = positionType;
    }

    public void setPositionSn(String positionSn) {
        this.positionSn = positionSn;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public void setFuturesName(String futuresName) {
        this.futuresName = futuresName;
    }

    public void setFuturesCode(String futuresCode) {
        this.futuresCode = futuresCode;
    }

    public void setFuturesGid(String futuresGid) {
        this.futuresGid = futuresGid;
    }

    public void setBuyOrderTime(Date buyOrderTime) {
        this.buyOrderTime = buyOrderTime;
    }

    public void setBuyOrderPrice(BigDecimal buyOrderPrice) {
        this.buyOrderPrice = buyOrderPrice;
    }

    public void setSellOrderTime(Date sellOrderTime) {
        this.sellOrderTime = sellOrderTime;
    }

    public void setSellOrderPrice(BigDecimal sellOrderPrice) {
        this.sellOrderPrice = sellOrderPrice;
    }

    public void setOrderDirection(String orderDirection) {
        this.orderDirection = orderDirection;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public void setFuturesStandard(Integer futuresStandard) {
        this.futuresStandard = futuresStandard;
    }

    public void setFuturesUnit(String futuresUnit) {
        this.futuresUnit = futuresUnit;
    }

    public void setAllDepositAmt(BigDecimal allDepositAmt) {
        this.allDepositAmt = allDepositAmt;
    }

    public void setOrderFee(BigDecimal orderFee) {
        this.orderFee = orderFee;
    }

    public void setLockMsg(String lockMsg) {
        this.lockMsg = lockMsg;
    }

    public void setIsLock(Integer isLock) {
        this.isLock = isLock;
    }

    public void setBuyRate(BigDecimal buyRate) {
        this.buyRate = buyRate;
    }

    public void setSellRate(BigDecimal sellRate) {
        this.sellRate = sellRate;
    }

    public void setAllProfitAndLose(BigDecimal allProfitAndLose) {
        this.allProfitAndLose = allProfitAndLose;
    }

    public void setProfitAndLose(BigDecimal profitAndLose) {
        this.profitAndLose = profitAndLose;
    }

    public void setCoinCode(String coinCode) {
        this.coinCode = coinCode;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof UserFuturesPosition)) return false;
        UserFuturesPosition other = (UserFuturesPosition) o;
        if (!other.canEqual(this)) return false;
        Object this$id = getId(), other$id = other.getId();
        if ((this$id == null) ? (other$id != null) : !this$id.equals(other$id)) return false;
        Object this$positionType = getPositionType(), other$positionType = other.getPositionType();
        if ((this$positionType == null) ? (other$positionType != null) : !this$positionType.equals(other$positionType))
            return false;
        Object this$positionSn = getPositionSn(), other$positionSn = other.getPositionSn();
        if ((this$positionSn == null) ? (other$positionSn != null) : !this$positionSn.equals(other$positionSn))
            return false;
        Object this$userId = getUserId(), other$userId = other.getUserId();
        if ((this$userId == null) ? (other$userId != null) : !this$userId.equals(other$userId)) return false;
        Object this$realName = getRealName(), other$realName = other.getRealName();
        if ((this$realName == null) ? (other$realName != null) : !this$realName.equals(other$realName)) return false;
        Object this$agentId = getAgentId(), other$agentId = other.getAgentId();
        if ((this$agentId == null) ? (other$agentId != null) : !this$agentId.equals(other$agentId)) return false;
        Object this$futuresName = getFuturesName(), other$futuresName = other.getFuturesName();
        if ((this$futuresName == null) ? (other$futuresName != null) : !this$futuresName.equals(other$futuresName))
            return false;
        Object this$futuresCode = getFuturesCode(), other$futuresCode = other.getFuturesCode();
        if ((this$futuresCode == null) ? (other$futuresCode != null) : !this$futuresCode.equals(other$futuresCode))
            return false;
        Object this$futuresGid = getFuturesGid(), other$futuresGid = other.getFuturesGid();
        if ((this$futuresGid == null) ? (other$futuresGid != null) : !this$futuresGid.equals(other$futuresGid))
            return false;
        Object this$buyOrderTime = getBuyOrderTime(), other$buyOrderTime = other.getBuyOrderTime();
        if ((this$buyOrderTime == null) ? (other$buyOrderTime != null) : !this$buyOrderTime.equals(other$buyOrderTime))
            return false;
        Object this$buyOrderPrice = getBuyOrderPrice(), other$buyOrderPrice = other.getBuyOrderPrice();
        if ((this$buyOrderPrice == null) ? (other$buyOrderPrice != null) : !this$buyOrderPrice.equals(other$buyOrderPrice))
            return false;
        Object this$sellOrderTime = getSellOrderTime(), other$sellOrderTime = other.getSellOrderTime();
        if ((this$sellOrderTime == null) ? (other$sellOrderTime != null) : !this$sellOrderTime.equals(other$sellOrderTime))
            return false;
        Object this$sellOrderPrice = getSellOrderPrice(), other$sellOrderPrice = other.getSellOrderPrice();
        if ((this$sellOrderPrice == null) ? (other$sellOrderPrice != null) : !this$sellOrderPrice.equals(other$sellOrderPrice))
            return false;
        Object this$orderDirection = getOrderDirection(), other$orderDirection = other.getOrderDirection();
        if ((this$orderDirection == null) ? (other$orderDirection != null) : !this$orderDirection.equals(other$orderDirection))
            return false;
        Object this$orderNum = getOrderNum(), other$orderNum = other.getOrderNum();
        if ((this$orderNum == null) ? (other$orderNum != null) : !this$orderNum.equals(other$orderNum)) return false;
        Object this$futuresStandard = getFuturesStandard(), other$futuresStandard = other.getFuturesStandard();
        if ((this$futuresStandard == null) ? (other$futuresStandard != null) : !this$futuresStandard.equals(other$futuresStandard))
            return false;
        Object this$futuresUnit = getFuturesUnit(), other$futuresUnit = other.getFuturesUnit();
        if ((this$futuresUnit == null) ? (other$futuresUnit != null) : !this$futuresUnit.equals(other$futuresUnit))
            return false;
        Object this$allDepositAmt = getAllDepositAmt(), other$allDepositAmt = other.getAllDepositAmt();
        if ((this$allDepositAmt == null) ? (other$allDepositAmt != null) : !this$allDepositAmt.equals(other$allDepositAmt))
            return false;
        Object this$orderFee = getOrderFee(), other$orderFee = other.getOrderFee();
        if ((this$orderFee == null) ? (other$orderFee != null) : !this$orderFee.equals(other$orderFee)) return false;
        Object this$lockMsg = getLockMsg(), other$lockMsg = other.getLockMsg();
        if ((this$lockMsg == null) ? (other$lockMsg != null) : !this$lockMsg.equals(other$lockMsg)) return false;
        Object this$isLock = getIsLock(), other$isLock = other.getIsLock();
        if ((this$isLock == null) ? (other$isLock != null) : !this$isLock.equals(other$isLock)) return false;
        Object this$buyRate = getBuyRate(), other$buyRate = other.getBuyRate();
        if ((this$buyRate == null) ? (other$buyRate != null) : !this$buyRate.equals(other$buyRate)) return false;
        Object this$sellRate = getSellRate(), other$sellRate = other.getSellRate();
        if ((this$sellRate == null) ? (other$sellRate != null) : !this$sellRate.equals(other$sellRate)) return false;
        Object this$allProfitAndLose = getAllProfitAndLose(), other$allProfitAndLose = other.getAllProfitAndLose();
        if ((this$allProfitAndLose == null) ? (other$allProfitAndLose != null) : !this$allProfitAndLose.equals(other$allProfitAndLose))
            return false;
        Object this$profitAndLose = getProfitAndLose(), other$profitAndLose = other.getProfitAndLose();
        if ((this$profitAndLose == null) ? (other$profitAndLose != null) : !this$profitAndLose.equals(other$profitAndLose))
            return false;
        Object this$coinCode = getCoinCode(), other$coinCode = other.getCoinCode();
        return !((this$coinCode == null) ? (other$coinCode != null) : !this$coinCode.equals(other$coinCode));
    }

    protected boolean canEqual(Object other) {
        return other instanceof UserFuturesPosition;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $id = getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        Object $positionType = getPositionType();
        result = result * 59 + (($positionType == null) ? 43 : $positionType.hashCode());
        Object $positionSn = getPositionSn();
        result = result * 59 + (($positionSn == null) ? 43 : $positionSn.hashCode());
        Object $userId = getUserId();
        result = result * 59 + (($userId == null) ? 43 : $userId.hashCode());
        Object $realName = getRealName();
        result = result * 59 + (($realName == null) ? 43 : $realName.hashCode());
        Object $agentId = getAgentId();
        result = result * 59 + (($agentId == null) ? 43 : $agentId.hashCode());
        Object $futuresName = getFuturesName();
        result = result * 59 + (($futuresName == null) ? 43 : $futuresName.hashCode());
        Object $futuresCode = getFuturesCode();
        result = result * 59 + (($futuresCode == null) ? 43 : $futuresCode.hashCode());
        Object $futuresGid = getFuturesGid();
        result = result * 59 + (($futuresGid == null) ? 43 : $futuresGid.hashCode());
        Object $buyOrderTime = getBuyOrderTime();
        result = result * 59 + (($buyOrderTime == null) ? 43 : $buyOrderTime.hashCode());
        Object $buyOrderPrice = getBuyOrderPrice();
        result = result * 59 + (($buyOrderPrice == null) ? 43 : $buyOrderPrice.hashCode());
        Object $sellOrderTime = getSellOrderTime();
        result = result * 59 + (($sellOrderTime == null) ? 43 : $sellOrderTime.hashCode());
        Object $sellOrderPrice = getSellOrderPrice();
        result = result * 59 + (($sellOrderPrice == null) ? 43 : $sellOrderPrice.hashCode());
        Object $orderDirection = getOrderDirection();
        result = result * 59 + (($orderDirection == null) ? 43 : $orderDirection.hashCode());
        Object $orderNum = getOrderNum();
        result = result * 59 + (($orderNum == null) ? 43 : $orderNum.hashCode());
        Object $futuresStandard = getFuturesStandard();
        result = result * 59 + (($futuresStandard == null) ? 43 : $futuresStandard.hashCode());
        Object $futuresUnit = getFuturesUnit();
        result = result * 59 + (($futuresUnit == null) ? 43 : $futuresUnit.hashCode());
        Object $allDepositAmt = getAllDepositAmt();
        result = result * 59 + (($allDepositAmt == null) ? 43 : $allDepositAmt.hashCode());
        Object $orderFee = getOrderFee();
        result = result * 59 + (($orderFee == null) ? 43 : $orderFee.hashCode());
        Object $lockMsg = getLockMsg();
        result = result * 59 + (($lockMsg == null) ? 43 : $lockMsg.hashCode());
        Object $isLock = getIsLock();
        result = result * 59 + (($isLock == null) ? 43 : $isLock.hashCode());
        Object $buyRate = getBuyRate();
        result = result * 59 + (($buyRate == null) ? 43 : $buyRate.hashCode());
        Object $sellRate = getSellRate();
        result = result * 59 + (($sellRate == null) ? 43 : $sellRate.hashCode());
        Object $allProfitAndLose = getAllProfitAndLose();
        result = result * 59 + (($allProfitAndLose == null) ? 43 : $allProfitAndLose.hashCode());
        Object $profitAndLose = getProfitAndLose();
        result = result * 59 + (($profitAndLose == null) ? 43 : $profitAndLose.hashCode());
        Object $coinCode = getCoinCode();
        return result * 59 + (($coinCode == null) ? 43 : $coinCode.hashCode());
    }

    public String toString() {
        return "UserFuturesPosition(id=" + getId() + ", positionType=" + getPositionType() + ", positionSn=" + getPositionSn() + ", userId=" + getUserId() + ", realName=" + getRealName() + ", agentId=" + getAgentId() + ", futuresName=" + getFuturesName() + ", futuresCode=" + getFuturesCode() + ", futuresGid=" + getFuturesGid() + ", buyOrderTime=" + getBuyOrderTime() + ", buyOrderPrice=" + getBuyOrderPrice() + ", sellOrderTime=" + getSellOrderTime() + ", sellOrderPrice=" + getSellOrderPrice() + ", orderDirection=" + getOrderDirection() + ", orderNum=" + getOrderNum() + ", futuresStandard=" + getFuturesStandard() + ", futuresUnit=" + getFuturesUnit() + ", allDepositAmt=" + getAllDepositAmt() + ", orderFee=" + getOrderFee() + ", lockMsg=" + getLockMsg() + ", isLock=" + getIsLock() + ", buyRate=" + getBuyRate() + ", sellRate=" + getSellRate() + ", allProfitAndLose=" + getAllProfitAndLose() + ", profitAndLose=" + getProfitAndLose() + ", coinCode=" + getCoinCode()+ ", orderLever=" + getOrderLever() + ")";
    }

    public UserFuturesPosition() {
    }

    @ConstructorProperties({"id", "positionType", "positionSn", "userId", "realName", "agentId", "futuresName", "futuresCode", "futuresGid", "buyOrderTime", "buyOrderPrice", "sellOrderTime", "sellOrderPrice", "orderDirection", "orderNum", "futuresStandard", "futuresUnit", "allDepositAmt", "orderFee", "lockMsg", "isLock", "buyRate", "sellRate", "allProfitAndLose", "profitAndLose", "coinCode", "orderLever"})

    public UserFuturesPosition(Integer id, Integer positionType, String positionSn, Integer userId, String realName, Integer agentId, String futuresName, String futuresCode, String futuresGid, Date buyOrderTime, BigDecimal buyOrderPrice, Date sellOrderTime, BigDecimal sellOrderPrice, String orderDirection, Integer orderNum, Integer futuresStandard, String futuresUnit, BigDecimal allDepositAmt, BigDecimal orderFee, String lockMsg, Integer isLock, BigDecimal buyRate, BigDecimal sellRate, BigDecimal allProfitAndLose, BigDecimal profitAndLose, String coinCode, Integer orderLever) {
        this.id = id;
        this.positionType = positionType;
        this.positionSn = positionSn;
        this.userId = userId;
        this.realName = realName;
        this.agentId = agentId;
        this.futuresName = futuresName;
        this.futuresCode = futuresCode;
        this.futuresGid = futuresGid;
        this.buyOrderTime = buyOrderTime;
        this.buyOrderPrice = buyOrderPrice;
        this.sellOrderTime = sellOrderTime;
        this.sellOrderPrice = sellOrderPrice;
        this.orderDirection = orderDirection;
        this.orderNum = orderNum;
        this.futuresStandard = futuresStandard;
        this.futuresUnit = futuresUnit;
        this.allDepositAmt = allDepositAmt;
        this.orderFee = orderFee;
        this.lockMsg = lockMsg;
        this.isLock = isLock;
        this.buyRate = buyRate;
        this.sellRate = sellRate;
        this.allProfitAndLose = allProfitAndLose;
        this.profitAndLose = profitAndLose;
        this.coinCode = coinCode;
        this.orderLever = orderLever;
    }


    public Integer getId() {
        return this.id;
    }


    public Integer getPositionType() {
        return this.positionType;
    }


    public String getPositionSn() {
        return this.positionSn;
    }


    public Integer getUserId() {
        return this.userId;
    }

    public String getRealName() {
        return this.realName;
    }


    public Integer getAgentId() {
        return this.agentId;
    }


    public String getFuturesName() {
        return this.futuresName;
    }


    public String getFuturesCode() {
        return this.futuresCode;
    }


    public String getFuturesGid() {
        return this.futuresGid;
    }


    public Date getBuyOrderTime() {
        return this.buyOrderTime;
    }


    public BigDecimal getBuyOrderPrice() {
        return this.buyOrderPrice;
    }


    public Date getSellOrderTime() {
        return this.sellOrderTime;
    }


    public BigDecimal getSellOrderPrice() {
        return this.sellOrderPrice;
    }


    public String getOrderDirection() {
        return this.orderDirection;
    }


    public Integer getOrderNum() {
        return this.orderNum;
    }


    public Integer getFuturesStandard() {
        return this.futuresStandard;
    }


    public String getFuturesUnit() {
        return this.futuresUnit;
    }


    public BigDecimal getAllDepositAmt() {
        return this.allDepositAmt;
    }


    public BigDecimal getOrderFee() {
        return this.orderFee;
    }


    public String getLockMsg() {
        return this.lockMsg;
    }


    public Integer getIsLock() {
        return this.isLock;
    }


    public BigDecimal getBuyRate() {
        return this.buyRate;
    }


    public BigDecimal getSellRate() {
        return this.sellRate;
    }


    public BigDecimal getAllProfitAndLose() {
        return this.allProfitAndLose;
    }


    public BigDecimal getProfitAndLose() {
        return this.profitAndLose;
    }


    public String getCoinCode() {
        return this.coinCode;
    }

    public Integer getOrderLever() {
        return orderLever;
    }

    public void setOrderLever(Integer orderLever) {
        this.orderLever = orderLever;
    }
}

