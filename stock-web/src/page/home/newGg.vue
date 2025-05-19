<template>
  <div :class="` ${$state.theme === 'red' ? 'red-theme' : 'black-theme'}`"  class="">
    
    <div class="newsTop">
      <div class="newsTitle">{{list.artTitle}}</div>
      <div class="newDetail-tits">{{ list.addTime| gettime}}</div>
    </div>
    <div class="newsDetail">
      <div style="white-space: pre-line;">{{list.artCnt}}</div>
      <div class="newsImg" v-if="list.artImg">
        <img :src="list.artImg"/>
      </div>
    </div>
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
	  data () {
		  return{
        list_id:"",
        list:[],
		  }},
	  mounted(){

	  },
	  methods:{
     async getqueryNewsList(){
        let data = await api.getArtList();
          this.list = data.data.list[0]
      }
      
	  },
    filters:{
      gettime(time) {
        if (!time) {
          return "";
        }
        var nd = new Date(time );
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
  .newDetail-tits{
    margin-top: 0.4rem;
    font-size: 0.4rem;
    margin-bottom: 0.4rem;
  }
  .newsTop{
    border-bottom: 0.01rem solid #cdcdcd;
    padding-top: 0.3rem;
    color: #000;
    width: 96%;
    margin-left: 0;
    margin-right: 0;
    margin: 0 auto;
  }
  .newsTitle{
    font-size: 0.55rem;
    font-weight: 500;
  }
  .newsDetail{
    width: 96%;
    margin-left: 0;
    margin-right: 0;
    margin: 0 auto;
    margin-top: 0.4rem;
    font-size: 0.35rem;
    line-height: 0.6rem;
  }
  .newsImg{
    width: 100%;
    margin-top: 0.4rem;
  }
  .newsImg img{
    width: 100%;
    height: 100%;
  }
</style>
