package com.xc.controller.backend;


import com.xc.common.ServerResponse;

import com.xc.service.*;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping({"/admin/log/"})
public class AdminLogsController {
    private static final Logger log = LoggerFactory.getLogger(AdminLogsController.class);

    @Autowired
    ISiteLoginLogService iSiteLoginLogService;

    @Autowired
    ISiteTaskLogService iSiteTaskLogService;

    @Autowired
    ISiteSmsLogService iSiteSmsLogService;

    @Autowired
    ISiteAmtTransLogService iSiteAmtTransLogService;

    @Autowired
    ISiteMessageService iSiteMessageService;

    //分页查询日志管理 所有定时任务信息及模糊查询
    @RequestMapping({"taskList.do"})
    @ResponseBody
    public ServerResponse taskList(@RequestParam(value = "taskType", required = false) String taskType, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return this.iSiteTaskLogService.taskList(taskType, pageNum, pageSize);
    }

    //分页查询日志管理 所有登陆日志信息
    @RequestMapping({"loginList.do"})
    @ResponseBody
    public ServerResponse loginList(@RequestParam(value = "userId", required = false) Integer userId, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return this.iSiteLoginLogService.loginList(userId, pageNum, pageSize);
    }

    //分页查询日志管理 所有短信日志信息
    @RequestMapping({"smsList.do"})
    @ResponseBody
    public ServerResponse smsList(@RequestParam(value = "phoneNum", required = false) String phoneNum, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return this.iSiteSmsLogService.smsList(phoneNum, pageNum, pageSize);
    }

    //分页查询日志管理 所有资金互转记录信息及模糊查询
    @RequestMapping({"transList.do"})
    @ResponseBody
    public ServerResponse transList(@RequestParam(value = "userId", required = false) Integer userId, @RequestParam(value = "realName", required = false) String realName, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return this.iSiteAmtTransLogService.transList(userId, realName, pageNum, pageSize);
    }

    //分页查询站内消息
    @RequestMapping({"messageList.do"})
    @ResponseBody
    public ServerResponse messageList(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, HttpServletRequest request) {
        return this.iSiteMessageService.getSiteMessageByUserIdList(pageNum, pageSize, 999, request);
    }

    //删除登陆日志
    @RequestMapping({"delLogin.do"})
    @ResponseBody
    public ServerResponse del(Integer id, HttpServletRequest request) {
        return this.iSiteLoginLogService.del(id, request);
    }

    //删除短信发送日志
    @RequestMapping({"delSms.do"})
    @ResponseBody
    public ServerResponse delSms(Integer id, HttpServletRequest request) {
        return this.iSiteSmsLogService.del(id, request);
    }

    //删除定时任务
    @RequestMapping({"delTask.do"})
    @ResponseBody
    public ServerResponse delTask(Integer id, HttpServletRequest request) {
        return this.iSiteTaskLogService.del(id, request);
    }

    //删除定时任务
    @RequestMapping({"delMessageList.do"})
    @ResponseBody
    public ServerResponse delMessageList(Integer id, HttpServletRequest request) {
        return this.iSiteMessageService.del(id, request);
    }




}

