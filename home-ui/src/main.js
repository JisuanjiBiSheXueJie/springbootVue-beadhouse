import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementPlus from 'element-plus'
import zhCn from 'element-plus/es/locale/lang/zh-cn'
import 'element-plus/dist/index.css'
import naiveUi from 'naive-ui'

createApp(App).use(store).use(ElementPlus, {
    locale: zhCn,size: "small"
}).use(router).use(naiveUi).mount('#app')
