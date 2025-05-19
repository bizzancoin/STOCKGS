package com.xc.dao;

import com.xc.pojo.FundsLever;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * funds_lever
 * @author lr
 * @date 2020/07/23
 */
@Mapper
@Repository
public interface FundsLeverMapper {

    /**
     * [新增]
     * @author lr
     * @date 2020/07/23
     **/
    int insert(FundsLever fundsLever);

    /**
     * [刪除]
     * @author lr
     * @date 2020/07/23
     **/
    int delete(int id);

    /**
     * [更新]
     * @author lr
     * @date 2020/07/23
     **/
    int update(FundsLever fundsLever);

    /**
     * [查询] 根据主键 id 查询
     * @author lr
     * @date 2020/07/23
     **/
    FundsLever load(int id);

    /**
     * [查询] 分页查询
     * @author lr
     * @date 2020/07/23
     **/
    List<FundsLever> pageList(int pageNum, int pageSize);

    /**
     * [查询] 分页查询 count
     * @author lr
     * @date 2020/07/23
     **/
    int pageListCount(int offset,int pagesize);

    /**
     * [查询] 查询配资类型杠杆
     * @author lr
     * @date 2020/07/23
     **/
    List<FundsLever> getFundsTypeList(@Param("cycleType") Integer cycleType);

    /**
     * [查询] 查询杠杆费率
     * @author lr
     * @date 2020/07/23
     **/
    FundsLever getLeverRateInfo(@Param("cycleType") Integer cycleType,@Param("lever") Integer lever);

}
