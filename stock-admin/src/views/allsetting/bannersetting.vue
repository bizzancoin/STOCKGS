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
                    添加轮播图</a-button>
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

        <span slot="bannerUrl" slot-scope="text">
          <template>
            <img :src="text" alt="" style="width:120px;height: 50px;">
          </template>
        </span>
        <span slot="isPc" slot-scope="text">
          <template>
            <div>
              <a-tag :color="text == 1 ? 'red' : 'green'">
                {{ text == 1 ? '隐藏' : '显示' }}
              </a-tag>
            </div>
          </template>
        </span>
        <span slot="isM" slot-scope="text">
          <template>
            <div>
              <a-tag :color="text == 1 ? 'red' : 'green'">
                {{ text == 1 ? '隐藏' : '显示' }}
              </a-tag>
            </div>
          </template>
        </span>

        <template slot="action" slot-scope="text,record">
          <a slot="action" href="javascript:;" @click="geteditbaseCurrency(record)">修改轮播图</a>
          <a-divider type="vertical" />
          <a slot="action" href="javascript:;" @click="deletebaseCurrency(record.id)">删除轮播图</a>
        </template>
      </a-table>
    </a-card>

    <a-modal
      :title="currentDetails ? '修改轮播图图片' : '添加轮播图图片'"
      :width="800"
      :visible="addUserdialog"
      :confirmLoading="addUserDialogloading"
      @ok="OkaddUserdialog"
      @cancel="CanceladdUserdialog">
      <a-form :form="addUserform" ref="addUserform">
        <a-form-item label="排序" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="请输入排序"
            v-decorator="['isOrder', { rules: [{ required: true, message: '排序值越大显示越靠前', }] }]" />
        </a-form-item>
        <a-form-item label="标题" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="请输入标题"
            v-decorator="['banTitle', { }]" />
        </a-form-item>
        <a-form-item label="描述" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="请输入描述"
            v-decorator="['banDesc', { }]" />
        </a-form-item>
        <a-form-item label="链接" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input
            placeholder="请输入链接"
            v-decorator="['targetUrl', {}]" />
        </a-form-item>
        <a-form-item label="PC端是否显示" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-select
            placeholder="请选择显示状态"
            v-decorator="['isPc', { }]">
            <a-select-option :value="0">显示</a-select-option>
            <a-select-option :value="1">隐藏</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="移动端是否显示" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-select
            placeholder="请选择显示状态"
            v-decorator="['isM', {}]">
            <a-select-option :value="0">显示</a-select-option>
            <a-select-option :value="1">隐藏</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="轮播图图片" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-upload
            name="avatar"
            list-type="picture-card"
            style="width: 200px;"
            accept=".jpg,.jpeg,.png"
            class="avatar-uploader"
            v-decorator="['bannerUrl', { valuePropName: 'file',rules: [{ required: true, message: '请上传轮播图图片', }] }]"
            :showUploadList="false"
            :customRequest="customRequest">
            <img v-if="bannerUrl" :src="bannerUrl" alt="avatar" style="width: 100%;" />
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
import { bannerslist, bannersupdate, bannersadd, bannersdelete, adminupload } from '@/api/allsetting'
import moment from 'moment'
import pick from 'lodash.pick'
export default {
    name: 'Basecurrency',
    data () {
        return {
            columns: [
                {
                    title: '图片',
                    dataIndex: 'bannerUrl',
                    align: 'center',
                    scopedSlots: { customRender: 'bannerUrl' }
                },
                {
                    title: '排序',
                    dataIndex: 'isOrder',
                    align: 'center'
                },
                {
                    title: 'PC是否显示',
                    dataIndex: 'isPc',
                    align: 'center',
                    scopedSlots: { customRender: 'isPc' }
                },
                {
                    title: '移动端是否显示',
                    dataIndex: 'isM',
                    align: 'center',
                    scopedSlots: { customRender: 'isM' }
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
                    width: 200,
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
            fields: ['bannerUrl', 'isOrder', 'isPc', 'isM', 'banDesc', 'banTitle', 'targetUrl'],
            currentDetails: '',
            bannerUrl: '',
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
                content: '确认删除轮播图？此操作不可恢复',
                onOk () {
                    var data = {
                        id: val
                    }
                    bannersdelete(data).then(res => {
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
                    this.bannerUrl = res.data.url
                    this.addUserform.setFieldsValue(({
                        bannerUrl: res.data.url
                    }))
                } else {
                    this.$message.error({ content: '上传失败请检查图片类型!' })
                }
                this.imgloading = false
            })
        },
        geteditbaseCurrency (val) {
            this.currentDetails = val
            this.bannerUrl = val.bannerUrl
            this.addUserdialog = true
            this.fields.forEach(v => this.addUserform.getFieldDecorator(v))
            this.addUserform.setFieldsValue(pick(val, this.fields))
        },
        CanceladdUserdialog () {
            this.addUserdialog = false
            const form = this.$refs.addUserform.form
            form.resetFields()
            this.bannerUrl = ''
        },
        OkaddUserdialog () {
            const form = this.$refs.addUserform.form
            form.validateFields((errors, values) => {
                if (!errors) {
                    this.addUserDialogloading = true
                    if (this.currentDetails != '') {
                        values.id = this.currentDetails.id
                        console.log(values)
                        bannersupdate(values).then(res => {
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
                        bannersadd(values).then(res => {
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
                    this.bannerUrl = ''
                }
            })
        },
        getlist () {
            this.loading = true
            bannerslist(this.queryParam).then(res => {
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
<style scoped lang="less">
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
