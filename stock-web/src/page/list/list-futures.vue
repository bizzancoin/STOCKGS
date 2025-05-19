<template>
  <div :class="`list-content-wrapper ${$state.theme === 'red' ? 'red-theme' : 'black-theme'}`">
    <!-- <mt-header fixed  title="期货">
        <router-link to="/list" slot="left">
            <mt-button icon="back">返回</mt-button>
        </router-link>
    </mt-header> -->
    <ul class="table-list list-table-title">
      <li class="title">
        <div>
          <ul class='clearfix'>
            <li class="li-title">名称<img :src="sortIcon" alt=""></li>
            <li class="li-base">最新<img :src="sortIcon" alt=""></li>
            <!-- <li class="li-base">基币</li> -->
          </ul>
        </div>
      </li>
    </ul>
    <div class="list-table-body">
      <ul class="table-list table-list-body"
          v-infinite-scroll="loadMore"
          infinite-scroll-disabled="loading"
          infinite-scroll-distance="10">
        <li class="list-body" v-for="item in list" :key="item.key">
          <div>
            <ul class="clearfix" :class="item.floatPoint<0?'green':item.floatPoint==0?'':'red'" @click='toDetail(item)'>
              <li class="li-title">
                <p class="name">
                  <img 
                  v-if="item.isOption == '1'" 
                  :src="require(`../../../static/img/list/${$state.theme === 'red' ? 'red-' : ''}loved-icon.png`)" 
                  @click.stop="toDeleteMy(item)"
                  alt="">
                  <img v-else
                  @click.stop="addOptions(item)"
                  :src="require(`../../../static/img/list/${$state.theme === 'red' ? 'red-' : ''}love-icon.png`)" alt="">
                  {{item.futuresName}}
                </p>
                <p class="code code-wra" style="padding-left: 0px">
                  <span class="futures-code" style="float: left;">{{item.futuresCode}}</span>
                </p>
              </li>
              <li class="li-base now-price-li">
                <p class="now">{{item.nowPrice?Number(item.nowPrice).toFixed(2):'-'}}</p>
                <!-- {{item.coinCode}} -->
                <!-- <p class="exchange"> ≈ {{(item.nowPrice * item.coinRate).toFixed(2)}}CNY</p> -->
              </li>
              <!-- <li class="li-base no-bold">
                  <span>{{item.coinCode}}</span>
              </li> -->
            </ul>
          </div>

        </li>
      </ul>
      <div v-if="list.length<=0" class="load-all text-center">
        <mt-spinner type="fading-circle"></mt-spinner>
      </div>
      <div v-show="loading" class="load-all text-center">
        <mt-spinner type="fading-circle"></mt-spinner>
        加载中...
      </div>
      <div v-show="!loading && list.length>0" class="load-all text-center">
        已全部加载
      </div>
      <!-- <div class="footer-btn">
          <p class="red">*注：
              <span><i class="iconfont icon-jiaoyi red"></i>表示该期货可交易</span>
              <span class="pull-right"><i class="iconfont icon-jinzhi yellow"></i>表示该期货不可交易</span>
          </p>
      </div> -->
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
  props: {
    selectedNumber: {
      type: String
    }
  },
  data () {
    return {
      loading: false,
      pageNum: 1,
      pageSize: 15,
      currentNum: 15,
      list: [],
      timer: '',
      sortIcon: require('../../../static/img/list/sort-icon.png')
    }
  },
  watch: {
    selectedNumber (val) {
      if (val === '4') {
        this.getListMarket()
        this.timer = setInterval(this.refreshList, 5000)
      } else {
        clearInterval(this.timer)
      }
    }
  },
  computed: {},
  created () {
    sessionStorage.setItem('qihuo2.0', '')
  },
  beforeDestroy () {
    clearInterval(this.timer)
  },
  mounted () {
  },
  methods: {
    async addOptions(val) {
      let data = await api.addOption({ code: val.futuresCode });
      if (data.status === 0) {
        Toast("添加自选成功");
      } else {
        Toast(data.msg);
      }
    },
    async toDeleteMy (val) {
      let data = await api.delOption({ code: val.futuresCode })
      if (data.status === 0) {
        Toast('删除自选股成功')
        this.refreshList()
      } else {
        Toast(data.msg)
      }
    },
    async getListMarket () {
      // 获取期货列表
      let result = await api.getFutures()
      if (result.status === 0) {
        this.list = result.data
      } else {
        Toast(result.msg)
      }
    },
    async refreshList () {
      if (this.loading) {
        return
      }
      let opt = {
        pageNum: 1,
        pageSize: this.currentNum
      }
      let data = await api.getFutures(opt)
      this.list = data.data
    },
    async loadMore () {
      if (this.list.length < 100) {
        return
      }
      this.loading = true
      // clearInterval(this.timer)
      // 加载下一页
      this.pageNum++
      this.currentNum = this.pageNum * this.pageSize
      await this.getFutures()
      this.loading = false
    },
    async toDetail (val) {
      // 先查询该期货是否可买

      // 可买
      if (val.transState === 1) {
        // this.$router.push({
        //   path: '/futuresBuy',
        //   query: {
        //     info: val
        //   }
        // })


        this.$router.push({
          path: '/listdetail2',
          query: {
            code: val.futuresGid,
            stock_type:'hf',
            qhinfo: val,
            type: 'qh'
            // name: val.name
          }
        })
      } else {
        Toast('该期货暂不能交易!')
      }
    },
    toSearch () {
      this.$router.push('/indexsearchlist')
    }
  }
}
</script>
<style lang="less" scoped>
  .wrapper {
    padding-bottom: 1.6rem;
  }

  .table-list-body {
    // padding-top: 0.62rem;
    // margin-top: 26px;
  }

  .table-list {
    ul {
      .li-title {
        width: 56%;

        .name {
          .iconfont {
            background: none;
            color: #d50000;

            &.icon-jinzhi {
              color: #ff9800
            }
          }
        }
      }
    }

    .li-base {
      width: 44%;
      text-align: center;
      text-align: right;
      padding-right: 0.4rem;

      &.no-bold {
        span {
          font-weight: 400;
        }
      }
    }
  }

  .footer-btn {
    position: fixed;
    z-index: 1;
    width: 100%;
    padding-right: 0;
    bottom: 0.94rem;
    padding: 0.2rem;
    // height: 1.1rem;
    // line-height: .1rem;
    box-shadow: 0px 0px 4px rgba(6, 0, 1, 0.2);

    .iconfont {
      margin: 0 0.1rem;
      font-size: 0.28rem;

      &.yellow {
        color: #ff9800;
      }
    }
  }

  .now-price-li {
    .now {
      font-size: 0.28rem;
      line-height: 0.5rem;
      margin-top: 0.1rem;
    }

    .exchange {
      font-size: 0.22rem;
      line-height: 0.2rem;
      // color: #ddd;
    }
  }
  .list-content-wrapper{
    width: 100%;
    height: 100%;
    position: relative;
    .list-table-title{
      width: 100%;
      position: absolute;
      left: 0;
      top: 0;
      .title{
        position: relative;
        top: 0;
        left: 0;
        ul{
          li{
            font-size:0.28rem;
            font-family:MicrosoftYaHeiLight;
            font-weight:400;
            color:rgba(255,255,255,0.5);
            text-align: center;
            display: flex;
            justify-content: center;
            align-items: center;
            img{
              width: 0.15rem;
              margin-left: 0.06rem;
            }
            &.li-title{
              width: 60%;
            }
            &.li-base{
              width: 40%;
            }
          }
        }
      }
    }
    .list-table-body{
      width: 100%;
      height: 100%;
      box-sizing: border-box;
      overflow-y: auto;
      padding-top: 0.6rem;
      ul{
        li.li-base{
          width: 40%;
          text-align: center;
          span{
            font-size:0.26rem;
            font-family:MicrosoftYaHeiLight;
            font-weight:400;
            color:rgba(177,37,37,1);
          }
        }
        .li-title{
          width: 60%;
          .name{
            color: #fff;
            width: 100%;
            display: flex;
            align-items: center;
            margin-top: 0.05rem;
            img{
              width: 0.32rem;
              margin-right: 0.05rem;
            }
          }
          .kechuang-mark{
            background: none;
            color: #138EB4;
          }
          .code-wra{
            width: 1.35rem;
            height: 0.38rem;
            display: block;
            // background: url(/static/img/list/code-bg.png) no-repeat;
            // background-size: contain;
            // background-color: #138EB4;
            display: flex;
            align-items: center;
            justify-content: flex-start;
            border-radius: 0.05rem;
            color: white;
            position: relative;
            margin-left: 0.37rem;
            span{
              color: white;
              border: 0;
            }
            &::before{
              content: '';
              display: block;
              width: 0;
              height: 0;
              border-top: 0.1rem solid transparent;
              border-right: 0.15rem solid #138EB4;
              border-bottom: 0.1rem solid transparent;
              position: absolute;
              left: 0;
              top: 50%;
              margin-top: -0.095rem;
              left: -0.1rem;
              display: none;
            }
          }
        }
      }
    }
  }
  .red-theme{
      background: white;
    .table-list{
      ul{
        li{
          border-color: #f7f7f7;
        }
      }
    }
    .list-table-title{
      .title{
        background: white;
        ul{
          li{
            color: #212121;
          }
        }
      }
    }
    .list-table-body{
      li.li-title{
        .name{
          color: #000;
        }
        .futures-code{
          color: #BB1715 !important;
        }
      }
    }
    .load-all{
      background-color: #e9e9e9;
      color: #000;
    }
  }
</style>
