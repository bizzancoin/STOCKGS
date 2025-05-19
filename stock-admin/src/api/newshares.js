import request from '@/utils/request'
import qs from 'qs'
const userApi = {
  subscribelist: '/admin/subscribe/list.do', // 新股列表
  subscribeadd: '/admin/subscribe/add.do', // 新股添加
  subscribeupdate: '/admin/subscribe/update.do', // 新股修改
  subscribedel: '/admin/subscribe/del.do', // 新股删除
  getStockSubscribeList: 'admin/subscribe/getStockSubscribeList.do', // 新股记录列表
  saveStockSubscribe: '/admin/subscribe/saveStockSubscribe.do', // 新股记录添加 修改
  delStockSubscribe: 'admin/subscribe/delStockSubscribe.do', // 新股记录删除
  addUserPosition: '/admin/position/addUserPosition.do', // 新股转持仓
  getStockSubscribeQcListByAdmin: '/admin/subscribe/getStockSubscribeQcListByAdmin.do', // 新股抢筹记录列表
  addStockSubscribeQcByAdmin: '/admin/subscribe/addStockSubscribeQcByAdmin.do', // 新股抢筹记录添加
  updateStockSubscribeQcByAdmin: 'admin/subscribe/updateStockSubscribeQcByAdmin.do', // 新股抢筹记录修改
  getDzListByAdmin: '/admin/stockDz/getDzListByAdmin.do', // 大宗交易列表
  addByAdmin: '/admin/stockDz/addByAdmin.do', // 大宗交易添加
  updateByAdmin: '/admin/stockDz/updateByAdmin.do', // 大宗交易修改
  deleteByAdmin: '/admin/stockDz/deleteByAdmin.do' // 大宗交易删除
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

export function subscribelist (parameter) {
  return request({
    url: userApi.subscribelist,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function subscribeadd (parameter) {
  return request({
    url: userApi.subscribeadd,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function subscribeupdate (parameter) {
  return request({
    url: userApi.subscribeupdate,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function subscribedel (parameter) {
  return request({
    url: userApi.subscribedel,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function getStockSubscribeList (parameter) {
  return request({
    url: userApi.getStockSubscribeList,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function saveStockSubscribe (parameter) {
  return request({
    url: userApi.saveStockSubscribe,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function delStockSubscribe (parameter) {
  return request({
    url: userApi.delStockSubscribe,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function addUserPosition (parameter) {
  return request({
    url: userApi.addUserPosition,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function getStockSubscribeQcListByAdmin (parameter) {
  return request({
    url: userApi.getStockSubscribeQcListByAdmin,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function addStockSubscribeQcByAdmin (parameter) {
  return request({
    url: userApi.addStockSubscribeQcByAdmin,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function updateStockSubscribeQcByAdmin (parameter) {
  return request({
    url: userApi.updateStockSubscribeQcByAdmin,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function getDzListByAdmin (parameter) {
  return request({
    url: userApi.getDzListByAdmin,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function addByAdmin (parameter) {
  return request({
    url: userApi.addByAdmin,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function updateByAdmin (parameter) {
  return request({
    url: userApi.updateByAdmin,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function deleteByAdmin (parameter) {
  return request({
    url: userApi.deleteByAdmin,
    method: 'post',
    data: qs.stringify(parameter)
  })
}
