package com.xc.service;

import com.xc.common.ServerResponse;
import com.xc.pojo.SiteIndexSetting;

public interface ISiteIndexSettingService {
  SiteIndexSetting getSiteIndexSetting();
  
  ServerResponse update(SiteIndexSetting paramSiteIndexSetting);
}
