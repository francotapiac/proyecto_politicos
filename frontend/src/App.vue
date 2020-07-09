<template>
  <component v-bind:is="layout"></component>
</template>

<script>
 import store from '@/store'  // <<< 
 import {mapState} from 'vuex'
import LayoutInicio from './components/layouts/LayoutInicio'
import LayoutDashboard from './components/layouts/LayoutDashboard'

export default {
  name: 'App',
  store: store,
  components: {
    'layout-inicio': LayoutInicio,
    'layout-dashboard': LayoutDashboard},
  computed: mapState(['layout']),
};
</script>
