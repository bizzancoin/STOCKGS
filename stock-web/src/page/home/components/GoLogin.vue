<template>
    <div class="go_to_login animated bounce">
        <div>
            <div class="left_mess"><span>{{ '登录后即可开启真实/模拟交易' }}</span></div>
            <div class="right_button">
                <div class="btn" @click="goToLogin()"><span>{{ '登录' }}</span></div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'goLogin',
    data() {
        return {
            isShow: true,
        }
    },
    methods: {
        goToLogin() {
            this.$router.push('/login')
        }
    }
}
</script>

<style scoped lang="less">
.go_to_login {
    width: 100%;
    height: 1.3rem;
    position: fixed;
    bottom: 2rem;
    display: flex;
    align-items: center;
    justify-content: center;
    
    
    > div {
        width: 90%;
        height: 100%;
        background: rgba(39,39,51,0.85);
        border-radius: .2228rem;
        display: flex;
        align-items: center;
        -webkit-backdrop-filter: blur(10px);
        backdrop-filter: blur(10px);
    }
    .left_mess {
        width: 70%;
        height: 100%;
        display: flex;
        align-items: center;
        margin-left: .2564rem;
        font-size: .3846rem;
        color: #fff;
    }
    .right_button {
        width: 30%;
        height: 100%;
        display: flex;
        justify-content: center;
        align-items: center;
        > .btn {
            width: 1.7949rem;
            height: .7692rem;
            background: rgb(25,122,246);
            border-radius: .1064rem;
            display: flex;
            justify-content: center;
            align-items: center;
            color: #fff;
            font-size: .3615rem;
        }
    }
}
//淡入动画
@keyframes show {
    0% {
        
        buttom:0rem;
    }
    100% {
        
        buttom:2rem;
    }
}


</style>
