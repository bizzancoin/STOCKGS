package com.xc.service;

import com.github.pagehelper.PageInfo;
import com.xc.common.ServerResponse;
import com.xc.pojo.FundsAppend;

import javax.servlet.http.HttpServletRequest;

/**
 * 配资追加申请
 * @author lr
 * @date 2020/07/24
 */
public interface IFundsAppendService {

    /**
     * 新增
     */
    int insert(FundsAppend model);

    /**
     * 更新
     */
    int update(FundsAppend model);

    /**
     * 配资追加申请-保存
     */
    ServerResponse save(FundsAppend model, HttpServletRequest request);

    /**
     * 配资追加申请-列表查询
     */
    ServerResponse<PageInfo> getList(int pageNum, int pageSize, String keyword, Integer status, Integer userId, Integer appendType, HttpServletRequest request);

    /**
     * 配资追加申请-查询详情
     */
    ServerResponse getDetail(int id);



}
