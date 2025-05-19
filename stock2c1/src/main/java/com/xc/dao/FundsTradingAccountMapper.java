package com.xc.dao;

import com.xc.pojo.FundsTradingAccount;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * 配资交易账户
 * @author lr
 * @date 2020/07/24
 */
@Mapper
@Repository
public interface FundsTradingAccountMapper {

    /**
     * [新增]
     * @author lr
     * @date 2020/07/24
     **/
    int insert(FundsTradingAccount fundsTradingAccount);

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
    int update(FundsTradingAccount fundsTradingAccount);

    /**
     * [查询] 根据主键 id 查询
     * @author lr
     * @date 2020/07/24
     **/
    FundsTradingAccount load(int id);

    /**
     * [查询] 分页查询 count
     * @author lr
     * @date 2020/07/24
     **/
    int pageListCount(int offset,int pagesize);

    /**
     * [查询] 分页查询
     * @author lr
     * @date 2020/07/24
     **/
    List<FundsTradingAccount> pageList(@Param("pageNum") int pageNum,@Param("pageSize") int pageSize,@Param("keyword") String keyword,@Param("status") Integer status);

    /**
     * [查询最新交易账户编号]
     * @author lr
     * @date 2020/07/24
     **/
    int getMaxNumber();

    /**
     * [查询] 根据子账户编号查询详细信息
     * @author lr
     * @date 2020/07/24
     **/
    FundsTradingAccount getAccountByNumber(@Param("subaccountNumber") Integer subaccountNumber);

}
