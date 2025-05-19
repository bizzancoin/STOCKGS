<template>
  <div class="wrapper">
    <!-- 搜索框 -->
    <div class="home-search">
      <div class="home-search-me" @click="$router.push('/user')">
        <img src="../../assets/ico/wogerenziliao.png">
      </div>
      <div class="home-search-input">
        <img src="../../assets/ico/fangdajing.png" alt="">
        <input type="text" @focus="$router.push('/Searchlist')" placeholder="名称/代码/拼音首字母">
      </div>
      <div class="home-search-ctl">
        <img class="lingdang" src="../../assets/ico/lingdang.png" alt="" @click="$router.push('/notify')">
        <img class="pifu" src="../../assets/ico/pifu.png" alt="" @click="$state.toggleTheme()">
      </div>
    </div>
    <!-- 头部概况 -->
    <div class="account-box zhishu" :style="{ backgroundImage: `url(${$state.theme == 'red' ? r_bg : b_bg})` }">
      <div class="content ">
        <div :class="i.floatPoint < 0 ? 'tab greenBg text-center' : 'tab redBg text-center'" v-for="(i, index) in market"
          v-if="index < 3" :key="i.key">
          <div :index='index' class="name">{{ i.indexName }}</div>
          <p :class="changeTextClass[index] == true ? 'price heartBeat' : 'price'">
            {{ Number(i.currentPoint).toFixed(2) }}
          </p>
          <div class="status">
            <span>{{ Number(i.floatPoint).toFixed(2) }}</span>
            <span>{{ i.floatRate }}%</span>
          </div>
        </div>
      </div>
    </div>
    <!-- 导航路由 -->
    <div class="icon-router clearfix home-ico-router">

      <div class="col-xs-3 text-center">
        <a class='icon-wrap animated zoomIn' @click="goList" href="javascript:;">
          <img class="icon-img" src="../../assets/ico/hangqing.png" alt="">
        </a>
        <p class="icon-title">行情</p>
      </div>
      <div class="col-xs-3 text-center">
        <a class='icon-wrap animated zoomIn' @click="goOrderlist" href="javascript:;">
          <img class="icon-img" src="../../assets/ico/jiaoyi.png" alt="">
        </a>
        <p class="icon-title">持仓</p>
      </div>
      <div class="col-xs-3 text-center">
        <a class='icon-wrap animated zoomIn' @click="goMyList" href="javascript:;">
          <img class="icon-img" src="../../assets/ico/xuanze.png" alt="">
          <!-- <i class="iconfont icon-xinshou"></i> -->
        </a>
        <p class="icon-title">自选</p>
      </div>
      <div class="col-xs-3 text-center">
        <a class='icon-wrap animated zoomIn' @click="goMyinfo" href="javascript:;">
          <img class="icon-img" src="../../assets/ico/wo.png" alt="">
          <!-- <i class="iconfont icon-xinshou"></i> -->
        </a>
        <p class="icon-title">我的</p>
      </div>
    </div>
    <div class="shadow-box">
      <img class="shadow-ico" src="../../assets/ico/shadow.png" alt="">
    </div>
    <!-- 公告栏 -->
    <div class="col-xs-24 horseLampModule">
      <div class="horseLamp-box" v-if="artList.artTitle" @click="toAltDetail">
        <img class="tzIco" v-show="$state.theme != 'red'" src="../../assets/ico/horn.png" />
        <img class="tzIco" v-show="$state.theme == 'red'" src="../../assets/ico/horn-red.png" />
        <div class="wrap">
          <!-- // 外框，固定宽度 -->
          <div ref="box" id="box">
            <!-- // 内部滚动框 -->
            <div id="marquee">{{ artList.artTitle }}</div>
            <!-- //展示的文字 -->
            <div id="copy"></div>
            <!-- // 文字副本，为了实现无缝滚动 -->
          </div>
          <div ref='node' id="node">{{ artList.artTitle }}</div>
          <!-- //为了获取text实际宽度 -->
        </div>
        <span class="right">{{ new Date(artList.addTime).getFullYear() }}-{{ new Date(artList.addTime).getMonth() + 1 }}-{{ new
            Date(artList.addTime).getDate()
        }}</span>
      </div>
    </div>

    <!-- <div class="nav-bg page-part" @click="goList">
        <img class="img" src="../../assets/img/shangpinbg.png" alt="shangpinbg">
      </div> -->
    <!-- 轮播图 -->
    <div class="swiper-home">
      <mt-swipe :auto="4000">
        <mt-swipe-item v-for="banner in bannerList" :key="banner.id">
          <a :href="banner.targetUrl || null">
            <img style="width:100%;height: 100%;" :src="banner.bannerUrl" alt="">
          </a>
        </mt-swipe-item>
      </mt-swipe>
    </div>
    <!-- 轮播图 -->
    <AllList />
    <div class="swiper-ad">
      <mt-swipe :auto="4000">
        <mt-swipe-item v-for="banner in bannerList" :key="banner.id">
          <a class="banner-ad" :href="banner.targetUrl || null">
            <img style="width:100%;height: 100%;" :src="banner.bannerUrl" alt="">
          </a>
        </mt-swipe-item>
      </mt-swipe>
    </div>
    <!-- <div v-show="true" class="box  page-part">
        <div class="box-title">
          <span class="left"></span>大盘指数
        </div>
        <div class="box-contain clearfix">
          <div :class="index < 3?'animated zoomInDown':index == 3?'animated zoomInLeft':index == 5?'animated zoomInRight':index == 4?'animated zoomIn':'animated zoomInUp'" v-for="(i,index) in market" :key="i.key">
          <div :class="i.floatPoint<0?'tab greenBg':'tab redBg'" v-for="(i,index) in market" :key="i.key">
            <p :index='index' class="name">{{i.indexName}}</p>
            <p :class="changeTextClass[index] == true?'price heartBeat':'price'">{{Number(i.currentPoint).toFixed(2)}}</p>
            <div class="status">
              <span :class="i.floatPoint<0?'pifting green':'pifting red'">{{Number(i.floatPoint).toFixed(2)}}</span>
              <span :class="i.floatPoint<0?'Percentage green':'Percentage red'">{{i.floatRate}}%</span>
            </div>
          </div>
        </div>
      </div> -->
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
            <div class="news-item" v-for="item of newsContent1" :key="item.id">
              <p class="news-title" style="-webkit-box-orient: vertical;">{{ item.title }}</p>
              <span class="news-status">
                <i class="glyphicon glyphicon-eye-open"></i>
                浏览量：{{ item.views }}
              </span>
            </div>
          </div>
        </mt-tab-container-item>
        <mt-tab-container-item id="tab_1">
          <div class="news-content">
            <div class="news-item" v-for="item of newsContent2" :key="item.id">
              <p class="news-title" style="-webkit-box-orient: vertical;">{{ item.title }}</p>
              <span class="news-status">
                <i class="glyphicon glyphicon-eye-open"></i>
                浏览量：{{ item.views }}
              </span>
            </div>
          </div>
        </mt-tab-container-item>
        <mt-tab-container-item id="tab_2">
          <div class="news-content">
            <div class="news-item" v-for="item of newsContent3" :key="item.id">
              <p class="news-title" style="-webkit-box-orient: vertical;">{{ item.title }}</p>
              <span class="news-status">
                <i class="glyphicon glyphicon-eye-open"></i>
                浏览量：{{ item.views }}
              </span>
            </div>
          </div>
        </mt-tab-container-item>
        <mt-tab-container-item id="tab_3">
          <div class="news-content">
            <div class="news-item" v-for="item of newsContent4" :key="item.id">
              <p class="news-title" style="-webkit-box-orient: vertical;">{{ item.title }}</p>
              <span class="news-status">
                <i class="glyphicon glyphicon-eye-open"></i>
                浏览量：{{ item.views }}
              </span>
            </div>
          </div>
        </mt-tab-container-item>
        <mt-tab-container-item id="tab_4">
          <div class="news-content">
            <div class="news-item" v-for="item of newsContent5" :key="item.id">
              <p class="news-title" style="-webkit-box-orient: vertical;">{{ item.title }}</p>
              <span class="news-status">
                <i class="glyphicon glyphicon-eye-open"></i>
                浏览量：{{ item.views }}
              </span>
            </div>
          </div>
        </mt-tab-container-item>
      </mt-tab-container>
    </div>
    <!-- tab -->

    <foot></foot>
  </div>

