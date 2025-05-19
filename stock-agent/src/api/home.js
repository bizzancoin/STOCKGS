import request from '@/utils/request'
import qs from 'qs'
const userApi = {
  agentuserlist: '/agent/user/list.do', // 用户列表
  agentgetSecondAgent: '/agent/getSecondAgent.do', // 代理列表
  agentaddSimulatedAccount: '/agent/addSimulatedAccount.do', // 添加真实/模拟用户
  agentaddAgent: '/agent/addAgent.do', // 添加下级代理
  agentgetAgentInfo: '/agent/getAgentInfo.do', // 获取代理信息
  agentgetAgentAgencyFeeList: '/agent/getAgentAgencyFeeList.do', // 获取代理利润明细列表
  agentupdatePwd: '/agent/updatePwd.do' // 修改密码
 }

/**
 * login func
 * parameter: {
 *     username: '',
 *     password: '',
 *     remember_me: true,
 *     captcha: '12345'
 * }
 * @param parameter
 * @returns {*}
 */

export function agentuserlist (parameter) {
  return request({
    url: userApi.agentuserlist,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function agentgetSecondAgent (parameter) {
  return request({
    url: userApi.agentgetSecondAgent,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function agentaddSimulatedAccount (parameter) {
  return request({
    url: userApi.agentaddSimulatedAccount,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function agentaddAgent (parameter) {
  return request({
    url: userApi.agentaddAgent,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function agentgetAgentInfo (parameter) {
  return request({
    url: userApi.agentgetAgentInfo,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function agentgetAgentAgencyFeeList (parameter) {
  return request({
    url: userApi.agentgetAgentAgencyFeeList,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function agentupdatePwd (parameter) {
  return request({
    url: userApi.agentupdatePwd,
    method: 'post',
    data: qs.stringify(parameter)
  })
}
