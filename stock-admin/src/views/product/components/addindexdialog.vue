<template>
  <div>
    <a-modal
      title="添加指数"
      :width="1000"
      :visible="addUserdialog"
      :confirmLoading="addUserDialogloading"
      @ok="OkaddUserdialog"
      @cancel="CanceladdUserdialog">
      <a-form :form="addUserform" ref="addUserform">
        <p>指数ID填写规范,例: 上证指数 为 "sh + 股票代码",深圳指数 为 "sz + 股票代码"</p>
        <a-row :gutter="48">
          <a-col :md="8" :lg="8" :sm="12">
            <a-form-item label="指数名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input
                placeholder="请输入指数名称"
                v-decorator="['indexName', { rules: [{ required: true, message: '请输入指数名称', }] }]" />
            </a-form-item>
          </a-col>
          <a-col :md="8" :lg="8" :sm="12">
            <a-form-item label="指数代码" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input
                placeholder="请输入指数代码"
                v-decorator="['indexCode', { rules: [{ required: true, message: '请输入指数代码', }] }]" />
            </a-form-item>
          </a-col>
          <a-col :md="8" :lg="8" :sm="12">
            <a-form-item label="指数ID" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input
                placeholder="请输入指数ID"
                v-decorator="['indexGid', { rules: [{ required: true, message: '请输入指数ID', }] }]" />
            </a-form-item>
          </a-col>
        </a-row>
        <p>该指数交易规则的设置信息,请根据您的设置仿照示例填写,价格单位为元,数量单位为手</p>
        <a-row :gutter="48">
          <a-col :md="8" :lg="8" :sm="12">
            <a-form-item label="每手保证金" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input
                placeholder="每手保证金(例:10000)"
                v-decorator="['depositAmt', { rules: [{ required: true, message: '请输入每手保证金', }] }]" />
            </a-form-item>
          </a-col>
          <a-col :md="8" :lg="8" :sm="12">
            <a-form-item label="双边手续费" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input
                placeholder="双边手续费(例:200)"
                v-decorator="['transFee', { rules: [{ required: true, message: '请输入双边手续费', }] }]" />
            </a-form-item>
          </a-col>
          <a-col :md="8" :lg="8" :sm="12">
            <a-form-item label="每点浮动价" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input
                placeholder="每点浮动价(例:300)"
                v-decorator="['eachPoint', { rules: [{ required: true, message: '请输入每点浮动价', }] }]" />
            </a-form-item>
          </a-col>
        </a-row>
        <a-row :gutter="48">
          <a-col :md="8" :lg="8" :sm="12">
            <a-form-item label="最大买入手数" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input
                placeholder="最大买入手数(例:200)"
                v-decorator="['maxNum', { rules: [{ required: true, message: '请输入最大买入手数', }] }]" />
            </a-form-item>
          </a-col>
          <a-col :md="8" :lg="8" :sm="12">
            <a-form-item label="最小买入手数" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input
                placeholder="最小买入手数(例:1)"
                v-decorator="['minNum', { rules: [{ required: true, message: '请输入最小买入手数', }] }]" />
            </a-form-item>
          </a-col>
          <a-col :md="8" :lg="8" :sm="12">
            <a-form-item label="是否可交易" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-select
                placeholder="请选择交易状态"
                v-decorator="['transState', { rules: [{ required: true, message: '请选择交易状态', }] }]">
                <a-select-option value="0">不可交易</a-select-option>
                <a-select-option value="1">可交易</a-select-option>
              </a-select>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row :gutter="48">
          <a-col :md="8" :lg="8" :sm="12">
            <a-form-item label="是否首页显示" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-select
                placeholder="首页显示状态"
                v-decorator="['homeShow', { rules: [{ required: true, message: '请选择首页显示状态', }] }]">
                <a-select-option value="0">不显示</a-select-option>
                <a-select-option value="1">显示</a-select-option>
              </a-select>
            </a-form-item>
          </a-col>
          <a-col :md="8" :lg="8" :sm="12">
            <a-form-item label="是否列表页显示" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-select
                placeholder="列表页显示状态"
                v-decorator="['listShow', { rules: [{ required: true, message: '请选择列表页显示状态', }] }]">
                <a-select-option value="0">不显示</a-select-option>
                <a-select-option value="1">显示</a-select-option>
              </a-select>
            </a-form-item>
          </a-col>
        </a-row>
      </a-form>
    </a-modal>
  </div>
</template>
<script>
import { indexaddIndex, querySingleIndex } from '@/api/product'
export default {
    components: {},
    props: {
        getinit: {
            type: Function,
            default: function () {
            }
        }
    },
    data () {
        return {
            labelCol: {
                xs: { span: 10 },
                sm: { span: 10 },
                md: { span: 10 }
            },
            wrapperCol: {
                xs: { span: 14 },
                sm: { span: 14 },
                md: { span: 14 }
            },
            addUserform: this.$form.createForm(this),
            addUserdialog: false,
            addUserDialogloading: false
        }
    },
    methods: {
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
                    querySingleIndex({ indexCode: this.addUserform.getFieldValue('indexGid') }).then(res => {
                        if (res.data.name == null || res.data.name === '') {
                            this.$message.error('您输入的指数不正确,请添加正确的指数')
                        } else {
                            this.addUserDialogloading = true
                            indexaddIndex(values).then(res => {
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
                    })
                }
            })
        }
    }
}
</script>
