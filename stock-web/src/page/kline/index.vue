<template>
  <div class="kline_detail_page">
    <div class="dahead">
      <div class="headf"><span @click="$router.back()"></span>
        <div class="wenebn"><a></a>
          <div class="wtxt">
            <h6>{{ singDetails.name }} </h6>
            <p>{{ singDetails.gid }}</p>
          </div><a class="you"></a>
        </div>
      </div>
      <!-- <div class="ghbn van-tabs van-tabs--line">
        <div class="van-tabs__wrap">
          <div role="tablist" class="van-tabs__nav van-tabs__nav--line"
            style="border-color: rgb(255, 255, 255); background: transparent;">
            <div role="tab" class="van-tab " aria-selected="true" :class="tabhq==0?'hqxz':'hqwx'" @click="(tabhq=0)">
              <span class="van-tab__text van-tab__text--ellipsis">行情</span>
            </div>
            <div role="tab" class="van-tab" style="" :class="tabhq==1?'hqxz':'hqwx'"  @click="(tabhq=1)"><span
                class="van-tab__text van-tab__text--ellipsis">资金流向</span></div>
            <div role="tab" class="van-tab" :class="tabhq==2?'hqxz':'hqwx'"  @click="(tabhq=2)"><span
                class="van-tab__text van-tab__text--ellipsis">业绩报告</span></div>
            <div role="tab" class="van-tab"  :class="tabhq==3?'hqxz':'hqwx'"  @click="(tabhq=3)"><span
                class="van-tab__text van-tab__text--ellipsis">流通股东</span></div>
            <div class="van-tabs__line"
              style="background-color: rgb(255, 255, 255); transform:  translateX(-50%); transition-duration: 0.3s;" :style="{'transform':tabhq==0?'translateX(34px)': tabhq==1?'translateX(130px)':tabhq==2?'translateX(225px)':'translateX(325px)'}">
            </div>
          </div>
        </div>
        <div class="van-tabs__content"></div>
      </div> -->
      <div class="boxh">
        <div class="bl">
          <h6>{{ singDetails.nowPrice }}</h6>
          <p><span>{{singDetails.hcrate>0?'+':''}}{{singDetails.hcrate}}%</span></p>
        </div>
        <div class="br" @click="option()">
          <img
            src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADQAAAA0CAYAAADFeBvrAAAAAXNSR0IArs4c6QAAAjtJREFUaEPtmr1rFFEUxX8HOwVLtRXstFY0Ksa/wo/aEFCxEhT8ACMKiqCoSLQ2+S+M4AfYayfYiVgq2C1HXvZNeC5mycvM7M4sb6rZ3Td3zrnnzt07M0ckm+2jwGVgHtiX/tbB/R/AGvBM0scKn8KO7R3AY+BSB4FvBdJz4IqkQUXoBbC4lSM7vGZZ0qJsHwM+JEC/AE+Abx0GH6DtD6oABxOcc4HQCnA2fhnIHJb0p+Nk1uHZ3gV8SkitBkLh4tobCSxIetUHMhVG2xeAl/Hz90DICYF5SW97RuhU7HYB9mCU0AlJ73tG6DjwbqNtjyhUCE1bTdtFoVoi2N4NXI1BHkr6VSvgyMETV8j2EnAj4rgj6XbfCb0GzkUSK5LOF0JjMjCNkisK5ZRkUSgnW/9ba7uUXE4SS8nlZKuUXN1sDe9aJzucdropJIPmnozkngYOxPVfgTcZx/4Exg60tRSyfR+4lgGoiaV3Jd3cLFBdQveA602gzIixJOlWW4Sqe5tJlVx4IvVo3D1ULYUysrqxtNNNoRAa/k+UWS6nEso1lJOtMsvVzVaZ5ZrJYOlyOXksXS4nW5t0ufRR8NjJeTvnmoZC6cP6B5J+bwd4K9N2k0CaijVxhZoCXhSKGZi5d6wz8Vp/5owXq8CZWHKfgSM9ssbsjNaYQxH/ujVmDkjNFv02LwVmtpeBhbZbbMvxh/aySCgYAIOl7GLLJ20r/L8GwOossfyCq/FkdGgFol3cBkBoZsHj8zS1aP4FSuRiJZn9KbwAAAAASUVORK5CYII="
            v-if="isOptionOpt === false">
            <img
            src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADQAAAA0CAYAAADFeBvrAAAAAXNSR0IArs4c6QAAAeFJREFUaEPtmjFLXEEUhb+DnUJKtU2d1AZjlOy/iD9AWUjESjCFaQymCEJCEmSTPv6MCImCvdZ2EixTpFuuXHdeGBcM77HsOE/nVW933+Wec7+ZWdg9IrrMbB5YAzrAbPxZhve/gR/AZ0lHlT75jZlNAB+AVxkKryPpC7AuqV8Z2gO6dSozfqYnqSszewocRkJPgY/AWcbiXdpDpwI8inQuuKHvwHJ4083MSfqbuZkreWY2BRxHpvbdkG+umWBgVdK3NpipNJrZCvA1vD53QxYZ6Eg6aJmh5+G0c9n9YUOLkn61zNAz4Oe/Y3uIUDF02zTNrBC6bQj/7V8IZY1n8OVa9lDWkAqhrPGUPZQ7nkKoEEo/gXJsxzM3swfABjCdiMUF8F7Sn5v6jUTIzN4Bm4nMVG3eStoal6Ed4HViQ9uS3ozLUOol579I7Y5tySUmU6vdSHuoVofEDxVDiQfeuF0h1HhkiQsKocQDb9yuEGo8ssQFhVDigTduVwg1HlnigkIo8cAbt7t3hO7E3/p3LnixD7wIi/cEeNKiaMxkiMY8DvqvojELQBy2aHd4yZ2ZWQ9YbXzE5FUwiJcFQx4A9EjZy7w01lZzPQBYlYXl56nGpZDQcqM5Xn3ADzPP+HyKI5qXGZ7cFn6IPbYAAAAASUVORK5CYII="
            v-if="isOptionOpt">
          <p v-if="isOptionOpt === false">加自選</p>
          <p v-if="isOptionOpt">刪除自選</p>
        </div>
      </div>
    </div>

    <div class="hangq">
      <div class="ghj"></div>
      <div class="kaihj">
        <p><span>今开</span><a>{{ singDetails.open_px }}</a></p>
        <p><span>最高</span><a>{{ singDetails.today_max }}</a></p>
        <p><span>成交</span><a>{{ (Number(singDetails.business_amount) / 10000).toFixed(2) }} 万</a></p>
        <p><span>昨收</span><a>{{ singDetails.preclose_px }}</a></p>
        <p><span>最低</span><a>{{ singDetails.today_min }}</a></p>
        <p><span>金额</span><a>{{ (Number(singDetails.business_balance) / 100000000).toFixed(2) }} 亿</a></p>
      </div>
      <div class="ganhj"></div>
      <!-- <div class="xinat"><a class="xuan">分时</a><a class="">日线</a><a class="">周线</a><a class="">月线</a><span
          class="tiaol"></span></div> -->
      <div id="Zline" class="zoushit">
        <Kline :type="singDetails.type" />
        <!-- <div tabindex="1"
          style="user-select: none; -webkit-tap-highlight-color: transparent; position: relative; outline: none; border-style: none; width: 100%; cursor: crosshair;">
          <div style="width: 100%; margin: 0px; padding: 0px; position: relative; overflow: hidden; height: 293px;">
            <div
              style="margin: 0px; padding: 0px; position: absolute; top: 0px; overflow: hidden; left: 0px; width: 308px; height: 293px;">
              <canvas width="924" height="879"
                style="position: absolute; top: 0px; left: 0px; z-index: 2; width: 308px; height: 293px;"></canvas><canvas
                width="924" height="879"
                style="position: absolute; top: 0px; left: 0px; z-index: 2; width: 308px; height: 293px;"></canvas>
            </div>
            <div
              style="margin: 0px; padding: 0px; position: absolute; top: 0px; overflow: hidden; left: 308px; width: 37px; height: 293px;">
              <canvas width="111" height="879"
                style="position: absolute; top: 0px; left: 0px; z-index: 2; width: 37px; height: 293px;"></canvas><canvas
                width="111" height="879"
                style="position: absolute; top: 0px; left: 0px; z-index: 2; width: 37px; height: 293px;"></canvas>
            </div>
          </div>
          <div style="width: 100%; margin: 0px; padding: 0px; position: relative; overflow: hidden; height: 25px;">
            <div
              style="margin: 0px; padding: 0px; position: absolute; top: 0px; overflow: hidden; left: 0px; width: 308px; height: 25px;">
              <canvas width="924" height="75"
                style="position: absolute; top: 0px; left: 0px; z-index: 2; width: 308px; height: 25px;"></canvas><canvas
                width="924" height="75"
                style="position: absolute; top: 0px; left: 0px; z-index: 2; width: 308px; height: 25px;"></canvas>
            </div>
          </div>
        </div> -->
      </div>
      <div id="Zlines" class="zoushit" style="display: none;"></div>
      <div id="Zlined" class="zoushit" style="display: none;"></div>
      <div id="Zlinef" class="zoushit" style="display: none;"></div>
      <div class="ganhj" style="margin-top: 0.2rem;"></div>
      <div class="gouk">
        <div class="xunjk"><a>买盘档</a><a>卖盘档</a></div>
        <div class="xuankl">
          <div class="xunl splk">
            <p><span>买1</span><span class="spa">{{ singDetails.buy1 }}</span><span>{{ singDetails.buy1_num }}</span></p>
            <p><span>买2</span><span class="spa">{{ singDetails.buy2 }}</span><span>{{ singDetails.buy2_num }}</span></p>
            <p><span>买3</span><span class="spa">{{ singDetails.buy3 }}</span><span>{{ singDetails.buy3_num }}</span></p>
            <p><span>买4</span><span class="spa">{{ singDetails.buy4 }}</span><span>{{ singDetails.buy4_num }}</span></p>
            <p><span>买5</span><span class="spa">{{ singDetails.buy5 }}</span><span>{{ singDetails.buy5_num }}</span></p>
          </div>
          <div class="xunl splk">
            <p><span>卖1</span><span class="spa">{{ singDetails.sell1 }}</span><span>{{ singDetails.sell1_num }}</span>
            </p>
            <p><span>卖2</span><span class="spa">{{ singDetails.sell2 }}</span><span>{{ singDetails.sell2_num }}</span>
            </p>
            <p><span>卖3</span><span class="spa">{{ singDetails.sell3 }}</span><span>{{ singDetails.sell3_num }}</span>
            </p>
            <p><span>卖4</span><span class="spa">{{ singDetails.sell4 }}</span><span>{{ singDetails.sell4_num }}</span>
            </p>
            <p><span>卖5</span><span class="spa">{{ singDetails.sell5 }}</span><span>{{ singDetails.sell5_num }}</span>
            </p>
          </div>
        </div>
      </div>
      <div class="mauru" @click="goBuy()">买入</div>
    </div>
  </div>
