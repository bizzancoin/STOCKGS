<template>
  <page-header-wrapper>
    <a-form @submit="handleSubmit" :form="form" class="form" ref="form">
      <a-card class="card" title="保证金设置" :bordered="false" :loading="loading">
        <span slot="extra">请按照括号中的示例填写</span>
        <a-row class="form-row" :gutter="16">
          <a-col :lg="6" :md="12" :sm="24">
            <a-form-item label="保证金最小值（例:100，单位元）">
              <a-input placeholder="保证金最小值" v-decorator="['marginMin', {}]" />
            </a-form-item>
          </a-col>
          <a-col :xl="{ span: 7, offset: 1 }" :lg="{ span: 8 }" :md="{ span: 12 }" :sm="24">
            <a-form-item label="保证金最大值（例:10000，单位元）">
              <a-input placeholder="请输入保证金最大值" v-decorator="['marginMax',{}]" />
            </a-form-item>
          </a-col>
        </a-row>
      </a-card>
      <a-card class="card" title="免息设置" :bordered="false" :loading="loading">
        <span slot="extra">填写规则请按照括号中的示例填写，":"统一为英文中的字符，提现时间为24小时制，请填写整数</span>
        <a-row class="form-row" :gutter="16">
          <a-col :lg="6" :md="12" :sm="24">
            <a-form-item label="免息杠杆（例：1，填倍数）">
              <a-input
                placeholder="请输入免息杠杆倍数"
                v-decorator="[
                  'interestFreeLever',{}]" />
            </a-form-item>
          </a-col>
          <a-col :xl="{ span: 7, offset: 1 }" :lg="{ span: 8 }" :md="{ span: 12 }" :sm="24">
            <a-form-item label="免息天数（例：5，单位天）">
              <a-input placeholder="请输入免息天数" v-decorator="['interestFreeDays',{}]" />
            </a-form-item>
          </a-col>
          <a-col :xl="{ span: 9, offset: 1 }" :lg="{ span: 10 }" :md="{ span: 24 }" :sm="24">
            <a-form-item label="免息盈利（例：0.85，百分比）">
              <a-input
                placeholder="请输入免息盈利"
                v-decorator="['interestFreeProfit',{}
                ]" />
            </a-form-item>
          </a-col>
        </a-row>
        <a-row class="form-row" :gutter="16">
          <a-col :lg="6" :md="12" :sm="24">
            <a-form-item label="免息预警线（例：0.75）">
              <a-input placeholder="请输入免息预警线" v-decorator="['interestFreeWarning',{}]" />
            </a-form-item>
          </a-col>
          <a-col :xl="{ span: 7, offset: 1 }" :lg="{ span: 8 }" :md="{ span: 12 }" :sm="24">
            <a-form-item label="免息平仓线（例：0.82）">
              <a-input placeholder="请输入免息平仓线" v-decorator="['interestFreeUnwind',{}]" />
            </a-form-item>
          </a-col>
        </a-row>
      </a-card>
      <a-card class="card" title="按天设置 " :bordered="false" :loading="loading">
        <span slot="extra">填写规则请按照括号中的示例填写，":"统一为英文中的字符，提现时间为24小时制，请填写整数</span>
        <a-row class="form-row" :gutter="16">
          <a-col :lg="6" :md="12" :sm="24">
            <a-form-item label="使用期限（例：2|3）">
              <a-input
                placeholder="请输入使用期限"
                v-decorator="[
                  'daysUsePeriod',
                  {}
                ]" />
            </a-form-item>
          </a-col>
          <a-col :xl="{ span: 7, offset: 1 }" :lg="{ span: 8 }" :md="{ span: 12 }" :sm="24">
            <a-form-item label="按天预警线（例：0.75，百分比">
              <a-input
                placeholder="请输入按天预警线"
                v-decorator="[
                  'daysWarning',
                  {}
                ]" />
            </a-form-item>
          </a-col>
          <a-col :xl="{ span: 9, offset: 1 }" :lg="{ span: 10 }" :md="{ span: 24 }" :sm="24">
            <a-form-item label="按天平仓线（例：0.82，百分比）">
              <a-input
                placeholder="请输入按天平仓线"
                v-decorator="[
                  'daysUnwind',
                  {}
                ]" />
            </a-form-item>
          </a-col>
        </a-row>
      </a-card>
      <a-card class="card" title="交易设置 " :bordered="false" :loading="loading">
        <span slot="extra">请按照括号中的示例填写,比例均采用小数来表示</span>
        <a-row class="form-row" :gutter="16">
          <a-col :lg="5" :md="12" :sm="24">
            <a-form-item label="交易佣金费率（例:0.75，百分比）">
              <a-input
                placeholder="请输入交易佣金费率"
                v-decorator="[
                  'tradingCommissionRate',
                  {}
                ]" />
            </a-form-item>
          </a-col>
          <a-col :xl="{ span: 5, offset: 1 }" :lg="{ span: 8 }" :md="{ span: 12 }" :sm="24">
            <a-form-item label="每笔最低费用（例:5，单位元）">
              <a-input
                placeholder="请输入每笔最低费用"
                v-decorator="[
                  'stampDutyRate',
                  {}
                ]" />
            </a-form-item>
          </a-col>
          <a-col :xl="{ span: 5, offset: 1 }" :lg="{ span: 10 }" :md="{ span: 24 }" :sm="24">
            <a-form-item label="盈利分成比例（例:0.85，百分比）">
              <a-input
                placeholder="请输入盈利分成比例"
                v-decorator="[
                  'profitSharingRatio',
                  {}
                ]" />
            </a-form-item>
          </a-col>
          <a-col :xl="{ span: 5, offset: 1 }" :lg="{ span: 10 }" :md="{ span: 24 }" :sm="24">
            <a-form-item label="提前终止利息（例:0.25，百分比）">
              <a-input
                placeholder="请输入提前终止利息"
                v-decorator="[
                  'earlyTerminationInterest',
                  {}
                ]" />
            </a-form-item>
          </a-col>
        </a-row>

      </a-card>
      <footer-tool-bar>
        <a-button type="primary" html-type="submit">
          修改提交
        </a-button>
      </footer-tool-bar>
    </a-form>
  </page-header-wrapper>
