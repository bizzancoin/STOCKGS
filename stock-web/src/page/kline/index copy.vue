<template>
  <div class="kline_detail_page">
    <div class="content">
      <div class="detail_title">
        <div class="ti_cont">
          <div class="top_back">
            <div class="left_back" @click="handleBack()">
              <img src="../../assets/img/zuojiantou.png" alt />
            </div>
            <div class="right_title">
              <div class="t_t" style="white-space: nowrap;">
                <span>{{ kLineDetails.name | getName }}</span>
              </div>
              <div class="b_t">
                <span>{{ singDetails.code }}</span>
              </div>
            </div>
          </div>
          <div class="right_money sss">
            <div class="content_money">
              <div class="top_price">
                <div class="left">
                  <span>{{ $t('hj47') }}</span>
                </div>
                <div class="right">
                  <span v-if="$store.state.userInfo.userAmt == undefined">¥0.00</span>
                  <span v-if="$store.state.userInfo && kLineDetails.if_zhishu != '0'" style="white-space: nowarp;">
                    {{ kLineDetails.if_us == 1 ? '$' + Number(Number($store.state.userInfo.userIndexAmt) /
                        7.1).toFixed(2) :
                        kLineDetails.if_us == 2 ? 'HK$' + (Number($store.state.userInfo.userIndexAmt) / 0.9).toFixed(2) :
                          '¥' +
                          $store.state.userInfo.userIndexAmt
                    }}
                  </span>
                  <span v-if="$store.state.userInfo.userAmt != undefined && kLineDetails.if_zhishu == '0'"
                    style="white-space: nowarp;">
                    {{ kLineDetails.if_us == 1 ? '$' + Number(Number($store.state.userInfo.userAmt) / 7.1).toFixed(2) :
                        kLineDetails.if_us == 2 ? 'HK$' + (Number($store.state.userInfo.userAmt) / 0.9).toFixed(2) : '¥ ' +
                          $store.state.userInfo.userAmt
                    }}
                  </span>
                </div>
                <div class="sanjiao">
                  <!-- <img
                    :class="dialogFlag ? 'xuanz' : ''"
                    style="transition: all 0.5s;"
                    src="../../assets/img/xiala.png"
                    alt
                  /> -->
                </div>
              </div>
              <div class="bottom_balance">
                <div>
                  <span>{{ $t('hj48') }}</span>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="real_time_detail">
          <div class="left_now_price">
            <div class="top_now">
              <span :class="singDetails.nowPrice - singDetails.preclose_px < 0 ? 'price green' : 'price red'">{{
                  singDetails.nowPrice
              }}</span>
            </div>
            <div class="bottom_now">
              <div>
                <span v-if="singDetails.nowPrice == 0">-</span>
                <span v-else>{{ singDetails.nowPrice - singDetails.preclose_px > 0
                    ? '+' : ''
                }}{{ (singDetails.nowPrice - singDetails.preclose_px).toFixed(2) }}</span>
              </div>
              <div class="tew"
                :class="singDetails.nowPrice - singDetails.preclose_px < 0 ? 'number green' : 'number red'">
                <span v-if="singDetails.nowPrice == 0">-</span>
                <span v-else>({{ singDetails.nowPrice - singDetails.preclose_px > 0 ? '+' : '' }}
                  {{ singDetails.hcrate ? singDetails.hcrate : '0' }}%)</span>
              </div>
            </div>
          </div>
          <div class="right_ets">
            <div class="tops">
              <div class="lefts topes">
                <span class="titles">{{ $t('hj72') }}</span>
                <span :class="singDetails.nowPrice - singDetails.preclose_px < 0 ? 'number green' : 'number red'">{{
                    singDetails.open_px
                }}</span>
              </div>
              <div class="rights topes">
                <span class="titles">{{ $t('hj73') }}</span>
                <span :class="singDetails.nowPrice - singDetails.preclose_px < 0 ? 'number green' : 'number red'">{{
                    singDetails.today_max
                }}</span>
              </div>
            </div>
            <div class="bottoms">
              <div class="lefts bots">
                <span class="titles">{{ $t('hj74') }}</span>
                <span :class="singDetails.nowPrice - singDetails.preclose_px < 0 ? 'number green' : 'number red'">{{
                    singDetails.preclose_px
                }}</span>
              </div>
              <div class="rights bots">
                <span class="titles">{{ $t('hj75') }}</span>
                <span :class="singDetails.nowPrice - singDetails.preclose_px < 0 ? 'number green' : 'number red'">{{
                    singDetails.today_min
                }}</span>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="k_line_detail">
        <Kline :type="singDetails.type" />
      </div>
      <div class="priect_top_bottom"
        v-if="(kLineDetails.if_us != 1 && kLineDetails.type != 'hk') && kLineDetails.type != 'hk' && kLineDetails.if_zhishu == 0">
        <div class="t_title">
          <span>{{ $t('hj76') }}</span>
        </div>
        <div class="charts">
          <div class="left_s">
            <div class="t_ma">
              <span>{{ $t('hj77') }}</span>
            </div>
            <div class="ets">
              <div class="left_sell1">
                <span>{{ singDetails.sell1 }}</span>
              </div>
              <div class="right_sell1">
                <span>{{ singDetails.sell1_num }}</span>
              </div>
            </div>
            <div class="ets">
              <div class="left_sell1">
                <span>{{ singDetails.sell2 }}</span>
              </div>
              <div class="right_sell1">
                <span>{{ singDetails.sell2_num }}</span>
              </div>
            </div>
            <div class="ets">
              <div class="left_sell1">
                <span>{{ singDetails.sell3 }}</span>
              </div>
              <div class="right_sell1">
                <span>{{ singDetails.sell3_num }}</span>
              </div>
            </div>
            <div class="ets">
              <div class="left_sell1">
                <span>{{ singDetails.sell4 }}</span>
              </div>
              <div class="right_sell1">
                <span>{{ singDetails.sell4_num }}</span>
              </div>
            </div>
            <div class="ets">
              <div class="left_sell1">
                <span>{{ singDetails.sell5 }}</span>
              </div>
              <div class="right_sell1">
                <span>{{ singDetails.sell5_num }}</span>
              </div>
            </div>
          </div>
          <div class="right_h">
            <div class="t_ma">
              <span>{{ $t('hj78') }}</span>
            </div>
            <div class="ets">
              <div class="left_sell1">
                <span>{{ singDetails.buy1 }}</span>
              </div>
              <div class="right_sell1">
                <span>{{ singDetails.buy1_num }}</span>
              </div>
            </div>
            <div class="ets">
              <div class="left_sell1">
                <span>{{ singDetails.buy2 }}</span>
              </div>
              <div class="right_sell1">
                <span>{{ singDetails.buy2_num }}</span>
              </div>
            </div>
            <div class="ets">
              <div class="left_sell1">
                <span>{{ singDetails.buy3 }}</span>
              </div>
              <div class="right_sell1">
                <span>{{ singDetails.buy3_num }}</span>
              </div>
            </div>
            <div class="ets">
              <div class="left_sell1">
                <span>{{ singDetails.buy4 }}</span>
              </div>
              <div class="right_sell1">
                <span>{{ singDetails.buy4_num }}</span>


              </div>
            </div>
            <div class="ets">
              <div class="left_sell1">
                <span>{{ singDetails.buy5 }}</span>
              </div>
              <div class="right_sell1">
                <span>{{ singDetails.buy5_num }}</span>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="priect_top_bottom" v-if="kLineDetails.if_us == 1">
        <div class="t_title">
          <span>{{ $t('hj79') }}</span>
        </div>
        <div class="charts">
          <div class="left_s">
            <div class="t_ma">
              <span>{{ $t('hj80') }}</span>
            </div>
            <div class="ets" v-for="item in timedata.data.details">
              <div class="left_sell1">
                <span>{{ item.split(',')[0] }}</span>
              </div>
              <div class="right_sell1">
                <span>{{ }}</span>
              </div>
            </div>

          </div>
          <div class="right_h">
            <div class="t_ma">
              <span>{{ $t('hj81') }}</span>
            </div>
            <div class="ets" v-for="item in timedata.data.details">
              <div class="left_sell1">
                <span>{{ item.split(',')[1] }}</span>
              </div>
              <div class="right_sell1">
                <span>{{ }}</span>
              </div>
            </div>

          </div>
          <div class="right_h">
            <div class="t_ma">
              <span>{{ $t('hj82') }}</span>
            </div>
            <div class="ets" v-for="item in timedata.data.details">
              <div class="left_sell1">
                <span>{{ item.split(',')[2] }}</span>
              </div>
              <div class="right_sell1">
                <span>{{ }}</span>
              </div>
            </div>

          </div>
        </div>
      </div>
      <div class="jianjie" v-if="!(kLineDetails.if_zhishu != '0' && singDetails.gid.indexOf('hk') > -1)"
        :class="acseFlag ? 'isjj' : ''" ref="isjj" id="isjj">
        <div class="top_jj">
          <span>{{ $t('hj83') }}</span>
        </div>
        <div class="jet">
          <span>{{ jianjie }}</span>
        </div>
      </div>
      <div class="hknews" v-if="kLineDetails.if_zhishu != '0' && singDetails.gid.indexOf('hk') > -1">

        <div class="news-tab">
          <div class="t_title">
            <span>{{ $t('hj6') }}</span>
          </div>
          <mt-tab-container v-model="news" :swipeable="false" style="padding-top: 0.5rem;">
            <mt-tab-container-item id="tab_2">
              <div class="news-content">
                <div class="item-out" v-for="(item, inde) in newsdetailList" :key="inde">
                  <div class="item-times">{{ item.Art_ShowTime | utc2beijing }}</div>
                  <div class="titContent" style="-webkit-box-orient: vertical;">{{ item.Art_Title }}</div>
                </div>
              </div>
            </mt-tab-container-item>
          </mt-tab-container>
        </div>
      </div>
      <div class="hknews" v-else>
        <div class="news-tab">
          <div class="t_title">
            <span>{{ $t('hj6') }}</span>
          </div>
          <mt-tab-container v-model="news" :swipeable="false" style="padding-top: 0.5rem;">
            <mt-tab-container-item id="tab_2">
              <div class="news-content">
                <div class="item-out" v-for="(item, inde) in newsdetailList" :key="inde">
                  <div class="item-times">{{ item.addTime | gettime }}</div>
                  <div class="titContent" style="-webkit-box-orient: vertical;">{{ item.title }}</div>
                </div>
              </div>
            </mt-tab-container-item>
          </mt-tab-container>
        </div>
      </div>
      <!-- <div class="border_bottom"></div> -->
    </div>
    <div class="btns">
      <div class="lefts">
        <div class="left_ca" @click="option()">
          <img src="../../assets/img/meishoucang.png" alt v-if="isOptionOpt === false" />
          <img src="../../assets/img/shoucangle.png" alt v-else />
        </div>
        <div class="right_xx" @click="handleJj()">
          <a href="#"><img src="../../assets/img/xiaoxi.png" alt /></a>
        </div>
      </div>
      <div class="rights">
        <div class="buy_btn">
          <div class="top_buy" @click="goBuy(0)">
            <span>{{ $t('hj84') }}</span>
          </div>
          <div class="bottom_buy">
            <span>{{ singDetails.nowPrice }}</span>
          </div>
        </div>
        <div class="sell_btn" @click="goBuy(1)">
          <div class="top_sell">
            <span>{{ $t('hj85') }}</span>
          </div>
          <div class="bottom_sell">
            <span>{{ singDetails.nowPrice }}</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Kline from "./components/kLine.vue";
