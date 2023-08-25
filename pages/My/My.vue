<template>
	<view class="page">
		<view class="top" :style="{ backgroundImage: `url(${myBg})`, paddingTop: safeTopHeight + 'px' }">
			<!-- #ifdef MP-WEIXIN -->
			<view class="weixin-header regular">个人中心</view>
			<!-- #endif -->
			<view class="user-info">
				<block v-if="userInfo">
					<image
						:src="userInfo.headPortrait ? userInfo.headPortrait : '/static/images/my/avatar.png'"
						style="width: 140rpx; height: 140rpx; border-radius: 50%"
						@tap="handleUpdateUserInfo"
					></image>
					<view class="username bold" @tap="handleUpdateUserInfo">{{ userInfo.nickName }}</view>
				</block>
				<block v-else>
					<image src="/static/images/my/avatar.png" style="width: 140rpx; height: 140rpx"></image>
					<view class="username bold" @tap="handleLogin">请登录</view>
				</block>
			</view>
			<view class="function-area">
				<view class="area" @tap="handleShowOrder('')">
					<view class="num">{{ userInfo.allOrder || 0 }}</view>
					<view class="title regular">预约记录</view>
				</view>
				<view class="area" @tap="handleShowOrder('WAIT')">
					<view class="num">{{ userInfo.waitOrder || 0 }}</view>
					<view class="title regular">待服务</view>
				</view>
				<view class="area" @tap="handleShowOrder('SERVEB')">
					<view class="num">{{ userInfo.completeOrder || 0 }}</view>
					<view class="title regular">已服务</view>
				</view>
			</view>
		</view>
		<view class="bottom">
			<view class="cell" @tap="handleGetSupport">
				<view class="cell-left">
					<image class="cell-left-icon" src="/static/images/my/about.png" style="width: 34rpx; height: 34rpx"></image>
					<view class="cell-left-text">技术支持</view>
				</view>
				<image class="cell-right-icon" src="/static/images/my/arrow-right.png"></image>
			</view>
			<view class="cell" v-if="isShowLogout" @tap="handleLogout">
				<view class="cell-left">
					<image class="cell-left-icon" src="/static/images/my/logout.png" style="width: 34rpx; height: 36rpx"></image>
					<view class="cell-left-text regular">注销登录</view>
				</view>
				<image class="cell-right-icon" src="/static/images/my/arrow-right.png"></image>
			</view>
		</view>
		<againReservate ref="againReservate" :text="text"></againReservate>
	</view>
</template>

<script>
import { getUserInfo } from '@/api/user.js';
import { myBg } from '@/utils/imgBase64.js';
import { isLogin, goLogin } from '@/utils/index.js';
export default {
	data() {
		return {
			userInfo: null,
			myBg: '',
			safeTopHeight: null,
			text: ''
		};
	},
	computed: {
		isShowLogout() {
			return !isLogin();
		}
	},
	onLoad() {
		this.myBg = myBg;
		this.handleGetUserInfo();
		const res = uni.getWindowInfo();
		this.safeTopHeight = res.safeArea.top;
	},
	onShow() {
		this.handleGetUserInfo();
	},
	methods: {
		handleGetUserInfo() {
			getUserInfo().then((res) => {
				this.userInfo = res.data;
				console.log(this.userInfo, '-------------');
			});
		},
		handleShowOrder(type) {
			if (isLogin()) {
				this.text = '您还未登录, 请先登录';
				this.$refs.againReservate.open();
				return;
			}
			uni.navigateTo({
				url: '/subpackages/order/order?orderType=' + type
			});
		},
		handleGetSupport() {
			uni.navigateTo({
				url: '/subpackages/My/support'
			});
		},
		// 修改资料
		handleUpdateUserInfo() {
			uni.navigateTo({
				url: '/subpackages/UserInfo/UserInfo'
			});
		},
		// 登录
		async handleLogin() {
			await goLogin();
			uni.reLaunch({
				url: '/pages/Home/Home'
			});
		},
		//  退出登录
		handleLogout() {
			uni.removeStorageSync('token');
			uni.reLaunch({
				url: '/pages/Home/Home'
			});
		}
	}
};
</script>

<style lang="scss" scoped>
.page {
	position: relative;
	.top {
		height: 660rpx;
		background-size: 100% 100%;
		background-repeat: no-repeat;
		.weixin-header {
			color: #ffffff;
			font-size: 32rpx;
			background-color: transparent;
			height: 44px;
			width: 100%;
			display: flex;
			align-items: center;
			justify-content: center;
		}
		.user-info {
			margin-top: 68rpx;
			margin-left: 74rpx;
			display: flex;
			align-items: center;
			image {
				margin-right: 38rpx;
			}
			.username {
				color: #ffffff;
				font-size: 36rpx;
			}
		}
		.function-area {
			display: flex;
			align-items: center;
			justify-content: space-around;
			margin-top: 48rpx;
			.area {
				display: flex;
				flex-direction: column;
				align-items: center;
				.num {
					font-size: 66rpx;
					font-family: bahnschrift;
					// font-family: Bahnschrift-SemiLight SemiCondensed, Bahnschrift;
					font-weight: normal;
					color: #ffffff;
				}
				.title {
					margin-top: 10rpx;
					font-size: 24rpx;
					color: #b1b1b1;
				}
			}
		}
	}
	.bottom {
		position: absolute;
		top: 600rpx;
		background-color: #ffffff;
		border-radius: 42rpx 42rpx 0 0;
		width: 678rpx;
		margin-left: 26rpx;
		padding-top: 14rpx;
		.cell {
			display: flex;
			align-items: center;
			justify-content: space-between;
			margin-top: 62rpx;
			padding: 0 30rpx 0 68rpx;
			.cell-left {
				display: flex;
				align-items: center;
				.cell-left-icon {
					margin-right: 30rpx;
				}
				.cell-left-text {
					font-size: 30rpx;
					color: #000000;
				}
			}
			.cell-right-icon {
				width: 11rpx;
				height: 19rpx;
			}
		}
	}
}
</style>
