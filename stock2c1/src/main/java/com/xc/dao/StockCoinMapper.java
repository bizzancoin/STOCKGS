package com.xc.dao;


import com.xc.pojo.StockCoin;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StockCoinMapper {
  int deleteByPrimaryKey(Integer paramInteger);
  
  int insert(StockCoin paramStockCoin);
  
  int insertSelective(StockCoin paramStockCoin);
  
  StockCoin selectByPrimaryKey(Integer paramInteger);
  
  int updateByPrimaryKeySelective(StockCoin paramStockCoin);
  
  int updateByPrimaryKey(StockCoin paramStockCoin);
  
  List listByAdmin(@Param("coinName") String paramString1, @Param("coinCode") String paramString2);
  
  StockCoin selectCoinByName(String paramString);
  
  StockCoin selectCoinByCode(String paramString);
  
  StockCoin selectCoinByGid(String paramString);
  
  List getSelectCoin();
}
