package com.xc.service;


import com.xc.common.ServerResponse;
import com.xc.pojo.UserBank;

import javax.servlet.http.HttpServletRequest;

public interface IUserBankService {
  UserBank findUserBankByUserId(Integer paramInteger);
  
  ServerResponse addBank(UserBank paramUserBank, HttpServletRequest paramHttpServletRequest);
  
  ServerResponse updateBank(UserBank paramUserBank, HttpServletRequest paramHttpServletRequest);
  
  ServerResponse getBankInfo(HttpServletRequest paramHttpServletRequest);
  
  ServerResponse updateBankByAdmin(UserBank paramUserBank);
  
  ServerResponse getBank(Integer paramInteger);
}