</template>

<script>
import Kline from "./components/kLine.vue";
import * as api from "@/axios/api";
import { Toast } from 'mint-ui'
export default {
  name: "kline",
  data() {
    return {
      news: 'tab_2',
      kLineDetails: {}, // K线图详情数据
      singDetails: {},
      scFlag: false,
      acseFlag: false,
      jianjie: "",
      optionBtn: false,
      isOptionOpt: false,
      dialogFlag: false,
      timedata: [],
      newsdetailList: [],
      tabhq:0,
    };
  },
  components: {
    Kline
  },
  created() {

    const { query } = this.$route;
    this.kLineDetails = query;
    if (query.if_us == '1') {

      this.getSingDetailUs();
    } else {
      this.getSingDetails();
    }
    this.getOpation();
    // this.$Lazyload();
    this.getUserInfo();
  },

  // beforeDestroy() {
  //   this.Klinetype = false;
  //   window.clearInterval()
  // },
  methods: {
    goBuy(){
      this.$router.push({path:'/buyStocks',query:{
        name:this.singDetails.name,
        code:this.singDetails.code,
        m:this.singDetails.nowPrice,
        id:this.singDetails.id,
      }})
    },
    async getHknews() {
      await api.queryIndexNews().then(res => {
        if (res.status == 0) {
          this.newsdetailList = res.data.data;
        }
      });
    },
    async getNohknews() {
      let data = await api.queryNewsList(4);
      this.newsdetailList = data.data.list
    },
    async option() {
      if (this.optionBtn) {
        return
      };
      this.optionBtn = true;
      if (this.isOptionOpt) {
        let data = await api.delOption({ code: this.kLineDetails.code });
        if (data.status === 0) {
          this.getOpation();
          this.optionBtn = false;
          
          Toast(this.$t('hj97'));
        } else {
          this.optionBtn = false;
         
          Toast(data.msg);
        }
      } else {
        let data = await api.addOption({ code: this.kLineDetails.code });
        if (data.status === 0) {
          this.getOpation();
          this.optionBtn = false;
          
          Toast(this.$t('hj96'));
        } else {
         
          Toast(data.msg);
          this.optionBtn = false;
        }
      }
      if (navigator.vibrate) {
        // 支持
        navigator.vibrate([55]);
      }
    },
    async getUserInfo() {
      // 获取用户信息
      //   let showcookie = this.getCookie('USER_TOKEN');
      let data = await api.getUserInfo();
      if (data.status === 0) {
        // this.getProductSetting()
        this.$store.state.userInfo = data.data;
      } else {
        Toast(data.msg);
      }
      this.$store.state.user = this.user;
    },
    async getOpation() {
      let opts = {
        code: this.$route.query.code
      }
      let data = await api.isOption(opts)
      if (data.status === 0) {
        // 0 --> 未添加
        this.isOptionOpt = false
      } else {
        this.isOptionOpt = true
      }
    },
    async getSingDetails() {
      let opts = {
        code: this.kLineDetails.code,
        stockType: this.kLineDetails.type
      };
      await api.getSingleStock(opts).then(res => {
        if (res.status === 0) {
          this.singDetails = res.data.stock;

          if (res.data.introduction) {
            this.jianjie = res.data.introduction;
          } else {
            this.jianjie = res.data.indexintroduction;
          }
          console.log(this.singDetails);
          if (this.kLineDetails.if_zhishu != '0' && this.singDetails.gid.indexOf('hk') > -1) {
            this.getHknews();
          } else {
            this.getNohknews();
          }
        }
      });
    },
    async getSingDetailUs() {
      let opts = {
        code: this.kLineDetails.code,
        stockType: this.kLineDetails.type
      };
      await api.getUsDetail(opts).then(res => {
        // console.log(res,1111123);
        // var that = this
        // if(!res){
        //   setTimeout(() => {
        //     that.getSingDetailUs()
        //   }, 3000);

        // }
        if (res.status === 0) {
          this.singDetails = res.data.stock;
          this.timedata = res.data.timedata;
          if (res.data.introduction) {
            this.jianjie = res.data.introduction;
          } else {
            this.jianjie = res.data.indexintroduction;
          }
          this.getNohknews();
          console.log(this.singDetails);
        }
      });
    },
    handleJj() {
      this.acseFlag = true;
      setTimeout(() => {
        this.acseFlag = false;
      }, 1000);
      if (navigator.vibrate) {
        // 支持
        navigator.vibrate([55]);
      }
    },
    handleBack() {
      this.$router.go(-1);
    },
    handleSc() {
      this.scFlag = !this.scFlag;
    },
    // goBuy(index) {
    //   this.$router.push({
    //     path: "/TradingBuy",
    //     query: {
    //       t: index,
    //       code: this.kLineDetails.code,
    //       m: this.singDetails.nowPrice,
    //       type: this.kLineDetails.if_zhishu,
    //       id: this.singDetails.id,
    //       name: this.kLineDetails.name,
    //       if_us: this.kLineDetails.if_us,
    //     }
    //   });
    //   if (navigator.vibrate) {
    //     // 支持
    //     navigator.vibrate([55]);
    //   }
    // }
  },
  filters: {
    getName(name) {
      if (name.length > 15) {
        return name.substring(0, 14);
      } else {
        return name;
      }
    },
    gettime(time) {
      if (!time) {
        return "";
      }
      var nd = new Date(time);
      var y = nd.getFullYear();
      var mm = nd.getMonth() + 1;
      var d = nd.getDate();
      var h = nd.getHours();
      var m = nd.getMinutes();
      var c = nd.getSeconds();
      if (mm < 10) {
        mm = "0" + mm;
      }
      if (d < 10) {
        d = "0" + d;
      }
      if (h < 10) {
        h = "0" + h;
      }
      if (m < 10) {
        m = "0" + m;
      }
      if (c < 10) {
        c = "0" + c;
      }
      //17:35:2922-06-2022
      return y + "-" + mm + "-" + d + " " + h + ":" + m + ":" + c;
    },
    utc2beijing(utc_datetime) {
      // 转为正常的时间格式 年-月-日 时:分:秒
      var T_pos = utc_datetime.indexOf('T');
      var Z_pos = utc_datetime.indexOf('Z');
      var year_month_day = utc_datetime.substr(0, T_pos);
      var hour_minute_second = utc_datetime.substr(T_pos + 1, Z_pos - T_pos - 1);
      var new_datetime = year_month_day + " " + hour_minute_second; // 2017-03-31 08:02:06

      // 处理成为时间戳
      timestamp = new Date(Date.parse(new_datetime));
      timestamp = timestamp.getTime();
      timestamp = timestamp / 1000;

      // 增加8个小时，北京时间比utc时间多八个时区
      var timestamp = timestamp + 8 * 60 * 60;

      // 时间戳转为时间
      var beijing_datetime = new Date(parseInt(timestamp) * 1000).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");
      return beijing_datetime; // 2017-03-31 16:02:06
    }
  }
};
</script>

