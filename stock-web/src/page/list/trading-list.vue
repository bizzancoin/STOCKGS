<template>
    <div class="stocklistcontainer">
        <div class="toubu">
            <div class="tl">
                <span :class="headertab == 0 ? 'xuank' : ''" @click="headertab = 0">沪深</span>
                <!-- <span :class="headertab == 1 ? 'xuank' : ''" @click="headertab = 1">可转债</span> -->
            </div>
            <a class="tr" @click="getsearch"></a>
        </div>
        <div class="othermain">
            <div class="daboxk">
                <div class="bankuai">
                    <div class="erty" v-for="(i,index) in listArr1" :key="i.id" v-if="index<3" :class="i.floatRate > 0 ? 'redbg' : 'greenbg'" >
                        <h3  :class="i.floatRate > 0 ? 'red' : 'green'">{{i.currentPoint}}</h3>
                        <h5>{{i.indexName}}<a :class="i.floatRate > 0 ? 'red' : 'green'">{{i.floatRate}}%</a></h5>
                    </div>
                    
                   
                </div>
            </div>
            <div class="poklk">
                <span>热门股票</span>
                <a></a>
            </div>
            <div class="renmen">
                <div class="dangeh">
                    <div class="dank" v-for="(item,index) in listArr" v-if="(index<6)" @click="goDetail(item)">
                        <h6>{{item.name}}</h6>
                        <p class="zhangl" :class="item.changepercent>0? 'red':'green'">{{item.buy}}</p>
                        <p class="bili" :class="item.changepercent>0? 'red':'green'">{{item.pricechange}} {{item.changepercent}}%</p>
                    </div>
                </div>
            </div>
            <div class="zhnj">
                <span v-for="(item, index) in diefuList" :key="index" :class="diefuidx == index ? 'xuan' : ''"  
                    @click="diefuidx = index">{{ item.name }}</span>
                <a class="pokl" :style="diefuidx == 0 ? 'left:10%' : diefuidx == 1 ? 'left:30%' : ''"></a>
            </div>
            <div class="kanbox">
                <span class="k1">股票</span>
                <span class="k2">最新</span>
                <span class="k2">涨跌幅</span>
                <span class="k4">涨跌额</span>
            </div>
            <div class="kfgfd"></div>
            <div class="everylistmain">
                <van-list v-model="loading" :finished="finished" :finished-text="$t('hj43')" @load="onLoad" v-if="(diefuidx==0)"
              :immediate-check="false">
                <div class="shujuk" v-for="(item, index) in listArr"  :key="index" @click="goDetail(item)">
                    <div class="s1">
                        <h6>{{item.name}}</h6>
                        <p>
                            <span v-if="(item.symbol.indexOf('sz')>=0)">深</span>
                            <span class="sh" v-if="(item.symbol.indexOf('sh')>=0)">沪</span>
                            <span class="bj"  v-if="(item.symbol.indexOf('bj')>=0)">北</span>
                            <a class="shbg">{{item.symbol}}</a>
                        </p>
                    </div>
                    <div class="s2 " :class="item.changepercent>0?'hqred':'hqgreen'">{{Number(item.buy).toFixed(2)}}</div>
                    <div class="s2 " :class="item.changepercent>0?'hqred':'hqgreen'">{{item.changepercent}}%</div>
                    <div class="s4"><a  :class="item.changepercent>0?'hqredbtnbg':'hqgreenbtnbg'">{{item.pricechange}}</a></div>
                </div>
                </van-list>
                <van-list v-model="loadings" :finished="finisheds" :finished-text="$t('hj43')" @load="onLoads" v-if="(diefuidx==1)"
              :immediate-check="false">
                <div class="shujuk" v-for="(item, index) in listArrs"  :key="index" @click="goDetail(item)">
                    <div class="s1">
                        <h6>{{item.name}}</h6>
                        <p>
                            <span v-if="(item.symbol.indexOf('sz')>=0)">深</span>
                            <span class="sh" v-if="(item.symbol.indexOf('sh')>=0)">沪</span>
                            <span class="bj"  v-if="(item.symbol.indexOf('bj')>=0)">北</span>
                            <a class="shbg">{{item.symbol}}</a>
                        </p>
                    </div>
                    <div class="s2 " :class="item.changepercent>0?'hqred':'hqgreen'">{{Number(item.buy).toFixed(2)}}</div>
                    <div class="s2 " :class="item.changepercent>0?'hqred':'hqgreen'">{{item.changepercent}}%</div>
                    <div class="s4"><a  :class="item.changepercent>0?'hqredbtnbg':'hqgreenbtnbg'">{{item.pricechange}}</a></div>
                </div>
                </van-list>
                <!-- <div class="shujuk">
                    <div class="s1">
                        <h6>格利尔</h6>
                        <p>
                            <span style="display: none;">深</span>
                            <span class="sh" style="display: none;">沪</span>
                            <span class="bj" style="">北</span>
                            <a class="bjbg">BJ831641</a>
                        </p>
                    </div>
                    <div class="s2 hqred">12.65</div>
                    <div class="s2 hqred">+31.77%</div>
                    <div class="s4"><a class="hqredbtnbg">3.05</a></div>
                </div> -->
                <!-- <div class="shujuk" v-for="i in 6">
                    <div class="s1">
                        <h6>安奈儿</h6>
                        <p><span style="">深</span><span class="sh" style="display: none;">沪</span><span class="bj"
                                style="display: none;">北</span><a class="">SZ002875</a></p>
                    </div>
                    <div class="s2 hqred">19.12</div>
                    <div class="s2 hqred">+10.01%</div>
                    <div class="s4"><a class="hqredbtnbg">1.74</a></div>
                </div> -->
            </div>
        </div>
        <div style="height:1.5rem"></div>
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
            headertab: 0,
            diefuList: [
                {
                    name: '涨跌幅',
                },
                {
                    name: '跌幅榜',
                }
            ],
            diefuidx: 0,
            listArr: [],
            listArr1: [],
            listArrs: [],
            pageNum: 1,
            finished: false,
            loading: false,
            pageNums: 1,
            finisheds: false,
            loadings: false,
        }
    },
    mounted() {
        this.getListMarket()
        this.getStock()
        this.getStocks()
    },
    methods: {
        // goDetail(item){
            
        //     var type = item.symbol.substring(0,2)
        //     this.$router.push({path:'/kline',query:{
        //             code:item.code,
        //             type:type,
        //         }})
        // },
        async getListMarket() {
      let val = {
        pageNum: 1,
        pageSize: 15
      };
      // 获取指数列表
      let result = await api.getListMarket(val);
      this.loading = false;
      if (result.status === 0) {
       
          this.listArr1 = result.data;
        
      } else {
        this.texts = result.msg;
        this.alertShow = true;
      }
    },
    getsearch() {
            this.$router.push({
                path: '/Searchlist'
            })
        },
    onLoad() {
      this.pageNum++;
      // 异步更新数据
      // setTimeout 仅做示例，真实场景中一般为 ajax 请求
      switch (this.diefuidx) {
        case 0:
        //   this.stockPlate = "";
        //   this.stockType = "";
          this.loading = true;
          this.getStock();
          break;
      }
    },
    onLoads() {
      this.pageNums++;
      // 异步更新数据
      // setTimeout 仅做示例，真实场景中一般为 ajax 请求
      switch (this.diefuidx) {
        case 1:
          this.loadings = true;
          this.getStocks();
          break;
      }
    },
    async getStock() {
      //沪深升序
      let opt = {
        pageNo: this.pageNum,
        pageSize: 15,
        asc:0,
        sort: 'changepercent',
        node: 'hs_a',
        // stockPlate: this.stockPlate,
        // keyWords: this.gpcode,
        // stockType: this.stockType
      };
      let data = await api.getStockSort(opt);
      this.loading = false;
      if (data.status === 0) {
        if (data.data.length < 15) {
          this.finished = true;
        }
        if (this.diefuidx == 0) {
          
            data.data.forEach(element => {
              this.listArr.push(element); 
            });
          console.log(this.listArr)
        }
      } else {
        this.texts = data.msg;
        this.alertShow = true;
      }
    },
    async getStocks() {
      //沪深降序
      let opt = {
        pageNo: this.pageNum,
        pageSize: 15,
        asc:1,
        sort: 'changepercent',
        node: 'hs_a',
        // stockPlate: this.stockPlate,
        // keyWords: this.gpcode,
        // stockType: this.stockType
      };
      let data = await api.getStockSort(opt);
      this.loadings = false;
      if (data.status === 0) {
        if (data.data.length < 15) {
          this.finished = true;
        }
        
          
            data.data.forEach(element => {
              this.listArrs.push(element); 
            });
     
      } else {
        // this.texts = data.msg;
        // this.alertShow = true;
      }
    },
    //进入详情
    goDetail(item) {
    //   if (this.userData.length == 0) {

    //     return;
    //   }
      var codes = "";
      var names = "";
      var stock_type = "";
      var soks = "";
      var if_zhishu = '0';
      var if_us = '';
      codes = item.code;
          names = item.name;
          stock_type = item.symbol.substring(0, 2);
          soks =  this.filterSH(item.symbol);
          if_zhishu = '0';
          if_us = item.stock_type == 'us' ? '1' : '';
      this.$router.push({
        path: "/kline",
        query: {
          name: names,
          stockplate: item.stock_plate,
          code: codes,
          type: stock_type,
          sok: soks,
          if_us: if_us,
          usType: item.type,
          if_zhishu: if_zhishu,
        }
      });
    },
    filterSH(val) {
      if (val.indexOf('sh')>=0) {
        return 1;
      } else if (val.indexOf('bj')>=0 || val.indexOf('sz')>=0) {
        return 0;
      }
    },
    }
}
</script>


