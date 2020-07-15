import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios';

Vue.use(Vuex)

export default new Vuex.Store({

  //Variables que son almacenadas en caché
  state: {
    layout: 'layout-dashboard',

    politicalRanking: {
      listOfPoliticians: null,
      listOfApprobations: null,
      estado: false
    }
  },

  //Funciones sincronas
  mutations: {
    SET_LAYOUT (state, newLayout){
      state.layout = newLayout
    },

    SET_RANKING(state,lists){
      console.log("3: mutation")

      state.politicalRanking.listOfPoliticians = lists.listOfPoliticians,
      state.politicalRanking.listOfApprobations = lists.listOfApprobations,
      //Se activa componente
      state.politicalRanking.estado = true
      console.log("lista de politicos: " + state.politicalRanking.listOfPoliticians)
      console.log("lista de aprobaciones: " + state.politicalRanking.listOfApprobations)
    }

  },

  //Funciones asincronas
  //Acciones que llaman a mutations 
  actions: {
    //Context hace referencia al store para llamar a las mutations
    async updatePoliticalRankingAction({commit}){
      console.log("2: action"),
      await axios.get('http://localhost:3000/rankingPoliticals')
        .then(res=>{
          commit('SET_RANKING',{listOfPoliticians:res.data.map(item => item.name),listOfApprobations:res.data.map(item => item.approbation)})
      })
    }
  },

  modules: {
  },

  //Obtienen los states
  getters:{
    getListsPoliticalNameRankings(state){
      return state.politicalRanking.listOfPoliticians
    },

    getListsPoliticalApprobationRankings(state){
      return state.politicalRanking.listOfApprobations
    }
  }
})
