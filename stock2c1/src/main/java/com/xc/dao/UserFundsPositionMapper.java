package com.xc.dao;

import com.xc.pojo.UserFundsPosition;
import com.xc.pojo.UserFuturesPosition;
import com.xc.pojo.UserPosition;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * user_funds_position
 * @author lr
 * @date 2020/07/27
 */
@Mapper
@Repository
public interface UserFundsPositionMapper {

    /**
     * [新增]
     * @author lr
     * @date 2020/07/27
     **/
    int insert(UserFundsPosition userFundsPosition);

    /**
     * [刪除]
     * @author lr
     * @date 2020/07/27
     **/
    int delete(int id);

    /**
     * [更新]
     * @author lr
     * @date 2020/07/27
     **/
    int update(UserFundsPosition userFundsPosition);

    /**
     * [查询] 根据主键 id 查询
     * @author lr
     * @date 2020/07/27
     **/
    UserFundsPosition load(int id);

    /**
     * [查询] 分页查询
     * @author lr
     * @date 2020/07/24
     **/
    List<UserFundsPosition> pageList(@Param("pageNum") int pageNum,@Param("pageSize") int pageSize,@Param("keyword") String keyword);


    /**
     * [查询] 分页查询 count
     * @author lr
     * @date 2020/07/27
     **/
    int pageListCount(int offset,int pagesize);

    /*
     * 分仓交易-查询所有平仓/持仓信息
     * */
    List findMyPositionByCodeAndSpell(@Param("uid") Integer paramInteger1, @Param("stockCode") String paramString1, @Param("stockSpell") String paramString2, @Param("state") Integer paramInteger2);

    /*
    * 根据单号查询配资信息
    * */
    UserFundsPosition findPositionBySn(String paramString);

    UserFundsPosition findUserFundsPositionByCode(@Param("userId") Integer paramInteger, @Param("fundsCode") String fundsCode);

}
