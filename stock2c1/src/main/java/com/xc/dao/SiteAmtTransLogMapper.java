package com.xc.dao;

import com.xc.pojo.SiteAmtTransLog;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SiteAmtTransLogMapper {
    int deleteByPrimaryKey(Integer paramInteger);

    int insert(SiteAmtTransLog paramSiteAmtTransLog);

    int insertSelective(SiteAmtTransLog paramSiteAmtTransLog);

    SiteAmtTransLog selectByPrimaryKey(Integer paramInteger);

    int updateByPrimaryKeySelective(SiteAmtTransLog paramSiteAmtTransLog);

    int updateByPrimaryKey(SiteAmtTransLog paramSiteAmtTransLog);

    List transList(@Param("userId") Integer paramInteger, @Param("realName") String paramString);
}
