<template>
  <page-header-wrapper>
    <template v-slot:content>
      <div class="page-header-content">
        <div class="avatar">
          <a-avatar size="large" :src="currentUser.avatar" />
        </div>
        <div class="content">
          <div class="content-title">
            {{ timeFix }}，{{ userdetail.agentName }}<span class="welcome-text">，{{ welcome }}</span>
          </div>
          <div>{{ lizhidetail.content }} —— {{ lizhidetail.author }}</div>
        </div>
      </div>
    </template>
    <!-- <template v-slot:extraContent>
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
    </template> -->
    <div>
      <a-card :bordered="false" :title="`【账号余额：${userdetail.totalMoney ? userdetail.totalMoney : '0.00'}】`">
        <div class="everylist">
          <a-row :gutter="48">
            <a-col :md="12" :lg="8" :sm="24">
              代理名称：{{ userdetail.agentName }}
            </a-col>
            <a-col :md="12" :lg="8" :sm="24">
              真实姓名：{{ userdetail.agentRealName }}
            </a-col>
            <a-col :md="12" :lg="8" :sm="24">
              代理代码：{{ userdetail.agentCode }}
            </a-col>
          </a-row>
        </div>

        <div class="everylist">
          <a-row :gutter="48">
            <a-col :md="12" :lg="8" :sm="24">
              锁定状态：
              {{ userdetail.isLock == 0 ? '正常' : '锁定' }}
            </a-col>
            <a-col :md="12" :lg="8" :sm="24">
              电话号码：{{ userdetail.agentPhone }}
            </a-col>
            <a-col :md="12" :lg="8" :sm="24">
              创建时间：
              {{ userdetail.addTime | moment }}
            </a-col>
          </a-row>
        </div>

        <div class="everylist">
          链接（移动端）：{{ wapurl }}
          <!-- <a-tag v-clipboard:copy="copyJson" v-clipboard:success="onCopy" v-clipboard:error="onError">
            <span>复制</span>
          </a-tag> -->
          <a-button type="primary" @click="onCopy(wapurl)" icon="copy" size="small" style="margin-left: 10px;">复制</a-button>
        </div>
      </a-card>
    </div>
  </page-header-wrapper>
</template>

<script>
import { timeFix } from '@/utils/util'
import { mapState } from 'vuex'
import { PageHeaderWrapper } from '@ant-design-vue/pro-layout'
import { Radar } from '@/components'
import { getRoleList, getServiceList } from '@/api/manage'
import { agentgetAgentInfo } from '@/api/home'
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
      userdetail: '',
      lizhidetail: '',
      wapurl: ''
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
    this.initRadar()
    this.getAgentInfo()
    this.getlizhi()
  },
  methods: {
    onCopy (text) {
      const oInput = document.createElement('input')
      oInput.value = text
      document.body.appendChild(oInput)
      oInput.select() // 选择对象
      document.execCommand('Copy') // 执行浏览器复制命令
      this.$message.success('复制成功')
      oInput.remove()
    },
    getlizhi () {
      axios.get('https://saying.api.azwcl.com/saying/get').then(res => {
        this.lizhidetail = res.data.data
      })
    },
    // 获取代理信息
    getAgentInfo () {
      agentgetAgentInfo().then(res => {
        if (res.status == 0) {
          this.userdetail = res.data
          // this.wapurl = location.protocol + '//' + location.host + '/#/register?agentCode=' + this.userdetail.agentCode
          var url = location.protocol + '//' + location.host + '/#/register?agentCode=' + this.userdetail.agentCode
          // this.wapurl = location.protocol + '//' + location.host + '/#/register?agentCode=' + this.userdetail.agentCode
          this.wapurl = url.replace('agent', 'wap')
        }
      })
    },
    color16 () { // 十六进制颜色随机
      var r = Math.floor(Math.random() * 256)
      var g = Math.floor(Math.random() * 256)
      var b = Math.floor(Math.random() * 256)
      var color = '#' + r.toString(16) + g.toString(16) + b.toString(16)
      return color
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

.everylist {
  margin: 20px 0;
  font-size: 14px;
  color: #000;
}

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
