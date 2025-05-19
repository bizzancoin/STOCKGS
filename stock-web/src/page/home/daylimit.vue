<template>
    <div>
        <div class="dabg">
            <div class="gebgh">
                <div>
                    <h2><span class="hbnh" @click="$router.go(-1)"><a class="fan"></a></span> 每日涨停 <a class="shaux" @click="stockgetztb"></a>
                    </h2>
                </div>
            </div>
            <div class="boxkl">
                <ul class="ulkm" style="display: block !important; margin-top: 0px !important;">
                    <li v-for="(item,index) in zhangtingList" :key="index">
                        <div class="pkl">
                            <p>股票名称：<span>{{item.n}}</span></p>
                            <p>股票代码：<span>{{item.c}}</span></p>
                        </div>
                        <p class="dangeh"> 换手率（%）： <span class="heihk">{{(item.hs ? item.hs.toFixed(2) : 0.00)+'%'}}</span></p>
                        <p class="dangeh"> 涨跌幅： <span>{{(item.zdp ? item.zdp.toFixed(2) : 0.00)+'%'}}</span></p>
                        <p class="dangeh"> 最新价：<span>{{(item.p / 1000).toFixed(2)}}</span></p>
                        <p class="dangeh"> 成交额：<span>{{item.amount | toWanYi}}</span></p>
                        <p class="dangeh"> 流通市值： <span>{{item.fund | toWanYi}}</span></p>
                        <p class="dangeh">总市值：<span>{{item.tshare | toWanYi}} </span></p>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</template>
<script>
import { Toast } from 'vant';
import { MessageBox } from 'mint-ui'
import * as api from '@/axios/api'
export default {
    props: {},
    data() {
        return {
            zhangtingList: [],
        }
    },
    mounted() {
        this.stockgetztb()
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
        async stockgetztb() {
            var opt = {}
            let data = await api.stockgetztb(opt)
            if (data.status === 0) {
                this.zhangtingList = data.data
            } else {
                Toast(data.msg)
            }
        },
    }
}
</script>
<style lang="less" scoped>
.dabg {
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

            .shaux {
                position: absolute;
                width: 0.48rem;
                height: 0.48rem;
                background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACQAAAAkCAYAAADhAJiYAAAAAXNSR0IArs4c6QAAA8VJREFUWEfNmEloXVUYx3//e18qCWitA4JUFLEURdOotcWpEKsLFbUVBONKKYIbRcEJ3VhwarvSiAspVlzoRpyow0Lr0No2LbW4cACllCK2DhREjWhy71/OzU087/W9vPuSl5K7u2f4zu9+9xuPmGeP5hkPlYFsLyTjBsQgZgBxDrAQMPA75gBiP+YTUrZK+nsmH9sWyPZSch4GhoDeiof8CbxCwiZJhyruKZa1BLLdR86TwL1ArROh0dqgpY0kPCVprIqMpkC2l5DzJnBhEyGjiBHMt8DRcv5UxPmYlU21aPaQslbST+2gjgGyfSk5HwCn1202O4DnSXlX0j/NBNvuJeMW4D7E5Q1rfiRhUNIP00HVAdk+j5ydDTBHMPeopnfafV0873HfjhgGTovGA9QKSYdbyZoCKm1mpO43mRFSbpb0Sycwk2ttLyZnK7Bsar/ZScoqSVkzmf8DZd4EPBhtDDCrJf01E5gIahE5nwEXRXIeUaqNLYFKI/4a6CkXHSbhYkk/zwYmgjqLnK+AReXYHyQsaSa/0JAzvwzcFWlnrWp6uxswU1DjvhOxJZK5QakebTxDtk8k5wjQV0yaHarp6m7CFGLthJwvI3v6jYQzG+OTSm94PdLObarpjW4DFVDjXofYPCU75zr16KP4LDnzS8Dd5eAoCae0ijOzhbQdDPxXIC1lPa1Uj9cDjXs3KiJsSCTblGj1bA+ebr8z7wcGyvM+VKLrGzUUiCeD1wtKFXJX154ivsEJkUlsxtxavn9HwhWNQCENLCgH1yvVE12jCXYz5kESPp4ukUfnbQk2NKdAhTFnfq7Ib9M/h0joD0Bz+stKl+8lJ9jO0hZMJuda9WhbcPvjYtS2V5LzReRhMduwUhUaPL5unzkUfHVuDnxPwoCk0QmgcQ8hXou8YO4Co72AnD1RtM5IuErS7snzm6WO7appVTc9LZZlu5+cvaVnP6NUj9W5fekFIZyvi7S0ptOCrJMPcOaQVIdIuEzSv8cCTVSK38xV+dEIazukjrMlHWiciwu0UDA9FGmpKwVaJ5orjHpyQ1nC7gL6G6BmU8KeCyyW9HlVsDkp8m2LjDsQLwJjJCyXdLAKVLM26BJy3gfOqBNgtgPDbdqgPjLWAA8glkf795FwZZWyplWjGIz8rY4aRXMBYkWLRnEvKTdVqdHbtdLrgftn0UqHxL2hbKXr3LvV76ty2RDa6om4Uf2yIbROr5LwbNcuG5rEjpPIuBFxDWZZeR1zcumpRzEHEfswn5Ly3kz7ubYaquIZ3Vwz74D+A2+DgOcUuMpOAAAAAElFTkSuQmCC) no-repeat 50%;
                background-size: 100%;
                right: 0.4rem;
                top: 0.4rem;
            }
        }
    }

    .boxkl {
        width: 100%;
        border-radius: 0.4rem 0.4rem 0 0;
        margin-top: -0.8rem;
        background: #fff;
        padding-top: 0.4rem;
        min-height: 1.07rem;

        .ulkm {
            li {
                width: 9.37rem;
                padding: 0.4rem 0;
                background: #ebe4fa;
                border-radius: 0.13rem;
                margin: 0 auto;
                margin-bottom: 0.266rem;

                .pkl {
                    width: 8.84rem;
                    margin: 0 auto;
                    display: flex;
                    justify-content: space-between;

                    p {
                        color: #1a1a1a;
                        font-size: .35rem;
                        width: 4rem;

                        span {
                            color: #5d7dfb;
                        }
                    }
                }

                .dangeh {
                    width: 8.84rem;
                    margin: 0 auto;
                    margin-top: 0.266rem;
                    font-size: .35rem;
                    color: #1a1a1a;
                    display: flex;

                    span {
                        color: #5d7dfb;
                    }
                }
            }
        }
    }
}
</style>