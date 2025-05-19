<template>
  <div class="login_page">
    <headers :mess="loginWay" />
    <!-- <div class="img_mess">
      <div class="logo_content animated fadeInRight">
        <img :src="Logo" alt />
      </div>
    </div> -->
    <div class="logins_content">
      <div class="login_title animated slideInDown">
        <span>{{ $t('hj18') }}</span>
      </div>
      <div class="login_forms">
        <div class="top_forms">
          <!-- <div class="user_name">
            <input type="text" :placeholder="placeholder" v-model="phone" />
          </div>
          <div class="captcha">
            <input type="text" :placeholder="verification" v-model="code" />
            <div v-if="codeshow" @click="checkCodeBox">获取验证码</div>
            <div v-if="!codeshow" class="getcode">{{count}}s</div>
          </div>
          <div class="password">
            <input type="password"  placeholder="密码为6~12位数字、字母或符号" v-model="userPassword"  />
            @input="handleInput()" 
          </div>
          <div class="password">
            <input type="password"  placeholder="请再次确认密码" v-model="rePassword"  />
          </div>
          <div class="user_name">
            <input type="text" placeholder="机构代码" v-model="userName" />
          </div> -->
          <el-input :placeholder="placeholder" size="medium" v-model="phone" clearable @input="handleInput()" maxlength="11">
          </el-input>
          <!-- <el-input style="margin-top: 0.46rem;" :placeholder="verification" :size="medium" v-model="code" clearable>
          </el-input> -->
          <el-input style="margin-top: 0.46rem;" show-password :placeholder="$t('hj19')" size="medium"
            v-model="userPassword" @input="handleInput()">
          </el-input>
          <el-input style="margin-top: 0.46rem;" show-password :placeholder="$t('hj20')" size="medium" v-model="rePassword"
            @input="handleInput()">
          </el-input>
          <el-input style="margin-top: 0.46rem;" :placeholder="$t('hj21')" size="medium" v-model="userName" clearable
            @input="handleInput()">
          </el-input>
        </div>
        <div class="bottom_btns" :class="btnClass ? 'on' : 'off'">
          <div class="top_btn " @click="gook" :class="dengl ? 'animated pulse' : ''">
            <span>{{ $t('hj22') }}</span>
          </div>
          <!-- <div
           class="register-form-item agree-model"
          >
            <i @click="isAgree"
             :class="agree?'glyphicon glyphicon glyphicon-ok-sign blue':'glyphicon glyphicon-ok-circle'"></i>
          我已阅读并同意
          <a  style="color:#fff">《注册协议》</a>
          </div> -->
          <div class="mes">
            <span>{{ $t('hj23') }}?</span>
            <span @click="$router.push('/login')" style="color: rgb(54,124,248);"> {{ $t('hj24') }} ></span>
          </div>
        </div>
      </div>
    </div>
    <el-alert v-show="alertShow" :closable="closable" :title="texts" :type="elType" center></el-alert>
  </div>
</template>

