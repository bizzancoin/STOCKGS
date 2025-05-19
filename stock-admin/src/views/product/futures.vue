<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <div class="table-page-search-wrapper">
        <a-form layout="inline">
          <a-row :gutter="48">
            <a-col :md="12" :lg="6" :sm="24">
              <a-form-item label="首页显示状态">
                <a-select v-model="queryParam.homeShow" placeholder="请选择首页显示状态">
                  <a-select-option :value="''">全部</a-select-option>
                  <a-select-option :value="1">显示</a-select-option>
                  <a-select-option :value="0">不显示</a-select-option>
                </a-select>
              </a-form-item>
            </a-col>
            <a-col :md="12" :lg="6" :sm="24">
              <a-form-item label="列表显示状态">
                <a-select v-model="queryParam.listShow" placeholder="请选择列表显示状态">
                  <a-select-option :value="''">全部</a-select-option>
                  <a-select-option :value="1">显示</a-select-option>
                  <a-select-option :value="0">不显示</a-select-option>
                </a-select>
              </a-form-item>
            </a-col>
            <a-col :md="12" :lg="6" :sm="24">
              <a-form-item label="交易状态">
                <a-select v-model="queryParam.transState" placeholder="请选择交易状态">
                  <a-select-option :value="1">可交易</a-select-option>
                  <a-select-option :value="0">不可交易</a-select-option>
                </a-select>
              </a-form-item>
            </a-col>
            <a-col :md="12" :lg="6" :sm="24">
              <a-form-item label="期货代码">
                <a-input v-model="queryParam.fuCode" style="width: 100%" placeholder="请输入期货代码" />
              </a-form-item>
            </a-col>
          </a-row>
          <a-row :gutter="48">
            <a-col :md="12" :lg="6" :sm="24">
              <a-form-item label="期货名称">
                <a-input v-model="queryParam.fuName" style="width: 100%" placeholder="请输入期货名称" />
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
                    @click="queryParam.pageNum = 1, getlist()">查询
                  </a-button>
                  <a-button
                    type="primary"
                    icon="plus"
                    style="margin-left: 8px"
                    @click="$refs.addfuturesdialog.addUserdialog = true">
                    添加期货产品</a-button>
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
        rowKey="id"
        :scroll="{ x: 2000 }">
        <span slot="homeShow" slot-scope="text,record">
          <template>
            <div>
              <a-tag :color="record.homeShow == 1 ? 'green' : 'red'">
                {{ record.homeShow == 1 ? '显示' : '隐藏' }}
              </a-tag>
            </div>
          </template>
        </span>

        <span slot="listShow" slot-scope="text,record">
          <template>
            <div>
              <a-tag :color="record.listShow == 1 ? 'green' : 'red'">
                {{ record.listShow == 1 ? '显示' : '隐藏' }}
              </a-tag>
            </div>
          </template>
        </span>

        <span slot="transState" slot-scope="text,record">
          <template>
            <div>
              <a-tag :color="record.transState == 1 ? 'green' : 'red'">
                {{ record.transState == 1 ? '可交易' : '不可交易' }}
              </a-tag>
            </div>
          </template>
        </span>

        <span slot="now_price" slot-scope="text,record">
          <template>
            <div>
              <span>
                {{ Number(record.now_price).toFixed(2) }}{{ record.coinCode }}
              </span>
              <span>
                ≈ {{ (record.coinRate * record.now_price).toFixed(2) }}CNY
              </span>
            </div>
          </template>
        </span>

        <span slot="floatRate" slot-scope="text,record">
          <template>
            <div>
              <P v-if="record.last_close > 0">
                {{ ((record.now_price - record.last_close) / record.last_close * 100).toFixed(2) }}%
              </P>
              <p v-else>0</p>
            </div>
          </template>
        </span>

        <span slot="transAmBegin" slot-scope="text,record">
          <template>
            <div>
              <P>
                上午：{{ record.transAmBegin }}点~{{ record.transAmEnd }}点
              </P>
              <p>
                下午：{{ record.transPmBegin }}点~{{ record.transPmEnd }}点
              </p>
            </div>
          </template>
        </span>

        <template slot="action" slot-scope="text,record">
          <a slot="action" href="javascript:;" @click="$refs.editfuturesdialog.getEditorder(record)">修改期货</a>
        </template>
      </a-table>
    </a-card>
    <addfuturesdialog ref="addfuturesdialog" :getinit="getinit" :coinlist="coinlist"></addfuturesdialog>
    <editfuturesdialog ref="editfuturesdialog" :getlist="getlist" :coinlist="coinlist"></editfuturesdialog>
  </page-header-wrapper>
