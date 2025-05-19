import { post, get } from '@/axios/index'
// import APIUrl from '@/axios/api.url'

// var img_url = APIUrl.util.image // 这个就是图片上传的api url


// 龙虎榜
export function stockgetlhb (options) {
  return get('/api/stock/getlhb.do', options)
}
// 每日停复牌
export function stockgetStop (options) {
  return get('/api/stock/getStop.do', options)
}
// 十大成交股
export function stockgetTop (options) {
  return get('/api/stock/getTop.do', options)
}
// 首页沪深涨停条
export function stockgetZdfNumber (options) {
  return post('/api/stock/getZdfNumber.do', options)
}
// 每日涨停
export function stockgetztb (options) {
  return post('/api/stock/getztb.do', options)
}
export function getMinK_Echarts (options) {
  return post('/api/stock/getMinK_Echarts.do   ', options)
}



// 就可以使用 post 和 get 了
export function queryIndexNews (options) {
  return post('/api/index/queryIndexNews.do', options)
}
// 获取产品配置信息
export function getProductSetting (options) {
  return post('/api/admin/getProductSetting.do', options)
}

export function submitSubscribe (options) {
  return post('/user/submitSubscribe.do', options)
}
//大宗交易记录
export function buyStockDzList (options) {
  return post('/user/buyStockDzList.do', options)
}
// 登录
export function login (options) {
  return post('/api/user/login.do', options)
}

// 注册
export function register (options) {
  return post('/api/user/reg.do', options)
}

// 注销登录
export function logout (options) {
  return post('/api/user/logout.do', options)
}

// 验证是否注册
export function checkPhone (options) {
  return post('/api/user/checkPhone.do', options)
}

// 更改密码 -- 忘记密码
export function forgetPas (options) {
  return get('/api/user/updatePwd.do', options)
}

// 修改密码
export function changePassword (options) {
  return get('/user/updatePwd.do', options)
}

// 获取验证码  -- 注册
export function getCode (options) {
  return get('/api/sms/sendRegSms.do', options)
}

// 获取验证码  -- 忘记密码
export function sendForgetSms (options) {
  return get('/api/sms/sendForgetSms.do', options)
}

// 获取图片验证码   -- 查看验证码
export function getCode2 (options) {
  return get('/code/getCode.do', options)
}

// 验证图片验证码 -- 验证
export function checkCode (options) {
  return get('/code/checkCode.do', options)
}

// /*** 首页 ****/
// 查询首页显示的指数
export function getIndexMarket (options) {
  return get('/api/index/queryHomeIndex.do', options)
}

// 查询列表页显示的指数
export function getListMarket (options) {
  return get('/api/index/queryListIndex.do', options)
}

// 查询指数是否可交易
export function getTransMarket (options) {
  return get('/api/index/queryTransIndex.do', options)
}

// 获取大盘指数
export function getMarket (options) {
  return get('/api/stock/getMarket.do', options)
}

// 股票列表数据
export function getStock (options) {
  return get('/api/stock/getStock.do', options)
}

// 单只股票行情数据
export function getSingleStock (options) {
  return post('/api/stock/getSingleStock.do', options)
}

// 单只指数行情数据
export function getSingleIndex (options) {
  return post('/api/index/querySingleIndex.do', options)
}

// 添加自选
export function addOption (options) {
  return post('/user/addOption.do', options)
}

// 删除自选
export function delOption (options) {
  return post('/user/delOption.do', options)
}

// 获取期货列表
export function getFutures (options) {
  return get('/api/futures/queryList.do', options)
}

// 获取首页显示的期货列表
export function getHomeFutures (options) {
  return get('/api/futures/queryHome.do', options)
}

// 获取期货列表
export function getListFutures (options) {
  return get('/api/futures/queryList.do', options)
}

// 查询期货产品的交易状态
export function queryTrans (options) {
  return get('/api/futures/queryTrans.do', options)
}

// 查询基币的汇率，对外暴露
export function queryExchange (options) {
  return get('/api/futures/queryExchange.do', options)
}

// 查询单个期货产品的行情（行情源的数据）
export function querySingleMarket (options) {
  return get('/api/futures/querySingleMarket.do', options)
}

