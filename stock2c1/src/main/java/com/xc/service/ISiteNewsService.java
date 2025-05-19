package com.xc.service;


import com.github.pagehelper.PageInfo;
import com.xc.common.ServerResponse;
import com.xc.pojo.SiteNews;

import javax.servlet.http.HttpServletRequest;

/**
 * 新闻资讯
 * @author lr
 * @date 2020/07/24
 */
public interface ISiteNewsService {

    /**
     * 新增
     */
    int insert(SiteNews model);

    /**
     * 更新
     */
    int update(SiteNews model);

    /**
     * 新闻资讯-保存
     */
    ServerResponse save(SiteNews model);

    /**
     * 新闻资讯-列表查询
     */
    ServerResponse<PageInfo> getList(int pageNum, int pageSize, Integer type, String sort, String keyword, HttpServletRequest request);

    /**
     * 新闻资讯-查询详情
     */
    ServerResponse getDetail(int id);

    /**
     * 抓取新闻
     */
    int grabNews();

    /**
     * 新闻资讯-修改新闻浏览量
     */
    ServerResponse updateViews(Integer id);

    /**
     * 新闻资讯-top最新新闻资讯
     */
    ServerResponse getTopNewsList(int pageSize);

}
