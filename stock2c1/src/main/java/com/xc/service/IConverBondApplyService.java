package com.xc.service;

import com.xc.common.ServerResponse;
import com.xc.pojo.ConvertBondApply;

import javax.servlet.http.HttpServletRequest;

public interface IConverBondApplyService {


    public void update(ConvertBondApply convertBondApply);
    public ServerResponse getMyConvertBond(Integer bondId,Integer userId);
    public ServerResponse getMyConvertBondList(HttpServletRequest request,Integer page,Integer size);

    public ServerResponse applyConvertBond(Integer userId, Integer count, Integer bondId, HttpServletRequest request) throws Exception;
}
