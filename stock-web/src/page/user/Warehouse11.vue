<template>
  <div class="warehouse_page">
    <div class="content">
      <div class="top_title" :class="titleDialog ? 'active' : ''" ref="topTitle">
        <div class="titles">
          <div class="left_title" @click="handleOpenDialog()">
            <div class="title">
              <span>{{ titleName }}</span>
            </div>
            <div class="img">
              <img src="../../assets/img/xiala.png" alt />
            </div>
          </div>
          <div class="right_box"></div>
        </div>
        <div class="overflow_box item" @click="SetTitleIndex(0)">
          <div class="left_titles">
            <span>{{ $t('hj114') }}</span>
          </div>
          <div class="right_price">
            <span>{{ '' }}</span>
          </div>
        </div>
        <div class="overflow_box item" @click="SetTitleIndex(1)">
          <div class="left_titles">
            <span>{{ $t('hj115') }}</span>
          </div>
          <div class="right_price">
            <span>{{ '' }}</span>
          </div>
        </div>
        <div class="overflow_box item" @click="SetTitleIndex(2)">
          <div class="left_titles">
            <span>{{ $t('hj116') }}</span>
          </div>
          <div class="right_price">
            <span>{{ '' }}</span>
          </div>
        </div>
      </div>
      <div class="warehouse_card">
        <div class="top_card">
          <div class="card_content">
            <div class="t_title">
              <span>{{ $t('hj49') }}</span>
            </div>
            <div class="t_price">
              <p 
                class="price">¥{{titleIndex == 0? $store.state.userInfo.userAmt : titleIndex == 1? $store.state.userInfo.userIndexAmt : $store.state.userInfo.userAmt
                }}</p>
              <!-- <span class="price">{{ '49,619.05' }}</span>
              <span class="profits">{{ '-370.00' }}</span> -->
              <!-- <p v-if="this.$store.state.settingForm.indexDisplay && !this.$store.state.settingForm.futuresDisplay"
                class="price">¥{{ $store.state.hide ? '****' : Number($store.state.userInfo.userAmt +
                    $store.state.userInfo.userIndexAmt).toFixed(2)
                }}</p>
              <p v-else-if="!this.$store.state.settingForm.indexDisplay && this.$store.state.settingForm.futuresDisplay"
                class="price">¥{{ $store.state.hide ? '****' : Number($store.state.userInfo.userAmt +
                    $store.state.userInfo.userFuturesAmt).toFixed(2)
                }}</p>
              <p v-else-if="!this.$store.state.settingForm.indexDisplay && !this.$store.state.settingForm.futuresDisplay"
                class="price">¥{{ $store.state.hide ? '****' : Number($store.state.userInfo.userAmt).toFixed(2) }}</p>
              <p v-else-if="this.$store.state.settingForm.indexDisplay && this.$store.state.settingForm.futuresDisplay"
                class="price">¥{{ $store.state.hide ? '****' : Number($store.state.userInfo.userAmt +
                    $store.state.userInfo.userIndexAmt + $store.state.userInfo.userFuturesAmt).toFixed(2)
                }}</p> -->
              <!-- <span class="profits" v-show="titleIndex == 1">{{ '¥ ' + $store.state.userInfo.userIndexAmt }}</span>
              <span class="profits" v-show="titleIndex == 0">{{ '¥ ' + $store.state.userInfo.userAmt }}</span>
              <span class="profits" v-show="titleIndex == 2">{{ '¥ ' + $store.state.userInfo.userFuturesAmt }}</span> -->
            </div>
            <div class="balance">
              <div class="left_titles">
                {{ $t('hj50') }}
              </div>
              <div class="right_titles">
                {{ $t('hj54') }}
              </div>
            </div>
            <div class="num">
              <div class="left_price">
                <span v-show="titleIndex == 1 ">{{ $store.state.hide ? '****' :
                    Number(($store.state.userInfo.enableIndexAmt +
                      $store.state.userInfo.allIndexFreezAmt) * indexSettingInfo.forceSellPercent).toFixed(2)
                }}</span>
                <span v-show="titleIndex == 0 || titleIndex == 2">{{ $store.state.hide ? '****' : Number(($store.state.userInfo.enableAmt
                    +
                    $store.state.userInfo.allFreezAmt) * settingInfo.forceStopPercent).toFixed(2)
                }} </span>

                <!-- <span v-show="titleIndex == 2">{{ $store.state.hide ? '****' :
                    Number(($store.state.userInfo.enableFuturesAmt +
                      $store.state.userInfo.allFuturesFreezAmt) * futuresSettingInfo.forceSellPercent).toFixed(2)
                }}</span> -->
              </div>
              <div class="right_margin">
                <span v-show="titleIndex == 1">{{ '¥ ' + $store.state.userInfo.enableIndexAmt }}</span>
                <span v-show="titleIndex == 0 || titleIndex == 2">{{ '¥ ' + $store.state.userInfo.enableAmt }}</span>

                <!-- <span v-show="titleIndex == 2">{{ '¥ ' + $store.state.userInfo.enableFuturesAmt }}</span> -->
              </div>
            </div>
            <div class="margin">
              <div class="left_titles">
                {{ $t('hj55') }}
              </div>
              <div class="right_titles">
                {{ $t('hj56') }}
              </div>
            </div>
            <div class="prices">
              <div class="left_titles">
                <span class="numDemo" v-show="titleIndex == 1">{{ '¥ ' + $store.state.userInfo.allIndexFreezAmt ?
                    $store.state.userInfo.allIndexFreezAmt : '0.00'
                }}</span>
                <span class="numDemo" v-show="titleIndex == 0">{{ '¥ ' +
                    $store.state.userInfo.allFreezAmt ? $store.state.userInfo.allFreezAmt : '0.00'
                }}</span>
                <span class="numDemo" v-show="titleIndex == 2">{{ '¥ ' +
                    $store.state.userInfo.djzj ? $store.state.userInfo.djzj : '0.00'
                }}</span>
              </div>
              <div class="right_titles">
                <span class="numDemo"
                  :class="$store.state.userInfo.allIndexProfitAndLose > 0 ? ' red' : $store.state.userInfo.allIndexProfitAndLose < 0 ? ' green' : ''"
                  v-show="titleIndex == 1">{{ '¥ ' +
                      $store.state.userInfo.allIndexProfitAndLose ? $store.state.userInfo.allIndexProfitAndLose : '0.00'
                  }}</span>
                <span class="numDemo"
                  :class="$store.state.userInfo.allProfitAndLose > 0 ? ' red' : $store.state.userInfo.allProfitAndLose < 0 ? ' green' : ''"
                  v-show="titleIndex == 0 || titleIndex == 2">{{ '¥ ' +
                      $store.state.userInfo.allProfitAndLose ? $store.state.userInfo.allProfitAndLose : '0.00'
                  }}</span>

                <!-- <span class="numDemo"
                  :class="$store.state.userInfo.allFuturesProfitAndLose > 0 ? 'red' : $store.state.userInfo.allFuturesProfitAndLose < 0 ? ' green' : ''"
                  v-show="titleIndex == 2">{{
                      '¥' + $store.state.userInfo.allFuturesProfitAndLose ?
                        Number($store.state.userInfo.allFuturesProfitAndLose).toFixed(2) : '0.00'
                  }}</span> -->

              </div>
            </div>
          </div>
        </div>
        <div class="tabs_card">
          <div class="tabs_card_content">
            <div class="tabs_top_title">
              <div class="title_items" v-for="(item, index) in tabsArr" :key="index"
                @click="handleTabsClick(item, index)" :class="index == 0 ? 'kuan' : 'kuan'">
                <span :class="tabsCurrentIndex === index ? 'active' : ''">
                  {{ item }}{{ index == 0 ? '(' + total + ')' : index == 1 ? '(' + totalss + ')' : index == 2 ? '(' +
                      totals + ')' : '(' + xgTotal + ')'
                  }}</span>
              </div>
            </div>
            <!-- 持仓 currentIndex: 0 -->
            <div class="over">
              <van-list v-model="loading" :finished="finished" :finished-text="$t('hj43')"
                v-show="tabsCurrentIndex === 0 && total != 0" @load="onLoad" :immediate-check="immediate">
                <div class="tabs_o">
                  <div class="tabs_o_items" v-for="(item, index) in tabsPositionNumArr" :key="item.buyOrderId">
                    <div class="tabs_o_title">
                      <div style="width: calc(100% - 3.5rem);">
                        <span class="title">{{ item.stockName ? item.stockName : item.indexName }}</span>
                        <span class="buy_to_sell" :class="item.orderDirection == '买跌' ? 'maichu' : 'mairu'">
                          {{ item.orderDirection == "买跌" ? $t('hj84') : $t('hj85') }}</span>
                        <span class="multiple">{{ item.allProfitAndLose }}</span>
                        <span class="nums">{{ item.orderNum / 100 + $t('hj117') }}</span>
                      </div>

                      <div class="right_btn pingbtn" @click="getpingcang(item.positionSn)">
                        <span style="margin-right: 0;">{{ $t('hj121') }}</span>
                      </div>
                    </div>
                    <div class="center_price">
                      <div class="profit">
                        <span>{{ item.profitAndLose }}</span>
                      </div>
                      <div class="start_price jiantou">
                        <span>{{ item.buyOrderPrice }}</span>
                      </div>
                      <div class="new_price">
                        <span>{{ item.now_price }}</span>
                      </div>
                    </div>
                    <div class="bottom_price_title">
                      <div class="profit_title">
                        <span>{{ $t('hj118') }}</span>
                      </div>
                      <div class="start_price">
                        <span>{{ $t('hj119') }}</span>
                      </div>
                      <div class="new_price">
                        <span>{{ $t('hj120') }}</span>
                      </div>
                    </div>

                  </div>
                </div>
              </van-list>
              <div class="waiting" v-if="total == 0 && tabsCurrentIndex === 0">
                <div class="waiting_box">
                  <div class="img_cont">
                    <img src="../../assets/img/zhaobudao.png" alt />
                    <span class="gd">{{ $t('hj122') }}</span>
                    <div class="trading" @click="$router.push('/trading-list')">
                      <span>{{ $t('hj123') }}</span>
                    </div>
                  </div>
                </div>
              </div>



              <!-- 挂单 -->
              <div class="waiting" v-if="totalss == 0 && tabsCurrentIndex === 1">
                <div class="waiting_box">
                  <div class="img_cont">
                    <img src="../../assets/img/zhaobudao.png" alt />
                    <span class="gd">{{ $t('hj124') }}</span>
                    <div class="trading" @click="$router.push('/trading-list')">
                      <span>{{ $t('hj123') }}</span>
                    </div>
                  </div>
                </div>
              </div>
              <van-list v-model="loadingss" :finished="finishedss" :finished-text="$t('hj43')"
                v-show="tabsCurrentIndex === 1 && totalss != 0"  :immediate-check="immediate">
                <div class="tabs_o" >
                  <div class="tabs_o_items" v-for="(item, index) in tabsOrderList" :key="item.id" >
                    <div class="tabs_o_title" >
                      <div>
                        <span class="title">{{ item.stockName ? item.stockName : item.indexName }}</span>
                        <span class="buy_to_sell" :class="item.buyType == 1 ? 'maichu' : 'mairu'">{{
                            item.buyType == 1 ? $t('hj84') : $t('hj85')
                        }}</span>
                          <span class="buy_to_sell" :class="item.status == 1 ? 'mairu' : 'maichu'">{{
                            item.status == 1 ? $t('hj254') : $t('hj255')
                        }}</span>
                        <span class="multiple">{{ item.lever }}X</span>
                        <span class="nums">{{ item.buyNum / 100 + $t('hj117') }}</span>
                      </div>

                      <div class="right_btn pingbtn" @click="gdClose(item)">
                        <span style="margin-right: 0;">{{ $t('hj126') }}</span>
                      </div>
                    </div>
                    <div class="center_price">
                      <div class="start_price jiantou">
                        <span>{{ item.targetPrice }}</span>
                      </div>
                      <div class="new_price">
                        <span>{{ item.nowPrice }}</span>
                      </div>
                    </div>
                    <div class="bottom_price_title">
                      <div class="start_price">
                        <span>{{ $t('hj125') }}</span>
                      </div>
                      <div class="new_price">
                        <span>{{ $t('hj120') }}</span>
                      </div>
                    </div>
                  </div>
                </div>
              </van-list>




              <!-- 平仓 -->
              <div class="waiting" v-if="totals == 0 && tabsCurrentIndex === 2">
                <div class="waiting_box">
                  <div class="img_cont">
                    <img src="../../assets/img/zhaobudao.png" alt />
                    <span class="gd">{{ $t('hj127') }}</span>
                    <div class="trading" @click="$router.push('/trading-list')">
                      <span>{{ $t('hj123') }}</span>
                    </div>
                  </div>
                </div>
              </div>

              <van-list v-model="loadings" :finished="finisheds" :finished-text="$t('hj43')" @load="onLoads"
                :immediate-check="immediate" v-show="tabsCurrentIndex === 2 && totals != 0">
                <div class="tabs_o">
                  <div class="tabs_o_items oes" v-for="(item, index) in tabsPcArr" :key="index">
                    <div class="tabs_o_title">
                      <div>
                        <span class="title">{{ item.stockName ? item.stockName : item.indexName }}</span>
                        <span class="buy_to_sell" :class="item.orderDirection == '买跌' ? 'maichu' : 'mairu'">{{
                            item.orderDirection == "买跌" ?
                              $t('hj84') : $t('hj85')
                        }}</span>
                        <span class="multiple">{{ item.allProfitAndLose }}</span>
                        <span class="nums">{{ item.orderNum / 100 + $t('hj117') }}</span>
                      </div>
                      <div class="right_count"
                        :class="item.profitAndLose > 0 ? 'red' : item.profitAndLose == 0 ? '' : 'green'">
                        <span style="margin-right: 0;">{{ item.profitAndLose }}</span>
                      </div>
                    </div>
                    <div class="bottom_price_title">
                      <div class="profit_res">
                        <span>{{ $t('hj128') }}: {{ item.buyOrderTime | gettime }}</span>
                      </div>
                    </div>

                  </div>
                </div>
              </van-list>
              <!-- 新股 -->
              <div class="waiting" v-if="xgTotal == 0 && tabsCurrentIndex === 3">
                <div class="waiting_box">
                  <div class="img_cont">
                    <img src="../../assets/img/zhaobudao.png" alt />
                    <span class="gd">{{ $t('hj129') }}</span>
                    <div class="trading" @click="$router.push({ path: '/trading-list', query: { listid: 5 } })">
                      <span>{{ $t('hj123') }}</span>
                    </div>
                  </div>
                </div>
              </div>
              <van-list v-model="loadingXg" :finished="finishedXg" :finished-text="$t('hj43')"
                v-show="tabsCurrentIndex === 3 && xgTotal != 0" @load="onLoadXg" :immediate-check="immediate">
                <div class="tabs_o">
                  <div class="tabs_o_items" v-for="(item, index) in tabsXgArr" :key="item.id">
                    <div class="tabs_o_title">
                      <span class="title">{{ item.newName }}</span>
                      <span class="buy_to_sell" :class="item.type == 1 ? 'mairu' : 'maichu'">{{
                          item.type == 1 ? $t('hj45') : $t('hj46')
                      }}</span>
                      <!-- <span class="multiple">{{ item.lever  }}X</span>
                      <span class="nums">{{ item.buyNum / 100 + '手' }}</span> -->
                    </div>
                    <div style="display: flex;align-items: center;justify-content: space-between;">
                      <div style="width: 100%;">
                        <div class="center_price">
                          <div class="start_price jiantou">
                            <span>{{ item.buyPrice }}</span>
                          </div>
                          <div class="new_price" v-if="item.status != 3">
                            <span>{{ item.applyNums }}</span>
                          </div>
                          <div class="new_price" v-else>
                            <span>{{ item.applyNumber }}</span>
                          </div>
                        </div>
                        <div class="bottom_price_title">

                          <div class="start_price">
                            <span>{{ $t('hj130') }}</span>
                          </div>
                          <div class="new_price" v-if="item.status != 3">
                            <span>{{ $t('hj57') }}</span>
                          </div>
                          <div class="new_price" v-else>
                            <span>{{ $t('hj253') }}</span>
                          </div>
                        </div>
                      </div>

                      <div class="right_btn" style="padding: 0 0.2rem;" @click="zcsg(item)" :class="item.status == 1 ? 'pink' : item.status == 2 ? 'red'
                      : (item.status == 3 && item.type != 1) ? 'green' : item.status == 4 ? 'blue' : item.status == 5 ? 'purple' : ''" v-if="!(item.type == 1 && item.status == 3)">
                        <span>{{ item.status == 1 ?
                            $t('hj131') : item.status == 2 ? $t('hj132')
                              : (item.status == 3 && item.type != 1) ? $t('hj133') : item.status == 4 ? $t('hj134') : item.status == 5 ?
                                $t('hj135')
                                : ''
                        }}</span>
                      </div>
                      <div v-if="item.type == 1 && item.status == 3" class="right_btn pingbtn"
                        style="width: 2rem !important;" @click="getrenjiao(item.id)">
                        {{ $t('hj250') }}
                      </div>
                    </div>


                  </div>
                </div>
              </van-list>


            </div>

          </div>
        </div>
      </div>
    </div>
    <div class="dialog" v-if="titleDialog" @click="titleDialog = false"></div>
  </div>
