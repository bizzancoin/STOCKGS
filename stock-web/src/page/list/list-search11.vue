<template>
  <div class="wrapper">
    <mt-button slot="right" class="search-btn-list" icon="search" @click="getStock">搜索</mt-button>
    <mt-search
      fixed
      show
      autofocus
      v-model="keywords"
      @keyup.enter.native="getStock"
      placeholder="可输入股票代码或简拼"
    >
      <ul class="table-list">
        <li class="title">
          <div>
            <ul class='clearfix'>
              <li class="li-title">股票</li>
              <li class="li-base">最新</li>
              <li class="li-base">涨跌幅</li>
              <li class="li-base">涨跌</li>
            </ul>
          </div>
        </li>
      </ul>
      <ul class="table-list table-list-body"
          v-infinite-scroll="loadMore"
          infinite-scroll-disabled="loading"
          infinite-scroll-distance="10">
        <li class="list-body" v-for="item in list" :key="item.key">
          <div>
            <ul class="clearfix"
                :class="item.nowPrice-item.preclose_px<0?'green':item.nowPrice-item.preclose_px==0?'':'red'"
                @click='toDetail(item)'>
              <li class="li-title">
                <p class="name">{{item.name}}</p>
                <p class="code">
                  <i v-if="item.stock_plate != '科创'"
                     :class="item.stock_type == 'sz'?'iconfont shen-mark hushen-mark':'iconfont hushen-mark'">{{item.stock_type
                    == 'sz'?'深':'沪'}}</i>
                  <i v-else class="iconfont kechuang-mark">科创</i>
                  {{item.code}}
                </p>
              </li>
              <li class="li-base">
                <span>{{item.nowPrice?Number(item.nowPrice).toFixed(2):'-'}}</span>
              </li>
              <li class="li-base">
                <span v-if="item.nowPrice == 0">-</span>
                <span v-else>{{item.nowPrice-item.preclose_px>0 ?'+':''}} {{item.hcrate?item.hcrate:'0'}}%</span>
              </li>
              <li class="li-base no-bold">
                <span v-if="item.nowPrice == 0">-</span>
                <span
                  v-else>{{item.nowPrice-item.preclose_px>0 ?'+':''}}{{(item.nowPrice-item.preclose_px).toFixed(2)}}</span>
              </li>

            </ul>
          </div>

        </li>
      </ul>
      <div v-show="loading" class="load-all text-center">
        <mt-spinner type="fading-circle"></mt-spinner>
        加载中...
      </div>
      <div v-show="!loading && hasSearch" class="load-all text-center">
        已全部加载
      </div>
      <div v-show="!hasSearch" class="load-all text-center">
        可输入您想要查询的股票代码或者简拼进行查询
      </div>
    </mt-search>
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
  data () {
    return {
      loading: false,
      keywords: '',
      pageNum: 1,
      pageSize: 15,
      list: [],
      timer: '',
      currentNum: 15,
      hasSearch: false // 是否查询
    }
  },
  watch: {
    keywords (newVal, oldVal) {
      if (newVal !== oldVal) {
        this.getStock()
      }
    }
  },
  computed: {},
  created () {
    this.timer = setInterval(this.refreshList, 5000)
  },
  beforeDestroy () {
    clearInterval(this.timer)
  },
  mounted () {
    //   this.getStock()
  },
  methods: {
    async getStock () {
      let opt = {
        keyWords: this.keywords,
        pageNum: this.pageNum,
        pageSize: 15
      }
      this.hasSearch = true
      let data = await api.getStock(opt)
      if (data.status === 0) {
        this.list = []
        data.data.list.forEach(element => {
          this.list.push(element)
        })
      } else {
        Toast(data.msg)
      }
    },
    async refreshList () {
      // 判断是不是已经查询 或者是否正在加载下一页 是则退出，不进行刷新
      if (!this.hasSearch || this.loading) {
        return
      }
      let opt = {
        keyWords: this.keywords,
        pageNum: 1,
        pageSize: this.currentNum
      }
      let data = await api.getStock(opt)
      this.list = data.data.list
    },
    async loadMore () {
      if (this.list.length < 10) {
        return
      }
      this.loading = true
      // 加载下一页
      this.pageNum++
      this.currentNum = this.pageNum * this.pageSize
      await this.getStock()
      this.loading = false
    },
    toDetail (val) {
      // if(true){
      //     Toast('系统正在升级，暂关闭交易！')
      //     return
      // }
      // 详情
      this.$router.push({
        path: '/listdetail',
        query: {
          code: val.code,
          stock_type:val.stock_type
          // name: val.name
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

  .table-list {
    .li-title {
      width: 34%;

    }

    .li-base {
      width: 22%;
      text-align: center;

      &.no-bold {
        span {
          font-weight: 400;
        }
      }
    }
  }

  .search-btn-list {
    position: fixed;
    right: 0;
    height: 42px;
    font-size: 0.25rem;
    z-index: 10;
    border: none;
    box-shadow: none;
  }

  .wrapper /deep/ .mint-searchbar {
    background: #2e3138;
    position: fixed;
    width: 100%;

    .mint-searchbar-inner {
      background-color: rgba(180, 180, 180, 0.1)
    }
  }

</style>
