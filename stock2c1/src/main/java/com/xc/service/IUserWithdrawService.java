package com.xc.service;


import com.github.pagehelper.PageInfo;
import com.xc.common.ServerResponse;

import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;

public interface IUserWithdrawService {
  ServerResponse outMoney(String paramString,String with_Pwd, HttpServletRequest paramHttpServletRequest) throws Exception;
  
  ServerResponse<PageInfo> findUserWithList(String paramString, HttpServletRequest paramHttpServletRequest, int paramInt1, int paramInt2);
  
  ServerResponse userCancel(Integer paramInteger);
  
  ServerResponse listByAgent(Integer paramInteger1, String paramString, Integer paramInteger2, HttpServletRequest paramHttpServletRequest, int paramInt1, int paramInt2);
  
  ServerResponse<PageInfo> listByAdmin(Integer paramInteger1, Integer paramInteger2, String paramString1, Integer paramInteger3, String paramString2, String paramString3, HttpServletRequest paramHttpServletRequest, int paramInt1, int paramInt2);
  
  ServerResponse updateState(Integer paramInteger1, Integer paramInteger2, String paramString) throws Exception;
  
  int deleteByUserId(Integer paramInteger);
  
  BigDecimal CountSpWithSumAmtByState(Integer paramInteger);

  BigDecimal CountSpWithSumAmTodaytByState(Integer paramInteger);

  ServerResponse deleteWithdraw(Integer withdrawId);
}
