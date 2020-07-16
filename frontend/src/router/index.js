import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import TrendingTweets from '../views/Trendingtweets.vue'

import Ranking from '../views/Ranking.vue'

Vue.use(VueRouter)

  const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/trendingtweets',
    name: 'Trendingtweets',
    component: TrendingTweets  
  },
  {
    path: '/rankings',
    name: 'Ranking',
    component: Ranking  
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  }
]

const router = new VueRouter({
  routes
})

export default router
