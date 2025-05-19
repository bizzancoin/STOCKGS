<template>
  <div class="wrapper">
    <mt-header fixed title="用户注册协议">
      <router-link to="/register" slot="left">
        <mt-button icon="back"></mt-button>
      </router-link>
    </mt-header>
    <div class="content" style="margin:20px;background:#fff;border-radius:5px;margin-top:60px;line-height:200%;">
        <p v-for="item in agreecontent.split('。')" :key="item">{{item}}。</p>
    </div>
    <div class="iframe-box">
      <canvas v-for="page in pages" :id="'the-canvas'+page" :key="page"></canvas>
      <!-- <iframe ref="iframeBox" class="iframe" :src="agreeUrl" width="100%" height="400%" scrolling="auto" frameborder="0"></iframe> -->
      <!-- <embed :src="agreeUrl" width="100%" height="100%" name="plugin" id="plugin" type="application/pdf" internalinstanceid="198"> -->
    </div>
  </div>
</template>
<script>
import { Toast } from 'mint-ui'
import * as api from '@/axios/api'
import PDFJS from 'pdfjs-dist'

// const Base64 = require('js-base64').Base64

export default {
  data () {
    return {
      agreeUrl: '', // 注册协议地址
      pdfDoc: null,
      agreecontent: '',
      pages: 0
    }
  },
  mounted: function () {
    this.getInfoSite()
    // let width = this.$refs.iframeBox
  },
  created () {
    window.addEventListener('onload', this.setIframeContent)
  },
  methods: {
    async getInfoSite () {
      // 获取 logo
      let data = await api.getInfoSite()
      if (data.status === 0) {
        this.logo = data.data.siteLogoSm
        this.agreeUrl = data.data.regAgree
        this.agreecontent = data.data.tradeAgreeText
        this._loadFile(this.agreeUrl)
      } else {
        Toast(data.msg)
      }
    },
    setIframeContent () {
      // let width = this.$refs.iframeBox
    },
    _renderPage (num) {
      this.pdfDoc.getPage(num).then((page) => {
        let canvas = document.getElementById('the-canvas' + num)
        let ctx = canvas.getContext('2d')
        let dpr = window.devicePixelRatio || 1
        let bsr = ctx.webkitBackingStorePixelRatio ||
          ctx.mozBackingStorePixelRatio ||
          ctx.msBackingStorePixelRatio ||
          ctx.oBackingStorePixelRatio ||
          ctx.backingStorePixelRatio || 1
        let ratio = dpr / bsr
        let viewport = page.getViewport(screen.availWidth / page.getViewport(1).width)
        canvas.width = viewport.width * ratio
        canvas.height = viewport.height * ratio
        canvas.style.width = viewport.width + 'px'
        canvas.style.height = viewport.height + 'px'
        ctx.setTransform(ratio, 0, 0, ratio, 0, 0)
        let renderContext = {
          canvasContext: ctx,
          viewport: viewport
        }
        page.render(renderContext)
        if (this.pages > num) {
          this._renderPage(num + 1)
        }
      })
    },
    _loadFile (url) {
      PDFJS.getDocument(url).then((pdf) => {
        this.pdfDoc = pdf
        this.pages = this.pdfDoc.numPages
        this.$nextTick(() => {
          this._renderPage(1)
        })
      })
    }
  }
}
</script>
<style lang="less" scoped>
  body {
    background-color: #fff;
  }

  .wrapper {
    width: 100%;
    height: 100%;
    padding-bottom: 0;
    overflow: hidden;
  }

  html, body {
    width: 100%;
    height: 100%;
  }

  body {
    -webkit-overflow-scrolling: touch;
    overflow-y: scroll;
  }

  .iframe-box {
    position: fixed;
    right: 0;
    bottom: 0;
    left: 0;
    top: 0;
    -webkit-overflow-scrolling: touch;
    overflow-y: scroll;
    width: 100%;
    height: 100%;

    overflow:scroll
    .iframe {
      overflow: scroll;
      width: 100%;
      height: 100%;

      body {
        overflow: auto;
      }
    }
  }
</style>
