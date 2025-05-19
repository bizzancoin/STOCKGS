package com.xc.controller.backend;


import com.xc.common.ServerResponse;
import com.xc.pojo.StockIndex;
import com.xc.service.IStockIndexService;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping({"/admin/index/"})
public class AdminStockIndexController {
    private static final Logger log = LoggerFactory.getLogger(AdminStockIndexController.class);

    @Autowired
    IStockIndexService iStockIndexService;

    //分页查询产品管理 所以指数信息及模糊查询
    @RequestMapping({"list.do"})
    @ResponseBody
    public ServerResponse list(@RequestParam(value = "homeShow", required = false) Integer homeShow, @RequestParam(value = "listShow", required = false) Integer listShow, @RequestParam(value = "transState", required = false) Integer transState, @RequestParam(value = "indexCode", required = false) String indexCode, @RequestParam(value = "indexName", required = false) String indexName, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, HttpServletRequest request) {
        return this.iStockIndexService.listByAdmin(homeShow, listShow, transState, indexCode, indexName, pageNum, pageSize, request);
    }

    //修改产品管理 指数信息
    @RequestMapping({"updateIndex.do"})
    @ResponseBody
    public ServerResponse updateIndex(StockIndex stockIndex) {
        return this.iStockIndexService.updateIndex(stockIndex);
    }

    //添加产品管理 指数信息
    @RequestMapping({"addIndex.do"})
    @ResponseBody
    public ServerResponse addIndex(StockIndex stockIndex) {
        return this.iStockIndexService.addIndex(stockIndex);
    }
}
