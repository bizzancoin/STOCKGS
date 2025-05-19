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
                <a-form-item label="真实姓名">
                  <a-input v-model="queryParam.realName" style="width: 100%" placeholder="请输入真实姓名" />
                </a-form-item>
              </a-col>
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
        <span slot="withStatus" slot-scope="text,record">
          <template>
            <a-tag
              :color="record.withStatus == 0 ? 'blue' : record.withStatus == 1 ? 'green' : record.withStatus == 2 ? 'red' : 'orange'">
              {{ record.withStatus == 1 ? '成功' : record.withStatus == 2 ? '失败' : record.withStatus == 0
                ? '审核中' : '取消'
              }}
            </a-tag>
          </template>
        </span>
        <template slot="action" slot-scope="text,record">
          <a slot="action" href="javascript:;" @click="currentDetails = record; chiDialog = true">出金详情</a>
        </template>
      </a-table>
    </a-card>
    <a-modal title="出金详情" :width="1000" :visible="chiDialog" :footer="false" @cancel="chiDialog = false">
      <a-descriptions
        bordered
        :title="`${currentDetails.nickName}(${currentDetails.userId})`"
        :column="{ xxl: 3, xl: 3, lg: 3, md: 3, sm: 2, xs: 1 }">
        <a-descriptions-item label="出金状态">
          <a-tag
            :color="currentDetails.withStatus == 0 ? 'blue' : currentDetails.withStatus == 1 ? 'green' : currentDetails.withStatus == 2 ? 'red' : 'orange'">
            {{ currentDetails.withStatus == 1 ? '成功' : currentDetails.withStatus == 2 ? '失败' : currentDetails.withStatus == 0
              ? '审核中' : '取消'
            }}
          </a-tag>
        </a-descriptions-item>
        <a-descriptions-item label="订单号">
          {{ currentDetails.bankNo ? currentDetails.bankNo : '--' }}
        </a-descriptions-item>
        <a-descriptions-item label="出金金额">
          {{ currentDetails.withAmt ? currentDetails.withAmt : '--' }}
        </a-descriptions-item>
        <a-descriptions-item label="手续费">
          {{ currentDetails.withFee ? currentDetails.withFee : '--' }}
        </a-descriptions-item>
        <a-descriptions-item label="应转账金额">
          {{ currentDetails.withAmt - currentDetails.withFee }}
        </a-descriptions-item>
        <a-descriptions-item label="取消原因">
          {{ currentDetails.withMsg }}
        </a-descriptions-item>
        <a-descriptions-item label="银行名称">
          {{ currentDetails.bankName }}
        </a-descriptions-item>
        <a-descriptions-item label="支行名称">
          {{ currentDetails.bankAddress }}
        </a-descriptions-item>
        <a-descriptions-item label="银行卡号">
          {{ currentDetails.bankNo }}
        </a-descriptions-item>
        <a-descriptions-item label="申请时间">
          {{ currentDetails.applyTime | moment }}
        </a-descriptions-item>
        <a-descriptions-item label="出金时间">
          {{ currentDetails.transTime | moment }}
        </a-descriptions-item>
      </a-descriptions>
    </a-modal>
  </page-header-wrapper>
</template>
<script>
import { agentwithdrawlist } from '@/api/capital'
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
                    title: '出金金额',
                    dataIndex: 'withAmt',
                    align: 'center'
                },
                {
                    title: '手续费',
                    dataIndex: 'withFee',
                    align: 'center'
                },
                {
                    title: '状态',
                    dataIndex: 'withStatus',
                    align: 'center',
                    scopedSlots: { customRender: 'withStatus' }
                },
                {
                    title: '申请时间',
                    dataIndex: 'applyTime',
                    align: 'center',
                    width: 180,
                    customRender: (text, row, index) => {
                        return text ? moment(text).format('YYYY-MM-DD HH:mm:ss') : ''
                    }
                },
                {
                    title: '出金时间',
                    dataIndex: 'transTime',
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
                agentId: undefined,
                state: undefined,
                realName: ''
            },
            datalist: [],
            agentlist: [],
            agentloading: false,
            agentqueryParam: {
                pageNum: 1,
                pageSize: 100
            },
            chiDialog: false,
            currentDetails: ''
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
            agentwithdrawlist(this.queryParam).then(res => {
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
