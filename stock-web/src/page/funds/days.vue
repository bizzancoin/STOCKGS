<template>
    <div class="wrapper">
        <div class="days-box">
            <div class="days-box_tile">
                <span>保证金</span>
                <span
                style="font-size:.24rem;color:#fff8"
                :style="{color:$state.theme =='red'?'#222':''}"
                >（保证金介于 {{fundsSetting.marginMin}} - {{fundsSetting.marginMax}} 元之间）</span>
            </div>
            <div class="days-box_items">
                <div
                :class="['days-box_item', item.value == form.margin?'active':'']"
                v-for="(item, key) in bzjs"
                :key="key"
                @click="setBaozhen(item.value)"
                >{{item.label}}</div>
                <div
                :class="['days-box_item', zidinyi?'active':'']"
                @click="setBaozhen('')"
                >
                自定义
                </div>
                <div
                class="days-box_item"
                @click="setBaozhen('')"
                >
                <input v-show="zidinyi" type="text" v-model="form.margin">
                </div>
            </div>
        </div>
        <div class="days-box">
            <div class="days-box_tile">
                <span>配资资金</span>
                <span
                style="font-size:.24rem;color:#fff8"
                :style="{color:$state.theme =='red'?'#222':''}"
                >（配资资金为{{fundsAmount}}元）</span>
            </div>
            <div class="days-box_items beishu">
                <div 
                v-for="(item, index) in fundsTypeList" :key="item.id"
                :class="['days-box_item', selLever==item.lever?'active':'']"
                :style="{marginRight:(index+1)%4==0&&'0'}"
                @click="currentSel(item)"
                >
                    {{item.lever}}倍
                </div>
            </div>
        </div>

        <div class="days-box">
            <div class="days-box_tile">
                <span>操盘期限</span>
                <span
                style="font-size:.24rem;color:#fff8"
                :style="{color:$state.theme =='red'?'#222':''}"
                >（操盘期限介于 {{selMinDay}} - {{selMaxDay}}  天之间）</span>
            </div>
            <div class="days-box_items beishu">
                <div
                :class="['days-box_item']"
                >
                <input type="text" v-model="selDaysUseVal"
                @blur="currentSelDays"
                >
                </div>
                <span class="unit">天</span>
            </div>
        </div>
        <div class="notify-1">
            <span style="color:#fff"
                :style="{color:$state.theme =='red'?'#222':''}"
            
            >总操盘资金：</span>
            {{totalTradingAmount}} 元 = {{Number(form.margin).toFixed(0)}}元（保证金）+ {{fundsAmount}} 元（配资资金）
        </div>
        <div class="notify-1">
            <span style="color:#fff"
                :style="{color:$state.theme =='red'?'#222':''}"
            
            >需准备资金：</span>
             {{Number(Number(form.margin) + (fundsAmount * selManageRate * selDaysUseVal / 100)).toFixed(1)}}  元 = {{Number(form.margin).toFixed(0)}} 元（保证金）+ {{Number(fundsAmount * selManageRate * selDaysUseVal / 100).toFixed(1)}} 元 (一次性收取管理费)
        </div>
        <div class="days-box">
            <div class="guize">
                <span class="tile">预警线</span>
                <span class="mony">{{Number(Number(form.margin) + (form.margin * fundsSetting.daysWarning)).toFixed(0)}} 元</span>
                <span class="desc">（预警线 = 配资资金 + 保证金 X {{fundsSetting.daysWarning}}）</span>
            </div>
            <div class="guize">
                <span class="tile">平仓线</span>
                <span class="mony">{{Number(Number(form.margin) + (form.margin * fundsSetting.daysUnwind)).toFixed(0)}} 元</span>
                <span class="desc">（平仓线 = 配资资金 + 保证金 X {{fundsSetting.daysUnwind}}）</span>
            </div>
            <div class="guize">
                <span class="tile">管理费</span>
                <span class="mony">{{selManageRate}}% </span>
                <span class="desc">（{{fundsAmount}}X天利率{{selManageRate}}%X{{selDaysUseVal}}={{manageFee}}元）</span>
            </div>
            <div class="guize">
                <span class="tile">自定续费</span>
                <span class="desc">默认开启到期自动药期，配资管理费先付后用。</span>
            </div>
            <div class="guize">
                <span class="tile">操盘须知</span>
                <span class="desc">单只股票最大持仓比例为 {{selSingleHoldingRatio*100}}%。</span>
            </div>
        </div>

        <div class="days-submit"
        @click="Onsubmit()"
        >
            立即申请
        </div>

        <div class="days-notify2">
            <div class="warn">注意事项：</div>
            <div class="warn-detail">
                <div>1.保证金：您用于投资股票的资金，起点相当低。 </div>
                <div>
                 2.操盘期限；按天计算，不包含各类法定节假日。 
                </div>
                <div>3.按天支付管理费（不包含交交易印花税，过户费和佣金），无任何其他费用</div>
                <div>4.如操盘10天，一次性收取10天管理费</div>
                <div>5.交易日当天14：50之前的申请于当日生效（当天开始收取账户管理费），交易日当天14：50后的申请于下个交易日生效。</div>
                <div>6.股市有风险，投资需谨慎。</div>
            </div>
        </div>
    </div>
