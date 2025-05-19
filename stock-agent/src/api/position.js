import request from '@/utils/request'
import qs from 'qs'
const userApi = {
    positionlist: '/agent/position/list.do', // 融资列表
    indexpositionlist: '/agent/index/position/list.do', // 指数列表
    futurespositionlist: '/agent/futures/position/list.do' // 期货列表
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
