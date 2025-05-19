package com.xc.controller;


import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.xc.common.ServerResponse;
import com.xc.common.StockIndexConst;
import com.xc.service.IStockIndexService;
import com.xc.utils.CacheUtil;
import com.xc.vo.stock.MarketVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(value = "指数数据")
@Controller
@RequestMapping({"/api/index/"})
public class StockIndexApiController {
    private static final Logger log = LoggerFactory.getLogger(StockIndexApiController.class);

    @Autowired
    IStockIndexService iStockIndexService;

    //查询指数信息
    @ApiOperation(value = "查询指数信息")
    @PostMapping(value = {"queryHomeIndex.do"})
    @ResponseBody
    public ServerResponse queryHomeIndex() {
        ServerResponse serverResponse=StockIndexConst.ZDFBMap.get("homeIndex");
        if(serverResponse==null){
            serverResponse= this.iStockIndexService.queryHomeIndex();
        }
        return serverResponse;
    }

    @PostMapping({"queryListIndex.do"})
    @ResponseBody
    public ServerResponse queryListIndex(HttpServletRequest request) {
        return this.iStockIndexService.queryListIndex(request);
    }

    @PostMapping({"queryTransIndex.do"})
    @ResponseBody
    public ServerResponse queryTransIndex(@RequestParam("indexId") Integer indexId) {
        return this.iStockIndexService.queryTransIndex(indexId);
    }

    @PostMapping({"querySingleIndex.do"})
    @ResponseBody
    public ServerResponse querySingleIndex(@RequestParam("indexCode") String indexCode) {
        MarketVO marketVO = this.iStockIndexService.querySingleIndex(indexCode);
        return ServerResponse.createBySuccess(marketVO);
    }
}
