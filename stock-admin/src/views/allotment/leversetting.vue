<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <div class="table-operator">
        <a-button type="primary" icon="plus" @click="addUserdialog = true; currentDetails = ''">添加杠杆</a-button>
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
        <span slot="cycle_type" slot-scope="text,record">
          <template>
            <div>
              <a-tag
                :color="record.cycle_type == 2 ? 'green' : record.cycle_type == 3 ? 'orange' : 'blue'">
                {{ record.cycle_type | statusFilter }}
              </a-tag>
            </div>
          </template>
        </span>
        <span slot="status" slot-scope="text,record">
          <template>
            <div>
              <a-tag :color="record.status == 0 ? 'red' : 'green'">{{ record.status == 0 ? '停用' : '启用' }}
              </a-tag>
            </div>
          </template>
        </span>
        <template slot="action" slot-scope="text,record">
          <a slot="action" href="javascript:;" @click="geteditganggan(record)">修改杠杆</a>
        </template>
      </a-table>
    </a-card>

    <a-modal
      :title="currentDetails ? '修改杠杆' : '添加杠杆'"
      :width="500"
      :visible="addUserdialog"
      :confirmLoading="addUserDialogloading"
      @ok="OkaddUserdialog"
      @cancel="CanceladdUserdialog">
      <a-form :form="addUserform" ref="addUserform">
        <a-form-item label="配置周期类型" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-select
            placeholder="请选择配置周期类型"
            v-decorator="['cycleType', { rules: [{ required: true, message: '请选择配置周期类型', }] }]">
            <a-select-option :value="1">按天配置</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="杠杆" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-select
            placeholder="请选择杠杆"
            v-decorator="['lever', { rules: [{ required: true, message: '请选择杠杆', }] }]">
            <a-select-option :value="1">1倍</a-select-option>
            <a-select-option :value="2">2倍</a-select-option>
            <a-select-option :value="3">3倍</a-select-option>
            <a-select-option :value="4">4倍</a-select-option>
            <a-select-option :value="5">5倍</a-select-option>
            <a-select-option :value="6">6倍</a-select-option>
            <a-select-option :value="7">7倍</a-select-option>
            <a-select-option :value="8">8倍</a-select-option>
            <a-select-option :value="9">9倍</a-select-option>
            <a-select-option :value="10">10倍</a-select-option>
          </a-select>
        </a-form-item>

        <a-form-item label="管理费率" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="请输入管理费率"
            v-decorator="['manageRate', { rules: [{ required: true, message: '请输入管理费率', }] }]" />
          <span>管理费率%,如：0.25</span>
        </a-form-item>
        <a-form-item label="单股持仓比例" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="请输入单股持仓比例"
            v-decorator="['singleHoldingRatio', { rules: [{ required: true, message: '请输入单股持仓比例', }] }]" />
          <span>单股持仓比例,如：0.85</span>
        </a-form-item>
        <a-form-item label="状态" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-select
            placeholder="请选择配置周期类型"
            v-decorator="['status', { rules: [{ required: true, message: '请选择配置周期类型', }] }]">
            <a-select-option :value="0">停用</a-select-option>
            <a-select-option :value="1">启用</a-select-option>
          </a-select>
        </a-form-item>
      </a-form>
    </a-modal>
  </page-header-wrapper>
</template>
<script>
import { getFundsLeverList, fundssaveFundsLever } from '@/api/allotment'
import pick from 'lodash.pick'
import moment from 'moment'
export default {
    name: 'Leversetting',
    data () {
        return {
            columns: [
                {
                    title: '杠杆（倍数）',
                    dataIndex: 'lever',
                    align: 'center'
                },
                {
                    title: '管理费率（%）',
                    dataIndex: 'manageRate',
                    align: 'center'
                },
                {
                    title: '单股持仓比例（%）',
                    dataIndex: 'singleHoldingRatio',
                    align: 'center'
                },
                {
                    title: '周期类型',
                    dataIndex: 'cycle_type',
                    align: 'center',
                    scopedSlots: { customRender: 'cycle_type' }
                },
                {
                    title: '配资状态',
                    dataIndex: 'status',
                    align: 'center',
                    scopedSlots: { customRender: 'status' }
                },
                {
                    title: '添加时间',
                    dataIndex: 'addTime',
                    align: 'center',
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
                pageSize: 10
            },
            datalist: [],
            addUserdialog: false,
            addUserDialogloading: false,
            addUserform: this.$form.createForm(this),
            labelCol: {
                xs: { span: 24 },
                sm: { span: 7 }
            },
            wrapperCol: {
                xs: { span: 24 },
                sm: { span: 13 }
            },
            fields: ['cycleType', 'lever', 'manageRate', 'singleHoldingRatio', 'status'],
            currentDetails: ''
        }
    },
    created () {
        this.getlist()
    },
    filters: {
        statusFilter (status) {
            return status == 2 ? '按周配资' : status == 3 ? '按月配资' : '按天配资'
        }
    },
    methods: {
        geteditganggan (val) {
            this.currentDetails = val
            this.addUserdialog = true
            this.fields.forEach(v => this.addUserform.getFieldDecorator(v))
            this.addUserform.setFieldsValue(pick(val, this.fields))
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
                    if (this.currentDetails != '') {
                        values.id = this.currentDetails.id
                    } else {
                        values.id = 0
                    }
                    fundssaveFundsLever(values).then(res => {
                        if (res.status == 0) {
                            this.addUserdialog = false
                            this.$message.success({ content: res.data.msg, duration: 2 })
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
        getlist () {
            var that = this
            this.loading = true
            getFundsLeverList(this.queryParam).then(res => {
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
