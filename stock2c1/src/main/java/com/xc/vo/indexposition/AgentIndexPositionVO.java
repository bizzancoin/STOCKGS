package com.xc.vo.indexposition;

import java.math.BigDecimal;
import java.util.Date;

public class AgentIndexPositionVO {
    private Integer id;
    private Integer positionType;

    private String positionSn;

    private Integer userId;

    private String realName;

    private Integer agentId;

    private String indexName;

    private String indexCode;

    private String indexGid;

    private Date buyOrderTime;

    private BigDecimal buyOrderPrice;

    private Date sellOrderTime;


    public void setId(Integer id) {
        this.id = id;
    }

    private BigDecimal sellOrderPrice;
    private String orderDirection;
    private Integer orderNum;
    private BigDecimal allDepositAmt;
    private BigDecimal orderFee;
    private Integer orderStayDays;
    private BigDecimal eachPoint;
    private BigDecimal profitAndLose;
    private BigDecimal allProfitAndLose;
    private Integer isLock;
    private String lockMsg;
    private String now_price;

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

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public void setIndexCode(String indexCode) {
        this.indexCode = indexCode;
    }

    public void setIndexGid(String indexGid) {
        this.indexGid = indexGid;
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

    public void setAllDepositAmt(BigDecimal allDepositAmt) {
        this.allDepositAmt = allDepositAmt;
    }

    public void setOrderFee(BigDecimal orderFee) {
        this.orderFee = orderFee;
    }

    public void setOrderStayDays(Integer orderStayDays) {
        this.orderStayDays = orderStayDays;
    }

    public void setEachPoint(BigDecimal eachPoint) {
        this.eachPoint = eachPoint;
    }

    public void setProfitAndLose(BigDecimal profitAndLose) {
        this.profitAndLose = profitAndLose;
    }

    public void setAllProfitAndLose(BigDecimal allProfitAndLose) {
        this.allProfitAndLose = allProfitAndLose;
    }

    public void setIsLock(Integer isLock) {
        this.isLock = isLock;
    }

    public void setLockMsg(String lockMsg) {
        this.lockMsg = lockMsg;
    }

    public void setNow_price(String now_price) {
        this.now_price = now_price;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof AgentIndexPositionVO)) return false;
        AgentIndexPositionVO other = (AgentIndexPositionVO) o;
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
        Object this$indexName = getIndexName(), other$indexName = other.getIndexName();
        if ((this$indexName == null) ? (other$indexName != null) : !this$indexName.equals(other$indexName))
            return false;
        Object this$indexCode = getIndexCode(), other$indexCode = other.getIndexCode();
        if ((this$indexCode == null) ? (other$indexCode != null) : !this$indexCode.equals(other$indexCode))
            return false;
        Object this$indexGid = getIndexGid(), other$indexGid = other.getIndexGid();
        if ((this$indexGid == null) ? (other$indexGid != null) : !this$indexGid.equals(other$indexGid)) return false;
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
        Object this$allDepositAmt = getAllDepositAmt(), other$allDepositAmt = other.getAllDepositAmt();
        if ((this$allDepositAmt == null) ? (other$allDepositAmt != null) : !this$allDepositAmt.equals(other$allDepositAmt))
            return false;
        Object this$orderFee = getOrderFee(), other$orderFee = other.getOrderFee();
        if ((this$orderFee == null) ? (other$orderFee != null) : !this$orderFee.equals(other$orderFee)) return false;
        Object this$orderStayDays = getOrderStayDays(), other$orderStayDays = other.getOrderStayDays();
        if ((this$orderStayDays == null) ? (other$orderStayDays != null) : !this$orderStayDays.equals(other$orderStayDays))
            return false;
        Object this$eachPoint = getEachPoint(), other$eachPoint = other.getEachPoint();
        if ((this$eachPoint == null) ? (other$eachPoint != null) : !this$eachPoint.equals(other$eachPoint))
            return false;
        Object this$profitAndLose = getProfitAndLose(), other$profitAndLose = other.getProfitAndLose();
        if ((this$profitAndLose == null) ? (other$profitAndLose != null) : !this$profitAndLose.equals(other$profitAndLose))
            return false;
        Object this$allProfitAndLose = getAllProfitAndLose(), other$allProfitAndLose = other.getAllProfitAndLose();
        if ((this$allProfitAndLose == null) ? (other$allProfitAndLose != null) : !this$allProfitAndLose.equals(other$allProfitAndLose))
            return false;
        Object this$isLock = getIsLock(), other$isLock = other.getIsLock();
        if ((this$isLock == null) ? (other$isLock != null) : !this$isLock.equals(other$isLock)) return false;
        Object this$lockMsg = getLockMsg(), other$lockMsg = other.getLockMsg();
        if ((this$lockMsg == null) ? (other$lockMsg != null) : !this$lockMsg.equals(other$lockMsg)) return false;
        Object this$now_price = getNow_price(), other$now_price = other.getNow_price();
        return !((this$now_price == null) ? (other$now_price != null) : !this$now_price.equals(other$now_price));
    }

    protected boolean canEqual(Object other) {
        return other instanceof AgentIndexPositionVO;
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
        Object $indexName = getIndexName();
        result = result * 59 + (($indexName == null) ? 43 : $indexName.hashCode());
        Object $indexCode = getIndexCode();
        result = result * 59 + (($indexCode == null) ? 43 : $indexCode.hashCode());
        Object $indexGid = getIndexGid();
        result = result * 59 + (($indexGid == null) ? 43 : $indexGid.hashCode());
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
        Object $allDepositAmt = getAllDepositAmt();
        result = result * 59 + (($allDepositAmt == null) ? 43 : $allDepositAmt.hashCode());
        Object $orderFee = getOrderFee();
        result = result * 59 + (($orderFee == null) ? 43 : $orderFee.hashCode());
        Object $orderStayDays = getOrderStayDays();
        result = result * 59 + (($orderStayDays == null) ? 43 : $orderStayDays.hashCode());
        Object $eachPoint = getEachPoint();
        result = result * 59 + (($eachPoint == null) ? 43 : $eachPoint.hashCode());
        Object $profitAndLose = getProfitAndLose();
        result = result * 59 + (($profitAndLose == null) ? 43 : $profitAndLose.hashCode());
        Object $allProfitAndLose = getAllProfitAndLose();
        result = result * 59 + (($allProfitAndLose == null) ? 43 : $allProfitAndLose.hashCode());
        Object $isLock = getIsLock();
        result = result * 59 + (($isLock == null) ? 43 : $isLock.hashCode());
        Object $lockMsg = getLockMsg();
        result = result * 59 + (($lockMsg == null) ? 43 : $lockMsg.hashCode());
        Object $now_price = getNow_price();
        return result * 59 + (($now_price == null) ? 43 : $now_price.hashCode());
    }

    public String toString() {
        return "AgentIndexPositionVO(id=" + getId() + ", positionType=" + getPositionType() + ", positionSn=" + getPositionSn() + ", userId=" + getUserId() + ", realName=" + getRealName() + ", agentId=" + getAgentId() + ", indexName=" + getIndexName() + ", indexCode=" + getIndexCode() + ", indexGid=" + getIndexGid() + ", buyOrderTime=" + getBuyOrderTime() + ", buyOrderPrice=" + getBuyOrderPrice() + ", sellOrderTime=" + getSellOrderTime() + ", sellOrderPrice=" + getSellOrderPrice() + ", orderDirection=" + getOrderDirection() + ", orderNum=" + getOrderNum() + ", allDepositAmt=" + getAllDepositAmt() + ", orderFee=" + getOrderFee() + ", orderStayDays=" + getOrderStayDays() + ", eachPoint=" + getEachPoint() + ", profitAndLose=" + getProfitAndLose() + ", allProfitAndLose=" + getAllProfitAndLose() + ", isLock=" + getIsLock() + ", lockMsg=" + getLockMsg() + ", now_price=" + getNow_price() + ")";
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


    public String getIndexName() {
        return this.indexName;
    }


    public String getIndexCode() {
        return this.indexCode;
    }


    public String getIndexGid() {
        return this.indexGid;
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


    public BigDecimal getAllDepositAmt() {
        return this.allDepositAmt;
    }


    public BigDecimal getOrderFee() {
        return this.orderFee;
    }


    public Integer getOrderStayDays() {
        return this.orderStayDays;
    }


    public BigDecimal getEachPoint() {
        return this.eachPoint;
    }


    public BigDecimal getProfitAndLose() {
        return this.profitAndLose;
    }


    public BigDecimal getAllProfitAndLose() {
        return this.allProfitAndLose;
    }


    public Integer getIsLock() {
        return this.isLock;
    }


    public String getLockMsg() {
        return this.lockMsg;
    }


    public String getNow_price() {
        return this.now_price;
    }
}
