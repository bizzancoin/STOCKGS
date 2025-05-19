<template>
  <page-header-wrapper>
    <a-form :form="addUserform" class="form" ref="addUserform">
      <a-card class="card" :bordered="false" :loading="loading">
        <a-row class="form-row" :gutter="48">
          <a-col :md="12" :lg="6" :sm="24">
            <a-form-item label="选择用户">
              <a-input-search
                placeholder="输入用户id查询用户信息"
                enter-button
                @search="getUsersearch"
                v-decorator="['userId', { rules: [{ required: true, message: '请输入用户ID', }] }]" />
            </a-form-item>
          </a-col>
        </a-row>
        <a-row class="form-row" :gutter="48">
          <a-col :md="24" :lg="24" :sm="24">
            <div>
              <a-descriptions bordered :column="{ xxl: 4, xl: 3, lg: 3, md: 3, sm: 2, xs: 1 }">
                <a-descriptions-item label="用户名称：">
                  {{ userInfo.realName }}
                </a-descriptions-item>
                <a-descriptions-item label="所属代理：">
                  {{ userInfo.agentName }}
                </a-descriptions-item>
                <a-descriptions-item label="账号类型：">
                  {{ userInfo.accountType == 1 ? '模拟用户' : userInfo.accountType == 0 ? '实盘用户' : '' }}
                </a-descriptions-item>
                <a-descriptions-item label="手机号码：">
                  {{ userInfo.phone }}
                </a-descriptions-item>
                <a-descriptions-item label="总资金：">
                  {{ userInfo.userAmt }}
                </a-descriptions-item>
                <a-descriptions-item label="可用资金：">
                  {{ userInfo.enableAmt }}
                </a-descriptions-item>
              </a-descriptions>
            </div>
          </a-col>
        </a-row>
        <a-row class="form-row" :gutter="48" style="margin-top:20px;">
          <a-col :md="12" :lg="6" :sm="24">
            <a-form-item label="选择股票">
              <a-input-search
                placeholder="输入股票代码查询股票信息"
                enter-button
                @search="getstockdetail"
                v-decorator="['stockCode', { rules: [{ required: true, message: '输入股票代码查询股票信息', }] }]" />
            </a-form-item>
          </a-col>
        </a-row>
        <a-row class="form-row" :gutter="48">
          <a-col :md="24" :lg="24" :sm="24">
            <div>
              <a-descriptions bordered :column="{ xxl: 4, xl: 3, lg: 3, md: 3, sm: 2, xs: 1 }">
                <a-descriptions-item label="股票名字：">
                  {{ stockInfo.name }}
                </a-descriptions-item>
                <a-descriptions-item label="股票代码：">
                  {{ stockInfo.code }}
                </a-descriptions-item>
                <a-descriptions-item label="股票现价：">
                  <span :class="stockInfo.hcrate < 0 ? 'greens' : 'reds'">{{ stockInfo.nowPrice
                  }}</span>
                </a-descriptions-item>
                <a-descriptions-item label="涨跌：">
                  <span :class="stockInfo.hcrate < 0 ? 'greens' : 'reds'">{{ stockInfo.hcrate
                  }}</span>
                </a-descriptions-item>
              </a-descriptions>
            </div>
          </a-col>
        </a-row>
        <a-row class="form-row" :gutter="48" style="margin-top:20px;">
          <a-col :md="12" :lg="6" :sm="24">
            <a-form-item label="买入时间">
              <a-date-picker
                show-time
                style="width:100%"
                @change="onChangeRangeDate"
                v-decorator="['buyTime', { rules: [{ required: true, message: '请填写买入时间', }] }]"
                format="YYYY-MM-DD HH:mm:ss">
              </a-date-picker>
            </a-form-item>
          </a-col>
          <a-col :md="12" :lg="6" :sm="24">
            <a-form-item label="买入价格">
              <a-input
                placeholder="输入所选择时间点对应的价格"
                v-decorator="['buyPrice', { rules: [{ required: true, message: '请填写买入价格', }] }]" />
            </a-form-item>
          </a-col>
          <a-col :md="12" :lg="6" :sm="24">
            <a-form-item label="杠杆倍数">
              <a-input
                placeholder="输入买入杠杆倍数"
                v-decorator="['lever', { rules: [{ required: true, message: '输入买入杠杆倍数', }] }]" />
            </a-form-item>
          </a-col>
          <a-col :md="12" :lg="6" :sm="24">
            <a-form-item label="买入方向">
              <a-select
                placeholder="请选择买入方向"
                v-decorator="['buyType', { rules: [{ required: true, message: '请选择买入方向', }] }]">
                <a-select-option :value="0">买涨</a-select-option>
                <a-select-option :value="1">买跌</a-select-option>
              </a-select>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row class="form-row" :gutter="48" style="margin-top:20px;">
          <a-col :md="12" :lg="6" :sm="24">
            <a-form-item label="买入数量">
              <a-input-number
                placeholder="请输入买入数量"
                :min="details.buyMinNum"
                :max="details.buyMaxNum"
                style="width:100%"
                v-decorator="['buyNum', { rules: [{ required: true, message: '请输入买入数量', }] }]" />
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
import { admingetSetting } from '@/api/risksetting'
import { userdetail, stockgetSingleStock, positioncreate } from '@/api/position'
import pick from 'lodash.pick'
export default {
    name: 'Sharessetting',
    data () {
        return {
            addUserform: this.$form.createForm(this),
            loading: false,
            labelCol: {
                xs: { span: 10 },
                sm: { span: 10 },
                md: { span: 7 }
            },
            wrapperCol: {
                xs: { span: 14 },
                sm: { span: 14 },
                md: { span: 16 }
            },
            addUserDialogloading: false,
            details: {},
            userInfo: {},
            stockInfo: {}
        }
    },
    mounted () {
        this.getdetail()
    },
    methods: {
        getdetail () {
            var that = this
            this.loading = true
            admingetSetting().then(res => {
                this.details = res.data
                setTimeout(() => {
                    that.loading = false
                }, 500)
            })
        },
        getUsersearch () {
            userdetail({ userId: this.addUserform.getFieldValue('userId') }).then(res => {
                if (res.status === 0) {
                    if (res.data) {
                        this.userInfo = res.data
                    } else {
                        this.$message.error({ content: '没有该用户!' })
                        this.userInfo = ''
                    }
                } else {
                    this.$message.error({ content: res.msg })
                    this.userInfo = ''
                }
            })
        },
        getstockdetail () {
            stockgetSingleStock({ code: this.addUserform.getFieldValue('stockCode') }).then(res => {
                if (res.status === 0) {
                    this.stockInfo = res.data.stock
                } else {
                    this.$message.error({ content: res.msg })
                    this.stockInfo = ''
                }
            })
        },
        onChangeRangeDate (value, dateString) {
            this.buyTime = dateString
            // this.addUserform.setFieldsValue(({buyTime: dateString }))
        },
        OkaddUserdialog () {
            const form = this.$refs.addUserform.form
            form.validateFields((errors, values) => {
                if (!errors) {
                    this.addUserDialogloading = true
                    values.buyTime = this.buyTime
                    positioncreate(values).then(res => {
                        if (res.status == 0) {
                            this.$message.success({ content: '生成模拟持仓成功', duration: 2 })
                            this.getdetail()
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
.greens {
    color: #52c41a;
}

.reds {
    color: #f5222d;
}

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
