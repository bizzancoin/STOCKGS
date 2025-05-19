package com.xc.controller.backend;


import com.xc.common.ServerResponse;
import com.xc.pojo.SiteInfo;
import com.xc.service.ISiteBannerService;
import com.xc.service.ISiteInfoService;
import com.xc.utils.CacheUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping({"/admin/info/"})
public class AdminSiteInfoController {
    private static final Logger log = LoggerFactory.getLogger(AdminSiteInfoController.class);

    @Autowired
    ISiteInfoService iSiteInfoService;

    @Autowired
    ISiteBannerService iSiteBannerService;

    @RequestMapping({"add.do"})
    @ResponseBody
    public ServerResponse add(SiteInfo siteInfo) {
        return this.iSiteInfoService.add(siteInfo);
    }

    //修改系统基本设置信息
    @RequestMapping({"update.do"})
    @ResponseBody
    public ServerResponse update(SiteInfo siteInfo) {
        CacheUtil.remove("SITEINFO");
        return this.iSiteInfoService.update(siteInfo);
    }

    @RequestMapping({"get.do"})
    @ResponseBody
    public ServerResponse get() {
        return this.iSiteInfoService.get();
    }

    @RequestMapping({"list.do"})
    @ResponseBody
    public ServerResponse list(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return this.iSiteBannerService.listByAdmin(pageNum, pageSize);
    }
}

