package com.xc.dao;
import com.xc.pojo.AgentAgencyFee;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * agent_agency_fee
 * @author gg
 * @date 2020/06/06
 */
@Mapper
@Repository
public interface AgentAgencyFeeMapper {

    /**
     * [新增]
     * @author gg
     * @date 2020/06/06
     **/
    int insert(AgentAgencyFee agentAgencyFee);

    /**
     * [刪除]
     * @author gg
     * @date 2020/06/06
     **/
    int delete(int id);

    /**
     * [更新]
     * @author gg
     * @date 2020/06/06
     **/
    int update(AgentAgencyFee agentAgencyFee);

    /**
     * [查询] 根据主键 id 查询
     * @author gg
     * @date 2020/06/06
     **/
    AgentAgencyFee load(int id);

    /**
     * [查询] 分页查询
     * @author gg
     * @date 2020/06/06
     **/
    List<AgentAgencyFee> pageList(int offset, int pagesize);

    /**
     * [查询] 分页查询 count
     * @author gg
     * @date 2020/06/06
     **/
    int pageListCount(int offset,int pagesize);

    List getAgentAgencyFeeList(Integer paramInteger);

}
