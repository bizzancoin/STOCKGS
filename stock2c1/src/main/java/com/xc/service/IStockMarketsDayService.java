package com.xc.service;


import java.math.BigDecimal;

public interface IStockMarketsDayService {
  void saveStockMarketDay();
  void saveHoliday();

  BigDecimal selectRateByDaysAndStockCode(Integer paramInteger, int paramInt);
}

