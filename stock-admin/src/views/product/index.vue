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
              <a-form-item label="指数代码">
                <a-input v-model="queryParam.indexCode" style="width: 100%" placeholder="请输入指数代码" />
              </a-form-item>
            </a-col>
          </a-row>
          <a-row :gutter="48">
            <a-col :md="12" :lg="6" :sm="24">
              <a-form-item label="指数名称">
                <a-input v-model="queryParam.indexName" style="width: 100%" placeholder="请输入指数名称" />
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

                  <a-button type="primary" icon="plus" style="margin-left: 8px" @click="$refs.addindexdialog.addUserdialog = true">
                    添加指数产品</a-button>

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
        :scroll="{ x: 1800 }">
        <span slot="indexName" slot-scope="text,record">
          <template>
            <div>
              <span style="margin-right:10px">{{ record.indexName }}</span>
              <a-tag color="green">{{ record.indexCode }}
              </a-tag>
            </div>
          </template>
        </span>

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
        <span slot="currentPoint" slot-scope="text,record">
          <template>
            <div>
              <span :class="record.floatRate < 0 ? 'greens' : record.floatRate > 0 ? 'reds' : ''">{{
                Number(record.currentPoint).toFixed(2)
              }}
              </span>
            </div>
          </template>
        </span>
        <span slot="floatPoint" slot-scope="text,record">
          <template>
            <div>
              <span :class="record.floatRate < 0 ? 'greens' : record.floatRate > 0 ? 'reds' : ''">{{
                Number(record.floatPoint).toFixed(2)
              }}
              </span>
            </div>
          </template>
        </span>
        <span slot="floatRate" slot-scope="text,record">
          <template>
            <div>
              <span :class="record.floatRate < 0 ? 'greens' : record.floatRate > 0 ? 'reds' : ''">
                {{ record.floatRate }}%
              </span>
            </div>
          </template>
        </span>

        <template slot="action" slot-scope="text,record">
          <a slot="action" href="javascript:;" @click="$refs.editindexdialog.getEditorder(record)">修改指数</a>
        </template>
      </a-table>
    </a-card>
    <addindexdialog ref="addindexdialog" :getinit="getinit"></addindexdialog>
    <editindexdialog ref="editindexdialog" :getinit="geteditinit"></editindexdialog>
  </page-header-wrapper>
</template>
<script>
import { indexlist } from '@/api/product'
import addindexdialog from './components/addindexdialog'
import editindexdialog from './components/editindexdialog'
import moment from 'moment'
export default {
    name: 'Index',
    components: {
        addindexdialog,
        editindexdialog
    },
    data () {
        return {
            columns: [
                {
                    title: '指数名称 / 指数代码',
                    dataIndex: 'indexName',
                    align: 'center',
                    width: 200,
                    // fixed: 'left',
                    scopedSlots: { customRender: 'indexName' }
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
                    title: '当前点数',
                    dataIndex: 'currentPoint',
                    align: 'center',
                    scopedSlots: { customRender: 'currentPoint' }
                },
                {
                    title: '涨跌点数',
                    dataIndex: 'floatPoint',
                    align: 'center',
                    scopedSlots: { customRender: 'floatPoint' }
                },
                {
                    title: '涨跌幅',
                    dataIndex: 'floatRate',
                    align: 'center',
                    scopedSlots: { customRender: 'floatRate' }
                },
                {
                    title: '买入范围',
                    dataIndex: 'isShow',
                    align: 'center',
                    customRender: (text, row, index) => {
                        return `${row.minNum}手~${row.maxNum}手`
                    }
                },
                {
                    title: '手续费（手）',
                    dataIndex: 'transFee',
                    align: 'center'
                },
                {
                    title: '每手保证金',
                    dataIndex: 'depositAmt',
                    align: 'center'
                },
                {
                    title: '点浮动价',
                    dataIndex: 'eachPoint',
                    align: 'center'
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
                indexCode: '',
                indexName: '',
                homeShow: '',
                listShow: '',
                transState: undefined
            },
            datalist: []
        }
    },
    created () {
        this.getlist()
    },
    methods: {
        getinit () {
            this.queryParam = {
                pageNum: 1,
                pageSize: 10,
                indexCode: '',
                indexName: '',
                homeShow: '',
                listShow: '',
                transState: undefined
            }
            this.pagination.current = 1
            this.getlist()
        },
        geteditinit () {
            this.getlist()
        },
        getqueryParam () {
            this.queryParam = {
                pageNum: 1,
                pageSize: 10,
                indexCode: '',
                indexName: '',
                homeShow: '',
                listShow: '',
                transState: undefined
            }
        },
        getlist () {
            this.loading = true
            this.queryParam.stockPlate == 'A股' ? this.queryParam.stockPlate = '' : this.queryParam.stockPlate
            indexlist(this.queryParam).then(res => {
                this.datalist = res.data.list
                this.pagination.total = res.data.total
                this.queryParam.stockPlate == '' ? this.queryParam.stockPlate = 'A股' : this.queryParam.stockPlate
                this.loading = false
            })
        },
        onPageChange (page, pageSize) {
            this.queryParam.pageNum = page
            this.pagination.current = page
            this.getlist()
        },
        onSizeChange (current, pageSize) {
            this.pagination.current = page
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
