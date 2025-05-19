<template>
  <div class="">
      <div class="head">
          <div rech="true">
              <h2><span class="hbnh"><a class="fan" @click="$router.go(-1)"></a></span> 银转证 </h2>
          </div>
          <h6  v-if="this.$store.state.settingForm.indexDisplay && !this.$store.state.settingForm.futuresDisplay">¥ {{ $store.state.hide ? '****' : Number($store.state.userInfo.userAmt +
                  $store.state.userInfo.userIndexAmt).toFixed(2)
          }}</h6>

          <h6 v-else-if="!this.$store.state.settingForm.indexDisplay && this.$store.state.settingForm.futuresDisplay"
          class="account">
          ¥ {{ $store.state.hide ? '****' : Number($store.state.userInfo.userAmt +
              $store.state.userInfo.userFuturesAmt).toFixed(2)
          }}
        </h6>
        <h6 v-else-if="!this.$store.state.settingForm.indexDisplay && !this.$store.state.settingForm.futuresDisplay"
          class="account">¥ {{ $store.state.hide ? '****' : Number($store.state.userInfo.userAmt).toFixed(2) }}</h6>

          <p>当前可用余额</p>
      </div>

      <div class="jine">
          <div class="boxk">
              <p class="cz">充值金额</p>
              <p class="min">最小充值金额为<span>100元</span></p><input placeholder="请输入" type="number" v-model="money"
                  class="inpt">
              <div class="xuank">
                  <a class="" @click="xz(item)" v-for="item in moneyList"
                      :class="(money == Number(item)) ? 'xl' : ''">{{ item }}</a>
              </div>
              <p class="cz" style="margin-top: 0.801rem;">通道密码</p><input placeholder="请输入" type="password"
                  v-model="password" class="inpt">
              <div class="enyt" @click="Recharge()">立即充值</div>
              <p class="czl"> 温馨提示: </p>
              <p class="czl">一、入金时间:工作日09:30-19:30，节假日休息。</p>
              <p class="czl"> 二、感谢您选择我们。为保障您的资金安全， 请确认您转账的是平台实时显示的账户。 请勿相信任何人(包括工作人员)向您私下提供账号转账，
                  非本平台实时显示的银行账号入金的一切损失自负。</p>
          </div>
      </div>
  </div>
</template>
<script>
import * as api from "@/axios/api";
export default {
  name: "recharge",
  data() {
      return {
          money: '',
          password: '',
          moneyList: ['50000', '100000', '300000', '500000']
      };
  },
  mounted() {
      this.getUserInfo()
  },
  methods: {
      xz(e) {
          this.money = Number(e)
      },
      Recharge() {
          
          if (this.money == '' || this.money == null || this.money == undefined) {
              this.$toast('请输入充值金额')
              return
          }
          if (this.password == '' || this.password == null || this.password == undefined) {
              this.$toast('请输入通道密码')
              return
          }
          this.setRecharge()
      },
      async setRecharge() {
          let data = {
              amt: this.money,
              payType: 1,
              password: this.password
          }
          let res = await api.inMoney(data)
          if (res.code == 200) {
              this.$toast(res.msg)
              this.$router.go(-1)
          } else {
              this.$toast(res.msg)
          }
      },
      async getUserInfo() {
          // 获取用户信息
          let data = await api.getUserInfo();
          if (data.status === 0) {
              // 判断是否登录
              this.$store.commit('dialogVisible', false);
              this.$store.state.userInfo = data.data;
          } else {
              this.$store.commit('dialogVisible', true);
          }
      },
  },
}
</script>
<style scoped>
.head {
  width: 100%;
  height: 6.141rem;
  background: linear-gradient(-55deg, #ea3544, #ea6248);
  text-align: center;
}

.head h2 {
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

.hbnh {
  position: absolute;
  left: 0.4005rem;
  font-size: 0.4272rem;
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

.xind {
  position: absolute;
  right: 0.4005rem;
  color: #fff;
  font-size: 0.3738rem;
  font-weight: 500;
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

.head h6 {
  font-weight: 500;
  font-size: 0.6408rem;
  color: #fff;
  margin-top: 0.9345rem;
}

.head p {
  color: #ff9fa0;
  font-size: 0.3204rem;
  margin-top: 0.3738rem;
}

.jine {
  background: #fff;
  border-radius: 0.4005rem 0.4005rem 0 0;
  min-height: 2.136rem;
  margin-top: -2.0025rem;
  padding-top: 0.534rem;
  position: relative;
  z-index: 9;
}

.boxk {
  width: 9.345rem;
  margin: 0 auto;

}

.cz {
  color: #333;
  font-size: 0.4005rem;
}

.min {
  color: #999;
  font-size: 0.3738rem;
  margin-top: 0.267rem;
}

.min span {
  color: #ea3744;
}

.boxk input {
  width: 100%;
  height: 1.1748rem;
  background: #e6e6e6;
  border-radius: 0.1335rem;
  margin-top: 0.4005rem;
  padding-left: 0.267rem;
  font-size: 0.4272rem;
  color: #000;
}

.xuank {
  width: 9.345rem;
  margin: 0 auto;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-pack: justify;
  -ms-flex-pack: justify;
  justify-content: space-between;
  margin-top: 0.267rem;
}

.xuank a {
  width: 2.2428rem;
  height: 1.1748rem;
  background: #e6e6e6;
  border-radius: 0.1335rem;
  color: #999;
  font-size: 0.4272rem;
  text-align: center;
  line-height: 1.1748rem;
}

.enyt {
  width: 9.345rem;
  height: 1.1748rem;
  background: linear-gradient(-55deg, #ea3544, #ea6248);
  border-radius: 0.1335rem;
  margin: 0 auto;
  margin-top: 0.5073rem;
  color: #fff;
  font-size: 0.4272rem;
  text-align: center;
  line-height: 1.1748rem;
  margin-bottom: 1.869rem;
}

.czl {
  color: #666;
  font-size: 0.3471rem;
  line-height: 0.5607rem;
}

.xl {
  background: #ea5947 !important;
  color: #fff !important;
}
</style>