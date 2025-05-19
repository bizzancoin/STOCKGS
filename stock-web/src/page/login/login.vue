<template>
  <div class="login_page">
    <!-- <headers :mess="loginWay" /> -->
    <!-- <div class="logins_content">
      <div class="login_title animated slideInDown">
        <span>{{ $t('hj8') }}</span>
      </div>
      <div class="login_forms">
        <div class="top_forms">
          
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
    <el-alert v-show="alertShow" :closable="closable" :title="texts" :type="eltype" center></el-alert> -->
    <div class="header">
      <div class="bock" @click="$router.go(-1)"></div>
    </div>
    <div class="logo"></div>
    <div class="formInt">
      <div class="formDemo">
        <span></span>
        <input placeholder="请输入账号" v-model="userName" />
      </div>
      <div class="formDemos">
        <span class="pwdIcon"></span>
        <input
          type="password"
          placeholder="请输入密码"
          v-model="userPassword"
          @input="handleInput()"
        />
      </div>
    </div>
    <div class="djzc" @click="$router.push('/register')">点击注册</div>
    <div @click="loginIN" class="loginBtn">登录</div>
    <el-alert
      v-show="alertShow"
      :closable="closable"
      :title="texts"
      :type="eltype"
      center
    ></el-alert>
  </div>
</template>
<script>
import { Toast } from "mint-ui";
import headers from "./components/header.vue";
import Logo from "@/assets/img/LOGO2.png";
import * as api from "@/axios/api";

