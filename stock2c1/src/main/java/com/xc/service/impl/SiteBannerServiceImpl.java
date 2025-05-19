package com.xc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xc.common.ServerResponse;
import com.xc.dao.SiteBannerMapper;
import com.xc.pojo.SiteBanner;
import com.xc.service.ISiteBannerService;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("iSiteBannerService")
public class SiteBannerServiceImpl
        implements ISiteBannerService {
    private static final Logger log = LoggerFactory.getLogger(SiteBannerServiceImpl.class);


    @Autowired
    SiteBannerMapper siteBannerMapper;


    public ServerResponse add(SiteBanner siteBanner) {
        if (StringUtils.isBlank(siteBanner.getBannerUrl()) || siteBanner
                .getIsOrder() == null || siteBanner
                .getIsPc() == null || siteBanner
                .getIsM() == null) {
            return ServerResponse.createByErrorMsg("参数不能为空");
        }

        siteBanner.setAddTime(new Date());
        int insertCount = this.siteBannerMapper.insert(siteBanner);
        if (insertCount > 0) {
            return ServerResponse.createBySuccessMsg("添加成功");
        }
        return ServerResponse.createByErrorMsg("添加失败");
    }


    public ServerResponse listByAdmin(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SiteBanner> siteBanners = this.siteBannerMapper.listByAdmin();

        PageInfo pageInfo = new PageInfo(siteBanners);
        return ServerResponse.createBySuccess(pageInfo);
    }


    public ServerResponse update(SiteBanner siteBanner) {
        if (siteBanner == null) {
            return ServerResponse.createByErrorMsg("id不能为空");
        }

        int updateCount = this.siteBannerMapper.updateByPrimaryKeySelective(siteBanner);
        if (updateCount > 0) {
            return ServerResponse.createBySuccessMsg("修改成功");
        }
        return ServerResponse.createByErrorMsg("修改失败");
    }


    public ServerResponse delete(Integer id) {
        if (id == null) {
            return ServerResponse.createByErrorMsg("id不能为空");
        }
        int deleteCount = this.siteBannerMapper.deleteByPrimaryKey(id);
        if (deleteCount > 0) {
            return ServerResponse.createBySuccessMsg("删除成功");
        }
        return ServerResponse.createByErrorMsg("删除失败");
    }


    public ServerResponse getBannerByPlat(String platType) {
        if ("m".equals(platType)) {
            List bannerList = this.siteBannerMapper.getBannerByMobile();
            return ServerResponse.createBySuccess(bannerList);
        }
        if ("pc".equals(platType)) {
            List bannerList = this.siteBannerMapper.getBannerByPC();
            return ServerResponse.createBySuccess(bannerList);
        }
        return ServerResponse.createByErrorMsg("不存在的平台类型");
    }
}

