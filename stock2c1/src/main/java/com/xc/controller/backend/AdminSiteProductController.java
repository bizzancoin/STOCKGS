 package com.xc.controller.backend;


 import com.xc.common.ServerResponse;
 import com.xc.pojo.SiteProduct;
 import com.xc.service.ISiteProductService;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.ResponseBody;


 @Controller
 @RequestMapping({"/admin/product/"})
 public class AdminSiteProductController {
     private static final Logger log = LoggerFactory.getLogger(AdminSiteProductController.class);

     @Autowired
     ISiteProductService iSiteProductService;

     //风控设置 修改产品配置信息
     @RequestMapping({"update.do"})
     @ResponseBody
     public ServerResponse update(SiteProduct siteProduct) {
         return this.iSiteProductService.update(siteProduct);
     }
 }
