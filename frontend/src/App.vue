<template>
  <div id="app">
    <SideNavPage></SideNavPage>
    <router-view/>
  </div>
</template>

<script>
import SideNavPage from '@/components/Sidebar.vue'
export default {
  name: 'App',
  components: {
    SideNavPage
  }
}
</script>

<style>
@import url('https://fonts.googleapis.com/css?family=Roboto+Condensed');
#app {
  font-family: 'Roboto Condensed', sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #D1DBBD;
  background: rgb(1,17,38);
  background: linear-gradient(180deg, rgba(1,17,38,1) 0%, rgba(1,28,64,1) 11%, rgba(3,40,89,1) 22%, rgba(7,73,115,1) 100%, rgba(93,138,166,1) 100%);}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
