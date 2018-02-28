import Vue from 'vue'
import Router from 'vue-router'
import OrderProgress from '@/modules/OrderProgress.vue'
import Users from '@/modules/Users.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {path: '/', component: OrderProgress},
    {path: '/orderProgress', component: OrderProgress},
    {path: '/users', component: Users}
  ]
})