<style lang="less" scoped>
.red {
    color: #ff5353 !important;
}

.green {
    color: #20b844 !important;
}

.hqred {
    color: #f11614 !important;
}

.hqgreen {
    color: #09965f !important;
}

.redbg {
    background: #fdf7f7 !important;
}

.greenbg {
    background: #f4faf8 !important;
}

.stocklistcontainer {}

.toubu {
    width: 100%;
    height: 1.60rem;
    background: linear-gradient(-55deg, rgb(80, 122, 250),rgb(115, 131, 251));
    display: flex;
    justify-content: space-between;

    .tl {
        margin-left: 0.32rem;
        margin-top: 0.37rem;

        span {
            color: #fff;
            font-size: .37rem;
            margin-right: 0.53rem;
            vertical-align: bottom;
        }

        .xuank {
            font-size: .48rem;
        }
    }

    .tr {
        width: 0.43rem;
        height: 0.43rem;
        background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAAXNSR0IArs4c6QAAA4dJREFUWEe9l0msn2MUxn8PYl4QolhIKhYS00JalAiCIIbeBg1FSrsTQxcsGrEo3WBR0pWZkjSGVEpMiSkiTRPTgkhKYiaGiilmHnna8/H1/r//4N6/+27u/X/vec953nPOc855xZBle39gAXAycCiwH7Ar8C3wIfAa8DTwhKSfh+mbvK9+B2wfCKwAFgI7jKD4G+BW4BZJP40gv0WkB4DtfLsaWAns0lL0MfAq8CnwA7AbcAhwHLBTS+4D4CJJr4wCYhsAtrcH7gQW12EDj9atNnYptL07MAEsBw4umd+ByyQ9MAzEPwDq5vcHfR36qG7y8jAl2be9HXANcGOF7M86v3bQ+TaAK4DbSjiuPk3S5lGMt2Vsnwo8DuwIJBfmSnq7n54tAGzPBiKUmCfWR0r66r8ab+Rtnw08VjmW0M2T9FeXvgbAmnJ9Yn6SpBenarwFYhVwVf1eKOmhTgC2ZwGfVNzWSQrnp71s7wG8D+TvBknz+gG4HFhdmydIemna1hsK2akLV9bP2ZJC0W2WbK8D5gOJ+SxJCcNYlu3jgeZCSyTd3QXgXeAgYL2kc8Zi+V8PJKl/BELRVZKWdQGIQKraakmh4liX7c+qf6yVdEEXgMblN0u6dqzWt1I8DeuAfh5ODqSJ7AncJWnp/wCg0b9G0iVdHngTOGIQVaYKyva+wOd1fqWk67oApPksAX4D9pKUnBjLsn0h8GApm5CU6thDw3OBh+vrpZLuHYv1rfF/LpUV+LUo/l0XgJ2r/u8NvAMcLumP6YKwPRdoWngnA2Kj6QWJzQ1l9HpJzf9TwmE7A0o6aka4sCzN7Y0uZQ2AzHhvAemK6eMLJK2fivWaC+5rzRUD2dWeB44Fnq8+npgtHWWiaYO0nXDeDlxc3zcBcyR93+8yk0eyRUCmopTOuC4MWS7p62HesH0UcAdwWMl+kXlR0nuDznYNpecD99TonbOhZagUpmxsaFquToU7BTivxvZGX/rLGZmGgHTbFZKe6ZsDkzdsJ3nSueZ0HPoSCEv26RjX47WAXxa3294AHF01Zr6kp3po2M89dcN4I/38mCEhSM6kyNwk6fVG1vbp9T3zYQpdD4i+D5NJyZVHyokV37yU8lD5pSae0O0FSXkp9SzbZwGPVHL3gBgJwLAEHLY/CMSMAAjADhDpDU/OGIACcWa9tJqcmJhRAB0gNs84gBaIFLxn/wb6PUsFkO1mDQAAAABJRU5ErkJggg==) no-repeat 50%;
        background-size: 100%;
        margin-right: 0.40rem;
        margin-top: 0.32rem;
    }
}

