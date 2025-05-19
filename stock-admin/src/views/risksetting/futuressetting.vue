<template>
  <page-header-wrapper>
    <a-form :form="addUserform" class="form" ref="addUserform">
      <a-card class="card" title="期货设置" :bordered="false" :loading="loading">
        <span slot="extra">填写规则请按照括号中的示例填写，":"统一为英文中的字符,百分比均采用小数来表示</span>
        <p>期货买入比例（期货买入比例即期货买入的仓位,期货最多能够买的金额）</p>
        <a-row class="form-row" :gutter="48">
          <a-col :md="12" :lg="6" :sm="12">
            <a-form-item label="最大买入比例（例:0.8）">
              <a-input
                placeholder="请输入最大买入比例"
                v-decorator="['buyMaxPercent', { rules: [{ required: true, message: '请输入最大买入比例', }] }]" />
            </a-form-item>
          </a-col>
        </a-row>
        <p>期货强平设置（百分比均采用小数来表示,例:0.8表示80% 强制平仓线计算规则：可用资金 + （冻结保证金 * 强制平仓比例））</p>
        <a-row class="form-row" :gutter="48">
          <a-col :md="12" :lg="6" :sm="12">
            <a-form-item label="强平比例（例:0.8）">
              <a-input
                placeholder="请输入强平比例"
                v-decorator="['forceSellPercent', { rules: [{ required: true, message: '请输入强平比例', }] }]" />
            </a-form-item>
          </a-col>
          <a-col :md="12" :lg="6" :sm="12">
            <a-form-item label="强制平仓提醒比例（例:0.5）">
              <a-input
                placeholder="请输入强制平仓提醒比例"
                v-decorator="['forceStopRemindRatio', { rules: [{ required: true, message: '请输入强制平仓提醒比例', }] }]" />
            </a-form-item>
          </a-col>
        </a-row>
        <a-row class="form-row" :gutter="48">
          <a-col :md="24" :lg="24" :sm="24">
            <a-form-item label="设置多少分钟内同一只股票不得下单多少次(同一用户)">
              <a-input-number
                placeholder="请输入分钟数"
                style="width: 300px"
                v-decorator="['buySameTimes', { rules: [{ required: true, message: '请输入分钟数', }] }]" />
              分钟内同一只股票不得下单
              <a-input-number
                placeholder="请输入下单次数"
                style="width: 300px"
                v-decorator="['buySameNums', { rules: [{ required: true, message: '请输入下单次数', }] }]" />
              次
            </a-form-item>
          </a-col>
        </a-row>
        <a-col :md="24" :lg="24" :sm="24">
          <a-form-item label="设置多少分钟内交易手数不得超过多少手(同一用户)">
            <a-input-number
              placeholder="请输入分钟数"
              style="width: 300px"
              v-decorator="['buyNumTimes', { rules: [{ required: true, message: '请输入分钟数', }] }]" />
            分钟内交易手数不得超过
            <a-input-number
              placeholder="请输入下单手数"
              style="width: 300px"
              v-decorator="['buyNumLots', { rules: [{ required: true, message: '请输入下单手数', }] }]" />
            手
          </a-form-item>
        </a-col>
      </a-card>
    </a-form>
    <div class="bottomfixed">
      <div style="float:right">
        <a-button type="primary" @click="OkaddUserdialog" :loading="addUserDialogloading">
          保存当前设置
        </a-button>
      </div>
    </div>

  </page-header-wrapper>
</template>
<script>
import { admingetFuturesSetting, sitefuturesupdate } from '@/api/risksetting'
import pick from 'lodash.pick'
export default {
    name: 'Sharessetting',
    data () {
        return {
            addUserform: this.$form.createForm(this),
            loading: false,
            fields: ['buyMaxPercent', 'forceSellPercent', 'buySameTimes', 'buySameNums', 'buyNumTimes', 'buyNumLots', 'forceStopRemindRatio' ],
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
            addUserDialogloading: false,
            details: {}
        }
    },
    mounted () {
        this.getdetail()
    },
    methods: {
        OkaddUserdialog () {
            const form = this.$refs.addUserform.form
            form.validateFields((errors, values) => {
                if (!errors) {
                    this.addUserDialogloading = true
                    this.loading = true
                    values.id = this.details.id
                    sitefuturesupdate(values).then(res => {
                        if (res.status == 0) {
                            this.$message.success({ content: res.msg, duration: 2 })
                            this.getdetail()
                        } else {
                            this.$message.error({ content: res.msg })
                        }
                        this.addUserDialogloading = false
                    })
                }
            })
        },
        getdetail () {
            var that = this
            this.loading = true
            admingetFuturesSetting().then(res => {
                this.details = res.data
                this.fields.forEach(v => this.addUserform.getFieldDecorator(v))
                this.addUserform.setFieldsValue(pick(res.data, this.fields))
                setTimeout(() => {
                    that.loading = false
                }, 500)
            })
        }
    }
}
</script>
<style lang="less" scoped>
.bottomfixed {
    position: fixed;
    right: 0;
    bottom: 0;
    z-index: 9;
    width: 100%;
    height: 56px;
    padding: 0 24px;
    line-height: 56px;
    background: #fff;
    border-top: 1px solid #e8e8e8;
}

.card {
    margin-bottom: 24px;
}

/deep/ .ant-pro-global-footer {
    margin: 0 0 48px 0 !important;
}

.popover-wrapper {
    :deep(.antd-pro-pages-forms-style-errorPopover .ant-popover-inner-content) {
        min-width: 256px;
        max-height: 290px;
        padding: 0;
        overflow: auto;
    }
}

.antd-pro-pages-forms-style-errorIcon {
    user-select: none;
    margin-right: 24px;
    color: #f5222d;
    cursor: pointer;

    i {
        margin-right: 4px;
    }
}

.antd-pro-pages-forms-style-errorListItem {
    padding: 8px 16px;
    list-style: none;
    border-bottom: 1px solid #e8e8e8;
    cursor: pointer;
    transition: all .3s;

    &:hover {
        background: #e6f7ff;
    }

    .antd-pro-pages-forms-style-errorIcon {
        float: left;
        margin-top: 4px;
        margin-right: 12px;
        padding-bottom: 22px;
        color: #f5222d;
    }

    .antd-pro-pages-forms-style-errorField {
        margin-top: 2px;
        color: rgba(0, 0, 0, .45);
        font-size: 12px;
    }
}
</style>
