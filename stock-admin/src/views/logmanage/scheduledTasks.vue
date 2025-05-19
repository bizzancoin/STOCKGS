<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <a-card :bordered="false">
        <div class="table-page-search-wrapper">
          <a-form layout="inline">
            <a-row :gutter="48">
              <a-col :md="12" :lg="6" :sm="24">
                <a-form-item label="任务类型">
                  <a-select v-model="queryParam.taskType" placeholder="请选择任务类型">
                    <a-select-option
                      v-for="(item, index) in tasktypeList"
                      :key="index"
                      :value="item.value">
                      {{ item.value }}
                    </a-select-option>
                  </a-select>
                </a-form-item>
              </a-col>
              <a-col :md="12" :lg="8" :sm="24">
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
                  </span>
                </a-form-item>
              </a-col>
            </a-row>
          </a-form>
        </div>
      </a-card>
      <a-table
        bordered
        :loading="loading"
        :pagination="pagination"
        :columns="columns"
        :data-source="datalist"
        rowKey="id">
        <span slot="isSuccess" slot-scope="text,record">
          <template>
            <a-tag :color="record.isSuccess == 0 ? 'green' : record.isSuccess == 1 ? 'red' : ''">
              {{ record.isSuccess == 0 ? '成功' : record.isSuccess == 1 ? '失败' : '' }}</a-tag>
          </template>
        </span>
      </a-table>
    </a-card>
  </page-header-wrapper>
</template>
<script>
import { logtaskList } from '@/api/logmanage'
import moment from 'moment'
export default {
    name: 'ScheduledTasks',
    data () {
        return {
            columns: [
                {
                    title: '任务类型',
                    dataIndex: 'taskType',
                    align: 'center'
                },
                {
                    title: '任务目标',
                    dataIndex: 'taskTarget',
                    align: 'center'
                },
                {
                    title: '任务状态',
                    dataIndex: 'isSuccess',
                    align: 'center',
                    scopedSlots: { customRender: 'isSuccess' }
                },
                {
                    title: '注册时间',
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
                taskType: undefined
            },
            datalist: [],
            tasktypeList: [
                { value: '扣除留仓费' },
                { value: '定时任务强制平仓-浮动盈亏' },
                { value: '强平任务-股票持仓' },
                { value: '强平任务-指数持仓' },
                // { value: '强平任务-期货持仓' },
                { value: '管理员修改金额' }
            ]
        }
    },
    created () {
        this.getlist()
    },
    methods: {
        getqueryParam () {
            this.queryParam = {
                pageNum: 1,
                pageSize: 10,
                taskType: undefined
            }
        },
        getlist () {
            var that = this
            this.loading = true
            logtaskList(this.queryParam).then(res => {
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
