package com.xc.pojo;

import java.util.Date;

public class DataDictionary {
    private Long id;

    private String bond;

    private String comment;

    private Date creationTime;

    private Date updateTime;

    private String value;

    public DataDictionary(Long id, String bond, String comment, Date creationTime, Date updateTime, String value) {
        this.id = id;
        this.bond = bond;
        this.comment = comment;
        this.creationTime = creationTime;
        this.updateTime = updateTime;
        this.value = value;
    }

    public DataDictionary() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBond() {
        return bond;
    }

    public void setBond(String bond) {
        this.bond = bond == null ? null : bond.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }
}