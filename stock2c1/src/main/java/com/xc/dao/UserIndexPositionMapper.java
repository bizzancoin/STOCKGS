package com.xc.dao;


import com.xc.pojo.UserIndexPosition;
import java.util.Date;
import java.util.List;

import com.xc.pojo.UserPosition;
import org.apache.ibatis.annotations.Param;

public interface UserIndexPositionMapper {
  int deleteByPrimaryKey(Integer paramInteger);
  
  int insert(UserIndexPosition paramUserIndexPosition);
  
  int insertSelective(UserIndexPosition paramUserIndexPosition);
  
  UserIndexPosition selectByPrimaryKey(Integer paramInteger);
  
  int updateByPrimaryKeySelective(UserIndexPosition paramUserIndexPosition);
  
  int updateByPrimaryKey(UserIndexPosition paramUserIndexPosition);
  
  UserIndexPosition selectIndexPositionBySn(String paramString);
  
  List findMyIndexPositionByNameAndCode(@Param("userId") Integer paramInteger1, @Param("indexName") String paramString1, @Param("indexCode") String paramString2, @Param("state") Integer paramInteger2);
  
  List listByAdmin(@Param("positionType") Integer paramInteger1, @Param("state") Integer paramInteger2, @Param("userId") Integer paramInteger3, @Param("searchId") Integer paramInteger4, @Param("positionSn") String paramString, @Param("beginTime") Date paramDate1, @Param("endTime") Date paramDate2);
  
  List listByAgent(@Param("positionType") Integer paramInteger1, @Param("state") Integer paramInteger2, @Param("userId") Integer paramInteger3, @Param("searchId") Integer paramInteger4, @Param("positionSn") String paramString, @Param("beginTime") Date paramDate1, @Param("endTime") Date paramDate2);
  
  List findPositionByUserIdAndSellPriceIsNull(Integer paramInteger);
  
  List findDistinctUserIdList();

  UserIndexPosition findUserIndexPositionByCode(@Param("userId") Integer paramInteger, @Param("indexGid") String indexGid);
}