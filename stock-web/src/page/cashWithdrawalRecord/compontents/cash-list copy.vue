<template>
  <div class="wrapper">
    <div v-if="list.length<=0" class="empty text-center">
      暂无提现信息!
    </div>
    <div v-else>
      <ul
        class="table-list"
        v-infinite-scroll="loadMore"
        infinite-scroll-disabled="loading"
        infinite-scroll-distance="10">
        <li class="list-body" v-for="(item) in list" :key="item.key">
          <div class="order-info-box" style="border-radius: .15rem">
            <div class="order-title">
                    <span class="main">
                        提现至银行卡
                    </span>
              <span class="payNumber">¥{{item.withAmt}}</span>
              <span class="red pull-right">
                        {{item.withStatus == 1?'提现成功':item.withStatus == 2?'提现失败':item.withStatus == 3?'订单取消':'审核中'}}
                        <i v-if="item.withStatus == 1" class="iconfont icon-tongguo4 animated bounceIn"></i>
                        <i v-if="item.withStatus==0" class="iconfont icon-dengdai animated bounceInDown"></i>
                        <i v-if="item.withStatus == 2" class="iconfont icon-failure animated bounceInDown"></i>
                        <i v-if="item.withStatus == 3"
                           class="iconfont icon-iconfontweitongguo animated bounceInDown"></i>
                    </span>
              <!-- <span class="secondary ">123456789</span> -->
            </div>
            <div class="order-info">
              <p class="clearfix">
                <span class="col-xs-6">手续费:<b class="space">￥{{item.withFee}}</b></span>
                <!-- <span class="col-xs-6">实际到账金额:<b class="space" style="font-size:0.26rem">{{item.withAmt - item.withFee}}</b>元</span>                         -->
              </p>
              <p class="clearfix">
                <span class="col-xs-12">银行卡:<b class="space">{{item.bankName}}-{{item.bankAddress}}</b></span>
              </p>
              <p class="clearfix">
                <span class="col-xs-12">卡号:<b class="space">{{item.bankNo}}</b></span>
              </p>
              <p v-if="item.withStatus == 3" class="clearfix">
                <span class="col-xs-12">取消原因:<b class="space">{{item.withMsg}}</b></span>
              </p>
              <p v-if="item.withStatus == 2" class="clearfix">
                <span class="col-xs-12">失败原因:<b class="space">{{item.withMsg}}</b></span>
              </p>
              <p class="clearfix">
                        <span class="secondary col-xs-6">时间:
                            <b v-if="item.applyTime">{{new Date(item.applyTime) | timeFormat}}</b>
                            <b v-else></b>
                        </span>
              </p>
            </div>
            <div v-if="item.withStatus == 0" class="order-foot clearfix">
              <div @click="cancle(item.id)" class="foot-btn">
                <i class='font-icon'></i>
                取消提现
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
      pageSize: 15
    }
  },
  watch: {},
  computed: {},
  created () {},
  mounted () {
    this.getListDetail()
  },
  methods: {
    async getListDetail () {
      let opt = {
        withStatus: '', // 提现状态 0已提交，1转账成功，2转账失败
        pageNum: this.pageNum,
        pageSize: 15
      }
      let data = await api.withdrawList(opt)
      if (data.status === 0) {
        data.data.list.forEach(element => {
          this.list.push(element)
        })
      } else {
        Toast(data.msg)
      }
    },
    async loadMore () {
      if (this.list.length < 10) {
        return
      }
      this.loading = true
      // 加载下一页
      this.pageNum++
      await this.getListDetail()
      this.loading = false
    },
    async cancle (val) {
      // 取消提现
      // MessageBox.confirm('您确定要平仓吗?').then(async action => {
      let opt = {
        withId: val
      }
      let data = await api.canceloutMoney(opt)
      if (data.status === 0) {
        this.list = []
        Toast(data.msg)
        this.getListDetail()
      } else {
        Toast(data.msg)
      }
      // });
    }
  }
}
</script>
<style lang="less" scoped>
  .wrapper {
    // padding-top: 0.9rem;
  }

  .payNumber {
    font-size: 0.3rem;
    color: #ff8000;
  }

  .table-list {
    padding: 0.2rem 0;

    .list-body {
      padding: 0.1rem 0.2rem;
      .capital:nth-child(1) {
        border-top: 0.01rem solid #3f444a;
      }

      .capital {
        padding: 0.2rem;
        // border-radius: 0.2rem;
        border-bottom: 0.01rem solid #3f444a;

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

  .order-info-box{
    background-color: #c2c2c2;
}
.load-all{
  background-color: #fff;
}
</style>
