package com.xc.pojo;


import java.util.Date;


public class SiteTaskLog {
    private Integer id;
    private String taskType;
    private String taskCnt;
    private String taskTarget;
    private Integer isSuccess;
    private String errorMsg;
    private Date addTime;

    public SiteTaskLog(Integer id, String taskType, String taskCnt, String taskTarget, Integer isSuccess, String errorMsg, Date addTime) {
        this.id = id;
        this.taskType = taskType;
        this.taskCnt = taskCnt;
        this.taskTarget = taskTarget;
        this.isSuccess = isSuccess;
        this.errorMsg = errorMsg;
        this.addTime = addTime;
    }

    public SiteTaskLog() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getTaskCnt() {
        return taskCnt;
    }

    public void setTaskCnt(String taskCnt) {
        this.taskCnt = taskCnt;
    }

    public String getTaskTarget() {
        return taskTarget;
    }

    public void setTaskTarget(String taskTarget) {
        this.taskTarget = taskTarget;
    }

    public Integer getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Integer isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}