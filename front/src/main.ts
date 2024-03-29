import './assets/main.css'

import {createApp} from 'vue'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import 'bootstrap/dist/css/bootstrap.css'
import store from "@/scripts/store";

const app = createApp(App)
app.provide("$store", store);
app.use(store)
app.use(router)
app.use(ElementPlus)

app.mount('#app')
