package com.xc.dao;

import com.xc.pojo.SiteMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  站内消息
 * @author lr 2020-07-16
 */
public interface SiteMessageMapper {
    /**
     * [新增]
     * @author lr
     * @date 2020/07/16
     **/
    int insert(SiteMessage siteMessage);

    /**
     * [刪除]
     * @author lr
     * @date 2020/07/16
     **/
    int delete(int id);

    /**
     * [更新]
     * @author lr
     * @date 2020/07/16
     **/
    int update(SiteMessage siteMessage);

    /**
     * [查询] 根据主键 id 查询
     * @author lr
     * @date 2020/07/16
     **/
    SiteMessage load(int id);


    /*查询用户站内消息列表*/
    List getSiteMessageByUserIdList(@Param("userId") Integer userId);

    /**
     * [今天该类型的站内消息是否推送过]
     * @author lr
     * @date 2020/07/16
     **/
    int getIsDayCount(@Param("userId") Integer userId,@Param("typeName") String typeName);

    /**
     * [用户站内消息状态变已读]
     * @author lr
     * @date 2020/07/16
     **/
    int updateMessageStatus(@Param("userId") Integer userId);

    /**
     * [查询用户未读消息数]
     * @author lr
     * @date 2020/07/16
     **/
    int getUnreadCount(@Param("userId") Integer userId);

}
