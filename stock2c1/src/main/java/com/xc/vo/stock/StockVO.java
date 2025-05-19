
package com.xc.vo.stock;

import java.math.BigDecimal;

public class StockVO {
    private int id;
    private String name;
    private String code;
    private String spell;
    private String gid;
    private String nowPrice;
    private BigDecimal hcrate;
    private String today_max;
    private String today_min;

    private String business_balance;

    private String business_amount;

    private String preclose_px;

    private String open_px;

    private String buy1;

    private String buy2;

    private String buy3;

    private String buy4;

    private String buy5;


    public void setId(int id) {
        this.id = id;
    }

    private String sell1;
    private String sell2;
    private String sell3;
    private String sell4;
    private String sell5;
    private String buy1_num;
    private String buy2_num;
    private String buy3_num;
    private String buy4_num;
    private String buy5_num;
    private String sell1_num;
    private String sell2_num;
    private String sell3_num;
    private String sell4_num;
    private String sell5_num;
    private String minImg;
    private String dayImg;
    private String weekImg;
    private String monthImg;

    private Integer depositAmt;

    public void setName(String name) {
        this.name = name;
    }

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

    public void setBuy1(String buy1) {
        this.buy1 = buy1;
    }

    public void setBuy2(String buy2) {
        this.buy2 = buy2;
    }

    public void setBuy3(String buy3) {
        this.buy3 = buy3;
    }

    public void setBuy4(String buy4) {
        this.buy4 = buy4;
    }

    public void setBuy5(String buy5) {
        this.buy5 = buy5;
    }

    public void setSell1(String sell1) {
        this.sell1 = sell1;
    }

    public void setSell2(String sell2) {
        this.sell2 = sell2;
    }

    public void setSell3(String sell3) {
        this.sell3 = sell3;
    }

    public void setSell4(String sell4) {
        this.sell4 = sell4;
    }

    public void setSell5(String sell5) {
        this.sell5 = sell5;
    }

    public void setBuy1_num(String buy1_num) {
        this.buy1_num = buy1_num;
    }

    public void setBuy2_num(String buy2_num) {
        this.buy2_num = buy2_num;
    }

    public void setBuy3_num(String buy3_num) {
        this.buy3_num = buy3_num;
    }

    public void setBuy4_num(String buy4_num) {
        this.buy4_num = buy4_num;
    }

    public void setBuy5_num(String buy5_num) {
        this.buy5_num = buy5_num;
    }

    public void setSell1_num(String sell1_num) {
        this.sell1_num = sell1_num;
    }

    public void setSell2_num(String sell2_num) {
        this.sell2_num = sell2_num;
    }

    public void setSell3_num(String sell3_num) {
        this.sell3_num = sell3_num;
    }

    public void setSell4_num(String sell4_num) {
        this.sell4_num = sell4_num;
    }

    public void setSell5_num(String sell5_num) {
        this.sell5_num = sell5_num;
    }

    public void setMinImg(String minImg) {
        this.minImg = minImg;
    }

    public void setDayImg(String dayImg) {
        this.dayImg = dayImg;
    }

    public void setWeekImg(String weekImg) {
        this.weekImg = weekImg;
    }

