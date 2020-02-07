import Vue from 'vue'
import App from './App'

// wbsocketapi
import * as wsClient from 'static/js/WebsocketClient.js'
// 全局常量
import globalConstant from 'static/js/GlobalConstant.js'

Vue.prototype.wsClient = wsClient
Vue.prototype.globalConstant = globalConstant

Vue.config.productionTip = false

App.mpType = 'app'

const app = new Vue({
    ...App
})
app.$mount()
