<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <div class="table-page-search-wrapper">
        <a-form layout="inline">
          <a-row :gutter="48">
            <a-col :md="6" :sm="24">
              <a-form-item label="订单号/手机号">
                <a-input v-model="queryParam.realName" style="width: 100%" placeholder="请填写订单号/手机号" />
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="24">
              <a-form-item label="配资状态">
                <a-select
                  v-model="queryParam.status"
                  placeholder="请选择用户类型"
                  :default-value="{ key: '0' }">
                  <a-select-option :value="0">待审核</a-select-option>
                  <a-select-option :value="1">操盘中</a-select-option>
                  <a-select-option :value="2">未通过</a-select-option>
                  <a-select-option :value="3">已到期</a-select-option>
                  <a-select-option :value="4">已结束</a-select-option>
                </a-select>
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="24">
              <a-form-item>
                <span class="table-page-search-submitButtons">
                  <a-button type="primary" icon="search" @click="queryParam.pageNum = 1, getlist()">查询
                  </a-button>
                  <a-button style="margin-left: 8px" @click="getqueryParam" icon="redo">
                    重置</a-button>

                </span>
              </a-form-item>
            </a-col>
          </a-row>
        </a-form>
      </div>
    </a-card>

    <a-card :bordered="false">
      <a-table
        bordered
        :loading="loading"
        :pagination="pagination"
        :columns="columns"
        :data-source="datalist"
        rowKey="id">
        <span slot="status" slot-scope="text,record">
          <template>
            <div>
              <a-tag :color="record.status == 0 ? 'green' : 'red'">{{ record.status | statusFilter }}
              </a-tag>
            </div>
          </template>
        </span>
        <template slot="action" slot-scope="text,record">
          <a
            slot="action"
            href="javascript:;"
            @click="currentDetails = record; addUserdialog = true"
            v-if="record.status == 0">配资审核</a>
        </template>
      </a-table>
    </a-card>
    <a-modal
      title="配资审核"
      :width="500"
      :visible="addUserdialog"
      :confirmLoading="addUserDialogloading"
      @ok="OkaddUserdialog"
      @cancel="CanceladdUserdialog">
      <a-form :form="addUserform" ref="addUserform">
        <a-form-item label="审核状态">
          <a-select
            placeholder="请选择审核状态"
            v-decorator="['status', { rules: [{ required: true, message: '请选择审核状态', }] }]">
            <a-select-option value="1">审核通过</a-select-option>
            <a-select-option value="2">未通过</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="子账户" v-if="addUserform.getFieldValue('status') == 1">
          <a-select
            placeholder="请选择子账户"
            v-decorator="['subaccountNumber', { rules: [{ required: true, message: '请选择子账户', }] }]"
            @focus="getEnabledAccount"
            :loading="Accountloading">
            <a-select-option
              v-for="(item, index) in accountList"
              :key="index"
              :value="item.subaccountNumber">{{
                item.subaccountNumber
              }}
            </a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="审核意见">
          <a-input
            placeholder="请输入审核意见"
            v-decorator="['auditOpinion', { rules: [{ required: true, message: '请输入审核意见', }] }]" />
        </a-form-item>
      </a-form>
    </a-modal>
  </page-header-wrapper>
</template>
<script>
import { getApplyList, getTradingAccountList, fundsapplyauditApply } from '@/api/allotment'
import moment from 'moment'
export default {
    name: 'Allotmentlist',
    data () {
        return {
            columns: [
                {
                    title: '订单号',
                    dataIndex: 'orderNumber',
                    align: 'center'
                },
                {
                    title: '配资人',
                    dataIndex: 'userName',
                    align: 'center'
                },
                {
                    title: '子账号',
                    dataIndex: 'subaccountNumber',
                    align: 'center'
                },
                {
                    title: '手机号',
                    dataIndex: 'userPhone',
                    align: 'center'
                },
                {
                    title: '保证金',
                    dataIndex: 'margin',
                    align: 'center'
                },
                {
                    title: '配资金额',
                    dataIndex: 'fundsAmount',
                    align: 'center'
                },
                {
                    title: '总操盘金额',
                    dataIndex: 'totalTradingAmount',
                    align: 'center'
                },
                {
                    title: '操盘期限',
                    dataIndex: 'tradersCycle',
                    align: 'center'
                },
                {
                    title: '申请时间',
                    dataIndex: 'addTime',
                    align: 'center',
                    customRender: (text, row, index) => {
                        return text ? moment(text).format('YYYY-MM-DD HH:mm:ss') : ''
                    }
                },
                {
                    title: '开始时间',
                    dataIndex: 'beginTime',
                    align: 'center',
                    customRender: (text, row, index) => {
                        return text ? moment(text).format('YYYY-MM-DD HH:mm:ss') : ''
                    }
                },
                {
                    title: '结束时间',
                    dataIndex: 'endTime',
                    align: 'center',
                    customRender: (text, row, index) => {
                        return text ? moment(text).format('YYYY-MM-DD HH:mm:ss') : ''
                    }
                },
                {
                    title: '配资状态',
                    dataIndex: 'status',
                    align: 'center',
                    scopedSlots: { customRender: 'status' }
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
                keyword: '',
                status: 0,
                pageNum: 1,
                pageSize: 10
            },
            datalist: [],
            addUserform: this.$form.createForm(this),
            addUserdialog: false,
            addUserDialogloading: false,
            accountList: [],
            Accountloading: false,
            currentDetails: {}
        }
    },
    created () {
        this.getlist()
    },
    filters: {
        statusFilter (status) {
            const statusMap = {
                0: '待审核',
                1: '操盘中',
                2: '未通过',
                3: '已到期',
                4: '已结束'
            }
            return statusMap[status]
        }
    },
    methods: {
        // 点击子账户调用交易账户管理列表 status  0 未用  1 已用
        getEnabledAccount () {
            this.Accountloading = true
            getTradingAccountList({ status: 0 }).then(res => {
                if (res.status == 0) {
                    this.accountList = res.data.data.list
                    this.Accountloading = false
                }
            })
        },
        CanceladdUserdialog () {
            this.addUserdialog = false
            const form = this.$refs.addUserform.form
            form.resetFields()
        },
        OkaddUserdialog () {
            const form = this.$refs.addUserform.form
            form.validateFields((errors, values) => {
                if (!errors) {
                    this.addUserDialogloading = true
                    values.id = this.currentDetails.id
                    values.tradersCycle = this.currentDetails.tradersCycle
                    fundsapplyauditApply(values).then(res => {
                        if (res.status == 0) {
                            this.addUserdialog = false
                            this.$message.success({ content: res.msg, duration: 2 })
                            form.resetFields()
                            this.getlist()
                        } else {
                            this.$message.error({ content: res.msg })
                        }
                        this.addUserDialogloading = false
                    })
                }
            })
        },
        getqueryParam () {
            this.queryParam = {
                keyword: '',
                status: 0,
                pageNum: 1,
                pageSize: 10
            }
        },
        getlist () {
            var that = this
            this.loading = true
            getApplyList(this.queryParam).then(res => {
                this.datalist = res.data.data.list
                this.pagination.total = res.data.data.total
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
