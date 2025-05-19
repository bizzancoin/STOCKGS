package com.xc.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xc.common.ServerResponse;
import com.xc.dao.SiteSpreadMapper;
import com.xc.pojo.AgentAgencyFee;
import com.xc.pojo.AgentUser;
import com.xc.pojo.SiteSpread;
import com.xc.service.ISiteSpreadService;
import com.xc.vo.agent.AgentAgencyFeeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * site_spread
 * @author lr
 * @date 2020/07/01
 */
@Service("ISiteSpreadService")
public class SiteSpreadServiceImpl implements ISiteSpreadService {

    @Autowired
    private SiteSpreadMapper siteSpreadMapper;


    @Override
    public int insert(SiteSpread siteSpread) {
        int ret = 0;
        // valid
        if (siteSpread == null) {
            return 0;
        }

        ret = siteSpreadMapper.insert(siteSpread);
        return ret;

    }


    @Override
    public int delete(int id) {
        int ret = 0;
        // valid
        if (id == 0) {
            return 0;
        }

        ret = siteSpreadMapper.delete(id);
        return ret;
    }


    @Override
    public int update(SiteSpread siteSpread) {
        int ret = 0;
        // valid
        if (siteSpread == null) {
            return 0;
        }

        ret = siteSpreadMapper.update(siteSpread);
        return ret;
    }


    @Override
    public SiteSpread load(int id) {
        return siteSpreadMapper.load(id);
    }


    @Override
    public ServerResponse<PageInfo> pageList(int pageNum, int pageSize, String typeName) {
        Page<AgentAgencyFeeVO> page = PageHelper.startPage(pageNum, pageSize);
        List<SiteSpread> list = this.siteSpreadMapper.pageList(pageNum, pageSize, typeName);
        PageInfo pageInfo = new PageInfo(page);
        pageInfo.setList(list);
        return ServerResponse.createBySuccess(pageInfo);

    }

    /**
     * 查询点差费率
     * @author lr
     * @date 2020/07/01
     * applies：涨跌幅
     * turnover：成交额
     * code:股票代码
     * unitprice：股票单价
     **/
    @Override
    public SiteSpread findSpreadRateOne(BigDecimal applies, BigDecimal turnover, String code, BigDecimal unitprice) {
        return siteSpreadMapper.findSpreadRateOne(applies,turnover,code,unitprice);
    }

}