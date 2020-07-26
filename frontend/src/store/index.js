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
      estado: false
    },
    politicianSpecific: {
      politician: "", 
    },

    politicalParty:{
      listOfPoliticalParty: null,
      dataIsReady: false,
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
      //Se activa componente
      state.politicalRanking.estado = true
    },

    SET_POLITICIAN (state, item){
      state.politicianSpecific.estado = false
      state.politicianSpecific.politician = item.politician
      state.politicianSpecific.estado = true
    },

    SET_LIST_POLITICAL_PARTY (state, list){
      state.politicalParty.listOfPoliticalParty = list.listOfPoliticalParty;
      state.politicalParty.dataIsReady = true;
    }

  },

  //Funciones asincronas
  //Acciones que llaman a mutations 
  actions: {
    //Context hace referencia al store para llamar a las mutations
    async updatePoliticalRankingAction({commit}){
      console.log("2: action"),
      await axios.get('http://localhost:8889/politician/ranking')
        .then(res=>{
          commit('SET_RANKING',{listOfPoliticians: res.data}) //Se envia json con politicos y sus atributo
        })
      },

    async updatePartyRankingAction({commit}){
      await axios.get('http://localhost:8889/politicalparty/ranking')
      .then(res =>{
        commit('SET_LIST_POLITICAL_PARTY',{listOfPoliticalParty: res.data})
      })
    }
  },

  modules: {
  },

  //Obtienen los states
  getters:{
    getListsPoliticalRankings(state){
      return state.politicalRanking.listOfPoliticians
    },
    getPolitician(state){
      return state.politicianSpecific.politician
    },
    getListPoliticalParty(state){
      return state.politicalParty.listOfPoliticalParty
    },
  }
})
