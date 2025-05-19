<template>
  <div>
    <a-modal
      title="账户扣入款"
      :width="500"
      :visible="editUserdialog"
      :confirmLoading="editUserDialogloading"
      @ok="OkeditUserdialog"
      @cancel="CanceleditUserdialog">
      <a-form :form="editUserform" ref="editUserform">
        <a-form-item label="用户id" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input placeholder="请输入用户id" disabled v-decorator="['agentId', {}]" />
        </a-form-item>
        <a-form-item label="金额" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="请输入金额"
            v-decorator="['amt', { rules: [{ required: true, message: '请输入金额', }] }]" />
        </a-form-item>
        <a-form-item label="扣入款" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-select
            placeholder="请选择扣入款"
            v-decorator="['direction', { rules: [{ required: true, message: '请选择扣入款', }] }]">
            <a-select-option value="1">扣款</a-select-option>
            <a-select-option value="0">入款</a-select-option>
          </a-select>
        </a-form-item>
      </a-form>
    </a-modal>
  </div>
</template>
<script>
import { agentupdateAgentAmt } from '@/api/home'
import pick from 'lodash.pick'
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
            fields: ['amt', 'direction', 'agentId'],
            currentDetails: {}
        }
    },
    methods: {
        getEditorder (val) {
            this.currentDetails = val
            this.editUserdialog = true
            this.fields.forEach(v => this.editUserform.getFieldDecorator(v))
            this.editUserform.setFieldsValue(pick(val, this.fields))
            this.editUserform.setFieldsValue(pick({ agentId: val.id }, this.fields))
        },
        CanceleditUserdialog () {
            this.editUserdialog = false
            const form = this.$refs.editUserform.form
            form.resetFields()
        },
        OkeditUserdialog () {
            const form = this.$refs.editUserform.form
            form.validateFields((errors, values) => {
                if (!errors) {
                    this.editUserDialogloading = true
                    agentupdateAgentAmt(values).then(res => {
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
