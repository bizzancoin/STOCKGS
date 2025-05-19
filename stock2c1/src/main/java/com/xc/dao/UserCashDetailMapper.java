package com.xc.dao;

import com.xc.pojo.UserCashDetail;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserCashDetailMapper {
  int deleteByPrimaryKey(Integer paramInteger);
  
  int insert(UserCashDetail paramUserCashDetail);
  
  int insertSelective(UserCashDetail paramUserCashDetail);
  
  UserCashDetail selectByPrimaryKey(Integer paramInteger);
  
  int updateByPrimaryKeySelective(UserCashDetail paramUserCashDetail);
  
  int updateByPrimaryKey(UserCashDetail paramUserCashDetail);
  
  List findUserCashDetailList(@Param("uid") Integer paramInteger1, @Param("positionId") Integer paramInteger2);
  
  List listByAgent(@Param("userId") Integer paramInteger1, @Param("userName") String paramString, @Param("searchId") Integer paramInteger2, @Param("positionId") Integer paramInteger3);
  
  List listByAdmin(@Param("userId") Integer paramInteger1, @Param("userName") String paramString, @Param("agentId") Integer paramInteger2, @Param("positionId") Integer paramInteger3);
  
  int deleteByUserId(@Param("userId") Integer paramInteger);
}