<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <div class="table-page-search-wrapper">
        <a-form layout="inline">
          <a-row :gutter="48">
            <a-col :md="12" :lg="6" :sm="24">
              <a-form-item>
                <span class="table-page-search-submitButtons">
                  <a-button
                    type="primary"
                    icon="plus"
                    style="margin-left: 8px"
                    @click="addUserdialog = true; currentDetails = ''">
                    添加公告</a-button>
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

        <span slot="artImg" slot-scope="text">
          <template>
            <img :src="text" alt="" style="width:80px">
          </template>
        </span>
        <span slot="isShow" slot-scope="text">
          <template>
            <div>
              <a-tag :color="text == 1 ? 'red' : 'green'">
                {{ text == 1 ? '隐藏' : '显示' }}
              </a-tag>
            </div>
          </template>
        </span>

        <template slot="action" slot-scope="text,record">
          <a slot="action" href="javascript:;" @click="geteditbaseCurrency(record)">修改公告</a>
          <a-divider type="vertical" />
          <a slot="action" href="javascript:;" @click="deletebaseCurrency(record.id)">删除公告</a>
        </template>
      </a-table>
    </a-card>

    <a-modal
      :title="currentDetails ? '修改公告' : '添加公告'"
      :width="800"
      :visible="addUserdialog"
      :confirmLoading="addUserDialogloading"
      @ok="OkaddUserdialog"
      @cancel="CanceladdUserdialog">
      <a-form :form="addUserform" ref="addUserform">
        <a-form-item label="公告标题" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="请输入公告标题"
            v-decorator="['artTitle', { rules: [{ required: true, message: '请输入公告标题', }] }]" />
        </a-form-item>
        <a-form-item label="公告类型" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="请输入公告类型"
            v-decorator="['artType', { rules: [{ required: true, message: '请输入公告类型', }] }]" />
        </a-form-item>
        <a-form-item label="公告来源" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="请输入公告来源"
            v-decorator="['author', { rules: [{ required: true, message: '请输入公告来源', }] }]" />
        </a-form-item>
        <a-form-item label="是否显示" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-select
            placeholder="请选择是否显示"
            v-decorator="['isShow', { rules: [{ required: true, message: '请选择是否显示', }] }]">
            <a-select-option :value="0">显示</a-select-option>
            <a-select-option :value="1">不显示</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="公告摘要" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-textarea
            placeholder="请输入公告摘要"
            :auto-size="{ minRows: 2, maxRows: 6 }"
            v-decorator="['artSummary', { rules: [{ required: true, message: '请输入公告摘要', }] }]" />
        </a-form-item>
        <a-form-item label="公告内容" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-textarea
            placeholder="请输入公告内容"
            :auto-size="{ minRows: 4, maxRows: 6 }"
            v-decorator="['artCnt', { rules: [{ required: true, message: '请输入公告内容', }] }]" />
        </a-form-item>
        <a-form-item label="公告链接" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-textarea
            placeholder="请输入公告链接"
            :auto-size="{ minRows: 4, maxRows: 6 }"
            v-decorator="['spiderUrl', {}]" />
        </a-form-item>
        <a-form-item label="公告图片" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-upload
            name="avatar"
            list-type="picture-card"
            accept=".jpg,.jpeg,.png"
            class="avatar-uploader"
            v-decorator="['artImg', { valuePropName: 'file' }]"
            :showUploadList="false"
            :customRequest="customRequest">
            <img v-if="artImg" :src="artImg" alt="avatar" style="width: 100%;"/>
            <div v-else>
              <a-icon :type="imgloading ? 'loading' : 'plus'" />
            </div>
          </a-upload>
        </a-form-item>
      </a-form>
    </a-modal>
  </page-header-wrapper>
