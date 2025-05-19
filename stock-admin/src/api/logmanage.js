import request from '@/utils/request'
import qs from 'qs'
const userApi = {
    logloginList: '/admin/log/loginList.do', // 登录日志列表
    logtaskList: '/admin/log/taskList.do', // 定时任务列表
    logsmsList: '/admin/log/smsList.do', // 短信日志列表
    logmessageList: '/admin/log/messageList.do'
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

export function logloginList (parameter) {
    return request({
        url: userApi.logloginList,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function logtaskList (parameter) {
    return request({
        url: userApi.logtaskList,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function logsmsList (parameter) {
    return request({
        url: userApi.logsmsList,
        method: 'post',
        data: qs.stringify(parameter)
    })
}

export function logmessageList (parameter) {
    return request({
        url: userApi.logmessageList,
        method: 'post',
        data: qs.stringify(parameter)
    })
}
