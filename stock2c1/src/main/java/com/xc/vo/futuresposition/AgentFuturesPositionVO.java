package com.xc.vo.futuresposition;

import java.math.BigDecimal;
import java.util.Date;

public class AgentFuturesPositionVO {
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
    private String orderDirection;

    public void setId(Integer id) {
        this.id = id;
    }

    private Integer orderNum;
    private Integer futuresStandard;
    private String futuresUnit;
    private BigDecimal allDepositAmt;
    private BigDecimal orderFee;
    private String lockMsg;
    private Integer isLock;
    private BigDecimal allProfitAndLose;
    private BigDecimal profitAndLose;
    private String nowPrice;
    private BigDecimal coinRate;
    private String coinCode;
    private BigDecimal buyRate;
    private BigDecimal sellRate;

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

    public void setAllProfitAndLose(BigDecimal allProfitAndLose) {
        this.allProfitAndLose = allProfitAndLose;
    }

    public void setProfitAndLose(BigDecimal profitAndLose) {
        this.profitAndLose = profitAndLose;
    }

    public void setNowPrice(String nowPrice) {
        this.nowPrice = nowPrice;
    }

    public void setCoinRate(BigDecimal coinRate) {
        this.coinRate = coinRate;
    }

    public void setCoinCode(String coinCode) {
        this.coinCode = coinCode;
    }

    public void setBuyRate(BigDecimal buyRate) {
        this.buyRate = buyRate;
    }

    public void setSellRate(BigDecimal sellRate) {
        this.sellRate = sellRate;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof AgentFuturesPositionVO)) return false;
        AgentFuturesPositionVO other = (AgentFuturesPositionVO) o;
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
        Object this$allProfitAndLose = getAllProfitAndLose(), other$allProfitAndLose = other.getAllProfitAndLose();
        if ((this$allProfitAndLose == null) ? (other$allProfitAndLose != null) : !this$allProfitAndLose.equals(other$allProfitAndLose))
            return false;
        Object this$profitAndLose = getProfitAndLose(), other$profitAndLose = other.getProfitAndLose();
        if ((this$profitAndLose == null) ? (other$profitAndLose != null) : !this$profitAndLose.equals(other$profitAndLose))
            return false;
        Object this$nowPrice = getNowPrice(), other$nowPrice = other.getNowPrice();
        if ((this$nowPrice == null) ? (other$nowPrice != null) : !this$nowPrice.equals(other$nowPrice)) return false;
        Object this$coinRate = getCoinRate(), other$coinRate = other.getCoinRate();
        if ((this$coinRate == null) ? (other$coinRate != null) : !this$coinRate.equals(other$coinRate)) return false;
        Object this$coinCode = getCoinCode(), other$coinCode = other.getCoinCode();
        if ((this$coinCode == null) ? (other$coinCode != null) : !this$coinCode.equals(other$coinCode)) return false;
        Object this$buyRate = getBuyRate(), other$buyRate = other.getBuyRate();
        if ((this$buyRate == null) ? (other$buyRate != null) : !this$buyRate.equals(other$buyRate)) return false;
        Object this$sellRate = getSellRate(), other$sellRate = other.getSellRate();
        return !((this$sellRate == null) ? (other$sellRate != null) : !this$sellRate.equals(other$sellRate));
    }

    protected boolean canEqual(Object other) {
        return other instanceof AgentFuturesPositionVO;
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
        Object $allProfitAndLose = getAllProfitAndLose();
        result = result * 59 + (($allProfitAndLose == null) ? 43 : $allProfitAndLose.hashCode());
        Object $profitAndLose = getProfitAndLose();
        result = result * 59 + (($profitAndLose == null) ? 43 : $profitAndLose.hashCode());
        Object $nowPrice = getNowPrice();
        result = result * 59 + (($nowPrice == null) ? 43 : $nowPrice.hashCode());
        Object $coinRate = getCoinRate();
        result = result * 59 + (($coinRate == null) ? 43 : $coinRate.hashCode());
        Object $coinCode = getCoinCode();
        result = result * 59 + (($coinCode == null) ? 43 : $coinCode.hashCode());
        Object $buyRate = getBuyRate();
        result = result * 59 + (($buyRate == null) ? 43 : $buyRate.hashCode());
        Object $sellRate = getSellRate();
        return result * 59 + (($sellRate == null) ? 43 : $sellRate.hashCode());
    }

    public String toString() {
        return "AgentFuturesPositionVO(id=" + getId() + ", positionType=" + getPositionType() + ", positionSn=" + getPositionSn() + ", userId=" + getUserId() + ", realName=" + getRealName() + ", agentId=" + getAgentId() + ", futuresName=" + getFuturesName() + ", futuresCode=" + getFuturesCode() + ", futuresGid=" + getFuturesGid() + ", buyOrderTime=" + getBuyOrderTime() + ", buyOrderPrice=" + getBuyOrderPrice() + ", sellOrderTime=" + getSellOrderTime() + ", sellOrderPrice=" + getSellOrderPrice() + ", orderDirection=" + getOrderDirection() + ", orderNum=" + getOrderNum() + ", futuresStandard=" + getFuturesStandard() + ", futuresUnit=" + getFuturesUnit() + ", allDepositAmt=" + getAllDepositAmt() + ", orderFee=" + getOrderFee() + ", lockMsg=" + getLockMsg() + ", isLock=" + getIsLock() + ", allProfitAndLose=" + getAllProfitAndLose() + ", profitAndLose=" + getProfitAndLose() + ", nowPrice=" + getNowPrice() + ", coinRate=" + getCoinRate() + ", coinCode=" + getCoinCode() + ", buyRate=" + getBuyRate() + ", sellRate=" + getSellRate() + ")";
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

    public BigDecimal getAllProfitAndLose() {
        return this.allProfitAndLose;
    }

    public BigDecimal getProfitAndLose() {
        return this.profitAndLose;
    }


    public String getNowPrice() {
        return this.nowPrice;
    }


    public BigDecimal getCoinRate() {
        return this.coinRate;
    }

    public String getCoinCode() {
        return this.coinCode;
    }


    public BigDecimal getBuyRate() {
        return this.buyRate;
    }

    public BigDecimal getSellRate() {
        return this.sellRate;
    }
}

