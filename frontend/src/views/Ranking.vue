<template>
    <v-container>
        <v-jumbotron>
            <v-container fill-height>
                <v-row align-center>
                    <v-col cols="9">
                        <h3 class="display-3">Ranking</h3>
                        <span class="subheading">Aqui se coloca una pequeña descripcion de la sección</span>
                    </v-col>
                </v-row>

                <v-row>
                    <v-col sm="12" md="12">
                        <v-divider class="my-3"></v-divider>
                        <v-card >
                            <!-- Grafico de ranking con un estado false inicialmente -->
                            <rankingChart v-if="politicalRanking.estado"></rankingChart>
                        </v-card>
                    </v-col>
                </v-row>

                <v-divider class="my-3"></v-divider>
                <v-row>
                    <v-col sm="6" md="6">
                        <v-card >
                            <!-- Panel de informacion con un estado false inicialmente -->
                            <informationPanel v-if="politicalRanking.estado"></informationPanel>
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
    },

    //Propiedad computada que se actualiza al existir un cambio en el index del store (state)
    //Ver Index.js en carpeta store
    computed:{
        ...mapState(['politicalRanking'])
    }
}
</script>

<style scoped>

</style>