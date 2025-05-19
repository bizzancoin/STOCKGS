<template>
  <div class="wrapper">
    <div v-if="list.length<=0 && !getStatus" class="empty text-center">
      暂无订单信息!
    </div>
    <div v-if="list.length<=0 && getStatus" class="empty text-center">
      <mt-spinner type="fading-circle"></mt-spinner>
      加载中...
    </div>
    <div v-if="list.length>0">
      <ul
        class="order-info-box-wrap"
        v-infinite-scroll="loadMore"
        :infinite-scroll-disabled="loading"
        infinite-scroll-distance="0">
        <li v-for="(item) in list" :key="item.key">
          <div class="order-info-box">
            <div class="order-title">
              <span @click="toDetail(item.stockCode)" class="main">{{item.stockName}}</span>
              <span class="secondary">({{item.stockCode}})</span>
              <span :class="item.orderDirection=='买涨'?'type type-up':'type type-down'">{{item.orderDirection=='买涨'?'买涨':'买跌'}}</span>
              <span class="direction pull-right big-font">
                        最新:
                        <b v-if="item.now_price == 0">-</b>
                        <b v-else
                           :class="item.now_price-item.buyOrderPrice<0?'space green':item.now_price-item.buyOrderPrice==0?'space':'space red'">{{item.now_price}}</b>
                    </span>
              <!-- <span class="red direction pull-right">{{item.orderDirection}}<i class="iconfont icon-up"></i></span> -->
              <!-- <span class="secondary ">123456789</span> -->
            </div>
            <div class="order-info">
              <!-- <p class="font"><span class="col-xs-4 text-center pull-right red">审核中</span></p> -->
              <p class="clearfix">
                <span class="col-xs-4">买入价格:<b class="space">{{item.buyOrderPrice}}</b></span>
                <span class="col-xs-4 text-center">数量:<b class="space">{{item.orderNum}}</b></span>
                <!-- <span class="col-xs-4 text-right">杠杆:<b class="space">{{item.orderLever}}</b>倍</span> -->
                <span class="col-xs-4 text-right">市值:<b class="space">{{item.orderTotalPrice}}</b></span>
              </p>
              <!-- <p class="clearfix">
                  <span class="col-xs-4">方向:
                      <b v-if="item.orderDirection=='买涨'">{{item.orderDirection}}<i style="color:red" class="iconfont icon-up"></i></b>
                      <b v-if="item.orderDirection=='买跌'">{{item.orderDirection}}<i style="color:green" class="iconfont icon-down"></i></b>
                  </span>
                  <span class="col-xs-4 text-center">市值:<b class="space">{{item.orderTotalPrice}}</b></span>
                  <span class="col-xs-4 text-right">留仓天数:<b class="space">{{item.orderStayDays}}</b></span>
              </p> -->
              <p class="clearfix">
                <span class="col-xs-4">手续费:<b class="space">{{item.orderFee}}</b></span>
                <span class="col-xs-4 text-center">印花税:<b class="space">{{item.orderSpread}}</b></span>
                <span class="col-xs-4 text-right">留仓费:<b class="space">{{item.orderStayFee}}</b></span>
              </p>
              <p class="clearfix">
                        <span class="col-xs-6">浮动盈亏:
                            <b v-if="item.now_price == 0">-</b>
                            <b v-else
                               :class="item.profitAndLose<0?'space green':item.profitAndLose==0?'space':'space red'">{{item.profitAndLose}}</b>
                        </span>
                <span class="col-xs-6 text-right big-font">总盈亏:
                            <b v-if="item.now_price == 0">-</b>
                            <b v-else
                               :class="item.allProfitAndLose<0?'space green':item.allProfitAndLose==0?'space':'space red'">{{item.allProfitAndLose}}</b>
                        </span>
              </p>
              <!-- <p class="clearfix">
                  <span class="secondary col-xs-12 text-right">买入时间:
                      <b v-if="item.buyOrderTime">{{new Date(item.buyOrderTime) | timeFormat}}</b>
                      <b v-else></b>
                  </span>
              </p> -->
            </div>
            <div class="order-foot clearfix">
              <div style="text-align: left;color: #666;padding: 0;" class="col-xs-6">
                <b v-if="item.buyOrderTime">{{new Date(item.buyOrderTime) | timeFormat}}</b>
                <b v-else></b>
              </div>
              <div @click="sell(item.positionSn)" class="foot-btn">
                <i class='font-icon'></i>
                我要平仓
              </div>
            </div>
          </div>

        </li>
      </ul>
      <div v-show="loading" class="load-all text-center">
        <mt-spinner type="fading-circle"></mt-spinner>
        加载中...
      </div>
      <div v-show="!loading" class="load-all text-center">
        已全部加载
      </div>
    </div>
  </div>
