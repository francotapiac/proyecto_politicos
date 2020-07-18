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

    //Data donde se crea el grafico de ranking
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
                position: 'bottom',
                
              },
            }
          },
          colors: ['#D90452', '#A6035D', '#BC0368', '#3B0273', '#F25C5C', 
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
            max: 100,
            labels: {
              show: false,
            }
          },
          title: {
              text: 'Politicos más aprobados',
              align: 'center',
              floating: true,
              style: {
                fontSize:  '20px',
                fontWeight:  'bold',
                fontFamily:  undefined,
                color:  '#FFF'
              },
          },
          subtitle: {
              text: 'Porcentaje de aprobación',
              align: 'center',
              style: {
                fontSize:  '12px',
                fontWeight:  'normal',
                fontFamily:  undefined,
                color:  '#FFF'
              },
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

    //Propiedad computada. Obtiene una lista de politicos desde el store (index)
    computed: {
      //La lista de politicos se obtiene de getters.
      //getListsPoliticalRankings corresponde al nombre de la cabecera del metodo en store.
      ...mapGetters(['getListsPoliticalRankings'])
    },

    //En esta sección se realizan los cambios sobre el data, con el objetivo de modificar el gráfico.
    methods:{

      //Actualiza el gráfico de ranking según los politicos obtenidos del back
      updatePoliticalRankig(){

        //this.getListsPoliticalRankings corresponde a un método creado en el store (index), el cual es llamado desde el computed
        let listsPoliticalRankings = this.getListsPoliticalRankings.map(item => item.realName).slice(0,5)     //slice retorna los elementos del 0 a 4 de la lista
        let listsApprobationRankings = this.getListsPoliticalRankings.map(item => item.aprobation).slice(0,5)

        //Asigna el valor de la aprobación de cada politico al gráfico
        this.series= [{
          data:listsApprobationRankings,
          
        }]

        //Asigna el nombre de cada politico al gráfico
        this.chartOptions = {
          xaxis :{
              categories: listsPoliticalRankings
              
          } 
        }
      },
    },
    

    //Se llama al método luego de crear el componente, debido a que se usa mounted
    mounted(){
      this.updatePoliticalRankig()
    }
}
</script>

<style scoped>

</style>