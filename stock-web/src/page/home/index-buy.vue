<template>
  <div class="wrapper"> 
    <div class="detail-part">
      <div class="index-name">
        <p>{{detail.indexName}} <span class="index-name_code">{{detail.indexCode}}</span></p>
      </div>
      <div class="clearfix">
        <div class="pull-left hangqin-left col-xs-4">
          <p :class="detail.floatPoint>0?'price red':detail.floatPoint<0?'green price':'price'">
            {{Number(detail.currentPoint).toFixed(2)}}</p>
          <p :class="detail.floatPoint>0?'gain red':detail.floatPoint<0?'green gain':'gain'">
            <span>{{Number(detail.floatPoint).toFixed(2)}}</span>
            <span style="margin-left: .1rem;">{{Number(detail.floatRate).toFixed(2)}}%</span>
          </p>
        </div>
        <div class="pull-right hangqin-right col-xs-8">
          <ul class="price-detail text-center">
            <li>
              <!-- <p class="title"></p> -->
              <p :class="detail.floatPoint<0?'number green': 'number red'">
                <span class="title">涨跌</span>
                {{Number(detail.floatPoint).toFixed(2)}} 
              </p>
            </li>
            <li>
              <p class="number red">
                <span class="title red">涨停限制</span>
                {{(Number(detail.currentPoint) * settingInfo.riseLimit + Number(detail.currentPoint)).toFixed(2)}}
              </p>
            </li>
            <li>
              <p :class="detail.floatRate<0?'number green': 'number red'">
                <span class="title">涨幅</span>
                {{Number(detail.floatRate).toFixed(2)}}%
              </p>
            </li>
            <li>
              <!-- <p class="title">最低</p> -->
              <p class="green">
                <span class="title green">跌停限制</span>
                {{(detail.currentPoint - Number(detail.currentPoint) * settingInfo.downLimit ).toFixed(2)}}
              </p>
            </li>
          </ul>
        </div>
      </div>
      <!-- <div class="clearfix">
          <div class="col-xs-4 red">涨停限制 </div>
          <div class="col-xs-4 green">跌停限制 </div>
      </div> -->
    </div>
    <div v-if="false" class="box-tab">
      <div class="tab-title">
        <span class="circle"></span>股票详情
      </div>
      <div class="tab-con">
        <ul class="first clearfix">
          <li class="pull-left">
            {{detail.indexName}}
          </li>
          <li :class="detail.floatPoint < 0?'pull-left green':detail.floatPoint == 0?'pull-left':'pull-left red'">
            当前价：
            <span>{{Number(detail.currentPoint).toFixed(2)}}</span>
          </li>
        </ul>
        <ul class="first clearfix">
          <li class="pull-left">
            {{detail.indexCode}}
            <!-- <span>(Scrb1905)</span> -->
          </li>
          <li :class="detail.floatRate < 0?'pull-left green':detail.floatRate == 0?'pull-left':'pull-left red'">
            <!-- 当前价： -->
            <span>{{detail.floatRate}}%</span>
          </li>
        </ul>
        <!-- <div class='buy-price clearfix'>
            <div class="col-xs-4">
                <p>市价买入</p>
            </div>
            <div class="col-xs-4">
                <p class="red">{{detail.nowPrice}}</p>
            </div>
            <div class="col-xs-4">
                <p>说明</p>
            </div>
        </div> -->
      </div>
    </div>
    <div class="box-tab">
      <div class="tab-title">
        <span class="circle"></span>选择手数
        <span class="notify">最小购买股数1手，最大可购买数量100手  </span>
      </div>
      <div class="tab-con">
        <ul class="radio-group clearfix">
          <li v-for="item in numberList" :key="item.key"
          @click="selectNumberFun(item.value)">
            <div :class="[selectNumber == item.value?'on':'']">
              {{item.label}}
            </div>
          </li>
          <li v-show="!selectNumber">
            <input @keyup="changeAutoNumber" v-model="autoNumber" type="text">手
          </li>
        </ul>
        <p class="clearfix">
          <span class="pull-left">最小购买股数{{Number(detail.minNum)}}手</span>
          <span class="protem pull-right">最大可购买数量{{Number(detail.maxNum)}}手</span>
        </p>
      </div>
    </div>
    <div class="box-tab">
      <div class="tab-title">
        <span class="circle"></span>买卖方向
        <span class="notify">最大购买金额:{{settingInfo.buyMaxPercent * $store.state.userInfo.enableIndexAmt}}</span>
      </div>
      <div class="tab-con">
        <ul class="radio-group clearfix">
          <li v-for="item in type" :key="item.key" @click="selectTypeFun(item.value)">
            <div :class="selectType == item.value?'on':''">
              {{item.label}}
            </div>
          </li>
        </ul>
      </div>
    </div>
    <div class="box-tab">
      <div class="tab-title">
        <span class="circle"></span>选择杠杆
      </div>
      <div class="tab-con">
        <ul class="radio-group clearfix">
          <li v-for="item in siteLeverList" :key="item.key" @click="selectCycleFun(item.value)">
            <div :class="selectCycle == item.value?'on':''">
              {{item.label}}
            </div>
          </li>
        </ul>
      </div>
    </div>
    <!-- <div class="box-tab">
      <div class="tab-con">
        <!-- <p class="text-left page-part">
            <span class="">{{selectNumber?selectNumber*100:autoNumber*100}}股</span>
            <span class="pull-right">买入金额:{{price?price:0}}元</span>
        </p> -->
        <!-- <p class="clearfix">
          <!-- <span class="pull-right">最小购买金额{{settingInfo.buyMinAmt}}元</span> -->
          <!-- <span class="pull-right">最大购买金额:{{settingInfo.buyMaxPercent * $store.state.userInfo.enableIndexAmt}}</span>
        </p>

      </div>
    </div> --> 
    <div class="agree">
      <p style="line-height: 0.4rem;padding: 0 0.2rem;">
        当该指数涨幅达到<span class="red">涨停限制</span>时,不能买涨；达到<span class="green">跌停限制</span>时，不能买跌.
      </p>
      <!-- <p>
          <i @click="isAgree" :class="agree?'glyphicon glyphicon glyphicon-ok-sign red':'glyphicon glyphicon-ok-circle'"></i>
          我已阅读并同意
          <a @click="totrageUrl" >《指数交易交易⻛险揭示书》</a>
      </p> -->
    </div>
    <div class="footer-btn">
      <div class="total">
        <p class="pay">支付保证金<span class="protem">{{total?total:0}}</span></p>
        <p class="account">(账户余额:{{$store.state.userInfo.enableIndexAmt}}元)</p>
      </div>
      <!-- <mt-button :disabled="buying" class="btn-red" size="small" type="danger" @click="toInquiry">下单</mt-button> -->
      <div class="right-btn">
        <div class="btn-buy" @click="toInquiry">
          <img src="../../assets/ico/hangqing-btn.png" alt="" srcset="">
          下单
        </div>
      </div>
    </div>
   
    <!-- <foot></foot> -->
  </div>
