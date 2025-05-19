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
        <div class="sange">
            <span v-for="(item, index) in shengouTab" :key="index" @click="shengouIdx = index"
                :class="shengouIdx == index ? 'xuan' : ''">{{ item.name }}</span>
        </div>
        <div class="dabol" v-if="shengouIdx == 1">
            <div class="ertg"><span class="shij"></span>
                <div class="plm">
                    <p>申购时间:08:45-15:00</p>
                    <p class="plk"><span>一分钟了解新债电购规则</span><a></a></p>
                </div>
            </div>
        </div>
        <!-- <div v-if="shengouIdx == 0">
            <div class="skm">
                <div class="shead shn">
                    <div class="shl">
                        <h6>丰立智能</h6>
                        <p><span>深</span><span class="sh" style="display: none;">沪</span><span class="bj"
                                style="display: none;">北</span><a class="">SZ301368</a></p>
                    </div>
                    <div>
                        <div class="she1" @click="getshengou"><span style="font-size: 0.12rem;">1元可申购</span><span>申购</span></div>
                        <div class="she2" @click="getshengou"><span style="font-size: 0.12rem;">100万可用</span><span>配售</span></div>
                    </div>
                </div>
                <div class="shead shn" style="padding-top: 0px !important;">
                    <p class="shr">申购时间 <span>2022-12-05</span></p>
                </div>
                <div class="plkm">
                    <p><span>申购价</span><a>22.33/股</a></p>
                    <p><span>市盈率</span><a>45.71</a></p>
                    <p style="display: none;"><span>申购上限</span><a>0.75万股</a></p>
                    <p><span>发行量</span><a>3010万股</a></p>
                </div>
            </div>
        </div>
        <div v-if="shengouIdx == 1">
            <div class="skm" style="">
                <div class="shead">
                    <div class="shl">
                        <h6>星源卓镁</h6>
                        <p><span>深</span><span class="sh" style="display: none;">沪</span><span class="bj"
                                style="display: none;">北</span><a class="">SZ301398</a></p>
                    </div>
                    <p class="shr">申购时间 <span>2022-12-06</span></p>
                </div>
                <div class="plkm">
                    <p><span>申购价</span><a>34.4/股</a></p>
                    <p><span>市盈率</span><a>59.74</a></p>
                    <p style="display: none;"><span>申购上限</span><a>0.5万股</a></p>
                    <p><span>发行量</span><a>2000万股</a></p>
                </div>
            </div>
        </div> -->
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
            shengouTab: [
                {
                    name: '可申购',
                    id: 1
                },
                {
                    name: '待申购',
                    id: 2
                }
            ],
            shengouIdx: 0,
        }
    },
    mounted() { },
    methods: {
        zhongqianjilu() {
            this.$router.push({
                path: '/zhongqianrecord'
            })
        },
        getshengou() {
            this.$router.push({
                path: '/newsharesDetail'
            })
        },
        shengoujilu() {
            this.$router.push({
                path: '/sharerecord'
            })
        }
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

.sange {
    width: 100%;
    margin-top: 0.4rem;
    height: 0.8rem;
    justify-content: space-between;
    display: flex;
    background: #e8dcff;

    span {
        width: 50%;
        text-align: center;
        line-height: .8rem;
        color: #999;
        font-size: .35rem;
    }

    .xuan {
        color: #5d7dfb;
    }
}

.dabol {
    .ertg {
        width: 9.35rem;
        margin: 0 auto;
        margin-top: 0.85rem;
        display: flex;

        .shij {
            background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABwAAAAcCAYAAAByDd+UAAAAAXNSR0IArs4c6QAAAzxJREFUSEu9lluITWEUx39rDzNEoUYSHlxyeRgPjMgll5BL7nX2mSEUz65RePHkVkReySXNOWcrcsldyQg1hnKJXPLigSKU65S9tPbZh32cc+acnfge91rf+n3f2mut7y+UW6qCxwSU2cA4YBDQI9z2HngO3EQ4S4JmRLS9kFLSuFUdhrEcZVMIKXc0sz9H2M5jDrNV/GIbigM97Y9PBhgVbvoOXAKuojykinf4dETpDQxEmAxMB2pC/xYcXBLy8k9oITClkxCOA7XAV5S9KHtolLftXrFJaxHWIawBOgNvERbhyvXovnxgk87A4RRQDdwDkiTlaSW5/OWT1sFAChgBtOEzj0a5kLP/BjbpUBxagK7ARdpYxFL5HAuWcz6qXajhNMoU4BM+o2iUJ2bOAj2twuc2UA/cpQsTmCNfisKyVXsusCWYVbIqPe2Gzy1gGHAHhzEk5EcWmNIVCAeAb/xgOIvlWcmbZQN9COwO3UnIx3Z8h+BzP/hFykoa5KBgJ85g/2kQynYaZHO7aYwDtEBp3QusDlrGZbDg6UR8roU/uE/ZaowL9LQnPq+CWzpMEtK6E9iIcBpX5pUtkrhAC5jRUyhzgV1CSpsRxgOrSMr+fwJMq6XUUttsN3wN9EKYhitX/gkwo1NRLgNvDGhjy/I7nIQ8iAmsJyGtFeypC6u1LT7wkHaic1DV/YAPKAtoECu60svTPGC8lFrYY9qXDkHz1wHfUZbRIDbsi6+8lMYtmlxIq1blRDi+FGEDruwuSkzpKoR9uaLZBWyouC2iET2txucQ0Bh+3kNS1hdA89oibuP/GS07qXYEvWyrmloWyrtfbgWNHx1twjZc2VK26oo5pNTFoROuHMkzR0fbE4YUDm+HOhJiOuXvl6dFhreFjfM8VXqM/OepFYfRv58nC/JfH+DcqTM6C+VkKDHsVMnY6U3rQBvXwMjgBYL5JOV8DlEootI605IcSo14IqqKtShrQxH1KdAEEZhBi8vEbHqPhac0P5u3JoSu4vMoVGQ2f3ujDIBAJs6IyMRWfJbkdEz0t1cihE0BWJoqWS+w1oothKOhswrc3ss5KGNDeFTqG+QmcIbH3CiluHMhfwLMYGwMIa833wAAAABJRU5ErkJggg==) no-repeat 50%;
            background-size: 100%;
            width: 0.37rem;
            height: 0.37rem;
        }

        .plm {
            // font-size: .32rem;
            margin-left: 0.26rem;

            p {
                color: #333;
                font-size: .37rem;
            }

            .plk {
                font-size: .32rem;
                margin-top: 0.266rem;

                span {
                    border-bottom: 0.0266rem solid #333;
                }

                a {
                    background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAA4AAAAYCAYAAADKx8xXAAAAAXNSR0IArs4c6QAAAYNJREFUOE+d1M9KHEEQBvDvG4k+gU9hTjV9lkElJgEvIboXBSEQfCIRJCQX0UMgyTMYAsl2T6+gK2K8JUH8g4jn7QodesNmszuzu33t/lFDzVdFY8yBqs4DWHPOfcaIh3meXwGYBfCQZdnTZrP5ZRRLEVkm+QnADIB7kk+std/qMOMDY8xzVf0AYDriEMKS995W4T8wHhFZIfk+4TuSi9ZaPwz/hQm/IHkA4BGAm1T5aBD+B8YHeZ6vAtgHMAXgmuSCtfa4H/8HU+UGyb2Er1S1KMvytBcPhKlhG6r6NuFLkoW19qyLh8JUeZPkGwAZgJ+p8vd4VwlT5Vequpve/gBQOOcuamFq2BaA7YRPnHOPR4Ii8prkToJt59xcLTTGjP+pItLbnF+dTqdotVrnlc0RkXWS78b6HSLSG4DrEELhvW9XBsAY81JVY15Hj5yIxJDHnMbxug0hLHrvq0M+0VgZY56p6sexBrl/dQBYds59rV0dEy+r7npU1UZZlod1lbr3vwH69L8ZZoscZQAAAABJRU5ErkJggg==) no-repeat 50%;
                    background-size: 100%;
                    display: inline-block;
                    width: 0.19rem;
                    height: 0.32rem;
                    vertical-align: middle;
                    margin-left: 0.03rem;
                }
            }
        }
    }
}

.skm {
    border-bottom: 1px solid #e0e0e0;
    padding-bottom: 0.4rem;

    .shead {
        width: 9.35rem;
        margin: 0 auto;
        display: flex;
        padding-top: 0.4rem;

        .shl {

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

        .she1 {
            width: 2.94rem;
            height: 0.69rem;
            color: #fff;
            display: flex;
            justify-content: space-around;
            align-items: center;
            background: url(data:image/png;base64,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);
            background-size: cover;
            margin-bottom: 10px;
        }

        .she2 {
            width: 2.94rem;
            height: 0.69rem;
            color: #fff;
            display: flex;
            justify-content: space-around;
            align-items: center;
            background: url(data:image/png;base64,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);
            background-size: cover;
        }

        .shr {
            margin-left: 1.34rem;
            color: #666;
            font-size: .32rem;
            margin-top: 0.266rem;

            span {
                color: #d73d3d;
            }
        }
    }



    .plkm {
        width: 9.35rem;
        margin: 0 auto;
        flex-wrap: wrap;
        display: flex;
        justify-content: space-between;

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
                color: #d73d3d;
                font-size: .32rem;
            }
        }
    }

    .shn {
        justify-content: space-between;

        .shr {
            margin-left: 0;
        }
    }
}
</style>