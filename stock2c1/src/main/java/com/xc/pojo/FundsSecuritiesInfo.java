package com.xc.pojo;

import java.io.Serializable;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *  配资证券信息
 * @author lr 2020-07-24
 */
@Data
public class FundsSecuritiesInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 证券机构id
     */
    private Integer dealerInstitutionsId;

    /**
     * 证券机构名称
     */
    private String dealerInstitutionsName;

    /**
     * 证券营业部
     */
    private String salesDepartment;

    /**
     * 证券账户
     */
    private String accountName;

    /**
     * 交易通账户
     */
    private String transactAccount;

    /**
     * 交易通密码
     */
    private String transactPassword;

    /**
     * 通讯密码
     */
    private String communicationPassword;

    /**
     * 佣金比例
     */
    private BigDecimal commissionRatio;

    /**
     * 最低佣金，单位元
     */
    private BigDecimal minimumCommissions;

    /**
     * 状态：1、启用，0、停用
     */
    private Integer status;

    /**
     * 添加时间
     */
    private Date addTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    public FundsSecuritiesInfo() {
    }

}
