<template>
    <div style="background-color: #fff; min-height: 100vh;">
        <div class="whead">
            <div>
                <h2>
                    <span class="hbnh"><a class="fan" @click="$router.go(-1)"></a></span> 证转银 <a class="xind"
                        style="display: none;">新增</a><a class="shaux" style="display: none;"></a><a class="xind"
                        style="display: none;">交易记录</a>
                </h2>
            </div>
            <h6 >¥ {{
                   $store.state.userInfo.enableAmt
            }}</h6>           


            <p>当前可用余额</p>
        </div>

        <div class="heizhi">
            <div class="hekm">
                <h6>提现金额</h6>
                <div class="shurdu"><input placeholder="请输入" v-model="money" type="number"><a @click="withdrawalAll">全部</a></div>
                <h6>资金密码</h6>
                <div class="shurdu"><input placeholder="请输入" v-model="password" type="password">
                </div>
            </div>
        </div>

        <!-- <div  class="xuanzh"><a >选择提现银行卡</a>
            <p ><span ></span></p>
        </div> -->

        <div class="rechg" @click="Recharge">确认提现</div>

        <div class="txtd">
            <p>1、当前有持仓订单不能提款。</p>
            <p>2、提款请先通过实名认证和绑定银行卡。</p>
            <p>3、提款时间工作日09:30到24:00之间。</p>
            <p>4、每笔提现金额最小100元。</p>
            <p>5、<span>提款时段内提款一般2小时到账，提款时间受银行间清算时间影响，各家银行到账时间不同，最迟T+1次日24点前到账</span></p>
        </div>


    </div>
</template>
<script>
import * as api from "@/axios/api";
import { mapState } from "vuex";
export default {
    name: "recharge",
    data() {
        return {
            money: '',
            password: '',
            userInfo: {},
            settingInfo: {},
        };
    },
    computed: {
    ...mapState([ "bankInfo"]),
    },
    mounted() {
        this.getUserInfo()
        this.getSettingInfo()
    },
    methods: {
        Recharge() {
            if (this.money == '' || this.money == null || this.money == undefined) {
                this.$toast('请输入充值金额')
                return
            }
            if (this.password == '' || this.password == null || this.password == undefined) {
                this.$toast('请输入通道密码')
                return
            }
            this.handleToSure()
        },
        withdrawalAll() {
      // 点击全部提现
      this.money = this.userInfo.enableAmt;
      if (navigator.vibrate) {
        // 支持
        navigator.vibrate([55]);
      }
    },
        async handleToSure() {
            // 点击确定提现
            if (!this.userInfo.idCard) {
                this.$toast(this.$t('hj178'));
                return;
            }
            // if (!this.bankInfo.bankNo) {
            //     this.$toast(this.$t('hj179'));
            //     return;
            // } else {
            // }
            if (!this.money || this.money <= 0) {
                this.$toast(this.$t('hj180'));
            } else if (this.money - this.settingInfo.withMinAmt < 0) {
                this.$toast(this.$t('hj181') + this.settingInfo.withMinAmt);
            } else {
                let opts = {
                    amt: this.money,
                    withPwd:this.password
                };
                let data = await api.outMoney(opts);
                if (data.status === 0) {
                    // 成功
                    this.$toast(this.$t('hj182'));
                    this.$router.push("/user");
                } else {
                    this.$toast(data.msg ? data.msg : this.$t('hj183'));
                }
            }
            if (navigator.vibrate) {
                // 支持
                navigator.vibrate([55]);
            }
        },
        async getUserInfo() {
            // 获取用户信息
            let data = await api.getUserInfo();
            if (data.status === 0) {
                // 判断是否登录
                this.$store.commit('dialogVisible', false);
                this.$store.state.userInfo = data.data;
                this.userInfo =data.data
            } else {
                this.$store.commit('dialogVisible', true);
            }
        },
        async getSettingInfo() {
      let data = await api.getSetting();
      if (data.status === 0) {
        // 成功
        this.settingInfo = data.data;
        console.log(this.settingInfo, "settingInfo");
      } else {
        Toast(data.msg);
      }
    },
    }
}

</script>
<style scoped>
.whead {
    width: 100%;
    height: 5.34rem;
    background: linear-gradient(-55deg, rgb(80, 122, 250), rgb(115, 131, 251));
    text-align: center;
}

.whead h2 {
    text-align: center;
    height: 1.2549rem;
    width: 100%;
    position: relative;
    line-height: 1.2549rem;
    font-size: 0.4806rem;
    color: #fff;
    background: transparent;
    font-weight: 500;
    z-index: 3;
}

h2 .hbnh {
    position: absolute;
    left: 0.4005rem;
    font-size: 0.4272rem;
    font-weight: 500;
}

.xind {
    position: absolute;
    right: 0.4005rem;
    color: #fff;
    font-size: 0.3738rem;
    font-weight: 500;
}

