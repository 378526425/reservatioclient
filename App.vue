<script>
import { wxlogin, wxregister } from '@/api/user.js';
export default {
	onLaunch: function() {
		console.log('App Launch');
		// #ifdef MP-WEIXIN
		if (!uni.getStorageSync('is_register')) {
			wx.login({
				success: res => {
					wxregister({ code: res.code, nickName: '微信用户' }).then(res => {
						uni.setStorageSync('is_register', 1);
					});
				}
			});
		}

		// #endif
	},
	onShow: function() {
		console.log('App Show');
	},
	onHide: function() {
		console.log('App Hide');
	}
};
</script>

<style>
view {
	/* -webkit-background-clip: text;
		-webkit-text-fill-color: transparent; */
	box-sizing: border-box;
}
.bold {
	font-family: PingFang SC-Bold, PingFang SC;
	font-weight: bold;
}
.regular {
	font-family: PingFang SC-Regular, PingFang SC;
	font-weight: 400;
}
.page {
	position: relative;
}
.loading {
	text-align: center;
	font-size: 24rpx;
	font-family: PingFang SC-Regular, PingFang SC;
	font-weight: 400;
	display: flex;
	align-items: center;
	justify-content: center;
	color: #dcdcdc;
}
.loading > image {
	width: 32rpx;
	height: 32rpx;
	margin-right: 10rpx;
}
.no-more {
	height: 34rpx;
	font-size: 24rpx;
	color: #c7c7c7;
	text-align: center;
	margin: 4rpx 0 36rpx 0;
}
.del-price {
	text-decoration: line-through;
}
</style>
