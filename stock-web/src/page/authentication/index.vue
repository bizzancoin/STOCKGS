<template>
  <div class="bank_card_page">
    <div class="content">
      <div class="top_back">
        <div class="left_back_icon" @click="$router.go(-1)">
          <img src="../../assets/img/zuojiantou.png" alt />
        </div>
      </div>
      <div class="titles">
        <span>{{ $t('hj146') }}</span>
      </div>
      <div class="bank_name">
        <div class="lefts">
          <span>{{ $t('hj195') + ':' }}</span>
        </div>
        <div class="rights">
          <input v-if="showBtn" type="text" v-model="form.name" />

          <input v-if="!showBtn" type="text" v-model="form.name" readonly />
        </div>
      </div>
      <div class="bank_name">
        <div class="lefts">
          <span>{{ $t('hj196') + ':' }}</span>
        </div>
        <div class="rights">
          <input v-if="showBtn" type="text" v-model="form.idCard" />
          <input v-if="!showBtn" type="text" v-model="form.idCard" readonly />
        </div>
      </div>
      <div class="uploads">
        <div class="lefts">
          <el-upload :with-credentials="true" class="avatar-uploader" :action="admin + '/user/upload.do'"
            list-type="picture-card" name="upload_file" :show-file-list="false" :on-success="handleAvatarSuccess"
            :on-error="handleError" :before-upload="beforeAvatarUpload" :disabled="!showBtn">
            <img v-if="form.img1key" :src="form.img1key" class="id-img avatar" style="width: 100%;height: 100%;" />
            <i v-else class="iconfont icon-zhaopian"></i>
            <span v-if="!form.img1key && !imgStatus" class="btn-title">{{$t('hj197')}}</span>
            <span v-if="imgStatus" class="btn-title">{{$t('hj198')}}</span>
          </el-upload>
        </div>
        <div class="rights">
          <el-upload :with-credentials="true" class="avatar-uploader" :action="admin + '/user/upload.do'"
            list-type="picture-card" name="upload_file" :show-file-list="false" :on-success="handleAvatarSuccess2"
            :on-error="handleError2" :before-upload="beforeAvatarUpload2" :disabled="!showBtn">
            <img v-if="form.img2key" :src="form.img2key" class="id-img avatar" style="width: 100%;height: 100%;"/>
            <i v-else class="iconfont icon-zhaopian"></i>
            <span v-if="!form.img2key && !imgStatus2" class="btn-title">{{$t('hj199')}}</span>
            <span v-if="imgStatus2" class="btn-title">{{$t('hj198')}}</span>
          </el-upload>
        </div>
      </div>
      <div class="bank_name bind" @click="toSure" v-if="showBtn">
        <span>{{ $t('hj161') }}</span>
      </div>
      <van-notify v-model="messFlag" type="primary">
        <div>
          <div class="eft">
            <span>{{ $t('hj200') }}</span>
          </div>
          <div class="bft">
            <span>{{ $t('hj201') }}：{{ this.$store.state.userInfo.authMsg }}</span>
          </div>
        </div>
      </van-notify>
    </div>
    <div class="pass">
      <div class="top_t">
        <span v-if="this.$store.state.userInfo.isActive == 1" class="ise_pass">{{ $t('hj202') }}</span>
        <span v-if="!showBtn && this.$store.state.userInfo.isActive != 1">{{ $t('hj203') }}</span>
        <span v-if="showBtn" class="ise_pass">{{ $t('hj204') }}</span>
      </div>
    </div>
  </div>
</template>

<script>
import * as api from "@/axios/api";
import { Toast } from "mint-ui";
import { isNull, idCardReg, isName } from "@/utils/utils";
import { compress } from "@/utils/imgupload";

