<template>
  <div class="wrapper">
    <div class="register-form">
      <div class="register-avatar">
        <img class="register-ico" src="../assets/ico/wogerenziliao.png" alt="">
      </div>
      <div
      class="register-form-item input-model"
      style="margin-top:1.11rem"
      >
        <img class="register-ico" v-show="$state.theme != 'red'" src="../assets/ico/loginuser.png" alt="">
        <img class="register-ico" v-show="$state.theme == 'red'" src="../assets/ico/loginuser-red.png" alt="">
        <input
        class="register-input"
        placeholder="请输入手机号码"
        type="tel"
        pattern="[0-9]*"
        v-model="phone"
        >
      </div>
      <div
      class="register-form-item input-model"
      >
        <img class="register-ico" v-show="$state.theme != 'red'" src="../assets/ico/vertify.png" alt="">
        <img class="register-ico" v-show="$state.theme == 'red'" src="../assets/ico/vertify-red.png" alt="">
        <input
        class="register-input"
        style="width:1.4rem"
        placeholder="验证码"
        type="tel"
        pattern="[0-9]*"
        v-model="code"
        >
        <div v-if="codeshow" class="getcode" @click="checkCodeBox">获取验证码</div>
        <div v-if="!codeshow" class="getcode">{{count}}s</div>
      </div>
      <div
      class="register-form-item input-model"
      >
        <img class="register-ico" v-show="$state.theme != 'red'" src="../assets/ico/loginpwd.png" alt="">
        <img class="register-ico" v-show="$state.theme == 'red'" src="../assets/ico/loginpwd-pwd.png" alt="">
        <input
        class="register-input"
        placeholder="密码为6~12位数字、字母或符号"
        type="password"
        pattern="[0-9]*"
        v-model="psd"
        >
      </div>
      <div
      class="register-form-item input-model"
      >
        <img class="register-ico" v-show="$state.theme != 'red'" src="../assets/ico/loginpwd.png" alt="">
        <img class="register-ico" v-show="$state.theme == 'red'" src="../assets/ico/loginpwd-pwd.png" alt="">
        <input
        class="register-input"
        placeholder="请再次确认密码"
        type="password"
        pattern="[0-9]*"
        v-model="psd2"
        >
      </div>
      <div
      class="register-form-item input-model"
      >
        <img class="register-ico" v-show="$state.theme != 'red'" src="../assets/ico/organization.png" alt="">
        <img class="register-ico" v-show="$state.theme == 'red'" src="../assets/ico/organization-red.png" alt="">
        <input
        class="register-input"
        placeholder="机构代码"
        type="tel"
        pattern="[0-9]*"
        v-model="invitecode"
        >
      </div>
      <div
       class="register-form-item agree-model"
      >
        <i @click="isAgree"
         :class="agree?'glyphicon glyphicon glyphicon-ok-sign red':'glyphicon glyphicon-ok-circle'"></i>
      我已阅读并同意
      <a @click="toagreeUrl" style="color:#fff">《注册协议》</a>
      </div>
      <div
      class="register-form-item submit-model"
      @click="gook"
      >
      立即注册
      </div>
      <div
      class="register-form-item "
      style="margin-top: .23rem;display:flex;justify-content:flex-end"
      >
      <div
      :style="{'font-size':'.2rem', color:$state.theme =='red'?'#000':'#86CBD1'}"
      >已有账号？<span
          :style="{color:$state.theme =='red'?'#BB1815':'#fff'}" 
      @click="goLogin">返回登录</span></div>
      </div>
    </div>
    <!-- <div class="text-center">
      <img class="banenr" :src="logo" alt="logo">
    </div>
    <div class="forms">
      <div class="form-view"> -->
        <!-- <icon class="form-ic" name="phone" slot="icon"></icon> -->
        <!-- <i class="iconfont icon-yonghu"></i>
        <input type="tel" pattern="[0-9]*" placeholder="手机号码" v-model="phone">
      </div>
      <div class="form-view" v-if="$store.state.siteInfo.smsDisplay">
        <i class="iconfont icon-yanzhengma"></i>
        <input type="number" pattern="[0-9]*" placeholder="验证码" v-model="code">
        <span v-if="codeshow" class="getcode" @click="checkCodeBox">获取验证码</span>
        <span v-if="!codeshow" class="getcode">{{count}}s</span>
      </div>
      <div class="form-view"> -->
        <!-- <icon class="form-ic" name="safe" slot="icon"></icon> -->
        <!-- <i class="iconfont icon-lr_password"></i>
        <input type="password" placeholder="密码为6~12位，数字、字母或符号" v-model="psd">
      </div>
      <div class="form-view"> -->
        <!-- <icon class="form-ic" name="safe" slot="icon"></icon> -->
        <!-- <i class="iconfont icon-lr_password"></i>
        <input type="password" placeholder="请再次确认密码" v-model="psd2">
      </div>
      <div class="form-view">
        <i class="iconfont icon-tuijian"></i>
        <input type="text" v-model="invitecode" placeholder="输入机构代码">
      </div>
    </div>
    <div class="chebox">
      <i @click="isAgree"
         :class="agree?'glyphicon glyphicon glyphicon-ok-sign red':'glyphicon glyphicon-ok-circle'"></i>
      我已阅读并同意
      <a @click="toagreeUrl">《注册协议》</a>
    </div>
    <div class="btnbox">
      <span class="btnok" @click="gook">确定</span>
    </div>
    <div class="back">
      已有账户？<a href="javascript:;" @click="goLogin">返回登录</a>
    </div> -->
    <!-- <mt-popup v-model="logindialogShow" :closeOnClickModal="false" class="mint-popup-box mint-popup-white">
        <div class="clearfix">
            <a @click="logindialogShow = false" class="pull-right"><i class="iconfont icon-weitongguo"></i></a>
        </div>
        <iframe :src="agreeUrl" frameborder="0"></iframe>
        <div class="text-center">
            <mt-button type="primary" @click="logindialogShow">我已阅读并同意注册协议</mt-button>
        </div>
    </mt-popup> -->
    <mt-popup v-model="dialogShow" :closeOnClickModal="false" class="mint-popup-box mint-popup-white">
      <div class="clearfix">
        <a @click="dialogShow = false" class="pull-right"><i class="iconfont icon-weitongguo"></i></a>
      </div>
      <div class="">
        <div class="row check-box">
          <div class="title">
            输入图片上的验证码
          </div>
          <mt-field label="验证码" placeholder="请输入验证码" v-model="code2">
            <img @click="refreshImg" :src="adminUrl+'/code/getCode.do?time='+ imgCodeTime" height="45px" width="100px">
          </mt-field>
          <p class="red" v-if="!checkCodeState">您输入的验证码有误,请重新输入</p>
          <div class="text-center">
            <mt-button type="primary" @click="checkImg">确定</mt-button>
            <!-- <mt-button style="margin-left: 10%;width:22%" type="default" @click="dialogShow = false">返回</mt-button> -->
          </div>
        </div>
      </div>
    </mt-popup>

  </div>
