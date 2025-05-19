package com.xc.dao;

import com.xc.pojo.UserWithdraw;
import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserWithdrawMapper {
  int deleteByPrimaryKey(Integer paramInteger);
  
  int insert(UserWithdraw paramUserWithdraw);
  
  int insertSelective(UserWithdraw paramUserWithdraw);
  
  UserWithdraw selectByPrimaryKey(Integer paramInteger);
  
  int updateByPrimaryKeySelective(UserWithdraw paramUserWithdraw);
  
  int updateByPrimaryKey(UserWithdraw paramUserWithdraw);
  
  List findUserWithList(@Param("uid") Integer paramInteger, @Param("withStatus") String paramString);
  
  List listByAgent(@Param("searchId") Integer paramInteger1, @Param("realName") String paramString, @Param("state") Integer paramInteger2);
  
  List listByAdmin(@Param("agentId") Integer paramInteger1, @Param("userId") Integer paramInteger2, @Param("realName") String paramString1, @Param("state") Integer paramInteger3, @Param("beginTime") String paramString2, @Param("endTime") String paramString3);
  
  BigDecimal CountSpWithSumAmtByState(Integer paramInteger);

  BigDecimal CountSpWithSumAmTodaytByState(Integer paramInteger);
  
  int deleteByUserId(@Param("userId") Integer paramInteger);
}
