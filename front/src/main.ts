import './assets/main.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'
import piniaPluginPersistedstate, { createPersistedState } from 'pinia-plugin-persistedstate'

import App from './App.vue'
import router from './router'

import 'normalize.css'

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

import 'bootstrap/dist/css/bootstrap.css'
import axios from 'axios'

import './styles/global.css'

const app = createApp(App)

app.use(ElementPlus)
app.use(router)
app.use(
  createPinia().use(
    createPersistedState({
      storage: localStorage,
      auto: true
    })
  )
)
axios.defaults.baseURL = 'http://localhost:8080'
axios.defaults.withCredentials = true

app.mount('#app')

// const pinia = createPinia()
// pinia.use(
//   createPersistedState({
//     storage: localStorage,
//     auto: true
//   })
// )
