<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <div class="table-page-search-wrapper">
        <a-form layout="inline">
          <a-row :gutter="48">
            <a-col :md="12" :lg="6" :sm="24">
              <a-form-item label="股票类型">
                <a-select v-model="queryParam.stockPlate" placeholder="请选择股票类型">
                  <a-select-option :value="'A股'">股票</a-select-option>
                  <a-select-option :value="'科创'">科创</a-select-option>
                </a-select>
              </a-form-item>
            </a-col>
            <a-col :md="12" :lg="6" :sm="24">
              <a-form-item label="沪深股">
                <a-select v-model="queryParam.stockType" placeholder="请选择显示状态">
                  <a-select-option value="sh">沪股</a-select-option>
                  <a-select-option value="sz">深股</a-select-option>
                  <a-select-option value="bj">京股</a-select-option>
                  <a-select-option value="hk">港股</a-select-option>
                  <a-select-option value="us">美股</a-select-option>
                </a-select>
              </a-form-item>
            </a-col>
            <a-col :md="12" :lg="6" :sm="24">
              <a-form-item label="锁定状态">
                <a-select v-model="queryParam.lockState" placeholder="请选择锁定状态">
                  <a-select-option :value="0">正常</a-select-option>
                  <a-select-option :value="1">锁定</a-select-option>
                </a-select>
              </a-form-item>
            </a-col>

            <a-col :md="12" :lg="6" :sm="24">
              <a-form-item label="显示状态">
                <a-select v-model="queryParam.showState" placeholder="请选择显示状态">
                  <a-select-option :value="0">显示</a-select-option>
                  <a-select-option :value="1">隐藏</a-select-option>
                </a-select>
              </a-form-item>
            </a-col>
          </a-row>
          <a-row :gutter="48">
            <a-col :md="12" :lg="6" :sm="24">
              <a-form-item label="股票代码">
                <a-input v-model="queryParam.code" style="width: 100%" placeholder="请输入股票代码" />
              </a-form-item>
            </a-col>
            <a-col :md="12" :lg="6" :sm="24">
              <a-form-item label="股票名称">
                <a-input v-model="queryParam.name" style="width: 100%" placeholder="请输入股票名称" />
              </a-form-item>
            </a-col>
            <a-col :md="12" :lg="6" :sm="24">
              <a-form-item>
                <span class="table-page-search-submitButtons">
                  <a-button @click="getqueryParam" icon="redo">
                    重置</a-button>
                  <a-button
                    type="primary"
                    icon="search"
                    style="margin-left: 8px"
                    @click="queryParam.pageNum = 1, pagination.current = 1,getlist()">查询
                  </a-button>

                  <a-button
                    type="primary"
                    icon="plus"
                    style="margin-left: 8px"
                    @click="addUserdialog = true">
                    添加股票</a-button>

                </span>
              </a-form-item>
            </a-col>
          </a-row>
        </a-form>
      </div>
    </a-card>

    <a-card :bordered="false">
      <a-table
        bordered
        :loading="loading"
        :pagination="pagination"
        :columns="columns"
        :data-source="datalist"
        rowKey="id">
        <span slot="stockName" slot-scope="text,record">
          <template>
            <div>
              <span style="margin-right:10px">{{ record.stockName }}</span>
              <a-tag color="green">{{ record.stockCode }}
              </a-tag>
            </div>
          </template>
        </span>
        <span slot="stockType" slot-scope="text,record">
          <template>
            <div>
              <a-tag color="red">{{ record.stockType == 'sz' ? '深股' : record.stockType == 'sh' ? '沪股' : record.stockType == 'bj' ? '京股' : record.stockType == 'hk' ? '港股' : record.stockType == 'us' ? '美股' : '' }}
              </a-tag>
            </div>
          </template>
        </span>
        <span slot="nowPrice" slot-scope="text,record">
          <template>
            <div>
              <a-tag :color="record.hcrate < 0 ? 'green' : record.hcrate > 0 ? 'red' : ''">{{
                Number(record.nowPrice).toFixed(2)
              }}
              </a-tag>
            </div>
          </template>
        </span>
        <span slot="hcrate" slot-scope="text,record">
          <template>
            <div>
              <a-tag :color="record.hcrate < 0 ? 'green' : record.hcrate > 0 ? 'red' : ''">
                {{ record.hcrate }}%
              </a-tag>
            </div>
          </template>
        </span>
        <span slot="day3Rate" slot-scope="text,record">
          <template>
            <div>
              <a-tag :color="record.day3Rate < 0 ? 'green' : record.day3Rate > 0 ? 'red' : ''">
                {{ record.day3Rate }}%
              </a-tag>
            </div>
          </template>
        </span>
        <span slot="spreadRate" slot-scope="text,record">
          <template>
            <div>
              <a-tag :color="record.spreadRate < 0 ? 'green' : record.spreadRate > 0 ? 'red' : ''">
                {{ record.spreadRate * 100 }}%
              </a-tag>
            </div>
          </template>
        </span>
        <span slot="isShow" slot-scope="text,record">
          <template>
            <div>
              <a-tag :color="record.isShow == 0 ? 'green' : record.isShow == 1 ? 'red' : ''">
                {{ record.isShow == 0 ? '显示' : '隐藏' }}
              </a-tag>
            </div>
          </template>
        </span>
        <span slot="isLock" slot-scope="text,record">
          <template>
            <div>
              <a-tag :color="record.isLock == 0 ? 'green' : record.isLock == 1 ? 'red' : ''">
                {{ record.isLock == 0 ? '正常' : '锁定' }}
              </a-tag>
            </div>
          </template>
        </span>

        <template slot="action" slot-scope="text,record">
          <a slot="action" href="javascript:;" @click="getisShow(record.id, record.isShow)">{{ record.isShow
            ==
            0 ? '隐藏股票' :
              '显示股票'
          }}</a>
          <a-divider type="vertical" />
          <a slot="action" href="javascript:;" @click="getisLock(record.id, record.isLock)">{{ record.isLock
            == 0 ? '锁定股票' :
              '解锁股票'
          }}</a>
          <a-divider type="vertical" />
          <a slot="action" href="javascript:;" @click="geteditStock(record)">{{ '修改股票' }}</a>
        </template>
      </a-table>
    </a-card>

    <a-modal
      title="添加股票"
      :width="500"
      :visible="addUserdialog"
      :confirmLoading="addUserDialogloading"
      @ok="OkaddUserdialog"
      @cancel="CanceladdUserdialog">
      <a-form :form="addUserform" ref="addUserform">
        <a-form-item label="股票名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="请输入股票名称"
            v-decorator="['stockName', { rules: [{ required: true, message: '请输入股票名称', }] }]" />
        </a-form-item>
        <a-form-item label="股票代码" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="请输入股票代码"
            v-decorator="['stockCode', { rules: [{ required: true, message: '请输入股票代码', }] }]" />
        </a-form-item>
        <a-form-item label="股票类型" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-select
            placeholder="请选择股票类型"
            v-decorator="['stockType', { rules: [{ required: true, message: '请选择股票类型', }] }]">
            <a-select-option value="sh">沪股</a-select-option>
            <a-select-option value="sz">深股</a-select-option>
            <a-select-option value="bj">京股</a-select-option>
            <a-select-option value="hk">港股</a-select-option>
            <a-select-option value="us">美股</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="科创板股票" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-select
            placeholder="请选择科创板股票"
            v-decorator="['stockPlate', { rules: [{ required: true, message: '请选择科创板股票', }] }]">
            <a-select-option value="A股">否</a-select-option>
            <a-select-option value="科创">是</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="锁定状态" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-select
            placeholder="请选择锁定状态"
            v-decorator="['isLock', { rules: [{ required: true, message: '请选择锁定状态', }] }]">
            <a-select-option value="0">未锁定</a-select-option>
            <a-select-option value="1">锁定</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="显示状态" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-select
            placeholder="请选择显示状态"
            v-decorator="['isShow', { rules: [{ required: true, message: '请选择显示状态', }] }]">
            <a-select-option value="0">显示</a-select-option>
            <a-select-option value="1">不显示</a-select-option>
          </a-select>
        </a-form-item>
      </a-form>
    </a-modal>

    <a-modal
      title="修改股票"
      :width="500"
      :visible="editStockdialog"
      :confirmLoading="editStockdialogloading"
      @ok="OkeditStockdialog"
      @cancel="CanceleditStockdialog">
      <a-form :form="editStockform" ref="editStockform">
        <a-form-item label="股票名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="请输入股票名称"
            v-decorator="['stockName', { rules: [{ required: true, message: '请输入股票名称', }] }]" />
        </a-form-item>
        <a-form-item label="点差费率" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="请输入点差费率"
            v-decorator="['spreadRate', { rules: [{ required: true, message: '请输入点差费率', }] }]" />
        </a-form-item>
      </a-form>
    </a-modal>
  </page-header-wrapper>
