<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <div class="table-page-search-wrapper">
        <a-form layout="inline">
          <a-row :gutter="48">
            <a-col :md="8" :xxl="6" :sm="24">
              <a-form-item label="关键词查询">
                <a-input v-model="queryParam.keyword" style="width: 100%" placeholder="请输入用户名/手机号" />
              </a-form-item>
            </a-col>
            <a-col :md="12" :xxl="7" :sm="24">
              <a-form-item label="类型选择">
                <div style="display:flex;">
                  <a-select
                    v-model="queryParam.appendType"
                    style="width: 140px"
                    placeholder="类型"
                    :default-value="{ key: '1' }">
                    <a-select-option :value="1">扩大配资</a-select-option>
                    <a-select-option :value="2">追加保证金</a-select-option>
                    <a-select-option :value="3">续期</a-select-option>
                    <a-select-option :value="4">终止操盘</a-select-option>
                  </a-select>
                  <a-select
                    v-model="queryParam.status"
                    style="width: 140px;margin-left: 10px;"
                    placeholder="类型"
                    :default-value="{ key: '0' }">
                    <a-select-option :value="0">申请列表</a-select-option>
                    <a-select-option :value="1">记录列表</a-select-option>
                  </a-select>
                </div>
              </a-form-item>
            </a-col>
            <template>
              <a-col :md="24" :xxl="6" :sm="24">
                <a-form-item>
                  <span class="table-page-search-submitButtons" style="white-space:wrap">
                    <a-button
                      type="primary"
                      icon="search"
                      @click="queryParam.pageNum = 1, getlist()">查询
                    </a-button>
                    <a-button @click="getqueryParam" style="margin-left: 8px" icon="redo">
                      重置</a-button>

                  </span>
                </a-form-item>
              </a-col>
            </template>

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
        rowKey="id"
        :scroll="{ x: 2400 }">
        <span slot="fundsType" slot-scope="text,record">
          <template>
            <div>
              <a-tag :color="record.fundsType == 0 ? 'blue' : record.fundsType == 1 ? 'green' : 'red'">{{
                record.fundsType | statusFilter
              }}
              </a-tag>
            </div>
          </template>
        </span>
        <span slot="status" slot-scope="text,record">
          <template>
            <div>
              <a-tag :color="record.status == 0 ? 'blue' : record.status == 1 ? 'green' : 'red'">{{
                record.status == 0 ? '审核中' : record.status == 1 ? '已通过' : '未通过'
              }}
              </a-tag>
            </div>
          </template>
        </span>
        <template slot="action" slot-scope="text,record">
          <a
            slot="action"
            href="javascript:;"
            @click="onUser(record)"
            v-if="record.status == 0 && record.appendType == 1">扩大资金修改</a>
          <a
            slot="action"
            href="javascript:;"
            @click="onUser(record)"
            v-if="record.status == 0 && record.appendType == 2">追加保证金修改</a>
          <a
            slot="action"
            href="javascript:;"
            @click="onUser(record)"
            v-if="record.status == 0 && record.appendType == 3">续期修改</a>
          <a
            slot="action"
            href="javascript:;"
            @click="onUser(record)"
            v-if="record.status == 0 && record.appendType == 4">终止操盘修改</a>
        </template>
      </a-table>
    </a-card>
    <a-modal
      :title="auditTitle"
      :width="1000"
      :visible="addUserdialog"
      :confirmLoading="addUserDialogloading"
      @ok="OkaddUserdialog"
      @cancel="CanceladdUserdialog">
      <a-descriptions bordered :column="{ xxl: 3, xl: 3, lg: 3, md: 3, sm: 2, xs: 1 }">
        <a-descriptions-item label="用户应交总费用">
          ￥{{ modeldetail.payAmount }}
        </a-descriptions-item>
        <a-descriptions-item label="审核前配资金额">
          ￥{{ modeldetail.fundsAmount }}
        </a-descriptions-item>
        <a-descriptions-item label="审核后配资金额">
          ￥{{ modeldetail.fundsAmount }}
        </a-descriptions-item>
        <a-descriptions-item label="审核前操盘总金额">
          ￥{{ modeldetail.totalTradingAmount }}
        </a-descriptions-item>
        <a-descriptions-item label="审核后操盘总金额">
          ￥{{ modeldetail.totalTradingAmount }}
        </a-descriptions-item>
      </a-descriptions>
      <a-form :form="addUserform" ref="addUserform">
        <a-form-item label="审核状态">
          <a-select
            placeholder="请选择审核状态"
            v-decorator="['status', { rules: [{ required: true, message: '请选择审核状态', }] }]">
            <a-select-option value="1">审核通过</a-select-option>
            <a-select-option value="2">未通过</a-select-option>
          </a-select>
        </a-form-item>
      </a-form>
    </a-modal>
  </page-header-wrapper>
