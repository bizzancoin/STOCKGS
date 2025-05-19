package com.xc.vo.position;

import java.math.BigDecimal;


public class PositionVO {
    private BigDecimal allProfitAndLose;
    private BigDecimal allFreezAmt;
    //所有总资产
    private BigDecimal allPositionMoney;

    public void setAllProfitAndLose(BigDecimal allProfitAndLose) {
        this.allProfitAndLose = allProfitAndLose;
    }

    public void setAllFreezAmt(BigDecimal allFreezAmt) {
        this.allFreezAmt = allFreezAmt;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof PositionVO)) return false;
        PositionVO other = (PositionVO) o;
        if (!other.canEqual(this)) return false;
        Object this$allProfitAndLose = getAllProfitAndLose(), other$allProfitAndLose = other.getAllProfitAndLose();
        if ((this$allProfitAndLose == null) ? (other$allProfitAndLose != null) : !this$allProfitAndLose.equals(other$allProfitAndLose))
            return false;
        Object this$allFreezAmt = getAllFreezAmt(), other$allFreezAmt = other.getAllFreezAmt();
        return !((this$allFreezAmt == null) ? (other$allFreezAmt != null) : !this$allFreezAmt.equals(other$allFreezAmt));
    }

    protected boolean canEqual(Object other) {
        return other instanceof PositionVO;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $allProfitAndLose = getAllProfitAndLose();
        result = result * 59 + (($allProfitAndLose == null) ? 43 : $allProfitAndLose.hashCode());
        Object $allFreezAmt = getAllFreezAmt();
        return result * 59 + (($allFreezAmt == null) ? 43 : $allFreezAmt.hashCode());
    }

    public String toString() {
        return "PositionVO(allProfitAndLose=" + getAllProfitAndLose() + ", allFreezAmt=" + getAllFreezAmt() + ")";
    }


    public BigDecimal getAllProfitAndLose() {
        return this.allProfitAndLose;
    }


    public BigDecimal getAllFreezAmt() {
        return this.allFreezAmt;
    }

    public BigDecimal getAllPositionMoney() {
        return allPositionMoney;
    }

    public void setAllPositionMoney(BigDecimal allPositionMoney) {
        this.allPositionMoney = allPositionMoney;
    }
}
