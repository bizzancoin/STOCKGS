package com.xc.service;

import com.github.pagehelper.PageInfo;
import com.xc.common.ServerResponse;
import com.xc.pojo.FundsApply;

import javax.servlet.http.HttpServletRequest;

/**
 * 配资申请
 * @author lr
 * @date 2020/07/24
 */
public interface IFundsApplyService {


    /**
     * 新增
     */
    ServerResponse insert(FundsApply model, HttpServletRequest request) throws Exception;

    /**
     * 更新
     */
    int update(FundsApply model);

    /**
     * 配资申请-保存
     */
    ServerResponse save(FundsApply model);

    /**
     * 配资申请-列表查询
     */
    ServerResponse<PageInfo> getList(int pageNum, int pageSize, String keyword, Integer status, HttpServletRequest request);

    /**
     * 配资申请-查询详情
     */
    ServerResponse getDetail(int id);

    /**
     * 配资申请-用户配资列表
     */
    ServerResponse<PageInfo> getUserApplyList(int pageNum, int pageSize, int userId, HttpServletRequest request);

    /**
     * 配资申请-审核
     */
    ServerResponse audit(FundsApply model, HttpServletRequest request) throws Exception ;

    /**
     * 配资申请-用户操盘中子账户
     */
    ServerResponse<PageInfo> getUserEnabledSubaccount(HttpServletRequest request);

}
