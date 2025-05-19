<template>
  <div>
    <div class="chartBox" ref="myEchart"></div>
  </div>
</template>
<script>
import echarts from 'echarts'
import { Toast } from 'mint-ui'
import * as api from '@/axios/api'

export default {
  components: {},
  props: {
    code: {
      type: String,
      default: function () {}
    }
  },
  data () {
    return {
      chart: null,
      timer: null,
      ChartsTime: [],
      classStyle: 'red'
    }
  },
  watch: {},
  computed: {},
  created () {
    this.timer = setInterval(this.getDate, 60000)
    this.classStyle = localStorage.getItem('styleName') ? localStorage.getItem('styleName') : 'red'
  },
  beforeDestroy () {
    clearInterval(this.timer)
  },
  mounted () {
    this.getDate()
    this.getChrtsTime()
  },
  methods: {
    getChrtsTime () {
      // 获取时间
      this.ChartsTime = []
      if(this.$route.query.code.indexOf('hf_HSI')!=-1){
        // 9:15~12:00
        for (var i = 9; i < 12; i++) {
          let item = ''
          for (let j = 0; j < 60; j++) {
            if (i === 9 && j < 15) {

            } else {
              let min = i
              let sec = j
              if (sec < 10) {
                sec = '0' + j
              }
              if (min < 10) {
                min = '0' + i
              }
              item = min + ':' + sec
              this.ChartsTime.push(item)
            }
          }
        }
        // 13:00~16:30
        for (var i = 13; i < 17; i++) {
          let item = ''
          for (let j = 0; j < 60; j++) {
            if (i === 16 && j > 30) {

            } else {
              let min = i
              let sec = j
              if (sec < 10) {
                sec = '0' + j
              }
              if (min < 10) {
                min = '0' + i
              }
              item = min + ':' + sec
              this.ChartsTime.push(item)
            }
          }
        }
        // 17:15~23:45
        for (var i = 17; i <= 23; i++) {
          let item = ''
          for (let j = 0; j < 60; j++) {
            if (i === 17 && j < 15) {

            } else if (i === 23 && j > 45) {

            } else {
              let min = i
              let sec = j
              if (sec < 10) {
                sec = '0' + j
              }
              if (min < 10) {
                min = '0' + i
              }
              item = min + ':' + sec
              this.ChartsTime.push(item)
            }
          }
        }
      } else if(this.$route.query.code.indexOf('hf_')!=-1){
        // 0:01 ~ 23:59
        for (var i = 0; i <= 23; i++) {
          let item = ''
          for (let j = 0; j < 60; j++) {
              let min = i
              let sec = j
              if (sec < 10) {
                sec = '0' + j
              }
              if (min < 10) {
                min = '0' + i
              }
              item = min + ':' + sec
              this.ChartsTime.push(item)
            }
        }
      } else{
        // 9:30 ~ 11:30  13:00~15:00
        for (var i = 9; i < 15; i++) {
          let item = ''
          if (i === 12) {

          } else {
            for (let j = 0; j < 60; j++) {
              if (i === 11 && j > 30) {

              } else if (i === 9 && j < 30) {

              } else {
                let min = i
                let sec = j
                if (sec < 10) {
                  sec = '0' + j
                }
                if (min < 10) {
                  min = '0' + i
                }
                item = min + ':' + sec
                this.ChartsTime.push(item)
              }
            }
          }
        }
      }
    },
    async getDate () {
      let opts = {
        stockCode: this.code
        //    code:this.code,
        //   time:15,
        //   ma:5,
        //   size:50,
      }
      let data = await api.getMinuteLine(opts)
      if (data.status === 0) {
        this.initEchartMap(data.data)
      } else {
        Toast(data.msg)
      }
    },
    splitData (rawData) {
      var categoryData = []
      var values = []
      var volumes = []
      for (var i = 0; i < rawData.length; i++) {
        categoryData.push(rawData[i].splice(0, 1)[0])
        values.push(rawData[i])
        volumes.push([i, rawData[i][4], rawData[i][0] > rawData[i][1] ? 1 : -1])
      }
      return {
        categoryData: categoryData,
        values: values,
        volumes: volumes
      }
    },
    calculateMA (dayCount, data) {
      var result = []
      for (var i = 0, len = data.values.length; i < len; i++) {
        if (i < dayCount) {
          result.push('-')
          continue
        }
        var sum = 0
        for (var j = 0; j < dayCount; j++) {
          sum += data.values[i - j][1]
        }
        result.push(+(sum / dayCount).toFixed(3))
      }
      return result
    },
    valuesVolumes (data) {
      // 处理成交量 现在返回的成交量是每一次分钟返回的总量
      // 用下一个数据减去上一个数据得到这分钟的成交量
      let newData = []
      data.reduce(function (previousValue, currentValue, index, array) {
        newData.push([index, currentValue - previousValue, 1])
        return currentValue
      })
      return newData
    },
    initEchartMap (data) {
      let volumesVal = [0]
      if (data.volumes.length > 0) {
        volumesVal = this.valuesVolumes(data.volumes)
      }
      if (data.price.length <= 0) {
        data.price = [0]
      }
      // let upColor = '#00da3c';
      // let downColor = '#ec0000';
      let this_ = this
      let upColor = '#ec0000'
      let downColor = '#00da3c'
      // var data = this.splitData(rawData);
      let myChart = echarts.init(this.$refs.myEchart)
      window.onresize = myChart.resize
      let option = {
        backgroundColor: this_.classStyle === 'red' ? '#fff' : '#21252a',
        animation: true,
        // legend: { // 图例组件
        //     bottom: 10, // 图例组件离容器下侧的距离。
        //     left: 'center', // 图例组件离容器左侧的距离。
        //     data: data.stockName
        // },
        tooltip: { // 提示框组件 坐标轴指示器是指示坐标轴当前刻度的工具
          trigger: 'axis', // 坐标轴触发，主要在柱状图，折线图等会使用类目轴的图
          axisPointer: {
            type: 'cross'
          },
          backgroundColor: 'rgba(245, 245, 245, 0.8)',
          borderWidth: 1,
          borderColor: '#ccc',
          padding: 10,
          textStyle: {
            color: '#000'
          },
          position: function (pos, params, el, elRect, size) {
            var obj = { top: 20 }
            obj[['left', 'right'][+(pos[0] < size.viewSize[0] / 2)]] = 30
            return obj
          },
          formatter: function (param) {
            let param2
            if (param[0].seriesName === '成交量') {
              param2 = param[0]
              param = param[1]
            } else {
              param2 = param[1]
              param = param[0]
            }
            let classFont = data.rates[param.dataIndex] < 0 ? 'green' : 'red'
            // data.rates[] 涨跌幅度 amounts 成交额
            if (param && param2) {
              return [
                '<span style="font-size:16px;font-weight:bold;margin-right:10px;">' + param.seriesName + '</span>' + param.name + '<hr size=1 style="margin: 3px 0">',
                // '时间：' + param.name +'</br>',
                '最新: ' + '<span class="' + classFont + '" style="font-size:16px;">' + param.value + '</span>' + '<br/>',
                '涨跌幅: ' + '<span class="' + classFont + '" style="font-size:16px;">' + data.rates[param.dataIndex] + '%</span>' + '<br/>',
                '成交量(手): ' + param2.value[0] + '<br/>',
                '成交额(万): ' + (data.amounts[param.dataIndex] / 10000).toFixed(2) + '<br/>'

              ].join('')
            }
          }
          // extraCssText: 'width: 170px'
        },
        axisPointer: { // 坐标轴指示器
          link: { xAxisIndex: 'all' },
          label: {
            backgroundColor: '#777' // hover  number bg
          }
        },
        toolbox: { // 工具栏
          feature: { // 各工具配置项。//除了各个内置的工具按钮外，还可以自定义工具按钮。
            dataZoom: {// 数据区域缩放
              yAxisIndex: false
            },
            brush: { // 选框组件的控制按钮
              type: ['clear']
            }
          }
        },
        brush: { // brush 是区域选择组
          xAxisIndex: 'all',
          brushLink: 'all',
          outOfBrush: {
            colorAlpha: 0.1
          }
        },
        visualMap: { // visualMap 是视觉映射组件，
          show: false,
          seriesIndex: 5,
          dimension: 2,
          pieces: [{
            value: 1,
            color: upColor
          }, {
            value: -1,
            color: downColor
          }]
        },
        grid: [ // 直角坐标系内绘图网格  设置表格的位置
          {
            left: '10%',
            right: '6%',
            height: '65%',
            top: '10%'
          },
          {
            left: '10%',
            right: '6%',
            top: '78%',
            height: '20%'
          }
        ],
        xAxis: [
          {
            type: 'category',
            data: this_.ChartsTime,
            scale: true,
            boundaryGap: false,
            axisLine: { onZero: false },
            splitLine: { show: false },
            splitNumber: 20,
            min: 'dataMin',
            max: 'dataMax',
            axisPointer: {
              z: 100
            },
            axisLabel: {
              show: true,
              textStyle: {
                color: this_.classStyle === 'red' ? '#666' : '#ccc'
              }
            }
          },
          {
            type: 'category',
            gridIndex: 1,
            data: this_.ChartsTime,
            scale: true,
            boundaryGap: false,
            axisLine: { onZero: false },
            axisTick: { show: false },
            splitLine: { show: false },
            axisLabel: { show: false },
            splitNumber: 20,
            min: 'dataMin',
            max: 'dataMax'
            // axisPointer: {
            //     label: {
            //         formatter: function (params) {
            //             var seriesValue = (params.seriesData[0] || {}).value;
            //             return params.value
            //             + (seriesValue != null
            //                 ? '\n' + echarts.format.addCommas(seriesValue)
            //                 : ''
            //             );
            //         }
            //     }
            // }
          }
        ],
        yAxis: [
          {
            scale: true,
            splitArea: { // 设置y轴分割块颜色
              show: false,
              areaStyle: {
                color: ['rgba(33,37,42,1)', 'rgba(33,37,42,1)'],
                opacity: 0
              }
            },
            axisLabel: {
              show: true,
              textStyle: {
                color: this_.classStyle === 'red' ? '#666' : '#ccc'
              }
            },
            splitLine: {
              lineStyle: {
                // 使用深浅的间隔色
                color: this_.classStyle === 'red' ? '#eee' : '#333' // 分割线
              }
            }
          },
          {
            scale: true,
            gridIndex: 1,
            splitNumber: 2,
            axisLabel: { show: false },
            axisLine: { show: false },
            axisTick: { show: false },
            splitLine: { show: false }
          }
        ],
        dataZoom: [
          {
            type: 'inside',
            xAxisIndex: [0, 1],
            start: 0,
            end: 100
          },
          {
            show: false,
            xAxisIndex: [0, 1],
            type: 'slider',
            top: '65%',
            start: 0,
            end: 100
          }
        ],
        series: [
          {
            name: data.stockCode,
            type: 'candlestick',
            data: data.values,
            itemStyle: {
              normal: {
                color: upColor,
                color0: downColor,
                borderColor: null,
                borderColor0: null
              }
            },
            tooltip: {
              formatter: function (param) {
                param = param[0]
                return [
                  '开盘: ' + param.data[0] + '<br/>',
                  '收盘: ' + param.data[1] + '<br/>',
                  '最低: ' + param.data[2] + '<br/>',
                  '最高: ' + param.data[3] + '<br/>'
                ].join('')
              }
            }
          },
          {
            name: data.stockCode,
            type: 'line',
            data: [],
            smooth: true,
            symbol: 'none',
            areaStyle: {
              opacity: 0.5
            },
            lineStyle: {
              normal: { opacity: 0.5 }
            }
          },
          {
            name: 'MA10',
            type: 'line',
            data: [],
            smooth: true,
            lineStyle: {
              normal: { opacity: 0.5 }
            }
          },
          {
            name: data.stockCode,
            type: 'line',
            data: data.price,
            smooth: true,
            symbol: 'none', // 折线点是否显示
            lineStyle: {
              color: this_.classStyle === 'red' ? '#eee' : '#7cb5ec',
              normal: { opacity: 0.5 }
            },
            itemStyle: {
              color: this_.classStyle === 'red' ? '#608efa' : '#5076d0'
            },
            areaStyle: {
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                offset: 0,
                color: this_.classStyle === 'red' ? 'rgba(104, 137, 213, 0.6)' : 'rgba(62, 82, 129,0.6)'
              }, {
                offset: 1,
                color: this_.classStyle === 'red' ? 'rgba(104, 137, 213, 0.1)' : 'rgba(62, 82, 129,0.1)'
              }])
            }
          },
          {
            name: 'MA30',
            type: 'line',
            data: [],
            smooth: true,
            lineStyle: {
              normal: { opacity: 0.5 }
            }
          },
          {
            name: '成交量',
            type: 'bar',
            xAxisIndex: 1,
            yAxisIndex: 1,
            itemStyle: {
              color: '#c23531'
            },
            data: volumesVal
          }
        ]
      }
      myChart.setOption(option)
      // myChart.dispatchAction({
      //     type: 'brush',
      //     areas: [
      //         {
      //             brushType: 'lineX',
      //             coordRange: ['2016-06-02', '2016-06-20'],
      //             xAxisIndex: 0
      //         }
      //     ]
      // });
    }
  }
}
</script>
<style lang="less" scoped>
  .chartBox {
    height: 380px;
    width: 100%;
    width: 7.5rem;
    margin-top: -6.3%;
  }
</style>
