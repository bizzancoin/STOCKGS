package com.xc.vo.agent;


import java.math.BigDecimal;

public class AgentIncomeVO {

    private Integer orderSize;

    private BigDecimal orderFeeAmt;

    private BigDecimal orderProfitAndLose;

    private BigDecimal orderAllAmt;


    public void setOrderSize(Integer orderSize) {
        this.orderSize = orderSize;
    }

    public void setOrderFeeAmt(BigDecimal orderFeeAmt) {
        this.orderFeeAmt = orderFeeAmt;
    }

    public void setOrderProfitAndLose(BigDecimal orderProfitAndLose) {
        this.orderProfitAndLose = orderProfitAndLose;
    }

    public void setOrderAllAmt(BigDecimal orderAllAmt) {
        this.orderAllAmt = orderAllAmt;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof AgentIncomeVO)) return false;
        AgentIncomeVO other = (AgentIncomeVO) o;
        if (!other.canEqual(this)) return false;
        Object this$orderSize = getOrderSize(), other$orderSize = other.getOrderSize();
        if ((this$orderSize == null) ? (other$orderSize != null) : !this$orderSize.equals(other$orderSize))
            return false;
        Object this$orderFeeAmt = getOrderFeeAmt(), other$orderFeeAmt = other.getOrderFeeAmt();
        if ((this$orderFeeAmt == null) ? (other$orderFeeAmt != null) : !this$orderFeeAmt.equals(other$orderFeeAmt))
            return false;
        Object this$orderProfitAndLose = getOrderProfitAndLose(), other$orderProfitAndLose = other.getOrderProfitAndLose();
        if ((this$orderProfitAndLose == null) ? (other$orderProfitAndLose != null) : !this$orderProfitAndLose.equals(other$orderProfitAndLose))
            return false;
        Object this$orderAllAmt = getOrderAllAmt(), other$orderAllAmt = other.getOrderAllAmt();
        return !((this$orderAllAmt == null) ? (other$orderAllAmt != null) : !this$orderAllAmt.equals(other$orderAllAmt));
    }

    protected boolean canEqual(Object other) {
        return other instanceof AgentIncomeVO;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $orderSize = getOrderSize();
        result = result * 59 + (($orderSize == null) ? 43 : $orderSize.hashCode());
        Object $orderFeeAmt = getOrderFeeAmt();
        result = result * 59 + (($orderFeeAmt == null) ? 43 : $orderFeeAmt.hashCode());
        Object $orderProfitAndLose = getOrderProfitAndLose();
        result = result * 59 + (($orderProfitAndLose == null) ? 43 : $orderProfitAndLose.hashCode());
        Object $orderAllAmt = getOrderAllAmt();
        return result * 59 + (($orderAllAmt == null) ? 43 : $orderAllAmt.hashCode());
    }

    public String toString() {
        return "AgentIncomeVO(orderSize=" + getOrderSize() + ", orderFeeAmt=" + getOrderFeeAmt() + ", orderProfitAndLose=" + getOrderProfitAndLose() + ", orderAllAmt=" + getOrderAllAmt() + ")";
    }


    public Integer getOrderSize() {
        return this.orderSize;
    }


    public BigDecimal getOrderFeeAmt() {
        return this.orderFeeAmt;
    }


    public BigDecimal getOrderProfitAndLose() {
        return this.orderProfitAndLose;
    }


    public BigDecimal getOrderAllAmt() {
        return this.orderAllAmt;
    }

}
