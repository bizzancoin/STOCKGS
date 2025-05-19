package com.xc.pojo;


import java.util.Date;


public class UserBank {

    private Integer id;

    private Integer userId;

    //銀行名稱
    private String bankName;
    //銀行代碼
    private String bankNo;
    //帳戶名字
    private String bankAddress;
    //銀行卡號
    private String bankImg;
    //轉數快ID
    private String bankPhone;

    private Date addTime;


    public UserBank(Integer id, Integer userId, String bankName, String bankNo, String bankAddress, String bankImg, String bankPhone, Date addTime) {

        this.id = id;

        this.userId = userId;

        this.bankName = bankName;

        this.bankNo = bankNo;

        this.bankAddress = bankAddress;

        this.bankImg = bankImg;

        this.bankPhone = bankPhone;

        this.addTime = addTime;

    }

    public UserBank() {
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

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }

    public String getBankImg() {
        return bankImg;
    }

    public void setBankImg(String bankImg) {
        this.bankImg = bankImg;
    }

    public String getBankPhone() {
        return bankPhone;
    }

    public void setBankPhone(String bankPhone) {
        this.bankPhone = bankPhone;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}