<template>
    <div class="bigbg">
        <div class="toubu">
            <div class="tl">
                <span class="xuank" @click="headertab = 0">自选</span>
            </div>
            <a class="tr" @click="getsearch"></a>
        </div>
        <div class="daboxk">
            <div class="bankuai">
                <div class="erty "  v-for="(i,index) in listArr1" :key="i.id" v-if="index<3" :class="i.floatRate > 0 ? 'redbg' : 'greenbg'"  >
                    <h3 :class="i.floatRate > 0 ? 'red' : 'green'">{{i.currentPoint}}</h3>
                    <h5>{{i.indexName}}<a  :class="i.floatRate > 0 ? 'red' : 'green'">{{i.floatRate}}%</a></h5>
                </div>
               
            </div>
        </div>
        <div class="xuanzhe">
            <div class="sangh">
                <span class="lety">股票</span>
                <span>最新</span>
                <span>涨跌额</span>
                <span>删除</span>
            </div>
            <van-list v-model="loadings" :finished="finisheds" :finished-text="$t('hj43')" @load="onLoads"
              :immediate-check="false">
            <div class="shujkl" @click="goDetail(item, index)" v-for="(item, index) in listArrs"
                    :key="item.indexCode">
                
                <div class="dange">
                    <h6>{{item.stockName}}</h6>
                    <p>
                        <span  v-show="(item.stockGid.indexOf('sz')>=0)">深</span>
                        <span class="sh"  v-show="(item.stockGid.indexOf('sh')>=0)">沪</span>
                        <span class="bj"  v-show="(item.stockGid.indexOf('bj')>=0)">北</span>
                        <a class="shbg">{{item.stockGid}}</a>
                    </p>
                </div>
                <div class="dange"><span class="plk " :class="item.hcrate > 0 ? 'hqred' : 'green'">{{item.nowPrice}}</span></div>
                <div class="dange"><a class="alk "  :class="item.hcrate > 0 ? 'hqredbtnbg' : 'hqgreenbtnbg'">{{item.hcrate}}%</a></div>
                <div class="dange" ><i @click.stop="optionszx(item)"></i></div>
            </div>
            </van-list>
            <!-- <div class="shujkl" v-for="i in 10">
                <div class="dange">
                    <h6>劲仔食品</h6>
                    <p><span style="">深</span><span class="sh" style="display: none;">沪</span><span class="bj"
                            style="display: none;">北</span><a class="">SZ003000</a></p>
                </div>
                <div class="dange"><span class="plk hqgreen">11.72</span></div>
                <div class="dange"><a class="alk hqgreenbtnbg">0.26%</a></div>
                <div class="dange"><i></i></div>
            </div> -->
        </div>

        <div class="tia" @click="$router.push('/trading-list')"><img
                src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABwAAAAcCAYAAAByDd+UAAAAAXNSR0IArs4c6QAAAOpJREFUSEvtljEOgkAQRf94Bk/Aegg7LSWKh9EGjFYasdHDSCBaGTsPIcbexBvAGkALSAw7JGLBTv3nz+TtZHYI3DA8B4RJmiaxw81yORbEEUMEJhAF+ZyWiXB4UPVhFvSWAOYF8xVCa/Gbgp29C0l2zpzkBtexowt+CPDeUCNVmJymIBX+ADLqlxIh6gHoFnQXSHkuzY1bJ9xHR4Lh26CYtQ9Lzb8JJGYE4T0AtCubcBIJzz8UzL6bNafRytoUaRK1Dg2nXb3aFGg1ZbUpoEglemgUSPGGRtR9JtZ+CCfIst07fZ/6W+6p/wKXtZ9BXfy6zgAAAABJRU5ErkJggg=="><span>添加自选</span>
        </div>
        <div style="height:1.5rem"></div>
    </div>
