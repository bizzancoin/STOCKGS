<template>
    <div class="bijnm">
        <div class="headf">
            <div >
                <h2 ><span  class="hbnh"><a 
                            @click="$router.go(-1)" class="fan"></a></span> 设置 </h2>
            </div>
        </div>
        <ul class="ulk">
            <li><span style="line-height: 1.1748rem;">头像</span><img
                src="~@/assets/imgRed/touxiang.jpg"
                    class="heasd"/></li>
            <li><span>手机号</span><a>{{ userInfo.phone ? userInfo.phone : userInfo.phone }}</a></li>
            <li @click="handleOutLoginClick()"><span>退出登录</span><img
                    src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAAXNSR0IArs4c6QAAAjVJREFUWEfll7+LE1EQx7+zEcMhBOFKRXG1sLFIZjcaRcxp652lP2oLz0JEsRFBOBubKxS8K87CRhBSKAfiIXKVJshm9j/wam0EiyhZyI5MSCCGCDHydgW32OLt2zefN+8782YIAMIwPJmm6QMANQBzNubg+a6qTSK6JyIfh+tTpVI5TUTvAOx2YHTSkonneQtRFDXtIwVB0FRV23lCRGuq+tkFiKruJ6JlAAUA2yJyrg/AzF3bvaq+iOP4sgvjwzWDINhU1UUAX0VkfgiggwnrInLdJQAzPwdwBcA3Edn7XwKsArgF4JOIHMncA7Vaba7b7S4VCoUoiqKdzAEm6cuiwJkImfkRgEVVvR3H8cs8AMzNhwD0ANwQkbVxCNceOAbgDYB9A8MrInJ/FMIpgBkql8sHPc/bAnB0YHjD9/3lRqNhXulnQmcaGO60Wq3O93q91wCO25iqbhaLxUutVuvHzAD1en1Xp9M5MG3mTNN0D4BnACqDf94nSbI0MwAzbwNYmBbgN/MkVwAi2poZ4G+PwHRQKpUuzgzwJ64fF6Fpwff9qxYJzgEmhOGqiNyxYHAehsw8mojM4F0ReZhZImLm0VR8TUSeZp2KHwO4oKo3c7mMphGqcxGOQlhBkiTJec/zJJeChJmHJdmOiBx2HgXjR/DPVsWZNSbM/MqiYrwxaQE4Ya2Zqj4hoi/TqHeGOVYVWePza2sWhuGZNE3fZtmcEtHZdrv9oS9CewVBcEpVV/Joz38CIWVsSJNAjP0AAAAASUVORK5CYII="
                    class="tui"></li>
        </ul>
    </div>
</template>
<script>
import * as api from "@/axios/api";

import { MessageBox } from 'mint-ui'
export default {
    name: 'setup',
    data() {
        return {
            userInfo: []
        }
    },
    mounted() {
        this.getUserInfo();
    },
    methods: {
        async getUserInfo() {
            // 获取用户信息
            let data = await api.getUserInfo();
            if (data.status === 0) {
                // 判断是否登录
                this.$store.commit('dialogVisible', false);
                this.$store.state.userInfo = data.data;
                this.userInfo = data.data;
            } else {
                this.$store.commit('dialogVisible', true);
            }
        },
        handleOutLoginClick() {
            // 退出登录
            MessageBox.confirm(this.$t('hj149') + '?', this.$t('hj165'), {
                confirmButtonText: this.$t('hj161'),
                cancelButtonText: this.$t('hj106'),
            }).then(() => {
                this.toRegister();
            }).catch(() => {

            });
        },
        async toRegister() {
      // 注销登陆
      window.localStorage.removeItem("USERTOKEN"); // 清空本地存储 USERTOKEN字段
      this.clearCookie();
      let data = await api.logout();
      if (data.status === 0) {
        // Toast(data.msg)
        this.$router.push("/login");
      } else {
        Toast(data.msg);
      }
      this.$router.push("/login");
    },
    },
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
    background: linear-gradient(-55deg, rgb(80, 122, 250),rgb(115, 131, 251));
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

ul {
    list-style: none;
}

.ulk li {
    width: 100%;
    padding: 0.534rem 0;
    border-bottom: 0.0267rem solid #e0e0e0;
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    -webkit-box-pack: justify;
    -ms-flex-pack: justify;
    justify-content: space-between;
}

.ulk li span {
    color: #333;
    font-size: 0.3738rem;
    margin-left: 0.3204rem;
}

.ulk li .heasd {
    margin-right: 0.3204rem;
    width: 1.1748rem;
    height: 1.1748rem;
    border-radius: 50%;
}

.ulk li a {
    color: #333;
    font-size: 0.3738rem;
    margin-right: 0.3204rem;

}

.ulk li .tui {
    margin-right: 0.3204rem;
    width: 0.4272rem;
    height: 0.4272rem;
}
.mint-msgbox-confirm {
    color: #5d7dfb;
}
.mint-msgbox-confirm:active{
    color: #5d7dfb;
}
</style>