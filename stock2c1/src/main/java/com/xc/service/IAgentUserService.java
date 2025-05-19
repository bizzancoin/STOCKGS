package com.xc.service;

import com.github.pagehelper.PageInfo;
import com.xc.common.ServerResponse;
import com.xc.pojo.AgentUser;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IAgentUserService {
  AgentUser getCurrentAgent(HttpServletRequest paramHttpServletRequest);

  AgentUser findByCode(String paramString);

  ServerResponse login(String paramString1, String paramString2, String paramString3, HttpServletRequest paramHttpServletRequest);

  ServerResponse getAgentInfo(HttpServletRequest paramHttpServletRequest);

  ServerResponse updatePwd(String paramString1, String paramString2, HttpServletRequest paramHttpServletRequest);

  ServerResponse addAgentUser(String paramString1, String paramString2, String paramString3, String paramString4, Integer parentId, String paramString5, String paramString6, String paramString7, HttpServletRequest paramHttpServletRequest);

  ServerResponse<PageInfo> getSecondAgent(int paramInt1, int paramInt2, HttpServletRequest paramHttpServletRequest);

  ServerResponse<PageInfo> listByAdmin(String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3, HttpServletRequest paramHttpServletRequest);

  ServerResponse add(AgentUser paramAgentUser, HttpServletRequest paramHttpServletRequest);

  ServerResponse update(AgentUser paramAgentUser);

  int CountAgentNum();

  List<AgentUser> getAgentSuperiorList(int agentId);

  ServerResponse updateAgentAmt(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3);
  ServerResponse delAgent(Integer agentId);
}