<style scoped lang="less">
h6 {
  font-weight: bold;
}


h2 {
  text-align: center;
  height: 1.2549rem;
  width: 100%;
  position: relative;
  line-height: 1.2549rem;
  font-size: 0.4806rem;
  color: #fff;
  background: transparent;
  font-weight: 500;
  z-index: 3
}

h2 .hbnh {
  position: absolute;
  left: 0.4005rem;
  font-size: 0.4272rem;
  font-weight: 500
}

h2 .hbnh .fan {
  width: 0.2403rem;
  height: 0.4272rem;
  background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABIAAAAgCAYAAAAffCjxAAAAAXNSR0IArs4c6QAAAVdJREFUSEut1usqBVEYxvH/c1MuRJJDkhwTEpIkJUlyCEk++CJJckpycw49Gs3Wa+y9Z82ePd/Xr5n38KwRNR7bE0APMK1OHdtTwAWQGZcdQbZngPMc+QImK0O2Z4GzgIxLuqoE2Z4DTgMyJuk6K08yZHseOAnIqKSbRo2TINsLwHFARiTdxkaVQrYXgaOADEu6K3a7LWR7CTjMD2XdaYq0rZHtFWA/Rz6BIUn3reau6RvZXgX28kMfOfLQbnj/QbbXgN380HuOPJZtwB/I9jqwE5ABSc9lyJ8a2d4AtgPSL+klBfmFbG8CWwHpk/SaivxAtjMgg7Inq0mvpLcqSHehTOvKpzU+oSvFDlj99gesOJCDkp7Kip+yIknT3XL7C0tbum9lMbIMHKQkQEqwJWVSKZTPWTElq0dt6GYxt6uHf8DqX0cBq39BBqz+lR2w+j8RAfv9rfkGqF24CUdT9E4AAAAASUVORK5CYII=) no-repeat 50%;
  background-size: 100%;
  display: inline-block;
  margin-right: 0.1335rem;
  vertical-align: middle;
  margin-top: -0.0534rem
}

