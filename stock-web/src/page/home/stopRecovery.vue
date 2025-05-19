<template>
    <div>
        <div class="dabg">
            <div class="gebgh">
                <div>
                    <h2><span class="hbnh" @click="$router.go(-1)"><a class="fan"></a></span> 每日停复牌 <a
                            class="shaux" @click="tingfuList = [];stockgetStop()"></a>
                    </h2>
                </div>
                <div class="sou">
                    <div>
                        <div class="sousuo" @click="getsearch">
                            <span></span>
                            <input placeholder="名称/代码/拼音首字母" disabled="disabled">
                        </div>
                    </div>
                </div>
            </div>
            <div class="topTen">
                <div class="let" v-for="(item, index) in paihangList" :key="index" @click="gettab(index)"
                    :class="paihangIdx == index ? 'xuan' : ''">{{ item.name }}</div>
                <span class="pokl" :style="paihangIdx == 0 ? 'left: 25%;' : 'left: 75%;'"></span>
            </div>
            <div class="ulkm"><span class="spl1">股票代码</span><span class="spl2">停牌日期</span><span
                    class="spl3">停牌截止时间</span>
            </div>
            <ul class="uls">
                <li v-for="(item,index) in tingfuList" :key="index">
                    <div class="lile">
                        <h6>{{item.SECURITY_NAME_ABBR}}</h6>
                        <p>{{item.SECURITY_CODE}}</p>
                    </div>
                    <p class="lice">{{item.SUSPEND_START_DATE}}</p>
                    <p class="lice">{{item.SUSPEND_END_TIME ? item.SUSPEND_END_TIME : '--'}}</p>
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
                    name: '停牌查看',
                },
                {
                    name: '复牌查看',
                }
            ],
            paihangIdx: 0,
            tingfuList: [],
        }
    },
    mounted() {
        this.stockgetStop()
    },
    methods: {
        getsearch() {
            this.$router.push({
                path: '/Searchlist'
            })
        },
        gettab(index) {
            this.paihangIdx = index
            if (index == 0) {
                this.stockgetStop()
            } else {
                this.tingfuList = []
            }
        },
        async stockgetStop() {
            let data = await api.stockgetStop()
            if (data.status === 0) {
                this.tingfuList = data.data
            } else {
                Toast(data.msg)
            }
            this.$store.state.user = this.user
        },
    }
}
</script>
<style lang="less" scoped>
.dabg {
    background: #fff;

    .gebgh {
        height: 3.34rem;
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

        .sousuo {
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
                margin-left: 0.19rem;
                width: 7.48rem;
                background: transparent;

                &::placeholder {
                    color: rgb(213, 204, 255);
                    font-size: 0.38rem;
                }
            }
        }
    }

    .topTen {
        display: flex;
        justify-content: space-between;
        border-radius: 0.4rem 0.4rem 0 0;
        margin: 0 auto;
        margin-top: -0.8rem;
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
            font-size: .38rem;

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

    .ulkm {
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
            width: 25%;
        }

        .spl2 {
            width: 35%;
            text-align: center;
        }

        .spl3 {
            width: 30%;
            text-align: center;
            margin-right: 0.32rem;
        }
    }

    .uls {
        width: 100%;
        list-style: none;

        li {
            height: 1.42rem;
            border-bottom: 0.03rem solid #e0e0e0;
            display: flex;
            justify-content: space-between;

            .lile {
                margin-left: 0.32rem;
                width: 25%;

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
                width: 35%;
            }

            span {
                text-align: center;
                color: #1a1a1a;
                font-size: .32rem;
                line-height: 1.42rem;
                width: 30%;
                margin-right: 0.32rem;
            }
        }
    }
}
</style>