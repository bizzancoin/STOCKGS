package com.xc.dao;


import com.xc.pojo.SiteArticle;
import java.util.List;

import com.xc.pojo.SiteNews;
import org.apache.ibatis.annotations.Param;

public interface SiteArticleMapper {
    int deleteByPrimaryKey(Integer paramInteger);

    int insert(SiteArticle paramSiteArticle);

    int insertSelective(SiteArticle paramSiteArticle);

    SiteArticle selectByPrimaryKey(Integer paramInteger);

    int updateByPrimaryKeySelective(SiteArticle paramSiteArticle);

    int updateByPrimaryKey(SiteArticle paramSiteArticle);

    List listByAdmin(@Param("artTitle") String paramString1, @Param("artType") String paramString2);
    List list(@Param("artTitle") String paramString1, @Param("artType") String paramString2);

    /**
     * [查询] top最新公告
     * @author lr
     * @date 2020/08/05
     **/
    List<SiteNews> getTopArtList(@Param("pageSize") int pageSize);

    /**
     * [查询]根据来源id查询公告数
     * @author lr
     * @date 2020/08/05
     **/
    int getArtBySourceIdCount(@Param("sourceId") String sourceId);

}