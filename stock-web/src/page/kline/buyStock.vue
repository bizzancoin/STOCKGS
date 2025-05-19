<template>
    <div>
        <div class="headf"><span @click="$router.go(-1)"></span>
            <div class="wenebn">
                <h6>{{name}}</h6>
                <p class="">{{nowPrice}}</p>
                <!-- <p class="">{{ currentItem.p }} {{ currentItem.zdp ? currentItem.zdp.toFixed(2) + '%' : '0.00%' }}</p> -->
            </div>
        </div>
        <div style="height:1.5rem">

        </div>
        <div class="goumais">
            <div class="xiaoge"><span><img
                        src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAABEklEQVQ4jaVSwUoDMRScl2alsIuIB89C9Su8iHqrWryKFy12Tz36cd79BfFiS13woKAfINuMpG7SbGjL2r5bJvMmM0Ow6ci6++eDIifwoNc2ILIvIh3VlH9287IdCcwCNBLo5cVxmmZf3fu3g0ABBJoJALJrAK1arR2H2GUjSxxc5cXR6fVz4s6mWvizXWFiHSwQuOiPDw35lGZZL3zNZZ5jAi50INK2ZBJbAeYzewHn4PJudNK9fd2Lyaau6jOHDixHKa0fkyQZhmSfOSqMQQRTubAfSRPQ4YUsKSzEfIS43VWFhQ6coIrJqwqr9TLDAEViPCUnDi/L6QeBgkp5zBDvBvj+KcvPOcYJyVGt638PgF/kfWPjHkHUbAAAAABJRU5ErkJggg==">数量</span>
                <div class="rtyh">
                    <div class="kunk"><input placeholder="请输入" v-model="buyNum"
                            @input="buyNum = buyNum.replace(/^(0+)|[^\d]+/g, '')" type="number"><a class="shou">手</a>
                    </div>
                </div>
            </div>
            <p class="shougu">1手=100股</p>
            <div class="xiaoge">
                <span class="eduleft"><img class="eduleftimg"
                        src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAABp0lEQVQ4jZXRTUsbURgF4HPeSZuIm9goFOpXQrtrQaiICG4KGVGxwU1WwYWgpeBPKCn1X2jSrHQjbiJaYUoX7nXlQhAUg13ED2bcuTH3lTstbarNtJ6Bgbnc59yXO0REcrNHgxTZpkgnQGj4AErL7Df2WhZYLI7jgdxTReUveATkQiwK2xMCP3i7sz5wPTl71GXxl8+ZC7tnYq5GEVmQSBxchTg3XytKLFZ3nFh96t3px3Djz0n4L5wtHKTa2tvPAC4a1YYCiw1jnoo4WYqsShS2648TCdqTjKqAdCiixijD+wAgUdhmq5y+NKpFkB9AFo3RT14lff7jQgWMws15M3OYiscT3C71Xg7n99s6Up0bDdUBTs+d1EF+9/1gtBVujsXJJ6kNJV/fGOOKAlVVvEp2JN2H4m9Lz3bF876+V+iKAmtT87XcQ3D4N+0r0bcs2bGxEsCCAvnN5d7q/+BfBTbx/rJkXbekQAFkfmupJyyJwn8U2DzqL4vbNIkfBF4UvlcQlqQrvycRZ98AmVbYxrm7YK6qeuy/3Mw8f9Gt5NCNMeOtMADcAijV/2tFJVE6AAAAAElFTkSuQmCC">可用额度</span>
                <div class="eduright">
                    <a class="ertyu">￥{{ userinfo.userAmt }}</a>
                </div>
            </div>
            <div class="rtyh">
                <!-- <div class="fangxi">
                    <a class="fanh" :class="buyType == 0 ? 'xuk' : ''" @click="buyType = 0">买涨</a>
                    <a class="fanh" :class="buyType == 1 ? 'xuk' : ''" @click="buyType = 1">买跌</a>
                </div> -->
            </div>
            <!-- <div class="xiaoge">
                <span class="eduleft"><img
                        src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAB7UlEQVQ4ja2SwWsTQRjF3zc7MbuJpQgFD1LbIIIHQbwKFtEexGJ7iKigUovm6klP/gEG6s2LJUgPWulBRawH0cQUMYqHCiUVFEPSePNiEsWa2t15sjGpW8mKgu8wwwzzfvO9b0aOnS9dIDm/XK0WF7LDxD/KB6xCZBMgHw35FJCs63m5B9M7q3+DkrGzb+NaR+5AJEJihcB+EdVLoESRLMnct2Yz//jmrk9dAf6QTJVnACnezSTSe4fzamBwcA9FDgKYhMgyRLYD8to1JqcsK1ur1wvPZ3c3A4DKDIHivUwi3SGPTJR6dSRSX3PdrZalt0CpowRGIbIPkO8ECi55WSVTlbQhRwG5NHaufKoDaHVTpHXHXGbg3dxU/9WHU/1DJC4SKHvAGkT1ac+YW0pZ875BLOvNr3A/zea3zARcAyw8ur5t3F/r+zd2LAFY6tYe06mkrcOpD1EqtRmQ6NBEpefZdOKLDnse36zWK2kjtT5OyBWKwHZi/tZJFQYI9qCjwouXt2uNhlOrNxyxrDOtCGEA23HoeWZDhM+LSe/VIrzgudAKms1VzzebQIRuCgUYMu6bCYn/CbABf2j8fY9tO7MEDlCpGCHwG2aIFQPkldYnnlzr+xoaIRq1T0PkSNDcnmNQasQzXP9o/0cAfgDTaMAiD+M0wAAAAABJRU5ErkJggg==">选择杠杆倍数</span>
                <div class="rtyh" @click="showPicker = true">
                    <div class="kunk">
                        <input placeholder="请选择" disabled="disabled" v-model="lever" class="canwei">
                        <img class="gangganicon"
                            src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAsAAAAGCAYAAAAVMmT4AAAAAXNSR0IArs4c6QAAAGRJREFUKFNjnDFjhiUjI2Pr////GRlwAEZGxpcsLCypYAUzZ87MYWBgmMTAwIBNw8v///87ZWRkXINL4tAAVwgyFMUkNA0oCjEUIzmp+v///84gq5G9gdVT/f39AoWFhR/Q/QsATGgt64otJQ8AAAAASUVORK5CYII=">
                    </div>
                </div>
            </div> -->
            <div class="xiaoge">
                <span class="eduleft"><img
                        src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAACEElEQVQ4jY1TPWhTURg9331vcdP6PzSpTtahi0InFS2CjakxEoNgFNrykqibTgpqdXDTRVAbWsRJfNBBGqKmFasoWBCEWEX8qfkRcWptHRTk3SPJa14ebah+cIcL55x7zvm4gqUjB/s+diplxAnEACGBEYe0R4c3TwKgn+EJRPs+bDcMs0o6DMhqTY4CYusqXam4iPRockaUsh1H29mhtleeQMz6/JhEJ0SyJOyZ2dkHEyPbfvlf2hF7vWJVS0u3iIo7ZBgik9nB1i64AtOM9n/qaBKn6YSsUkc4/bUWRWEhFIk//ytAVLFuelWvgstz0JOqnN2fLKcX4z0Hvj4RsYqnI8lSoH4PJ8tXCJwRpV7W8VpcvNnUgUg7RJ5GUpUuDZwkcEyTe3KDrQUskJU/Qq0DaTh4lM+nSIw7ZIHAUU3uzmUChUYH4uFdAZE5AGvrgN9FS08Xi0kCF0WpXblM4J3foDLNdZr44UUgOQaRBIBnddBUfiengKvNtyC9GnzY2ILIZWUYiQPJcugfy8DeE99jDlk9lzyB+5ngG615SgN2yCpFlyEfp8gdivRO3Nr43q3fN+FU5QiBIUBuz83Pn3t+d+vPGjH9bSVEXaPIIYdMPLm5IestbPEr3Va5XRnGsAMENTFAEVOUMeCQb5Vp9o9dX/PFj2/2nbFl3wsJtm1KUOQCRFED58dvrL+3BAjgL/OP2zMWLMK9AAAAAElFTkSuQmCC">支付保证金</span>
                <div class="eduright"><a class="ertyu"> ￥{{ (nowPrice * buyNum * 100 ).toFixed(2) }}</a>
                </div>
            </div>
            <!-- <div class="xiaoge">
                <span class="eduleft"><img
                        src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAAXNSR0IArs4c6QAAAbdJREFUOE+N0z9IG1EcB/Dv76URtGiXekEsgRCHunToXZrV5A7XIB0LzeTUreJQMnTRrYODCFUchDpauulwgSCK4AUXyVAIpVCKuUtSIdr8UfJ+5bB3TVLUu+3de78P3/vd7xEGHgaophofJSFLxJ3B/X9rYsUyH1HvAbe4qukbTDQHxlakaL69DXA040IpmqM+4BWDEBGMvS4o7gG2mo4LUOay3l2LfS+0XbQP6C0eb1Ze1kYmspIx7QKVZ7MPEZYZIbDNQFw5D/+k8m7HBwaLqVS6qiaMeQ+wNeMLETLepzCwGLHMDz7gaPobEK0yo0rA9c1BHgGwphTzuZqaSkqIDAS9Y5avScoj5aRQ9oGqZrwHsWy2O6u9DYueHjQI6LrvKmoqKYTI/Wq0Xz39enjR1wMXkITfbqzbf9n/O30J7gLqyeTYZTMc8ohoa7TZ18T7EjiaUWAg5gGCsDxumeuBEzjPZ6ZCHBrygFYY9pPjfD0wYCf0zwSK+gnAK4+t/KfAwJmWfvGAQsMecIXOt0lr/0dgwEnoGwAm/UFi2owUzZ3AwL2XyVb1BRK0BPDfKQw+DYqVH/sD+c0JIKaNn8sAAAAASUVORK5CYII=">手续费</span>
                <div class="eduright"><a class="ertyu">￥26600640.90</a></div>
            </div> -->
        </div>
        <div class="mail" @click="setBuy">买入</div>
        <van-popup v-model="showPicker" round position="bottom">
            <van-picker :columns="siteLeverList" @cancel="showPicker = false" @confirm="onConfirm" show-toolbar />
        </van-popup>
    </div>
