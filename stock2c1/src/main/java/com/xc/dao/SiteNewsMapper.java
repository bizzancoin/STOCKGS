package com.xc.dao;

import com.xc.pojo.SiteNews;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * 新闻资讯
 * @author lr
 * @date 2020/08/05
 */
@Mapper
@Repository
public interface SiteNewsMapper {

    /**
     * [新增]
     * @author lr
     * @date 2020/08/05
     **/
    int insert(SiteNews siteNews);

    /**
     * [刪除]
     * @author lr
     * @date 2020/08/05
     **/
    int delete(int id);

    /**
     * [更新]
     * @author lr
     * @date 2020/08/05
     **/
    int update(SiteNews siteNews);

    /**
     * [查询] 根据主键 id 查询
     * @author lr
     * @date 2020/08/05
     **/
    SiteNews load(int id);

    /**
     * [查询] 分页查询
     * @author lr
     * @date 2020/08/05
     **/
    List<SiteNews> pageList(@Param("pageNum") int pageNum,@Param("pageSize") int pageSize,@Param("type") Integer type,@Param("sort") String sort,@Param("keyword") String keyword);


    /**
     * [查询] 分页查询 count
     * @author lr
     * @date 2020/08/05
     **/
    int pageListCount(int offset,int pagesize);

    /**
     * [查询]根据来源id查询新闻数
     * @author lr
     * @date 2020/08/05
     **/
    int getNewsBySourceIdCount(@Param("sourceId") String sourceId);

    /**
     * [修改]修改新闻浏览量
     * @author lr
     * @date 2020/08/05
     **/
    int updateViews(@Param("id") Integer id);

    /**
     * [查询] top最新新闻资讯
     * @author lr
     * @date 2020/08/05
     **/
    List<SiteNews> getTopNewsList(@Param("pageSize") int pageSize);

}
