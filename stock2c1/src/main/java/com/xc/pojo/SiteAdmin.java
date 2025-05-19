package com.xc.pojo;
import java.util.Date;

public class SiteAdmin {
    private Integer id;
    private String adminName;
    private String adminPwd;
    private String adminPhone;
    private Integer isLock;
    private Date addTime;

    public SiteAdmin(Integer id, String adminName, String adminPwd, String adminPhone, Integer isLock, Date addTime) {
        this.id = id;
        this.adminName = adminName;
        this.adminPwd = adminPwd;
        this.adminPhone = adminPhone;
        this.isLock = isLock;
        this.addTime = addTime;
    }

    public SiteAdmin() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPwd() {
        return adminPwd;
    }

    public void setAdminPwd(String adminPwd) {
        this.adminPwd = adminPwd;
    }

    public String getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone;
    }

    public Integer getIsLock() {
        return isLock;
    }

    public void setIsLock(Integer isLock) {
        this.isLock = isLock;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}

