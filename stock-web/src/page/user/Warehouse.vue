<template>
    <div class="chicangcontainer">
        <div class="toubu">
            <div class="tl"></div>
            <a class="tr" @click="getsearch"></a>
        </div>
        <div class="dabokl">
            <div class="sert">
                <span v-for="(item, index) in chicangList" :key="index" @click="chicangidx = index"
                    :class="chicangidx == index ? 'sdpl' : ''">{{ item.name }}</span>
                <a :style="chicangidx == 0 ? 'left: 0.8rem;' : 'left: 4.54rem;'"></a>
            </div>
            <div class="bnm"></div>
        </div>
        <div class="chicanglistmain">
            
        <van-list v-model="loading" :finished="finished" :finished-text="$t('hj43')"
                v-show="chicangidx === 0" @load="onLoad" :immediate-check="immediate">
            <div class="cichang" v-for="(item,index) in tabsPositionNumArr" :key="item.buyOrderId">
                <div class="dingbu">
                    <div class="dl">
                        <h6>{{ item.stockName ? item.stockName : item.indexName }}</h6>
                        <p>
                            <span v-show="(item.stockGid.indexOf('sz')>=0)">深</span>
                            <span class="sh"  v-show="(item.stockGid.indexOf('sh')>=0)">沪</span>
                            <span class="bj"  v-show="(item.stockGid.indexOf('bj')>=0)">北</span>
                            <a class="">{{item.stockGid}}</a>
                        </p>
                    </div>
                    <div class="dr" v-if="chicangidx == 0" @click="getpingcang(item.positionSn)"> 平仓 </div>
                </div>
                <div class="fudo" v-if="chicangidx == 0">
                    <h6> 最新 <span class="red">{{ item.now_price }}</span></h6>
                    <p>{{ item.buyOrderTime | gettime }}</p>
                </div>
                <div class="fudo" v-else>
                    <h6> 卖出时间 </h6>
                    <p>2022-12-03 13:21</p>
                </div>
                <div class="fudong redbg">
                    <div class="kuij">
                        <p><span>浮动盈亏：</span><a class="red">{{item.profitAndLose}}</a></p>
                        <p><span>总盈亏：</span><a class="red">{{item.allProfitAndLose}}</a></p>
                    </div>
                </div>
                <div class="plkm">
                    <p><span>买入价格</span><a class="red">{{item.buyOrderPrice}}/股</a></p>
                    <p><span>数量</span><a class="red">{{item.orderNum}}</a></p>
                    <p><span>买入手续费</span><a class="red">{{item.orderFee}}</a></p>
                    <p><span>市值</span><a class="red">{{item.orderTotalPrice}}</a></p>
                    <p v-if="chicangidx == 1"><span>买入价格</span><a class="red">5.49/股</a></p>
                    <p v-if="chicangidx == 1"><span>留仓费</span><a class="red">49959.00</a></p>
                </div>
            </div>
        </van-list>
        <!-- 平仓 -->
        <van-list v-model="loadings" :finished="finisheds" :finished-text="$t('hj43')" @load="onLoads"
                :immediate-check="immediate" v-show="(chicangidx === 1)">
                <div class="cichang" v-for="(item,index) in tabsPcArr" :key="item.id">
                <div class="dingbu">
                    <div class="dl">
                        <h6>{{ item.stockName ? item.stockName : item.indexName }}</h6>
                        <p>
                            <span v-show="(item.stockGid.indexOf('sz')>=0)">深</span>
                            <span class="sh"  v-show="(item.stockGid.indexOf('sh')>=0)">沪</span>
                            <span class="bj"  v-show="(item.stockGid.indexOf('bj')>=0)">北</span>
                            <a class="">{{item.stockGid}}</a>
                        </p>
                    </div>
                    <div class="dr" v-if="chicangidx == 0" > 平仓 </div>
                </div>
                <div class="fudo" v-if="chicangidx == 0">
                    <h6> 最新 <span class="red">{{ item.now_price }}</span></h6>
                    <p>{{ item.buyOrderTime | gettime }}</p>
                </div>
                <div class="fudo" v-else>
                    <h6> 卖出时间 </h6>
                    <p>{{item.sellOrderTime | gettime}}</p>
                </div>
                <div class="fudong redbg">
                    <div class="kuij">
                        <p><span>浮动盈亏：</span><a class="red">{{item.profitAndLose}}</a></p>
                        <p><span>总盈亏：</span><a class="red">{{item.allProfitAndLose}}</a></p>
                    </div>
                </div>
                <div class="plkm">
                    <p><span>买入价格</span><a class="red">{{item.buyOrderPrice}}/股</a></p>
                    <p><span>数量</span><a class="red">{{item.orderNum}}</a></p>
                    <p><span>买入手续费</span><a class="red">{{item.orderFee}}</a></p>
                    <p><span>市值</span><a class="red">{{item.orderTotalPrice}}</a></p>
                    <p v-if="chicangidx == 1"><span>买入价格</span><a class="red">{{item.buyOrderPrice}}/股</a></p>
                    <p v-if="chicangidx == 1"><span>留仓费</span><a class="red">{{item.orderStayFee}}</a></p>
                </div>
            </div>
        </van-list>

        </div>
        <div style="height:2rem">

        </div>
    </div>
