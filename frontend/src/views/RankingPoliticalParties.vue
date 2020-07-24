<template>
      <v-container>
     <v-jumbotron>
        <v-container fill-height>
        <v-row align-center>
            <v-col cols="9">
            <h3 class="display-3">Ranking Political Parties</h3>
            <span class="subheading">Aqui se coloca una pequeña descripcion de la sección</span>
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
import {mapState, mapMutations, mapActions, mapGetters} from "vuex";
import axios from 'axios';
export default {
    
}
</script>