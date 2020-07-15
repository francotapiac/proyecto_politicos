<template>
    <v-container>
        <v-row justify="space-between">
            <v-col v-for="(item, i) in items" :key="i" cols="12">

                <div class="d-flex flex-no-wrap justify-space-between">
                    <v-card-title >
                         <p class="display-1 text--primary">{{item.title}}</p>
                    </v-card-title>
                    <v-card-subtitle v-text="item.artist"></v-card-subtitle>
                    <v-avatar class="ma-3" size="125" tile>
                        <v-img :src="item.src"></v-img>
                    </v-avatar>
                </div>
            </v-col>
        </v-row>
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
          src: 'http://www.gamba.cl/wp-content/uploads/2012/07/Pi%C3%B1era-comiendo-chorip%C3%A1n.jpg',
          title: 'Piñera',
          artist: 'Foster the People',
        },
        {
          src: 'https://www.laizquierdadiario.cl/IMG/arton100906.jpg',
          title: 'Kast',
          artist: 'Ellie Goulding',
        },
      ],
    }),

  
    mounted(){
      console.log("4: information Panel")
      this.items[0].artist =  this.getListsPoliticalNameRankings
    },
    
    //Propiedad computada que obtiene los datos del store respecto a los politicos
    computed: {
      ...mapGetters(['getListsPoliticalNameRankings'])
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