</template>
<script>
import { Toast } from 'vant';
import { MessageBox } from 'mint-ui'
import * as api from '@/axios/api'
export default {
    components: {

    },
    props: {},
    data() {
        return {
            headertab: 0,
            chicangList: [
                {
                    name: '我的持仓',
                },
                {
                    name: '我的平仓',
                }
            ],
            chicangidx: 0,
            loading: false,
            loadings: false,
            finished: false,
            finisheds: false,
            page:1,
            pages:1,
            immediate: false,
            tabsPositionNumArr: [],
            tabsPcArr: [],
        }
    },
    mounted() {
        this.getUserInfo()
        this.getListDetail()
        this.getListDetails()
    },
    methods: {
        onLoad() {
      //持仓
      this.page++;
      switch (this.chicangidx) {
        case 0:
          //沪深京持仓
          this.getListDetail();
          break;
          case 1:
            //指数持仓
        //   this.getzhishuListDetail();
          break;
          
        default:
          break;
      }
    },
    getsearch() {
            this.$router.push({
                path: '/Searchlist'
            })
        },
    onLoads() {
      //平仓
      this.pages++;
      //沪深京平仓
      this.getListDetails();
    },
        getpingcang(val) {
            // if (!this.$store.state.userInfo.idCard) {
            //     Toast(this.$t('hj138'))
            //     this.$router.push('/authentications')
            //     return
            // }
            if (this.chicangidx == 0) {
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
                        this.tabsPositionNumArr = []
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
                //this.total = data.data.total
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
            let data = await api.getUserInfo()
            if (data.status === 0) {
                this.$store.state.userInfo = data.data
            } else {
                Toast(data.msg)
            }
            this.$store.state.user = this.user
        },
    },
    filters:{
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
      return y + '-' + mm + '-' + d + ' ' + h + ":" + m + ":" + c;
    }
    }
}
</script>


<style lang="less" scoped>
.red {
    color: #d73d3d !important;
}

.green {
    color: #20b844 !important;
}

.redbg {
    background: rgba(115, 131, 251,0.3) !important;
}

.greenbg {
    background: #dcf7ee !important;
}

.chicangcontainer {}

.toubu {
    width: 100%;
    height: 1.60rem;
    background: linear-gradient(-55deg, rgb(80, 122, 250),rgb(115, 131, 251));
    display: flex;
    justify-content: space-between;

    .tr {
        width: 0.43rem;
        height: 0.43rem;
        background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAAXNSR0IArs4c6QAAA4dJREFUWEe9l0msn2MUxn8PYl4QolhIKhYS00JalAiCIIbeBg1FSrsTQxcsGrEo3WBR0pWZkjSGVEpMiSkiTRPTgkhKYiaGiilmHnna8/H1/r//4N6/+27u/X/vec953nPOc855xZBle39gAXAycCiwH7Ar8C3wIfAa8DTwhKSfh+mbvK9+B2wfCKwAFgI7jKD4G+BW4BZJP40gv0WkB4DtfLsaWAns0lL0MfAq8CnwA7AbcAhwHLBTS+4D4CJJr4wCYhsAtrcH7gQW12EDj9atNnYptL07MAEsBw4umd+ByyQ9MAzEPwDq5vcHfR36qG7y8jAl2be9HXANcGOF7M86v3bQ+TaAK4DbSjiuPk3S5lGMt2Vsnwo8DuwIJBfmSnq7n54tAGzPBiKUmCfWR0r66r8ab+Rtnw08VjmW0M2T9FeXvgbAmnJ9Yn6SpBenarwFYhVwVf1eKOmhTgC2ZwGfVNzWSQrnp71s7wG8D+TvBknz+gG4HFhdmydIemna1hsK2akLV9bP2ZJC0W2WbK8D5gOJ+SxJCcNYlu3jgeZCSyTd3QXgXeAgYL2kc8Zi+V8PJKl/BELRVZKWdQGIQKraakmh4liX7c+qf6yVdEEXgMblN0u6dqzWt1I8DeuAfh5ODqSJ7AncJWnp/wCg0b9G0iVdHngTOGIQVaYKyva+wOd1fqWk67oApPksAX4D9pKUnBjLsn0h8GApm5CU6thDw3OBh+vrpZLuHYv1rfF/LpUV+LUo/l0XgJ2r/u8NvAMcLumP6YKwPRdoWngnA2Kj6QWJzQ1l9HpJzf9TwmE7A0o6aka4sCzN7Y0uZQ2AzHhvAemK6eMLJK2fivWaC+5rzRUD2dWeB44Fnq8+npgtHWWiaYO0nXDeDlxc3zcBcyR93+8yk0eyRUCmopTOuC4MWS7p62HesH0UcAdwWMl+kXlR0nuDznYNpecD99TonbOhZagUpmxsaFquToU7BTivxvZGX/rLGZmGgHTbFZKe6ZsDkzdsJ3nSueZ0HPoSCEv26RjX47WAXxa3294AHF01Zr6kp3po2M89dcN4I/38mCEhSM6kyNwk6fVG1vbp9T3zYQpdD4i+D5NJyZVHyokV37yU8lD5pSae0O0FSXkp9SzbZwGPVHL3gBgJwLAEHLY/CMSMAAjADhDpDU/OGIACcWa9tJqcmJhRAB0gNs84gBaIFLxn/wb6PUsFkO1mDQAAAABJRU5ErkJggg==) no-repeat 50%;
        background-size: 100%;
        margin-right: 0.40rem;
        margin-top: 0.32rem;
    }
}

