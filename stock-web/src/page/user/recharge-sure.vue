<template>
  <div class="wrapper">
    <!-- <div class="header">
      <mt-header title="充值说明">
        <router-link to="/recharge" slot="left">
          <mt-button icon="back">充值</mt-button>
        </router-link>
      </mt-header>
    </div> -->
    <div class="box1">
      <div class="form-block page-part">
        <mt-field label="充值金额" placeholder="充值金额" disabled type="number" v-model="selectNumber"></mt-field>
        <div v-if="type == 0">
          <mt-field label="支付宝名称" placeholder="支付宝名称" disabled type="text" v-model="payInfo.channelName">
          </mt-field>
          <mt-field label="支付宝账号" placeholder="支付宝账号" disabled type="text" v-model="payInfo.channelAccount">
                    <span v-clipboard:copy="payInfo.channelAccount"
                          v-clipboard:success="onCopy"
                          v-clipboard:error="onError">
                        <i class="iconfont icon-fuzhi"></i>复制
                    </span>
          </mt-field>
          <div class="text-right" style="padding: 0.2rem 0.3rem 0;" @click="popupVisible2 = true">查看支付宝限额说明</div>
        </div>
        <div v-else-if="type == 1">
          <mt-field label="收款名称" placeholder="收款名称" disabled type="text" v-model="payInfo.channelName">
                    <span v-clipboard:copy="payInfo.channelName"
                          v-clipboard:success="onCopy"
                          v-clipboard:error="onError">
                        <i class="iconfont icon-fuzhi"></i>复制
                    </span>
          </mt-field>
          <mt-field label="收款银行" placeholder="收款银行" disabled type="text" v-model="payInfo.channelDesc">
                    <span v-clipboard:copy="payInfo.channelDesc"
                          v-clipboard:success="onCopy"
                          v-clipboard:error="onError">
                        <i class="iconfont icon-fuzhi"></i>复制
                    </span>
          </mt-field>
          <!-- <mt-field label="收款支行" placeholder="收款支行" disabled type="text" v-model="payInfo.payBankAddress">
              <span v-clipboard:copy="info.payBankAddress"
                  v-clipboard:success="onCopy"
                  v-clipboard:error="onError">
                  <i class="iconfont icon-fuzhi"></i>复制
              </span>
          </mt-field> -->
          <mt-field label="收款账号" placeholder="收款账号" disabled type="text" v-model="payInfo.channelAccount">
                    <span v-clipboard:copy="payInfo.channelAccount"
                          v-clipboard:success="onCopy"
                          v-clipboard:error="onError">
                        <i class="iconfont icon-fuzhi"></i>复制
                    </span>
          </mt-field>
        </div>
        <div v-else>
          <mt-field label="收款方" placeholder="收款方" disabled type="text" v-model="payInfo.channelName">
          </mt-field>
          <mt-field label="收款账号" placeholder="收款账号" disabled type="text" v-model="payInfo.channelAccount">
                    <span v-clipboard:copy="payInfo.channelAccount"
                          v-clipboard:success="onCopy"
                          v-clipboard:error="onError">
                        <i class="iconfont icon-fuzhi"></i>复制
                    </span>
          </mt-field>
          <mt-field v-if="payInfo.channelDesc" label="备注" placeholder="备注" disabled type="text"
                    v-model="payInfo.channelDesc">
                    <span v-clipboard:copy="payInfo.channelDesc"
                          v-clipboard:success="onCopy"
                          v-clipboard:error="onError">
                        <i class="iconfont icon-fuzhi"></i>复制
                    </span>
          </mt-field>
          <div v-if="payInfo.channelImg" class="eq-code-img">
            <img :src="payInfo.channelImg" alt="">
          </div>
        </div>
      </div>
      <div v-if="type == 0">
        <div class="tips-group">
          <p><i class="iconfont icon-jinggao1"></i>注意事项：</p>
          <p class="tip-text"><i class="iconfont icon-jingpaibuzhou"></i>由于支付收款上限限制，每次收款公户可能不一样，<span class="red">请每次充值前获取最新的二维码完成支付</span>
          </p>
          <p class="tip-text"><i class="iconfont icon-jingpaibuzhou3"></i>如果您的网络环境不稳定，或由于设备、环境、偏好、行为、关系、账户、身份等维度问题，<span
            class="red">可能导致支付宝风控系统提示风险，请您在安全的环境下发起支付</span></p>
          <p class="tip-text"><i class="iconfont icon-jingpaibuzhou2"></i>为确保入金及时到账，<span class="red">请确认您输入的金额和提交的充值金额一致</span>.
          </p>
          <p class="tip-text"><i class="iconfont icon-jingpaibuzhou1"></i>受支付宝到账通知时间影响,入金时间到账时间可能会延迟，请耐心等待.</p>
        </div>
        <div v-if="payInfo.channelImg" class="btnbox">
          <span class="text-center btnok" @click="toSure">点击获取二维码</span>
        </div>
        <div v-if="false" class="tips-group">
          <p><i class="iconfont icon-liucheng"></i>充值方式：</p>
          <p class="tip-text"><i class="iconfont icon-buzhou"></i>将二维码保存到本地，打开支付宝扫描二维码，转账到平台指定对公账户</p>
          <p class="tip-text"><i class="iconfont icon-buzhou2"></i>点击“复制”，复制支付宝账号，打开支付宝点击转账，转账到平台指定对公账户</p>
        </div>
      </div>
      <div v-else-if="type == 1">
        <div class="tips-group">
          <p><i class="iconfont icon-liucheng"></i>操作流程：</p>
          <p class="tip-text"><i class="iconfont icon-buzhou"></i>点击“复制”，复制信息</p>
          <p class="tip-text"><i class="iconfont icon-buzhou2"></i>完成复制，打开手机银行进行转账</p>
        </div>
        <div class="tips-group red">
          <p><i class="iconfont icon-jinggao1"></i>注意事项：</p>
          <p class="tip-text"><i class="iconfont icon-jingpaibuzhou"></i>为确保入金及时到账，请确认您输入的金额和提交的充值金额一致.</p>
        </div>
      </div>
      <div v-else>

      </div>

    </div>

    <!-- 倒计时弹框 -->
    <mt-popup v-model="popupVisible" pop-transition='popup-fade' :closeOnClickModal="false" class="mint-popup-white">
      <div class="clearfix">
        <a @click="closePopup" class="pull-right"><i class="iconfont icon-weitongguo"></i></a>
      </div>
      <div class="box-block">
        <p class="text-center">
          <i v-if="type == 0" class="iconfont icon-umidd17"></i>
          <i v-if="type == 1" class="iconfont icon-02"></i>
        </p>
        <div class="prompt-box text-center">
          扫码后请输入以下金额支付
        </div>
        <p class="text-center money">
          ¥<span class="number">{{selectNumber}}</span>
        </p>
        <div class="qrCode">
          <!-- <img src="../../assets/img/timg.png" alt="二维码"> -->
          <img :src="payInfo.channelImg" alt="二维码">
          <div v-if="stopTime" class="alert-box">
            <i class="iconfont icon-jinggao2"></i>
            支付已过期
          </div>
        </div>
        <div class="timer-box">
          {{time.minutes}}:{{time.seconds}}
        </div>
        <div class="scan">
          <span v-if="type == 0">打开支付宝扫一扫</span>
          <span v-if="type == 1">打开微信扫一扫</span>
        </div>
      </div>
    </mt-popup>
    <mt-popup v-model="popupVisible2" pop-transition='popup-fade' :closeOnClickModal="false"
              class="mint-popup-white mint-popup-white1">
      <div class="clearfix">
        <a @click="popupVisible2 = false" class="pull-right"><i class="iconfont icon-weitongguo"></i></a>
      </div>
      <img width="100%" src="../../assets/img/xiane.png" alt="">
    </mt-popup>
  </div>
