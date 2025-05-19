package com.xc.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xc.common.ServerResponse;
import com.xc.dao.SitePayMapper;
import com.xc.pojo.SitePay;
import com.xc.service.ISitePayService;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("iSitePayService")
public class SitePayServiceImpl
        implements ISitePayService {
    @Autowired
    SitePayMapper sitePayMapper;

    public ServerResponse add(SitePay sitePay) {
        if (StringUtils.isBlank(sitePay.getChannelType()) ||
                StringUtils.isBlank(sitePay.getChannelName()) ||
                StringUtils.isBlank(sitePay.getChannelAccount()) || sitePay

                .getChannelMinLimit() == null || sitePay
                .getChannelMaxLimit() == null || sitePay
                .getIsShow() == null || sitePay
                .getIsLock() == null) {
            return ServerResponse.createByErrorMsg("参数不能为空");
        }


        SitePay dbSitePay = this.sitePayMapper.findByChannelType(sitePay.getChannelType());
        if (dbSitePay != null) {
            return ServerResponse.createByErrorMsg("支付类型已存在");
        }

        int insertCount = this.sitePayMapper.insert(sitePay);
        if (insertCount > 0) {
            return ServerResponse.createBySuccessMsg("添加成功");
        }
        return ServerResponse.createByErrorMsg("添加失败");
    }


    public ServerResponse listByAdmin(String channelType, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        List<SitePay> sitePays = this.sitePayMapper.listByAdmin(channelType);
        PageInfo pageInfo = new PageInfo(sitePays);

        return ServerResponse.createBySuccess(pageInfo);
    }


    public ServerResponse update(SitePay sitePay) {
        if (sitePay.getId() == null) {
            return ServerResponse.createByErrorMsg("修改id不能为空");
        }

        int updateCount = this.sitePayMapper.updateByPrimaryKeySelective(sitePay);
        if (updateCount > 0) {
            return ServerResponse.createBySuccessMsg("修改成功");
        }
        return ServerResponse.createByErrorMsg("修改失败");
    }


    public ServerResponse del(Integer cId) {
        if (cId == null) {
            return ServerResponse.createByErrorMsg("id不能为空");
        }
        int delCount = this.sitePayMapper.deleteByPrimaryKey(cId);
        if (delCount > 0) {
            return ServerResponse.createBySuccessMsg("删除成功");
        }
        return ServerResponse.createByErrorMsg("删除失败");
    }


    public ServerResponse getPayInfo() {
        List<SitePay> sitePays = this.sitePayMapper.getPayInfo();
        return ServerResponse.createBySuccess(sitePays);
    }


    public ServerResponse getPayInfoById(Integer payId) {
        return ServerResponse.createBySuccess(this.sitePayMapper.selectByPrimaryKey(payId));
    }
}
