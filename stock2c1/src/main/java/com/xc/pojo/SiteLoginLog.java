package com.xc.pojo;

import java.util.Date;


public class SiteLoginLog {
    private Integer id;
    private Integer userId;
    private String userName;
    private String loginIp;
    private String loginAddress;
    private Date addTime;

    public SiteLoginLog(Integer id, Integer userId, String userName, String loginIp, String loginAddress, Date addTime) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.loginIp = loginIp;
        this.loginAddress = loginAddress;
        this.addTime = addTime;
    }

    public SiteLoginLog() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getLoginAddress() {
        return loginAddress;
    }

    public void setLoginAddress(String loginAddress) {
        this.loginAddress = loginAddress;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}