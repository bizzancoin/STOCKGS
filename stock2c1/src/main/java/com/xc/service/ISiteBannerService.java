package com.xc.service;


import com.xc.common.ServerResponse;
import com.xc.pojo.SiteBanner;

public interface ISiteBannerService {
  ServerResponse add(SiteBanner paramSiteBanner);
  
  ServerResponse listByAdmin(int paramInt1, int paramInt2);
  
  ServerResponse update(SiteBanner paramSiteBanner);
  
  ServerResponse delete(Integer paramInteger);
  
  ServerResponse getBannerByPlat(String paramString);
}
