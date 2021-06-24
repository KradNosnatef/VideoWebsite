<template>
	<div>
		<p>管理后台页面</p>
		<button v-on:click="gotoAudit()">审核入口</button>
	</div>
</template>

<script>
	export default {
		data() {
			return {

			}
		},
		methods: {
			gotoAudit(){
				this.$emit("setRouterPointer","/Audit")
			}
		}
	}
</script>

<style>
</style>
