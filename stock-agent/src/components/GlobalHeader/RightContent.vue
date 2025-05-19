<template>
  <div :class="wrpCls">
    <avatar-dropdown :menu="showMenu" :current-user="currentUser" :class="prefixCls" />
    <!-- <select-lang :class="prefixCls" /> -->
  </div>
</template>

<script>
import AvatarDropdown from './AvatarDropdown'
import SelectLang from '@/components/SelectLang'
import { agentgetAgentInfo } from '@/api/home'
export default {
  name: 'RightContent',
  components: {
    AvatarDropdown,
    SelectLang
  },
  props: {
    prefixCls: {
      type: String,
      default: 'ant-pro-global-header-index-action'
    },
    isMobile: {
      type: Boolean,
      default: () => false
    },
    topMenu: {
      type: Boolean,
      required: true
    },
    theme: {
      type: String,
      required: true
    }
  },
  data () {
    return {
      showMenu: true,
      currentUser: {}
    }
  },
  computed: {
    wrpCls () {
      return {
        'ant-pro-global-header-index-right': true,
        [`ant-pro-global-header-index-${(this.isMobile || !this.topMenu) ? 'light' : this.theme}`]: true
      }
    }
  },
  mounted () {
    this.getAgentInfo()
  },
  methods: {
    // 获取代理信息
    getAgentInfo () {
      agentgetAgentInfo().then(res => {
        if (res.status == 0) {
          setTimeout(() => {
            this.currentUser = {
              name: res.data.agentName
            }
          }, 1500)
        } else {
          setTimeout(() => {
            this.currentUser = {
              name: '代理'
            }
          }, 1500)
        }
      })
    }
  }
}
</script>
