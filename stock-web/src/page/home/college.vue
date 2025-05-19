<template>
    <div class="jijincontainer">
        <div class="headf">
            <div>
                <h2>
                    <span class="hbnh" @click="$router.go(-1)">
                        <a class="fan"></a>
                    </span> 学院
                </h2>
            </div>
        </div>
        <div class="hjkl"></div>
        <ul class="xianlu">
            <li>
                <div class="erty"><img
                        src="data:image/png;base64,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">
                    <p>线路1</p>
                </div><span></span>
            </li>
        </ul>
    </div>
</template>
<style lang="less" scoped>
.jijincontainer {
    .headf {
        background: linear-gradient(-55deg, rgb(80, 122, 250),rgb(115, 131, 251));
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;

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
                left: 0.40rem;
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
    }

    .hjkl {
        height: 1.4rem;
    }

    .xianlu {
        li {
            height: 1.9rem;
            border-bottom: 0.0266rem solid #e0e0e0;
            justify-content: space-between;
            display: flex;

            .erty {
                margin-left: 0.32rem;
                display: flex;

                img {
                    width: 1.17rem;
                    height: 1.17rem;
                    margin-top: 0.4rem;
                }

                p {
                    color: #333;
                    font-size: .37rem;
                    line-height: 1.98rem;
                    margin-left: 0.266rem;
                }
            }

            span {
                width: 0.16rem;
                height: 0.29rem;
                background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAwAAAAWCAYAAAD0OH0aAAAAAXNSR0IArs4c6QAAAOhJREFUOE+V1D0OgkAQBeC3ZA6gvZr413mJpdNrUFBwHooJd1EvYGUBegD1EBtixiwGAQeg3i/zhp1Zw8xnABMiOkRR9EDPZ5j5CmAH4E5Etg+ZLMtmZVkeAWwB3Igo1JCRBB6dAGwEAbBxHD+70n1ADUk/aw19QQcqAITNSj9AUJqmcyKSeFKpcM7ZJEleVZIWqCGJt2qiTtCBcudcKJX+AkHMvAAg8aRSHgSBVUENSbwlgMtQUFXRgf9jVfN6pOZhtWntLlo9+LmqRkS/uOZhdTSGTuz48R69QKNX1D8CUyLa9+2zjMoby8mmFQ3T2QUAAAAASUVORK5CYII=) no-repeat 50%;
                background-size: 100%;
                margin-right: 0.32rem;
                margin-top: 0.83rem;
            }
        }
    }
}
</style>