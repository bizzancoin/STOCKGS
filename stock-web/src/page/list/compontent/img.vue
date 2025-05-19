<template>
  <div class="img">
    <mt-navbar v-model="selected">
      <mt-tab-item id="1">分时</mt-tab-item>
      <mt-tab-item id="2">5分</mt-tab-item>
      <mt-tab-item id="3">15分</mt-tab-item>
      <mt-tab-item id="4">30分</mt-tab-item>
      <mt-tab-item id="5">日线</mt-tab-item>
    </mt-navbar>

    <!-- tab-container -->
    <mt-tab-container v-model="selected">
      <mt-tab-container-item id="1">
        <chart1 :selected="selected" :code="code"></chart1>
      </mt-tab-container-item>
      <mt-tab-container-item id="2">
        <chart2 :selected="selected" :code="code"></chart2>
      </mt-tab-container-item>
      <mt-tab-container-item id="3">
        <chart3 :selected="selected" :code="code"></chart3>
      </mt-tab-container-item>
      <mt-tab-container-item id="4">
        <!-- <img class="img" :src="imgList.monthImg" alt=""> -->
        <chart4 :selected="selected" :code="code"></chart4>
      </mt-tab-container-item>
      <mt-tab-container-item id="5">
        <chart5 :selected="selected" :code="code"></chart5>
      </mt-tab-container-item>
    </mt-tab-container>
  </div>
</template>

<script>
import chart1 from './chart.vue'
import chart2 from './chart-1.vue'
import chart3 from './chart-2.vue'
import chart4 from './chart-3.vue'
import chart5 from './chart-day.vue'

export default {
  components: {
    chart1,
    chart2,
    chart3,
    chart4,
    chart5
  },
  props: {
    imgList: {
      type: Object,
      default: function () {}
    },
    code: {
      type: String,
      default: function () {}
    }
  },
  data () {
    return {
      selected: '1'
    }
  },
  watch: {
    selected () {
    }
  },
  computed: {},
  created () {},
  mounted () {},
  methods: {}
}
</script>
<style lang="less" scoped>
  .mint-navbar {
    background: none;

    .mint-tab-item.is-selected {
      border: none;
    }
  }

  .img {
    min-height: 5rem;
    width: 100%;
    height: 100%;
  }
</style>