</template>

<script>
import { Toast } from 'vant';
import { MessageBox } from 'mint-ui'
import * as api from "@/axios/api";
export default {
  data() {
    return {
      tabsArr: [this.$t('hj2'), this.$t('hj109'), this.$t('hj136'), this.$t('hj3')],
      tabsCurrentIndex: 0,
      titleName: this.$t('hj114'),
      indexSettingInfo: {},
      futuresSettingInfo: {},
      tabsPositionNumArr: [],
      titleIndex: 0,
      settingInfo: {},
      tabsPcArr: [],
      titleDialog: false,
      total: 0,
      totals: 0,
      totalss: 0,
      loading: false,
      finished: false,
      finisheds: false,
      finishedss: false,
      page: 1,
      pages: 1,
      pagess: 1,
      immediate: false,
      loadings: false,
      loadingss: false,
      tabsOrderList: [],
      xgTotal: 0,
      tabsXgArr: [],
      loadingXg: false,
      finishedXg: false,
    };
  },
  mounted() {
    this.getListDetail();
    this.getUserInfo();
    this.getIndexSettingInfo();
    this.getSettingInfo();
    this.getFuturesSetting();
    this.getListDetails();
    this.getorderList();
    this.getNewXg();
  },
  created() {
    if (this.$route.query.index) {
      this.tabsCurrentIndex = Number(this.$route.query.index)
    }


  },
  methods: {
    getrenjiao(val) {
      MessageBox.confirm(this.$t('hj251') + '?', this.$t('hj165'), {
        confirmButtonText: this.$t('hj161'),
        cancelButtonText: this.$t('hj106'),
      }).then(async () => {
        let opt = {
          id: val
        }
        let data = await api.submitSubscribe(opt)
        if (data.status == 0) {
          Toast(data.msg)
          this.finishedXg = false;
          this.getNewXg();
          this.getUserInfo();
        } else {
          Toast(data.msg)
        }
      }).catch(() => {

      });
    },
    getpingcang(val) {
      if (!this.$store.state.userInfo.idCard) {
        Toast(this.$t('hj138'))
        this.$router.push('/authentication')
        return
      }
      if (this.titleIndex == 0) {
        //沪深京
        MessageBox.confirm(this.$t('hj139') + '?', this.$t('hj165'), {
          confirmButtonText: this.$t('hj161'),
          cancelButtonText: this.$t('hj106'),
        }).then(async () => {
          let opt = {
            positionSn: val
          }
          let data = await api.sell(opt)
          if (data.status === 0) {
            Toast(data.msg)

            //沪深京持仓
            this.finished = false;
            this.getListDetail();
            this.tabsPositionNumArr=[]
            //沪深京平仓
            this.finisheds = false;
            this.tabsPcArr = [];
            this.getListDetails();

          } else if (data.msg.indexOf('不在交易时段内') > -1) {
            Toast(this.$t('hj140'))
          } else {
            Toast(data.msg)
          }
        }).catch(() => {

        });
      } else {
        //指数
        MessageBox.confirm(this.$t('hj139') + '?', this.$t('hj165'), {
          confirmButtonText: this.$t('hj161'),
          cancelButtonText: this.$t('hj106'),
        }).then(async () => {
          let opt = {
            positionSn: val
          }
          let data = await api.sellIndex(opt)
          if (data.status === 0) {
            Toast(data.msg)
            //指数持仓
            this.finished = false;
            this.getzhishuListDetail();
            this.tabsPositionNumArr=[];
            //指数平仓
            this.finisheds = false;
            this.tabsPcArr = [];
            this.getzhishuListDetails();
          } else if (data.msg.indexOf('不在交易时段内') > -1) {
            Toast(this.$t('hj140'))
          } else {
            Toast(data.msg)
          }
        }).catch(() => {

        });
      }
    },
    onLoad() {
      //持仓
      this.page++;
      switch (this.titleIndex) {
        case 0:
          //沪深京持仓
          this.getListDetail();
          break;
          case 1:
            //指数持仓
          this.getzhishuListDetail();
          break;
          
        default:
          break;
      }
    },
    onLoads() {
      //平仓
      this.pages++;
      switch (this.titleIndex) {
        case 0:
          //沪深京平仓
          this.getListDetails();
          break;
          case 1:
            //指数平仓
          this.getzhishuListDetails();
          break;  
        default:
          break;
      }
    },
    onLoadss() {
      this.pagess++;
      switch (this.titleIndex) {
        case 1:
          this.getorderList();
          break;
        default:
          break;
      }
    },
    onLoadXg() {
      // this.getNewXg();
    },
    async gdClose(item) {
      let opts = {
        id: item.id
      }
      let data = await api.delGuaDan(opts)
      if (data.status == 1) {
        Toast(this.$t('hj137'))
        this.page = 1;
        this.pages = 1;
        this.finished = false;
        this.finisheds = false;
        this.finishedss = false;
        this.tabsPositionNumArr = [];
        this.tabsPcArr = [];
        this.tabsOrderList = []
        switch (this.titleIndex) {
          case 0:
            this.titleName = this.$t('hj114');
            this.getListDetail();
            this.getListDetails();
            this.getorderList();
            break;
          case 1:
            this.titleName = this.$t('hj115');
            this.getzhishuListDetail();
            this.getzhishuListDetails();
            this.getorderList();
            break;
          case 2:
            this.titleName = this.$t('hj116');


            // this.getQhListDetail();
            // this.getQhListDetails();
            this.getorderList();
            break;
          default:
            break;
        }
      } else {
        Toast(data.msg)
      }
    },
    zcsg(item) {
      this.$router.push({ path: '/trading-list', query: { listid: 5 } })
    },
    SetTitleIndex(index) {
      //账户选项卡
      this.titleIndex = index;
      this.page = 1;
      this.pages = 1;
      this.finished = false;
      this.finisheds = false;
      this.finishedss = false;
      this.tabsPositionNumArr = [];
      this.tabsPcArr = [];
      this.tabsOrderList = [];
      switch (index) {
        case 0:
          this.titleName = this.$t('hj114');
          this.getListDetail();
          this.getListDetails();
          this.getorderList();
          this.handleTabsClick('', 0)
          break;
        case 1:
          this.titleName = this.$t('hj115');
          this.getzhishuListDetail();
          this.getzhishuListDetails();
          this.getorderList();
          this.handleTabsClick('', 0)
          break;
        case 2:
          this.totalss = 0;
          this.totals = 0;
          this.total = 0;
          this.titleName = this.$t('hj116');
          this.handleTabsClick('', 3)
          // this.getQhListDetail();
          // this.getQhListDetails();
          // this.getorderList();
          break;
        default:
          break;
      }
      this.titleDialog = false;
      if (navigator.vibrate) {
        // 支持
        navigator.vibrate([55]);
      }
    },
    handleTabsClick(item, index) {
      //持仓已平仓选项卡
      this.tabsCurrentIndex = index;
      if (index == 3) {
        this.finishedXg =false;
        this.getNewXg();
        
      }
      if (navigator.vibrate) {
        // 支持
        navigator.vibrate([55]);
      }
    },
    handleOpenDialog() {
      this.titleDialog = !this.titleDialog;
    },
    //挂单
    async getorderList() {
      let opts = {}
      let data = await api.getorderList(opts);
      this.loadingss = false;
      if (data.status === 0) {
        data.data.forEach(element => {
          this.tabsOrderList.push(element)
        })
        this.totalss = data.data.length
        this.finishedss = true; //只有一页，所以锁住翻页。有需要刷新数据的时候在调用方法前重新解锁
      } else {
        Toast(data.msg)
      }
    },
    async getIndexSettingInfo() {
      // 网站设置信息 指数
      let data = await api.getIndexSetting()
      if (data.status === 0) {
        // 成功
        this.indexSettingInfo = data.data
      } else {
        Toast(data.msg)
      }
    },
    async getFuturesSetting() {
      // 网站设置信息 期货
      let data = await api.getFuturesSetting()
      if (data.status === 0) {
        // 成功
        this.futuresSettingInfo = data.data
      } else {
        Toast(data.msg)
      }
    },
    async getSettingInfo() {
      let data = await api.getSetting()
      if (data.status === 0) {
        // 成功
        this.settingInfo = data.data
      } else {
        Toast(data.msg)
      }
    },
    async getUserInfo() {
      // 获取用户信息
      //   let showcookie = this.getCookie('USER_TOKEN');
      let data = await api.getUserInfo()
      if (data.status === 0) {
        // this.getProductSetting()
        this.$store.state.userInfo = data.data
      } else {
        Toast(data.msg)
      }
      this.$store.state.user = this.user
    },
    async getListDetail() {
      //获取沪深我的持仓列表
      this.loading = true;
      let opt = {
        state: 0,
        stockCode: '', // 代码
        stockSpell: '', // 简拼
        pageNum: this.page,
        pageSize: 15,
      }
      let data = await api.getOrderList(opt)
      this.loading = false;
      if (data.status === 0) {
        if (data.data.list.length < 15) {
          this.finished = true;
        }
        data.data.list.forEach(element => {
          this.tabsPositionNumArr.push(element)
        })
        this.total = data.data.total
      } else {
        Toast(data.msg)
      }
    },
    async getzhishuListDetail() {
      //获取指数持仓
      this.loading = true;
      let opt = {
        state: 0,
        stockCode: '', // 代码
        stockSpell: '', // 简拼
        pageNum: this.pageNum,
        pageSize: this.pageSize
      }
      let data = await api.getIndexOrderList(opt)
      this.loading = false;
      if (data.status === 0) {
        if (data.data.list.length < 15) {
          this.finished = true;
        }
        data.data.list.forEach(element => {
          this.tabsPositionNumArr.push(element)
        })
        this.total = data.data.total
      } else {
        Toast(data.msg)
      }
    },
    async getQhListDetail() {
      //获取期货持仓
      this.loading = true;
      let opt = {
        state: 0,
        fnCode: '', // 代码
        fnName: '', // 简拼
        pageNum: this.pageNum,
        pageSize: this.pageSize
      }
      let data = await api.getFuturesOrderList(opt)
      this.loading = false;
      if (data.status === 0) {
        if (data.data.list.length < 15) {
          this.finished = true;
        }
        data.data.list.forEach(element => {
          this.tabsPositionNumArr.push(element)
        })
        this.total = data.data.total
      } else {
        Toast(data.msg)
      }
    },
    async getListDetails() {
      //获取沪深我的平仓列表
      this.loadings = true;
      let opt = {
        state: 1,
        stockCode: '', // 代码
        stockSpell: '', // 简拼
        pageNum: this.pages,
        pageSize: 15
      }
      let data = await api.getOrderList(opt)
      this.loadings = false;
      if (data.status === 0) {
        if (data.data.list.length < 15) {
          this.finisheds = true;
        }
        data.data.list.forEach(element => {
          this.tabsPcArr.push(element)
        })
        this.totals = data.data.total
      } else {
        Toast(data.msg)
      }
    },
    async getzhishuListDetails() {
      //获取指数平仓
      this.loadings = true;
      let opt = {
        state: 1,
        stockCode: '', // 代码
        stockSpell: '', // 简拼
        pageNum: this.pageNum,
        pageSize: 15
      }
      let data = await api.getIndexOrderList(opt)
      this.loadings = false;
      if (data.data.list.length < 15) {
        this.finisheds = true;
      }
      if (data.status === 0) {
        data.data.list.forEach(element => {
          this.tabsPcArr.push(element)
        })
        this.totals = data.data.total
      } else {
        Toast(data.msg)
      }
    },
    async getQhListDetails() {
      //获取期货平仓
      this.loadings = true;
      let opt = {
        state: 1,
        fnCode: '', // 代码
        fnName: '', // 简拼
        pageNum: this.pageNum,
        pageSize: this.pageSize
      }
      let data = await api.getFuturesOrderList(opt)
      this.loadings = false;
      if (data.status === 0) {
        if (data.data.list.length < 15) {
          this.finisheds = true;
        }
        data.data.list.forEach(element => {
          this.tabsPcArr.push(element)
        })
        this.totals = data.data.total
      } else {
        Toast(data.msg)
      }
    },
    async getNewXg() {
      //获取新股
      this.loadingXg = true;
      let opt = {
      }
      let data = await api.getUserNewGuList(opt)
      this.loadingXg = false;
      if (data.status === 0) {
        this.tabsXgArr = data.data
        // data.data.list.forEach(element => {
        //   this.tabsXgArr.push(element)
        // })
        this.xgTotal = data.data.length;
        this.finishedXg = true;//只有一页，所以锁住翻页。有需要刷新数据的时候在调用方法前重新解锁
      } else {
        Toast(data.msg)
      }
    },
  },
  filters: {
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
      return y + '/' + mm + '/' + d + ' ' + h + ":" + m + ":" + c;
    }
  },
};
</script>

