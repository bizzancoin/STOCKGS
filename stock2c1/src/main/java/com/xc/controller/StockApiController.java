package com.xc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lly835.bestpay.utils.DateUtil;
import com.wordnik.swagger.annotations.*;
import com.wordnik.swagger.models.auth.In;
import com.xc.common.ServerResponse;
import com.xc.common.StockIndexConst;
import com.xc.service.INewListService;
import com.xc.service.IStockService;
import com.xc.utils.CacheUtil;
import com.xc.utils.DateTimeUtil;
import com.xc.utils.HttpClientRequest;
import com.xc.utils.PropertiesUtil;
import com.xc.vo.stock.ChartCellVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping({"/api/stock/"})
@Api("股票数据")
public class StockApiController {
    private static final Logger log = LoggerFactory.getLogger(StockApiController.class);


    @Autowired
    IStockService iStockService;


    //查询 股票指数、大盘指数信息
    @ApiOperation("查询 股票指数、大盘指数信息")
    @RequestMapping({"getMarket.do"})
    @ResponseBody
    public ServerResponse getMarket() {
        ServerResponse getMarket = StockIndexConst.ZDFBMap.get("zs");
        if(ObjectUtils.isEmpty(getMarket)){
            ServerResponse market = this.iStockService.getMarket();
            StockIndexConst.ZDFBMap.put("zs",market);
            return market;
        }else {
            return getMarket;
        }
    }

    //查询官网PC端交易 所有股票信息及指定股票信息
    @ApiOperation("查询所有股票信息及指定股票信息")
    @PostMapping({"getStock.do"})
    @ResponseBody
    public ServerResponse getStock(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                   @ApiParam("板块名字 ep:沪深 科创 创业")  @RequestParam(value = "stockPlate", required = false) String stockPlate,
                                   @ApiParam("股票类型") @RequestParam(value = "stockType", required = false) String stockType,
                                   @ApiParam("关键字") @RequestParam(value = "keyWords", required = false) String keyWords, HttpServletRequest request) {
        return this.iStockService.getStock(pageNum, pageSize, keyWords, stockPlate, stockType, request);
    }

    //通过股票代码查询股票信息
    @ApiOperation("通过股票代码查询股票买卖盘口信息")
    @PostMapping({"getSingleStock.do"})
    @ResponseBody
    public ServerResponse getSingleStock(@RequestParam("code") String code) {
        return this.iStockService.getSingleStock(code);
    }
    @ApiOperation("查询股票分钟线")
    @PostMapping({"getMinK.do"})
    @ResponseBody
    public ServerResponse getMinK(@RequestParam("code") String code, @RequestParam("time") Integer time, @RequestParam("ma") Integer ma, @RequestParam("size") Integer size) {
        return this.iStockService.getMinK(code, time, ma, size);
    }

    /*查询股票日线*/
    @ApiOperation("查询股票日线")
    @PostMapping({"getDayK.do"})
    @ResponseBody
    public ServerResponse getDayK(@RequestParam("code") String code) {
        return this.iStockService.getDayK_Echarts(code);
    }

    /*查询股票周线  月线 年线*/
    @ApiOperation("查询股票周线  月线 年线")
    @PostMapping({"getDaysK.do"})
    @ResponseBody
    public ServerResponse getDaysK(@RequestParam("code") String code,@RequestParam("type") String type) {
        return this.iStockService.getDayK_EchartsByType(code,type);
    }

    //查询股票历史数据数据
    @ApiOperation("查询股票历史数据数据")
    @PostMapping({"getMinK_Echarts.do"})
    @ResponseBody
    public ServerResponse getMinK_Echarts(@RequestParam("code") String code, @RequestParam("time") Integer time, @RequestParam("ma") Integer ma, @RequestParam("size") Integer size) {
        return this.iStockService.getMinK_Echarts(code, time, ma, size);
    }

    /*期货分时-k线*/
    @PostMapping({"getFuturesMinK_Echarts.do"})
    @ResponseBody
    public ServerResponse getFuturesMinK_Echarts(@RequestParam("code") String code, @RequestParam("time") Integer time, @RequestParam("size") Integer size) {
        return this.iStockService.getFuturesMinK_Echarts(code, time, size);
    }

    /*指数分时-k线*/
    @PostMapping({"getIndexMinK_Echarts.do"})
    @ResponseBody
    public ServerResponse getIndexMinK_Echarts(@RequestParam("code") String code, @RequestParam("time") Integer time, @RequestParam("size") Integer size) {
        return this.iStockService.getIndexMinK_Echarts(code, time, size);
    }