// 新股抢筹列表
export function newStockQc (options) {
  return post('/user/newStockQc.do', options)
}
// 新股列表
export function getNewGu (options) {
  return post('/user/newStockList.do', options)
}
// 大宗交易列表
export function stockgetDzList (options) {
  return post('/api/stock/getDzList.do', options)
}
// vip抢筹列表
export function getVipList (options) {
  return post('/api/stock/getVipList.do', options)
}
// vip抢筹详情下单
export function buyVipQc (options) {
  return post('/user/buyVipQc.do', options)
}


// 大宗交易下单
export function buyStockDz (options) {
  return post('/user/buyStockDz.do', options)
}

// 新股申购
export function getNewAdd (options) {
  return post('/user/add.do', options)
}
// 新股抢筹申购
export function buyNewStockQc (options) {
  return post('/user/buyNewStockQc.do', options)
}

// 申购列表
export function getUserNewGuList (options) {
  return post('/user/getOneSubscribeByUserId.do', options)
}
export function getzqjl (options) {
  return post('/user/getzqjl.do', options)
}

// 期货下单
export function buyFutures (options) {
  return post('/user/buyFutures.do', options)
}

// 挂单
export function guadan (options) {
  return post('/user/addOrder.do', options)
}

// 删除挂单
export function delGuaDan (options) {
  return post('/user/delOrder.do', options)
}
// 挂单列表
export function getorderList (options) {
  return post('/user/orderList.do', options)
}

// 美股详情
export function getUsDetail (options) {
  return post('/api/stock/getSingleStockByCode.do', options)
}

// 下单
export function buy (options) {
  return post('/user/buy.do', options)
}

// 指数下单
export function indexBuy (options) {
  return post('/user/buyIndex.do', options)
}

// 用户平仓
export function sell (options) {
  return post('/user/sell.do', options)
}

// 指数平仓
export function sellIndex (options) {
  return post('/user/sellIndex.do', options)
}

// 期货平仓
export function sellFutures (options) {
  return post('/user/sellFutures.do', options)
}

// /***用户中心***/
// 用户资金户转
export function AmtChange (options) {
  return post('/user/transAmt.do', options)
}

// 用户详情
export function getUserInfo (options) {
  return post('/user/getUserInfo.do', options)
}

// 添加银行卡
export function addBankCard (options) {
  return post('/user/bank/add.do', options)
}

// 修改银行卡
export function updateBankCard (options) {
  return post('/user/bank/update.do', options)
}

// 获取银行卡信息
export function getBankCard (options) {
  return post('/user/bank/getBankInfo.do', options)
}

// 获取我的持仓单
export function getOrderList (options) {
  return post('/user/position/list.do', options)
}

// 获取我的持仓单 - 指数
export function getIndexOrderList (options) {
  return post('/user/index/position/list.do', options)
}

// 获取我的持仓单 - 期货
export function getFuturesOrderList (options) {
  return post('/user/futures/position/list.do', options)
}

// 获取我的自选列表
export function getMyList (options) {
  return post('/user/option/list.do', options)
}

// 实名认证
export function userAuth (options) {
  return post('/user/auth.do', options)
}

// 资金明细
export function cashDetail (options) {
  return post('/user/cash/list.do', options)
}

// 提现记录
export function rechargeList (options) {
  return post('/user/recharge/list.do', options)
}

// 充值记录
export function withdrawList (options) {
  return post('/user/withdraw/list.do', options)
}

// 充值
export function inMoney (options) {
  return post('/user/recharge/inMoney.do', options)
}

// 提现
export function outMoney (options) {
  return post('/user/withdraw/outMoney.do', options)
}

// 取消提现
export function canceloutMoney (options) {
  return post('/user/withdraw/cancel.do', options)
}

// k线图
export function getMinK (options) {
  return post('/api/stock/getMinK.do', options)
}

// k线图
export function getMinKEcharts (options) {
  return post('/api/stock/getMinK_Echarts.do', options)
}

// 是否已添加自选
export function isOption (options) {
  return post('/user/isOption.do', options)
}

// 获取网站设置信息
export function getSetting (options) {
  return post('/api/admin/getSetting.do', options)
}

// 获取指数网站设置信息
export function getIndexSetting (options) {
  return post('/api/admin/getIndexSetting.do', options)
}

// 获取期货网站设置信息
export function getFuturesSetting (options) {
  return post('/api/admin/getFuturesSetting.do', options)
}

// 获取首页banner
export function getBannerByPlat (options) {
  return post('/api/site/getBannerByPlat.do', options)
}

// 公告列表
export function getArtList (options) {
  return post('/api/art/list.do', options)
}