<style scoped lang="less">
/deep/ .mint-msgbox-title {
  font-size: 0.4rem !important;
}

.pingbtn {
  width: auto !important;
  height: auto !important;
  background: rgb(45, 106, 233);
  color: rgb(255, 255, 255);
  padding: 0.2rem 0.4rem;
  border-radius: 0.2rem !important;
}

.pink {
  color: #eb2f96;
  background: #fff0f6;
  border-color: #ffadd2;
}

.red {
  color: #f5222d;
  background: #fff1f0;
  border-color: #ffa39e;
}

.blue {
  color: #1890ff;
  background: #e6f7ff;
  border-color: #91d5ff;
}

.green {
  color: #52c41a;
  background: #f6ffed;
  border-color: #b7eb8f;
}

.purple {
  color: #722ed1;
  background: #f9f0ff;
  border-color: #d3adf7;
}

@boxCenter: {
  display: flex;
  justify-content: center;
  align-items: center;
}

;
@topCardColor: #bdbdbd;

.warehouse_page {
  width: 100%;
  height: calc(100% - 1.2974rem);
}

.content {
  width: 100%;
  height: 100%;
  position: relative;
}

.top_title.active {
  height: 6.5rem;
}

.top_title {
  width: 100%;
  height: 0.9231rem;
  position: absolute;
  top: 0.6rem;
  z-index: 10;
  overflow: hidden;
  transition: all 0.3s;
  padding: 0 .3rem;

  .titles {
    width: 100%;
    height: 0.9231rem;
    display: flex;
    padding: 0 0.1rem;
  }

  .left_title,
  .right_box {
    // width: 50%;
    height: 100%;
    display: flex;
  }

  .title {
    width: auto;
    height: 100%;
    font-size: 0.4897rem;
    display: flex;
    align-items: center;

    span {
      font-size: 0.4897rem;
      font-weight: 600;
    }
  }

  .img {
    // width: 40%;
    height: 100%;
    display: flex;
    align-items: center;

    img {
      width: 0.6rem;
      height: 0.6rem;
    }
  }
}

