package com.xc.vo.stock;

public class StockOptionListVO {
    private int id;
    private String stockName;
    private String stockCode;
    private String stockGid;
    private String nowPrice;

    public void setId(int id) {
        this.id = id;
    }

    private String hcrate;
    private String preclose_px;
    private String open_px;
    private String stock_plate;
    private String stock_type;
    /*是否添加自选：1、添加自选，0、未添加自选*/
    private String isOption;


    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public void setStockGid(String stockGid) {
        this.stockGid = stockGid;
    }

    public void setNowPrice(String nowPrice) {
        this.nowPrice = nowPrice;
    }

    public void setHcrate(String hcrate) {
        this.hcrate = hcrate;
    }

    public void setPreclose_px(String preclose_px) {
        this.preclose_px = preclose_px;
    }

    public void setOpen_px(String open_px) {
        this.open_px = open_px;
    }

    public void setStock_plate(String stock_plate) {
        this.stock_plate = stock_plate;
    }

    public void setStock_type(String stock_type) {
        this.stock_type = stock_type;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof StockOptionListVO)) return false;
        StockOptionListVO other = (StockOptionListVO) o;
        if (!other.canEqual(this)) return false;
        if (getId() != other.getId()) return false;
        Object this$stockName = getStockName(), other$stockName = other.getStockName();
        if ((this$stockName == null) ? (other$stockName != null) : !this$stockName.equals(other$stockName))
            return false;
        Object this$stockCode = getStockCode(), other$stockCode = other.getStockCode();
        if ((this$stockCode == null) ? (other$stockCode != null) : !this$stockCode.equals(other$stockCode))
            return false;
        Object this$stockGid = getStockGid(), other$stockGid = other.getStockGid();
        if ((this$stockGid == null) ? (other$stockGid != null) : !this$stockGid.equals(other$stockGid)) return false;
        Object this$nowPrice = getNowPrice(), other$nowPrice = other.getNowPrice();
        if ((this$nowPrice == null) ? (other$nowPrice != null) : !this$nowPrice.equals(other$nowPrice)) return false;
        Object this$hcrate = getHcrate(), other$hcrate = other.getHcrate();
        if ((this$hcrate == null) ? (other$hcrate != null) : !this$hcrate.equals(other$hcrate)) return false;
        Object this$preclose_px = getPreclose_px(), other$preclose_px = other.getPreclose_px();
        if ((this$preclose_px == null) ? (other$preclose_px != null) : !this$preclose_px.equals(other$preclose_px))
            return false;
        Object this$open_px = getOpen_px(), other$open_px = other.getOpen_px();
        if ((this$open_px == null) ? (other$open_px != null) : !this$open_px.equals(other$open_px)) return false;
        Object this$stock_plate = getStock_plate(), other$stock_plate = other.getStock_plate();
        if ((this$stock_plate == null) ? (other$stock_plate != null) : !this$stock_plate.equals(other$stock_plate))
            return false;
        Object this$stock_type = getStock_type(), other$stock_type = other.getStock_type();
        return !((this$stock_type == null) ? (other$stock_type != null) : !this$stock_type.equals(other$stock_type));
    }

    protected boolean canEqual(Object other) {
        return other instanceof StockOptionListVO;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + getId();
        Object $stockName = getStockName();
        result = result * 59 + (($stockName == null) ? 43 : $stockName.hashCode());
        Object $stockCode = getStockCode();
        result = result * 59 + (($stockCode == null) ? 43 : $stockCode.hashCode());
        Object $stockGid = getStockGid();
        result = result * 59 + (($stockGid == null) ? 43 : $stockGid.hashCode());
        Object $nowPrice = getNowPrice();
        result = result * 59 + (($nowPrice == null) ? 43 : $nowPrice.hashCode());
        Object $hcrate = getHcrate();
        result = result * 59 + (($hcrate == null) ? 43 : $hcrate.hashCode());
        Object $preclose_px = getPreclose_px();
        result = result * 59 + (($preclose_px == null) ? 43 : $preclose_px.hashCode());
        Object $open_px = getOpen_px();
        result = result * 59 + (($open_px == null) ? 43 : $open_px.hashCode());
        Object $stock_plate = getStock_plate();
        result = result * 59 + (($stock_plate == null) ? 43 : $stock_plate.hashCode());
        Object $stock_type = getStock_type();
        return result * 59 + (($stock_type == null) ? 43 : $stock_type.hashCode());
    }

    public String toString() {
        return "StockOptionListVO(id=" + getId() + ", stockName=" + getStockName() + ", stockCode=" + getStockCode() + ", stockGid=" + getStockGid() + ", nowPrice=" + getNowPrice() + ", hcrate=" + getHcrate() + ", preclose_px=" + getPreclose_px() + ", open_px=" + getOpen_px() + ", stock_plate=" + getStock_plate() + ", stock_type=" + getStock_type() + ")";
    }


    public int getId() {
        return this.id;
    }

    public String getStockName() {
        return this.stockName;
    }

    public String getStockCode() {
        return this.stockCode;
    }

    public String getStockGid() {
        return this.stockGid;
    }


    public String getNowPrice() {
        return this.nowPrice;
    }


    public String getHcrate() {
        return this.hcrate;
    }


    public String getPreclose_px() {
        return this.preclose_px;
    }


    public String getOpen_px() {
        return this.open_px;
    }


    public String getStock_plate() {
        return this.stock_plate;
    }

    public String getStock_type() {
        return this.stock_type;
    }

    public String getIsOption() {
        return isOption;
    }

    public void setIsOption(String isOption) {
        this.isOption = isOption;
    }
}

