<template>
  <div class="bank_card_page">
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