export default {
  name: "newLogin",
  data() {
    return {
      loginWay: this.$t("hj8"),
      currentLoginMode: "email",
      placeholder: this.$t("hj16"),
      Logo,
      userPassword: "",
      userName: "",
      btnClass: false,
      medium: "medium",
      alertShow: false,
      closable: false,
      eltype: "warning",
      texts: "",
      dengl: false,
      loginBtn: false,
      docmHeight: document.documentElement.clientHeight, //默认屏幕高度
      showHeight: document.documentElement.clientHeight, //实时屏幕高度
      hideshow: true, //显示或者隐藏footer
    };
  },
  components: {
    headers,
  },
  mounted() {
    window.onresize = () => {
      return (() => {
        this.showHeight = document.body.clientHeight;
      })();
    };
  },
  watch: {
    showHeight: function () {
      if (this.docmHeight > this.showHeight) {
        this.hideshow = false;
      } else {
        this.hideshow = true;
      }
    },
  },
  methods: {
    getApp() {
      Toast(this.$t("hj17"));
      //Toast 弹窗大小
      // this.texts = this.$t('hj17')
      // this.alertShow = true
      // setTimeout(() => {
      //   this.alertShow = false
      // }, 2000)
    },
    handleInput() {
      console.log(this.userPassword !== "" && this.userName !== "");
      if (this.userPassword !== "" && this.userName !== "") {
        this.btnClass = true;
      } else {
        this.btnClass = false;
      }
    },
    async loginIN() {
      // this.dengl = true;
      // setTimeout(() => {
      //   this.dengl = false;
      // }, 1000);
      // if (this.loginBtn) {
      //   return;
      // }
      // this.loginBtn = true;
      let opts = {
        phone: this.userName,
        userPwd: this.userPassword,
      };
      let data = await api.login(opts);
      if (data.status === 0) {
        this.$store.state.userInfo.phone = this.userName;
        this.$store.state.userInfo.token = data.data.token;
        // this.texts = this.$t('hj36')
        // this.eltype = 'success'
        // this.alertShow = true
        Toast(this.$t("hj36"));
        setTimeout(() => {
          // this.alertShow = false
          // this.eltype = 'warning'
          this.$router.push("/home");
        }, 1000);
        // this.loginBtn = false;
        // window.localStorage.clear()
        window.localStorage.setItem("USERTOKEN", data.data.token);
      } else {
        // this.texts = data.msg
        // this.alertShow = true
        // this.loginBtn = false;
        // setTimeout(() => {
        //   this.alertShow = false
        // }, 2000)
        Toast(data.msg);
      }
      if (navigator.vibrate) {
        // 支持
        navigator.vibrate([55]);
      }
    },
  },
  beforeDestroy() {},
  created() {},
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

    > span {
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

        > input {
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

        > span {
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

    > span {
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

    > img {
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
.header {
  height: 5.7rem;
  width: 100%;
  background: url("~@/assets/img/huxbj.8fe20e1f.png") no-repeat 50%;
  background-size: 100%;
  position: relative;
}
.bock {
  position: absolute;
  background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABIAAAAgCAYAAAAffCjxAAAAAXNSR0IArs4c6QAAAVdJREFUSEut1usqBVEYxvH/c1MuRJJDkhwTEpIkJUlyCEk++CJJckpycw49Gs3Wa+y9Z82ePd/Xr5n38KwRNR7bE0APMK1OHdtTwAWQGZcdQbZngPMc+QImK0O2Z4GzgIxLuqoE2Z4DTgMyJuk6K08yZHseOAnIqKSbRo2TINsLwHFARiTdxkaVQrYXgaOADEu6K3a7LWR7CTjMD2XdaYq0rZHtFWA/Rz6BIUn3reau6RvZXgX28kMfOfLQbnj/QbbXgN380HuOPJZtwB/I9jqwE5ABSc9lyJ8a2d4AtgPSL+klBfmFbG8CWwHpk/SaivxAtjMgg7Inq0mvpLcqSHehTOvKpzU+oSvFDlj99gesOJCDkp7Kip+yIknT3XL7C0tbum9lMbIMHKQkQEqwJWVSKZTPWTElq0dt6GYxt6uHf8DqX0cBq39BBqz+lR2w+j8RAfv9rfkGqF24CUdT9E4AAAAASUVORK5CYII=)
    no-repeat 50%;
  background-size: 100%;
  width: 0.23rem;
  height: 0.41rem;
  left: 0.38461rem;
  top: 0.38461rem;
}
.logo {
  width: 2.03rem;
  height: 2.03rem;
  background: url("~@/assets/imgRed/touxiang.jpg") no-repeat 50%;
  background-size: 100%;
  margin: 0 auto;
  border-radius: 50%;
  margin-top: -1.0153rem;
  position: relative;
}
.formInt {
  width: 8.63rem;
  margin: 0 auto;
  margin-top: 1rem;
}
.formDemo {
  display: flex;
  height: 1.26923rem;
  width: 8.63rem;
  background: #f5f5f5;
  border-radius: 0.126923rem;
  position: relative;
  margin-bottom: 0.253846rem;
}
.formDemo span {
  width: 0.406rem;
  height: 0.406rem;
  background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAEAklEQVRYhbWXXWgcVRTHf2dmk1jjS4QK9qEKKamFYrDgixUfShKUipYiRgRL6iZtRUSpJpsvRCFN3V0CihY02fTDFmmKeVBTkIjBmvSh2Ddb1GLRllDMuxHzMXPkzkcy2WayOzb9s7Azd845//8998y5d4QE+PiUpubmeQHYi/A4yiaFeWAa4WdVRquqGH1znyyWG7VsAflB3S1CHtjmean3WxFF/ctfXKU90ybny4lrlTLY0TAhA0PaL8I3ZZCb/20ijOUKevTRpgslJ1jSID+onSIcXbJW/lYYVuUrV7lm26hCncAehTRwXyTykY609P5vAQNDukthHLAD8imF5vY2ubWafa6gm4ARhCeDIVWlMdMq38dxxC6BSb3CRxHyS7P/0hBHbtDRKrf+maMBuLQ0QeHD7U0/xk409kFuUBstYTwgn1tw2Np1SG7E2UfRP6gPpWx+U6gyw67S1NUq361mG5sBS3g+UnBflEtu0H1Abhif8F7huVie2CjCY2G1q1LWK1WEsYDcTGZHcgF+kwnFXE/K7irXI/4PJhag4ATOJgOJOmYgwAr9CWMlEQDMhLRiUZtUgG18lmXPJBcg/MRyh9uTVACmiAMofqxkAuDLSHttzg9rXbnc2ROe7Uuw1LJHEwtob5UpgctBgJQq5/oHtboUed+QVhtbz8cfutz1qkwlFmDguLzFcjHWp2wm88d1S5x99oRuSdlMAPUBuRPEiEXJ6s4V9B38bTjEvMJp4GsRfldfaK1leWv+ClAZvn6u0tmTluwdCfBmVtB2ET4wGdPIePGWHBlzA/Ko8FVR8jyQLehmEUxROWWSGziWxdYjx3VzqfhrZiBb0DeCs0B1DPlCcFURfe41L/9qVqGzZ798kkhAblgrgWNAKytndsVRzqoyYVn8cebcRa/BvLh35wMpm4cRdlkWLytsLxJaUHi9t0XmSwoIyM1G0hgJcg14+/ORyfNXx5/SYp8oHnn6ouxrfmK3wgBQF8mG2Y6fLRaxWg0UisiHLYv6TFrGSpEb/PrtTu3eL2PGB2E44tCocGrNDGQLmhbxBITk+UxaOkqRroW+k5ozfS0UotD2bosUbstA36e6UYRchPzM6ZHJzJ2QG/S2SEfQN8K4uZ6CbrxNQGUFh4H7A6Obiw6HrpSR8nKw6PCawk3PVKip8rmWBbx3TM3Z7WCkg3X3HJDZ9SA3eD8tsyZmZMEPZj7zOH0B997DMwo1Afl0VSUj60Ue4s8ZzqrLdHBbYziXBLjqV703YDFyOMG3Xbk42SmObfsTC86ZjUsCJDg0BhvLhfUmD+EqP4TLHHKGRVgbPrAsrt4tAY67Ira3rXsCFDb4sliwLf66WwIqUt7ZMNw/NgD8B2plfW1RGaDfAAAAAElFTkSuQmCC)
    no-repeat 50%;
  background-size: 100%;
  margin-top: 0.431rem;
  margin-left: 0.25346rem;
}
.formDemos {
  display: flex;
  height: 1.26923rem;
  width: 8.63rem;
  background: #f5f5f5;
  border-radius: 0.126923rem;
  position: relative;
}
.pwdIcon {
  width: 0.406rem;
  height: 0.406rem;
  background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAADMklEQVRYha2WXYhMYRjHf88xu2uJ5EZJWiFFkq8LJYRSvpILRMQ2NheIlWXJd5F2JKTY3Qi5FeFKiciNC+Vjb2R9XOyVVqHFzJ7z6Mx5j52ZPWfmHTP/5nSa877P8/+/z9c5Qploa9fxjrADWK4wEQH16EZ4qMqVlib5Uo7HsgS0tes+RzgF1Gtordlf6O2XwtGWpKSqLiDVoacFWgk4fyHcVY+XPrkIcxHWKNQbTWdaknKoagJSHbpS4L4hf66waf/2/FCf7cim5rbCfPNoVUtSHlQs4NINddJp3gBTXeV1IsG85kbpi9p77poOcz1eADMQumprmL5ni3jF/DulBKTTLPDJNTj9zjhyH/sapc/12GWONTWdydoWRUkBnrLQFFz3gSZ5Vmp/a7CnO7StWIBCg6n2d6X2/rNR3prOaKhYgONQa1rth60AhJ8E3VFbsYC8Pi8TNnY2KQhQ5sy0FR3p9my7LhwiJBUm41/CaIVegY8aY537XGECMBqhF3jvX65H5+GkPC0pINWpR1FO5J5cB6auDXn8unCsdZucjBWQ6tSVKPeNQQ/CY4W+CsmHAYsRxqpfT8Kqw9sGJmQi7/hKs3HyKlHDor1b5TtVQNtNHZnp54kKM4Fm4J8AJ5+fOeZ+oVrkPvZvke8eXDDRmZO7VtgFI0zOv1aLPETWZ+B7RLyAnIKrNkSiWzOvBkLyoq8vgyOXtW74UF4YtfMONkq6RAQGd1KhgHCDzRCpr2MMwiyC000CukrZRPktLEJrSE6eXI8htuSFHJGjWMooAtuIRZFHCyiTHEvBcSIHdUG55NnOsQhB3EstEbHXKqQZl2+1NfT65BmPTxYmkQcclALbfB5rkh/9LlNcZcrx7VLyYyXkLtqGcZUahyNJsZ+YMYOoMAK/jYBR1o4tkePzdzEB74zKddOWPavaRG5Y8VJEWB9y5K7lpcBVrjvCbMdh9eb18x/JBr3jKf3ZRVPtGsqSwSmL+mYQIeEpa4El/n9HuBYr4HOPXpkwTpYCa3wDTwOj/yXPDqn8xN/70MPViK0DOH9LE38y7FZImu/BxP+SG/gR/AC019Vw8eBGCSLqA/gLhUArt4NKdJwAAAAASUVORK5CYII=)
    no-repeat 50%;
  background-size: 100%;
  margin-top: 0.431rem;
  margin-left: 0.25346rem;
}
.formDemos input {
  background: transparent;
  height: 1.25rem;
  width: 7rem;
  font-size: 0.4rem;
  color: #000;
  margin-left: 0.25rem;
}
.formDemo input {
  background: transparent;
  height: 1.25rem;
  width: 7rem;
  font-size: 0.4rem;
  color: #000;
  margin-left: 0.25rem;
}

//input默认字样式
::-webkit-input-placeholder {
  color: #999;
  font-size: 0.4rem;
}
.djzc {
  width: 8.5rem;
  margin: 0.45rem auto;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-pack: justify;
  -ms-flex-pack: justify;
  justify-content: flex-end;
  font-size: 0.375rem;
  color: #5d7dfb;
}
.loginBtn {
  width: 8.5rem;
  height: 1.1rem;
  background: linear-gradient(-55deg, rgb(80, 122, 250), rgb(115, 131, 251));
  border-radius: 0.125rem;
  margin: 0 auto;
  margin-top: 1rem;
  color: #fff;
  font-weight: 600;
  font-size: 0.4rem;
  text-align: center;
  line-height: 1.125rem;
}
</style>
