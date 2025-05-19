<template>
  <div>
    <a-modal
      title="添加用户(添加的金额默认为融资资金)"
      :width="500"
      :visible="addUserdialog"
      :confirmLoading="addUserDialogloading"
      @ok="OkaddUserdialog"
      @cancel="CanceladdUserdialog">
      <a-form :form="addUserform" ref="addUserform">
        <a-form-item label="账号类型" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-select
            placeholder="请选择账号类型"
            v-decorator="['accountType', { rules: [{ required: true, message: '请选择账号类型', }] }]">
            <a-select-option value="0">正式</a-select-option>
            <a-select-option value="1">模拟</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="手机号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="请输入手机号"
            v-decorator="['phone', { rules: [{ required: true, message: '请输入手机号', }] }]" />
        </a-form-item>
        <a-form-item label="密码" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="请输入密码"
            v-decorator="['pwd', { rules: [{ required: true, message: '请输入密码', }] }]" />
        </a-form-item>
        <a-form-item label="金额" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="请输入金额"
            v-decorator="['amt', { rules: [{ required: true, message: '请输入金额', }] }]" />
        </a-form-item>
      </a-form>
    </a-modal>
  </div>
</template>
<script>
import { agentaddSimulatedAccount } from '@/api/home'
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
                    this.addUserDialogloading = true
                    agentaddSimulatedAccount(values).then(res => {
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
    }
}
</script>
