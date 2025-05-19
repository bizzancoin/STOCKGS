package com.xc.dao;

import com.xc.pojo.StockFutures;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StockFuturesMapper {
  int deleteByPrimaryKey(Integer paramInteger);
  
  int insert(StockFutures paramStockFutures);
  
  int insertSelective(StockFutures paramStockFutures);
  
  StockFutures selectByPrimaryKey(Integer paramInteger);
  
  int updateByPrimaryKeySelective(StockFutures paramStockFutures);
  
  int updateByPrimaryKey(StockFutures paramStockFutures);
  
  List listByAdmin(@Param("fuName") String paramString1, @Param("fuCode") String paramString2);
  
  StockFutures selectFuturesByName(String paramString);
  
  StockFutures selectFuturesByCode(String paramString);
  
  List queryHome();
  
  List queryList();
}
