package com.xc.pojo;

import java.io.Serializable;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *  分仓交易表
 * @author lr 2020-07-27
 */
@Data
public class UserFundsPosition implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 账户类型：0、实盘，1、模拟盘
     */
    private Integer positionType;

    /**
     * 订单编号
     */
    private String positionSn;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户真实姓名
     */
    private String nickName;

    /**
     * 子账户编号，默认从80000000开始
     */
    private Integer subaccountNumber;

    /**
     * 代理id
     */
    private Integer agentId;

    /**
     * 股票id
     */
    private Integer stockId;

    /**
     * 股票名称
     */
    private String stockName;

    /**
     * 股票代码
     */
    private String stockCode;

    /**
     * 股票gid
     */
    private String stockGid;

    /**
     * 股票简拼
     */
    private String stockSpell;

    /**
     * 入仓单号
     */
    private String buyOrderId;

    /**
     * 入仓时间
     */
    private Date buyOrderTime;

    /**
     * 入仓单价
     */
    private BigDecimal buyOrderPrice;

    /**
     * 出仓单号
     */
    private String sellOrderId;

    /**
     * 出仓时间
     */
    private Date sellOrderTime;

    /**
     * 出仓单价
     */
    private BigDecimal sellOrderPrice;

    /**
     * 下单方向：买涨、买跌
     */
    private String orderDirection;

    /**
     * 订单数量
     */
    private Integer orderNum;

    /**
     * 订单杠杆
     */
    private Integer orderLever;

    /**
     * 订单总金额
     */
    private BigDecimal orderTotalPrice;

    /**
     * 手续费
     */
    private BigDecimal orderFee;

    /**
     * 印花税
     */
    private BigDecimal orderSpread;

    /**
     * 留仓费
     */
    private BigDecimal orderStayFee;

    /**
     * 留仓天数
     */
    private Integer orderStayDays;

    /**
     * 浮动盈亏
     */
    private BigDecimal profitAndLose;

    /**
     * 总盈亏
     */
    private BigDecimal allProfitAndLose;

    /**
     * 是否锁仓：1、是，0、否
     */
    private Integer isLock;

    /**
     * 锁仓原因
     */
    private String lockMsg;

    /**
     * 股票板块：科创
     */
    private String stockPlate;

    /**
     * 点差费
     */
    private BigDecimal spreadRatePrice;

    public UserFundsPosition() {
    }

}

