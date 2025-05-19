<template>
    <div>
        <div class="dabg">
            <div class="gebgh">
                <div>
                    <h2><span class="hbnh" @click="$router.go(-1)"><a class="fan"></a></span> 龙虎榜</h2>
                </div>
            </div>
            <ul class="ulk">
                <div class="ulto"><span class="spl1">名称</span><span class="spl2">上榜日</span><span class="spl3">解读</span>
                </div>
                <li class="lis" v-for="(item, index) in longhuList" :key="index">
                    <div class="lisl">
                        <h6>{{ item.SECURITY_NAME_ABBR }}</h6>
                        <p>{{ item.SECUCODE }}</p>
                    </div>
                    <div class="lisc">
                        <p>{{ item.TRADE_DATE }}</p>
                    </div>
                    <div class="lisr"><a @click="Currentitem = item; lhshow = true">详情</a></div>
                </li>
            </ul>
            <van-popup v-model="lhshow" round>
                <div class="dbox">
                    <div class="hezi">
                        <div class="dange">
                            <p>总成金额</p>
                            <h6>{{ Currentitem.ACCUM_AMOUNT | toWanYi }}</h6>
                        </div>
                        <div class="dange">
                            <p>换手率</p>
                            <h6>{{ Currentitem.TURNOVERRATE ? (Currentitem.TURNOVERRATE).toFixed(2) : 0.00 }}</h6>
                        </div>
                        <div class="dange alk">
                            <p>涨跌幅</p>
                            <h6>{{ Currentitem.CHANGE_RATE ? (Currentitem.CHANGE_RATE).toFixed(2) : 0.00 }}</h6>
                        </div>
                        <div class="dange alk">
                            <p>当日流通市值</p>
                            <h6>{{ Currentitem.FREE_MARKET_CAP | toWanYi }}</h6>
                        </div>
                        <div class="dange alk">
                            <p>卖出额</p>
                            <h6>{{ Currentitem.BILLBOARD_SELL_AMT | toWanYi }}</h6>
                        </div>
                        <div class="dange alk">
                            <p>买入额</p>
                            <h6>{{ Currentitem.BILLBOARD_BUY_AMT | toWanYi }}</h6>
                        </div>
                        <div class="dange alk">
                            <p>成交额</p>
                            <h6>{{ Currentitem.BILLBOARD_DEAL_AMT | toWanYi }}</h6>
                        </div>
                        <div class="dange alk">
                            <p>净买入额</p>
                            <h6>{{ Currentitem.BILLBOARD_NET_AMT | toWanYi }}</h6>
                        </div>
                    </div>
                    <p class="liyouj">上榜理由：日换手率达到20%的前5只证券</p>
                </div>
            </van-popup>
        </div>
    </div>
</template>
<script>
import { Toast } from 'vant';
import { MessageBox } from 'mint-ui'
import * as api from '@/axios/api'
import { getCurrentInstance } from 'vue';
export default {
    props: {},
    data() {
        return {
            lhshow: false,
            longhuList: [],
            Currentitem: ''
        }
    },
    mounted() {
        this.stockgetlhb()
    },
    filters: {
        // 数字转万亿
        toWanYi(num) {
            if (num > 1000000000000) {
                return (num / 1000000000000).toFixed(2) + '万亿'
            } else if (num > 100000000) {
                return (num / 100000000).toFixed(2) + '亿'
            } else if (num > 10000) {
                return (num / 10000).toFixed(2) + '万'
            } if (num < -1000000000000) {
                return (num / 1000000000000).toFixed(2) + '万亿'
            } else if (num < -100000000) {
                return (num / 100000000).toFixed(2) + '亿'
            } else if (num < -10000) {
                return (num / 10000).toFixed(2) + '万'
            } else {
                return num
            }
        }
    },
    methods: {
        async stockgetlhb() {
            let data = await api.stockgetlhb()
            if (data.status === 0) {
                this.longhuList = data.data
            } else {
                Toast(data.msg)
            }
            this.$store.state.user = this.user
        },
    }
}
</script>


