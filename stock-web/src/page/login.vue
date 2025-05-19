<template>
  <div class="wrapper">
    <!-- <mt-header fixed title="">
      <router-link to="/home" slot="left">
        <mt-button icon="back"></mt-button>
      </router-link>
    </mt-header> -->
    <div class="login-form">
      <div class="login-avatar">
        <img class="login-ico" src="../assets/ico/wogerenziliao.png" alt="">
      </div>
      <div class="login-form-item input-model"
      style="margin-top:1.32rem;"
      >
        <img v-show="$state.theme != 'red'" class="login-ico" src="../assets/ico/loginuser.png" alt="">
        <img v-show="$state.theme == 'red'" class="login-ico" src="../assets/ico/loginuser-red.png" alt="">
        <input
        class="login-input"
        placeholder="用户名"
        type="tel" pattern="[0-9]*"
        v-model="phone"
        >
      </div>
      <div class="login-form-item input-model">
        <img v-show="$state.theme != 'red'" class="login-ico" src="../assets/ico/loginpwd.png" alt="">
        <img v-show="$state.theme == 'red'" class="login-ico" src="../assets/ico/loginpwd-pwd.png" alt="">
        <input class="login-input" type="password" placeholder="密码" v-model="psd">
      </div>
      <div class="login-form-item submit-model" @click="gook">
        立即登录<i v-show="isloading" style="color:#fff;" class="iconfont icon-jiazaizhong"></i>
      </div>
      <div class="login-form-item extra-model">
        <div style="color:#0E6580"><span @click="toForget">忘记密码？</span></div>
        <div :style="{color:$state.theme =='red'?'#BB1815':'#86CBD1'}">
          <span style="color:#0E6580">还没有账号？</span><span @click="toRegister">立即注册</span></div>
      </div>
    </div>
    <!-- <div class="text-center">
      <img class="banenr" :src="logo" alt="logo">
    </div> -->
    <!-- <div class="forms"> -->
      <!-- <div class="form-view">
        <icon class="form-ic" name="phone" slot="icon"></icon>
        <i class="iconfont icon-yonghu"></i>
        <input type="tel" pattern="[0-9]*" placeholder="手机号码" v-model="phone">
      </div> -->
      <!-- <div class="form-view">
          <i class="iconfont icon-yanzhengma"></i>
          <input type="number" pattern="[0-9]*" placeholder="验证码" v-model="code">
          <span v-if="codeshow" class="getcode" @click="getcode">获取验证码</span>
          <span v-if="!codeshow" class="getcode">{{count}}s</span>
      </div> -->
      <!-- <div class="form-view"> -->
        <!-- <icon class="form-ic" name="safe" slot="icon"></icon> -->
        <!-- <i class="iconfont icon-lr_password"></i> -->
        <!-- <input type="password" autocomplete="new-password" pattern="[0-9]*" placeholder="请输入密码" v-model="psd"> -->
      <!-- </div> -->
    <!-- </div> -->
    <!-- <div class="chebox">
        <span class="checked">
            <input id="checkcode" type="checkbox" :checked="isChecked" @click="handleDisabled"/>
            <label for="checkcode"></label>一天内自动登陆
        </span>
    </div> -->
    <!-- <div class="btnbox">
        <span class="btnok" @click="gook">
            确定
            <i v-show="isloading" style="color:#fff;" class="iconfont icon-jiazaizhong"></i>
        </span>
    </div>
    <div>
      <mt-button class="text-btn fl" type="default" @click="toForget">忘记密码</mt-button>
      <mt-button class="text-btn fr" type="default" @click="toRegister">注册</mt-button>
    </div> -->

  </div>
</template>
<script>
import { Toast } from 'mint-ui'
import { isNull, isPhone } from '@/utils/utils'
import * as api from '@/axios/api'

