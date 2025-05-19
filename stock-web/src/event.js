import Vue from 'vue'

const state = new Vue({
  data: {
    theme: 'black'
  },
  methods: {
    toggleTheme: function () {
      this.theme = (this.theme === 'black' ? 'red' : 'black')
      window.localStorage.setItem('vue-liang-rong-wap-theme', JSON.stringify({data: this.theme}))
    }
  },
  created: function () {
    const theme = JSON.parse(window.localStorage.getItem('vue-liang-rong-wap-theme'))
    if (theme && theme.data) {
      this.theme = theme.data
    }
  }
})

export default state
