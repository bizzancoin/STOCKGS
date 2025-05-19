<template>
  <div class="wrapper">
    <ul class="table-list"
        v-infinite-scroll="loadMore"
        infinite-scroll-disabled="loading"
        infinite-scroll-distance="10">
      <li class="list-body" v-for="item in list" :key="item.key">
        <div class="capital">
          <div>
            {{item.deType}}
            <span :class="item.deAmt<0?'pull-right green':'pull-right red'">{{item.deAmt}}</span>
          </div>
          <div class="pro clearfix">
            {{item.deSummary}}
            <!-- 股票:300092/开山股份 <span class="pull-right">金额:50.241</span> -->
          </div>
          <!-- <div class="clearfix">

              <div class="col-xs-4">价格:</div>
              <div class="col-xs-4">数量:5000</div>
              <div class="col-xs-4">总金额:34558.000</div>
          </div> -->
          <div class="pro clearfix">
            <div class="col-xs-12 text-right">生成时间:{{new Date(item.addTime) | timeFormat}}</div>
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
</template>

<script>
import { Toast } from 'mint-ui'
import * as api from '@/axios/api'

export default {
  components: {},
  props: {},
  data () {
    return {
      loading: false,
      list: [],
      pageNum: 1,
      pageSize: 10,
      total: 0
    }
  },
  watch: {},
  computed: {},
  created () {},
  mounted () {
    this.getcashDetail()
  },
  methods: {
    async getcashDetail () {
      // 获取资金明细
      let opts = {
        pageNum: this.pageNum,
        pageSize: 10
      }
      let data = await api.cashDetail(opts)
      if (data.status === 0) {
        data.data.list.forEach(element => {
          this.list.push(element)
        })
        this.total = data.data.total
      } else {
        Toast(data.msg)
      }
    },
    async loadMore () {
      if (this.list.length < this.pageSize || this.total <= this.pageNum * this.pageSize) {
        return
      }
      this.loading = true
      // 加载下一页
      this.pageNum++
      await this.getcashDetail()
      this.loading = false
    }
  }
}
</script>
<style lang="less" scoped>
  .table-list {
    padding: 0.2rem 0;

    .list-body {
      padding: 0.1rem 0.2rem;

      .capital {
        padding: 0.2rem;
        border-radius: 0.2rem;
        border: 0.01rem solid #3f444a;

        div {
          line-height: 0.4rem;
        }

        .col-xs-4 {
          padding-left: 0;
          padding-right: 0;
        }

        .pro {
          color: #999;
        }
      }
    }
  }

</style>
