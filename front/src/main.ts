import './assets/main.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'

import 'normalize.css'

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

import 'bootstrap/dist/css/bootstrap.css'
import axios from 'axios'

const app = createApp(App)

app.use(ElementPlus)
app.use(createPinia())
app.use(router)
axios.defaults.baseURL = 'http://localhost:8080'
axios.defaults.withCredentials = true

app.mount('#app')
