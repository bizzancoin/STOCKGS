<template>
    <div>
        <div class="dabg">
            <div class="gebgh">
                <div>
                    <h2><span class="hbnh" @click="$router.go(-1)"><a class="fan"></a></span> 十大成交股 <a
                            class="shaux" @click="chengjiaoList = [];stockgetTop()"></a></h2>
                </div>
            </div>
            <div class="topTen">
                <div class="let" v-for="(item, index) in paihangList" :key="index" @click="paihangIdx = index;stockgetTop()"
                    :class="paihangIdx == index ? 'xuan' : ''">{{item.name}}</div>
                <span class="pokl" :style="paihangIdx == 0 ? 'left: 25%;' : 'left: 75%;'"></span>
            </div>
            <div class="erfg">
                <span class="spl1">股票</span>
                <span class="spl2">收盘价</span>
                <span class="spl2">涨跌额</span>
                <span class="spl3">排名</span>
            </div>
            <ul class="ulkm">
                <li v-for="(item,index) in chengjiaoList" :key="index">
                    <div class="lile">
                        <h6>{{item.SECURITY_NAME}}</h6>
                        <p>{{item.DERIVE_SECURITY_CODE}}</p>
                    </div>
                    <p class="lice">{{item.CLOSE_PRICE}}</p>
                    <p class="lice">{{item.CHANGE_RATE ? (item.CHANGE_RATE).toFixed(2) : '0.00'}}</p><span>{{item.RANK}}</span>
                </li>
            </ul>
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
            paihangList: [
                {
                    name: '沪市排行',
                },
                {
                    name: '深市排行',
                }
            ],
            paihangIdx: 0,
            chengjiaoList: [],
        }
    },
    mounted() {
        this.stockgetTop()
    },
    methods: {
        async stockgetTop() {
            var opt = {
                content: this.paihangIdx == 0 ? '1' : '3',
            }
            let data = await api.stockgetTop(opt)
            if (data.status === 0) {
                this.chengjiaoList = data.data
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

    .topTen {
        display: flex;
        justify-content: space-between;
        border-radius: 0.4rem 0.4rem 0 0;
        margin: 0 auto;
        margin-top: -0.95rem;
        background: #fff;
        padding-top: 0.19rem;
        padding-bottom: 0.37rem;
        position: relative;

        .let {
            width: 50%;
            height: 0.8rem;
            text-align: center;
            line-height: 0.8rem;
            color: #666;
            font-size: .37rem;

            &:first-child {
                border-right: 2px solid #e0e0e0;
            }
        }

        .xuan {
            color: #5d7dfb;
        }

        .pokl {
            position: absolute;
            width: 0.53rem;
            height: 0.11rem;
            background: #5d7dfb;
            border-radius: 0.053rem;
            bottom: 0.32rem;
            margin-left: -0.266rem;
            transition: all .5s;
        }
    }

    .erfg {
        width: 100%;
        height: 0.88rem;
        border-top: 0.0266rem solid #e0e0e0;
        border-bottom: 0.0266rem solid #e0e0e0;
        display: flex;
        justify-content: space-between;

        span {
            color: #999;
            font-size: .32rem;
            line-height: .88rem;
            width: 22%;
        }

        .spl1 {
            margin-left: 0.32rem;
        }

        .spl2 {
            text-align: center;
        }

        .spl3 {
            width: 10%;
            text-align: center;
            margin-right: 0.32rem;
        }
    }

    .ulkm {
        width: 100%;
        list-style: none;

        li {
            height: 1.42rem;
            border-bottom: 0.03rem solid #e0e0e0;
            display: flex;
            justify-content: space-between;

            .lile {
                margin-left: 0.32rem;
                width: 22%;

                h6 {
                    color: #1a1a1a;
                    font-size: .37rem;
                    font-weight: 500;
                    padding-top: 0.266rem;
                    font-weight: 600;
                }

                p {
                    color: #666;
                    padding-top: 0.266rem;
                    font-size: .32rem;
                }
            }

            .lice {
                text-align: center;
                color: #1a1a1a;
                font-size: .32rem;
                line-height: 1.42rem;
                width: 22%;
            }

            span {
                text-align: center;
                color: #1a1a1a;
                font-size: .32rem;
                line-height: 1.42rem;
                width: 10%;
                margin-right: 0.32rem;
            }
        }
    }
}
</style>