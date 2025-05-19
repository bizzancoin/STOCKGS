<template>
  <div class="user_page">
    <div class="content">
      <div class="top_icon">
        <div class="right_icon">
          <div @click="goToTopUp()">
            <img src="@/assets/img/qianbao.png" alt />
          </div>
          <div @click="goOnline" style="justify-content: flex-end;">
            <img style="width: 0.5rem;height: 0.5rem;" src="@/assets/img/kefu.png" alt />
          </div>
        </div>
      </div>
      <div class="users" @click="goWall()">
        <div class="left_tou">
          <div class="left_tx">
            <div>
              <img src="@/assets/img/eslogo.png" alt />
            </div>
          </div>
          <div class="right_name">
            <span>{{ userInfo.realName ? userInfo.realName : userInfo.phone }}</span>
          </div>
        </div>
        <div class="right_go">
          <img src="@/assets/img/youjiantou.png" alt />
        </div>
      </div>
      <div class="center_card">
        <div class="keyon">
          <span>{{ $t('hj49') }}</span>
        </div>
        <div class="num_price" v-if="userInfo.length == 0">
          ¥0
        </div>
        <div class="num_price" v-if="userInfo.length != 0">
          <p v-if="this.$store.state.settingForm.indexDisplay && !this.$store.state.settingForm.futuresDisplay"
            class="account">
            ¥{{ $store.state.hide ? '****' : Number($store.state.userInfo.userAmt +
                $store.state.userInfo.userIndexAmt).toFixed(2)
            }}
          </p>
          <p v-else-if="!this.$store.state.settingForm.indexDisplay && this.$store.state.settingForm.futuresDisplay"
            class="account">
            ¥{{ $store.state.hide ? '****' : Number($store.state.userInfo.userAmt +
                $store.state.userInfo.userFuturesAmt).toFixed(2)
            }}
          </p>
          <p v-else-if="!this.$store.state.settingForm.indexDisplay && !this.$store.state.settingForm.futuresDisplay"
            class="account">¥&nbsp;&nbsp;{{ $store.state.hide ? '****' :
                Number($store.state.userInfo.userAmt).toFixed(2)
            }}</p>
        </div>
        <div class="yk es">
          <div>
            <span>{{ $t('hj55') }}</span>
          </div>
          <div>
            <span>{{ $t('hj141') }}</span>
          </div>

          <div>
            <span>{{ $t('hj48') }}</span>
          </div>
        </div>
        <div class="yk as">
          <div>
            <span class="orenge">¥ {{ userInfo.length != 0 ? ($store.state.userInfo.allFreezAmt + $store.state.userInfo.djzj).toFixed(2) : 0
            }}</span>
          </div>
          <div v-if="userInfo.length == 0">
            <span v-if="!selectUserFlag">
              <span>{{ '¥ 0' }}</span>
            </span>
            <span v-else>
              <span>{{ '¥ 0' }}</span>
            </span>
          </div>
          <div v-if="userInfo.length != 0">
            <span v-if="!selectUserFlag">
              <span
                :class="$store.state.userInfo.allIndexProfitAndLose > 0 ? ' red' : $store.state.userInfo.allIndexProfitAndLose < 0 ? ' green' : ''">{{
                    '¥ ' + $store.state.userInfo.allIndexProfitAndLose
                }}</span>
            </span>
            <span v-else>
              <span
                :class="$store.state.userInfo.allProfitAndLose > 0 ? ' red' : $store.state.userInfo.allProfitAndLose < 0 ? ' green' : ''">{{
                    '¥ ' + $store.state.userInfo.allProfitAndLose
                }}</span>
            </span>
          </div>

          <div v-if="userInfo.length == 0">
            <span class="bzz" v-if="!selectUserFlag">{{ '¥ 0' }}</span>
            <span class="bzz" v-else>{{ '¥ 0' }}</span>
          </div>
          <div v-if="userInfo.length != 0">
            <span class="bzz" v-if="!selectUserFlag">{{ '¥ ' + $store.state.userInfo.enableIndexAmt }}</span>
            <span class="bzz" v-else>{{ '¥ ' + $store.state.userInfo.enableAmt }}</span>
          </div>

        </div>
        <div class="btns" @click="handleZh()" :class="selectUserFlag ? '' : 'active'">
          <span>{{ selectUserFlag ? $t('hj142') : $t('hj143') }}</span>
        </div>
      </div>
      <div class="jy" @click="goToSettings()">
        <div class="left_gn">
          <div class="l_icon">
            <img src="../../assets/img/xiugaimima.png" alt />
          </div>
          <div class="r_title">
            <span>{{ $t('hj144') }}</span>
          </div>
        </div>
        <div class="right_gos">
          <img src="../../assets/img/youjiantou.png" alt />
        </div>
      </div>
      <div class="jy" @click="handleGoToTransfer()">
        <div class="left_gn">
          <div class="l_icon">
            <img src="../../assets/img/huazhuan2.png" alt />
          </div>
          <div class="r_title">
            <span>{{ $t('hj145') }}</span>
          </div>
        </div>
        <div class="right_gos">
          <img src="../../assets/img/youjiantou.png" alt />
        </div>
      </div>
      <div class="jy" @click="handleGoToAuthentication()">
        <div class="left_gn">
          <div class="l_icon">
            <img src="../../assets/img/shiming.png" alt />
          </div>
          <div class="r_title">
            <span>{{ $t('hj146') }}</span>
          </div>
        </div>
        <div class="right_gos">
          <img src="../../assets/img/youjiantou.png" alt />
        </div>
      </div>
      <div class="jy" @click="handleGoToBankCard()">
        <div class="left_gn">
          <div class="l_icon">
            <img src="../../assets/img/shiming.png" alt />
          </div>
          <div class="r_title">
            <span>{{ $t('hj147') }}</span>
          </div>
        </div>
        <div class="right_gos">
          <img src="../../assets/img/youjiantou.png" alt />
        </div>
      </div>
      <div class="jy" @click="handleOutLoginClick()">
        <div class="left_gn">
          <div class="l_icon">
            <img src="../../assets/img/out2.png" alt />
          </div>
          <div class="r_title">
            <span>{{ $t('hj148') }}</span>
          </div>
        </div>
        <div class="right_gos">
          <img src="../../assets/img/youjiantou.png" alt />
        </div>
      </div>
    </div>
    <van-popup v-model="settingDialog" position="bottom" :style="{ height: '40%' }">
      <div class="setting_content">
        <div class="old_password">
          <div class="left_titles">
            <span>{{ $t('hj150') + ':' }}</span>
          </div>
          <div class="right_password_input">
            <input type="password" v-model="oldPassword" />
          </div>
        </div>
        <div class="old_password">
          <div class="left_titles">
            <span>{{ $t('hj151') + ':' }}</span>
          </div>
          <div class="right_password_input">
            <input type="password" v-model="newPassword" />
          </div>
        </div>
        <div class="old_password">
          <div class="left_titles">
            <span>{{ $t('hj152') + ':' }}</span>
          </div>
          <div class="right_password_input">
            <input type="password" v-model="cirNewPassword" />
          </div>
        </div>
        <div class="btn_setting" @click="changeLoginPsd()">
          <span>{{ $t('hj153') }}</span>
        </div>
      </div>
    </van-popup>

  </div>
