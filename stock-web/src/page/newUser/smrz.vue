<template>
    <div>
        <div class="heand">
            <div data-v-7793a11f="" class="ert">
                <h2 data-v-7793a11f=""><span data-v-7793a11f="" class="hbnh"><a data-v-7793a11f="" class="fan"
                            @click="$router.go(-1)"></a></span> 实名认证 <a data-v-7793a11f="" class="xind"
                        style="display: none;">新增</a><a data-v-7793a11f="" class="shaux" style="display: none;"></a><a
                        data-v-7793a11f="" class="xind" style="display: none;">交易记录</a></h2>
            </div>
        </div>
        <ul class="verul">
            <li class="verli"><input v-if="showBtn" placeholder="请输入姓名" v-model="form.name" /> <input
                    placeholder="请输入姓名" v-model="form.name" v-if="!showBtn" readonly /></li>
            <li class="verli"><input placeholder="请输入身份证号码" v-model="form.idCard" v-if="showBtn" /><input
                    placeholder="请输入身份证号码" v-model="form.idCard" v-if="!showBtn" readonly /></li>
        </ul>
        <div class="sfz">
            <div class="let">
                <h6>身份证正面</h6>
                <p>上传您的身份证正面</p>
            </div>
            <div class="rih">
                <el-upload :with-credentials="true" class="avatar-uploader" :action="admin + 'user/upload.do'" :class="form.img1key?'tou':'butou'"
                    list-type="picture-card" name="upload_file" :show-file-list="false"
                    :on-success="handleAvatarSuccess" :on-error="handleError" :before-upload="beforeAvatarUpload"
                    :disabled="!showBtn"  :headers="headers">
                    <img v-if="form.img1key" :src="form.img1key" class="id-img avatar"
                        style="width: 100%;height: 100%;"  v-show="form.img1key" />
                    <i v-else class="iconfont icon-zhaopian"></i>
                    <span v-if="!form.img1key && !imgStatus" class="btn-title">{{ $t('hj197') }}</span>
                    <span v-if="imgStatus" class="btn-title">{{ $t('hj198') }}</span>
                </el-upload>
            </div>
        </div>
        <div class="sfz">
            <div class="let">
                <h6>身份证反面</h6>
                <p>上传您的身份证反面</p>
            </div>
            <div class="rih fanmian">
                <!-- <img  :src="imgUrl1" v-show="imgUrl1">
                <input
                     accept="image/*" type="file" class="inp"> -->

                <el-upload :with-credentials="true" class="avatar-uploader"  :action="admin + 'user/upload.do'"  :class="form.img2key?'tou':'butou'"
                    list-type="picture-card" name="upload_file" :show-file-list="false"
                    :on-success="handleAvatarSuccess2" :on-error="handleError2" :before-upload="beforeAvatarUpload2"
                    :disabled="!showBtn" :headers="headers">
                    <img v-if="form.img2key" :src="form.img2key" class="id-img avatar" style="width: 100%;height: 100%;"
                         />
                    <i v-else class="iconfont icon-zhaopian"></i>
                    <span v-if="!form.img2key && !imgStatus2" class="btn-title">{{ $t('hj199') }}</span>
                    <span v-if="imgStatus2" class="btn-title">{{ $t('hj198') }}</span>
                </el-upload>
            </div>
        </div>
        <div class="enter" @click="toSure" v-if="showBtn">提交</div>
    </div>
