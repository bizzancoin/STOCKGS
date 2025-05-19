<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <a-card :bordered="false">
        <div class="table-page-search-wrapper">
          <a-form layout="inline">
            <a-row :gutter="48">
              <a-col :md="12" :lg="6" :sm="24">
                <a-form-item label="充值状态">
                  <a-select v-model="queryParam.state" placeholder="请选择充值状态">
                    <a-select-option :value="0">审核中</a-select-option>
                    <a-select-option :value="1">入金成功</a-select-option>
                    <a-select-option :value="2">入金失败</a-select-option>
                    <a-select-option :value="3">入金取消</a-select-option>
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
                <a-form-item label="支付时间">
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
                      icon="plus"
                      style="margin-left: 8px"
                      @click="addOrderdialog = true">创建充值订单
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
        rowKey="id">
        <span slot="payChannel" slot-scope="text,record">
          <template>
            <div>
              <a-tag
                :color="record.payChannel == 0 ? 'blue' : record.payChannel == 1 ? 'orange' : 'cyan'">
                {{ record.payChannel == 0 ? '支付宝' : record.payChannel == 1 ? '对公转账' : '现金转账'
                }}
              </a-tag>
            </div>
          </template>
        </span>
        <span slot="orderStatus" slot-scope="text,record">
          <template>
            <div>
              <a-tag
                :color="record.orderStatus == 0 ? 'blue' : record.orderStatus == 1 ? 'green' : record.orderStatus == 2 ? 'red' : 'orange'">
                {{ record.orderStatus == 0 ? '审核中' : record.orderStatus == 1 ? '充值成功' :
                  record.orderStatus == 2 ? '充值失败' : '订单取消'
                }}
              </a-tag>
            </div>
          </template>
        </span>

        <template slot="action" slot-scope="text,record">
          <a
            slot="action"
            href="javascript:;"
            @click="getEditorder(record.id)"
            v-if="record.orderStatus == 0">修改状态</a>
          <a-divider type="vertical" v-if="record.orderStatus == 0" />
          <a slot="action" href="javascript:;" @click="getDelorder(record.id)">删除订单</a>
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
            v-decorator="['chargeId', { rules: [{ type: 'number', required: true, message: '请输入锁仓原因！', }] }]"
            disabled />
        </a-form-item>
        <a-form-item label="充值状态">
          <a-select
            placeholder="请选择充值状态"
            v-decorator="['state', { rules: [{ required: true, message: '请选择充值状态', }] }]">
            <a-select-option value="1">充值成功</a-select-option>
            <a-select-option value="2">充值失败</a-select-option>
          </a-select>
        </a-form-item>
      </a-form>
    </a-modal>

    <a-modal
      title="创建充值订单"
      :width="500"
      :visible="addOrderdialog"
      :confirmLoading="addOrderDialogloading"
      @ok="OkaddOrderdialog"
      @cancel="CanceladdOrderdialog">
      <a-form :form="addOrderform" ref="addOrderform">
        <a-form-item label="用户ID">
          <a-input
            placeholder="请输入用户ID"
            v-decorator="['userId', { rules: [{ required: true, message: '请输入用户ID！', }] }]" />
        </a-form-item>
        <a-form-item label="充值金额">
          <a-input
            placeholder="请输入充值金额"
            v-decorator="['amt', { rules: [{ required: true, message: '请输入充值金额！', }] }]" />
        </a-form-item>
        <a-form-item label="充值状态">
          <a-select
            placeholder="请选择充值状态"
            v-decorator="['state', { rules: [{ required: true, message: '请选择充值状态', }] }]">
            <a-select-option value="0">充值中</a-select-option>
            <a-select-option value="1">充值成功</a-select-option>
            <a-select-option value="2">充值失败</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="充值渠道">
          <a-select
            placeholder="请选择充值渠道"
            v-decorator="['payChannel', { rules: [{ required: true, message: '请选择充值渠道', }] }]">
            <a-select-option value="0">支付宝</a-select-option>
            <a-select-option value="1">对公转账</a-select-option>
            <a-select-option value="2">现金转账</a-select-option>
          </a-select>
        </a-form-item>
      </a-form>
    </a-modal>
  </page-header-wrapper>