</template>

<script>
import * as api from '@/axios/api'
import { Toast } from 'mint-ui'

export default {
  components: {},
  props: {},
  data () {
    return {
      type: '0', // 0 支付宝扫码 1 对公转账
      selectNumber: 500,
      popupVisible: false, // 二维码倒计时
      minutes: 5,
      seconds: 0,
      time: {
        minutes: 5,
        seconds: '00'
      },
      stopTime: false, // 倒计时结束提示
      timer: null, // 定时器
      info: {
        account: '收款账号',
        payName: '收款名称',
        payBank: '收款银行',
        payBankAddress: '收款支行',
        zhifubaoAccount: 'jintongxue2018@163.com',
        zhifubaoName: '中山市金有米信息咨询有限公司'
      },
      popupVisible2: false, // 限额说明
      payInfo: {
        channelImg: ''
      } // 支付渠道的相关信息
    }
  },
  computed: {},
  created () {},
  mounted () {
    this.type = this.$route.query.type
    this.selectNumber = this.$route.query.selectNumber
    this.getPayInfo()
  },
  beforeDestroy () {
    window.clearInterval(this.timer)
  },
  watch: {},
  methods: {
    async getPayInfo () {
      // 获取支付渠道 详细信息
      let data = await api.getPayInfoDetail({ payId: this.$route.query.id })
      if (data.status === 0) {
        this.payInfo = data.data
      } else {
        Toast(data.msg)
      }
    },
    onCopy: function (e) {
      Toast('复制成功！')
    },
    onError: function (e) {
      Toast('复制失败，请重试！')
    },
    toSure () {
      // 充值
      if (this.selectNumber > this.payInfo.channelMaxLimit || this.selectNumber < this.payInfo.channelMinLimit) {
        Toast('一次最高充值' + this.payInfo.channelMaxLimit + ',最低充值' + this.payInfo.channelMinLimit)
      } else {
        this.popupVisible = true
        this.minutes = 5
        this.seconds = 0
        this.time.minutes = 5
        this.time.seconds = '00'
        this.stopTime = false
        this.timerInterval()
        // this.recharge()
      }
    },
    async recharge () {
      let opts = {
        amt: this.selectNumber,
        payType: this.type
      }
      let data = await api.inMoney(opts)
      if (data.status === 0) {
        // 成功
        Toast(data.msg ? data.msg : '充值成功!')
      } else {
        Toast(data.msg ? data.msg : '充值失败,请重新充值')
      }
    },
    closePopup () {
      // 关闭弹窗
      this.popupVisible = false
      this.$router.push('/recharge')
      window.clearInterval(this.timer)
    },
    goBack () {
      this.$router.back(-1)
    },
    num (n) {
      return n < 10 ? '0' + n : '' + n
    },
    timerInterval () {
      var _this = this
      _this.timer = window.setInterval(function () {
        if (_this.seconds === 0 && _this.minutes !== 0) {
          _this.seconds = 59
          _this.minutes -= 1
        } else if (_this.minutes === 0 && _this.seconds === 0) {
          // 倒计时结束
          _this.seconds = 0
          _this.stopTime = true
          this.$router.push('/recharge')
          window.clearInterval(_this.timer)
        } else {
          _this.seconds -= 1
        }
        _this.time.minutes = _this.num(_this.minutes)
        _this.time.seconds = _this.num(_this.seconds)
      }, 1000)
    }
  }
}
</script>
<style lang="less" scoped>
  .pay-img {
    padding: 0 0.2rem;
    padding-top: 0.417rem;

    img {
      width: 100%;
    }
  }

  .pay-radio {
    /* padding: 0.2rem 0.1rem; */

    margin-bottom: 0.2rem;
    height: 0.8rem;
    line-height: 0.75rem;

    .pay-icon {
      .iconfont {
        margin-right: 0.2rem;
      }
    }

    .pay-list {
      border: 0.02rem solid #4e4d4d;
      border-radius: 0.2rem;
    }

    .pay-weixin {
      border-color: #36ae55;
    }

    .pay-zhifubao {
      // border-color:#1296db;
    }

    .icon-on {
      color: #b60c0d;
    }

  }

  .btn-default {
    border: 0.02rem solid #4e4d4d;
    border-radius: 0.2rem;
    display: inline-block;
    height: 0.8rem;
    width: 100%;
    text-indent: 0.2rem;
    background: none;
    color: #ddd;
  }

  .tips-group {
    padding: 0.2rem 0.417rem;

    .iconfont {
      margin-right: 0.2rem;
    }

    p {
      line-height: 2;
      font-size: 0.25rem;
    }

    .tip-text {
      text-indent: 0.28rem;
    }
  }

  .radio-group li {
    width: 19%;
    margin-right: 1%;
  }

  .mint-popup-white {
    color: #333;
    width: 80%;
    padding: 0.2rem 0.3rem 0;
    // bottom: 30%;
    border-radius: 0.1rem;
    box-shadow: 0.014rem 0.014rem 0.014rem rgba(103, 107, 111, 0.38);

    .popup-silide-bottom-leave-active {
      // bottom: -10%;
    }

    .box-block {
      .qrCode {
        border: 1px solid #f3f3f3;
        padding: 0.1rem;
        height: 3rem;
        width: 3rem;
        margin: 0.3rem auto;
        position: relative;

        img {
          width: 100%;
          height: 100%;
        }

        .alert-box {
          width: 100%;
          height: 100%;
          background: rgba(255, 255, 255, 0.9);
          position: absolute;
          left: 0;
          top: 0;
          color: #333;
          text-align: center;

          .iconfont {
            color: #f98700;
            font-size: 0.6rem;
            display: block;
            margin-top: 0.8rem;
            margin-bottom: 0.4rem;
          }
        }
      }

      .prompt-box {
        padding: 0.2rem;
        margin: 0.2rem 0;
        color: #666;
      }

      .money {
        font-weight: bold;
        font-size: 0.5rem;

        .number {
          margin-left: 0.1rem;
        }
      }

      .timer-box {
        text-align: center;
        font-size: 0.5rem;
      }

      .number {
        font-size: 0.6rem;
      }
    }

    .scan {
      border-top: 0.02rem dashed #ddd;
      margin-top: 0.5rem;
      padding: 0.3rem;
      text-align: center;
      color: #ff1100;
    }

    // 微信支付宝icon设置
    .icon-umidd17 {
      color: #1296db;
      font-size: 0.6rem;
    }

    .icon-02 {
      color: #36ae55;
      font-size: 0.6rem;
    }
  }

  .mint-popup-white1 {
    background: #eaeaea;
    padding-bottom: 0.3rem;
  }

  .box1 {
    // margin-top: 0.4rem;
    .icon-fuzhi {
      font-size: 0.24rem;
      margin-right: 0.1rem;
    }
  }

  .eq-code-img {
    text-align: center;
    margin-top: 0.2rem;

    img {
      width: 60%
    }
  }
</style>
