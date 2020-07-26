<template>
  <v-container>
     <v-jumbotron>
        <v-container fill-height>
        <v-row align-center>
            <v-col cols="9">
            <h3 class="display-3" style="color:#198CD3;">Trending Tweets</h3>
            <span class="subheading">Aqui se coloca una pequeña descripcion de la sección</span>
            </v-col>
            <v-col cols="12" sm="3">
              <v-select
              id="btn-cantidad"
              class="my-2"
              :items="dropdown_items"
              label="¿Cuantos tweets deseas ver?"
              outlined
              @input="changeNumTweets"
              ></v-select>
            </v-col>
        </v-row>
        <v-row>
          <v-col>
            <v-divider class="my-3"></v-divider>
            <tweets v-if="dataIsReady" :listoftweets= "tweetList" :numberoftweets= "numtweets" ></tweets>
            <v-btn id="btn-back-home" @click="$router.push('/')" large class="mx-0">Volver al Inicio</v-btn>
            </v-col>
        </v-row>
        </v-container>
    </v-jumbotron>
  </v-container>
</template>


<script>
  import axios from "axios";
  import Tweets from '@/components/Tweets.vue';
  export default {
    data: () => ({
      tweetList: null,
      numtweets:10,
      dropdown_items: [5, 10, 15, 20, 25, 30, 35, 40, 45, 50],
      dataIsReady: false,
    }),
    components: {
      Tweets,
    },
    
    
    created () {
      this.$store.commit('SET_LAYOUT', 'layout-dashboard');
      this.leerAPI();
      console.log("¡created() existoso!");
    },
    
    methods: {
    async leerAPI() {
      this.dataIsReady = false;
      await axios
        .get('http://localhost:8889/tweets/sort/51' /*+ this.numtweets.toString()*/)
        .then(response => {
          this.tweetList = response.data;
        })
        .catch(error => {
          console.log(error);
        });
        this.fixList();
        this.dataIsReady = true;
        console.log("¡leerAPI() existoso!");
    },

    fixList(){
      let auxDate;
      for (let i = 0; i < this.tweetList.length; i++) {
          auxDate = this.tweetList[i].createdAt.substring(0,10);
          this.tweetList[i].createdAt = auxDate.split("-").reverse().join("-");
      }
    }, 

    changeNumTweets(Event){
      this.dataIsReady = false;
      this.numtweets= Event;
      this.dataIsReady = true;
      console.log("¡changeNumTweets() existoso!");
      //this.leerAPI();
      }
  
  },

  }
</script>

<style scoped>
  #btn-back-home{
    background-color:#198CD3;
    color:whitesmoke;
  }
  #btn-cantidad{
    background: rgb(14,0,27);
    background: linear-gradient(90deg, rgba(14,0,27,0.7) 0%, rgba(14,0,27,0.7035013834635417) 100%);
  }
</style>