<template>
  <div>
    <div v-if="list.length <= 0" class="empty text-center">
      {{ $t('hj228') }}
    </div>
    <div v-else>
      <ul class="table-list" v-infinite-scroll="loadMore" infinite-scroll-disabled="loading"
        infinite-scroll-distance="10">
        <li class="list-body" v-for="(item) in list" :key="item.key">
          <div class="order-info-box">
            <div class="order-title">
              <span :class="['main', item.payChannel == 0 ? 'ali' : item.payChannel == 1 ? 'cart' : 'wechat']">
                <!-- <i v-if="item.payChannel == 0 || item.payChannel == '支付宝'" style="color:#1296db;"
                           class="iconfont icon-zhifubao"></i>
                        <i v-if="item.payChannel == '微信' " style="color:#1296db;" class="iconfont icon-weixin"></i>
                        <i v-if="item.payChannel == 1 || item.payChannel == '对公转账'" style="color:#1296db;"
                           class="iconfont icon-yinlian"></i> -->
                {{ item.payChannel == 0 ? $t('hj229') : item.payChannel == 1 ? $t('hj230') : item.payChannel }}
              </span>
              <span class="payNumber">{{$t('hj172')}}：<span :style="{ color: $state.theme == 'red' ? '#BB1815' : '' }">￥{{
                  item.payAmt
              }}</span></span>
              <span
                :class="item.orderStatus == 1 ? 'green pull-right' : item.orderStatus == 2 ? 'red pull-right' : 'red pull-right'">
                <i v-if="item.orderStatus == 1" class="iconfont icon-tongguo4 animated bounceIn"></i>
                <i v-if="item.orderStatus == 0" class="iconfont icon-dengdai animated bounceInDown"></i>
                <i v-if="item.orderStatus == 2" class="iconfont icon-failure animated bounceInDown"></i>
                <i v-if="item.orderStatus == 3" class="iconfont icon-iconfontweitongguo animated bounceInDown"></i>
                <!-- 1 => 成功 2 失败 3取消 4 等待 -->
                {{ item.orderStatus == 1 ? $t('hj231') : item.orderStatus == 2 ? $t('hj232') : item.orderStatus == 3 ? $t('hj233') :
                    $t('hj202')
                }}

              </span>
              <!-- <span class="secondary ">123456789</span> -->
            </div>
            <div class="order-info">
              <!-- <p class="clearfix">
                <span class="col-xs-5">{{item.orderDesc}}</span>
              </p> -->
              <!-- <p class="clearfix">
                <span class="col-xs-12">订单号:<b>{{item.orderSn}}</b></span>
              </p>
              <p class="clearfix">
                        <span class="secondary col-xs-6">时间:
                            <b v-if="item.addTime">{{new Date(item.addTime) | timeFormat}}</b>
                            <b v-else></b>
                        </span>
              </p> -->
              <div class="info-mix ">
                <span class="info-item">{{$t('hj234')}}:<b>{{ item.orderSn }}</b></span>
                <span class="info-item">{{$t('hj80')}}:
                  <b v-if="item.addTime">{{ new Date(item.addTime) | timeFormat }}</b>
                  <b v-else></b>
                </span>
              </div>
            </div>

          </div>
          <!-- <div class="capital">
              <div class="pro">
                  {{item.payChannel}} <span class="pull-right">金额:{{item.payAmt}}</span>
              </div>
              <div class=" clearfix">
                  <div class="col-xs-4"></div>
                  <div class="col-xs-8">
                      <span class="pull-right">
                          {{new Date(item.addTime) | timeFormat}}
                      </span>
                  </div>
              </div>
          </div> -->
        </li>
      </ul>
      <div v-show="loading" class="load-all text-center">
        <mt-spinner type="fading-circle"></mt-spinner>
        {{$t('hj235') }}
      </div>
      <div v-show="!loading" class="load-all text-center">
        {{$t('hj236') }}
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
      pageSize: 15,
      total: 0
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
        payChannel: '', // 支付方式
        orderStatus: '', // 订单状态
        pageNum: this.pageNum,
        pageSize: 15
      }
      let data = await api.rechargeList(opt)
      if (data.status === 0) {
        data.data.list.forEach(element => {
          this.list.push(element)
        })
        this.total = data.data.total
      } else {
        Toast(data.msg)
      }
    },
    async loadMore() {
      if (this.list.length < 10 || this.total <= this.pageNum * this.pageNum) {
        return
      }
      this.loading = true
      // 加载下一页
      this.pageNum++
      await this.getListDetail()
      this.loading = false
    }
  }
}
</script>
<style lang="less" scoped>
.wrapper {
  padding-top: 0.9rem;
}

.table-list {
  padding: 0.2rem 0;

  .list-body {
    padding: 0.1rem 0.3rem;

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

.payNumber {
  font-size: 0.24rem;
  font-weight: bold;

  span {
    font-family: lightnumber;
  }
}

/deep/.order-info-box {
  background-color: #16171d;
  padding: 0;

  .main {
    padding: 0.05rem .15rem;
    letter-spacing: 0;
    text-align: center;
    margin-right: .16rem;
    font-size: 0.24rem;
    border-radius: 3px;

    &.ali {
      background-color: #138EB4;
    }

    &.cart {
      background-color: #7266BA;
    }

    &.wechat {
      background-color: #009C46;
    }
  }

  .order-info {
    border-bottom: 1px solid #2e3237;
    padding-bottom: .3rem;
  }

  .order-title {
    border-bottom: none;
  }

  .info-mix {
    display: flex;
    font-size: .2rem;
    width: 100%;

    .info-item {
      margin-right: .2rem;
      color: #fff8;
    }
  }
}

.red-theme {
  .list-body {
    background-color: #fff;
  }

  .order-info-box {
    background-color: #fff;

    .order-info {
      border-bottom-color: #e9e9e9;
    }
  }

  .order-info-box .main.cart {
    color: #fff;
  }

  .payNumber {
    color: #000;
  }

  .order-info-box .info-mix .info-item {
    color: #666666;
  }

  .load-all {
    background-color: #fff;
  }
}
</style>
