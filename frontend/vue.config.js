module.exports = {
	outputDir: 'target/dist',
  	assetsDir: 'static',
	publicPath: '/natlib/',

	// see https://cli.vuejs.org/config/#devserver-proxy
	devServer: {
		proxy: {
			'/api': {
				target: 'http://localhost:8888',
				ws: true,
				changeOrigin: true
			}
		}
	},
}