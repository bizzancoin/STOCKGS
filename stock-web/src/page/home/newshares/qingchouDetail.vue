<template>
    <div>
        <div class="toubu">
            <div>
                <h2><span class="hbnh" @click="$router.go(-1)"><a class="fan"></a></span> 线下配售</h2>
            </div>
        </div>
        <div class="xuanhze">
            <div class="boxd">
                <div class="txtbox">
                    <h6>{{(currentItem.name)}}</h6>
                    <h5> <span v-if="currentItem.stockType == '深' || currentItem.stockType == '创'">深</span>
                        <span class="sh" v-if="currentItem.stockType == '沪' || currentItem.stockType == '科'">沪</span>
                        <span class="bj" v-if="currentItem.stockType == '北'">北</span>
                        <a
                            :class="(currentItem.stockType == '沪' || currentItem.stockType == '科') ? 'shbg' : currentItem.stockType == '北' ? 'bjbg' : ''">{{ currentItem.code }}</a>
                    </h5>
                    <p><span>发行价格</span><a>{{ currentItem.price }}/股</a></p>
                    <p><span>配售价格</span><a>{{ currentItem.price }}</a></p>
                    <p><span>上网发行日期</span><a>{{ dayjs(currentItem.subscribeTime).format('YYYY-MM-DD') }}</a></p>
                </div>
            </div>
            <div class="kunm"><span class="goud"></span>
                <h3>自定义申购(手)</h3><input placeholder="请输入" type="number" class="inpl" v-model="applyNums" @input="applyNums = applyNums.replace(/^(0+)|[^\d]+/g, '')">
                <!-- <p> 1手 = 100股 </p> -->
            </div>
        </div>
        <div class="queren" @click="buyNewStockQc">确认申购</div>
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
        this.currentItem = JSON.parse(decodeURIComponent(this.$route.query.item))
        console.log(this.currentItem)
        this.getUserInfo()
    },
    methods: {
        async buyNewStockQc() {
            if (this.applyNums == '') {
                this.$toast('请输入申购数量')
                return
            }
            var opt = {
                code: this.currentItem.code,
                num: this.applyNums * 100,
            }
            let res = await api.buyNewStockQc(opt)
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
.toubu {
    width: 100%;
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
                width: 0.24rem;
                height: 0.43rem;
                background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABIAAAAgCAYAAAAffCjxAAAAAXNSR0IArs4c6QAAAVdJREFUSEut1usqBVEYxvH/c1MuRJJDkhwTEpIkJUlyCEk++CJJckpycw49Gs3Wa+y9Z82ePd/Xr5n38KwRNR7bE0APMK1OHdtTwAWQGZcdQbZngPMc+QImK0O2Z4GzgIxLuqoE2Z4DTgMyJuk6K08yZHseOAnIqKSbRo2TINsLwHFARiTdxkaVQrYXgaOADEu6K3a7LWR7CTjMD2XdaYq0rZHtFWA/Rz6BIUn3reau6RvZXgX28kMfOfLQbnj/QbbXgN380HuOPJZtwB/I9jqwE5ABSc9lyJ8a2d4AtgPSL+klBfmFbG8CWwHpk/SaivxAtjMgg7Inq0mvpLcqSHehTOvKpzU+oSvFDlj99gesOJCDkp7Kip+yIknT3XL7C0tbum9lMbIMHKQkQEqwJWVSKZTPWTElq0dt6GYxt6uHf8DqX0cBq39BBqz+lR2w+j8RAfv9rfkGqF24CUdT9E4AAAAASUVORK5CYII=) no-repeat 50%;
                background-size: 100%;
                display: inline-block;
                margin-right: 0.13rem;
                vertical-align: middle;
                margin-top: -0.053rem;
            }
        }
    }
}

