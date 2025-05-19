package com.xc.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.xc.common.ServerResponse;
import com.xc.service.IStockService;
import com.xc.service.RealTimeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/api/realTime/"})
@Api(value = "分时图数据")
public class RealTimeController {

    @Autowired
    RealTimeService realTimeService;

    @Autowired
    IStockService stockService;

    @ApiOperation(value = "获取指定股票分时图数据")
    @PostMapping({"findStock.do"})
    @ResponseBody
    public ServerResponse findStock(@RequestParam(value = "stockCode", required = false) String stockCode) {
        return this.realTimeService.findStock(stockCode);
    }

}
