<template>
  <page-header-wrapper>
    <template v-slot:content>
      <div class="page-header-content">
        <div class="avatar">
          <a-avatar size="large" :src="currentUser.avatar" />
        </div>
        <div class="content">
          <div class="content-title">
            {{ timeFix }}，{{ userdetails.adminName ? userdetails.adminName : 'Admin' }}<span class="welcome-text">，{{
              welcome
            }}</span>
          </div>
          <div>{{ lizhidetail.content }} —— {{ lizhidetail.author }}</div>
        </div>
      </div>
    </template>
    <template v-slot:extraContent>
      <div class="extra-content">
        <div class="stat-item">
          <a-statistic title="代理数量" :value="infodetail.agent_num" />
        </div>
        <div class="stat-item">
          <a-statistic title="实盘用户" :value="infodetail.user_sp_num" />
        </div>
        <div class="stat-item">
          <a-statistic title="模拟用户" :value="infodetail.user_moni_num" />
        </div>
      </div>
    </template>
    <div>
      <a-row :gutter="24">
        <a-col :xl="16" :lg="24" :md="24" :sm="24" :xs="24">
          <a-card
            class="project-list"
            :loading="loading"
            style="margin-bottom: 24px;"
            :bordered="false"
            title="大盘指数"
            :body-style="{ padding: 0 }">
            <!-- <a slot="extra">全部项目</a> -->
            <div>
              <a-card-grid class="project-card-grid" :key="i" v-for="(item, i) in market" v-if="i < 6">
                <a-card :bordered="false" :body-style="{ padding: 0 }">
                  <a-card-meta>
                    <div slot="title" class="card-title">
                      <!-- <a-avatar size="small" :src="item.cover" /> -->
                      <a
                        :style="'background:' + color16()"
                        style="color:#fff;font-size:14px;border-radius:50%;padding:6px;line-height: 1;">
                        {{ item.name.substr(0, 1) }}
                      </a>
                      <a style="margin-left:8px">{{ item.name }}</a>
                      <a :class="item.increase < 0 ? 'greens' : 'reds'" style="margin-left:10px">({{ item.nowPrice
                      }})</a>
                    </div>
                    <div slot="description" class="card-description" v-if="i == 0">
                      上证指数是一个反映上海证券交易所挂牌股票总体走势的统计指标
                    </div>
                    <div slot="description" class="card-description" v-if="i == 1">
                      深证成指交易所成份股价指数（简称深证成指）是深圳证券交易所的主要股指。
                    </div>
                    <div slot="description" class="card-description" v-if="i == 2">
                      创业板是一种证券市场，是专为暂时无法在主板市场上市的创业型企业。
                    </div>
                    <div slot="description" class="card-description" v-if="i == 3">
                      沪深300指数由沪深市场中规模大、流动性好的最具代表性的300只证券组成。
                    </div>
                    <div slot="description" class="card-description" v-if="i == 4">
                      中小企业100指数样本应该都是在深交所中小企业板上市交易的A股。
                    </div>
                    <div slot="description" class="card-description" v-if="i == 5">
                      创业板50指数是在创业板指的100只股票中，选取最近半年日均成交额排名靠前的50只股票。
                    </div>
                  </a-card-meta>
                  <div class="project-item">
                    <a href="/#/" :class="item.increase < 0 ? 'greens' : 'reds'">{{ item.increase }}</a>
                    <span class="datetime" :class="item.increase < 0 ? 'greens' : 'reds'">{{ item.increaseRate
                    }}%</span>
                    <!-- <a-tag :color="item.increase < 0 ? '#87d068' : '#f50'" style="margin-right:0">
                      {{item.increaseRate}}%
                    </a-tag> -->
                  </div>
                </a-card>
              </a-card-grid>
            </div>
          </a-card>

          <a-card :loading="loading" title="持仓动态" :bordered="false">
            <a-list>
              <a-list-item :key="index" v-for="(item, index) in rongzilist">
                <a-list-item-meta>
                  <div slot="title">
                    <span>@{{ item.nickName }}</span>&nbsp; 买入了&nbsp;<a href="#">{{ item.stockName }}({{ item.stockCode
                    }})</a>&nbsp;
                    <span style="margin-right:10px;">方向</span>
                    <a-tag :color="item.orderDirection == '买涨' ? 'red' : 'green'">
                      {{ item.orderDirection }}
                    </a-tag>
                  </div>
                  <div slot="description">{{ item.buyOrderTime | moment }}</div>
                </a-list-item-meta>
              </a-list-item>
            </a-list>
          </a-card>
        </a-col>
        <a-col
          style="padding: 0 12px"
          :xl="8"
          :lg="24"
          :md="24"
          :sm="24"
          :xs="24">
          <a-card
            title="快速开始 / 便捷导航"
            style="margin-bottom: 24px;display:none"
            :bordered="false"
            :body-style="{ padding: 0 }">
            <div class="item-group">
              <a>操作一</a>
              <a>操作二</a>
              <a>操作三</a>
              <a>操作四</a>
              <a>操作五</a>
              <a>操作六</a>
              <a-button size="small" type="primary" ghost icon="plus">添加</a-button>
            </div>
          </a-card>
          <a-card
            title="占比统计"
            style="margin-bottom: 24px"
            :loading="radarLoading"
            :bordered="false"
            :body-style="{ padding: 0 }">
            <div style="min-height: 400px;">
              <!-- <radar :data="radarData" /> -->
              <v-chart :force-fit="true" :height="405" :data="pieData" :scale="pieScale">
                <v-tooltip :showTitle="false" dataKey="item*percent" />
                <v-axis />
                <!-- position="right" :offsetX="-140" -->
                <v-legend dataKey="item" />
                <v-pie position="percent" color="item" :vStyle="pieStyle" />
                <v-coord type="theta" :radius="0.75" :innerRadius="0.6" />
              </v-chart>
            </div>
          </a-card>
          <a-card :loading="loading" title="充值提现统计" :bordered="false">
            <div class="members">
              <a-row>
                <a-col :span="12">
                  <a>
                    <span>今日充值</span>
                    <span class="member">{{ infodetail.charge_today_sum_amt ? infodetail.charge_today_sum_amt : 0
                    }}</span>
                  </a>
                </a-col>
                <a-col :span="12">
                  <a>
                    <span>总充值</span>
                    <span class="member">{{ infodetail.charge_sum_amt ? infodetail.charge_sum_amt : 0 }}</span>
                  </a>
                </a-col>
                <a-col :span="12">
                  <a>
                    <span>今日提现</span>
                    <span class="member">{{ infodetail.sp_withdraw_sum_today_amt_success ?
                      infodetail.sp_withdraw_sum_today_amt_success : 0
                    }}</span>
                  </a>
                </a-col>
                <a-col :span="12">
                  <a>
                    <span>总提现</span>
                    <span class="member">{{ infodetail.sp_withdraw_sum_amt_success ?
                      infodetail.sp_withdraw_sum_amt_success : 0
                    }}</span>
                  </a>
                </a-col>
              </a-row>
            </div>
          </a-card>
        </a-col>
      </a-row>
    </div>
  </page-header-wrapper>
