
package com.xc.vo.agent;


public class AgentSecondInfoVO {

    private Integer id;

    private String agentName;

    private String agentRealName;

    private String agentPhone;

    private String agentCode;


    public void setId(Integer id) {
        this.id = id;
    }

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

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof AgentSecondInfoVO)) return false;
        AgentSecondInfoVO other = (AgentSecondInfoVO) o;
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
        return !((this$agentCode == null) ? (other$agentCode != null) : !this$agentCode.equals(other$agentCode));
    }

    protected boolean canEqual(Object other) {
        return other instanceof AgentSecondInfoVO;
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
        return result * 59 + (($agentCode == null) ? 43 : $agentCode.hashCode());
    }

    public String toString() {
        return "AgentSecondInfoVO(id=" + getId() + ", agentName=" + getAgentName() + ", agentRealName=" + getAgentRealName() + ", agentPhone=" + getAgentPhone() + ", agentCode=" + getAgentCode() + ")";
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

}
