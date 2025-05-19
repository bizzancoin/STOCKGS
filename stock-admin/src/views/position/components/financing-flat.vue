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
            <a-col :md="12" :lg="8" :sm="24">
              <a-form-item label="卖出时间">
                <a-range-picker
                  show-time
                  style="width: 100%"
                  v-model="times"
                  @change="onChangeRangeDate"
                  format="YYYY-MM-DD HH:mm:ss">
                </a-range-picker>
              </a-form-item>
            </a-col>

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
      <span slot="stockName" slot-scope="text,record">
        <template>
          <div>
            <span style="margin-right:10px">{{ record.stockName }}</span>
            <a-tag
              :color="record.stockPlate == '科创' ? 'blue' : !record.stockPlate ? 'orange' : record.stockPlate == '创业' ? 'pink' : 'purple'">
              {{ record.stockPlate == '科创' ? '科创' : !record.stockPlate ? '股票' : record.stockPlate }}
            </a-tag>
            <p>({{ record.stockCode }})</p>
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
      <template slot="action" slot-scope="text,record">
        <a slot="action" href="javascript:;" @click="getFlatdetails(record)">查看详情</a>
        <a-divider type="vertical" />
        <a slot="action" href="javascript:;" @click="getDelflat(record.id)">删除</a>
      </template>
    </a-table>
    <a-modal title="融资详情" :width="1000" :visible="finacingDialog" :footer="false" @cancel="finacingDialog = false">
      <a-descriptions bordered :title="`${clickitem.stockName}(${clickitem.stockCode})`">
        <a-descriptions-item label="用户名称（ID）">
          <span>{{ clickitem.nickName }}（{{ clickitem.userId }}）</span>
        </a-descriptions-item>
        <a-descriptions-item label="股票类型">
          <a-tag
            :color="clickitem.stockPlate == '科创' ? 'blue' : !clickitem.stockPlate ? 'orange' : clickitem.stockPlate == '创业' ? 'pink' : 'purple'">
            {{ clickitem.stockPlate == '科创' ? '科创' : !clickitem.stockPlate ? 'A股' : clickitem.stockPlate }}
          </a-tag>
        </a-descriptions-item>
        <a-descriptions-item label="账户类型">
          <a-tag :color="clickitem.positionType == 1 ? 'blue' : 'green'">
            {{ clickitem.positionType == 1 ? '模拟持仓' : '正式持仓' }}
          </a-tag>
        </a-descriptions-item>
        <a-descriptions-item label="持仓ID">
          <span>
            {{ clickitem.id }}
          </span>
        </a-descriptions-item>
        <a-descriptions-item label="浮动盈亏">
          <span :class="clickitem.profitAndLose > 0 ? 'reds' : clickitem.profitAndLose < 0 ? 'greens' : ''">
            {{ clickitem.profitAndLose }}
          </span>
        </a-descriptions-item>
        <a-descriptions-item label="总盈亏">
          <span
            :class="clickitem.allProfitAndLose > 0 ? 'reds' : clickitem.allProfitAndLose < 0 ? 'greens' : ''">
            {{ clickitem.allProfitAndLose }}
          </span>
        </a-descriptions-item>
        <a-descriptions-item label="当前价格" v-if="clickitem.now_price">
          <span
            :class="clickitem.now_price - clickitem.buyOrderPrice > 0 ? 'reds' : clickitem.now_price - clickitem.buyOrderPrice < 0 ? 'greens' : ''">
            {{ clickitem.now_price }}
          </span>
        </a-descriptions-item>
        <a-descriptions-item label="卖出价格">
          <span>
            {{ clickitem.sellOrderPrice }}
          </span>
        </a-descriptions-item>
        <a-descriptions-item label="买入价格">
          <span>
            {{ clickitem.buyOrderPrice }}
          </span>
        </a-descriptions-item>
        <a-descriptions-item label="买入数量">
          <span>
            {{ clickitem.orderNum }}
          </span>
        </a-descriptions-item>
        <a-descriptions-item label="买卖方向">
          <a-tag :color="clickitem.orderDirection == '买涨' ? 'red' : 'green'">
            {{ clickitem.orderDirection }}
          </a-tag>
        </a-descriptions-item>
        <a-descriptions-item label="杠杆倍数">
          <span>
            {{ clickitem.orderLever }}
          </span>
        </a-descriptions-item>
        <a-descriptions-item label="总市值">
          <span>
            {{ clickitem.orderTotalPrice }}
          </span>
        </a-descriptions-item>
        <a-descriptions-item label="留仓天数">
          <span>
            {{ clickitem.orderStayDays }}
          </span>
        </a-descriptions-item>
        <a-descriptions-item label="留仓费">
          <span>
            {{ clickitem.orderStayFee }}
          </span>
        </a-descriptions-item>
        <a-descriptions-item label="手续费">
          <span>
            {{ clickitem.orderFee }}
          </span>
        </a-descriptions-item>
        <a-descriptions-item label="印花税">
          <span>
            {{ clickitem.orderSpread }}
          </span>
        </a-descriptions-item>
        <a-descriptions-item label="买入时间">
          <span>
            {{ clickitem.buyOrderTime | moment }}
          </span>
        </a-descriptions-item>
        <a-descriptions-item label="卖出时间">
          <span>
            {{ clickitem.sellOrderTime | moment }}
          </span>
        </a-descriptions-item>
        <a-descriptions-item label="买入订单号">
          {{ clickitem.buyOrderId }}
        </a-descriptions-item>
        <a-descriptions-item label="卖出订单号">
          {{ clickitem.sellOrderId }}
        </a-descriptions-item>
        <a-descriptions-item label="持仓订单号">
          {{ clickitem.positionSn }}
        </a-descriptions-item>
        <a-descriptions-item label="锁仓原因">
          {{ clickitem.lockMsg }}
        </a-descriptions-item>
      </a-descriptions>
    </a-modal>
  </div>
