package com.xc.vo.stock;


import java.math.BigDecimal;


public class StockListVO {

    private String name;

    private String code;

    private String spell;

    private String gid;

    //最新价
    private String nowPrice;

    //涨幅
    private BigDecimal hcrate;

    private String today_max;

    public void setName(String name) {
        this.name = name;
    }

    private String today_min;
    //成交额
    private String business_balance;
    //成交量
    private String business_amount;
    private String preclose_px;
    //开盘价
    private String open_px;
    private BigDecimal day3Rate;
    private String stock_type;
    private String stock_plate;
    /*是否添加自选：1、添加自选，0、未添加自选*/
    private String isOption;

    public void setCode(String code) {
        this.code = code;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public void setNowPrice(String nowPrice) {
        this.nowPrice = nowPrice;
    }

    public void setHcrate(BigDecimal hcrate) {
        this.hcrate = hcrate;
    }

    public void setToday_max(String today_max) {
        this.today_max = today_max;
    }

    public void setToday_min(String today_min) {
        this.today_min = today_min;
    }

    public void setBusiness_balance(String business_balance) {
        this.business_balance = business_balance;
    }

    public void setBusiness_amount(String business_amount) {
        this.business_amount = business_amount;
    }

    public void setPreclose_px(String preclose_px) {
        this.preclose_px = preclose_px;
    }

    public void setOpen_px(String open_px) {
        this.open_px = open_px;
    }

    public void setDay3Rate(BigDecimal day3Rate) {
        this.day3Rate = day3Rate;
    }

    public void setStock_type(String stock_type) {
        this.stock_type = stock_type;
    }

    public void setStock_plate(String stock_plate) {
        this.stock_plate = stock_plate;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof StockListVO)) return false;
        StockListVO other = (StockListVO) o;
        if (!other.canEqual(this)) return false;
        Object this$name = getName(), other$name = other.getName();
        if ((this$name == null) ? (other$name != null) : !this$name.equals(other$name)) return false;
        Object this$code = getCode(), other$code = other.getCode();
        if ((this$code == null) ? (other$code != null) : !this$code.equals(other$code)) return false;
        Object this$spell = getSpell(), other$spell = other.getSpell();
        if ((this$spell == null) ? (other$spell != null) : !this$spell.equals(other$spell)) return false;
        Object this$gid = getGid(), other$gid = other.getGid();
        if ((this$gid == null) ? (other$gid != null) : !this$gid.equals(other$gid)) return false;
        Object this$nowPrice = getNowPrice(), other$nowPrice = other.getNowPrice();
        if ((this$nowPrice == null) ? (other$nowPrice != null) : !this$nowPrice.equals(other$nowPrice)) return false;
        Object this$hcrate = getHcrate(), other$hcrate = other.getHcrate();
        if ((this$hcrate == null) ? (other$hcrate != null) : !this$hcrate.equals(other$hcrate)) return false;
        Object this$today_max = getToday_max(), other$today_max = other.getToday_max();
        if ((this$today_max == null) ? (other$today_max != null) : !this$today_max.equals(other$today_max))
            return false;
        Object this$today_min = getToday_min(), other$today_min = other.getToday_min();
        if ((this$today_min == null) ? (other$today_min != null) : !this$today_min.equals(other$today_min))
            return false;
        Object this$business_balance = getBusiness_balance(), other$business_balance = other.getBusiness_balance();
        if ((this$business_balance == null) ? (other$business_balance != null) : !this$business_balance.equals(other$business_balance))
            return false;
        Object this$business_amount = getBusiness_amount(), other$business_amount = other.getBusiness_amount();
        if ((this$business_amount == null) ? (other$business_amount != null) : !this$business_amount.equals(other$business_amount))
            return false;
        Object this$preclose_px = getPreclose_px(), other$preclose_px = other.getPreclose_px();
        if ((this$preclose_px == null) ? (other$preclose_px != null) : !this$preclose_px.equals(other$preclose_px))
            return false;
        Object this$open_px = getOpen_px(), other$open_px = other.getOpen_px();
        if ((this$open_px == null) ? (other$open_px != null) : !this$open_px.equals(other$open_px)) return false;
        Object this$day3Rate = getDay3Rate(), other$day3Rate = other.getDay3Rate();
        if ((this$day3Rate == null) ? (other$day3Rate != null) : !this$day3Rate.equals(other$day3Rate)) return false;
        Object this$stock_type = getStock_type(), other$stock_type = other.getStock_type();
        if ((this$stock_type == null) ? (other$stock_type != null) : !this$stock_type.equals(other$stock_type))
            return false;
        Object this$stock_plate = getStock_plate(), other$stock_plate = other.getStock_plate();
        return !((this$stock_plate == null) ? (other$stock_plate != null) : !this$stock_plate.equals(other$stock_plate));
    }

    protected boolean canEqual(Object other) {
        return other instanceof StockListVO;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $name = getName();
        result = result * 59 + (($name == null) ? 43 : $name.hashCode());
        Object $code = getCode();
        result = result * 59 + (($code == null) ? 43 : $code.hashCode());
        Object $spell = getSpell();
        result = result * 59 + (($spell == null) ? 43 : $spell.hashCode());
        Object $gid = getGid();
        result = result * 59 + (($gid == null) ? 43 : $gid.hashCode());
        Object $nowPrice = getNowPrice();
        result = result * 59 + (($nowPrice == null) ? 43 : $nowPrice.hashCode());
        Object $hcrate = getHcrate();
        result = result * 59 + (($hcrate == null) ? 43 : $hcrate.hashCode());
        Object $today_max = getToday_max();
        result = result * 59 + (($today_max == null) ? 43 : $today_max.hashCode());
        Object $today_min = getToday_min();
        result = result * 59 + (($today_min == null) ? 43 : $today_min.hashCode());
        Object $business_balance = getBusiness_balance();
        result = result * 59 + (($business_balance == null) ? 43 : $business_balance.hashCode());
        Object $business_amount = getBusiness_amount();
        result = result * 59 + (($business_amount == null) ? 43 : $business_amount.hashCode());
        Object $preclose_px = getPreclose_px();
        result = result * 59 + (($preclose_px == null) ? 43 : $preclose_px.hashCode());
        Object $open_px = getOpen_px();
        result = result * 59 + (($open_px == null) ? 43 : $open_px.hashCode());
        Object $day3Rate = getDay3Rate();
        result = result * 59 + (($day3Rate == null) ? 43 : $day3Rate.hashCode());
        Object $stock_type = getStock_type();
        result = result * 59 + (($stock_type == null) ? 43 : $stock_type.hashCode());
        Object $stock_plate = getStock_plate();
        return result * 59 + (($stock_plate == null) ? 43 : $stock_plate.hashCode());
    }

    public String toString() {
        return "StockListVO(name=" + getName() + ", code=" + getCode() + ", spell=" + getSpell() + ", gid=" + getGid() + ", nowPrice=" + getNowPrice() + ", hcrate=" + getHcrate() + ", today_max=" + getToday_max() + ", today_min=" + getToday_min() + ", business_balance=" + getBusiness_balance() + ", business_amount=" + getBusiness_amount() + ", preclose_px=" + getPreclose_px() + ", open_px=" + getOpen_px() + ", day3Rate=" + getDay3Rate() + ", stock_type=" + getStock_type() + ", stock_plate=" + getStock_plate() + ")";
    }


    public String getName() {
        return this.name;
    }

    public String getCode() {
        return this.code;
    }

    public String getSpell() {
        return this.spell;
    }

    public String getGid() {
        return this.gid;
    }


    public String getNowPrice() {
        return this.nowPrice;
    }


    public BigDecimal getHcrate() {
        return this.hcrate;
    }


    public String getToday_max() {
        return this.today_max;
    }


    public String getToday_min() {
        return this.today_min;
    }


    public String getBusiness_balance() {
        return this.business_balance;
    }


    public String getBusiness_amount() {
        return this.business_amount;
    }


    public String getPreclose_px() {
        return this.preclose_px;
    }


    public String getOpen_px() {
        return this.open_px;
    }


    public BigDecimal getDay3Rate() {
        return this.day3Rate;
    }


    public String getStock_type() {
        return this.stock_type;
    }

    public String getStock_plate() {
        return this.stock_plate;
    }

    public String getIsOption() {
        return isOption;
    }

    public void setIsOption(String isOption) {
        this.isOption = isOption;
    }
}
