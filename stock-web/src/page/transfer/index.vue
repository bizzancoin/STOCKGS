<template>
  <div class="wrapper">
    <div class="header">
      <div class="left_back" @click="handleBackClick()">
        <img src="../../assets/img/zuojiantou.png" alt="">
      </div>
      <div class="header_titles">
      </div>
    </div>
    <div class="bars">
      <div>
        <span>{{ $t('hj187') }}</span>
      </div>
    </div>
    <mt-navbar v-model="selected">
      <mt-tab-item v-if="this.$store.state.settingForm.indexDisplay" id="1">{{ $t('hj188') }}</mt-tab-item>
      <mt-tab-item v-if="this.$store.state.settingForm.indexDisplay" id="2">{{ $t('hj189') }}</mt-tab-item>
      <!-- <mt-tab-item v-if="this.$store.state.settingForm.futuresDisplay" id="3">融资转期货</mt-tab-item>
      <mt-tab-item v-if="this.$store.state.settingForm.futuresDisplay" id="4">期货转融资</mt-tab-item> -->
    </mt-navbar>
    <mt-tab-container class="order-list" v-model="selected">
      <mt-tab-container-item id="1">
        <div class="form-block">
          <mt-field :label="$t('hj190')" :placeholder="$t('hj190')" type="text" disabled
            v-model="this.$store.state.userInfo.enableAmt">
          </mt-field>
        </div>
        <div class="form-block">
          <mt-field :label="$t('hj191')" name="amt" v-model="form.account1" :placeholder="$t('hj192')" type="text">
            <span @click="selectAll1">{{ $t('hj160') }}</span>
          </mt-field>
        </div>
        <!-- <div class="form-block">
            <mt-field label="资金密码" placeholder="资金密码" type="password" v-model="form.password"></mt-field>
        </div>
        <p class="prompt">资金密码默认为登录密码</p> -->
        <div class="btnbox">
          <span class="text-center btnok loginout" @click="tosubmit">{{ $t('hj193') }}</span>
        </div>
      </mt-tab-container-item>
      <mt-tab-container-item id="2">
        <div class="form-block">
          <mt-field :label="$t('hj190')" :placeholder="$t('hj190')" type="text" disabled
            v-model="this.$store.state.userInfo.enableIndexAmt"></mt-field>
        </div>
        <div class="form-block">
          <mt-field :label="$t('hj191')" v-model="form.account2" :placeholder="$t('hj192')" type="text">
            <span @click="selectAll2">{{ $t('hj160') }}</span>
          </mt-field>
        </div>
        <div class="btnbox">
          <span class="text-center btnok loginout" @click="tosubmit">{{ $t('hj194') }}</span>
        </div>
      </mt-tab-container-item>
      <mt-tab-container-item id="3">
        <div class="form-block">
          <mt-field label="可转金额" placeholder="可转金额" type="text" disabled v-model="this.$store.state.userInfo.enableAmt">
          </mt-field>
        </div>
        <div class="form-block">
          <mt-field label="转账金额" v-model="form.account3" placeholder="请输入转账金额" type="text">
            <span @click="selectAll3">全部</span>
          </mt-field>
        </div>
        <div class="btnbox">
          <span class="text-center btnok loginout" @click="tosubmit">确认转入期货账户</span>
        </div>
      </mt-tab-container-item>
      <mt-tab-container-item id="4">
        <div class="form-block">
          <mt-field label="可转金额" placeholder="可转金额" type="text" disabled
            v-model="this.$store.state.userInfo.enableFuturesAmt"></mt-field>
        </div>
        <div class="form-block">
          <mt-field label="转账金额" v-model="form.account4" placeholder="请输入转账金额" type="text">
            <span @click="selectAll4">全部</span>
          </mt-field>
        </div>
        <div class="btnbox">
          <span class="text-center btnok loginout" @click="tosubmit">确认转入融资账户</span>
        </div>
      </mt-tab-container-item>
    </mt-tab-container>
  </div>
</template>

<script>
// import '@/assets/style/common.less'
import * as api from '@/axios/api'
import { Toast } from 'mint-ui'