.dabokl {
    width: 100%;
    min-height: 1.07rem;
    border-radius: 0.4rem 0.4rem 0 0;
    margin-top: -0.4rem;
    background: #fff;

    .sert {
        width: 5.34rem;
        margin: 0 auto;
        display: flex;
        justify-content: space-between;
        position: relative;
        height: 1.07rem;

        span {
            text-align: center;
            color: #666;
            font-size: .4rem;
            font-weight: 550;
            line-height: 1.07rem;
        }

        .sdpl {
            color: #5d7dfb;
        }

        a {
            width: 0.53rem;
            height: 0.11rem;
            background: #5d7dfb;
            border-radius: 0.05rem;
            position: absolute;
            bottom: 0;
            margin-left: -0.266rem;
            transition: all .5s;
        }
    }

    .bnm {
        height: 0.03rem;
        width: 100%;
        background: #e0e0e0;
    }
}

.chicanglistmain {
    .cichang {
        width: 100%;
        border-bottom: 1px solid #e0e0e0;
        padding: 0.4rem 0;

        .dingbu {
            width: 9.35rem;
            margin: 0 auto;
            display: flex;
            justify-content: space-between;

            .dl {
                h6 {
                    color: #333;
                    font-size: .4rem;
                    font-weight: 500;
                }

                p {
                    color: #333;
                    font-size: .32rem;
                    margin-top: 0.13rem;

                    span {
                        width: 0.4rem;
                        height: 0.4rem;
                        background: #3b4fde;
                        border-radius: 0.05rem;
                        text-align: center;
                        line-height: .4rem;
                        color: #fff;
                        font-size: .3rem;
                        display: inline-block;
                    }

                    a {
                        display: inline-block;
                        height: 0.4rem;
                        line-height: .4rem;
                        padding: 0 0.11rem;
                        background: rgba(59, 79, 222, .1);
                        border-radius: 0.05rem;
                        color: #3b4fde;
                        font-size: .32rem;
                        vertical-align: middle;
                    }

                    .bj {
                        background: #ea6248;
                    }

                    .sh {
                        background: #aa3bde;
                    }

                    .shbg {
                        color: #aa3bde;
                        background: rgba(170, 59, 222, .1);
                    }

                    .bjbg {
                        color: #ea6248;
                        background: rgba(234, 98, 72, .1);
                    }
                }
            }

            .dr {
                width: 1.6rem;
                height: 0.67rem;
                background: linear-gradient(-55deg, #4d7aff, #6f7fff);
                border-radius: 0.35rem;
                margin-top: 0.13rem;
                text-align: center;
                color: #fff;
                font-size: .32rem;
                line-height: .67rem;
            }
        }

        .fudo {
            width: 9.35rem;
            margin: 0 auto;
            margin-top: 0.4rem;
            display: flex;
            justify-content: space-between;

            h6 {
                color: #333;
                font-size: .37rem;

                span {
                    font-weight: 600;
                }
            }

            p {
                color: #666;
                font-size: .32rem;
            }
        }

        .fudong {
            width: 100%;
            height: 0.8rem;
            margin: 0 auto;
            margin-top: 0.4rem;

            .kuij {
                width: 9.35rem;
                margin: 0 auto;
                display: flex;
                justify-content: space-between;

                p {
                    width: 47%;
                    line-height: .8rem;
                    display: flex;
                    justify-content: space-between;

                    span {
                        color: #666;
                        font-size: .32rem;
                    }

                    a {
                        font-size: .32rem;
                    }
                }
            }
        }

        .plkm {
            display: flex;
            justify-content: space-between;
            width: 9.35rem;
            margin: 0 auto;
            flex-wrap: wrap;

            p {
                width: 47%;
                margin-top: 0.4rem;
                display: flex;
                justify-content: space-between;

                span {
                    color: #666;
                    font-size: .32rem;
                }

                a {
                    font-size: .32rem;
                }
            }
        }
    }
}
</style>