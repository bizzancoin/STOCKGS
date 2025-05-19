<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <div class="table-page-search-wrapper">
        <a-form layout="inline">
          <a-row :gutter="48">

            <a-col :md="12" :lg="6" :sm="24">
              <a-form-item label="显示状态">
                <a-select v-model="queryParam.zt" placeholder="请选择显示状态">
                  <a-select-option :value="0">隐藏</a-select-option>
                  <a-select-option :value="1">显示</a-select-option>
                </a-select>
              </a-form-item>
            </a-col>
            <a-col :md="12" :lg="6" :sm="24">
              <a-form-item label="新股代码">
                <a-input v-model="queryParam.code" style="width: 100%" placeholder="请输入新股代码" />
              </a-form-item>
            </a-col>
            <a-col :md="12" :lg="6" :sm="24">
              <a-form-item label="新股名称">
                <a-input v-model="queryParam.name" style="width: 100%" placeholder="请输入新股名称" />
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
                    @click="queryParam.pageNum = 1, pagination.current = page, getlist()">查询
                  </a-button>

                  <a-button
                    type="primary"
                    icon="plus"
                    style="margin-left: 8px"
                    @click="addUserdialog = true, currentdetail = ''">
                    添加新股</a-button>

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
        rowKey="newlistId">
        <span slot="name" slot-scope="text,record">
          <template>
            <div>
              <span style="margin-right:10px">{{ record.name }}</span>
              <a-tag color="green">{{ record.code }}
              </a-tag>
            </div>
          </template>
        </span>
        <span slot="zt" slot-scope="text,record">
          <template>
            <div>
              <a-tag :color="record.zt == 0 ? 'red' : record.zt == 1 ? 'green' : ''">
                {{ record.zt == 0 ? '隐藏' : '显示' }}
              </a-tag>
            </div>
          </template>
        </span>
        <span slot="stockType" slot-scope="text,record">
          <template>
            <div>
              <a-tag
                :color="(record.stockType == '沪' || record.stockType == '科') ? 'purple' : (record.stockType == '深' || record.stockType == '创') ? 'blue' : record.stockType == '北' ? 'orange' : ''">
                {{ record.stockType == '科' ? '沪' : record.stockType == '创' ? '深' : record.stockType }}
              </a-tag>
            </div>
          </template>
        </span>
        <span slot="leixing" slot-scope="text,record">
          <template>
            <div>
              <a-tag :color="getleixing(record.listDate) == '新股抢筹' ? 'orange' : 'green'">
                {{ getleixing(record.listDate) }}
              </a-tag>
            </div>
          </template>
        </span>

        <template slot="action" slot-scope="text,record">
          <a slot="action" href="javascript:;" @click="geteditStock(record)">{{ '修改新股' }}</a>
          <a-divider type="vertical" />
          <a slot="action" href="javascript:;" @click="getdeleteStock(record)">{{ '删除新股' }}</a>
        </template>
      </a-table>
    </a-card>

    <a-modal
      :title="currentdetail ? '修改新股' : '添加新股'"
      :width="700"
      :visible="addUserdialog"
      :confirmLoading="addUserDialogloading"
      @ok="OkaddUserdialog"
      @cancel="CanceladdUserdialog">
      <a-form :form="addUserform" ref="addUserform">
        <a-row :gutter="48">
          <a-col :md="12" :lg="12" :sm="12">
            <a-form-item label="新股名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input
                placeholder="请输入新股名称"
                v-decorator="['name', { rules: [{ required: true, message: '请输入新股名称', }] }]" />
            </a-form-item>
          </a-col>
          <a-col :md="12" :lg="12" :sm="12">
            <a-form-item label="新股代码" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input
                placeholder="请输入新股代码"
                v-decorator="['code', { rules: [{ required: true, message: '请输入新股代码', }] }]" />
            </a-form-item>
          </a-col>
        </a-row>
        <a-row :gutter="48">
          <a-col :md="12" :lg="12" :sm="12">
            <a-form-item label="新股价格" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input
                placeholder="请输入新股价格"
                v-decorator="['price', { rules: [{ required: true, message: '请输入新股价格', }] }]" />
            </a-form-item>
          </a-col>
          <a-col :md="12" :lg="12" :sm="12">
            <a-form-item label="最大购买数量" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input
                placeholder="请输入最大购买数量"
                v-decorator="['orderNumber', { rules: [{ required: true, message: '请输入最大购买数量', }] }]" />
            </a-form-item>
          </a-col>
        </a-row>
        <a-row :gutter="48">
          <a-col :md="12" :lg="12" :sm="12">
            <a-form-item label="股票类型" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-select
                placeholder="请选择股票类型"
                v-decorator="['stockType', { rules: [{ required: true, message: '请选择股票类型', }] }]">
                <a-select-option :value="'沪'">沪股</a-select-option>
                <a-select-option :value="'深'">深股</a-select-option>
                <a-select-option :value="'北'">京股</a-select-option>
                <a-select-option :value="'科'">科</a-select-option>
                <a-select-option :value="'创'">创</a-select-option>
              </a-select>
            </a-form-item>
          </a-col>
          <a-col :md="12" :lg="12" :sm="12">
            <a-form-item label="显示状态" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-select
                placeholder="请选择显示状态"
                v-decorator="['zt', { rules: [{ required: true, message: '请选择显示状态', }] }]">
                <a-select-option :value="0">隐藏</a-select-option>
                <a-select-option :value="1">显示</a-select-option>
              </a-select>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row :gutter="48">

          <a-col :md="12" :lg="12" :sm="12">
            <a-form-item label="申购时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-date-picker
                show-time
                style="width:100%"
                @change="getsubscribeTime"
                v-decorator="['subscribeTime', { rules: [{ required: true, message: '请填写申购时间', }] }]"
                format="YYYY-MM-DD HH:mm:ss">
              </a-date-picker>
            </a-form-item>
          </a-col>
          <a-col :md="12" :lg="12" :sm="12">
            <a-form-item label="认缴时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-date-picker
                show-time
                style="width:100%"
                @change="getsubscriptionTime"
                v-decorator="['subscriptionTime', { rules: [{ required: true, message: '请填写认缴时间', }] }]"
                format="YYYY-MM-DD HH:mm:ss">
              </a-date-picker>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row :gutter="48">
          <a-col :md="12" :lg="12" :sm="12">
            <a-form-item label="上市时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-date-picker
                show-time
                style="width:100%"
                @change="getlistDate"
                v-decorator="['listDate', { rules: [{ required: true, message: '请填写上市时间', }] }]"
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
import { subscribelist, subscribeadd, subscribeupdate, subscribedel } from '@/api/newshares'
import moment from 'moment'
import pick from 'lodash.pick'
export default {
    name: 'Shares',
    data () {
        return {
            columns: [
                {
                    title: '新股名称 / 新股代码',
                    dataIndex: 'name',
                    align: 'center',
                    scopedSlots: { customRender: 'name' }
                },
                {
                    title: '最大购买数量',
                    dataIndex: 'orderNumber',
                    align: 'center',
                    customRender: (text, row, index) => {
                        return text + '/万股'
                    }
                },

                {
                    title: '价格',
                    dataIndex: 'price',
                    align: 'center',
                    customRender: (text, row, index) => {
                        return text ? text.toFixed(2) : '0.00'
                    }
                },
                {
                    title: '是否显示',
                    dataIndex: 'zt',
                    align: 'center',
                    scopedSlots: { customRender: 'zt' }
                },
                {
                    title: '股票类型',
                    dataIndex: 'stockType',
                    align: 'center',
                    scopedSlots: { customRender: 'stockType' }
                },
                {
                    title: '类型',
                    dataIndex: 'leixing',
                    align: 'center',
                    scopedSlots: { customRender: 'leixing' }
                },
                {
                    title: '申购时间',
                    dataIndex: 'subscribeTime',
                    align: 'center',
                    customRender: (text, row, index) => {
                        return text ? moment(text).format('YYYY-MM-DD HH:mm:ss') : ''
                    }
                },
                {
                    title: '认缴时间',
                    dataIndex: 'subscriptionTime',
                    align: 'center',
                    customRender: (text, row, index) => {
                        return text ? moment(text).format('YYYY-MM-DD HH:mm:ss') : '- -'
                    }
                },
                {
                    title: '上市时间',
                    dataIndex: 'listDate',
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
                code: '',
                name: '',
                zt: undefined,
                type: undefined
            },
            datalist: [],
            labelCol: {
                xs: { span: 8 },
                sm: { span: 8 },
                md: { span: 8 }
            },
            wrapperCol: {
                xs: { span: 14 },
                sm: { span: 14 },
                md: { span: 14 }
            },
            addUserform: this.$form.createForm(this),
            addUserdialog: false,
            addUserDialogloading: false,

            fields: ['name', 'code', 'price', 'orderNumber', 'zt', 'subscribeTime', 'subscriptionTime', 'stockType', 'code', 'listDate'],
            currentdetail: '',
            subscribeTime: '',
            subscriptionTime: '',
            listDate: ''
        }
    },
    created () {
        this.getlist()
    },
    methods: {
        getleixing (val) {
            if (moment(val).format('YYYY-MM-DD') == moment().format('YYYY-MM-DD')) {
                return '新股抢筹'
            } else {
                return '新股申购'
            }
        },
        getdeleteStock (val) {
            var that = this
            this.$confirm({
                title: '提示',
                content: '确认删除该新股吗？此操作不可恢复！',
                onOk () {
                    var data = {
                        id: val.newlistId
                    }
                    subscribedel(data).then(res => {
                        if (res.status == 0) {
                            that.$message.success({ content: res.msg, duration: 2 })
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
        getsubscribeTime (date, dateString) {
            this.subscribeTime = dateString
        },
        getsubscriptionTime (date, dateString) {
            this.subscriptionTime = dateString
        },
        getlistDate (date, dateString) {
            this.listDate = dateString
        },
        geteditStock (val) {
            this.currentdetail = val
            this.addUserdialog = true
            this.fields.forEach(v => this.addUserform.getFieldDecorator(v))
            this.addUserform.setFieldsValue(pick(val, this.fields))
            // this.addUserform.setFieldsValue({ stockType: val.stockType.toFixed(2) })
            this.addUserform.setFieldsValue({ subscriptionTime: val.subscriptionTime ? moment(val.subscriptionTime).format('YYYY-MM-DD HH:mm:ss') : '' })
            this.addUserform.setFieldsValue({ subscribeTime: val.subscribeTime ? moment(val.subscribeTime).format('YYYY-MM-DD HH:mm:ss') : '' })
            this.addUserform.setFieldsValue({ listDate: val.listDate ? moment(val.listDate).format('YYYY-MM-DD HH:mm:ss') : '' })
            this.subscribeTime = moment(val.subscribeTime).format('YYYY-MM-DD HH:mm:ss')
            this.subscriptionTime = moment(val.subscriptionTime).format('YYYY-MM-DD HH:mm:ss')
            this.listDate = moment(val.listDate).format('YYYY-MM-DD HH:mm:ss')
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
                    if (this.currentdetail != '') {
                        this.addUserDialogloading = true
                        values.newlistId = this.currentdetail.newlistId
                        values.subscriptionTime = this.subscriptionTime
                        values.subscribeTime = this.subscribeTime
                        values.listDate = this.listDate
                        subscribeupdate(values).then(res => {
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
                    } else {
                        this.addUserDialogloading = true
                        values.subscriptionTime = this.subscriptionTime
                        values.subscribeTime = this.subscribeTime
                        values.listDate = this.listDate
                        subscribeadd(values).then(res => {
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
                code: '',
                name: '',
                zt: undefined,
                type: undefined
            }
        },
        getinit () {
            this.getqueryParam()
            this.pagination.current = 1
            this.getlist()
        },
        getlist () {
            this.loading = true
            subscribelist(this.queryParam).then(res => {
                this.datalist = res.data.list
                this.pagination.total = res.data.total
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
