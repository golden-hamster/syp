import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import WriteView from '@/views/WriteView.vue'
import LoginView from '@/views/LoginView.vue'
import ReadView from '@/views/ReadView.vue'
import AllView from '@/views/AllView.vue'
import UpdateView from '@/views/UpdateView.vue'
import MyPlaylistView from '@/views/MyPlaylistView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/login',
      name: 'login',
      component: LoginView
    },
    {
      path: '/write',
      name: 'write',
      component: WriteView
    },
    {
      path: '/article/:articleId',
      name: 'article',
      component: ReadView,
      props: true
    },
    {
      path: '/update/:articleId',
      name: 'update',
      component: UpdateView,
      props: true
    },
    {
      path: '/all',
      name: 'all',
      component: AllView
    },
    {
      path: '/my-playlist',
      name: 'myPlaylist',
      component: MyPlaylistView
    }
    // {
    //   path: '/about',
    //   name: 'about',
    //   // route level code-splitting
    //   // this generates a separate chunk (About.[hash].js) for this route
    //   // which is lazy-loaded when the route is visited.
    //   component: () => import('../views/AboutView.vue')
    // }
  ]
})

export default router
