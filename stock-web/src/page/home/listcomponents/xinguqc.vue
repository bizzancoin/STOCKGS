<template>
    <div>
        <div class="kuange">
            <div class="kdan" @click="shengoujilu"><img
                    src="data:image/png;base64,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">
                <p>申购记录</p>
            </div>
            <div class="kdan" @click="zhongqianjilu"><img
                    src="data:image/png;base64,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">
                <p>中签记录</p>
            </div>
        </div>
        <div class="gnj"></div>
        <div class="jilkm" v-for="(item, index) in qiangchouList" :key="index">
            <div class="namkl">
                <div class="nlt">
                    <h6>{{ item.name }}</h6>
                    <p>
                        <span v-if="item.stockType == '深' || item.stockType == '创'">深</span>
                        <span class="sh" v-if="item.stockType == '沪' || item.stockType == '科'">沪</span>
                        <span class="bj" v-if="item.stockType == '北'">北</span>
                        <a
                            :class="(item.stockType == '沪' || item.stockType == '科') ? 'shbg' : item.stockType == '北' ? 'bjbg' : ''">{{
                                    item.code
                            }}</a>
                    </p>
                </div>
                <div class="rlt" @click="getqiangchou(item)">申购</div>
            </div>
            <div class="plkm">
                <p><span>发行价格</span><a>{{ item.price ? (item.price).toFixed(2) : '0.00' }}/股</a></p>
                <p><span>配售价格</span><a>{{ item.price ? (item.price).toFixed(2) : '0.00'}}</a></p>
            </div>
        </div>
    </div>
</template>
<script>
import * as api from '@/axios/api'
export default {
    components: {

    },
    props: {},
    data() {
        return {
            qiangchouList: [],
        }
    },
    mounted() {
        this.newStockQc()
    },
    methods: {
        zhongqianjilu() {
            this.$router.push({
                path: '/zhongqianrecord'
            })
        },
        getqiangchou(item){
            this.$router.push({
                path: '/qingchouDetail',
                query: {
                    item: encodeURIComponent(JSON.stringify(item))
                }
            })
        },
        shengoujilu() {
            this.$router.push({
                path: '/sharerecord'
            })
        },
        async newStockQc() {
            var opt = {
                pageNum: this.pageNum,
                pageSize: 9,
            }
            let data = await api.newStockQc(opt);
            if (data.status == 0) {
                this.qiangchouList = data.data
            }
        },
    },
}
</script>
<style lang="less" scoped>
.kuange {
    width: 5.34rem;
    margin: 0 auto;
    display: flex;
    justify-content: space-between;

    .kdan {
        width: 2.14rem;
        text-align: center;
        margin: 0 auto;

        img {
            width: 0.51rem;
            height: 0.51rem;
        }

        p {
            color: #333;
            font-size: .35rem;
            margin-top: 0.24rem;
        }
    }
}

.gnj {
    height: 1px;
    width: 100%;
    background: #e0e0e0;
    margin-top: 0.4rem;
}

.jilkm {
    padding-top: 0.4rem;
    // border-top: 2px solid #e0e0e0;
    border-bottom: 2px solid #e0e0e0;

    .namkl {
        width: 9.35rem;
        margin: 0 auto;
        // margin-bottom: 0.4rem;
        display: flex;
        justify-content: space-between;

        .nlt {
            h6 {
                color: #333;
                font-size: .4rem;
                font-weight: 600;

                span {
                    color: #ea3544;
                    font-size: .38rem;
                    margin-left: 0.1rem;
                }
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

        .rlt {
            width: 1.6rem;
            height: 0.67rem;
            background: linear-gradient(-55deg, rgb(80, 122, 250),rgb(115, 131, 251));
            border-radius: 0.33rem;
            color: #fff;
            font-size: .32rem;
            text-align: center;
            line-height: .67rem;
        }
    }



    .plkm {
        display: flex;
        justify-content: space-between;
        width: 9.35rem;
        margin: 0 auto;
        flex-wrap: wrap;
        padding-bottom: 0.4rem;

        p {
            width: 47%;
            margin-top: 0.4rem;
            display: flex;
            justify-content: space-between;

            span {
                color: #666;
                font-size: 0.32rem;
            }

            a {
                color: #d73d3d;
                font-size: 0.35rem;
            }
        }
    }
}
</style>