<template>
  <div>
    <a-modal title="实名认证审核" :width="1000" :visible="userDialog" :footer="false" @cancel="userDialog = false">
      <a-descriptions
        bordered
        :title="currentDetails.realName ? currentDetails.realName : '未认证'"
        :column="{ xxl: 3, xl: 3, lg: 3, md: 3, sm: 2, xs: 1 }">
        <a-descriptions-item label="真实姓名">
          {{ currentDetails.realName ? currentDetails.realName : '--' }}
        </a-descriptions-item>
        <a-descriptions-item label="认证状态">
          <a-tag
            :color="(currentDetails.isActive == 0 || currentDetails.isActive == 1) ? 'blue' : currentDetails.isActive == 2 ? 'green' : 'red'">
            {{ currentDetails.isActive == 0 ? '待认证' : currentDetails.isActive == 1 ? '待审核' :
              currentDetails.isActive == 2 ? '认证成功' : '驳回'
            }}
          </a-tag>
        </a-descriptions-item>
        <a-descriptions-item label="驳回原因" v-if="currentDetails.isActive == 3">
          {{ currentDetails.authMsg ? currentDetails.authMsg : '--' }}
        </a-descriptions-item>
        <a-descriptions-item label="身份证号码">
          {{ currentDetails.idCard ? currentDetails.idCard : '--' }}
        </a-descriptions-item>
        <a-descriptions-item label="注册ip">
          {{ currentDetails.regIp ? currentDetails.regIp : '--' }}
        </a-descriptions-item>
        <a-descriptions-item label="注册地址">
          {{ currentDetails.regAddress ? currentDetails.regAddress : '--' }}
        </a-descriptions-item>
        <a-descriptions-item label="注册时间">
          {{ currentDetails.regTime | moment }}
        </a-descriptions-item>
        <a-descriptions-item label="身份证正面">
          <img :src="currentDetails.img2Key" alt="" style="width:140px;height: 70px;">
        </a-descriptions-item>
        <a-descriptions-item label="身份证背面">
          <img :src="currentDetails.img1Key" alt="" style="width:140px;height: 70px;">
        </a-descriptions-item>
        <a-descriptions-item label="手持身份证">
          <img :src="currentDetails.img3Key" alt="" style="width:140px;height: 70px;">
        </a-descriptions-item>
      </a-descriptions>
      <div style="margin-top:20px;display:flex;justify-content: center;" v-show="currentDetails.isActive == 0 || currentDetails.isActive == 1">
        <a-button type="danger" @click="userDialog = false;bohuidialog = true">
          驳回
        </a-button>
        <a-button type="primary" style="margin-left:10px;" @click="gettongguo(2)">
          通过
        </a-button>
      </div>
    </a-modal>
    <a-modal
      title="驳回原因"
      :width="500"
      :visible="bohuidialog"
      :confirmLoading="bohuidialogloading"
      @ok="Okbohuidialog"
      @cancel="Cancelbohuidialog">
      <a-form :form="bohuiform" ref="bohuiform">
        <a-form-item>
          <a-input
            placeholder="请输入驳回原因"
            v-decorator="['authMsg', { rules: [{ required: true, message: '请输入驳回原因', }] }]" />
        </a-form-item>
      </a-form>
    </a-modal>
  </div>
</template>
<script>
import { userauthByAdmin } from '@/api/home'
export default {
    components: {},
    props: {
        currentDetails: {
            type: Object
        },
        getinit: {
            type: Function,
            default: function () {
            }
        }
    },
    data () {
        return {
            userDialog: false,
            bohuidialog: false,
            bohuidialogloading: false,
            bohuiform: this.$form.createForm(this)
        }
    },
    methods: {
        Okbohuidialog () {
            const form = this.$refs.bohuiform.form
            form.validateFields((errors, values) => {
                if (!errors) {
                    values.userId = this.currentDetails.id
                    values.state = 3
                    this.bohuidialogloading = true
                    userauthByAdmin(values).then(res => {
                        if (res.status == 0) {
                            this.bohuidialog = false
                            this.$message.success({ content: res.msg, duration: 2 })
                            form.resetFields()
                            this.getinit()
                        } else {
                            this.$message.error({ content: res.msg })
                        }
                        this.bohuidialogloading = false
                    })
                }
            })
        },
        Cancelbohuidialog () {
            this.bohuidialog = false
            const form = this.$refs.bohuiform.form
            form.resetFields()
        },
        gettongguo (val) {
            var data = {
                userId: this.currentDetails.id,
                state: val
            }
            userauthByAdmin(data).then(res => {
                if (res.status == 0) {
                    this.userDialog = false
                    this.getinit()
                } else {
                    this.$message.error({ content: res.msg })
                }
                this.userDialog = false
            })
        }
    }
}
</script>