</template>

<script>
import { Toast, MessageBox } from 'mint-ui'
import * as api from '@/axios/api'

export default {
  components: {},
  props: {
    // list:{
    //     type:Array,
    //     default:{
    //         return:[]
    //     }
    // },
    // form:{
    //     type:Object,
    //     default:{
    //         return:{}
    //     }
    // },
    // getListDetail:{
    //     type:Function,
    //     default: function(){}
    // },
    handleOptions: {
      type: Function,
      default: function () {}
    }
  },
  data () {
    return {
      loading: false, // 是否正在加载更多
      isRefresh: false, // 是否正在刷新
      getStatus: false, // 是否正在数据
      pageNum: 1,
      pageSize: 10,
      currentNum: 10,
      list: [],
      total: 0, // 记录总值
      hasChangeSell: false // 平仓状态改变
    }
  },
  watch: {},
  computed: {},
  created () {
    this.timer = setInterval(this.refreshList, 5000)
  },
  beforeDestroy () {
    clearInterval(this.timer)
  },
  mounted () {
    if (!this.$store.state.userInfo.idCard) {
      this.getUserInfo()
    }
    this.getListDetail()
  },
  methods: {
    async getUserInfo () {
      // 获取用户信息
      let data = await api.getUserInfo()
      if (data.status === 0) {
        this.$store.state.userInfo = data.data
      } else {
        Toast(data.msg)
      }
    },
    async loadMore () {
      // 1、总数小于 该次查询的总页数 不进行加载更多 （无下一页）
      // 2、当页加载数据还未加载完 不进行下一个加载
      if (this.list.length < this.pageSize || this.loading || this.total <= this.currentNum) {
        return
      }
      this.loading = true
      // 加载下一页
      this.pageNum++
      await this.getListDetail()
      this.currentNum = this.pageNum * this.pageSize
      this.loading = false
    },
    async getListDetail () {
      let opt = {
        state: 0,
        stockCode: '', // 代码
        stockSpell: '', // 简拼
        pageNum: this.pageNum,
        pageSize: this.pageSize
      }
      this.getStatus = true
      let data = await api.getOrderList(opt)
      if (data.status === 0) {
        data.data.list.forEach(element => {
          this.list.push(element)
        })
        this.getStatus = false
        this.total = data.data.total
      } else {
        Toast(data.msg)
      }
    },
    async refreshList () {
      // 判断是不是已经查询 或者是否正在加载下一页 是则退出，不进行刷新
      if (this.loading) {
        return
      }
      let opt = {
        state: 0,
        stockPlate: '科创',
        stockCode: '', // 代码
        stockSpell: '', // 简拼
        pageNum: 1,
        pageSize: this.currentNum
      }
      this.isRefresh = true
      let data = await api.getOrderList(opt)
      this.isRefresh = false
      this.total = data.data.total
      this.list = data.data.list
    },
    canBuyStatus () {
      let dataTime = new Date()
      let day = dataTime.getDay() // 星期几
      let hour = dataTime.getHours() // 小时
      let minute = dataTime.getMinutes() // 分钟
      if (day === 6 || day === 7) {
        return false
      }
      if (hour < 9 || (hour >= 12 && hour < 13) || hour >= 15) {
        return false
      }
      if (hour === 9 && minute < 32) {
        return false
      }
      if (hour === 11 && minute >= 30) {
        return false
      }
      if (hour === 14 && minute > 57) {
        return false
      }
      return true
    },
    sell (val) {
      // if(!this.canBuyStatus()){
      //     Toast('不在开盘时间内，暂不能交易！')
      //     return
      // }
      if (!this.$store.state.userInfo.idCard) {
        Toast('您还未实名认证,请先实名认证了再下单')
        this.$router.push('/authentication')
        return
      }
      MessageBox.confirm('您确定要平仓吗?').then(async action => {
        let opt = {
          positionSn: val
        }
        let data = await api.sell(opt)
        if (data.status === 0) {
          Toast(data.msg)
          this.hasChangeSell = true
          this.handleOptions(this.hasChangeSell)
          this.getListDetail()
        } else {
          Toast(data.msg)
        }
      })
    },
    toDetail (code) {
      this.$router.push({
        path: '/listdetail',
        query: {
          code: code
        }
      })
    }
  }
}
</script>
<style lang="less" scoped>
  .wrapper {
    padding-bottom: 0
  }

  .col-xs-3 .iconfont {
    font-size: 0.22rem;
  }
</style>