</template>
<script>
import * as api from '@/axios/api'
import { Toast } from "mint-ui";
export default {
    components: {

    },
    props: {},
    data() {
        return {
            loadings: false,
            finisheds: false,
            listArrs: [],
            pageNums: 1,
            gpcodes: "",
            listArr1: [],
        }
    },
    mounted() {
        this.getMyList()
        this.getListMarket()
    },
    methods: {
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
      codes = item.stockCode;
          names = item.stockName;
          stock_type = item.stock_type;
          soks =  this.filterSH(item.stock_type);
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
        getsearch() {
            this.$router.push({
                path: '/Searchlist'
            })
        },
        onLoads() {
      this.pageNums++;
      this.loadings = true;
      this.getMyList();
    },
        async getMyList() {
      this.loadings = true;
      //获取自选列表
      let opt = {
        pageNum: this.pageNums,
        pageSize: 15,
        keyWords: this.gpcodes
      };
      let data = await api.getMyList(opt);
      this.loadings = false;
      if (data.status == 0) {
        data.data.list.forEach(element => {
          this.listArrs.push(element);
        });
      }
      if (data.data.list.length < 15) {
        this.finisheds = true;
      }
    },
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
    async optionszx(val) {
      let data = await api.delOption({ code: val.stockGid });
      if (data.status === 0) {
        Toast({
          message: "删除成功",
          position: "middle",
          duration: 2000
        });
        this.listArrs = [];
        this.pageNums = 1;
        this.finisheds = false;
       
        this.getMyList();
      } else {
        console.log(data.msg);
      }
      if (navigator.vibrate) {
        // 支持
        navigator.vibrate([55]);
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

.xuanzhe {
    width: 9.48rem;
    margin: 0 auto;
    margin-top: 0.29rem;
    border-radius: 0.13rem;

    .sangh {
        width: 9.48rem;
        display: flex;
        justify-content: space-between;

        span {
            width: 25%;
            color: #666;
            text-align: center;
            font-size: .35rem;
        }

        .lety {
            text-align: left;
            padding-left: 0.266rem;
        }
    }

    .shujkl {
        width: 9.48rem;
        display: flex;
        justify-content: space-between;
        border-bottom: 0.026rem solid #e0e0e0;
        padding-bottom: 0.266rem;

        .dange {
            width: 25%;
            margin-top: 0.4rem;
            text-align: center;

            h6 {
                color: #333;
                font-size: .4rem;
                font-weight: 500;
                text-align: left;
            }

            p {
                color: #333;
                font-size: .32rem;
                margin-top: 0.13rem;
                text-align: left;

                span {
                    width: 0.4rem;
                    height: 0.4rem;
                    padding: 0.04rem;
                    background: #3b4fde;
                    border-radius: 0.05rem;
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
                    background: rgba(115, 131, 251, .1);
                }

                .bjbg {
                    color: #ea6248;
                    background: rgba(234, 98, 72, .1);
                }
            }

            .plk {
                font-size: .42rem;
                text-align: center;
                margin-top: 0.13rem;
                display: inline-block;
                font-weight: 600;
            }

            .alk {
                width: 1.33rem;
                height: 0.67rem;
                border-radius: 0.13rem;
                display: inline-block;
                text-align: center;
                line-height: .67rem;
                color: #fff;
                font-size: .35rem;
            }

            i {
                width: 0.43rem;
                height: 0.43rem;
                display: inline-block;
                background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAAXNSR0IArs4c6QAAAW1JREFUWEftl89KAlEUxn9nRsxNf4mw2hhpBKIpQb1AUPtepHbZA2S7epDaF/QAEUSiiwKFIGkKC0oI+qdzQ0cXmYu54WSLucvhm3N+57twuJ/QOKlKDLu2g2IFGGl+8+48I5xgBLbJTRSFeCWKUTsDRr3r2bXyE3ZgSUhaByjWHYm6Qswj6vV3T2BMcwBVXwOZb9YXDoWEVQWGQC55fUlTinnTvD1R5DrEYPCiBVFtACiHxtgnH970ZPLOosn7PZS94ZjQBkDtUpjO/AlA4jYLsuUD+A74DvgOuHcgXh5DSRpTxPWiCkqO86nHrnrtRZS4K4KKum7uCG8oTM6A2D/+0waIWyUMZvUApEwhHOkNwKI1zodKaQGImScfrvTmCrQ6uxBrX4GLmloSH8B3wHfgfzrQx2d5n4NJZzRTcoxSb1qbza1YJISo1e/RbOFhDvvztC/h1AwsOw8MJ55nW/F82O1Av9RVW/E804jnX6EtCd72evTqAAAAAElFTkSuQmCC) no-repeat 50%;
                background-size: 100%;
                margin-top: 0.13rem;
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

.tia {
    text-align: center;
    width: 3.5rem;
    margin: 0.4rem auto;

    img {
        width: 0.37rem;
        height: 0.37rem;
        vertical-align: middle;
        margin-top: -0.11rem;
        margin-right: 0.06rem;
    }

    span {
        color: #333;
        font-size: .37rem;
    }
}
</style>