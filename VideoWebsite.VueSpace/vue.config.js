module.exports = {
    devServer: {
        open: false,
        port: 8080,
        https: false,
        //以上的ip和端口是我们本机的;下面为需要跨域的
		proxy:{
			'/Xapi':{
				target:'http://192.168.3.14:8086',
				ws:true,
				changeOrigin:true
			}
		}
    },
	runtimeCompiler: true
}