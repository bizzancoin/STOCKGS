package com.xc.vo.admin;

import java.math.BigDecimal;

public class AdminCountVO {
    private int user_sp_num;
    private int user_moni_num;
    private int agent_num;
    private BigDecimal user_sp_sum_amt;
    private BigDecimal user_sp_sum_enable;
    private BigDecimal charge_sum_amt;
    private BigDecimal charge_today_sum_amt;
    private BigDecimal sp_withdraw_sum_amt_success;
    private BigDecimal sp_withdraw_sum_today_amt_success;

    public void setUser_sp_num(int user_sp_num) {
        this.user_sp_num = user_sp_num;
    }

    private BigDecimal sp_withdraw_sum_amt_apply;
    private int sp_position_num;
    private int sp_pc_position_num;
    private BigDecimal sp_profit_and_lose;
    private BigDecimal sp_all_profit_and_lose;
    private int stock_num;
    private int stock_show_num;
    private int stock_un_lock_num;

    public void setUser_moni_num(int user_moni_num) {
        this.user_moni_num = user_moni_num;
    }

    public void setAgent_num(int agent_num) {
        this.agent_num = agent_num;
    }

    public void setUser_sp_sum_amt(BigDecimal user_sp_sum_amt) {
        this.user_sp_sum_amt = user_sp_sum_amt;
    }

    public void setUser_sp_sum_enable(BigDecimal user_sp_sum_enable) {
        this.user_sp_sum_enable = user_sp_sum_enable;
    }

    public void setCharge_sum_amt(BigDecimal charge_sum_amt) {
        this.charge_sum_amt = charge_sum_amt;
    }

    public void setSp_withdraw_sum_amt_success(BigDecimal sp_withdraw_sum_amt_success) {
        this.sp_withdraw_sum_amt_success = sp_withdraw_sum_amt_success;
    }

    public void setSp_withdraw_sum_amt_apply(BigDecimal sp_withdraw_sum_amt_apply) {
        this.sp_withdraw_sum_amt_apply = sp_withdraw_sum_amt_apply;
    }

    public void setSp_position_num(int sp_position_num) {
        this.sp_position_num = sp_position_num;
    }

    public void setSp_pc_position_num(int sp_pc_position_num) {
        this.sp_pc_position_num = sp_pc_position_num;
    }

    public void setSp_profit_and_lose(BigDecimal sp_profit_and_lose) {
        this.sp_profit_and_lose = sp_profit_and_lose;
    }

    public void setSp_all_profit_and_lose(BigDecimal sp_all_profit_and_lose) {
        this.sp_all_profit_and_lose = sp_all_profit_and_lose;
    }

    public void setStock_num(int stock_num) {
        this.stock_num = stock_num;
    }

    public void setStock_show_num(int stock_show_num) {
        this.stock_show_num = stock_show_num;
    }

