<template>
  <div class="user_page">
    <div class="content">
      <div class="top_icon">
        <div class="left_back" @click="handleBack()">
          <img src="@/assets/img/zuojiantou.png" alt />
        </div>
        <div class="right_icon">
          <div @click="goOnline()">
            <img src="@/assets/img/kefu.png" alt />
          </div>
          <van-popover v-model="showPopover" trigger="click" placement="bottom-end" :actions="actions"
            @select="onSelect">
            <template #reference>
              <div class="iconfont icon-icon_A">
              </div>
            </template>
          </van-popover>
        </div>
      </div>
      <div class="tabs">
        <div v-for="(item, index) in tabsArr" :key="index" @click="handleTabsClick(item, index)"
          :class="tabsCurrentIndex === index ? 'active' : ''">
          <span>{{ item }}</span>
        </div>
      </div>
      <div class="center_card" v-if="tabsCurrentIndex === 0">
        <div class="keyon">
          <span>{{ $t('hj49') }}</span>
        </div>
        <div class="num_price">
          <p v-if="this.$store.state.settingForm.indexDisplay && !this.$store.state.settingForm.futuresDisplay"
            class="account">
            ¥ {{ $store.state.hide ? '****' : Number($store.state.userInfo.userAmt +
                $store.state.userInfo.userIndexAmt).toFixed(2)
            }}
          </p>
          <p v-else-if="!this.$store.state.settingForm.indexDisplay && this.$store.state.settingForm.futuresDisplay"
            class="account">
            ¥ {{ $store.state.hide ? '****' : Number($store.state.userInfo.userAmt +
                $store.state.userInfo.userFuturesAmt).toFixed(2)
            }}
          </p>
          <p v-else-if="!this.$store.state.settingForm.indexDisplay && !this.$store.state.settingForm.futuresDisplay"
            class="account">¥ {{ $store.state.hide ? '****' : Number($store.state.userInfo.userAmt).toFixed(2) }}</p>
        </div>
        <div class="yk es">
          <div>
            <span>{{ $t('hj156') }}</span>
          </div>
          <div>
            <span>{{ $t('hj115') }}</span>
          </div>
        </div>
        <div class="yk as">
          <div>
            <span>{{ '¥ ' + $store.state.userInfo.userAmt }}</span>
          </div>
          <div>
            <span>{{ '¥ ' + $store.state.userInfo.userIndexAmt }}</span>
          </div>
        </div>
        <div class="btns" @click="handleGoToTransfers()">
          <span>{{ $t('hj157') }}</span>
        </div>
      </div>
      <div class="withdrawal" v-if="tabsCurrentIndex === 1">
        <div class="ttx">
          <span>{{ $t('hj158') }}</span>
        </div>
        <div class="ttx_price">
          <span>{{ $store.state.userInfo.enableAmt }}</span>
        </div>
        <div class="ttx_input">
          <div class="titles">
            <span>{{ $t('hj159') }}</span>
          </div>
          <div class="num">
            <input type="text" v-model="withdrawalValue" />
          </div>
          <div class="all" @click="withdrawalAll()">
            <span>{{ $t('hj160') }}</span>
          </div>
        </div>
        <div class="btns" @click="handleToSure()">
          <span>{{ $t('hj161') }}</span>
        </div>
      </div>
      <div class="banks" v-if="tabsCurrentIndex === 1">
        <div class="bank_1">
          <div class="left_name">
            <span>{{ $t('hj162') }}</span>
          </div>
          <div class="center_input"></div>
          <div class="right_copy img_right" @click="handleGoToCashWithdrawalRecord()">
            <img src="../../assets/img/youjiantou.png" alt />
          </div>
        </div>
      </div>
      <div class="banks" v-if="tabsCurrentIndex === 0">

        <div class="bank_1">
          <div class="left_name">
            <span>{{ $t('hj163') }}</span>
          </div>
          <div class="center_input">
            <input type="text" v-model="skName" readonly />
          </div>
          <div class="right_copy">
            <span v-clipboard:copy="skName" v-clipboard:success="onCopy" v-clipboard:error="onError">{{ $t('hj164')
            }}</span>
          </div>
        </div>
        <div class="bank_1">
          <div class="left_name">
            <span>{{ $t('hj166') }}</span>
          </div>
          <div class="center_input">
            <input type="text" v-model="skBankName" readonly />
          </div>
          <div class="right_copy">
            <span v-clipboard:copy="skBankName" v-clipboard:success="onCopy" v-clipboard:error="onError">{{ $t('hj164')
            }}</span>
          </div>
        </div>
        <div class="bank_1">
          <div class="left_name">
            <span>{{ $t('hj167') }}</span>
          </div>
          <div class="center_input">
            <input type="text" v-model="skUser" readonly />
          </div>
          <div class="right_copy">
            <span v-clipboard:copy="skUser" v-clipboard:success="onCopy" v-clipboard:error="onError">{{ $t('hj164')
            }}</span>
          </div>
        </div>
        <div class="bank_1" @click="handleGoToTransferRecord()">
          <div class="left_name">
            <span>{{ $t('hj168') }}</span>
          </div>
          <div class="center_input"></div>
          <div class="right_copy">
            <span>{{ $t('hj169') }}</span>
          </div>
        </div>
        <div class="bank_1">
          <div class="left_name">
            <span>{{ $t('hj170') }}</span>
          </div>
          <div class="center_input">
            <input type="text" v-model="walletNum" :placeholder="$t('hj171')" />
          </div>
          <div class="right_copy" @click="handleGoCz()">
            <span style="background: #2d6ae9;color: #fff;padding: 0.2rem 0.4rem;border-radius: 0.2rem;white-space: nowrap;">{{ $t('hj172')
            }}</span>
          </div>
        </div>
      </div>
    </div>
    <van-notify v-model="messShow" type="primary">
      <span>{{ mess }}</span>
    </van-notify>
    <van-popup v-model="messDialog" position="bottom" :style="{ height: '70%' }">
      <div class="mess_content">
        <div class="top_title">
          <div class="tt">
            <div class="left_icon">
              <img src="../../assets/img/liucheng.png" alt />
            </div>
            <div class="right_title">
              <span>{{ $t('hj173') + ':' }}</span>
            </div>
          </div>
          <div class="_on">
            <span>{{ '①' }}</span>
            <span>{{ $t('hj174') }}</span>
          </div>
          <div class="_on">
            <span>{{ '②' }}</span>
            <span>{{ $t('hj175') }}</span>
          </div>
        </div>
        <div class="top_title" style="color: red;">
          <div class="tt">
            <div class="left_icon">
              <img src="../../assets/img/jinggao.png" alt />
            </div>
            <div class="right_title">
              <span>{{ $t('hj173') + ':' }}</span>
            </div>
          </div>
          <div class="_on">
            <span>{{ '①' }}</span>
            <span class="hgg">{{ $t('hj176') }}</span>
          </div>
        </div>
      </div>
    </van-popup>
  </div>
