<template>
  <div class="wrapper">
    <div class="header">
      <mt-header title="合作协议">
        <router-link to="" slot="left">
          <mt-button @click="goBack" icon="back"></mt-button>
        </router-link>
        <!-- <mt-button icon="more" slot="right"></mt-button> -->
      </mt-header>
    </div>
    <div style="margin:12px 20px;">
      <div class="risk_text">
        <p v-for="item in siteInfo.tradeAgreeText.split('。')" :key="item">{{item}}。</p>
      </div>

    </div>
  </div>

</template>
<script>
export default {
  data () {
    return {siteInfo: {}}
  },
  mounted () {
    this.getInfoSite()
  },
  methods: {
    goBack () {
      this.$router.back(-1)
    },
    async getInfoSite () {
        // 获取网站信息
        let result = await api.getInfoSite()
        if (result.status === 0) {
          this.siteInfo = result.data
        } else {
          this.$message.error(result.msg)
        }
    }
  }
}
</script>
<style lang="css" scoped>
  h1 {
    font-size: 0.25rem;
    text-align: center;
  }

  h4 {
    font-size: 13px;
    line-height: 25px;
    margin: 25px 0;
  }

  p {
    font-size: 12px;
    line-height: 25px;
    text-indent: 2em;
  }
</style>
