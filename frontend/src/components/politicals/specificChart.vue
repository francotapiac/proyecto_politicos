<template>
    <div>
      <!-- Titulo del gráfico -->
      <v-card-title >
        <v-row align="center">
        <v-col >
          <h4 class="text-sm-left">Gráfico Circular</h4>
        </v-col>
        <v-divider class="mx-4" vertical></v-divider>
        <v-col>
          <v-select
                id="btn-cantidad"
                class="my-2"
                :items="listOfPolitician"
                label="Ver gráfico de un politico"
                outlined
                @input="selectPolitician"
            ></v-select>
        </v-col>
        </v-row> 
      </v-card-title>
      <v-card-actions>
        <apexchart type="pie" width="380" :options="chartOptions" :series="series"></apexchart>
      </v-card-actions>
    </div>
</template>

<script>
import VueApexCharts from "vue-apexcharts";
import store from '@/store/index';
import {mapGetters, mapMutations, mapState} from "vuex";
export default {
    name: 'specificChart',
    components: {
        apexchart: VueApexCharts
    },

    data: function(){
        return{
          listOfPolitician: [],
          series: [5, 10, 35, 33, 22],
          chartOptions: {
            chart: {
              width: 380,
              type: 'pie',
              },
            labels: ['Team A', 'Team B', 'Team C', 'Team D', 'Team E'],
            responsive: [{
              breakpoint: 480,
              options: {
                chart: {
                  width: 200
                },
                legend: {
                  position: 'bottom'
                }
              }
            }]
          },
        }
      },

    methods:{
        //Se llama a mutation de store
        ...mapMutations(['SET_POLITICIAN']),
        //Método encargado de mostrar un select con los politicos entregados por el Backend
        updateListOfPolitician(){
          this.listOfPolitician = this.getListsPoliticalRankings.map(item => item.realName)
        },

        selectPolitician(event){
          let politician = this.getListsPoliticalRankings.filter(item => item.realName == event)
          this.SET_POLITICIAN({politician: politician})
          this.chartOptions = {
            labels: ["Positivo","Negativo","Muy positivo","Muy negativo"]
            }
          this.series = [politician.countPositive,politician.countNegative,politician.countVeryPositive,politician.countVeryNegative]
        },

        createSpecificChart(){
            //Asigando primer nombre a lista de pie
            let politician = this.getListsPoliticalRankings[0]
            this.chartOptions = {
              labels: ["Positivo","Negativo","Muy positivo","Muy negativo"]
            }
            this.series = [politician.countPositive,politician.countNegative,politician.countVeryPositive,politician.countVeryNegative]

        },

    },

    created(){
      this.updateListOfPolitician()
      this.createSpecificChart()
    },
    
    mounted(){
      this.selectPolitician()
    },

    //Propiedad computada que obtiene los datos del store respecto a los politicos
    computed: {
      ...mapGetters(['getListsPoliticalRankings','getPolitician']),

    }



}
</script>