</template>

<script>
import * as api from "@/axios/api";
import { Toast } from "mint-ui";
import { isNull, pwdReg } from "@/utils/utils";
import { MessageBox } from 'mint-ui'


export default {
  name: "newUser",
  data() {
    return {
      name: "大狗子",
      selectUserFlag: true,
      settingDialog: false,
      oldPassword: "", // 旧密码
      newPassword: "", // 新密码
      cirNewPassword: "", //确认新密码
      userInfo: [],
      onlineService: "",
    };
  },
  components: {
  },
  created() {
    this.getUserInfo();
    this.getInfoSite();
  },
  methods: {
    goOnline() {
      if (navigator.vibrate) {
        // 支持
        navigator.vibrate([55]);
      }
      this.$router.push('/service');
    },
    async getInfoSite() {
      let data = await api.getInfoSite()
      if (data.status === 0) {
        this.onlineService = data.data.onlineService
      } else {
        Toast(data.msg)
      }
    },
    goWall() {
      if (this.userInfo.length == 0) {
        this.$store.commit('dialogVisible', true);
        return;
      }
      this.$router.push('/wallet')
    },
    handleZh() {
      this.selectUserFlag = !this.selectUserFlag;

      if (this.userInfo.length == 0) {
        this.$store.commit('dialogVisible', true);
        return;
      }
      if (navigator.vibrate) {
        // 支持
        navigator.vibrate([55]);
      }
    },
    async getUserInfo() {
      // 获取用户信息
      let data = await api.getUserInfo();
      if (data.status === 0) {
        // 判断是否登录
        this.$store.commit('dialogVisible', false);
        this.$store.state.userInfo = data.data;
        this.userInfo = data.data;
      } else {
        this.$store.commit('dialogVisible', true);
      }
    },
    goToTopUp() {
      if (this.userInfo.length == 0) {
        this.$store.commit('dialogVisible', true);
        return;
      }
      if (navigator.vibrate) {
        // 支持
        navigator.vibrate([55]);
      }
      this.$router.push("/wallet");
    },
    handleOutLoginClick() {
      // 退出登录
      MessageBox.confirm(this.$t('hj149') + '?',this.$t('hj165'), {
        confirmButtonText: this.$t('hj161'),
        cancelButtonText: this.$t('hj106'),
      }).then(() => {
        this.toRegister();
      }).catch(() => {

      });
    },
    goToSettings() {
      if (this.userInfo.length == 0) {
        this.$store.commit('dialogVisible', true);
        return;
      }
      // 每次打开dialog 清空密码数据
      this.settingDialog = !this.settingDialog;
      if (this.settingDialog) {
        this.oldPassword = "";
        this.newPassword = "";
        this.cirNewPassword = "";
      }
    },
    handleGoToTransfer() {
      if (this.userInfo.length == 0) {
        this.$store.commit('dialogVisible', true);
        return;
      }
      this.$router.push("/transfers");
    },
    handleGoToAuthentication() {
      if (this.userInfo.length == 0) {
        this.$store.commit('dialogVisible', true);
        return;
      }
      this.$router.push("/authentications");
    },
    handleGoToBankCard() {
      if (this.userInfo.length == 0) {
        this.$store.commit('dialogVisible', true);
        return;
      }
      this.$router.push("/bankCard");
    },
    async toRegister() {
      // 注销登陆
      window.localStorage.removeItem("USERTOKEN"); // 清空本地存储 USERTOKEN字段
      this.clearCookie();
      let data = await api.logout();
      if (data.status === 0) {
        // Toast(data.msg)
        this.$router.push("/login");
      } else {
        Toast(data.msg);
      }
      this.$router.push("/login");
    },
    async changeLoginPsd() {
      // 修改密码
      if (
        isNull(this.oldPassword) ||
        isNull(this.newPassword) ||
        isNull(this.cirNewPassword)
      ) {
        Toast(this.$t('hj154'));
        this.settingDialog = false;
      } else if (!pwdReg(this.newPassword)) {
        Toast(this.$t('hj19'));
        this.settingDialog = false;
      } else {
        // 修改密码
        if (this.newPassword === this.cirNewPassword) {
          let opts = {
            oldPwd: this.oldPassword,
            newPwd: this.newPassword
          };
          let data = await api.changePassword(opts);
          if (data.status === 0) {
            this.changeLoginPsdBox = false;
            Toast(data.msg);
            this.settingDialog = false;
          } else {
            Toast(data.msg);
            this.settingDialog = false;
          }
        } else {
          Toast(this.$t('hj155'));
          this.settingDialog = false;
        }
      }
      if (navigator.vibrate) {
        // 支持
        navigator.vibrate([55]);
      }
    }
  }
};
</script>

