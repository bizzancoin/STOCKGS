package com.xc.vo.agent;


public class AgentLoginResultVO {
    private String key;
    private String token;

    public void setKey(String key) {
        this.key = key;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof AgentLoginResultVO)) return false;
        AgentLoginResultVO other = (AgentLoginResultVO) o;
        if (!other.canEqual(this)) return false;
        Object this$key = getKey(), other$key = other.getKey();
        if ((this$key == null) ? (other$key != null) : !this$key.equals(other$key)) return false;
        Object this$token = getToken(), other$token = other.getToken();
        return !((this$token == null) ? (other$token != null) : !this$token.equals(other$token));
    }

    protected boolean canEqual(Object other) {
        return other instanceof AgentLoginResultVO;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $key = getKey();
        result = result * 59 + (($key == null) ? 43 : $key.hashCode());
        Object $token = getToken();
        return result * 59 + (($token == null) ? 43 : $token.hashCode());
    }

    public String toString() {
        return "AgentLoginResultVO(key=" + getKey() + ", token=" + getToken() + ")";
    }


    public String getKey() {
        return this.key;
    }

    public String getToken() {
        return this.token;
    }
}
