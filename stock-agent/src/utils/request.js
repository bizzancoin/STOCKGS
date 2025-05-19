import axios from 'axios'
import store from '@/store'
import storage from 'store'
import notification from 'ant-design-vue/es/notification'
import { VueAxios } from './axios'
import { ACCESS_TOKEN } from '@/store/mutation-types'

// 创建 axios 实例
const request = axios.create({
  // API 请求的默认前缀
  baseURL: process.env.VUE_APP_API_BASE_URL,
  // content-type: application/x-www-form-urlencoded
  headers: {
    'Content-Type': 'application/x-www-form-urlencoded'
  },
  timeout: 6000 // 请求超时时间
})

// 异常拦截处理器
const errorHandler = (error) => {
  if (error.response) {
    const data = error.response.data
    // 从 localstorage 获取 token
    const token = storage.get(ACCESS_TOKEN)
    if (error.response.status === 403) {
      notification.error({
        message: 'Forbidden',
        description: data.msg
      })
    }
    if (error.response.status === 401 && !(data.result && data.result.isLogin)) {
      notification.error({
        message: 'Unauthorized',
        description: 'Authorization verification failed'
      })
      if (token) {
        store.dispatch('Logout').then(() => {
          setTimeout(() => {
            window.location.reload()
          }, 1500)
        })
      }
    }
  }
  return Promise.reject(error)
}

// request interceptor
request.interceptors.request.use(config => {
  const token = storage.get(ACCESS_TOKEN)
  // 如果 token 存在
  // 让每个请求携带自定义 token 请根据实际情况自行修改
  if (token) {
    config.headers['agenttoken'] = token
  }
  return config
}, errorHandler)

// response interceptor
// request.interceptors.response.use((response) => {
//   return response.data
// }, errorHandler)
request.interceptors.response.use(async (response) => {
  // 克隆响应对象做解析处理
  // 这里的res就是我们请求到的数据
  const res = await response
  const { code, msg } = res.data
  if (msg == '請先登錄，無權限訪問agent' || msg == '請先登錄') {
    // messageUtils.error('未登录或登录过期，请重新登录', 10)
    notification.error({
      message: '重新登陆',
      description: '未登录或登录过期，请重新登录'
    })
    store.dispatch('Logout').then(() => {
      setTimeout(() => {
        window.localStorage.clear()
        window.location.reload()
      }, 1500)
    })
  } else if (!res.data) {
    notification.error({
      message: '网络错误',
      description: '网络错误，请稍后刷新页面重试！'
    })
  }
  return res.data
})

const installer = {
  vm: {},
  install (Vue) {
    Vue.use(VueAxios, request)
  }
}

export default request

export {
  installer as VueAxios,
  request as axios
}