    public void setStock_un_lock_num(int stock_un_lock_num) {
        this.stock_un_lock_num = stock_un_lock_num;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof AdminCountVO)) return false;
        AdminCountVO other = (AdminCountVO) o;
        if (!other.canEqual(this)) return false;
        if (getUser_sp_num() != other.getUser_sp_num()) return false;
        if (getUser_moni_num() != other.getUser_moni_num()) return false;
        if (getAgent_num() != other.getAgent_num()) return false;
        Object this$user_sp_sum_amt = getUser_sp_sum_amt(), other$user_sp_sum_amt = other.getUser_sp_sum_amt();
        if ((this$user_sp_sum_amt == null) ? (other$user_sp_sum_amt != null) : !this$user_sp_sum_amt.equals(other$user_sp_sum_amt))
            return false;
        Object this$user_sp_sum_enable = getUser_sp_sum_enable(), other$user_sp_sum_enable = other.getUser_sp_sum_enable();
        if ((this$user_sp_sum_enable == null) ? (other$user_sp_sum_enable != null) : !this$user_sp_sum_enable.equals(other$user_sp_sum_enable))
            return false;
        Object this$charge_sum_amt = getCharge_sum_amt(), other$charge_sum_amt = other.getCharge_sum_amt();
        if ((this$charge_sum_amt == null) ? (other$charge_sum_amt != null) : !this$charge_sum_amt.equals(other$charge_sum_amt))
            return false;
        Object this$sp_withdraw_sum_amt_success = getSp_withdraw_sum_amt_success(), other$sp_withdraw_sum_amt_success = other.getSp_withdraw_sum_amt_success();
        if ((this$sp_withdraw_sum_amt_success == null) ? (other$sp_withdraw_sum_amt_success != null) : !this$sp_withdraw_sum_amt_success.equals(other$sp_withdraw_sum_amt_success))
            return false;
        Object this$sp_withdraw_sum_amt_apply = getSp_withdraw_sum_amt_apply(), other$sp_withdraw_sum_amt_apply = other.getSp_withdraw_sum_amt_apply();
        if ((this$sp_withdraw_sum_amt_apply == null) ? (other$sp_withdraw_sum_amt_apply != null) : !this$sp_withdraw_sum_amt_apply.equals(other$sp_withdraw_sum_amt_apply))
            return false;
        if (getSp_position_num() != other.getSp_position_num()) return false;
        if (getSp_pc_position_num() != other.getSp_pc_position_num()) return false;
        Object this$sp_profit_and_lose = getSp_profit_and_lose(), other$sp_profit_and_lose = other.getSp_profit_and_lose();
        if ((this$sp_profit_and_lose == null) ? (other$sp_profit_and_lose != null) : !this$sp_profit_and_lose.equals(other$sp_profit_and_lose))
            return false;
        Object this$sp_all_profit_and_lose = getSp_all_profit_and_lose(), other$sp_all_profit_and_lose = other.getSp_all_profit_and_lose();
        return ((this$sp_all_profit_and_lose == null) ? (other$sp_all_profit_and_lose != null) : !this$sp_all_profit_and_lose.equals(other$sp_all_profit_and_lose)) ? false : ((getStock_num() != other.getStock_num()) ? false : ((getStock_show_num() != other.getStock_show_num()) ? false : (!(getStock_un_lock_num() != other.getStock_un_lock_num()))));
    }

    protected boolean canEqual(Object other) {
        return other instanceof AdminCountVO;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + getUser_sp_num();
        result = result * 59 + getUser_moni_num();
        result = result * 59 + getAgent_num();
        Object $user_sp_sum_amt = getUser_sp_sum_amt();
        result = result * 59 + (($user_sp_sum_amt == null) ? 43 : $user_sp_sum_amt.hashCode());
        Object $user_sp_sum_enable = getUser_sp_sum_enable();
        result = result * 59 + (($user_sp_sum_enable == null) ? 43 : $user_sp_sum_enable.hashCode());
        Object $charge_sum_amt = getCharge_sum_amt();
        result = result * 59 + (($charge_sum_amt == null) ? 43 : $charge_sum_amt.hashCode());
        Object $sp_withdraw_sum_amt_success = getSp_withdraw_sum_amt_success();
        result = result * 59 + (($sp_withdraw_sum_amt_success == null) ? 43 : $sp_withdraw_sum_amt_success.hashCode());
        Object $sp_withdraw_sum_amt_apply = getSp_withdraw_sum_amt_apply();
        result = result * 59 + (($sp_withdraw_sum_amt_apply == null) ? 43 : $sp_withdraw_sum_amt_apply.hashCode());
        result = result * 59 + getSp_position_num();
        result = result * 59 + getSp_pc_position_num();
        Object $sp_profit_and_lose = getSp_profit_and_lose();
        result = result * 59 + (($sp_profit_and_lose == null) ? 43 : $sp_profit_and_lose.hashCode());
        Object $sp_all_profit_and_lose = getSp_all_profit_and_lose();
        result = result * 59 + (($sp_all_profit_and_lose == null) ? 43 : $sp_all_profit_and_lose.hashCode());
        result = result * 59 + getStock_num();
        result = result * 59 + getStock_show_num();
        return result * 59 + getStock_un_lock_num();
    }

    public String toString() {
        return "AdminCountVO(user_sp_num=" + getUser_sp_num() + ", user_moni_num=" + getUser_moni_num() + ", agent_num=" + getAgent_num() + ", user_sp_sum_amt=" + getUser_sp_sum_amt() + ", user_sp_sum_enable=" + getUser_sp_sum_enable() + ", charge_sum_amt=" + getCharge_sum_amt() + ", sp_withdraw_sum_amt_success=" + getSp_withdraw_sum_amt_success() + ", sp_withdraw_sum_amt_apply=" + getSp_withdraw_sum_amt_apply() + ", sp_position_num=" + getSp_position_num() + ", sp_pc_position_num=" + getSp_pc_position_num() + ", sp_profit_and_lose=" + getSp_profit_and_lose() + ", sp_all_profit_and_lose=" + getSp_all_profit_and_lose() + ", stock_num=" + getStock_num() + ", stock_show_num=" + getStock_show_num() + ", stock_un_lock_num=" + getStock_un_lock_num() + ")";
    }


    public int getUser_sp_num() {
        return this.user_sp_num;
    }

    public int getUser_moni_num() {
        return this.user_moni_num;
    }


    public int getAgent_num() {
        return this.agent_num;
    }


    public BigDecimal getUser_sp_sum_amt() {
        return this.user_sp_sum_amt;
    }

    public BigDecimal getUser_sp_sum_enable() {
        return this.user_sp_sum_enable;
    }


    public BigDecimal getCharge_sum_amt() {
        return this.charge_sum_amt;
    }

    public BigDecimal getSp_withdraw_sum_amt_success() {
        return this.sp_withdraw_sum_amt_success;
    }

    public BigDecimal getSp_withdraw_sum_amt_apply() {
        return this.sp_withdraw_sum_amt_apply;
    }


    public int getSp_position_num() {
        return this.sp_position_num;
    }

    public int getSp_pc_position_num() {
        return this.sp_pc_position_num;
    }


    public BigDecimal getSp_profit_and_lose() {
        return this.sp_profit_and_lose;
    }

    public BigDecimal getSp_all_profit_and_lose() {
        return this.sp_all_profit_and_lose;
    }


    public int getStock_num() {
        return this.stock_num;
    }

    public int getStock_show_num() {
        return this.stock_show_num;
    }

    public int getStock_un_lock_num() {
        return this.stock_un_lock_num;
    }

    public BigDecimal getSp_withdraw_sum_today_amt_success() {
        return sp_withdraw_sum_today_amt_success;
    }

    public void setSp_withdraw_sum_today_amt_success(BigDecimal sp_withdraw_sum_today_amt_success) {
        this.sp_withdraw_sum_today_amt_success = sp_withdraw_sum_today_amt_success;
    }

    public BigDecimal getCharge_today_sum_amt() {
        return charge_today_sum_amt;
    }

    public void setCharge_today_sum_amt(BigDecimal charge_today_sum_amt) {
        this.charge_today_sum_amt = charge_today_sum_amt;
    }
}
