<template>
      <v-container>
     <v-jumbotron>
        <v-container fill-height>
        <v-row align-center>
            <v-col cols="9">
            <h3 class="display-3" style="color:#198CD3;">Ranking Political Parties</h3>
            <span class="subheading">Aqui se coloca una pequeña descripcion de la sección</span>
            </v-col>
        </v-row>
        <v-row>
          <v-col>
            <v-card flat outlined>
              <ranking-chart-party v-if="politicalParty.dataIsReady"></ranking-chart-party>
            </v-card>
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <v-divider class="my-3"></v-divider>
            <v-btn @click="$router.push('/')" large color="primary" class="mx-0">Volver al Inicio</v-btn>
            </v-col>
        </v-row>
        </v-container>
    </v-jumbotron>
  </v-container>
</template>

<script>
import RankingChartParty from '../components/politicalParty/rankingChartParty.vue'
import store from '../store/index'
import {mapState, mapMutations, mapActions, mapGetters} from "vuex";
import axios from 'axios';
export default {
    data:{

    },
    components: {
      RankingChartParty
    },
    store:store,

    methods:{
      updatePoliticalPartyData(){
        this.$store.dispatch('updatePartyRankingAction');
      }
    },
    
    mounted(){
      this.updatePoliticalPartyData();
    },

    computed:{
      ...mapState(['politicalParty'])
    }
}
</script>