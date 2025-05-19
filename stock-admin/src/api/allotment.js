import request from '@/utils/request'
import qs from 'qs'
const userApi = {
    getApplyList: '/admin/funds/apply/getApplyList.do', // 配资列表
    getFundsLeverList: '/admin/funds/getFundsLeverList.do', // 杠杆设置列表
    getDealerInstitutionsList: '/admin/funds/dealer/getDealerInstitutionsList.do', // 券商机构管理列表
    getSecuritiesInfoList: '/admin/funds/dealer/getSecuritiesInfoList.do', // 证券信息管理列表
    getTradingAccountList: '/admin/funds/dealer/getTradingAccountList.do', // 交易账户管理列表
    getAppendList: '/admin/funds/apply/getAppendList.do', // 配资资金管理  续期申请/记录等 4大状态
    getFundsSetting: '/admin/funds/getFundsSetting.do', // 配置设置
    saveFundsSetting: '/admin/funds/saveFundsSetting.do', // 配资配置审核
    fundsapplyauditApply: '/admin/funds/apply/auditApply.do', // 配资列表的配资审核
    fundssaveFundsLever: '/admin/funds/saveFundsLever.do', // 添加修改杠杆
    saveDealerInstitutions: '/admin/funds/dealer/saveDealerInstitutions.do', // 添加修改券商机构
    saveSecuritiesInfo: '/admin/funds/dealer/saveSecuritiesInfo.do', // 添加修改证券信息
    getSecuritiesEnabledList: '/admin/funds/dealer/getSecuritiesEnabledList.do', // 交易账户管理证券机构名列表
    saveTradingAccount: 'admin/funds/dealer/saveTradingAccount.do', // 添加修改交易账户
    dealergetMaxNumber: '/admin/funds/dealer/getMaxNumber.do', // 配资交易账户-查询最新交易账户编号
    applygetAppendDetail: '/admin/funds/apply/getAppendDetail.do', // 配资资金管理  申请列表查询详情
    applysaveAppendApply: '/admin/funds/apply/saveAppendApply.do' // 配资资金管理  申请列表查询保存
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
export function getApplyList (parameter) {
  return request({
    url: userApi.getApplyList,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function getFundsLeverList (parameter) {
  return request({
    url: userApi.getFundsLeverList,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function getDealerInstitutionsList (parameter) {
  return request({
    url: userApi.getDealerInstitutionsList,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function getSecuritiesInfoList (parameter) {
  return request({
    url: userApi.getSecuritiesInfoList,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function getTradingAccountList (parameter) {
  return request({
    url: userApi.getTradingAccountList,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function getAppendList (parameter) {
  return request({
    url: userApi.getAppendList,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function getFundsSetting (parameter) {
  return request({
    url: userApi.getFundsSetting,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function saveFundsSetting (parameter) {
  return request({
    url: userApi.saveFundsSetting,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function fundsapplyauditApply (parameter) {
  return request({
    url: userApi.fundsapplyauditApply,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function fundssaveFundsLever (parameter) {
  return request({
    url: userApi.fundssaveFundsLever,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function saveDealerInstitutions (parameter) {
  return request({
    url: userApi.saveDealerInstitutions,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function saveSecuritiesInfo (parameter) {
  return request({
    url: userApi.saveSecuritiesInfo,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function getSecuritiesEnabledList (parameter) {
  return request({
    url: userApi.getSecuritiesEnabledList,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function saveTradingAccount (parameter) {
  return request({
    url: userApi.saveTradingAccount,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function dealergetMaxNumber (parameter) {
  return request({
    url: userApi.dealergetMaxNumber,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function applygetAppendDetail (parameter) {
  return request({
    url: userApi.applygetAppendDetail,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function applysaveAppendApply (parameter) {
  return request({
    url: userApi.applysaveAppendApply,
    method: 'post',
    data: qs.stringify(parameter)
  })
}