.fan {
    width: 0.2403rem;
    height: 0.4272rem;
    background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABIAAAAgCAYAAAAffCjxAAAAAXNSR0IArs4c6QAAAVdJREFUSEut1usqBVEYxvH/c1MuRJJDkhwTEpIkJUlyCEk++CJJckpycw49Gs3Wa+y9Z82ePd/Xr5n38KwRNR7bE0APMK1OHdtTwAWQGZcdQbZngPMc+QImK0O2Z4GzgIxLuqoE2Z4DTgMyJuk6K08yZHseOAnIqKSbRo2TINsLwHFARiTdxkaVQrYXgaOADEu6K3a7LWR7CTjMD2XdaYq0rZHtFWA/Rz6BIUn3reau6RvZXgX28kMfOfLQbnj/QbbXgN380HuOPJZtwB/I9jqwE5ABSc9lyJ8a2d4AtgPSL+klBfmFbG8CWwHpk/SaivxAtjMgg7Inq0mvpLcqSHehTOvKpzU+oSvFDlj99gesOJCDkp7Kip+yIknT3XL7C0tbum9lMbIMHKQkQEqwJWVSKZTPWTElq0dt6GYxt6uHf8DqX0cBq39BBqz+lR2w+j8RAfv9rfkGqF24CUdT9E4AAAAASUVORK5CYII=) no-repeat 50%;
    background-size: 100%;
    display: inline-block;
    margin-right: 0.1335rem;
    vertical-align: middle;
    margin-top: -0.0534rem;
}

.shaux {
    position: absolute;
    width: 0.4806rem;
    height: 0.4806rem;
    background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACQAAAAkCAYAAADhAJiYAAAAAXNSR0IArs4c6QAAA8VJREFUWEfNmEloXVUYx3//e18qCWitA4JUFLEURdOotcWpEKsLFbUVBONKKYIbRcEJ3VhwarvSiAspVlzoRpyow0Lr0No2LbW4cACllCK2DhREjWhy71/OzU087/W9vPuSl5K7u2f4zu9+9xuPmGeP5hkPlYFsLyTjBsQgZgBxDrAQMPA75gBiP+YTUrZK+nsmH9sWyPZSch4GhoDeiof8CbxCwiZJhyruKZa1BLLdR86TwL1ArROh0dqgpY0kPCVprIqMpkC2l5DzJnBhEyGjiBHMt8DRcv5UxPmYlU21aPaQslbST+2gjgGyfSk5HwCn1202O4DnSXlX0j/NBNvuJeMW4D7E5Q1rfiRhUNIP00HVAdk+j5ydDTBHMPeopnfafV0873HfjhgGTovGA9QKSYdbyZoCKm1mpO43mRFSbpb0Sycwk2ttLyZnK7Bsar/ZScoqSVkzmf8DZd4EPBhtDDCrJf01E5gIahE5nwEXRXIeUaqNLYFKI/4a6CkXHSbhYkk/zwYmgjqLnK+AReXYHyQsaSa/0JAzvwzcFWlnrWp6uxswU1DjvhOxJZK5QakebTxDtk8k5wjQV0yaHarp6m7CFGLthJwvI3v6jYQzG+OTSm94PdLObarpjW4DFVDjXofYPCU75zr16KP4LDnzS8Dd5eAoCae0ijOzhbQdDPxXIC1lPa1Uj9cDjXs3KiJsSCTblGj1bA+ebr8z7wcGyvM+VKLrGzUUiCeD1wtKFXJX154ivsEJkUlsxtxavn9HwhWNQCENLCgH1yvVE12jCXYz5kESPp4ukUfnbQk2NKdAhTFnfq7Ib9M/h0joD0Bz+stKl+8lJ9jO0hZMJuda9WhbcPvjYtS2V5LzReRhMduwUhUaPL5unzkUfHVuDnxPwoCk0QmgcQ8hXou8YO4Co72AnD1RtM5IuErS7snzm6WO7appVTc9LZZlu5+cvaVnP6NUj9W5fekFIZyvi7S0ptOCrJMPcOaQVIdIuEzSv8cCTVSK38xV+dEIazukjrMlHWiciwu0UDA9FGmpKwVaJ5orjHpyQ1nC7gL6G6BmU8KeCyyW9HlVsDkp8m2LjDsQLwJjJCyXdLAKVLM26BJy3gfOqBNgtgPDbdqgPjLWAA8glkf795FwZZWyplWjGIz8rY4aRXMBYkWLRnEvKTdVqdHbtdLrgftn0UqHxL2hbKXr3LvV76ty2RDa6om4Uf2yIbROr5LwbNcuG5rEjpPIuBFxDWZZeR1zcumpRzEHEfswn5Ly3kz7ubYaquIZ3Vwz74D+A2+DgOcUuMpOAAAAAElFTkSuQmCC) no-repeat 50%;
    background-size: 100%;
    right: 0.4005rem;
    top: 0.4005rem;
}

.whead h6 {
    color: #fff;
    font-size: 0.6408rem;
    margin-top: 0.801rem;
}

.whead p {
    margin-top: 0.4005rem;
    color: #9fa1ff;
    font-size: 0.3204rem;
}

