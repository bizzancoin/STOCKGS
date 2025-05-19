<template>
  <div class="buy_page">
    <div class="content">
      <div class="top_cny">
        <div class="top_back">
          <div class="left_back" @click="handleBack()">
            <img src="../../assets/img/zuojiantou.png" alt />
          </div>
          <div class="right_title">
            <div class="t_t" style="white-space: nowrap;">
              <span>{{ name | getName }}</span>
            </div>
            <div class="b_t">
              <span>{{ code }}</span>
            </div>
          </div>
        </div>
        <div class="bottom_buy_price">
          <div class="cot">
            <div class="lefts">
              <div class="top_new">
                <span>{{ nowPrice }}</span>
              </div>
              <div class="bottom_es">
                <span>{{ $t('hj98') }}</span>
              </div>
            </div>
            <div class="rights">
              <div class="ese">
                <div class="mc" @click="handleTradingClick(0)" :class="tabsCurrentIndex === 0 ? 'actives' : ''">
                  <span>{{ $t('hj84') }}</span>
                </div>

                <div class="mr" @click="handleTradingClick(1)" :class="tabsCurrentIndex === 1 ? 'active' : ''">
                  <span>{{ $t('hj85') }}</span>
                </div>
              </div>
            </div>
          </div>
        </div>

      </div>

      <div class="price_tabs">
        <div class="tabs">
          <div class="tab_item" v-for="(item, index) in priceTabs" :key="index" @click="handleTabsClick(item, index)"
            :class="priceTabsCurrentIndex === index ? 'active' : ''">
            <span>{{ item }}</span>
          </div>
        </div>
        <div class="tabs_content">
          <div class="num" v-show="priceTabsCurrentIndex == 1">
            <span>{{ $t('hj99') }}</span>
          </div>
          <div class="tr_es flexJy" v-show="priceTabsCurrentIndex == 1">
            <div class="top_input">
              <input type="Number" v-model="nums">
            </div>
            <div class="right_sw flexJy" style="width:auto;">
              <div class="addorj" @click="gdJian">


                <img src="@/assets/img/ic_number_jian.png" alt />
              </div>
              <div class="addorj"></div>
              <div class="addorj"
                @click="typeof nums == 'string' ? nums = Number(Number(nums) + 1).toFixed(2) : nums = (Number(nums) + 1).toFixed(2)">
                <img src="@/assets/img/ic_number_add.png" />
              </div>
            </div>
          </div>
          <div class="num">
            <span>{{ $t('hj100') }}</span>
          </div>
          <div class="tr_es flexJy">
            <div class="top_input">
              <input type="number" onkeyup="value=value.replace(/[^\d]/g,'')"  v-model="num">
            </div>
            <div class="right_sw flexJy" style="width:auto;">
              <div class="addorj" @click="jyslJian">
                <img src="@/assets/img/ic_number_jian.png" />
              </div>
              <div class="addorj"></div>
              <div class="addorj"
                @click="typeof num == 'string' ? num = Number(Number(num) + 1) : num = Number(Number(num) + 1)">
                <img src="@/assets/img/ic_number_add.png" />
              </div>
            </div>
          </div>
          <div class="tr_rs gg" @click="showGg = true">
            <div class="top_bzz">
              <span>{{ $t('hj101') }}</span>
              <span>{{ selectCycle + 'X' }}</span>
            </div>
            <!-- <div class="bottom_bzz">
                <span>{{  }}</span>
                <span>{{  }}</span>
              </div> -->
          </div>
          <div class="tr_rs">
            <div class="top_bzz" style="padding-bottom: 0.2rem;">
              <span style="text-align: left;">{{ if_us == 1 ? $t('hj102') + '($)' : if_us == '2' ? $t('hj102') + '(HK$)' : $t('hj102') + '(¥)'
              }}</span>
              <span style="text-align: right;">{{ if_us == 1 ? $t('hj103') + '($)' : if_us == '2' ? $t('hj103') + '(HK$)' : $t('hj103') + '(¥)'
              }}</span>
            </div>
            <div class="bottom_bzz">
              <!-- <span>{{ (nowPrice/ selectCycle).toFixed(2) }}</span> -->
              <span>{{ (nowPrice / selectCycle * num * 100).toFixed(2) }}</span>
              <span v-if="$store.state.userInfo.userAmt == undefined">¥0.00</span>
              <span v-if="$store.state.userInfo.userIndexAmt != undefined && type != '0'" style="white-space: nowarp;">
                {{ if_us == 1 ? '$' + (Number($store.state.userInfo.userIndexAmt) / 7.1).toFixed(2) : if_us == '2' ?
                    'HK$' +
                    Number($store.state.userInfo.userIndexAmt / 0.9).toFixed(2) : '¥' + $store.state.userInfo.userIndexAmt
                }}
              </span>
              <span v-if="$store.state.userInfo.userAmt != undefined && type == '0'" style="white-space: nowarp;">
                {{ if_us == 1 ? '$' + (Number($store.state.userInfo.userAmt) / 7.1).toFixed(2) : if_us == '2' ? 'HK$' +
                    Number($store.state.userInfo.userAmt / 0.9).toFixed(2) : '¥ ' + $store.state.userInfo.userAmt
                }}
              </span>
            </div>
          </div>
        </div>
      </div>
      <div class="switchs">
        <div class="zy">
          <div class="left_zy">
            <span>{{ $t('hj104') }}</span>
          </div>
          <div class="right_sw">
            <van-switch v-model="checkedZy" />
          </div>
        </div>
        <div class="zy" v-show="checkedZy">
          <div class="left_zy">
            <input v-model="profitTarget" onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,2})?).*$/g, '$1')"
              @input="zyInt" />
          </div>
          <div class="right_sw" style="width:auto;">
            <div class="addorj" @click="zYjian">
              <img src="@/assets/img/ic_number_jian.png" />
            </div>
            <div class="addorj"></div>
            <div class="addorj" @click="profitTarget = (Number(profitTarget) + 1).toFixed(2)">
              <img src="@/assets/img/ic_number_add.png" />
            </div>
          </div>
        </div>
        <div class="zy">
          <div class="left_zy">
            <span>{{ $t('hj105') }}</span>
          </div>
          <div class="right_sw">
            <van-switch v-model="checkedZs" />
          </div>
        </div>
        <div class="zy" v-show="checkedZs">
          <div class="left_zy">
            <input v-model="zhisun" onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,2})?).*$/g, '$1')" @input="zsInt" />
          </div>
          <div class="right_sw" style="width:auto;">
            <div class="addorj" @click="zhisun--">
              <img src="@/assets/img/ic_number_jian.png" />
            </div>
            <div class="addorj"></div>
            <div class="addorj" @click="zSjia">
              <img src="@/assets/img/ic_number_add.png" />
            </div>
          </div>
        </div>
        <!-- <div class="zy">
            <div class="left_zy">
              <span>追踪止损</span>
            </div>
            <div class="right_sw">
              <van-switch v-model="profitArr[2].checked" />
            </div>
          </div> -->
      </div>
      <div class="btn_buy" @click="gdOrSetBuy()">
        <div :class="tabsCurrentIndex == 0 ? 'maichu' : ''">
          <span>{{ tabsCurrentIndex == 0 ? $t('hj84') : $t('hj85') }}</span>
        </div>
      </div>
    </div>
    <van-action-sheet v-model="showGg" :actions="siteLeverList" :cancel-text="$t('hj106')" :description="$t('hj107')"
      close-on-click-action @select="onSelect" />
  </div>
