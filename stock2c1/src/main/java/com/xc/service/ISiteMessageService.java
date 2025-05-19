package com.xc.service;

import com.github.pagehelper.PageInfo;
import com.xc.common.ServerResponse;
import com.xc.pojo.SiteMessage;

import javax.servlet.http.HttpServletRequest;

/**
 * 站内消息
 * @author lr
 * @date 2020/07/16
 */
public interface ISiteMessageService {
    /**
     * 新增
     */
    int insert(SiteMessage siteMessage);

    /**
     * 删除
     */
    int delete(int id);

    /**
     * 更新
     */
    int update(SiteMessage siteMessage);


    /*查询用户站内消息列表*/
    ServerResponse<PageInfo> getSiteMessageByUserIdList(int pageNum, int pageSize, int userId, HttpServletRequest request);

    /**
     * 今天该类型的站内消息是否推送过
     */
    int getIsDayCount(Integer userId, String typeName);

    /**
     * [用户站内消息状态变已读]
     * @author lr
     * @date 2020/07/16
     **/
    int updateMessageStatus(HttpServletRequest request);

    /**
     * [查询用户未读消息数]
     * @author lr
     * @date 2020/07/16
     **/
    int getUnreadCount(HttpServletRequest request);

    ServerResponse del(Integer id, HttpServletRequest request);

}
