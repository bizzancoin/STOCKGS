<template>
  <div>
    <a-modal
      title="修改指数信息"
      :width="500"
      :visible="editUserdialog"
      :confirmLoading="editUserDialogloading"
      @ok="OkeditUserdialog"
      @cancel="CanceleditUserdialog">
      <a-form :form="editUserform" ref="editUserform">
        <a-form-item label="每手保证金" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="每手保证金(例:10000)"
            v-decorator="['depositAmt', { rules: [{ required: true, message: '请输入每手保证金', }] }]" />
        </a-form-item>
        <a-form-item label="双边手续费" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="双边手续费(例:200)"
            v-decorator="['transFee', { rules: [{ required: true, message: '请输入双边手续费', }] }]" />
        </a-form-item>
        <a-form-item label="每点浮动价" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="每点浮动价(例:300)"
            v-decorator="['eachPoint', { rules: [{ required: true, message: '请输入每点浮动价', }] }]" />
        </a-form-item>
        <a-form-item label="最大买入手数" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="最大买入手数(例:200)"
            v-decorator="['maxNum', { rules: [{ required: true, message: '请输入最大买入手数', }] }]" />
        </a-form-item>
        <a-form-item label="最小买入手数" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="最小买入手数(例:1)"
            v-decorator="['minNum', { rules: [{ required: true, message: '请输入最小买入手数', }] }]" />
        </a-form-item>
        <a-form-item label="是否可交易" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-select
            placeholder="请选择交易状态"
            v-decorator="['transState', { rules: [{ required: true, message: '请选择交易状态', }] }]">
            <a-select-option :value="0">不可交易</a-select-option>
            <a-select-option :value="1">可交易</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="是否首页显示" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-select
            placeholder="首页显示状态"
            v-decorator="['homeShow', { rules: [{ required: true, message: '请选择首页显示状态', }] }]">
            <a-select-option :value="0">不显示</a-select-option>
            <a-select-option :value="1">显示</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="是否列表页显示" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-select
            placeholder="列表页显示状态"
            v-decorator="['listShow', { rules: [{ required: true, message: '请选择列表页显示状态', }] }]">
            <a-select-option :value="0">不显示</a-select-option>
            <a-select-option :value="1">显示</a-select-option>
          </a-select>
        </a-form-item>
      </a-form>
    </a-modal>
  </div>
</template>
<script>
import { indexupdateIndex } from '@/api/product'
import pick from 'lodash.pick'
export default {
    components: {},
    props: {
        getinit: {
            type: Function,
            default: function () {
            }
        },
        agentlist: {
            type: Array
        }
    },
    data () {
        return {
            labelCol: {
                xs: { span: 24 },
                sm: { span: 7 }
            },
            wrapperCol: {
                xs: { span: 24 },
                sm: { span: 13 }
            },
            editUserform: this.$form.createForm(this),
            editUserdialog: false,
            editUserDialogloading: false,
            fields: ['depositAmt', 'transFee', 'eachPoint', 'maxNum', 'minNum', 'homeShow', 'listShow', 'transState'],
            currentDetails: {}
        }
    },
    methods: {
        getEditorder (val) {
            this.currentDetails = val
            this.editUserdialog = true
            this.fields.forEach(v => this.editUserform.getFieldDecorator(v))
            this.editUserform.setFieldsValue(pick(val, this.fields))
        },
        // 新增用户取消弹窗
        CanceleditUserdialog () {
            this.editUserdialog = false
            const form = this.$refs.editUserform.form
            form.resetFields()
        },
        // 新增用户确定
        OkeditUserdialog () {
            const form = this.$refs.editUserform.form
            form.validateFields((errors, values) => {
                if (!errors) {
                    values.id = this.currentDetails.id
                    this.editUserDialogloading = true
                    indexupdateIndex(values).then(res => {
                        if (res.status == 0) {
                            this.editUserdialog = false
                            this.$message.success({ content: res.msg, duration: 2 })
                            form.resetFields()
                            this.getinit()
                        } else {
                            this.$message.error({ content: res.msg })
                        }
                        this.editUserDialogloading = false
                    })
                }
            })
        }
    }
}
</script>
