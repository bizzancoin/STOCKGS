<template>
  <div :class="`wrapper ${$state.theme === 'red' ? 'red-theme' : 'black-theme'}`">
    <!-- <div class="header">
      <mt-header :title="detail.name+' ('+detail.code+')'">
        <router-link to="/list" slot="left">
          <mt-button icon="back"></mt-button>
        </router-link>
      </mt-header>
    </div> -->
    <!--<mt-search-->
    <!--style="height:auto;"-->
    <!--fixed-->
    <!--@click.enter.native="toSearch"-->
    <!--placeholder="可输入股票代码或简拼"-->
    <!--&gt;-->
    <!--</mt-search>-->
    <div class="flex ">
      <div class="">
        <div></div>
        <div class="">
          <div class="detailName">黄金</div>
          <div class="detailCode">XAUSD</div>
        </div>
      </div>
    </div>
     <div class="page-part detail-part ">
                <!-- 明细 -->
                <div class="clearfix">
                    <div class="pull-left col-xs-7">
                        <p :class="detail.nowPrice - detail.preclose_px <0?'price green':'price red'">
                            {{detail.nowPrice}}</p>
                        <p :class="detail.nowPrice - detail.preclose_px <0?'gain green':'gain red'">
                            {{detail.hcrate}}%</p>
                    </div>
                    <div class="pull-right col-xs-5">
                        <ul class="price-detail text-center">
                            <li>
                                <p class="title">昨收</p>
                                <p :class="detail.nowPrice - detail.preclose_px <0?'number green':'number red'">
                                    {{detail.preclose_px}}</p>
                            </li>
                            <li>
                                <p class="title">今开</p>
                                <p :class="detail.nowPrice - detail.preclose_px <0?'number green':'number red'">
                                    {{detail.open_px}}</p>
                            </li>
                            <li>
                                <p class="title">最高</p>
                                <p :class="detail.nowPrice - detail.preclose_px <0?'number green':'number red'">
                                    {{detail.today_max}}</p>
                            </li>
                            <li>
                                <p class="title">最低</p>
                                <p :class="detail.nowPrice - detail.preclose_px <0?'number green':'number red'">
                                    {{detail.today_min}}</p>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="row detail-list">
                    <div class="col-xs-4">
                        <p class="title">成交量</p>
                        <p class="number">{{(Number(detail.business_amount)/100/10000).toFixed(2)}}万</p>
                    </div>
                    <div class="col-xs-6">
                        <p class="title">成交额</p>
                        <p class="number">{{(Number(detail.business_balance)/100000000).toFixed(2)}}亿</p>
                    </div>
                </div>
            </div>
    <!-- <stockHq /> -->
       <div class="page-part box-part">
                <!-- 图 -->
                <imgBox :code="$route.query.code" :imgList='detail'/>
            </div>
    <!-- tab -->
    <div class="news-tab">
      <mt-navbar v-model="news">
        <mt-tab-item id="tab_0">
          <span class="tab-name">财经要闻</span>
        </mt-tab-item>
        <mt-tab-item id="tab_1">
          <span class="tab-name">经济数据</span>
        </mt-tab-item>
        <mt-tab-item id="tab_2">
          <span class="tab-name">全球股市</span>
        </mt-tab-item>
        <mt-tab-item id="tab_3">
          <span class="tab-name">7*24全球</span>
        </mt-tab-item>
        <mt-tab-item id="tab_4">
          <span class="tab-name">商品资讯</span>
        </mt-tab-item>
        <!-- <mt-tab-item id="tab_5">
          <span class="tab-name">上市公司</span>
        </mt-tab-item> -->
        <!-- <mt-tab-item id="tab_6">
          <span class="tab-name">全球央行</span>
        </mt-tab-item> -->
      </mt-navbar>
      <mt-tab-container v-model="news" :swipeable="true">
        <mt-tab-container-item id="tab_0">
          <div class="news-content">
            <div 
              class="news-item"
              v-for="item of newsContent1"
              :key="item.id"
            >
              <p
                class="news-title"
                style="-webkit-box-orient: vertical;"
              >{{item.title}}</p>
              <span class="news-status">
                <i class="glyphicon glyphicon-eye-open"></i>
                浏览量：{{item.views}}
              </span>
            </div>
          </div>
        </mt-tab-container-item>
        <mt-tab-container-item id="tab_1">
          <div class="news-content">
            <div 
              class="news-item"
              v-for="item of newsContent2"
              :key="item.id"
            >
              <p
                class="news-title"
                style="-webkit-box-orient: vertical;"
              >{{item.title}}</p>
              <span class="news-status">
                <i class="glyphicon glyphicon-eye-open"></i>
                浏览量：{{item.views}}
              </span>
            </div>
          </div>
        </mt-tab-container-item>
        <mt-tab-container-item id="tab_2">
          <div class="news-content">
            <div 
              class="news-item"
              v-for="item of newsContent3"
              :key="item.id"
            >
              <p
                class="news-title"
                style="-webkit-box-orient: vertical;"
              >{{item.title}}</p>
              <span class="news-status">
                <i class="glyphicon glyphicon-eye-open"></i>
                浏览量：{{item.views}}
              </span>
            </div>
          </div>
        </mt-tab-container-item>
        <mt-tab-container-item id="tab_3">
          <div class="news-content">
            <div 
              class="news-item"
              v-for="item of newsContent4"
              :key="item.id"
            >
              <p
                class="news-title"
                style="-webkit-box-orient: vertical;"
              >{{item.title}}</p>
              <span class="news-status">
                <i class="glyphicon glyphicon-eye-open"></i>
                浏览量：{{item.views}}
              </span>
            </div>
          </div>
        </mt-tab-container-item>
        <mt-tab-container-item id="tab_4">
          <div class="news-content">
            <div 
              class="news-item"
              v-for="item of newsContent5"
              :key="item.id"
            >
              <p
                class="news-title"
                style="-webkit-box-orient: vertical;"
              >{{item.title}}</p>
              <span class="news-status">
                <i class="glyphicon glyphicon-eye-open"></i>
                浏览量：{{item.views}}
              </span>
            </div>
          </div>
        </mt-tab-container-item>
      </mt-tab-container>
    </div>
    <div class="btn-list">
      <div class="btn btn1" @click="addOptions">
        <img :src="btnIcon1" alt="">
        自选
      </div>
      <div class="btn btn2" @click="handleTwoBuyClick">
        <img :src="btnIcon2" alt="">
        两融交易
      </div>
      <div class="btn btn3" @click="handleSubBuyClick">
        <img :src="btnIcon3" alt="">
        分仓交易
      </div>
    </div>
    <!-- <foot></foot> -->
  </div>