</template>

<script>
import * as api from "@/axios/api";
import {
  Toast
} from "mint-ui";
export default {
  name: 'trBuy',
  created() {
    if (this.$route.query.t) {
      this.tabsCurrentIndex = Number(this.$route.query.t)
      console.log(this.tabsCurrentIndex)
    }
    if (this.$route.query.m) {
      this.nowPrice = Number(this.$route.query.m)
      this.profitTarget = Number(this.$route.query.m)
      this.zhisun = Number(this.$route.query.m)
      this.nums = Number(this.$route.query.m)
    }
    if (this.$route.query.id) {
      this.id = this.$route.query.id
    }
    if (this.$route.query.code) {
      this.code = this.$route.query.code
    }
    if (this.$route.query.name) {
      this.name = this.$route.query.name
    }
    if (this.$route.query.type) {
      this.type = this.$route.query.type
    }
    if (this.$route.query.if_us) {
      this.if_us = this.$route.query.if_us
    }
  },
  data() {
    return {
      tradingArr: [this.$t('hj84'), this.$t('hj85')],
      tabsCurrentIndex: 0,
      priceTabs: [this.$t('hj108'), this.$t('hj109')],
      priceTabsCurrentIndex: 0,
      num: 1,
      nums: 1,
      profit: false,
      nowPrice: 0,
      type: 0,
      code: '',
      name: '',
      settingInfo: [],
      selectCycle: 20,
      siteLeverList: [],
      checkedZy: false,
      checkedZs: false,
      buying: false,
      zhisun: 0,
      showGg: false,
      profitTarget: 0,
      if_us: 0,
      id:'',
      actions: [
        { name: '100X', subname: this.$t('hj102') },
        { name: '200X', subname: this.$t('hj102') },
        { name: '300X', subname: this.$t('hj102') },
      ],
      profitArr: [
        {
          name: this.$t('hj104'),
          checked: false
        },
        {
          name: this.$t('hj105'),
          checked: false
        },
        {
          name: this.$t('hj110'),
          checked: false
        }
      ]
    }
  },
  mounted() {
    this.getUserInfo();
    this.getSettingInfo();
  },
  watch: {
    checkedZy(val) {
      if (navigator.vibrate) {
        // 支持
        navigator.vibrate([55]);
      }
    },
    checkedZs(val) {
      if (navigator.vibrate) {
        // 支持
        navigator.vibrate([55]);
      }
    }
  },
  methods: {
    handleBack() {
      this.$router.go(-1);
    },
    handleTradingClick(index) {
      this.tabsCurrentIndex = index;
    },
    handleTabsClick(item, index) {
      this.priceTabsCurrentIndex = index;
    },
    jyslJian() {
      if (typeof this.num == 'string') {
        this.num = 1
      } else {
        if (this.num > 1) {
          if (typeof this.num == 'string') {
            this.num = Number((this.num) - 1)
          } else {
            this.num = Number((this.num) - 1)
          }
        } else {
          this.num = 1
        }
      }
    },
    gdJian() {

      if (this.nums > 1) {
        if (typeof this.nums == 'string') {
          this.nums = Number(Number(this.nums) - 1).toFixed(2)
        } else {
          this.nums = Number(Number(this.nums) - 1).toFixed(2)
        }
      } else {
        this.nums = this.nowPrice
      }

    },
    zYjian() {
      if ((Number(this.profitTarget) - 1).toFixed(2) < this.nowPrice) {
        this.profitTarget = this.nowPrice
      } else {
        this.profitTarget = (Number(this.profitTarget) - 1).toFixed(2)
      }

    },
    zSjia() {
      if ((Number(this.zhisun) + 1).toFixed(2) > this.nowPrice) {
        this.zhisun = this.nowPrice
      } else {
        this.zhisun++
      }
    },
    zsInt(e) {
      if (typeof e.target.value == 'string') {
        this.zhisun = this.nowPrice
      } else {
        if (Number(e.target.value) > this.nowPrice) {
          this.zhisun = this.nowPrice
        }
      }

    },
    zyInt(e) {
      if (typeof e.target.value == 'string') {
        this.profitTarget = this.nowPrice
      } else {
        if (Number(e.target.value) < Number(this.nowPrice)) {
          this.profitTarget = Number(this.nowPrice)
        }
      }
    },
    onSelect(val) {
      this.selectCycle = val.gg

    },
    gdOrSetBuy() {
      if (this.priceTabsCurrentIndex == 0) {
        this.setBuy()
      } else {
        this.gdBuy()
      }
      if (navigator.vibrate) {
        // 支持
        navigator.vibrate([55]);
      }
    },
    //挂单
    async gdBuy() {
      if (this.buying) {
        return;
      }
      this.buying = true
      let opts = {
        stockId: this.code,
        buyNum: this.num * 100,
        lever: this.selectCycle ? this.selectCycle : 0,
        targetPrice: this.nums,
      }
      if (this.tabsCurrentIndex == 0) {
        opts.buyType = 1
      } else {
        opts.buyType = 0
      }
      if (this.checkedZy) {
        //opts.profitTarget = this.profitTarget.substring(0,this.profitTarget.indexOf(".")+3)
        opts.profitTarget = this.profitTarget
      }
      if (this.checkedZs) {
        opts.stopTarget = this.zhisun
      }
      let data = await api.guadan(opts)
      this.buying = false
      if (data.status === 0) {
        this.$store.commit('elAlertShow', { 'elAlertShow': true, 'elAlertText': data.msg, 'elAlertType': 'success' });
        this.getUserInfo()
        this.$router.push('/warehouse?index=1')
      } else {
        this.$store.commit('elAlertShow', { 'elAlertShow': true, 'elAlertText': data.msg });
      }
    },
    //买卖
    setBuy() {
      if (!this.$store.state.userInfo.idCard) {
        this.$store.commit('elAlertShow', { 'elAlertShow': true, 'elAlertText': this.$t('hj111') });
        this.$router.push('/authentications')
        return
      }
      // if (!this.agree) {
      //   Toast('需同意合作协议才能交易!')
      // } else if (isNull(this.selectNumber) && isNull(this.autoNumber)) {
      //   Toast('请选择购买手数')
      // } else if (isNull(this.selectType)) {
      //   Toast('请选择买卖方向')
      // } else if(isNull(this.subaccountNumber)) {
      //   Toast('请选择子账户')
      // } else {}
      if (this.buying) {
        return;
      }
      this.buying = true
      let opts = {}
      opts = {
        // stockId: this.detail.id,
        // buyNum: this.selectNumber ? this.selectNumber * 100 : 0, // 单位为手
        // buyType: this.selectType,
        // lever: this.selectCycle ? this.selectCycle : 0,
        // subaccountNumber:this.subaccountNumber

        //买入是买涨buyType:0, 卖出是买跌buyType:1,卖出的状态是0，买入的状态是1
        buyNum: this.num * 100, // 单位为手
        //buyNum: (this.num.match(/\d+/g))[0] * 100, // 单位为手
        lever: this.selectCycle ? this.selectCycle : 0,
      }
      if (this.tabsCurrentIndex == 0) {
        opts.buyType = 1
      } else {
        opts.buyType = 0
      }
      if (this.checkedZy) {
        //opts.profitTarget = this.profitTarget.substring(0,this.profitTarget.indexOf(".")+3)
        opts.profitTarget = this.profitTarget
      }
      if (this.checkedZs) {
        opts.stopLoss = this.zhisun
      }
      if (this.type == 0) {
        //股票
        this.gpBuy(opts)
        //   opts.stockId= this.code,
        //  data = await api.buy(opts)  
      } else {
        //指数
        this.zsBuy(opts)
        //   opts.indexId= this.code,
        //  data = await api.indexBuy(opts)
      }


    },
    //股票买入
    async gpBuy(opts) {
      opts.stockId = this.id
      let data = await api.buy(opts)
      this.buying = false
      if (data.status === 0) {
        this.$store.commit('elAlertShow', { 'elAlertShow': true, 'elAlertText': data.msg, 'elAlertType': 'success' });
        this.getUserInfo()
        this.$router.push('/warehouse?index=0')
      } else {
        if (data.msg.indexOf('不在交易时段内') > -1) {
          this.$store.commit('elAlertShow', { 'elAlertShow': true, 'elAlertText': this.$t('hj113') });
        } else {
          this.$store.commit('elAlertShow', { 'elAlertShow': true, 'elAlertText': data.msg });
        }

      }
    },
    //指数买入
    async zsBuy(opts) {
      opts.indexId = this.id
      let data = await api.indexBuy(opts)
      this.buying = false
      if (data.status === 0) {
        this.$store.commit('elAlertShow', { 'elAlertShow': true, 'elAlertText': data.msg, 'elAlertType': 'success' });
        this.getUserInfo()
        this.$router.push('/warehouse?index=0')
      } else {
        this.$store.commit('elAlertShow', { 'elAlertShow': true, 'elAlertText': data.msg });
      }
    },
    async getUserInfo() {
      // 获取用户信息
      //   let showcookie = this.getCookie('USER_TOKEN');
      let data = await api.getUserInfo();
      if (data.status === 0) {
        // this.getProductSetting()
        this.$store.state.userInfo = data.data;
      } else {
        this.$store.commit('elAlertShow', { 'elAlertShow': true, 'elAlertText': data.msg });
      }
      this.$store.state.user = this.user;
    },
    async getSettingInfo() {
      // 网站设置信息
      let data = await api.getSetting()
      if (data.status === 0) {
        // 成功
        this.settingInfo = data.data
        // 杠杆倍数
        //data.data.siteLever根据/分割成数组
        //this.siteLeverList = data.data.siteLever.split('/')


        this.selectCycle = data.data.siteLever
        if (this.$store.state.userInfo !== undefined && this.$store.state.userInfo !== null && this.$store.state.userInfo.phone !== '' && this.$store.state.userInfo.siteLever != null) {
          this.selectCycle = this.$store.state.userInfo.siteLever.split('/')[0]
          this.siteLeverList = []
          for (let i = 0; i < this.$store.state.userInfo.siteLever.split('/').length; i++) {
            let val = this.$store.state.userInfo.siteLever.split('/')[i]
            let item = { label: val + this.$t('hj112'), value: val }
            this.siteLeverList.push(item)
          }
        } else {
          this.selectCycle = data.data.siteLever.split('/')[0]
          this.siteLeverList = []
          for (let i = 0; i < data.data.siteLever.split('/').length; i++) {

            let val = data.data.siteLever.split('/')[i]
            var ccet = (Number(this.nowPrice) / Number(val)).toFixed(2)
            //let item = { label: val + '倍', value: val ,bzj:ccet}
            let item = { name: val + 'X', subname: this.$t('hj102') + '：' + (ccet * 100).toFixed(2), gg: val, bzj: ccet }
            this.siteLeverList.push(item)
          }
        }
      } else {
        this.$store.commit('elAlertShow', { 'elAlertShow': true, 'elAlertText': data.msg });
      }
    },
  },
  filters: {
    getName(name) {
      if (name.length > 15) {
        return name.substring(0, 14);
      } else {
        return name;
      }
    }
  }
};
</script>

