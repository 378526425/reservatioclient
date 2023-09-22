import {
	wxlogin,
	wxregister
} from '@/api/user.js';

function handleMonthDay(monthDay) {
	return monthDay < 10 ? '0' + monthDay : monthDay
}

const weekHan = ['日', '一', '二', '三', '四', '五', '六']

export function timeFormatter(timestamp) {
	if (!timestamp) return "";
	const currentDate = new Date();
	const date = new Date(timestamp);
	return {
		week: date.getDay() === currentDate.getDay() ? '今天' : (currentDate.getDay() + 1 === date.getDay() || 0 ? '明天' :
			'周' + weekHan[date.getDay()]),
		date: date.getFullYear() + '-' + handleMonthDay(date.getMonth() + 1) + '-' + handleMonthDay(date.getDate()),
		monthDay: handleMonthDay(date.getMonth() + 1) + '-' + handleMonthDay(date.getDate()),
		children: []
	}
}

export function generateTime() {
	const arr = new Array();
	for (let i = 9; i < 21; i++) {
		arr.push(i + ':00:00')
	}
	return arr
}

export function isLogin() {
	return !uni.getStorageSync('token')
}

export function goLogin() {

	return new Promise((resolve) => {

		// #ifdef MP-WEIXIN
		wx.login({
			success: (res) => {
				const {
					code
				} = res;
				wxlogin({
					code
				}).then((res) => {
					uni.setStorageSync('token', res.data.token);
					resolve()
				});
			}
		});
		// #endif
		// #ifdef H5

		wxlogin({
			code: "ip"
		}).then((res) => {

			if (res.code == 11002) {
				//用户不存在
				wxregister({
					code: "ip",
					nickName: 'H5用户'
				}).then(res => {
					wxlogin({
						code: "ip"
					}).then((res) => {
						uni.setStorageSync('token', res.data.token);
						resolve()
					});
				});
			} else {
				uni.setStorageSync('token', res.data.token);
				resolve()
			}

		});
		// #endif
	})
}