</template>
<script>
import * as api from '@/axios/api'
import { Toast } from 'vant'
export default {
    components: {

    },
    props: {},
    data() {
        return {
            showPicker: false,
            currentItem: '',
            userinfo: '',
            settingdetail: '',
            siteLeverList: [],
            nowPrice: 0,
            buyType: 0, // 0 买入 1 卖出
            buyNum: '', //买入数量
            lever: '', //杠杆倍数
            name:'',
            code:'',
            id:'',
            buying: false,
        }
    },
    mounted() {
        if (this.$route.query.code) {
      this.code = this.$route.query.code
    }   
    if (this.$route.query.name) {
      this.name = this.$route.query.name
    }
    if (this.$route.query.id) {
      this.id = this.$route.query.id
    }
    if (this.$route.query.m) {
      this.nowPrice = Number(this.$route.query.m)
    }
        // this.currentItem = JSON.parse(decodeURIComponent(this.$route.query.item))
        
        this.getUserInfo()
        this.getuserSetting()
    },
    methods: {
        onConfirm(item) {
            console.log(item)
            this.showPicker = false
            this.lever = item.value
        },
        async getuserSetting() {
            var opt = {};
            var data = await api.getSetting(opt);
            this.settingdetail = data.data;
            this.siteLever = this.settingdetail.siteLever.split("/")
            this.lever = this.siteLever[0];
            this.siteLeverList = [];
            for (let i = 0; i < this.siteLever.length; i++) {
                let val = this.siteLever[i];
                let item = {
                    text: val + "倍",
                    value: val
                };
                this.siteLeverList.push(item);
            }
            this.buyNum = this.settingdetail.buyMinNum / 100;
        },
        async getbuyVipQc() {
            if (this.buyNum * 100 < this.settingdetail.buyMinNum) {
                this.$message.error('交易数量不能小于' + this.settingdetail.buyMinNum / 100 + '手');
                return
            }
            if (this.buyNum * 100 > this.settingdetail.buyMaxNum) {
                this.$message.error('交易数量不能大于' + this.settingdetail.buyMaxNum / 100 + '手');
                return
            }
            var opt = {
                buyNum: this.buyNum * 100,
                stockCode: this.currentItem.c,
                lever: 1,
                buyType: this.buyType,
            };
            var data = await api.buyVipQc(opt);
            if (data.status == 0) {
                this.$toast('下单成功');
            } else {
                this.$toast(data.msg);
            }
        },
        async getUserInfo() {
            // 获取用户信息
            let data = await api.getUserInfo();
            if (data.status === 0) {
                this.userinfo = data.data;
            }
        },
        //买卖
    setBuy() {
      if (!this.$store.state.userInfo.idCard) {
        Toast(this.$t('hj111'))
        this.$router.push('/smrz')
        return
      }
      if (this.buyNum * 100 < this.settingdetail.buyMinNum) {
                this.$message.error('交易数量不能小于' + this.settingdetail.buyMinNum / 100 + '手');
                return
            }
            if (this.buyNum * 100 > this.settingdetail.buyMaxNum) {
                this.$message.error('交易数量不能大于' + this.settingdetail.buyMaxNum / 100 + '手');
                return
            }
      // if (!this.agree) {
      //   Toast('需同意合作协议才能交易!')
      // } else if (isNull(this.selectNumber) && isNull(this.autoNumber)) {
      //   Toast('请选择购买手数')
      // } else if (isNull(this.selectType)) {
      //   Toast('请选择买卖方向')
      // } else if(isNull(this.subaccountNumber)) {
      //   Toast('请选择子账户')
      // } else {}
      if (this.buying) {
        return;
      }
      this.buying = true
      let opts = {}
      opts = {
        // stockId: this.detail.id,
        // buyNum: this.selectNumber ? this.selectNumber * 100 : 0, // 单位为手
        // buyType: this.selectType,
        // lever: this.selectCycle ? this.selectCycle : 0,
        // subaccountNumber:this.subaccountNumber
        //买入是买涨buyType:0, 卖出是买跌buyType:1,卖出的状态是0，买入的状态是1
        buyNum: Number(this.buyNum) * 100, // 单位为手
        //buyNum: (this.num.match(/\d+/g))[0] * 100, // 单位为手
        lever: 1,
        buyType :0,
        stockId: this.id,
      }
      this.gpBuy(opts)

    },
    //股票买入
    async gpBuy(opts) {
      opts.stockId = this.id
      let data = await api.buy(opts)
      this.buying = false
      if (data.status === 0) {
        Toast(data.data)
        this.getUserInfo()
        this.$router.push('/warehouse')
      } else {
        if (data.msg.indexOf('不在交易时段内') > -1) {
            Toast(this.$t('hj113'))
        } else {
            Toast(data.msg)
        }

      }
    },
    },
}
</script>
<style lang="less" scoped>
/deep/ .van-picker__cancel{
    font-size: 0.4rem;
}
/deep/ .van-picker__confirm{
    font-size: 0.4rem;
}
/deep/ .van-picker__toolbar{
    height: 1.3rem;
}
/deep/ .van-picker-column{
    font-size: 0.4rem;
}
.headf {
    height: 1.17rem;
    position: fixed;
    background: linear-gradient(-55deg, rgb(80, 122, 250),rgb(115, 131, 251));
    top: 0;
    left: 0;
    width: 100%;
    border-bottom: 1px solid #ececec;
    z-index: 33;

    span {
        position: absolute;
        width: 0.266rem;
        height: 0.45rem;
        background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABIAAAAgCAYAAAAffCjxAAAAAXNSR0IArs4c6QAAAVdJREFUSEut1usqBVEYxvH/c1MuRJJDkhwTEpIkJUlyCEk++CJJckpycw49Gs3Wa+y9Z82ePd/Xr5n38KwRNR7bE0APMK1OHdtTwAWQGZcdQbZngPMc+QImK0O2Z4GzgIxLuqoE2Z4DTgMyJuk6K08yZHseOAnIqKSbRo2TINsLwHFARiTdxkaVQrYXgaOADEu6K3a7LWR7CTjMD2XdaYq0rZHtFWA/Rz6BIUn3reau6RvZXgX28kMfOfLQbnj/QbbXgN380HuOPJZtwB/I9jqwE5ABSc9lyJ8a2d4AtgPSL+klBfmFbG8CWwHpk/SaivxAtjMgg7Inq0mvpLcqSHehTOvKpzU+oSvFDlj99gesOJCDkp7Kip+yIknT3XL7C0tbum9lMbIMHKQkQEqwJWVSKZTPWTElq0dt6GYxt6uHf8DqX0cBq39BBqz+lR2w+j8RAfv9rfkGqF24CUdT9E4AAAAASUVORK5CYII=) no-repeat 50%;
        background-size: 100%;
        left: 0.4rem;
        top: 0.37rem;
    }

    .wenebn {
        text-align: center;
        width: 8rem;
        margin: 0 auto;

        h6 {
            color: #fff;
            font-size: .4rem;
            padding-top: 0.133rem;
            font-weight: 500;
        }

        p {
            font-size: .32rem;
            color: #fff;
            margin-top: 0.133rem;
        }
    }
}

