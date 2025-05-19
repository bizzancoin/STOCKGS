<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <div class="table-page-search-wrapper">
        <a-form layout="inline">
          <a-row :gutter="48">
            <a-col :md="12" :lg="6" :sm="24">
              <a-form-item label="关键词">
                <a-input v-model="queryParam.keyword" style="width: 100%" placeholder="请输入关键词" />
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
                    添加记录</a-button>
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
        <span slot="status" slot-scope="text,record">
          <template>
            <div>
              <a-tag
                style="margin-right:10px"
                :color="record.status == 1 ? 'pink' : record.status == 2 ? 'red'
                  : record.status == 3 ? 'green' : record.status == 4 ? 'blue' : record.status == 5 ? 'purple' : 'orange'">
                {{ record.status == 1 ?
                  '待中签' : record.status == 2 ? '未中签'
                    : record.status == 3 ? '已中签' : record.status == 4 ? '已缴纳' : record.status == 5 ? '已转持仓'
                      : ''
                }}</a-tag>
            </div>
          </template>
        </span>
        <!-- <span slot="type" slot-scope="text,record">
                    <template>
                        <div>
                            <a-tag :color="record.type == 1 ? 'purple' : record.type == 2 ? 'blue' : ''">
                                {{ record.type == 1 ? '新股申购' : '线下配售' }}
                            </a-tag>
                        </div>
                    </template>
                </span> -->

        <template slot="action" slot-scope="text,record">
          <a slot="action" href="javascript:;" @click="geteditStock(record)">{{ '修改记录' }}</a>
          <a-divider type="vertical" />
          <a slot="action" href="javascript:;" @click="getdeleteStock(record)">{{ '删除记录' }}</a>
          <a-divider type="vertical" />
        </template>
      </a-table>
    </a-card>

    <a-modal
      :title="currentdetail ? '修改记录' : '添加记录'"
      :width="700"
      :visible="addUserdialog"
      :confirmLoading="addUserDialogloading"
      @ok="OkaddUserdialog"
      @cancel="CanceladdUserdialog">
      <a-form :form="addUserform" ref="addUserform">
        <a-row :gutter="48" v-if="!currentdetail">
          <a-col :md="12" :lg="12" :sm="12">
            <a-form-item label="用户手机号" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input
                placeholder="请输入用户手机号"
                v-decorator="['userPhone', { rules: [{ required: true, message: '请输入用户手机号', }] }]" />
            </a-form-item>
          </a-col>
          <a-col :md="12" :lg="12" :sm="12">
            <a-form-item label="新股代码" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input
                placeholder="请输入新股代码"
                v-decorator="['newCode', { rules: [{ required: true, message: '请输入新股代码', }] }]" />
            </a-form-item>
          </a-col>
        </a-row>
        <a-row :gutter="48">
          <a-col :md="12" :lg="12" :sm="12">
            <a-form-item label="买入数量" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input
                placeholder="请输入买入数量"
                v-decorator="['num', { rules: [{ required: true, message: '请输入买入数量', }] }]" />
            </a-form-item>
          </a-col>
          <a-col :md="12" :lg="12" :sm="12" v-if="currentdetail">
            <a-form-item label="状态" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-select
                placeholder="请选择状态"
                v-decorator="['status', { rules: [{ required: true, message: '请选择状态', }] }]">
                <a-select-option :value="1">待中签</a-select-option>
                <a-select-option :value="2">未中签</a-select-option>
                <a-select-option :value="3">已中签</a-select-option>
                <!-- <a-select-option :value="4">已缴纳</a-select-option>
                                <a-select-option :value="5">转持仓</a-select-option> -->
              </a-select>
            </a-form-item>
          </a-col>
        </a-row>
      </a-form>
    </a-modal>
  </page-header-wrapper>
</template>
<script>
import { getStockSubscribeQcListByAdmin, addStockSubscribeQcByAdmin, updateStockSubscribeQcByAdmin, delStockSubscribe } from '@/api/newshares'
import pick from 'lodash.pick'
export default {
    name: 'Shares',
    data () {
        return {
            columns: [
                {
                    title: '用户名称（ID）',
                    dataIndex: 'realName',
                    align: 'center',
                    scopedSlots: { customRender: 'realName' }
                },
                {
                    title: '用户手机号',
                    dataIndex: 'phone',
                    align: 'center'
                },
                {
                    title: '新股名称',
                    dataIndex: 'newName',
                    align: 'center'
                },
                {
                    title: '新股代码',
                    dataIndex: 'newCode',
                    align: 'center'
                },
                {
                    title: '买入价格',
                    dataIndex: 'buyPrice',
                    align: 'center'
                },
                {
                    title: '保证金',
                    dataIndex: 'bond',
                    align: 'center'
                },
                {
                    title: '买入数量',
                    dataIndex: 'applyNums',
                    align: 'center'
                },
                {
                    title: '中签数量',
                    dataIndex: 'applyNumber',
                    align: 'center'
                },
                {
                    title: '申购时间',
                    dataIndex: 'addTime',
                    align: 'center'
                },
                {
                    title: '状态',
                    dataIndex: 'status',
                    align: 'center',
                    scopedSlots: { customRender: 'status' }
                },
                // {
                //     title: '新股模式',
                //     dataIndex: 'type',
                //     align: "center",
                //     scopedSlots: { customRender: 'type' },
                // },
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
                keyword: ''
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
            fields: ['userPhone', 'newCode', 'num', 'status'],
            currentdetail: ''
        }
    },
    created () {
        this.getlist()
    },
    methods: {
        getdeleteStock (val) {
            var that = this
            this.$confirm({
                title: '提示',
                content: '确认删除该新股吗？此操作不可恢复！',
                onOk () {
                    var data = {
                        id: val.id
                    }
                    delStockSubscribe(data).then(res => {
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
        geteditStock (val) {
            this.currentdetail = val
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
                    if (this.currentdetail != '') {
                        values.id = this.currentdetail.id
                        updateStockSubscribeQcByAdmin(values).then(res => {
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
                        addStockSubscribeQcByAdmin(values).then(res => {
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
                keyword: ''
            }
        },
        getinit () {
            this.getqueryParam()
            this.pagination.current = 1
            this.getlist()
        },
        getlist () {
            this.loading = true
            getStockSubscribeQcListByAdmin(this.queryParam).then(res => {
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
