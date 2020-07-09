<template>
  <v-container>
    <v-layout>
      <v-flex>
          <h1>
        Pagina de Inicio
      </h1>
      <v-btn @click="$router.push('/trendingtweets')" large color="primary" class="mx-0">Visualizar Trending Tweets</v-btn>
   
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>


export default {
  name: 'Home',
  created () {
    this.$store.commit('SET_LAYOUT', 'layout-inicio')
  }
}
</script>
