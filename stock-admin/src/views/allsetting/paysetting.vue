<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <div class="table-page-search-wrapper">
        <a-form layout="inline">
          <a-row :gutter="48">
            <a-col :md="12" :lg="6" :sm="24">
              <a-form-item label="通道类型">
                <a-input v-model="queryParam.channelType" style="width: 100%" placeholder="请输入通道类型" />
              </a-form-item>
            </a-col>
            <a-col :md="12" :lg="6" :sm="24">
              <a-form-item>
                <span class="table-page-search-submitButtons">
                  <a-button icon="redo" @click="getqueryParam">重置</a-button>
                  <a-button
                    type="primary"
                    icon="search"
                    style="margin-left: 8px"
                    @click="queryParam.pageNum = 1, getlist()">查询</a-button>
                  <a-button
                    type="primary"
                    icon="plus"
                    style="margin-left: 8px"
                    @click="addUserdialog = true; currentDetails = ''">
                    添加支付渠道</a-button>
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

        <span slot="channelImg" slot-scope="text">
          <template>
            <img :src="text" alt="" style="width:120px;height: 50px;object-fit: cover;">
          </template>
        </span>
        <span slot="isShow" slot-scope="text">
          <template>
            <div>
              <a-tag :color="text == 1 ? 'red' : 'green'">
                {{ text == 1 ? '隐藏' : '显示' }}
              </a-tag>
            </div>
          </template>
        </span>

        <template slot="action" slot-scope="text,record">
          <a slot="action" href="javascript:;" @click="geteditbaseCurrency(record)">修改通道</a>
          <a-divider type="vertical" />
          <a slot="action" href="javascript:;" @click="deletebaseCurrency(record.id)">删除通道</a>
        </template>
      </a-table>
    </a-card>

    <a-modal
      :title="currentDetails ? '修改支付渠道' : '添加支付渠道'"
      :width="800"
      :visible="addUserdialog"
      :confirmLoading="addUserDialogloading"
      @ok="OkaddUserdialog"
      @cancel="CanceladdUserdialog">
      <a-form :form="addUserform" ref="addUserform">
        <a-row :gutter="48">
          <a-col :md="12" :lg="12" :sm="12">
            <a-form-item label="通道名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input
                placeholder="请输入通道名称"
                v-decorator="['channelType', { rules: [{ required: true, message: '请输入通道名称', }] }]" />
            </a-form-item>
          </a-col>
          <a-col :md="12" :lg="12" :sm="12">
            <a-form-item label="通道类型" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-select
                placeholder="请选择通道类型"
                v-decorator="['ctype', { rules: [{ required: true, message: '请选择通道类型', }] }]">
                <a-select-option :value="0">支付宝</a-select-option>
                <a-select-option :value="1">公户转账</a-select-option>
                <a-select-option :value="2">其他</a-select-option>
              </a-select>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row :gutter="48">
          <a-col :md="12" :lg="12" :sm="12">
            <a-form-item label="最小充值" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input
                placeholder="请输入最小充值"
                v-decorator="['channelMinLimit', { rules: [{ required: true, message: '请输入最小充值金额', }] }]" />
            </a-form-item>
          </a-col>
          <a-col :md="12" :lg="12" :sm="12">
            <a-form-item label="最大充值" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input
                placeholder="请输入最大充值"
                v-decorator="['channelMaxLimit', { rules: [{ required: true, message: '请输入最大充值金额', }] }]" />
            </a-form-item>
          </a-col>
        </a-row>
        <a-row :gutter="48">
          <a-col :md="12" :lg="12" :sm="12">
            <a-form-item label="是否显示" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-select
                placeholder="请选择是否显示"
                v-decorator="['isShow', { rules: [{ required: true, message: '请选择是否显示', }] }]">
                <a-select-option :value="0">显示</a-select-option>
                <a-select-option :value="1">不显示</a-select-option>
              </a-select>
            </a-form-item>
          </a-col>
          <a-col :md="12" :lg="12" :sm="12">
            <a-form-item label="是否可用" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-select
                placeholder="请选择是否可用"
                v-decorator="['isLock', { rules: [{ required: true, message: '请选择是否可用', }] }]">
                <a-select-option :value="0">显示</a-select-option>
                <a-select-option :value="1">不显示</a-select-option>
              </a-select>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row :gutter="48">
          <a-col :md="12" :lg="12" :sm="12">
            <a-form-item label="收款名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input
                placeholder="请输入收款名称，如：**公司"
                v-decorator="['channelName', { rules: [{ required: true, message: '请输入收款名称', }] }]" />
            </a-form-item>
          </a-col>
          <a-col :md="12" :lg="12" :sm="12">
            <a-form-item label="收款账号" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input
                placeholder="请输入收款账号"
                v-decorator="['channelAccount', { rules: [{ required: true, message: '请输入收款账号', }] }]" />
            </a-form-item>
          </a-col>
        </a-row>
        <a-row :gutter="48">
          <a-col :md="12" :lg="12" :sm="12">
            <a-form-item label="备注信息" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input
                placeholder="对公转账备注请填写收款银行和支行"
                v-decorator="['channelDesc', { rules: [{ required: true, message: '请输入备注信息', }] }]" />
            </a-form-item>
          </a-col>
          <a-col :md="12" :lg="12" :sm="12">
            <a-form-item label="支付接口" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input
                placeholder="/user/recharge/inMoney.do"
                disabled
                v-if="addUserform.getFieldValue('ctype') == 0 || addUserform.getFieldValue('ctype') == 1" />
              <a-input placeholder="支付提交的接口地址" v-else v-decorator="['formUrl', {}]" />
            </a-form-item>
          </a-col>
        </a-row>
        <a-row :gutter="48" v-if="addUserform.getFieldValue('ctype') == 2">
          <a-col :md="12" :lg="12" :sm="12">
            <a-form-item label="代码" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input
                placeholder="请输入代码"
                v-decorator="['code', { rules: [{ required: true, message: '请输入代码', }] }]" />
            </a-form-item>
          </a-col>
        </a-row>
        <a-row :gutter="48">
          <a-col :md="12" :lg="12" :sm="12">
            <a-form-item label="收款二维码" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-upload
                name="avatar"
                list-type="picture-card"
                accept=".jpg,.jpeg,.png"
                class="avatar-uploader"
                v-decorator="['channelImg', { valuePropName: 'file', rules: [{ required: true, message: '请上传收款二维码', }] }]"
                :showUploadList="false"
                :customRequest="customRequest">
                <img v-if="channelImg" :src="channelImg" alt="avatar" />
                <div v-else>
                  <a-icon :type="imgloading ? 'loading' : 'plus'" />
                </div>
              </a-upload>
            </a-form-item>
          </a-col>
        </a-row>
      </a-form>
    </a-modal>
  </page-header-wrapper>
