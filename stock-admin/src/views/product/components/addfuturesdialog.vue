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
        <p>该期货交易规则的设置信息,请根据您的设置仿照示例填写,价格单位为元,数量单位为手</p>
        <a-row :gutter="48">
          <a-col :md="8" :lg="8" :sm="12">
            <a-form-item label="期货名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input
                placeholder="请输入期货名称"
                v-decorator="['futuresName', { rules: [{ required: true, message: '请输入期货名称', }] }]" />
            </a-form-item>
          </a-col>
          <a-col :md="8" :lg="8" :sm="12">
            <a-form-item label="期货代码" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input
                placeholder="请输入期货代码"
                v-decorator="['futuresCode', { rules: [{ required: true, message: '请输入期货代码', }] }]" />
            </a-form-item>
          </a-col>

          <a-col :md="8" :lg="8" :sm="12">
            <a-form-item label="每手手续费" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input
                placeholder="请输入每手手续费"
                v-decorator="['transFee', { rules: [{ required: true, message: '请输入每手手续费', }] }]" />
            </a-form-item>
          </a-col>
        </a-row>
        <a-row :gutter="48">
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
        <a-row :gutter="48">
          <a-col :md="12" :lg="12" :sm="12">
            <a-form-item label="买入手数限制" :labelCol="labelCol1" :wrapperCol="wrapperCol1">
              <a-input
                placeholder="请输入最小手数"
                v-decorator="['minNum', { rules: [{ required: true, message: '请输入最小手数', }] }]" />
              &nbsp;&nbsp;手
              <span class="separator">~&nbsp;&nbsp;</span>
              <a-input
                placeholder="请输入最大手数"
                v-decorator="['maxNum', { rules: [{ required: true, message: '请输入最大手数', }] }]" />
              &nbsp;&nbsp;手
            </a-form-item>
          </a-col>
          <a-col :md="12" :lg="12" :sm="12">
            <a-form-item label="每手保证金" :labelCol="labelCol1" :wrapperCol="wrapperCol1">
              <a-input
                placeholder="请输入金额"
                v-decorator="['futuresStandard', { rules: [{ required: true, message: '请输入金额', }] }]" />
              <a-select
                placeholder="请选择基础货币单位"
                v-decorator="['coinCode', { rules: [{ required: true, message: '请选择基础货币单位', }] }]">
                <a-select-option v-for="(item, index) in coinlist" :key="index" :value="item.coinCode">
                  {{ item.coinName }}
                </a-select-option>
              </a-select>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row :gutter="48">
          <a-col :md="12" :lg="12" :sm="12">
            <a-form-item label="上午" :labelCol="labelCol1" :wrapperCol="wrapperCol1">
              <a-input
                placeholder="例：1:00"
                v-decorator="['transAmBegin', { rules: [{ required: true, message: '请输入上午开始时间 例：1:00', }] }]" />
              &nbsp;&nbsp;点
              <span class="separator">~&nbsp;&nbsp;</span>
              <a-input
                placeholder="例：12:00"
                v-decorator="['transAmEnd', { rules: [{ required: true, message: '请输入上午开始时间 例：12:00', }] }]" />
              &nbsp;&nbsp;点
            </a-form-item>
          </a-col>
          <a-col :md="12" :lg="12" :sm="12">
            <a-form-item label="下午" :labelCol="labelCol1" :wrapperCol="wrapperCol1">
              <a-input
                placeholder="例：12:00"
                v-decorator="['transPmBegin', { rules: [{ required: true, message: '请输入上午开始时间 例：12:00', }] }]" />
              &nbsp;&nbsp;点
              <span class="separator">~&nbsp;&nbsp;</span>
              <a-input
                placeholder="例：23:00"
                v-decorator="['transPmEnd', { rules: [{ required: true, message: '请输入上午开始时间 例：23:00', }] }]" />
              &nbsp;&nbsp;点
            </a-form-item>
          </a-col>
        </a-row>
        <a-row :gutter="48">
          <a-col :md="24" :lg="24" :sm="24">
            <a-form-item label="每标准手规格" :labelCol="labelCol2" :wrapperCol="wrapperCol1">
              <div style="display:flex;width: 100%;margin-top: 5px;">
                <span style="width:300px;margin-left: 10px;line-height: 30px;align-items: center;">1标准手 =</span>
                <a-input
                  placeholder="每标准手数量"
                  v-decorator="['depositAmt', { rules: [{ required: true, message: '每标准手数量', }] }]" />
                /
                <span class="separator">~&nbsp;&nbsp;</span>
                <a-input
                  placeholder="单位"
                  v-decorator="['futuresUnit', { rules: [{ required: true, message: '单位', }] }]" />
                <span style="width:800px;margin-left: 10px;line-height: 30px;align-items: center;">例如美原油：1标准手 = 1000桶</span>
              </div>
            </a-form-item>
          </a-col>
        </a-row>
      </a-form>
    </a-modal>
  </div>
</template>
<script>
import { futuresadd } from '@/api/product'
export default {
    props: {
        getinit: {
            type: Function,
            default: function () {
            }
        },
        coinlist: {
            type: Array,
            default: function () {
                return []
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
            labelCol1: {
                xs: { span: 10 },
                sm: { span: 10 },
                md: { span: 6 }
            },
            wrapperCol1: {
                xs: { span: 14 },
                sm: { span: 14 },
                md: { span: 18 }
            },
            labelCol2: {
                xs: { span: 4 },
                sm: { span: 4 },
                md: { span: 4 }
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
                    values.futuresGid = 'hf_' + this.addUserform.getFieldValue('futuresCode'),
                    futuresadd(values).then(res => {
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
<style lang="less" scoped>
/deep/ .ant-form-item-children {
    display: flex;
    align-items: center;
}
</style>
