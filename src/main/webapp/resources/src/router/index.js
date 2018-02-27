import Vue from 'vue'
import Router from 'vue-router'
import Progress from '@/modules/Progress.vue'
import Users from '@/modules/Users.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {path: '/', component: Progress},
    {path: '/progress', component: Progress},
    {path: '/users', component: Users}
  ]
})
