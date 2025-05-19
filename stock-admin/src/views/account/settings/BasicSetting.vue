<template>
  <div class="account-settings-info-view">

    <a-form layout="vertical" :form="addUserform" ref="addUserform">
      <a-row :gutter="16" type="flex" justify="center">
        <a-col :order="isMobile ? 2 : 1" :md="24" :lg="16">

          <a-form-item label="网站名称">
            <a-input
              placeholder="请输入您的网站名称"
              v-decorator="['siteName', { rules: [{ required: true, message: '请输入您的网站名称', }] }]" />
          </a-form-item>

          <a-form-item label="在线客服链接">
            <a-input
              placeholder="请输入您的在线客服链接"
              v-decorator="['onlineService', { rules: [{ required: true, message: '请输入您的在线客服链接', }] }]" />
          </a-form-item>

          <a-form-item label="网站logo">
            <a-upload
              name="avatar"
              list-type="picture-card"
              accept=".jpg,.jpeg,.png"
              class="avatar-uploader"
              v-decorator="['siteLogo', { valuePropName: 'file', rules: [{ required: true, message: '请上传网站logo', }] }]"
              :showUploadList="false"
              :customRequest="customRequest">
              <img v-if="siteLogo" :src="siteLogo" alt="avatar" />
              <div v-else>
                <a-icon :type="imgloading ? 'loading' : 'plus'" />
              </div>
            </a-upload>
          </a-form-item>
          <a-form-item label="文章修改">
            <a-select v-model="contenttype" placeholder="请选择持仓类型" @change="getchange">
              <a-select-option :value="0">用户协议</a-select-option>
              <a-select-option :value="1">隐私协议</a-select-option>
              <a-select-option :value="2">关于我们</a-select-option>
              <a-select-option :value="3">玩法规则</a-select-option>
            </a-select>
            <a-textarea placeholder="请输入文本" :rows="4" v-model="content" />
            <!-- <quill-editor v-model="content" ref="myQuillEditor" :options="editorOption" @blur="onEditorBlur($event)"
                @focus="onEditorFocus($event)" @ready="onEditorReady($event)" @change="onEditorChange($event)">
              </quill-editor> -->
          </a-form-item>
          <a-form-item>
            <a-button type="primary" @click="saveinfo">保存基本信息</a-button>
          </a-form-item>

        </a-col>
      </a-row>
    </a-form>

    <avatar-modal ref="modal" @ok="setavatar" />

  </div>
</template>

<script>
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
import { quillEditor } from 'vue-quill-editor'
import AvatarModal from './AvatarModal'
import { baseMixin } from '@/store/app-mixin'
import { sitegetInfo, adminupload, infoupdate } from '@/api/allsetting'
import pick from 'lodash.pick'
export default {
  mixins: [baseMixin],
  components: {
    AvatarModal,
    quillEditor
  },
  data () {
    return {
      content: '',
      editorOption: {
        // some quill options
      },
      // cropper
      preview: {},
      option: {
        img: '/avatar2.jpg',
        info: true,
        size: 1,
        outputType: 'jpeg',
        canScale: false,
        autoCrop: true,
        // 只有自动截图开启 宽度高度才生效
        autoCropWidth: 180,
        autoCropHeight: 180,
        fixedBox: true,
        // 开启宽度和高度比例
        fixed: true,
        fixedNumber: [1, 1]
      },
      settingdetail: {},
      addUserform: this.$form.createForm(this),
      fields: ['siteName', 'onlineService', 'siteLogo'],
      siteLogo: '',
      imgloading: false,
      contenttype: 0
    }
  },
  mounted () {
    this.getsetting()
  },
  methods: {
    onEditorBlur (quill) {
      console.log('editor blur!', quill)
    },
    onEditorFocus (quill) {
      console.log('editor focus!', quill)
    },
    onEditorReady (quill) {
      console.log('editor ready!', quill)
    },
    onEditorChange ({ quill, html, text }) {
      console.log('editor change!', quill, html, text)
    },
    getchange (e) {
      this.content = e == 0 ? this.settingdetail.companyInfo : e == 1 ? this.settingdetail.certImg1 : e == 2 ? this.settingdetail.certImg2 : this.settingdetail.tradeAgreeText
    },
    setavatar (url) {
      this.option.img = url
    },
    saveinfo () {
      const form = this.$refs.addUserform.form
      form.validateFields((errors, values) => {
        if (!errors) {
          values.id = this.settingdetail.id
          console.log(this.content)
          this.contenttype == 0 ? values.companyInfo = this.content : this.contenttype == 1 ? values.certImg1 = this.content : this.contenttype == 2 ? values.certImg2 = this.content : values.tradeAgreeText = this.content
          infoupdate(values).then(res => {
            if (res.status == 0) {
              this.$message.success({ content: '修改成功', duration: 2 })
              this.contenttype = 0
              form.resetFields()
              this.getsetting()
            } else {
              this.$message.error({ content: res.msg })
            }
          })
        }
      })
    },
    customRequest (data) {
      this.imgloading = true
      var formData = new FormData()
      formData.append('upload_file', data.file)
      adminupload(formData).then(res => {
        if (res.status == 0) {
          this.siteLogo = res.data.url
          this.addUserform.setFieldsValue(({
            siteLogo: res.data.url
          }))
        } else {
          this.$message.error({ content: '上传失败请检查图片类型!' })
        }
        this.imgloading = false
      })
    },
    getsetting () {
      sitegetInfo().then(res => {
        if (res.status == 0) {
          this.settingdetail = res.data
          this.siteLogo = this.settingdetail.siteLogo
          this.fields.forEach(v => this.addUserform.getFieldDecorator(v))
          this.addUserform.setFieldsValue(pick(this.settingdetail, this.fields))
          this.content = this.settingdetail.companyInfo
        }
      })
    }
  }
}
</script>

<style lang="less" scoped>
.avatar-upload-wrapper {
  height: 200px;
  width: 100%;
}

.ant-upload-preview {
  position: relative;
  margin: 0 auto;
  width: 100%;
  max-width: 180px;
  border-radius: 50%;
  box-shadow: 0 0 4px #ccc;

  .upload-icon {
    position: absolute;
    top: 0;
    right: 10px;
    font-size: 1.4rem;
    padding: 0.5rem;
    background: rgba(222, 221, 221, 0.7);
    border-radius: 50%;
    border: 1px solid rgba(0, 0, 0, 0.2);
  }

  .mask {
    opacity: 0;
    position: absolute;
    background: rgba(0, 0, 0, 0.4);
    cursor: pointer;
    transition: opacity 0.4s;

    &:hover {
      opacity: 1;
    }

    i {
      font-size: 2rem;
      position: absolute;
      top: 50%;
      left: 50%;
      margin-left: -1rem;
      margin-top: -1rem;
      color: #d6d6d6;
    }
  }

  img,
  .mask {
    width: 100%;
    max-width: 180px;
    height: 100%;
    border-radius: 50%;
    overflow: hidden;
  }
}
</style>
