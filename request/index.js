// #ifdef H5
const baseURl = `${location.protocol}//${location.host}/`
// #endif

// #ifdef MP-WEIXIN
// const baseURl = 'http://101.35.152.208:8083/'
export const baseURl = 'https://www.wxmblog.com/reservationdevapi'
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
					uni.showToast({
						icon: 'none',
						title: '接口数据异常,稍后再试'
					})
				}
			},
			fail(res) {
				isLoading && uni.hideLoading()
				reject(res)
				uni.showToast({
					icon: 'none',
					title: '接口数据异常,稍后再试'
				})
			}
		})
	})
}