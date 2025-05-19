package com.xc.utils.ip.juhe;


public class AddressResultsVo {
    private String resultcode;
    private String reason;
    private AddressResult result;
    private int error_code;

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setResult(AddressResult result) {
        this.result = result;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof AddressResultsVo)) return false;
        AddressResultsVo other = (AddressResultsVo) o;
        if (!other.canEqual(this)) return false;
        Object this$resultcode = getResultcode(), other$resultcode = other.getResultcode();
        if ((this$resultcode == null) ? (other$resultcode != null) : !this$resultcode.equals(other$resultcode))
            return false;
        Object this$reason = getReason(), other$reason = other.getReason();
        if ((this$reason == null) ? (other$reason != null) : !this$reason.equals(other$reason)) return false;
        Object this$result = getResult(), other$result = other.getResult();
        return ((this$result == null) ? (other$result != null) : !this$result.equals(other$result)) ? false : (!(getError_code() != other.getError_code()));
    }

    protected boolean canEqual(Object other) {
        return other instanceof AddressResultsVo;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $resultcode = getResultcode();
        result = result * 59 + (($resultcode == null) ? 43 : $resultcode.hashCode());
        Object $reason = getReason();
        result = result * 59 + (($reason == null) ? 43 : $reason.hashCode());
        Object $result = getResult();
        result = result * 59 + (($result == null) ? 43 : $result.hashCode());
        return result * 59 + getError_code();
    }

    public String toString() {
        return "AddressResultsVo(resultcode=" + getResultcode() + ", reason=" + getReason() + ", result=" + getResult() + ", error_code=" + getError_code() + ")";
    }


    public String getResultcode() {
        return this.resultcode;
    }

    public String getReason() {
        return this.reason;
    }

    public AddressResult getResult() {
        return this.result;
    }

    public int getError_code() {
        return this.error_code;
    }
}