<style scoped lang="less">
.buy_page {
  width: 100%;
  min-height: 100vh;
  background-color: #fff;

  >.content {
    width: 100%;
    height: calc(100% - 1.6rem);
    position: relative;
    background-color: rgb(241, 242, 246);
  }
}

.top_cny {
  width: 100%;
  height: 3.5385rem;

  .top_back {
    width: 100%;
    height: 1.2rem;
    display: flex;
    align-items: center;
    padding: 0 .1rem;

    .left_back {
      width: 0.8rem;
      height: 80%;
      display: flex;
      align-items: center;

      >img {
        margin-top: 0.2rem;
        width: 0.6rem;
        height: 0.6rem;
      }
    }

    .right_title {
      width: 3rem;
      height: 80%;

      .t_t {
        width: 100%;
        height: 70%;
        display: flex;
        align-items: center;
        font-size: 0.4615rem;

        span {
          font-weight: 600;
        }
      }

      .b_t {
        width: 100%;
        height: 30%;
        font-size: 0.3615rem;
        display: flex;
        align-items: flex-start;
        color: #8c8c8c;
      }
    }
  }

  .bottom_buy_price {
    width: 100%;
    height: 2rem;
    display: flex;
    align-items: center;
    padding: 0 .8rem;
    margin-top: .3rem;

    .cot {
      width: 100%;
      height: 1.2rem;
      display: flex;

      .lefts {
        width: 40%;
        height: 100%;

        .top_new {
          width: 100%;
          height: 60%;
          font-size: .5615rem;
          color: rgb(232, 55, 70);

          span {
            font-weight: 600;
          }
        }

        .bottom_es {
          width: 100%;
          height: 40%;
          color: rgb(173, 173, 173);
        }
      }

      .rights {
        width: 60%;
        height: 100%;
        display: flex;
        align-items: center;
        justify-content: flex-end;

        >div {
          width: 65%;
          height: 80%;
          display: flex;
          justify-content: center;

          >div {
            width: 45%;
            height: 70%;
            background: rgb(236, 236, 236);
            display: flex;
            justify-content: center;
            align-items: center;
            border-radius: .1rem;
            color: rgb(173, 173, 173);
          }

          .active {
            background: #fff;
            color: rgb(110, 169, 118);

            span {
              font-weight: 600;
            }
          }

          .actives {
            background: #fff;
            color: rgb(212, 71, 78);

            span {
              font-weight: 600;
            }
          }
        }
      }
    }
  }
}