    /*查询期货日线*/
    @PostMapping({"getFuturesDayK.do"})
    @ResponseBody
    public ServerResponse getFuturesDayK(@RequestParam("code") String code) {
        return this.iStockService.getFuturesDayK(code);
    }

    /*指数日线*/
    @PostMapping({"getIndexDayK.do"})
    @ResponseBody
    public ServerResponse getIndexDayK(@RequestParam("code") String code) {
        return this.iStockService.getIndexDayK(code);
    }

    /*获取同花顺涨跌幅榜*/
    @PostMapping({"getZdfb.do"})
    @ResponseBody
    public ServerResponse getZdfb() {
        ServerResponse serverResponse = StockIndexConst.ZDFBMap.get("zdfb");
        if(ObjectUtils.isEmpty(serverResponse)){
            return this.iStockService.getZdfb();
        }
        return serverResponse;
    }

    /*获取涨跌数据*/
    @ApiOperation("获取涨跌数据")
    @PostMapping({"getZdfNumber.do"})
    @ResponseBody
    public ServerResponse getZdfNumber() {
//        return StockIndexConst.ZDFBMap.get("zdfb");
//
        ServerResponse zdfb = StockIndexConst.ZDFBMap.get("zdfb");
        if(ObjectUtils.isEmpty(zdfb)){
            zdfb= this.iStockService.getZdfb();
        }
        ArrayList<ChartCellVO> data = (ArrayList<ChartCellVO>) zdfb.getData();
        if(CollectionUtils.isEmpty(data)){
            return ServerResponse.createByError();
        }
        ChartCellVO chartCellVO = data.get(0);
        ChartCellVO chartCellVO1 = data.get(3);
        ChartCellVO chartCellVO3 = data.get(2);
        ChartCellVO chartCellVO4 = data.get(5);
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("zhang",Integer.parseInt(chartCellVO.getyAxis())+ Integer.parseInt(chartCellVO1.getyAxis()));
        resultMap.put("die",Integer.parseInt(chartCellVO3.getyAxis())+ Integer.parseInt(chartCellVO4.getyAxis()));
        return ServerResponse.createBySuccess(resultMap);
    }

    /**
     * 获取股票根据 涨跌幅 成交额 价格排序
     * @return
     */
    @ApiOperation("获取股票根据 涨跌幅 成交额 价格排序")
    @PostMapping({"getStockSort.do"})
    @ResponseBody
    public ServerResponse getStockMarketZDFB(Integer pageNo,Integer pageSize,@ApiParam("根据什么排序   changepercent 涨跌幅  pricechange 涨跌额  volume 成交量 amount 成交额") String sort,
                                             @ApiParam("是否升序 0否 1是") Integer asc,
                                             @ApiParam("排序的主板类型 hs_bjs 北交所  cyb 创业板  kcb 科创板   hs_a 沪深a股") String node){
        String key=pageNo+pageSize+sort+asc+node;
        String result = (String) CacheUtil.get(key);
        if(StringUtils.isEmpty(result)){
            ServerResponse stockSort = this.iStockService.getStockSort(pageNo, pageSize, sort, asc, node);
            CacheUtil.set(key,JSON.toJSONString(stockSort.getData()),30000);
            return stockSort;
        }
        log.info("命中缓存：{}",key);
        return ServerResponse.createBySuccess(JSON.parseArray(result));
    }


    /**
     * 手动同步股票数据
     * @return
     */
    @PostMapping({"sycnStockData.do"})
    @ResponseBody
    public ServerResponse getStockMarketZDFB(String pass,Integer pageNo,Integer endPage){
        if(!StringUtils.isEmpty(pass)&&pass.equals("a888888")){
            for( ;pageNo<=endPage;pageNo++){
                String market_url = "https://vip.stock.finance.sina.com.cn/quotes_service/api/json_v2.php/Market_Center.getHQNodeData?page="+pageNo+"&num=100&sort=changepercent&asc=0&node=hs_a&symbol=&_s_r_a=setlen";
                String result = null;
                try {
                    log.info("新浪接口：{}",market_url);
                    result = HttpClientRequest.doGet(market_url);
                    com.alibaba.fastjson.JSONArray jsonArray = JSON.parseArray(result);
                    if(jsonArray!=null&&jsonArray.size()>0){
                        for(int i=0;i<jsonArray.size();i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            this.iStockService.addStock(jsonObject.getString("name"),jsonObject.getString("code")
                                    ,jsonObject.getString("symbol").substring(0,2),"cc",0,0);
                        }
                    }
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (Exception e) {
                    log.error("e = {}", e);
                }
            }
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByError();

    }


}