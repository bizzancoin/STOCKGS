package com.xc.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class ConvertBondApply {
    private Integer id;

    private Integer agentId;

    private Integer userId;

    private String tel;

    private Integer bondId;

    private BigDecimal applyMoney;

    private Integer applyNum;

    private Integer sucNum;

    private BigDecimal sucMony;

    private Date applyDate;

    private Integer status;

    private BigDecimal refundMony;

    private Date sucDate;

    public ConvertBondApply(Integer id, Integer agentId, Integer userId, String tel, Integer bondId, BigDecimal applyMoney, Integer applyNum, Integer sucNum, BigDecimal sucMony, Date applyDate, Integer status, BigDecimal refundMony, Date sucDate) {
        this.id = id;
        this.agentId = agentId;
        this.userId = userId;
        this.tel = tel;
        this.bondId = bondId;
        this.applyMoney = applyMoney;
        this.applyNum = applyNum;
        this.sucNum = sucNum;
        this.sucMony = sucMony;
        this.applyDate = applyDate;
        this.status = status;
        this.refundMony = refundMony;
        this.sucDate = sucDate;
    }

    public ConvertBondApply() {
        super();
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public Integer getBondId() {
        return bondId;
    }

    public void setBondId(Integer bondId) {
        this.bondId = bondId;
    }

    public BigDecimal getApplyMoney() {
        return applyMoney;
    }

    public void setApplyMoney(BigDecimal applyMoney) {
        this.applyMoney = applyMoney;
    }

    public Integer getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(Integer applyNum) {
        this.applyNum = applyNum;
    }

    public Integer getSucNum() {
        return sucNum;
    }

    public void setSucNum(Integer sucNum) {
        this.sucNum = sucNum;
    }

    public BigDecimal getSucMony() {
        return sucMony;
    }

    public void setSucMony(BigDecimal sucMony) {
        this.sucMony = sucMony;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getRefundMony() {
        return refundMony;
    }

    public void setRefundMony(BigDecimal refundMony) {
        this.refundMony = refundMony;
    }

    public Date getSucDate() {
        return sucDate;
    }

    public void setSucDate(Date sucDate) {
        this.sucDate = sucDate;
    }
}