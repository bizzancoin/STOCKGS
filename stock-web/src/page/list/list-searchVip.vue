<template>
    <div>
        <div class="headk">
            <div>
                <h2>
                    <span class="hbnh" @click="$router.go(-1)">
                        <a class="fan"></a>
                    </span> 搜索
                </h2>
            </div>
            <div class="soudsu"><span></span><input placeholder="请输入股票代码/名称" v-model="keyWords" @input="listArr = [];getStock();loading = true"></div>
        </div>
        <div class="xuanzhe">
            <div class="xiakm" style="">抢筹列表</div>
            <div class="gangj" style="">
                <div class="xiage"><span class="spl1">名称</span><span class="spl1">价格</span><span
                        class="spl1">涨跌幅</span><span class="spl4">详情</span></div>
            </div>
            <ul class="shuju" v-if="keyWords">
                <van-list v-model="loading" :finished="finished" finished-text="没有更多了" @load="onLoad" offset="500"
                    :immediate-check="false">
                    <div class="list" v-for="(item, index) in listArr" :key="index" @click="goDetail(item)">
                        <div class="lbox">
                <div class="lb1">
                    <h6>{{ item.n }}</h6>
                    <p>
                        <span v-if="item.stockType == 'sz'">深</span>
                        <span class="sh" v-if="item.stockType == 'sh'">沪</span>
                        <span class="bj" v-if="item.stockType == 'bj'">北</span>
                        <a :class="item.stockType == 'sh' ? 'shbg' : item.stockType == 'bj' ? 'bjbg' : ''">{{
                                item.c
                        }}</a>
                    </p>
                </div>
                <div class="lb2" :class="item.zdp > 0 ? 'hqred' : 'hqgreen'"> {{ Number(item.p)/1000 }} </div>
                <div class="lb3" :class="item.zdp > 0 ? 'hqred' : 'hqgreen'"> {{ item.zdp ? item.zdp.toFixed(2)+'%' : '0.00%'
                }} </div>
                <div class="lb4"><a @click="getdetail(item)">详情</a></div>
            </div>
                        <!-- <div class="s2" :class="item.hcrate > 0 ? 'hqred' : 'hqgreen'">{{ item.nowPrice }}</div>
                        <div class="s2" :class="item.hcrate > 0 ? 'hqred' : 'hqgreen'">{{ item.hcrate }}%</div>
                        <div class="s4"><a :class="item.hcrate > 0 ? 'hqredbtnbg' : 'hqgreenbtnbg'">3.05</a></div> -->
                    </div>
                </van-list>
            </ul>
        </div>
    </div>
</template>
<script>
import { Toast } from 'vant';
import { MessageBox } from 'mint-ui'
import handleDt from "@/utils/deTh";
import * as api from '@/axios/api'
export default {
    props: {},
    data() {
        return {
            keyWords: '',
            pageNum: 1,
            loading: false,
            finished: false,
            listArr: []
        }
    },
    mounted() {

    },
    watch:{
        keyWords(val){
            if(val == ''){
                this.listArr = []
            }
        }
    },
    methods: {
        getdetail(item) {
            this.$router.push({
                path: '/vipdetail',
                query: {
                    item: encodeURIComponent(JSON.stringify(item))
                }
            })
        },
    filterSH(val) {
      if (val.indexOf('sh')>=0) {
        return 1;
      } else if (val.indexOf('bj')>=0 || val.indexOf('sz')>=0) {
        return 0;
      }
    },
        onLoad() {
            // 异步更新数据
            // setTimeout 仅做示例，真实场景中一般为 ajax 请求
            // setTimeout(() => {
            //     for (let i = 0; i < 10; i++) {
            //         this.list.push(this.list.length + 1);
            //     }

            //     // 加载状态结束
            //     this.loading = false;

            //     // 数据全部加载完成
            //     if (this.list.length >= 40) {
            //         this.finished = true;
            //     }
            // }, 1000);
            this.loading = true;
            this.pageNum ++
            this.getStock()
        },
        getStock: handleDt.debounce(async function () {
            // this.listArr = []
            let opt = {
                code: this.keyWords,
            };
            let data = await api.getVipQc(opt);
            if (data.status == 0) {
                this.loading = false;
                
                if (data.data.length == 0) {
                    this.finished = true;
                    return
                } else if(data.data.length > 0 && data.data.length < 15){
                    this.finished = true;
                    data.data.forEach(element => {
                        this.listArr.push(element);
                    });
                } else {
                    data.data.forEach(element => {
                        this.listArr.push(element);
                    });
                }
            }
        }, 2000),
    }
}
</script>
<style lang="less" scoped>
.hqred {
    color: #f11614 !important;
}

