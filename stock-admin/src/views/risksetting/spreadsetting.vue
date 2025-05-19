<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <div class="table-page-search-wrapper">
        <a-form layout="inline">
          <a-row :gutter="48">
            <a-col :md="12" :lg="6" :sm="24">
              <a-form-item label="点差类型">
                <a-select v-model="queryParam.typeName" placeholder="请选择点差类型">
                  <a-select-option :value="'涨跌幅'">涨跌幅</a-select-option>
                  <a-select-option :value="'成交额'">成交额</a-select-option>
                  <a-select-option :value="'开头收取'">开头收取</a-select-option>
                  <a-select-option :value="'低于收取'">低于收取</a-select-option>
                </a-select>
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
                    @click="addUserdialog = true; currentDetails = ''">
                    添加点差</a-button>
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

        <span slot="zhi" slot-scope="text,record">
          <template>
            <div>
              &le; X &lt;
            </div>
          </template>
        </span>

        <span slot="spreadRate" slot-scope="text,record">
          <template>
            <div>
              <span :class="record.spreadRate < 0 ? 'greens' : 'reds'">
                {{ record.spreadRate }}
              </span>
            </div>
          </template>
        </span>

        <template slot="action" slot-scope="text,record">
          <a slot="action" href="javascript:;" @click="geteditbaseCurrency(record)">修改点差</a>
        </template>
      </a-table>
    </a-card>

    <a-modal
      :title="currentDetails ? '修改点差' : '添加点差'"
      :width="500"
      :visible="addUserdialog"
      :confirmLoading="addUserDialogloading"
      @ok="OkaddUserdialog"
      @cancel="CanceladdUserdialog">
      <a-form :form="addUserform" ref="addUserform">
        <a-form-item label="点差类型" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-select
            placeholder="请选择点差类型"
            v-decorator="['typeName', { rules: [{ required: true, message: '请选择点差类型', }] }]">
            <a-select-option :value="'涨跌幅'">涨跌幅</a-select-option>
            <a-select-option :value="'成交额'">成交额</a-select-option>
            <a-select-option :value="'开头收取'">开头收取</a-select-option>
            <a-select-option :value="'低于收取'">低于收取</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="单位" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="请输入单位"
            v-decorator="['unit', { rules: [{ required: true, message: '请输入单位', }] }]" />
        </a-form-item>
        <a-form-item label="开始区间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="请输入开始区间"
            v-decorator="['startInterval', { rules: [{ required: true, message: '请输入开始区间', }] }]" />
        </a-form-item>
        <a-form-item label="结束区间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="请输入结束区间"
            v-decorator="['endInterval', { rules: [{ required: true, message: '请输入结束区间', }] }]" />
        </a-form-item>
        <a-form-item label="点差费率" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="请输入点差费率"
            v-decorator="['spreadRate', { rules: [{ required: true, message: '请输入点差费率', }] }]" />
        </a-form-item>
      </a-form>
    </a-modal>
  </page-header-wrapper>
</template>
<script>
import { admingetSiteSpreadList, adminaddSiteSpread, adminupdateSiteSpread } from '@/api/risksetting'
import moment from 'moment'
import pick from 'lodash.pick'
export default {
    name: 'Basecurrency',
    data () {
        return {
            columns: [
                {
                    title: '类型名称',
                    dataIndex: 'typeName',
                    align: 'center'
                },
                {
                    title: '单位',
                    dataIndex: 'unit',
                    align: 'center'
                },
                {
                    title: '开始区间',
                    dataIndex: 'startInterval',
                    align: 'center'
                },
                {
                    title: '值',
                    dataIndex: 'zhi',
                    align: 'center',
                    scopedSlots: { customRender: 'zhi' }
                },
                {
                    title: '结束区间',
                    dataIndex: 'endInterval',
                    align: 'center'
                },
                {
                    title: '点差费率',
                    dataIndex: 'spreadRate',
                    align: 'center',
                    scopedSlots: { customRender: 'spreadRate' }
                },
                {
                    title: '添加时间',
                    dataIndex: 'addTime',
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
                    fixed: 'right',
                    width: 150,
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
                typeName: undefined
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
            fields: ['typeName', 'unit', 'startInterval', 'endInterval', 'spreadRate'],
            currentDetails: ''
        }
    },
    created () {
        this.getlist()
    },
    methods: {
        geteditbaseCurrency (val) {
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
                        adminupdateSiteSpread(values).then(res => {
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
                        values.id = 0
                        adminaddSiteSpread(values).then(res => {
                            if (res.status == 0) {
                                this.addUserdialog = false
                                this.$message.success({ content: '添加成功', duration: 2 })
                                form.resetFields()
                                this.getlist()
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
                typeName: undefined
            }
        },
        getlist () {
            this.loading = true
            admingetSiteSpreadList(this.queryParam).then(res => {
                this.datalist = res.data.data.list
                this.pagination.total = res.data.data.total
                this.loading = false
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