</template>

<script>
import foot from '../../components/foot/foot'
import { Toast } from 'mint-ui'
import { isNull } from '@/utils/utils'
import * as api from '@/axios/api'

export default {
  components: {
    foot
  },
  props: {},
  data () {
    return {
      detail: {
        currentPoint: '2852.9948',
        depositAmt: 10000,
        eachPoint: 300,
        floatPoint: '0.4795',
        floatRate: '0.02',
        id: 1,
        indexCode: '000001',
        indexGid: 'sh000001',
        indexName: '上证指数',
        listShow: 1,
        maxNum: 100,
        minNum: 1,
        transFee: 200,
        transState: 0
      }, //
      cycle: [ // 杠杆倍数
        { label: '10', value: '10' },
        { label: '20', value: '20' },
        { label: '30', value: '30' }
      ],
      selectCycle: '20',
      numberList: [
        { label: '1手', value: '1' },
        { label: '20手', value: '20' },
        { label: '30手', value: '30' },
        { label: '50手', value: '50' },
        { label: '80手', value: '80' },
        { label: '100手', value: '100' },
        { label: '自定义', value: '' }
      ],
      selectNumber: '',
      autoNumber: '',
      type: [
        { label: '买涨', value: '0' },
        { label: '买跌', value: '1' }
      ],
      selectType: '',
      // number:0,// 股
      // price:0,// 股价格
      // total:0, // 总价
      agree: true,
      settingInfo: {
        buyMaxNum: 1000, // 最大买入股数
        buyMinNum: 100 // 最小买入股数
      }, // 设置规则信息
      dialogShow: false,
      timer: null,
      buying: false, // 点击下单
      siteLeverList:[]
    }
  },
  watch: {},
  computed: {
    total () {
      if (this.autoNumber) {
        return (this.detail.depositAmt * this.autoNumber  / this.selectCycle).toFixed(2)
      } else if (this.selectNumber) {
        return (this.detail.depositAmt * this.selectNumber / this.selectCycle).toFixed(2)
      } else {
        return 0
      }
      // 需支付总价 = 每手保证金 * 股（1手 = 100股）
    },
    price () {
      if (this.autoNumber) {
        return (this.detail.eachPoint * this.autoNumber * 100).toFixed(2)
      } else if (this.selectNumber) {
        return (this.detail.eachPoint * this.selectNumber * 100).toFixed(2)
      } else {
        return 0
      }
      // 买入金额 = 每股价格 * 股（1手 = 100股）
    }
  },
  created () {
    // this.timer = setInterval(this.getDetail, 5000)
  },
  beforeDestroy () {
    clearInterval(this.timer)
  },
  mounted () {
    // this.getDetail()
    this.selectNumber = 0
    this.getSettingIndexInfo()
    this.getSettingInfo()
    if (!this.$store.state.userInfo.enableAmt) {
      this.getUserInfo()
    }
    this.detail = this.$route.query.info
  },
  methods: {
    changeAutoNumber () {
      // 自定义手数
      this.selectNumber = ''
    },
    async getSettingIndexInfo () {
      // 网站设置信息 指数
      let data = await api.getIndexSetting()
      if (data.status === 0) {
        // 成功
        this.settingInfo = data.data
        
      } else {
        Toast(data.msg)
      }
    },
    async getSettingInfo () {
      // 网站设置信息
      let data = await api.getSetting()
      if (data.status === 0) {
        // 成功
        // 杠杆倍数
        this.selectCycle = data.data.siteLever
        // console.log(this.$store.state.userInfo)
        // alert(this.$store.state.userInfo +"=="+this.$store.state.userInfo.phone)
        if(this.$store.state.userInfo !== undefined && this.$store.state.userInfo !== null && this.$store.state.userInfo.phone !== '' && this.$store.state.userInfo.siteLever !== null){
            this.selectCycle = this.$store.state.userInfo.siteLever.split('/')[0]
            this.siteLeverList = []
            for (let i = 0; i < this.$store.state.userInfo.siteLever.split('/').length; i++) {
              let val = this.$store.state.userInfo.siteLever.split('/')[i]
              let item = { label: val + '倍', value: val }
              this.siteLeverList.push(item)
            }
          } else {
            this.selectCycle = data.data.siteLever.split('/')[0]
            this.siteLeverList = []
            for (let i = 0; i < data.data.siteLever.split('/').length; i++) {
              let val = data.data.siteLever.split('/')[i]
              let item = { label: val + '倍', value: val }
              this.siteLeverList.push(item)
            }
          }
      } else {
        Toast(data.msg)
      }
    },
    isAgree () {
      let i = false
      let j = true
      this.agree = this.agree ? i : j
    },
    totrageUrl () {
      this.$router.push('/trade')
    },
    async getDetail () {
      let opts = {
        indexCode: this.$route.query.info ? this.$route.query.info.indexGid : ''
      }
      let data = await api.getSingleIndex(opts)
      if (data.status === 0) {
        this.detail = data.data
      } else {
        Toast(data.msg)
      }
    },
    selectCycleFun (value) {
      this.selectCycle = value
    },
    selectNumberFun (value) {
      
      this.selectNumber = value
      if (value !== 0) {
        this.autoNumber = ''
      }
      console.log(value, this.selectNumber)
    },
    selectTypeFun (value) {
      this.selectType = value
    },
    canBuyStatus () {
      let dataTime = new Date()
      let day = dataTime.getDay() // 星期几
      let hour = dataTime.getHours() // 小时
      let minute = dataTime.getMinutes() // 分钟
      if (day === 6 || day === 7) {
        return false
      }
      if (hour < 9 || (hour >= 12 && hour < 13) || hour >= 15) {
        return false
      }
      if (hour === 9 && minute < 32) {
        return false
      }
      if (hour === 11 && minute >= 30) {
        return false
      }
      if (hour === 14 && minute > 57) {
        return false
      }
      return true
    },
    async toInquiry () {
      // 判断在不在开盘时间内
      // if(!this.canBuyStatus()){
      //     Toast('不在交易时段内！')
      //     return
      // }
      // 下单

      if (!this.$store.state.userInfo.idCard) {
        Toast('您还未实名认证,请先实名认证了再下单')
        this.$router.push('/authentication')
        return
      }
      if (!this.agree) {
        Toast('需同意合作协议才能交易!')
      } else if (isNull(this.selectNumber) && isNull(this.autoNumber)) {
        Toast('请选择购买手数')
      } else if (isNull(this.selectType)) {
        Toast('请选择买卖方向')
      } else {
        this.buying = true
        let opts = {
          indexId: this.detail.id,
          buyNum: this.selectNumber ? this.selectNumber : this.autoNumber, // 单位为手
          buyType: this.selectType,
          lever: this.selectCycle ? this.selectCycle : 0
        }
        let data = await api.indexBuy(opts)
        this.buying = false
        if (data.status === 0) {
          Toast(data.data)
          this.getUserInfo()
          this.$router.push('/orderlist?index=2')
        } else {
          Toast(data.msg)
        }
      }
    },
    toDetail () {
      this.$router.push('/listdetail')
    },
    goBack () {
      this.$router.go(-1)
    },
    async getUserInfo () {
      // 获取用户信息
      let data = await api.getUserInfo()
      if (data.status === 0) {
        // Toast(data.msg)
        this.$store.state.userInfo = data.data
      } else {
        Toast(data.msg)
      }
    }
  }
}
</script>

