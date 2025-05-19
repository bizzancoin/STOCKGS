package com.xc.service;


import com.github.pagehelper.PageInfo;
import com.xc.common.ServerResponse;
import com.xc.pojo.UserRecharge;

import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;

public interface IUserRechargeService {
  ServerResponse checkInMoney(int paramInt, Integer paramInteger);

    ServerResponse inMoneyByAdmin(String amt, String payType, Integer uid);

    ServerResponse inMoney(String paramString1, String paramString2, HttpServletRequest paramHttpServletRequest);
  
  ServerResponse findUserRechargeByOrderSn(String paramString);
  
  ServerResponse chargeSuccess(UserRecharge paramUserRecharge) throws Exception;
  
  ServerResponse chargeFail(UserRecharge paramUserRecharge) throws Exception;
  
  ServerResponse chargeCancel(UserRecharge paramUserRecharge) throws Exception;
  
  ServerResponse<PageInfo> findUserChargeList(String paramString1, String paramString2, HttpServletRequest paramHttpServletRequest, int paramInt1, int paramInt2);
  
  ServerResponse<PageInfo> listByAgent(Integer paramInteger1, String paramString1, String paramString2, Integer paramInteger2, HttpServletRequest paramHttpServletRequest, int paramInt1, int paramInt2);
  
  ServerResponse listByAdmin(Integer paramInteger1, Integer paramInteger2, String paramString1, Integer paramInteger3, String paramString2, String paramString3, HttpServletRequest paramHttpServletRequest, int paramInt1, int paramInt2);
  
  ServerResponse updateState(Integer paramInteger1, Integer paramInteger2) throws Exception;
  
  ServerResponse createOrder(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, String paramString);
  
  ServerResponse del(Integer paramInteger);
  
  int deleteByUserId(Integer paramInteger);
  
  BigDecimal CountChargeSumAmt(Integer paramInteger);

  ServerResponse createOrder(Integer userId, Integer state, BigDecimal amt, String payChannel);

  BigDecimal CountTotalRechargeAmountByTime(Integer paramInteger);
}
