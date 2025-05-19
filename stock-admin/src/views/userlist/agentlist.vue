<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <div class="table-page-search-wrapper">
        <a-form layout="inline">
          <a-row :gutter="48">
            <a-col :md="6" :sm="24">
              <a-form-item label="下级代理">
                <a-select
                  v-model="agentqueryParam.id"
                  placeholder="请选择下级代理"
                  @focus="getclickagentlist"
                  :loading="agentloading">
                  <a-select-option
                    v-for="(item, index) in clickagentlist"
                    :key="index"
                    :value="item.id">
                    {{ `${item.agentName}/${item.id}` }}
                  </a-select-option>
                </a-select>
              </a-form-item>
            </a-col>

            <a-col :md="6" :sm="24">
              <a-form-item label="代理真实姓名">
                <a-input v-model="agentqueryParam.realName" style="width: 100%" placeholder="请填写真实姓名" />
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="24">
              <a-form-item label="用户手机">
                <a-input v-model="agentqueryParam.phone" style="width: 100%" placeholder="请填写用户手机号" />
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="24">
              <a-form-item>
                <span class="table-page-search-submitButtons">
                  <a-button
                    type="primary"
                    icon="search"
                    @click="agentqueryParam.pageNum = 1, pagination.current = 1,getagentlist()">查询
                  </a-button>
                  <a-button
                    style="margin-left: 8px"
                    @click="() => this.agentqueryParam = {}"
                    icon="redo">重置</a-button>

                </span>
              </a-form-item>
            </a-col>
          </a-row>
        </a-form>
      </div>
      <div class="table-operator">
        <a-button
          type="primary"
          icon="plus"
          @click="$refs.addagentdialog.addUserdialog = true">添加下级代理</a-button>
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
        <template slot="action" slot-scope="text,record">
          <a
            slot="action"
            href="javascript:;"
            @click="$refs.editCapitalAgentdialog.getEditorder(record)">修改资金</a>
          <a-divider type="vertical" />
          <a slot="action" href="javascript:;" @click="getDelagent(record)">删除代理</a>
          <a-divider type="vertical" />
          <a
            slot="action"
            href="javascript:;"
            @click="$refs.EditAgentinfodialog.getEditorder(record)">修改代理</a>
        </template>
      </a-table>
    </a-card>
    <editCapitalAgentdialog ref="editCapitalAgentdialog" :getinit="geteditinit"></editCapitalAgentdialog>
    <EditAgentinfodialog ref="EditAgentinfodialog" :getinit="geteditinit"></EditAgentinfodialog>
    <addagentdialog ref="addagentdialog" :getinit="getinit"></addagentdialog>
  </page-header-wrapper>
</template>
<script>

import editCapitalAgentdialog from './agentcomponents/editCapitalAgentdialog'
import EditAgentinfodialog from './agentcomponents/EditAgentinfodialog'
import addagentdialog from './agentcomponents/addagentdialog'
import { nextagent, agentdelAgent } from '@/api/home'
import moment from 'moment'
export default {
    name: 'AgentList',
    components: {
        editCapitalAgentdialog,
        EditAgentinfodialog,
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
                    title: '代理状态',
                    dataIndex: 'isLock',
                    align: 'center',
                    scopedSlots: { customRender: 'isLock' }
                },
                {
                    title: '注册时间',
                    dataIndex: 'addTime',
                    align: 'center',
                    customRender: (text, row, index) => {
                        return moment(text).format('YYYY-MM-DD HH:mm:ss')
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
            agentqueryParam: {
                id: undefined,
                phone: '',
                realName: '',
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
        getDelagent (val) {
            var that = this
            this.$confirm({
                title: '提示',
                content: '确认删除代理？此操作不可恢复',
                onOk () {
                    var data = {
                        agentId: val.id
                    }
                    agentdelAgent(data).then(res => {
                        if (res.status == 0) {
                            that.$message.success({ content: res.msg, duration: 2 })
                            that.getinit()
                        } else {
                            that.$message.error({ content: res.msg })
                        }
                    })
                },
                onCancel () {
                    console.log('Cancel')
                }
            })
        },
        getinit () {
            this.queryParam = {
                id: undefined,
                phone: '',
                realName: '',
                pageNum: 1,
                pageSize: 10
            }
            this.pagination.current = 1
            this.getagentlist()
        },
        geteditinit () {
            this.getagentlist()
        },
        getagentlist () {
            var that = this
            this.loading = true
            nextagent(this.agentqueryParam).then(res => {
                this.agentlist = res.data.list
                this.pagination.total = res.data.total
                setTimeout(() => {
                    that.loading = false
                }, 500)
            })
        },
        getclickagentlist () {
            var that = this
            this.agentloading = true
            var data = {
                pageNum: 1,
                pageSize: 100
            }
            nextagent(data).then(res => {
                this.clickagentlist = res.data.list
                setTimeout(() => {
                    that.agentloading = false
                }, 500)
            })
        },
        onPageChange (page, pageSize) {
            this.agentqueryParam.pageNum = page
            this.pagination.current = page
            this.getagentlist()
        },
        onSizeChange (current, pageSize) {
            this.agentqueryParam.pageNum = current
            this.pagination.current = page
            this.agentqueryParam.pageSize = pageSize
            this.getagentlist()
        }
    }
}
</script>
