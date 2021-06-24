<template>
	<div class="maindiv">
		<ItemCard v-for="post in itemCards" v-bind:inputTitle="post.filename" v-bind:inputUsername="post.username"
			v-bind:inputUid="post.uid" v-bind:inputVid="post.vid" v-bind:inputEnabled="post.enabled">
		</ItemCard>
	</div>
</template>

<script>
	import ItemCard from './subComponents/ItemCard.vue'
	import jsonp from "jsonp"
	export default {
		data() {
			return {
				itemCards: null
			}
		},
		components: {
			ItemCard
		},
		mounted: function() {
			jsonp(
				"/Xapi/Video/listAllMyVideo", {
					param: "callback",
					timeout: 10000,
					prefix: "callback",
					name: "callback_IOlistAllMyVideo"
				},
				(err, data) => {
					if (err) {
						alert("获取视频数据时出错");
					} else {
						this.itemCards = data;
						console.log(this.itemCards);

					}
				}
			)
		}
	}
</script>

<style>
	.maindiv {
		height: auto;
		border: 1px #41B883;
		box-shadow: 10px 10px 5px #888888;
	}

	.loginbox {
		height: 50px;
		position: absolute;
		right: 32px;
		border: 1px #41B883;
	}
</style>
