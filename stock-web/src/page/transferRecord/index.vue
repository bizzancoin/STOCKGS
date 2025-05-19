<template>
  <div class="wrapper">
    <div class="header">
      <div class="left_back" @click="handleGoToBack()">
        <img src="../../assets/img/zuojiantou.png" alt="">
      </div>
      <div class="header_titles">
        <span>{{ $t('hj168') }}</span>
      </div>
    </div>
    <!-- <div class="text-center">
      <div class="btn-group">
        <a href="#/recharge" class="with-draw-btn">充值</a>
        <a href="javascript:;" class="with-draw-detai-btn on">记录</a>
      </div>
    </div> -->
    <div class="history_content">
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
    handleGoToBack() {
      this.$router.go(-1);
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
    background-color: #ffffff !important;
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
  .header {
    width: 100%;
    height: 1.5rem;
    background: #fff;
    position: fixed;
    z-index: 999;
    border-radius: 0 0 .15rem .15rem;
    .left_back {
      width: 1rem;
      height: 100%;
      left: 0;
      position: absolute;
      display: flex;
      justify-content: center;
      align-items: center;
      img {
        width: .6rem;
        height: .6rem;
      }
    }
    .header_titles {
      width: 100%;
      height: 100%;
      text-align: center;
      font-size: .4615rem;
      line-height: 1.5rem;
      span {
        font-weight: 600;
      }
    }
  }
  .history_content {
    height: calc(100% - 1.7rem);
  }
</style>