</template>
<script>
import FooterToolBar from '@/components/FooterToolbar'
import { getFundsSetting, saveFundsSetting } from '@/api/allotment'
import pick from 'lodash.pick'
export default {
    name: 'Setting',
    components: {
        FooterToolBar
    },
    data () {
        return {
            form: this.$form.createForm(this),
            loading: false,
            id: '',
            details: {},
            fields: ['marginMin', 'marginMax', 'interestFreeLever', 'interestFreeDays', 'interestFreeProfit', 'interestFreeWarning', 'interestFreeUnwind', 'daysUsePeriod', 'daysWarning', 'daysUnwind', 'tradingCommissionRate', 'stampDutyRate', 'profitSharingRatio', 'earlyTerminationInterest']
        }
    },
    mounted () {
        this.getdetail()
    },
    methods: {
        handleSubmit (e) {
            e.preventDefault()
            this.form.validateFields((err, values) => {
                var that = this
                var formitem = values
                formitem.id = that.id
                saveFundsSetting(formitem).then(res => {
                    if (res.status == 0) {
                        that.$message.loading({ content: 'Loading...' })
                        setTimeout(() => {
                            that.$message.success({ content: '提交成功', duration: 2 })
                            that.getdetail()
                        }, 2000)
                    } else {
                        that.$message.error({ content: res.msg })
                    }
                })
            })
        },
        getdetail () {
            var that = this
            this.loading = true
            getFundsSetting().then(res => {
                that.id = res.data.id
                this.fields.forEach(v => this.form.getFieldDecorator(v))
                this.form.setFieldsValue(pick(res.data, this.fields))
                setTimeout(() => {
                    that.loading = false
                }, 500)
            })
        }
    }
}
</script>
<style lang="less" scoped>
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