import * as api from "@/axios/api";

export default {
  name: "kline",
  data() {
    return {
      news: 'tab_2',
      kLineDetails: {}, // K线图详情数据
      singDetails: {},
      scFlag: false,
      acseFlag: false,
      jianjie: "",
      optionBtn: false,
      isOptionOpt: false,
      dialogFlag: false,
      timedata: [],
      newsdetailList: []
    };
  },
  components: {
    Kline
  },
  created() {

    const { query } = this.$route;
    this.kLineDetails = query;
    if (query.if_us == '1') {

      this.getSingDetailUs();
    } else {
      this.getSingDetails();
    }
    this.getOpation();
    // this.$Lazyload();
    this.getUserInfo();
  },

  // beforeDestroy() {
  //   this.Klinetype = false;
  //   window.clearInterval()
  // },
  methods: {
    async getHknews() {
      await api.queryIndexNews().then(res => {
        if (res.status == 0) {
          this.newsdetailList = res.data.data;
        }
      });
    },
    async getNohknews() {
      let data = await api.queryNewsList(4);
      this.newsdetailList = data.data.list
    },
    async option() {
      if (this.optionBtn) {
        return
      };
      this.optionBtn = true;
      if (this.isOptionOpt) {
        let data = await api.delOption({ code: this.kLineDetails.code });
        if (data.status === 0) {
          this.getOpation();
          this.optionBtn = false;
          this.$message({
            message: this.$t('hj97'),
            type: 'success'
          });
        } else {
          this.optionBtn = false;
          this.$message({
            message: data.msg,
            type: 'warning'
          });
        }
      } else {
        let data = await api.addOption({ code: this.kLineDetails.code });
        if (data.status === 0) {
          this.getOpation();
          this.optionBtn = false;
          this.$message({
            message: this.$t('hj96'),
            type: 'success'
          });
        } else {
          this.$message({
            message: data.msg,
            type: 'warning'
          });
          this.optionBtn = false;
        }
      }
      if (navigator.vibrate) {
        // 支持
        navigator.vibrate([55]);
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
        Toast(data.msg);
      }
      this.$store.state.user = this.user;
    },
    async getOpation() {
      let opts = {
        code: this.$route.query.code
      }
      let data = await api.isOption(opts)
      if (data.status === 0) {
        // 0 --> 未添加
        this.isOptionOpt = false
      } else {
        this.isOptionOpt = true
      }
    },
    async getSingDetails() {
      let opts = {
        code: this.kLineDetails.code,
        stockType: this.kLineDetails.type
      };
      await api.getSingleStock(opts).then(res => {
        if (res.status === 0) {
          this.singDetails = res.data.stock;

          if (res.data.introduction) {
            this.jianjie = res.data.introduction;
          } else {
            this.jianjie = res.data.indexintroduction;
          }
          console.log(this.singDetails);
          if (this.kLineDetails.if_zhishu != '0' && this.singDetails.gid.indexOf('hk') > -1) {
            this.getHknews();
          } else {
            this.getNohknews();
          }
        }
      });
    },
    async getSingDetailUs() {
      let opts = {
        code: this.kLineDetails.code,
        stockType: this.kLineDetails.type
      };
      await api.getUsDetail(opts).then(res => {
        // console.log(res,1111123);
        // var that = this
        // if(!res){
        //   setTimeout(() => {
        //     that.getSingDetailUs()
        //   }, 3000);

        // }
        if (res.status === 0) {
          this.singDetails = res.data.stock;
          this.timedata = res.data.timedata;
          if (res.data.introduction) {
            this.jianjie = res.data.introduction;
          } else {
            this.jianjie = res.data.indexintroduction;
          }
          this.getNohknews();
          console.log(this.singDetails);
        }
      });
    },
    handleJj() {
      this.acseFlag = true;
      setTimeout(() => {
        this.acseFlag = false;
      }, 1000);
      if (navigator.vibrate) {
        // 支持
        navigator.vibrate([55]);
      }
    },
    handleBack() {
      this.$router.go(-1);
    },
    handleSc() {
      this.scFlag = !this.scFlag;
    },
    goBuy(index) {
      this.$router.push({
        path: "/TradingBuy",
        query: {
          t: index,
          code: this.kLineDetails.code,
          m: this.singDetails.nowPrice,
          type: this.kLineDetails.if_zhishu,
          id: this.singDetails.id,
          name: this.kLineDetails.name,
          if_us: this.kLineDetails.if_us,
        }
      });
      if (navigator.vibrate) {
        // 支持
        navigator.vibrate([55]);
      }
    }
  },
  filters: {
    getName(name) {
      if (name.length > 15) {
        return name.substring(0, 14);
      } else {
        return name;
      }
    },
    gettime(time) {
      if (!time) {
        return "";
      }
      var nd = new Date(time);
      var y = nd.getFullYear();
      var mm = nd.getMonth() + 1;
      var d = nd.getDate();
      var h = nd.getHours();
      var m = nd.getMinutes();
      var c = nd.getSeconds();
      if (mm < 10) {
        mm = "0" + mm;
      }
      if (d < 10) {
        d = "0" + d;
      }
      if (h < 10) {
        h = "0" + h;
      }
      if (m < 10) {
        m = "0" + m;
      }
      if (c < 10) {
        c = "0" + c;
      }
      //17:35:2922-06-2022
      return y + "-" + mm + "-" + d + " " + h + ":" + m + ":" + c;
    },
    utc2beijing(utc_datetime) {
      // 转为正常的时间格式 年-月-日 时:分:秒
      var T_pos = utc_datetime.indexOf('T');
      var Z_pos = utc_datetime.indexOf('Z');
      var year_month_day = utc_datetime.substr(0, T_pos);
      var hour_minute_second = utc_datetime.substr(T_pos + 1, Z_pos - T_pos - 1);
      var new_datetime = year_month_day + " " + hour_minute_second; // 2017-03-31 08:02:06

      // 处理成为时间戳
      timestamp = new Date(Date.parse(new_datetime));
      timestamp = timestamp.getTime();
      timestamp = timestamp / 1000;

      // 增加8个小时，北京时间比utc时间多八个时区
      var timestamp = timestamp + 8 * 60 * 60;

      // 时间戳转为时间
      var beijing_datetime = new Date(parseInt(timestamp) * 1000).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");
      return beijing_datetime; // 2017-03-31 16:02:06
    }
  }
};
</script>

