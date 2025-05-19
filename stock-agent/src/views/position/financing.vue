<template>
  <page-header-wrapper>
    <a-card>
      <a-tabs default-active-key="1" @tabClick="gettabchange">
        <a-tab-pane key="1" tab="融资持仓单" forceRender>
          <financingHold ref="financingHold"></financingHold>
        </a-tab-pane>
        <a-tab-pane key="2" tab="融资平仓单" forceRender>
          <financingFlat ref="financingFlats"></financingFlat>
        </a-tab-pane>
        <a-tab-pane key="3" tab="指数持仓单" forceRender>
          <indexHold ref="indexHold"></indexHold>
        </a-tab-pane>
        <a-tab-pane key="4" tab="指数平仓单" forceRender>
          <indexFlat ref="indexFlat"></indexFlat>
        </a-tab-pane>
        <!-- <a-tab-pane key="5" tab="期货持仓单" forceRender>
                    <futuresHold ref="futuresHold"></futuresHold>
                </a-tab-pane>
                <a-tab-pane key="6" tab="期货平仓单" forceRender>
                    <futuresFlat ref="futuresFlat"></futuresFlat>
                </a-tab-pane> -->
      </a-tabs>
    </a-card>
  </page-header-wrapper>
</template>

<script>
import financingHold from './components/financing-hold'
import financingFlat from './components/financing-flat'
import indexHold from './components/index-hold'
import indexFlat from './components/index-flat'
// import futuresHold from './components/futures-hold'
// import futuresFlat from './components/futures-flat'
export default {
    name: 'Financing',
    components: {
        financingHold,
        financingFlat,
        indexHold,
        indexFlat
        // futuresHold,
        // futuresFlat
    },
    data () {
        return {
        }
    },
    methods: {
        gettabchange (val) {
            if (val == 1) {
                this.$refs.financingHold.getinit()
            } else if (val == 2) {
                this.$refs.financingFlats.getinit()
            } else if (val == 3) {
                this.$refs.indexHold.getinit()
            } else if (val == 4) {
                this.$refs.indexFlat.getinit()
            } else if (val == 5) {
                this.$refs.futuresHold.getinit()
            } else if (val == 6) {
                this.$refs.futuresFlat.getinit()
            }
        }
    }
}
</script>

<style lang="less" scoped>
/deep/ .ant-tabs-nav-scroll {
    display: flex;
    justify-content: center;
}
</style>