export default {
  data () {
    return {
      isloading: false,
      phone: '',
      code: '',
      psd: '',
      isChecked: true, // 自动登录
      isDisabled: false,
      codeshow: true,
      count: '', // 倒计时
      clickFalg: 0, //  点击次数
      logo: '' // 设置信息
    }
  },
  created: function () {
    this.$setgoindex()
  },
  mounted: function () {
    this.getInfoSite()
    this.phone = this.$store.state.userInfo.phone
  },
  methods: {
    async getInfoSite () {
      // 获取 logo
      let data = await api.getInfoSite()
      if (data.status === 0) {
        this.logo = data.data.siteLogoSm
      } else {
        Toast(data.msg)
      }
    },
    async checkPhone () {
      // 先验证是否已经注册
      let data = await api.checkPhone({ phoneNum: this.phone })
      if (data.status === 0) {
        // 如果用户已存在返回 0
        this.loginIN()
      } else {
        Toast('用户还未注册,请先注册')
        // this.$router.push('/register')
      }
    },
    gook () {
      // 登录
      if (this.clickFalg !== 0) {
        this.clickFalg = 0
        return
      }
      this.clickFalg++
      if (isNull(this.phone) || !isPhone(this.phone)) {
        Toast('请输入正确的手机号码')
      } else if (isNull(this.psd)) {
        Toast('请输入密码')
      } else {
        this.checkPhone()
      }
    },
    async loginIN () {
      let opts = {
        phone: this.phone,
        userPwd: this.psd
      }
      this.isloading = true
      let data = await api.login(opts)
      this.clickFalg = 0
      if (data.status === 0) {
        this.$store.state.userInfo.phone = this.phone
        // this.$store.state.userInfo.token = data.data.cookie
        this.clickFalg = 0
        // this.clearCookie()
        // this.setCookie(data.data.key,data.data.token,0)
        this.$router.push('/home')
      } else {
        Toast(data.msg)
      }
      this.isloading = false
    },
    handleDisabled: function () {
      this.isChecked = !this.isChecked
      if (this.isChecked === true) {
        this.isDisabled = true
      } else {
        this.isDisabled = false
      }
    },
    toForget () {
      // 忘记密码
      this.$router.push('/forget')
    },
    toRegister () {
      // 注册
      this.$router.push('/register')
    },
    toHome () {
      this.$router.push('/home')
    },
    goBack () {
      if (this.$route.query.goindex === 'true') {
        this.$router.push('/')
      } else {
        this.$router.back(-1)
      }
    }
  }
}
</script>
<style lang="less" scoped>
  body {
    background-color: #16171d;
  }

  .wrapper {
    color: #888;
    height: 100%;
    padding-bottom: 0;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    border-radius: .1rem;
  }

  .login-form {
    display: block;
    width: 6.13rem;
    height: 5.58rem;
    background-color: #1B1C25;
    position: relative;
    box-shadow: 0 0 .1rem .1rem #0002;
    .login-avatar {
      width: 1.2rem;
      height: 1.2rem;
      background-color: #444656;
      border-radius: 50%;
      position: absolute;
      top: -.6rem;
      left: 2.46rem;
      display: flex;
      align-items: center;
      justify-content: center;
      box-shadow: 0 0 .1rem .1rem #0002;
      img {
        width: .55rem;
        height: .58rem;
      }
    }
    .login-form-item {
      width: 4.95rem;
      height: .66rem;
      border-radius: .33rem;
      margin: .45rem auto 0;
      &.input-model {
        background-color: #121319;
        padding: 0 .33rem;
        display: flex;
        align-items: center;
        img.login-ico {
          width: .2rem;
          height: .23rem;
        }
        .login-input {
          flex: 1;
          padding: 0 .2rem;
          &::-webkit-input-placeholder {
            color: #363636;
          }
        }
      }
      &.submit-model {
        background-color: #024DA1;
        line-height: .66rem;
        text-align: center;
        color: #ffffff;
      }
      &.extra-model {
        margin-top: .24rem;
        display: flex;
        justify-content: space-between;
        font-size: .2rem;
      }
    }
  }
  .red-theme{
    .login-avatar{
      background-color: #222222;
    }
    .login-form{
      background-color: #fff;
    }
    .login-form-item.input-model{
      background-color: #fff;
      border: 1px solid #C9C9C9;
    }
    .login-form-item.submit-model{
      background-color: #BB1815;
    }
  }
</style>


