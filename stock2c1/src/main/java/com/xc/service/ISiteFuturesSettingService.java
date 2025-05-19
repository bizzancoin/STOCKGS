package com.xc.service;


import com.xc.common.ServerResponse;
import com.xc.pojo.SiteFuturesSetting;

public interface ISiteFuturesSettingService {
  SiteFuturesSetting getSetting();
  
  ServerResponse update(SiteFuturesSetting paramSiteFuturesSetting);
}
