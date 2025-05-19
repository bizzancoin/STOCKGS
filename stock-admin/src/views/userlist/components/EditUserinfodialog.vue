<template>
  <div>
    <a-modal
      title="修改用户信息"
      :width="500"
      :visible="editUserdialog"
      :confirmLoading="editUserDialogloading"
      @ok="OkeditUserdialog"
      @cancel="CanceleditUserdialog">
      <a-form :form="editUserform" ref="editUserform">
        <a-form-item label="所属代理" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-select placeholder="请选择所属代理" v-decorator="['agentId', {}]">
            <a-select-option v-for="(item, index) in agentlist" :key="index" :value="item.id">{{
              item.agentName
            }}
            </a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="手机号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input placeholder="请输入手机号" v-decorator="['phone', {}]" />
        </a-form-item>
        <a-form-item label="用户名" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input placeholder="请输入用户名" v-decorator="['realName', {}]" />
        </a-form-item>
        <a-form-item label="密码" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input placeholder="请输入密码" v-decorator="['userPwd', {}]" />
        </a-form-item>
        <a-form-item label="身份证号码" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input placeholder="请输入身份证号码" v-decorator="['idCard', {}]" />
        </a-form-item>
        <a-form-item label="登录状态" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-select placeholder="请选择登录状态" v-decorator="['isLogin', {}]">
            <a-select-option :value="0">可登录</a-select-option>
            <a-select-option :value="1">不可交易</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="交易状态" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-select placeholder="请选择交易状态" v-decorator="['isLock', {}]">
            <a-select-option :value="0">可交易</a-select-option>
            <a-select-option :value="1">不可交易</a-select-option>
          </a-select>
        </a-form-item>
      </a-form>
    </a-modal>
  </div>
</template>
<script>
import { userupdate } from '@/api/home'
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
            fields: ['agentId', 'phone', 'realName', 'userPwd', 'idCard', 'isLogin', 'isLock'],
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
                    userupdate(values).then(res => {
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
