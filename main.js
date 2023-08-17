import App from './App'
import Vue from 'vue'
import './uni.promisify.adaptor'
Vue.config.productionTip = false
App.mpType = 'app'
// 挂载全局filter
Vue.filter('timeUnitFormat', function(val) {
	switch (val) {
		case 'HOUR':
			return '小时';
		case 'DAY':
			return '天';
		default:
			return '分钟';
	}
});

Vue.filter('statusFormat', function(val) {
	switch (val) {
		case 'WAIT':
			return '待服务';
		case 'SERVEB':
			return '已服务';
		case 'EXPIRED':
			return '过期';
		case 'CANCELED':
			return '取消';
		default:
			return '';
	}
})
const app = new Vue({
	...App
})
app.$mount()