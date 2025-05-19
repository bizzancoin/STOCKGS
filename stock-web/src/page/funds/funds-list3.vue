<template>
  <div class="wrapper">
    
    <mt-tab-container class="order-list" v-model="selected">
      <mt-tab-container-item id="1">
        <holdPosition :selectedNumber='selectedNumber' :handleOptions='handleOptions'/>
      </mt-tab-container-item>
    </mt-tab-container>
    <foot></foot>
  </div>
</template>

<script>
import foot from '@/components/foot/foot'
import holdPosition from './compontents/fundssellorder'

export default {
  components: {
    foot,
    holdPosition
  },
  props: {
    selectedNumber: {
      type: String
    }
  },
  data () {
    return {
      selected: '1', // 选中
      list: [], // 持仓
      form: {
        pageNum: 1,
        pageSize: 10,
        currentNum: 10
      },
      hasChangeSell: false,
      hasChangeSell2: false
    }
  },
  watch: {},
  computed: {},
  created () {},
  mounted () {

  },
  methods: {
    handleOptions (opts) {
      this.hasChangeSell = opts
      if (this.hasChangeSell) {
        this.selected = '2'
      }
    }
  }
}
</script>
<style lang="less" scoped>
  .is-selected .mint-tab-item-label:hover {
    text-decoration: none;
  }

  .wrapper /deep/ .mint-tab-item-label {
    font-size: 0.264rem;
  }

  .mint-navbar .mint-tab-item.is-selected {
    border-bottom: 2px solid #d50000;
    text-decoration: none;
  }

  .mint-tab-container-item {
    // padding-top: 1.2rem;
    .mint-button--default {
      padding: 0 0.2rem;
      font-size: 0.24rem;
      height: 0.5rem;
      margin: 0.2rem 0.2rem 0;
      color: #607d8b;
      box-shadow: 0 0 1px #3b71b9;
      background: none;
    }
  }

  .mint-navbar {
    box-shadow: 0px 0px 4px rgba(6, 0, 1, 0.2);

    .mint-tab-item {
      // background: #21252a;
      padding: 0.2rem 0;

      &.is-selected {
        border: none;
        margin-bottom: 0;
      }

      .mint-tab-item-label {
        font-size: 0.22rem;
      }

      .iconfont {
        // display: block;
        font-size: 0.46rem;
        margin-bottom: 0.12rem;
        vertical-align: middle;
        margin-right: 0.12rem;
      }
    }
  }
  .sub-navbar {
    background-color: #16171D !important;
    height: .7rem;
    justify-content: center;
    box-shadow: none;

    /deep/.mint-tab-item-label {
        font-size: .24rem;
      }
    .mint-tab-item {
      position: relative;
      flex-grow: 0;
      flex-shrink:1;
      flex-basis:auto;
      width: 1.4rem;
      height: .4rem;
      background-color: #3D4456;
      padding: 0;
      line-height: .4rem;
      margin: 0.3rem .2rem 0;
      /deep/.mint-tab-item-label {
        line-height: .4rem;
        height: .4rem;
      }
      &.is-selected {
        color: #fff;
        background-color: #138EB4;
      }
    }
  }
  #app.red-theme {
    .sub-navbar {
      background-color: #E9E9E9 !important;
      .mint-tab-item {
        background-color: #CCCCCC;
        color: #000;
        &.is-selected {
          color: #fff;
          background-color: #BB1815;
        }
      }
    }
    
  }
</style>