</template>
  
<script>
import foot from '@/components/foot/foot'
import AllList from '@/page/list/list-all'
import HomeList from './components/home-list'
import { Toast } from 'mint-ui'
import * as api from '@/axios/api'
import bannerImg from '../../assets/img/banner.png'
import eventBus from '@/event.js'

export default {
  components: {
    foot,
    HomeList,
    AllList
  },
  props: {},
  data() {
    return {
      news: 'tab_0',
      market: [],
      moveStats: false,
      bannerList: '',
      bannerImg: bannerImg,
      // market: {}, // 大盘指数
      changeTextClass: {},
      artList: {}, // 公告列表
      timer: null,
      timer2: null,
      settingForm: {
        futuresDisplay: false,
        indexDisplay: false,
        kcStockDisplay: false,
        stockDisplay: false
      },
      theme: 'black',
      newsContent1: [], // 财经要闻
      newsContent2: [], // 经济数据
      newsContent3: [], // 全球股市
      newsContent4: [], // 7*24全球
      newsContent5: [], // 商品资讯,
      b_bg: require('../../../static/img/bg-zhisu.png'),
      r_bg: require('../../../static/img/bg-zhisu-red.png')
    }
  },
  created() {
    this.getProductSetting()
    // this.timer = setInterval(this.refreshList, 3000)
  },

  beforeDestroy() {
    clearInterval(this.timer)
    clearInterval(this.timer2)
  },
  computed: {},
  // 更新的时候运动
  updated() {
    if (!this.moveStats) {
      this.move()
    }
  },
  methods: {
    async getNewsList(type) {
      console.log(type)
      let data = await api.queryNewsList(type);
      console.log('xinwen:', data)
      switch (type) {
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
    // 主题切换
    handleChangeThemeClick() {
      if (this.theme === 'black') {
        eventBus.$emit("getTheme", 'red');
        this.theme = 'red'
      } else {
        eventBus.$emit("getTheme", 'black');
        this.theme = 'black'
      }
    },
    move() {
      // 获取文字text 的计算后宽度  （由于overflow的存在，直接获取不到，需要独立的node计算）
      if (!document.getElementById('node')) {
        return
      }
      let width = document.getElementById('node').getBoundingClientRect().width
      // let width = this.$refs.node.getBoundingClientRect().width
      let box = document.getElementById('box')
      let copy = document.getElementById('copy')
      copy.innerText = this.artList.artTitle // 文字副本填充
      let distance = 0 // 位移距离
      // 设置位移
      this.moveStats = true
      clearInterval(this.timer2)
      this.timer2 = setInterval(function () {
        distance = distance - 1
        // 如果位移超过文字宽度，则回到起点
        if (-distance >= width) {
          distance = 16
          // clearInterval(timer)
        }
        box.style.transform = 'translateX(' + distance + 'px)'
      }, 30)
    },
    async getArtList() {
      // 获取公告列表
      let opts = {
        pageNum: 1,
        pageSize: 1
      }
      let result = await api.getArtList(opts)
      if (result.status === 0) {
        if (result.data.list.length > 0) {
          this.artList = result.data.list[0]
        }
      } else {
        this.$message.error(result.msg)
      }
    },
    async getMarket() {
      // 获取大盘指数
      // let result = await api.getMarket()
      let result = await api.getIndexMarket()
      if (result.status === 0) {
        this.market = result.data
      } else {
        Toast(result.msg)
      }
    },
    async refreshList() {
      // 刷新大盘指数
      let result = await api.getIndexMarket()
      this.changeTextClass = {}
      if (+result.status === 0) {
        // this.market = result.data.market
        result.data.forEach((element, i) => {
          this.changeTextClass[i] = ''
          if (element.currentPoint !== this.market[i].currentPoint) {
            this.changeTextClass[i] = true // 设置对应的动画过滤
            this.market[i].currentPoint = element.currentPoint
            this.market[i].floatPoint = element.floatPoint
            this.market[i].floatRate = element.floatRate
          }
        })
      } else {
        Toast(result.msg)
      }
    },
    async getBanner() {
      // 获取显示的banner
      let result = await api.getBannerByPlat({ platType: 'm' })
      if (result.status === 0) {
        this.bannerList = result.data
      } else {
        Toast(result.msg)
      }
    },
    async getProductSetting() {
      // 获取产品配置信息
      let result = await api.getProductSetting()
      if (+result.status === 0) {
        this.settingForm = result.data
      } else {
        Toast(result.msg)
      }
    },
    articleList() {
      // 新手
      this.$router.push('/articleList')
    },
    toBuy() {
      // 去购买页面
      this.$router.push('/buy')
    },
    goList() {
      // 去列表页面
      this.$router.push('/list')
    },
    goMyList() {
      this.$router.push('/mylist')
    },
    goOrderlist() {
      this.$router.push('/orderlist')
    },
    goMyinfo() {
      this.$router.push('/user')
    },
    goIndexList() {
      this.$router.push('/indexlist')
    },
    toDetail() {
      // 去列表页面
      this.$router.push('/listdetail')
    },
    toAltDetail() {
      this.$router.push({
        path: 'alertdetail',
        query: {
          id: this.artList.id
        }
      })
    }
  },
  mounted() {
    this.getNewsList(1)
    this.getNewsList(2)
    this.getNewsList(3)
    this.getNewsList(4)
    this.getNewsList(5)
    this.getMarket() // 获取大盘指数
    this.getBanner() //获取banner
    this.getArtList() // 获取公告
    // let header = document.querySelector('.header-box')
    let body = document.querySelector('.wrapper')
    // header.style.display = 'none'
    body.style.height = 'calc(100%)'
    body.style.paddingBottom = '0'
  }
}
</script>
<style lang="less" scoped>
@fontColor: #cfd0d1;
@fontColor2: #ccc;

.horseLampModule {
  height: 0.5rem;
  line-height: 0.5rem;
  padding: 0 0.3rem;
  background-color: #16171d;

  .horseLamp-box {
    position: relative;
    // padding-left: 0.3rem;
    // padding-right: .3rem;
    display: flex;
    align-items: center;

    .tzIco {
      width: .24rem;
      height: .24rem;
    }

    .wrap {
      flex: 1;
      margin-left: 0.2rem;
    }

    .right {
      width: 1.45rem;
    }
  }

  .iconfont {
    position: absolute;
    left: 0;
    vertical-align: middle;
    margin-right: 0.1rem;
  }

  .pull-right {
    width: 1.45rem;
  }

  .pull-right::before {
    content: '';
    height: 0.3rem;
    border-left: 0.01rem solid #666;
    padding-left: 0.15rem;
  }

  // 限制外框宽度，隐藏多余的部分
  .wrap {
    letter-spacing: 0.06rem;
    overflow: hidden;
  }
}

// 移动框宽度设置
#box {
  width: 80000%;
}

// 文字一行显示
#box div {
  float: left;
}

