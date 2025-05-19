<template>
    <div>
        <div class="kuange">
            <!-- <div class="kdan" @click="shengoujilu"><img
                    src="data:image/png;base64,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">
                <p>申购记录</p>
            </div> -->
            <div class="kdan" @click="sharerecordDz"><img
                    src="data:image/png;base64,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">
                <p>交易记录</p>
            </div>
        </div>
        <div class="dbox">
            <div class="daz"><span class="d1">名称</span><span class="d2">价格</span><span class="d3"></span></div>
        </div>
        <div class="list" v-for="(item, index) in dazongList" :key="index">
            <div class="lbox">
                <div class="lb1">
                    <h6>{{ item.stockName }}</h6>
                    <p>
                        <span v-if="item.stockType == 'sz'">深</span>
                        <span class="sh" v-if="item.stockType == 'sh'">沪</span>
                        <span class="bj" v-if="item.stockType == 'bj'">北</span>
                        <a :class="item.stockType == 'sh' ? 'shbg' : item.stockType == 'bj' ? 'bjbg' : ''">{{
                                item.stockGid
                        }}</a>
                    </p>
                </div>
                <div class="lb2"> {{ item.price }} </div>
                <div class="lb3"><a @click="getdetail(item)">详情</a></div>
            </div>
        </div>
        <van-popup v-model="show" round position="bottom">
            <div class="boxd">
                <div class="boxh"> 大宗交易 <span @click="show = false"></span></div>
                <h5>申购价格</h5>
                <h6>{{ currentItem.price ? currentItem.price : '' }}</h6>
                <div class="erty tghj"><input placeholder="请输入数量" type="number" class="inpy" v-model="num"
                        @input="num = num.replace(/^(0+)|[^\d]+/g, '')"><a>手</a></div>
                <p class="plm"><span>购买金额</span><a>{{ currentItem.price ? (currentItem.price * num * 100).toFixed(2) :
                        '0.00'
                }}</a></p>
                <div class="erty"><input placeholder="请输入秘钥" type="password" class="inpy" v-model="password"></div>
                <p class="plm"><span>可用资金</span><a>{{ userinfo.enableAmt }}</a></p>
                <div class="maik" @click="getxiadan">买入</div>
            </div>
        </van-popup>
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
            show: false,
            dazongList: [],
            num: '',
            currentItem: '',
            userinfo: '',
            password: ''
        }
    },
    mounted() {
        this.stockgetDzList()
        this.getUserInfo()
    },
    methods: {
        sharerecordDz(){
            this.$router.push({path:'/sharerecordDz'})
        },
        getdetail(item) {
            this.currentItem = item
            this.show = true
        },
        async getxiadan() {
            if (!this.num) {
                this.show = false
                this.$toast('请输入数量')
                return
            }
            if (!this.password) {
                this.show = false
                this.$toast('请输入秘钥')
                return
            }
            var opt = {
                stockCode: this.currentItem.stockCode,
                password: this.password,
                num: this.num * 100,
            }
            let res = await api.buyStockDz(opt)
            if (res.status == 0) {
                this.$toast('买入成功')
            } else {
                this.$toast(res.msg)
            }
            this.show = false
        },
        async stockgetDzList() {
            let res = await api.stockgetDzList()
            if (res.status == 0) {
                this.dazongList = res.data
            }
        },
        async getUserInfo() {
            // 获取用户信息
            let data = await api.getUserInfo();
            if (data.status === 0) {
                this.userinfo = data.data;
            }
        },
    },
}
</script>
<style lang="less" scoped>
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

        .d1 {
            width: 40%;
        }

        .d2 {
            width: 20%;
            text-align: center;
        }

        .d3 {
            width: 40%;
        }
    }
}
.kuange {
    width: 5.34rem;
    margin: 0 auto;
    display: flex;
    justify-content: space-between;

    .kdan {
        width: 2.14rem;
        text-align: center;
        margin: 0 auto;
        margin-bottom: 0.5rem;
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
            color: #d73d3d;
            font-size: .32rem;
            margin-top: 0.32rem;
        }

        .lb3 {
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

.boxd {
    background: #fff;
    border-radius: 0.266rem 0.266rem 0 0;
    padding-bottom: 0.53rem;

    .boxh {
        height: 1.2rem;
        border-bottom: 0.0266rem solid #e0e0e0;
        text-align: center;
        line-height: 1.2rem;
        color: #333;
        font-size: 0.43rem;
        width: 9.48rem;
        margin: 0 auto;
        position: relative;

        span {
            position: absolute;
            width: 0.32rem;
            height: 0.32rem;
            background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAAAXNSR0IArs4c6QAAAStJREFUSEutlk1qhDAUgF+EzG5EWsYTzK6HKO0hcgB3gscR3IgH8BBTeojuegJLQWg3NqDlyUQwRn0xcWeQ7zMv7ycMAKCu61PTNOcsy77x3fUpy/IhDMNfIcQfQ3jbtvUwDFfO+UuSJF8ugqqqLlLKN8bYZxRFguV5/sg5fweAJwD4cJEouGJJKZ8Z/m1RFHEQBDcXiQ7v+/41TdNmFLhK1uDInQRHJVvwhcBWsgc3CqgSCnxVsCehwjcFaxJcxzynZtzskE0Fpqfw/RtyzewKDDvBJXJBkgRazP0K9AP1GiIdjn2q6zpm01ZWQ2SCq05r07uMgi24be9aCChwG8lMYAOnSibBEThFMgpc4HsSryNTz65xZPoe+koyDX3cou9rC14k4jj+wWvLP1ylVM57GzhpAAAAAElFTkSuQmCC) no-repeat 50%;
            background-size: 100%;
            right: 0.266rem;
            top: 0.4rem;
        }
    }

    h5 {
        color: #333;
        font-size: .37rem;
        font-weight: 500;
        width: 9.48rem;
        margin: 0 auto;
        margin-top: 0.32rem;
    }

    h6 {
        color: #ea3544;
        font-size: .43rem;
        width: 9.48rem;
        margin: 0 auto;
        margin-top: 0.32rem;
        font-weight: 600;
    }

    .erty {
        width: 9.21rem;
        height: 1.07rem;
        border: 0.0266rem solid #999;
        border-radius: 0.13rem;
        margin: 0 auto;
        display: flex;
        justify-content: space-between;
        margin-top: 0.59rem;

        .inpy {
            height: 1.07rem;
            width: 5.34rem;
            margin-left: 0.266rem;
            background: transparent;
            font-size: .37rem;
            color: #000;
        }

        a {
            height: 0.64rem;
            border-left: 0.0266rem solid #999;
            width: 1.15rem;
            margin-top: 0.266rem;
            text-align: center;
            font-size: .37rem;
            color: #000;
            line-height: .64rem;
        }
    }

    .tghj {
        border: 0.0266rem solid #3b4fde;
        margin-top: 0.45rem;
    }

    .plm {
        width: 8.94rem;
        margin: 0 auto;
        margin-top: 0.266rem;

        span {
            color: #999;
            font-size: .32rem;
        }

        a {
            color: #f33030;
            margin-left: 0.11rem;
        }
    }

    .maik {
        width: 9.21rem;
        height: 1.07rem;
        background: linear-gradient(-55deg, rgb(80, 122, 250),rgb(115, 131, 251));
        border-radius: 0.26rem;
        margin: 0 auto;
        margin-top: 0.56rem;
        text-align: center;
        line-height: 1.07rem;
        color: #fff;
        font-size: .37rem;
    }
}
</style>