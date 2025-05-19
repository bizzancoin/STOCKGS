<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <a-table
        bordered
        :loading="loading"
        :pagination="pagination"
        :columns="columns"
        :data-source="datalist"
        rowKey="id">
        <span slot="deAmt" slot-scope="text,record">
          <template>
            <a-tag :color="record.deAmt > 0 ? 'red' : record.deAmt < 0 ? 'green' : ''">{{ record.deAmt }}
            </a-tag>
          </template>
        </span>
        <span slot="feeType" slot-scope="text,record">
          <template>
            <span>{{ text | statusFormat }}</span>
          </template>
        </span>
        <span slot="aymentType" slot-scope="text,record">
          <template>
            <span>{{ text | aymentTypeFormat }}</span>
          </template>
        </span>
        <span slot="profitMoney" slot-scope="text,record">
          <template>
            <a-tag :color="record.profitMoney > 0 ? 'red' : record.profitMoney < 0 ? 'green' : ''">{{ record.profitMoney }}
            </a-tag>
          </template>
        </span>
      </a-table>
    </a-card>
  </page-header-wrapper>
</template>
<script>
import { agentgetAgentAgencyFeeList } from '@/api/home'
import moment from 'moment'
export default {
    name: 'Fundrecords',
    data () {
        return {
            columns: [
                {
                    title: '订单ID',
                    dataIndex: 'id',
                    align: 'center'
                },
                {
                    title: '费用类型',
                    dataIndex: 'feeType',
                    align: 'center',
                    scopedSlots: { customRender: 'feeType' }
                },
                {
                    title: '收支类型',
                    dataIndex: 'aymentType',
                    align: 'center',
                    scopedSlots: { customRender: 'aymentType' }
                },
                {
                    title: '总金额',
                    dataIndex: 'totalMoney',
                    align: 'center'
                },
                {
                    title: '利润金额',
                    dataIndex: 'profitMoney',
                    align: 'center',
                    scopedSlots: { customRender: 'profitMoney' }
                },
                {
                    title: '备注',
                    dataIndex: 'remarks',
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
                agentId: undefined,
                positionId: '',
                userId: '',
                userName: ''
            },
            datalist: []
        }
    },
    created () {
        this.getlist()
    },
    filters: {
        statusFormat: function (val) {
            let str = ''
            if (val === 1) {
                str = '入仓手续费'
            } else if (val === 2) {
                str = '平仓手续费'
            } else if (val === 3) {
                str = '延递费'
            } else if (val === 4) {
                str = '分红'
            }
            return str
        },
        aymentTypeFormat: function (val) {
            let str = ''
            if (val === 1) {
                str = '收入'
            } else {
                str = '支出'
            }
            return str
        }
    },
    methods: {
        getlist () {
            var that = this
            this.loading = true
            agentgetAgentAgencyFeeList(this.queryParam).then(res => {
                this.datalist = res.data.list
                this.pagination.total = res.data.total
                setTimeout(() => {
                    that.loading = false
                }, 500)
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
