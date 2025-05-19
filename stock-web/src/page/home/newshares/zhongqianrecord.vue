<template>
    <div>
        <div class="dabg">
            <div class="gebgh">
                <div>
                    <h2><span class="hbnh" @click="$router.go(-1)"><a class="fan"></a></span> 中签记录 </h2>
                </div>
            </div>
            <div class="jilkm" v-for="(item, index) in zhongqianList" :key="index">
                <div class="namkl">
                    <div class="nlt">
                        <h6>{{ item.newName }}</h6>
                        <p>
                            <span v-if="item.newType == '深' || item.newType == '创'">深</span>
                            <span class="sh" v-if="item.newType == '沪' || item.newType == '科'">沪</span>
                            <span class="bj" v-if="item.newType == '北'">北</span>
                            <a
                                :class="(item.newType == '沪' || item.newType == '科') ? 'shbg' : item.newType == '北' ? 'bjbg' : ''">{{ item.newCode }}</a>
                        </p>
                    </div>
                    <div class="rlt"> 申购价格 <span>{{ item.bond }}</span></div>
                    <div v-if="item.type == 1 && item.status == 3" @click="getrenjiao(item.id)" class="renjiao">认缴</div>
                </div>
                <div class="gnj"></div>
                <div class="plkm">
                    <p><span>申购数量</span><a>{{ item.applyNums }}</a></p>
                    <p><span>申购单价</span><a>{{ item.buyPrice }}</a></p>
                    <p><span>申购配号</span><span>{{ item.orderNo }}</span></p>
                    <p><span>申购时间</span><span>{{ item.addTime }}</span></p>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import * as api from '@/axios/api'
import { MessageBox } from 'mint-ui'
import { Toast } from 'mint-ui'
export default {
    components: {

    },
    props: {},
    data() {
        return {
            zhongqianList: [],
        }
    },
    mounted() {
        this.getzqjl()
    },
    methods: {
        getrenjiao(val) {
            MessageBox.confirm(this.$t('hj251') + '?', this.$t('hj165'), {
                confirmButtonText: this.$t('hj161'),
                cancelButtonText: this.$t('hj106'),
            }).then(async () => {
                let opt = {
                    id: val
                }
                let data = await api.submitSubscribe(opt)
                if (data.status == 0) {
                    Toast(data.msg)
                    this.getzqjl()
                } else {
                    Toast(data.msg)
                }
            }).catch(() => {

            });
        },
        async getzqjl() {
            // 获取用户信息
            let data = await api.getzqjl();
            if (data.status == 0) {
                this.zhongqianList = data.data;
            }
        },
    },
}
</script>
<style lang="less" scoped>
.dabg {
    background: #ffffff;

    .gebgh {
        height: 1.17rem;
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

        .fourg {
            width: 7.34rem;
            margin: 0 auto;
            display: flex;
            justify-content: space-between;
            height: 0.8rem;
            position: relative;
            margin-top: 0.8rem;

            .xian {
                border-top: 0.0266rem dashed #fff;
                position: absolute;
                left: 0.4rem;
                width: 6.54rem;
                top: 0.4rem;
            }

            .fouda {
                width: 0.8rem;
                position: relative;

                .danhe {
                    width: 0.8rem;
                    height: 0.8rem;
                    background: hsla(0, 0%, 100%, .2);
                    border-radius: 0.4rem;
                    text-align: center;

                    span {
                        width: 0.53rem;
                        height: 0.53rem;
                        background: #fff;
                        display: inline-block;
                        margin: 0 auto;
                        margin-top: 0.13rem;
                        border-radius: 50%;
                        text-align: center;
                        color: #ea3544;
                        font-size: .43rem;
                        line-height: .54rem;
                    }
                }

                p {
                    text-align: center;
                    color: #fff;
                    font-size: .32rem;
                    margin-top: 0.13rem;
                }
            }
        }
    }

    .jilkm {
        padding-top: 0.4rem;
        border-bottom: 10px solid #f5f5f5;

        .namkl {
            width: 9.35rem;
            margin: 0 auto;
            margin-bottom: 0.4rem;
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
                font-sizE: .38rem;
                color: #666;
                margin-top: 0.266rem;

                span {
                    color: #d73d3d;
                    margin-left: 0.08rem;
                }
            }

            .renjiao {
                width: 1.6rem;
                height: 0.67rem;
                background: linear-gradient(-55deg, rgb(80, 122, 250),rgb(115, 131, 251));
                border-radius: 0.33rem;
                color: #fff;
                font-size: 0.32rem;
                text-align: center;
                line-height: 0.67rem;
            }
        }

        .gnj {
            height: 2px;
            width: 100%;
            background: #e0e0e0;
        }

        .plkm {
            display: flex;
            justify-content: space-between;
            width: 9.35rem;
            margin: 0 auto;
            flex-wrap: wrap;
            padding-bottom: 0.4rem;

            p {
                width: 48%;
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
}
</style>