package com.xc.service;


import com.xc.common.ServerResponse;
import com.xc.pojo.StockIndex;
import com.xc.vo.stock.MarketVO;

import javax.servlet.http.HttpServletRequest;

public interface IStockIndexService {
  ServerResponse listByAdmin(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, String paramString1, String paramString2, int paramInt1, int paramInt2, HttpServletRequest paramHttpServletRequest);
  
  ServerResponse updateIndex(StockIndex paramStockIndex);
  
  ServerResponse addIndex(StockIndex paramStockIndex);
  
  ServerResponse queryHomeIndex();
  
  ServerResponse queryListIndex(HttpServletRequest request);
  
  ServerResponse queryTransIndex(Integer paramInteger);
  
  MarketVO querySingleIndex(String paramString);
  
  StockIndex selectIndexById(Integer paramInteger);
}
