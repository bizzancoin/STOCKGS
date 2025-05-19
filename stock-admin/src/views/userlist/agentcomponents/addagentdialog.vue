<template>
  <div>
    <a-modal
      title="添加下级代理"
      :width="500"
      :visible="addUserdialog"
      :confirmLoading="addUserDialogloading"
      @ok="OkaddUserdialog"
      @cancel="CanceladdUserdialog">
      <a-form :form="addUserform" ref="addUserform">
        <a-form-item label="上级代理" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-select
            placeholder="请选择上级代理"
            v-decorator="['parentId', { rules: [{ required: true, message: '请选择上级代理', }] }]">
            <a-select-option value="0">总代理</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="代理名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="请输入代理名称"
            v-decorator="['agentName', { rules: [{ required: true, message: '请输入代理名称', }] }]" />
        </a-form-item>
        <a-form-item label="代理手机号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="请输入代理手机号"
            v-decorator="['agentPhone', { rules: [{ required: true, message: '请输入代理手机号', }] }]" />
        </a-form-item>
        <a-form-item label="密码" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="请输入密码"
            v-decorator="['agentPwd', { rules: [{ required: true, message: '请输入密码', }] }]" />
        </a-form-item>
        <a-form-item label="真实姓名" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="请输入真实姓名"
            v-decorator="['agentRealName', { rules: [{ required: true, message: '请输入真实姓名', }] }]" />
        </a-form-item>
      </a-form>
    </a-modal>
  </div>
</template>
<script>
import { agentadd } from '@/api/home'
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
                    agentadd(values).then(res => {
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
