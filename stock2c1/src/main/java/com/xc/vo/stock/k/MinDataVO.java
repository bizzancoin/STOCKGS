package com.xc.vo.stock.k;


import com.xc.utils.stock.sina.vo.SinaStockMinData;

import java.util.List;


public class MinDataVO {

    private String stockName;

    private String stockCode;

    private String gid;

    private List<SinaStockMinData> data;


    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public void setData(List<SinaStockMinData> data) {
        this.data = data;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof MinDataVO)) return false;
        MinDataVO other = (MinDataVO) o;
        if (!other.canEqual(this)) return false;
        Object this$stockName = getStockName(), other$stockName = other.getStockName();
        if ((this$stockName == null) ? (other$stockName != null) : !this$stockName.equals(other$stockName))
            return false;
        Object this$stockCode = getStockCode(), other$stockCode = other.getStockCode();
        if ((this$stockCode == null) ? (other$stockCode != null) : !this$stockCode.equals(other$stockCode))
            return false;
        Object this$gid = getGid(), other$gid = other.getGid();
        if ((this$gid == null) ? (other$gid != null) : !this$gid.equals(other$gid)) return false;
        Object this$data = getData(), other$data = other.getData();
        return !((this$data == null) ? (other$data != null) : !this$data.equals(other$data));
    }

    protected boolean canEqual(Object other) {
        return other instanceof MinDataVO;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $stockName = getStockName();
        result = result * 59 + (($stockName == null) ? 43 : $stockName.hashCode());
        Object $stockCode = getStockCode();
        result = result * 59 + (($stockCode == null) ? 43 : $stockCode.hashCode());
        Object $gid = getGid();
        result = result * 59 + (($gid == null) ? 43 : $gid.hashCode());
        Object $data = getData();
        return result * 59 + (($data == null) ? 43 : $data.hashCode());
    }

    public String toString() {
        return "MinDataVO(stockName=" + getStockName() + ", stockCode=" + getStockCode() + ", gid=" + getGid() + ", data=" + getData() + ")";
    }


    public String getStockName() {
        return this.stockName;
    }


    public String getStockCode() {
        return this.stockCode;
    }


    public String getGid() {
        return this.gid;
    }


    public List<SinaStockMinData> getData() {
        return this.data;
    }

}

