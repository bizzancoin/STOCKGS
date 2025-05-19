<template>
  <div class="wrapper">
    <div class="header">
      <mt-header title="合作协议">
        <router-link to="/user" slot="left">
          <mt-button icon="back">返回</mt-button>
        </router-link>
        <!-- <mt-button icon="more" slot="right"></mt-button> -->
      </mt-header>
    </div>
    <div style="margin:12px 20px;">
      <h1>委托投资合作协议</h1>
      <p></p>
      <p>本合作协议(下称“本协议”)由以下各方签署：</p>
      <p>甲方</p>
      <p>第一条 总则</p>
      <p>
        1，甲方是中金所、期货公司、期货风险子公司等规定的股指及商品期权合格机构投资者，并有参与交易的资格和权限，乙方是对金融市场投资风险有独立认知和投资经验的自然人，在金融市场有投资能力和抗风险能力，认可并了解中金所、期货公司、期货风险子公司的交易规则，委托甲方参与股指及商品期权投资。</p>
    </div>
    <div class="agree-footer text-center">
      <div class="agree">
        <p @click="isAgree">
          <i :class="agree?'glyphicon glyphicon glyphicon-ok-sign red':'glyphicon glyphicon-ok-circle'"></i>
          我同意
          <a href="#">《委托合作协议》</a>
        </p>
      </div>
      <div class="btn-box">
        <mt-button :class="agree?'btn btn-red':'btn btn-default'" size="small" @click="toBuy()">确定</mt-button>
        <mt-button class="btn btn-cancel" size="small" @click="toBuy">取消</mt-button>
      </div>
    </div>
  </div>

</template>
<script>
export default {
  data () {
    return {
      agree: false
    }
  },
  methods: {
    isAgree () {
      let i = false
      let j = true
      this.agree = this.agree ? i : j
    },
    toBuy () {
      this.$router.push('/buy')
    }
  }
}
</script>
<style lang="less" scoped>
  h1 {
    font-size: 0.25rem;
    text-align: center;
  }

  h4 {
    font-size: 13px;
    line-height: 25px;
    margin: 25px 0;
  }

  p {
    font-size: 12px;
    line-height: 25px;
    text-indent: 2em;
  }

  .agree-footer {
    .agree {
      padding: 0.2rem 0;
    }

    a {
      color: #177be1;
    }

  }
</style>
