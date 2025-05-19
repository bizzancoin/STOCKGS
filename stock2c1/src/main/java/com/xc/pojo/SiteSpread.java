package com.xc.pojo;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *  site_spread
 * @author lr 2020-07-01
 */
public class SiteSpread implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 类型名称
     */
    private String typeName;

    /**
     * 单位：%，万等
     */
    private String unit;

    /**
     * 开始区间
     */
    private Integer startInterval;

    /**
     * 结束区间
     */
    private Integer endInterval;

    /**
     * 点差费率
     */
    private BigDecimal spreadRate;

    /**
     * 添加时间
     */
    private Date addTime;

    /**
     * 修改时间
     */
    private Date updateTime;


    public SiteSpread() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getStartInterval() {
        return startInterval;
    }

    public void setStartInterval(Integer startInterval) {
        this.startInterval = startInterval;
    }

    public Integer getEndInterval() {
        return endInterval;
    }

    public void setEndInterval(Integer endInterval) {
        this.endInterval = endInterval;
    }

    public BigDecimal getSpreadRate() {
        return spreadRate;
    }

    public void setSpreadRate(BigDecimal spreadRate) {
        this.spreadRate = spreadRate;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
