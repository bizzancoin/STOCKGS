package com.xc.dao;

import com.xc.pojo.UserFuturesPosition;
import java.util.Date;
import java.util.List;

import com.xc.pojo.UserIndexPosition;
import org.apache.ibatis.annotations.Param;

public interface UserFuturesPositionMapper {
  int deleteByPrimaryKey(Integer paramInteger);
  
  int insert(UserFuturesPosition paramUserFuturesPosition);
  
  int insertSelective(UserFuturesPosition paramUserFuturesPosition);
  
  UserFuturesPosition selectByPrimaryKey(Integer paramInteger);
  
  int updateByPrimaryKeySelective(UserFuturesPosition paramUserFuturesPosition);
  
  int updateByPrimaryKey(UserFuturesPosition paramUserFuturesPosition);
  
  List findMyFuturesPositionByNameAndCode(@Param("userId") Integer paramInteger1, @Param("fuName") String paramString1, @Param("fuCode") String paramString2, @Param("state") Integer paramInteger2);
  
  List listByAdmin(@Param("positionType") Integer paramInteger1, @Param("state") Integer paramInteger2, @Param("userId") Integer paramInteger3, @Param("searchId") Integer paramInteger4, @Param("positionSn") String paramString, @Param("beginTime") Date paramDate1, @Param("endTime") Date paramDate2);
  
  List listByAgent(@Param("positionType") Integer paramInteger1, @Param("state") Integer paramInteger2, @Param("userId") Integer paramInteger3, @Param("searchId") Integer paramInteger4, @Param("positionSn") String paramString, @Param("beginTime") Date paramDate1, @Param("endTime") Date paramDate2);
  
  UserFuturesPosition selectPositionBySn(String paramString);
  
  List findPositionByFuturesCodeAndTimes(@Param("minuteTimes") Date paramDate, @Param("futuresCode") String paramString, @Param("userId") Integer paramInteger);
  
  Integer findPositionNumByTimes(@Param("beginDate") Date paramDate, @Param("userId") Integer paramInteger);
  
  List findDistinctUserIdList();
  
  List findFuturesPositionByUserIdAndSellPriceIsNull(Integer paramInteger);

  UserFuturesPosition findUserFuturesPositionByCode(@Param("userId") Integer paramInteger, @Param("futuresGid") String futuresGid);

}
