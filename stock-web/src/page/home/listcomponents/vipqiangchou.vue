<template>
    <div>
        <div class="dbox">
            <div class="daz"><span class="d1" style="width: 30%;">名称</span><span class="d2"
                    style="width: 20%;">价格</span><span class="d3"
                    style="width: 20%; text-align: center;">涨跌幅</span><span class="d4" style="width: 30%;"></span></div>
        </div>
        <div class="list" v-for="(item, index) in vipqcList" :key="index">
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
            vipqcList: [],
        }
    },
    mounted() {
        this.getVipList()
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
        async getVipList() {
            let res = await api.getVipList()
            if (res.status == 0) {
                this.vipqcList = res.data
            }
        },
    },
}
</script>
<style lang="less" scoped>
.hqred {
    color: #f11614 !important;
}

.hqgreen {
    color: #09965f !important;
}

.dbox {
    width: 100%;
    padding-bottom: 0.266rem;
    border-bottom: 1px solid #e0e0e0;

    .daz {
        width: 9.35rem;
        margin: 0 auto;
        display: flex;
        justify-content: space-between;

        span {
            color: #666;
            font-size: .35rem;
        }

        .d2 {
            text-align: center;
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