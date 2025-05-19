package com.xc.service;


import com.github.pagehelper.PageInfo;
import com.xc.common.ServerResponse;

public interface ISiteAmtTransLogService {
  ServerResponse<PageInfo> transList(Integer paramInteger, String paramString, int paramInt1, int paramInt2);
}
