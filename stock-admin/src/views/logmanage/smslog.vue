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
        <span slot="smsStatus" slot-scope="text,record">
          <template>
            <a-tag :color="record.smsStatus == 0 ? 'green' : record.smsStatus == 1 ? 'red' : ''">
              {{ record.smsStatus == 0 ? '成功' : record.smsStatus == 1 ? '失败' : '' }}</a-tag>
          </template>
        </span>
      </a-table>
    </a-card>
  </page-header-wrapper>
</template>
<script>
import { logsmsList } from '@/api/logmanage'
import moment from 'moment'
export default {
    name: 'Smslog',
    data () {
        return {
            columns: [
                {
                    title: '发送类型',
                    dataIndex: 'smsTitle',
                    align: 'center'
                },
                {
                    title: '发送手机',
                    dataIndex: 'smsPhone',
                    align: 'center'
                },
                {
                    title: '发送内容',
                    dataIndex: 'smsCnt',
                    align: 'center'
                },
                {
                    title: '发送模板',
                    dataIndex: 'smsTemplate',
                    align: 'center'
                },
                {
                    title: '发送状态',
                    dataIndex: 'smsStatus',
                    align: 'center',
                    scopedSlots: { customRender: 'smsStatus' }
                },
                {
                    title: '发送时间',
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
                pageSize: 10
            },
            datalist: []
        }
    },
    created () {
        this.getlist()
    },
    methods: {
        getlist () {
            var that = this
            this.loading = true
            logsmsList(this.queryParam).then(res => {
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
