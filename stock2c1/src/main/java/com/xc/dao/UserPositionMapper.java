package com.xc.dao;

import com.xc.pojo.UserPosition;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserPositionMapper {
  int deleteByPrimaryKey(Integer paramInteger);
  
  int insert(UserPosition paramUserPosition);
  
  int insertSelective(UserPosition paramUserPosition);
  
  UserPosition selectByPrimaryKey(Integer paramInteger);
  
  int updateByPrimaryKeySelective(UserPosition paramUserPosition);
  
  int updateByPrimaryKey(UserPosition paramUserPosition);
  
  UserPosition findPositionBySn(String paramString);
  
  List findMyPositionByCodeAndSpell(@Param("uid") Integer paramInteger1, @Param("stockCode") String paramString1, @Param("stockSpell") String paramString2, @Param("state") Integer paramInteger2);

  /**
   * 查询当前持仓信息
   * @param paramInteger
   * @return
   */
  List findPositionByUserIdAndSellIdIsNull(Integer paramInteger);

  /**
   * 查询当前以卖出持仓信息
   * @param paramInteger
   * @return
   */
  List findPositionByUserIdAndSellIdNotNull(Integer paramInteger);
  
  List listByAgent(@Param("positionType") Integer paramInteger1, @Param("state") Integer paramInteger2, @Param("userId") Integer paramInteger3, @Param("searchId") Integer paramInteger4, @Param("positionSn") String paramString, @Param("beginTime") Date paramDate1, @Param("endTime") Date paramDate2);
  
  List findAllStayPosition();
  
  List findDistinctUserIdList();
  
  int CountPositionNum(@Param("state") Integer paramInteger1, @Param("accountType") Integer paramInteger2);
  
  BigDecimal CountPositionProfitAndLose();
  
  BigDecimal CountPositionAllProfitAndLose();
  
  int deleteByUserId(@Param("userId") Integer paramInteger);
  
  List findPositionByStockCodeAndTimes(@Param("minuteTimes") Date paramDate, @Param("stockCode") String paramString, @Param("userId") Integer paramInteger);
  
  Integer findPositionNumByTimes(@Param("beginDate") Date paramDate, @Param("userId") Integer paramInteger);

  List findPositionTopList(@Param("pageSize") Integer pageSize);

  UserPosition findUserPositionByCode(@Param("userId") Integer paramInteger,@Param("stockCode") String stockCode);

}
