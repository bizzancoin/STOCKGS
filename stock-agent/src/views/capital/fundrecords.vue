<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <a-card :bordered="false">
        <div class="table-page-search-wrapper">
          <a-form layout="inline">
            <a-row :gutter="48">
              <a-col :md="12" :lg="6" :sm="24">
                <a-form-item label="下级代理">
                  <a-select
                    v-model="queryParam.agentId"
                    placeholder="请选择下级代理"
                    @focus="getagentlist"
                    :loading="agentloading">
                    <a-select-option
                      v-for="(item, index) in agentlist"
                      :key="index"
                      :value="item.id">
                      {{ item.agentName }}
                    </a-select-option>
                  </a-select>
                </a-form-item>
              </a-col>
              <a-col :md="12" :lg="6" :sm="24">
                <a-form-item label="持仓Id">
                  <a-input
                    v-model="queryParam.positionId"
                    style="width: 100%"
                    placeholder="请输入持仓Id" />
                </a-form-item>
              </a-col>
              <a-col :md="12" :lg="6" :sm="24">
                <a-form-item label="用户Id">
                  <a-input v-model="queryParam.userId" style="width: 100%" placeholder="请输入用户Id" />
                </a-form-item>
              </a-col>
              <a-col :md="12" :lg="6" :sm="24">
                <a-form-item label="真实姓名">
                  <a-input v-model="queryParam.userName" style="width: 100%" placeholder="请输入真实姓名" />
                </a-form-item>
              </a-col>
            </a-row>
            <a-row :gutter="48">
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
        <span slot="deAmt" slot-scope="text,record">
          <template>
            <a-tag :color="record.deAmt > 0 ? 'red' : record.deAmt < 0 ? 'green' : ''">{{ record.deAmt }}
            </a-tag>
          </template>
        </span>
      </a-table>
    </a-card>
  </page-header-wrapper>
</template>
<script>
import { agentcashlist } from '@/api/capital'
import { agentgetSecondAgent } from '@/api/home'
import moment from 'moment'
export default {
    name: 'Fundrecords',
    data () {
        return {
            columns: [
                {
                    title: '用户名称（ID）',
                    dataIndex: 'userName',
                    align: 'center',
                    width: 180,
                    customRender: (text, row, index) => {
                        return `${row.userName}（${row.userId}）`
                    }
                },
                {
                    title: '代理名称（ID）',
                    dataIndex: 'agentName',
                    align: 'center',
                    width: 180,
                    customRender: (text, row, index) => {
                        return `${row.agentName}（${row.agentId}）`
                    }
                },
                {
                    title: '持仓ID',
                    dataIndex: 'positionId',
                    align: 'center'
                },
                {
                    title: '类型',
                    dataIndex: 'deType',
                    align: 'center',
                    width: 180
                },
                {
                    title: '金额',
                    dataIndex: 'deAmt',
                    align: 'center',
                    scopedSlots: { customRender: 'deAmt' }
                },
                {
                    title: '描述',
                    dataIndex: 'deSummary',
                    align: 'center'
                },
                {
                    title: '创建时间',
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
            datalist: [],
            agentlist: [],
            agentloading: false,
            agentqueryParam: {
                pageNum: 1,
                pageSize: 100
            }
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
                agentId: undefined,
                positionId: '',
                userId: '',
                realName: ''
            }
        },
        onChangeRangeDate (value, dateString) {
            this.queryParam.beginTime = dateString[0]
            this.queryParam.endTime = dateString[1]
        },
        getagentlist () {
            var that = this
            this.agentloading = true
            agentgetSecondAgent(this.agentqueryParam).then(res => {
                this.agentlist = res.data.list
                setTimeout(() => {
                    that.agentloading = false
                }, 500)
            })
        },
        getlist () {
            var that = this
            this.loading = true
            agentcashlist(this.queryParam).then(res => {
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