</template>

<script>
import { timeFix } from '@/utils/util'
import { mapState } from 'vuex'
import { PageHeaderWrapper } from '@ant-design-vue/pro-layout'
import { Radar } from '@/components'
import { getRoleList, getServiceList } from '@/api/manage'
import { stockgetMarket, countdata } from '@/api/home'
import { positionlist } from '@/api/position'
import { adminlist } from '@/api/managesettings'
import axios from 'axios'

const DataSet = require('@antv/data-set')
export default {
  name: 'Workplace',
  components: {
    PageHeaderWrapper,
    Radar
  },
  data () {
    return {
      pieScale: null,
      pieData: null,
      sourceData: null,
      pieStyle: {
        stroke: '#fff',
        lineWidth: 1
      },
      timeFix: timeFix(),
      avatar: '',
      user: {},

      projects: [],
      loading: true,
      radarLoading: true,
      activities: [],
      teams: [],

      // data
      axis1Opts: {
        dataKey: 'item',
        line: null,
        tickLine: null,
        grid: {
          lineStyle: {
            lineDash: null
          },
          hideFirstLine: false
        }
      },
      axis2Opts: {
        dataKey: 'score',
        line: null,
        tickLine: null,
        grid: {
          type: 'polygon',
          lineStyle: {
            lineDash: null
          }
        }
      },
      scale: [
        {
          dataKey: 'score',
          min: 0,
          max: 80
        }
      ],
      axisData: [
        { item: '引用', a: 70, b: 30, c: 40 },
        { item: '口碑', a: 60, b: 70, c: 40 },
        { item: '产量', a: 50, b: 60, c: 40 },
        { item: '贡献', a: 40, b: 50, c: 40 },
        { item: '热度', a: 60, b: 70, c: 40 },
        { item: '引用', a: 70, b: 50, c: 40 }
      ],
      radarData: [],

      market: [],
      infodetail: {},
      rongzilist: [],
      userdetails: '',
      lizhidetail: ''
    }
  },
  computed: {
    ...mapState({
      nickname: state => state.user.nickname,
      welcome: state => state.user.welcome
    }),
    currentUser () {
      return {
        name: 'Serati Ma',
        avatar: 'https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png'
      }
    },
    userInfo () {
      return this.$store.getters.userInfo
    }
  },
  created () {
    this.user = this.userInfo
    this.avatar = this.userInfo.avatar

    getRoleList().then(res => {
      // console.log('workplace -> call getRoleList()', res)
    })

    getServiceList().then(res => {
      // console.log('workplace -> call getServiceList()', res)
    })
  },
  mounted () {
    this.getActivity()
    this.initRadar()
    this.getdapan()
    this.getcountdata()
    this.getrongzilist()
    this.getnowuser()
    this.getlizhi()
  },
  methods: {
    getlizhi () {
      axios.get('https://apisto.bizzan.site/saying/get').then(res => {
        this.lizhidetail = res.data.data
      })
    },
    getnowuser () {
      adminlist().then(res => {
        if (window.localStorage.getItem('phones')) {
          var index = res.data.list.findIndex(item => item.adminPhone == window.localStorage.getItem('phones'))
          this.userdetails = res.data.list[index]
        } else {
          this.$notification['error']({
            message: '重新登陆',
            description: '未登录或登录过期，请重新登录'
          })
          this.$store.dispatch('Logout').then(() => {
            setTimeout(() => {
              window.localStorage.clear()
              window.location.reload()
            }, 1500)
          })
        }
      })
    },
    getrongzilist () {
      var data = {
        pageNum: 1,
        pageSize: 5,
        state: 0
      }
      positionlist(data).then(res => {
        this.rongzilist = res.data.list
      })
    },
    color16 () { // 十六进制颜色随机
      var r = Math.floor(Math.random() * 256)
      var g = Math.floor(Math.random() * 256)
      var b = Math.floor(Math.random() * 256)
      var color = '#' + r.toString(16) + g.toString(16) + b.toString(16)
      return color
    },
    getdapan () {
      stockgetMarket().then(res => {
        this.market = res.data.market
        this.loading = false
      })
    },
    getcountdata () {
      countdata().then(res => {
        this.infodetail = res.data
        this.sourceData = [
          { item: '股票数量', count: this.infodetail.stock_num },
          { item: '客户总资金', count: this.infodetail.user_sp_sum_amt },
          { item: '客户可用资金', count: this.infodetail.user_sp_sum_enable },
          { item: '持仓订单', count: this.infodetail.sp_position_num },
          { item: '平仓订单', count: this.infodetail.sp_pc_position_num },
          { item: '总入金', count: this.infodetail.charge_sum_amt }
        ]
        this.pieScale = [{
          dataKey: 'percent',
          min: 0,
          formatter: '.0%'
        }]

        var dv = new DataSet.View().source(this.sourceData)
        dv.transform({
          type: 'percent',
          field: 'count',
          dimension: 'item',
          as: 'percent'
        })
        this.pieData = dv.rows
      })
    },
    getActivity () {
      this.$http.get('/workplace/activity').then(res => {
        this.activities = res.result
      })
    },
    initRadar () {
      this.radarLoading = true

      this.$http.get('/workplace/radar').then(res => {
        const dv = new DataSet.View().source(res.result)
        dv.transform({
          type: 'fold',
          fields: ['个人', '团队', '部门'],
          key: 'user',
          value: 'score'
        })

        this.radarData = dv.rows
        this.radarLoading = false
      })
    }
  }
}
</script>

