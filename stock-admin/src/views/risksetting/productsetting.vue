<template>
  <page-header-wrapper>
    <a-form :form="addUserform" class="form" ref="addUserform">
      <a-card class="card" title="A股" :bordered="false" :loading="loading">
        <span slot="extra">状态为开启即表示用户可以进行该产品的交易</span>
        <a-row class="form-row" :gutter="48">
          <a-col :md="8" :lg="8" :sm="12">
            <a-form-item label="融资融券交易" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-switch v-decorator="['stockDisplay', { valuePropName: 'checked' }]">
                <a-icon slot="checkedChildren" type="check" />
                <a-icon slot="unCheckedChildren" type="close" />
              </a-switch>
            </a-form-item>
          </a-col>
          <a-col :md="8" :lg="8" :sm="12">
            <a-form-item label="科创板交易" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-switch v-decorator="['kcStockDisplay', { valuePropName: 'checked' }]">
                <a-icon slot="checkedChildren" type="check" />
                <a-icon slot="unCheckedChildren" type="close" />
              </a-switch>
            </a-form-item>
          </a-col>
          <a-col :md="8" :lg="8" :sm="12">
            <a-form-item label="指数交易" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-switch v-decorator="['indexDisplay', { valuePropName: 'checked' }]">
                <a-icon slot="checkedChildren" type="check" />
                <a-icon slot="unCheckedChildren" type="close" />
              </a-switch>
            </a-form-item>
          </a-col>
        </a-row>
      </a-card>
      <!-- <a-card class="card" title="期货" :bordered="false" :loading="loading"> -->
      <!-- <span slot="extra">状态为开启即表示用户可以进行该产品的交易</span>
                <a-row class="form-row" :gutter="48">
                    <a-col :md="8" :lg="8" :sm="12">
                        <a-form-item label="外盘期货" :labelCol="labelCol" :wrapperCol="wrapperCol">
                            <a-switch v-decorator="['futuresDisplay', { valuePropName: 'checked' }]">
                                <a-icon slot="checkedChildren" type="check" />
                                <a-icon slot="unCheckedChildren" type="close" />
                            </a-switch>
                        </a-form-item>
                    </a-col>
                </a-row> -->
      <!-- </a-card> -->
      <a-card class="card" title="用户实名认证" :bordered="false" :loading="loading">
        <span slot="extra">状态为开启即表示用户实名认证后可以进行交易，关闭则不需要实名认证就可以正常交易</span>
        <a-row class="form-row" :gutter="48">
          <a-col :md="8" :lg="8" :sm="12">
            <a-form-item label="实名认证" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-switch v-decorator="['realNameDisplay', { valuePropName: 'checked' }]">
                <a-icon slot="checkedChildren" type="check" />
                <a-icon slot="unCheckedChildren" type="close" />
              </a-switch>
            </a-form-item>
          </a-col>
        </a-row>
      </a-card>

      <!-- <a-card class="card" title="分仓配资" :bordered="false" :loading="loading">
                <span slot="extra">状态为开启即表示分仓配资板块全部可用，关闭则不需要分仓配资</span>
                <a-row class="form-row" :gutter="48">
                    <a-col :md="8" :lg="8" :sm="12">
                        <a-form-item label="分仓配资" :labelCol="labelCol" :wrapperCol="wrapperCol">
                            <a-switch v-decorator="['fundsDisplay', { valuePropName: 'checked' }]">
                                <a-icon slot="checkedChildren" type="check" />
                                <a-icon slot="unCheckedChildren" type="close" />
                            </a-switch>
                        </a-form-item>
                    </a-col>
                    <a-col :md="8" :lg="8" :sm="12">
                        <a-form-item label="续期审核" :labelCol="labelCol" :wrapperCol="wrapperCol">
                            <a-switch v-decorator="['delayDisplay', { valuePropName: 'checked' }]">
                                <a-icon slot="checkedChildren" type="check" />
                                <a-icon slot="unCheckedChildren" type="close" />
                            </a-switch>
                        </a-form-item>
                    </a-col>
                    <a-col :md="8" :lg="8" :sm="12">
                        <a-form-item label="扩大配资审核" :labelCol="labelCol" :wrapperCol="wrapperCol">
                            <a-switch v-decorator="['expandDisplay', { valuePropName: 'checked' }]">
                                <a-icon slot="checkedChildren" type="check" />
                                <a-icon slot="unCheckedChildren" type="close" />
                            </a-switch>
                        </a-form-item>
                    </a-col>
                </a-row>
                <a-row class="form-row" :gutter="48">
                    <a-col :md="8" :lg="8" :sm="12">
                        <a-form-item label="追加保证金审核" :labelCol="labelCol" :wrapperCol="wrapperCol">
                            <a-switch v-decorator="['marginDisplay', { valuePropName: 'checked' }]">
                                <a-icon slot="checkedChildren" type="check" />
                                <a-icon slot="unCheckedChildren" type="close" />
                            </a-switch>
                        </a-form-item>
                    </a-col>
                    <a-col :md="8" :lg="8" :sm="12">
                        <a-form-item label="终止操盘审核" :labelCol="labelCol" :wrapperCol="wrapperCol">
                            <a-switch v-decorator="['endDisplay', { valuePropName: 'checked' }]">
                                <a-icon slot="checkedChildren" type="check" />
                                <a-icon slot="unCheckedChildren" type="close" />
                            </a-switch>
                        </a-form-item>
                    </a-col>
                </a-row>
            </a-card> -->

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
import { getProductSetting, productupdate } from '@/api/risksetting'
import pick from 'lodash.pick'
export default {
    name: 'Productsetting',
    data () {
        return {
            addUserform: this.$form.createForm(this),
            loading: false,
            fields: ['stockDisplay', 'kcStockDisplay', 'indexDisplay', 'futuresDisplay', 'realNameDisplay', 'fundsDisplay', 'delayDisplay', 'expandDisplay', 'marginDisplay', 'endDisplay'],
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
                    productupdate(values).then(res => {
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
            getProductSetting().then(res => {
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
