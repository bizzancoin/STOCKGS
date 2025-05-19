 package com.xc.controller.backend;

 import com.xc.common.ServerResponse;

 import com.xc.pojo.Stock;
 import com.xc.service.IStockService;

 import javax.servlet.http.HttpServletRequest;

 import org.slf4j.Logger;

 import org.slf4j.LoggerFactory;

 import org.springframework.beans.factory.annotation.Autowired;

 import org.springframework.stereotype.Controller;

 import org.springframework.web.bind.annotation.RequestMapping;

 import org.springframework.web.bind.annotation.RequestParam;

 import org.springframework.web.bind.annotation.ResponseBody;


 @Controller
 @RequestMapping({"/admin/stock/"})
 public class AdminStockController {
     private static final Logger log = LoggerFactory.getLogger(AdminStockController.class);

     @Autowired
     IStockService iStockService;

     //查询产品管理 所以股票信息及模糊查询
     @RequestMapping({"list.do"})
     @ResponseBody
     public ServerResponse list(@RequestParam(value = "showState", required = false) Integer showState, @RequestParam(value = "lockState", required = false) Integer lockState, @RequestParam(value = "code", required = false) String code, @RequestParam(value = "name", required = false) String name, @RequestParam(value = "stockPlate", required = false) String stockPlate, @RequestParam(value = "stockType", required = false) String stockType, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, HttpServletRequest request) {
         return this.iStockService.listByAdmin(showState, lockState, code, name, stockPlate, stockType, pageNum, pageSize, request);
     }


     //修改产品管理 股票是否锁定
     @RequestMapping({"updateLock.do"})
     @ResponseBody
     public ServerResponse updateLock(Integer stockId) {
         return this.iStockService.updateLock(stockId);
     }

     //修改产品管理 股票状态
     @RequestMapping({"updateShow.do"})
     @ResponseBody
     public ServerResponse updateShow(Integer stockId) {
         return this.iStockService.updateShow(stockId);
     }

     //添加产品管理 股票信息
     @RequestMapping({"add.do"})
     @ResponseBody
     public ServerResponse add(@RequestParam(value = "stockName", required = false) String stockName, @RequestParam(value = "stockCode", required = false) String stockCode, @RequestParam(value = "stockType", required = false) String stockType, @RequestParam(value = "stockPlate", required = false) String stockPlate, @RequestParam(value = "isLock", required = false) Integer isLock, @RequestParam(value = "isShow", required = false) Integer isShow) {
         return this.iStockService.addStock(stockName, stockCode, stockType, stockPlate, isLock, isShow);
     }

     //修改票信息
     @RequestMapping({"updateStock.do"})
     @ResponseBody
     public ServerResponse updateStock(Stock model) {
         return this.iStockService.updateStock(model);
     }

     //删除股票
     @RequestMapping({"delStock.do"})
     @ResponseBody
     public ServerResponse deleteByPrimaryKey(Integer id) {
         return this.iStockService.deleteByPrimaryKey(id);
     }



 }