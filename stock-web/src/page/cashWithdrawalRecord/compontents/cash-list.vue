<template>
  <div class="wrapper">
    <div v-if="list.length <= 0" class="empty text-center">
      暂无提现信息!
    </div>
    <div v-else>
      <ul class="table-list" v-infinite-scroll="loadMore" infinite-scroll-disabled="loading"
        infinite-scroll-distance="10">

        <li   v-for="(item) in list" :key="item.key" class="czli">
          <div class="ddh">
            <h6 > 订单号：{{item.bankNo}} </h6>
            <h5 >{{item.withAmt}}</h5>
            <p  class="riqi">{{ new Date(item.applyTime) | timeFormat }}</p>
            <p  class="status"><span  class="jishi"></span> {{ item.withStatus == 1 ? '提现成功' : item.withStatus == 2 ? '提现失败' : item.withStatus == 3 ? '订单取消' : '审核中' }} </p>
          </div>
          <div  class="title" style="display: none;">
            <p > </p>
            <h3 ><span >2022-09-28 14:56</span><a >100.00</a></h3>
          </div>
        </li>

        <!-- <li class="list-body">
          <div class="order-info-box" style="border-radius: .15rem">
            <div class="order-title">
              <span class="main">
                提现至银行卡
              </span>
              <span class="payNumber">¥{{ item.withAmt }}</span>
              <span class="red pull-right">
                {{ item.withStatus == 1 ? '提现成功' : item.withStatus == 2 ? '提现失败' : item.withStatus == 3 ? '订单取消' : '审核中' }}
                <i v-if="item.withStatus == 1" class="iconfont icon-tongguo4 animated bounceIn"></i>
                <i v-if="item.withStatus == 0" class="iconfont icon-dengdai animated bounceInDown"></i>
                <i v-if="item.withStatus == 2" class="iconfont icon-failure animated bounceInDown"></i>
                <i v-if="item.withStatus == 3" class="iconfont icon-iconfontweitongguo animated bounceInDown"></i>
              </span>

            </div>
            <div class="order-info">
              <p class="clearfix">
                <span class="col-xs-6">手续费:<b class="space">￥{{ item.withFee }}</b></span>

              </p>
              <p class="clearfix">
                <span class="col-xs-12">银行卡:<b class="space">{{ item.bankName }}-{{ item.bankAddress }}</b></span>
              </p>
              <p class="clearfix">
                <span class="col-xs-12">卡号:<b class="space">{{ item.bankNo }}</b></span>
              </p>
              <p v-if="item.withStatus == 3" class="clearfix">
                <span class="col-xs-12">取消原因:<b class="space">{{ item.withMsg }}</b></span>
              </p>
              <p v-if="item.withStatus == 2" class="clearfix">
                <span class="col-xs-12">失败原因:<b class="space">{{ item.withMsg }}</b></span>
              </p>
              <p class="clearfix">
                <span class="secondary col-xs-6">时间:
                  <b v-if="item.applyTime">{{ new Date(item.applyTime) | timeFormat }}</b>
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

        </li> -->
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
  data() {
    return {
      loading: false,
      list: [],
      pageNum: 1,
      pageSize: 15
    }
  },
  watch: {},
  computed: {},
  created() { },
  mounted() {
    this.getListDetail()
  },
  methods: {
    async getListDetail() {
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
    async loadMore() {
      if (this.list.length < 10) {
        return
      }
      this.loading = true
      // 加载下一页
      this.pageNum++
      await this.getListDetail()
      this.loading = false
    },
    async cancle(val) {
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
  width: 96%;
  margin: 0 auto;
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

.order-info-box {
  background-color: #c2c2c2;
}

.load-all {
  background-color: #fff;
}


.czli {
    width: 9.4785rem;
    padding-bottom: 0.267rem;
    border-bottom: 0.0267rem solid #ececec;
    position: relative;
    margin-bottom: 0.267rem;
}
 .czli h6 {
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    font-weight: 500;
    height: 0.534rem;
    font-size: 0.3204rem;
    color: #666;
    line-height: 0.534rem;
    padding-left: 0.267rem;
    padding-top: 0.1335rem;
}
.czli h5 {
    font-size: 0.4005rem;
    font-family: PingFang SC;
    font-weight: 500;
    color: #000;
    margin-top: 0.267rem;
    margin-left: 0.267rem;
}

.riqi {
    margin-top: 0.2937rem;
    color: #666;
    font-size: 0.267rem;
    margin-left: 0.267rem;
}
.status {
    position: absolute;
    right: 0.3738rem;
    top: 0.801rem;
    font-size: 0.3738rem;
    color: #d73d3d;
    font-weight: 550;
}

.jishi {
    display: inline-block;
    background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACQAAAAkCAYAAADhAJiYAAAAAXNSR0IArs4c6QAAAtlJREFUWEftls1LVUEYxp/3oBcCKzKiTbSQvDMibjSitO7cKAsSWvVFtbCNtG5Vf0FtonVtosigj1VkQWJ2riURUgsxZ64gIq2Eirr04dXOG+eSl0zvnTnn3E3g2b7zPO9vnpkzM4SYny/lIQKOMfMBANv+2HwgoiEGHiqtn8Wxpqgiv7m5hTzvBgN7q2kJeMlB0Kempiaj9IgEFKYC5gcANjg2+Qqi41HScgbypWwD8ysA6x1hloYVQNSltB530TkBMUA5IcYAtLuYrhhD9C6j9U4CApveCcgX4gSAezYzS/2kMua+zcMVaADAEZuZpf5UGWP1cAX6EmEjV+IqKGOsP4MVaKStbVNQLH5KmE5J7qVSjfvGxz9bjovqrUZbWxsXFhc/1gKovq5uc+fERNXJWRMKQXwhCgAaEkJ9zxjTQAAnSigU56QcZOaDCYF8ZUzW5uGUUE7KXma+aTOrWic6r7S+bvNwAhrr6Kj/Vii8B9EOm2GF+mwwPy/2z8z8tOmdgErLJkQXA88BpGym/9R/EfPhTD4/5KJzBipBpdOnmOg2gHoXcwCLDPRljXFe7khAIcSwlFmP+RaA7RaoWQZ6s8YMO8KXhkUGKh0DUl4G80XLJr6itL4UBWYNyCWtFUs22NS0MZVK9SAImkG0blUTZgWi3VUbML8GkV9B/wOeN1UsFge6p6fDi7v8LQPyhTgKIPwjGl1mU4Mx4b12ThnzaMmrDJRLp3cx0UiMcyYpV3hOdWby+TfLNrUv5WMw9yR1j6l/oowp9S4n5AtRi0dYTB6UH29/A1V9FsTt5KpTxpRY1oAqJbZaQnMAtrhGXONxc8qYrcuW7IWUewg4gyBwvclrw+R5CwDuKq1HY99ltSFZ3cXpts+1tLRzEHQnASHPG8xMTr61eTgB+ULcQbicyb5+ZcxZm4UrUD+A0zYzS/2uMsY6qf8TKCflVWa+kCQhJrqW1drq8RuX9AQ08fOD3AAAAABJRU5ErkJggg==) no-repeat 50%;
    background-size: 100%;
    width: 0.4806rem;
    height: 0.4806rem;
    vertical-align: middle;
    margin-top: -0.0534rem;
}

.ddh{
  margin: 0;
    padding: 0;
    border: 0;
    outline: 0;
    font-size: 100%;
    vertical-align: baseline;
    background: transparent;
    font-family: 苹方;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
}
</style>
