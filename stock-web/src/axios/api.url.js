
const ENV = process.env.NODE_ENV
export default {
  DOMAIN: 'https://apisto.bizzan.site/',
  baseURL: ENV == 'development'?'https://apisto.bizzan.site/':'https://apisto.bizzan.site/',
  // baseURL: 'http://localhost:8070',
  /* Util API */
  util: {
    image: '/util/image.html' // 图片上传
  }
}
// https://t.me/usdtvps666