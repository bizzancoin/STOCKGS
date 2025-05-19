<!--  -->
<template>
    <div>
        <div class="top_icon">
            <div class="left_back" @click="handleBack()">
                <img src="@/assets/img/zuojiantou.png" alt />
            </div>
            <div class="right_icon">

            </div>
        </div>
        <div class="bottomIfr" v-if="onlineShow">
            <iframe  class="conentIfr" scrolling="no" :src="onlineService"></iframe>
        </div>
    </div>
</template>

<script>
import * as api from "@/axios/api";
export default {
    data() {
        return {
            onlineShow:false,
            onlineService: "",
        }
    },
    //生命周期 - 创建完成（访问当前this实例）
    created() {
        this.getInfoSite();
    },
    //生命周期 - 挂载完成（访问DOM元素）
    mounted() {
        
    },
    methods: {
        handleBack() {
            // 点击返回/
            this.$router.go(-1);
        },
        async getInfoSite() {

            let data = await api.getInfoSite()
            if (data.status === 0) {
                this.onlineService = data.data.onlineService
                this.onlineShow = true;
            } else {
                this.$store.commit('elAlertShow', { 'elAlertShow': true, 'elAlertText': data.msg });
            }
        },
    }
}
</script>
<style scoped lang='less'>
/* @import url(); 引入css类 */
.top_icon {
    position: absolute;
    width: 100%;
    height: 1.5rem;
    display: flex;
    justify-content: space-between;
    align-items: center;
    z-index: 9999999;

    .left_back {
        width: 10%;
        height: 50%;
        display: flex;
        align-items: center;
        justify-content: center;

        img {
            width: 0.6rem;
            height: 0.6rem;
        }
    }

    .right_icon {
        width: 25%;
        height: 50%;
        display: flex;

        >div {
            width: 50%;
            height: 100%;
            display: flex;
            justify-content: center;
            align-items: center;

            img {
                width: 0.6rem;
                height: 0.6rem;
            }
        }
    }
}

.bottomIfr {
    width: 100%;
    height: calc(100vh - 0.04rem);

}

.conentIfr {
    width: 100%;
    height: 100%;
    border: none;
    //取消滚动条
    overflow: hidden;
}
</style>