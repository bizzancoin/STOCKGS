package com.xc.vo.pay;

public class GuoPayVO {
    private String postUrl;
    private String pay_memberid;
    private String pay_orderid;
    private String pay_applydate;
    private String pay_bankcode;
    private String pay_notifyurl;

    public void setPostUrl(String postUrl) {
        this.postUrl = postUrl;
    }

    private String pay_callbackurl;
    private String pay_amount;
    private String pay_productname;
    private String pay_productnum;
    private String pay_productdesc;
    private String pay_producturl;
    private String pay_md5sign;

    public void setPay_memberid(String pay_memberid) {
        this.pay_memberid = pay_memberid;
    }

    public void setPay_orderid(String pay_orderid) {
        this.pay_orderid = pay_orderid;
    }

    public void setPay_applydate(String pay_applydate) {
        this.pay_applydate = pay_applydate;
    }

    public void setPay_bankcode(String pay_bankcode) {
        this.pay_bankcode = pay_bankcode;
    }

    public void setPay_notifyurl(String pay_notifyurl) {
        this.pay_notifyurl = pay_notifyurl;
    }

    public void setPay_callbackurl(String pay_callbackurl) {
        this.pay_callbackurl = pay_callbackurl;
    }

    public void setPay_amount(String pay_amount) {
        this.pay_amount = pay_amount;
    }

    public void setPay_productname(String pay_productname) {
        this.pay_productname = pay_productname;
    }

    public void setPay_productnum(String pay_productnum) {
        this.pay_productnum = pay_productnum;
    }

    public void setPay_productdesc(String pay_productdesc) {
        this.pay_productdesc = pay_productdesc;
    }

    public void setPay_producturl(String pay_producturl) {
        this.pay_producturl = pay_producturl;
    }

