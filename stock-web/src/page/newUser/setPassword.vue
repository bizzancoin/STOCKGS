<template>
    <div style="background-color: #fff;min-height: 100vh;">
        <div class="header">
            <div>
                <h2><span class="hbnh"><a @click="$router.go(-1)" class="fan"></a></span> 资金密码 </h2>
            </div>
        </div>
        <ul class="erty">
            <li style="display: none;"><input placeholder="请输入旧密码" type="password"></li>
            <li><input placeholder="请输入新密码" v-model="newPassword" type="password"></li>
            <li><input placeholder="请确认新密码" v-model="cirNewPassword" type="password"></li>
        </ul>
        <div class="denlu" @click="insertWithPwd()">确定</div>
    </div>
</template>
<script>
import * as api from "@/axios/api";
import { isNull, pwdReg } from "@/utils/utils";
    export default{

        data(){
            return{
                newPassword: '',
                cirNewPassword: '',
            }
        },
        methods:{
            async insertWithPwd(){
                var that = this
                // 修改密码
                if (
                    isNull(that.newPassword) ||
                    isNull(that.cirNewPassword)
                ) {
                    this.$toast("密码不能为空");
                    
                } else if (!pwdReg(that.newPassword)) {
                    this.$toast("密码格式不正确");
                    
                } else if (that.newPassword != that.cirNewPassword) {
                    this.$toast("两次密码不一致");
                    
                } else {
                    let params = {
                        withPwd: that.newPassword,
                    }
                    let res = await api.insertWithPwd(params);
                    console.log(res.status == 0)
                    if (res.status == 0) {
                        this.$toast(res.msg);
                        this.$router.push('/user');
                    } else {
                        this.$toast('修改失败');
                    }
                }
            },
        }
    }
</script>
<style scoped>
.header {
    width: 100%;
    background: url(~@/assets/imgRed/bj.c3bdbbdb.png) no-repeat 50%;
    background-size: 100%;
    height: 6.141rem;
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

.xind {
    position: absolute;
    right: 0.4005rem;
    color: #fff;
    font-size: 0.3738rem;
    font-weight: 500;
}

.shaux {
    position: absolute;
    width: 0.4806rem;
    height: 0.4806rem;
    background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACQAAAAkCAYAAADhAJiYAAAAAXNSR0IArs4c6QAAA8VJREFUWEfNmEloXVUYx3//e18qCWitA4JUFLEURdOotcWpEKsLFbUVBONKKYIbRcEJ3VhwarvSiAspVlzoRpyow0Lr0No2LbW4cACllCK2DhREjWhy71/OzU087/W9vPuSl5K7u2f4zu9+9xuPmGeP5hkPlYFsLyTjBsQgZgBxDrAQMPA75gBiP+YTUrZK+nsmH9sWyPZSch4GhoDeiof8CbxCwiZJhyruKZa1BLLdR86TwL1ArROh0dqgpY0kPCVprIqMpkC2l5DzJnBhEyGjiBHMt8DRcv5UxPmYlU21aPaQslbST+2gjgGyfSk5HwCn1202O4DnSXlX0j/NBNvuJeMW4D7E5Q1rfiRhUNIP00HVAdk+j5ydDTBHMPeopnfafV0873HfjhgGTovGA9QKSYdbyZoCKm1mpO43mRFSbpb0Sycwk2ttLyZnK7Bsar/ZScoqSVkzmf8DZd4EPBhtDDCrJf01E5gIahE5nwEXRXIeUaqNLYFKI/4a6CkXHSbhYkk/zwYmgjqLnK+AReXYHyQsaSa/0JAzvwzcFWlnrWp6uxswU1DjvhOxJZK5QakebTxDtk8k5wjQV0yaHarp6m7CFGLthJwvI3v6jYQzG+OTSm94PdLObarpjW4DFVDjXofYPCU75zr16KP4LDnzS8Dd5eAoCae0ijOzhbQdDPxXIC1lPa1Uj9cDjXs3KiJsSCTblGj1bA+ebr8z7wcGyvM+VKLrGzUUiCeD1wtKFXJX154ivsEJkUlsxtxavn9HwhWNQCENLCgH1yvVE12jCXYz5kESPp4ukUfnbQk2NKdAhTFnfq7Ib9M/h0joD0Bz+stKl+8lJ9jO0hZMJuda9WhbcPvjYtS2V5LzReRhMduwUhUaPL5unzkUfHVuDnxPwoCk0QmgcQ8hXou8YO4Co72AnD1RtM5IuErS7snzm6WO7appVTc9LZZlu5+cvaVnP6NUj9W5fekFIZyvi7S0ptOCrJMPcOaQVIdIuEzSv8cCTVSK38xV+dEIazukjrMlHWiciwu0UDA9FGmpKwVaJ5orjHpyQ1nC7gL6G6BmU8KeCyyW9HlVsDkp8m2LjDsQLwJjJCyXdLAKVLM26BJy3gfOqBNgtgPDbdqgPjLWAA8glkf795FwZZWyplWjGIz8rY4aRXMBYkWLRnEvKTdVqdHbtdLrgftn0UqHxL2hbKXr3LvV76ty2RDa6om4Uf2yIbROr5LwbNcuG5rEjpPIuBFxDWZZeR1zcumpRzEHEfswn5Ly3kz7ubYaquIZ3Vwz74D+A2+DgOcUuMpOAAAAAElFTkSuQmCC) no-repeat 50%;
    background-size: 100%;
    right: 0.4005rem;
    top: 0.4005rem;
}

.erty {
    width: 100%;
    margin: 0 auto;
    min-height: 2.136rem;
    margin-top: -1.7889rem;
    background: #fff;
    border-radius: 0.4005rem 0.4005rem 0 0;
    padding-top: 0.801rem;
}

.erty li {
    width: 9.345rem;
    height: 1.1748rem;
    background: #e6e6e6;
    border-radius: 0.1335rem;
    margin: 0 auto;
    margin-bottom: 0.4005rem;
}

.denlu {
    width: 9.345rem;
    height: 1.1748rem;
    background: linear-gradient(-55deg, rgb(80, 122, 250),rgb(115, 131, 251));
    border-radius: 0.1335rem;
    line-height: 1.2015rem;
    text-align: center;
    color: #fff;
    margin: 0 auto;
    font-size: 0.3738rem;
    font-weight: 600;
    margin-top: 0.801rem;
}

input::-webkit-input-placeholder {
    color: #999;

}

input {
    background: transparent;
    height: 1.1748rem;
    width: 6.942rem;
    color: #000;
    font-size: 0.3738rem;
    padding-left: 0.267rem;
}
</style>