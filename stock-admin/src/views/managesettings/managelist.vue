<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <div class="table-page-search-wrapper">
        <a-form layout="inline">
          <a-row :gutter="48">
            <a-col :md="12" :lg="6" :sm="24">
              <a-form-item label="管理员姓名">
                <a-input v-model="queryParam.adminName" style="width: 100%" placeholder="请输入管理员姓名" />
              </a-form-item>
            </a-col>
            <a-col :md="12" :lg="6" :sm="24">
              <a-form-item label="手机号">
                <a-input v-model="queryParam.adminPhone" style="width: 100%" placeholder="请输入手机号" />
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
                    @click="queryParam.pageNum = 1, getlist()">查询
                  </a-button>
                  <a-button
                    type="primary"
                    icon="plus"
                    style="margin-left: 8px"
                    @click="addUserdialog = true">
                    添加管理</a-button>
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
        <span slot="isLock" slot-scope="text,record">
          <template>
            <div>
              <a-tag :color="record.isLock == 0 ? 'green' : record.isLock == 1 ? 'red' : ''">
                {{ record.isLock == 0 ? '正常' : '锁定' }}
              </a-tag>
            </div>
          </template>
        </span>

        <template slot="action" slot-scope="text,record">
          <a slot="action" href="javascript:;" @click="getisLock(record.id, record.isLock)">{{ record.isLock
            == 0 ? '锁定用户' :
              '解锁用户'
          }}</a>
          <a-divider type="vertical" />
          <a slot="action" href="javascript:;" @click="geteditStock(record)">修改密码</a>
        </template>
      </a-table>
    </a-card>

    <a-modal
      title="添加管理员"
      :width="500"
      :visible="addUserdialog"
      :confirmLoading="addUserDialogloading"
      @ok="OkaddUserdialog"
      @cancel="CanceladdUserdialog">
      <a-form :form="addUserform" ref="addUserform">
        <a-form-item label="管理员名" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="请输入管理员名"
            v-decorator="['adminName', { rules: [{ required: true, message: '请输入管理员名', }] }]" />
        </a-form-item>
        <a-form-item label="管理员手机号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="请输入管理员手机号"
            v-decorator="['adminPhone', { rules: [{ required: true, message: '请输入管理员手机号', }] }]" />
        </a-form-item>
        <a-form-item label="管理员密码" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="请输入管理员密码"
            v-decorator="['adminPwd', { rules: [{ required: true, message: '请输入管理员密码', }] }]" />
        </a-form-item>
        <a-form-item label="管理员状态" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-select
            placeholder="请选择管理员状态"
            v-decorator="['isLock', { rules: [{ required: true, message: '请选择管理员状态', }] }]">
            <a-select-option value="0">正常</a-select-option>
            <a-select-option value="1">锁定</a-select-option>
          </a-select>
        </a-form-item>
      </a-form>
    </a-modal>

    <a-modal
      title="修改密码"
      :width="500"
      :visible="editStockdialog"
      :confirmLoading="editStockdialogloading"
      @ok="OkeditStockdialog"
      @cancel="CanceleditStockdialog">
      <a-form :form="editStockform" ref="editStockform">
        <a-form-item label="用户ID" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="请输入用户ID"
            disabled
            v-decorator="['id', { rules: [{ required: true, message: '请输入用户ID', }] }]" />
        </a-form-item>
        <a-form-item label="密码" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="请输入密码"
            v-decorator="['adminPwd', { rules: [{ required: true, message: '请输入密码', }] }]" />
        </a-form-item>
      </a-form>
    </a-modal>
  </page-header-wrapper>
</template>
<script>
import { adminlist, adminupdateLock, adminadd, adminupdate } from '@/api/managesettings'
import moment from 'moment'
export default {
    name: 'Shares',
    data () {
        return {
            columns: [
                {
                    title: 'id',
                    dataIndex: 'id',
                    align: 'center'
                },
                {
                    title: '管理员姓名',
                    dataIndex: 'adminName',
                    align: 'center'
                },
                {
                    title: '管理员手机号',
                    dataIndex: 'adminPhone',
                    align: 'center'
                },
                {
                    title: '管理员状态',
                    dataIndex: 'isLock',
                    align: 'center',
                    scopedSlots: { customRender: 'isLock' }
                },

                {
                    title: '注册时间',
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
                adminName: '',
                adminPhone: ''
            },
            datalist: [],
            labelCol: {
                xs: { span: 24 },
                sm: { span: 7 }
            },
            wrapperCol: {
                xs: { span: 24 },
                sm: { span: 13 }
            },
            addUserform: this.$form.createForm(this),
            addUserdialog: false,
            addUserDialogloading: false,

            editStockdialog: false,
            editStockdialogloading: false,
            editStockform: this.$form.createForm(this),
            fields: ['id', 'adminPwd']
        }
    },
    created () {
        this.getlist()
    },
    methods: {
        geteditStock (val) {
            this.editStockdialog = true
            this.fields.forEach(v => this.editStockform.getFieldDecorator(v))
            this.editStockform.setFieldsValue(({ id: val.id }))
        },
        CanceleditStockdialog () {
            this.editStockdialog = false
            const form = this.$refs.editStockform.form
            form.resetFields()
        },
        OkeditStockdialog () {
            const form = this.$refs.editStockform.form
            form.validateFields((errors, values) => {
                if (!errors) {
                    this.editStockdialogloading = true
                    values.adminPwd = this.editStockform.getFieldValue('adminPwd')
                    adminupdate(values).then(res => {
                        if (res.status == 0) {
                            this.editStockdialog = false
                            this.$message.success({ content: res.msg, duration: 2 })
                            form.resetFields()
                            this.getlist()
                        } else {
                            this.$message.error({ content: res.msg })
                        }
                        this.editStockdialogloading = false
                    })
                }
            })
        },
        // 新增用户取消弹窗
        CanceladdUserdialog () {
            this.addUserdialog = false
            const form = this.$refs.addUserform.form
            form.resetFields()
        },
        // 新增用户确定
        OkaddUserdialog () {
            const form = this.$refs.addUserform.form
            form.validateFields((errors, values) => {
                if (!errors) {
                    this.addUserDialogloading = true
                    adminadd(values).then(res => {
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
                pageNum: 1,
                pageSize: 10,
                adminName: '',
                adminPhone: ''
            }
        },
        getlist () {
            this.loading = true
            adminlist(this.queryParam).then(res => {
                this.datalist = res.data.list
                this.pagination.total = res.data.total
                this.loading = false
            })
        },
        // 股票的锁定解锁
        getisLock (idval, isLockval) {
            adminupdateLock({ adminId: idval }).then(res => {
                if (res.status === 0) {
                    this.getlist()
                    if (isLockval == 0) {
                        this.$message.success({ content: '锁定成功', duration: 2 })
                    } else {
                        this.$message.success({ content: '解锁成功', duration: 2 })
                    }
                } else {
                    this.$message.error({ content: res.msg, duration: 2 })
                }
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
