package com.xc.vo.user;

public class UserBankInfoVO {
    private String realName;
    private String bankName;
    private String bankNo;
    private String bankAddress;
    //银行卡号
    private String bankImg;
    //转数id
    private String bankPhone;


    public String getBankPhone() {
        return bankPhone;
    }

    public void setBankPhone(String bankPhone) {
        this.bankPhone = bankPhone;
    }

    public String getBankImg() {
        return bankImg;
    }

    public void setBankImg(String bankImg) {
        this.bankImg = bankImg;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof UserBankInfoVO)) return false;
        UserBankInfoVO other = (UserBankInfoVO) o;
        if (!other.canEqual(this)) return false;
        Object this$realName = getRealName(), other$realName = other.getRealName();
        if ((this$realName == null) ? (other$realName != null) : !this$realName.equals(other$realName)) return false;
        Object this$bankName = getBankName(), other$bankName = other.getBankName();
        if ((this$bankName == null) ? (other$bankName != null) : !this$bankName.equals(other$bankName)) return false;
        Object this$bankNo = getBankNo(), other$bankNo = other.getBankNo();
        if ((this$bankNo == null) ? (other$bankNo != null) : !this$bankNo.equals(other$bankNo)) return false;
        Object this$bankAddress = getBankAddress(), other$bankAddress = other.getBankAddress();
        return !((this$bankAddress == null) ? (other$bankAddress != null) : !this$bankAddress.equals(other$bankAddress));
    }

    protected boolean canEqual(Object other) {
        return other instanceof UserBankInfoVO;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $realName = getRealName();
        result = result * 59 + (($realName == null) ? 43 : $realName.hashCode());
        Object $bankName = getBankName();
        result = result * 59 + (($bankName == null) ? 43 : $bankName.hashCode());
        Object $bankNo = getBankNo();
        result = result * 59 + (($bankNo == null) ? 43 : $bankNo.hashCode());
        Object $bankAddress = getBankAddress();
        return result * 59 + (($bankAddress == null) ? 43 : $bankAddress.hashCode());
    }

    public String toString() {
        return "UserBankInfoVO(realName=" + getRealName() + ", bankName=" + getBankName() + ", bankNo=" + getBankNo() + ", bankAddress=" + getBankAddress() + ")";
    }


    public String getRealName() {
        return this.realName;
    }

    public String getBankName() {
        return this.bankName;
    }

    public String getBankNo() {
        return this.bankNo;
    }

    public String getBankAddress() {
        return this.bankAddress;
    }
}
