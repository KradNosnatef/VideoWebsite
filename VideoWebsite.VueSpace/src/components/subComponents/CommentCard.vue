<template>
	<div>
		<p >&ensp;&ensp;&ensp;&ensp;&ensp;{{inputCommentCard.username}}说：</p>
		<a >&ensp;{{inputCommentCard.value}}</a>
		<button  v-on:click="deleteComment" v-if="inputCommentCard.accessability">删除</button>
		<br />
		<br />
	</div>
</template>

<script>
	import jsonp from 'jsonp'
	export default{
		props:[
			"inputCommentCard"
		],
		methods:{
			deleteComment(){
				var url=this.$URLGenerator("/Xapi/deleteComment",{
					cid:this.inputCommentCard.cid
				});
				jsonp(url,
					{
						param: "callback",
						timeout: 10000,
						prefix: "callback",
						name: "callback_IOdeleteComment"
					},
					(err, data) => {
						if (err) {
							alert("删除评论时出错");
						} else {
							console.log(data);
							alert("删除成功！");
							this.$emit("iDeleteMyself");
						}
					}
				)
			}
		}
	}
</script>

<style>
</style>
