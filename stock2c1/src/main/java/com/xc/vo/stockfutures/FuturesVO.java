
package com.xc.vo.stockfutures;


public class FuturesVO {

    private String nowPrice;

    private String lastClose;

    private String buyPrice;

    private String sellPrice;

    private String maxPrice;

    private String minPrice;


    public void setNowPrice(String nowPrice) {
        this.nowPrice = nowPrice;
    }

    private String hourTime;
    private String todayOpen;
    private String extra1;
    private String extra2;
    private String extra3;
    private String dayTime;
    private String name;

    public void setLastClose(String lastClose) {
        this.lastClose = lastClose;
    }

    public void setBuyPrice(String buyPrice) {
        this.buyPrice = buyPrice;
    }

    public void setSellPrice(String sellPrice) {
        this.sellPrice = sellPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public void setHourTime(String hourTime) {
        this.hourTime = hourTime;
    }

    public void setTodayOpen(String todayOpen) {
        this.todayOpen = todayOpen;
    }

    public void setExtra1(String extra1) {
        this.extra1 = extra1;
    }

    public void setExtra2(String extra2) {
        this.extra2 = extra2;
    }

    public void setExtra3(String extra3) {
        this.extra3 = extra3;
    }

    public void setDayTime(String dayTime) {
        this.dayTime = dayTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof FuturesVO)) return false;
        FuturesVO other = (FuturesVO) o;
        if (!other.canEqual(this)) return false;
        Object this$nowPrice = getNowPrice(), other$nowPrice = other.getNowPrice();
        if ((this$nowPrice == null) ? (other$nowPrice != null) : !this$nowPrice.equals(other$nowPrice)) return false;
        Object this$lastClose = getLastClose(), other$lastClose = other.getLastClose();
        if ((this$lastClose == null) ? (other$lastClose != null) : !this$lastClose.equals(other$lastClose))
            return false;
        Object this$buyPrice = getBuyPrice(), other$buyPrice = other.getBuyPrice();
        if ((this$buyPrice == null) ? (other$buyPrice != null) : !this$buyPrice.equals(other$buyPrice)) return false;
        Object this$sellPrice = getSellPrice(), other$sellPrice = other.getSellPrice();
        if ((this$sellPrice == null) ? (other$sellPrice != null) : !this$sellPrice.equals(other$sellPrice))
            return false;
        Object this$maxPrice = getMaxPrice(), other$maxPrice = other.getMaxPrice();
        if ((this$maxPrice == null) ? (other$maxPrice != null) : !this$maxPrice.equals(other$maxPrice)) return false;
        Object this$minPrice = getMinPrice(), other$minPrice = other.getMinPrice();
        if ((this$minPrice == null) ? (other$minPrice != null) : !this$minPrice.equals(other$minPrice)) return false;
        Object this$hourTime = getHourTime(), other$hourTime = other.getHourTime();
        if ((this$hourTime == null) ? (other$hourTime != null) : !this$hourTime.equals(other$hourTime)) return false;
        Object this$todayOpen = getTodayOpen(), other$todayOpen = other.getTodayOpen();
        if ((this$todayOpen == null) ? (other$todayOpen != null) : !this$todayOpen.equals(other$todayOpen))
            return false;
        Object this$extra1 = getExtra1(), other$extra1 = other.getExtra1();
        if ((this$extra1 == null) ? (other$extra1 != null) : !this$extra1.equals(other$extra1)) return false;
        Object this$extra2 = getExtra2(), other$extra2 = other.getExtra2();
        if ((this$extra2 == null) ? (other$extra2 != null) : !this$extra2.equals(other$extra2)) return false;
        Object this$extra3 = getExtra3(), other$extra3 = other.getExtra3();
        if ((this$extra3 == null) ? (other$extra3 != null) : !this$extra3.equals(other$extra3)) return false;
        Object this$dayTime = getDayTime(), other$dayTime = other.getDayTime();
        if ((this$dayTime == null) ? (other$dayTime != null) : !this$dayTime.equals(other$dayTime)) return false;
        Object this$name = getName(), other$name = other.getName();
        return !((this$name == null) ? (other$name != null) : !this$name.equals(other$name));
    }

    protected boolean canEqual(Object other) {
        return other instanceof FuturesVO;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $nowPrice = getNowPrice();
        result = result * 59 + (($nowPrice == null) ? 43 : $nowPrice.hashCode());
        Object $lastClose = getLastClose();
        result = result * 59 + (($lastClose == null) ? 43 : $lastClose.hashCode());
        Object $buyPrice = getBuyPrice();
        result = result * 59 + (($buyPrice == null) ? 43 : $buyPrice.hashCode());
        Object $sellPrice = getSellPrice();
        result = result * 59 + (($sellPrice == null) ? 43 : $sellPrice.hashCode());
        Object $maxPrice = getMaxPrice();
        result = result * 59 + (($maxPrice == null) ? 43 : $maxPrice.hashCode());
        Object $minPrice = getMinPrice();
        result = result * 59 + (($minPrice == null) ? 43 : $minPrice.hashCode());
        Object $hourTime = getHourTime();
        result = result * 59 + (($hourTime == null) ? 43 : $hourTime.hashCode());
        Object $todayOpen = getTodayOpen();
        result = result * 59 + (($todayOpen == null) ? 43 : $todayOpen.hashCode());
        Object $extra1 = getExtra1();
        result = result * 59 + (($extra1 == null) ? 43 : $extra1.hashCode());
        Object $extra2 = getExtra2();
        result = result * 59 + (($extra2 == null) ? 43 : $extra2.hashCode());
        Object $extra3 = getExtra3();
        result = result * 59 + (($extra3 == null) ? 43 : $extra3.hashCode());
        Object $dayTime = getDayTime();
        result = result * 59 + (($dayTime == null) ? 43 : $dayTime.hashCode());
        Object $name = getName();
        return result * 59 + (($name == null) ? 43 : $name.hashCode());
    }

    public String toString() {
        return "FuturesVO(nowPrice=" + getNowPrice() + ", lastClose=" + getLastClose() + ", buyPrice=" + getBuyPrice() + ", sellPrice=" + getSellPrice() + ", maxPrice=" + getMaxPrice() + ", minPrice=" + getMinPrice() + ", hourTime=" + getHourTime() + ", todayOpen=" + getTodayOpen() + ", extra1=" + getExtra1() + ", extra2=" + getExtra2() + ", extra3=" + getExtra3() + ", dayTime=" + getDayTime() + ", name=" + getName() + ")";
    }


    public String getNowPrice() {
        return this.nowPrice;
    }


    public String getLastClose() {
        return this.lastClose;
    }


    public String getBuyPrice() {
        return this.buyPrice;
    }


    public String getSellPrice() {
        return this.sellPrice;
    }


    public String getMaxPrice() {
        return this.maxPrice;
    }


    public String getMinPrice() {
        return this.minPrice;
    }


    public String getHourTime() {
        return this.hourTime;
    }


    public String getTodayOpen() {
        return this.todayOpen;
    }


    public String getExtra1() {
        return this.extra1;
    }

    public String getExtra2() {
        return this.extra2;
    }

    public String getExtra3() {
        return this.extra3;
    }


    public String getDayTime() {
        return this.dayTime;
    }


    public String getName() {
        return this.name;
    }

}