</template>
<script>
import { futureslist, coingetSelectCoin } from '@/api/product'
import addfuturesdialog from './components/addfuturesdialog'
import editfuturesdialog from './components/editfuturesdialog'
import moment from 'moment'
export default {
    components: {
        addfuturesdialog,
        editfuturesdialog
    },
    name: 'Futures',
    data () {
        return {
            columns: [
                {
                    title: '期货名称 / 期货代码',
                    dataIndex: 'futuresName',
                    align: 'center',
                    width: 200,
                    customRender: (text, row, index) => {
                        return `${row.futuresName}（${row.futuresCode}）`
                    }
                },
                {
                    title: '首页显示状态',
                    dataIndex: 'homeShow',
                    align: 'center',
                    scopedSlots: { customRender: 'homeShow' }
                },
                {
                    title: '列表显示状态',
                    dataIndex: 'listShow',
                    align: 'center',
                    scopedSlots: { customRender: 'listShow' }
                },
                {
                    title: '交易状态',
                    dataIndex: 'transState',
                    align: 'center',
                    scopedSlots: { customRender: 'transState' }
                },
                {
                    title: '最新价',
                    dataIndex: 'now_price',
                    align: 'center',
                    scopedSlots: { customRender: 'now_price' }
                },
                {
                    title: '涨跌幅',
                    dataIndex: 'floatRate',
                    align: 'center',
                    scopedSlots: { customRender: 'floatRate' }
                },
                {
                    title: '每手保证金',
                    dataIndex: 'depositAmt',
                    align: 'center',
                    customRender: (text, row, index) => {
                        return row.depositAmt + row.coinCode
                    }
                },
                {
                    title: '每标准手',
                    dataIndex: 'futuresStandard',
                    align: 'center',
                    customRender: (text, row, index) => {
                        return row.futuresStandard + row.futuresUnit
                    }
                },
                {
                    title: '手续费（手）',
                    dataIndex: 'transFee',
                    align: 'center',
                    customRender: (text, row, index) => {
                        return row.transFee + row.coinCode
                    }
                },
                {
                    title: '汇率',
                    dataIndex: 'coinCode',
                    align: 'center',
                    customRender: (text, row, index) => {
                        return `1${row.coinCode} ≈ ${row.coinRate} CNY`
                    }
                },
                {
                    title: '交易时间',
                    dataIndex: 'transAmBegin',
                    align: 'center',
                    width: 180,
                    scopedSlots: { customRender: 'transAmBegin' }
                },
                {
                    title: '买入范围',
                    dataIndex: 'minNum',
                    align: 'center',
                    width: 150,
                    customRender: (text, row, index) => {
                        return `${row.minNum}手~${row.maxNum}手`
                    }
                },
                {
                    title: '添加时间',
                    dataIndex: 'addTime',
                    align: 'center',
                    width: 180,
                    customRender: (text, row, index) => {
                        return text ? moment(text).format('YYYY-MM-DD HH:mm:ss') : ''
                    }
                },
                {
                    title: '操作',
                    key: 'action',
                    align: 'center',
                    fixed: 'right',
                    width: 150,
                    scopedSlots: { customRender: 'action' }
                }
            ],
            // 表头
            pagination: {
                total: 0,
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
                fuCode: '',
                fuName: '',
                homeShow: '',
                listShow: '',
                transState: undefined
            },
            datalist: [],
            coinlist: []
        }
    },
    created () {
        this.getlist()
        this.getcoin()
    },
    methods: {
        getcoin () {
            coingetSelectCoin().then(res => {
                this.coinlist = res.data
            })
        },
        getinit () {
            this.queryParam = {
                pageNum: 1,
                pageSize: 10,
                fuCode: '',
                fuName: '',
                homeShow: '',
                listShow: '',
                transState: undefined
            }
            this.getlist()
        },
        getqueryParam () {
            this.queryParam = {
                pageNum: 1,
                pageSize: 10,
                fuCode: '',
                fuName: '',
                homeShow: '',
                listShow: '',
                transState: undefined
            }
        },
        getlist () {
            this.loading = true
            futureslist(this.queryParam).then(res => {
                this.datalist = res.data.list
                this.pagination.total = res.data.total
                this.loading = false
            })
        },
        onPageChange (page, pageSize) {
            this.queryParam.pageNum = page
            this.getlist()
        },
        onSizeChange (current, pageSize) {
            this.queryParam.pageNum = current
            this.queryParam.pageSize = pageSize
            this.getlist()
        }
    }
}
</script>
<style scoped>
.greens {
    color: #52c41a;
}
.reds {
    color: #f5222d;
}
</style>