h2 .shaux {
  position: absolute;
  width: 0.4806rem;
  height: 0.4806rem;
  background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACQAAAAkCAYAAADhAJiYAAAAAXNSR0IArs4c6QAAA8VJREFUWEfNmEloXVUYx3//e18qCWitA4JUFLEURdOotcWpEKsLFbUVBONKKYIbRcEJ3VhwarvSiAspVlzoRpyow0Lr0No2LbW4cACllCK2DhREjWhy71/OzU087/W9vPuSl5K7u2f4zu9+9xuPmGeP5hkPlYFsLyTjBsQgZgBxDrAQMPA75gBiP+YTUrZK+nsmH9sWyPZSch4GhoDeiof8CbxCwiZJhyruKZa1BLLdR86TwL1ArROh0dqgpY0kPCVprIqMpkC2l5DzJnBhEyGjiBHMt8DRcv5UxPmYlU21aPaQslbST+2gjgGyfSk5HwCn1202O4DnSXlX0j/NBNvuJeMW4D7E5Q1rfiRhUNIP00HVAdk+j5ydDTBHMPeopnfafV0873HfjhgGTovGA9QKSYdbyZoCKm1mpO43mRFSbpb0Sycwk2ttLyZnK7Bsar/ZScoqSVkzmf8DZd4EPBhtDDCrJf01E5gIahE5nwEXRXIeUaqNLYFKI/4a6CkXHSbhYkk/zwYmgjqLnK+AReXYHyQsaSa/0JAzvwzcFWlnrWp6uxswU1DjvhOxJZK5QakebTxDtk8k5wjQV0yaHarp6m7CFGLthJwvI3v6jYQzG+OTSm94PdLObarpjW4DFVDjXofYPCU75zr16KP4LDnzS8Dd5eAoCae0ijOzhbQdDPxXIC1lPa1Uj9cDjXs3KiJsSCTblGj1bA+ebr8z7wcGyvM+VKLrGzUUiCeD1wtKFXJX154ivsEJkUlsxtxavn9HwhWNQCENLCgH1yvVE12jCXYz5kESPp4ukUfnbQk2NKdAhTFnfq7Ib9M/h0joD0Bz+stKl+8lJ9jO0hZMJuda9WhbcPvjYtS2V5LzReRhMduwUhUaPL5unzkUfHVuDnxPwoCk0QmgcQ8hXou8YO4Co72AnD1RtM5IuErS7snzm6WO7appVTc9LZZlu5+cvaVnP6NUj9W5fekFIZyvi7S0ptOCrJMPcOaQVIdIuEzSv8cCTVSK38xV+dEIazukjrMlHWiciwu0UDA9FGmpKwVaJ5orjHpyQ1nC7gL6G6BmU8KeCyyW9HlVsDkp8m2LjDsQLwJjJCyXdLAKVLM26BJy3gfOqBNgtgPDbdqgPjLWAA8glkf795FwZZWyplWjGIz8rY4aRXMBYkWLRnEvKTdVqdHbtdLrgftn0UqHxL2hbKXr3LvV76ty2RDa6om4Uf2yIbROr5LwbNcuG5rEjpPIuBFxDWZZeR1zcumpRzEHEfswn5Ly3kz7ubYaquIZ3Vwz74D+A2+DgOcUuMpOAAAAAElFTkSuQmCC) no-repeat 50%;
  background-size: 100%;
  right: 0.4005rem;
  top: 0.4005rem
}