<style scoped lang="less">
.news-tab {
  width: 100%;
  position: relative;
  left: 0;
  right: 0;
  margin: auto;
  margin-top: 0.2rem;
  border-radius: 0.4rem 0.4rem 0 0;
  padding-top: 0.3rem;

  .t_title {
    width: 100%;
    font-size: 0.4546rem;
    margin-top: 0.2rem;
    padding: 0 0.3rem;

    span {
      font-weight: 700;
    }
  }

  /deep/.is-selected .tab-name {
    position: relative;
  }

  /deep/.mint-navbar .mint-tab-item.is-selected {
    border: 0 !important;
    border-bottom: none;
  }

  /deep/.is-selected .tab-name:after {
    position: absolute;
    display: block;
    content: '';
    height: .07rem;
    background-color: #1381A4;
    width: 100%;
    left: 0;
    bottom: -.25rem;
  }
}

.news-content {
  position: relative;
  padding: 0.3rem;
}

.item-out {
  position: relative;
  border-left: 0.01rem solid rgba(192, 192, 192, 0.8);
  padding: 0 0.25rem 1rem 0.25rem;
}

.item-out::before {
  content: "●";
  position: absolute;
  top: -0.1rem;
  left: -0.128rem;
  margin: auto;
}

.item-times {
  color: #999;
  margin-bottom: 0.15rem;
}

