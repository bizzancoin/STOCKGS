<template>
    <div>
        <div class="dabg">
            <div class="gebgh">
                <div>
                    <h2>
                        <span class="hbnh" @click="$router.go(-1)">
                            <a class="fan"></a>
                        </span> 开户
                    </h2>
                </div>
                <div class="fourg"><span class="xian"></span>
                    <div class="fouda">
                        <div class="danhe"><span>1</span></div>
                        <p>注册</p>
                    </div>
                    <div class="fouda">
                        <div class="danhe"><span>2</span></div>
                        <p>认证</p>
                    </div>
                    <div class="fouda">
                        <div class="danhe"><span>3</span></div>
                        <p>入金</p>
                    </div>
                    <div class="fouda">
                        <div class="danhe"><span>4</span></div>
                        <p>交易</p>
                    </div>
                </div>
            </div>
            <h2 class="kaihu">开户前准备</h2>
            <div class="kaibox">
                <div class="sand">
                    <div class="namert"> 身份证 <span>NO.1</span></div>
                    <p>有效期内的二代身份证</p>
                </div>
                <div class="sand sanda">
                    <div class="namert"> 银行卡 <span>NO.2</span></div>
                    <p>户名与身份证一致的银行卡</p>
                </div>
                <div class="sand sands">
                    <div class="namert"> WIFI网络 <span>NO.3</span></div>
                    <p>3G/4G网络</p>
                </div>
            </div>
            <div class="kapi"><img
                    src="data:image/png;base64,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">
                <div class="txt">
                    <h6>行情分发,极速稳定</h6>
                    <p>专线直连交易所,毫秒级下单速度</p>
                </div>
            </div>
            <div class="kapi"><img
                    src="data:image/png;base64,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">
                <div class="txt">
                    <h6>客户至上,优享服务</h6>
                    <p>灵活投资、实现收益最大化</p>
                </div>
            </div>
            <div class="kapi"><img
                    src="data:image/png;base64,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">
                <div class="txt">
                    <h6>极速开户,超高配额</h6>
                    <p>0资金门槛,无需线下见证</p>
                </div>
            </div>
            <div class="hg" @click="kaihu"> 开户 </div>
        </div>
    </div>
</template>
<script>
    export default{
        data(){
            return{

            }
        },
        methods:{
            kaihu(){
                //判断是否登录
                if(localStorage.getItem('USERTOKEN')){
                    this.$router.push('/smrz');
                }else{
                    this.$router.push('/register');
                }
            }
        }
    }
