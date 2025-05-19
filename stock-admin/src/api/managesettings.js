import request from '@/utils/request'
import qs from 'qs'
const userApi = {
    adminlist: '/admin/list.do', // 管理列表
    adminupdateLock: '/admin/updateLock.do', // 管理锁定 解锁
    adminadd: '/admin/add.do', // 添加管理员
    adminupdate: '/admin/update.do' // 修改管理员密码
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
export function adminlist (parameter) {
    return request({
        url: userApi.adminlist,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function adminupdateLock (parameter) {
    return request({
        url: userApi.adminupdateLock,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function adminadd (parameter) {
    return request({
        url: userApi.adminadd,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function adminupdate (parameter) {
    return request({
        url: userApi.adminupdate,
        method: 'post',
        data: qs.stringify(parameter)
    })
}
