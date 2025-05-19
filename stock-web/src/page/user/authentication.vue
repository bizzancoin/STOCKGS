<template>
  <div class="wrapper">
    <!-- <div class="header">
      <mt-header title="实名认证">
        <router-link to="/user" slot="left">
          <mt-button icon="back">我的</mt-button>
        </router-link>
        <mt-button icon="more" slot="right"></mt-button>
      </mt-header>
    </div> -->
    <div class="box transaction">
      <div class="box-contain clearfix">
        <div class="empty text-center">
          <!-- 您已通过实名认证 -->
          <!-- 审核中 -->
          <i v-show="this.$store.state.userInfo.isActive == 1" style="color:red;font-size: 1.5rem;"
             class="iconfont icon-shenhezhong"></i>
             <!-- 通过 -->
          <i v-show="!showBtn && this.$store.state.userInfo.isActive != 1" style="color:red;font-size: 1.5rem;"
             class="iconfont icon-tongguo1"></i>
             <!-- 未通过 -->
          <i v-show="showBtn" style="color:red;font-size: 1.5rem;" class="iconfont icon-icon-test"></i>
        </div>
      </div>
    </div>
    <div class="form-block">
      <div class="auth-msg" v-if="this.$store.state.userInfo.isActive == 3">
        <p>认证失败，请重新认证</p>
        <div>
          失败原因：{{this.$store.state.userInfo.authMsg}}
        </div>
      </div>
      <!-- <mt-field label="手机号" placeholder="请输入您的手机号" v-model="form.phone"></mt-field> -->
      <mt-field label="真实姓名" placeholder="请输入您的真实姓名" type="text" v-model="form.name"></mt-field>
      <mt-field label="身份证号" placeholder="请输入您的身份证号" type="text" v-model="form.idCard"></mt-field>
    </div>
    <div class="upload-box clearfix">
      <!-- <form action=""> -->
      <div class="upload-btn">
        <el-upload
          :with-credentials='true'
          class="avatar-uploader"
          :action="admin+'/user/upload.do'"
          list-type="picture-card"
          name="upload_file"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :on-error='handleError'
          :before-upload="beforeAvatarUpload">
          <img v-if="form.img1key" :src="form.img1key" class="id-img avatar">
          <i v-else class="iconfont icon-zhaopian"></i>
          <span v-if="!form.img1key && !imgStatus" class="btn-title">身份证正面</span>
          <span v-if="imgStatus" class="btn-title">正在上传中...</span>
        </el-upload>

      </div>
      <div class="upload-btn">
        <el-upload
          :with-credentials='true'
          class="avatar-uploader"
          :action="admin+'/user/upload.do'"
          list-type="picture-card"
          name="upload_file"
          :show-file-list="false"
          :on-success="handleAvatarSuccess2"
          :on-error='handleError2'
          :before-upload="beforeAvatarUpload2">
          <img v-if="form.img2key" :src="form.img2key" class="id-img avatar">
          <i v-else class="iconfont icon-zhaopian"></i>
          <span v-if="!form.img2key && !imgStatus2" class="btn-title">身份证背面</span>
          <span v-if="imgStatus2" class="btn-title">正在上传中...</span>
        </el-upload>

      </div>

    </div>
    <div class="rule-box">
      <div class="title">认证规则：</div>
      <ul>
        <li>1、新用户注册后必须通过实名认证审核。</li>
        <li>2、姓名和身份证号码一经认证不予修改，修改请联系客服。</li>
        <li>3、真实姓名必须和出金银行卡户名一致。</li>
      </ul>
    </div>
    <div v-show="showBtn" class="btnbox">
      <span class="text-center btnok" @click="toSure">确定</span>
    </div>

  </div>
</template>

<script>
import * as api from '@/axios/api'
import { Toast } from 'mint-ui'
import { isNull, idCardReg, isName } from '@/utils/utils'
import { compress } from '@/utils/imgupload'

export default {
  components: {},
  props: {},
  data () {
    return {
      form: {
        phone: '',
        name: '',
        idCard: '',
        img1key: '',
        img2key: '',
        img3key: ''
      },
      img1Key: '',
      img2Key: '',
      img3Key: '',
      showBtn: true,
      admin: '',
      imgStatus: false,
      imgStatus2: false
    }
  },
  watch: {},
  computed: {},
  created () {
    if (this.$store.state.userInfo.isActive === 1 || this.$store.state.userInfo.isActive === 2) {
      this.form.idCard = this.$store.state.userInfo.idCard
      this.form.name = this.$store.state.userInfo.realName
      this.form.img1key = this.$store.state.userInfo.img1Key
      this.form.img2key = this.$store.state.userInfo.img2Key
      //   this.form.img3key = this.$store.state.userInfo.img3Key
      this.showBtn = false
    }
  },
  beforeDestroy () {
    if (this.$state.theme =='red') {
      document.body.classList.remove('red-bg')
        document.body.classList.add('black-bg')
    }
  },
  mounted () {
    if (this.$state.theme =='red') {
        document.body.classList.remove('black-bg')
        document.body.classList.add('red-bg')
    }
    this.admin = process.env.API_HOST
    if (this.admin === undefined) {
      this.admin = ''
    }
  },
  
}
</script>
<style lang="less" scoped>
  .transaction {
    color: rgba(100, 100, 100, 0.78);

    .empty {
      width: 100%;
      // height: 1.34rem;
      font-size: 0.43rem;
      color: #888888;
      text-align: center;
      line-height: 2rem;
      // background: url('../../assets/img/thingsOk.png') no-repeat center center;
      background-size: 70%;
    }
  }

  .rule-box {
    padding: 0.2rem 0.3rem;

    .title {
      font-size: 0.3rem;
      height: 0.5rem;
      line-height: 0.5rem;
      margin-bottom: 0.2rem;
    }

    ul {
      li {
        color: #999;
        line-height: 0.5rem;
      }
    }
  }

  .upload-box {
    padding: 0.5rem;

    .upload-btn {
      // border: 1px solid #ddd;
      border-radius: 4px;
      width: 40%;
      height: 1.6rem;
      margin-bottom: 10px;
      float: left;
      margin: 0.2rem 5%;
      text-align: center;
      position: relative;

      .btn-hidden {
        height: 100%;
        width: 100%;
        position: absolute;
        top: 0;
        left: 0;
        z-index: 3;
        opacity: 0;
      }

      .id-img {
        max-width: 100%;
        max-height: 100%;
      }

      /deep/ .el-upload--picture-card {
        background: none;
        width: 100%;
        height: 1.6rem;
        line-height: 1.6rem;
      }

      .btn-title {
        position: absolute;
        top: 23px;
        left: 0;
        width: 100%;
      }

      /deep/ .el-upload__input {
        display: none;
      }

    }

  }

  .auth-msg {
    padding: 0.2rem 0.6rem;
    line-height: 0.4rem;

    p {
      color: red;
    }

    div {
      color: #ddd;
    }
  }

</style>
