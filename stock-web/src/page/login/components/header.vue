<template>
    <div class="header_history">
        <div class="header_content">
            <div class="left_back" @click="$router.go(-1)"><img src="../../../assets/img/back.png" alt=""></div>
            <div class="right_mess"><span>{{ '' }}</span></div>
            <div class="right_back">
                <div class="service">
                    <img @click="goOnline()" :src="Service" alt />
                </div>
                <van-popover v-model="showPopover" trigger="click" placement="bottom-end" :actions="actions"
                    @select="onSelect">
                    <template #reference>
                        <div class="iconfont icon-icon_A">
                        </div>
                    </template>
                </van-popover>
                

            </div>
        </div>
    </div>
</template>

<script>
import Service from "@/assets/home/icon_talk.png";
import * as api from "@/axios/api";
export default {
    name: 'headers',
    props: ['mess'],
    data() {
        return {
            Service,
            onlineService: "",
            showPopover: false,
            actions: [
               
            { text: '简体中文', icon: require('@/assets/ico/Chinese.png'), lang: 'zh-CN' },
            { text: '繁體中文', icon: require('@/assets/ico/tw.png'), lang: 'tww' },
            { text: '更多定制', icon: require('@/assets/ico/dz.png'), lang: 'zh-CN' }
        ],
        }
    },
    methods: {
        onSelect(e) {
            this.$i18n.locale = e.lang;
            window.localStorage.setItem('language', e.lang);
        },
        goOnline() {
            this.$router.push('/service');
        },
        async getInfoSite() {
            let data = await api.getInfoSite()
            if (data.status === 0) {
                this.onlineService = data.data.onlineService
            } else {
                Toast(data.msg)
            }
        },
    },
    mounted() {
        this.getInfoSite()
    }
}
</script>

<style scope lang="less">
.header_history {
    width: 100%;
    height: 1.6256rem;
    display: flex;
    align-items: center;
    padding-right: 0.25rem;
    >div {
        width: 100%;
        height: 80%;
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding-left: 0.2rem;
        padding-right: 0.2rem;
    }

    .left_back {
        width: .6513rem;
        height: 1.0513rem;
        display: flex;
        align-items: center;
        justify-content: center;

    }

    .right_back {
        width: 17%;
        height: 1.0513rem;
        display: flex;
        align-items: center;
        justify-content: space-between;

        .service {
            width: 0.5rem;
            height: 0.5rem;

            img {
                width: 0.5rem;
                height: 0.5rem;

            }
        }

    }

    .right_mess {
        width: 60%;
        height: 100%;
        display: flex;
        justify-content: flex-end;
        align-items: center;

        >span {
            margin-right: .5128rem;
        }
    }

    img {
        width: 50%;
        height: 50%;
    }
}
</style>