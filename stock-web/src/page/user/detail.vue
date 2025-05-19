<template>
  <div class="wrapper">
    <!-- <div class="header">
      <mt-header title="资金明细">
        <router-link to="/user" slot="left">
          <mt-button icon="back">我的</mt-button>
        </router-link>
        <mt-button icon="more" slot="right"></mt-button>
      </mt-header>
    </div> -->
    <div class="box page-part">
      <div class="box-contain clearfix">
        <div class="account text-center">
          <p class="title">当前可用余额（元）</p>
          <p class="red number">{{$store.state.userInfo.enableAmt}}</p>
        </div>
        <div class="account-panel">
          <div class="col-xs-6">
            <a href="#/recharge">
              <img src="../../assets/img/recharge_icon.png"><br>
              充值
            </a>
          </div>
          <div class="col-xs-6">
            <a href="#/cash">
              <img src="../../assets/img/withdrew_icon.png"><br>
              提现
            </a>
          </div>
        </div>
      </div>
    </div>
    <div>
      <listDetail/>
    </div>
  </div>
</template>

<script>
import listDetail from './compontents/list-detail'

export default {
  components: {
    listDetail
  },
  props: {},
  data () {
    return {
      listDetail
    }
  },
  watch: {},
  computed: {},
  created () {},
  mounted () {
    if (this.$state.theme =='red') {
        document.body.classList.remove('black-bg')
        document.body.classList.add('red-bg')
    }
  },
  beforeDestroy () {
    if (this.$state.theme =='red') {
      document.body.classList.remove('red-bg')
        document.body.classList.add('black-bg')
    }
  },
  methods: {}
}
</script>
<style lang="less" scoped>
  .account {
    padding-bottom: 0.2rem;

    .title {
      height: 1.4rem;
      line-height: 1.4rem;
      font-size: 0.29rem;
      // color: rgb(51, 51, 51);
      // color: #ddd;
      text-align: center;
      // font-weight: 700;
    }

    .number {
      font-size: 0.566rem;
      font-weight: 600;
    }
  }

  .transaction {
    // color: rgba(100, 100, 100, 0.78);
    color: #ccc;

    .transaction-title {
      padding: 0.15rem 0.3rem;
    }

    .empty {
      height: 2.8rem;
      line-height: 2.8rem;
    }
  }

  .account-panel {
    div {
      text-align: center;
    }

    a {
      width: 50%;
      text-align: center;
      margin: 0 auto;

      img {
        width: 0.48rem;
        margin-bottom: 0.1rem;
      }
    }
  }
</style>
