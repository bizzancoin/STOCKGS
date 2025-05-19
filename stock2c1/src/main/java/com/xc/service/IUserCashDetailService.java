package com.xc.service;

import com.github.pagehelper.PageInfo;
import com.xc.common.ServerResponse;

import javax.servlet.http.HttpServletRequest;

public interface IUserCashDetailService {
  ServerResponse<PageInfo> findUserCashDetailList(Integer paramInteger, HttpServletRequest paramHttpServletRequest, int paramInt1, int paramInt2);
  
  ServerResponse<PageInfo> listByAgent(Integer paramInteger1, String paramString, Integer paramInteger2, Integer paramInteger3, HttpServletRequest paramHttpServletRequest, int paramInt1, int paramInt2);
  
  ServerResponse<PageInfo> listByAdmin(Integer paramInteger1, String paramString, Integer paramInteger2, Integer paramInteger3, int paramInt1, int paramInt2);
  
  int deleteByUserId(Integer paramInteger);

  ServerResponse delCash(Integer cashId);
}
