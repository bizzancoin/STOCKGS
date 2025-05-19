<template>
  <div class="wrapper">
    <mt-header fixed :title="siteInfo.tradeAgreeTitle">
      <router-link to="/buy" slot="left">
        <mt-button icon="back"></mt-button>
      </router-link>
    </mt-header>
    <div class="content" style="margin:20px;background:#fff;border-radius:5px;margin-top:60px;line-height:200%;">
      <p v-for="item in tradeAgreeText.split('。')" :key="item">{{item}}。</p>
    </div>
    <!-- <iframe class="iframe" :src="agreeUrl" frameborder="0"></iframe> -->
    <canvas v-for="page in pages" :id="'the-canvas'+page" :key="page"></canvas>

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
      pages: 0,
      tradeAgreeText: '',
      siteInfo: {}
    }
  },
  mounted: function () {
    this.getInfoSite()
  },
  methods: {
    async getInfoSite () {
      // 获取 logo
      let data = await api.getInfoSite()
      if (data.status === 0) {
        this.siteInfo = data.data
        this.logo = data.data.siteLogoSm
        this.agreeUrl = data.data.tradeAgree
        this.tradeAgreeText = data.data.tradeAgreeText
        this._loadFile(data.data.tradeAgree)
      } else {
        Toast(data.msg)
      }
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
    height: 100%;
    padding-bottom: 0;
  }

  .iframe {
    width: 100%;
    height: 100%;
    min-height: 16rem;
  }

  .mint-field .mint-cell-value {
    padding-left: 0;
  }

  .mint-popup-box {
    // width: 100%;
    // height: 100%;
    // background:#fff;
    .title {
      font-size: 0.43rem;
      margin-bottom: 0.34rem;
      // margin-top: 1.40rem;
      padding: 0.1rem 0.4rem;
      color: #333;
    }

    .mint-cell {
      background: none;
      color: #000;
    }

    /deep/ .mint-cell-text {
      color: #000;
    }

    .mint-cell-wrapper {
      border: 0.02rem solid #ddd;
    }

    .mint-button {
      margin-top: 0.2rem;
      width: 60%;
    }

    .check-box {
      .text-center {
        padding-top: 0.2rem;
      }

      p {
        padding: 0.2rem 0.4rem;
      }

    }
  }

  .check-box-wrap {
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.5);
    position: fixed;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;

    .check-box {
      width: 60%;
      height: 2rem;
      background: #fff;
      position: absolute;
      top: 20%;
      left: 0;
      right: 0;
      margin: 0 auto;
      border-radius: 0.2rem;
      padding: 0.2rem;

      .mint-cell {
        background: none;
      }
    }
  }

  .text-center {
    padding: 0.52rem;
  }

  img.banenr {
    width: 60%;
    width: 36%;
    height: auto;
  }

  .forms {
    // width: 92%;
    margin: 0.3rem 0;
    // height: 110px;
    // float: left;
    border-radius: 8px;
    // background: #ffffff;
    /* box-shadow: 1px 1px 10px #cfcfcf; */
  }

  .form-view {
    width: 90%;
    // height: 41px;
    height: 1rem;
    position: relative;
    margin: 0 auto;
    margin-bottom: 0.14rem;

    .iconfont {
      padding: 0.3rem;
      vertical-align: middle;
      position: absolute;
      font-size: 0.4rem;
    }

    .icon-lr_password {
      font-size: 0.5rem;
    }

    .icon-tuijian {
      font-size: 0.5rem;
    }
  }

  .form-view:first-child {
    margin-top: 12px;
  }

  .form-view input {
    display: block;
    width: 100%;
    // height: 40px;
    height: 1rem;
    /* margin: 0 auto; */
    // font-size: 13px;
    border: none;
    border-radius: 0;
    line-height: 40px;
    text-indent: 1.2rem;
    border-bottom: 0.028rem solid #676b6f;

    &:focus {
      border: 0 solid #ccc !important;
      border-radius: 0.695rem;
      box-shadow: 0 0 0.28rem #676b6f;
      border-bottom: none;
    }
  }

  .form-ic {
    width: 20px;
    height: 20px;
    position: absolute;
    top: 10px;
    left: 5px;
  }

  .getcode {
    position: absolute;
    right: 0.2rem;
    line-height: 0.9rem;
    color: #d50000;
    border-radius: 10px;
    padding: 0 0.2rem;
    top: 0.05rem;
  }

  .chebox {
    width: 90%;
    float: left;
    padding-left: 1%;
  }

  .checked {
    position: relative;
    display: block;
    /* float: left; */
    margin: 0 auto;
    font-size: 0.25rem;
    padding-left: 22px;
  }

  input[type='checkbox'] {
    display: none;
  }

  label {
    position: absolute;
    top: 1px;
    left: 1px;
    width: 12px;
    height: 12px;
    border-radius: 10px;
    border: 1px solid #ff9600;
    background: white;
  }

  input[type='checkbox']:checked + label:before {
    content: '';
    position: absolute;
    top: 0px;
    left: 3px;
    width: 3px;
    height: 8px;
    border: solid #ff9600;
    border-width: 0 2px 2px 0;
    transform: rotate(45deg);
  }

  .btnbox {
    /* float: left; */
    width: 100%;
  }

  .btnok {
    display: block;
    width: 85%;
    height: 0.92rem;
    margin: 30px auto 0 auto;
    font-size: 14px;
    color: #ffffff;
    text-align: center;
    border-radius: 0.46rem;
    background: #d50000;
  }

  .aggre {
    color: #ff7e00;
  }

  .back {
    font-size: 0.25rem;
    margin-top: 0.3rem;
    text-align: center;
    color: #666;
  }

  .back a {
    border-bottom: 1px solid #888;
    padding-bottom: 0.06rem;

  }
</style>
