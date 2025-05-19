import request from '@/utils/request'
import qs from 'qs'
const userApi = {
    getProductSetting: '/api/admin/getProductSetting.do', // 产品配置回显
    productupdate: '/admin/product/update.do', // 产品配置修改
    admingetSetting: '/api/admin/getSetting.do', // 股票风控配置回显
    setupdate: '/admin/set/update.do', // 股票风控配置修改
    admingetIndexSetting: '/api/admin/getIndexSetting.do', // 指数风控配置回显
    siteindexupdate: '/admin/site/index/update.do', // 指数风控配置修改
    admingetFuturesSetting: '/api/admin/getFuturesSetting.do', // 期货风控配置回显
    sitefuturesupdate: '/admin/site/futures/update.do', // 期货风控配置修改
    admingetSiteSpreadList: '/api/admin/getSiteSpreadList.do', // 获取点差列表
    adminaddSiteSpread: '/api/admin/addSiteSpread.do', // 添加点差
    adminupdateSiteSpread: '/api/admin/updateSiteSpread.do' // 修改点差
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

export function getProductSetting (parameter) {
    return request({
        url: userApi.getProductSetting,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function productupdate (parameter) {
    return request({
        url: userApi.productupdate,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function admingetSetting (parameter) {
    return request({
        url: userApi.admingetSetting,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function setupdate (parameter) {
    return request({
        url: userApi.setupdate,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function admingetIndexSetting (parameter) {
    return request({
        url: userApi.admingetIndexSetting,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function siteindexupdate (parameter) {
    return request({
        url: userApi.siteindexupdate,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function admingetFuturesSetting (parameter) {
    return request({
        url: userApi.admingetFuturesSetting,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function sitefuturesupdate (parameter) {
    return request({
        url: userApi.sitefuturesupdate,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function admingetSiteSpreadList (parameter) {
    return request({
        url: userApi.admingetSiteSpreadList,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function adminaddSiteSpread (parameter) {
    return request({
        url: userApi.adminaddSiteSpread,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function adminupdateSiteSpread (parameter) {
    return request({
        url: userApi.adminupdateSiteSpread,
        method: 'post',
        data: qs.stringify(parameter)
    })
}