.warehouse_card {
  width: 100%;
  height: calc(100% - 1rem);
  position: absolute;
  top: 1.5231rem;
  z-index: 0;
  padding: 0 0.3333rem;
  background: rgb(242, 243, 247);

  .top_card {
    width: 100%;
    height: 4.2308rem;
    border-radius: 0.15rem;
    margin-top: 0.3rem;
    background: #fff;

    .card_content {
      width: 100%;
      height: 100%;
      padding: 0.4rem;

      .t_title {
        width: 100%;
        height: 0.5128rem;
        display: flex;
        align-items: center;

        span {
          font-size: 0.3046rem;
          font-weight: bold;
        }
      }

      .t_price {
        width: 100%;
        height: 0.5128rem;
        display: flex;
        align-items: center;

        .price {
          font-weight: 600;
          font-size: 0.4246rem;
          margin-top: 0.07rem;
        }

        .profits {
          font-weight: 800;
          font-size: 0.2846rem;
          color: #dd2c34;
          margin-top: 0.2rem;
          margin-left: 0.2rem;
        }
      }

      .balance {
        width: 100%;
        height: 0.3846rem;
        margin-top: 0.428rem;
        display: flex;

        >div {
          width: 50%;
          height: 100%;
          color: @topCardColor;

          span {
            font-weight: 600;
          }
        }
      }

      .num {
        width: 100%;
        height: 0.3846rem;
        margin-top: 0.1rem;
        display: flex;

        >div {
          width: 50%;
          height: 100%;

          span {
            font-weight: 600;
          }
        }

        .right_margin {
          color: rgb(81, 164, 99);

          span {
            font-weight: normal !important;
          }
        }
      }

      .margin {
        width: 100%;
        height: 0.3846rem;
        margin-top: 0.2rem;
        display: flex;

        >div {
          width: 50%;
          height: 100%;
          color: @topCardColor;

          span {
            font-weight: 600;
          }
        }
      }

      .prices {
        width: 100%;
        height: 0.3846rem;
        margin-top: 0.1rem;
        display: flex;

        >div {
          width: 50%;
          height: 100%;

          span {
            font-weight: 600;
          }
        }
      }
    }
  }
}

