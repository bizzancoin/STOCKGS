package com.xc.dao;

import com.xc.pojo.SiteSmsLog;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SiteSmsLogMapper {
  int deleteByPrimaryKey(Integer paramInteger);
  
  int insert(SiteSmsLog paramSiteSmsLog);
  
  int insertSelective(SiteSmsLog paramSiteSmsLog);
  
  SiteSmsLog selectByPrimaryKey(Integer paramInteger);
  
  int updateByPrimaryKeySelective(SiteSmsLog paramSiteSmsLog);
  
  int updateByPrimaryKey(SiteSmsLog paramSiteSmsLog);
  
  List smsList(@Param("phoneNum") String paramString);
}
