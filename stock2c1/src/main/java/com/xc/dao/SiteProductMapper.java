package com.xc.dao;

import com.xc.pojo.SiteProduct;
import java.util.List;

public interface SiteProductMapper {
  int deleteByPrimaryKey(Integer paramInteger);
  
  int insert(SiteProduct paramSiteProduct);
  
  int insertSelective(SiteProduct paramSiteProduct);
  
  SiteProduct selectByPrimaryKey(Integer paramInteger);
  
  int updateByPrimaryKeySelective(SiteProduct paramSiteProduct);
  
  int updateByPrimaryKey(SiteProduct paramSiteProduct);
  
  List findAllSiteSetting();
}