.over {
  width: 100%;
  height: calc(100% - 0.7179rem);
  overflow: auto;
  padding: 0 0.3rem;
}

.tabs_card {
  width: 100%;
  height: calc(100% - 4.24rem - 0.5rem - 0.6rem);
  margin-top: 0.3rem;
  background: #fff;
  border-radius: 0.2rem;
  padding-top: 0.15rem;
}

.over::-webkit-scrollbar {
  display: none;
}

.tabs_card_content {
  width: 100%;
  height: 100%;

  .tabs_top_title {
    width: 100%;
    height: 1rem;
    border-bottom: 0.01rem solid #ececec;
    display: flex;
    align-items: center;
    font-size: 0.4103rem;
    padding: 0.2rem 0.3rem 0;
    justify-content: space-between;
    overflow-x: scroll;

    &::-webkit-scrollbar {
      display: none;
    }

    >div {
      // width: 20%;
      height: 100%;
      display: flex;
      align-items: center;
      justify-content: center;
      white-space: nowrap;
      padding: 0 0.3rem;

      span {
        display: inline-block;
        height: 100%;
        font-weight: 550;
        // line-height: 0.7179rem;
        text-align: center;


      }

      span.active {
        color: rgb(62, 121, 226);
        text-align: center;
        // border-bottom: 0.1rem solid rgb(62, 121, 226);

      }

      span.active::after {
        content: '';
        width: 80%;

        height: 0.001rem;
        display: block;
        margin: 0 auto;
        margin-top: 0.3rem;
        border-bottom: 0.06rem solid rgb(62, 121, 226);
      }

    }
  }

  .tabs_o {
    width: 100%;
    margin-top: 0.3rem;

    >.tabs_o_items {
      width: 100%;
      // height: 2.2rem;
      position: relative;
      border-bottom: 0.01rem solid #ececec;
      // margin-bottom: 0.1rem;
      padding: 0.3rem 0;

      .tabs_o_title {
        width: 100%;
        // height: 0.7179rem;
        display: flex;
        align-items: center;
        justify-content: space-between;

        span {
          display: inline-block;
          margin-right: 0.2rem;
        }

        .title {
          font-weight: 600;
          font-size: 0.4059rem;
        }

        .buy_to_sell {

          padding: 0.08rem 0.1rem;

          font-weight: 600;
        }

        .multiple {
          padding: 0.08rem 0.1rem;
          background: rgb(236, 243, 252);
          color: rgb(42, 108, 230);
          font-weight: 600;
        }

        .nums {
          color: @topCardColor;
        }
      }

      .center_price {
        width: 100%;
        // height: 0.3846rem;
        display: flex;
        align-items: center;
        margin-top: 0.3rem;

        >div {
          width: 28%;
          height: 100%;

          span {
            font-weight: 600;
          }
        }

        .profit {
          color: rgb(209, 79, 91);
        }
      }

      .bottom_price_title {
        width: 100%;
        // height: 0.4rem;
        margin-top: 0.2rem;
        display: flex;
        align-items: center;

        >div {
          width: 28%;
          height: 100%;
          color: @topCardColor;

          span {
            font-weight: 600;
          }
        }
      }
    }
  }
}