</template>
<script>
import { paylist, payupdate, payadd, paydel, adminupload } from '@/api/allsetting'
import pick from 'lodash.pick'
export default {
    name: 'Basecurrency',
    data () {
        return {
            columns: [
                {
                    title: '通道类型',
                    dataIndex: 'channelType',
                    align: 'center'
                },
                {
                    title: '图片',
                    dataIndex: 'channelImg',
                    align: 'center',
                    scopedSlots: { customRender: 'channelImg' }
                },
                {
                    title: '通道名字',
                    dataIndex: 'channelName',
                    align: 'center'
                },
                {
                    title: '账号',
                    dataIndex: 'channelAccount',
                    align: 'center'
                },
                {
                    title: '描述',
                    dataIndex: 'channelDesc',
                    align: 'center'
                },
                {
                    title: '最小充值金额',
                    dataIndex: 'channelMinLimit',
                    align: 'center'
                },
                {
                    title: '最大充值金额',
                    dataIndex: 'channelMaxLimit',
                    align: 'center'
                },
                {
                    title: '是否显示',
                    dataIndex: 'isShow',
                    align: 'center',
                    scopedSlots: { customRender: 'isShow' }
                },
                {
                    title: '操作',
                    key: 'action',
                    align: 'center',
                    fixed: 'right',
                    width: 200,
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
                channelType: ''
            },
            datalist: [],

            addUserdialog: false,
            addUserDialogloading: false,
            addUserform: this.$form.createForm(this),
            labelCol: {
                xs: { span: 24 },
                sm: { span: 6 }
            },
            wrapperCol: {
                xs: { span: 24 },
                sm: { span: 18 }
            },
            fields: ['channelType', 'channelName', 'channelDesc', 'channelAccount', 'channelMinLimit', 'channelMaxLimit', 'isShow', 'ctype', 'isLock', 'channelImg', 'code', 'formUrl'],
            currentDetails: '',
            channelImg: '',
            imgloading: false
        }
    },
    created () {
        this.getlist()
    },
    methods: {
        deletebaseCurrency (val) {
            var that = this
            this.$confirm({
                title: '提示',
                content: '确认删除该支付渠道？此操作不可恢复',
                onOk () {
                    var data = {
                        cId: val
                    }
                    paydel(data).then(res => {
                        if (res.status == 0) {
                            that.$message.success({ content: res.msg, duration: 2 })
                            that.getlist()
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
        customRequest (data) {
            this.imgloading = true
            var formData = new FormData()
            formData.append('upload_file', data.file)
            adminupload(formData).then(res => {
                if (res.status == 0) {
                    this.channelImg = res.data.url
                    this.addUserform.setFieldsValue(({
                        channelImg: res.data.url
                    }))
                } else {
                    this.$message.error({ content: '上传失败请检查图片类型!' })
                }
                this.imgloading = false
            })
        },
        geteditbaseCurrency (val) {
            this.currentDetails = val
            this.channelImg = val.channelImg
            this.addUserdialog = true
            this.fields.forEach(v => this.addUserform.getFieldDecorator(v))
            this.addUserform.setFieldsValue(pick(val, this.fields))
        },
        CanceladdUserdialog () {
            this.addUserdialog = false
            const form = this.$refs.addUserform.form
            form.resetFields()
            this.channelImg = ''
        },
        OkaddUserdialog () {
            const form = this.$refs.addUserform.form
            form.validateFields((errors, values) => {
                if (!errors) {
                    this.addUserDialogloading = true
                    if (this.currentDetails != '') {
                        values.id = this.currentDetails.id
                        console.log(values)
                        payupdate(values).then(res => {
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
                        payadd(values).then(res => {
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
                    this.channelImg = ''
                }
            })
        },
        getqueryParam () {
            this.queryParam = {
                pageNum: 1,
                pageSize: 10,
                channelType: ''
            }
        },
        getlist () {
            this.loading = true
            paylist(this.queryParam).then(res => {
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
