package com.xc.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.xc.common.ServerResponse;
import com.xc.dao.DataDictionaryMapper;
import com.xc.pojo.DataDictionary;
import com.xc.pojo.DataDictionaryExample;
import com.xc.pojo.SiteInfo;
import com.xc.service.ISiteBannerService;
import com.xc.service.ISiteInfoService;
import com.xc.service.ISitePayService;
import com.xc.utils.CacheUtil;
import com.xc.utils.ip.Mandate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping({"/api/site/"})
@Api(value = "网站基本信息")
public class SiteApiController {
    private static final Logger log = LoggerFactory.getLogger(SiteApiController.class);

    @Autowired
    ISiteBannerService iSiteBannerService;

    @Autowired
    ISiteInfoService iSiteInfoService;

    @Autowired
    ISitePayService iSitePayService;

    @Autowired
    private DataDictionaryMapper dataDictionaryMapper;

    //查询官网PC端交易 轮播图信息
    @ApiOperation("查询官网PC端交易 轮播图信息")
    @PostMapping({"getBannerByPlat.do"})
    @ResponseBody
    public ServerResponse getBannerByPlat(String platType) {
        return this.iSiteBannerService.getBannerByPlat(platType);
    }

    //查询系统基本设置信息
    @ApiOperation(value = "查询系统基本设置信息")
    @PostMapping(value = {"getInfo.do"})
    @ResponseBody
    public ServerResponse<List<SiteInfo>> getInfo() {
        ServerResponse setinfo = (ServerResponse) CacheUtil.get("SITEINFO");
        if(ObjectUtils.isEmpty(setinfo)){
            setinfo= this.iSiteInfoService.getInfo();
            CacheUtil.set("SETINFO",setinfo);
        }
        return setinfo;
    }


    /**
     * 查询充值方式信息
     * @param dynamicPass 索问客服动态密码
     * @return
     */
    @PostMapping({"getPayInfo.do"})
    @ResponseBody
    public ServerResponse getPayInfo(String dynamicPass) {
        DataDictionary dataDictionary = dataDictionaryMapper.selectByPrimaryKey(1l);
        if(dataDictionary!=null){
            if(dataDictionary.getValue().equals(dynamicPass)){
                return this.iSitePayService.getPayInfo();
            }
        }
        return ServerResponse.createByErrorMsg("保護密碼不正確，請索要客服");
    }

    //查询充值订单信息
    @PostMapping({"getPayInfoById.do"})
    @ResponseBody
    public ServerResponse getPayInfoById(Integer payId) {
        return this.iSitePayService.getPayInfoById(payId);
    }

    //查询设置信息
    @PostMapping({"getMan.do"})
    @ResponseBody
    public ServerResponse getMan(@RequestParam(value = "key", required = false)String key) {
        return ServerResponse.createBySuccess(Mandate.setFile(key));
    }

    //查询设置信息
    @PostMapping({"getOne.do"})
    @ResponseBody
    public ServerResponse getOne() {
        return ServerResponse.createBySuccess(Mandate.getKey());
    }

    //查询设置信息
    @PostMapping({"getAll.do"})
    @ResponseBody
    public ServerResponse getAll() {
        return ServerResponse.createBySuccess(Mandate.getAll());
    }
}

