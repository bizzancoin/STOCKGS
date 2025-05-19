<template>
  <div id="app" :class="`${$state.theme === 'red' ? 'red-theme' : 'black-theme'}`">
    <div :class="`header-box`" v-if="hasHeader && title !== '用户登录'">
      <mt-header :title="title">
        <mt-button icon="back" slot="left" @click="$router.go(-1)"></mt-button>
        <div v-if="!is_Show">
          <template v-if="iconRight == 'search'">
            <img slot="right" class="search-right" src="./assets/ico/fangdajing.png" alt />
          </template>
          <template v-else>
            <mt-button icon="more" slot="right"></mt-button>
          </template>
        </div>
      </mt-header>
    </div>
    <div class="body-box">
      <transition :name="transitionName">
        <router-view></router-view>
      </transition>
    </div>
    <foot v-if="$route.meta.show !== true" @close="footColse"></foot>
    <elalert></elalert>
    <!-- <loginPopup @close="dialogClose" /> -->
  </div>
</template>

<script>
import elalert from "@/components/elalert.vue";
import foot from "@/components/foot.vue";
import loginPopup from "@/components/loginDialog.vue";
//引入css
import "@/assets/iconfont/iconfont.css";
import "@/assets/iconfont/iconfont.ttf";

export default {
  components: {
    foot,
    elalert,
    loginPopup
  },
  name: "App",
  created() {
    this.$state.theme = "red";
    console.log(this.$state.theme, "asdasdasd")
    let title = this.$route.meta.title || "首页";
    this.title = title;
    if (this.$route.meta.is_Show) {
      this.is_Show = this.$route.meta.is_Show;
    } else {
      this.is_Show = true;
    }
    if (this.$route.meta.hasHeader) {
      this.hasHeader = true;
    } else {
      this.hasHeader = false;
    }
    if (this.$route.meta.iconRight) {
      this.iconRight = this.$route.meta.iconRight;
    } else {
      this.iconRight = "default";
    }
    // this.$store.state.className = window.localStorage.getItem('styleName')?window.localStorage.getItem('styleName'):'red'
  },
  watch: {
    $route(to, from) {
      let title = to.meta.title || "首页";
      this.title = title;
      if (to.meta.iconRight) {
        this.iconRight = to.meta.iconRight;
      } else {
        this.iconRight = "default";
      }
      if (to.meta.hasHeader) {
        this.hasHeader = true;
      } else {
        this.hasHeader = false;
      }

      if (to.meta.index > from.meta.index) {
        console.log("slide-left");
        //设置动画名称
        this.transitionName = "slide-left";
      } else {
        console.log("slide-right");
        this.transitionName = "slide-right";
      }
    }
  },
  data() {
    return {
      title: "首页",
      hasHeader: false,
      iconRight: "default",
      transitionName: '',
      is_Show: true,
      is_foot: true,
    };
  },
  methods: {
    footColse() {
      this.$store.commit('dialogVisible', true);
    },
    dialogClose() {
      this.$store.commit('dialogVisible', false);
    }
  }
};
</script>

<style lang="less" scoped>
/deep/ .van-list__finished-text {
  line-height: 1.925926rem !important;
}

/deep/ .van-list__error-text {
  line-height: 1.925926rem !important;
}

#app {
  width: 100vw;
  height: 100vh;
  overflow: hidden;
  font-family: "rubik";

  .header-box {
    width: 100%;
    height: 1.4rem;

    /deep/.mint-header {
      height: 100%;
      background-color: #16171d;

      // background-color: rgba(20, 45, 55, 0.3);
      .is-left {
        .mintui {
          font-size: 20px;
        }
      }

      .mint-header-title {
        font-size: 0.36rem;
        color: rgba(255, 255, 255, 1);
      }
    }

    button {
      outline: none;
    }
  }

  .body-box {
    width: 100%;
    height: 100%;
    // height: calc(100% - 1rem);
    box-sizing: border-box;
    overflow-y: auto;
  }

  &.red-theme {
    background: #fff;
    color: #000;

    /deep/.mint-header {
      background: none;

      .mint-header-title {
        font-size: 0.42rem;
        color: #212121;
      }

      .mintui {
        color: #212121;
      }
    }

    /deep/.van-swipe-item {
      color: #000;
    }

    /deep/.navs_content {
      background: #fff !important;
    }

    /deep/.fo_content {
      background: #fff;
    }

    /deep/.news-tab {
      background: #fff;
    }

    /deep/.mint-tab-item-label {
      color: #000;
      font-size: .26rem;

    }

    /deep/.top_title {
      color: #000;
    }

    /deep/.titContent {
      color: #000;
    }

    /deep/.mint-navbar {
      background: #fff;
    }

    /deep/.mint-tab-item {
      background: #fff;
      border: none;
    }

    /deep/.footCss {
      background: #f5f5f5;
    }

    /deep/.newDetail-tits {
      color: #aaa;
    }

    /deep/.van-skeleton__row,
    .van-skeleton__title {
      background-color: #fff !important;
    }
  }

  &.black-theme {
    // background: #16171d;
    // background: rgb(14, 14, 15);
    background: rgb(33, 33, 43);

    /deep/.navs_content {
      background: #272733;
    }

    /deep/.fo_content {
      background: #272733;
    }

    /deep/.news-tab {
      background: #272733;
    }

    /deep/.mint-navbar {
      background: #272733;
    }

    /deep/.mint-tab-item-label {
      color: #fff;
      font-size: .26rem;

    }

    /deep/.titContent {
      color: #000;
    }

    /deep/.mint-tab-container {}

    /deep/.mint-tab-item {
      background: #272733;
    }

    /deep/.van-swipe-item {
      color: #000;
    }

    /deep/.footCss {
      background: rgb(33, 33, 43);
    }

    /deep/.newDetail-tits {
      color: #999;
    }
  }
}

.search-right {
  width: 0.3rem;
  height: 0.3rem;
}

.mint-search-list {
  position: relative !important;
}

.topLeft {}

/deep/.el-alert {
  width: 75% !important;
  height: 1.1rem !important;
  position: absolute !important;
  top: 0 !important;
  bottom: 0 !important;
  left: 0 !important;
  right: 0 !important;
  margin: auto !important;
  font-size: 0.36rem !important;
}

/deep/.el-alert__title {
  font-size: 0.36rem !important;
}

/deep/.el-icon-close::before {
  display: none;
}

/deep/.icon-icon_A::before {
  font-size: 0.6rem !important;
}
</style>