</template>

<script>
  import * as api from '../../axios/api'

export default {
    data() {
        return {
            bzjs:[
                { value:1000,label:'1000' },
                { value:1500,label:'1500' },
                { value:2000,label:'2000' },
                { value:3000,label:'3000' },
                { value:5000,label:'5000' },
                { value:8000,label:'8000' }, 
            ],
            baozhen:0,
            beishu:100,
            isloading: false,
            form: {
                margin: ''
            },
            selLever: 0,
            selManageRate: 0,
            selCycleType: '',
            selDaysUsePeriod: '',
            selDaysUseVal: '',
            selMinDay: '',
            selMaxDay: '',
            selSingleHoldingRatio: '',
            fundsSetting: {},
            fundsTypeList: [{}]
        }
    },
    methods:{
        setBaozhen(val) {
            this.form.margin = val
        },
            async getUserInfo () {
        // 获取用户信息
        let data = await api.getUserInfo()
        if (data.status === 0) {
          // 判断是否登录
          this.$store.state.userInfo = data.data
        } else {
        }
      },
      async getFundsSetting () {
        // 分仓配资设置信息查询
        let data = await api.getFundsSetting()
        if (data.status === 0) {
          this.fundsSetting = data.data
          this.form.margin = this.fundsSetting.marginMin
          this.selDaysUsePeriod = this.fundsSetting.daysUsePeriod.split('|')[0] + '天'
          this.selDaysUseVal = this.fundsSetting.daysUsePeriod.split('|')[0]
          this.selMinDay = this.fundsSetting.daysUsePeriod.split('|')[0]
          this.selMaxDay = this.fundsSetting.daysUsePeriod.split('|')[this.fundsSetting.daysUsePeriod.split('|').length - 1]
        } else {
        }
      },
      async getFundsTypeList () {
        // 查询配资类型杠杆
        let data = await api.getFundsTypeList({cycleType:1})
        if (data.status === 0) {
          this.fundsTypeList = data.data.list
          this.selLever = this.fundsTypeList[0].lever
          this.selManageRate = this.fundsTypeList[0].manageRate
          this.selCycleType = this.fundsTypeList[0].lever + '倍'
          this.selSingleHoldingRatio = this.fundsTypeList[0].singleHoldingRatio
        } else {
        }
      },
      getIntNumber () {
        if (this.form.margin >= this.fundsSetting.marginMax) {
          this.form.margin = this.fundsSetting.marginMax
        }
        this.form.margin = Math.floor(this.form.margin)
      },
      async Onsubmit () {
        // 融资转指数
        let opt = {
          userId: this.$store.state.userInfo.id,
          userName: this.$store.state.userInfo.realName,
          userPhone: this.$store.state.userInfo.phone,
          fundsType: 1, // 配资类型：1按天、2按周、3按月
          margin: this.form.margin, //保证金
          fundsAmount: this.fundsAmount, //配资金额
          lever: this.selLever, //杠杆
          totalTradingAmount: this.totalTradingAmount, //总操盘金额
          tradersCycle: this.selDaysUseVal, //操盘期限
          manageFee: this.manageFee //管理费
        }
        let data = await api.addFundsApply(opt)
        if (data.status === 0) {
          this.$message.success(data.msg)
        } else {
          this.$message.error(data.msg)
        }
      },
      currentSel(selVal) {
        this.selLever = selVal.lever
        this.selManageRate = selVal.manageRate
        this.selCycleType = selVal.lever + '倍'
        this.selSingleHoldingRatio = selVal.singleHoldingRatio
      },
      currentSelDays(selVal) {
        let val = parseInt(selVal.target.value)
        console.log(val)
        if (isNaN(val)) {
            this.selDaysUsePeriod = this.selMinDay + '天'
            this.selDaysUseVal = this.selMinDay
        } else {
            if (val< this.selMinDay) {
                this.selDaysUsePeriod = this.selMinDay + '天'
                this.selDaysUseVal = this.selMinDay
            } else if(val> this.selMaxDay) {
                this.selDaysUsePeriod = this.selMaxDay + '天'
                this.selDaysUseVal = this.selMaxDay
            } else {
                this.selDaysUsePeriod = val + '天'
                this.selDaysUseVal = val
            }
        }
        // this.selDaysUsePeriod = selVal + '天'
        // this.selDaysUseVal = selVal
      }
    },
    created() {
         this.getUserInfo()
        this.getFundsSetting()
        this.getFundsTypeList()
    },
    computed:{
        zidinyi() {
            return !this.bzjs.map(it => it.value).includes(this.form.margin)
        },
        fundsAmount () { //配资金额= 保证金*杠杆倍数
        if (this.form.margin) {
          return Number(this.form.margin * this.selLever).toFixed(0)
        } else {
          return 0
        }
      },
      totalTradingAmount () {//总操盘金额
        if (this.form.margin) {
          return Number(Number(this.form.margin) + (this.form.margin * this.selLever)).toFixed(0)
        } else {
          return 0
        }
      },
      manageFee () {//管理费
        if (this.selDaysUseVal) {
          return (this.fundsAmount * this.selManageRate * this.selDaysUseVal / 100).toFixed(1)
        } else {
          return 0
        }
      }
    }
}
</script>

