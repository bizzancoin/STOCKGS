<template>
  <div>
    <a-dropdown v-if="currentUser && currentUser.name" placement="bottomRight">
      <span class="ant-pro-account-avatar">
        <a-avatar
          size="small"
          src="https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png"
          class="antd-pro-global-header-index-avatar" />
        <span>{{ currentUser.name }}</span>
      </span>
      <template v-slot:overlay>
        <a-menu class="ant-pro-drop-down menu" :selected-keys="[]">
          <!-- <a-menu-item v-if="menu" key="center" @click="handleToCenter">
          <a-icon type="user" />
          {{ $t('menu.account.center') }}
        </a-menu-item> -->
          <a-menu-item v-if="menu" key="settings" @click="handleToSettings">
            <a-icon type="setting" />
            修改密码
          </a-menu-item>
          <a-menu-divider v-if="menu" />
          <a-menu-item key="logout" @click="handleLogout">
            <a-icon type="logout" />
            {{ $t('menu.account.logout') }}
          </a-menu-item>
        </a-menu>
      </template>
    </a-dropdown>

    <span v-else>
      <a-spin size="small" :style="{ marginLeft: 8, marginRight: 8 }" />
    </span>
    <span>
      <a-modal
        title="修改密码"
        :width="500"
        :visible="addUserdialog"
        :confirmLoading="addUserDialogloading"
        @ok="OkaddUserdialog"
        @cancel="CanceladdUserdialog">
        <a-form :form="addUserform" ref="addUserform">
          <a-form-item label="旧密码">
            <a-input
              placeholder="请输入旧密码"
              v-decorator="['oldPwd', { rules: [{ required: true, message: '请输入旧密码', }] }]" />
          </a-form-item>
          <a-form-item label="新密码">
            <a-input
              placeholder="请输入新密码"
              v-decorator="['newPwd', { rules: [{ required: true, message: '请输入新密码', }] }]" />
          </a-form-item>
        </a-form>
      </a-modal>
    </span>
  </div>
</template>

<script>
import { Modal } from 'ant-design-vue'
import { agentupdatePwd } from '@/api/home'
export default {
  name: 'AvatarDropdown',
  props: {
    currentUser: {
      type: Object,
      default: () => null
    },
    menu: {
      type: Boolean,
      default: true
    }
  },
  data () {
    return {
      addUserform: this.$form.createForm(this),
      addUserdialog: false,
      addUserDialogloading: false
    }
  },
  methods: {
    CanceladdUserdialog () {
      this.addUserdialog = false
      const form = this.$refs.addUserform.form
      form.resetFields()
    },
    OkaddUserdialog () {
      const form = this.$refs.addUserform.form
      form.validateFields((errors, values) => {
        if (!errors) {
          this.addUserDialogloading = true
          agentupdatePwd(values).then(res => {
            if (res.status == 0) {
              this.addUserdialog = false
              this.$message.success({ content: res.msg, duration: 2 })
              form.resetFields()
            } else {
              this.$message.error({ content: res.msg })
            }
            this.addUserDialogloading = false
          })
        }
      })
    },
    handleToCenter () {
      this.$router.push({ path: '/account/center' })
    },
    handleToSettings () {
      this.addUserdialog = true
    },
    handleLogout (e) {
      Modal.confirm({
        title: this.$t('layouts.usermenu.dialog.title'),
        content: this.$t('layouts.usermenu.dialog.content'),
        onOk: () => {
          // return new Promise((resolve, reject) => {
          //   setTimeout(Math.random() > 0.5 ? resolve : reject, 1500)
          // }).catch(() => console.log('Oops errors!'))
          return this.$store.dispatch('Logout').then(() => {
            this.$router.push({ name: 'login' })
          })
        },
        onCancel () { }
      })
    }
  }
}
</script>

<style lang="less" scoped>
.ant-pro-drop-down {
  :deep(.action) {
    margin-right: 8px;
  }

  :deep(.ant-dropdown-menu-item) {
    min-width: 160px;
  }
}
</style>
