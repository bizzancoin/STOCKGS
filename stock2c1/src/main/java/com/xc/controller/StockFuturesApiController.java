package com.xc.controller;


import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.xc.common.ServerResponse;

import com.xc.pojo.StockFutures;
import com.xc.service.IStockFuturesService;

import com.xc.vo.stockfutures.FuturesVO;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(value = "期货数据")
@Controller
@RequestMapping({"/api/futures/"})
public class StockFuturesApiController {

    private static final Logger log = LoggerFactory.getLogger(StockFuturesApiController.class);

    @Autowired
    IStockFuturesService iStockFuturesService;

    //查询所有首页显示的期货信息
    @ApiOperation("查询所有首页显示的期货信息")
    @PostMapping(value = {"queryHome.do"})
    @ResponseBody
    public ServerResponse queryHome() {
        return this.iStockFuturesService.queryHome();
    }


    //查询所有列表显示的期货信息
    @ApiOperation("查询所有列表显示的期货信息")
    @PostMapping({"queryList.do"})
    @ResponseBody
    public ServerResponse queryList(HttpServletRequest request) {
        return this.iStockFuturesService.queryList(request);
    }

    @PostMapping(value = {"queryTrans.do"})
    @ResponseBody
    public ServerResponse queryTrans(@RequestParam("futuresId") Integer futuresId) {
        return this.iStockFuturesService.queryTrans(futuresId);
    }

    //查询汇率
    @ApiOperation("查询汇率")
    @PostMapping(value = {"queryExchange.do"})
    @ResponseBody
    public ServerResponse queryExchange(@RequestParam("coinCode") String coinCode) {
        return this.iStockFuturesService.getExchangeRate(coinCode);
    }

    //查询期货详情信息 （开盘价/收盘价/最高/最低等等。。。）
    @ApiOperation("查询期货详情信息 （开盘价/收盘价/最高/最低等等。。。）")
    @PostMapping(value = {"querySingleMarket.do"})
    @ResponseBody
    public ServerResponse querySingleMarket(@RequestParam("futuresGid") String futuresGid) {
        FuturesVO futuresVO = this.iStockFuturesService.querySingleMarket(futuresGid);
        return ServerResponse.createBySuccess(futuresVO);
    }

    //查询期货详情
    @ApiOperation("查询期货详情")
    @PostMapping(value = {"queryFuturesByCode.do"})
    @ResponseBody
    public ServerResponse queryFuturesByCode(@RequestParam("futuresCode") String futuresCode) {
        StockFutures stockFutures = this.iStockFuturesService.selectFuturesByCode(futuresCode);
        return ServerResponse.createBySuccess(stockFutures);
    }
}
