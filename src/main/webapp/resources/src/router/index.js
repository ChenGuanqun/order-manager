import Vue from 'vue'
import Router from 'vue-router'
import Page1 from '@/modules/Page1.vue'
import Users from '@/modules/Users.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {path: '/', component: Page1},
    {path: '/page1', component: Page1},
    {path: '/users', component: Users}
  ]
})
