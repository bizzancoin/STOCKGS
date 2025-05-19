<template>
  <div class="wrapper">
    <div class="header">
      <!-- <mt-header fixed title="充值记录">
        <router-link to="/user" slot="left">
          <mt-button icon="back">我的</mt-button>
        </router-link>
      </mt-header> -->
    </div>
    <!-- <div class="text-center">
      <div class="btn-group">
        <a href="#/recharge" class="with-draw-btn">充值</a>
        <a href="javascript:;" class="with-draw-detai-btn on">记录</a>
      </div>
    </div> -->
    <div>
      <div class="box page-part transaction">
        <div class="box-contain clearfix">
          <rechargeList></rechargeList>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import rechargeList from './compontents/recharge-list'

export default {
  components: {
    rechargeList
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
  mounted () {},
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

  .wrapper {
    // padding-top: 1.5rem;
    background-color: #16171d;
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
    color: rgba(100, 100, 100, 0.78);

    .transaction-title {
      padding: 0.15rem 0.3rem;
    }

    .empty {
      height: 2.8rem;
      line-height: 2.8rem;
    }
  }

  .box.page-part.transaction {
    background-color: #16171d;
    .box-contain {
      background-color: #16171d;
    }
  }
  .red-theme {
    .wrapper{
      background-color: #E9E9E9;
    }
    .box.page-part.transaction {
      background-color: #E9E9E9;
      .box-contain {
      background-color: #E9E9E9;
        .list-body{
          background-color: #fff;
          .order-info-box {
            background-color: #fff;
          }
        }
      }
    }
    
  }
</style>