h2 .xind {
  position: absolute;
  right: 0.4005rem;
  color: #fff;
  font-size: 0.3738rem;
  font-weight: 500
}

.ghb {
  height: 1.2549rem;
  width: 100%
}

.dahead {
  padding-bottom: 0.4806rem;
  background: linear-gradient(-55deg, rgb(80, 122, 250), rgb(115, 131, 251))
}

.boxh {
  width: 8.6775rem;
  margin: 0 auto;
  margin-top: 0.5607rem;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-pack: justify;
  -ms-flex-pack: justify;
  justify-content: space-between
}

.boxh .bl h6 {
  color: #fff;
  font-size: 0.9078rem
}

.boxh .bl p {
  margin-top: 0.4005rem
}

.boxh .bl p span {
  color: #fff;
  font-size: 0.3738rem;
  margin-right: 0.4005rem
}

.boxh .br {
  width: 1.335rem;
  text-align: center;
  margin-top: 0.267rem
}

.boxh .br img {
  width: 0.6942rem;
  height: 0.6942rem
}

.boxh .br p {
  color: #fff;
  font-size: 0.3204rem;
  margin-top: 0.0801rem
}

.headf {
  height: 1.335rem;
  position: relative;
  width: 100%;
  z-index: 33
}

.headf span {
  position: absolute;
  width: 0.2403rem;
  height: 0.4272rem;
  background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABIAAAAgCAYAAAAffCjxAAAAAXNSR0IArs4c6QAAAVdJREFUSEut1usqBVEYxvH/c1MuRJJDkhwTEpIkJUlyCEk++CJJckpycw49Gs3Wa+y9Z82ePd/Xr5n38KwRNR7bE0APMK1OHdtTwAWQGZcdQbZngPMc+QImK0O2Z4GzgIxLuqoE2Z4DTgMyJuk6K08yZHseOAnIqKSbRo2TINsLwHFARiTdxkaVQrYXgaOADEu6K3a7LWR7CTjMD2XdaYq0rZHtFWA/Rz6BIUn3reau6RvZXgX28kMfOfLQbnj/QbbXgN380HuOPJZtwB/I9jqwE5ABSc9lyJ8a2d4AtgPSL+klBfmFbG8CWwHpk/SaivxAtjMgg7Inq0mvpLcqSHehTOvKpzU+oSvFDlj99gesOJCDkp7Kip+yIknT3XL7C0tbum9lMbIMHKQkQEqwJWVSKZTPWTElq0dt6GYxt6uHf8DqX0cBq39BBqz+lR2w+j8RAfv9rfkGqF24CUdT9E4AAAAASUVORK5CYII=) no-repeat 50%;
  background-size: 100%;
  left: 0.4005rem;
  top: 0.3738rem
}

