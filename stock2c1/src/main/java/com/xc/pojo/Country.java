package com.xc.pojo;

public class Country {
    private String zhName;

    private String areaCode;

    private String enName;

    private String language;

    private String localCurrency;

    private Integer sort;

    public Country(String zhName, String areaCode, String enName, String language, String localCurrency, Integer sort) {
        this.zhName = zhName;
        this.areaCode = areaCode;
        this.enName = enName;
        this.language = language;
        this.localCurrency = localCurrency;
        this.sort = sort;
    }

    public Country() {
        super();
    }

    public String getZhName() {
        return zhName;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName == null ? null : zhName.trim();
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName == null ? null : enName.trim();
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language == null ? null : language.trim();
    }

    public String getLocalCurrency() {
        return localCurrency;
    }

    public void setLocalCurrency(String localCurrency) {
        this.localCurrency = localCurrency == null ? null : localCurrency.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}