package com.xc.service;

import com.github.pagehelper.PageInfo;
import com.xc.common.ServerResponse;
import com.xc.pojo.FundsTradingAccount;

import javax.servlet.http.HttpServletRequest;

/**
 * 配资交易账户
 * @author lr
 * @date 2020/07/24
 */
public interface IFundsTradingAccountService {

    /**
     * 新增
     */
    int insert(FundsTradingAccount model);

    /**
     * 更新
     */
    int update(FundsTradingAccount model);

    /**
     * 配资交易账户-保存
     */
    ServerResponse save(FundsTradingAccount model);

    /**
     * 配资交易账户-列表查询
     */
    ServerResponse<PageInfo> getList(int pageNum, int pageSize, String keyword, Integer status, HttpServletRequest request);

    /**
     * 配资交易账户-查询详情
     */
    ServerResponse getDetail(int id);

    /**
     * 查询最新交易账户编号
     */
    ServerResponse getMaxNumber();

}
