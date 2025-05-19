<template>
  <!-- <div class="bank_card_page">
    <div class="content">
      <div class="top_back">
        <div class="left_back_icon" @click="$router.go(-1)">
          <img src="../../assets/img/zuojiantou.png" alt />
        </div>
      </div>
      <div class="titles">
        <span>{{ addBank ? $t('hj211') : $t('hj212') }}</span>
      </div>
      <div class="bank_name">
        <div class="lefts">
          <span>{{ $t('hj213') }}</span>
        </div>
        <div class="rights">
          <input type="text" v-model="bankName" :disabled="!addBank" />
        </div>
      </div>
      <div class="bank_name">
        <div class="lefts">
          <span>{{ $t('hj214') }}</span>
        </div>
        <div class="rights">
          <input type="text" v-model="bankAddress" :disabled="!addBank" />
        </div>
      </div>
      <div class="bank_name">
        <div class="lefts">
          <span>{{ $t('hj215') }}</span>
        </div>
        <div class="rights">
          <input type="text" v-model="bankNo" :disabled="!addBank" />
        </div>
      </div>
      <div class="bank_name bind" @click="toSure" v-if="addBank">
        <span>{{ $t('hj216') }}</span>
      </div>
      <div class="bank_hck"></div>
      <div class="bank_code"></div>
    </div>
  </div> -->
  <div data-v-0b1b5c11="" class="bijnm">
    <div data-v-0b1b5c11="" class="headf">
      <div data-v-7793a11f="" data-v-0b1b5c11="">
        <h2 data-v-7793a11f=""><span data-v-7793a11f="" class="hbnh"><a @click="$router.go(-1)" class="fan"></a></span> 绑定银行卡
          <a data-v-7793a11f="" class="xind" style="display: none;">新增</a><a data-v-7793a11f="" class="shaux"
            style="display: none;"></a><a data-v-7793a11f="" class="xind" style="display: none;">交易记录</a></h2>
      </div>
    </div>
    <ul data-v-0b1b5c11="" class="banul">
      <li data-v-0b1b5c11=""><span data-v-0b1b5c11="">银行名称：</span>
        <input  :disabled="!addBank" v-model="bankName" placeholder="请输入银行名称">
      </li>
      <li data-v-0b1b5c11=""><span data-v-0b1b5c11="">开户支行：</span><input data-v-0b1b5c11="" :disabled="!addBank" v-model="bankAddress" placeholder="请输入开户支行"></li>
    </ul>
    <ul data-v-0b1b5c11="" class="banul">
      <li data-v-0b1b5c11=""><span data-v-0b1b5c11="">卡号：</span><input data-v-0b1b5c11="" :disabled="!addBank" v-model="bankNo" placeholder="请输入你的银行卡号"></li>
      
       <!-- <li data-v-0b1b5c11=""><span data-v-0b1b5c11="">开户地址：</span><input data-v-0b1b5c11="" placeholder="请输入开户行地址"></li>  -->
    </ul>
    <div data-v-0b1b5c11="" @click="toSure" v-if="addBank" class="enter">提交</div>
    <div data-v-0b1b5c11="" @click="$router.push('/bankUpDate')" v-if="!addBank" class="enter" >更换</div>
  </div>
</template>

<script>
import * as api from "@/axios/api";
import { Toast } from "mint-ui";
import { isNull, bankNoReg, isName } from '@/utils/utils'

export default {
  name: "bankCard",
  data() {
    return {
      bankName: "", //银行名称,
      bankAddress: "", //需要精确到分行或者支行,
      bankNo: "", // 印象卡号
      addBank: false
    };
  },
  created() {
    this.getCardDetail();
  },
  methods: {
    async toSure() {
      // 添加银行卡
      if (isNull(this.bankNo) || !bankNoReg(this.bankNo)) {
        Toast(this.$t("hj217"));
      } else if (isNull(this.bankName) || !isName(this.bankName)) {
        Toast(this.$t("hj218"));
      } else if (isNull(this.bankAddress) || !isName(this.bankAddress)) {
        Toast(this.$t("hj219"));
      } else {
        let opts = {
          bankName: this.bankName,
          bankNo: this.bankNo,
          bankAddress: this.bankAddress
        };
        let data = await api.addBankCard(opts);
        if (data.status === 0) {
          Toast(this.$t("hj220"));
          this.$router.push("/newUser");
        } else {
          Toast(data.msg);
        }
      }
    },
    async getCardDetail() {
      // 获取银行卡信息
      let data = await api.getBankCard()
      if (data.status === 0) {
        const { bankAddress, bankName, bankNo } = data.data;
        this.bankAddress = bankAddress;
        this.bankName = bankName;
        this.bankNo = bankNo;
        this.addBank = false;
      } else {
        this.addBank = true;
      }
    },
  }
};
</script>

<style scoped lang="less">
.bank_card_page {
  width: 100%;
  height: 100%;
  background: #fff;

  .content {
    width: 100%;
    height: 100%;
    padding: 0 0.6rem;

    .top_back {
      width: 100%;
      height: 2rem;

      >div {
        width: 10%;
        height: 100%;
        display: flex;
        align-items: center;
      }

      img {
        width: 0.6rem;
        height: 0.6rem;
      }
    }

    .titles {
      width: 100%;
      height: 1.5rem;
      font-size: 0.641rem;
      margin-top: 1rem;

      span {
        font-weight: 600;
      }
    }
  }

  .bank_name {
    width: 100%;
    height: 1.5rem;
    display: flex;
    padding: 0 0.2rem;
    background: rgb(247, 247, 247);
    border-radius: 0.2rem;
    margin-top: 0.3rem;

    .lefts {
      width: 25%;
      height: 100%;
      display: flex;
      align-items: center;
      font-size: 0.3846rem;

      span {
        font-weight: 600;
      }
    }

    .rights {
      width: 75%;
      height: 100%;
      display: flex;
      align-items: center;
      justify-content: center;

      input {
        width: 100%;
        height: 100%;
      }
    }
  }
}

