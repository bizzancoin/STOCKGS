package com.xc.pojo;

import java.util.Date;

public class SiteSmsLog {
    private Integer id;
    private String smsPhone;
    private String smsTitle;
    private String smsCnt;
    private String smsTemplate;
    private Integer smsStatus;
    private Date addTime;

    public SiteSmsLog(Integer id, String smsPhone, String smsTitle, String smsCnt, String smsTemplate, Integer smsStatus, Date addTime) {
        this.id = id;
        this.smsPhone = smsPhone;
        this.smsTitle = smsTitle;
        this.smsCnt = smsCnt;
        this.smsTemplate = smsTemplate;
        this.smsStatus = smsStatus;
        this.addTime = addTime;
    }

    public SiteSmsLog() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSmsPhone() {
        return smsPhone;
    }

    public void setSmsPhone(String smsPhone) {
        this.smsPhone = smsPhone;
    }

    public String getSmsTitle() {
        return smsTitle;
    }

    public void setSmsTitle(String smsTitle) {
        this.smsTitle = smsTitle;
    }

    public String getSmsCnt() {
        return smsCnt;
    }

    public void setSmsCnt(String smsCnt) {
        this.smsCnt = smsCnt;
    }

    public String getSmsTemplate() {
        return smsTemplate;
    }

    public void setSmsTemplate(String smsTemplate) {
        this.smsTemplate = smsTemplate;
    }

    public Integer getSmsStatus() {
        return smsStatus;
    }

    public void setSmsStatus(Integer smsStatus) {
        this.smsStatus = smsStatus;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}
