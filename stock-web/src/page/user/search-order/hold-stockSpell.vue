<template>
  <div class="wrapper">
    <!-- <div class="header">
      <mt-header fixed title="查询持仓单">
        <router-link to="/orderlist" slot="left">
          <mt-button icon="back"></mt-button>
        </router-link> -->
        <!-- <mt-button icon="more" slot="right"></mt-button> -->
      <!-- </mt-header>
    </div> -->
    <!-- <form target="frameFile" v-on:submit.prevent='formSubmit'>
      <mt-button slot="right" class="search-btn-list" @click="getOrderList" icon="search">搜索</mt-button> -->
    <!-- </form> --> 
    <div class="search">
      <!-- <mt-search
        fixed
        show
        v-model="stockCode"
        @keyup.enter.native="getOrderList"
        placeholder="可输入股票简拼查询持仓"
      >
        
      </mt-search> -->
      <div class="search-input">
        <input type="text" placeholder="可输入股票简拼查询持仓"
         v-model="stockCode"
         @keyup.enter="getOrderList"
        >
      </div>
      <mt-button size="small" @click="getOrderList" icon="search"
      style="background-color:#000"
      >搜索</mt-button>

    </div>
    <div class="">
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
                <span v-if="item.stockPlate=='科创'" :class="item.stockPlate=='科创'?'type':''">科创</span>
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
                  <!-- <span class="col-xs-4">方向:<b class="red">买涨</b></span> -->
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

    </div>
    <div v-show="loading" class="load-all text-center">
          <mt-spinner type="fading-circle"></mt-spinner>
          加载中...
        </div>
        <div v-show="!loading && hasSearch" class="load-all text-center">
          已全部加载
        </div>
        <div class="text-center" v-if="!hasSearch">
          请查询订单
        </div>
    <foot></foot>
  </div>
</template>

<script>
import foot from '@/components/foot/foot'
import { Toast, MessageBox } from 'mint-ui'
import * as api from '@/axios/api'

export default {
  components: {
    foot
  },
  props: {},
  data () {
    return {
      loading: false,
      list: [],
      timer: '',
      pageNum: 1,
      pageSize: 10,
      currentNum: 10,
      hasSearch: false, // 是否查询
      stockCode: '',
      total: 0
    }
  },
  watch: {
    stockCode (newVal) {
      if (!newVal) {
        // 取消事件
        this.list = []
      } else {
        // this.getOrderList()
      }
    }
  },
  computed: {},
  created () {
    // this.timer = setInterval(this.refreshList, 5000)
  },
  beforeDestroy () {
    // clearInterval(this.timer)
  },
  mounted () {
    //   this.getStock()
  },
  methods: {
    formSubmit () {
      return false
    },
    async getOrderList () {
      // 查询持仓
      let opt = {
        state: 0,
        stockSpell: this.stockCode,
        pageNum: this.pageNum,
        pageSize: 15
      }
      this.loading = true
      this.hasSearch = true
      let data = await api.getOrderList(opt)
      if (data.status === 0) {
        this.list = data.data.list
        this.total = data.data.total
      } else {
        Toast(data.msg)
      }
      this.loading = false
      document.activeElement.blur()
    },
    async refreshList () {
      // 判断是不是已经查询 或者是否正在加载下一页 是则退出，不进行刷新
      if (!this.hasSearch || this.loading) {
        return
      }
      let opt = {
        state: 0,
        stockSpell: this.stockCode,
        pageNum: 1,
        pageSize: this.currentNum
      }
      let data = await api.getOrderList(opt)
      this.list = data.data.list
      this.total = data.data.total
    },
    async loadMore () {
      if (this.list.length < this.pageSize || this.total <= this.currentNum) {
        return
      }
      this.loading = true
      // 加载下一页
      this.pageNum++
      await this.getOrderList()
      this.currentNum = this.pageNum * this.pageSize
      this.loading = false
    },
    goBack () {
      this.$router.back(-1)
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
  .table-list-body {
    padding-top: 0.62rem;
  }

  // .order-info-box-wrap {
  //   margin-top: 0.9rem;
  // }

  .wrapper /deep/ .mint-searchbar {
    background: #16171d;
    position: fixed;
    width: 100%;
    top: 1rem;
    border-bottom: 1px solid #16171d;

    .mint-searchbar-inner {
      background-color: rgba(180, 180, 180, 0.1)
    }
  }
.load-all {
  background-color: #16171d;
}
  /deep/.mint-search-list{
    position: relative !important;
    padding-top: 60px;
    padding-bottom: 1rem;
    .order-info-box-wrap{
      padding-bottom: 1rem;
    }
  }

  .search {
    display: flex;
    justify-content: space-between;
    padding: .2rem;
    position: fixed;
    width: 100%;
    background-color: #16171d;
    z-index: 1;
    top: 1rem;
    &-input {
      flex: 1;
      height: .6rem;
      background-color: #2e3138;
      input {
        height: .6rem;
        width: 100%;
        padding: .2rem;
      }
    }
    button {
      height: .6rem;
    }
  }
  .order-info-box-wrap{
    padding-top: 1rem;
  }
</style>
