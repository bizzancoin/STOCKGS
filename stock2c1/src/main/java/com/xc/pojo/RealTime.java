package com.xc.pojo;

public class RealTime {
    private int id;

    private String time;

    private int volumes;

    private double price;

    private double rates;

    private int amounts;

    private String stockCode;
    /*均价*/
    private Double averagePrice;

    public String toString() {
        return "RealTime{id=" + this.id + ", time='" + this.time + '\'' + ", volumes=" + this.volumes + ", price=" + this.price + ", rates=" + this.rates  + ", averagePrice=" + this.averagePrice + ", amounts=" + this.amounts + ", stockCode='" + this.stockCode + '\'' + '}';
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getVolumes() {
        return this.volumes;
    }

    public void setVolumes(int volumes) {
        this.volumes = volumes;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRates() {
        return this.rates;
    }

    public void setRates(double rates) {
        this.rates = rates;
    }

    public int getAmounts() {
        return this.amounts;
    }

    public void setAmounts(int amounts) {
        this.amounts = amounts;
    }

    public String getStockCode() {
        return this.stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public Double getAveragePrice() {
        return this.averagePrice;
    }

    public void setAveragePrice(Double averagePrice) {
        this.averagePrice = averagePrice;
    }
}