export default {
  components: {
  },
  data() {
    return {
      selected: '1', // 选中
      form: {
        account1: '',
        account2: '',
        account3: '',
        account4: '',
        password: ''
      },
      userInfo: {
        realName: ''
      }
    }
  },
  watch: {},
  computed: {},
  created() {
    this.getProductSetting()
  },
  mounted() {
    if (this.$route.query.type) {
      this.selected = this.$route.query.type + ''
    }
    this.getUserInfo()
  },
  methods: {
    async getProductSetting() {
      let data = await api.getProductSetting()
      console.log(data)
      if (data.status === 0) {
        this.$store.state.settingForm = data.data
        if (!this.$store.state.settingForm.indexDisplay) {
          this.selected = '3'
        }
      } else {
        this.$message.error(data.msg)
      }
    },
    handleBackClick() {
      this.$router.go(-1);
    },
    selectAll1() {
      // 选择全部
      this.form.account1 = this.$store.state.userInfo.enableAmt
    },
    selectAll2() {
      // 选择全部
      this.form.account2 = this.$store.state.userInfo.enableIndexAmt
    },
    selectAll3() {
      // 选择全部
      this.form.account3 = this.$store.state.userInfo.enableAmt
    },
    selectAll4() {
      // 选择全部
      this.form.account4 = this.$store.state.userInfo.enableFuturesAmt
    },
    async tosubmit() {
      // 融资转指数
      let opt = {
        amt: this.selected === '1' ? this.form.account1 : this.selected === '2' ? this.form.account2 : this.selected === '3' ? this.form.account3 : this.form.account4,
        type: this.selected // 1 融资转指数 2 指数转融资
      }
      let data = await api.AmtChange(opt)
      if (data.status === 0) {
        Toast(data.msg)
        this.$router.push('/user')
      } else {
        Toast(data.msg)
      }
    },
    async getUserInfo() {
      // 获取用户信息
      let data = await api.getUserInfo()
      if (data.status === 0) {
        this.$store.state.userInfo = data.data
      } else {
        Toast(data.msg)
      }
    }
  }
}
</script>
<style lang="less" scoped>
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

.form-block {
  width: 100%;
  height: 1.5rem;
  margin-top: .3rem;
}

/deep/ .mint-field-other {
  padding-right: .3rem;
}

/deep/ .mint-cell-wrapper {
  height: 100%;
  border: none;
  background: rgb(245, 245, 245);
  border-radius: .15rem;
}

.is-selected {
  background: rgb(235, 235, 235) !important;
  border-radius: .15rem;
}

.mint-navbar {
  padding: 0 .3rem;
}

.btnbox {
  width: 94%;
  margin-top: .1rem;
}

.loginout {
  height: 1.2rem !important;
  line-height: 1.2rem !important;
}

.int-cell {
  width: 100%;
  height: 100%;
}

a {
  width: 100%;
  height: 100%;

  .mint-cell-wrapper {
    width: 100%;
    height: 100%;
  }
}

.bars {
  width: 100%;
  height: 6rem;
  display: flex;
  padding: 0 .3rem;
  align-items: flex-end;

  >div {
    margin-bottom: 1.2rem;
    font-size: .65rem;

    span {
      font-weight: 600;
    }
  }
}

.mint-cell.mint-field {
  background: #fff !important;
  color: #000 !important;
}

/deep/ .mint-cell-text {
  color: #000 !important;
}

.text-center.btnok {
  display: inline-block;
  height: 1rem;
  line-height: 1rem;
  background: #2d6ae9;
  border: none;
  border-radius: .1rem;
}

.wrapper {
  width: 100%;
  height: 100%;
  background: #fff;
}

/deep/.mint-cell-wrapper {

  span {
    font-size: 0.35rem !important;
  }

  /deep/input {
    font-size: 0.35rem !important;
  }
}

/deep/.mint-cell-value {
  font-size: 0.35rem !important;
}

/deep/.mint-tab-item-label {
  font-size: 0.35rem !important;
}

/deep/.loginout {
  font-size: 0.35rem !important;
}
</style>
