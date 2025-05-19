package com.xc.vo.pay;

import com.xc.utils.PropertiesUtil;

public class FlyPayVO {
    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl;
    }

    public void setMerchantid(String merchantid) {
        this.merchantid = merchantid;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public void setOrderamount(String orderamount) {
        this.orderamount = orderamount;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    public void setOrdercurrency(String ordercurrency) {
        this.ordercurrency = ordercurrency;
    }

    public void setServerbackurl(String serverbackurl) {
        this.serverbackurl = serverbackurl;
    }

    public void setCallbackurl(String callbackurl) {
        this.callbackurl = callbackurl;
    }

    public void setSigntype(String signtype) {
        this.signtype = signtype;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof FlyPayVO)) return false;
        FlyPayVO other = (FlyPayVO) o;
        if (!other.canEqual(this)) return false;
        Object this$payUrl = getPayUrl(), other$payUrl = other.getPayUrl();
        if ((this$payUrl == null) ? (other$payUrl != null) : !this$payUrl.equals(other$payUrl)) return false;
        Object this$merchantid = getMerchantid(), other$merchantid = other.getMerchantid();
        if ((this$merchantid == null) ? (other$merchantid != null) : !this$merchantid.equals(other$merchantid))
            return false;
        Object this$orderno = getOrderno(), other$orderno = other.getOrderno();
        if ((this$orderno == null) ? (other$orderno != null) : !this$orderno.equals(other$orderno)) return false;
        Object this$orderamount = getOrderamount(), other$orderamount = other.getOrderamount();
        if ((this$orderamount == null) ? (other$orderamount != null) : !this$orderamount.equals(other$orderamount))
            return false;
        Object this$paytype = getPaytype(), other$paytype = other.getPaytype();
        if ((this$paytype == null) ? (other$paytype != null) : !this$paytype.equals(other$paytype)) return false;
        Object this$ordercurrency = getOrdercurrency(), other$ordercurrency = other.getOrdercurrency();
        if ((this$ordercurrency == null) ? (other$ordercurrency != null) : !this$ordercurrency.equals(other$ordercurrency))
            return false;
        Object this$serverbackurl = getServerbackurl(), other$serverbackurl = other.getServerbackurl();
        if ((this$serverbackurl == null) ? (other$serverbackurl != null) : !this$serverbackurl.equals(other$serverbackurl))
            return false;
        Object this$callbackurl = getCallbackurl(), other$callbackurl = other.getCallbackurl();
        if ((this$callbackurl == null) ? (other$callbackurl != null) : !this$callbackurl.equals(other$callbackurl))
            return false;
        Object this$signtype = getSigntype(), other$signtype = other.getSigntype();
        if ((this$signtype == null) ? (other$signtype != null) : !this$signtype.equals(other$signtype)) return false;
        Object this$sign = getSign(), other$sign = other.getSign();
        return !((this$sign == null) ? (other$sign != null) : !this$sign.equals(other$sign));
    }

    protected boolean canEqual(Object other) {
        return other instanceof FlyPayVO;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $payUrl = getPayUrl();
        result = result * 59 + (($payUrl == null) ? 43 : $payUrl.hashCode());
        Object $merchantid = getMerchantid();
        result = result * 59 + (($merchantid == null) ? 43 : $merchantid.hashCode());
        Object $orderno = getOrderno();
        result = result * 59 + (($orderno == null) ? 43 : $orderno.hashCode());
        Object $orderamount = getOrderamount();
        result = result * 59 + (($orderamount == null) ? 43 : $orderamount.hashCode());
        Object $paytype = getPaytype();
        result = result * 59 + (($paytype == null) ? 43 : $paytype.hashCode());
        Object $ordercurrency = getOrdercurrency();
        result = result * 59 + (($ordercurrency == null) ? 43 : $ordercurrency.hashCode());
        Object $serverbackurl = getServerbackurl();
        result = result * 59 + (($serverbackurl == null) ? 43 : $serverbackurl.hashCode());
        Object $callbackurl = getCallbackurl();
        result = result * 59 + (($callbackurl == null) ? 43 : $callbackurl.hashCode());
        Object $signtype = getSigntype();
        result = result * 59 + (($signtype == null) ? 43 : $signtype.hashCode());
        Object $sign = getSign();
        return result * 59 + (($sign == null) ? 43 : $sign.hashCode());
    }

    public String toString() {
        return "FlyPayVO(payUrl=" + getPayUrl() + ", merchantid=" + getMerchantid() + ", orderno=" + getOrderno() + ", orderamount=" + getOrderamount() + ", paytype=" + getPaytype() + ", ordercurrency=" + getOrdercurrency() + ", serverbackurl=" + getServerbackurl() + ", callbackurl=" + getCallbackurl() + ", signtype=" + getSigntype() + ", sign=" + getSign() + ")";
    }


    private String payUrl = PropertiesUtil.getProperty("fly.pay.payurl");

    public String getPayUrl() {
        return this.payUrl;
    }

    private String orderno;
    private String orderamount;
    private String merchantid = PropertiesUtil.getProperty("fly.pay.merchantid");
    private String paytype;
    private String ordercurrency;

    public String getMerchantid() {
        return this.merchantid;
    }

    public String getOrderno() {
        return this.orderno;
    }

    public String getOrderamount() {
        return this.orderamount;
    }

    public String getPaytype() {
        return this.paytype;
    }

    public String getOrdercurrency() {
        return this.ordercurrency;
    }

    private String serverbackurl = PropertiesUtil.getProperty("fly.pay.serverbackurl");

    public String getServerbackurl() {
        return this.serverbackurl;
    }

    private String callbackurl = PropertiesUtil.getProperty("fly.pay.callbackurl");

    public String getCallbackurl() {
        return this.callbackurl;
    }

    private String signtype = "md5";
    private String sign;

    public String getSigntype() {
        return this.signtype;
    }

    public String getSign() {
        return this.sign;
    }
}
