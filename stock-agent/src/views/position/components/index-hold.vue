<template>
  <div>
    <a-card :bordered="false">
      <div class="table-page-search-wrapper">
        <a-form layout="inline">
          <a-row :gutter="48">
            <a-col :md="12" :lg="6" :sm="24">
              <a-form-item label="持仓类型">
                <a-select v-model="queryParam.positionType" placeholder="请选择持仓类型">
                  <a-select-option :value="''">全部</a-select-option>
                  <a-select-option :value="0">正式持仓</a-select-option>
                  <a-select-option :value="1">模拟持仓</a-select-option>
                </a-select>
              </a-form-item>
            </a-col>
            <a-col :md="12" :lg="6" :sm="24">
              <a-form-item label="下级代理">
                <a-select
                  v-model="queryParam.agentId"
                  placeholder="请选择下级代理"
                  @focus="getagentlist"
                  :loading="agentloading">
                  <a-select-option v-for="(item, index) in agentlist" :key="index" :value="item.id">
                    {{ item.agentName }}
                  </a-select-option>
                </a-select>
              </a-form-item>
            </a-col>
            <a-col :md="12" :lg="6" :sm="24">
              <a-form-item label="用户Id">
                <a-input v-model="queryParam.userId" style="width: 100%" placeholder="请输入用户Id" />
              </a-form-item>
            </a-col>
            <a-col :md="12" :lg="6" :sm="24">
              <a-form-item label="持仓订单号">
                <a-input v-model="queryParam.positionSn" style="width: 100%" placeholder="请输入持仓订单号" />
              </a-form-item>
            </a-col>
          </a-row>
          <a-row :gutter="48">
            <a-col :md="12" :lg="6" :sm="24">
              <a-form-item>
                <span class="table-page-search-submitButtons">
                  <a-button @click="getqueryParam" icon="redo">
                    重置</a-button>
                  <a-button
                    type="primary"
                    icon="search"
                    style="margin-left: 8px"
                    @click="queryParam.pageNum = 1, getlist()">查询
                  </a-button>

                </span>
              </a-form-item>
            </a-col>
          </a-row>
        </a-form>
      </div>
    </a-card>
    <a-table
      bordered
      :loading="loading"
      :pagination="pagination"
      :columns="columns"
      :data-source="datalist"
      rowKey="id"
      :scroll="{ x: 2800 }">

      <span slot="indexName" slot-scope="text,record">
        <template>
          <div>
            <span style="margin-right:10px">{{ record.indexName }}</span>
            <p>({{ record.indexCode }})</p>
          </div>
        </template>
      </span>
      <span slot="positionType" slot-scope="text,record">
        <template>
          <div>
            <a-tag :color="record.positionType == 1 ? 'blue' : 'green'">
              {{ record.positionType == 1 ? '模拟持仓' : '正式持仓' }}
            </a-tag>
          </div>
        </template>
      </span>
      <span slot="orderDirection" slot-scope="text,record">
        <template>
          <div>
            <a-tag :color="record.orderDirection == '买涨' ? 'red' : 'green'">
              {{ record.orderDirection }}
            </a-tag>
          </div>
        </template>
      </span>
      <span slot="now_price" slot-scope="text,record">
        <template>
          <div>
            <p
              :class="Number(record.now_price) - record.buyOrderPrice < 0 ? 'greens' : Number(record.now_price) - record.buyOrderPrice > 0 ? 'reds' : ''">
              {{ Number(record.now_price).toFixed(2) }}
            </p>
          </div>
        </template>
      </span>
      <span slot="profitAndLose" slot-scope="text">
        <template>
          <div>
            <p :class="text < 0 ? 'greens' : text > 0 ? 'reds' : ''">
              {{ text }}
            </p>
          </div>
        </template>
      </span>
      <span slot="allProfitAndLose" slot-scope="text">
        <template>
          <div>
            <p :class="text < 0 ? 'greens' : text > 0 ? 'reds' : ''">
              {{ text }}
            </p>
          </div>
        </template>
      </span>
    </a-table>
  </div>
