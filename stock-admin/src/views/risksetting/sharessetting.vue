<template>
  <page-header-wrapper>
    <a-form :form="addUserform" class="form" ref="addUserform">
      <a-card class="card" title="时间设置" :bordered="false" :loading="loading">
        <span slot="extra">填写规则请按照括号中的示例填写，":"统一为英文中的字符，提现时间为24小时制，请填写整数</span>
        <a-row class="form-row" :gutter="48">
          <a-col :md="12" :lg="6" :sm="12">
            <a-form-item label="上午开始交易时间（例：9:30）">
              <a-input
                placeholder="请输入上午开始交易时间"
                v-decorator="['transAmBegin', { rules: [{ required: true, message: '请输入上午开始交易时间', }] }]" />
            </a-form-item>
          </a-col>
          <a-col :md="12" :lg="6" :sm="12">
            <a-form-item label="上午结束交易时间（例：10:30）">
              <a-input
                placeholder="请输入上午结束交易时间"
                v-decorator="['transAmEnd', { rules: [{ required: true, message: '请输入上午结束交易时间', }] }]" />
            </a-form-item>
          </a-col>
          <a-col :md="12" :lg="6" :sm="12">
            <a-form-item label="下午开始交易时间（例：13:30）">
              <a-input
                placeholder="请输入下午开始交易时间"
                v-decorator="['transPmBegin', { rules: [{ required: true, message: '请输入下午开始交易时间', }] }]" />
            </a-form-item>
          </a-col>
          <a-col :md="12" :lg="6" :sm="12">
            <a-form-item label="下午结束交易时间（例：15:00）">
              <a-input
                placeholder="请输入下午结束交易时间"
                v-decorator="['transPmEnd', { rules: [{ required: true, message: '请输入下午结束交易时间', }] }]" />
            </a-form-item>
          </a-col>
        </a-row>
        <!-- <a-row class="form-row" :gutter="48">
                    <a-col :md="12" :lg="6" :sm="12">
                        <a-form-item label="美股上午开始交易时间（例：9:30）">
                            <a-input placeholder="请输入上午开始交易时间"
                                v-decorator="['transAmBeginUs', { rules: [{ required: true, message: '请输入上午开始交易时间', }] }]" />
                        </a-form-item>
                    </a-col>
                    <a-col :md="12" :lg="6" :sm="12">
                        <a-form-item label="美股上午结束交易时间（例：10:30）">
                            <a-input placeholder="请输入上午结束交易时间"
                                v-decorator="['transAmEndUs', { rules: [{ required: true, message: '请输入上午结束交易时间', }] }]" />
                        </a-form-item>
                    </a-col>
                    <a-col :md="12" :lg="6" :sm="12">
                        <a-form-item label="美股下午开始交易时间（例：13:30）">
                            <a-input placeholder="请输入下午开始交易时间"
                                v-decorator="['transPmBeginUs', { rules: [{ required: true, message: '请输入下午开始交易时间', }] }]" />
                        </a-form-item>
                    </a-col>
                    <a-col :md="12" :lg="6" :sm="12">
                        <a-form-item label="美股下午结束交易时间（例：15:00）">
                            <a-input placeholder="请输入下午结束交易时间"
                                v-decorator="['transPmEndUs', { rules: [{ required: true, message: '请输入下午结束交易时间', }] }]" />
                        </a-form-item>
                    </a-col>
                </a-row>
                <a-row class="form-row" :gutter="48">
                    <a-col :md="12" :lg="6" :sm="12">
                        <a-form-item label="港股上午开始交易时间（例：9:30）">
                            <a-input placeholder="请输入上午开始交易时间"
                                v-decorator="['transAmBeginhk', { rules: [{ required: true, message: '请输入上午开始交易时间', }] }]" />
                        </a-form-item>
                    </a-col>
                    <a-col :md="12" :lg="6" :sm="12">
                        <a-form-item label="港股上午结束交易时间（例：10:30）">
                            <a-input placeholder="请输入上午结束交易时间"
                                v-decorator="['transAmEndhk', { rules: [{ required: true, message: '请输入上午结束交易时间', }] }]" />
                        </a-form-item>
                    </a-col>
                    <a-col :md="12" :lg="6" :sm="12">
                        <a-form-item label="港股下午开始交易时间（例：13:30）">
                            <a-input placeholder="请输入下午开始交易时间"
                                v-decorator="['transPmBeginhk', { rules: [{ required: true, message: '请输入下午开始交易时间', }] }]" />
                        </a-form-item>
                    </a-col>
                    <a-col :md="12" :lg="6" :sm="12">
                        <a-form-item label="港股下午结束交易时间（例：15:00）">
                            <a-input placeholder="请输入下午结束交易时间"
                                v-decorator="['transPmEndhk', { rules: [{ required: true, message: '请输入下午结束交易时间', }] }]" />
                        </a-form-item>
                    </a-col>
                </a-row> -->
        <a-row class="form-row" :gutter="48">
          <a-col :md="12" :lg="6" :sm="12">
            <a-form-item label="提现开始时间（例：9）">
              <a-input
                placeholder="请输入提现开始时间"
                v-decorator="['withTimeBegin', { rules: [{ required: true, message: '请输入提现开始时间', }] }]" />
            </a-form-item>
          </a-col>
          <a-col :md="12" :lg="6" :sm="12">
            <a-form-item label="提现结束时间（例：18）">
              <a-input
                placeholder="请输入提现结束时间"
                v-decorator="['withTimeEnd', { rules: [{ required: true, message: '请输入提现结束时间', }] }]" />
            </a-form-item>
          </a-col>
        </a-row>
      </a-card>
      <a-card class="card" title="费用设置" :bordered="false" :loading="loading">
        <span slot="extra">请按照括号中的示例填写</span>
        <a-row class="form-row" :gutter="48">
          <a-col :md="12" :lg="6" :sm="12">
            <a-form-item label="买入手续费（例:0.001）">
              <a-input
                placeholder="请输入买入手续费"
                v-decorator="['buyFee', { rules: [{ required: true, message: '请输入买入手续费', }] }]" />
            </a-form-item>
          </a-col>
          <a-col :md="12" :lg="6" :sm="12">
            <a-form-item label="卖出手续费（例:0.001）">
              <a-input
                placeholder="请输入卖出手续费"
                v-decorator="['sellFee', { rules: [{ required: true, message: '请输入卖出手续费', }] }]" />
            </a-form-item>
          </a-col>
          <a-col :md="12" :lg="6" :sm="12">
            <a-form-item label="留仓费（例:0.001）">
              <a-input
                placeholder="请输入留仓费"
                v-decorator="['stayFee', { rules: [{ required: true, message: '请输入留仓费', }] }]" />
            </a-form-item>
          </a-col>
          <a-col :md="12" :lg="6" :sm="12">
            <a-form-item label="印花税（例:0.001）">
              <a-input
                placeholder="请输入印花税"
                v-decorator="['dutyFee', { rules: [{ required: true, message: '请输入印花税', }] }]" />
            </a-form-item>
          </a-col>
        </a-row>
      </a-card>
      <a-card class="card" title="购买设置" :bordered="false" :loading="loading">
        <span slot="extra">请按照括号中的示例填写</span>
        <a-row class="form-row" :gutter="48">
          <a-col :md="12" :lg="6" :sm="12">
            <a-form-item label="最小购买金额（例:1000）">
              <a-input
                placeholder="请输入最小购买金额"
                v-decorator="['buyMinAmt', { rules: [{ required: true, message: '请输入最小购买金额', }] }]" />
            </a-form-item>
          </a-col>
          <a-col :md="12" :lg="6" :sm="12">
            <a-form-item label="最大买入比例（例:0.8）">
              <a-input
                placeholder="请输入最大买入比例"
                v-decorator="['buyMaxAmtPercent', { rules: [{ required: true, message: '请输入最大买入比例', }] }]" />
            </a-form-item>
          </a-col>
          <a-col :md="12" :lg="6" :sm="12">
            <a-form-item label="最小购买股数（例:5000）">
              <a-input
                placeholder="请输入最小购买股数"
                v-decorator="['buyMinNum', { rules: [{ required: true, message: '请输入最小购买股数', }] }]" />
            </a-form-item>
          </a-col>
          <a-col :md="12" :lg="6" :sm="12">
            <a-form-item label="最大买入股数（例:1000000）">
              <a-input
                placeholder="请输入最大买入股数"
                v-decorator="['buyMaxNum', { rules: [{ required: true, message: '请输入最大买入股数', }] }]" />
            </a-form-item>
          </a-col>
        </a-row>
        <a-row class="form-row" :gutter="48">
          <a-col :md="12" :lg="6" :sm="12">
            <a-form-item label="杠杆倍数（例:100/50/30）">
              <a-input
                placeholder="请输入杠杆倍数"
                v-decorator="['siteLever', { rules: [{ required: true, message: '请输入杠杆倍数', }] }]" />
            </a-form-item>
          </a-col>
          <a-col :md="12" :lg="12" :sm="12">
            <a-form-item label="买入多长时间内不能平仓/分钟（例:30）">
              <a-input-number
                placeholder="请输入时间"
                style="width: 100%"
                v-decorator="['cantSellTimes', { rules: [{ required: true, message: '请输入时间', }] }]" />
            </a-form-item>
          </a-col>
          <a-col :md="12" :lg="6" :sm="12">
            <a-form-item label="VIP抢筹资金限制">
              <a-input
                placeholder="请输入VIP抢筹资金数量"
                v-decorator="['vipQcMaxAmt', { rules: [{ required: true, message: '请输入VIP抢筹资金数量', }] }]" />
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
        <a-row class="form-row" :gutter="48">
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
        </a-row>
        <a-row class="form-row" :gutter="48">
          <a-col :md="24" :lg="24" :sm="24">
            <a-form-item label="同一股票连续 x 天 内涨幅超过 y 不能买入(同一用户)">
              同一股票连续
              <a-input-number
                placeholder="请输入天数"
                style="width: 300px"
                v-decorator="['stockDays', { rules: [{ required: true, message: '请输入天数', }] }]" />
              天 内涨幅超过
              <a-input-number
                placeholder="请输入下单次数"
                style="width: 300px"
                v-decorator="['stockRate', { rules: [{ required: true, message: '请输入下单次数', }] }]" />
              次不能买入(同一用户)
            </a-form-item>
          </a-col>
        </a-row>
        <a-row class="form-row" :gutter="48">
          <a-col :md="12" :lg="6" :sm="12">
            <a-form-item label="A股超过多少个点不能买入（例:7)">
              <a-input-number
                placeholder="请输入点数"
                style="width: 300px"
                v-decorator="['creaseMaxPercent', { rules: [{ required: true, message: '请输入点数', }] }]" />
            </a-form-item>
          </a-col>
          <a-col :md="12" :lg="6" :sm="12">
            <a-form-item label="科创板超过多少个点不能买入（例:7)">
              <a-input-number
                placeholder="请输入点数"
                style="width: 300px"
                v-decorator="['kcCreaseMaxPercent', { rules: [{ required: true, message: '请输入点数', }] }]" />
            </a-form-item>
          </a-col>
        </a-row>
      </a-card>

      <a-card class="card" title="强制平仓设置" :bordered="false" :loading="loading">
        <span slot="extra">请按照括号中的示例填写,比例均采用小数来表示&nbsp;&nbsp;&nbsp;&nbsp;强制平仓线计算规则：可用资金 + （冻结保证金 *
          强制平仓比例）</span>
        <a-row class="form-row" :gutter="48">
          <a-col :md="12" :lg="6" :sm="12">
            <a-form-item label="强制平仓比例（例:0.7）">
              <a-input
                placeholder="请输入强制平仓比例"
                v-decorator="['forceStopPercent', { rules: [{ required: true, message: '请输入强制平仓比例', }] }]" />
            </a-form-item>
          </a-col>
          <a-col :md="12" :lg="6" :sm="12">
            <a-form-item label="连续涨停强制平仓（例:0.2）">
              <a-input
                placeholder="请输入连续涨停强制平仓比例"
                v-decorator="['hightAndLow', { rules: [{ required: true, message: '请输入连续涨停强制平仓比例', }] }]" />
            </a-form-item>
          </a-col>
          <a-col :md="12" :lg="6" :sm="12">
            <a-form-item label="最大留仓天数（例:15）">
              <a-input
                placeholder="请输入最大留仓天数"
                v-decorator="['stayMaxDays', { rules: [{ required: true, message: '请输入最大留仓天数', }] }]" />
            </a-form-item>
          </a-col>
          <a-col :md="12" :lg="6" :sm="12">
            <a-form-item label="强制平仓手续费（例:0.001）">
              <a-input
                placeholder="请输入强制平仓手续费"
                v-decorator="['forceStopFee', { rules: [{ required: true, message: '请输入强制平仓手续费', }] }]" />
            </a-form-item>
          </a-col>
        </a-row>
        <a-row class="form-row" :gutter="48">
          <a-col :md="12" :lg="6" :sm="12">
            <a-form-item label="强制平仓提醒比例（例:0.5）">
              <a-input
                placeholder="请输入强制平仓提醒比例"
                v-decorator="['forceStopRemindRatio', { rules: [{ required: true, message: '请输入强制平仓提醒比例', }] }]" />
            </a-form-item>
          </a-col>
        </a-row>
      </a-card>

      <a-card class="card" title="充值提现设置" :bordered="false" :loading="loading">
        <span slot="extra">请按照括号中的示例填写,比例均采用小数来表示</span>
        <a-row class="form-row" :gutter="48">
          <a-col :md="12" :lg="6" :sm="12">
            <a-form-item label="最小充值金额（例:1000）">
              <a-input
                placeholder="请输入最小充值金额"
                v-decorator="['chargeMinAmt', { rules: [{ required: true, message: '请输入最小充值金额', }] }]" />
            </a-form-item>
          </a-col>
          <a-col :md="12" :lg="6" :sm="12">
            <a-form-item label="最小提现金额（例:1000）">
              <a-input
                placeholder="请输入最小提现金额"
                v-decorator="['withMinAmt', { rules: [{ required: true, message: '请输入最小提现金额', }] }]" />
            </a-form-item>
          </a-col>
          <a-col :md="12" :lg="6" :sm="12">
            <a-form-item label="提现单笔手续费（例:5）">
              <a-input
                placeholder="请输入提现单笔手续费"
                v-decorator="['withFeeSingle', { rules: [{ required: true, message: '请输入提现单笔手续费', }] }]" />
            </a-form-item>
          </a-col>
          <a-col :md="12" :lg="6" :sm="12">
            <a-form-item label="提现手续费百分比（例:0.005）">
              <a-input
                placeholder="请输入提现手续费百分比"
                v-decorator="['withFeePercent', { rules: [{ required: true, message: '请输入提现手续费百分比', }] }]" />
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
import { admingetSetting, setupdate } from '@/api/risksetting'
import pick from 'lodash.pick'
export default {
    name: 'Sharessetting',
    data () {
        return {
            addUserform: this.$form.createForm(this),
            loading: false,
            fields: ['buyFee', 'sellFee', 'stayFee', 'dutyFee', 'stayMaxDays', 'buyMinAmt', 'chargeMinAmt', 'buyMinNum', 'forceStopFee', 'buyMaxAmtPercent', 'forceStopPercent', 'hightAndLow', 'withMinAmt', 'creaseMaxPercent', 'kcCreaseMaxPercent', 'buyMaxNum', 'cantSellTimes', 'buySameTimes', 'buySameNums', 'buyNumTimes', 'buyNumLots', 'stockDays', 'stockRate', 'withTimeBegin', 'withTimeEnd',
                'transAmBegin', 'transAmEnd', 'transPmBegin', 'transPmEnd',
                'transAmBeginUs', 'transAmEndUs', 'transPmBeginUs', 'transPmEndUs',
                'transAmBeginhk', 'transAmEndhk', 'transPmBeginhk', 'transPmEndhk',
                'withFeeSingle', 'withFeePercent', 'siteLever', 'forceStopRemindRatio', 'vipQcMaxAmt'],
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
                    setupdate(values).then(res => {
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
            admingetSetting().then(res => {
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
