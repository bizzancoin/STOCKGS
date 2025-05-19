import request from '@/utils/request'
import qs from 'qs'
const userApi = {
    artlist: '/admin/art/list.do', // 公告列表
    adminupload: '/admin/upload.do', // 上传图片
    artadd: '/admin/art/add.do', // 添加公告
    artupdate: '/admin/art/update.do', // 修改公告
    artdelArt: '/admin/art/delArt.do', // 删除公告
    bannerslist: '/admin/banners/list.do', // banner列表
    bannersupdate: '/admin/banners/update.do', // 修改轮播图
    bannersadd: '/admin/banners/add.do', // 添加轮播图
    bannersdelete: '/admin/banners/delete.do', // 删除轮播图
    paylist: '/admin/pay/list.do', // 支付渠道列表
    paydel: '/admin/pay/del.do', // 删除支付渠道
    payadd: '/admin/pay/add.do', // 添加支付渠道
    payupdate: '/admin/pay/update.do', // 修改支付渠道
    sitegetInfo: '/api/site/getInfo.do', // 系统设置
    infoupdate: '/admin/info/update.do' // 修改系统设置
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
export function artlist (parameter) {
    return request({
        url: userApi.artlist,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function adminupload (parameter) {
    return request({
        url: userApi.adminupload,
        method: 'post',
        data: parameter
    })
}

export function artadd (parameter) {
    return request({
        url: userApi.artadd,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function artupdate (parameter) {
    return request({
        url: userApi.artupdate,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function artdelArt (parameter) {
    return request({
        url: userApi.artdelArt,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function bannerslist (parameter) {
    return request({
        url: userApi.bannerslist,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function bannersupdate (parameter) {
    return request({
        url: userApi.bannersupdate,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function bannersadd (parameter) {
    return request({
        url: userApi.bannersadd,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function bannersdelete (parameter) {
    return request({
        url: userApi.bannersdelete,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function paylist (parameter) {
    return request({
        url: userApi.paylist,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function paydel (parameter) {
    return request({
        url: userApi.paydel,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function payadd (parameter) {
    return request({
        url: userApi.payadd,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function payupdate (parameter) {
    return request({
        url: userApi.payupdate,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function sitegetInfo (parameter) {
    return request({
        url: userApi.sitegetInfo,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function infoupdate (parameter) {
    return request({
        url: userApi.infoupdate,
        method: 'post',
        data: qs.stringify(parameter)
    })
}
