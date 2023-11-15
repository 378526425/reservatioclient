import Vue from 'vue'

import 'normalize.css/normalize.css' // A modern alternative to CSS resets

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/en' // lang i18n

import '@/styles/index.scss' // global css

import App from './App'
import store from './store'
import router from './router'

import '@/icons' // icon
import '@/permission' // permission control
import API from '@/api'
import CategorySelect from '@/components/CategorySelect'
import HinButton from '@/components/HinButton'
import zhLocale from 'element-ui/lib/locale/lang/zh-CN'
/**
 * If you don't want to use mock-server
 * you want to use MockJs for mock api
 * you can execute: mockXHR()
 *
 * Currently MockJs will be used in the production environment,
 * please remove it before going online ! ! !
 */
if (process.env.NODE_ENV === 'production') {
  const { mockXHR } = require('../mock')
  mockXHR()
}

// set ElementUI lang to EN
// Vue.use(ElementUI, { locale })
// 如果想要中文版 element-ui，按如下方式声明
Vue.use(ElementUI)

Vue.config.productionTip = false
Vue.prototype.$API = API
Vue.component(CategorySelect.name,CategorySelect)
Vue.component(HinButton.name,HinButton)
new Vue({
  el: '#app',
  router,
  store,
  beforeCreate() {
		Vue.prototype.$bus = this //安装全局事件总线
	},
  render: h => h(App)
})