.othermain {
    .daboxk {
        width: 100%;
        background: #fff;
        margin-top: -0.32rem;
        min-height: 1.07rem;
        border-radius: 0.4rem 0.4rem 0 0;
        padding-top: 0.4rem;

        .bankuai {
            width: 9.32rem;
            margin: 0 auto;
            display: flex;
            justify-content: space-between;

            .erty {
                width: 2.99rem;
                height: 2.14rem;
                background: #ffffff;
                border-radius: 0.21rem;
                text-align: center;

                h3 {
                    font-size: .53rem;
                    font-family: SourceHanSansCN;
                    padding-top: 0.266rem;
                    font-weight: 800;
                    line-height: .75rem;
                    margin-top: 0.13rem;
                }

                h5 {
                    font-size: .32rem;
                    font-family: SourceHanSansCN;
                    font-weight: 500;
                    color: #333;
                    margin-top: 0.266rem;

                    a {
                        margin-left: 0.11rem;
                    }
                }
            }
        }
    }

    .poklk {
        width: 9.48rem;
        margin: 0 auto;
        display: flex;
        flex-direction: column;
        margin-top: 0.4rem;

        span {
            color: #333;
            font-size: .38rem;
            position: relative;
            font-weight: 600;
        }

        a {
            background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAFoAAAAQCAYAAACWa2gIAAAAAXNSR0IArs4c6QAABGRJREFUWEftmD1vI1UUht/3nnvveEm8sCuxiBRUq21o+A0QWn4HDQ0VBS0SHQUfJR0NlPyARdAhaKEAgVhWG8FKCfl2bM856Fx7kvHkY8deoAluPBl7bM/jZ557HGLJ2/vb28+u10cv6Hi6ZmRY8vBr8XSaacjx8ECe+eOd27d3/aS5zJl/+PjBhk7sxWWOue7PDYlbbz3/0qPeoN3kG6P9u9cd3CrnfzwY/twb9EePH96rJ/VwlTe67sdIkv3eoD9++Osr0xDkukNb5fyjav0/6FXILXmML44LoM0sfA/IPiAHQFgHwhSQNezJL9vH96bQmySFEEaogBQQYmAQ3wZE5vsFEo0aCEaQGcoYBcnARLVMCZHKbNRESCQ0BzIBiAQzYBGE/50CGA2WAX8/Rmp5LBoQA1Ded/YYIgCfhAIMkQQNEJZF3yck88f8nJtpqdluc7hs2/EeqdkPh3X92YO9vW/68u6kw3jfIHeA8CcQHPQtQI6B4KC3Tuy5/cOjuw46AEH8g5Ni/qFJcdC+n1AHK848UKULWsEY1CoKIlWSUTPBFBwwmQhk/zIAS5yBWwW0Qw80hDnoGfwZ5KcBfcp2XNef/rS7+0kf2AuLodv8BcCXAXHQ42IyQgN6iiCP/jraODHdaBvdBt02+mrQzBRkKt367EYHaHLQDhZg5qLRye112916Uyv2Xmm0f8gz0JcZ3YbfMLvK6AWue9Px27/vHXx9Fexz410b9G8Aa0DaRjvoGuuyc7B1azRNd4LVQ6Vf0mdGO+gABlKjg5ZyXy73TjqYKEhn6SgmJ4fo9yUvq4P2lPB8Okpi/NbY3Ww3li8NWs2++3Fn580u6Cf8YDmfjnajHfTYISLECITRaByzp2PsICdxXFUxTaaloXbay9Jnb+6AVE9CNiKz1gxaZZDK6GmwyhRlH4NkqmYjs6llEJVbbp6S8gXAXyN580Fz+2Pp+cz2qDPbHarvE5iL0MD1pJ12up2RxuKroF8k7v7mYHizTz5OLxM3+isgXNboxugJjgpsHZ1IH9BKplQAa+WgSSRVG8wTUYFaKSQF1epi0CEb1JtdWt4TdIFMIJihAe/5cNDtBbC7GC4L+mBzMOz126LVo5nRQ4DN1NFOx0lZtYfRQVegNEaPyVgWxskkkqEY7JZZsYqxAe33gA66RmO2GGY32mg5BKkWjQ4Z8KthZrRnJcCSGdOTjO4ButvoZUHf3xwMX/1PjHbQaTyRJh2GJGSdZgmBOGABqyYdbrTVWnkaAA7aoN1olIkDA87SkcBFo0u/aaX5/wDopzKaCG+8Nlj78l8D7UZ7OsAQm0arz9dzo7uNdtCeDvWJgqzg412x9OJGm2rVBt022tPh/V3SaDd3tkieLYZP1WgzvPf6jeG7fSB3/ntn/NwQmvGuO0c3jVYci4ISRyMfME5BX7YYdhvdpOMyo30xnIP2MS73MZqeEp94OothKx3zSeNcoy9KR5tL55czDwH7lggf9DW5+SL+BkSaMTe4+9tFAAAAAElFTkSuQmCC) no-repeat 50%;
            background-size: 100%;
            width: 1.20rem;
            height: 0.21rem;
            margin-top: 0.13rem;
        }
    }

    .renmen {
        .dangeh {
            width: 9.48rem;
            margin: 0 auto;
            display: flex;
            justify-content: space-between;
            flex-wrap: wrap;

            .dank {
                width: 30%;
                text-align: center;
                padding: 0.42rem 0;
                background: #fdf7f7;
                border-radius: 0.21rem;
                margin-top: 0.266rem;

                h6 {
                    color: #333;
                    font-size: .37rem;
                }

                .zhangl {
                    font-size: .53rem;
                    margin-top: 0.266rem;
                    font-weight: 600;
                }

                .bili {
                    font-size: .266rem;
                    margin-top: 0.29rem;
                }
            }
        }
    }

    .zhnj {
        width: 100%;
        display: flex;
        height: 1.01rem;
        border-bottom: 0.03rem solid #e0e0e0;
        position: relative;

        span {
            width: 20%;
            text-align: center;
            line-height: 1.01rem;
            color: #333;
            font-size: .37rem;
        }

        .pokl {
            position: absolute;
            width: 0.53rem;
            height: 0.11rem;
            background: #5d7dfb;
            border-radius: 0.05rem;
            bottom: 0;
            left: 10%;
            margin-left: -0.266rem;
        }

        .xuan {
            color: #5d7dfb;
        }
    }

    .kanbox {
        width: 9.48rem;
        margin: 0 auto;
        margin-top: 0.32rem;
        padding-bottom: 0.32rem;
        display: flex;
        justify-content: space-between;

        span {
            color: #666;
            font-size: .35rem;
        }

        .k1 {
            width: 30%;
            padding-left: 0.266rem;
        }

        .k2 {
            width: 25%;
            text-align: center;
        }

        .k4 {
            width: 20%;
            text-align: right;
        }
    }

    .kfgfd {
        height: 1px;
        background: #e0e0e0;
    }

    .everylistmain {
        width: 9.48rem;
        margin: 0 auto;

        .shujuk {
            margin: 0 auto;
            margin-top: 0.40rem;
            padding-bottom: 0.32rem;
            display: flex;
            justify-content: space-between;

            .s1 {
                width: 30%;

                h6 {
                    color: #333;
                    font-size: .37rem;
                    font-weight: 500;
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

            .s2 {
                text-align: center;
                font-size: .4rem;
                margin-top: 0.13rem;
                font-weight: 600;
                width: 25%;
            }

            .s4 {
                width: 20%;
                text-align: right;

                span {
                    display: inline-block;
                    width: 1.34rem;
                    height: 0.67rem;
                    border-radius: 0.13rem;
                    color: #fff;
                    font-size: .35rem;
                    text-align: center;
                    line-height: .67rem;
                }

                a {
                    display: inline-block;
                    width: 1.34rem;
                    height: 0.67rem;
                    border-radius: 0.13rem;
                    color: #fff;
                    font-size: .35rem;
                    text-align: center;
                    line-height: .67rem;
                }

                .hqgreenbtnbg {
                    background: #09965f;
                }

                .hqredbtnbg {
                    background: #f11614;
                }
            }
        }
    }
}
</style>