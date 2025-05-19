package com.xc.dao;

import com.xc.pojo.FundsDealerInstitutions;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * funds_dealer_institutions
 * @author lr
 * @date 2020/07/24
 */
@Mapper
@Repository
public interface FundsDealerInstitutionsMapper {

    /**
     * [新增]
     * @author lr
     * @date 2020/07/24
     **/
    int insert(FundsDealerInstitutions fundsDealerInstitutions);

    /**
     * [刪除]
     * @author lr
     * @date 2020/07/24
     **/
    int delete(int id);

    /**
     * [更新]
     * @author lr
     * @date 2020/07/24
     **/
    int update(FundsDealerInstitutions fundsDealerInstitutions);

    /**
     * [查询] 根据主键 id 查询
     * @author lr
     * @date 2020/07/24
     **/
    FundsDealerInstitutions load(int id);

    /**
     * [查询] 分页查询
     * @author lr
     * @date 2020/07/24
     **/
    List<FundsDealerInstitutions> pageList(@Param("pageNum") int pageNum,@Param("pageSize") int pageSize,@Param("keyword") String keyword, @Param("status") Integer status);

    /**
     * [查询] 分页查询 count
     * @author lr
     * @date 2020/07/24
     **/
    int pageListCount(int offset,int pagesize);

}
