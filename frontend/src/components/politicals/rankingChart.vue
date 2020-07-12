<template>
     <apexcharts 
        width="100%"
        height="350"
        type="bar"
        :options="chartOptions"
        :series="series"
      ></apexcharts>
</template>

<script>
import VueApexCharts from "vue-apexcharts";
import store from '@/store/index';
import axios from 'axios';
export default {
    name: 'rankingChart',
    components: {
        apexcharts: VueApexCharts
    },
    data: function() {
      return {
          series: [{
            data: [400, 430, 448, 470, 540, 580, 690, 1100, 1200, 1380]
          }],
          chartOptions: {
            chart: {
              type: 'bar',
              height: 380,
            },
          plotOptions: {
            bar: {
              barHeight: '100%',
              distributed: true,
              horizontal: true,
              dataLabels: {
                position: 'bottom'
              },
            }
          },
          colors: ['#33b2df', '#546E7A', '#d4526e', '#13d8aa', '#A5978B', '#2b908f', '#f9a3a4', '#90ee7e',
            '#f48024', '#69d2e7'
          ],
          dataLabels: {
            enabled: true,
            textAnchor: 'start',
            style: {
              colors: ['#fff']
            },
            formatter: function (val, opt) {
              return opt.w.globals.labels[opt.dataPointIndex] + ":  " + val
            },
            offsetX: 0,
            dropShadow: {
              enabled: true
            }
          },
          stroke: {
            width: 1,
            colors: ['#fff']
          },
          xaxis: {
            categories: ['South Korea', 'Canada', 'United Kingdom', 'Netherlands', 'Italy', 'France', 'Japan',
              'United States', 'China', 'India'
            ],
          },
          yaxis: {
            labels: {
              show: false
            }
          },
          title: {
              text: 'Politicos más aprobados',
              align: 'center',
              floating: true,
          },
          subtitle: {
              text: 'Category Names as DataLabels inside bars',
              align: 'center',
          },
          tooltip: {
            theme: 'dark',
            x: {
              show: true
            },
            y: {
              title: {
                formatter: function () {
                  return 'cantidad'
                },
              }
            }
          }
        },          
      }
    },

    methods: {
          async updateRankig(){
            try{
              await axios.get('http://localhost:3000/rankingPoliticals')
                .then(res=>{
                  let nameList =  this.listNameFilter(res.data);
                  let approbation = this.listApprobattionFilter(res.data);
                  //Asignando a lista de nombres variable de data chartOptions
                  this.chartOptions = {
                    xaxis: {
                      categories: nameList
                    },
                  }
                  //Asignando la lista de aprobación a variable data de serie
                  this.series = [{
                    data: approbation
                  }]
                })
            }catch{
              console.log("Error")
            }
          },

          listNameFilter(data){
            return data.map(item => item.name);
          },

          listApprobattionFilter(data){
            return data.map(item => item.approbation);
          }
        },
        
        created(){
           console.log("approbation")
          this.updateRankig()
        }
}
</script>

<style scoped>

</style>