package com.xc.pojo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *  新股申购
 * @author lr 2020-09-11
 */
@Data
public class UserStockSubscribe implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户真实姓名
     */
    private String realName;

    /**
     * 用户手机号
     */
    private String phone;

    /**
     * 管理员id
     */
    private Integer adminId;

    /**
     * 管理员姓名
     */
    private String adminName;

    /**
     * 提交金额
     */
    private BigDecimal submitAmount;

    /**
     * 状态：1、预约成功，2、提交成功，3、已中签，4、未中签
     */
    private Integer status;

    /**
     * 添加时间
     */
    private Date addTime;

    /**
     * 提交时间
     */
    private Date submitTime;

    /**
     * 中签时间
     */
    private Date endTime;

    /**
     * 备注
     */
    private String remarks;

    public UserStockSubscribe() {
    }

}