.price_tabs {
  width: 100%;
  height: auto;
  padding: .2rem .8rem;
  background: #fff;
  margin-top: .1rem;
  border-radius: 0.3rem;

  .tabs {
    width: 100%;
    height: .8205rem;
    background: rgb(247, 247, 247);
    display: flex;
    align-items: center;
    justify-content: center;
    border-radius: .15rem;

    >div {
      width: 49%;
      height: 80%;
      border-radius: .15rem;
      display: flex;
      justify-content: center;
      align-items: center;
    }

    .active {
      background: #fff;

      span {
        font-weight: 600;
      }
    }
  }

  .tabs_content {
    width: 100%;
    margin-top: .3rem;

    .num {
      width: 100%;
      height: .5rem;
      line-height: 0.5rem;
      position: relative;
      top: 0.2rem;
    }

    .tr_es {
      width: 100%;
      height: 1.3rem;
      margin-top: .15rem;
      border-bottom: 0.05rem solid rgb(236, 236, 236);

      .top_input {
        width: 70%;
        height: 70%;
        font-size: .6615rem;

        input {
          font-weight: 600;
        }
      }
    }

    .tr_rs {
      width: 100%;
      height: 1.3rem;
      margin-top: .4rem;

      >div {
        width: 100%;
        height: 50%;
        color: rgb(160, 160, 160);
        display: flex;
        justify-content: space-between;

        span {
          font-weight: 600;
        }
      }

      .bottom_bzz {
        color: #000;
        font-size: .3846rem;

        span {
          font-weight: 600;
        }
      }

      .top_bzz {
        align-items: center;
      }
    }

    .gg {
      margin-top: 0 !important;
      height: 1.8rem;
      display: flex;
      align-items: center;
      font-size: .3803rem;
      border-bottom: 0.05rem solid rgb(236, 236, 236);

      >div {
        color: #000;
      }
    }
  }
}

