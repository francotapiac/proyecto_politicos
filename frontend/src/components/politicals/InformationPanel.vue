<template>
    <v-container >
  
       <!-- Fila con nombres de columnas -->
      <v-row no-gutters> 
        <!-- Primera columna -->
        <v-col sm="3"> 
            
        </v-col>
    
        <!-- Segunda columna -->
        <v-col sm="6"> 
          <v-card-text class="text-md-left font-weight-black"> Nombre</v-card-text>
        </v-col>

        <!-- Tercera columna --> 
        <v-col sm="3"> 
          <v-list-item-avatar >
            <v-icon color="green">thumb_up_alt</v-icon> / <v-icon color="red">thumb_down_alt</v-icon>
          </v-list-item-avatar>
        </v-col>
      </v-row>
      <v-divider light></v-divider>
      <!-- Fila con detalle de politicos --> 
      

        <!-- Scroll en lista de politicos -->
        <v-virtual-scroll
        :items="items"
        :item-height="80"
        height="300"
      >
        <template v-slot="{ item }">
          <v-list-item :key="item" cols="12">
            
            <!-- Fila con detalle del politico -->
            <v-row no-gutters align="center" justify="center" >
              
              <v-col sm="3">
                
                <v-btn fab icon>
                  <v-avatar
                    size="50"
                    >
                    <img :src=item.src >
                  </v-avatar>
              </v-btn>
              </v-col>
              
              <v-col sm="6" class="text-sm-left"> 
                {{item.name}}
              </v-col>

              <v-col sm="3">
                {{item.positive}}%/ {{100 - item.positive}}%
              </v-col>
            </v-row>
            

            </v-list-item>
             <v-divider></v-divider>
          </template>
          
        </v-virtual-scroll>
   
    </v-container>
</template>

<script>
import store from '@/store/index'
import {mapState, mapMutations, mapActions, mapGetters} from "vuex";
import axios from 'axios';
export default {
     data: () => ({
      items: [
        {
          src: '',
          name: '',
          positive : '',
          negative: ''
        },
      ],
    }),

    methods:{
      fillListPoliticians(){
        let listPoliticians = this.getListsPoliticalRankings
     
        for(var i= 0; i < listPoliticians.length; i++){
          this.items[i] = {
            src: listPoliticians[i].urlImage,
            name: listPoliticians[i].realName,
            positive: listPoliticians[i].aprobation,
            negative: 100- listPoliticians[i].aprobation 
          }
        }
      }
    },

    created(){
      console.log("4: information Panel")
      this.fillListPoliticians()
    },
    
    //Propiedad computada que obtiene los datos del store respecto a los politicos
    computed: {
      ...mapGetters(['getListsPoliticalRankings']),

    }
}
</script>

<style scoped>
  #rounded-card {
    border-radius: 50%; 
    min-height: 500px;
    min-width: 500px;
  }
</style>