.goumais {
    width: 9.21rem;
    margin: 0 auto;

    .xiaoge {
        margin-top: 10px;
        display: flex;
        height: 1.50rem;
        justify-content: space-between;
        align-items: center;
        margin: 5px 0;

        span {
            display: flex;
            align-items: center;
            color: #333;
            font-size: .43rem;
            line-height: 1.5rem;

            img {
                width: 0.43rem;
                height: 0.43rem;
                margin-right: 0.133rem;
            }
        }

        .rtyh {
            background-color: rgb(245, 245, 245);
            width: 4.75rem;
            border: 1px solid rgb(224, 224, 224);
            border-radius: 5px;
            display: flex;


            .kunk {
                border-radius: 0.08rem;
                display: flex;
                align-items: center;

                input {
                    color: #000;
                    font-size: .37rem;
                    background-color: rgb(245, 245, 245);
                    width: 4rem;
                    padding-left: 10px;
                    height: 1.07rem !important;
                }

                a {
                    color: #333;
                    font-size: .4rem;
                    line-height: 1.07rem;
                    margin-right: 0.266rem;
                }

                .gangganicon {
                    width: 0.26rem;
                    height: 0.24rem;
                }
            }
        }

        .eduleft {
            display: flex;
            align-items: center;

            .eduleftimg {
                width: 0.43rem;
                height: 0.43rem;
                margin-right: 0.13rem;
            }
        }

        .eduright {
            justify-content: space-between;
        }

        .ertyu {
            color: #333;
            font-size: .4rem;
            line-height: 1.5rem;
        }
    }

    .fangxi {
        display: flex;
        justify-content: space-between;
        align-items: center;
        font-size: .4rem;
        text-align: center;
        margin-top: 20px;

        .fanh {
            width: 49%;
            height: 1.07rem;
            line-height: 1.07rem;
            border-radius: 5px;
            color: #999;
            border: 1px solid #e0e0e0;
        }

        .xuk {
            color: #fff;
            background: linear-gradient(-55deg, rgb(80, 122, 250),rgb(115, 131, 251));
            border: 1px solid #fff;
        }
    }
}

.shougu {
    color: rgb(153, 153, 153);
    text-align: right;
}

.mail {
    width: 9.21rem;
    height: 1.2rem;
    border-radius: 0.13rem;
    line-height: 1.2rem;
    margin: 0.8rem auto;
    color: #fff;
    font-size: .43rem;
    text-align: center;
    background: linear-gradient(-55deg, rgb(80, 122, 250),rgb(115, 131, 251));
}
</style>