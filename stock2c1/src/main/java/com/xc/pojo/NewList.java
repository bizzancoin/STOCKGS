package com.xc.pojo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@ApiModel("新股响应模型")
public class NewList {
    @ApiModelProperty(value = "主键")
    private Integer newlistId;

    @ApiModelProperty(value = "股票名字")
    private String names;

    @ApiModelProperty(value = "股票代码")
    private String code;

    @ApiModelProperty(value = "发行价")
    private String price;

    @ApiModelProperty(value = "状态启用 1/禁用 0  ")
    private Byte zt;

    @ApiModelProperty(value = "市盈率")
    //市盈率
    private String pe;

    @ApiModelProperty(value = "申购日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date issueDate;

    @ApiModelProperty(value = "上市日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date listDate;

    @ApiModelProperty(value = "中签日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date winDate;


    public NewList(Integer newlistId, String names, String code, String price, Byte zt, String pe, Date issueDate, Date listDate, Date winDate) {
        this.newlistId = newlistId;
        this.names = names;
        this.code = code;
        this.price = price;
        this.zt = zt;
        this.pe = pe;
        this.issueDate = issueDate;
        this.listDate = listDate;
        this.winDate = winDate;
    }

    public NewList() {
        super();
    }

    public Integer getNewlistId() {
        return newlistId;
    }

    public void setNewlistId(Integer newlistId) {
        this.newlistId = newlistId;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names == null ? null : names.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
    }

    public Byte getZt() {
        return zt;
    }

    public void setZt(Byte zt) {
        this.zt = zt;
    }

    public String getPe() {
        return pe;
    }

    public void setPe(String pe) {
        this.pe = pe == null ? null : pe.trim();
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getListDate() {
        return listDate;
    }

    public void setListDate(Date listDate) {
        this.listDate = listDate;
    }

    public Date getWinDate() {
        return winDate;
    }

    public void setWinDate(Date winDate) {
        this.winDate = winDate;
    }
}