// 公告详情
export function getArtDetail (options) {
  return post('/api/art/detail.do', options)
}

// 获取支付渠道
export function getPayInfo (options) {
  return post('/api/site/getPayInfo.do', options)
}

// 获取单个渠道信息
export function getPayInfoDetail (options) {
  return post('/api/site/getPayInfoById.do', options)
}

// 获取网站设置信息
export function getInfoSite (options) {
  return post('/api/site/getInfo.do', options)
}

// k线图 分时
export function getMinuteLine (options) {
  return post('/api/realTime/findStock.do', options)
}

// 新增渠道  支付宝扫码
export function getjuhe1 (options) {
  return post('/user/pay/juhe1.do', options)
}

//H5支付
export function getjuheH5 (options) {
  return post('/user/pay/juheh5.do', options)
}

// 支付渠道
export function payLInk (url, options) {
  return post(url, options)
}

// 图片上传 uploadimg
export function uploadimg (options) {
  return post('/user/upload.do', options)
}

// 查询点差费率
export function findSpreadRateOne (options) {
  return post('/api/user/findSpreadRateOne.do', options)
}

// ==================最新修改内容：日线、添加自选等，2020年7月10日15:37:20======================
// 期货分钟-k线图
export function getFuturesMinKEcharts (options) {
  return post('/api/stock/getFuturesMinK_Echarts.do', options)
}

// 指数分钟-k线图
export function getIndexMinKEcharts (options) {
  return post('/api/stock/getIndexMinK_Echarts.do', options)
}

// 股票日线图
export function getDayK (options) {
  return post('/api/stock/getDayK.do', options)
}

// 期货日线图
export function getFuturesDayK (options) {
  return post('/api/stock/getFuturesDayK.do', options)
}

// 指数日线图
export function getIndexDayK (options) {
  return post('/api/stock/getIndexDayK.do', options)
}

// 查询期货详情
export function queryFuturesByCode (options) {
  return get('/api/futures/queryFuturesByCode.do', options)
}


// ==================最新修改内容：新版-新闻资讯、交易大厅，2020年8月26日10:39======================

// 查询期货详情
export function queryNewsList (type) {
  return get(`/api/news/getNewsList.do?pageNum=1&pageSize=15&type=${type}`, {})
}

// 查询新闻详情
export function queryNewsDetail (type) {
  return get(`/api/news/getDetail.do?id=${type}`, {})
}

// 配资申请-用户配资列表
export function getUserApplyList (options) {
  return post('/user/funds/getUserApplyList.do', options)
}

// -----分仓配资----- 2020 08 30 

export function getFundsSetting (options) {
  return post('/user/funds/getFundsSetting.do', options)
}

// 查询配资类型杠杆
export function getFundsTypeList (options) {
  return post('/user/funds/getFundsTypeList.do', options)
}

// 配资申请-添加
export function addFundsApply (options) {
  return post('/user/funds/addFundsApply.do', options)
}
//分仓下单
export function buyFunds (options) {
  return post('/user/funds/buyFunds.do', options)
}

// 配资申请-用户操盘中子账户
export function getUserSubaccount (options) {
  return post('/user/funds/getUserSubaccount.do', options)
}

// 获取消息列表
export function getNoticeList(options) {
  return post('/user/cash/getMessagelist.do', options)
}

// 分仓交易-获取我的配资持仓单
export function getFundsOrderList (options) {
  return post('/user/funds/fundsList.do', options)
}

// 分仓交易-配资平仓
export function sellFunds (options) {
  return post('/user/funds/sellFunds.do', options)
}

export function getZs() {
  return get('https://69.push2.eastmoney.com/api/qt/clist/get?pn=1&pz=25&po=1&np=1&ut=bd1d9ddb04089700cf9c27f6f7426281&fltt=2&invt=2&wbp2u=|0|0|0|web&fid=&fs=b:MK0010&fields=f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f12,f13,f14,f15,f16,f17,f18,f20,f21,f23,f24,f25,f26,f22,f11,f62,f128,f136,f115,f152&_=1666359071216')
}


// 股票根据
export function getStockSort (options) {
  return post('/api/stock/getStockSort.do', options)
}



// 修改资金密码
export function insertWithPwd (options) {
  return post('/user/insertWithPwd.do', options)
}

// 搜索vip抢筹
export function getVipQc (options) {
  return post('/api/stock/getVipByCode.do', options)
}