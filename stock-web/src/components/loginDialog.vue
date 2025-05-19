<template>
  <div class="login_dialog_pag">
    <van-popup v-model="$store.state.dialogVisible" position="bottom" :close-on-click-overlay="clickOverlay" @click-overlay="overlay" :style="{ height: '50%' }">
      <div class="dialog_content">
        <div class="top_close">
          <div class="close" @click="close">
            <img src="@/assets/img/close3.png" alt="">
          </div>
        </div>
        <div class="welcome_dialog">
          <span>{{ $t('hj221') }}</span>
        </div>
        <div class="portrait">
          <div>
            <img src="@/assets/img/eslogo.png" alt />
          </div>
        </div>
        <div class="names">
          <span>{{ 'Neptune' }}</span>
        </div>
        <div class="go_login_btn background" @click="goToLogin()">
          <span class="title1">{{ $t('hj222') }}</span>
        </div>
        <div class="go_login_btn">
          <span class="title2" @click="goToReg()">{{ $t('hj223') }}</span>
        </div>
      </div>
    </van-popup>
  </div>
</template>

<script>
import * as api from "@/axios/api";

export default {
  name: "dialogVisible",
  props: {
    // dialogVisible: {
    //   type: Boolean,
    //   default: false
    // }
  },
  data() {
    return {
      //dialogVisible: false,
      routeShowPathArr: ['/home'],
      clickOverlay: false
    };
  },
  mounted() {
    if (!this.routeShowPathArr.includes(this.$route.path)) { // 查找不弹出的path
      this.getUserInfo();
    }
  },
  methods: {
    close() {
      this.$emit("close");
      //dialogVisible = false
    },
    goToLogin() {
      this.$emit("close");
      //this.dialogVisible = false;
      this.$router.push('/login')
    },
    overlay(){
      this.$emit("close");
    },
    goToReg() {
      this.$emit("close");
      //this.dialogVisible = false;
      this.$router.push('/register');
    },
    async getUserInfo() {
      // 获取用户信息
      // let data = await api.getUserInfo();
      // if (data.status === 0) {
      //   // 判断是否登录
      //   this.dialogVisible = false;
      // } else {
      //   setTimeout(() => {
      //   this.dialogVisible = true;
      // }, 1000);
      // }
    },
  }
};
</script>

<style scoped lang="less">
.login_dialog_pag {
  width: 100%;
  height: 100%;
  .dialog_content {
    width: 100%;
    height: 100%;
    padding: .4rem .8rem;
    .top_close {
      width: 100%;
      height: .7rem;
      display: flex;
      justify-content: flex-end;
      align-items: center;
      .close {
        width: .7rem;
        height: .7rem;
        background: rgb(158, 158, 158);
        border-radius: 50%;
        display: flex;
        align-items: center;
        justify-content: center;
        img {
          width: .4rem;
          height: .4rem;
        }
      }
    }
    .welcome_dialog {
      width: 100%;
      height: 1rem;
      display: flex;
      align-items: center;
      justify-content: center;
      font-size: .5615rem;
      margin-top: .5rem;
      span {
        font-weight: 600;
      }
    }
    .portrait {
      width: 100%;
      height: 2.5rem;
      display: flex;
      align-items: center;
      justify-content: center;
      margin-top: .7rem;
      div {
        width: 2.8rem;
        height: 2.8rem;
        background: #ccc;
        border-radius: 50%;
        overflow: hidden;
        img {
          width: 100%;
          height: 100%;
        }
      }
    }
    .names {
      width: 100%;
      height: 1rem;
      display: flex;
      align-items: center;
      justify-content: center;
      font-size: .359rem;
      margin-top: .1rem;
      span {
        font-weight: 600;
      }
    }
    .go_login_btn {
      width: 100%;
      height: 1.3rem;
      border-radius: .23rem;
      display: flex;
      align-items: center;
      justify-content: center;
      font-size: .4015rem;
      margin-top: .5rem;
      .title1 {
        color: #fff;
        font-weight: 600;
      }
      .title2 {
        font-weight: 600;
        color: #2d6ae9;
      }
    }
    .go_login_btn.background {
      background: #2d6ae9;
    }
  }
}
.van-popup.van-popup--bottom{
  border-radius: .2rem .2rem 0 0;
}
</style>