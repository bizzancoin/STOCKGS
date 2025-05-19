import request from '@/utils/request'
import qs from 'qs'
const userApi = {
    positionlist: '/admin/position/list.do', // 融资列表
    indexpositionlist: '/admin/index/position/list.do', // 指数列表
    futurespositionlist: '/admin/futures/position/list.do', // 期货列表
    positionlock: '/admin/position/lock.do', // 融资锁仓 解锁
    positionsell: '/admin/position/sell.do', // 融资强制平仓
    positiondel: '/admin/position/del.do', //  融资平仓删除
    indexpositionlock: '/admin/index/position/lock.do', // 指数锁仓 解锁
    indexpositionsell: '/admin/index/position/sell.do', // 指数强制平仓
    indexpositiondel: '/admin/index/position/del.do', // 指数平仓删除
    futurespositionlock: '/admin/futures/position/lock.do', // 指数锁仓 解锁
    futurespositionsell: '/admin/futures/position/sell.do', // 指数强制平仓
    futurespositiondel: '/admin/futures/position/del.do', // 指数平仓删除
    userdetail: '/admin/user/detail.do', // 创建融资持仓 单个用户详情搜索
    stockgetSingleStock: '/api/stock/getSingleStock.do', // 创建融资持仓 获取单个股票信息
    positioncreate: '/admin/position/create.do' // 创建融资持仓
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
export function positionlist (parameter) {
    return request({
        url: userApi.positionlist,
        method: 'post',
        data: qs.stringify(parameter)
    })
}
export function indexpositionlist (parameter) {
    return request({
        url: userApi.indexpositionlist,
        method: 'post',
        data: qs.stringify(parameter)
    })
}
export function futurespositionlist (parameter) {
    return request({
        url: userApi.futurespositionlist,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function positionlock (parameter) {
    return request({
        url: userApi.positionlock,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function positionsell (parameter) {
    return request({
        url: userApi.positionsell,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function positiondel (parameter) {
    return request({
        url: userApi.positiondel,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function indexpositionlock (parameter) {
    return request({
        url: userApi.indexpositionlock,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function indexpositionsell (parameter) {
    return request({
        url: userApi.indexpositionsell,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function indexpositiondel (parameter) {
    return request({
        url: userApi.indexpositiondel,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function futurespositionlock (parameter) {
    return request({
        url: userApi.futurespositionlock,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function futurespositionsell (parameter) {
    return request({
        url: userApi.futurespositionsell,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function futurespositiondel (parameter) {
    return request({
        url: userApi.futurespositiondel,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function userdetail (parameter) {
    return request({
        url: userApi.userdetail,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function stockgetSingleStock (parameter) {
    return request({
        url: userApi.stockgetSingleStock,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function positioncreate (parameter) {
    return request({
        url: userApi.positioncreate,
        method: 'post',
        data: qs.stringify(parameter)
    })
}
