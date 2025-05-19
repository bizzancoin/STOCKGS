package com.xc.pojo;

import java.math.BigDecimal;
import java.util.Date;


public class AgentUser {
    private Integer id;
    private String agentName;
    private String agentPwd;
    private String agentRealName;
    private String agentPhone;
    private String agentCode;
    private Date addTime;
    private Integer isLock;
    private Integer parentId;
    private String parentName;
    //代理级别
    private Integer agentLevel;
    /**
     * 手续费比例
     */
    private BigDecimal poundageScale;

    /**
     * 递延费比例
     */
    private BigDecimal deferredFeesScale;

    /**
     * 分红比例
     */
    private BigDecimal receiveDividendsScale;

    /**
     * 总资金
     */
    private BigDecimal totalMoney;

    /**
     * 杠杆倍数,多个用/分割
     */
    private String siteLever;


    public AgentUser(Integer id, String agentName, String agentPwd, String agentRealName, String agentPhone, String agentCode, Date addTime, Integer isLock, Integer parentId, String parentName, Integer agentLevel, BigDecimal poundageScale, BigDecimal deferredFeesScale, BigDecimal receiveDividendsScale, BigDecimal totalMoney, String siteLever) {
        this.id = id;
        this.agentName = agentName;
        this.agentPwd = agentPwd;
        this.agentRealName = agentRealName;
        this.agentPhone = agentPhone;
        this.agentCode = agentCode;
        this.addTime = addTime;
        this.isLock = isLock;
        this.parentId = parentId;
        this.parentName = parentName;
        this.agentLevel = agentLevel;
        this.poundageScale = poundageScale;
        this.deferredFeesScale = deferredFeesScale;
        this.receiveDividendsScale = receiveDividendsScale;
        this.totalMoney = totalMoney;
        this.siteLever = siteLever;
    }

    public AgentUser() {
    }

    public Integer getId() {
        return this.id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getAgentName() {
        return this.agentName;
    }


    public void setAgentName(String agentName) {
        this.agentName = (agentName == null) ? null : agentName.trim();
    }


    public String getAgentPwd() {
        return this.agentPwd;
    }


    public void setAgentPwd(String agentPwd) {
        this.agentPwd = (agentPwd == null) ? null : agentPwd.trim();
    }


    public String getAgentRealName() {
        return this.agentRealName;
    }


    public void setAgentRealName(String agentRealName) {
        this.agentRealName = (agentRealName == null) ? null : agentRealName.trim();
    }


    public String getAgentPhone() {
        return this.agentPhone;
    }


    public void setAgentPhone(String agentPhone) {
        this.agentPhone = (agentPhone == null) ? null : agentPhone.trim();
    }


    public String getAgentCode() {
        return this.agentCode;
    }


    public void setAgentCode(String agentCode) {
        this.agentCode = (agentCode == null) ? null : agentCode.trim();
    }


    public Date getAddTime() {
        return this.addTime;
    }


    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }


    public Integer getIsLock() {
        return this.isLock;
    }


    public void setIsLock(Integer isLock) {
        this.isLock = isLock;
    }


    public Integer getParentId() {
        return this.parentId;
    }


    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }


    public String getParentName() {
        return this.parentName;
    }


    public void setParentName(String parentName) {
        this.parentName = (parentName == null) ? null : parentName.trim();
    }

    public Integer getAgentLevel() {
        return agentLevel;
    }

    public void setAgentLevel(Integer agentLevel) {
        this.agentLevel = agentLevel;
    }

    public BigDecimal getPoundageScale() {
        return poundageScale;
    }

    public void setPoundageScale(BigDecimal poundageScale) {
        this.poundageScale = poundageScale;
    }

    public BigDecimal getDeferredFeesScale() {
        return deferredFeesScale;
    }

    public void setDeferredFeesScale(BigDecimal deferredFeesScale) {
        this.deferredFeesScale = deferredFeesScale;
    }

    public BigDecimal getReceiveDividendsScale() {
        return receiveDividendsScale;
    }

    public void setReceiveDividendsScale(BigDecimal receiveDividendsScale) {
        this.receiveDividendsScale = receiveDividendsScale;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getSiteLever() {
        return siteLever;
    }

    public void setSiteLever(String siteLever) {
        this.siteLever = siteLever;
    }
}
