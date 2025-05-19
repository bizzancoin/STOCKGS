<template>
  <div style="width: 100%;height: 100%;">
    <div style="width: 100%;height: 100%;" :id="'main'+ids"></div>
  </div>
</template>
<script>
  import * as echarts from 'echarts';
  export default{
    props:{
      ids:{
        type:String,
        default: '0',
      },
      colorType:{
        type:Number,
        default: 0,
      }
    },
    data () {
      return {
        myChart:"",
      };
    },
    methods: {
      initCharts () {
        var chartDom = document.getElementById('main'+this.ids);
       var myChart = echarts.init(chartDom, 'dark');
        var option;
        var greenOrRed="";
        var greenOrReds="";
        console.log(this.colorType)
        if(this.colorType>0){
          greenOrRed='rgba(65,172,117,0.6)'
          greenOrReds='rgba(65,172,117,0.05)'
        }else{
          greenOrRed='rgba(166,10,36,0.6)'
          greenOrReds='rgba(166,10,36,0.05)'
        }
        option = {
          xAxis: {
            show:false,
            type: 'category',
            boundaryGap: false,

          },
          yAxis: {
            show:false,
            type: 'value'
          },
          tooltip: {
                              show: true,
                              extraCssText:'100%;height: 1.5385rem;'
                          },
          backgroundColor: 'rgba(0,0,0,0)',
          color: {
              type: 'linear',
              x:0,
              y:0,
              x2: 0,
              y2: 1,
              colorStops: [{
                  offset: 0, color: greenOrRed    // 0% 处的颜色
              }, {
                  offset: 1, color: greenOrReds   // 100% 处的颜色
              }],
              globalCoord: false // 缺省为 false
          },
          series: [
            {
              data: [820, 932, 901, 934, 1000, 900, 1320,820, 932, 901, 934, 1000, 900,],
              type: 'line',
              areaStyle: {}
            }
          ]
        };

        option && myChart.setOption(option);
      }
    },
    mounted () {
      this.initCharts();
      window.onresize = function() {
        this.myChart.resize();
      };
    }
  }
</script>
