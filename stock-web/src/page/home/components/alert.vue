<template>
  <div class="wrapper">
    <div class="header">
      <mt-header title="公告">
        <router-link to="/home" slot="left">
          <mt-button icon="back">返回</mt-button>
        </router-link>
      </mt-header>
    </div>
    <div class="introduce">
      <h2 class="title text-center">{{artList.artTitle}}</h2>
      <div class="introduce-body">
        <p class="text-center origin">
          {{artList.author}} {{new Date(artList.addTime) | timeFormat}}
          <!-- <span class="pull-right"><i class="iconfont icon-yanjing"></i>{{artList.hitTimes?artList.hitTimes:0}}</span> -->
        </p>
        <div class="summary">
          <pre>{{artList.artSummary}}</pre>
        </div>
        <!-- <div class="risk-box">
          <img class="cert-img" :src="artList.artImg" alt="">
        </div> -->
        <div class="content">
          <pre>{{artList.artCnt}}</pre>
        </div>
        <div class="bottom-box ">
          <p class="pull-right">{{siteInfo.siteName}}</p>
          <div class="clearfix">
            <p class="pull-right">{{new Date(artList.addTime) | timeFormat}}</p>
          </div>
        </div>

      </div>
    </div>
    <div class="alert-box">
      免责声明：本文观点仅代表作者个人观点，不构成平台的投资建议，本平台不对文章的准确性、完整性和及时性做出任何保证，亦不对因使用或信赖文章信息引发的任何损失承担责任。
    </div>
  </div>
</template>

<script>
import { Toast } from 'mint-ui'
import * as api from '@/axios/api'

export default {
  components: {},
  props: {},
  data () {
    return {
      artList: '',
      siteInfo: {}
    }
  },
  watch: {},
  computed: {},
  created () {},
  mounted () {
    this.getArtList()
    this.getInfoSite()
  },
  methods: {
    async getInfoSite () {
      // 获取设置信息
      let result = await api.getInfoSite()
      if (+result.status === 0) {
        this.siteInfo = result.data
      } else {
        this.$message.error(result.msg)
      }
    },
    async getArtList () {
      // 获取公告详情
      let result = await api.getArtDetail({ artId: this.$route.query.id })
      if (+result.status === 0) {
        this.artList = result.data
      } else {
        Toast(result.msg)
      }
    }
  }
}
</script>
<style lang="less" scoped>
  .introduce {
    // color: #fff;
    min-height: 400px;
    width: 100%;
    margin: 10px auto;

    .title {
      font-size: 20px;
      line-height: 38px;
    }

    .introduce-body {
      // color: #666;
      line-height: 28px;
      font-size: 14px;
      width: 92%;
      margin: 0 auto;

      p {
        margin-bottom: 10px;
      }

      .origin {
        font-size: 12px;
        // color: #9c9c9c;
      }

      .content {
        font-size: 18px;
        text-indent: 2em;
        text-indent: 2em;
        margin-top: 20px;
      }

      .bottom-box {
        padding-top: 0.5rem;

        p {
          margin-bottom: 0.1rem;
        }
      }
    }

    .risk-box {
      margin-top: 25px;
      text-align: center;
    }

    .cert-img {
      max-height: 300px;
      max-width: 100%;
    }

  }

  pre {
    white-space: pre-wrap;
    word-wrap: break-word;
    font-size: 0.24rem;
    // color:#ccc;
    background: none;
    overflow: inherit;
    line-height: 0.36rem;
  }

  .alert-box {
    color: #666;
    font-size: 0.24rem;
    background: #1e2125;
    padding: 0.2rem;
    line-height: 0.32rem;
  }

  .wrapper {
    padding: 0;
  }
</style>
