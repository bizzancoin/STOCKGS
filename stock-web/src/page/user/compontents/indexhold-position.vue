<template>
  <div class="wrapper">
    <div v-if="list.length<=0 && !getStatus"
    class="empty text-center">
      暂无订单信息!
    </div>
    <div v-if="list.length<=0 && getStatus"
    class="empty text-center">
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
              <span @click="toDetail(item.indexCode)" class="main">{{item.indexName}}</span>
              
              <span :class="item.orderDirection=='买涨'?'type type-up':'type type-down'">{{item.orderDirection=='买涨'?'买涨':'买跌'}}</span>
              <span class="direction pull-right big-font">
                        总盈亏:
                        <b v-if="item.now_price == 0">-</b>
                        <b v-else
                           :class="item.allProfitAndLose<0?'space green':item.allProfitAndLose==0?'space':'space red'">{{item.allProfitAndLose}}</b>
                    </span>
              <!-- <span class="red direction pull-right">{{item.orderDirection}}<i class="iconfont icon-up"></i></span> -->
              <!-- <span class="secondary ">123456789</span> -->
            </div>
            <div class="code">
              <span class="secondary">({{item.indexCode}})</span>
            </div>
            <div class="order-info">
              <!-- <p class="font"><span class="col-xs-4 text-center pull-right red">审核中</span></p> -->
              <p v-if="false" class="clearfix">
                <span class="col-xs-4">买入点数:<b class="space">{{item.buyOrderPrice}}</b></span>
                <span class="col-xs-4 text-center">数量:<b class="space">{{item.orderNum}}</b>(手)</span>
                <span class="col-xs-4 text-right">保证金:<b class="space">{{item.allDepositAmt}}</b></span>
              </p>
              <p v-if="false" class="clearfix">
                <span class="col-xs-4">手续费:<b class="space">{{item.orderFee}}</b></span>
                <span class="col-xs-4 text-center">浮动盈亏:
                            <b v-if="item.now_price == 0">-</b>
                            <b v-else
                               :class="item.profitAndLose<0?'space green':item.profitAndLose==0?'space':'space red'">{{item.profitAndLose}}</b>
                        </span>
                <span class="col-xs-4 text-right big-font">总盈亏:
                            <b v-if="item.now_price == 0">-</b>
                            <b v-else
                               :class="item.allProfitAndLose<0?'space green':item.allProfitAndLose==0?'space':'space red'">{{item.allProfitAndLose}}</b>
                        </span>
              </p>
              <div class="table-title clearfix">
                <span class="col-xs-4">买入/最新</span>
                <span class="col-xs-4">数量(手)/保证金</span>
                <span class="col-xs-4">手续费/浮动盈亏</span>
              </div>
              <div class="table-value clearfix">
                <div class="col-xs-4">
                  <p>{{item.buyOrderPrice}}</p>
                  <p>
                    <b v-if="item.now_price == 0">-</b>
                    <b v-else
                       :class="item.now_price-item.buyOrderPrice<0?'green':item.now_price-item.buyOrderPrice==0?'':' red'">{{Number(item.now_price).toFixed(2)}}</b>
                  </p>
                </div>
                <div class="col-xs-4">
                  <p>{{item.orderNum}}</p>
                  <p>{{item.allDepositAmt}}</p>
                </div>
                <div class="col-xs-4">
                  <p>{{item.orderFee}}</p>
                  <p>
                    <b v-if="item.now_price == 0">-</b>
                    <b v-else :class="item.profitAndLose<0?' green':item.profitAndLose==0?'':' red'">{{item.profitAndLose}}</b>
                  </p>
                </div>
              </div>
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
    },
    selectedNumber: {
      type: String
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
  watch: {
    selectedNumber (val) {
      if (val === '2') {
        if (!this.$store.state.userInfo.idCard) {
          this.getUserInfo()
        }
        this.getListDetail()
        this.timer = setInterval(this.refreshList, 5000)
      } else {
        clearInterval(this.timer)
      }
    }
  },
  computed: {},
  created () {
    // this.timer = setInterval(this.refreshList, 5000)
  },
  beforeDestroy () {
    clearInterval(this.timer)
  },
  mounted () {

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
      let data = await api.getIndexOrderList(opt)
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
        stockCode: '', // 代码
        stockSpell: '', // 简拼
        pageNum: 1,
        pageSize: this.currentNum
      }
      this.isRefresh = true
      let data = await api.getIndexOrderList(opt)
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
        let data = await api.sellIndex(opt)
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
        path: '/list',
        query: {
          index: '1'
        }
      })
    }
  }
}
</script>
<style lang="less" scoped>
  .wrapper {
    padding-bottom: 0;
    /deep/.empty {
      background-color: #16171d;
    }
  }

  .col-xs-3 .iconfont {
    font-size: 0.22rem;
  }

  .table-title {
    font-size: 0.22rem;
    line-height: 0.4rem;
    margin-bottom: 0.1rem;

    .col-xs-4 {
      padding: 0;
    }
  }

  .table-value {
    .col-xs-4 {
      padding: 0;

      p {
        padding: 0;
        line-height: 0.32rem;
      }
    }
  }
  #app.red-theme{
    .order-info-box{
      background-color: #fff;
      .order-title{
        .main {
          color: #000;
        }
      }
      .order-info{
        color: #000;
      }
    }
    .order-foot {
      border-top-color: #ccc;
    }
    .load-all{
      background-color: #BB1815;
      color: #fff;
    }
    .empty{
      background-color: #E9E9E9;
      color: #000;
    }
  }
</style>