</template>
<script>
import { rechargelist, rechargedel, rechargeupdateState, rechargecreateOrder, rechargeexport } from '@/api/capital'
import { nextagent } from '@/api/home'
import pick from 'lodash.pick'
import fileDownload from 'js-file-download'
import moment from 'moment'
export default {
    name: 'Rechargelist',
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
                    title: '订单号',
                    dataIndex: 'orderSn',
                    align: 'center'
                },
                {
                    title: '充值渠道',
                    dataIndex: 'payChannel',
                    align: 'center',
                    scopedSlots: { customRender: 'payChannel' }
                },
                {
                    title: '充值金额',
                    dataIndex: 'payAmt',
                    align: 'center'
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
                    title: '支付时间',
                    dataIndex: 'payTime',
                    align: 'center',
                    width: 180,
                    customRender: (text, row, index) => {
                        return text ? moment(text).format('YYYY-MM-DD HH:mm:ss') : ''
                    }
                },
                {
                    title: '状态',
                    dataIndex: 'orderStatus',
                    align: 'center',
                    scopedSlots: { customRender: 'orderStatus' }
                },
                {
                    title: '操作',
                    key: 'action',
                    align: 'center',
                    width: 180,
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
            fields: ['chargeId', 'state'],

            addOrderform: this.$form.createForm(this),
            addOrderdialog: false,
            addOrderDialogloading: false,
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
            rechargeexport(this.queryParam).then(res => {
                fileDownload(res, '充值列表.xls')
            })
        },
        // 新增充值订单弹窗
        CanceladdOrderdialog () {
            this.addOrderdialog = false
            const form = this.$refs.addOrderform.form
            form.resetFields()
        },
        // 新增充值订单
        OkaddOrderdialog () {
            const form = this.$refs.addOrderform.form
            form.validateFields((errors, values) => {
                if (!errors) {
                    this.addOrderDialogloading = true
                    rechargecreateOrder(values).then(res => {
                        if (res.status == 0) {
                            this.addOrderdialog = false
                            this.$message.success({ content: res.msg, duration: 2 })
                            form.resetFields()
                            this.getlist()
                        } else {
                            this.$message.error({ content: res.msg })
                        }
                        this.addOrderDialogloading = false
                    }).catch(error => {
                        reject(error)
                    })
                }
            })
        },
        // 点击修改充值状态
        getEditorder (val) {
            this.editOrderdialog = true
            this.fields.forEach(v => this.editOrderform.getFieldDecorator(v))
            this.editOrderform.setFieldsValue(pick({ chargeId: val }, this.fields))
        },
        // 开始更改充值状态
        OkeditOrderdialog () {
            const form = this.$refs.editOrderform.form
            form.validateFields((errors, values) => {
                if (!errors) {
                    this.editOrderDialogloading = true
                    rechargeupdateState(values).then(res => {
                        if (res.status == 0) {
                            this.editOrderdialog = false
                            this.$message.success({ content: res.msg, duration: 2 })
                            form.resetFields()
                            this.getlist()
                        } else {
                            this.$message.error({ content: res.msg })
                        }
                        this.editOrderDialogloading = false
                    }).catch(error => {
                        reject(error)
                    })
                }
            })
        },
        // 取消充值状态修改
        CanceleditOrderdialog () {
            this.editOrderdialog = false
            const form = this.$refs.editOrderform.form
            form.resetFields()
        },
        // 删除充值订单
        getDelorder (val) {
            var that = this
            this.$confirm({
                title: '提示',
                content: '确认删除充值订单吗？此操作不可恢复',
                onOk () {
                    var data = {
                        cId: val
                    }
                    rechargedel(data).then(res => {
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
            rechargelist(this.queryParam).then(res => {
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
