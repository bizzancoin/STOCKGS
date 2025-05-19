<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <div class="table-page-search-wrapper">
        <a-form layout="inline">
          <a-row :gutter="48">
            <a-col :md="6" :sm="24">
              <a-form-item>
                <span class="table-page-search-submitButtons">
                  <a-button type="primary" icon="plus" style="margin-left: 8px" @click="$refs.addagentdialog.addUserdialog = true">添加下级代理</a-button>
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
        :data-source="agentlist"
        rowKey="id">
        <span slot="agentName" slot-scope="text,record">
          <template>
            <div>
              <span>{{ record.agentName }}（{{ record.id }}）</span>
            </div>
          </template>
        </span>
        <span slot="parentName" slot-scope="text,record">
          <template>
            <div>
              <span>{{ record.parentName }}</span>
              <span v-if="record.parentId">({{ record.parentId }})</span>
            </div>
          </template>
        </span>
        <span slot="isLock" slot-scope="text,record">
          <template>
            <div>
              <a-tag :color="record.isLock == 0 ? 'green' : 'red'">{{ record.isLock == 0 ? '正常' : '锁定'
              }}</a-tag>
            </div>
          </template>
        </span>
        <template slot="action" slot-scope="text,record">
          <a
            slot="action"
            href="javascript:;"
            @click="$refs.editCapitalAgentdialog.getEditorder(record)">修改资金</a>
          <a-divider type="vertical" />
          <a slot="action" href="javascript:;" @click="getDelagent(record)">删除代理</a>
          <a-divider type="vertical" />
          <a slot="action" href="javascript:;" @click="$refs.EditAgentinfodialog.getEditorder(record)">修改代理</a>
        </template>
      </a-table>
    </a-card>
    <!-- <editCapitalAgentdialog ref="editCapitalAgentdialog" :getinit="getinit"></editCapitalAgentdialog>
        <EditAgentinfodialog ref="EditAgentinfodialog" :getinit="getinit"></EditAgentinfodialog> -->
    <addagentdialog ref="addagentdialog" :getinit="getinit"></addagentdialog>
  </page-header-wrapper>
</template>
<script>

// import editCapitalAgentdialog from './agentcomponents/editCapitalAgentdialog'
// import EditAgentinfodialog from './agentcomponents/EditAgentinfodialog'
import addagentdialog from './agentcomponents/addagentdialog'
import { agentgetSecondAgent } from '@/api/home'
import moment from 'moment'
export default {
    name: 'AgentList',
    components: {
        // editCapitalAgentdialog,
        // EditAgentinfodialog,
        addagentdialog
    },
    data () {
        return {
            columns: [
                {
                    title: '所属代理（Id）',
                    scopedSlots: { customRender: 'agentName' },
                    align: 'center'
                },
                {
                    title: '代理编码',
                    dataIndex: 'agentCode',
                    align: 'center'
                },
                {
                    title: '上级代理',
                    dataIndex: 'parentName',
                    align: 'center',
                    scopedSlots: { customRender: 'parentName' }
                },
                {
                    title: '代理手机号',
                    dataIndex: 'agentPhone',
                    align: 'center'
                },
                {
                    title: '真实姓名',
                    dataIndex: 'agentRealName',
                    align: 'center'
                },
                {
                    title: '总资金',
                    dataIndex: 'totalMoney',
                    align: 'center',
                    customRender: (text, row, index) => {
                        return text
                    }
                },
                {
                    title: '代理等级',
                    dataIndex: 'agentLevel',
                    align: 'center'
                },
                {
                    title: '手续费比例',
                    dataIndex: 'poundageScale',
                    align: 'center'
                },
                {
                    title: '递延费比例',
                    dataIndex: 'deferredFeesScale',
                    align: 'center'
                },
                {
                    title: '分红比例',
                    dataIndex: 'receiveDividendsScale',
                    align: 'center'
                },
                {
                    title: '注册时间',
                    dataIndex: 'addTime',
                    align: 'center',
                    customRender: (text, row, index) => {
                        return moment(text).format('YYYY-MM-DD HH:mm:ss')
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
            agentqueryParam: {
                pageNum: 1,
                pageSize: 10
            },
            agentloading: false,
            agentlist: [],
            clickagentlist: []
        }
    },
    created () {
        this.getagentlist()
    },
    methods: {
        getinit () {
            this.queryParam = {
                pageNum: 1,
                pageSize: 10
            }
            this.getagentlist()
        },
        getagentlist () {
            var that = this
            this.loading = true
            agentgetSecondAgent(this.agentqueryParam).then(res => {
                this.agentlist = res.data.list
                this.pagination.total = res.data.total
                setTimeout(() => {
                    that.loading = false
                }, 500)
            })
        },
        onPageChange (page, pageSize) {
            this.agentqueryParam.pageNum = page
            this.getagentlist()
        },
        onSizeChange (current, pageSize) {
            this.agentqueryParam.pageNum = current
            this.agentqueryParam.pageSize = pageSize
            this.getagentlist()
        }
    }
}
</script>
