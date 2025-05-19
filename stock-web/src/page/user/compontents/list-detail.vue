<template>
  <div class="list-header">
    <mt-navbar v-model="selected">
      <mt-tab-item id="1"></mt-tab-item>
    </mt-navbar>
    <mt-tab-container v-model="selected">
      <mt-tab-container-item id="1">
        <capitalAll/>
      </mt-tab-container-item>
    </mt-tab-container>
  </div>
</template>

<script>
import capitalAll from './capital-all'

export default {
  components: {
    capitalAll
  },
  props: {},
  data () {
    return {
      selected: '1'
    }
  },
  watch: {},
  computed: {},
  created () {},
  mounted () {},
  methods: {}
}
</script>
<style lang="less" scoped>
  .list-header {
    .mint-navbar {
      background: none;
      // border-bottom:0.01rem solid #3f444a;
    }
  }

  .mint-navbar {
    .mint-tab-item {
      padding: 0;
      font-size: 0.2rem;
    }

    .mint-tab-item.is-selected {
      color: #21252a;
      border-bottom: 0.01rem solid #3f444a;
    }
  }
</style>