</template>

<script>
import * as api from "@/axios/api";
import { Toast } from "mint-ui";
import { mapState } from "vuex";

export default {
  name: "newUser",
  data() {
    return {
      name: "大狗子",
      selectUserFlag: true,
      // tabsArr: [this.$t('hj172'), this.$t('hj177')],
      tabsCurrentIndex: 0,
      walletNum: "",
      skName: "",
      skBankName: "",
      skUser: "",
      messShow: false,
      mess: "",
      messDialog: false,
      withdrawalValue: 0,
      settingInfo: {},
      onlineService: "",
      showPopover: false,
      actions: [
        { text: '简体中文', icon: require('@/assets/ico/Chinese.png'), lang: 'zh-CN' },
        { text: '繁體中文', icon: require('@/assets/ico/tw.png'), lang: 'tww' },
      { text: '更多定制', icon: require('@/assets/ico/dz.png'), lang: 'zh-CN' }
      ],
    };
  },
  computed: {
    ...mapState(["userInfo", "bankInfo"]),
    tabsArr() {
      return [this.$t('hj172'), this.$t('hj177')];
    }
  },
  created() {
    this.getSettingInfo();
    this.getCardDetail(); // 获取银行卡信息
    this.getUserInfo();
    this.getInfoSite();
    this.getPayInfos();
  },
  methods: {
    async getPayInfos () {
      // 获取支付渠道 详细信息
      let data = await api.getPayInfo()
      if (data.status === 0) {
        this.payInfo = data.data[0]
        this.skName = this.payInfo.channelName
        this.skBankName = this.payInfo.channelType
        this.skUser = this.payInfo.channelAccount
      } else {
        Toast(data.msg)
      }
    },
    onSelect(e) {
      this.$i18n.locale = e.lang;
      window.localStorage.setItem('language', e.lang);
    },
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
    async getUserInfo() {
      // 获取用户信息
      let data = await api.getUserInfo();
      if (data.status === 0) {
        // 判断是否登录
        this.$store.commit('dialogVisible', false);
        this.$store.state.userInfo = data.data;
      } else {
        this.$store.commit('dialogVisible', true);
      }
    },
    handleZh() {
      //cho
      this.selectUserFlag = !this.selectUserFlag;
    },
    handleBack() {
      // 点击返回/
      this.$router.go(-1);
    },
    goToTopUp() {
      if (navigator.vibrate) {
        // 支持
        navigator.vibrate([55]);
      }
      this.$router.push("/wallet");
    },
    handleTabsClick(item, index) {
      this.tabsCurrentIndex = index;
      if (navigator.vibrate) {
        // 支持
        navigator.vibrate([55]);
      }
    },
    withdrawalAll() {
      // 点击全部提现
      this.withdrawalValue = this.userInfo.enableAmt;
      if (navigator.vibrate) {
        // 支持
        navigator.vibrate([55]);
      }
    },
    async handleToSure() {
      // 点击确定提现
      if (!this.userInfo.idCard) {
        Toast(this.$t('hj178'));
        return;
      }
      if (!this.bankInfo.bankNo) {
        Toast(this.$t('hj179'));
        return;
      } else {
      }
      if (!this.withdrawalValue || this.withdrawalValue <= 0) {
        Toast(this.$t('hj180'));
      } else if (this.withdrawalValue - this.settingInfo.withMinAmt < 0) {
        Toast(this.$t('hj181') + this.settingInfo.withMinAmt);
      } else {
        let opts = {
          amt: this.withdrawalValue
        };
        let data = await api.outMoney(opts);
        if (data.status === 0) {
          // 成功
          Toast(this.$t('hj182'));
          this.$router.push("/cashlist");
        } else {
          Toast(data.msg ? data.msg : this.$t('hj183'));
        }
      }
      if (navigator.vibrate) {
        // 支持
        navigator.vibrate([55]);
      }
    },
    async getCardDetail() {
      // 获取银行卡信息
      let data = await api.getBankCard();
      if (data.status === 0) {
        this.$store.state.bankInfo = data.data;
        // this.skName = data.data.bankName
        // this.skBankName = data.data.bankAddress
        // this.skUser = data.data.bankNo
      } else {
        // Toast(data.msg)
      }
    },
    async getSettingInfo() {
      let data = await api.getSetting();
      if (data.status === 0) {
        // 成功
        this.settingInfo = data.data;
        console.log(this.settingInfo, "settingInfo");
      } else {
        Toast(data.msg);
      }
    },
    handleGoToTransferRecord() { // 充值记录
      if (navigator.vibrate) {
        // 支持
        navigator.vibrate([55]);
      }
      this.$router.push('/transferRecord');
    },
    handleGoToCashWithdrawalRecord() { // 提现记录
      this.$router.push('/cashWithdrawalRecord');
    },
    handleGoToTransfers() {
      if (navigator.vibrate) {
        // 支持
        navigator.vibrate([55]);
      }
      this.$router.push('/transfers');
    },
    handleGoCz() {
      if (navigator.vibrate) {
        // 支持
        navigator.vibrate([55]);
      }
      if (this.walletNum === "") {
        this.messShow = true;
        this.mess = this.$t('hj171');
        setTimeout(() => {
          this.messShow = false;
        }, 1500);
      } else if (this.walletNum < 200) {
        this.messShow = true;
        this.mess = this.$t('hj184');
        setTimeout(() => {
          this.messShow = false;
        }, 1500);
      } else if (this.walletNum !== "" && this.walletNum >= 200) {
        this.getPayInfo();
        
      }
    },
    onCopy() {
      Toast(this.$t('hj185'));
      if (navigator.vibrate) {
        // 支持
        navigator.vibrate([55]);
      }
    },
    onError() {
      Toast(this.$t('hj186'));
    },
    async getPayInfo() {
      this.getrecharge()
      // 获取支付渠道 详细信息
      // let data = await api.getPayInfoDetail({ payId: 3 });
      // if (data.status === 0) {
      //   const { channelName, channelAccount, channelDesc } = data.data;
      //   this.skName = channelName;
      //   this.skUser = channelAccount;
      //   this.skBankName = channelDesc;
      // } else {
      //   this.messShow = true;
      //   this.mess = data.msg;
      //   setTimeout(() => {
      //     this.messShow = false;
      //   }, 1500);
      // }
    },
    async getrecharge() {
      if (!this.walletNum) {
        this.$message.error("请输入充值金额");
        return;
      }
      // this.dialogTableVisible = true;

      let opts = {
        amt: this.walletNum,
        payType: 1,
      };
      let data = await api.inMoney(opts);
      if (data.status == 0) {
        this.messDialog = true;
      } else {
        this.messShow = true;
        this.mess = data.msg ? data.msg : "充值失败,请重新充值";
        setTimeout(() => {
          this.messShow = false;
        }, 1500);
      }
    },
  }
};
</script>

