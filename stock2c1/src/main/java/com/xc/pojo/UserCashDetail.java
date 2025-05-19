package com.xc.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class UserCashDetail {

    private Integer id;

    private Integer agentId;

    private String agentName;

    private Integer userId;

    private String userName;

    private Integer positionId;

    private String deType;

    private BigDecimal deAmt;

    private String deSummary;

    private Date addTime;

    private String addIp;

    private String addAddress;

    private Integer isRead;


    public UserCashDetail(Integer id, Integer agentId, String agentName, Integer userId, String userName, Integer positionId, String deType, BigDecimal deAmt, String deSummary, Date addTime, String addIp, String addAddress, Integer isRead) {
        this.id = id;

        this.agentId = agentId;

        this.agentName = agentName;

        this.userId = userId;

        this.userName = userName;

        this.positionId = positionId;

        this.deType = deType;

        this.deAmt = deAmt;

        this.deSummary = deSummary;

        this.addTime = addTime;

        this.addIp = addIp;

        this.addAddress = addAddress;

        this.isRead = isRead;
    }

    public UserCashDetail() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getDeType() {
        return deType;
    }

    public void setDeType(String deType) {
        this.deType = deType;
    }

    public BigDecimal getDeAmt() {
        return deAmt;
    }

    public void setDeAmt(BigDecimal deAmt) {
        this.deAmt = deAmt;
    }

    public String getDeSummary() {
        return deSummary;
    }

    public void setDeSummary(String deSummary) {
        this.deSummary = deSummary;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getAddIp() {
        return addIp;
    }

    public void setAddIp(String addIp) {
        this.addIp = addIp;
    }

    public String getAddAddress() {
        return addAddress;
    }

    public void setAddAddress(String addAddress) {
        this.addAddress = addAddress;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }
}