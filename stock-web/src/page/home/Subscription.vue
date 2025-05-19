<template>
    <div>
        <div class="heabox">
            <div class="toubu">
                <span class="fanhui" @click="$router.go(-1)"></span>
                <van-tabs v-model="tabactive" class="ghbn" @click="getNewsList" color="#ffffff"
                    title-inactive-color="#ffffff" title-active-color="#ffffff"
                    background="linear-gradient(-55deg, rgb(80, 122, 250),rgb(115, 131, 251))">
                    <van-tab title="新股申购" name="1"></van-tab>
                    <van-tab title="新股抢筹" name="2"></van-tab>
                    <van-tab title="新债申购" name="3"></van-tab>
                    <!-- <van-tab title="新债配售" name="4"></van-tab> -->
                    <van-tab title="大宗交易" name="4"></van-tab>
                    <van-tab title="VIP抢筹" name="5"></van-tab>
                </van-tabs>
            </div>
            <div class="suo" @click="getsearch"><a class="tr"></a></div>
        </div>
        <div>
            <div class="xuanhze">
                <xingusg v-if="tabactive == 1"></xingusg>
                <xinguqc v-if="tabactive == 2"></xinguqc>
                <xinzhaisg v-if="tabactive == 3"></xinzhaisg>
                <!-- <xinzhaips v-if="tabactive == 4"></xinzhaips> -->
                <dazongjiaoyi v-if="(tabactive == 4)"></dazongjiaoyi>
                <vipqiangchou v-if="(tabactive == 5)"></vipqiangchou>
            </div>
        </div>
    </div>
</template>
<script>
import * as api from '@/axios/api'
import xingusg from './listcomponents/xingusg.vue'
import xinguqc from './listcomponents/xinguqc.vue'
import xinzhaisg from './listcomponents/xinzhaisg.vue'
import xinzhaips from './listcomponents/xinzhaips.vue'
import dazongjiaoyi from './listcomponents/dazongjiaoyi.vue'
import vipqiangchou from './listcomponents/vipqiangchou.vue'
export default {
    components: {
        xingusg,
        xinguqc,
        xinzhaisg,
        xinzhaips,
        dazongjiaoyi,
        vipqiangchou,
    },
    props: {},
    data() {
        return {
            tabactive: 1,
        }
    },
    mounted() {
        this.tabactive = this.$route.query.idx
    },
    methods: {
        getNewsList() {

        },
        getsearch() {
            if(this.tabactive == 5){
                this.$router.push({
                path: '/VipSearchlist'
            })
            }else{
                this.$router.push({
                path: '/Searchlist'
            })
            }
            
        }
    },
}
</script>


<style lang="less" scoped>
/deep/ .van-tabs--line .van-tabs__wrap {
    height: 1.2rem !important;
}

/deep/ .van-tab {
    font-size: 0.36rem !important;
}