.headf .wenebn {
  text-align: center;
  width: 8.01rem;
  margin: 0 auto
}

.headf .wenebn a {
  display: inline-block;
  background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAUCAYAAACEYr13AAAAAXNSR0IArs4c6QAAAN9JREFUOE+l1EEuhEEQxfFfh4WF4AQILmDFyjGEBYlbWLJngwRhJyzcyjGsnrT4JtJmzHzz1bbr/etVdVcXPSLJBp6xj8tSylWZVZ/kCA9Y/dF8llKWpgKSrOAWp22xUuM/B0n28IrtcXkTAUkWcI4LLE4qMhaQZB0vOJg2nz+AJId4xNo0cT0fAZIs4wZnswi7nG9Akl28Y6ePeOQgyQdq372jczAYMKyF6nvQEH83Pvc1NpD5H1IHGvSUGzd1md6w1WuZGkhd5zuc9F7nBnSM+94fSgPZxBNqa/VLu/4CAOZUFRaNLlUAAAAASUVORK5CYII=) no-repeat 50%;
  background-size: 100%;
  width: 0.2136rem;
  height: 0.267rem;
  vertical-align: middle;
  margin: 0 0.267rem;
  margin-top: -0.534rem
}

.headf .wenebn a.you {
  -webkit-transform: rotate(180deg);
  transform: rotate(180deg)
}

.headf .wenebn .wtxt {
  display: inline-block
}

