<template>
  <div>
    <a-modal
      title="修改代理信息"
      :width="500"
      :visible="editUserdialog"
      :confirmLoading="editUserDialogloading"
      @ok="OkeditUserdialog"
      @cancel="CanceleditUserdialog">
      <a-form :form="editUserform" ref="editUserform">
        <a-form-item label="代理ID" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input placeholder="请输入代理ID" disabled v-decorator="['id', {}]" />
        </a-form-item>
        <!-- <a-form-item label="代理名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
                    <a-input placeholder="请输入代理名称" v-decorator="['agentName', {}]" />
                </a-form-item> -->
        <a-form-item label="代理手机号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input placeholder="请输入代理手机号" v-decorator="['agentPhone', {rules: [{ required: true, message: '请输入代理手机号', }] }]" />
        </a-form-item>
        <a-form-item label="真实姓名" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input placeholder="请输入真实姓名" v-decorator="['agentRealName', {rules: [{ required: true, message: '请输入真实姓名', }] }]" />
        </a-form-item>
        <a-form-item label="密码" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input placeholder="请输入密码" v-decorator="['agentPwd', {}]" />
        </a-form-item>
        <a-form-item label="杠杆倍数" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input placeholder="请输入杠杆倍数" v-decorator="['siteLever', {}]" />
          <span>（例:100/50）</span>
        </a-form-item>
        <a-form-item label="代理状态" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-select placeholder="请选择代理状态" v-decorator="['isLock', {rules: [{ required: true, message: '请选择代理状态', }]}]">
            <a-select-option :value="0">正常</a-select-option>
            <a-select-option :value="1">锁定</a-select-option>
          </a-select>
        </a-form-item>
      </a-form>
    </a-modal>
  </div>
</template>
<script>
import { agentupdate } from '@/api/home'
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
            fields: ['id', 'agentPhone', 'agentRealName', 'agentPwd', 'siteLever', 'isLock'],
            currentDetails: {}
        }
    },
    methods: {
        getEditorder (val) {
            this.currentDetails = val
            this.editUserdialog = true
            this.fields.forEach(v => this.editUserform.getFieldDecorator(v))
            this.editUserform.setFieldsValue(pick(val, this.fields))
            this.editUserform.setFieldsValue(pick({ agentPwd: '' }, this.fields))
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
                    this.editUserDialogloading = true
                    agentupdate(values).then(res => {
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
