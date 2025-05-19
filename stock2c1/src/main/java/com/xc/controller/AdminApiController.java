package com.xc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xc.common.ServerResponse;
import com.xc.pojo.SiteSpread;
import com.xc.service.*;
import com.xc.utils.PropertiesUtil;
import com.xc.utils.redis.CookieUtils;
import com.xc.utils.redis.JsonUtil;
import com.xc.utils.redis.RedisConst;
import com.xc.utils.redis.RedisShardedPoolUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/api/admin/"})
public class AdminApiController {
    private static final Logger log = LoggerFactory.getLogger(AdminApiController.class);

    @Autowired
    ISiteAdminService iSiteAdminService;

    @Autowired
    ISiteSettingService iSiteSettingService;

    @Autowired
    ISiteIndexSettingService iSiteIndexSettingService;

    @Autowired
    ISiteFuturesSettingService iSiteFuturesSettingService;

    @Autowired
    ISiteProductService iSiteProductService;

    @Autowired
    ISiteSpreadService iSiteSpreadService;

    //管理系统登录
    @RequestMapping({"login.do"})
    @ResponseBody
    public ServerResponse login(@RequestParam("adminPhone") String adminPhone, @RequestParam("adminPwd") String adminPwd, @RequestParam("verifyCode") String verifyCode, HttpSession httpSession, HttpServletRequest request, HttpServletResponse response) {
        ServerResponse serverResponse = this.iSiteAdminService.login(adminPhone, adminPwd, verifyCode, request);
        String admin_cookie_name = PropertiesUtil.getProperty("admin.cookie.name");
        if (serverResponse.isSuccess()) {
            String adminRedisKey = RedisConst.getAdminRedisKey(httpSession.getId());
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(serverResponse.getData()));
            jsonObject.put("token",adminRedisKey);
            CookieUtils.writeLoginToken(response,adminRedisKey, admin_cookie_name);
            String str = RedisShardedPoolUtils.setEx(RedisConst.getAdminRedisKey(httpSession.getId()),
                    JsonUtil.obj2String(serverResponse.getData()), 5400);
            return ServerResponse.createBySuccess(jsonObject);
        }
        return serverResponse;
    }

    //管理系统注销
    @RequestMapping({"logout.do"})
    @ResponseBody
    public ServerResponse logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String cookie_name = PropertiesUtil.getProperty("admin.cookie.name");
        String logintoken = CookieUtils.readLoginToken(httpServletRequest, cookie_name);
        log.info("管理员 token = {} ,退出登陆", logintoken);
        RedisShardedPoolUtils.del(logintoken);
        CookieUtils.delLoginToken(httpServletRequest, httpServletResponse, cookie_name);
        return ServerResponse.createBySuccess();
    }

    @RequestMapping({"authCharge.do"})
    @ResponseBody
    public ServerResponse authCharge(@RequestParam("token") String token, @RequestParam("state") Integer state, @RequestParam("orderSn") String orderSn) {
        return this.iSiteAdminService.authCharge(token, state, orderSn);
    }

    //查询风控设置 股票分控信息
    @RequestMapping({"getSetting.do"})
    @ResponseBody
    public ServerResponse getSetting() {
        return ServerResponse.createBySuccess(this.iSiteSettingService.getSiteSetting());
    }

    //查询风控设置 指数风控信息
    @RequestMapping({"getIndexSetting.do"})
    @ResponseBody
    public ServerResponse getIndexSetting() {
        return ServerResponse.createBySuccess(this.iSiteIndexSettingService.getSiteIndexSetting());
    }

    //查询风控设置 期货风控信息
    @RequestMapping({"getFuturesSetting.do"})
    @ResponseBody
    public ServerResponse getFuturesSetting() {
        return ServerResponse.createBySuccess(this.iSiteFuturesSettingService.getSetting());
    }

    //风控设置 显示产品配置信息
    @RequestMapping({"getProductSetting.do"})
    @ResponseBody
    public ServerResponse getProductSetting() {
        return ServerResponse.createBySuccess(this.iSiteProductService.getProductSetting());
    }

    //查询点差设置列表
    @RequestMapping({"getSiteSpreadList.do"})
    @ResponseBody
    public ServerResponse getSiteSpreadList(int pageNum, int pageSize, String typeName) {
        return ServerResponse.createBySuccess(this.iSiteSpreadService.pageList(pageNum, pageSize, typeName));
    }

    //添加点差设置
    @RequestMapping({"addSiteSpread.do"})
    @ResponseBody
    public ServerResponse addSiteSpread(SiteSpread siteSpread) {
        return ServerResponse.createBySuccess(this.iSiteSpreadService.insert(siteSpread));
    }

    //添加点差设置
    @RequestMapping({"updateSiteSpread.do"})
    @ResponseBody
    public ServerResponse updateSiteSpread(SiteSpread siteSpread) {
        return ServerResponse.createBySuccess(this.iSiteSpreadService.update(siteSpread));
    }

}