</script>
<style lang="less" scoped>
.dabg {
    background: #f0f0f0;
    position: fixed;
    top: 0;
    left: 0;
    bottom: 0;
    right: 0;
    overflow: hidden;
    overflow-y: scroll;

    .gebgh {
        height: 4rem;
        background: linear-gradient(-55deg, rgb(80, 122, 250),rgb(115, 131, 251));

        h2 {
            text-align: center;
            height: 1.25rem;
            width: 100%;
            position: relative;
            line-height: 1.25rem;
            font-size: .48rem;
            color: #fff;
            background: transparent;
            font-weight: 500;
            z-index: 3;

            .hbnh {
                position: absolute;
                left: 0.4rem;
                font-size: .43rem;
                font-weight: 500;

                .fan {
                    width: 0.24rem;
                    height: 0.43rem;
                    background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABIAAAAgCAYAAAAffCjxAAAAAXNSR0IArs4c6QAAAVdJREFUSEut1usqBVEYxvH/c1MuRJJDkhwTEpIkJUlyCEk++CJJckpycw49Gs3Wa+y9Z82ePd/Xr5n38KwRNR7bE0APMK1OHdtTwAWQGZcdQbZngPMc+QImK0O2Z4GzgIxLuqoE2Z4DTgMyJuk6K08yZHseOAnIqKSbRo2TINsLwHFARiTdxkaVQrYXgaOADEu6K3a7LWR7CTjMD2XdaYq0rZHtFWA/Rz6BIUn3reau6RvZXgX28kMfOfLQbnj/QbbXgN380HuOPJZtwB/I9jqwE5ABSc9lyJ8a2d4AtgPSL+klBfmFbG8CWwHpk/SaivxAtjMgg7Inq0mvpLcqSHehTOvKpzU+oSvFDlj99gesOJCDkp7Kip+yIknT3XL7C0tbum9lMbIMHKQkQEqwJWVSKZTPWTElq0dt6GYxt6uHf8DqX0cBq39BBqz+lR2w+j8RAfv9rfkGqF24CUdT9E4AAAAASUVORK5CYII=) no-repeat 50%;
                    background-size: 100%;
                    display: inline-block;
                    margin-right: 0.13rem;
                    vertical-align: middle;
                    margin-top: -0.05rem;
                }
            }
        }

        .fourg {
            width: 7.34rem;
            margin: 0 auto;
            display: flex;
            justify-content: space-between;
            height: 0.8rem;
            position: relative;
            margin-top: 0.8rem;

            .xian {
                border-top: 0.0266rem dashed #fff;
                position: absolute;
                left: 0.4rem;
                width: 6.54rem;
                top: 0.4rem;
            }

            .fouda {
                width: 0.8rem;
                position: relative;

                .danhe {
                    width: 0.8rem;
                    height: 0.8rem;
                    background: hsla(0, 0%, 100%, .2);
                    border-radius: 0.4rem;
                    text-align: center;

                    span {
                        width: 0.53rem;
                        height: 0.53rem;
                        background: #fff;
                        display: inline-block;
                        margin: 0 auto;
                        margin-top: 0.13rem;
                        border-radius: 50%;
                        text-align: center;
                        color: #5d7dfb;
                        font-size: .43rem;
                        line-height: .54rem;
                    }
                }

                p {
                    text-align: center;
                    color: #fff;
                    font-size: .32rem;
                    margin-top: 0.13rem;
                }
            }
        }
    }

    .kaihu {
        color: #333;
        font-size: .15rem;
        text-align: center;
        margin-top: 0.17rem;
    }

    .kaibox {
        width: 9.48rem;
        margin: 0 auto;
        display: flex;
        justify-content: space-between;
        margin-top: 0.43rem;

        .sand {
            width: 3.07rem;
            padding: 0 0.266rem;
            background: linear-gradient(125deg, #ff839b, #fb5d7d);
            border-radius: 0.13rem;
            padding: 0.266rem 0;
            .namert {
                color: #fff;
                font-size: .4rem;
                text-align: center;


                span {
                    display: inline-block;
                    height: 0.37rem;
                    background: hsla(0, 0%, 100%, .2);
                    border-radius: 0.05rem;
                    font-size: .266rem;
                    line-height: .37rem;
                    padding: 0 0.13rem;
                    vertical-align: middle;
                    margin-top: -0.053rem;
                }
            }

            p {
                color: #fff;
                font-size: .266rem;
                margin-top: 0.266rem;
                text-align: left;
                line-height: 1.5;
                padding: 0 0.2rem;
            }
        }

        .sanda {
            background: linear-gradient(125deg, #feb145, #ff7e2b);
        }

        .sands {
            background: linear-gradient(125deg, #fb8066, #f64a41);
        }
    }

    .kapi {
        width: 9.29rem;
        height: 1.74rem;
        background: #fff;
        border-radius: 0.13rem;
        margin: 0 auto;
        margin-top: 0.53rem;
        display: flex;

        img {
            width: 1.07rem;
            height: 1.07rem;
            margin-left: 0.67rem;
            margin-top: 0.32rem;
        }

        .txt {
            margin-left: 0.67rem;

            h6 {
                color: #333;
                font-size: 0.4rem;
                padding-top: 0.4rem;
                font-weight: 600;
            }

            p {
                color: #999;
                font-size: .32rem;
                margin-top: 0.19rem;
            }
        }
    }

    .hg {
        width: 90%;
        height: 60px;
        background: linear-gradient(-55deg, rgb(80, 122, 250),rgb(115, 131, 251));
        border-radius: 5px;
        margin: 20px auto;
        text-align: center;
        line-height: 60px;
        color: #fff;
        margin-top: 50px;
        font-size: 18px;
    }
}
</style>