    public void setMonthImg(String monthImg) {
        this.monthImg = monthImg;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof StockVO)) return false;
        StockVO other = (StockVO) o;
        if (!other.canEqual(this)) return false;
        if (getId() != other.getId()) return false;
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
        Object this$buy1 = getBuy1(), other$buy1 = other.getBuy1();
        if ((this$buy1 == null) ? (other$buy1 != null) : !this$buy1.equals(other$buy1)) return false;
        Object this$buy2 = getBuy2(), other$buy2 = other.getBuy2();
        if ((this$buy2 == null) ? (other$buy2 != null) : !this$buy2.equals(other$buy2)) return false;
        Object this$buy3 = getBuy3(), other$buy3 = other.getBuy3();
        if ((this$buy3 == null) ? (other$buy3 != null) : !this$buy3.equals(other$buy3)) return false;
        Object this$buy4 = getBuy4(), other$buy4 = other.getBuy4();
        if ((this$buy4 == null) ? (other$buy4 != null) : !this$buy4.equals(other$buy4)) return false;
        Object this$buy5 = getBuy5(), other$buy5 = other.getBuy5();
        if ((this$buy5 == null) ? (other$buy5 != null) : !this$buy5.equals(other$buy5)) return false;
        Object this$sell1 = getSell1(), other$sell1 = other.getSell1();
        if ((this$sell1 == null) ? (other$sell1 != null) : !this$sell1.equals(other$sell1)) return false;
        Object this$sell2 = getSell2(), other$sell2 = other.getSell2();
        if ((this$sell2 == null) ? (other$sell2 != null) : !this$sell2.equals(other$sell2)) return false;
        Object this$sell3 = getSell3(), other$sell3 = other.getSell3();
        if ((this$sell3 == null) ? (other$sell3 != null) : !this$sell3.equals(other$sell3)) return false;
        Object this$sell4 = getSell4(), other$sell4 = other.getSell4();
        if ((this$sell4 == null) ? (other$sell4 != null) : !this$sell4.equals(other$sell4)) return false;
        Object this$sell5 = getSell5(), other$sell5 = other.getSell5();
        if ((this$sell5 == null) ? (other$sell5 != null) : !this$sell5.equals(other$sell5)) return false;
        Object this$buy1_num = getBuy1_num(), other$buy1_num = other.getBuy1_num();
        if ((this$buy1_num == null) ? (other$buy1_num != null) : !this$buy1_num.equals(other$buy1_num)) return false;
        Object this$buy2_num = getBuy2_num(), other$buy2_num = other.getBuy2_num();
        if ((this$buy2_num == null) ? (other$buy2_num != null) : !this$buy2_num.equals(other$buy2_num)) return false;
        Object this$buy3_num = getBuy3_num(), other$buy3_num = other.getBuy3_num();
        if ((this$buy3_num == null) ? (other$buy3_num != null) : !this$buy3_num.equals(other$buy3_num)) return false;
        Object this$buy4_num = getBuy4_num(), other$buy4_num = other.getBuy4_num();
        if ((this$buy4_num == null) ? (other$buy4_num != null) : !this$buy4_num.equals(other$buy4_num)) return false;
        Object this$buy5_num = getBuy5_num(), other$buy5_num = other.getBuy5_num();
        if ((this$buy5_num == null) ? (other$buy5_num != null) : !this$buy5_num.equals(other$buy5_num)) return false;
        Object this$sell1_num = getSell1_num(), other$sell1_num = other.getSell1_num();
        if ((this$sell1_num == null) ? (other$sell1_num != null) : !this$sell1_num.equals(other$sell1_num))
            return false;
        Object this$sell2_num = getSell2_num(), other$sell2_num = other.getSell2_num();
        if ((this$sell2_num == null) ? (other$sell2_num != null) : !this$sell2_num.equals(other$sell2_num))
            return false;
        Object this$sell3_num = getSell3_num(), other$sell3_num = other.getSell3_num();
        if ((this$sell3_num == null) ? (other$sell3_num != null) : !this$sell3_num.equals(other$sell3_num))
            return false;
        Object this$sell4_num = getSell4_num(), other$sell4_num = other.getSell4_num();
        if ((this$sell4_num == null) ? (other$sell4_num != null) : !this$sell4_num.equals(other$sell4_num))
            return false;
        Object this$sell5_num = getSell5_num(), other$sell5_num = other.getSell5_num();
        if ((this$sell5_num == null) ? (other$sell5_num != null) : !this$sell5_num.equals(other$sell5_num))
            return false;
        Object this$minImg = getMinImg(), other$minImg = other.getMinImg();
        if ((this$minImg == null) ? (other$minImg != null) : !this$minImg.equals(other$minImg)) return false;
        Object this$dayImg = getDayImg(), other$dayImg = other.getDayImg();
        if ((this$dayImg == null) ? (other$dayImg != null) : !this$dayImg.equals(other$dayImg)) return false;
        Object this$weekImg = getWeekImg(), other$weekImg = other.getWeekImg();
        if ((this$weekImg == null) ? (other$weekImg != null) : !this$weekImg.equals(other$weekImg)) return false;
        Object this$monthImg = getMonthImg(), other$monthImg = other.getMonthImg();
        return !((this$monthImg == null) ? (other$monthImg != null) : !this$monthImg.equals(other$monthImg));
    }

    protected boolean canEqual(Object other) {
        return other instanceof StockVO;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + getId();
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
        Object $buy1 = getBuy1();
        result = result * 59 + (($buy1 == null) ? 43 : $buy1.hashCode());
        Object $buy2 = getBuy2();
        result = result * 59 + (($buy2 == null) ? 43 : $buy2.hashCode());
        Object $buy3 = getBuy3();
        result = result * 59 + (($buy3 == null) ? 43 : $buy3.hashCode());
        Object $buy4 = getBuy4();
        result = result * 59 + (($buy4 == null) ? 43 : $buy4.hashCode());
        Object $buy5 = getBuy5();
        result = result * 59 + (($buy5 == null) ? 43 : $buy5.hashCode());
        Object $sell1 = getSell1();
        result = result * 59 + (($sell1 == null) ? 43 : $sell1.hashCode());
        Object $sell2 = getSell2();
        result = result * 59 + (($sell2 == null) ? 43 : $sell2.hashCode());
        Object $sell3 = getSell3();
        result = result * 59 + (($sell3 == null) ? 43 : $sell3.hashCode());
        Object $sell4 = getSell4();
        result = result * 59 + (($sell4 == null) ? 43 : $sell4.hashCode());
        Object $sell5 = getSell5();
        result = result * 59 + (($sell5 == null) ? 43 : $sell5.hashCode());
        Object $buy1_num = getBuy1_num();
        result = result * 59 + (($buy1_num == null) ? 43 : $buy1_num.hashCode());
        Object $buy2_num = getBuy2_num();
        result = result * 59 + (($buy2_num == null) ? 43 : $buy2_num.hashCode());
        Object $buy3_num = getBuy3_num();
        result = result * 59 + (($buy3_num == null) ? 43 : $buy3_num.hashCode());
        Object $buy4_num = getBuy4_num();
        result = result * 59 + (($buy4_num == null) ? 43 : $buy4_num.hashCode());
        Object $buy5_num = getBuy5_num();
        result = result * 59 + (($buy5_num == null) ? 43 : $buy5_num.hashCode());
        Object $sell1_num = getSell1_num();
        result = result * 59 + (($sell1_num == null) ? 43 : $sell1_num.hashCode());
        Object $sell2_num = getSell2_num();
        result = result * 59 + (($sell2_num == null) ? 43 : $sell2_num.hashCode());
        Object $sell3_num = getSell3_num();
        result = result * 59 + (($sell3_num == null) ? 43 : $sell3_num.hashCode());
        Object $sell4_num = getSell4_num();
        result = result * 59 + (($sell4_num == null) ? 43 : $sell4_num.hashCode());
        Object $sell5_num = getSell5_num();
        result = result * 59 + (($sell5_num == null) ? 43 : $sell5_num.hashCode());
        Object $minImg = getMinImg();
        result = result * 59 + (($minImg == null) ? 43 : $minImg.hashCode());
        Object $dayImg = getDayImg();
        result = result * 59 + (($dayImg == null) ? 43 : $dayImg.hashCode());
        Object $weekImg = getWeekImg();
        result = result * 59 + (($weekImg == null) ? 43 : $weekImg.hashCode());
        Object $monthImg = getMonthImg();
        return result * 59 + (($monthImg == null) ? 43 : $monthImg.hashCode());
    }

    public String toString() {
        return "StockVO(id=" + getId() + ", name=" + getName() + ", code=" + getCode() + ", spell=" + getSpell() + ", gid=" + getGid() + ", nowPrice=" + getNowPrice() + ", hcrate=" + getHcrate() + ", today_max=" + getToday_max() + ", today_min=" + getToday_min() + ", business_balance=" + getBusiness_balance() + ", business_amount=" + getBusiness_amount() + ", preclose_px=" + getPreclose_px() + ", open_px=" + getOpen_px() + ", buy1=" + getBuy1() + ", buy2=" + getBuy2() + ", buy3=" + getBuy3() + ", buy4=" + getBuy4() + ", buy5=" + getBuy5() + ", sell1=" + getSell1() + ", sell2=" + getSell2() + ", sell3=" + getSell3() + ", sell4=" + getSell4() + ", sell5=" + getSell5() + ", buy1_num=" + getBuy1_num() + ", buy2_num=" + getBuy2_num() + ", buy3_num=" + getBuy3_num() + ", buy4_num=" + getBuy4_num() + ", buy5_num=" + getBuy5_num() + ", sell1_num=" + getSell1_num() + ", sell2_num=" + getSell2_num() + ", sell3_num=" + getSell3_num() + ", sell4_num=" + getSell4_num() + ", sell5_num=" + getSell5_num() + ", minImg=" + getMinImg() + ", dayImg=" + getDayImg() + ", weekImg=" + getWeekImg() + ", monthImg=" + getMonthImg()+ ", depositAmt=" + getDepositAmt() + ")";
    }


    public int getId() {
        return this.id;
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


    public String getBuy1() {
        return this.buy1;
    }

    public String getBuy2() {
        return this.buy2;
    }

    public String getBuy3() {
        return this.buy3;
    }

    public String getBuy4() {
        return this.buy4;
    }

    public String getBuy5() {
        return this.buy5;
    }


    public String getSell1() {
        return this.sell1;
    }

    public String getSell2() {
        return this.sell2;
    }

    public String getSell3() {
        return this.sell3;
    }

    public String getSell4() {
        return this.sell4;
    }

    public String getSell5() {
        return this.sell5;
    }


    public String getBuy1_num() {
        return this.buy1_num;
    }

    public String getBuy2_num() {
        return this.buy2_num;
    }

    public String getBuy3_num() {
        return this.buy3_num;
    }

    public String getBuy4_num() {
        return this.buy4_num;
    }

    public String getBuy5_num() {
        return this.buy5_num;
    }


    public String getSell1_num() {
        return this.sell1_num;
    }

    public String getSell2_num() {
        return this.sell2_num;
    }

    public String getSell3_num() {
        return this.sell3_num;
    }

    public String getSell4_num() {
        return this.sell4_num;
    }

    public String getSell5_num() {
        return this.sell5_num;
    }


    public String getMinImg() {
        return this.minImg;
    }

    public String getDayImg() {
        return this.dayImg;
    }

    public String getWeekImg() {
        return this.weekImg;
    }

    public String getMonthImg() {
        return this.monthImg;
    }

    public Integer getDepositAmt() {
        return depositAmt;
    }

    public void setDepositAmt(Integer depositAmt) {
        this.depositAmt = depositAmt;
    }
}

