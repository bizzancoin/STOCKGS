import request from '@/utils/request'
import qs from 'qs'
const userApi = {
    rechargelist: '/admin/recharge/list.do', // 充值列表
    rechargedel: '/admin/recharge/del.do', // 充值列表删除
    rechargeupdateState: '/admin/recharge/updateState.do', // 充值列表修改状态
    rechargecreateOrder: '/admin/recharge/createOrder.do', // 新增充值订单
    rechargeexport: '/admin/recharge/export.do', // 充值订单导出
    withdrawlist: '/admin/withdraw/list.do', // 提现列表
    withdrawupdateState: 'admin/withdraw/updateState.do', // 提现列表修改状态
    withdrawexport: '/admin/withdraw/export.do', // 提现列表导出
    cashlist: '/admin/cash/list.do', // 资金记录
    logtransList: '/admin/log/transList.do' // 资金互转记录
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

export function rechargelist (parameter) {
  return request({
    url: userApi.rechargelist,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function rechargedel (parameter) {
  return request({
    url: userApi.rechargedel,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function rechargeupdateState (parameter) {
  return request({
    url: userApi.rechargeupdateState,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function rechargecreateOrder (parameter) {
  return request({
    url: userApi.rechargecreateOrder,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function rechargeexport (parameter) {
  return request({
    url: userApi.rechargeexport,
    method: 'post',
    responseType: 'blob',
    data: qs.stringify(parameter)
  })
}

export function withdrawlist (parameter) {
  return request({
    url: userApi.withdrawlist,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function withdrawupdateState (parameter) {
  return request({
    url: userApi.withdrawupdateState,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function withdrawexport (parameter) {
  return request({
    url: userApi.withdrawexport,
    method: 'post',
    responseType: 'blob',
    data: qs.stringify(parameter)
  })
}

export function cashlist (parameter) {
  return request({
    url: userApi.cashlist,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function logtransList (parameter) {
  return request({
    url: userApi.logtransList,
    method: 'post',
    data: qs.stringify(parameter)
  })
}
