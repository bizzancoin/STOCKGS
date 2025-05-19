package com.xc.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.xc.common.ServerResponse;
import com.xc.pojo.SiteNews;
import com.xc.service.ISiteNewsService;
import com.xc.service.IUserPositionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping({"/api/news/"})
@Api("新闻数据")
public class SiteNewsController {
    private static final Logger log = LoggerFactory.getLogger(SiteNewsController.class);
    @Autowired
    ISiteNewsService iSiteNewsService;

    @Autowired
    IUserPositionService iUserPositionService;

    //新闻资讯-列表查询
    @ApiOperation("新闻资讯-列表查询")
    @PostMapping({"getNewsList.do"})
    @ResponseBody
    public ServerResponse getNewsList(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "15") int pageSize, @RequestParam(value = "type", defaultValue = "0") Integer type, @RequestParam(value = "sort", defaultValue = "time1") String sort, @RequestParam(value = "keyword", required = false) String keyword, HttpServletRequest request) {
        return this.iSiteNewsService.getList(pageNum, pageSize, type, sort, keyword, request);
    }

    //新闻资讯-详情
    @ApiOperation("新闻资讯-详情")
    @PostMapping({"getDetail.do"})
    @ResponseBody
    public ServerResponse getDetail(int id) {
        return this.iSiteNewsService.getDetail(id);
    }

    //新闻资讯-修改新闻浏览量
    @PostMapping({"updateViews.do"})
    @ResponseBody
    public ServerResponse updateViews(Integer id) {
        return this.iSiteNewsService.updateViews(id);
    }

    //新闻资讯-列表查询
    @PostMapping({"getTopNews.do"})
    @ResponseBody
    public ServerResponse getTopNewsList(@RequestParam(value = "pageSize", defaultValue = "15") int pageSize) {
        return this.iSiteNewsService.getTopNewsList(pageSize);
    }

    //新闻资讯-列表查询
    @PostMapping({"getPositionTop.do"})
    @ResponseBody
    public ServerResponse findPositionTopList(@RequestParam(value = "pageSize", defaultValue = "15") int pageSize) {
        return this.iUserPositionService.findPositionTopList(pageSize);
    }




}