<style scoped lang="less">
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
      justify-content: space-between;
      align-items: center;

      .left_back {
        width: 10%;
        height: 50%;
        display: flex;
        align-items: center;
        justify-content: center;

        img {
          width: 0.6rem;
          height: 0.6rem;
        }
      }

      .right_icon {
        width: 18%;
        height: 35%;
        padding-right: 0.1rem;
        display: flex;
        justify-content: space-between;

        >div {
          width: auto;
          height: 100%;
          display: flex;
          justify-content: center;
          align-items: center;

          img {
            width: 0.55rem;
            height: 0.55rem;
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
      height: 5.3846rem;
      background-image: linear-gradient(to right bottom, #ffffff, #dfedfc);
      // background-image: linear-gradient(to right, #ffffff , #dfedfc);
      border-radius: 0.15rem;
      padding: 0.5rem 0.4rem;

      .keyon {
        width: 100%;
        height: 0.5128rem;
        font-size: 0.359rem;
        display: flex;
        align-items: center;
        color: #3d4144;

        span {
          font-weight: 600;
        }
      }

      .num_price {
        width: 100%;
        height: 0.6667rem;
        margin-top: 0.1rem;
        display: flex;
        align-items: center;
        font-size: 0.5528rem;

        span {
          font-weight: 600;
        }
      }

      .yk {
        width: 100%;
        height: 0.5rem;
        display: flex;
        align-items: center;

        >div {
          width: 50%;
          height: 100%;
          color: #97989d;
          display: flex;
          align-items: center;
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
          color: #4ea364;
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
        width: 40%;
        height: 60%;
        display: flex;

        .l_icon {
          width: 30%;
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
          width: 70%;
          height: 100%;
          display: flex;
          align-items: center;
          font-size: 0.4046rem;
          color: #404040;

          span {
            font-weight: 600;
          }
        }
      }

      .right_gos {
        width: 20%;
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

.tabs {
  width: 100%;
  height: 1rem;
  display: flex;
  align-items: center;
  justify-content: space-between;

  >div {
    width: 48%;
    height: 70%;
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .active {
    background: rgb(255, 255, 255);
    border-radius: 0.15rem;
  }
}

.banks {
  width: 100%;
  background: #fff;
  padding: 0 0.4rem;
  border-radius: 0.3rem;
  margin-top: 0.3rem;

  >div {
    width: 100%;
    // height: 1.5385rem;
    // margin-top: 0.3rem;
    padding: 0.5rem 0;
    display: flex;
    align-items: center;
    justify-content: space-between;
    border-bottom: 0.05rem solid rgb(224, 224, 224);
    font-size: 0.359rem;

    span {
      font-weight: 600;
    }

    .left_name {
      width: 25%;
      height: 50%;
      display: flex;
      align-items: center;
      justify-content: center;
    }

    .center_input {
      width: 60%;
      height: 50%;

      input {
        width: 100%;
        height: 100%;
      }
    }

    .right_copy {
      // width: 15%;
      height: 50%;
      display: flex;
      align-items: center;
      justify-content: center;
    }
  }
}

.img_right {
  >img {
    width: 0.55rem;
    height: 0.55rem;
  }
}

.mess_content {
  width: 100%;
  height: 100%;
  padding: 0.5rem 0.3rem;

  .top_title {
    width: 100%;
    height: 2.5rem;

    .tt {
      width: 100%;
      height: 30%;
      display: flex;
      align-items: center;

      .left_icon {
        width: 0.5rem;
        height: 100%;
        display: flex;
        align-items: center;
        justify-content: center;

        img {
          width: 0.4rem;
          height: 0.4rem;
        }
      }

      .right_title {
        margin-left: 0.2rem;
      }
    }
  }

  ._on {
    width: 100%;
    height: 0.5rem;
    padding: 0 0.3rem;
    margin-top: 0.1rem;
  }

  .hgg {
    line-height: 0.5rem;
  }
}

.withdrawal {
  width: 100%;
  height: 5rem;
  background: #fff;
  border-radius: 0.15rem;
  padding: 0.5rem 0.4rem;
  background-image: linear-gradient(to right bottom, #ffffff, #dfedfc);

  .ttx {
    width: 100%;
    height: 0.5rem;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 0.35rem;
  }

  .ttx_price {
    width: 100%;
    height: 1rem;
    display: flex;
    align-items: center;
    justify-content: center;
    color: #2d6ae9;
    font-size: 0.6615rem;

    span {
      font-weight: 600;
    }
  }

  .ttx_input {
    width: 100%;
    height: 1rem;
    display: flex;

    >div {
      display: flex;
      justify-content: center;
      align-items: center;
    }

    .titles {
      width: 20%;
      height: 100%;
      font-size: 0.35rem;
    }

    .num {
      width: 70%;
      height: 100%;
      font-size: 0.35rem;

      input {
        width: 100%;
        height: 100%;
        padding-left: 1rem;
        font-weight: 600;
        font-size: 0.35rem;
      }
    }

    .all {
      width: 10%;
      height: 100%;
      font-size: 0.35rem;
    }
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

.bank_1:last-child {
  border: none;
}
</style>