</template>

<script>
import imgBox from "./compontent/img";
import stockHq from "./compontent/stock/stockHq";
import { Toast } from "mint-ui";
import * as api from "@/axios/api";
import foot from '@/components/foot/foot'

export default {
  components: {
    imgBox,
    stockHq,
    foot
  },
  props: {},
  data() {
    return {
      detail: {
        name: "青海华鼎",
        code: "600243",
        stockType: "sh",
        spell: "qhhd",
        gid: "sh600243",
        nowPrice: "4.290",
        hcrate: 1.18,
        today_max: "4.300",
        today_min: "4.240",
        business_balance: "4151985.000",
        business_amount: "973005",
        preclose_px: "4.240",
        open_px: "4.240",
        buy1: "4.290",
        buy2: "4.280",
        buy3: "4.270",
        buy4: "4.260",
        buy5: "4.250",
        sell1: "4.300",
        sell2: "4.310",
        sell3: "4.320",
        sell4: "4.330",
        sell5: "4.340",
        buy1_num: "34700",
        buy2_num: "38900",
        buy3_num: "35900",
        buy4_num: "26200",
        buy5_num: "50100",
        sell1_num: "76005",
        sell2_num: "5100",
        sell3_num: "2500",
        sell4_num: "40200",
        sell5_num: "11500",
      }, // 详情
      buyList: [
        { price: 33.5, price2: 14323.5 },
        { price: 33.5, price2: 14323.5 },
        { price: 33.5, price2: 14323.5 },
        { price: 33.5, price2: 14323.5 },
        { price: 33.5, price2: 14323.5 },
      ],
      isOptionOpt: false, // 是否已经添加自选
      timer: null,
      loading: false,
      // 新闻
      news:'tab_0',
      btnIcon1: require('../../../static/img/detail/zixuan-icon.png'),
      btnIcon2: require('../../../static/img/detail/liangrong-icon.png'),
      btnIcon3: require('../../../static/img/detail/fencang-icon.png'),
      newsContent1: [], // 财经要闻
      newsContent2: [], // 经济数据
      newsContent3: [], // 全球股市
      newsContent4: [], // 7*24全球
      newsContent5: [] // 商品资讯
    };
  },
  watch: {},
  computed: {},
  created() {
    this.detail.code = this.$route.query.code;
    this.detail.stockType = this.$route.query.stock_type;
    // this.timer = setInterval(this.refreshList, 5000)
  },
  beforeDestroy() {
    clearInterval(this.timer);
  },
  mounted() {
    this.getNewsList(1)
    this.getNewsList(2)
    this.getNewsList(3)
    this.getNewsList(4)
    this.getNewsList(5)
    this.getDetail();
    if (this.$store.state.userInfo.phone) {
      // 判断是否登录
      this.getOpation();
    } else {
      // 获取用户信息
      this.getUserInfo();
    }
  },
  methods: {
    // 两融交易
    handleTwoBuyClick() {
      this.$router.push({
        path: '/twoBuy',
        query: {
          code: this.detail.code
        }
      })
    },
    // 分仓交易
    handleSubBuyClick() {
      this.$router.push({
        path: '/subWarehouseBuy',
        query: {
          code: this.detail.code
        }
      })
    },
    toSearch() {
      this.$router.push("/searchlist");
    },
    async getNewsList(type) {
      let data = await api.queryNewsList(type);
      console.log('xinwen:',data)
      switch(type) {
        case 1:
          this.newsContent1 = data.data.list
          break;
        case 2:
          this.newsContent2 = data.data.list
          break;
        case 3:
          this.newsContent3 = data.data.list
          break;
        case 4:
          this.newsContent4 = data.data.list
          break;
        case 5:
          this.newsContent5 = data.data.list
          break;
      }
    },
    async getUserInfo() {
      // 获取用户信息
      let data = await api.getUserInfo();
      if (data.status === 0) {
        this.$store.state.userInfo = data.data;
        this.getOpation();
      } else {
        Toast(data.msg);
      }
      this.$store.state.user = this.user;
    },
    async refreshList() {
      if (this.loading) {
        return;
      }
      // this.getDetail()
    },
    async getOpation() {
      let opts = {
        code: this.$route.query.code,
      };
      let data = await api.isOption(opts);
      if (data.status === 0) {
        // 0 --> 未添加
        this.isOptionOpt = false;
      } else {
        this.isOptionOpt = true;
      }
    },
    async getDetail() {
      let opts = {
        code: this.$route.query.code,
        stockType: this.$route.query.stock_type,
      };
      let data = await api.getSingleStock(opts);
      this.loading = false;
      if (data.status === 0) {
        this.detail = data.data;
      } else {
        Toast(data.msg);
      }
    },
    async addOptions() {
      //   if(!this.$store.state.userInfo.phone){
      //     MessageBox.confirm('您还未登录，是否去登录?').then(action => {
      //         this.$router.push('/login')
      //     });
      //     return
      //   }
      let data = await api.addOption({ code: this.detail.code });
      if (data.status === 0) {
        Toast("添加自选成功");
        this.isOptionOpt = true;
      } else {
        Toast(data.msg);
      }
    },
    async deteleOptions() {
      let data = await api.delOption({ code: this.detail.code });
      if (data.status === 0) {
        Toast("删除自选股成功");
        this.isOptionOpt = false;
      } else {
        Toast(data.msg);
      }
    },
    toBuy() {
      this.$router.push({
        name: "buy",
        params: {
          gid: this.detail.id,
          name: this.detail.name,
          code: this.detail.code,
          hcrate: this.detail.hcrate,
          nowPrice: this.detail.nowPrice,
        },
      });
    },
  },
};
</script>
<style lang="less" scoped>
.wrapper {
  width: 100%;
  height: 100%;
  // background-color: #151515;
}
.btn-box {
  padding: 0;
}

