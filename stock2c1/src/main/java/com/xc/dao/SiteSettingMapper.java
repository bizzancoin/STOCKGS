package com.xc.dao;

import com.xc.pojo.SiteSetting;
import java.util.List;

public interface SiteSettingMapper {
  int deleteByPrimaryKey(Integer paramInteger);
  
  int insert(SiteSetting paramSiteSetting);
  
  int insertSelective(SiteSetting paramSiteSetting);
  
  SiteSetting selectByPrimaryKey(Integer paramInteger);
  
  int updateByPrimaryKeySelective(SiteSetting paramSiteSetting);
  
  int updateByPrimaryKey(SiteSetting paramSiteSetting);
  
  List findAllSiteSetting();
}
