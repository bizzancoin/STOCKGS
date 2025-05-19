package com.xc.controller.backend;


import com.xc.common.ServerResponse;
import com.xc.pojo.SiteBanner;
import com.xc.service.ISiteBannerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping({"/admin/banners/"})
public class AdminSiteBannerController {
    private static final Logger log = LoggerFactory.getLogger(AdminSiteBannerController.class);

    @Autowired
    ISiteBannerService iSiteBannerService;

    //添加系统基本设置  banner设置信息
    @RequestMapping({"add.do"})
    @ResponseBody
    public ServerResponse add(SiteBanner siteBanner) {
        return this.iSiteBannerService.add(siteBanner);
    }

    //修改系统基本设置  banner设置信息
    @RequestMapping({"update.do"})
    @ResponseBody
    public ServerResponse update(SiteBanner siteBanner) {
        return this.iSiteBannerService.update(siteBanner);
    }

    //删除系统基本设置  banner设置信息
    @RequestMapping({"delete.do"})
    @ResponseBody
    public ServerResponse delete(Integer id) {
        return this.iSiteBannerService.delete(id);
    }

    //查询系统基本设置 banner设置信息
    @RequestMapping({"list.do"})
    @ResponseBody
    public ServerResponse list(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return this.iSiteBannerService.listByAdmin(pageNum, pageSize);
    }
}
