import request from '@/utils/request'
import qs from 'qs'
const userApi = {
  agentcashlist: '/agent/cash/list.do', // 资金记录
  agentrechargelist: '/agent/recharge/list.do', // 充值记录
  agentwithdrawlist: '/agent/withdraw/list.do' // 出金记录
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

export function agentcashlist (parameter) {
  return request({
    url: userApi.agentcashlist,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function agentrechargelist (parameter) {
  return request({
    url: userApi.agentrechargelist,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function agentwithdrawlist (parameter) {
  return request({
    url: userApi.agentwithdrawlist,
    method: 'post',
    data: qs.stringify(parameter)
  })
}
