// #ifdef H5
const baseURl = `${location.protocol}//${location.host}/`
// #endif

// #ifdef MP-WEIXIN
export const baseURl = 'https://www.wxmblog.com/reservationdevapi'
//export const baseURl = 'http://localhost:8083'
// #endif

export default function request(config, isLoading = false) {
	isLoading && uni.showLoading()
	return new Promise((resolve, reject) => {
		uni.request({
			url: baseURl + config['url'],
			method: config['method'],
			header: {
				"content-type": "application/json",
				"Authorization": uni.getStorageSync('token')
			},
			data: config['data'],
			success: (res) => {
				uni.hideLoading()
				if (res.statusCode === 200) {
					resolve(res.data)
				} else {
					reject(res)
				}
			},
			fail(res) {
				isLoading && uni.hideLoading()
				reject(res)
			}
		})
	})
}