package com.xc.dao;

import com.xc.pojo.FundsAppend;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * 配资追加申请
 * @author lr
 * @date 2020/08/01
 */
@Mapper
@Repository
public interface FundsAppendMapper {

    /**
     * [新增]
     * @author lr
     * @date 2020/08/01
     **/
    int insert(FundsAppend fundsAppend);

    /**
     * [刪除]
     * @author lr
     * @date 2020/08/01
     **/
    int delete(int id);

    /**
     * [更新]
     * @author lr
     * @date 2020/08/01
     **/
    int update(FundsAppend fundsAppend);

    /**
     * [查询] 根据主键 id 查询
     * @author lr
     * @date 2020/08/01
     **/
    FundsAppend load(int id);


    /**
     * [查询] 分页查询
     * @author lr
     * @date 2020/07/24
     **/
    List<FundsAppend> pageList(@Param("pageNum") int pageNum,@Param("pageSize") int pageSize,@Param("keyword") String keyword,@Param("status") Integer status,@Param("userId") Integer userId,@Param("appendType") Integer appendType);


    /**
     * [查询] 分页查询 count
     * @author lr
     * @date 2020/08/01
     **/
    int pageListCount(int offset,int pagesize);

    /**
     * [查询] 根据子账户查询是否申请终止
     * @author lr
     * @date 2020/08/01
     **/
    int isAppendEnd(@Param("applyId") Integer applyId);

}