<script>
import headers from "./components/header.vue";
import Logo from "@/assets/img/LOGO2.png";
import { isNull, isPhone, pwdReg } from '@/utils/utils'
import { Toast } from 'mint-ui'
import * as api from '@/axios/api'
export default {
  components: {
    headers
  },
  name: "newRegister",
  data() {
    return {
      verification: this.$t('hj25'),
      loginWay: this.$t('hj26'),
      loginWay: this.$t('hj26'),
      placeholder: this.$t('hj27'),
      Logo,
      phone: "",
      userName: "",
      code: "",
      userPassword: "",
      btnClass: false,
      codeshow: true,
      count: '', // 倒计时
      clickFalg: 0, //  点击次数
      rePassword: "",
      agree: false,
      dengl: false,
      alertShow: false,
      closable: false,
      texts: "",
      elType: "warning",
    }
  },
  mounted() {

  },
  methods: {
    handleInput() {

      if (this.userPassword !== "" && this.phone !== '' && this.userPassword == this.rePassword && this.userName != "") {
        this.btnClass = true;
      } else {
        this.btnClass = false;
      }
    },
    checkCodeBox() {
      if (isNull(this.phone) || !isPhone(this.phone)) {
        // Toast('请输入正确的手机号')
        this.texts = this.$t('hj28')
        this.alertShow = true
        setTimeout(() => {
          this.alertShow = false
        }, 2000)
      } else {
        this.checkPhone()
      }
    },
    async is_login(){
      //获取localStorage中的token
      let token = localStorage.getItem('USERTOEKN')
      if(token){
        this.$router.push('/home')
      }
    },
   
    async getcode() {
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
        this.texts = this.$t('hj29')
        this.alertShow = true
        setTimeout(() => {
          this.alertShow = false
        }, 2000)
      } else {
        if (!reg.test(this.phone)) {
          this.texts = this.$t('hj28')
          this.alertShow = true
          setTimeout(() => {
            this.alertShow = false
          }, 2000)
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
              // Toast(result.msg)
              this.texts = result.msg
              this.elType = "success"
              this.alertShow = true
              setTimeout(() => {
                this.alertShow = false
                this.elType = "warning"
              }, 2000)
            }
          } else {
            this.texts = result.msg
            this.alertShow = true
            setTimeout(() => {
              this.alertShow = false
            }, 2000)
          }
        }
      }
    },
    async gook() {
      this.dengl = true
      setTimeout(() => {
        this.dengl = false
      }, 1000)
      if (this.loginBtn) {
        return;
      }
      this.loginBtn = true;
      // 注册
      // if (!this.agree) {
      //   Toast('需同意注册协议才能注册!')
      //   this.loginBtn = false;
      // } else
      if (isNull(this.phone) || !isPhone(this.phone)) {

        this.texts = this.$t('hj28')
        this.alertShow = true
        setTimeout(() => {
          this.alertShow = false
        }, 2000)
        this.loginBtn = false;
      } else if (isNull(this.userPassword)) {
        this.texts = this.$t('hj30')
        this.alertShow = true
        setTimeout(() => {
          this.alertShow = false
        }, 2000)
        this.loginBtn = false;
      } else if (isNull(this.rePassword)) {
        this.texts = this.$t('hj31')
        this.alertShow = true
        setTimeout(() => {
          this.alertShow = false
        }, 2000)
        this.loginBtn = false;
      } else {
        if (this.userPassword !== this.rePassword) {
          this.texts = this.$t('hj32')
          this.alertShow = true
          setTimeout(() => {
            this.alertShow = false
          }, 2000)
          this.password = 0
          this.password2 = 0
          this.loginBtn = false;
        } else if (!pwdReg(this.userPassword)) {

          this.texts = this.$t('hj19')
          this.alertShow = true
          setTimeout(() => {
            this.alertShow = false
          }, 2000)
          this.loginBtn = false;
        } else if (isNull(this.userName)) {
          this.texts = this.$t('hj33')
          this.alertShow = true
          setTimeout(() => {
            this.alertShow = false
          }, 2000)
          this.loginBtn = false;
        } else {
          let opts = {
            // agentCode:'4023', // SR330001
            phone: this.phone,
            yzmCode: '6666',
            userPwd: this.userPassword,
            agentCode: this.userName
          }
          let data = await api.register(opts)
          if (data.status === 0) {
            this.texts = this.$t('hj34')
            this.elType = "success"
            this.alertShow = true
            setTimeout(() => {
              this.$router.push('/login')
              this.alertShow = false
              this.elType = "warning"
            }, 1000)
            this.loginBtn = false;
           
          } else {
            this.texts = data.msg
            this.alertShow = true
            setTimeout(() => {
              this.alertShow = false
            }, 2000)
            this.loginBtn = false;
          }
        }
      }
      if (navigator.vibrate) {
          // 支持
        navigator.vibrate([55]);
      }
        //   if (isNull(this.code)) {
        //     this.texts="请输入验证码"
        // this.alertShow=true
        // setTimeout(()=>{
        //   this.alertShow=false
        // },2000)
        //     this.loginBtn = false;
        //   } else 
       
    },
    isAgree() {
      this.agree = !this.agree
    },
    async checkPhone() {
      // 先验证是否已经注册
      let data = await api.checkPhone({ phoneNum: this.phone })
      if (data.status === 0) {
        // 如果用户已存在返回 0
        this.texts = this.$t('hj35')
            this.alertShow = true
            setTimeout(() => {
              this.alertShow = false
            }, 2000)
        this.$router.push('/login')
      } else {
        // this.dialogShow = false
        // this.adminUrl = process.env.API_HOST
        // if (this.adminUrl === undefined) {
        //   this.adminUrl = ''
        // }
        // this.gook()
        this.getcode()
      }
    },
  },

}
</script>