</template>
<script>
import { indexpositionlist } from '@/api/position'
import { agentgetSecondAgent } from '@/api/home'
import moment from 'moment'
export default {
    name: 'IndexHold',
    data () {
        return {
            columns: [
                {
                    title: '指数名称',
                    dataIndex: 'indexName',
                    align: 'center',
                    width: 180,
                    scopedSlots: { customRender: 'indexName' }
                },
                {
                    title: '账户类型',
                    dataIndex: 'positionType',
                    align: 'center',
                    scopedSlots: { customRender: 'positionType' }
                },
                {
                    title: '用户名称（ID）',
                    dataIndex: 'realName',
                    align: 'center',
                    customRender: (text, row, index) => {
                        return `${row.realName}（${row.userId}）`
                    }

                },
                {
                    title: '持仓订单号（ID）',
                    dataIndex: 'positionSn',
                    align: 'center',
                    customRender: (text, row, index) => {
                        return `${row.positionSn}（${row.id}）`
                    }

                },
                {
                    title: '买卖方向',
                    dataIndex: 'orderDirection',
                    align: 'center',
                    scopedSlots: { customRender: 'orderDirection' }
                },
                {
                    title: '买入点数',
                    dataIndex: 'buyOrderPrice',
                    align: 'center',
                    customRender: (text, row, index) => {
                        return text.toFixed(2)
                    }
                },
                {
                    title: '最新点数',
                    dataIndex: 'now_price',
                    align: 'center',
                    scopedSlots: { customRender: 'now_price' }
                },
                {
                    title: '保证金',
                    dataIndex: 'allDepositAmt',
                    align: 'center'
                },
                {
                    title: '浮动盈亏',
                    dataIndex: 'profitAndLose',
                    align: 'center',
                    scopedSlots: { customRender: 'profitAndLose' }
                },
                {
                    title: '总盈亏',
                    dataIndex: 'allProfitAndLose',
                    align: 'center',
                    scopedSlots: { customRender: 'allProfitAndLose' }
                },
                {
                    title: '数量（手）',
                    dataIndex: 'orderNum',
                    align: 'center'
                },
                {
                    title: '点浮动价',
                    dataIndex: 'eachPoint',
                    align: 'center'
                },
                {
                    title: '双边手续费',
                    dataIndex: 'orderFee',
                    align: 'center'
                },
                {
                    title: '锁定原因',
                    dataIndex: 'lockMsg',
                    align: 'center'
                },
                {
                    title: '买入时间',
                    dataIndex: 'buyOrderTime',
                    align: 'center',
                    width: 180,
                    customRender: (text, row, index) => {
                        return text ? moment(text).format('YYYY-MM-DD HH:mm:ss') : ''
                    }
                }
            ],
            // 表头
            pagination: {
                total: 0,
                pageSize: 10, // 每页中显示10条数据
                showSizeChanger: true,
                pageSizeOptions: ['10', '20', '50', '100'], // 每页中显示的数据
                onShowSizeChange: (current, pageSize) => this.onSizeChange(current, pageSize), // 改变每页数量时更新显示
                onChange: (page, pageSize) => this.onPageChange(page, pageSize), // 点击页码事件
                showTotal: total => `共有 ${total} 条数据` // 分页中显示总的数据
            },
            loading: false,
            queryParam: {
                pageNum: 1,
                pageSize: 10,
                positionType: '',
                agentId: undefined,
                userId: '',
                positionSn: '',
                state: 0
            },
            datalist: [],
            agentlist: [],
            agentloading: false,
            Lockvisibledialig: false,
            Lockvisibleloading: false,
            Lockvisibleform: this.$form.createForm(this),
            clickpositionId: '',
            agentqueryParam: {
                pageNum: 1,
                pageSize: 100
            }
        }
    },
    created () {
    },
    methods: {
        getinit () {
            this.queryParam = {
                pageNum: 1,
                pageSize: 10,
                positionType: '',
                agentId: undefined,
                userId: '',
                positionSn: '',
                state: 0
            }
            this.getlist()
        },
        getqueryParam () {
            this.queryParam = {
                pageNum: 1,
                pageSize: 10,
                positionType: '',
                agentId: undefined,
                userId: '',
                positionSn: '',
                state: 0
            }
        },
        getagentlist () {
            var that = this
            this.agentloading = true
            agentgetSecondAgent(this.agentqueryParam).then(res => {
                this.agentlist = res.data.list
                setTimeout(() => {
                    that.agentloading = false
                }, 500)
            })
        },
        getlist () {
            this.loading = true
            indexpositionlist(this.queryParam).then(res => {
                this.datalist = res.data.list
                this.pagination.total = res.data.total
                this.loading = false
            })
        },
        onPageChange (page, pageSize) {
            this.queryParam.pageNum = page
            this.getlist()
        },
        onSizeChange (current, pageSize) {
            this.queryParam.pageNum = current
            this.queryParam.pageSize = pageSize
            this.getlist()
        }
    }
}
</script>
<style scoped>
.greens {
    color: #52c41a;
}

.reds {
    color: #f5222d;
}
</style>