<style lang="less" scoped>
.dabg {
    // position: fixed;
    top: 0;
    left: 0;
    bottom: 0;
    right: 0;
    // overflow: hidden;
    // overflow-y: scroll;
    background: #fff;

    .gebgh {
        height: 2.4rem;
        background: linear-gradient(-55deg, rgb(80, 122, 250),rgb(115, 131, 251));

        h2 {
            text-align: center;
            height: 1.25rem;
            width: 100%;
            position: relative;
            line-height: 1.25rem;
            font-size: .48rem;
            color: #fff;
            background: transparent;
            font-weight: 500;
            z-index: 3;

            .hbnh {
                position: absolute;
                left: 0.4rem;
                font-size: .43rem;
                font-weight: 500;

                .fan {
                    width: 0.24rem;
                    height: 0.43rem;
                    background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABIAAAAgCAYAAAAffCjxAAAAAXNSR0IArs4c6QAAAVdJREFUSEut1usqBVEYxvH/c1MuRJJDkhwTEpIkJUlyCEk++CJJckpycw49Gs3Wa+y9Z82ePd/Xr5n38KwRNR7bE0APMK1OHdtTwAWQGZcdQbZngPMc+QImK0O2Z4GzgIxLuqoE2Z4DTgMyJuk6K08yZHseOAnIqKSbRo2TINsLwHFARiTdxkaVQrYXgaOADEu6K3a7LWR7CTjMD2XdaYq0rZHtFWA/Rz6BIUn3reau6RvZXgX28kMfOfLQbnj/QbbXgN380HuOPJZtwB/I9jqwE5ABSc9lyJ8a2d4AtgPSL+klBfmFbG8CWwHpk/SaivxAtjMgg7Inq0mvpLcqSHehTOvKpzU+oSvFDlj99gesOJCDkp7Kip+yIknT3XL7C0tbum9lMbIMHKQkQEqwJWVSKZTPWTElq0dt6GYxt6uHf8DqX0cBq39BBqz+lR2w+j8RAfv9rfkGqF24CUdT9E4AAAAASUVORK5CYII=) no-repeat 50%;
                    background-size: 100%;
                    display: inline-block;
                    margin-right: 0.13rem;
                    vertical-align: middle;
                    margin-top: -0.05rem;
                }
            }
        }
    }

    .ulk {
        background: #fff;
        width: 100%;
        margin-top: -0.96rem;
        border-radius: 0.4rem 0.4rem 0 0;

        .ulto {
            height: 1.01rem;
            border-bottom: 0.03rem solid #e0e0e0;
            display: flex;
            justify-content: space-between;

            .spl1 {
                width: 30%;
                line-height: 1.01rem;
                color: #666;
                font-size: .37rem;
                margin-left: 0.35rem;
            }

            .spl2 {
                text-align: center;
                width: 30%;
                line-height: 1.01rem;
                color: #666;
                font-size: .37rem;
            }

            .spl3 {
                margin-right: 0.35rem;
                text-align: right;
                width: 30%;
                line-height: 1.01rem;
                color: #666;
                font-size: .37rem;
            }
        }

        .lis {
            border-bottom: 0.0266rem solid #e0e0e0;
            height: 1.68rem;
            display: flex;
            justify-content: space-between;

            .lisl {
                margin-left: 0.35rem;

                h6 {
                    color: #1a1a1a;
                    font-size: .37rem;
                    padding-top: 0.37rem;
                    font-weight: 500;
                }

                p {
                    color: #666;
                    font-size: .32rem;
                    margin-top: 0.27rem;
                }
            }

            .lisc {
                text-align: center;
                display: table;
                height: 1.68rem;

                p {
                    display: table-cell;
                    vertical-align: middle;
                    text-align: center;
                    color: #1a1a1a;
                    font-size: .32rem;
                }
            }

            .lisr {
                margin-right: 0.35rem;
                text-align: center;
                margin-top: 0.53rem;

                a {
                    width: 1.6rem;
                    height: 0.67rem;
                    background: linear-gradient(-55deg, #ea3544, #ea6248);
                    border-radius: 0.35rem;
                    text-align: center;
                    display: inline-block;
                    color: #fff;
                    font-size: .32rem;
                    line-height: .67rem;
                }
            }
        }
    }
}

.dbox {
    width: 9.02rem;
    padding-bottom: 0.53rem;
    background: #f7f7f7;
    border-radius: 0.16rem;
    margin: 0 auto;

    .hezi {
        width: 100%;
        display: flex;
        justify-content: space-between;
        flex-wrap: wrap;

        .dange {
            width: 25%;
            text-align: center;

            p {
                color: #000;
                font-size: .32rem;
                padding-top: 0.56rem;
                height: 1.28rem;
            }

            h6 {
                color: #dd362b;
                font-size: .32rem;
                margin-top: 0.266rem;
                font-weight: 600;
                font-family: '苹方';
            }
        }
    }

    .liyouj {
        color: #333;
        font-size: .32rem;
        margin-top: 0.85rem;
        text-align: center;
    }
}
</style>