<style lang="less" scoped>
  body {
    background: #fff;
  }
  .wrapper {
    background-color: #16171d;
  }
  .protem {
    color: #ff8000;
  }

  .agree {
    margin-top: 0.2rem;
    padding-bottom: 1rem;
    a {
      color: #428bca;
    }
  }

  .footer-btn {
    position: fixed;
    z-index: 1;
    width: 100%;
    padding-right: 0;
    bottom: 0rem;
    height: 1.32rem;
    line-height: 1.32rem;
    display: flex;

    .total {
      font-size: 0.26rem;
      padding-left: 0.3rem;
      flex: 3;
      display: flex;
      flex-direction: column;
      justify-content: center;
      .pay {
        line-height: 0.45rem;
        font-size: .32rem;
        .protem {
          margin-left: .1rem;
        }
      }

      .account {
        line-height: 0.3rem;
        font-size: 0.24rem;
        color: #999;
      }
    }

    .right-btn{
      flex: 2;
      display: flex;
      flex-direction: column;
      justify-content: center;
      .btn-buy {
        width: 2.4rem;
        height: .76rem;
        border-radius: .38rem;
        background-color: #7266BA;
        line-height: .76rem;
        text-align: center;
        font-size: .28rem;
        img{
          width: .26rem;
          height: .26rem;
          margin-right: .1rem;
        }
      }
    }

    .btn-red {
      flex: 2;
      border-radius: 0;
      padding: 0;
    }
  }

  .auto {
    input {
      display: inline-block;
      width: 75%;
      border-bottom: 0.01rem solid #ddd;
    }
  }

  // bottom 7rem -> 0.97rem
  .buy-price {
    // border-top: 0.01rem solid #000000;
    padding-top: 0.15rem;

    p {
      height: 0.32rem;
      line-height: 0.32rem;
    }
  }

  .mint-popup-white {
    height: 6.5rem;
    padding: 0.25rem;

    .check-box {
      height: 5.3rem;
      line-height: 0.35rem;
      overflow: auto;

      h3 {
        margin-bottom: 0.2rem;
      }
    }

    .box-btn {
      width: 100%;
      padding-top: 0.2rem;

      .btn-red {
        width: 100%;
        height: 0.6rem;
      }
    }
  }

  .detail-part {
    .index-name {
      font-size: 0.32rem;
      padding: 0.3rem;

      span.index-name_code {
        font-size: 0.22rem;
        color: #fff;
        margin-left: 0.2rem;
        background-image: url(../../assets/ico/zhishu.png);
        background-size: 100% 100%;
        padding:0.02rem .05rem 0.02rem 0.15rem;
      }
    }

    .price {
      font-size: 0.5rem;
      padding-bottom: 0.1rem;
    }

    .gain {
      font-size: 0.24rem;
    }

    .title {
      color: #999;
      line-height: 0.36rem;
      padding-right: 0.1rem;
      font-size: .24rem;
      &.red {
        color: #b60c0d;
      }

      &.green {
        color: #31b97e;
      }
    }

    .number {
      line-height: 0.36rem;
    }

    .price-detail {
      li {
        width: 60%;
        float: left;
        margin-bottom: 0.15rem;
        margin-top: 0.15rem;
        text-align: right;
        div {
          background-color: #2D2E3B;
        }
        &:nth-child(odd) {
          width: 40%;
          text-align: left;
        }
      }
    }

    .detail-list {
      padding-left: 0.3rem;
    }
  }
  .hangqin-left {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    padding: 0 0.1rem 0 .3rem;
    position: relative;
    .price {
      padding-bottom: .35rem;
      font-family: lightnumber;
    }
    &:after {
      display: block;
      position: absolute;
      content: '';
      width: 2px;
      height: .77rem;
      background-color: #2E2F34;
      top: 0.2rem;
      right: 0;
    }
  }
  .hangqin-right{
    padding: 0 .3rem 0 0.1rem;
  }
  .box-tab {
    margin :0.15rem .3rem;
    width: 6.9rem;
    background-color: #1B1C25;
    border-bottom: none;
    border-radius: .1rem;
    .tab-title {
      margin-bottom: 0;
      margin-top: .14rem;
      border-bottom: 1px solid #32333B;
      font-size: .32rem;
      font-weight: bold;
      padding-top: .12rem;
      padding-bottom: .12rem;
      height: auto;
      .notify {
        font-size: .24rem;
        color: #fff8;
      }
      &:after {
        background: #138EB4;
      }
    }
  }
  .radio-group li div {
    background-color: #2D2E3B;
    border-radius: .03rem;
    border: none;
  }
  .radio-group li div.on {
    background-color: #E00101;
  }
  .radio-group li input {
    background-color: #2D2E3B;
    border-radius: .03rem;
    border: none;
    width: 1rem;
    margin-right: .15rem;
    padding: 0 0.2rem;
  }
  .agree {
    font-size: .24rem;
    padding-bottom: 1.32rem;
  }
  .red-theme {
    .wrapper {
      background-color: #E9E9E9;
    }
    .detail-part .index-name {
      color: #222222;
    }
    .detail-part .title {
      color: #222;
    }
    .hangqin-left::after {
      background-color: transparent;
    }
    .box-tab {
      background-color: #fff;
      .tab-title{
        color: #000;
        border-bottom-color: #E9E9E9;
        .notify{
          color: #0008;
        }
      }
    }
    .detail-part .index-name span.index-name_code {
      background-image:url(../../assets/ico/zhishu-red.png)
    }
    .radio-group li {
      color: #000;
    }
    .radio-group li div {
      background-color: #D9D9D9;
      color: #000;
      border: 1px solid #AEAEAE;
    }
    .radio-group li div.on{
      background-color: #E00202;
      border: 1px solid #E00202;
      color: #fff;
    }
    .radio-group li input {
      background-color: #D9D9D9;
    }
    .agree{
      color: #000000;
    }
    .footer-btn{
      background-color: #E0E0E0;
    }
    .footer-btn .total .pay {
      color: #000;
      .protem{
        color: #E00202;
        font-family: lightnumber;
      }
    }
    .footer-btn .right-btn .btn-buy {
      background-color: #E00202;
      color: #fff;
    }
  }
</style>
