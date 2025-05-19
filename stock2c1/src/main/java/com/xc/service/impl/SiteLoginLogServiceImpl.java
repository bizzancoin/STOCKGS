package com.xc.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xc.common.ServerResponse;
import com.xc.dao.SiteLoginLogMapper;
import com.xc.pojo.SiteLoginLog;
import com.xc.pojo.User;
import com.xc.service.ISiteLoginLogService;
import com.xc.utils.ip.IpUtils;
import com.xc.utils.ip.JuheIpApi;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("iSiteLoginLogService")
public class SiteLoginLogServiceImpl
        implements ISiteLoginLogService {
    private static final Logger log = LoggerFactory.getLogger(SiteLoginLogServiceImpl.class);


    @Autowired
    SiteLoginLogMapper siteLoginLogMapper;


    public ServerResponse saveLog(User user, HttpServletRequest request) {
        SiteLoginLog siteLoginLog = new SiteLoginLog();
        siteLoginLog.setUserId(user.getId());
        siteLoginLog.setUserName(user.getRealName());
        String ips = IpUtils.getIp(request);

        siteLoginLog.setLoginIp(ips);
        String ip_address = JuheIpApi.ip2Add(ips);
        siteLoginLog.setLoginAddress(ip_address);
        siteLoginLog.setAddTime(new Date());
        this.siteLoginLogMapper.insert(siteLoginLog);

        return ServerResponse.createBySuccess();
    }


    public ServerResponse<PageInfo> loginList(Integer userId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SiteLoginLog> siteLoginLogs = this.siteLoginLogMapper.loginList(userId);
        PageInfo pageInfo = new PageInfo(siteLoginLogs);
        return ServerResponse.createBySuccess(pageInfo);
    }


    public int deleteByUserId(Integer userId) {
        return this.siteLoginLogMapper.deleteByUserId(userId);
    }

    public ServerResponse del(Integer id, HttpServletRequest request) {
        if (id == null) {
            return ServerResponse.createByErrorMsg("id不能为空");
        }

        int updateCount = this.siteLoginLogMapper.deleteByPrimaryKey(id);
        if (updateCount > 0) {
            return ServerResponse.createBySuccessMsg("删除成功");
        }
        return ServerResponse.createByErrorMsg("删除失败");
    }
}
