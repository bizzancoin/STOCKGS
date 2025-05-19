package com.xc.vo.stock.k.echarts;

import java.util.Arrays;

public class EchartsDataVO {
    private String stockName;
    private String stockCode;
    private double[][] values;
    private Object[][] volumes;
    private String[] date;

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public void setValues(double[][] values) {
        this.values = values;
    }

    public void setVolumes(Object[][] volumes) {
        this.volumes = volumes;
    }

    public void setDate(String[] date) {
        this.date = date;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof EchartsDataVO)) return false;
        EchartsDataVO other = (EchartsDataVO) o;
        if (!other.canEqual(this)) return false;
        Object this$stockName = getStockName(), other$stockName = other.getStockName();
        if ((this$stockName == null) ? (other$stockName != null) : !this$stockName.equals(other$stockName))
            return false;
        Object this$stockCode = getStockCode(), other$stockCode = other.getStockCode();
        return ((this$stockCode == null) ? (other$stockCode != null) : !this$stockCode.equals(other$stockCode)) ? false : (!Arrays.deepEquals(getValues(), other.getValues()) ? false : (!Arrays.deepEquals(getVolumes(), other.getVolumes()) ? false : (!!Arrays.deepEquals(getDate(), other.getDate()))));
    }

    protected boolean canEqual(Object other) {
        return other instanceof EchartsDataVO;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $stockName = getStockName();
        result = result * 59 + (($stockName == null) ? 43 : $stockName.hashCode());
        Object $stockCode = getStockCode();
        result = result * 59 + (($stockCode == null) ? 43 : $stockCode.hashCode());
        result = result * 59 + Arrays.deepHashCode(getValues());
        result = result * 59 + Arrays.deepHashCode(getVolumes());
        return result * 59 + Arrays.deepHashCode(getDate());
    }

    public String toString() {
        return "EchartsDataVO(stockName=" + getStockName() + ", stockCode=" + getStockCode() + ", values=" + Arrays.deepToString(getValues()) + ", volumes=" + Arrays.deepToString(getVolumes()) + ", date=" + Arrays.deepToString(getDate()) + ")";
    }


    public String getStockName() {
        return this.stockName;
    }


    public String getStockCode() {
        return this.stockCode;
    }


    public double[][] getValues() {
        return this.values;
    }

    public Object[][] getVolumes() {
        return this.volumes;
    }

    public String[] getDate() {
        return this.date;
    }
}

