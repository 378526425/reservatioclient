module.exports = {
	devServer: {
		proxy: {
			'': {
				target: 'http://localhost:8083/',
				changeOrigin: true
			}
		}
	}
}