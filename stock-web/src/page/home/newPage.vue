<template>
  <div :class="` ${$state.theme === 'red' ? 'red-theme' : 'black-theme'}`" class="">
    <div class="headf">
      <div>
        <h2>
          <span class="hbnh" @click="$router.go(-1)">
            <a class="fan"></a>
          </span> 详情页
        </h2>
      </div>
    </div>
    <div class="hjkl"></div>
    <div class="newsTop">
      <div class="newsTitle">{{ list.title }}</div>
      <div class="newDetail-tits">{{ list.addTime | gettime }}</div>
    </div>
    <div class="newsDetail">
      <div>{{ list.content }}</div>
      <div class="newsImg" v-if="list.imgurl">
        <img :src="list.imgurl" />
      </div>
    </div>
    <div style="height:1.3rem"></div>
  </div>
</template>

<script>
import * as api from "@/axios/api";
export default {
  created() {
    if (this.$route.query.listid) {
      this.list_id = this.$route.query.listid;
    }
    this.getqueryNewsList(this.$route.query.listid);
  },
  data() {
    return {
      list_id: "",
      list: [],
    }
  },
  mounted() {

  },
  methods: {
    async getqueryNewsList(id) {
      let data = await api.queryNewsDetail(id);
      this.list = data.data

    }

  },
  filters: {
    gettime(time) {
      if (!time) {
        return "";
      }
      var nd = new Date(time);
      var y = nd.getFullYear();
      var mm = nd.getMonth() + 1;
      var d = nd.getDate();
      var h = nd.getHours();
      var m = nd.getMinutes();
      var c = nd.getSeconds();
      if (mm < 10) {
        mm = "0" + mm;
      }
      if (d < 10) {
        d = "0" + d;
      }
      if (h < 10) {
        h = "0" + h;
      }
      if (m < 10) {
        m = "0" + m;
      }
      if (c < 10) {
        c = "0" + c;
      }
      //17:35:2922-06-2022
      return y + "-" + mm + "-" + d + " " + h + ":" + m + ":" + c;
    }
  },

}
</script>

<style scoped lang="less">
.headf {
  background: linear-gradient(-55deg, rgb(80, 122, 250), rgb(115, 131, 251));
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

.newDetail-tits {
  margin-top: 0.4rem;
  font-size: 0.4rem;
  margin-bottom: 0.4rem;
}

.newsTop {
  // border-bottom: 0.01rem solid #cdcdcd;
  padding-top: 0.3rem;
  color: #000;
  width: 96%;
  margin-left: 0;
  margin-right: 0;
  margin: 0 auto;
}

.newsTitle {
  font-size: 0.61rem;
  font-weight: 600;
  line-height: .88rem;
}

.newsDetail {
  width: 96%;
  margin-left: 0;
  margin-right: 0;
  margin: 0 auto;
  margin-top: 0.4rem;
  font-size: 0.35rem;
  line-height: 0.6rem;
}

.newsImg {
  width: 100%;
  margin-top: 0.4rem;
}

.newsImg img {
  width: 100%;
  height: 100%;
}
</style>
