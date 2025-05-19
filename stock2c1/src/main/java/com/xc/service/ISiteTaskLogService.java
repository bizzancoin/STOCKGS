package com.xc.service;


import com.github.pagehelper.PageInfo;
import com.xc.common.ServerResponse;

import javax.servlet.http.HttpServletRequest;

public interface ISiteTaskLogService {
  ServerResponse<PageInfo> taskList(String paramString, int paramInt1, int paramInt2);

  ServerResponse del(Integer id, HttpServletRequest request);
}
