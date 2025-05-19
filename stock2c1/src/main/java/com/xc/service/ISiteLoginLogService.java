package com.xc.service;


import com.github.pagehelper.PageInfo;
import com.xc.common.ServerResponse;
import com.xc.pojo.User;

import javax.servlet.http.HttpServletRequest;

public interface ISiteLoginLogService {
  ServerResponse saveLog(User paramUser, HttpServletRequest paramHttpServletRequest);
  
  ServerResponse<PageInfo> loginList(Integer paramInteger, int paramInt1, int paramInt2);
  
  int deleteByUserId(Integer paramInteger);

  ServerResponse del(Integer id, HttpServletRequest request);
}
