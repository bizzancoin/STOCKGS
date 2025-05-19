package com.xc.service;


import com.xc.common.ServerResponse;
import com.xc.pojo.UserFuturesPosition;
import com.xc.vo.futuresposition.FuturesPositionVO;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

public interface IUserFuturesPositionService {
  ServerResponse buyFutures(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, Integer lever, HttpServletRequest paramHttpServletRequest) throws Exception;

  ServerResponse del(Integer paramInteger);

  ServerResponse sellFutures(String paramString, int paramInt) throws Exception;
  
  ServerResponse listByAdmin(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, Integer paramInteger4, String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2);
  
  ServerResponse lock(Integer paramInteger1, Integer paramInteger2, String paramString);
  
  ServerResponse findMyFuturesPositionByNameAndCode(String paramString1, String paramString2, Integer paramInteger, HttpServletRequest paramHttpServletRequest, int paramInt1, int paramInt2);
  
  ServerResponse listByAgent(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, Integer paramInteger4, String paramString1, String paramString2, String paramString3, HttpServletRequest paramHttpServletRequest, int paramInt1, int paramInt2);
  
  List<UserFuturesPosition> findPositionByFuturesCodeAndTimes(int paramInt, String paramString, Integer paramInteger);
  
  Integer findPositionNumByTimes(int paramInt, Integer paramInteger);
  
  List<Integer> findDistinctUserIdList();
  
  List<UserFuturesPosition> findFuturesPositionByUserIdAndSellPriceIsNull(Integer paramInteger);
  
  FuturesPositionVO findUserFuturesPositionAllProfitAndLose(Integer paramInteger);
  
  ServerResponse getFuturesIncome(Integer paramInteger1, Integer paramInteger2, String paramString1, String paramString2);

  ServerResponse findUserFuturesPositionByCode(HttpServletRequest paramHttpServletRequest, String futuresGid);


}