// 设置前后间隔
#marquee {
  margin: 0 16px 0 0;
}

// 获取宽度的节点，隐藏掉
#node {
  position: absolute;
  z-index: -999;
  top: -999999px;
}

.banner {
  width: 100%;
  height: 3.74rem;
  height: 4.2rem;
  overflow: hidden;

  .banner-box {
    width: 100%;
    height: 100%;
    position: relative;
    opacity: 0.86;

    .box {
      position: absolute;
      text-align: center;
      top: 30%;
      width: 100%;
      background: none;
    }

    .title {
      color: #ff9600;
      font-size: 0.46rem;
      // font-weight: 600;
      margin-bottom: 0.4rem;
      letter-spacing: 0.08rem;
    }

    .desc {
      font-size: 0.26rem;
      margin-bottom: 0.8rem;
    }

    .target-btn {
      display: inline-block;
      font-size: 0.22rem;
      color: #ff9600;
      padding: 0.1rem 0.3rem;
      border: 0.01rem solid #ff9600;
      border-radius: 0.5rem;
    }

    .img {
      width: 100%;
      height: 100%;
    }
  }

}

.tipstexts {
  // height: 0.91rem;
  height: 0.41rem;

  .horseLampModule {
    width: 80%;
    height: .91rem;
    margin: .24rem auto;
    margin-left: 0.16rem;
    padding: 0 .417rem;
    height: .43rem;
    border-radius: 0.72rem;
    position: relative;
    float: left;
  }

  .novice {
    float: right;
    height: 0.3rem;
    font-size: 0.25rem;
    margin-top: 0.2rem;
    display: block;
    color: #b63717;
    padding: .04rem .24rem;
    border-left: .027rem solid #989898
  }
}

