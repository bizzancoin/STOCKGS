<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <div class="table-page-search-wrapper">
        <a-form layout="inline">
          <a-row :gutter="48">
            <a-col :md="6" :sm="24">
              <a-form-item label="关键词查询">
                <a-input
                  v-model="queryParam.keyword"
                  style="width: 100%"
                  placeholder="证券公司/证券营业部/证券账户" />
              </a-form-item>
            </a-col>
            <a-col :md="12" :sm="24">
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
                    @click="addUserdialog = true; currentDetails = ''">
                    添加证券信息</a-button>

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
              <a-tag :color="record.status == 0 ? 'red' : 'green'">{{ record.status == 0 ? '停用' : '启用' }}
              </a-tag>
            </div>
          </template>
        </span>
        <template slot="action" slot-scope="text,record">
          <a slot="action" href="javascript:;" @click="geteditbroker(record)">修改证券信息</a>
        </template>
      </a-table>
    </a-card>

    <a-modal
      :title="currentDetails ? '修改证券信息' : '添加证券信息'"
      :width="500"
      :visible="addUserdialog"
      :confirmLoading="addUserDialogloading"
      @ok="OkaddUserdialog"
      @cancel="CanceladdUserdialog">
      <a-form :form="addUserform" ref="addUserform">
        <a-form-item label="证券机构" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-select
            placeholder="请选择证券机构"
            v-decorator="['dealerInstitutionsName', { rules: [{ required: true, message: '请选择证券机构', }] }]"
            @change="getdealerchange">
            <a-select-option v-for="item in dealerList" :key="item.id" :value="item.dealerName">
              {{ item.dealerName }}
            </a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="证券营业部" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="请输入证券营业部"
            v-decorator="['salesDepartment', { rules: [{ required: true, message: '请输入证券营业部', }] }]" />
        </a-form-item>
        <a-form-item label="证券账户" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="请输入证券账户"
            v-decorator="['accountName', { rules: [{ required: true, message: '请输入证券账户', }] }]" />
        </a-form-item>
        <a-form-item label="交易通账户" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="请输入交易通账户"
            v-decorator="['transactAccount', { rules: [{ required: true, message: '请输入交易通账户', }] }]" />
        </a-form-item>
        <a-form-item label="交易通密码" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="请输入交易通密码"
            v-decorator="['transactPassword', { rules: [{ required: true, message: '请输入交易通密码', }] }]" />
        </a-form-item>
        <a-form-item label="通讯密码" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="请输入通讯密码"
            v-decorator="['communicationPassword', { rules: [{ required: true, message: '请输入通讯密码', }] }]" />
        </a-form-item>
        <a-form-item label="佣金比例" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="请输入佣金比例"
            v-decorator="['commissionRatio', { rules: [{ required: true, message: '佣金比例,0.005代表千分之五', }] }]" />
        </a-form-item>
        <a-form-item label="最低佣金" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="请输入最低佣金"
            v-decorator="['minimumCommissions', { rules: [{ required: true, message: '请输入最低佣金', }] }]" />
        </a-form-item>
        <a-form-item label="状态" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-select
            placeholder="请选择状态"
            v-decorator="['status', { rules: [{ required: true, message: '请选择状态', }] }]">
            <a-select-option :value="0">停用</a-select-option>
            <a-select-option :value="1">启用</a-select-option>
          </a-select>
        </a-form-item>
      </a-form>
    </a-modal>
  </page-header-wrapper>
</template>
<script>
import { getSecuritiesInfoList, getDealerInstitutionsList, saveSecuritiesInfo } from '@/api/allotment'
import moment from 'moment'
import pick from 'lodash.pick'
export default {
    name: 'Setting',
    data () {
        return {
            columns: [
                {
                    title: '证券机构名称',
                    dataIndex: 'dealerInstitutionsName',
                    align: 'center'
                },
                {
                    title: '证券营业部',
                    dataIndex: 'salesDepartment',
                    align: 'center'
                },
                {
                    title: '证券账户',
                    dataIndex: 'accountName',
                    align: 'center'
                },
                {
                    title: '交易通账户',
                    dataIndex: 'transactAccount',
                    align: 'center'
                },
                {
                    title: '状态',
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
                pageSize: 10,
                keyword: ''
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
            fields: ['dealerInstitutionsName', 'dealerInstitutionsId', 'salesDepartment', 'accountName', 'transactAccount', 'transactPassword', 'communicationPassword', 'commissionRatio', 'minimumCommissions', 'status'],
            currentDetails: '',
            dealerList: [],
            dealerInstitutionsId: ''
        }
    },
    created () {
        this.getlist()
        this.getDealerlist()
    },
    methods: {
        getdealerchange (value, option) {
            this.dealerInstitutionsId = option.key
        },
        getDealerlist () {
            getDealerInstitutionsList({ status: 1 }).then(res => {
                if (res.status == 0) {
                    this.dealerList = res.data.data.list
                }
            })
        },
        geteditbroker (val) {
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
                    this.dealerInstitutionsId ? values.dealerInstitutionsId = this.dealerInstitutionsId : values.dealerInstitutionsId
                    saveSecuritiesInfo(values).then(res => {
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
        getqueryParam () {
            this.queryParam = {
                pageNum: 1,
                pageSize: 10,
                keyword: ''
            }
        },
        getlist () {
            var that = this
            this.loading = true
            getSecuritiesInfoList(this.queryParam).then(res => {
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
