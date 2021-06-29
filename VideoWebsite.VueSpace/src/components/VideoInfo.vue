<template>
	<div>
		<p>视频标题：{{inputHorizontalProp.title}}</p>
		<p>发布人：{{inputHorizontalProp.username}}</p>
		<a :href="'/Xapi/media?uid='+inputHorizontalProp.uid+'&vid='+inputHorizontalProp.vid">视频下载链接</a>
		<br />
		<br />
		<br />
		<br />
		<br />
		<p>评论区</p>
		<CommentCard v-for="post in CommentCards" v-bind:inputCommentCard="post" v-on:iDeleteMyself="refresh">
		</CommentCard>
		<br />
		<button v-on:click="pushComment">发表评论</button>
		<input v-model="commentValue" />
	</div>
</template>

<script>
	import CommentCard from './subComponents/CommentCard.vue'
	import jsonp from 'jsonp'
	export default {
		data() {
			return {
				CommentCards: null,
				commentValue: ""
			}
		},
		props: [
			"inputHorizontalProp"
		],
		methods: {
			somebodyDeleteItself(){
				this.refresh();
			},
			refresh() {

				var url = this.$URLGenerator("/Xapi/listCommentByVID", {
					vid: this.inputHorizontalProp.vid
				});
				jsonp(url, {
					param: "callback",
					timeout: 3000,
					prefix: "callback",
					name: "callback_IOlistCommentByVID"
				}, (err, data) => {
					if (err) {
					} else {
						console.log(data);
						this.CommentCards = data;
					}
				})
			},

			pushComment() {
				var stringifiedValue = encodeURIComponent(JSON.stringify(this.commentValue));
				this.commentValue = "";
				var url = "/Xapi/pushComment?vid=" + this.inputHorizontalProp.vid + "&value=" + stringifiedValue;
				console.log(url);
				jsonp(url, {

					param: "callback",
					timeout: 3000,
					prefix: "callback",
					name: "callback_IOpushComment"
				}, (err, data) => {
					if (err) {
						alert("发布评论出现未知错误");
					} else {
						console.log(data);
						alert("评论成功！")
						this.refresh();
					}
				})
			}
		},
		components: {
			CommentCard
		},
		mounted: function() {
			this.refresh();
		}
	}
</script>

<style>
</style>
