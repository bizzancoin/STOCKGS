package com.xc.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class ConvertBond {
    private Integer id;

    private String bondBuyCode;

    private String bondName;

    private String bondType;

    private String bondCode;

    private String stockCode;

    private BigDecimal price;

    private Date applyDate;

    private Date pubDate;

    private Date listDate;

    private Integer surplus;

    private Integer applyLimit;

    private Integer status;

    private Date createTime;

    public ConvertBond(Integer id, String bondBuyCode, String bondName, String bondType, String bondCode, String stockCode, BigDecimal price, Date applyDate, Date pubDate, Date listDate, Integer surplus, Integer applyLimit, Integer status, Date createTime) {
        this.id = id;
        this.bondBuyCode = bondBuyCode;
        this.bondName = bondName;
        this.bondType = bondType;
        this.bondCode = bondCode;
        this.stockCode = stockCode;
        this.price = price;
        this.applyDate = applyDate;
        this.pubDate = pubDate;
        this.listDate = listDate;
        this.surplus = surplus;
        this.applyLimit = applyLimit;
        this.status = status;
        this.createTime = createTime;
    }

    public ConvertBond() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBondBuyCode() {
        return bondBuyCode;
    }

    public void setBondBuyCode(String bondBuyCode) {
        this.bondBuyCode = bondBuyCode == null ? null : bondBuyCode.trim();
    }

    public String getBondName() {
        return bondName;
    }

    public void setBondName(String bondName) {
        this.bondName = bondName == null ? null : bondName.trim();
    }

    public String getBondType() {
        return bondType;
    }

    public void setBondType(String bondType) {
        this.bondType = bondType == null ? null : bondType.trim();
    }

    public String getBondCode() {
        return bondCode;
    }

    public void setBondCode(String bondCode) {
        this.bondCode = bondCode == null ? null : bondCode.trim();
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode == null ? null : stockCode.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public Date getListDate() {
        return listDate;
    }

    public void setListDate(Date listDate) {
        this.listDate = listDate;
    }

    public Integer getSurplus() {
        return surplus;
    }

    public void setSurplus(Integer surplus) {
        this.surplus = surplus;
    }

    public Integer getApplyLimit() {
        return applyLimit;
    }

    public void setApplyLimit(Integer applyLimit) {
        this.applyLimit = applyLimit;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}