import request from '@/utils/request'
import qs from 'qs'
const userApi = {
  countdata: '/admin/count.do', // 首页统计类
  usermanag: '/admin/user/list.do', // 用户列表
  useraddSimulatedAccount: '/admin/user/addSimulatedAccount.do', // 添加真实/模拟用户
  userupdate: '/admin/user/update.do', // 修改用户信息
  usergetBank: '/admin/user/getBank.do', // 获取用户银行卡信息
  userupdateBank: 'admin/user/updateBank.do', // 修改用户银行卡信息
  userupdateAmt: '/admin/user/updateAmt.do', // 修改用户资金
  userauthByAdmin: '/admin/user/authByAdmin.do', // 用户实名认证审核
  userdelete: '/admin/user/delete.do', // 删除用户

  nextagent: '/admin/agent/list.do', // 代理列表
  agentupdateAgentAmt: '/admin/agent/updateAgentAmt.do', // 修改代理资金
  agentdelAgent: '/admin/agent/delAgent.do', // 删除代理
  agentupdate: '/admin/agent/update.do', // 修改代理信息
  agentadd: '/admin/agent/add.do', // 添加下级代理
  stockgetMarket: '/api/stock/getMarket.do', // 获取大盘指数行情
  adminsetSiteStyle: '/api/admin/setSiteStyle.do', // 设置网站风格
  admingetSiteStyle: '/api/admin/getSiteStyle.do' // 获取网站风格
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
export function countdata (parameter) {
  return request({
    url: userApi.countdata,
    method: 'get',
    param: parameter
  })
}

export function usermanag (parameter) {
  return request({
    url: userApi.usermanag,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function nextagent (parameter) {
  return request({
    url: userApi.nextagent,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function useraddSimulatedAccount (parameter) {
  return request({
    url: userApi.useraddSimulatedAccount,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function userupdate (parameter) {
  return request({
    url: userApi.userupdate,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function usergetBank (parameter) {
  return request({
    url: userApi.usergetBank,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function userupdateBank (parameter) {
  return request({
    url: userApi.userupdateBank,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function userupdateAmt (parameter) {
  return request({
    url: userApi.userupdateAmt,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function userauthByAdmin (parameter) {
  return request({
    url: userApi.userauthByAdmin,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function userdelete (parameter) {
  return request({
    url: userApi.userdelete,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function agentupdateAgentAmt (parameter) {
  return request({
    url: userApi.agentupdateAgentAmt,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function agentdelAgent (parameter) {
  return request({
    url: userApi.agentdelAgent,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function agentupdate (parameter) {
  return request({
    url: userApi.agentupdate,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function agentadd (parameter) {
  return request({
    url: userApi.agentadd,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function stockgetMarket (parameter) {
  return request({
    url: userApi.stockgetMarket,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function adminsetSiteStyle (parameter) {
  return request({
    url: userApi.adminsetSiteStyle,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function admingetSiteStyle (parameter) {
  return request({
    url: userApi.admingetSiteStyle,
    method: 'post',
    data: qs.stringify(parameter)
  })
}
