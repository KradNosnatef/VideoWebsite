<template>
	<div class="maindiv">
		<p>待审核视频清单</p>
		<ItemCard v-for="post in itemCards" v-bind:inputTitle="post.filename" v-bind:inputUsername="post.username"
			v-bind:inputUid="post.uid" v-bind:inputVid="post.vid" v-bind:inputEnabled="0"
			v-on:onItemClicked="getItemClicked">
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
		methods:{
			getItemClicked(val){
				//console.log("yes you get clicked!");
				console.log(val);
				this.$emit("setRouterPointerWithParam",{
					dest:"/AuditTools",
					value:val
				});
			}
		},
		mounted: function() {
			jsonp(
				"/Xapi/admin/listAllUnabledVideo", {
					param: "callback",
					timeout: 10000,
					prefix: "callback",
					name: "callback_IOlistAllUnabledVideo"
				},
				(err, data) => {
					if (err) {
						alert("获取全部视频数据时出错");
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
