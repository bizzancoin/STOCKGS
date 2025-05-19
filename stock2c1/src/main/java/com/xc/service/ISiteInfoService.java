package com.xc.service;


import com.xc.common.ServerResponse;
import com.xc.pojo.SiteInfo;

public interface ISiteInfoService {
  ServerResponse get();
  
  ServerResponse add(SiteInfo paramSiteInfo);
  
  ServerResponse update(SiteInfo paramSiteInfo);
  
  ServerResponse getInfo();
}