.heabox {
    height: 3.6rem;
    background: linear-gradient(-55deg, rgb(80, 122, 250),rgb(115, 131, 251));

    .toubu {
        width: 100%;
        display: flex;

        .fanhui {
            width: 0.266rem;
            height: 0.43rem;
            background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABIAAAAgCAYAAAAffCjxAAAAAXNSR0IArs4c6QAAAVdJREFUSEut1usqBVEYxvH/c1MuRJJDkhwTEpIkJUlyCEk++CJJckpycw49Gs3Wa+y9Z82ePd/Xr5n38KwRNR7bE0APMK1OHdtTwAWQGZcdQbZngPMc+QImK0O2Z4GzgIxLuqoE2Z4DTgMyJuk6K08yZHseOAnIqKSbRo2TINsLwHFARiTdxkaVQrYXgaOADEu6K3a7LWR7CTjMD2XdaYq0rZHtFWA/Rz6BIUn3reau6RvZXgX28kMfOfLQbnj/QbbXgN380HuOPJZtwB/I9jqwE5ABSc9lyJ8a2d4AtgPSL+klBfmFbG8CWwHpk/SaivxAtjMgg7Inq0mvpLcqSHehTOvKpzU+oSvFDlj99gesOJCDkp7Kip+yIknT3XL7C0tbum9lMbIMHKQkQEqwJWVSKZTPWTElq0dt6GYxt6uHf8DqX0cBq39BBqz+lR2w+j8RAfv9rfkGqF24CUdT9E4AAAAASUVORK5CYII=) no-repeat 50%;
            background-size: 100%;
            display: inline-block;
            vertical-align: middle;
            margin-top: 0.45rem;
            margin-left: 0.32rem;
        }

        .ghbn {
            width: 100%;
        }
    }

    .suo {
        width: 9.35rem;
        height: 0.93rem;
        background: hsla(0, 0%, 100%, .1);
        border-radius: 0.13rem;
        margin: 0 auto;
        margin-top: 0.19rem;
        border-top: 0.0266rem solid transparent;

        .tr {
            width: 0.43rem;
            height: 0.43rem;
            display: block;
            background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAAXNSR0IArs4c6QAAA4dJREFUWEe9l0msn2MUxn8PYl4QolhIKhYS00JalAiCIIbeBg1FSrsTQxcsGrEo3WBR0pWZkjSGVEpMiSkiTRPTgkhKYiaGiilmHnna8/H1/r//4N6/+27u/X/vec953nPOc855xZBle39gAXAycCiwH7Ar8C3wIfAa8DTwhKSfh+mbvK9+B2wfCKwAFgI7jKD4G+BW4BZJP40gv0WkB4DtfLsaWAns0lL0MfAq8CnwA7AbcAhwHLBTS+4D4CJJr4wCYhsAtrcH7gQW12EDj9atNnYptL07MAEsBw4umd+ByyQ9MAzEPwDq5vcHfR36qG7y8jAl2be9HXANcGOF7M86v3bQ+TaAK4DbSjiuPk3S5lGMt2Vsnwo8DuwIJBfmSnq7n54tAGzPBiKUmCfWR0r66r8ab+Rtnw08VjmW0M2T9FeXvgbAmnJ9Yn6SpBenarwFYhVwVf1eKOmhTgC2ZwGfVNzWSQrnp71s7wG8D+TvBknz+gG4HFhdmydIemna1hsK2akLV9bP2ZJC0W2WbK8D5gOJ+SxJCcNYlu3jgeZCSyTd3QXgXeAgYL2kc8Zi+V8PJKl/BELRVZKWdQGIQKraakmh4liX7c+qf6yVdEEXgMblN0u6dqzWt1I8DeuAfh5ODqSJ7AncJWnp/wCg0b9G0iVdHngTOGIQVaYKyva+wOd1fqWk67oApPksAX4D9pKUnBjLsn0h8GApm5CU6thDw3OBh+vrpZLuHYv1rfF/LpUV+LUo/l0XgJ2r/u8NvAMcLumP6YKwPRdoWngnA2Kj6QWJzQ1l9HpJzf9TwmE7A0o6aka4sCzN7Y0uZQ2AzHhvAemK6eMLJK2fivWaC+5rzRUD2dWeB44Fnq8+npgtHWWiaYO0nXDeDlxc3zcBcyR93+8yk0eyRUCmopTOuC4MWS7p62HesH0UcAdwWMl+kXlR0nuDznYNpecD99TonbOhZagUpmxsaFquToU7BTivxvZGX/rLGZmGgHTbFZKe6ZsDkzdsJ3nSueZ0HPoSCEv26RjX47WAXxa3294AHF01Zr6kp3po2M89dcN4I/38mCEhSM6kyNwk6fVG1vbp9T3zYQpdD4i+D5NJyZVHyokV37yU8lD5pSae0O0FSXkp9SzbZwGPVHL3gBgJwLAEHLY/CMSMAAjADhDpDU/OGIACcWa9tJqcmJhRAB0gNs84gBaIFLxn/wb6PUsFkO1mDQAAAABJRU5ErkJggg==) no-repeat 50%;
            background-size: 100%;
            margin-left: 0.266rem;
            margin-top: 0.24rem;
        }
    }
}

.xuanhze {
    width: 100%;
    background: #fff;
    margin-top: -0.32rem;
    min-height: 1.07rem;
    border-radius: 0.4rem 0.4rem 0 0;
    padding-top: 0.35rem;
}
</style>