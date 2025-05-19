package com.xc.controller;


import com.xc.common.ServerResponse;
import com.xc.pojo.Country;
import com.xc.pojo.CountryExample;
import com.xc.service.IConverBondApplyService;
import com.xc.service.IConverBondService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Iterator;
import java.util.List;

/**
 * 可转债接口
 */
@Controller
@RequestMapping({"/api/bond/"})
public class ConvertBondApiController {

    private static final Logger log = LoggerFactory.getLogger(ConvertBondApiController.class);

    @Autowired
    private IConverBondService converBondService;

    @Autowired
    private IConverBondApplyService converBondApplyService;



    /**
     * 获取可转债数据
     * @return
     */
    @RequestMapping(value = "/bondList", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse bondList(@RequestParam(value = "page", required = false,defaultValue = "1") Integer page,
                                   @RequestParam(value = "size", required = false,defaultValue = "20") Integer size ) {
        return ServerResponse.createBySuccess(converBondService.listByPage(page,size));
    }




}
