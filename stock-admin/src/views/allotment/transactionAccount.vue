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
                  placeholder="请输入子账户名称/证券机构名称" />
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

                  <a-button type="primary" icon="plus" style="margin-left: 8px" @click="getaddjiaoyi">
                    添加交易账户</a-button>

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
              <a-tag :color="record.status == 0 ? 'red' : 'green'">{{ record.status == 0 ? '未用' : '已用' }}
              </a-tag>
            </div>
          </template>
        </span>
        <template slot="action" slot-scope="text,record">
          <a
            slot="action"
            href="javascript:;"
            @click="geteditbroker(record)"
            v-if="record.status == 0">修改交易账户</a>
        </template>
      </a-table>
    </a-card>

    <a-modal
      :title="currentDetails ? '修改交易账户' : '添加交易账户'"
      :width="500"
      :visible="addUserdialog"
      :confirmLoading="addUserDialogloading"
      @ok="OkaddUserdialog"
      @cancel="CanceladdUserdialog">
      <a-form :form="addUserform" ref="addUserform">
        <a-form-item label="证券机构" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-select
            placeholder="请选择证券机构"
            v-decorator="['dealerInstitutionsId', { rules: [{ required: true, message: '请选择证券机构', }] }]"
            @change="getdealerchange">
            <a-select-option v-for="item in dealerList" :key="item.id" :value="item.id">
              {{ item.dealerInstitutionsName }}
            </a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="证券账户名" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="请先选择证券机构"
            v-decorator="['accountName', { rules: [{ required: true, message: '请先选择证券机构', }] }]"
            disabled />
        </a-form-item>
        <a-form-item label="子账户名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="请输入子账户名称"
            v-decorator="['subaccountNumber', { rules: [{ required: true, message: '请输入子账户名称', }] }]"
            disabled />
          <span>
            系统自动生成不允许修改
          </span>
        </a-form-item>
        <a-form-item label="备注" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-textarea
            placeholder="请输入备注"
            v-decorator="['remarks', { rules: [{ required: true, message: '请输入备注', }] }]" />
        </a-form-item>
        <a-form-item label="状态" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-select
            placeholder="请选择状态"
            v-decorator="['status', { rules: [{ required: true, message: '请选择状态', }] }]">
            <a-select-option :value="0">未用</a-select-option>
            <a-select-option :value="1">已用</a-select-option>
          </a-select>
        </a-form-item>
      </a-form>
    </a-modal>
  </page-header-wrapper>
</template>
<script>
import { getTradingAccountList, getSecuritiesEnabledList, saveTradingAccount, dealergetMaxNumber } from '@/api/allotment'
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
                    title: '证券账户名',
                    dataIndex: 'accountName',
                    align: 'center'
                },
                {
                    title: '子账户名称',
                    dataIndex: 'subaccountNumber',
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
            fields: ['dealerInstitutionsId', 'dealerInstitutionsName', 'accountName', 'subaccountNumber', 'status', 'remarks'],
            currentDetails: '',
            dealerList: []
        }
    },
    created () {
        this.getlist()
        this.getDealerlist()
    },
    methods: {
        getaddjiaoyi () {
            this.addUserdialog = true
            this.currentDetails = ''
            dealergetMaxNumber().then(res => {
                if (res.status == 0) {
                    this.addUserform.setFieldsValue({ subaccountNumber: res.data })
                }
            })
        },
        getdealerchange (value, option) {
            console.log(value, option)
            this.dealerInstitutionsId = option.key
            var index = this.dealerList.findIndex(item => {
                if (item.id == option.key) {
                    return true
                }
            })
            this.dealerInstitutionsName = this.dealerList[index].dealerInstitutionsName
            this.fields.forEach(v => this.addUserform.getFieldDecorator(v))
            this.addUserform.setFieldsValue(pick({ dealerInstitutionsName: this.dealerList[index].dealerInstitutionsName }, this.fields))
            this.addUserform.setFieldsValue(pick({ accountName: this.dealerList[index].accountName }, this.fields))
        },
        getDealerlist () {
            getSecuritiesEnabledList().then(res => {
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
                        values.dealerInstitutionsName = this.dealerInstitutionsName
                    }
                    saveTradingAccount(values).then(res => {
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
            getTradingAccountList(this.queryParam).then(res => {
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
