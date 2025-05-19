<template>
    <div>
        <div class="hesadf">
            <div>
                <h2><span class="hbnh"><a class="fan" href="javascript:history.back(-1)"></a></span> 资金明细 </h2>
            </div>
        </div>
        <div class="head">
            <div class="thj"><a :class="xz == '0' ? 'xuan' : ''" @click="xuanze(0)">资金明细</a>
                <a :class="xz == '1' ? 'xuan' : ''" @click="xuanze(1)">充值记录</a>
                <a :class="xz == '2' ? 'xuan' : ''" @click="xuanze(2)">提现记录</a>
                <span class="tiaokl" :class="xz == 0 ? 'tiaok1' : xz == 1 ? 'tiaok2' : 'tiaok3'"></span>
            </div>
        </div>
        <div role="feed" class="van-list">
            <!-- <ul class="czjl"></ul>
            <div class="van-list__placeholder"></div> -->
            <capitalList v-if="(xz==0)"></capitalList>
            <rechargeList v-if="(xz==1)"></rechargeList>
            <cashList v-if="(xz==2)"></cashList>
        </div>
    </div>
</template>
<script>
//引入提现记录
import cashList from '@/page/cashWithdrawalRecord/compontents/cash-list'
import rechargeList from '@/page/user/compontents/recharge-list'
import capitalList from '@/page/user/compontents/capital-all'


export default {
    components: {
        cashList,
        capitalList,
        rechargeList
    },
    data() {
        return {
            xz: 0,
        }
    },
    methods: {
        xuanze(e) {
            this.xz = e
            console.log(this.xz)
        }
    }
}
</script>
<style scoped>
.hesadf {
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

.xind {
    position: absolute;
    right: 0.4005rem;
    color: #fff;
    font-size: 0.3738rem;
    font-weight: 500;
}

.shaux[data-v-7793a11f] {
    position: absolute;
    width: 0.4806rem;
    height: 0.4806rem;
    background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACQAAAAkCAYAAADhAJiYAAAAAXNSR0IArs4c6QAAA8VJREFUWEfNmEloXVUYx3//e18qCWitA4JUFLEURdOotcWpEKsLFbUVBONKKYIbRcEJ3VhwarvSiAspVlzoRpyow0Lr0No2LbW4cACllCK2DhREjWhy71/OzU087/W9vPuSl5K7u2f4zu9+9xuPmGeP5hkPlYFsLyTjBsQgZgBxDrAQMPA75gBiP+YTUrZK+nsmH9sWyPZSch4GhoDeiof8CbxCwiZJhyruKZa1BLLdR86TwL1ArROh0dqgpY0kPCVprIqMpkC2l5DzJnBhEyGjiBHMt8DRcv5UxPmYlU21aPaQslbST+2gjgGyfSk5HwCn1202O4DnSXlX0j/NBNvuJeMW4D7E5Q1rfiRhUNIP00HVAdk+j5ydDTBHMPeopnfafV0873HfjhgGTovGA9QKSYdbyZoCKm1mpO43mRFSbpb0Sycwk2ttLyZnK7Bsar/ZScoqSVkzmf8DZd4EPBhtDDCrJf01E5gIahE5nwEXRXIeUaqNLYFKI/4a6CkXHSbhYkk/zwYmgjqLnK+AReXYHyQsaSa/0JAzvwzcFWlnrWp6uxswU1DjvhOxJZK5QakebTxDtk8k5wjQV0yaHarp6m7CFGLthJwvI3v6jYQzG+OTSm94PdLObarpjW4DFVDjXofYPCU75zr16KP4LDnzS8Dd5eAoCae0ijOzhbQdDPxXIC1lPa1Uj9cDjXs3KiJsSCTblGj1bA+ebr8z7wcGyvM+VKLrGzUUiCeD1wtKFXJX154ivsEJkUlsxtxavn9HwhWNQCENLCgH1yvVE12jCXYz5kESPp4ukUfnbQk2NKdAhTFnfq7Ib9M/h0joD0Bz+stKl+8lJ9jO0hZMJuda9WhbcPvjYtS2V5LzReRhMduwUhUaPL5unzkUfHVuDnxPwoCk0QmgcQ8hXou8YO4Co72AnD1RtM5IuErS7snzm6WO7appVTc9LZZlu5+cvaVnP6NUj9W5fekFIZyvi7S0ptOCrJMPcOaQVIdIuEzSv8cCTVSK38xV+dEIazukjrMlHWiciwu0UDA9FGmpKwVaJ5orjHpyQ1nC7gL6G6BmU8KeCyyW9HlVsDkp8m2LjDsQLwJjJCyXdLAKVLM26BJy3gfOqBNgtgPDbdqgPjLWAA8glkf795FwZZWyplWjGIz8rY4aRXMBYkWLRnEvKTdVqdHbtdLrgftn0UqHxL2hbKXr3LvV76ty2RDa6om4Uf2yIbROr5LwbNcuG5rEjpPIuBFxDWZZeR1zcumpRzEHEfswn5Ly3kz7ubYaquIZ3Vwz74D+A2+DgOcUuMpOAAAAAElFTkSuQmCC) no-repeat 50%;
    background-size: 100%;
    right: 0.4005rem;
    top: 0.4005rem;
}

.head {
    height: 1.1214rem;
    background: #fff;
    border-bottom: 0.0267rem solid #e0e0e0;
}

.thj {
    width: 9.345rem;
    height: 1.1214rem;
    margin: 0 auto;
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    -webkit-box-pack: justify;
    -ms-flex-pack: justify;
    justify-content: space-between;
    position: relative;
}

.thj a {
    width: 33.3%;
    text-align: center;
    color: #666;
    line-height: 1.1214rem;
    font-size: 0.3471rem;
    -webkit-transition: all .3s;
    transition: all .3s;
}

.xuan {
    width: 33.3%;
    text-align: center !important;
    ;
    color: #666 !important;
    line-height: 1.1214rem !important;
    font-size: 0.3471rem !important;
    -webkit-transition: all .3s !important;
    transition: all .3s !important;
    color: #5d7dfb !important;
}

.tiaokl {
    width: 0.534rem;
    height: 0.1068rem;
    background: #5d7dfb;
    border-radius: 0.0534rem;
    position: absolute;
    bottom: 0.1335rem;
    left: 17%;
    margin-left: -0.267rem;
    -webkit-transition: all .5s;
    transition: all .5s;
}

.tiaok1 {
    left: 17%;
}

.tiaok2 {
    left: 50%;
}

.tiaok3 {
    left: 83%;
}

</style>