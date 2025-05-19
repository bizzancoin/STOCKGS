package com.xc.vo.futuresposition;


import java.math.BigDecimal;


public class FuturesPositionProfitVO {

    private String nowPrice;

    private BigDecimal profitAndLose;

    private BigDecimal allProfitAndLose;

    private BigDecimal coinRate;


    public void setNowPrice(String nowPrice) {
        this.nowPrice = nowPrice;
    }

    public void setProfitAndLose(BigDecimal profitAndLose) {
        this.profitAndLose = profitAndLose;
    }

    public void setAllProfitAndLose(BigDecimal allProfitAndLose) {
        this.allProfitAndLose = allProfitAndLose;
    }

    public void setCoinRate(BigDecimal coinRate) {
        this.coinRate = coinRate;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof FuturesPositionProfitVO)) return false;
        FuturesPositionProfitVO other = (FuturesPositionProfitVO) o;
        if (!other.canEqual(this)) return false;
        Object this$nowPrice = getNowPrice(), other$nowPrice = other.getNowPrice();
        if ((this$nowPrice == null) ? (other$nowPrice != null) : !this$nowPrice.equals(other$nowPrice)) return false;
        Object this$profitAndLose = getProfitAndLose(), other$profitAndLose = other.getProfitAndLose();
        if ((this$profitAndLose == null) ? (other$profitAndLose != null) : !this$profitAndLose.equals(other$profitAndLose))
            return false;
        Object this$allProfitAndLose = getAllProfitAndLose(), other$allProfitAndLose = other.getAllProfitAndLose();
        if ((this$allProfitAndLose == null) ? (other$allProfitAndLose != null) : !this$allProfitAndLose.equals(other$allProfitAndLose))
            return false;
        Object this$coinRate = getCoinRate(), other$coinRate = other.getCoinRate();
        return !((this$coinRate == null) ? (other$coinRate != null) : !this$coinRate.equals(other$coinRate));
    }

    protected boolean canEqual(Object other) {
        return other instanceof FuturesPositionProfitVO;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $nowPrice = getNowPrice();
        result = result * 59 + (($nowPrice == null) ? 43 : $nowPrice.hashCode());
        Object $profitAndLose = getProfitAndLose();
        result = result * 59 + (($profitAndLose == null) ? 43 : $profitAndLose.hashCode());
        Object $allProfitAndLose = getAllProfitAndLose();
        result = result * 59 + (($allProfitAndLose == null) ? 43 : $allProfitAndLose.hashCode());
        Object $coinRate = getCoinRate();
        return result * 59 + (($coinRate == null) ? 43 : $coinRate.hashCode());
    }

    public String toString() {
        return "FuturesPositionProfitVO(nowPrice=" + getNowPrice() + ", profitAndLose=" + getProfitAndLose() + ", allProfitAndLose=" + getAllProfitAndLose() + ", coinRate=" + getCoinRate() + ")";
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


    public BigDecimal getCoinRate() {
        return this.coinRate;
    }

}
