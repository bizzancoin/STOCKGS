package com.xc.common;

import com.xc.utils.stock.lide.LideDataPanVo;
import com.xc.utils.stock.lide.LideDataVo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StockIndexConst {
    //存储涨跌幅榜数据
    public final static Map<String,ServerResponse> ZDFBMap=new ConcurrentHashMap<>();

    //存储行情股票数据
    public final static Map<String, LideDataVo> STOCKMap=new ConcurrentHashMap<>();

    //存储股票盘口数据
    public final static Map<String, LideDataPanVo> STOCKPANMap=new ConcurrentHashMap<>();

}

