package com.xc.dao;

import com.xc.pojo.FundsApply;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * funds_apply
 * @author lr
 * @date 2020/07/25
 */
@Mapper
@Repository
public interface FundsApplyMapper {

    /**
     * [新增]
     * @author lr
     * @date 2020/07/25
     **/
    int insert(FundsApply fundsApply);

    /**
     * [刪除]
     * @author lr
     * @date 2020/07/25
     **/
    int delete(int id);

    /**
     * [更新]
     * @author lr
     * @date 2020/07/25
     **/
    int update(FundsApply fundsApply);

    /**
     * [查询] 根据主键 id 查询
     * @author lr
     * @date 2020/07/25
     **/
    FundsApply load(int id);

    /**
     * [查询] 分页查询
     * @author lr
     * @date 2020/07/24
     **/
    List<FundsApply> pageList(@Param("pageNum") int pageNum,@Param("pageSize") int pageSize,@Param("keyword") String keyword,@Param("status") Integer status);


    /**
     * [查询] 分页查询 count
     * @author lr
     * @date 2020/07/25
     **/
    int pageListCount(int offset,int pagesize);

    /**
     * [查询] 用户配资列表
     * @author lr
     * @date 2020/07/27
     **/
    List<FundsApply> getUserApplyList(@Param("pageNum") int pageNum,@Param("pageSize") int pageSize,@Param("userId") int userId);

    /**
     * [查询] 配资申请-用户操盘中子账户
     * @author lr
     * @date 2020/07/27
     **/
    List<FundsApply> getUserEnabledSubaccount(@Param("userId") int userId);

    /**
     * [查询] 配资申请-查询用户分仓保证金
     * @author lr
     * @date 2020/07/31
     **/
    List<FundsApply> getUserMarginList(@Param("userId") int userId);

}
