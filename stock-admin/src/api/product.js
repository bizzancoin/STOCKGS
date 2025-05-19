import request from '@/utils/request'
import qs from 'qs'
const userApi = {
    stocklist: '/admin/stock/list.do', // 股票产品列表
    updateShow: '/admin/stock/updateShow.do', // 股票产品列表 显示隐藏
    updateLock: '/admin/stock/updateLock.do', // 股票产品列表 锁定解锁
    indexlist: '/admin/index/list.do', // 指数产品列表
    coinlist: '/admin/coin/list.do', // 基础货币列表
    futureslist: '/admin/futures/list.do', // 期货产品列表
    stockadd: '/admin/stock/add.do', // 添加股票产品
    stockupdateStock: '/admin/stock/updateStock.do', // 修改股票产品
    indexaddIndex: '/admin/index/addIndex.do', // 添加指数产品
    querySingleIndex: '/api/index/querySingleIndex.do', // 检查指数股票代码是否正确
    indexupdateIndex: '/admin/index/updateIndex.do', // 修改指数产品
    coinadd: '/admin/coin/add.do', // 添加基础货币
    coinupdate: '/admin/coin/update.do', // 修改基础货币
    coingetSelectCoin: '/admin/coin/getSelectCoin.do', // 期货产品币种
    futuresadd: '/admin/futures/add.do', // 添加期货产品
    futuresupdate: '/admin/futures/update.do'// 修改期货产品
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
export function stocklist (parameter) {
    return request({
        url: userApi.stocklist,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function updateShow (parameter) {
    return request({
        url: userApi.updateShow,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function updateLock (parameter) {
    return request({
        url: userApi.updateLock,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function indexlist (parameter) {
    return request({
        url: userApi.indexlist,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function coinlist (parameter) {
    return request({
        url: userApi.coinlist,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function futureslist (parameter) {
    return request({
        url: userApi.futureslist,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function stockadd (parameter) {
    return request({
        url: userApi.stockadd,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function stockupdateStock (parameter) {
    return request({
        url: userApi.stockupdateStock,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function indexaddIndex (parameter) {
    return request({
        url: userApi.indexaddIndex,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function querySingleIndex (parameter) {
    return request({
        url: userApi.querySingleIndex,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function indexupdateIndex (parameter) {
    return request({
        url: userApi.indexupdateIndex,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function coinadd (parameter) {
    return request({
        url: userApi.coinadd,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function coinupdate (parameter) {
    return request({
        url: userApi.coinupdate,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function coingetSelectCoin (parameter) {
    return request({
        url: userApi.coingetSelectCoin,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function futuresadd (parameter) {
    return request({
        url: userApi.futuresadd,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function futuresupdate (parameter) {
    return request({
        url: userApi.futuresupdate,
        method: 'post',
        data: qs.stringify(parameter)
    })
}