.nav-bg {
  width: 100%;
  padding: 0 2%;
  height: 1.11rem;
  margin: 0 auto;
  padding-bottom: .14rem;
  overflow: hidden;

  // margin-bottom: 0.12rem;
  .img {
    width: 100%;
    height: 100%;
    display: block;
  }
}

.icon-router {
  .col-xs-3 {
    width: 25%;
  }
}

/*大盘指数*/

.dynamic-info {
  padding: .14rem;
  border-bottom: .02rem solid rgba(147, 147, 147, .2);
}

.dynamic-info li {
  float: left;
  font-size: .25rem;
  height: auto;
}

.dynamic-info li.tips {
  width: 15%;
}

.dynamic-info li.tips p {
  font-size: .22rem;
  text-align: center;
  width: .417rem;
  height: .625rem;
  color: #fff;
  padding-top: .04rem;
  background: url(../../assets/img/buyicon.png) no-repeat;
  background-size: contain;
}

.dynamic-info li p {
  font-size: .25rem;
}

.dynamic-name-code {
  width: 25%;
}

.dynamic-phone-win {
  width: 38%;
}

.dynamic-name-code p,
.dynamic-phone-win p {
  font-size: .22rem;
  text-align: center;
  padding-top: .18rem;
  color: @fontColor2;
}

