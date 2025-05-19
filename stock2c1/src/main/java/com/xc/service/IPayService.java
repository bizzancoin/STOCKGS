package com.xc.service;


import com.xc.common.ServerResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

public interface IPayService {
  ServerResponse juhe1(String paramString1, String paramString2, HttpServletRequest paramHttpServletRequest);

  ServerResponse juhenewpay(String paramString1, String paramString2, HttpServletRequest paramHttpServletRequest) throws Exception;

  ServerResponse juheh5pay(String paramString1, String paramString2, HttpServletRequest paramHttpServletRequest) throws Exception;

  ServerResponse juhe1Notify(HttpServletRequest paramHttpServletRequest);

  ServerResponse juhenewpayNotify(HttpServletRequest paramHttpServletRequest) throws UnsupportedEncodingException;

  ServerResponse flyPay(String paramString1, String paramString2, String paramString3, HttpServletRequest paramHttpServletRequest);
  
  ServerResponse flyNotify(HttpServletRequest paramHttpServletRequest);
}
