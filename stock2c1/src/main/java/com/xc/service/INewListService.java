package com.xc.service;

import com.github.pagehelper.PageInfo;
import com.xc.pojo.NewList;

import java.util.List;

/**
 * 新股发行接口
 */
public interface INewListService {

     NewList save(NewList newList);
     void update(NewList newList);

    /**
     * 通过股票代码查询新股票信息
     * @param code
     * @return
     */
     NewList getByCode(String code);

    /**
     * 获取即将到达申购日期的股票
     * @return
     */
     List<NewList> getUpcomingSubscription();

    /**
     * 获取今日申购的股票
     * @return
     */
     List<NewList> getCurrentDayStockList();

    /**
     * 获取今日上市的股票
     * @return
     */
     List<NewList> getNewShowStockList();

    /**
     * 获取公布今日中签的新股
     * @return
     */
    List<NewList> getWinStockList();

    /**
     * 获取新股数据
     */
    void  getNewStockListTask() throws Exception;

    /**
     * 根据查询条件获取查询数据
     * @param names
     * @param code
     * @param price
     * @param zt
     * @return
     */
    PageInfo listByNewList(String names, String code, String price, Byte zt, Integer pageNo, Integer pageSize);

    void deleteById(Integer id);
}
