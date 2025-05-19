package com.xc.pojo;

public class Lists {
    private Integer listsId;

    private String agent;

    private String zname;

    private String phone;

    private String xgname;

    private String codes;

    private String bzj;

    private Short zts;

    private String mrsj;

    private String nums;

    private String winums;

    public Lists(Integer listsId, String agent, String zname, String phone, String xgname, String codes, String bzj, Short zts, String mrsj, String nums, String winums) {
        this.listsId = listsId;
        this.agent = agent;
        this.zname = zname;
        this.phone = phone;
        this.xgname = xgname;
        this.codes = codes;
        this.bzj = bzj;
        this.zts = zts;
        this.mrsj = mrsj;
        this.nums = nums;
        this.winums = winums;
    }

    public Lists() {
        super();
    }

    public Integer getListsId() {
        return listsId;
    }

    public void setListsId(Integer listsId) {
        this.listsId = listsId;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent == null ? null : agent.trim();
    }

    public String getZname() {
        return zname;
    }

    public void setZname(String zname) {
        this.zname = zname == null ? null : zname.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getXgname() {
        return xgname;
    }

    public void setXgname(String xgname) {
        this.xgname = xgname == null ? null : xgname.trim();
    }

    public String getCodes() {
        return codes;
    }

    public void setCodes(String codes) {
        this.codes = codes == null ? null : codes.trim();
    }

    public String getBzj() {
        return bzj;
    }

    public void setBzj(String bzj) {
        this.bzj = bzj == null ? null : bzj.trim();
    }

    public Short getZts() {
        return zts;
    }

    public void setZts(Short zts) {
        this.zts = zts;
    }

    public String getMrsj() {
        return mrsj;
    }

    public void setMrsj(String mrsj) {
        this.mrsj = mrsj == null ? null : mrsj.trim();
    }

    public String getNums() {
        return nums;
    }

    public void setNums(String nums) {
        this.nums = nums == null ? null : nums.trim();
    }

    public String getWinums() {
        return winums;
    }

    public void setWinums(String winums) {
        this.winums = winums == null ? null : winums.trim();
    }
}