<style lang="less" scoped>
@import './Workplace.less';

.greens {
  color: #52c41a !important;
}

.reds {
  color: #f5222d !important;
}

.project-list {
  .card-title {
    font-size: 0;

    a {
      color: rgba(0, 0, 0, 0.85);
      // margin-left: 12px;
      line-height: 24px;
      height: 24px;
      display: inline-block;
      vertical-align: top;
      font-size: 16px;
      font-weight: 600;

      &:hover {
        color: #1890ff;
      }
    }
  }

  .card-description {
    color: rgba(0, 0, 0, 0.45);
    height: 44px;
    line-height: 22px;
    overflow: hidden;
    // font-size: 20px;
    // font-weight: 600;
  }

  .project-item {
    display: flex;
    margin-top: 8px;
    overflow: hidden;
    font-size: 12px;
    height: 20px;
    line-height: 20px;

    a {
      color: rgba(0, 0, 0, 0.45);
      display: inline-block;
      flex: 1 1 0;
      font-size: 14px;
      letter-spacing: 1px;

      &:hover {
        color: #1890ff;
      }
    }

    .datetime {
      color: rgba(0, 0, 0, 0.25);
      flex: 0 0 auto;
      float: right;
      font-size: 14px;
      letter-spacing: 1px;
    }
  }

  .ant-card-meta-description {
    color: rgba(0, 0, 0, 0.45);
    height: 44px;
    line-height: 22px;
    overflow: hidden;
  }
}

.item-group {
  padding: 20px 0 8px 24px;
  font-size: 0;

  a {
    color: rgba(0, 0, 0, 0.65);
    display: inline-block;
    font-size: 14px;
    margin-bottom: 13px;
    width: 25%;
  }
}

.members {
  a {
    display: block;
    margin: 12px 0;
    line-height: 24px;
    height: 24px;

    .member {
      font-size: 14px;
      color: rgba(0, 0, 0, 0.65);
      line-height: 24px;
      max-width: 100px;
      vertical-align: top;
      margin-left: 12px;
      transition: all 0.3s;
      display: inline-block;
    }

    &:hover {
      span {
        color: #1890ff;
      }
    }
  }
}

.mobile {
  .project-list {
    .project-card-grid {
      width: 100%;
    }
  }

  .more-info {
    border: 0;
    padding-top: 16px;
    margin: 16px 0 16px;
  }

  .headerContent .title .welcome-text {
    display: none;
  }
}
</style>
