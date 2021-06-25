<template>
	<div>
		<p>审核工具页面</p>
		<a :href="'/Xapi/media?uid='+inputHorizontalProp.uid+'&vid='+inputHorizontalProp.vid">视频下载链接</a>
		<button></button>
	</div>
</template>

<script>
	import jsonp from "jsonp"
	export default {
		props: [
			"inputHorizontalProp"
		],
		methods: {

		},
		data() {
			return {
			}
		}
	}
</script>

<style>
</style>
