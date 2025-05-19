package com.xc.dao;

import com.xc.pojo.StockIndex;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StockIndexMapper {
  int deleteByPrimaryKey(Integer paramInteger);
  
  int insert(StockIndex paramStockIndex);
  
  int insertSelective(StockIndex paramStockIndex);
  
  StockIndex selectByPrimaryKey(Integer paramInteger);
  
  int updateByPrimaryKeySelective(StockIndex paramStockIndex);
  
  int updateByPrimaryKey(StockIndex paramStockIndex);
  
  StockIndex selectIndexByName(@Param("indexName") String paramString);
  
  StockIndex selectIndexByCode(@Param("indexCode") String paramString);
  
  List listByAdmin(@Param("homeShow") Integer paramInteger1, @Param("listShow") Integer paramInteger2, @Param("transState") Integer paramInteger3, @Param("indexCode") String paramString1, @Param("indexName") String paramString2);
  
  List queryHomeIndex();
  
  List queryListIndex();
}
