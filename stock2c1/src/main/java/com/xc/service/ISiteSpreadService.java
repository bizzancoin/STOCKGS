package com.xc.service;

import com.github.pagehelper.PageInfo;
import com.xc.common.ServerResponse;
import com.xc.pojo.SiteSpread;

import java.math.BigDecimal;

public interface ISiteSpreadService {
    /**
     * 新增
     */
    int insert(SiteSpread siteSpread);

    /**
     * 删除
     */
    int delete(int id);

    /**
     * 更新
     */
    int update(SiteSpread siteSpread);

    /**
     * 根据主键 id 查询
     */
    SiteSpread load(int id);

    /**
     * 分页查询
     */
    ServerResponse<PageInfo> pageList(int pageNum, int pageSize, String typeName);

    /**
     * 查询点差费率
     * @author lr
     * @date 2020/07/01
     * applies：涨跌幅
     * turnover：成交额
     * code:股票代码
     * unitprice：股票单价
     **/
    SiteSpread findSpreadRateOne(BigDecimal applies, BigDecimal turnover, String code, BigDecimal unitprice);
}