</template>
<script>
import { stocklist, updateShow, updateLock, stockadd, stockupdateStock } from '@/api/product'
import moment from 'moment'
import pick from 'lodash.pick'
export default {
    name: 'Shares',
    data () {
        return {
            columns: [
                {
                    title: '股票名称 / 股票代码',
                    dataIndex: 'stockName',
                    align: 'center',
                    scopedSlots: { customRender: 'stockName' }
                },
                {
                    title: '沪深京股',
                    dataIndex: 'stockType',
                    align: 'center',
                    scopedSlots: { customRender: 'stockType' }
                },
                {
                    title: '现价',
                    dataIndex: 'nowPrice',
                    align: 'center',
                    scopedSlots: { customRender: 'nowPrice' }
                },
                {
                    title: '涨跌幅',
                    dataIndex: 'hcrate',
                    align: 'center',
                    scopedSlots: { customRender: 'hcrate' }
                },
                {
                    title: '最近3天涨跌',
                    dataIndex: 'day3Rate',
                    align: 'center',
                    scopedSlots: { customRender: 'day3Rate' }
                },
                {
                    title: '点差费率',
                    dataIndex: 'spreadRate',
                    align: 'center',
                    scopedSlots: { customRender: 'spreadRate' }
                },
                {
                    title: '显示状态',
                    dataIndex: 'isShow',
                    align: 'center',
                    scopedSlots: { customRender: 'isShow' }
                },
                {
                    title: '股票状态',
                    dataIndex: 'isLock',
                    align: 'center',
                    scopedSlots: { customRender: 'isLock' }
                },
                {
                    title: '添加时间',
                    dataIndex: 'addTime',
                    align: 'center',
                    customRender: (text, row, index) => {
                        return text ? moment(text).format('YYYY-MM-DD HH:mm:ss') : ''
                    }
                },
                {
                    title: '操作',
                    key: 'action',
                    align: 'center',
                    scopedSlots: { customRender: 'action' }
                }
            ],
            // 表头
            pagination: {
                total: 0,
                current: 1,
                pageSize: 10, // 每页中显示10条数据
                showSizeChanger: true,
                pageSizeOptions: ['10', '20', '50', '100'], // 每页中显示的数据
                onShowSizeChange: (current, pageSize) => this.onSizeChange(current, pageSize), // 改变每页数量时更新显示
                onChange: (page, pageSize) => this.onPageChange(page, pageSize), // 点击页码事件
                showTotal: total => `共有 ${total} 条数据` // 分页中显示总的数据
            },
            loading: false,
            queryParam: {
                pageNum: 1,
                pageSize: 10,
                code: '',
                name: '',
                stockPlate: 'A股',
                stockType: undefined,
                showState: undefined,
                lockState: undefined
            },
            datalist: [],
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
            addUserDialogloading: false,

            editStockdialog: false,
            editStockdialogloading: false,
            editStockform: this.$form.createForm(this),
            fields: ['stockName', 'spreadRate'],
            currentid: ''
        }
    },
    created () {
        this.getlist()
    },
    methods: {
        geteditStock (val) {
            this.currentid = val.id
            this.editStockdialog = true
            this.fields.forEach(v => this.editStockform.getFieldDecorator(v))
            this.editStockform.setFieldsValue(pick(val, this.fields))
        },
        CanceleditStockdialog () {
            this.editStockdialog = false
            const form = this.$refs.editStockform.form
            form.resetFields()
        },
        OkeditStockdialog () {
            const form = this.$refs.editStockform.form
            form.validateFields((errors, values) => {
                if (!errors) {
                    this.editStockdialogloading = true
                    values.id = this.currentid
                    stockupdateStock(values).then(res => {
                        if (res.status == 0) {
                            this.editStockdialog = false
                            this.$message.success({ content: res.msg, duration: 2 })
                            form.resetFields()
                            this.getlist()
                        } else {
                            this.$message.error({ content: res.msg })
                        }
                        this.editStockdialogloading = false
                    })
                }
            })
        },
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
                    values.stockPlate == 'A股' ? values.stockPlate = '' : values.stockPlate
                    stockadd(values).then(res => {
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
        },
        getqueryParam () {
            this.queryParam = {
                pageNum: 1,
                pageSize: 10,
                code: '',
                name: '',
                stockPlate: 'A股',
                stockType: undefined,
                showState: undefined,
                lockState: undefined
            }
        },
        getinit () {
            this.getqueryParam()
            this.pagination.current = 1
            this.getlist()
        },
        getlist () {
            this.loading = true
            this.queryParam.stockPlate == 'A股' ? this.queryParam.stockPlate = '' : this.queryParam.stockPlate
            stocklist(this.queryParam).then(res => {
                this.datalist = res.data.list
                this.pagination.total = res.data.total
                this.queryParam.stockPlate == '' ? this.queryParam.stockPlate = 'A股' : this.queryParam.stockPlate
                this.loading = false
            })
        },
        // 股票的显示隐藏
        getisShow (idval, isShowval) {
            updateShow({ stockId: idval }).then(res => {
                if (res.status === 0) {
                    this.getlist()
                    if (isShowval == 0) {
                        this.$message.success({ content: '隐藏成功', duration: 2 })
                    } else {
                        this.$message.success({ content: '显示成功', duration: 2 })
                    }
                } else {
                    this.$message.error({ content: res.msg, duration: 2 })
                }
            })
        },
        // 股票的锁定解锁
        getisLock (idval, isLockval) {
            updateLock({ stockId: idval }).then(res => {
                if (res.status === 0) {
                    this.getlist()
                    if (isLockval == 0) {
                        this.$message.success({ content: '锁定成功', duration: 2 })
                    } else {
                        this.$message.success({ content: '解锁成功', duration: 2 })
                    }
                } else {
                    this.$message.error({ content: res.msg, duration: 2 })
                }
            })
        },
        onPageChange (page, pageSize) {
            this.queryParam.pageNum = page
            this.pagination.current = page
            this.getlist()
        },
        onSizeChange (current, pageSize) {
            this.queryParam.pageNum = current
            this.pagination.current = page
            this.queryParam.pageSize = pageSize
            this.getlist()
        }
    }
}
</script>
