package com.xc.controller.protol;


import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.xc.common.ServerResponse;
import com.xc.service.IStockOptionService;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Api("自选股列表")
@Controller
@RequestMapping({"/user/option/"})
public class UserOptionController {
    private static final Logger log = LoggerFactory.getLogger(UserOptionController.class);

    @Autowired
    IStockOptionService iStockOptionService;

    //查询所有自选股票信息及模糊查询
    @ApiOperation("查询自选股列表")
    @RequestMapping({"list.do"})
    @ResponseBody
    public ServerResponse list(HttpServletRequest request, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, @RequestParam(value = "keyWords", required = false) String keyWords) {
        return this.iStockOptionService.findMyStockOptions(keyWords, request, pageNum, pageSize);
    }
}
