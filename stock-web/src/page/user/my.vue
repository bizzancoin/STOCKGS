<template>
  <div class="wrapper">
    <div class="header">
      <mt-header title="用户信息">
        <router-link to="/user" slot="left">
          <mt-button icon="back">我的</mt-button>
        </router-link>
      </mt-header>
    </div>
    <div class="form-block">
      <mt-field label="真实姓名" placeholder="真实姓名" type="text" disabled
                v-model="$store.state.userInfo.realName"></mt-field>
      <mt-field label="手机号码" placeholder="手机号码" type="text" disabled v-model="$store.state.userInfo.phone"></mt-field>
    </div>
    <div class="form-block">
      <mt-field label="登录密码" @click.native="changeLogin" autocomplete="new-password" placeholder="点击可修改登录密码"
                type="password" disabled>
        <span @click="changeLogin"><i class="iconfont icon-xiugai"></i>修改密码</span>
      </mt-field>
    </div>
    <div class="btnbox">
      <span class="text-center btnok loginout" @click="toRegister">退出系统</span>
    </div>
    <!-- 修改密码 -->
    <mt-popup v-model="changeLoginPsdBox" position="bottom" class="mint-popup-wrap">
      <div class="clearfix">
        <a @click="changeLoginPsdBox = false" class="pull-right"><i class="iconfont icon-weitongguo"></i></a>
      </div>
      <div class="form-block">
        <mt-field label="旧密码" type="password" placeholder="请输入旧密码" v-model="nextPsd"></mt-field>
        <mt-field label="新密码" placeholder="密码为6~12位，数字、字母或符号" type="password" v-model="newPsd"></mt-field>
      </div>
      <div class="text-center">
        <mt-button class="btn-sure" type="default" @click="changeLoginPsd">确定</mt-button>
      </div>
    </mt-popup>
  </div>
</template>

<script>
import * as api from '@/axios/api'
import { Toast } from 'mint-ui'
import { isNull, pwdReg } from '@/utils/utils'

export default {
  components: {},
  props: {},
  data () {
    return {
      username: '',
      changeLoginPsdBox: false,
      nextPsd: '',
      newPsd: ''
    }
  },
  watch: {},
  computed: {},
  created () {},
  mounted () {},
  methods: {
    async toRegister () {
      // 注销登陆
      this.clearCookie()
      let data = await api.logout()
      if (data.status === 0) {
        // Toast(data.msg)
        this.$router.push('/login')
      } else {
        Toast(data.msg)
      }
      this.$router.push('/login')
    },
    changeLogin () {
      this.changeLoginPsdBox = true
    },
    async changeLoginPsd () {
      if (isNull(this.nextPsd) || isNull(this.newPsd)) {
        Toast('请输入新旧密码')
      } else if (!pwdReg(this.newPsd)) {
        Toast('密码为6~12位，数字、字母或符号')
      } else {
        // 修改密码
        let opts = {
          oldPwd: this.nextPsd,
          newPwd: this.newPsd
        }
        let data = await api.changePassword(opts)
        if (data.status === 0) {
          this.changeLoginPsdBox = false
          Toast(data.msg)
        } else {
          Toast(data.msg)
        }
      }
    }
  }
}
</script>
<style lang="less" scoped>
  .loginout {
    color: #999;
    border: 0.015rem solid #606060;
    font-size: 0.3rem;
    background: none;
  }

  .mint-popup-wrap {
    width: 100%;
    padding: 0.3rem 0.3rem 0.6rem;

    .btn-sure {
      margin-top: 0.5rem;
      width: 80%;
      color: #fff;
      border: none;
    }
  }

  .btnbox .btnok {
    background: none;
  }
</style>
