package com.xc.service;


import com.xc.common.ServerResponse;

public interface ISmsService {
  ServerResponse sendAliyunSMS(String paramString1, String paramString2);
}
