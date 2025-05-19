<template>
  <div class="wrapper">
    <!-- <mt-header fixed  title="我的持���">
        <router-link to="/" slot="left">
            <mt-button icon="back"></mt-button>
        </router-link>
    </mt-header> -->
    <mt-navbar class="sub-navbar" v-model="selected">
      <mt-tab-item id="1"></i>我的持仓</mt-tab-item>
      <mt-tab-item id="2">我的平仓</mt-tab-item>
    </mt-navbar>
    <mt-tab-container class="order-list" v-model="selected">
      <mt-tab-container-item id="1">
        <div class="text-center">
          <mt-button @click="toSearchName" type="default">根据股票简拼查询订单</mt-button>
          <mt-button @click="toSearchCode" type="default">根据股票代码查询订单</mt-button>
        </div>
        <holdPosition :selectedNumber='selectedNumber' :handleOptions='handleOptions'/>
      </mt-tab-container-item>
      <mt-tab-container-item id="2">
        <div class="text-center">
          <mt-button @click="toSearchName2" type="default">根据股票简拼查询订单</mt-button>
          <mt-button @click="toSearchCode2" type="default">根据股票代码查询订单</mt-button>
        </div>
        <HistoryList :selectedNumber='selectedNumber' :hasChangeSell="hasChangeSell" :handleOptions='handleOptions'/>
      </mt-tab-container-item>
    </mt-tab-container>
    <!-- <foot></foot> -->
  </div>
</template>

<script>
import foot from '@/components/foot/foot'
// import '@/assets/style/common.less'
import holdPosition from './compontents/hold-position'
import HistoryList from './compontents/history-list'
import indexHoldPosition from './compontents/indexhold-position'
import indexHistoryList from './compontents/indexhistory-list'
// import * as api from '@/axios/api'

export default {
  components: {
    foot,
    holdPosition,
    HistoryList,
    indexHoldPosition,
    indexHistoryList
  },
  props: {
    selectedNumber: {
      type: String
    }
  },
  data () {
    return {
      selected: '1', // 选中
      list: [], // 持仓
      form: {
        pageNum: 1,
        pageSize: 10,
        currentNum: 10
      },
      list2: [], // 平仓
      form2: {
        pageNum: 1,
        pageSize: 10
      },
      hasChangeSell: false,
      hasChangeSell2: false
    }
  },
  watch: {},
  computed: {},
  created () {},
  mounted () {
    //   this.getListDetail()
    //   this.getListDetail2()

  },
  methods: {
    handleOptions (opts) {
      this.hasChangeSell = opts
      //   this.hasChangeSell = { ...this.hasChangeSell, ...opts};
      if (this.hasChangeSell) {
        this.selected = '2'
      }
    },
    handleOptions2 (opts) {
      this.hasChangeSell2 = opts
      if (this.hasChangeSell2) {
        this.selected = '4'
      }
    },
    toSearchName () {
      // 按照持仓名称查持仓订单
      this.$router.push({
        path: '/holdorderlist2',
        query: {
          type: 1 // 查询类型 1--> name 2--> code
        }
      })
    },
    toSearchCode () {
      // 按照持仓代码查持仓订单
      this.$router.push({
        path: '/holdorderlist',
        query: {
          type: 2 // 查询类型 1--> name 2--> code
        }
      })
    },
    toSearchName2 () {
      // 按照持仓名称查持仓订单
      this.$router.push({
        path: '/sellorderlist2',
        query: {
          type: 1 // 查询类型 1--> name 2--> code
        }
      })
    },
    toSearchCode2 () {
      // 按照持仓代码查持仓订单
      this.$router.push({
        path: '/sellorderlist',
        query: {
          type: 2 // 查询类型 1--> name 2--> code
        }
      })
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
        // display: block;
        font-size: 0.46rem;
        margin-bottom: 0.12rem;
        vertical-align: middle;
        margin-right: 0.12rem;
      }
    }
  }
  .sub-navbar {
    background-color: #16171D !important;
    height: .7rem;
    justify-content: center;
    box-shadow: none;

    /deep/.mint-tab-item-label {
        font-size: .24rem;
      }
    .mint-tab-item {
      position: relative;
      flex-grow: 0;
      flex-shrink:1;
      flex-basis:auto;
      width: 1.4rem;
      height: .4rem;
      background-color: #3D4456;
      padding: 0;
      line-height: .4rem;
      margin: 0.3rem .2rem 0;
      /deep/.mint-tab-item-label {
        line-height: .4rem;
        height: .4rem;
      }
      &.is-selected {
        color: #fff;
        background-color: #138EB4;
      }
    }
  }
  #app.red-theme {
    .sub-navbar {
      background-color: #E9E9E9 !important;
      .mint-tab-item {
        background-color: #CCCCCC;
        color: #000;
        &.is-selected {
          color: #fff;
          background-color: #BB1815;
        }
      }
    }
    
  }
</style>