.waiting {
  width: 100%;
  margin-top: 0.3rem;
  position: relative;

  .waiting_box {
    width: 100%;
    height: 4rem;
    display: flex;
    align-items: center;
    justify-content: center;
    margin-top: 1rem;

    >div {
      width: 60%;
      height: 100%;

      img {
        width: 100%;
        height: 100%;
      }

      .gd {
        display: inline-block;
        width: 100%;
        text-align: center;
        color: rgb(133, 133, 133);
      }

      .trading {
        width: 100%;
        height: 2rem;
        display: flex;
        align-items: center;
        justify-content: center;

        span {
          display: inline-block;
          width: 4.1026rem;
          height: 1.0256rem;
          background: #f7f7f7;
          color: #3773dd;
          font-weight: 600;
          font-size: 0.4615rem;
          text-align: center;
          line-height: 1.0256rem;
          border-radius: 0.2rem;
        }
      }
    }
  }
}

.right_btn {
  width: 1.9949rem;
  height: 0.6667rem;
  // line-height: 0.6667rem;
  // position: absolute;
  right: 1%;
  top: 27%;
  // background: #f7f7f7;
  // color: #3773dd;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 0.15rem;

  span {
    font-weight: 600;
    font-size: 0.2846rem;
  }
}

.right_count {
  width: 1.7949rem;
  height: 0.6667rem;
  position: absolute;
  right: 1%;
  top: 20%;

  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 0.3rem;

  span {
    font-weight: 600;
    font-size: 0.3846rem;
  }
}

