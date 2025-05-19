package com.xc.service;


import com.xc.common.ServerResponse;
import com.xc.pojo.SiteSetting;

public interface ISiteSettingService {
  SiteSetting getSiteSetting();
  
  ServerResponse update(SiteSetting paramSiteSetting);
}