.headf .wenebn .wtxt h6 {
  color: #fff;
  font-size: 0.4272rem;
  padding-top: 0.2136rem;
  font-weight: 500
}

.headf .wenebn .wtxt p {
  font-size: 0.3204rem;
  color: #fff;
  margin-top: 0.1335rem
}

.jiage {
  width: 9.2115rem;
  margin: 0 auto;
  margin-top: 0.4005rem
}

.jiage a {
  color: #ff5353;
  font-size: 0.801rem;
  font-weight: 600
}

.jiage span {
  color: #ff5353;
  font-size: 0.3738rem;
  margin-left: 0.2937rem;
  font-weight: 600
}

.jiage.die a,
.jiage.die span {
  color: #36bd33
}

.kaihj {
  width: 9.2115rem;
  margin: 0 auto;
  -ms-flex-wrap: wrap;
  flex-wrap: wrap;
  margin-top: 0.4005rem
}

.kaihj,
.kaihj p {
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-pack: justify;
  -ms-flex-pack: justify;
  justify-content: space-between
}

.kaihj p {
  width: 30%;
  margin-bottom: 0.267rem
}

.kaihj p span {
  color: #666;
  font-size: 0.3738rem
}

.kaihj p a {
  font-sizE: 0.3738rem;
  color: #333;
  font-weight: 600
}

.ganhj {
  height: 0.267rem;
  background: #f8f8f8;
  margin-top: 0.1335rem
}

.gouk {
  width: 100%;
  margin: 0 auto
}

.gouk .xunjk {
  width: 9.2115rem;
  margin: 0.4005rem auto;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-pack: justify;
  -ms-flex-pack: justify;
  justify-content: space-between
}

.gouk .xunjk a {
  width: 4.272rem;
  text-align: center;
  color: #333;
  font-size: 0.4272rem
}

.gouk .xuankl {
  width: 9.2115rem;
  margin: 0 auto;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-pack: justify;
  -ms-flex-pack: justify;
  justify-content: space-between
}

.gouk .xuankl .xunl {
  width: 4.272rem
}

.gouk .xuankl .xunl.splk p .spa {
  color: #ef1515
}

.gouk .xuankl .xunl p {
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-pack: justify;
  -ms-flex-pack: justify;
  justify-content: space-between;
  font-size: 0.3204rem;
  margin-top: 0.1602rem;
  color: #333
}

.gouk .xuankl .xunl p span {
  width: 30%;
  text-align: center
}

.gouk .xuankl .xunl p .spa {
  text-align: center;
  color: #00b278
}

.box {
  width: 9.2649rem;
  margin: 0 auto;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex
}

.box .box_left {
  width: 35%;
  text-align: center
}

.box .box_left h6 {
  color: #ef1515;
  font-size: 0.5874rem;
  font-weight: 500;
  padding-top: 0.4806rem
}

.box .box_left h6.alk {
  color: #00b278
}

.box .box_left p {
  color: #ff2929;
  
  font-size: 0.3204rem;
  margin-top: 0.0801rem
}

.box .box_left p.lusk {
  color: #00b278
}

.box .box_left p .sdpl {
  margin-right: 0.1335rem
}

.box .box_re {
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-pack: justify;
  -ms-flex-pack: justify;
  justify-content: space-between;
  -ms-flex-wrap: wrap;
  flex-wrap: wrap;
  width: 65%
}

.box .box_re .tksl {
  width: 33%;
  text-align: right
}

.box .box_re .tksl p {
  font-size: 0.2937rem;
  color: #fff;
  display: inline-block;
  padding: 0.0801rem 0.1068rem;
  border-radius: 0.0534rem
}

.box .box_re .tksl p.pl1 {
  background: #d73d3d
}

.box .box_re .tksl p.pl2 {
  background: #f67f37
}

.box .box_re .tksl p.pl3 {
  background: #3d89d7
}

.box .box_re .tksl p.pl4 {
  background: #5a3dd7;
  margin-top: 0.1869rem
}

.box .box_re .tksl p.pl5 {
  background: #a737f6;
  margin-top: 0.1869rem
}

.box .box_re .tksl p.pl6 {
  background: #56ab6a;
  margin-top: 0.1869rem
}

.box .box_re .tksl h6 {
  font-size: 0.2937rem;
  color: #ccc;
  font-weight: 500;
  margin-top: 0.1068rem
}

.box .box_re .tksl h6.hl {
  color: #d73d3d
}

.xinat {
  width: 9.2115rem;
  margin: 0 auto;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-pack: justify;
  -ms-flex-pack: justify;
  justify-content: space-between;
  margin-top: 0.3738rem;
  position: relative
}

