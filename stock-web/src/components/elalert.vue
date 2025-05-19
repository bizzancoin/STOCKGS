<!--  -->
<template>
    <div v-if="$store.state.elAlertShow">
        <el-alert  :closable="closable" :title="$store.state.elAlertText" :type="$store.state.elAlertType" center style="z-index:99999999999999999;position: fixed;top: 0;bottom: 0;margin: auto;">
    </el-alert>
    </div>
    
</template>

<script>
export default {
    props: {
        alertShow: {
            type: Boolean,
            default: false
        },
        closable: {
            type: Boolean,
            default: false
        },
        texts: {
            type: String,
            default: ''
        },
        elType: {
            type: String,
            default: 'warning'
        }
    },
    data() {
        return {

        }
    },
    //监听alertShow变成true时，2秒后自动关闭
    watch: {
        // 监听vuex中的elAlertShow变化
        '$store.state.elAlertShow': function (val) {
            if (val) {
                setTimeout(() => {
            this.$store.commit('elAlertShow', {'elAlertShow':false});
                }, 2000)
            }
        }
    },
    //生命周期 - 创建完成（访问当前this实例）
    created() {
        
    },
    //生命周期 - 挂载完成（访问DOM元素）
    mounted() {
        
    },
    methods:{
        //定时调用父组件方法关闭弹窗
        closeAlert(){
            //定时调用父组件方法关闭弹窗
            setTimeout(()=>{
                this.$emit('closeAlert')
            },2000)
        },
        
    }
}
</script>
<style scoped lang="less">
/* @import url(); 引入css类 */


.tit {
    width: 1rem;
    height: 1rem;
    position: absolute;
    top: 50%;
}
</style>