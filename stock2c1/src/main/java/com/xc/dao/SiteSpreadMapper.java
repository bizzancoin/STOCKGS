package com.xc.dao;

import com.xc.pojo.SiteSpread;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * site_spread
 * @author lr
 * @date 2020/07/01
 */
public interface SiteSpreadMapper {

    /**
     * [新增]
     * @author lr
     * @date 2020/07/01
     **/
    int insert(SiteSpread siteSpread);

    /**
     * [刪除]
     * @author lr
     * @date 2020/07/01
     **/
    int delete(int id);

    /**
     * [更新]
     * @author lr
     * @date 2020/07/01
     **/
    int update(SiteSpread siteSpread);

    /**
     * [查询] 根据主键 id 查询
     * @author lr
     * @date 2020/07/01
     **/
    SiteSpread load(int id);

    /**
     * [查询] 分页查询
     * @author lr
     * @date 2020/07/01
     **/
    List<SiteSpread> pageList(@Param("pageNum") int pageNum,@Param("pageSize")  int pageSize,@Param("typeName") String typeName);

    /**
     * 查询点差费率
     * @author lr
     * @date 2020/07/01
     * applies：涨跌幅
     * turnover：成交额
     * code:股票代码
     * unitprice：股票单价
     **/
    SiteSpread findSpreadRateOne(@Param("applies") BigDecimal applies,@Param("turnover") BigDecimal turnover,@Param("code") String code,@Param("unitprice") BigDecimal unitprice);

}
