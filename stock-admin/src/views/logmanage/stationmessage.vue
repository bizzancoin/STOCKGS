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
        <span slot="status" slot-scope="text,record">
          <template>
            <a-tag :color="record.status == 1 ? 'red' : record.status == 2 ? 'green' : ''">
              {{ record.status == 1 ? '未读' : record.status == 2 ? '已读' : '' }}</a-tag>
          </template>
        </span>
      </a-table>
    </a-card>
  </page-header-wrapper>
</template>
<script>
import { logmessageList } from '@/api/logmanage'
import moment from 'moment'
export default {
    name: 'Smslog',
    data () {
        return {
            columns: [
                {
                    title: '用户名称',
                    dataIndex: 'userName',
                    align: 'center'
                },
                {
                    title: '类型',
                    dataIndex: 'typeName',
                    align: 'center'
                },
                {
                    title: '内容',
                    dataIndex: 'content',
                    align: 'center',
                    width: 600
                },
                {
                    title: '发送状态',
                    dataIndex: 'status',
                    align: 'center',
                    scopedSlots: { customRender: 'status' }
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
            logmessageList(this.queryParam).then(res => {
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
