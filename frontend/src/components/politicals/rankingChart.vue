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
import axios from 'axios';
import store from '@/store/index';
import {mapGetters, mapState} from "vuex";

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
              return opt.w.globals.labels[opt.dataPointIndex] + ":  " + val + "%"
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
                  return 'Aprobación'
                },
              }
            }
          }
        },          
      }
    },

    methods:{
      updatePoliticalRankig(){
       
        let listsPoliticalRankings = this.getListsPoliticalRankings.map(item => item.realName).slice(0,5)
        let listsApprobationRankings = this.getListsPoliticalRankings.map(item => item.aprobation).slice(0,5)

        //Asigna el valor de la aprobación de cada politico al gráfico
        this.series= [{
         
          data:listsApprobationRankings
        }]

        //Asigna el nombre de cada politico al gráfico
        this.chartOptions = {
          xaxis :{
              categories: listsPoliticalRankings
          }
          
        }
      },
    },
    

    mounted(){
      this.updatePoliticalRankig()
    },

    //Propiedad computada que obtiene los datos del store respecto a los politicos
    computed: {
      ...mapGetters(['getListsPoliticalRankings'])
    }


}
</script>

<style scoped>

</style>