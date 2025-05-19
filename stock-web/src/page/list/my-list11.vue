<template>
  <div class="wrapper">
    <!-- <mt-header fixed title="我的自选">
      <router-link to="/list" slot="left">
        <!-- <mt-button icon="back"></mt-button> -->
    <!-- </router-link>
      <mt-button slot="right" icon="search" @click="toSearch"></mt-button>
    </mt-header>  -->
    <div class="account-box">
      <div class="content">
        <div class="box page-part">
          <div class="box-contain clearfix">
            <div :class="i.floatPoint < 0 ? 'tab greenBg' : 'tab redBg'" v-for="(i,index) in market" v-if="index < 3"
              :key="i.key">
              <p :index='index' class="name">{{ i.indexName }}</p>
              <p :class="changeTextClass[index] == true ? 'price heartBeat' : 'price'">
                {{ Number(i.currentPoint).toFixed(2) }}</p>
              <div class="status">
                <span :class="i.floatPoint < 0 ? 'pifting green' : 'pifting red'">{{ Number(i.floatPoint).toFixed(2) }}</span>
                <span :class="i.floatRate < 0 ? 'Percentage green' : 'Percentage red'">{{ i.floatRate }}%</span>
              </div>
            </div>

          </div>
        </div>
      </div>
    </div>
    <ul class="table-list">
      <li class="title">
        <div>
          <ul class='clearfix'>
            <li class="li-title">股票</li>
            <li class="li-base">最新</li>
            <li class="li-base">涨跌幅</li>
            <li class="li-base text-center">
              删自选
            </li>
          </ul>
        </div>
      </li>
    </ul>
    <ul class="table-list table-list-body" v-infinite-scroll="loadMore" infinite-scroll-disabled="loading"
      infinite-scroll-distance="10">
      <li class="list-body" v-for="item in list" :key="item.key">
        <div>
          <ul class="clearfix" :class="item.nowPrice - item.preclose_px < 0 ? 'green' : 'red'">
            <li @click='toDetail(item)' :class="item.stock_plate == '科创' ? 'li-title li-title-kc' : 'li-title'">
              <p class="name">
                <!-- <i v-if="item.stock_plate != '科创'" class="iconfont icon-r red"></i> -->
                {{ item.stockName }}
              </p>
              <!-- <a href="javascript:;"  class="name">{{item.title}}</a> -->
              <p v-if="item.stock_plate == '科创'" class="code"><i class="iconfont kechuang-mark">科创</i>{{ item.stockCode }}
              </p>
              <p v-else class="code">
                <i :class="item.stock_type == 'sz' ? 'iconfont shen-mark hushen-mark' : 'iconfont hushen-mark'">{{ item.stock_type
                    == 'sz' ? '深' : '沪'
                }}</i>
                {{ item.stockCode }}
              </p>
            </li>
            <li @click='toDetail(item)' class="li-base">
              <span>{{ item.nowPrice ? Number(item.nowPrice).toFixed(2) : '-' }}</span>
            </li>
            <li @click='toDetail(item)' class="li-base">
              <span v-if="item.nowPrice == 0">-</span>
              <span v-else>{{ item.nowPrice - item.preclose_px > 0 ? '+' : '' }}{{ item.hcrate ? item.hcrate : '-' }}%</span>
            </li>
            <li class="li-base text-center">
              <mt-button plain @click="toDeleteMy(item)"><i class="iconfont icon-shanchucopy"></i></mt-button>
            </li>
          </ul>
        </div>

      </li>
    </ul>
    <div v-if="list.length <= 0 && getStatus" class="load-all text-center">
      <mt-spinner type="fading-circle"></mt-spinner>
    </div>
    <div v-show="loading" class="load-all text-center">
      <mt-spinner type="fading-circle"></mt-spinner>
      加载中...
    </div>
    <div v-show="!loading && !getStatus" class="load-all text-center">
      已全部加载
    </div>
    <foot></foot>
  </div>
</template>

<script>
import foot from '../../components/foot/foot'
import { Toast } from 'mint-ui'
import * as api from '@/axios/api'

