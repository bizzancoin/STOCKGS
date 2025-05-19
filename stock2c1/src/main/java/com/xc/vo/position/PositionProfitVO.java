package com.xc.vo.position;

import java.math.BigDecimal;

public class PositionProfitVO {

    private String nowPrice;

    private BigDecimal profitAndLose;

    private BigDecimal allProfitAndLose;


    public void setNowPrice(String nowPrice) {
        this.nowPrice = nowPrice;
    }

    public void setProfitAndLose(BigDecimal profitAndLose) {
        this.profitAndLose = profitAndLose;
    }

    public void setAllProfitAndLose(BigDecimal allProfitAndLose) {
        this.allProfitAndLose = allProfitAndLose;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof PositionProfitVO)) return false;
        PositionProfitVO other = (PositionProfitVO) o;
        if (!other.canEqual(this)) return false;
        Object this$nowPrice = getNowPrice(), other$nowPrice = other.getNowPrice();
        if ((this$nowPrice == null) ? (other$nowPrice != null) : !this$nowPrice.equals(other$nowPrice)) return false;
        Object this$profitAndLose = getProfitAndLose(), other$profitAndLose = other.getProfitAndLose();
        if ((this$profitAndLose == null) ? (other$profitAndLose != null) : !this$profitAndLose.equals(other$profitAndLose))
            return false;
        Object this$allProfitAndLose = getAllProfitAndLose(), other$allProfitAndLose = other.getAllProfitAndLose();
        return !((this$allProfitAndLose == null) ? (other$allProfitAndLose != null) : !this$allProfitAndLose.equals(other$allProfitAndLose));
    }

    protected boolean canEqual(Object other) {
        return other instanceof PositionProfitVO;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $nowPrice = getNowPrice();
        result = result * 59 + (($nowPrice == null) ? 43 : $nowPrice.hashCode());
        Object $profitAndLose = getProfitAndLose();
        result = result * 59 + (($profitAndLose == null) ? 43 : $profitAndLose.hashCode());
        Object $allProfitAndLose = getAllProfitAndLose();
        return result * 59 + (($allProfitAndLose == null) ? 43 : $allProfitAndLose.hashCode());
    }

    public String toString() {
        return "PositionProfitVO(nowPrice=" + getNowPrice() + ", profitAndLose=" + getProfitAndLose() + ", allProfitAndLose=" + getAllProfitAndLose() + ")";
    }


    public String getNowPrice() {
        return this.nowPrice;
    }


    public BigDecimal getProfitAndLose() {
        return this.profitAndLose;
    }


    public BigDecimal getAllProfitAndLose() {
        return this.allProfitAndLose;
    }

}

