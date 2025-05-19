<template>
  <div>
    <a-modal title="用户详情" :width="1000" :visible="userDialog" :footer="false" @cancel="userDialog = false">
      <a-descriptions bordered :title="currentDetails.realName ? currentDetails.realName : '未认证'" :column="{ xxl: 3, xl: 3, lg: 3, md: 3, sm: 2, xs: 1 }">
        <a-descriptions-item label="用户ID">
          {{ currentDetails.id ? currentDetails.id : '--' }}
        </a-descriptions-item>
        <a-descriptions-item label="手机号码">
          {{ currentDetails.phone ? currentDetails.phone : '--' }}
        </a-descriptions-item>
        <a-descriptions-item label="登录状态">
          <a-tag :color="currentDetails.positionType == 1 ? 'red' : 'green'">
            {{ currentDetails.isLogin == 1 ? '不可登录' : '正常' }}
          </a-tag>
        </a-descriptions-item>
        <a-descriptions-item label="账号类型">
          <a-tag :color="currentDetails.accountType == 1 ? 'blue' : 'green'">
            {{ currentDetails.accountType == 1 ? '模拟用户' : '实盘用户' }}
          </a-tag>
        </a-descriptions-item>
        <a-descriptions-item label="交易状态">
          <a-tag :color="currentDetails.isLock == 1 ? 'red' : 'green'">
            {{ currentDetails.isLock == 1 ? '不可交易' : '正常' }}
          </a-tag>
        </a-descriptions-item>
        <a-descriptions-item label="所属代理">
          {{ currentDetails.agentName ? currentDetails.agentName : '--' }}
        </a-descriptions-item>
        <a-descriptions-item label="真实姓名">
          {{ currentDetails.realName ? currentDetails.realName : '--' }}
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
        <a-descriptions-item label="总资金(￥)">
          {{ Number(currentDetails.userAmt + currentDetails.userIndexAmt).toFixed(2) }}
        </a-descriptions-item>
        <a-descriptions-item label="融资总资金(￥)">
          {{ currentDetails.userAmt ? currentDetails.userAmt : '0' }}
        </a-descriptions-item>
        <a-descriptions-item label="指数总资金(￥)">
          {{ currentDetails.userIndexAmt ? currentDetails.userIndexAmt : '0' }}
        </a-descriptions-item>
        <!-- <a-descriptions-item label="期货总资金(￥)">
                    {{ currentDetails.userFutAmt ? currentDetails.userFutAmt : '0' }}
                </a-descriptions-item> -->
        <a-descriptions-item label="融资可用资金(￥)">
          {{ currentDetails.enableAmt ? currentDetails.enableAmt : '0' }}
        </a-descriptions-item>
        <a-descriptions-item label="指数可用资金(￥)">
          {{ currentDetails.enableIndexAmt ? currentDetails.enableIndexAmt : '0' }}
        </a-descriptions-item>
        <!-- <a-descriptions-item label="期货可用资金(￥)">
                    {{ currentDetails.enableFutAmt ? currentDetails.enableFutAmt : '0' }}
                </a-descriptions-item> -->

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
    </a-modal>
  </div>
</template>
<script>
export default {
    components: {},
    props: {
        currentDetails: {
            type: Object
        }
    },
    data () {
        return {
            userDialog: false
        }
    },
    methods: {

    }
}
</script>
