import Vue from 'vue'
import App from './App.vue'
import Router from 'vue-router'
import router from './router/index.js'
import {
	VueJsonp
} from 'vue-jsonp'

import VideoPlayer from 'vue-video-player'
import 'vue-video-player/src/custom-theme.css'
import 'video.js/dist/video-js.css'
Vue.use(VideoPlayer)

Vue.use(VueJsonp)

Vue.config.productionTip = false

Vue.prototype.$URLGenerator = function(urlset, paramStruct) {
	var urlresult = urlset + "?";
	let paramKeys = Object.keys(paramStruct);

	for (var i = 0; i <= paramKeys.length - 1; i++) {
		urlresult += paramKeys[i] + "=" + paramStruct[paramKeys[i]];
		if (i < paramKeys.length - 1) {
			urlresult += '&';
		}
	}
	return (urlresult);
}


new Vue({
	router,
	render: h => h(App),
}).$mount('#app')
