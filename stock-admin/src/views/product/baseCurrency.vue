<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <div class="table-page-search-wrapper">
        <a-form layout="inline">
          <a-row :gutter="48">
            <a-col :md="12" :lg="6" :sm="24">
              <a-form-item label="基础货币名称">
                <a-input v-model="queryParam.coinName" style="width: 100%" placeholder="请输入基础货币名称" />
              </a-form-item>
            </a-col>
            <a-col :md="12" :lg="6" :sm="24">
              <a-form-item label="基础货币代码">
                <a-input v-model="queryParam.coinCode" style="width: 100%" placeholder="请输入基础货币代码" />
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
                    添加基础货币</a-button>

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

        <span slot="dynamicRate" slot-scope="text,record">
          <template>
            <div>
              <a-tag :color="record.dynamicRate == 1 ? 'blue' : 'green'">
                {{ record.dynamicRate == 1 ? '默认' : '动态' }}
              </a-tag>
            </div>
          </template>
        </span>

        <span slot="isUse" slot-scope="text,record">
          <template>
            <div>
              <a-tag :color="record.isUse == 1 ? 'green' : 'red'">
                {{ record.isUse == 1 ? '可用' : '不可用' }}
              </a-tag>
            </div>
          </template>
        </span>

        <template slot="action" slot-scope="text,record">
          <a slot="action" href="javascript:;" @click="geteditbaseCurrency(record)">修改基础货币</a>
        </template>
      </a-table>
    </a-card>

    <a-modal
      :title="currentDetails ? '修改基础货币' : '添加基础货币'"
      :width="500"
      :visible="addUserdialog"
      :confirmLoading="addUserDialogloading"
      @ok="OkaddUserdialog"
      @cancel="CanceladdUserdialog">
      <a-form :form="addUserform" ref="addUserform">
        <a-form-item label="基币名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="请输入基币名称"
            v-decorator="['coinName', { rules: [{ required: true, message: '请输入基币名称', }] }]" />
        </a-form-item>
        <a-form-item label="基币代码" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="请输入基币代码"
            v-decorator="['coinCode', { rules: [{ required: true, message: '请输入基币代码', }] }]" />
        </a-form-item>
        <a-form-item label="默认汇率" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="请输入默认汇率"
            v-decorator="['defaultRate', { rules: [{ required: true, message: '请输入默认汇率', }] }]" />
        </a-form-item>
        <a-form-item label="启用动态行情" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-select
            placeholder="请选择启用动态行情"
            v-decorator="['dynamicRate', { rules: [{ required: true, message: '请选择启用动态行情', }] }]">
            <a-select-option :value="0">启用动态汇率值</a-select-option>
            <a-select-option :value="1">使用默认汇率值</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="是否可用" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-select
            placeholder="请选择是否可用"
            v-decorator="['isUse', { rules: [{ required: true, message: '请选择是否可用', }] }]">
            <a-select-option :value="0">不可用</a-select-option>
            <a-select-option :value="1">可用</a-select-option>
          </a-select>
        </a-form-item>
      </a-form>
    </a-modal>
  </page-header-wrapper>
</template>
<script>
import { coinlist, coinadd, coinupdate } from '@/api/product'
import moment from 'moment'
import pick from 'lodash.pick'
export default {
    name: 'Basecurrency',
    data () {
        return {
            columns: [
                {
                    title: '基础货币名称 / 代码',
                    dataIndex: 'name',
                    align: 'center',
                    width: 200,
                    customRender: (text, row, index) => {
                        return `${row.coinName}（${row.coinCode}）`
                    }
                },
                {
                    title: '基础货币Gid',
                    dataIndex: 'coinGid',
                    align: 'center'
                },
                {
                    title: '当前汇率',
                    dataIndex: 'nowPrice',
                    align: 'center'
                },
                {
                    title: '默认汇率',
                    dataIndex: 'defaultRate',
                    align: 'center'
                },
                {
                    title: '汇率使用',
                    dataIndex: 'dynamicRate',
                    align: 'center',
                    scopedSlots: { customRender: 'dynamicRate' }
                },
                {
                    title: '是否可用',
                    dataIndex: 'isUse',
                    align: 'center',
                    scopedSlots: { customRender: 'isUse' }
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
                coinCode: '',
                coinName: ''
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
            fields: ['coinName', 'coinCode', 'defaultRate', 'dynamicRate', 'isUse'],
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
                        coinupdate(values).then(res => {
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
                        values.id = 0
                        values.coinGid = 'fx_s' + this.addUserform.getFieldValue('coinCode') + 'cny',
                            coinadd(values).then(res => {
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
                }
            })
        },
        getqueryParam () {
            this.queryParam = {
                pageNum: 1,
                pageSize: 10,
                coinCode: '',
                coinName: ''
            }
        },
        getlist () {
            this.loading = true
            coinlist(this.queryParam).then(res => {
                this.datalist = res.data.list
                this.pagination.total = res.data.total
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
