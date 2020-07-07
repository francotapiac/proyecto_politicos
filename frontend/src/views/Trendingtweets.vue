<template>
  <v-container>
     <v-jumbotron color="grey lighten-2">
        <v-container fill-height>
        <v-layout align-center>
            <v-flex>
            <h3 class="display-3">Trending Tweets</h3>
            <span class="subheading">Aqui se coloca una pequeña descripcion de la sección</span>
            <v-divider class="my-3"></v-divider>
            <tweets :listoftweets= "tweetList" ></tweets>
            <v-btn id="btn-back-home" @click="$router.push('/')" large class="mx-0">Volver al Inicio</v-btn>
            </v-flex>
        </v-layout>
        </v-container>
    </v-jumbotron>
  </v-container>
</template>


<script>
  import axios from "axios";
  import Tweets from '@/components/Tweets.vue';
  export default {
    data: () => ({
      tweetList: null
    }),
    components: {
      Tweets,
    },
    
    
    created () {
      this.$store.commit('SET_LAYOUT', 'layout-dashboard')
      this.leerAPI();
      
    },
    
    methods: {
    async leerAPI() {
      await axios
        .get("http://localhost:8887/tweets")
        .then(response => {
          this.tweetList = response.data;
        })
        .catch(error => {
          console.log(error);
        });
        this.fixList();
    },

    fixList(){
      let auxDate;
      for (let i = 0; i < this.tweetList.length; i++) {
          auxDate = this.tweetList[i].createdAt.substring(0,10);
          this.tweetList[i].createdAt = auxDate.split("-").reverse().join("-");
      }
      console.log(this.tweetList[0].createdAt);
    }
  }

  }
</script>

<style scoped>
  #btn-back-home{
    background-color:#D90452
  }
</style>