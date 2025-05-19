package com.xc.utils.stock.lide;

import java.io.Serializable;

/**
 * 立德盘口数据
 */
public class LideDataPanVo implements Serializable {

    private static final  long serialVersionUID=1l;

    private String code;

    private String bn1;
    private String bn2;
    private String bn3;
    private String bn4;
    private String bn5;

    private String sn1;
    private String sn2;
    private String sn3;
    private String sn4;
    private String sn5;


    public LideDataPanVo() {
    }

    public LideDataPanVo(String code, String bn1, String bn2, String bn3, String bn4, String bn5, String sn1, String sn2, String sn3, String sn4, String sn5) {
        this.code = code;
        this.bn1 = bn1;
        this.bn2 = bn2;
        this.bn3 = bn3;
        this.bn4 = bn4;
        this.bn5 = bn5;
        this.sn1 = sn1;
        this.sn2 = sn2;
        this.sn3 = sn3;
        this.sn4 = sn4;
        this.sn5 = sn5;
    }

    @Override
    public String toString() {
        return "LideDataPanVo{" +
                "code='" + code + '\'' +
                ", bn1='" + bn1 + '\'' +
                ", bn2='" + bn2 + '\'' +
                ", bn3='" + bn3 + '\'' +
                ", bn4='" + bn4 + '\'' +
                ", bn5='" + bn5 + '\'' +
                ", sn1='" + sn1 + '\'' +
                ", sn2='" + sn2 + '\'' +
                ", sn3='" + sn3 + '\'' +
                ", sn4='" + sn4 + '\'' +
                ", sn5='" + sn5 + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBn1() {
        return bn1;
    }

    public void setBn1(String bn1) {
        this.bn1 = bn1;
    }

    public String getBn2() {
        return bn2;
    }

    public void setBn2(String bn2) {
        this.bn2 = bn2;
    }

    public String getBn3() {
        return bn3;
    }

    public void setBn3(String bn3) {
        this.bn3 = bn3;
    }

    public String getBn4() {
        return bn4;
    }

    public void setBn4(String bn4) {
        this.bn4 = bn4;
    }

    public String getBn5() {
        return bn5;
    }

    public void setBn5(String bn5) {
        this.bn5 = bn5;
    }

    public String getSn1() {
        return sn1;
    }

    public void setSn1(String sn1) {
        this.sn1 = sn1;
    }

    public String getSn2() {
        return sn2;
    }

    public void setSn2(String sn2) {
        this.sn2 = sn2;
    }

    public String getSn3() {
        return sn3;
    }

    public void setSn3(String sn3) {
        this.sn3 = sn3;
    }

    public String getSn4() {
        return sn4;
    }

    public void setSn4(String sn4) {
        this.sn4 = sn4;
    }

    public String getSn5() {
        return sn5;
    }

    public void setSn5(String sn5) {
        this.sn5 = sn5;
    }
}