.xinat a {
  width: 2.2161rem;
  height: 0.7743rem;
  font-size: 0.3204rem;
  color: #999;
  text-align: center;
  line-height: 0.7743rem
}

.xinat a.xuan {
  color: #5d7dfb
}

.xinat .tiaol {
  width: 0.6675rem;
  height: 0.0534rem;
  background: #5d7dfb;
  border-radius: 0.0267rem;
  position: absolute;
  left: 0.7476rem;
  bottom: 0;
  -webkit-transition: all .5s;
  transition: all .5s
}

.minc {
  width: 9.2115rem;
  margin: 0 auto;
  font-size: 0.3204rem;
  margin-top: 0.3471rem;
  font-weight: 500;
  color: #9b9a9b
}

.minc span {
  color: #0e6ac1;
  margin-right: 0.0801rem
}

.minc a {
  color: #00b278;
  margin-left: 0.0801rem
}

.minc a.hjk {
  color: #d73d3d
}

.zoushit {
  width: 9.2649rem;
  height: 8.544rem;
  margin: 0 auto;
  background: #e9e9e9;
  margin-top: 0.1335rem
}

.mauru {
  width: 9.2115rem;
  height: 1.2015rem;
  background: linear-gradient(-55deg, rgb(80, 122, 250), rgb(115, 131, 251));
  border-radius: 0.1335rem;
  margin: 0.801rem auto;
  color: #fff;
  font-size: 0.4272rem;
  line-height: 1.2015rem;
  text-align: center;
  font-weight: 600
}

.boxkl {
  width: 100%;
  border-radius: 0.4005rem 0.4005rem 0 0;
  margin-top: -0.267rem;
  background: #fff;
  padding-top: 0.4005rem;
  min-height: 0.801rem
}

.boxkl .name {
  color: #666;
  font-size: 0.3471rem;
  text-align: center;
  margin-top: 0.267rem;
  font-weight: 500;
  margin-bottom: 0.1602rem
}

.boxkl .ulkm {
  height: auto !important
}

.boxkl .ulkm li {
  width: 9.3717rem;
  padding: 0.5073rem 0;
  background: #fff0f0;
  border-radius: 0.1335rem;
  margin: 0 auto;
  margin-bottom: 0.267rem
}

.boxkl .ulkm li .pkl {
  width: 8.8377rem;
  margin: 0 auto;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-pack: justify;
  -ms-flex-pack: justify;
  justify-content: space-between
}

.boxkl .ulkm li .pkl p {
  color: #1a1a1a;
  font-size: 0.3471rem;
  width: 4.005rem
}

.boxkl .ulkm li .pkl p span {
  color: #d9302e
}

.boxkl .ulkm li .dangeh {
  width: 8.8377rem;
  margin: 0 auto;
  margin-top: 0.267rem;
  font-size: 0.3471rem;
  color: #1a1a1a
}

.boxkl .ulkm li .dangeh span {
  color: #d9302e
}

.boxkl .ulkm li .plk {
  margin-top: 0.267rem
}

.boxdskl {
  width: 100%;
  border-radius: 0.4005rem 0.4005rem 0 0;
  margin-top: -0.267rem;
  background: #fff;
  padding-top: 0.4005rem;
  min-height: 0.801rem
}

.boxdskl .fgh li {
  width: 9.3717rem;
  padding: 0.4005rem 0;
  background: #fff0f0;
  border-radius: 0.1335rem;
  margin: 0 auto;
  margin-bottom: 0.267rem
}

.boxdskl .fgh li .bosx {
  width: 8.811rem;
  margin: 0 auto
}

.boxdskl .fgh li .bosx .pl {
  color: #000;
  font-size: 0.3204rem
}

.boxdskl .fgh li .bosx .hl {
  color: #dd362b;
  font-size: 0.3204rem;
  margin-top: 0.267rem;
  font-weight: 500
}

.boxdskl .fgh li .bosxs {
  width: 8.811rem;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  margin: 0 auto;
  -webkit-box-pack: justify;
  -ms-flex-pack: justify;
  justify-content: space-between;
  margin-top: 0.534rem
}

.boxdskl .fgh li .bosxs .plk {
  width: 33%;
  text-align: center
}

.boxdskl .fgh li .bosxs .plk p {
  color: #000;
  font-size: 0.3204rem
}

.boxdskl .fgh li .bosxs .plk h6 {
  color: #dd362b;
  margin-top: 0.1602rem;
  font-size: 0.3204rem;
  font-weight: 500
}


.van-tab {
  font-size: 0.35rem;
}
.hqxz{
  color: rgb(255, 255, 255);
}
.hqwx{
  color: rgb(227, 210, 255);
}
</style>