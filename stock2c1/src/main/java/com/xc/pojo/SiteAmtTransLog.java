package com.xc.pojo;


import java.math.BigDecimal;
import java.util.Date;


public class SiteAmtTransLog {
    private Integer id;
    private Integer userId;
    private String realName;
    private Integer agentId;
    private String amtFrom;
    private String amtTo;
    private BigDecimal transAmt;
    private Date addTime;
    private String tDesc;

    public SiteAmtTransLog(Integer id, Integer userId, String realName, Integer agentId, String amtFrom, String amtTo, BigDecimal transAmt, Date addTime, String tDesc) {
        this.id = id;
        this.userId = userId;
        this.realName = realName;
        this.agentId = agentId;
        this.amtFrom = amtFrom;
        this.amtTo = amtTo;
        this.transAmt = transAmt;
        this.addTime = addTime;
        this.tDesc = tDesc;
    }

    public SiteAmtTransLog() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public String getAmtFrom() {
        return amtFrom;
    }

    public void setAmtFrom(String amtFrom) {
        this.amtFrom = amtFrom;
    }

    public String getAmtTo() {
        return amtTo;
    }

    public void setAmtTo(String amtTo) {
        this.amtTo = amtTo;
    }

    public BigDecimal getTransAmt() {
        return transAmt;
    }

    public void setTransAmt(BigDecimal transAmt) {
        this.transAmt = transAmt;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String gettDesc() {
        return tDesc;
    }

    public void settDesc(String tDesc) {
        this.tDesc = tDesc;
    }
}