<style scoped lang="less">
.green {
  color: #028f52 !important;
}

.red {
  color: #b60c0d !important;
}

.user_page {
  width: 100%;
  height: calc(100% - 1.3rem);

  .content {
    width: 100%;
    height: 100%;
    padding: 0 0.3rem;

    .top_icon {
      width: 100%;
      height: 1.5rem;
      display: flex;
      justify-content: flex-end;
      align-items: center;

      .right_icon {
        width: 17%;
        height: 50%;
        display: flex;

        >div {
          width: 100%;
          height: 100%;
          display: flex;
          justify-content: space-between;
          align-items: center;

          img {
            width: 0.6rem;
            height: 0.6rem;
          }
        }
      }
    }

    .users {
      width: 100%;
      height: 1.7949rem;
      background: #fff;
      border-radius: 0.15rem;
      display: flex;
      align-items: center;
      justify-content: space-between;

      .left_tou {
        width: 80%;
        height: 70%;
        display: flex;

        .left_tx {
          width: 20%;
          height: 100%;
          margin-left: 0.3rem;
          display: flex;
          align-items: center;
          justify-content: center;

          >div {
            width: 1rem;
            height: 1rem;
            border-radius: 50%;
            overflow: hidden;
            background: rgb(211, 211, 211);

            img {
              width: 100%;
              height: 100%;
            }
          }
        }

        .right_name {
          width: 80%;
          height: 100%;
          display: flex;
          align-items: center;
          font-size: 0.4415rem;

          span {
            font-weight: 600;
          }
        }
      }

      .right_go {
        width: 20%;
        height: 70%;
        display: flex;
        justify-content: flex-end;
        align-items: center;
        padding-right: 0.2rem;

        img {
          width: 0.6rem;
          height: 0.6rem;
        }
      }
    }

    .center_card {
      width: 100%;
      // height: 5.3846rem;
      background-image: linear-gradient(to right bottom, #ffffff, #dfedfc);
      // background-image: linear-gradient(to right, #ffffff , #dfedfc);
      margin-top: 0.5rem;
      border-radius: 0.15rem;
      padding: 0.5rem 0.4rem;

      .keyon {
        width: 100%;
        font-size: 0.359rem;
        display: flex;
        align-items: center;
        color: #3d4144;
        font-weight: 600;
        justify-content: center;
        padding-bottom: 0.2rem;

        span {
          font-weight: 600;
          font-size: 0.6rem;
        }
      }

      .num_price {
        width: 100%;
        // height: 0.6667rem;
        margin-top: 0.1rem;
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 0.5528rem;
        padding-bottom: 0.3rem;

        span {
          font-weight: 600;
        }
      }

      .account {
        font-size: 0.6rem;
        font-weight: 600;
      }

      .yk {
        width: 100%;
        height: 0.5rem;
        display: flex;
        align-items: center;
        justify-content: space-between;

        >div {
          width: 33%;
          height: 100%;
          color: #97989d;
          display: flex;
          align-items: center;
          justify-content: center;
        }

        .orenge {
          // color: rgb(216, 141, 1) !important;
        }
      }

      .yk.es {
        margin-top: 0.3rem;
      }

      .yk.as {
        font-size: 0.4033rem;
        margin-top: 0.1rem;

        span {
          color: #000;
          font-weight: 600;
        }

        .bzz {
          // color: #4ea364;
        }
      }

      .btns {
        width: 100%;
        height: 1.2821rem;
        display: flex;
        align-items: center;
        justify-content: center;
        border-radius: 0.25rem;
        background: #2d6ae9;
        font-size: 0.4015rem;
        color: #fff;
        margin-top: 0.35rem;

        span {
          font-weight: 600;
        }
      }

      .active {
        background: #4ea364;
      }
    }

    .jy {
      width: 100%;
      height: 1.5rem;
      border-radius: 0.2rem;
      background: #fff;
      display: flex;
      align-items: center;
      justify-content: space-between;
      padding: 0 0.3rem;
      margin-top: 0.3rem;

      .left_gn {
        // width: 40%;
        height: 60%;
        display: flex;

        .l_icon {
          // width: 30%;
          height: 100%;
          display: flex;
          justify-content: center;
          align-items: center;

          img {
            width: 0.5041rem;
            height: 0.5041rem;
          }
        }

        .r_title {
          // width: 70%;
          height: 100%;
          display: flex;
          align-items: center;
          font-size: 0.4046rem;
          color: #404040;
          padding-left: 0.3rem;

          span {
            font-weight: 600;
          }
        }
      }

      .right_gos {
        // width: 20%;
        height: 60%;
        display: flex;
        align-items: center;
        justify-content: flex-end;

        img {
          width: 0.6rem;
          height: 0.6rem;
        }
      }
    }
  }
}

.setting_content {
  width: 100%;
  height: 5rem;
  padding: 0.3rem;

  .old_password {
    width: 100%;
    height: 1rem;
    background: rgb(243, 243, 243);
    border-radius: 0.15rem;
    display: flex;
    margin-top: 0.5rem;

    .left_titles {
      width: 25%;
      height: 100%;
      display: flex;
      align-items: center;
      padding-left: 0.2rem;

      // justify-content: flex-end;
      span {
        font-weight: 600;
      }
    }

    .right_password_input {
      width: 80%;
      height: 100%;
      display: flex;
      align-items: center;

      input {
        width: 100%;
        height: 100%;
        padding-left: 0.2rem;
      }
    }
  }

  .btn_setting {
    width: 100%;
    height: 1.3rem;
    border-radius: 0.15rem;
    background: #2d6ae9;
    color: #fff;
    font-size: 0.4615rem;
    margin-top: 0.5rem;
    display: flex;
    align-items: center;
    justify-content: center;

    span {
      font-weight: 600;
    }
  }
}
</style>