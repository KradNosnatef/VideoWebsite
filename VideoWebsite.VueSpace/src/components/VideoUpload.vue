<template>
	<div><p>上传视频（目前仅限mp4）</p>
		<div class="con">
			<div class="tip">选择文件：</div>
			<input class="file" type="file" title="请选择文件" value="请选择文件">
			<button class="submit" @click="submit">提交</button>
		</div>
	</div>
</template>

<script>
	import axios from 'axios'

	export default {
		data() {
			return {}
		},
		methods: {
			submit: function() {
				var formData = new window.FormData();
				formData.append('file', document.querySelector('input[type=file]').files[0])
				//'userfile'是formData这个对象的键名
				axios({
					url: '/Xapi/Video/upload',
					data: formData,
					method: 'post',
					headers: {
						'Content-Type': 'multipart/form-data',
					}
				}).then((res) => {
					console.log(res.data);
					alert(res.data);
					formData = new window.FormData();
					this.$emit("setRouterPointer", "/home")
				}) // 发送请求
			},
		}
	}
</script>

<style>
</style>
