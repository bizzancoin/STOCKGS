<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <div class="table-page-search-wrapper">
        <a-form layout="inline">
          <a-row :gutter="48">
            <a-col :md="12" :lg="6" :sm="24">
              <a-form-item label="关键词">
                <a-input v-model="queryParam.keywords" style="width: 100%" placeholder="请输入关键词" />
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
                    @click="queryParam.pageNum = 1, pagination.current = 1, getlist()">查询
                  </a-button>
                  <a-button
                    type="primary"
                    icon="plus"
                    style="margin-left: 8px"
                    @click="addUserdialog = true, currentdetail = ''">
                    添加大宗交易</a-button>
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
        <span slot="realName" slot-scope="text,record">
          <template>
            <div>
              <span style="margin-right:10px">{{ record.realName }}({{ record.userId }})</span>
            </div>
          </template>
        </span>
        <span slot="stockType" slot-scope="text,record">
          <template>
            <div>
              <a-tag
                :color="record.stockType == 'sh' ? 'purple' : record.stockType == 'sz' ? 'blue' : 'orange'">
                {{ record.stockType == 'sz' ? '深股' : record.stockType == 'sh' ? '沪股' : '京股' }}
              </a-tag>
            </div>
          </template>
        </span>

        <template slot="action" slot-scope="text,record">
          <a slot="action" href="javascript:;" @click="geteditStock(record)">{{ '修改大宗交易' }}</a>
          <a-divider type="vertical" />
          <a slot="action" href="javascript:;" @click="getdeleteStock(record)">{{ '删除大宗交易' }}</a>
          <a-divider type="vertical" />
        </template>
      </a-table>
    </a-card>

    <a-modal
      :title="currentdetail ? '修改大宗交易' : '添加大宗交易'"
      :width="700"
      :visible="addUserdialog"
      :confirmLoading="addUserDialogloading"
      @ok="OkaddUserdialog"
      @cancel="CanceladdUserdialog">
      <a-form :form="addUserform" ref="addUserform">
        <a-row :gutter="48">
          <a-col :md="12" :lg="12" :sm="12" v-if="!currentdetail">
            <a-form-item label="股票代码" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input
                placeholder="请输入股票代码"
                v-decorator="['stockCode', { rules: [{ required: true, message: '请输入股票代码', }] }]" />
            </a-form-item>
          </a-col>
          <a-col :md="12" :lg="12" :sm="12" >
            <a-form-item label="最小买入数量" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input
                placeholder="请输入最小买入数量"
                v-decorator="['stockNum', { rules: [{ required: true, message: '请输入最小买入数量', }] }]" />
            </a-form-item>
          </a-col>
        </a-row>
        <a-row :gutter="48">
          <a-col :md="12" :lg="12" :sm="12">
            <a-form-item label="交易密钥" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input
                placeholder="请输入交易密钥"
                v-decorator="['password', { rules: [{ required: true, message: '请输入交易密钥', }] }]" />
            </a-form-item>
          </a-col>
          <a-col :md="12" :lg="12" :sm="12">
            <a-form-item label="折扣" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input
                placeholder="请输入折扣（例：0.8）"
                v-decorator="['discount', { rules: [{ required: true, message: '请输入折扣', }] }]" />
            </a-form-item>
          </a-col>
        </a-row>
        <a-row :gutter="48">
          <a-col :md="12" :lg="12" :sm="12">
            <a-form-item label="开始时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-date-picker
                show-time
                style="width:100%"
                @change="getstartTime"
                v-decorator="['startTime', { rules: [{ required: true, message: '请填写开始时间', }] }]"
                format="YYYY-MM-DD HH:mm:ss">
              </a-date-picker>
            </a-form-item>
          </a-col>
          <a-col :md="12" :lg="12" :sm="12">
            <a-form-item label="结束时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-date-picker
                show-time
                style="width:100%"
                @change="getendTime"
                v-decorator="['endTime', { rules: [{ required: true, message: '请填写结束时间', }] }]"
                format="YYYY-MM-DD HH:mm:ss">
              </a-date-picker>
            </a-form-item>
          </a-col>
        </a-row>
      </a-form>
    </a-modal>
  </page-header-wrapper>
