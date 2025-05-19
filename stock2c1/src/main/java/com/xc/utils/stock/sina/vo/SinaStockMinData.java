package com.xc.utils.stock.sina.vo;

public class SinaStockMinData {
    private String day;
    private String open;
    private String high;
    private String low;
    private String close;
    private String volume;
    private double ma_price;
    private String ma_volume;

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof SinaStockMinData)) return false;
        SinaStockMinData other = (SinaStockMinData) o;
        if (!other.canEqual(this)) return false;
        Object this$day = getDay(), other$day = other.getDay();
        if ((this$day == null) ? (other$day != null) : !this$day.equals(other$day)) return false;
        Object this$open = getOpen(), other$open = other.getOpen();
        if ((this$open == null) ? (other$open != null) : !this$open.equals(other$open)) return false;
        Object this$high = getHigh(), other$high = other.getHigh();
        if ((this$high == null) ? (other$high != null) : !this$high.equals(other$high)) return false;
        Object this$low = getLow(), other$low = other.getLow();
        if ((this$low == null) ? (other$low != null) : !this$low.equals(other$low)) return false;
        Object this$close = getClose(), other$close = other.getClose();
        if ((this$close == null) ? (other$close != null) : !this$close.equals(other$close)) return false;
        Object this$volume = getVolume(), other$volume = other.getVolume();
        return ((this$volume == null) ? (other$volume != null) : !this$volume.equals(other$volume)) ? false : ((Double.compare(getMa_price(), other.getMa_price()) != 0) ? false : (!(getMa_volume() != other.getMa_volume())));
    }

    protected boolean canEqual(Object other) {
        return other instanceof SinaStockMinData;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $day = getDay();
        result = result * 59 + (($day == null) ? 43 : $day.hashCode());
        Object $open = getOpen();
        result = result * 59 + (($open == null) ? 43 : $open.hashCode());
        Object $high = getHigh();
        result = result * 59 + (($high == null) ? 43 : $high.hashCode());
        Object $low = getLow();
        result = result * 59 + (($low == null) ? 43 : $low.hashCode());
        Object $close = getClose();
        result = result * 59 + (($close == null) ? 43 : $close.hashCode());
        Object $volume = getVolume();
        result = result * 59 + (($volume == null) ? 43 : $volume.hashCode());
        long $ma_price = Double.doubleToLongBits(getMa_price());
        result = result * 59 + (int) ($ma_price >>> 32 ^ $ma_price);
        Object $ma_volume = getMa_volume();
        return result * 59 + (($ma_volume == null) ? 43 : $volume.hashCode());
        //return result * 59 + getMa_volume();
    }

    public String toString() {
        return "SinaStockMinData(day=" + getDay() + ", open=" + getOpen() + ", high=" + getHigh() + ", low=" + getLow() + ", close=" + getClose() + ", volume=" + getVolume() + ", ma_price=" + getMa_price() + ", ma_volume=" + getMa_volume() + ")";
    }

    public String getDay() {
        return this.day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getOpen() {
        return this.open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getHigh() {
        return this.high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return this.low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getClose() {
        return this.close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getVolume() {
        return this.volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public double getMa_price() {
        return this.ma_price;
    }

    public void setMa_price(double ma_price) {
        this.ma_price = ma_price;
    }

    public String getMa_volume() {
        return this.ma_volume;
    }

    public void setMa_volume(String ma_volume) {
        this.ma_volume = ma_volume;
    }
}


