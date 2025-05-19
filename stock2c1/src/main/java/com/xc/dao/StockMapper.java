package com.xc.dao;

import com.xc.pojo.Stock;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface StockMapper {
  int deleteByPrimaryKey(Integer paramInteger);

  int insert(Stock paramStock);

  int insertSelective(Stock paramStock);

  Stock selectByPrimaryKey(Integer paramInteger);

  int updateByPrimaryKeySelective(Stock paramStock);

  int updateByPrimaryKey(Stock paramStock);

  List findStockListByKeyWords(@Param("keyWords") String paramString1, @Param("stockPlate") String paramString2, @Param("stockType") String paramString3, @Param("show") Integer paramInteger);

  List findStockCode(@Param("stockType") String stockType,@Param("stock_num")Integer stock_num,@Param("stock_nums")Integer stock_nums);

  Stock findStockByCode(String paramString);

  Stock findStockByName(String paramString);

  List listByAdmin(@Param("showState") Integer paramInteger1, @Param("lockState") Integer paramInteger2, @Param("code") String paramString1, @Param("name") String paramString2, @Param("stockPlate") String paramString3, @Param("stockType") String paramString4);

  int CountStockNum();

  int CountShowNum(Integer paramInteger);

  int CountUnLockNum(Integer paramInteger);

  List findStockList();

}