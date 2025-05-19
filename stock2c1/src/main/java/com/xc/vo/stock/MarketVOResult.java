package com.xc.vo.stock;


import java.util.List;


public class MarketVOResult {

    List<MarketVO> market;


    public String toString() {
        return "MarketVOResult(market=" + getMarket() + ")";
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $market = getMarket();
        return result * 59 + (($market == null) ? 43 : $market.hashCode());
    }

    protected boolean canEqual(Object other) {
        return other instanceof MarketVOResult;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof MarketVOResult)) return false;
        MarketVOResult other = (MarketVOResult) o;
        if (!other.canEqual(this)) return false;
        Object this$market = getMarket(), other$market = other.getMarket();
        return !((this$market == null) ? (other$market != null) : !this$market.equals(other$market));
    }

    public void setMarket(List<MarketVO> market) {
        this.market = market;
    }


    public List<MarketVO> getMarket() {
        return this.market;
    }

}