.switchs {
  width: 100%;
  height: auto;
  padding: 0 .8rem;
  background: #fff;
  margin-top: .3rem;
  border-radius: 0.3rem 0.3rem 0 0;
  transition: all 0.5s;

  .zy {
    transition: all 0.5s;
    width: 100%;
    height: 1.8rem;
    display: flex;
    justify-content: space-between;
    align-items: center;
    border-bottom: 0.05rem solid rgb(236, 236, 236);

    >div {
      // width: 20%;
      height: 50%;
      display: flex;
      align-items: center;
      font-size: .3346rem;
    }

    .right_sw {
      justify-content: flex-end;
    }
  }
}

.btn_buy {
  width: 100%;
  height: auto;
  background: #fff;
  padding: .8rem .8rem;
  display: flex;
  align-items: flex-end;
  // padding-top: 0.8rem;

  >div {
    width: 100%;
    height: 1.2821rem;
    display: flex;
    justify-content: center;
    align-items: center;
    background: rgb(68, 155, 84);
    color: #fff;
    font-size: .3846rem;
    border-radius: .15rem;
  }
}

/deep/.van-action-sheet__name {
  font-size: .3846rem;
  color: #000;
}

/deep/.van-action-sheet__gap {
  height: 0.25rem;

}

/deep/.van-action-sheet__cancel {
  font-size: .4246rem;
  height: 1.3rem;
  color: #000;
}