.dynamic-name-code p:first-child,
.dynamic-phone-win p:first-child {
  font-size: .25rem;
  // color: rgb(34, 34, 34);
}

.btn-group {
  width: 22%;
  text-align: right;
  padding-top: .2rem;
  padding-right: .14rem;
}

.btn-group button {
  color: #fff;
  width: 1.279rem;
  font-size: .22rem;
  padding: .125rem .18rem;
  background: rgb(213, 0, 0);
  border: none;
}

.table-list .title {
  ul li {
    width: 33.33333333%;
    padding-right: 15px;
    padding-left: 15px;
  }
}

.account-box {
  position: relative;
  background-color: #000C16;
  padding-top: .2rem;

  .content {
    width: 6.86rem;
    height: 1.72rem;
    margin: 0 auto;
    padding: 0 0.14rem;
    display: flex;
    justify-content: space-between;

    .tab {
      width: 2.04rem;
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: space-between;
      padding: .25rem 0 .15rem;

      .name {
        width: 1.52rem;
        height: .4rem;
        line-height: .4rem;
        border: 1px solid rgba(255, 255, 255, .2);
        border-radius: .2rem;
        font-size: .24rem;
      }

      .price {
        font-size: .24rem;
      }

      .status {
        position: relative;
        width: 100%;
        display: flex;
        justify-content: space-between;
        padding: 0 .1rem;
        color: #fff8;

        &::before {
          display: block;
          position: absolute;
          content: '';
          width: 100%;
          height: 1px;
          left: 0%;
          top: -.1rem;
          background-color: #fff3;
        }
      }
    }
  }
}

.icon-img {
  width: .4rem;
  height: .4rem;
}

.icon-title {
  font-size: .24rem;
  font-weight: 400;
}

.icon-router.home-ico-router {
  padding: .4rem 0;
  background-color: #16171d !important;
}

.zhishu {
  // background-image: url(../../../static/img/bg-zhisu.png);
  background-size: cover;
}

.shadow-box {
  width: 100%;
  height: .4rem;
  background-color: #16171d;
  overflow: hidden;

  .shadow-ico {
    width: 100%;
    transform: translateY(-1px);
  }
}

