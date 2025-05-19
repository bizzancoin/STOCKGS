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
                  placeholder="请填写券商ID/券商名称/客户端版本号" />
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
                    添加券商</a-button>

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
          <a slot="action" href="javascript:;" @click="geteditbroker(record)">修改券商机构</a>
        </template>
      </a-table>
    </a-card>

    <a-modal
      :title="currentDetails ? '修改券商机构' : '添加券商机构'"
      :width="500"
      :visible="addUserdialog"
      :confirmLoading="addUserDialogloading"
      @ok="OkaddUserdialog"
      @cancel="CanceladdUserdialog">
      <a-form :form="addUserform" ref="addUserform">
        <a-form-item label="券商ID" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="请输入券商ID"
            v-decorator="['dealerNumber', { rules: [{ required: true, message: '请输入券商ID', }] }]" />
          <span>券商ID,如:27代表银河证券</span>
        </a-form-item>
        <a-form-item label="券商名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="请输入券商名称"
            v-decorator="['dealerName', { rules: [{ required: true, message: '请输入券商名称', }] }]" />
          <span>券商名称,如:银河证券</span>
        </a-form-item>
        <a-form-item label="客户端版本号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="请输入客户端版本号"
            v-decorator="['clientVersionNumber', { rules: [{ required: true, message: '请输入客户端版本号', }] }]" />
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
            <a-select-option :value="0">停用</a-select-option>
            <a-select-option :value="1">启用</a-select-option>
          </a-select>
        </a-form-item>
      </a-form>
    </a-modal>
  </page-header-wrapper>
</template>
<script>
import { getDealerInstitutionsList, saveDealerInstitutions } from '@/api/allotment'
import moment from 'moment'
import pick from 'lodash.pick'
export default {
    name: 'Setting',
    data () {
        return {
            columns: [
                {
                    title: '券商ID',
                    dataIndex: 'dealerNumber',
                    align: 'center'
                },
                {
                    title: '券商名称',
                    dataIndex: 'dealerName',
                    align: 'center'
                },
                {
                    title: '客户端版本号',
                    dataIndex: 'clientVersionNumber',
                    align: 'center'
                },
                {
                    title: '备注',
                    dataIndex: 'remarks',
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
            fields: ['dealerNumber', 'dealerName', 'clientVersionNumber', 'remarks', 'status'],
            currentDetails: ''
        }
    },
    created () {
        this.getlist()
    },
    methods: {
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
                    saveDealerInstitutions(values).then(res => {
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
            getDealerInstitutionsList(this.queryParam).then(res => {
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
