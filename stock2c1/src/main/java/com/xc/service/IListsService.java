package com.xc.service;

import com.github.pagehelper.PageInfo;
import com.xc.common.ServerResponse;
import com.xc.pojo.Lists;
import com.xc.pojo.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 新股申购列表处理
 */
public interface IListsService {
    /**
     * 新股申购申请
     * @param newStockApply
     * @return
     */
     Lists save(Lists newStockApply);

    /**
     * 新股申购列表更新
     * @param newStockApply
     * @return
     */
    void update(Lists newStockApply);

    /**
     * 根据id删除
     * @param lists
     */
    void deleteById(Lists lists);

    /**
     * 根据关键词查询
     * @return
     */
    PageInfo listByQueryWordsByPage(String agent, String zname, String phone, String xgname, String codes, String bzj, Short zts, String mrsj_start, String mrsj_end, String nums, Integer pageNo, Integer pageSize);


    PageInfo listAgentByQueryWordsByPage(String agent, String zname, String phone, String xgname, String codes, String bzj, Short zts, String mrsj_start, String mrsj_end, String nums, Integer pageNo, Integer pageSize, HttpServletRequest request);

    /**
     * 根据id查询列表
     * @param lists
     * @return
     */
    Lists getById(Lists lists);


    List<Lists> getMyWinNewStockByPhone(String phone);

    /**
     * 申请审核
     * @param lists
     */
    void applyVerify(Lists lists);


    /**
     * 获取我的申购列表
     * @param phone
     * @return
     */
    List<Lists> getMyApplyList(String phone);

    //获取新股根据代码和状态
    List<Lists> getNewStockByCodeAndZts(String code,Short zts);


    /**
     * 完成中签任务缴费和并加入到持仓功能
     * @param listsId
     * @return
     */
    ServerResponse doFinshWinNewStock(Integer listsId, User user) throws Exception;
}