/deep/.van-action-sheet__description {
  font-size: 0.3846rem;
  height: 1.45rem;
  line-height: 0.7rem;
}

.left_zy input {
  font-size: 0.48rem;
  font-weight: 500;
}

.addorj {
  width: 0.7rem;
  height: 0.7rem;
  color: #000;
  font-weight: 500;
  cursor: pointer;
}

.addorj img {
  width: 100%;
  height: 100%;
  cursor: pointer;
}

.flexJy {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.top_input input {
  width: 100%;
}

.maichu {
  background-color: rgb(225, 59, 69) !important;
}

.mr {
  position: relative;

  &::after {
    content: " ";
    position: absolute;
    display: block;
    width: 100%;
    height: 100%;
    top: 0;
    left: -.1rem;
    z-index: 10000;
    background: rgba(255, 255, 255, 0);
    border-left: 0.12rem solid rgb(241, 242, 246);
    transform-origin: bottom right;
    -ms-transform: skew(-10deg, 0deg);
    -webkit-transform: skew(-10deg, 0deg);
    transform: skew(-10deg, 0deg);
  }
}

.mc {
  position: relative;

  &::after {
    content: " ";
    position: absolute;
    display: block;
    width: 100%;
    height: 100%;
    top: 0;
    left: 0;
    z-index: 10000;
    background: rgba(255, 255, 255, 0);
    border-right: 0.1rem solid rgb(241, 242, 246);
    transform-origin: bottom left;
    -ms-transform: skew(-10deg, 0deg);
    -webkit-transform: skew(-10deg, 0deg);
    transform: skew(-10deg, 0deg);

  }
}
/deep/.van-overlay{
  z-index: 10000!important;
}

/deep/.van-popup{
  z-index: 10001!important;
}
</style>