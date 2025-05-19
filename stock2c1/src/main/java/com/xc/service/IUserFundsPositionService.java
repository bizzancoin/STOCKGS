package com.xc.service;


import com.github.pagehelper.PageInfo;
import com.xc.common.ServerResponse;
import com.xc.pojo.UserFundsPosition;

import javax.servlet.http.HttpServletRequest;

/**
 * 分仓交易
 * @author lr
 * @date 2020/07/24
 */
public interface IUserFundsPositionService {

    /**
     * 新增
     */
    ServerResponse insert(UserFundsPosition model, HttpServletRequest request);

    /**
     * 更新
     */
    int update(UserFundsPosition model);

    /**
     * 分仓交易-保存
     */
    ServerResponse save(UserFundsPosition model);

    /**
     * 分仓交易-列表查询
     */
    ServerResponse<PageInfo> getList(int pageNum, int pageSize, String keyword, HttpServletRequest request);

    /**
     * 分仓交易-查询详情
     */
    ServerResponse getDetail(int id);

    /**
     * 分仓交易-入仓
     */
    ServerResponse buyFunds(Integer stockId, Integer buyNum, Integer buyType, Integer lever, Integer subaccountNumber, HttpServletRequest request) throws Exception;

    //分仓交易-用户平仓操作
    ServerResponse sellFunds(String paramString, int paramInt) throws Exception;

    /*
    * 分仓交易-查询所有平仓/持仓信息
    * */
    ServerResponse<PageInfo> findMyPositionByCodeAndSpell(String paramString1, String paramString2, Integer paramInteger, HttpServletRequest paramHttpServletRequest, int paramInt1, int paramInt2);

    ServerResponse findUserFundsPositionByCode(HttpServletRequest request, String fundsCode);

}