</template>
<script>
import { artlist, adminupload, artadd, artupdate, artdelArt } from '@/api/allsetting'
import moment from 'moment'
import pick from 'lodash.pick'
export default {
    name: 'Basecurrency',
    data () {
        return {
            columns: [
                {
                    title: '公告id',
                    dataIndex: 'id',
                    align: 'center'
                },
                {
                    title: '图片',
                    dataIndex: 'artImg',
                    align: 'center',
                    scopedSlots: { customRender: 'artImg' }
                },
                {
                    title: '公告标题',
                    dataIndex: 'artTitle',
                    align: 'center'
                },
                {
                    title: '来源',
                    dataIndex: 'author',
                    align: 'center'
                },
                {
                    title: '是否显示',
                    dataIndex: 'isShow',
                    align: 'center',
                    scopedSlots: { customRender: 'isShow' }
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
                    width: 180,
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
                pageSize: 10
            },
            datalist: [],

            addUserdialog: false,
            addUserDialogloading: false,
            addUserform: this.$form.createForm(this),
            labelCol: {
                xs: { span: 24 },
                sm: { span: 5 }
            },
            wrapperCol: {
                xs: { span: 24 },
                sm: { span: 18 }
            },
            fields: ['artTitle', 'artType', 'author', 'artSummary', 'artCnt', 'spiderUrl', 'isShow', 'artImg'],
            currentDetails: '',
            artImg: '',
            imgloading: false
        }
    },
    created () {
        this.getlist()
    },
    methods: {
        deletebaseCurrency (val) {
            var that = this
            this.$confirm({
                title: '提示',
                content: '确认删除公告？此操作不可恢复',
                onOk () {
                    var data = {
                        artId: val
                    }
                    artdelArt(data).then(res => {
                        if (res.status == 0) {
                            that.$message.success({ content: res.msg, duration: 2 })
                            that.getlist()
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
        customRequest (data) {
            this.imgloading = true
            var formData = new FormData()
            formData.append('upload_file', data.file)
            adminupload(formData).then(res => {
                if (res.status == 0) {
                    this.artImg = res.data.url
                    this.addUserform.setFieldsValue(({
                        artImg: res.data.url
                    }))
                } else {
                    this.$message.error({ content: '上传失败请检查图片类型!' })
                }
                this.imgloading = false
            })
        },
        geteditbaseCurrency (val) {
            this.currentDetails = val
            this.artImg = val.artImg
            this.addUserdialog = true
            this.fields.forEach(v => this.addUserform.getFieldDecorator(v))
            this.addUserform.setFieldsValue(pick(val, this.fields))
        },
        CanceladdUserdialog () {
            this.addUserdialog = false
            const form = this.$refs.addUserform.form
            form.resetFields()
            this.artImg = ''
        },
        OkaddUserdialog () {
            const form = this.$refs.addUserform.form
            form.validateFields((errors, values) => {
                if (!errors) {
                    this.addUserDialogloading = true
                    if (this.currentDetails != '') {
                        values.id = this.currentDetails.id
                        console.log(values)
                        artupdate(values).then(res => {
                            if (res.status == 0) {
                                this.addUserdialog = false
                                this.$message.success({ content: '修改成功', duration: 2 })
                                form.resetFields()
                                this.getlist()
                            } else {
                                this.$message.error({ content: res.msg })
                            }
                            this.addUserDialogloading = false
                        })
                    } else {
                        values.id = 0
                        artadd(values).then(res => {
                            if (res.status == 0) {
                                this.addUserdialog = false
                                this.$message.success({ content: '添加成功', duration: 2 })
                                form.resetFields()
                                this.getlist()
                            } else {
                                this.$message.error({ content: res.msg })
                            }
                            this.addUserDialogloading = false
                        })
                    }
                }
            })
        },
        getlist () {
            this.loading = true
            artlist(this.queryParam).then(res => {
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
<style lang="less" scoped>
.greens {
    color: #52c41a;
}

.reds {
    color: #f5222d;
}

/deep/ .ant-upload.ant-upload-select-picture-card{
    width: 200px !important;
}
</style>