export default {
  name: "bankCard",
  data() {
    return {
      form: {
        phone: "",
        name: "",
        idCard: "",
        img1key: "",
        img2key: "",
        img3key: ""
      },
      img1Key: "",
      img2Key: "",
      img3Key: "",
      showBtn: true,
      admin: "",
      imgStatus: false,
      imgStatus2: false,
      messFlag: this.$store.state.userInfo.isActive == 3 ? true : false
    };
  },
  created() {
    this.getUserInfo();
  },
 
  beforeDestroy() {
    if (this.$state.theme == "red") {
      document.body.classList.remove("red-bg");
      document.body.classList.add("black-bg");
    }
  },
  mounted() {
    if (this.$state.theme == "red") {
      document.body.classList.remove("black-bg");
      document.body.classList.add("red-bg");
    }
    this.admin = process.env.API_HOST;
    console.log(this.admin)
    if (this.admin == undefined) {
      this.admin = "https://apisto.bizzan.site";
    }
  },
  methods: {
    handleAvatarSuccess(res, file) {
      this.imgStatus = false;
      this.form.img1key = res.data.url;
    },
    beforeAvatarUpload(file) {
      this.imgStatus = true;
    },
    handleError() {
      this.imgStatus = false;
    },
    handleAvatarSuccess2(res, file) {
      this.imgStatus2 = false;
      this.form.img2key = res.data.url; // URL.createObjectURL(file.raw);
    },
    beforeAvatarUpload2(file) {
      this.imgStatus2 = true;
      // const _that = this
      const isLt10M = file.size / 1024 / 1024 < 10;
      if (!isLt10M) {
        this.$message.error(this.$t('hj205'));
        return false;
      } else {
        this.form.img2key = URL.createObjectURL(file);
        compress(file, function (val) { });
      }
    },
    handleError2() {
      this.imgStatus2 = false;
    },
    handleAvatarSuccess3(res, file) {
      this.form.img3key = res.data.url; // URL.createObjectURL(file.raw);
    },
    async getUserInfo() {
    // 获取用户信息
    let data = await api.getUserInfo();
    if (data.status === 0) {
      // 判断是否登录
      this.$store.commit('dialogVisible', false);
      this.$store.state.userInfo = data.data;
      this.userInfo = data.data;
      if (
        this.$store.state.userInfo.isActive === 1 ||
        this.$store.state.userInfo.isActive === 2
      ) {
        this.form.idCard = this.$store.state.userInfo.idCard;
        this.form.name = this.$store.state.userInfo.realName;
        this.form.img1key = this.$store.state.userInfo.img1Key;
        this.form.img2key = this.$store.state.userInfo.img2Key;
        //   this.form.img3key = this.$store.state.userInfo.img3Key
        this.showBtn = false;
      }
    } else {
      //this.$store.commit('dialogVisible',true);
      //跳转到login
      this.$router.push({ path: "/login" });
    }
  },
    beforeAvatarUpload3(file) { },
    // 上传
    handleFile: function (e) {
      // var that = this
      let $target = e.target || e.srcElement;
      let file = $target.files[0];
      // if(file.size > 1024 * 1024 *20){
      console.log(file, "file");
      let i = false;
      if (i) {
        Toast(this.$t('hj206'));
      } else {
        // Indicator.open('Loading...')
        this.img1Key = file;
        // this.$refs.formDate.submit()
        // this.uploadIdImg({upload_file:file})
        var reader = new FileReader();
        reader.onload = data => {
          let res = data.target || data.srcElement;
          this.form.img1Key = res.result;
          // Indicator.close()
        };
        // reader.onloadend = () => {
        //   Indicator.close()
        // }
        reader.readAsDataURL(file);
      }
    },
    toSure() {
      // 实名认证弹框
      if (isNull(this.form.name) || !isName(this.form.name)) {
        Toast(this.$t('hj207'));
      } else if (isNull(this.form.idCard) || !idCardReg(this.form.idCard)) {
        Toast(this.$t('hj208'));
      } else if (isNull(this.form.img1key) || isNull(this.form.img2key)) {
        Toast(this.$t('hj209'));
      } else {
        // 显示确认弹窗
        this.toAuthentication();
      }
    },
    async toAuthentication() {
      let opts = {
        realName: this.form.name,
        idCard: this.form.idCard,
        img1key: this.form.img1key,
        img2key: this.form.img2key,
        img3key: this.form.img3key
      };
      let data = await api.userAuth(opts);
      if (data.status === 0) {
        Toast(this.$t('hj210'));
        this.goBack();
      } else {
        Toast(data.msg);
      }
    },
    goBack() {
      this.$router.back(-1);
    }
  }
};
</script>

<style scoped lang="less">
.bank_card_page {
  width: 100%;
  height: 100%;
  background: #fff;
  position: relative;

  .content {
    width: 100%;
    height: 100%;
    padding: 0 0.6rem;

    .top_back {
      width: 100%;
      height: 2rem;

      >div {
        width: 10%;
        height: 100%;
        display: flex;
        align-items: center;
      }

      img {
        width: 0.6rem;
        height: 0.6rem;
      }
    }

    .titles {
      width: 100%;
      height: 1.5rem;
      font-size: 0.641rem;
      margin-top: 1rem;

      span {
        font-weight: 600;
      }
    }
  }

  .bank_name {
    width: 100%;
    height: 1.5rem;
    display: flex;
    padding: 0 0.2rem;
    background: rgb(247, 247, 247);
    border-radius: 0.2rem;
    margin-top: 0.3rem;

    .lefts {
      width: 25%;
      height: 100%;
      display: flex;
      align-items: center;
      font-size: 0.3846rem;

      span {
        font-weight: 600;
      }
    }

    .rights {
      width: 75%;
      height: 100%;
      display: flex;
      align-items: center;
      justify-content: center;

      input {
        width: 100%;
        height: 100%;
      }
    }
  }
}

.bank_name.bind {
  display: flex;
  align-items: center;
  justify-content: center;
  background: #2d6ae9;
  font-size: 0.4103rem;
  color: #fff;

  span {
    font-weight: 600;
  }
}

.uploads {
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-top: 0.3rem;

  >div {
    width: 48%;
    background: rgb(247, 247, 247);
  }
}

/deep/ .el-upload--picture-card {
  background: none;
  width: 100%;
  height: 1.6rem;
  line-height: 1.6rem;
}

/deep/ .el-upload__input {
  display: none;
}

.pass {
  width: 70%;
  height: auto;
  position: absolute;
  top: 1.72rem;
  right: .2rem;
  opacity: 0.2;

  .top_t {
    width: 100%;
    height: auto;
    display: flex;
    justify-content: flex-end;
    font-size: 3.2821rem;
    color: #99b8f5;
    align-items: flex-end;
  }

  .ise_pass {
    font-size: 2.2rem;
  }
}
</style>