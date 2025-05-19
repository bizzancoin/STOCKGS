<template>
    <div class="bijnm">
        <div class="headf">
            <div>
                <h2><span class="hbnh"><a class="fan" @click="$router.back()"></a></span> {{ title }}</h2>
            </div>
        </div>
        <div class="hezi" v-show="type == '1'">
            <img src="~@/assets/imgRed/touxiang.jpg">
            <h6>软件名称</h6>
            <p>版本号：V1.1.2</p>
        </div>
        <div v-show="type != '1'" class="hezi xxxxxxx" style="text-align: center !important;line-height: 0.5rem;">
            {{ type == 2 ? userInfo.companyInfo : type == 3 ? userInfo.certImg1 : type == 4 ? userInfo.certImg2 : type == 5 ?
                    userInfo.tradeAgreeText : type == 6 ? "如需此项服务请联系您的客户经理" : ''
            }}</div>
    </div>
</template>
<script>
import * as api from "@/axios/api";
export default {
    name: 'about',
    created() {
        var that = this;
        //接收页面传值
        console.log(this.$route.query.e);
        if (this.$route.query.e) {
            switch (this.$route.query.e) {
                case '1':
                    this.title = "版本更新"

                    break;
                case '2':
                    this.title = "用户协议"
                    break;
                case '3':
                    this.title = "隐私协议"
                    break;
                case '4':
                    this.title = "关于我们"
                    break;
                case '5':
                    this.title = "交易规则"
                    break;
                case '6':
                    this.title = "暂未开放"
                    break;
                default:
                    break;
            }
            this.type = this.$route.query.e;
        }
    },
    data() {
        return {
            title: '关于我们',
            type: 0,
            userInfo: {},
        }
    },
    mounted() {
        this.getUserInfo();
    },
    methods: {
        async getUserInfo() {
            // 获取用户信息
            let data = await api.getInfoSite();
            if (data.status === 0) {
                this.userInfo = data.data;

            } else {

            }
        },
    }
}
</script>
<style scoped>
.bijnm {
    background: #fff;
    min-height: 100vh;
}

.headf {
    width: 100%;
    height: 1.1748rem;
    background: linear-gradient(-55deg, rgb(80, 122, 250), rgb(115, 131, 251));
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

.fan {
    width: 0.2403rem;
    height: 0.4272rem;
    background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABIAAAAgCAYAAAAffCjxAAAAAXNSR0IArs4c6QAAAVdJREFUSEut1usqBVEYxvH/c1MuRJJDkhwTEpIkJUlyCEk++CJJckpycw49Gs3Wa+y9Z82ePd/Xr5n38KwRNR7bE0APMK1OHdtTwAWQGZcdQbZngPMc+QImK0O2Z4GzgIxLuqoE2Z4DTgMyJuk6K08yZHseOAnIqKSbRo2TINsLwHFARiTdxkaVQrYXgaOADEu6K3a7LWR7CTjMD2XdaYq0rZHtFWA/Rz6BIUn3reau6RvZXgX28kMfOfLQbnj/QbbXgN380HuOPJZtwB/I9jqwE5ABSc9lyJ8a2d4AtgPSL+klBfmFbG8CWwHpk/SaivxAtjMgg7Inq0mvpLcqSHehTOvKpzU+oSvFDlj99gesOJCDkp7Kip+yIknT3XL7C0tbum9lMbIMHKQkQEqwJWVSKZTPWTElq0dt6GYxt6uHf8DqX0cBq39BBqz+lR2w+j8RAfv9rfkGqF24CUdT9E4AAAAASUVORK5CYII=) no-repeat 50%;
    background-size: 100%;
    display: inline-block;
    margin-right: 0.1335rem;
    vertical-align: middle;
    margin-top: -0.0534rem;
}

.hezi {
    width: 9.2115rem;
    border-bottom: 0.0267rem solid #e0e0e0;
    margin: 0 auto;
    margin-top: 1.1748rem;
    text-align: center;
    padding-bottom: 0.534rem;
}
</style>