.hqgreen {
    color: #09965f !important;
}

.headk {
    height: 3.07rem;
    background: linear-gradient(-55deg, rgb(80, 122, 250),rgb(115, 131, 251));

    h2 {
        text-align: center;
        height: 1.25rem;
        width: 100%;
        position: relative;
        line-height: 1.25rem;
        font-size: 0.48rem;
        color: #fff;
        background: transparent;
        font-weight: 500;
        z-index: 3;

        .hbnh {
            position: absolute;
            left: 0.4rem;
            font-size: 0.43rem;
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

    .soudsu {
        width: 9.35rem;
        height: 0.91rem;
        background: hsla(0, 0%, 100%, .2);
        border-radius: 0.45rem;
        margin: 0 auto;
        display: flex;

        span {
            width: 0.43rem;
            height: 0.43rem;
            background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAAXNSR0IArs4c6QAAA4dJREFUWEe9l0msn2MUxn8PYl4QolhIKhYS00JalAiCIIbeBg1FSrsTQxcsGrEo3WBR0pWZkjSGVEpMiSkiTRPTgkhKYiaGiilmHnna8/H1/r//4N6/+27u/X/vec953nPOc855xZBle39gAXAycCiwH7Ar8C3wIfAa8DTwhKSfh+mbvK9+B2wfCKwAFgI7jKD4G+BW4BZJP40gv0WkB4DtfLsaWAns0lL0MfAq8CnwA7AbcAhwHLBTS+4D4CJJr4wCYhsAtrcH7gQW12EDj9atNnYptL07MAEsBw4umd+ByyQ9MAzEPwDq5vcHfR36qG7y8jAl2be9HXANcGOF7M86v3bQ+TaAK4DbSjiuPk3S5lGMt2Vsnwo8DuwIJBfmSnq7n54tAGzPBiKUmCfWR0r66r8ab+Rtnw08VjmW0M2T9FeXvgbAmnJ9Yn6SpBenarwFYhVwVf1eKOmhTgC2ZwGfVNzWSQrnp71s7wG8D+TvBknz+gG4HFhdmydIemna1hsK2akLV9bP2ZJC0W2WbK8D5gOJ+SxJCcNYlu3jgeZCSyTd3QXgXeAgYL2kc8Zi+V8PJKl/BELRVZKWdQGIQKraakmh4liX7c+qf6yVdEEXgMblN0u6dqzWt1I8DeuAfh5ODqSJ7AncJWnp/wCg0b9G0iVdHngTOGIQVaYKyva+wOd1fqWk67oApPksAX4D9pKUnBjLsn0h8GApm5CU6thDw3OBh+vrpZLuHYv1rfF/LpUV+LUo/l0XgJ2r/u8NvAMcLumP6YKwPRdoWngnA2Kj6QWJzQ1l9HpJzf9TwmE7A0o6aka4sCzN7Y0uZQ2AzHhvAemK6eMLJK2fivWaC+5rzRUD2dWeB44Fnq8+npgtHWWiaYO0nXDeDlxc3zcBcyR93+8yk0eyRUCmopTOuC4MWS7p62HesH0UcAdwWMl+kXlR0nuDznYNpecD99TonbOhZagUpmxsaFquToU7BTivxvZGX/rLGZmGgHTbFZKe6ZsDkzdsJ3nSueZ0HPoSCEv26RjX47WAXxa3294AHF01Zr6kp3po2M89dcN4I/38mCEhSM6kyNwk6fVG1vbp9T3zYQpdD4i+D5NJyZVHyokV37yU8lD5pSae0O0FSXkp9SzbZwGPVHL3gBgJwLAEHLY/CMSMAAjADhDpDU/OGIACcWa9tJqcmJhRAB0gNs84gBaIFLxn/wb6PUsFkO1mDQAAAABJRU5ErkJggg==) no-repeat 50%;
            background-size: 100%;
            margin-left: 0.266rem;
            margin-top: 0.21rem;
        }

        input {
            height: 0.91rem;
            width: 5.34rem;
            background: transparent;
            color: #fff;
            font-size: .43rem;
            margin-left: 0.16rem;

            &::placeholder {
                color: rgb(213, 204, 255);
                font-size: 0.38rem;
            }
        }
    }
}

.xuanzhe {
    width: 100%;
    margin: 0 auto;
    margin-top: -0.5rem;
    min-height: 0.8rem;
    border-radius: 0.53rem .53rem 0 0;
    margin-bottom: 0.53rem;
    background: #fff;

    .xiakm {
        width: 9.35rem;
        margin: 0 auto;
        color: #333;
        font-size: .38rem;
        padding-top: 0.53rem;
    }

    .gangj {
        height: 0.88rem;
        border-top: 1px solid #e0e0e0;
        border-bottom: 1px solid #e0e0e0;
        margin-top: 0.4rem;

        .xiage {
            width: 9.48rem;
            display: flex;
            margin: 0 auto;
            justify-content: space-between;

            span {
                color: #666;
                font-size: .35rem;
                line-height: .83rem;
            }

            .spl1 {
                width: 25%;
                text-align: center;
            }

            .spl4 {
                width: 20%;
                text-align: right;
            }
        }
    }

    .shuju {
        width: 9.48rem;
        margin: 0 auto;

        .shujuk {
            margin: 0 auto;
            margin-top: 0.40rem;
            padding-bottom: 0.32rem;
            display: flex;
            justify-content: space-between;
            align-items: center;
            border-bottom: 1px solid #e0e0e0;

            .s1 {
                width: 25%;

                h6 {
                    color: #333;
                    font-size: .37rem;
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
                        padding: 0.04rem;
                        text-align: center;
                        line-height: .4rem;
                        color: #fff;
                        font-size: .3rem;
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

            .s2 {
                text-align: center;
                font-size: .4rem;
                margin-top: 0.13rem;
                font-weight: 600;
                width: 25%;
            }

            .s4 {
                width: 20%;
                text-align: right;

                span {
                    display: inline-block;
                    width: 1.34rem;
                    height: 0.67rem;
                    border-radius: 0.13rem;
                    color: #fff;
                    font-size: .35rem;
                    text-align: center;
                    line-height: .67rem;
                }

                a {
                    display: inline-block;
                    width: 1.34rem;
                    height: 0.67rem;
                    border-radius: 0.13rem;
                    color: #fff;
                    font-size: .35rem;
                    text-align: center;
                    line-height: .67rem;
                }

                .hqgreenbtnbg {
                    background: #09965f;
                }

                .hqredbtnbg {
                    background: #f11614;
                }
            }
        }
    }
}



.list {
    width: 100%;
    padding: 0.4rem 0;
    border-bottom: 1px solid #e0e0e0;

    .lbox {
        width: 9.35rem;
        margin: 0 auto;
        display: flex;
        justify-content: space-between;

        .lb1 {
            width: 30%;

            h6 {
                color: #333;
                font-size: .4rem;
                font-weight: 600;
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
                    padding: 0.04rem;
                    text-align: center;
                    line-height: .4rem;
                    color: #fff;
                    font-size: .3rem;
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

        .lb2 {
            font-size: .32rem;
            margin-top: 0.32rem;
            width: 20%;
            text-align: center;
        }

        .lb3 {
            font-size: .32rem;
            margin-top: 0.32rem;
            width: 20%;
            text-align: center;
        }

        .lb4 {
            width: 30%;
            text-align: right;

            a {
                display: inline-block;
                width: 1.6rem;
                height: 0.67rem;
                background: linear-gradient(-55deg, rgb(80, 122, 250),rgb(115, 131, 251));
                border-radius: 0.35rem;
                text-align: center;
                color: #fff;
                font-sizE: .32rem;
                line-height: .67rem;
                margin-top: 0.08rem;
            }
        }
    }
}
</style>