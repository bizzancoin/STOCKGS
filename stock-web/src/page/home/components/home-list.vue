<template>
  <div class="wrapper">
    <ul class="table-list" v-if="false">
      <li class="title">
        <div>
          <ul class='clearfix'>
            <li class="li-title">股票</li>
            <li class="li-base">最新</li>
            <li class="li-base">涨跌幅</li>
            <!-- <li class="li-base">
            </li> -->
          </ul>
        </div>

      </li>
    </ul>
    <ul>
      <li v-for="item in list" :key="item.key">
        <div class="box-gp" @click="toDetail(item.code)">
          <!-- <div class="name">
            {{item.title}}<span class="code">{{item.code}}</span>
            <span class="pull-right">{{item.status==1?'可买入':'不可买'}}</span>
          </div> -->
          <div class="con clearfix">
            <div class="col-xs-4">
              <p><span>{{item.title}}</span></p>
              <p class="title"><span>{{item.code}}</span></p>
            </div>
            <div class="col-xs-4">
              <p class="number red">{{item.nowPrice}}</p>
              <p class="title">最新价格</p>
            </div>
            <div class="col-xs-4">
              <p class="number red">{{item.gain}}</p>
              <p class="title">涨跌幅</p>
            </div>
          </div>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  components: {},
  props: {},
  data () {
    return {
      list: [
        { title: '浦发银行', code: '600000', nowPrice: '11.01', gain: '2.04%', high: '11.01', low: '10.07', status: 1 },
        { title: '白云机场', code: '600000', nowPrice: '11.01', gain: '2.04%', high: '11.01', low: '10.07', status: 1 },
        { title: '东风汽车', code: '600000', nowPrice: '11.01', gain: '2.04%', high: '11.01', low: '10.07', status: 1 },
        { title: '中国国贸', code: '600000', nowPrice: '11.01', gain: '2.04%', high: '11.01', low: '10.07', status: 1 }
      ]
    }
  },
  watch: {},
  computed: {},
  created () {},
  mounted () {},
  methods: {
    toDetail () {
      // 去列表页面
      this.$router.push('/listdetail')
    },
    getDetail () {

    }
  }
}
</script>
<style lang="less" scoped>

</style>