export default {
  components: {
    foot
  },
  props: {},
  data() {
    return {
      loading: false, // 正在加载下一页
      isRefresh: false, // 正在刷新
      getStatus: false, // 正在获取数据
      list: [],
      timer: '',
      currentNum: 15,
      market: [],
      changeTextClass: {},
      // pageNum:1,
      // pageSize:15
    }
  },
  watch: {},
  computed: {},
  created() {
    this.timer = setInterval(this.refreshList, 5000)
  },
  beforeDestroy() {
    clearInterval(this.timer)
  },
  mounted() {
    this.getStock()
    this.getMarket()
  },
  methods: {
    async getMarket() {
      // 获取大盘指数
      let result = await api.getIndexMarket()
      if (result.status === 0) {
        this.market = result.data
      } else {
        Toast(result.msg)
      }
    },
    async getStock() {
      let opt = {
        pageNum: this.pageNum,
        pageSize: 15
      }
      this.getStatus = true
      let data = await api.getMyList(opt)
      this.getStatus = false
      if (data.status === 0) {
        this.list = data.data.list
      } else {
        Toast(data.msg)
      }
    },
    async refreshList() {
      if (this.loading) {
        return
      }
      let opt = {
        pageNum: 1,
        pageSize: this.currentNum
      }
      this.isRefresh = true
      let data = await api.getMyList(opt)
      this.list = data.data.list
      this.isRefresh = false
    },
    async loadMore() {
      if (this.list.length < 10) {
        return
      }
      this.loading = true
      // 加载下一页
      this.pageNum++
      await this.getStock()
      this.currentNum = this.pageNum * this.pageSize
      this.loading = false
    },
    async toDeleteMy(val) {
      let data = await api.delOption({ code: val.stockCode })
      if (data.status === 0) {
        Toast('删除自选股成功')
        this.getStock()
      } else {
        Toast(data.msg)
      }
    },
    async getQhDetail(code, stockGid) {
      let opts = {
        futuresCode: code
      }
      let data = await api.queryFuturesByCode(opts)
      if (data.status === 0) {
        this.statusDetail = data.data
        this.$router.push({
          path: '/listdetail2',
          query: {
            code: stockGid,
            qhinfo: this.statusDetail
          }
        })
      } else {
        Toast(data.msg)
      }
    },
    toDetail(val) {
      // if(true){
      //     Toast('系统正在升级，暂关闭交易！')
      //     return
      // }
      let code = val.stockCode
      if (val.stockGid !== undefined && val.stockGid.indexOf('hf_') != -1) {
        this.getQhDetail(val.stockCode, val.stockGid)
      } else if (val.stockCode !== undefined && val.stockCode.substring(0, 3) == '000') {
        code = val.stockGid
        // this.$router.push({
        //   path: '/listdetail',
        //   query: {
        //     code: code,
        //     stock_type:val.stock_type,
        //     zsinfo: val
        //   }
        // })

        this.$router.push({
          path: '/listdetail2',
          query: {
            code: code,
            stock_type: val.stock_type,
            zsinfo: val
          }
        })
        return
      } else {
        // 详情
        // this.$router.push({
        //   path: '/listdetail',
        //   query: {
        //     code: code,
        //     name: val.stockName
        //   }
        // })

        this.$router.push({
          path: '/listdetail2',
          query: {
            code: code,
            stock_type: val.stock_type,
            zsinfo: val
          }
        })
      }

    },
    toSearch() {
      this.$router.push('/searchmylist')
    }
  }
}
</script>
<style lang="less" scoped>
.table-list-body {
  // padding-top:0.62rem;
  // margin-top: 40px;
}

.wrapper {
  padding-top: 0.2rem;
  background-color: #16171d;
}

.account-box {
  margin-top: 0.5rem;
  background-color: #16171d;

  .box-contain {
    background-color: #16171d;
  }
}

.red-bg .table-list .title {
  //   top: 2.5rem;
}

/*大盘指数*/
.box-contain {
  min-height: 1.3rem;

  .more {
    position: absolute;
    right: 0;
    padding-top: 0.5rem;
    padding-right: 0.2rem;
    cursor: pointer;
  }

  .tab {
    float: left;
    width: 31%;
    margin: 0.05rem 1%;
    margin-top: 0;
    text-align: center;
    padding: 0.1rem 0;
    background: none !important;

    p {
      margin-top: 0.1rem;
    }

    .name {
      font-size: .22rem;
    }

    .price {
      font-size: 0.34rem;
    }

    .status {
      margin-top: 0.1rem;
      font-size: .22rem;
    }
  }
}

.table-list .title {
  top: 1rem;
}

.red-theme {
  .table-list .title {
    background-color: #E9E9E9;
    color: #222;
  }

  .wrapper {
    background-color: #E9E9E9;
  }

  .table-list ul li {
    border-bottom-color: #E9E9E9;
    background-color: #fff;
  }

  .account-box .box-contain {
    background-color: #E9E9E9;
  }

  .box-contain .tab .name {
    color: #222;
  }

  .table-list ul .li-title .name {
    color: #222;
  }

  .load-all {
    background-color: #E9E9E9;
  }
}
</style>
