<!--  -->
<template>
    <div style="width: 100%;">
        <!-- header -->
        <div class="flex detailHerd justify-between align-center">
            <div class="flex align-center">
                <div class="goBack"><img src="@/assets/img/back.png" /></div>
                <div class="">
                    <div class="detailName">黄金</div>
                    <div class="detailCode">XAUSD</div>
                </div>
            </div>
            <div>
                <div class="flex align-center">
                    <div class="simulation">模拟</div>
                    <div class="money">$112.84</div>
                    <div class="sanjiao"><img :class="false ? 'xuanz' : ''" style="transition: all 0.5s;"
                            src="../../assets/img/xiala.png" alt /></div>
                </div>
                <div class="flex align-center justify-end wcbzj">维持保证金
                    <div class="sanjiao"></div>
                </div>
            </div>
        </div>
        <!-- 提示标签 -->
        <div class="flex justify-between ">
            <div>
                    <div class="flex">
                        <div>
                            <img src=""/>
                        </div>
                        <div>
                            市场关闭 - 仅接受挂单交易
                        </div>
                    </div>
                    <div class="flex"> 
                        <div class="">
                            <img src="@/assets/img/calendar.png"/>
                        </div>
                        <div>在今天07：00开市</div>
                    </div>
                
            </div>
            <div>

            </div>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            dialogFlag: false,
        }
    },
    //生命周期 - 创建完成（访问当前this实例）
    created() {

    },
    //生命周期 - 挂载完成（访问DOM元素）
    mounted() {

    }
}
</script>
<style scoped lang='less'>
/* @import url(); 引入css类 */
.flex {
    display: flex;
}

.justify-between {
    justify-content: space-between;
}

.align-end {
    align-items: flex-end;
}

.justify-end {
    justify-content: flex-end;
}

.flex-wrap {
    flex-wrap: wrap;
}

.align-center {
    align-items: center;
}

.detailName {
    font-size: 0.48rem;
    color: #000;
    font-weight: 700;
}

.detailCode {
    margin-top: 0.15rem;
    font-size: 0.16rem;
    color: rgb(126, 127, 129);
}

.tabs_title {
    width: 23%;
    height: 100%;
    display: flex;
}

.tb {
    width: 50%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 0.4103rem;
}

.active {
    font-size: 0.5003rem;
    font-weight: 800;
}


.sanjiao {
    width: 0.4rem;
    height: 0.4rem;
    display: flex;
    align-items: center;
    justify-content: center;

    img {
        width: 0.4rem;
        height: 0.4rem;
    }
}

.goBack {
    width: 0.35rem;
    height: 0.6rem;
    margin-right: 0.2rem;

    img {
        width: 100%;
        height: 100%;
    }
}

.detailHerd {
    width: 96%;
    height: 1rem;
    margin-left: 0;
    margin-right: 0;
    margin: 0 auto;
    margin-top: 0.1rem;
}

.simulation {
    width: 0.8rem;
    height: 0.4103rem;
    border-radius: 0.1rem;
    background: #4d73b1;
    color: #fff;
    font-size: 0.1577rem!important;
    display: flex;
    align-items: center;
    justify-content: center;
}

.money {
    margin-left: 0.1rem;
    margin-right: 0.1rem;
}

.wcbzj {
    font-size: 0.3077rem;
    color: #acaeaf;
    transform: scale(0.9);
}
</style>