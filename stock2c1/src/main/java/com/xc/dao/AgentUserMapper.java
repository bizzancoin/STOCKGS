package com.xc.dao;



import com.xc.pojo.AgentUser;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AgentUserMapper {
  int deleteByPrimaryKey(Integer paramInteger);
  
  int insert(AgentUser paramAgentUser);
  
  int insertSelective(AgentUser paramAgentUser);
  
  AgentUser selectByPrimaryKey(Integer paramInteger);
  
  int updateByPrimaryKeySelective(AgentUser paramAgentUser);
  
  int updateByPrimaryKey(AgentUser paramAgentUser);

  int updateTotalMoney(AgentUser paramAgentUser);

  AgentUser findByCode(String paramString);
  
  AgentUser findByPhone(String paramString);
  
  AgentUser findByName(String paramString);
  
  AgentUser login(@Param("agentPhone") String paramString1, @Param("agentPwd") String paramString2);
  
  List getSecondAgent(Integer paramInteger);
  
  List listByAdmin(@Param("realName") String paramString1, @Param("phone") String paramString2, @Param("id") int paramint1);
  
  int CountAgentNum();

  List getAgentSuperiorList(@Param("agentId") int agentId);

  AgentUser findAgentByAgentId(@Param("agentId") int agentId);
}