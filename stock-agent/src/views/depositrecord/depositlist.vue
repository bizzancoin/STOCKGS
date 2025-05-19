<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <a-card :bordered="false">
        <div class="table-page-search-wrapper">
          <a-form layout="inline">
            <a-row :gutter="48">
              <a-col :md="12" :lg="6" :sm="24">
                <a-form-item label="下级代理">
                  <a-select
                    v-model="queryParam.agentId"
                    placeholder="请选择下级代理"
                    @focus="getagentlist"
                    :loading="agentloading">
                    <a-select-option
                      v-for="(item, index) in agentlist"
                      :key="index"
                      :value="item.id">
                      {{ item.agentName }}
                    </a-select-option>
                  </a-select>
                </a-form-item>
              </a-col>
              <a-col :md="12" :lg="6" :sm="24">
                <a-form-item label="入金状态">
                  <a-select v-model="queryParam.state" placeholder="请选择入金状态">
                    <a-select-option :value="0">审核中</a-select-option>
                    <a-select-option :value="1">入金成功</a-select-option>
                    <a-select-option :value="2">入金失败</a-select-option>
                    <a-select-option :value="3">入金取消</a-select-option>
                  </a-select>
                </a-form-item>
              </a-col>
              <a-col :md="12" :lg="6" :sm="24">
                <a-form-item label="支付方式">
                  <a-select v-model="queryParam.payChannel" placeholder="请选择支付方式">
                    <a-select-option :value="0">支付宝</a-select-option>
                    <a-select-option :value="1">对公转账</a-select-option>
                    <a-select-option :value="''">模拟持仓</a-select-option>
                  </a-select>
                </a-form-item>
              </a-col>
              <a-col :md="12" :lg="6" :sm="24">
                <a-form-item label="真实姓名">
                  <a-input v-model="queryParam.realName" style="width: 100%" placeholder="请输入真实姓名" />
                </a-form-item>
              </a-col>
            </a-row>
            <a-row :gutter="48">
              <a-col :md="12" :lg="8" :sm="24">
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
        rowKey="id">
        <span slot="payChannel" slot-scope="text,record">
          <template>
            <a-tag :color="record.payChannel == 0 ? 'blue' : record.payChannel == 1 ? 'purple' : 'green'">
              {{ record.payChannel == 0 ? '支付宝' : record.payChannel == 1 ? '对公转账' : '现金转账' }}
            </a-tag>
          </template>
        </span>
        <span slot="orderStatus" slot-scope="text,record">
          <template>
            <a-tag
              :color="record.orderStatus == 0 ? 'blue' : record.orderStatus == 1 ? 'green' : record.orderStatus == 2 ? 'red' : 'orange'">
              {{ record.orderStatus == 1 ? '成功' : record.orderStatus == 2 ? '失败' : record.orderStatus == 0
                ? '审核中' : '取消'
              }}
            </a-tag>
          </template>
        </span>
      </a-table>
    </a-card>
  </page-header-wrapper>
</template>
<script>
import { agentrechargelist } from '@/api/capital'
import { agentgetSecondAgent } from '@/api/home'
import moment from 'moment'
export default {
    name: 'Fundrecords',
    data () {
        return {
            columns: [
                {
                    title: '用户名称（ID）',
                    dataIndex: 'nickName',
                    align: 'center',
                    width: 180,
                    customRender: (text, row, index) => {
                        return `${row.nickName}（${row.userId}）`
                    }
                },
                {
                    title: '订单号',
                    dataIndex: 'orderSn',
                    align: 'center'
                },
                {
                    title: '充值金额',
                    dataIndex: 'payAmt',
                    align: 'center'
                },
                {
                    title: '充值方式',
                    dataIndex: 'payChannel',
                    align: 'center',
                    scopedSlots: { customRender: 'payChannel' }
                },
                {
                    title: '状态',
                    dataIndex: 'orderStatus',
                    align: 'center',
                    scopedSlots: { customRender: 'orderStatus' }
                },
                {
                    title: '申请时间',
                    dataIndex: 'addTime',
                    align: 'center',
                    width: 180,
                    customRender: (text, row, index) => {
                        return text ? moment(text).format('YYYY-MM-DD HH:mm:ss') : ''
                    }
                },
                {
                    title: '入金时间',
                    dataIndex: 'payTime',
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
                agentId: undefined,
                state: undefined,
                payChannel: undefined,
                realName: ''
            },
            datalist: [],
            agentlist: [],
            agentloading: false,
            agentqueryParam: {
                pageNum: 1,
                pageSize: 100
            }
        }
    },
    created () {
        this.getlist()
    },
    methods: {
        getqueryParam () {
            this.queryParam = {
                pageNum: 1,
                pageSize: 10,
                agentId: undefined,
                state: undefined,
                payChannel: undefined,
                realName: ''
            }
        },
        onChangeRangeDate (value, dateString) {
            this.queryParam.beginTime = dateString[0]
            this.queryParam.endTime = dateString[1]
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
            var that = this
            this.loading = true
            agentrechargelist(this.queryParam).then(res => {
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