</template>
<script>
import APIUrl from "@/axios/api.url";
import * as api from "@/axios/api";
import { Toast } from "mint-ui";
import { isNull, idCardReg, isName } from "@/utils/utils";
import { compress } from "@/utils/imgupload";
export default {
    data() {
        return {
            imgUrl: '',
            imgUrl1: '',
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
            admin: APIUrl.baseURL,
            headers:{
                'USERTOKEN':localStorage.getItem("USERTOKEN")
            },
            imgStatus: false,
            imgStatus2: false,
            messFlag: this.$store.state.userInfo.isActive == 3 ? true : false
        }
    },
    mounted(){
        this.getUserInfo()
    //     if (this.$state.theme == "red") {
    //   document.body.classList.remove("black-bg");
    //   document.body.classList.add("red-bg");
    // }
    // this.admin = process.env.API_HOST;

    // //修改getElementsByName('upload_file')的透明度
    // var upload_file = document.getElementsByName('upload_file');
    // for (var i = 0; i < upload_file.length; i++) {
    //   upload_file[i].style.opacity = 0;
    // }
    // if (this.admin == undefined) {
    //   this.admin = "http://143.92.58.242:8091";
    // }
    
    },
    methods: {

        //图片上传
        // async handleChange(e) {
        // this.imgname = e.target.files[0].name.split(".")[0];
        // const files = e.target.files;
        // const data = new FormData();
        // data.append("file", files[0]);
        // let res = await api.uploadimg(data);
        // if (res.status == 200) {
        //     this.imgUrl = APIUrl.url + res.data;
        // }
        // console.log(this.imgUrl)
        // },
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
}
</script>
<style lang="less" scoped >
.avatar-uploader{
    overflow: hidden;
    
}
/deep/ .avatar-uploader .el-upload__input{
        opacity: 0 !important;
    }
.heand {
    width: 100%;
    height: 6.141rem;
    background: url(~@/assets/imgRed/beij.cb205259.png) no-repeat 50%;
    background-size: 100%;
}

.ert {
    position: relative;
}

h2 {
    text-align: center;
    height: 1.2549rem;
    width: 100%;
    position: relative;
    line-height: 1.2549rem;
    font-size: 0.4806rem;
    color: #fff;
    background: transparent;
    font-weight: 500;
    z-index: 3;
}

.hbnh {
    position: absolute;
    left: 0.4005rem;
    font-size: 0.4272rem;
    font-weight: 500;
}

.hbnh .fan {
    width: 0.2403rem;
    height: 0.4272rem;
    background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABIAAAAgCAYAAAAffCjxAAAAAXNSR0IArs4c6QAAAVdJREFUSEut1usqBVEYxvH/c1MuRJJDkhwTEpIkJUlyCEk++CJJckpycw49Gs3Wa+y9Z82ePd/Xr5n38KwRNR7bE0APMK1OHdtTwAWQGZcdQbZngPMc+QImK0O2Z4GzgIxLuqoE2Z4DTgMyJuk6K08yZHseOAnIqKSbRo2TINsLwHFARiTdxkaVQrYXgaOADEu6K3a7LWR7CTjMD2XdaYq0rZHtFWA/Rz6BIUn3reau6RvZXgX28kMfOfLQbnj/QbbXgN380HuOPJZtwB/I9jqwE5ABSc9lyJ8a2d4AtgPSL+klBfmFbG8CWwHpk/SaivxAtjMgg7Inq0mvpLcqSHehTOvKpzU+oSvFDlj99gesOJCDkp7Kip+yIknT3XL7C0tbum9lMbIMHKQkQEqwJWVSKZTPWTElq0dt6GYxt6uHf8DqX0cBq39BBqz+lR2w+j8RAfv9rfkGqF24CUdT9E4AAAAASUVORK5CYII=) no-repeat 50%;
    background-size: 100%;
    display: inline-block;
    margin-right: 0.1335rem;
    vertical-align: middle;
    margin-top: -0.0534rem;
}

.verul {
    width: 100%;
    min-height: 2.136rem;
    margin: 0 auto;
    margin-top: -1.8156rem;
    background: #fff;
    padding-top: 0.801rem;
    border-radius: 0.4005rem 0.4005rem 0 0;
}

.verul .verli {
    width: 9.345rem;
    height: 1.1748rem;
    background: #e6e6e6;
    border-radius: 0.1335rem;
    margin: 0 auto;
    margin-bottom: 0.4005rem;
}

.verli input {
    width: 7.476rem;
    height: 1.1748rem;
    background: transparent;
    font-size: 0.4005rem;
    margin-left: 0.4005rem;
    color: #000;
}

.sfz {
    width: 9.2115rem;
    background: #e6e6e6;
    margin: 0 auto;
    margin-top: 0.4539rem;
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    -webkit-box-pack: justify;
    -ms-flex-pack: justify;
    justify-content: space-between;
    border-radius: 0.1602rem;
}

.sfz .let {
    width: 4.1652rem;
    text-align: center;
}

.sfz .let h6 {
    font-size: 0.4005rem;
    font-weight: 500;
    color: #000;
    margin-top: 0.9612rem;
}

.let p {
    font-size: 0.267rem;
    font-weight: 500;
    color: #333;
    opacity: .5;
    margin-top: 0.4005rem
}

.sfz .rih {
    width: 4.1652rem;
    height: 2.6967rem;
    background: url(~@/assets/imgRed/sfzz.0e35404d.png) no-repeat 50%;
    background-size: 100%;
    margin-right: 0.2937rem;
    margin-top: 0.3471rem;
    margin-bottom: 0.3471rem;
    position: relative;
}

.sfz .rih .inp {
    opacity: 0;
}

.sfz .rih .inp,
.sfz .rih img {
    position: absolute;
    width: 100%;
    height: 100%;
    left: 0;
    top: 0;
}

.rih .inp,
.sfz .rih img {
    position: absolute;
    width: 100%;
    height: 100%;
    left: 0;
    top: 0;
}

.enter {
    width: 9.345rem;
    height: 1.1748rem;
    background: linear-gradient(-55deg, rgb(80, 122, 250),rgb(115, 131, 251));
    border-radius: 0.1335rem;
    text-align: center;
    color: #fff;
    font-size: 0.4272rem;
    line-height: 1.1748rem;
    margin: 0.801rem auto;
}

input::-webkit-input-placeholder {
    color: #999;

}
.tou{
    opacity: 1;
}
.butou{
    opacity: 0;
}

</style>