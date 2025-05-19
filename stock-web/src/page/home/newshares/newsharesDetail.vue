<template>
    <div>
        <div class="dabg">
            <div class="gebgh">
                <div>
                    <h2><span class="hbnh" @click="$router.go(-1)"><a class="fan"></a></span> 新股详情 </h2>
                </div>
            </div>
            <div class="dabox">
                <div class="namkl">
                    <h6>{{ currentItem.name }}</h6>
                    <p>
                        <span v-if="currentItem.stockType == '深' || currentItem.stockType == '创'">深</span>
                        <span class="sh" v-if="currentItem.stockType == '沪' || currentItem.stockType == '科'">沪</span>
                        <span class="bj" v-if="currentItem.stockType == '北'">北</span>
                        <a
                            :class="(currentItem.stockType == '沪' || currentItem.stockType == '科') ? 'shbg' : currentItem.stockType == '北' ? 'bjbg' : ''">{{
                                    currentItem.code
                            }}</a>
                    </p>
                </div>
                <div class="dashedf"></div>
                <div class="plkm">
                    <p><span>发行价</span><a>{{ currentItem.price }}/股</a></p>
                    <p><span>市盈率</span><a>{{ currentItem.pe }}</a></p>
                    <p style="display: none;"><span>申购上限</span><a>0.75万股</a></p>
                    <p><span>发行总量</span><a>{{ currentItem.orderNumber }}万股</a></p>
                </div>
                <div class="jindu">
                    <div class="jdtu"><span></span></div>
                    <div class="jd1"><span class="erty">1</span>
                        <h6>申购日</h6>
                        <p>{{ dayjs(new Date().getTime()).format('YYYY-MM-DD') }}</p>
                    </div>
                    <div class="jd1"><span>2</span>
                        <h6>公布日</h6>
                        <p>{{ dayjs(currentItem.subscribeTime).format('YYYY-MM-DD') }}</p>
                    </div>
                    <div class="jd1"><span>3</span>
                        <h6>认缴日</h6>
                        <p>{{ currentItem.subscriptionTime ? dayjs(currentItem.subscriptionTime).format('YYYY-MM-DD') :
                                '--'
                        }}</p>
                    </div>
                    <div class="jd1"><span>4</span>
                        <h6>待上市</h6>
                        <p>{{ currentItem.listDate ? dayjs(currentItem.listDate).format('YYYY-MM-DD') :
                                '--'
                        }}</p>
                    </div>
                </div>
                <div class="jidu"></div>
                <div class="dashedf"></div>
                <div class="namkl" style="margin-top: 0.15rem;">
                    <h6>发行概况</h6>
                </div>
                <h5><span>募集资金</span><a>6.721亿元</a></h5>
                <h5><span>中签率</span><a>--%</a></h5>
                <h5><span>公布中签日</span><a>{{ currentItem.subscriptionTime ?
                        dayjs(currentItem.subscriptionTime).format('YYYY-MM-DD') : '--'
                }}</a></h5>
                <h5><span>认缴日</span><a>{{ currentItem.subscriptionTime ?
                        dayjs(currentItem.subscriptionTime).format('YYYY-MM-DD') : '--'
                }}</a></h5>
                <h5><span>上网发行日期</span><a>{{ dayjs(currentItem.subscribeTime).format('YYYY-MM-DD') }}</a></h5>
            </div>
            <div class="shuliang">
                <div class="hnjk"><span>申购数量</span><a>账户余额：<i>{{ userinfo.enableAmt }}</i></a></div><input
                    v-model="applyNums" placeholder="请输入申购数量" type="number" class="inkl" @input="applyNums = applyNums.replace(/^(0+)|[^\d]+/g, '')">
                <div class="sjnmh" @click="getNewAdd">申购</div>
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
            xingutype: 1,
            currentItem: '',
            userinfo: '',
            applyNums: ''
        }
    },
    mounted() {
        this.xingutype = this.$route.query.xingutype
        this.currentItem = JSON.parse(decodeURIComponent(this.$route.query.item))
        this.getUserInfo()
    },
    methods: {
        async getNewAdd() {
            if (this.applyNums == '') {
                this.$toast('请输入申购数量')
                return
            }
            var opt = {
                newCode: this.currentItem.code,
                applyNums: this.applyNums,
                phone: this.userinfo.phone,
                type: this.xingutype,
            }
            let res = await api.getNewAdd(opt)
            if (res.status == 0) {
                this.$toast('申购成功')
                this.$router.go(-1)
            } else {
                this.$toast(res.msg)
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
.dabg {
    background: #fff;

    .gebgh {
        height: 1.6rem;
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
                    width: 0.266rem;
                    height: 0.43rem;
                    background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABIAAAAgCAYAAAAffCjxAAAAAXNSR0IArs4c6QAAAVdJREFUSEut1usqBVEYxvH/c1MuRJJDkhwTEpIkJUlyCEk++CJJckpycw49Gs3Wa+y9Z82ePd/Xr5n38KwRNR7bE0APMK1OHdtTwAWQGZcdQbZngPMc+QImK0O2Z4GzgIxLuqoE2Z4DTgMyJuk6K08yZHseOAnIqKSbRo2TINsLwHFARiTdxkaVQrYXgaOADEu6K3a7LWR7CTjMD2XdaYq0rZHtFWA/Rz6BIUn3reau6RvZXgX28kMfOfLQbnj/QbbXgN380HuOPJZtwB/I9jqwE5ABSc9lyJ8a2d4AtgPSL+klBfmFbG8CWwHpk/SaivxAtjMgg7Inq0mvpLcqSHehTOvKpzU+oSvFDlj99gesOJCDkp7Kip+yIknT3XL7C0tbum9lMbIMHKQkQEqwJWVSKZTPWTElq0dt6GYxt6uHf8DqX0cBq39BBqz+lR2w+j8RAfv9rfkGqF24CUdT9E4AAAAASUVORK5CYII=) no-repeat 50%;
                    background-size: 100%;
                    display: inline-block;
                    margin-right: 0.3rem;
                    vertical-align: middle;
                    // margin-top: -0.02rem;
                }
            }
        }
    }

    .dabox {
        width: 100%;
        border-radius: 0.4rem 0.4rem 0 0;
        background: #fff;
        margin-top: -0.4rem;
        min-height: 1.07rem;
        padding-top: 0.4rem;
        padding-bottom: 1.07rem;

        .namkl {
            width: 9.35rem;
            margin: 0 auto;

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

        .dashedf {
            width: 100%;
            border-top: 1px dashed #ccc;
            margin-top: 0.4rem;
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
                    font-size: 0.35rem;
                }

                a {
                    color: #d73d3d;
                    font-size: 0.35rem;
                }
            }
        }

        .jindu {
            width: 9.35rem;
            margin: 0 auto;
            margin-top: 0.43rem;
            display: flex;
            position: relative;

            .jdtu {
                position: absolute;
                width: 7.48rem;
                height: 0.05rem;
                background: #999;
                top: 0.37rem;
                left: 50%;
                margin-left: -3.74rem;

                span {
                    position: absolute;
                    left: 0;
                    top: 0;
                    height: 0.05rem;
                    background: #3b4fde;
                    width: 30%;
                }
            }

            .jd1 {
                width: 25%;
                text-align: center;
                position: relative;

                span {
                    display: block;
                    width: 0.77rem;
                    height: 0.77rem;
                    background: #fff;
                    border: 5px solid #999;
                    border-radius: 50%;
                    color: #999;
                    font-size: .37rem;
                    line-height: .64rem;
                    text-align: center;
                    margin: 0 auto;
                }

                .erty {
                    border: 5px solid #3b4fde;
                    color: #3b4fde;
                }

                h6 {
                    color: #333;
                    font-size: .32rem;
                    margin-top: 0.13rem;
                    font-weight: 500;
                }

                p {
                    color: #333;
                    font-size: .266rem;
                    margin-top: 0.08rem;
                }
            }
        }

        .namkl {
            width: 9.35rem;
            margin: 0 auto;

            h6 {
                color: #333;
                font-size: .4rem;
                font-weight: 600;
            }
        }

        h5 {
            width: 9.35rem;
            margin: 0 auto;
            display: flex;
            justify-content: space-between;
            font-weight: 500;
            margin-top: 0.4rem;

            span {
                color: #999;
                font-size: .37rem;
            }

            a {
                color: #191919;
                font-size: .37rem;
            }
        }
    }

    .shuliang {
        width: 100%;
        padding-bottom: 0.4rem;
        background: #f0f0f0;
        border-radius: 0.4rem 0.4rem 0 0;
        padding-top: 0.43rem;

        .hnjk {
            width: 9.35rem;
            margin: 0 auto;
            display: flex;
            justify-content: space-between;

            span {
                color: #333;
                font-size: .4rem;
                font-weight: 550;
            }

            a {
                color: #333;
                font-size: .35rem;
                text-decoration: none;

                i {
                    font-style: normal;
                    color: #ea3544;
                }
            }
        }

        .inkl {
            width: 9.35rem;
            height: 1.17rem;
            border-radius: 0.13rem;
            margin: 0 auto;
            margin-top: 0.43rem;
            font-size: .37rem;
            background: #e6e6e6;
            display: block;
            padding-left: 0.266rem;
        }

        .sjnmh {
            background: linear-gradient(-55deg, rgb(80, 122, 250),rgb(115, 131, 251));
            text-align: center;
            color: #fff;
            line-height: 1.17rem;
            width: 9.35rem;
            height: 1.17rem;
            border-radius: 0.13rem;
            margin: 0 auto;
            margin-top: 0.43rem;
            font-size: .37rem;
        }
    }
}
</style>