.heizhi {
    width: 100%;
    min-height: 1.335rem;
    background: #fff;
    margin-top: -1.068rem;
    border-radius: 0.4005rem 0.4005rem 0 0;
}

.heizhi .hzh {
    width: 100%;
}

.hekm {
    width: 9.345rem;
    margin: 0 auto;
}

.heizhi .hekm h6 {
    color: #333;
    font-size: 0.4005rem;
    padding-top: 0.534rem;
}

.heizhi .hekm .shurdu {
    width: 9.345rem;
    height: 1.1748rem;
    background: #e6e6e6;
    border-radius: 0.1335rem;
    margin-top: 0.4272rem;
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    -webkit-box-pack: justify;
    -ms-flex-pack: justify;
    justify-content: space-between;
}

.heizhi .hekm .shurdu {
    width: 9.345rem;
    height: 1.1748rem;
    background: #e6e6e6;
    border-radius: 0.1335rem;
    margin-top: 0.4272rem;
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    -webkit-box-pack: justify;
    -ms-flex-pack: justify;
    justify-content: space-between;
}

.heizhi .hekm .shurdu input {
    height: 1.1748rem;
    width: 7.476rem;
    background: transparent;
    padding-left: 0.267rem;
    font-size: 0.4272rem;
    color: #000;
}

.heizhi .hekm .shurdu a {
    color: #333;
    font-size: 0.4005rem;
    margin-right: 0.4005rem;
    line-height: 1.1748rem;
}

.heizhi .hekm .shurdu input {
    height: 1.1748rem;
    width: 7.476rem;
    background: transparent;
    padding-left: 0.267rem;
    font-size: 0.4272rem;
    color: #000;
}

.heizhi .hekm .shurdu input::-webkit-input-placeholder {
    color: #99a3ba;
}

.xuanzh {
    width: 9.3455rem;
    margin: 0.4005rem auto;
    height: 1.1748rem;
    border-radius: 0.2403rem;
    line-height: 1.1748rem;
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    -webkit-box-pack: justify;
    -ms-flex-pack: justify;
    justify-content: space-between;
}

.xuanzh a {
    color: #000;
    font-size: 0.4272rem;
}

.xuanzh p {
    color: #000;
    font-size: 0.4272rem;
}

.xuanzh p span {
    background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAwAAAAWCAYAAAD0OH0aAAAAAXNSR0IArs4c6QAAAe1JREFUOE+F1Otv0mAUBvDntOUyo5saL9GIbKOFjibMLcQY9cvK4mX+v4rOZESNIV42V0Mpo7AhRo0a75FLaY95pxgvBc7X5/0l7+Wclyr2zn0ijkcj8g1VVd9iQpFdrT1mRh4gG+wVDMN4M87QZr1+PD4I7jIjB4JD7JvZbPb1KEQieFKrHZvyaR3gc0RUkyhu6vrZV2FoH4iqVNpHSeqsM/MyQPWBR+biovbyX/QbiMCyWkeUSP8Oc5AH0IhF5RVVVdt/or+ACLa2dg9HY95tgM+D0Px1ptYQ/Qf2z9RozBzo+UUGXwCwJ1F0ZWFhbk9koUAE5XJ9enomuMWMiwBaEYXMdDrdHAkEchznkO/TTRAuA2iDB+ZY8PP2KgchKUUwLgHkTgSWtTMvK1wCkJAIxbGgWt2dDbgvFicJuOd53bWRwLbtJEMWi2cBPAAPrhuG8S0UPHfdhOT5JTDmifBQIr6m6/rX0Gvd3q6fUZSgBEKKiMoRha5qmvYl9OEc58VpP+iWANYAetSJyVfyqdTn0NawbfsUSNlg5gwDT71edHVpae5TaPM1m82TnZ4n9qwT0ebAi63mcsmPoe3tuu6JXj/YADgL0DPwVMEwEh9GDtBwRIlgfZdQyGcy78eO6PAT6CrS2rKmvZv0CfwA8CTM1t7ZfC0AAAAASUVORK5CYII=) no-repeat 50%;
    background-size: 100%;
    width: 0.1602rem;
    height: 0.2937rem;
    display: inline-block;
    margin-right: 0.4005rem;
    vertical-align: middle;
    margin-top: 0.0534rem;
    margin-left: 0.1335rem;
}

.rechg {
    width: 9.345rem;
    height: 1.1748rem;
    background: linear-gradient(-55deg, rgb(80, 122, 250), rgb(115, 131, 251));
    border-radius: 0.1335rem;
    margin: 0 auto;
    text-align: center;
    line-height: 1.1748rem;
    color: #fff;
    font-size: 0.4272rem;
    margin-top: 0.801rem;
}

.txtd {
    width: 8.6775rem;
    margin-left: 0.3471rem;
    margin-top: 0.9612rem;
}

.txtd p {
    color: #666;
    font-size: 0.3471rem;
    line-height: 0.5607rem;
}

.txtd p span {
    color: #cb1a1e;
}
</style>