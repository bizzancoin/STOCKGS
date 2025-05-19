<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <a-card :bordered="false">
        <div class="table-page-search-wrapper">
          <a-form layout="inline">
            <a-row :gutter="48">
              <a-col :md="12" :lg="6" :sm="24">
                <a-form-item label="提现状态">
                  <a-select v-model="queryParam.state" placeholder="请选择提现状态">
                    <a-select-option :value="0">审核中</a-select-option>
                    <a-select-option :value="1">出金成功</a-select-option>
                    <a-select-option :value="2">出金失败</a-select-option>
                    <a-select-option :value="3">出金取消</a-select-option>
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
                <a-form-item label="用户Id">
                  <a-input v-model="queryParam.userId" style="width: 100%" placeholder="请输入用户Id" />
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
                <a-form-item label="出金时间">
                  <a-range-picker
                    show-time
                    style="width: 100%"
                    v-model="times"
                    @change="onChangeRangeDate"
                    format="YYYY-MM-DD HH:mm:ss">
                  </a-range-picker>
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
                    <a-button
                      type="primary"
                      icon="vertical-align-bottom"
                      style="margin-left: 8px"
                      @click="getexport">导出搜索数据
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
        :scroll="{ x: 2000 }">
        <span slot="withStatus" slot-scope="text,record">
          <template>
            <div>
              <a-tag
                :color="record.withStatus == 0 ? 'blue' : record.withStatus == 1 ? 'green' : record.withStatus == 2 ? 'red' : 'orange'">
                {{ record.withStatus == 0 ? '审核中' : record.withStatus == 1 ? '提现成功' :
                  record.withStatus == 2 ? '提现失败' : '提现取消'
                }}
              </a-tag>
            </div>
          </template>
        </span>

        <template slot="action" slot-scope="text,record">
          <a slot="action" href="javascript:;" @click="getEditorder(record.id)">修改提现记录</a>
        </template>
      </a-table>
    </a-card>
    <a-modal
      title="修改订单状态"
      :width="500"
      :visible="editOrderdialog"
      :confirmLoading="editOrderDialogloading"
      @ok="OkeditOrderdialog"
      @cancel="CanceleditOrderdialog">
      <a-form :form="editOrderform" ref="editOrderform">
        <a-form-item label="订单ID">
          <a-input
            v-decorator="['withId', { rules: [{ type: 'number', required: true, message: '请输入锁仓原因！', }] }]"
            disabled />
        </a-form-item>
        <a-form-item label="提现状态">
          <a-select
            placeholder="请选择提现状态"
            v-decorator="['state', { rules: [{ required: true, message: '请选择提现状态', }] }]">
            <a-select-option value="1">提现成功</a-select-option>
            <a-select-option value="2">提现失败</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="失败原因" v-if="editOrderform.getFieldValue('state') == 2">
          <a-input v-decorator="['authMsg', { rules: [{ required: true, message: '请输入提现失败原因！', }] }]" />
        </a-form-item>

      </a-form>
    </a-modal>
  </page-header-wrapper>
</template>
<script>
import { withdrawlist, withdrawupdateState, withdrawexport } from '@/api/capital'
import { nextagent } from '@/api/home'
import pick from 'lodash.pick'
import fileDownload from 'js-file-download'
import moment from 'moment'
export default {
    name: 'Withdrawallist',
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
                    title: '订单ID',
                    dataIndex: 'id',
                    align: 'center'
                },
                {
                    title: '代理ID',
                    dataIndex: 'agentId',
                    align: 'center'
                },
                {
                    title: '应转金额',
                    dataIndex: 'payChannel',
                    align: 'center',
                    customRender: (text, row, index) => {
                        return row.withAmt - row.withFee
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
                    title: '提现银行',
                    dataIndex: 'bankName',
                    align: 'center'
                },
                {
                    title: '提现支行',
                    dataIndex: 'bankAddress',
                    align: 'center',
                    width: 200
                },
                {
                    title: '银行号码',
                    dataIndex: 'bankNo',
                    align: 'center'
                },
                {
                    title: '原因',
                    dataIndex: 'withMsg',
                    align: 'center',
                    width: 200
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
                    width: 180,
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
                agentId: undefined,
                state: undefined,
                userId: '',
                realName: '',
                beginTime: '',
                endTime: ''
            },
            datalist: [],
            agentlist: [],
            agentloading: false,
            times: [],
            editOrderform: this.$form.createForm(this),
            editOrderdialog: false,
            editOrderDialogloading: false,
            fields: ['withId', 'state'],

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
        getexport () {
            withdrawexport(this.queryParam).then(res => {
                fileDownload(res, '提现列表.xls')
            })
        },
        // 点击修改提现状态
        getEditorder (val) {
            this.editOrderdialog = true
            this.fields.forEach(v => this.editOrderform.getFieldDecorator(v))
            this.editOrderform.setFieldsValue(pick({ withId: val }, this.fields))
        },
        // 开始更改提现状态
        OkeditOrderdialog () {
            const form = this.$refs.editOrderform.form
            form.validateFields((errors, values) => {
                if (!errors) {
                    this.editOrderDialogloading = true
                    withdrawupdateState(values).then(res => {
                        if (res.status == 0) {
                            this.$message.success({ content: res.msg, duration: 2 })
                            form.resetFields()
                            this.getlist()
                        } else {
                            this.$message.error({ content: res.msg })
                        }
                        this.editOrderdialog = false
                        this.editOrderDialogloading = false
                    }).catch(error => {
                        reject(error)
                    })
                }
            })
        },
        // 取消提现状态修改
        CanceleditOrderdialog () {
            this.editOrderdialog = false
            const form = this.$refs.editOrderform.form
            form.resetFields()
        },
        onChangeRangeDate (value, dateString) {
            this.queryParam.beginTime = dateString[0]
            this.queryParam.endTime = dateString[1]
        },
        getqueryParam () {
            this.queryParam = {
                pageNum: 1,
                pageSize: 10,
                agentId: undefined,
                state: undefined,
                userId: '',
                realName: '',
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
            withdrawlist(this.queryParam).then(res => {
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
