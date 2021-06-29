<template>
	<div>
		<p>审核工具页面</p>
		<a :href="'/Xapi/download?uid='+inputHorizontalProp.uid+'&vid='+inputHorizontalProp.vid">视频下载链接</a>
		<br />
		<button v-on:click="auditPass()">审核通过</button>
		<button v-on:click="auditDenied()">审核不通过</button>
	</div>
</template>

<script>
	import jsonp from "jsonp"
	export default {
		props: [
			"inputHorizontalProp"
		],
		methods: {
			auditPass() {
				var url = this.$URLGenerator("/Xapi/changeEnabled", {
					enabled: 1,
					vid: this.inputHorizontalProp.vid
				})
				jsonp(url, {
					param: "callback",
					timeout: 3000,
					prefix: "callback",
					name: "callback_IOchangeEnabled"
				}, (err, data) => {
					if (err) {
						alert(未知错误);
					} else {
						console.log(data);
					}
				})
				alert("已审核通过此视频！");
				this.$emit("setRouterPointer", "/Admin");
			},
			auditDenied(){
				this.$emit("setRouterPointer","/Admin");
			}
		},
		data() {
			return {}
		}
	}
</script>

<style>
</style>
