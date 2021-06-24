import Vue from 'vue'
import Router from 'vue-router'
import Home from '../components/Home.vue'
import User from '../components/User.vue'
import Admin from '../components/Admin.vue'
import VideoUpload from '../components/VideoUpload.vue'
import Audit from '../components/Audit.vue'
import AuditTools from '../components/AuditTools.vue'

import Register from '../components/subComponents/Register.vue'

Vue.use(Router)
const router = new Router({
	mode: "history",
	routes: [{
			path: '/',
			redirect: Home
		},
		{
			path: '/Home',
			name: 'Home',
			component: Home
		},
		{
			path: '/User',
			name: 'User',
			component: User
		},
		{
			path: '/Admin',
			name: 'Admin',
			component: Admin
		},
		{
			path: '/Register',
			name: 'Register',
			component: Register
		},
		{
			path: '/VideoUpload',
			name: 'VideoUpload',
			component: VideoUpload
		},
		{
			path:'/Audit',
			name:'Audit',
			component:Audit
		},
		{
			path:'/AuditTools',
			name:'AuditTools',
			component:AuditTools
		}
	]
})
export default router
