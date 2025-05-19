package com.xc.vo.indexposition;


import java.math.BigDecimal;


public class IndexPositionVO {
    private BigDecimal allIndexProfitAndLose;
    private BigDecimal allIndexFreezAmt;

    public void setAllIndexProfitAndLose(BigDecimal allIndexProfitAndLose) {
        this.allIndexProfitAndLose = allIndexProfitAndLose;
    }

    public void setAllIndexFreezAmt(BigDecimal allIndexFreezAmt) {
        this.allIndexFreezAmt = allIndexFreezAmt;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof IndexPositionVO)) return false;
        IndexPositionVO other = (IndexPositionVO) o;
        if (!other.canEqual(this)) return false;
        Object this$allIndexProfitAndLose = getAllIndexProfitAndLose(), other$allIndexProfitAndLose = other.getAllIndexProfitAndLose();
        if ((this$allIndexProfitAndLose == null) ? (other$allIndexProfitAndLose != null) : !this$allIndexProfitAndLose.equals(other$allIndexProfitAndLose))
            return false;
        Object this$allIndexFreezAmt = getAllIndexFreezAmt(), other$allIndexFreezAmt = other.getAllIndexFreezAmt();
        return !((this$allIndexFreezAmt == null) ? (other$allIndexFreezAmt != null) : !this$allIndexFreezAmt.equals(other$allIndexFreezAmt));
    }

    protected boolean canEqual(Object other) {
        return other instanceof IndexPositionVO;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $allIndexProfitAndLose = getAllIndexProfitAndLose();
        result = result * 59 + (($allIndexProfitAndLose == null) ? 43 : $allIndexProfitAndLose.hashCode());
        Object $allIndexFreezAmt = getAllIndexFreezAmt();
        return result * 59 + (($allIndexFreezAmt == null) ? 43 : $allIndexFreezAmt.hashCode());
    }

    public String toString() {
        return "IndexPositionVO(allIndexProfitAndLose=" + getAllIndexProfitAndLose() + ", allIndexFreezAmt=" + getAllIndexFreezAmt() + ")";
    }


    public BigDecimal getAllIndexProfitAndLose() {
        return this.allIndexProfitAndLose;
    }


    public BigDecimal getAllIndexFreezAmt() {
        return this.allIndexFreezAmt;
    }
}
