<template>
  <div class="wrapper">
    <div class="header">
      <mt-header title="忘记密码">
        <router-link to="/login" slot="left">
          <mt-button icon="back"></mt-button>
        </router-link>
      </mt-header>
    </div>
    <div class="form-group">
      <div class="title">
        输入您的手机号
      </div>
      <div class="page-part">
        <mt-field label="+86" type="tel" placeholder="请输入手机号" v-model="phone"></mt-field>
        <mt-field label="获取验证码" type="number" placeholder="输入验证码" v-model="code">
          <span v-if="codeshow" class="getcode" @click="checkCodeBox">获取验证码</span>
          <span v-if="!codeshow" class="getcode">{{count}}s</span>
        </mt-field>
        <mt-field label="输入密码" placeholder="请输入密码" type="password" v-model="password"></mt-field>
        <mt-field label="输入密码" placeholder="确认您输入的密码" type="password" v-model="password2"></mt-field>
      </div>
    </div>
    <div class="btnbox">
      <span class="text-center btnok" @click="gook">确定</span>
    </div>
    <mt-popup v-model="dialogShow" :closeOnClickModal="false" class="mint-popup-box mint-popup-white">
      <div class="clearfix">
        <a @click="dialogShow = false" class="pull-right"><i class="iconfont icon-weitongguo"></i></a>
      </div>
      <div class="">
        <div class="check-box row">
          <div class="title">
            输入图片上的验证码
          </div>
          <mt-field label="验证码" placeholder="请输入验证码" v-model="code2">
            <img @click="refreshImg" :src="adminUrl+'/code/getCode.do?time=' + imgCodeTime" height="45px" width="100px">
          </mt-field>
          <p class="red" v-if="!checkCodeState">您输入的验证码有误,请重新输入</p>
          <div class="text-center" style="width: 100%; padding: 0.2rem;">
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
import { isNull, isPhone } from '@/utils/utils'
import * as api from '@/axios/api'

export default {
  components: {},
  props: {},
  data () {
    return {
      phone: '',
      password: '',
      password2: '',
      code: '',
      code2: '',
      codeshow: true,
      count: '', // 倒计时
      clickFalg: 0, //  点击次数
      imgCode: '',
      adminUrl: '',
      dialogShow: false, // 显示弹窗
      ischeckImg: false,
      checkCodeState: true, // 验证码的状态
      dialogImgShow: false, // 图片显示
      imgCodeTime: 0
    }
  },
  watch: {
    code2 (newval) {
      if (newval) {
        this.checkCodeState = true
      }
    }
  },
  computed: {},
  methods: {
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
        this.checkCodeState = false
        Toast('您输入的验证码有误,请重新输入')
        return
      }
      // await this.checkCode()
      let data = await api.checkCode({ code: this.code2 })
      if (data === 'true' || data === true) {
        this.getcode()
        this.dialogShow = false
        this.checkCodeState = true
      } else {
        this.checkCodeState = false
        Toast('您输入的验证码有误,请重新输入')
        this.adminUrl = process.env.API_HOST + '1'
        this.adminUrl = process.env.API_HOST
        if (this.adminUrl === undefined) {
          this.adminUrl = ''
        }
      }
    },
    async getcode () {
      if (this.clickFalg !== 0) {
        this.clickFalg = 0
        return
      }
      this.clickFalg++
      // var reg = 11 && /^((13|14|15|17|18)[0-9]{1}\d{8})$/
      let reg = /^[0-9]{11}$/ // 手机号码验证
      if (isNull(this.phone)) {
        Toast('手机号不可为空')
      } else {
        if (!reg.test(this.phone)) {
          Toast('请输入正确的手机号码')
        } else {
          let result = await api.sendForgetSms({ phoneNum: this.phone })
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
          }
        }
      }
    },
    async checkPhone () {
      // 先验证是否已经注册
      let data = await api.checkPhone({ phoneNum: this.phone })
      if (data.status === 0) {
        // 如果用户已存在返回 0
        this.dialogShow = true
        this.adminUrl = process.env.API_HOST
        if (this.adminUrl === undefined) {
          this.adminUrl = ''
        }
        // this.gook()
      } else {
        Toast('用户还未注册,请注册')
        this.$router.push('/register')
      }
    },
    async gook () {
      if (isNull(this.phone) || !isPhone(this.phone)) {
        Toast('请输入正确的手机号码')
      } else if (isNull(this.password)) {
        Toast('请输入密码')
      } else if (isNull(this.password2)) {
        Toast('请确认密码')
      } else if (isNull(this.code)) {
        Toast('请输入验证码')
      } else if (this.password !== this.password2) {
        Toast('两次输入的密码不一致')
        this.password = 0
        this.password2 = 0
      } else {
        let opts = {
          phoneNum: this.phone,
          code: this.code,
          newPwd: this.password
        }
        let data = await api.forgetPas(opts)
        if (data.status === 0) {
          Toast('修改成功,请登录!')
          this.$router.push('/login')
        } else {
          Toast(data.msg ? data.msg : '修改失败,请重新修改')
        }
      }
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
    }
  },
  created () {},
  mounted () {}
}
</script>
<style lang="less" scoped>
  body {
    background-color: #fff;
  }

  body /deep/ .mint-toast {
    z-index: 1000000;
  }

  .form-group {
    padding: 0rem 0.97rem;
    font-size: 0.29rem;

    .title {
      font-size: 0.43rem;
      margin-bottom: 0.34rem;
      margin-top: 1.40rem;
    }

    .mint-cell {
      padding: 0;
    }

    /deep/ .mint-cell-title {
      text-align: left;
    }

    /deep/ .mint-cell-wrapper {
      height: 1.19rem;
      line-height: 1.19rem;
    }
  }

  .btnbox {
    width: 35%;
    padding-top: 0.28rem;
  }

  .mint-popup-box {
    // width: 100%;
    // height: 100%;
    // background:#fff;
    .title {
      font-size: 0.43rem;
      margin-bottom: 0.34rem;
      // margin-top: 1.40rem;
      padding: 0.1rem 0.4rem;
      color: #333;
    }

    .mint-cell {
      background: none;
      color: #000;
      width: 100%;
    }

    /deep/ .mint-cell-text {
      color: #000;
    }

    .mint-cell-wrapper {
      border: 0.02rem solid #ddd;
    }

    .mint-button {
      margin-top: 0.2rem;
      width: 60%;
    }

    .check-box {
      p {
        padding: 0.2rem 0.4rem;
      }

      padding-bottom: 0.3rem;
    }
  }
</style>
