package com.xc.dao;
import com.xc.pojo.FundsSetting;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * funds_setting
 * @author lr
 * @date 2020/07/23
 */
@Mapper
@Repository
public interface FundsSettingMapper {

    /**
     * [新增]
     * @author lr
     * @date 2020/07/23
     **/
    int insert(FundsSetting fundsSetting);

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
    int update(FundsSetting fundsSetting);

    /**
     * [查询] 根据主键 id 查询
     * @author lr
     * @date 2020/07/23
     **/
    FundsSetting load(int id);

    /**
     * [查询] 分页查询
     * @author lr
     * @date 2020/07/23
     **/
    List<FundsSetting> pageList(int offset, int pagesize);

    /**
     * [查询] 分页查询 count
     * @author lr
     * @date 2020/07/23
     **/
    int pageListCount(int offset,int pagesize);

    /*查询所有数据*/
    List findAllFundsSetting();

}
