<template>
  <div class="wrapper">
    <mt-navbar class="top-navbar" v-model="selected" fixed>
      <mt-tab-item
      class="top-nav-item" id="1">我的配资</mt-tab-item>
      <mt-tab-item
      class="top-nav-item" id="2">配资持仓</mt-tab-item>
      <mt-tab-item
      class="top-nav-item" id="3">配资平仓</mt-tab-item>
    </mt-navbar>
    <mt-tab-container class="order-list" v-model="selected">
      <mt-tab-container-item id="1">
        <List1 :selectedNumber='selected'/>
      </mt-tab-container-item>
      <mt-tab-container-item id="2">
        <List2 :selectedNumber='selected'/>
      </mt-tab-container-item>
      <mt-tab-container-item id="3">
        <List3 :selectedNumber='selected'/>
      </mt-tab-container-item>
    </mt-tab-container>
    <foot></foot>
  </div>
</template>

<script>
import foot from '@/components/foot/foot'
// import '@/assets/style/common.less'
import List1 from './funds-list1'
import List2 from './funds-list2'
import List3 from './funds-list3'
import * as api from '@/axios/api'
import { Toast } from 'mint-ui'

export default {
  components: {
    foot,
    List1,
    List2,
    List3
  },
  props: {},
  data () {
    return {
      selected: '1' // 选中

    }
  },
  watch: {},
  computed: {},
  created () {
    if (!this.$store.state.userInfo.phone) {
      this.getUserInfo()
    }
  },
  mounted () {
    if (this.$route.query.index) {
      this.selected = this.$route.query.index
    }
    this.getProductSetting()
  },
  methods: {
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
      } else {
        this.$message.error(data.msg)
      }
    }
  }
}
</script>
<style lang="less" scoped>
.wrapper {
  background-color: #16171d;
}
  .is-selected .mint-tab-item-label:hover {
    text-decoration: none;
  }

  .wrapper /deep/ .mint-tab-item-label {
    font-size: 0.264rem;
  }

  .mint-navbar .mint-tab-item.is-selected {
    border-bottom: 2px solid #d50000;
    text-decoration: none;
    box-shadow: none;
  }

  .mint-tab-container-item {
    padding-top: 0.8rem;

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
    // box-shadow: 0px 0px 4px rgba(6,0,1,0.2);
    .mint-tab-item {
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
    top: 1rem !important;
    background-color: #16171D !important;
    justify-content: center;
    .mint-tab-item {
      padding: 0.3rem 0;
      background-color: #16171D;
      
    }
    .top-nav-item{
      position: relative;
      flex-grow: 0;
      flex-shrink:1;
      flex-basis:auto;
      width: 1.5rem;
      /deep/.mint-tab-item-label {
        font-size: .24rem;
      }
    }
    .top-nav-item.is-selected {
      color : #fff;
    }
    .top-nav-item.is-selected :after {
        display: block;
        content:'';
        position: absolute;
        width: .7rem;
        height: .06rem;
        background-color: #138EB4;
        left: .4rem;
        bottom: 0.05rem;
      }
  }
  /deep/.order-info-box {
    background-color: #08080D;
    .main {
      font-size: .3rem;
    }
    .order-title {
      border-bottom: none;
      height: 0.7rem;
      .space {
        font-size: .24rem;
      }
      .type-up{
        color : #fff;
        border: none;
        background-image: url(../../assets/ico/zhang.png);
        background-size: cover;
        padding-left: .2rem;
      }
      .type-down{
        color : #fff;
        border: none;
        background-image: url(../../assets/ico/die.png);
        background-size: cover;
        padding-left: .2rem;
      }
    }
    .code {
      margin-bottom: .3rem;
    }
    .table-title {
      font-size: .24rem;
      
    }
    .table-value {
      font-size: .24rem;
      color: #ccc;
    }
    .order-foot {
      padding: 0;
      .foot-btn{
        border-color: #B12525;
        background-color: #B12525;
        color: #fff;
        padding: 0 0.1rem;
        height: .34rem;
        line-height: .34rem;
        font-size: .2rem;
      }
    }
  }

  #app.red-theme {
    .top-navbar {
      background-color: #E9E9E9 !important;
      .mint-tab-item {
        background-color: #E9E9E9; 
      }
      .top-nav-item.is-selected {
        color : #000;
      }
      .top-nav-item.is-selected :after {
        background-color: #BB1815;
      }
    }
    .sub-navbar{
      background-color: #E9E9E9;
    }
    .wrapper{
      background-color: #E9E9E9;
    }
    .load-all {
      background: #fff !important;
    }
    
  }
  /deep/.red-theme .order-info-box{
      background-color: #fff ;
  }
</style>
