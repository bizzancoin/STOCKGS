package com.xc.controller.backend;


import com.github.pagehelper.PageInfo;

import com.google.common.collect.Maps;

import com.xc.common.ServerResponse;

import com.xc.pojo.SiteAdmin;

import com.xc.service.IFileUploadService;

import com.xc.service.ISiteAdminService;

import com.xc.utils.PropertiesUtil;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping({"/admin/"})
public class AdminController {

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);


    @Autowired
    ISiteAdminService iSiteAdminService;


    @Autowired
    IFileUploadService iFileUploadService;

    //分页查询管理设置 所有管理列表数据及模糊查询
    @RequestMapping({"list.do"})
    @ResponseBody
    public ServerResponse<PageInfo> list(@RequestParam(value = "adminName", required = false) String adminName, @RequestParam(value = "adminPhone", required = false) String adminPhone, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, HttpServletRequest request) {
        return this.iSiteAdminService.listByAdmin(adminName, adminPhone, request, pageNum, pageSize);
    }

    //修改管理员状态 锁定管理员/解锁管理员
    @RequestMapping({"updateLock.do"})
    @ResponseBody
    public ServerResponse updateLock(Integer adminId) {
        return this.iSiteAdminService.updateLock(adminId);
    }

    //管理设置 添加管理员
    @RequestMapping({"add.do"})
    @ResponseBody
    public ServerResponse add(SiteAdmin siteAdmin) {
        return this.iSiteAdminService.add(siteAdmin);
    }

    //管理设置 修改管理员密码
    @RequestMapping({"update.do"})
    @ResponseBody
    public ServerResponse update(SiteAdmin siteAdmin) {
        return this.iSiteAdminService.update(siteAdmin);
    }

    //查询首页 资金情况、持仓情况、盈亏信息、提现情况、股票信息、代理信息
    @RequestMapping({"count.do"})
    @ResponseBody
    public ServerResponse count() {
        return this.iSiteAdminService.count();
    }

    //处理图片上传
    @RequestMapping({"upload.do"})
    @ResponseBody
    public ServerResponse upload(HttpSession session, @RequestParam(value = "upload_file", required = false) MultipartFile file, HttpServletRequest request) {

        String path = request.getSession().getServletContext().getRealPath("upload");

        ServerResponse serverResponse = this.iFileUploadService.upload(file, path);

        if (serverResponse.isSuccess()) {

            String targetFileName = serverResponse.getData().toString();

            String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFileName;


            Map fileMap = Maps.newHashMap();

            fileMap.put("uri", targetFileName);

            fileMap.put("url", url);


            return ServerResponse.createBySuccess(fileMap);

        }

        return serverResponse;

    }

    //删除管理员
    @RequestMapping({"deleteAdmin.do"})
    @ResponseBody
    public ServerResponse deleteAdmin(Integer adminId) {
        return this.iSiteAdminService.deleteAdmin(adminId);
    }
}
