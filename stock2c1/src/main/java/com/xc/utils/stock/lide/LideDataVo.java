package com.xc.utils.stock.lide;

import java.io.Serializable;

/**
 * 立德全量数据
 */
public class LideDataVo implements Serializable {

    private static final  long serialVersionUID=2l;

    //品种代码,名称,开盘价,最高价,最低价,昨收,现价,成交量,成交额,涨跌幅,涨跌额,卖一价,卖二价,卖三价,卖四价,卖五价,买一价,买二价,买三价,买四价,买五价
    private String code;
    private String name;
    private String open;
    private String high;
    private String low;
    //前一天收盘价
    private String prclose;
    private String price;
    //成交量
    private String amount;
    //成家额
    private String volume;


    //涨跌幅度
    private String zdf;
    //涨跌额度
    private String zde;

    private String s1;
    private String s2;
    private String s3;
    private String s4;
    private String s5;

    private String b1;
    private String b2;
    private String b3;
    private String b4;
    private String b5;


    public LideDataVo(String code, String name, String open, String high, String low, String prclose, String price, String amount, String volume, String zdf, String zde, String s1, String s2, String s3, String s4, String s5, String b1, String b2, String b3, String b4, String b5) {
        this.code = code;
        this.name = name;
        this.open = open;
        this.high = high;
        this.low = low;
        this.prclose = prclose;
        this.price = price;
        this.amount = amount;
        this.volume = volume;
        this.zdf = zdf;
        this.zde = zde;
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.s4 = s4;
        this.s5 = s5;
        this.b1 = b1;
        this.b2 = b2;
        this.b3 = b3;
        this.b4 = b4;
        this.b5 = b5;
    }

    public LideDataVo() {
    }

    @Override
    public String toString() {
        return "LideDataVo{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", low='" + low + '\'' +
                ", high='" + high + '\'' +
                ", open='" + open + '\'' +
                ", prclose='" + prclose + '\'' +
                ", volume='" + volume + '\'' +
                ", amount='" + amount + '\'' +
                ", zdf='" + zdf + '\'' +
                ", zde='" + zde + '\'' +
                ", b1='" + b1 + '\'' +
                ", b2='" + b2 + '\'' +
                ", b3='" + b3 + '\'' +
                ", b4='" + b4 + '\'' +
                ", b5='" + b5 + '\'' +
                ", s1='" + s1 + '\'' +
                ", s2='" + s2 + '\'' +
                ", s3='" + s3 + '\'' +
                ", s4='" + s4 + '\'' +
                ", s5='" + s5 + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getPrclose() {
        return prclose;
    }

    public void setPrclose(String prclose) {
        this.prclose = prclose;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getZdf() {
        return zdf;
    }

    public void setZdf(String zdf) {
        this.zdf = zdf;
    }

    public String getZde() {
        return zde;
    }

    public void setZde(String zde) {
        this.zde = zde;
    }

    public String getB1() {
        return b1;
    }

    public void setB1(String b1) {
        this.b1 = b1;
    }

    public String getB2() {
        return b2;
    }

    public void setB2(String b2) {
        this.b2 = b2;
    }

    public String getB3() {
        return b3;
    }

    public void setB3(String b3) {
        this.b3 = b3;
    }

    public String getB4() {
        return b4;
    }

    public void setB4(String b4) {
        this.b4 = b4;
    }

    public String getB5() {
        return b5;
    }

    public void setB5(String b5) {
        this.b5 = b5;
    }

    public String getS1() {
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    public String getS2() {
        return s2;
    }

    public void setS2(String s2) {
        this.s2 = s2;
    }

    public String getS3() {
        return s3;
    }

    public void setS3(String s3) {
        this.s3 = s3;
    }

    public String getS4() {
        return s4;
    }

    public void setS4(String s4) {
        this.s4 = s4;
    }

    public String getS5() {
        return s5;
    }

    public void setS5(String s5) {
        this.s5 = s5;
    }
}
