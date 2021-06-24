<template>
	<div v-on:click="onClick()" v-bind:style="styleObject" v-on:mouseover="mouseOver()" v-on:mouseout="mouseOut()"
		v-on:mousedown="mouseDown()" v-on:mouseup="mouseUp()">
		<p>视频标题：{{inputTitle}}</p>
		<p>发布人：{{inputUsername}}</p>
		<p v-if="inputEnabled=='0'">视频状态：审核中</p>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				styleObject: {
					marginTop: "60px",
					marginLeft: "60px",
					marginRight: "60px",
					marginBottom: "60px",
					border: "2px solid blue",
					boxShadow: "3px 3px 1.5px #888888",
					borderRadius:"1.5625rem"
				}
			}
		},
		props: [
			"inputTitle",
			"inputUsername",
			"inputUid",
			"inputVid",
			"inputEnabled"
		],
		methods: {
			onClick() {
				//console.log("i am clicked!");
				this.$emit("onItemClicked",{
					uid:this.inputUid,
					vid:this.inputVid,
					title:this.inputTitle,
					username:this.inputUsername,
					enabled:this.inputEnabled
				});
			},
			mouseDown() {
				this.styleObject.border = "2px solid grey"
			},
			mouseUp() {
				this.styleObject.border = "2px solid blue"
			},
			mouseOver() {
				this.styleObject.boxShadow = "none";
			},
			mouseOut() {
				this.styleObject.boxShadow = "3px 3px 1.5px #888888";
			}
		},
		watch: {},
		mounted: function() {

		}
	}
</script>

<style>
</style>
