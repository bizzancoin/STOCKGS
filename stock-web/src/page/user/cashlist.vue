<template>
  <div class="wrapper">
    <!-- <div class="header">
      <mt-header title="提现记录">
        <router-link to="/user" slot="left">
          <mt-button icon="back">我的</mt-button>
        </router-link>
      </mt-header>
    </div> -->
    <div class="text-center">
      <div class="btn-group">
        <a href="#/cash" class="with-draw-btn">提现</a>
        <a href="javascript:;" class="with-draw-detai-btn on">记录</a>
      </div>
    </div>
    <div>
      <div class="box page-part transaction">
        <div class="box-contain clearfix">
          <cashList></cashList>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import cashList from './compontents/cash-list'

export default {
  components: {
    cashList
  },
  props: {},
  data () {
    return {
      number: ''
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
  methods: {
    toSure () {
      // 确定
    }
  }
}
</script>
<style lang="less" scoped>
  .mint-header {
    // background: #f4f4f4;
    margin-bottom: 0.2rem;
  }

  .btn-group {
    // background: #f4f4f4;
    text-align: center;
    margin: 0 auto;
    margin-bottom: 0.5rem;

    a {
      display: inline-block;
      text-align: center;
      font-size: 0.29rem;
      height: 0.7rem;
      line-height: 0.6rem;
      width: 1.44rem;
      margin: 0;
      margin-top: 0;
      padding: 0;
    }

    .with-draw-btn {
      position: relative;
      right: -10px;
      width: 1.51rem;
      border-top-left-radius: 0.695rem;
      border-bottom-left-radius: 0.695rem;
    }

    .with-draw-detai-btn {
      border-top-right-radius: 0.695rem;
      border-bottom-right-radius: 0.695rem;
    }

    .on {
      color: #fff;
    }
  }

  .transaction {
    margin-top: 0.2rem;
    color: rgba(100, 100, 100, 0.78);

    .transaction-title {
      padding: 0.15rem 0.3rem;
    }

    .empty {
      height: 2.8rem;
      line-height: 2.8rem;
    }
  }
</style>
