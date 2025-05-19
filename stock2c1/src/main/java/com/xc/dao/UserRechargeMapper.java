package com.xc.dao;

import com.xc.pojo.UserRecharge;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRechargeMapper {
  int deleteByPrimaryKey(Integer paramInteger);
  
  int insert(UserRecharge paramUserRecharge);
  
  int insertSelective(UserRecharge paramUserRecharge);
  
  UserRecharge selectByPrimaryKey(Integer paramInteger);
  
  int updateByPrimaryKeySelective(UserRecharge paramUserRecharge);
  
  int updateByPrimaryKey(UserRecharge paramUserRecharge);
  
  int checkInMoney(@Param("status") int paramInt, @Param("userId") Integer paramInteger);
  
  UserRecharge findUserRechargeByOrderSn(String paramString);
  
  List findUserChargeList(@Param("uid") Integer paramInteger, @Param("payChannel") String paramString1, @Param("orderStatus") String paramString2);
  
  List listByAdmin(@Param("agentId") Integer paramInteger1, @Param("userId") Integer paramInteger2, @Param("realName") String paramString, @Param("state") Integer paramInteger3, @Param("begin_time") Date paramDate1, @Param("end_time") Date paramDate2);
  
  int deleteByUserId(@Param("userId") Integer paramInteger);
  
  List listByAgent(@Param("searchId") Integer paramInteger1, @Param("realName") String paramString1, @Param("payChannel") String paramString2, @Param("state") Integer paramInteger2);
  
  BigDecimal CountChargeSumAmt(Integer paramInteger);

  BigDecimal CountTotalRechargeAmountByTime(Integer paramInteger);
}
