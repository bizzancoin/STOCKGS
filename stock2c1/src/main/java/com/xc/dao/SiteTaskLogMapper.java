package com.xc.dao;


import com.xc.pojo.SiteTaskLog;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SiteTaskLogMapper {
  int deleteByPrimaryKey(Integer paramInteger);
  
  int insert(SiteTaskLog paramSiteTaskLog);
  
  int insertSelective(SiteTaskLog paramSiteTaskLog);
  
  SiteTaskLog selectByPrimaryKey(Integer paramInteger);
  
  int updateByPrimaryKeySelective(SiteTaskLog paramSiteTaskLog);
  
  int updateByPrimaryKey(SiteTaskLog paramSiteTaskLog);
  
  List taskList(@Param("taskType") String paramString);
}
