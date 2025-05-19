package com.xc.dao;

import com.xc.pojo.StockOption;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StockOptionMapper {
  int deleteByPrimaryKey(Integer paramInteger);
  
  int insert(StockOption paramStockOption);
  
  int insertSelective(StockOption paramStockOption);
  
  StockOption selectByPrimaryKey(Integer paramInteger);
  
  int updateByPrimaryKeySelective(StockOption paramStockOption);
  
  int updateByPrimaryKey(StockOption paramStockOption);
  
  StockOption findMyOptionIsExistByCode(@Param("uid") Integer paramInteger, @Param("stockCode") String paramString);
  
  List findMyOptionByKeywords(@Param("uid") Integer paramInteger, @Param("keyWords") String paramString);
  
  StockOption isOption(@Param("uid") Integer paramInteger, @Param("code") String paramString);
}
