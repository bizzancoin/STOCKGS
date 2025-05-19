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
        <span>{{ $t('hj8') }}</span>
      </div>
      <div class="login_forms">
        <div class="top_forms">
          <!-- <div class="user_name">
            <input type="text" :placeholder="placeholder" v-model="userName" />
          </div>
          <div class="password">
            <input type="password"  placeholder="登录密码" v-model="userPassword" @input="handleInput()" />
          </div> -->
          <el-input :placeholder="$t('hj9')" :size="medium" maxlength="11" v-model="userName" clearable>
          </el-input>
          <el-input style="margin-top: 0.46rem;" show-password :placeholder="$t('hj10')" :size="medium"
            v-model="userPassword" @input="handleInput()">
          </el-input>
        </div>
        <div class="bottom_btns" :class="btnClass ? 'on' : 'off'">
          <div class="top_btn " @click="loginIN" :class="dengl ? 'animated pulse' : ''">
            <span>{{ $t('hj11') }}</span>
          </div>
          <div class="mes" @click="getApp()">
            <span>{{ $t('hj12') }}</span>
          </div>
        </div>
      </div>
    </div>
    <div class="login_bom" v-if="hideshow">
      <div class="line">
        <div class="left_line"></div>
        <div class="center_line">
          <span>{{ $t('hj13') }}</span>
        </div>
        <div class="right_line"></div>
      </div>
      <div class="imgDemo">
        <div class="appImg" @click="getApp()"><img src="@/assets/img/apple.png" /></div>
        <div class="appImg" @click="getApp()"><img src="@/assets/img/google.png" /></div>
        <div class="appImg" @click="getApp()"><img src="@/assets/img/facebook.png" /></div>
      </div>
      <div class="myzh"><span class="mes">{{ $t('hj14') }}?</span><span @click="$router.push('/register')" class="mes"
          style="color: rgb(54,124,248);">{{ $t('hj15') }}</span> </div>
    </div>
    <el-alert v-show="alertShow" :closable="closable" :title="texts" :type="eltype" center></el-alert>
  </div>
</template>
<script>
import { Toast } from 'mint-ui'
import headers from "./components/header.vue";
import Logo from "@/assets/img/LOGO2.png";
import * as api from '@/axios/api';

export default {
  name: "newLogin",
  data() {
    return {
      loginWay: this.$t('hj8'),
      currentLoginMode: "email",
      placeholder: this.$t('hj16'),
      Logo,
      userPassword: "",
      userName: "",
      btnClass: false,
      medium: "medium",
      alertShow: false,
      closable: false,
      eltype: 'warning',
      texts: "",
      dengl: false,
      loginBtn: false,
      docmHeight: document.documentElement.clientHeight,  //默认屏幕高度
      showHeight: document.documentElement.clientHeight,   //实时屏幕高度
      hideshow: true,  //显示或者隐藏footer
    };
  },
  components: {
    headers
  },
  mounted() {
    window.onresize = () => {
      return (() => {
        this.showHeight = document.body.clientHeight;
      })()
    }
  },
  watch: {
    showHeight: function () {
      if (this.docmHeight > this.showHeight) {
        this.hideshow = false
      } else {
        this.hideshow = true
      }
    }
  },
  methods: {
    getApp() {
      // Toast('您所在的地区暂未开通此服务')
      //Toast 弹窗大小
      this.texts = this.$t('hj17')
      this.alertShow = true
      setTimeout(() => {
        this.alertShow = false
      }, 2000)
    },
    handleInput() {
      console.log(this.userPassword !== "" && this.userName !== '');
      if (this.userPassword !== "" && this.userName !== '') {
        this.btnClass = true;
      } else {
        this.btnClass = false;
      }
    },
    async loginIN() {

      this.dengl = true
      setTimeout(() => {
        this.dengl = false
      }, 1000)
      if (this.loginBtn) {
        return;
      }
      this.loginBtn = true;
      let opts = {
        phone: this.userName,
        userPwd: this.userPassword
      }
      let data = await api.login(opts)
      if (data.status === 0) {
        this.$store.state.userInfo.phone = this.userName
        this.$store.state.userInfo.token = data.data.token
        this.texts = this.$t('hj36')
        this.eltype = 'success'
        this.alertShow = true
        setTimeout(() => {
          this.alertShow = false
          this.eltype = 'warning'
          this.$router.push('/home')
        }, 1000)
        this.loginBtn = false;
        // window.localStorage.clear()
        window.localStorage.setItem("USERTOKEN", data.data.token);


      } else {
        this.texts = data.msg
        this.alertShow = true
        this.loginBtn = false;
        setTimeout(() => {
          this.alertShow = false
        }, 2000)
        //Toast(data.msg)
      }
      if (navigator.vibrate) {
        // 支持
        navigator.vibrate([55]);
      }
    },
  },
  beforeDestroy() { },
  created() { }
};
</script>

<style scoped lang="less">
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

/deep/.el-input__suffix {
  width: 1rem !important;
  display: flex;
  justify-content: center;
  align-items: center;
}

/deep/.el-input__suffix-inner {

  display: flex;
  justify-content: center;
  align-items: center;

}

/deep/.el-input__icon {
  width: 1rem !important;
  display: flex;
  justify-content: center;
  align-items: center;
}

.on .top_btn {
  background: #2d8cf0 !important;
}

/deep/.el-icon-circle-close::before {
  font-size: 0.5rem !important;

}

/deep/.el-input__clear {
  font-size: 0.5rem !important;
}
</style>