.profit_res {
  width: 100% !important;
  margin-top: .2rem;
}

.oes {
  // height: 1.5rem !important;
}

.dialog {
  width: 100%;
  height: 100%;
  position: absolute;
  top: 0;
  background: rgba(0, 0, 0, .5);
  z-index: 9;
}

.overflow_box {
  width: 100%;
  height: 1.5rem;
  border-radius: .2rem;
  padding: 0 .3rem;
  margin: .3rem 0;
  background: #fff;
  display: flex;
  align-items: center;
  justify-content: space-between;

  >div {
    height: 50%;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .left_titles {
    color: rgb(129, 131, 133);
    font-size: .3815rem;

    span {
      font-weight: normal !important;


    }
  }

  .right_price {
    color: rgb(128, 135, 145);
  }
}

.kuan {
  width: auto !important;
  // padding-left: 0.5rem;
  letter-spacing: 0.02rem;
}

.kuans {
  width: auto !important;
  padding-left: 0.5rem;
  letter-spacing: 0.02rem;
}

.left_price {
  font-weight: normal;

  span {
    font-size: 0.3rem;
    color: #000;
    font-weight: 500 !important;
    ;
  }
}

.left_price {
  font-weight: normal;

  span {
    font-size: 0.3rem;
    color: #000;
    font-weight: 500 !important;
    ;
  }
}

.numDemo {
  font-size: 0.3rem;
  color: #000;
  font-weight: 500 !important;
  ;
}

.maichu {
  color: rgb(225, 57, 65);
  background: rgb(252, 238, 240);
}

.mairu {
  color: rgb(109, 175, 125);
  background: rgb(215, 243, 235);
}

.jiantou {
  position: relative;
}

.jiantou::before {
  content: '';
  background-image: url('../../assets/img/youyou.png');
  background-size: 100% 100%;
  width: 0.6rem;
  height: 0.2rem;
  font-size: 0.8rem;
  position: absolute;
  right: 0.5rem;
  top: calc(50% - 0.15rem);
}
</style>