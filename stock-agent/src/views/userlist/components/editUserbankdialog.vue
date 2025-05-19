<template>
  <div>
    <a-modal
      title="修改银行卡信息"
      :width="500"
      :visible="editUserdialog"
      :confirmLoading="editUserDialogloading"
      @ok="OkeditUserdialog"
      @cancel="CanceleditUserdialog">
      <a-form :form="editUserform" ref="editUserform">
        <a-form-item label="银行名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input placeholder="请输入银行名称" v-decorator="['bankName', {}]" />
        </a-form-item>
        <a-form-item label="银行卡" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input placeholder="请输入银行卡" v-decorator="['bankNo', {}]" />
        </a-form-item>
        <a-form-item label="支行地址" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input placeholder="请输入支行地址" v-decorator="['bankAddress', {}]" />
        </a-form-item>
      </a-form>
    </a-modal>
  </div>
</template>
<script>
import { userupdateBank, usergetBank } from '@/api/home'
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
            fields: ['bankName', 'bankAddress', 'bankNo'],
            currentDetails: {}
        }
    },
    methods: {
        getbankinfo (val) {
            this.currentDetails = val
            const data = {
                userId: val.id
            }
            usergetBank(data).then(res => {
                if (res.status == 0) {
                    this.getEditorder(res.data)
                } else {

                }
            })
        },
        getEditorder (val) {
            this.editUserdialog = true
            this.fields.forEach(v => this.editUserform.getFieldDecorator(v))
            this.editUserform.setFieldsValue(pick(val, this.fields))
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
                    values.id = this.currentDetails.id
                    this.editUserDialogloading = true
                    userupdateBank(values).then(res => {
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