.bank_name.bind {
  display: flex;
  align-items: center;
  justify-content: center;
  background: #2d6ae9;
  font-size: 0.4103rem;
  color: #fff;

  span {
    font-weight: 600;
  }
}

</style>


<style scoped>
.bijnm {
    background: #f0f0f0;
    min-height: 100vh;
}


.headf{
    width: 100%;
    height: 1.1748rem;
    background: linear-gradient(-55deg,rgb(80, 122, 250),rgb(115, 131, 251));
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
.hbnh{
    position: absolute;
    left: 0.4005rem;
    font-size: 0.4272rem;
    font-weight: 500;
}
 .xind {
    position: absolute;
    right: 0.4005rem;
    color: #fff;
    font-size: 0.3738rem;
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
 .shaux {
    position: absolute;
    width: 0.4806rem;
    height: 0.4806rem;
    background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACQAAAAkCAYAAADhAJiYAAAAAXNSR0IArs4c6QAAA8VJREFUWEfNmEloXVUYx3//e18qCWitA4JUFLEURdOotcWpEKsLFbUVBONKKYIbRcEJ3VhwarvSiAspVlzoRpyow0Lr0No2LbW4cACllCK2DhREjWhy71/OzU087/W9vPuSl5K7u2f4zu9+9xuPmGeP5hkPlYFsLyTjBsQgZgBxDrAQMPA75gBiP+YTUrZK+nsmH9sWyPZSch4GhoDeiof8CbxCwiZJhyruKZa1BLLdR86TwL1ArROh0dqgpY0kPCVprIqMpkC2l5DzJnBhEyGjiBHMt8DRcv5UxPmYlU21aPaQslbST+2gjgGyfSk5HwCn1202O4DnSXlX0j/NBNvuJeMW4D7E5Q1rfiRhUNIP00HVAdk+j5ydDTBHMPeopnfafV0873HfjhgGTovGA9QKSYdbyZoCKm1mpO43mRFSbpb0Sycwk2ttLyZnK7Bsar/ZScoqSVkzmf8DZd4EPBhtDDCrJf01E5gIahE5nwEXRXIeUaqNLYFKI/4a6CkXHSbhYkk/zwYmgjqLnK+AReXYHyQsaSa/0JAzvwzcFWlnrWp6uxswU1DjvhOxJZK5QakebTxDtk8k5wjQV0yaHarp6m7CFGLthJwvI3v6jYQzG+OTSm94PdLObarpjW4DFVDjXofYPCU75zr16KP4LDnzS8Dd5eAoCae0ijOzhbQdDPxXIC1lPa1Uj9cDjXs3KiJsSCTblGj1bA+ebr8z7wcGyvM+VKLrGzUUiCeD1wtKFXJX154ivsEJkUlsxtxavn9HwhWNQCENLCgH1yvVE12jCXYz5kESPp4ukUfnbQk2NKdAhTFnfq7Ib9M/h0joD0Bz+stKl+8lJ9jO0hZMJuda9WhbcPvjYtS2V5LzReRhMduwUhUaPL5unzkUfHVuDnxPwoCk0QmgcQ8hXou8YO4Co72AnD1RtM5IuErS7snzm6WO7appVTc9LZZlu5+cvaVnP6NUj9W5fekFIZyvi7S0ptOCrJMPcOaQVIdIuEzSv8cCTVSK38xV+dEIazukjrMlHWiciwu0UDA9FGmpKwVaJ5orjHpyQ1nC7gL6G6BmU8KeCyyW9HlVsDkp8m2LjDsQLwJjJCyXdLAKVLM26BJy3gfOqBNgtgPDbdqgPjLWAA8glkf795FwZZWyplWjGIz8rY4aRXMBYkWLRnEvKTdVqdHbtdLrgftn0UqHxL2hbKXr3LvV76ty2RDa6om4Uf2yIbROr5LwbNcuG5rEjpPIuBFxDWZZeR1zcumpRzEHEfswn5Ly3kz7ubYaquIZ3Vwz74D+A2+DgOcUuMpOAAAAAElFTkSuQmCC) no-repeat 50%;
    background-size: 100%;
    right: 0.4005rem;
    top: 0.4005rem;
}
.banul {
    width: 100%;
    margin: 0 auto;
    margin-top: 0.4272rem;
    background: #fff;
}
.banul li {
    width: 9.2115rem;
    height: 1.3617rem;
    margin: 0 auto;
    border-bottom: 0.0267rem solid #ebebeb;
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    line-height: 1.3617rem;
}
.banul li span {
    width: 2.403rem;
    margin-left: 0.3471rem;
    font-size: 0.3738rem;
    color: #333;
}
.banul li input {
    width: 5.874rem;
    color: #000;
    font-size: 0.4005rem;
    background: transparent;
}
.enter{
  width: 9.078rem;
    margin: 1.335rem auto;
    height:1.2015rem;
    line-height: 1.2015rem;
    text-align: center;
    color: #fff;
    font-size: 0.3738rem;
    border-radius: 0.1335rem;
    background: linear-gradient(-55deg,rgb(80, 122, 250),rgb(115, 131, 251));
}
input::-webkit-input-placeholder {
    color: #999;
}
</style>