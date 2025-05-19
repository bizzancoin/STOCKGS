<template>
  <div class="wrapper">
    <!-- <div class="header">
      <mt-header :title="type+'银行卡'">
        <router-link to="/card" slot="left">
          <mt-button icon="back">返回</mt-button>
        </router-link>
      </mt-header>
    </div> -->
    <div class="box transaction">
      <div class="box-contain clearfix">
        <div class="empty text-center" style="margin-top: 0.3rem;">
          <!-- 您已通过实名认证 -->
          <i style="color:red;font-size: 1.2rem;margin-top:0.2rem" class="iconfont icon-chongzhi2"></i>
        </div>
      </div>
    </div>
    <div class="form-block page-part">
      <mt-field label="银行名称" placeholder="例:中国工商银行（全称）" type="text" v-model="bankName"></mt-field>
      <mt-field label="开户支行" placeholder="需要精确到分行或者支行" type="text" v-model="bankAddress"></mt-field>
      <mt-field label="银行卡号" placeholder="请输入银行卡号" v-model="bankNo"></mt-field>
    </div>
    <!-- <div class="form-block page-part">
        <mt-field label="持卡人姓名" placeholder="和银行卡绑定一致" type="text" v-model="username"></mt-field>
        <mt-field label="银行预留手机号" placeholder="请输入持卡人手机号" type="text" v-model="username"></mt-field>
    </div> -->
    <div class="rule-box">
      <div class="title">提示：</div>
      <ul>
        <li>1、新用户注册后必须通过添加银行卡。</li>
        <li>2、用户只能添加一张本人银行卡。</li>
        <li>3、真实姓名必须和出金银行卡户名一致。</li>
      </ul>
    </div>
    <div class="btnbox">
      <span class="text-center btnok" @click="toSure">确定</span>
    </div>

  </div>
</template>

<script>
import * as api from '@/axios/api'
import { Toast } from 'mint-ui'
import { isNull, bankNoReg, isName } from '@/utils/utils'

export default {
  components: {},
  props: {},
  data () {
    return {
      bankName: '',
      bankNo: '',
      bankAddress: '', // 支行地址
      type: '添加'
    }
  },
  watch: {},
  computed: {},
  created () {},
  mounted () {
    this.type = this.$route.query.type ? '修改' : '添加'
    if (this.$store.state.bankInfo) {
      this.bankName = this.$store.state.bankInfo.bankName
      this.bankNo = this.$store.state.bankInfo.bankNo
      this.bankAddress = this.$store.state.bankInfo.bankAddress
    }
  },
  methods: {
    async toSure () {
      // 添加银行卡
      if (isNull(this.bankNo) || !bankNoReg(this.bankNo)) {
        Toast('请输入银行卡号')
      } else if (isNull(this.bankName) || !isName(this.bankName)) {
        Toast('请输入银行名称')
      } else if (isNull(this.bankAddress) || !isName(this.bankAddress)) {
        Toast('请输入开户支行')
      } else {
        let opts = {
          bankName: this.bankName,
          bankNo: this.bankNo,
          bankAddress: this.bankAddress
        }
        if (this.$route.query.type === 'edit') {
          let data = await api.updateBankCard(opts)
          if (data.status === 0) {
            Toast('修改成功!')
            this.$router.push('/card')
          } else {
            Toast(data.msg)
          }
        } else {
          let data = await api.addBankCard(opts)
          if (data.status === 0) {
            Toast('添加成功!')
            this.$router.push('/card')
          } else {
            Toast(data.msg)
          }
        }
      }
    },
    goBack () {
      this.$router.back(-1)
    }
  }
}
</script>
<style lang="less" scoped>
  .rule-box {
    padding: 0.2rem 0.3rem;

    .title {
      font-size: 0.3rem;
      height: 0.5rem;
      line-height: 0.5rem;
      margin-bottom: 0.2rem;
    }

    ul {
      li {
        color: #999;
        line-height: 0.5rem;
      }
    }
  }

  .transaction {
    // padding-bottom: 0.2rem;
  }
</style>
