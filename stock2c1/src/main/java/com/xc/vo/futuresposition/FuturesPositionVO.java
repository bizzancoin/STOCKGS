package com.xc.vo.futuresposition;


import java.math.BigDecimal;


public class FuturesPositionVO {
    private BigDecimal allFuturesProfitAndLose;
    private BigDecimal allFuturesDepositAmt;

    public void setAllFuturesProfitAndLose(BigDecimal allFuturesProfitAndLose) {
        this.allFuturesProfitAndLose = allFuturesProfitAndLose;
    }

    public void setAllFuturesDepositAmt(BigDecimal allFuturesDepositAmt) {
        this.allFuturesDepositAmt = allFuturesDepositAmt;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof FuturesPositionVO)) return false;
        FuturesPositionVO other = (FuturesPositionVO) o;
        if (!other.canEqual(this)) return false;
        Object this$allFuturesProfitAndLose = getAllFuturesProfitAndLose(), other$allFuturesProfitAndLose = other.getAllFuturesProfitAndLose();
        if ((this$allFuturesProfitAndLose == null) ? (other$allFuturesProfitAndLose != null) : !this$allFuturesProfitAndLose.equals(other$allFuturesProfitAndLose))
            return false;
        Object this$allFuturesDepositAmt = getAllFuturesDepositAmt(), other$allFuturesDepositAmt = other.getAllFuturesDepositAmt();
        return !((this$allFuturesDepositAmt == null) ? (other$allFuturesDepositAmt != null) : !this$allFuturesDepositAmt.equals(other$allFuturesDepositAmt));
    }

    protected boolean canEqual(Object other) {
        return other instanceof FuturesPositionVO;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $allFuturesProfitAndLose = getAllFuturesProfitAndLose();
        result = result * 59 + (($allFuturesProfitAndLose == null) ? 43 : $allFuturesProfitAndLose.hashCode());
        Object $allFuturesDepositAmt = getAllFuturesDepositAmt();
        return result * 59 + (($allFuturesDepositAmt == null) ? 43 : $allFuturesDepositAmt.hashCode());
    }

    public String toString() {
        return "FuturesPositionVO(allFuturesProfitAndLose=" + getAllFuturesProfitAndLose() + ", allFuturesDepositAmt=" + getAllFuturesDepositAmt() + ")";
    }


    public BigDecimal getAllFuturesProfitAndLose() {
        return this.allFuturesProfitAndLose;
    }


    public BigDecimal getAllFuturesDepositAmt() {
        return this.allFuturesDepositAmt;
    }
}

