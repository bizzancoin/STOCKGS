package com.xc.dao;

import com.xc.pojo.SiteLoginLog;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SiteLoginLogMapper {
  int deleteByPrimaryKey(Integer paramInteger);
  
  int insert(SiteLoginLog paramSiteLoginLog);
  
  int insertSelective(SiteLoginLog paramSiteLoginLog);
  
  SiteLoginLog selectByPrimaryKey(Integer paramInteger);
  
  int updateByPrimaryKeySelective(SiteLoginLog paramSiteLoginLog);
  
  int updateByPrimaryKey(SiteLoginLog paramSiteLoginLog);
  
  List loginList(@Param("userId") Integer paramInteger);
  
  int deleteByUserId(@Param("userId") Integer paramInteger);
}
