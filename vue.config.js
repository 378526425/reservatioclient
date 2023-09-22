module.exports = {
	devServer: {
		proxy: {
			'': {
				target: 'https://www.wxmblog.com/reservationdevapi/',
				changeOrigin: true
			}
		}
	}
}