<style  scoped lang="less">
.login_page {
  position: relative;
  background-color: #fff !important;
  width: 100%;
  height: 100%;
  overflow: hidden;
  overflow-y: auto;
}

.logins_content {

  width: 100%;
  height: 9.7436rem;
  margin-top: 0.4359rem;
  padding: 0 0.4564rem;

  .login_title {
    width: 100%;
    height: 2.0513rem;
    display: flex;

    align-items: center;
    font-size: 0.7023rem;
    color: #000;
    font-weight: 600 !important;

    >span {
      font-weight: 600 !important;
    }
  }

  .login_forms {
    width: 100%;
    height: auto;
    margin-top: 0.35rem;

    .top_forms {
      width: 100%;
      height: auto;
      display: flex;
      align-items: center;
      flex-wrap: wrap;

      .user_name,
      .password {
        width: 100%;
        height: 35%;
        background: rgb(246, 246, 247);
        // background: #1e1e1e;
        border-radius: 0.3564rem;

        >input {
          width: 100%;
          height: 100%;
          padding-left: 0.3564rem;
          font-size: 0.4615rem;
        }
      }
    }

    /deep/.el-input__inner {
      width: 100%;
      height: 1.45rem !important;
      background-color: rgb(246, 246, 247) !important;
      // background: #1e1e1e;
      border-radius: 0.3564rem;
      font-size: 0.45rem !important;
    }

    .bottom_btns {
      width: 100%;
      height: 3.3333rem;
      margin-top: 0.8528rem;

      .top_btn {
        width: 100%;
        height: 1.25rem;
        border-radius: 0.3564rem;
        background: rgb(154, 197, 250);
        color: #fff;
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 0.5128rem;
        margin-top: 0.75rem;

        >span {
          font-weight: 530 !important;
        }
      }



      .mes {
        width: 100%;
        height: 20%;
        margin-top: 0.4rem;
        display: flex;
        justify-content: flex-end;
        align-items: center;
        font-size: 0.3846rem;
      }
    }
  }
}

.login_bom {
  position: absolute;
  bottom: 2.6rem;
  width: 100%;
  height: 4rem;
  padding: 0 2rem;
}

.line {
  //分割线
  width: 100%;
  height: 0.3846rem;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-top: 0.3846rem;

  .left_line {
    width: 20%;
    height: 0.01rem;
    background: #e5e5e5;
  }

  .center_line {
    width: 50%;
    height: 0.01rem;
    background: #fff;
    display: flex;
    align-items: center;
    justify-content: center;

    >span {
      font-size: 0.3046rem;
      color: #000;
      background-color: #fff;

    }
  }

  .right_line {
    width: 20%;
    height: 0.01rem;
    background: #e5e5e5;
  }

}

.myzh {
  width: calc(100% - 4rem);
  position: absolute;
  bottom: 0.01rem;
  text-align: center;

  .mes {
    width: 100%;
    height: 40%;

    font-size: 0.3846rem;
  }
}

.imgDemo {
  display: flex;
  justify-content: space-between;
  position: relative;
  transform: translateY(50%);
}

.appImg {
  width: 1.4rem;
  height: 1.4rem;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: rgb(246, 246, 247);
  border-radius: 100%;
}

.appImg img {
  width: 0.8rem;
  height: 0.8rem;
}

.img_mess {
  width: 100%;
  height: 4.0513rem;
  display: flex;
  justify-content: center;
  align-items: center;

  .logo_content {
    width: 3rem;
    height: 3.5769rem;

    >img {
      width: 100%;
      height: 100%;
    }
  }
}

/deep/.el-alert {
  width: 70% !important;
  height: 1rem !important;
  position: absolute !important;
  top: 0 !important;
  bottom: 0 !important;
  left: 0 !important;
  right: 0 !important;
  margin: auto !important;
  font-size: 0.3rem !important;
}

/deep/.el-alert__title {
  font-size: 0.3rem !important;
}

/deep/el-input__suffix {
  width: 1rem !important;
}

/deep/.el-input__suffix-inner {
  width: 1rem !important;
}

/deep/.el-input__icon {
  width: 1rem !important;
}

/deep/.el-icon-circle-close::before {
  font-size: 0.5rem !important;

}

/deep/.el-input__clear {
  font-size: 0.5rem !important;
}

.on .top_btn {
  background: #2d8cf0 !important;
}

.agree-model {
  margin-top: 0.2rem;
}
</style>
