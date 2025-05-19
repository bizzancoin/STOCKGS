<template>
  <div :class="`wrapper ${$state.theme === 'red' ? 'red-theme' : 'black-theme'}`" style="background-color: #21212b;">
    <div>
      <div @click="list_type=!list_type">1</div>
    </div>
    <!-- <mt-header v-if="selected == '2'" fixed  title="">
        <router-link to="/" slot="left">
        </router-link>
        <mt-button slot="right" icon="search" @click="toSearch"></mt-button>
    </mt-header> -->

   <!-- <mt-navbar class="top-navbar" v-model="selected" :style="selected != '2'?'':''" :fixed="selected != '2'?true:false">

      <mt-tab-item v-if="this.$store.state.settingForm.indexDisplay" id="1">指数</mt-tab-item>
      <mt-tab-item v-if="this.$store.state.settingForm.stockDisplay" id="2">沪深</mt-tab-item>
      <mt-tab-item v-if="this.$store.state.settingForm.kcStockDisplay" id="3">科创</mt-tab-item>
      <mt-tab-item v-if="this.$store.state.settingForm.futuresDisplay" id="4">期货</mt-tab-item>
    </mt-navbar> -->
    <!-- <mt-tab-container class="order-list" v-model="selected">
      <mt-tab-container-item v-if="this.$store.state.settingForm.indexDisplay" id="1">
        <List1 :selectedNumber='selected'/>
      </mt-tab-container-item>
      <mt-tab-container-item v-if="this.$store.state.settingForm.stockDisplay" id="2">
        <List2 :selectedNumber='selected'/>
      </mt-tab-container-item>
      <mt-tab-container-item v-if="this.$store.state.settingForm.kcStockDisplay" id="3">
        <List3 :selectedNumber='selected'/>
      </mt-tab-container-item>
      <mt-tab-container-item v-if="this.$store.state.settingForm.futuresDisplay" id="4">
        <List4 :selectedNumber='selected'/>
      </mt-tab-container-item>
    </mt-tab-container> -->
    <!-- <foot></foot> -->
    <div style="background-color: #21212b;">
      <div class="list-title">
        <div class="list-left">代码</div>
        <div class="list-conter">最in假</div>
        <div class="list-right">涨跌幅</div>
      </div>
      <div class="list-content" v-for="item in 20">
        <div class="list-left">
          <div class="taini">
            <span class="circular">●</span>
            太腻<span v-show="list_type" class="minCaption">(太腻)</span>
          </div>
          <div v-show="!list_type" class="list-code">1101</div>
        </div>
        <div class="list-conter">
          <div class="down">30.25</div>
          <div v-show="!list_type" class="list-code">最低:30.25</div>
        </div>
        <div class="list-right">
          <div class="chgTextRed">-0.82%</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

// import '@/assets/style/common.less'
import List0 from './list-all'
import List1 from './list-index'
import List2 from './list-stock'
import List3 from './list-kechuang'
import List4 from './list-futures'
import * as api from '@/axios/api'
import { Toast } from 'mint-ui'

