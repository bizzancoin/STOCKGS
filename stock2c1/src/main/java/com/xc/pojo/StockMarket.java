package com.xc.pojo;

public class StockMarket {
  private Integer id;
  
  private Integer stockId;
  
  private String stockName;
  
  private String stockCode;

  public StockMarket(Integer id, Integer stockId, String stockName, String stockCode) {
    this.id = id;
    this.stockId = stockId;
    this.stockName = stockName;
    this.stockCode = stockCode;
  }

  public StockMarket() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getStockId() {
    return stockId;
  }

  public void setStockId(Integer stockId) {
    this.stockId = stockId;
  }

  public String getStockName() {
    return stockName;
  }

  public void setStockName(String stockName) {
    this.stockName = stockName;
  }

  public String getStockCode() {
    return stockCode;
  }

  public void setStockCode(String stockCode) {
    this.stockCode = stockCode;
  }
}