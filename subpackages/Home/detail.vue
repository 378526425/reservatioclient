<template>
	<view class="page">
		<view class="project-img">
			<image :src="productDetail.preImg" mode="aspectFill"></image>
		</view>
		<view class="project-info">
			<view class="project-name">{{ productDetail.title || '' }}</view>
			<view class="project-sku regular">
				<text class="project-time">{{ productDetail.duration || '' }}{{ productDetail.timeUnit | timeUnitFormat }}</text>
				<text class="project-type">{{ productDetail.introduction }}</text>
			</view>
			<view class="project-price">
				<text class="coupon-price bold">¥{{ productDetail.price || 0 }}</text>
				<text class="del-price regular">¥{{ productDetail.oriPrice || 0 }}</text>
			</view>
		</view>
		<view class="driver"></view>
		<view class="project-detail">
			<view class="text">项目详情</view>
			<view class="project-detail-img">
				<block v-if="productDetail.info && productDetail.info.length > 0">
					<image v-for="(imgSrc, index) in productDetail.info" :src="imgSrc" :key="index" mode="widthFix" style="width: 750rpx; margin-bottom: -15px"></image>
				</block>
			</view>
		</view>
		<view class="now-reservate" @tap="handleNow">立即预约</view>
	</view>
</template>

<script>
import { getProductDetail } from '@/api/product.js';
export default {
	data() {
		return {
			productId: '',
			productDetail: {}
		};
	},
	onLoad(option) {
		this.productId = option.id;
		this.handleGetDetail();
	},
	methods: {
		async handleGetDetail() {
			const resp = await getProductDetail(this.productId);
			this.productDetail = resp.data;
		},
		// 立即预约
		handleNow() {
			uni.navigateTo({
				url: '/subpackages/Home/reservate?id=' + this.productDetail.id + '&name=' + this.productDetail.title
			});
		}
	}
};
</script>

<style lang="scss" scoped>
.page {
	padding-bottom: 110rpx;
	position: relative;
}
.project-img {
	image {
		height: 452rpx;
		width: 750rpx;
	}
}
.project-info {
	background-color: #ffffff;
	padding: 50rpx 32rpx 34rpx;
	.project-name {
		font-size: 36rpx;
		font-family: PingFang SC-Heavy, PingFang SC;
		font-weight: 800;
		color: #333333;
	}
	.project-sku {
		font-size: 32rpx;
		color: #888888;
		line-height: 42rpx;
		margin: 20rpx 0 30rpx;
		display: flex;
		align-items: center;
		.project-time {
			margin-right: 36rpx;
			width: 120rpx;
		}
		.project-type {
			text-overflow: ellipsis;
			white-space: nowrap;
			overflow: hidden;
			display: inline-block;
			width: 100%;
		}
	}
	.project-price {
		.coupon-price {
			color: #b93333;
			font-size: 42rpx;
			margin-right: 26rpx;
		}
		.del-price {
			font-size: 30rpx;
			color: #c5c5c5;
		}
	}
}
.driver {
	height: 30rpx;
	background-color: #f8f8f8;
	width: 100%;
}
.project-detail {
	background-color: #ffffff;
	.text {
		margin: 40rpx 0 54rpx 36rpx;
		font-size: 36rpx;
		font-family: PingFang SC-Heavy, PingFang SC;
		font-weight: 800;
		color: #333333;
	}
}
.now-reservate {
	position: fixed;
	bottom: 30rpx;
	left: calc((100vw - 686rpx) / 2);
	width: 686rpx;
	height: 80rpx;
	background: #2e2e2e;
	border-radius: 52rpx 52rpx 52rpx 52rpx;
	opacity: 1;
	font-size: 30rpx;
	font-family: PingFang SC-Heavy, PingFang SC;
	font-weight: 800;
	color: #ffffff;
	display: flex;
	align-items: center;
	justify-content: center;
	margin: auto;
}
</style>
