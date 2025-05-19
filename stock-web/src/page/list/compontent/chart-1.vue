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
    },
    selected: {
      type: String,
      default: function () {}
    }
  },
  data () {
    return {
      chart: null,
      hasload: false,
      classStyle: 'red'
    }
  },
  watch: {
    selected (newval) {
      if (newval === 3 && !this.hasload) {
        this.getDate()
      }
    }
  },
  computed: {},
  created () {
    this.getDate()
    this.classStyle = localStorage.getItem('styleName') ? localStorage.getItem('styleName') : 'red'
  },
  mounted () {
  },
  methods: {
    async getDate () {
      //期货
      if(this.code.indexOf('hf_')!=-1){
        let data = await api.getFuturesMinKEcharts({code: this.code, time: 5, size: 50})
        if (data.status === 0) {
          this.initEchartMap(data.data)
          this.hasload = true
        } else {
          //Toast(data.msg)
        }
      } else if(this.code.indexOf('sh')!=-1 || this.code.indexOf('sz')!=-1){
        let data = await api.getIndexMinKEcharts({code: this.code, time: 5, size: 50})
        if (data.status === 0) {
          this.initEchartMap(data.data)
          this.hasload = true
        } else {
          //Toast(data.msg)
        }
      } else{
        let opts = {
          code: this.code,
          time: 5,
          ma: 5,
          size: 50
        }
        let data = await api.getMinKEcharts(opts)
        if (data.status === 0) {
          this.hasload = true
          this.initEchartMap(data.data)
        } else {
          //Toast(data.msg)
        }
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
    initEchartMap (data) {
      let this_ = this
      let upColor = '#00da3c'
      let downColor = '#ec0000'
      // var data = this.splitData(rawData);
      let myChart = echarts.init(this.$refs.myEchart)
      window.onresize = myChart.resize
      let option = {
        backgroundColor: this_.classStyle === 'red' ? '#fff' : '#21252a',
        animation: true,
        legend: {
          bottom: 10,
          left: 'center',
          data: data.stockName
        },
        tooltip: {
          trigger: 'axis',
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
            return [
              param.seriesName + '<hr size=1 style="margin: 3px 0">',
              '开盘: ' + param.data[1] + '<br/>',
              '收盘: ' + param.data[2] + '<br/>',
              '最低: ' + param.data[3] + '<br/>',
              '最高: ' + param.data[4] + '<br/>',
              '成交量(手): ' + param2.data[1] / 100 + '<br/>',
              param.name
            ].join('')
          }
          // extraCssText: 'width: 170px'
        },
        axisPointer: {
          link: { xAxisIndex: 'all' },
          label: {
            backgroundColor: '#777'
          }
        },
        toolbox: {
          feature: {
            dataZoom: {
              yAxisIndex: false
            },
            brush: {
              type: ['clear']
            }
          }
        },
        brush: {
          xAxisIndex: 'all',
          brushLink: 'all',
          outOfBrush: {
            colorAlpha: 0.1
          }
        },
        visualMap: {
          show: false,
          seriesIndex: 5,
          dimension: 2,
          pieces: [{
            value: 1,
            color: downColor
          }, {
            value: -1,
            color: upColor
          }]
        },
        grid: [
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
            data: data.date,
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
            data: data.date,
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
            name: data.stockName,
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
            name: 'Volume',
            type: 'bar',
            xAxisIndex: 1,
            yAxisIndex: 1,
            data: data.volumes
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
