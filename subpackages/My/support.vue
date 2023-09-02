<template>
	<view class="page">
		<image class="wechart-qr" mode="aspectFill" show-menu-by-longpress src="/static/images/my/wechart.png"></image>
		<view class="wechart-no bold">微信号 MMRWXM</view>
		<view class="tips bold">如需软件定制开发请扫码添加微信</view>
	</view>
</template>

<script>
import { weChat } from '@/utils/imgBase64.js';
export default {
	data() {
		return {
			imgSrc: ''
		};
	},
	onLoad() {
		this.imgSrc = weChat;
	}
};
</script>

<style lang="scss" scoped>
.page {
	/* #ifdef MP-WEIXIN */
	min-height: 100vh;
	/* #endif */
	/* #ifdef H5 */
	min-height: calc(100vh - 44px - env(safe-area-inset-top));
	/* #endif */
	padding: 236rpx 160rpx 178rpx 158rpx;
	text-align: center;
	position: relative;
}
.wechart-qr {
	width: 432rpx;
	height: 424rpx;
	margin-bottom: 40rpx;
}
.wechart-no {
	font-size: 36rpx;
	color: #000000;
}
.tips {
	width: 288rpx;
	font-size: 36rpx;
	color: #000000;
	line-height: 60rpx;
	position: absolute;
	bottom: 178rpx;
	left: 230rpx;
}
</style>