.mint-header {
  background: #21252a;
}

.page-part {
  // background: #21252a;
  padding: 0.3rem;
  margin-bottom: 0.1rem;
  border-bottom: 0.01rem solid #676b6f;
}

.stock-price li {
  width: 20%;

  p {
    height: 0.34rem;
    line-height: 0.34rem;
  }
}

.detail-part {
  .price {
    font-size: 0.56rem;
    margin-top: 0.1rem;
    margin-bottom: 0.2rem;
    padding-bottom: 0.2rem;
  }

  .gain {
    font-size: 0.24rem;
  }

  .title {
    line-height: 0.36rem;
  }

  .number {
    line-height: 0.36rem;
  }

  .price-detail {
    li {
      width: 50%;
      float: left;
      margin-bottom: 0.16rem;
    }
  }

  .detail-list {
    padding-left: 0.3rem;
  }
}

.box-part {
  padding: 0;
}
.news-tab {
  min-height: 5rem;
  padding: 0 0.2rem 1.2rem;
  margin-top: 0.3rem;
  /deep/.mint-tab-container {
    background-color: #1d1e29;
  }
  /deep/.mint-tab-item {
    background-color: #1d1e29;
  }
  /deep/.mint-tab-item-label {
    color: #fff;
    font-size: 0.26rem;
  }
  /deep/.is-selected .tab-name {
    position: relative;
  }
  /deep/.mint-navbar .mint-tab-item.is-selected {
    border-bottom: none;
  }
  /deep/.is-selected .tab-name:after {
    position: absolute;
    display: block;
    content: "";
    height: 0.07rem;
    background-color: #1381a4;
    width: 100%;
    left: 0;
    bottom: -0.25rem;
  }
}
.news-content {
  position: relative;
  .news-item {
    padding: 0.2rem;
    .news-title {
      position: relative;
      width: 100%;
      font-size: 0.3rem;
      line-height: 0.46rem;
      overflow: hidden;
      text-overflow: ellipsis;
      -webkit-line-clamp: 2;
      display: -webkit-box;
    }
    .news-status {
      display: block;
      margin-top: 0.1rem;
      font-size: 0.2rem;
      line-height: 0.36rem;
      color: #606167;
      margin-top: 0.1rem;
      padding-bottom: 0.25rem;
      border-bottom: 0.01rem solid #32333b;
    }
  }
}
.btn-list{
  display: flex;
  align-items: center;
  justify-content: space-between;
  box-sizing: border-box;
  padding: 0.1rem 0.2rem 0.1rem 0.2rem;
  width: 100%;
  position: fixed;
  bottom: 0;
  background-color: #16171d;
  .btn{
    width: 2.4rem;
    height: 0.76rem;
    border-radius: 1rem;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size:0.29rem;
    font-family:Microsoft YaHei;
    font-weight:400;
    color:rgba(255,255,255,1);
    img{
      width: 0.28rem;
      margin-right: 0.1rem;
    }
    &.btn1{
      width: 1.85rem;
      background-color: #D63535;
    }
    &.btn2{
      background-color: #7266BA;
    }
    &.btn3{
      background-color: #148EB4;
    }
  }
}
.red-theme{
  .exchangeData{
    background-color: white;
    .overall-item{
      h4{
        color: #656565;
      }
      span{
        color: #212121;
      }
    }
  }
  .news-tab{
    /deep/.mint-navbar{
      background: white;
      .mint-tab-item{
        background: white;
        .mint-tab-item-label{
          color: #000000;
        }
        &.is-selected{
          .mint-tab-item-label{
            color: #BB1815;
            .tab-name{
              &::after{
                background-color: #BB1815;
              }
            }
          }
        }
      }
    }
    .mint-tab-container{
      background: white;
      .news-title{
        color: #656565;
      }
      .news-status{
        border-color: #DFDFDF;
      }
    }
  }
  .btn-list{
    background-color: #e9e9e9;
    .btn{
      &.btn1{
        background-color: #D63535;
      }
      &.btn2{
        background-color: #7266BA;
      }
      &.btn3{
        background-color: #138EB4;
      }
    }
  }
}
.flex{
  display: flex;
}
.justify-between{
  justify-content: space-between;
}
.flex-wrap{
  flex-wrap: wrap;
}
.align-center{
  align-items: center;
}
.detailName{
  font-size: 0.48rem;
  color: #000;
  font-weight: 700;
}
.detailCode{
  font-size: 0.16rem;
  color: #bbb;
}
</style>
