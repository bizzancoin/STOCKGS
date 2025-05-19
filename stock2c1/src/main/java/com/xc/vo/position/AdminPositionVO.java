package com.xc.vo.position;

import java.math.BigDecimal;
import java.util.Date;

public class AdminPositionVO {
    private Integer id;
    private Integer positionType;
    private String positionSn;
    private Integer userId;
    private String nickName;
    private Integer agentId;

    private String stockName;

    private String stockCode;

    private String stockGid;

    private String stockSpell;

    private String buyOrderId;

    private Date buyOrderTime;

    private BigDecimal buyOrderPrice;

    private String sellOrderId;

    private Date sellOrderTime;

    private BigDecimal sellOrderPrice;


    public void setId(Integer id) {
        this.id = id;
    }

    private BigDecimal profitTargetPrice;
    private BigDecimal stopTargetPrice;
    private String orderDirection;
    private Integer orderNum;
    private Integer orderLever;
    private BigDecimal orderTotalPrice;
    private BigDecimal orderFee;
    private BigDecimal orderSpread;
    private BigDecimal orderStayFee;
    private Integer orderStayDays;
    private BigDecimal profitAndLose;
    private BigDecimal allProfitAndLose;
    private String now_price;
    private Integer isLock;
    private String lockMsg;
    private String stockPlate;

    public void setPositionType(Integer positionType) {
        this.positionType = positionType;
    }

