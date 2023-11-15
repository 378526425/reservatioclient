module.exports = {
    devServer: {
		    proxy: {
		      '/reservationdevapi': {
		        target: 'https://www.wxmblog.com/reservationdevapi',
				changeOrigin: true,
		        pathRewrite: {
		          '^/reservationdevapi': ''
		        }
		      }
		    },
	}
}