</template>
<script>
import { getAppendList, applygetAppendDetail, applysaveAppendApply } from '@/api/allotment'
import moment from 'moment'
export default {
    name: 'Setting',
    data () {
        return {
            columns: [
                {
                    title: '配资人',
                    dataIndex: 'userName',
                    align: 'center',
                    width: 150,
                    fixed: 'left'
                },
                {
                    title: '手机号',
                    dataIndex: 'userPhone',
                    align: 'center',
                    width: 150,
                    fixed: 'left'
                },
                {
                    title: '申请时长（天）',
                    dataIndex: 'appendCycle',
                    align: 'center',
                    customRender: (text, row, index) => {
                        return this.queryParam.appendType == 3 ? text : '--'
                    }
                },
                {
                    title: '申请服务费',
                    dataIndex: 'appendServiceFee',
                    align: 'center',
                    customRender: (text, row, index) => {
                        return (this.queryParam.appendType != 2) ? text : '--'
                    }
                },
                {
                    title: '原保证金',
                    dataIndex: 'margin',
                    align: 'center'
                },
                {
                    title: '追加保证金',
                    dataIndex: 'appendMargin',
                    align: 'center',
                    customRender: (text, row, index) => {
                        return this.queryParam.appendType == 2 ? text : '--'
                    }
                },
                {
                    title: '原操盘金额',
                    dataIndex: 'totalTradingAmount',
                    align: 'center'
                },
                {
                    title: '配资倍数',
                    dataIndex: 'lever',
                    align: 'center'
                },

                {
                    title: '配资金额',
                    dataIndex: 'fundsAmount',
                    align: 'center'
                },
                {
                    title: '预警线',
                    dataIndex: 'lineWarning',
                    align: 'center'
                },
                {
                    title: '止损线',
                    dataIndex: 'lineUnwind',
                    align: 'center'
                },
                {
                    title: '使用时长',
                    dataIndex: 'tradersCycle',
                    align: 'center'
                },
                {
                    title: '配资类型',
                    dataIndex: 'fundsType',
                    align: 'center',
                    scopedSlots: { customRender: 'fundsType' }
                },
                {
                    title: '状态',
                    dataIndex: 'status',
                    align: 'center',
                    scopedSlots: { customRender: 'status' }
                },
                {
                    title: '申请时间',
                    dataIndex: 'addTime',
                    align: 'center',
                    width: 200,
                    customRender: (text, row, index) => {
                        return text ? moment(text).format('YYYY-MM-DD HH:mm:ss') : ''
                    }
                },
                {
                    title: '终止时间',
                    dataIndex: 'endTime',
                    align: 'center',
                    width: 200,
                    customRender: (text, row, index) => {
                        return text ? moment(text).format('YYYY-MM-DD HH:mm:ss') : ''
                    }
                },
                {
                    title: '操作',
                    key: 'action',
                    align: 'center',
                    width: 150,
                    fixed: 'right',
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
                keyword: '',
                status: 0,
                appendType: 1
            },
            datalist: [],
            currentDetails: '',
            auditTitle: '',
            addUserform: this.$form.createForm(this),
            addUserdialog: false,
            addUserDialogloading: false,
            modeldetail: ''
        }
    },
    created () {
        this.getlist()
    },
    watch: {
        currentDetails (val) {
            if (this.currentDetails.appendType == 2) {
                this.auditTitle = '追加保证金审核'
            } else if (this.currentDetails.appendType == 3) {
                this.auditTitle = '续期审核'
            } else if (this.currentDetails.appendType == 4) {
                this.auditTitle = '终止操盘审核'
            } else {
                this.auditTitle = '扩大配资审核'
            }
        }
    },
    filters: {
        statusFilter (status) {
            return status == 2 ? '按周配资' : status == 3 ? '按月配资' : '按天配资'
        }
    },
    methods: {
        CanceladdUserdialog () {
            this.addUserdialog = false
            const form = this.$refs.addUserform.form
            form.resetFields()
            this.currentDetails = ''
            this.modeldetail = ''
        },
        OkaddUserdialog () {
            const form = this.$refs.addUserform.form
            form.validateFields((errors, values) => {
                if (!errors) {
                    values.id = this.currentDetails.id
                    values.applyId = this.currentDetails.applyId
                    applysaveAppendApply(values).then(res => {
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
        onUser (val) {
            this.currentDetails = val
            this.applydetail()
        },
        applydetail () {
            var data = {
                id: this.currentDetails.id
            }
            applygetAppendDetail(data).then(res => {
                if (res.status == 0) {
                    this.modeldetail = res.data
                    this.addUserdialog = true
                }
            })
        },
        getqueryParam () {
            this.queryParam = {
                pageNum: 1,
                pageSize: 10,
                keyword: '',
                status: 0,
                appendType: 1
            }
        },
        getlist () {
            var that = this
            this.loading = true
            getAppendList(this.queryParam).then(res => {
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
