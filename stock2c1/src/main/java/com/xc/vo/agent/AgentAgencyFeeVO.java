package com.xc.vo.agent;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AgentAgencyFeeVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 代理id
     */
    private Integer agentId;

    /**
     * 状态：1、可用，0、停用
     */
    private Integer status;

    /**
     * 业务主键id
     */
    private Integer businessId;

    /**
     * 费用类型：1、入仓手续费，2、平仓手续费，3、延递费(留仓费)，4、分红
     */
    private Integer feeType;

    /**
     * 收支类型：1、收，2、支
     */
    private Integer aymentType;

    /**
     * 总金额
     */
    private BigDecimal totalMoney;

    /**
     * 利润金额
     */
    private BigDecimal profitMoney;

    /**
     * 添加时间
     */
    private Date addTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 备注
     */
    private String remarks;


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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public Integer getFeeType() {
        return feeType;
    }

    public void setFeeType(Integer feeType) {
        this.feeType = feeType;
    }

    public Integer getAymentType() {
        return aymentType;
    }

    public void setAymentType(Integer aymentType) {
        this.aymentType = aymentType;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public BigDecimal getProfitMoney() {
        return profitMoney;
    }

    public void setProfitMoney(BigDecimal profitMoney) {
        this.profitMoney = profitMoney;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