</template>
<script>
import { positionlist, positiondel } from '@/api/position'
import { nextagent } from '@/api/home'
import moment from 'moment'
export default {
    name: 'Financingflat',
    data () {
        return {
            columns: [
                {
                    title: '融资名称',
                    dataIndex: 'stockName',
                    align: 'center',
                    width: 180,
                    scopedSlots: { customRender: 'stockName' }
                },
                {
                    title: '账户类型',
                    dataIndex: 'positionType',
                    align: 'center',
                    scopedSlots: { customRender: 'positionType' }
                },
                {
                    title: '用户名称（ID）',
                    dataIndex: 'nickName',
                    align: 'center',
                    customRender: (text, row, index) => {
                        return `${row.nickName}（${row.userId}）`
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
                    title: '买入价',
                    dataIndex: 'buyOrderPrice',
                    align: 'center',
                    customRender: (text, row, index) => {
                        return text.toFixed(2)
                    }
                },
                {
                    title: '卖出价',
                    dataIndex: 'sellOrderPrice',
                    align: 'center',
                    customRender: (text, row, index) => {
                        return text.toFixed(2)
                    }
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
                    title: '数量（股）',
                    dataIndex: 'orderNum',
                    align: 'center'
                },
                {
                    title: '总市值',
                    dataIndex: 'orderTotalPrice',
                    align: 'center'
                },
                {
                    title: '杠杆倍数',
                    dataIndex: 'orderLever',
                    align: 'center'
                },
                {
                    title: '手续费',
                    dataIndex: 'orderFee',
                    align: 'center'
                },
                {
                    title: '印花税',
                    dataIndex: 'orderSpread',
                    align: 'center'
                },
                {
                    title: '留仓费',
                    dataIndex: 'orderStayFee',
                    align: 'center'
                },
                {
                    title: '留仓天数',
                    dataIndex: 'orderStayDays',
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
                },
                {
                    title: '卖出时间',
                    dataIndex: 'sellOrderTime',
                    align: 'center',
                    width: 180,
                    customRender: (text, row, index) => {
                        return text ? moment(text).format('YYYY-MM-DD HH:mm:ss') : ''
                    }
                },
                {
                    title: '操作',
                    key: 'action',
                    align: 'center',
                    fixed: 'right',
                    width: 150,
                    scopedSlots: { customRender: 'action' }
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
                state: 1,
                beginTime: '',
                endTime: ''
            },
            datalist: [],
            agentlist: [],
            agentloading: false,
            times: [],
            finacingDialog: false,
            clickitem: {},
            agentqueryParam: {
                pageNum: 1,
                pageSize: 100
            }
        }
    },
    created () {
    },
    methods: {
        getFlatdetails (item) {
            this.clickitem = item
            this.finacingDialog = true
        },
        getDelflat (val) {
            var that = this
            this.$confirm({
                title: '提示',
                content: '确认删除平仓单吗？此操作不可恢复',
                onOk () {
                    var data = {
                        positionId: val
                    }
                    positiondel(data).then(res => {
                        if (res.status == 0) {
                            that.$message.success({ content: res.msg, duration: 2 })
                            that.getlist()
                        } else {
                            that.$message.error({ content: res.msg })
                        }
                    })
                },
                onCancel () {
                    console.log('Cancel')
                }
            })
        },
        getinit () {
            this.queryParam = {
                pageNum: 1,
                pageSize: 10,
                positionType: '',
                agentId: undefined,
                userId: '',
                positionSn: '',
                state: 1,
                beginTime: '',
                endTime: ''
            }
            this.times = []
            this.getlist()
        },
        onChangeRangeDate (value, dateString) {
            this.queryParam.beginTime = dateString[0]
            this.queryParam.endTime = dateString[1]
        },
        getqueryParam () {
            this.queryParam = {
                pageNum: 1,
                pageSize: 10,
                positionType: '',
                agentId: undefined,
                userId: '',
                positionSn: '',
                state: 1,
                beginTime: '',
                endTime: ''
            }
            this.times = []
        },
        getagentlist () {
            var that = this
            this.agentloading = true
            nextagent(this.agentqueryParam).then(res => {
                this.agentlist = res.data.list
                setTimeout(() => {
                    that.agentloading = false
                }, 500)
            })
        },
        getlist () {
            var that = this
            this.loading = true
            positionlist(this.queryParam).then(res => {
                this.datalist = res.data.list
                this.pagination.total = res.data.total
                setTimeout(() => {
                    that.loading = false
                }, 500)
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