<style lang="less" scoped>
.wrapper {
    padding-bottom: 0;
    .days-box {
        display: block;
        width: 6.9rem;
        margin:.3rem;
        padding: .6rem .2rem .12rem .2rem;
        background-color: #1F2636;
        border-radius: .1rem;
        &_tile {
            padding: .12rem 0 .12rem .25rem;
            position: relative;
            font-size: .32rem;
            display: flex;
            justify-content: space-between;
            font-weight: bold;
            border-bottom: 1px solid #fff2;
            &:after {
                content: "";
                position: absolute;
                left: 0;
                top: 50%;
                width: .1rem;
                height: .1rem;
                margin-top: -.05rem;
                background: #138EB4;
                border-radius: 50%;
            }
        }
        &_items {
            display: flex;
            justify-content: space-between;
            padding-top: .25rem;
            flex-wrap: wrap;
            padding-bottom: .15rem;
            &.beishu {
                justify-content: flex-start;
                .days-box_item {
                    margin-right: .15rem;
                }
            }
            span.unit {
            line-height: .44rem;
            }
        }
        &_item {
            width: 1.5rem;
            height: .44rem;
            line-height: .44rem;
            border-radius: 3px;
            background-color: #2D2E3B;
            color: #fff;
            font-size: .24rem;
            margin-bottom: .2rem;
            text-align: center;
            padding: 0 .1rem;
            input {
                text-align: center;
                width: 100%;
            }
            &.active {
                background-color: #E00101;
            }
        }
        .guize {
            margin-bottom: .24rem;
            display: flex;
            align-items: center;
            .tile {
                width: 1.5rem ;
                height: .4rem;
                font-size: .22rem;
                line-height: .4rem;
                text-align: center;
                background-color: #138EB4;
                color: #fff;
                padding: 0.02rem 0rem;
                margin-right: .1rem;
                display: block;
            }
            .mony{
                color: #fff;
                font-size: .22rem;
            }
            .desc {
                color :#A9AAAE ;
                font-size: .22rem;
            }
        }
    }
    .notify-1 {
        padding: .2rem .3rem;
        font-size: .24rem;
        letter-spacing: 0;
        color: #999;
    }
    .days-submit {
        background-color: #024DA1;
        width: 6.9rem;
        height: .66rem;
        line-height: .66rem;
        margin: 0 auto;
        color: #fff;
        border-radius: .33rem;
        text-align: center;
    }
    .days-notify2 {
        margin-top: .8rem;
        padding: 0 .3rem .3rem;
        .warn {
            font-size: .36rem;
            font-weight: bold;
            margin-bottom: .2rem;

        }
        .warn-detail{
            color: #8D8F94;
            div {
                margin-bottom: .2rem;
            }
        }
    }
}
.red-theme {
    .days-box{
        background-color: #fff;
    }
    .wrapper .days-box_tile {
        color: #000;
        border-bottom-color: #E9E9E9;
    }
    .wrapper .days-box_tile:after {
        background:#000;
    }
    .wrapper .days-box_items span.unit{
        color: #222;
    }
    .wrapper .days-box_item{
        background-color: #D9D9D9;
        border: 1px solid #AEAEAE;
        color: #222;
    }
    .wrapper .days-box_item.active {
        background-color: #E00101;
        border: 1px solid #E00101;
        color: #fff;
    }
    .wrapper .days-box .guize .tile {
        background-color: #E00101;
    }
    .wrapper .days-box .guize .mony {
        color: #222;
    }
    .wrapper .days-box .guize .desc {
        color: #222;
    }
    .wrapper .days-submit {
        background-color: #E00101;
    }
    .wrapper .days-notify2 .warn {
        color: #000;
    }
}
</style>