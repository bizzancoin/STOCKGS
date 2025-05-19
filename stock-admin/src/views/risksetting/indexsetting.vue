<template>
  <page-header-wrapper>
    <a-form :form="addUserform" class="form" ref="addUserform">
      <a-card class="card" title="指数设置" :bordered="false" :loading="loading">
        <span slot="extra">填写规则请按照括号中的示例填写，":"统一为英文中的字符，提现时间为24小时制，请填写整数</span>
        <a-row class="form-row" :gutter="48">
          <a-col :md="12" :lg="6" :sm="24">
            <a-form-item label="上午开始交易时间（例：9:30）">
              <a-input placeholder="请输入上午开始交易时间" v-decorator="['transAmBegin', {}]" />
            </a-form-item>
          </a-col>
          <a-col :md="12" :lg="6" :sm="24">
            <a-form-item label="上午结束交易时间（例：10:30）">
              <a-input placeholder="请输入上午结束交易时间" v-decorator="['transAmEnd', {}]" />
            </a-form-item>
          </a-col>
          <a-col :md="12" :lg="6" :sm="24">
            <a-form-item label="下午开始交易时间（例：13:30）">
              <a-input placeholder="请输入下午开始交易时间" v-decorator="['transPmBegin', {}]" />
            </a-form-item>
          </a-col>
          <a-col :md="12" :lg="6" :sm="24">
            <a-form-item label="下午结束交易时间（例：15:00）">
              <a-input placeholder="请输入下午结束交易时间" v-decorator="['transPmEnd', {}]" />
            </a-form-item>
          </a-col>
        </a-row>
        <!-- <a-row class="form-row" :gutter="48">
                    <a-col :md="12" :lg="6" :sm="24">
                        <a-form-item label="美股上午开始交易时间（例：9:30）">
                            <a-input placeholder="请输入上午开始交易时间" v-decorator="['transAmBeginUs', {}]" />
                        </a-form-item>
                    </a-col>
                    <a-col :md="12" :lg="6" :sm="24">
                        <a-form-item label="美股上午结束交易时间（例：10:30）">
                            <a-input placeholder="请输入上午结束交易时间" v-decorator="['transAmEndUs', {}]" />
                        </a-form-item>
                    </a-col>
                    <a-col :md="12" :lg="6" :sm="24">
                        <a-form-item label="美股下午开始交易时间（例：13:30）">
                            <a-input placeholder="请输入下午开始交易时间" v-decorator="['transPmBeginUs', {}]" />
                        </a-form-item>
                    </a-col>
                    <a-col :md="12" :lg="6" :sm="24">
                        <a-form-item label="美股下午结束交易时间（例：15:00）">
                            <a-input placeholder="请输入下午结束交易时间" v-decorator="['transPmEndUs', {}]" />
                        </a-form-item>
                    </a-col>
                </a-row>
                <a-row class="form-row" :gutter="48">
                    <a-col :md="12" :lg="6" :sm="24">
                        <a-form-item label="上午开始交易时间（例：9:30）">
                            <a-input placeholder="请输入上午开始交易时间" v-decorator="['transAmBeginhk', {}]" />
                        </a-form-item>
                    </a-col>
                    <a-col :md="12" :lg="6" :sm="24">
                        <a-form-item label="上午结束交易时间（例：10:30）">
                            <a-input placeholder="请输入上午结束交易时间" v-decorator="['transAmEndhk', {}]" />
                        </a-form-item>
                    </a-col>
                    <a-col :md="12" :lg="6" :sm="24">
                        <a-form-item label="下午开始交易时间（例：13:30）">
                            <a-input placeholder="请输入下午开始交易时间" v-decorator="['transPmBeginhk', {}]" />
                        </a-form-item>
                    </a-col>
                    <a-col :md="12" :lg="6" :sm="24">
                        <a-form-item label="下午结束交易时间（例：15:00）">
                            <a-input placeholder="请输入下午结束交易时间" v-decorator="['transPmEndhk', {}]" />
                        </a-form-item>
                    </a-col>
                </a-row> -->
        <p>指数买入比例（指数买入比例即指数买入的仓位,指数最多能够买的金额）</p>
        <a-row class="form-row" :gutter="48">
          <a-col :md="12" :lg="6" :sm="12">
            <a-form-item label="最大买入比例（例:0.8）">
              <a-input placeholder="请输入最大买入比例" v-decorator="['buyMaxPercent', {}]" />
            </a-form-item>
          </a-col>
        </a-row>
        <p>指数强平设置 （百分比均采用小数来表示,例:0.8表示80% 强制平仓线计算规则：可用资金 + （冻结保证金 * 强制平仓比例））</p>
        <a-row class="form-row" :gutter="48">
          <a-col :md="12" :lg="6" :sm="24">
            <a-form-item label="强平比例（例:0.8）">
              <a-input placeholder="请输入强平比例" v-decorator="['forceSellPercent', {}]" />
            </a-form-item>
          </a-col>
          <a-col :md="12" :lg="6" :sm="24">
            <a-form-item label="强制平仓提醒比例（例:0.5）">
              <a-input placeholder="请输入强制平仓提醒比例" v-decorator="['forceStopRemindRatio', {}]" />
            </a-form-item>
          </a-col>
        </a-row>
        <p> 指数涨跌停限制 （百分比均采用小数来表示,例:0.1表示10%）</p>
        <p>在买涨的情况下,当该指数涨幅达到涨停限制的时候,用户不能买入; 当该指数涨幅达到跌停限制的时候,用户不能卖出.</p>
        <p>在买跌的情况下,当该指数涨幅达到跌停限制的时候,用户不能买入; 当该指数涨幅达到涨停限制的时候,用户不能卖出.</p>
        <a-row class="form-row" :gutter="48">
          <a-col :md="12" :lg="6" :sm="24">
            <a-form-item label="涨停限制（例:0.1）">
              <a-input placeholder="请输入涨停限制" v-decorator="['riseLimit', {}]" />
            </a-form-item>
          </a-col>
          <a-col :md="12" :lg="6" :sm="24">
            <a-form-item label="跌停限制（例:0.1）">
              <a-input placeholder="请输入跌停限制" v-decorator="['downLimit', {}]" />
            </a-form-item>
          </a-col>
        </a-row>
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
import { admingetIndexSetting, siteindexupdate } from '@/api/risksetting'
import pick from 'lodash.pick'
export default {
    name: 'Sharessetting',
    data () {
        return {
            addUserform: this.$form.createForm(this),
            loading: false,
            fields: ['buyMaxPercent', 'forceSellPercent', 'transAmBegin', 'transAmEnd', 'transPmBegin', 'transPmEnd', 'transAmBeginUs', 'transAmEndUs', 'transPmBeginUs', 'transPmEndUs',
                'transAmBeginhk', 'transAmEndhk', 'transPmBeginhk', 'transPmEndhk', 'downLimit', 'riseLimit', 'forceStopRemindRatio'],
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
                    siteindexupdate(values).then(res => {
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
            admingetIndexSetting().then(res => {
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
