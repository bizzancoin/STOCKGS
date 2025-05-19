package com.xc.utils.stock.qq;

public class QqMiniData {
    //最新价
    String nowPrice;
    //涨跌
    String zd;
    //涨跌幅度%
    String zdfd;
    //股票代码
    String code;


    public QqMiniData(String nowPrice, String zd, String zdfd) {
            this.nowPrice = nowPrice;
            this.zd = zd;
            this.zdfd = zdfd;
    }

    public QqMiniData(String nowPrice, String zd, String zdfd, String code) {
        this.nowPrice = nowPrice;
        this.zd = zd;
        this.zdfd = zdfd;
        this.code = code;
    }



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNowPrice() {
        return nowPrice;
    }

    public void setNowPrice(String nowPrice) {
        this.nowPrice = nowPrice;
    }

    public String getZd() {
        return zd;
    }

    public void setZd(String zd) {
        this.zd = zd;
    }

    public String getZdfd() {
        return zdfd;
    }

    public void setZdfd(String zdfd) {
        this.zdfd = zdfd;
    }

    @Override
    public String toString() {
        return "QqMiniData{" +
                "nowPrice='" + nowPrice + '\'' +
                ", zd='" + zd + '\'' +
                ", zdfd='" + zdfd + '\'' +
                '}';
    }

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        System.out.println(l/1000);

    }
}