.xuanhze {
    width: 100%;
    background: #fff;
    margin-top: -0.32rem;
    min-height: 0.4rem;
    border-radius: 0.4rem 0.4rem 0 0;
    padding-top: 0.35rem;

    .boxd {
        border-bottom: 0.0266rem solid #e0e0e0;
        padding-bottom: 0.13rem;

        .txtbox {
            width: 9.35rem;
            margin: 0 auto;

            h6 {
                color: #333;
                font-size: .4rem;
                margin-top: 0.266rem;
                font-weight: 500;
            }

            h5 {
                color: #333;
                font-size: .32rem;
                margin-top: 0.13rem;
                font-weight: 500;
                margin-bottom: 0.21rem;

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

            p {
                font-size: .35rem;
                margin-bottom: 0.266rem;

                span {
                    color: #666;
                }

                a {
                    color: #d73d3d;
                    margin-left: 0.19rem;
                }
            }
        }
    }

    .kunm {
        width: 9.35rem;
        padding: 0.4rem 0;
        border: 1px solid #3b4fde;
        border-radius: 0.4rem;
        margin: 0 auto;
        margin-top: 0.43rem;
        position: relative;

        .goud {
            position: absolute;
            background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABwAAAAcCAYAAAByDd+UAAAAAXNSR0IArs4c6QAAAvZJREFUSEu9lt9Lk2EUx7/n2cyIIFbbQCla2U+6SrchNiJ19gMVQjCIbgT9A7ookK68iijpHyihi7pJisjKfpiKTEdzm3dSoNVV4t6hBK7wx54Tz+ve4TbnXjfyvT0/Ps9z3u85zyEU+Bg9QvNM+AiylUF1AFcxYFNhBCwCNEvgCYYYcEzWBQg9cquUlM+og9yBDhDuAKgqdLCUfRaMu46w70k+8KbAeM3FU5KST0FUYxKU7RYVUtywRz5+zTbkAGPuhisQ4jkYe4uErYcRliDlNWd4eHBjngyg5vU3S8ZLAnaVBEsFM7AiCG2O0NBbI18aqJfRIidLvlluDZdEUniM8upAbm+3aD8WvpTwzwoVJOxw2Wqpvz+pA2Pexk4wPS4UVZKduMsZ+txHuvy9gW9gHCsp4XpwEBDdxLKaCQ/XWzX1EWYcoaETpLn9F5gwUjqMx5BINjunR5f0qrn90yCczlAoo540r/8+M26XAmTwcFLuaa2MDPxReeY89WcssEQAlGe1RC/FPP4AgHPFA+nTmtx91YDpahdyGEDFJjnHFXAegDNHzYxXIAowcBPgg3kO9H7Zmmg7FAz+VfYCMOUyr4ArAMqyEsYcLlulkvFc7WWXJbk6ApArw4fw7vdCWdvxmcFlkzDltpoPmEiulR2pmBrUlNevsw2HrVYaNaAMvHYmbO003a8Oa+Zmxll14KYlBRAtt1qa9gU/LGRBo46E7XoRsHRJtxJNBlSruVRhRzxOkcjqNm9m3HCcNI//AQO3tlBpBtTwMyGQnJQE9Jpt/AxoMTBFJ9X42xhtUyy4QwAHWNKzPH2Wv1D6aPOd1Ged5m3qYuZHxTe/iUhjeCtX/Xn6uRgCUG0itBiXqMNl86afp7Ti/scDDCSEFO6MB9g4suZtbJFML3ZkxTCgO7pEZfWYUmKx/9T8mmhAVbvEPYFOBroBHDWplO8E3LNP+vq2tQhvTK6D3YHzkriF9FVf38L3p3zUnJ1l8IRgemMP+8YKrfr/AEvRXm8MSklIAAAAAElFTkSuQmCC) no-repeat 50%;
            background-size: 100%;
            width: 0.37rem;
            height: 0.37rem;
            right: 0.24rem;
            top: 0.37rem;
        }

        h3 {
            font-size: .37rem;
            color: #000;
            font-weight: 550;
            text-align: center;
        }

        input {
            width: 8.94rem;
            height: 1.07rem;
            border: 0.0266rem solid #ccc;
            border-radius: 0.133rem;
            display: block;
            background: transparent;
            margin: 0 auto;
            margin-top: 0.266rem;
            padding-left: 0.4rem;
            font-size: .37rem;
            color: #000;
        }

        p {
            text-align: center;
            font-size: .37rem;
            color: #999;
            margin-top: 0.35rem;
        }
    }
}

.queren {
    width: 100%;
    height: 1.31rem;
    background: linear-gradient(-55deg, rgb(80, 122, 250),rgb(115, 131, 251));
    position: fixed;
    left: 0;
    bottom: 0;
    text-align: center;
    line-height: 1.31rem;
    color: #fff;
    font-size: .43rem;
}
</style>