export default {
  components: {

    List0,
    List1,
    List2,
    List3,
    List4,
  },
  props: {},
  data () {
    return {
      selected: '', // 选中
      count: 1000,
      loading: false,
      list_type:false,
    }
  },
  watch: {},
  computed: {},
  created () {
    this.getProductSetting()
    if (!this.$store.state.userInfo.phone) {
      this.getUserInfo()
    }
  },
  mounted () {
    if (this.$route.query.index) {
      this.selected = this.$route.query.index
    }
  },
  methods: {
    toSearch () {
      this.$router.push('/searchlist')
    },
    changeNavOptions (opts) {
      this.selected = opts
    },
    async getUserInfo () {
      // 获取用户信息
      let data = await api.getUserInfo()
      if (data.status === 0) {
        this.$store.state.userInfo = data.data
      } else {
        Toast(data.msg)
      }
    },
    async getProductSetting () {
      let data = await api.getProductSetting()
      if (data.status === 0) {
        this.$store.state.settingForm = data.data
        // 1 指数 2 沪深 3科创 4 期货
        if (this.$store.state.settingForm.indexDisplay) {
          this.selected = '1'
        } else if (this.$store.state.settingForm.stockDisplay) {
          this.selected = '2'
        } else if (this.$store.state.settingForm.kcStockDisplay) {
          this.selected = '3'
        } else {
          this.selected = '4'
        }
      } else {
        this.$message.error(data.msg)
      }
    }
  }
}
</script>
<style lang="less" scoped>
  .is-selected .mint-tab-item-label:hover {
    text-decoration: none;
  }

  .wrapper /deep/ .mint-tab-item-label {
    font-size: 0.264rem;
  }

  .mint-navbar .mint-tab-item.is-selected {
    border-bottom: 2px solid #d50000;
    text-decoration: none;
  }

  .nav-wrapper {
    padding-top: 0.8rem;
  }

  .mint-tab-container-item {
    // padding-top: 1.2rem;

    .mint-button--default {
      padding: 0 0.2rem;
      font-size: 0.24rem;
      height: 0.5rem;
      margin: 0.2rem 0.2rem 0;
      color: #607d8b;
      box-shadow: 0 0 1px #3b71b9;
      background: none;
    }
  }

  .mint-navbar {
    box-shadow: 0px 0px 4px rgba(6, 0, 1, 0.2);

    .mint-tab-item {
      // background: #21252a;
      padding: 0.2rem 0;

      &.is-selected {
        border: none;
        margin-bottom: 0;
      }

      .mint-tab-item-label {
        font-size: 0.22rem;
      }

      .iconfont {
        display: block;
        font-size: 0.46rem;
        margin-bottom: 0.12rem;
      }
    }
  }

  .top-navbar {
    .mint-tab-item {
      padding: 0.2rem 0;
      width: 1.42rem;
      height: 0.44rem;
      margin: 0.3rem 0.1rem 0 0.1rem;
      display: flex;
      justify-content: center;
      align-items: center;
      border-radius: 0.01rem;
    }
  }
  .wrapper{
    width: 100%;
    height: 100%;
    position: relative;
    box-sizing: border-box;
    padding-top: 1rem;
    .top-navbar{
      position: absolute;
      top: 0;
      left: 50%;
      width: 70%;
      margin-left: -35%;
      background: none;
      box-shadow: none;
      /deep/.mint-tab-item{
        .mint-tab-item-label{
          font-size:0.28rem;
          font-family:MicrosoftYaHeiLight;
          font-weight:400;
          color:rgba(255,255,255,1);
        }
        &.is-selected{
          position: relative;
          background:linear-gradient(0deg,rgba(27,166,208,1),rgba(2,116,150,1));
          &::after{
            position: absolute;
            content: '';
            display: block;
            width: 0.67rem;
            height: 0.06rem;
            background-color: #138EB4;
            bottom: 0;
            left: 50%;
            margin-left: -0.335rem;
            display: none;
          }
        }
      }
    }
    .order-list{
      width: 100%;
      height: 100%;
      /deep/.mint-tab-container-wrap{
        width: 100%;
        height: 100%;
      }
    }
  }
  .top-search{
    padding: .2rem;
    .top-search-btn {
      background-color: #16171d;
      padding: .1rem .2rem;
      width: 1.60rem;
      text-align: center;
      border-radius: .2rem;
      color: #fff;
      border: 1px solid rgb(96, 125, 139);
      margin: 0 auto;
    }
  }
  .red-theme{
     .top-search-btn {
       border-color: #000;
       color: #000;
       background-color: #fff;
     }
    .top-navbar{
      /deep/.mint-tab-item{
        background-color: #CBCBCB;
        .mint-tab-item-label{
          color: #000;
        }
        &.is-selected{
          background: #BB1715;
          .mint-tab-item-label{
            color: #fff;
          }
        }
      }
    }
  }
  .list-title{
    display: flex;
    width: calc(100vw - 32px);
    margin-left: 0;
    margin-right: 0;
    margin: 0 auto;
    color: #8b8896;
    border-bottom: #000 0.01rem solid;
    align-items: center;
    padding-top: 0.28rem;
    padding-bottom: 0.28rem;
  }
  .taini{
    color: #f1f0f5;
    font-size: 0.38rem;
  }
  .circular{
    color: rgb(169,169,169);
    font-size: 0.4rem;
  }
  .list-left{
    width: 39.44986%;
  }
  .down{
    font-size: 0.38rem;
        color: #ef534f;
  }
  .list-code{
    padding-left: 0.35rem;
    margin-top: 0.2rem;
    transition: all 0.2s;
  }
  .list-content{
    display: flex;
    width: calc(100vw - 32px);
    margin-left: 0;
    margin-right: 0;
    margin: 0 auto;
    color: #8b8896;
    border-bottom: #000 0.01rem solid;
    align-items: center;
    padding-top: 0.28rem;
    padding-bottom: 0.28rem;
    transition: all 0.2s;
    background-color: #21212b;
  }

  .list-conter{
    width: 21.90556%;
    text-align: right;
  }
  .list-right{
    width:  38.64456%;
    text-align: right;
  }
  .chgTextRed{
    float: right;
    font-size: 0.38rem;
    width: 50%;
    border-radius: 2px;
    color: #fff!important;
    text-align: center;
    height: 0.65rem;
    line-height: 0.65rem;
    background-color: #ef534f;
  }
  .minCaption{
    color: #8b8896;
    font-size: 0.16rem;
    transition: all 0.2s;
  }
</style>