    public void setPay_md5sign(String pay_md5sign) {
        this.pay_md5sign = pay_md5sign;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof GuoPayVO)) return false;
        GuoPayVO other = (GuoPayVO) o;
        if (!other.canEqual(this)) return false;
        Object this$postUrl = getPostUrl(), other$postUrl = other.getPostUrl();
        if ((this$postUrl == null) ? (other$postUrl != null) : !this$postUrl.equals(other$postUrl)) return false;
        Object this$pay_memberid = getPay_memberid(), other$pay_memberid = other.getPay_memberid();
        if ((this$pay_memberid == null) ? (other$pay_memberid != null) : !this$pay_memberid.equals(other$pay_memberid))
            return false;
        Object this$pay_orderid = getPay_orderid(), other$pay_orderid = other.getPay_orderid();
        if ((this$pay_orderid == null) ? (other$pay_orderid != null) : !this$pay_orderid.equals(other$pay_orderid))
            return false;
        Object this$pay_applydate = getPay_applydate(), other$pay_applydate = other.getPay_applydate();
        if ((this$pay_applydate == null) ? (other$pay_applydate != null) : !this$pay_applydate.equals(other$pay_applydate))
            return false;
        Object this$pay_bankcode = getPay_bankcode(), other$pay_bankcode = other.getPay_bankcode();
        if ((this$pay_bankcode == null) ? (other$pay_bankcode != null) : !this$pay_bankcode.equals(other$pay_bankcode))
            return false;
        Object this$pay_notifyurl = getPay_notifyurl(), other$pay_notifyurl = other.getPay_notifyurl();
        if ((this$pay_notifyurl == null) ? (other$pay_notifyurl != null) : !this$pay_notifyurl.equals(other$pay_notifyurl))
            return false;
        Object this$pay_callbackurl = getPay_callbackurl(), other$pay_callbackurl = other.getPay_callbackurl();
        if ((this$pay_callbackurl == null) ? (other$pay_callbackurl != null) : !this$pay_callbackurl.equals(other$pay_callbackurl))
            return false;
        Object this$pay_amount = getPay_amount(), other$pay_amount = other.getPay_amount();
        if ((this$pay_amount == null) ? (other$pay_amount != null) : !this$pay_amount.equals(other$pay_amount))
            return false;
        Object this$pay_productname = getPay_productname(), other$pay_productname = other.getPay_productname();
        if ((this$pay_productname == null) ? (other$pay_productname != null) : !this$pay_productname.equals(other$pay_productname))
            return false;
        Object this$pay_productnum = getPay_productnum(), other$pay_productnum = other.getPay_productnum();
        if ((this$pay_productnum == null) ? (other$pay_productnum != null) : !this$pay_productnum.equals(other$pay_productnum))
            return false;
        Object this$pay_productdesc = getPay_productdesc(), other$pay_productdesc = other.getPay_productdesc();
        if ((this$pay_productdesc == null) ? (other$pay_productdesc != null) : !this$pay_productdesc.equals(other$pay_productdesc))
            return false;
        Object this$pay_producturl = getPay_producturl(), other$pay_producturl = other.getPay_producturl();
        if ((this$pay_producturl == null) ? (other$pay_producturl != null) : !this$pay_producturl.equals(other$pay_producturl))
            return false;
        Object this$pay_md5sign = getPay_md5sign(), other$pay_md5sign = other.getPay_md5sign();
        return !((this$pay_md5sign == null) ? (other$pay_md5sign != null) : !this$pay_md5sign.equals(other$pay_md5sign));
    }

    protected boolean canEqual(Object other) {
        return other instanceof GuoPayVO;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $postUrl = getPostUrl();
        result = result * 59 + (($postUrl == null) ? 43 : $postUrl.hashCode());
        Object $pay_memberid = getPay_memberid();
        result = result * 59 + (($pay_memberid == null) ? 43 : $pay_memberid.hashCode());
        Object $pay_orderid = getPay_orderid();
        result = result * 59 + (($pay_orderid == null) ? 43 : $pay_orderid.hashCode());
        Object $pay_applydate = getPay_applydate();
        result = result * 59 + (($pay_applydate == null) ? 43 : $pay_applydate.hashCode());
        Object $pay_bankcode = getPay_bankcode();
        result = result * 59 + (($pay_bankcode == null) ? 43 : $pay_bankcode.hashCode());
        Object $pay_notifyurl = getPay_notifyurl();
        result = result * 59 + (($pay_notifyurl == null) ? 43 : $pay_notifyurl.hashCode());
        Object $pay_callbackurl = getPay_callbackurl();
        result = result * 59 + (($pay_callbackurl == null) ? 43 : $pay_callbackurl.hashCode());
        Object $pay_amount = getPay_amount();
        result = result * 59 + (($pay_amount == null) ? 43 : $pay_amount.hashCode());
        Object $pay_productname = getPay_productname();
        result = result * 59 + (($pay_productname == null) ? 43 : $pay_productname.hashCode());
        Object $pay_productnum = getPay_productnum();
        result = result * 59 + (($pay_productnum == null) ? 43 : $pay_productnum.hashCode());
        Object $pay_productdesc = getPay_productdesc();
        result = result * 59 + (($pay_productdesc == null) ? 43 : $pay_productdesc.hashCode());
        Object $pay_producturl = getPay_producturl();
        result = result * 59 + (($pay_producturl == null) ? 43 : $pay_producturl.hashCode());
        Object $pay_md5sign = getPay_md5sign();
        return result * 59 + (($pay_md5sign == null) ? 43 : $pay_md5sign.hashCode());
    }

    public String toString() {
        return "GuoPayVO(postUrl=" + getPostUrl() + ", pay_memberid=" + getPay_memberid() + ", pay_orderid=" + getPay_orderid() + ", pay_applydate=" + getPay_applydate() + ", pay_bankcode=" + getPay_bankcode() + ", pay_notifyurl=" + getPay_notifyurl() + ", pay_callbackurl=" + getPay_callbackurl() + ", pay_amount=" + getPay_amount() + ", pay_productname=" + getPay_productname() + ", pay_productnum=" + getPay_productnum() + ", pay_productdesc=" + getPay_productdesc() + ", pay_producturl=" + getPay_producturl() + ", pay_md5sign=" + getPay_md5sign() + ")";
    }


    public String getPostUrl() {
        return this.postUrl;
    }

    public String getPay_memberid() {
        return this.pay_memberid;
    }

    public String getPay_orderid() {
        return this.pay_orderid;
    }

    public String getPay_applydate() {
        return this.pay_applydate;
    }

    public String getPay_bankcode() {
        return this.pay_bankcode;
    }

    public String getPay_notifyurl() {
        return this.pay_notifyurl;
    }

    public String getPay_callbackurl() {
        return this.pay_callbackurl;
    }

    public String getPay_amount() {
        return this.pay_amount;
    }

    public String getPay_productname() {
        return this.pay_productname;
    }

    public String getPay_productnum() {
        return this.pay_productnum;
    }

    public String getPay_productdesc() {
        return this.pay_productdesc;
    }

    public String getPay_producturl() {
        return this.pay_producturl;
    }

    public String getPay_md5sign() {
        return this.pay_md5sign;
    }
}

