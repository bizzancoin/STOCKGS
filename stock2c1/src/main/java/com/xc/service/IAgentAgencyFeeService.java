package com.xc.service;

import com.github.pagehelper.PageInfo;
import com.xc.common.ServerResponse;
import com.xc.pojo.AgentAgencyFee;
import com.xc.pojo.AgentUser;

import javax.servlet.http.HttpServletRequest;

/**
 * @author gg
 * @date 2020/06/06
 */
public interface IAgentAgencyFeeService {
    /**
     * 新增
     */
    int insert(AgentAgencyFee agentAgencyFee);


    /**
     * 更新
     */
    int update(AgentAgencyFee agentAgencyFee);

    /**
     * 代理费收入，考虑多级代理的问题
     */
    int AgencyFeeIncome(int feeType,String positionSn);

    /*查询代理利润明细列表*/
    ServerResponse<PageInfo> getAgentAgencyFeeList(int pageNum, int pageSize, HttpServletRequest request);

    /*查询代理登录信息*/
    AgentUser getCurrentAgent(HttpServletRequest request);

}