.home-search {
  padding: 0 .3rem;
  height: .8rem;
  background-color: #000C16;
  display: flex;
  align-items: center;

  &-me {
    width: .6rem;
    height: .6rem;
    border-radius: .3rem;
    border: 1px solid #234B6E;
    display: flex;
    align-items: center;
    justify-content: center;

    >img {
      width: .27rem;
      height: .29rem;
    }
  }

  &-input {
    width: 5.4rem;
    height: .6rem;
    border-radius: .3rem;
    border: 1px solid #234B6E;
    margin-left: .27rem;
    padding: 0 .27rem;
    display: flex;
    align-items: center;

    >img {
      width: .26rem;
      height: .26rem;
      margin-right: .26rem;
    }

    >input {
      font-size: .24rem;

      &::-webkit-input-placeholder {
        color: #363636;
      }

      // color: 
    }
  }

  &-ctl {
    width: 1rem;
    display: flex;
    align-items: center;
    justify-content: flex-end;

    .lingdang {
      width: .25rem;
      height: .32rem;
    }

    .pifu {
      width: .34rem;
      height: .28rem;
      margin-left: .18rem;
    }
  }
}

.swiper-home {
  padding: 0.1rem .3rem;
  height: 2.67rem;
}

.mint-swipe-indicators {
  left: .4rem !important;
  bottom: .3rem !important;
}

.swiper-ad {
  padding: 0.1rem .3rem;
  height: 1.2rem;

  .banner-ad {
    display: block;
    border-radius: .1rem;
    overflow: hidden;
    height: 100%;
  }
}

.news-tab {
  min-height: 5rem;
  padding: 0 .3rem;
  margin-top: .1rem;

  /deep/.mint-tab-container {
    background-color: #1D1E29;
  }

  /deep/.mint-tab-item {
    background-color: #1D1E29;
  }

  /deep/.mint-tab-item-label {
    color: #fff;
    font-size: .26rem;

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

  .news-item {
    padding: .3rem;

    .news-title {
      position: relative;
      width: 100%;
      font-size: .3rem;
      line-height: .46rem;
      overflow: hidden;
      text-overflow: ellipsis;
      -webkit-line-clamp: 2;
      display: -webkit-box;
    }

    .news-status {
      display: block;
      margin-top: .1rem;
      font-size: .2rem;
      line-height: .36rem;
      color: #606167;
      margin-top: .1rem;
      padding-bottom: .25rem;
      border-bottom: .01rem solid #32333B;
    }
  }
}

#app.red-theme {
  .home-search {
    background-color: #C01815;

    &-me {
      border-color: #fff;
    }

    &-input {
      border-color: #fff;

      input::-webkit-input-placeholder {
        color: #fff8;
      }
    }
  }

  .zhishu {
    background-color: #c01815;
    // background-image: url(../../../static/img/bg-zhisu-red.png);
  }

  .icon-router.home-ico-router {
    background-color: #fff !important;

    .icon-title {
      color: #000;
    }
  }

  .shadow-box {
    background-color: #fff;

    .shadow-ico {
      transform: translateY(0);
      opacity: .5;
    }
  }

  .horseLampModule {
    background-color: #fff;

    .wrap {
      color: #000;
    }

    .right {
      color: #000;
    }
  }

  .swiper-home {
    background-color: #fff;
  }

  .news-tab {
    /deep/.mint-navbar {
      background: white;

      .mint-tab-item {
        background: white;

        .mint-tab-item-label {
          color: #000000;
        }

        &.is-selected {
          .mint-tab-item-label {
            color: #BB1815;

            .tab-name {
              &::after {
                background-color: #BB1815;
              }
            }
          }
        }
      }
    }

    .mint-tab-container {
      background: white;

      .news-title {
        color: #656565;
      }

      .news-status {
        border-color: #DFDFDF;
      }
    }
  }

  .account-box .content .tab .name {
    border-color: #E9E9E9;
    color: #222222;
  }

  .account-box .content .tab .price {
    color: #C21816;
  }

  .account-box .content .tab .status {
    color: #565656;
  }

  .account-box .content .tab .status::before {
    background-color: #565656;
    opacity: .3;
  }

  .account-box .content {
    position: relative;
  }

  .account-box .content::after {
    display: block;
    content: '';
    position: absolute;
    width: 100%;
    height: .4rem;
    bottom: -.4rem;
    left: 0;
    background-image: url(../../assets/ico/shadow.png);
    background-size: cover;
    opacity: .5;
  }
}
</style>
  