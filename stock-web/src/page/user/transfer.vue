<template>
  <div class="wrapper">
    <div class="header">
      <mt-header title="账户资金互转">
        <router-link to="/user" slot="left">
          <mt-button icon="back">我的</mt-button>
        </router-link>
      </mt-header>
    </div>
    <mt-navbar v-model="selected">
      <mt-tab-item v-if="this.$store.state.settingForm.indexDisplay" id="1">融资转指数</mt-tab-item>
      <mt-tab-item v-if="this.$store.state.settingForm.indexDisplay" id="2">指数转融资</mt-tab-item>
      <mt-tab-item v-if="this.$store.state.settingForm.futuresDisplay" id="3">融资转期货</mt-tab-item>
      <mt-tab-item v-if="this.$store.state.settingForm.futuresDisplay" id="4">期货转融资</mt-tab-item>
    </mt-navbar>
    <mt-tab-container class="order-list" v-model="selected">
      <mt-tab-container-item id="1">
        <div class="form-block">
          <mt-field label="可转金额" placeholder="可转金额" type="text" disabled v-model="this.$store.state.userInfo.enableAmt">
          </mt-field>
        </div>
        <div class="form-block">
          <mt-field label="转账金额" name="amt" v-model="form.account1" placeholder="请输入转账金额" type="text">
            <span @click="selectAll1">全部</span>
          </mt-field>
        </div>
        <!-- <div class="form-block">
            <mt-field label="资金密码" placeholder="资金密码" type="password" v-model="form.password"></mt-field>
        </div>
        <p class="prompt">资金密码默认为登录密码</p> -->
        <div class="btnbox">
          <span class="text-center btnok loginout" @click="tosubmit">确认转入指数账户</span>
        </div>
      </mt-tab-container-item>
      <mt-tab-container-item id="2">
        <div class="form-block">
          <mt-field label="可转金额" placeholder="可转金额" type="text" disabled
            v-model="this.$store.state.userInfo.enableIndexAmt"></mt-field>
        </div>
        <div class="form-block">
          <mt-field label="转账金额" v-model="form.account2" placeholder="请输入转账金额" type="text">
            <span @click="selectAll2">全部</span>
          </mt-field>
        </div>
        <div class="btnbox">
          <span class="text-center btnok loginout" @click="tosubmit">确认转入融资账户</span>
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
      if (data.status === 0) {
        this.$store.state.settingForm = data.data
        if (!this.$store.state.settingForm.indexDisplay) {
          this.selected = '3'
        }
      } else {
        this.$message.error(data.msg)
      }
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
.is-selected .mint-tab-item-label:hover {
  text-decoration: none;
}

.wrapper /deep/ .mint-tab-item-label {
  font-size: 0.264rem;
}

.mint-navbar .mint-tab-item.is-selected {
  // color: #d50000;
  // border-bottom: 2px solid #d50000;
  text-decoration: none;
  margin-bottom: 0;
}

.prompt {
  padding: 0.3rem 0 0.2rem 0.7rem;
}
</style>