.titContent {
  position: relative;
  width: 100%;
  font-size: .35rem;
  line-height: .46rem;
  overflow: hidden;
  text-overflow: ellipsis;
  -webkit-line-clamp: 2;
  display: -webkit-box;
}






.ti_cont {
  width: 100%;
  display: flex;
}

.kline_detail_page {
  width: 100%;
  height: calc(100% - 1.7rem);
  overflow: auto;

  >.content {
    width: 100%;
    height: 100%;
    position: relative;
  }
}

.sss {
  width: 50%;
  height: 1.2rem;
  display: flex;
  justify-content: flex-end;
  align-items: center;
}

.detail_title {
  width: 100%;
  // height: 3.2rem;
  padding: 0 0.3rem;
  background: #fff;

  .top_back {
    width: 50%;
    height: 1.2rem;
    display: flex;
    align-items: center;

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
}

.k_line_detail {
  width: 100%;
  height: 8.3rem;
  background: #fff;
  border-radius: 0 0 0.3rem 0.3rem;
}

.real_time_detail {
  width: 100%;
  // height: 2rem;
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  align-items: center;
  padding: 0.3rem 0;

  .left_now_price {
    // width: 35%;
    height: 100%;

    .top_now {
      width: 100%;
      // height: 65%;
      font-size: 0.741rem;
      display: flex;
      align-items: center;

      span {
        font-weight: 500;
      }
    }

    .bottom_now {
      width: 100%;
      // height: 35%;
      display: flex;
      padding-left: 0.1rem;
      padding-top: 0.1rem;
    }

    .tew {
      margin-left: 0.2rem;
    }
  }

  .right_ets {
    max-width: 60%;
    height: 100%;

    >div {
      width: 100%;
      padding: 0.15rem 0;
      display: flex;
      align-items: center;

      >div {
        // width: 50%;
        height: 100%;
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 0 0.1rem;
        .titles{
          padding-right: 0.1rem;
        }
      }
    }
  }
}

.topes {
  span {
    display: inline-block;
    // padding-top: 0.6rem;
  }
}

.bots {
  span {
    display: inline-block;
    // padding-top: 0.2rem;
  }
}

.titles {
  color: rgb(159, 159, 159);
}

.border_bottom {
  width: 100%;
  height: 0.05rem;
  border-bottom: 0.02rem solid rgb(235, 235, 235);
  position: absolute;
  top: 3rem;
}

.priect_top_bottom {
  width: 100%;
  background: #fff;
  padding: 0.3rem 0.3rem;
  margin-top: 0.2rem;
  border-radius: 0.3rem 0.3rem 0 0;

  .t_title {
    width: 100%;
    height: 15%;
    font-size: 0.4546rem;
    margin-top: 0.2rem;

    span {
      font-weight: 800;
    }
  }

  .charts {
    width: 100%;
    height: 85%;
    display: flex;
    justify-content: space-between;
    margin-top: 0.6rem;

    >div {
      width: 49%;
      height: 100%;
    }

    .t_ma {
      width: 100%;
      height: 0.3rem;
      color: rgb(0, 0, 0);
      display: flex;
      font-size: 0.3046rem;
    }
  }
}

.ets {
  width: 100%;
  height: 0.5rem;
  display: flex;
  margin-top: 0.08rem;

  >div {
    width: 50%;
    height: 100%;
    display: flex;
    align-items: center;
  }

  .right_sell1 {
    justify-content: flex-end;
  }

  span {
    font-weight: 600;
  }
}

.left_s {
  color: #028f52;
}

.right_h {
  color: #d50000;
}

.ob_detail {
  width: 100%;
  height: 10rem;
  background: #fff;
  margin-bottom: 1.2821rem;
  margin-top: 0.3rem;

  .ob_content {
    width: 100%;
    height: 100%;
    padding: 0 0.3rem;

    .ob_title {
      width: 100%;
      height: 1rem;
      display: flex;
      align-items: center;
      font-size: 0.5128rem;
    }

    .details {
      width: 100%;
      height: 1rem;
      display: flex;
      font-size: 0.3846rem;

      .left_details {
        width: 40%;
        height: 100%;
        display: flex;
        align-items: center;
      }

      .right_details {
        width: 60%;
        height: 100%;
        display: flex;
        justify-content: flex-end;
        align-items: center;
      }
    }
  }
}

.btns {
  width: 100%;
  height: 1.7rem;
  position: fixed;
  bottom: 0;
  background: #fff;
  display: flex;
  align-items: center;
  justify-content: center;

  .lefts {
    width: 25%;
    height: 70%;
    display: flex;
    align-items: center;

    >div {
      width: 50%;
      height: 100%;
      display: flex;
      align-items: center;
      justify-content: center;

      img {
        width: 0.6rem;
        height: 0.6rem;
      }
    }
  }

  .rights {
    width: 70%;
    height: 70%;
    display: flex;
    align-items: center;
    justify-content: space-around;

    >div {
      border-radius: 0.15rem;
    }

    .buy_btn {
      width: 45%;
      height: 100%;
      background: rgb(225, 59, 69);
      color: #fff;

      .top_buy {
        width: 100%;
        height: 45%;
        display: flex;
        justify-content: center;
        align-items: flex-end;
      }

      .bottom_buy {
        width: 100%;
        height: 55%;
        display: flex;
        justify-content: center;
        align-items: flex-start;
        font-size: 0.4015rem;
        padding: 0.1rem 0 0 0;
      }
    }

    .sell_btn {
      width: 45%;
      height: 100%;
      background: rgb(68, 155, 84);
      color: #fff;

      .top_sell {
        width: 100%;
        height: 45%;
        display: flex;
        justify-content: center;
        align-items: flex-end;
      }

      .bottom_sell {
        width: 100%;
        height: 55%;
        display: flex;
        justify-content: center;
        align-items: flex-start;
        padding: 0.1rem 0 0 0;
        font-size: 0.4015rem;
      }
    }
  }
}

.jianjie {
  width: 100%;
  // height: 5rem;
  background: rgb(255, 255, 255);
  margin-top: 0.2rem;
  border-radius: 0.3rem;
  margin-bottom: 0.3rem;
  padding: 0.4rem 0.3rem;

  .top_jj {
    width: 100%;
    height: 1.3rem;
    display: flex;
    align-items: center;
    font-size: 0.4546rem;

    span {
      font-weight: 800;
    }
  }

  .jet {
    width: 100%;

    span {
      line-height: 0.5rem;
    }
  }
}

@-webkit-keyframes zy {
  10% {
    transform: rotate(15deg);
  }

  20% {
    transform: rotate(-10deg);
  }

  30% {
    transform: rotate(5deg);
  }

  40% {
    transform: rotate(-5deg);
  }

  50%,
  100% {
    transform: rotate(0deg);
  }
}

.isjj {
  animation: zy 2.5s .15s linear infinite;
  animation: zy 2.5s .15s linear infinite;
  animation: zy 2.5s .15s linear infinite;
  animation: zy 2.5s .15s linear infinite;
  animation: zy 2.5s 0.15s linear infinite;
  animation: zy 2.5s 0.15s linear infinite;
  animation: zy 2.5s 0.15s linear infinite;
  animation: zy 2.5s 0.15s linear infinite;
}

.content_money {
  width: 80%;
  height: 80%;
}

.sanjiao {
  width: 0.4rem;
  height: 0.4rem;
  display: flex;
  align-items: center;
  justify-content: center;

  img {
    width: 0.4rem;
    height: 0.4rem;
  }
}

.top_price {
  width: 100%;
  height: 70%;
  display: flex;
  align-items: center;
  justify-content: flex-end;

  .left {
    // width: 0.8rem;
    height: 0.4103rem;
    border-radius: 0.1rem;
    background: #4d73b1;
    color: #fff;
    font-size: 0.3077rem;
    display: flex;
    align-items: center;
    justify-content: center;

    span {
      display: inline-block;
      transform: scale(0.8);
      font-weight: 600;
    }
  }

  .right {
    width: auto;
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: flex-end;
    white-space: nowrap;
    margin-left: 0.1rem;
    margin-right: 0.1rem;
    min-width: 1.9rem;

    span {
      font-weight: 600;
      text-align: right;
    }
  }
}

.bottom_balance {
  width: 100%;
  height: 30%;
  display: flex;
  justify-content: flex-end;
  font-size: 0.3077rem;
  color: #acaeaf;
  transform: scale(0.9);
  margin-left: 0.2rem;
  padding-right: 0.68rem;
}

</style>