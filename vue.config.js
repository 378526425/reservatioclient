module.exports = {
	devServer: {
		proxy: {
			'': {
				target: 'http://101.35.152.208:8083/',
				changeOrigin: true
			}
		}
	}
}