</template>
<script>
import { Toast } from 'mint-ui'
import { isNull, isPhone, pwdReg } from '@/utils/utils'
// import APIUrl from '@/axios/api.url'
import * as api from '@/axios/api'

export default {
  data () {
    return {
      phone: '',
      code: '',
      code2: '',
      psd: '',
      psd2: '',
      invitecode: '',
      codeshow: true,
      count: '', // 倒计时
      clickFalg: 0, //  点击次数
      imgCode: '',
      adminUrl: '',
      dialogShow: false, // 显示弹窗
      ischeckImg: false,
      checkCodeState: true,
      dialogImgShow: false, // 图片显示
      logo: '',
      agree: false,
      logindialogShow: false, // 注册协议
      agreeUrl: '', // 注册协议地址
      siteInfo: {},
      imgCodeTime: 0
    }
  },
  mounted: function () {
    if (this.$route.query.code) {
      this.invitecode = this.$route.query.code
    }
    this.getInfoSite()
  },
  methods: {
    async getInfoSite () {
      // 获取 logo
      let data = await api.getInfoSite()
      if (data.status === 0) {
        this.logo = data.data.siteLogoSm
        this.agreeUrl = data.data.regAgree
        this.siteInfo = data.data
        if(this.siteInfo.smsDisplay === false){
            this.code = '6666'
        }
        this.$store.state.siteInfo = this.siteInfo
        // this.invitecode = this.siteInfo.agentCode
      } else {
        Toast(data.msg)
      }
    },
    checkCodeBox () {
      if (isNull(this.phone) || !isPhone(this.phone)) {
        Toast('请输入正确的手机号')
      } else {
        this.checkPhone()
      }
    },
    async checkCode () {
      let data = await api.checkCode({ code: this.code2 })
      this.ischeckImg = data
    },
    async checkImg () {
      if (!this.code2) {
        Toast('您输入的验证码有误,请重新输入')
        this.checkCodeState = false
        return
      }
      // await this.checkCode()
      let data = await api.checkCode({ code: this.code2 })
      if (data === 'true' || data === true) {
        this.getcode()
        this.dialogShow = false
        this.checkCodeState = true
      } else {
        Toast('您输入的验证码有误,请重新输入')
        this.checkCodeState = false
        this.adminUrl = process.env.API_HOST + '1'
        this.adminUrl = process.env.API_HOST
        if (this.adminUrl === undefined) {
          this.adminUrl = ''
        }
      }
    },
    async getcode () {
      // if(!this.checkCode()){
      //     // 验证图形码是否正确
      //     Toast('请输入正确图形验证码')
      //     return
      // }
      // 获取验证码
      if (this.clickFalg !== 0) {
        this.clickFalg = 0
        return
      }
      this.clickFalg++
      //   var reg = 11 && /^((13|14|15|17|18)[0-9]{1}\d{8})$/
      let reg = /^[0-9]{11}$/ // 手机号码验证
      if (isNull(this.phone)) {
        Toast('手机号不可为空')
      } else {
        if (!reg.test(this.phone)) {
          Toast('请输入正确的手机号码')
        } else {
          //   var sign  = this.$md5(this.phone+'W&WzL42v').toUpperCase()
          let result = await api.getCode({ phoneNum: this.phone })
          if (result.status === 0) {
            const TIME_COUNT = 60
            if (!this.timer) {
              this.count = TIME_COUNT
              this.codeshow = false
              this.clickFalg = 0
              this.timer = setInterval(() => {
                if (this.count > 0 && this.count <= TIME_COUNT) {
                  this.count--
                } else {
                  this.codeshow = true
                  clearInterval(this.timer)
                  this.timer = null
                }
              }, 1000)
            } else {
              Toast(result.msg)
            }
          } else {
            Toast(result.msg)
          }
        }
      }
    },
    async checkPhone () {
      // 先验证是否已经注册
      let data = await api.checkPhone({ phoneNum: this.phone })
      if (data.status === 0) {
        // 如果用户已存在返回 0
        Toast('用户已注册,请登录')
        this.$router.push('/login')
      } else {
        this.dialogShow = false
        this.adminUrl = process.env.API_HOST
        if (this.adminUrl === undefined) {
          this.adminUrl = ''
        }
        // this.gook()
        this.getcode()
      }
    },
    async gook () {
      // 注册
      if (!this.agree) {
        Toast('需同意注册协议才能注册!')
      } else if (isNull(this.phone) || !isPhone(this.phone)) {
        Toast('请输入正确的手机号码')
      } else if (isNull(this.psd)) {
        Toast('请输入密码')
      } else if (isNull(this.psd2)) {
        Toast('请确认密码')
      } else if (isNull(this.code)) {
        Toast('请输入验证码')
      } else if (this.psd !== this.psd2) {
        Toast('两次输入的密码不一致')
        this.password = 0
        this.password2 = 0
      } else if (!pwdReg(this.psd)) {
        Toast('密码为6~12位，数字、字母或符号')
      } else if (isNull(this.invitecode)) {
        Toast('请输入机构代码')
      } else {
        let opts = {
          // agentCode:'4023', // SR330001
          phone: this.phone,
          yzmCode: this.code,
          userPwd: this.psd,
          agentCode: this.invitecode
        }
        let data = await api.register(opts)
        if (data.status === 0) {
          Toast('注册成功，请登录')
          this.$router.push('/login')
        } else {
          Toast(data.msg)
        }
      }
    },
    goLogin: function () {
      this.$router.push('/login')
    },
    refreshImg () {
      this.adminUrl = ''
      this.imgCodeTime = Date.now()
      this.dialogImgShow = false
      let this_ = this
      setTimeout(function () {
        this_.adminUrl = process.env.API_HOST
        if (this_.adminUrl === undefined) {
          this_.adminUrl = ''
        }
        this_.dialogImgShow = true
      }, 500)
    },
    isAgree () {
      let i = false
      let j = true
      this.agree = this.agree ? i : j
    },
    toagreeUrl () {
      this.$router.push('/agree')
    }
  }
}
</script>
<style lang="less" scoped>
  body {
    background-color: #fff;
  }
  #app .body-box {
    height: 100%
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
  .register-form {
    width: 6.14rem;
    height: 6.9rem;
    background-color: #1B1C25;
    position: relative;
    box-shadow: 0 0 .1rem .1rem #0002;
    .register-avatar {
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
    .register-form-item {
      width: 4.95rem;
      height: .53rem;
      border-radius: .265rem;
      margin: .15rem auto 0;
      &.input-model {
        background-color: #121319;
        padding: 0 .33rem;
        display: flex;
        align-items: center;
        img.register-ico {
          width: .2rem;
          height: .23rem;
        }
        .register-input {
          flex: 1;
          padding: 0 .2rem;
          height: 100%;
          font-size: .24rem;
          &::-webkit-input-placeholder {
            color: #363636;
          }
        }
      }
      &.agree-model {
        height: auto;
        margin-top: .3rem;
        font-size: .18rem;
        color: #86CBD1;
      }
      &.submit-model {
        height: .66rem;
        line-height: .66rem;
        margin-top: .3rem;
        background-color: #024DA1;
        border-radius: .33rem;
        color: #FFFFFF;
        font-size: .24rem;
        text-align: center;
      }
    }
  }
.glyphicon-ok-sign.red {
  color: #409EFF;
}
.getcode {
  font-size: .24rem;
}

.red-theme {
  .register-avatar{
    background-color: #222;
  }
  .register-form{
    background-color: #fff;
  }
  .register-form-item.input-model{
    background-color: #fff;
    border-color: #C9C9C9;
    border: .01rem solid #c9c9c9;
  }
  .register-form-item.agree-model {
    color: #000;
    a{
      color: #BB1815 !important;
    }
  }
  .register-form-item.submit-model {
    background-color: #BB1815;
  }
}
</style>
