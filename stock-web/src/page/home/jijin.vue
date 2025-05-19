<template>
    <div class="jijincontainer">
        <div class="headf">
            <div>
                <h2>
                    <span class="hbnh" @click="$router.go(-1)">
                        <a class="fan"></a>
                    </span> 基金
                </h2>
            </div>
        </div>
        <div class="hjkl"></div>
        <div class="boxk">
            <h6 class="tile">优选基金</h6>
            <p style="color: rgb(153, 153, 153); margin-top: 10px; font-size: 0.32rem;">优选好基，追求更好回报</p>
        </div>
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

    .boxk {
        width: 9.48rem;
        margin: 0 auto;

        .tile {
            color: #333;
            font-size: .43rem;
            font-weight: 500;
            margin-top: 0.4rem;
            font-weight: 700;
        }
    }
}
</style>