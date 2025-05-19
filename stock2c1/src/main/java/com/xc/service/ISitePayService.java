package com.xc.service;


import com.xc.common.ServerResponse;
import com.xc.pojo.SitePay;

public interface ISitePayService {
  ServerResponse add(SitePay paramSitePay);
  
  ServerResponse listByAdmin(String paramString, int paramInt1, int paramInt2);
  
  ServerResponse update(SitePay paramSitePay);
  
  ServerResponse del(Integer paramInteger);
  
  ServerResponse getPayInfo();
  
  ServerResponse getPayInfoById(Integer paramInteger);
}