    public void setPositionSn(String positionSn) {
        this.positionSn = positionSn;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public void setStockGid(String stockGid) {
        this.stockGid = stockGid;
    }

    public void setStockSpell(String stockSpell) {
        this.stockSpell = stockSpell;
    }

    public void setBuyOrderId(String buyOrderId) {
        this.buyOrderId = buyOrderId;
    }

    public void setBuyOrderTime(Date buyOrderTime) {
        this.buyOrderTime = buyOrderTime;
    }

    public void setBuyOrderPrice(BigDecimal buyOrderPrice) {
        this.buyOrderPrice = buyOrderPrice;
    }

    public void setSellOrderId(String sellOrderId) {
        this.sellOrderId = sellOrderId;
    }

    public void setSellOrderTime(Date sellOrderTime) {
        this.sellOrderTime = sellOrderTime;
    }

    public void setSellOrderPrice(BigDecimal sellOrderPrice) {
        this.sellOrderPrice = sellOrderPrice;
    }

    public void setProfitTargetPrice(BigDecimal profitTargetPrice) {
        this.profitTargetPrice = profitTargetPrice;
    }

    public void setStopTargetPrice(BigDecimal stopTargetPrice) {
        this.stopTargetPrice = stopTargetPrice;
    }

    public void setOrderDirection(String orderDirection) {
        this.orderDirection = orderDirection;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public void setOrderLever(Integer orderLever) {
        this.orderLever = orderLever;
    }

    public void setOrderTotalPrice(BigDecimal orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public void setOrderFee(BigDecimal orderFee) {
        this.orderFee = orderFee;
    }

    public void setOrderSpread(BigDecimal orderSpread) {
        this.orderSpread = orderSpread;
    }

    public void setOrderStayFee(BigDecimal orderStayFee) {
        this.orderStayFee = orderStayFee;
    }

    public void setOrderStayDays(Integer orderStayDays) {
        this.orderStayDays = orderStayDays;
    }

    public void setProfitAndLose(BigDecimal profitAndLose) {
        this.profitAndLose = profitAndLose;
    }

    public void setAllProfitAndLose(BigDecimal allProfitAndLose) {
        this.allProfitAndLose = allProfitAndLose;
    }

    public void setNow_price(String now_price) {
        this.now_price = now_price;
    }

    public void setIsLock(Integer isLock) {
        this.isLock = isLock;
    }

    public void setLockMsg(String lockMsg) {
        this.lockMsg = lockMsg;
    }

    public void setStockPlate(String stockPlate) {
        this.stockPlate = stockPlate;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof AdminPositionVO)) return false;
        AdminPositionVO other = (AdminPositionVO) o;
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
        Object this$nickName = getNickName(), other$nickName = other.getNickName();
        if ((this$nickName == null) ? (other$nickName != null) : !this$nickName.equals(other$nickName)) return false;
        Object this$agentId = getAgentId(), other$agentId = other.getAgentId();
        if ((this$agentId == null) ? (other$agentId != null) : !this$agentId.equals(other$agentId)) return false;
        Object this$stockName = getStockName(), other$stockName = other.getStockName();
        if ((this$stockName == null) ? (other$stockName != null) : !this$stockName.equals(other$stockName))
            return false;
        Object this$stockCode = getStockCode(), other$stockCode = other.getStockCode();
        if ((this$stockCode == null) ? (other$stockCode != null) : !this$stockCode.equals(other$stockCode))
            return false;
        Object this$stockGid = getStockGid(), other$stockGid = other.getStockGid();
        if ((this$stockGid == null) ? (other$stockGid != null) : !this$stockGid.equals(other$stockGid)) return false;
        Object this$stockSpell = getStockSpell(), other$stockSpell = other.getStockSpell();
        if ((this$stockSpell == null) ? (other$stockSpell != null) : !this$stockSpell.equals(other$stockSpell))
            return false;
        Object this$buyOrderId = getBuyOrderId(), other$buyOrderId = other.getBuyOrderId();
        if ((this$buyOrderId == null) ? (other$buyOrderId != null) : !this$buyOrderId.equals(other$buyOrderId))
            return false;
        Object this$buyOrderTime = getBuyOrderTime(), other$buyOrderTime = other.getBuyOrderTime();
        if ((this$buyOrderTime == null) ? (other$buyOrderTime != null) : !this$buyOrderTime.equals(other$buyOrderTime))
            return false;
        Object this$buyOrderPrice = getBuyOrderPrice(), other$buyOrderPrice = other.getBuyOrderPrice();
        if ((this$buyOrderPrice == null) ? (other$buyOrderPrice != null) : !this$buyOrderPrice.equals(other$buyOrderPrice))
            return false;
        Object this$sellOrderId = getSellOrderId(), other$sellOrderId = other.getSellOrderId();
        if ((this$sellOrderId == null) ? (other$sellOrderId != null) : !this$sellOrderId.equals(other$sellOrderId))
            return false;
        Object this$sellOrderTime = getSellOrderTime(), other$sellOrderTime = other.getSellOrderTime();
        if ((this$sellOrderTime == null) ? (other$sellOrderTime != null) : !this$sellOrderTime.equals(other$sellOrderTime))
            return false;
        Object this$sellOrderPrice = getSellOrderPrice(), other$sellOrderPrice = other.getSellOrderPrice();
        if ((this$sellOrderPrice == null) ? (other$sellOrderPrice != null) : !this$sellOrderPrice.equals(other$sellOrderPrice))
            return false;
        Object this$profitTargetPrice = getProfitTargetPrice(), other$profitTargetPrice = other.getProfitTargetPrice();
        if ((this$profitTargetPrice == null) ? (other$profitTargetPrice != null) : !this$profitTargetPrice.equals(other$profitTargetPrice))
            return false;
        Object this$stopTargetPrice = getStopTargetPrice(), other$stopTargetPrice = other.getStopTargetPrice();
        if ((this$stopTargetPrice == null) ? (other$stopTargetPrice != null) : !this$stopTargetPrice.equals(other$stopTargetPrice))
            return false;
        Object this$orderDirection = getOrderDirection(), other$orderDirection = other.getOrderDirection();
        if ((this$orderDirection == null) ? (other$orderDirection != null) : !this$orderDirection.equals(other$orderDirection))
            return false;
        Object this$orderNum = getOrderNum(), other$orderNum = other.getOrderNum();
        if ((this$orderNum == null) ? (other$orderNum != null) : !this$orderNum.equals(other$orderNum)) return false;
        Object this$orderLever = getOrderLever(), other$orderLever = other.getOrderLever();
        if ((this$orderLever == null) ? (other$orderLever != null) : !this$orderLever.equals(other$orderLever))
            return false;
        Object this$orderTotalPrice = getOrderTotalPrice(), other$orderTotalPrice = other.getOrderTotalPrice();
        if ((this$orderTotalPrice == null) ? (other$orderTotalPrice != null) : !this$orderTotalPrice.equals(other$orderTotalPrice))
            return false;
        Object this$orderFee = getOrderFee(), other$orderFee = other.getOrderFee();
        if ((this$orderFee == null) ? (other$orderFee != null) : !this$orderFee.equals(other$orderFee)) return false;
        Object this$orderSpread = getOrderSpread(), other$orderSpread = other.getOrderSpread();
        if ((this$orderSpread == null) ? (other$orderSpread != null) : !this$orderSpread.equals(other$orderSpread))
            return false;
        Object this$orderStayFee = getOrderStayFee(), other$orderStayFee = other.getOrderStayFee();
        if ((this$orderStayFee == null) ? (other$orderStayFee != null) : !this$orderStayFee.equals(other$orderStayFee))
            return false;
        Object this$orderStayDays = getOrderStayDays(), other$orderStayDays = other.getOrderStayDays();
        if ((this$orderStayDays == null) ? (other$orderStayDays != null) : !this$orderStayDays.equals(other$orderStayDays))
            return false;
        Object this$profitAndLose = getProfitAndLose(), other$profitAndLose = other.getProfitAndLose();
        if ((this$profitAndLose == null) ? (other$profitAndLose != null) : !this$profitAndLose.equals(other$profitAndLose))
            return false;
        Object this$allProfitAndLose = getAllProfitAndLose(), other$allProfitAndLose = other.getAllProfitAndLose();
        if ((this$allProfitAndLose == null) ? (other$allProfitAndLose != null) : !this$allProfitAndLose.equals(other$allProfitAndLose))
            return false;
        Object this$now_price = getNow_price(), other$now_price = other.getNow_price();
        if ((this$now_price == null) ? (other$now_price != null) : !this$now_price.equals(other$now_price))
            return false;
        Object this$isLock = getIsLock(), other$isLock = other.getIsLock();
        if ((this$isLock == null) ? (other$isLock != null) : !this$isLock.equals(other$isLock)) return false;
        Object this$lockMsg = getLockMsg(), other$lockMsg = other.getLockMsg();
        if ((this$lockMsg == null) ? (other$lockMsg != null) : !this$lockMsg.equals(other$lockMsg)) return false;
        Object this$stockPlate = getStockPlate(), other$stockPlate = other.getStockPlate();
        return !((this$stockPlate == null) ? (other$stockPlate != null) : !this$stockPlate.equals(other$stockPlate));
    }

    protected boolean canEqual(Object other) {
        return other instanceof AdminPositionVO;
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
        Object $nickName = getNickName();
        result = result * 59 + (($nickName == null) ? 43 : $nickName.hashCode());
        Object $agentId = getAgentId();
        result = result * 59 + (($agentId == null) ? 43 : $agentId.hashCode());
        Object $stockName = getStockName();
        result = result * 59 + (($stockName == null) ? 43 : $stockName.hashCode());
        Object $stockCode = getStockCode();
        result = result * 59 + (($stockCode == null) ? 43 : $stockCode.hashCode());
        Object $stockGid = getStockGid();
        result = result * 59 + (($stockGid == null) ? 43 : $stockGid.hashCode());
        Object $stockSpell = getStockSpell();
        result = result * 59 + (($stockSpell == null) ? 43 : $stockSpell.hashCode());
        Object $buyOrderId = getBuyOrderId();
        result = result * 59 + (($buyOrderId == null) ? 43 : $buyOrderId.hashCode());
        Object $buyOrderTime = getBuyOrderTime();
        result = result * 59 + (($buyOrderTime == null) ? 43 : $buyOrderTime.hashCode());
        Object $buyOrderPrice = getBuyOrderPrice();
        result = result * 59 + (($buyOrderPrice == null) ? 43 : $buyOrderPrice.hashCode());
        Object $sellOrderId = getSellOrderId();
        result = result * 59 + (($sellOrderId == null) ? 43 : $sellOrderId.hashCode());
        Object $sellOrderTime = getSellOrderTime();
        result = result * 59 + (($sellOrderTime == null) ? 43 : $sellOrderTime.hashCode());
        Object $sellOrderPrice = getSellOrderPrice();
        result = result * 59 + (($sellOrderPrice == null) ? 43 : $sellOrderPrice.hashCode());
        Object $profitTargetPrice = getProfitTargetPrice();
        result = result * 59 + (($profitTargetPrice == null) ? 43 : $profitTargetPrice.hashCode());
        Object $stopTargetPrice = getStopTargetPrice();
        result = result * 59 + (($stopTargetPrice == null) ? 43 : $stopTargetPrice.hashCode());
        Object $orderDirection = getOrderDirection();
        result = result * 59 + (($orderDirection == null) ? 43 : $orderDirection.hashCode());
        Object $orderNum = getOrderNum();
        result = result * 59 + (($orderNum == null) ? 43 : $orderNum.hashCode());
        Object $orderLever = getOrderLever();
        result = result * 59 + (($orderLever == null) ? 43 : $orderLever.hashCode());
        Object $orderTotalPrice = getOrderTotalPrice();
        result = result * 59 + (($orderTotalPrice == null) ? 43 : $orderTotalPrice.hashCode());
        Object $orderFee = getOrderFee();
        result = result * 59 + (($orderFee == null) ? 43 : $orderFee.hashCode());
        Object $orderSpread = getOrderSpread();
        result = result * 59 + (($orderSpread == null) ? 43 : $orderSpread.hashCode());
        Object $orderStayFee = getOrderStayFee();
        result = result * 59 + (($orderStayFee == null) ? 43 : $orderStayFee.hashCode());
        Object $orderStayDays = getOrderStayDays();
        result = result * 59 + (($orderStayDays == null) ? 43 : $orderStayDays.hashCode());
        Object $profitAndLose = getProfitAndLose();
        result = result * 59 + (($profitAndLose == null) ? 43 : $profitAndLose.hashCode());
        Object $allProfitAndLose = getAllProfitAndLose();
        result = result * 59 + (($allProfitAndLose == null) ? 43 : $allProfitAndLose.hashCode());
        Object $now_price = getNow_price();
        result = result * 59 + (($now_price == null) ? 43 : $now_price.hashCode());
        Object $isLock = getIsLock();
        result = result * 59 + (($isLock == null) ? 43 : $isLock.hashCode());
        Object $lockMsg = getLockMsg();
        result = result * 59 + (($lockMsg == null) ? 43 : $lockMsg.hashCode());
        Object $stockPlate = getStockPlate();
        return result * 59 + (($stockPlate == null) ? 43 : $stockPlate.hashCode());
    }

    public String toString() {
        return "AdminPositionVO(id=" + getId() + ", positionType=" + getPositionType() + ", positionSn=" + getPositionSn() + ", userId=" + getUserId() + ", nickName=" + getNickName() + ", agentId=" + getAgentId() + ", stockName=" + getStockName() + ", stockCode=" + getStockCode() + ", stockGid=" + getStockGid() + ", stockSpell=" + getStockSpell() + ", buyOrderId=" + getBuyOrderId() + ", buyOrderTime=" + getBuyOrderTime() + ", buyOrderPrice=" + getBuyOrderPrice() + ", sellOrderId=" + getSellOrderId() + ", sellOrderTime=" + getSellOrderTime() + ", sellOrderPrice=" + getSellOrderPrice() + ", profitTargetPrice=" + getProfitTargetPrice() + ", stopTargetPrice=" + getStopTargetPrice() + ", orderDirection=" + getOrderDirection() + ", orderNum=" + getOrderNum() + ", orderLever=" + getOrderLever() + ", orderTotalPrice=" + getOrderTotalPrice() + ", orderFee=" + getOrderFee() + ", orderSpread=" + getOrderSpread() + ", orderStayFee=" + getOrderStayFee() + ", orderStayDays=" + getOrderStayDays() + ", profitAndLose=" + getProfitAndLose() + ", allProfitAndLose=" + getAllProfitAndLose() + ", now_price=" + getNow_price() + ", isLock=" + getIsLock() + ", lockMsg=" + getLockMsg() + ", stockPlate=" + getStockPlate() + ")";
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


    public String getNickName() {
        return this.nickName;
    }


    public Integer getAgentId() {
        return this.agentId;
    }


    public String getStockName() {
        return this.stockName;
    }


    public String getStockCode() {
        return this.stockCode;
    }


    public String getStockGid() {
        return this.stockGid;
    }


    public String getStockSpell() {
        return this.stockSpell;
    }


    public String getBuyOrderId() {
        return this.buyOrderId;
    }


    public Date getBuyOrderTime() {
        return this.buyOrderTime;
    }


    public BigDecimal getBuyOrderPrice() {
        return this.buyOrderPrice;
    }


    public String getSellOrderId() {
        return this.sellOrderId;
    }


    public Date getSellOrderTime() {
        return this.sellOrderTime;
    }


    public BigDecimal getSellOrderPrice() {
        return this.sellOrderPrice;
    }


    public BigDecimal getProfitTargetPrice() {
        return this.profitTargetPrice;
    }


    public BigDecimal getStopTargetPrice() {
        return this.stopTargetPrice;
    }


    public String getOrderDirection() {
        return this.orderDirection;
    }


    public Integer getOrderNum() {
        return this.orderNum;
    }


    public Integer getOrderLever() {
        return this.orderLever;
    }


    public BigDecimal getOrderTotalPrice() {
        return this.orderTotalPrice;
    }


    public BigDecimal getOrderFee() {
        return this.orderFee;
    }


    public BigDecimal getOrderSpread() {
        return this.orderSpread;
    }


    public BigDecimal getOrderStayFee() {
        return this.orderStayFee;
    }


    public Integer getOrderStayDays() {
        return this.orderStayDays;
    }


    public BigDecimal getProfitAndLose() {
        return this.profitAndLose;
    }


    public BigDecimal getAllProfitAndLose() {
        return this.allProfitAndLose;
    }


    public String getNow_price() {
        return this.now_price;
    }


    public Integer getIsLock() {
        return this.isLock;
    }


    public String getLockMsg() {
        return this.lockMsg;
    }


    public String getStockPlate() {
        return this.stockPlate;
    }
}

