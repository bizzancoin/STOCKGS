package com.xc.vo.agent;


import java.math.BigDecimal;
import java.util.Date;

public class AgentInfoVO {
    private Integer id;
    private String agentName;
    private String agentRealName;
    private String agentPhone;
    private String agentCode;

    public void setId(Integer id) {
        this.id = id;
    }

    private Date addTime;
    private Integer isLock;
    private Integer parentId;
    private String parentName;
    private String mUrl;
    private String pcUrl;

    /**
     * 总资金
     */
    private BigDecimal totalMoney;

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public void setAgentRealName(String agentRealName) {
        this.agentRealName = agentRealName;
    }

    public void setAgentPhone(String agentPhone) {
        this.agentPhone = agentPhone;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public void setIsLock(Integer isLock) {
        this.isLock = isLock;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public void setMUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public void setPcUrl(String pcUrl) {
        this.pcUrl = pcUrl;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof AgentInfoVO)) return false;
        AgentInfoVO other = (AgentInfoVO) o;
        if (!other.canEqual(this)) return false;
        Object this$id = getId(), other$id = other.getId();
        if ((this$id == null) ? (other$id != null) : !this$id.equals(other$id)) return false;
        Object this$agentName = getAgentName(), other$agentName = other.getAgentName();
        if ((this$agentName == null) ? (other$agentName != null) : !this$agentName.equals(other$agentName))
            return false;
        Object this$agentRealName = getAgentRealName(), other$agentRealName = other.getAgentRealName();
        if ((this$agentRealName == null) ? (other$agentRealName != null) : !this$agentRealName.equals(other$agentRealName))
            return false;
        Object this$agentPhone = getAgentPhone(), other$agentPhone = other.getAgentPhone();
        if ((this$agentPhone == null) ? (other$agentPhone != null) : !this$agentPhone.equals(other$agentPhone))
            return false;
        Object this$agentCode = getAgentCode(), other$agentCode = other.getAgentCode();
        if ((this$agentCode == null) ? (other$agentCode != null) : !this$agentCode.equals(other$agentCode))
            return false;
        Object this$addTime = getAddTime(), other$addTime = other.getAddTime();
        if ((this$addTime == null) ? (other$addTime != null) : !this$addTime.equals(other$addTime)) return false;
        Object this$isLock = getIsLock(), other$isLock = other.getIsLock();
        if ((this$isLock == null) ? (other$isLock != null) : !this$isLock.equals(other$isLock)) return false;
        Object this$parentId = getParentId(), other$parentId = other.getParentId();
        if ((this$parentId == null) ? (other$parentId != null) : !this$parentId.equals(other$parentId)) return false;
        Object this$parentName = getParentName(), other$parentName = other.getParentName();
        if ((this$parentName == null) ? (other$parentName != null) : !this$parentName.equals(other$parentName))
            return false;
        Object this$mUrl = getMUrl(), other$mUrl = other.getMUrl();
        if ((this$mUrl == null) ? (other$mUrl != null) : !this$mUrl.equals(other$mUrl)) return false;
        Object this$pcUrl = getPcUrl(), other$pcUrl = other.getPcUrl();
        return !((this$pcUrl == null) ? (other$pcUrl != null) : !this$pcUrl.equals(other$pcUrl));
    }

    protected boolean canEqual(Object other) {
        return other instanceof AgentInfoVO;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $id = getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        Object $agentName = getAgentName();
        result = result * 59 + (($agentName == null) ? 43 : $agentName.hashCode());
        Object $agentRealName = getAgentRealName();
        result = result * 59 + (($agentRealName == null) ? 43 : $agentRealName.hashCode());
        Object $agentPhone = getAgentPhone();
        result = result * 59 + (($agentPhone == null) ? 43 : $agentPhone.hashCode());
        Object $agentCode = getAgentCode();
        result = result * 59 + (($agentCode == null) ? 43 : $agentCode.hashCode());
        Object $addTime = getAddTime();
        result = result * 59 + (($addTime == null) ? 43 : $addTime.hashCode());
        Object $isLock = getIsLock();
        result = result * 59 + (($isLock == null) ? 43 : $isLock.hashCode());
        Object $parentId = getParentId();
        result = result * 59 + (($parentId == null) ? 43 : $parentId.hashCode());
        Object $parentName = getParentName();
        result = result * 59 + (($parentName == null) ? 43 : $parentName.hashCode());
        Object $mUrl = getMUrl();
        result = result * 59 + (($mUrl == null) ? 43 : $mUrl.hashCode());
        Object $pcUrl = getPcUrl();
        return result * 59 + (($pcUrl == null) ? 43 : $pcUrl.hashCode());
    }

    public String toString() {
        return "AgentInfoVO(id=" + getId() + ", agentName=" + getAgentName() + ", agentRealName=" + getAgentRealName() + ", agentPhone=" + getAgentPhone() + ", agentCode=" + getAgentCode() + ", addTime=" + getAddTime() + ", isLock=" + getIsLock() + ", parentId=" + getParentId() + ", parentName=" + getParentName() + ", mUrl=" + getMUrl() + ", pcUrl=" + getPcUrl() + ")";
    }


    public Integer getId() {
        return this.id;
    }

    public String getAgentName() {
        return this.agentName;
    }

    public String getAgentRealName() {
        return this.agentRealName;
    }

    public String getAgentPhone() {
        return this.agentPhone;
    }

    public String getAgentCode() {
        return this.agentCode;
    }

    public Date getAddTime() {
        return this.addTime;
    }

    public Integer getIsLock() {
        return this.isLock;
    }

    public Integer getParentId() {
        return this.parentId;
    }

    public String getParentName() {
        return this.parentName;
    }

    public String getMUrl() {
        return this.mUrl;
    }

    public String getPcUrl() {
        return this.pcUrl;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }
}

