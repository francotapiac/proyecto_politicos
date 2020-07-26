<template>
    <v-container>
        <v-jumbotron>
            <v-container fill-height>
                <v-row align-center>
                    <v-col cols="9">
                        <h3 class="display-3" style="color:#198CD3;">Ranking</h3>
                        <span class="subheading">Aqui se coloca una pequeña descripcion de la sección</span>
                    </v-col>
                </v-row>

                <!-- Gráfico de Ranking: primera fila -->
                <v-row align="center">
                    <v-col sm="12" md="12">
                        <v-divider class="my-3"></v-divider>
                        <v-card id="card-ranking" flat outlined>
                            <!-- Grafico de ranking con un estado false inicialmente -->
                            <rankingChart v-if="politicalRanking.estado"></rankingChart>
                        </v-card>
                        <v-card v-if="!politicalRanking.estado" id="card-ranking" flat outlined >
                            <div class="text-center">
                                <v-progress-circular
                                size="69"
                                rotate=5
                                width=9
                                color="primary"
                                indeterminate
                                ></v-progress-circular>
                            </div>
                        </v-card>
                    </v-col>
                </v-row>

                <v-divider class="my-3"></v-divider>
                
                <v-row>
                <!-- Gráfico de pie: segunda fila y columna izquierda-->
                    <v-col sm="12" md="6">
                        <v-card id="card-specific-chart" height="100%" flat outlined>
                            <specificChart v-if="politicalRanking.estado"></specificChart>
                        </v-card>
                        <v-card v-if="!politicalRanking.estado" id="card-ranking" flat outlined >
                            <div class="text-center">
                                <v-progress-circular
                                size="69"
                                rotate=5
                                width=9
                                color="primary"
                                indeterminate
                                ></v-progress-circular>
                            </div>
                        </v-card>
                    </v-col>

                <!-- Panel de información: segunda fila y columna derecha -->
                    <v-col sm="12" md="6">
                        <v-card  id="card-information-panel" height="100%" flat outlined>
                            <informationPanel v-if="politicalRanking.estado"></informationPanel>
                        </v-card>
                        <v-card v-if="!politicalRanking.estado" id="card-ranking" flat outlined >
                            <div class="text-center">
                                <v-progress-circular
                                size="69"
                                rotate=5
                                width=9
                                color="primary"
                                indeterminate
                                ></v-progress-circular>
                            </div>
                        </v-card>
                    </v-col>
                </v-row>
            </v-container>
        </v-jumbotron>
    </v-container>
</template>

<script>
import RankingChart from '../components/politicals/rankingChart.vue'
import InformationPanel from '../components/politicals/InformationPanel.vue'
import SpecificChart from '../components/politicals/specificChart.vue'
import store from '../store/index'
import {mapState, mapMutations, mapActions, mapGetters} from "vuex";
import axios from 'axios';

export default {
    data:{
        dataReady: false,
        estado: false
    },
    components: {
      RankingChart,
      InformationPanel,
      SpecificChart
    },
    store:store,

    //Metodo encargado de actualizar 
    methods:{
        updatePoliticalData(){
            this.$store.dispatch('updatePoliticalRankingAction')
        }
    },
    
    //Carga las variables obtenidas del bakend posteriormente a ser cargado el template
    //Cada variable se carga en action
    mounted () {
        console.log("1: Ranking vista principal")
        this.updatePoliticalData()
        this.dataReady=true;
    },

    //Propiedad computada que se actualiza al existir un cambio en el index del store (state)
    //Ver Index.js en carpeta store
    computed:{
        ...mapState(['politicalRanking'])
    }
}
</script>

<style scoped>
    #card-ranking{
    }
    #card-specific-chart{
    }

    #card-information-panel{
    }
</style>