</template>
<script>
import { getDzListByAdmin, addByAdmin, updateByAdmin, deleteByAdmin } from '@/api/newshares'
import pick from 'lodash.pick'
import moment from 'moment'
export default {
    name: 'Shares',
    data () {
        return {
            columns: [
                {
                    title: '新股名称',
                    dataIndex: 'stockName',
                    align: 'center'
                },
                {
                    title: '新股代码',
                    dataIndex: 'stockCode',
                    align: 'center'
                },
                {
                    title: '最小购买数量',
                    dataIndex: 'stockNum',
                    align: 'center'
                },
                {
                    title: '折扣',
                    dataIndex: 'discount',
                    align: 'center'
                },
                {
                    title: '股票类型',
                    dataIndex: 'stockType',
                    align: 'center',
                    scopedSlots: { customRender: 'stockType' }
                },
                {
                    title: '开始时间',
                    dataIndex: 'startTime',
                    align: 'center',
                    customRender: (text, row, index) => {
                        return text ? moment(text).format('YYYY-MM-DD HH:mm:ss') : '- -'
                    }
                },
                {
                    title: '结束时间',
                    dataIndex: 'endTime',
                    align: 'center',
                    customRender: (text, row, index) => {
                        return text ? moment(text).format('YYYY-MM-DD HH:mm:ss') : '- -'
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
                current: 1,
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
                keywords: ''
            },
            datalist: [],
            labelCol: {
                xs: { span: 9 },
                sm: { span: 9 },
                md: { span: 9 }
            },
            wrapperCol: {
                xs: { span: 14 },
                sm: { span: 14 },
                md: { span: 14 }
            },
            addUserform: this.$form.createForm(this),
            addUserdialog: false,
            addUserDialogloading: false,
            fields: ['stockCode', 'stockNum', 'password', 'startTime', 'endTime', 'discount'],
            currentdetail: '',
            startTime: '',
            endTime: ''
        }
    },
    created () {
        this.getlist()
    },
    methods: {
        getstartTime (date, dateString) {
            this.startTime = dateString
        },
        getendTime (date, dateString) {
            this.endTime = dateString
        },
        getdeleteStock (val) {
            var that = this
            this.$confirm({
                title: '提示',
                content: '确认删除该大宗股票吗？此操作不可恢复！',
                onOk () {
                    var data = {
                        id: val.id
                    }
                    deleteByAdmin(data).then(res => {
                        if (res.status == 0) {
                            that.$message.success({ content: '删除成功', duration: 2 })
                            that.getinit()
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
        geteditStock (val) {
            this.currentdetail = val
            this.addUserdialog = true
            this.fields.forEach(v => this.addUserform.getFieldDecorator(v))
            this.addUserform.setFieldsValue(pick(val, this.fields))
            this.addUserform.setFieldsValue({ startTime: val.startTime ? moment(val.startTime).format('YYYY-MM-DD HH:mm:ss') : '' })
            this.addUserform.setFieldsValue({ endTime: val.endTime ? moment(val.endTime).format('YYYY-MM-DD HH:mm:ss') : '' })
            this.endTime = moment(val.endTime).format('YYYY-MM-DD HH:mm:ss')
            this.startTime = moment(val.startTime).format('YYYY-MM-DD HH:mm:ss')
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
                    values.startTime = this.startTime
                    values.endTime = this.endTime
                    if (this.currentdetail != '') {
                        values.id = this.currentdetail.id
                        updateByAdmin(values).then(res => {
                            if (res.status == 0) {
                                this.addUserdialog = false
                                this.$message.success({ content: '修改成功', duration: 2 })
                                form.resetFields()
                                this.getlist()
                            } else {
                                this.$message.error({ content: res.msg })
                            }
                            this.addUserDialogloading = false
                        })
                    } else {
                        addByAdmin(values).then(res => {
                            if (res.status == 0) {
                                this.addUserdialog = false
                                this.$message.success({ content: res.msg, duration: 2 })
                                form.resetFields()
                                this.getinit()
                            } else {
                                this.$message.error({ content: res.msg })
                            }
                            this.addUserDialogloading = false
                        })
                    }
                }
            })
        },
        getqueryParam () {
            this.queryParam = {
                pageNum: 1,
                pageSize: 10,
                keywords: ''
            }
        },
        getinit () {
            this.getqueryParam()
            this.pagination.current = 1
            this.getlist()
        },
        getlist () {
            this.loading = true
            getDzListByAdmin(this.queryParam).then(res => {
                this.datalist = res.data
                // this.pagination.total = res.data.total
                this.loading = false
            })
        },
        onPageChange (page, pageSize) {
            this.queryParam.pageNum = page
            this.pagination.current = page
            this.getlist()
        },
        onSizeChange (current, pageSize) {
            this.queryParam.pageNum = current
            this.pagination.current = page
            this.queryParam.pageSize = pageSize
            this.getlist()
        }
    }
}
</script>
