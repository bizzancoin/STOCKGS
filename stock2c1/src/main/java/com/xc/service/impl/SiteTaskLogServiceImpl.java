package com.xc.service.impl;


import com.github.pagehelper.PageHelper;

import com.github.pagehelper.PageInfo;

import com.xc.common.ServerResponse;

import com.xc.dao.SiteTaskLogMapper;

import com.xc.pojo.SiteTaskLog;

import com.xc.service.ISiteTaskLogService;

import java.util.List;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


@Service("iSiteTaskLogService")
public class SiteTaskLogServiceImpl implements ISiteTaskLogService {

    private static final Logger log = LoggerFactory.getLogger(SiteTaskLogServiceImpl.class);


    @Autowired
    SiteTaskLogMapper siteTaskLogMapper;


    public ServerResponse<PageInfo> taskList(String taskType, int pageNum, int pageSize) {

        PageHelper.startPage(pageNum, pageSize);


        List<SiteTaskLog> siteTaskLogs = this.siteTaskLogMapper.taskList(taskType);


        PageInfo pageInfo = new PageInfo(siteTaskLogs);


        return ServerResponse.createBySuccess(pageInfo);

    }

    public ServerResponse del(Integer id, HttpServletRequest request) {
        if (id == null) {
            return ServerResponse.createByErrorMsg("id不能为空");
        }

        int updateCount = this.siteTaskLogMapper.deleteByPrimaryKey(id);
        if (updateCount > 0) {
            return ServerResponse.createBySuccessMsg("删除成功");
        }
        return ServerResponse.createByErrorMsg("删除失败");
    }

}
