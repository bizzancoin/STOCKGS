<template>
  <div class="account-settings-info-view">

    <a-form layout="vertical" :form="addUserform" ref="addUserform">
      <a-row :gutter="16" type="flex" justify="center">
        <a-col :order="isMobile ? 2 : 1" :md="24" :lg="16">

          <a-form-item label="昵称">
            <a-input
              placeholder="请输入您的昵称"
              v-decorator="['adminName', { rules: [{ required: false, message: '请输入您的昵称', }] }]"
            />
          </a-form-item>

          <a-form-item label="ID">
            <a-input
              placeholder="请输入您的ID"
              v-decorator="['id', { rules: [{ required: true, message: '请输入您的ID', }] }]"
              disabled />
          </a-form-item>

          <a-form-item label="新密码">
            <a-input
              placeholder="请输入您的新密码"
              v-decorator="['adminPwd', { rules: [{ required: true, message: '请输入您的新密码', }] }]" />
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
import AvatarModal from './AvatarModal'
import { baseMixin } from '@/store/app-mixin'
import { adminlist, adminupdate } from '@/api/managesettings'
import pick from 'lodash.pick'
export default {
  mixins: [baseMixin],
  components: {
    AvatarModal
  },
  data () {
    return {
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
      fields: ['id', 'adminPwd', 'adminName']
    }
  },
  mounted () {
    this.getnowuser()
  },
  methods: {
    getnowuser () {
      adminlist().then(res => {
        var index = res.data.list.findIndex(item => item.adminPhone == window.localStorage.getItem('phones'))
        this.addUserform.setFieldsValue({
          id: res.data.list[index].id,
          adminName: res.data.list[index].adminName

        })
      })
    },
    setavatar (url) {
      this.option.img = url
    },
    saveinfo () {
      const form = this.$refs.addUserform.form
      form.validateFields((errors, values) => {
        if (!errors) {
          adminupdate(values).then(res => {
            if (res.status == 0) {
              this.$message.success({ content: '修改成功', duration: 2 })
              form.resetFields()
              this.getnowuser()
            } else {
              this.$message.error({ content: res.msg })
            }
          })
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
