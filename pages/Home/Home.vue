<template>
	<view style="padding-bottom: 10rpx">
		<view style="height: 422rpx">
			<image src="../../static/images/home/home-logo.png" style="width: 100%; height: 422rpx"></image>
		</view>
		<view class="shop">
			<view class="shop-logo">
				<image src="../../static/images/home/shop-img.png" style="width: 144rpx; height: 144rpx"></image>
			</view>
			<view class="shop-info">
				<view class="shop-name">莱特妮斯（沙坪坝店）</view>
				<view class="shop-time">
					<image src="../../static/images/home/shop-time.png"></image>
					<view>周一至周日 10:30-20:30</view>
				</view>
				<view class="shop-address">
					<image src="../../static/images/home/home-addr.png"></image>
					<view>三峡广场龙湖光年金沙天街12-9号</view>
				</view>
			</view>
		</view>
		<view class="coupon" style="background-image: url('/static/images/home/home-bg.png')">优惠项目</view>
		<view class="project-list">
			<view class="project-item" v-for="product in productList" :key="product.id" @tap="handleProductDetail(product.id)">
				<view class="project-image">
					<image :src="product.preImg" mode="aspectFill"></image>
				</view>
				<view class="project-body">
					<view class="project-title bold">{{ product.title }}</view>
					<view class="project-time regular">{{ product.duration }}{{ product.timeUnit | timeUnitFormat }} {{ product.introduction }}</view>
					<view class="project-price">
						<text class="coupon-price bold">¥{{ product.price }}</text>
						<text class="del-price regular">¥{{ product.oriPrice }}</text>
					</view>
				</view>
			</view>
		</view>
		<view v-show="loading" class="loading">
			<image src="/static/loading.png"></image>
			<text>加载中...</text>
		</view>
		<view v-if="noMoreShow" class="no-more regular">暂无更多了～</view>
	</view>
</template>

<script>
import { getHomeData } from '@/api/home.js';
import { getCouponProductList } from '@/api/product.js';
export default {
	data() {
		return {
			params: {
				pageIndex: 1,
				pageSize: 10
			},
			total: 0,
			productList: [],
			noMoreShow: false,
			loading: false
		};
	},
	onLoad() {
		getHomeData().then((res) => {});
		this.getList();
	},
	onReachBottom() {
		if (this.params.pageIndex * this.params.pageSize < this.total) {
			this.params.pageIndex += 1;
			this.getList();
		} else {
			this.noMoreShow = true;
		}
	},
	methods: {
		getList() {
			this.loading = true;
			getCouponProductList(this.params).then((res) => {
				this.loading = false;
				this.total = res.data.totalCount;
				this.productList = this.productList.concat(res.data.rows);
			});
		},
		handleProductDetail(id) {
			uni.navigateTo({
				url: '/subpackages/Home/detail?id=' + id
			});
		}
	}
};
</script>

<style lang="scss" scoped>
.shop {
	width: 686rpx;
	height: 218rpx;
	border-radius: 20rpx;
	background-color: #1a1a1a;
	padding: 34rpx 54rpx 40rpx 50rpx;
	margin: auto;
	display: flex;
	.shop-logo {
		width: 144rpx;
		height: 144rpx;
	}
	.shop-info {
		margin-left: 42rpx;
		flex: 1;
		.shop-name {
			font-size: 30rpx;
			font-family: Source Han Serif CN-Heavy, Source Han Serif CN;
			font-weight: 800;
			color: #ffffff;
		}
		.shop-time {
			display: flex;
			align-items: center;
			margin: 20rpx 0 14rpx 0;
			image {
				width: 15.47rpx;
				height: 15.47rpx;
			}
			view {
				margin-left: 10rpx;
				font-size: 24rpx;
				font-family: PingFang SC-Regular, PingFang SC;
				font-weight: 400;
				color: #cecece;
			}
		}
		.shop-address {
			display: flex;
			align-items: center;
			image {
				width: 14.53rpx;
				height: 18.32rpx;
			}
			view {
				margin-left: 10rpx;
				font-size: 24rpx;
				font-family: PingFang SC-Regular, PingFang SC;
				font-weight: 400;
				color: #cecece;
				line-height: 0;
			}
		}
	}
}
.coupon {
	width: 190rpx;
	font-size: 30rpx;
	font-family: PingFang SC-Heavy, PingFang SC;
	font-weight: 800;
	color: #000000;
	margin: 44rpx auto 50rpx;
	text-align: center;
	background-size: 100% 50%;
	background-repeat: no-repeat;
	background-position-y: center;
}
.project-list {
	width: 682rpx;
	margin: auto;
	display: flex;
	align-items: center;
	flex-wrap: wrap;
	.project-item {
		width: 320rpx;
		border-radius: 24rpx 24rpx 0 0;
		margin-bottom: 34rpx;
		&:nth-child(2n + 1) {
			margin-right: 42rpx;
		}
		.project-image {
			width: 320rpx;
			height: 318rpx;
			border-radius: 24rpx 24rpx 0 0;
			image {
				width: 320rpx;
				height: 318rpx;
				border-radius: 24rpx 24rpx 0 0;
			}
		}
		.project-body {
			background-color: #f6f6f6;
			padding: 30rpx 0 48rpx 34rpx;
			border-radius: 0 0 24rpx 24rpx;
			.project-title {
				font-size: 28rpx;
				color: #333333;
			}
			.project-time {
				height: 34rpx;
				font-size: 24rpx;
				color: #888888;
				line-height: 28rpx;
				margin: 16rpx 0 12rpx 0;
				display: inline-block;
				text-overflow: ellipsis;
				white-space: nowrap;
				overflow: hidden;
				width: 100%;
			}
			.project-price {
				display: flex;
				align-items: center;
				.coupon-price {
					height: 40rpx;
					font-size: 28rpx;
					color: #b93333;
				}
				.del-price {
					margin-left: 14rpx;
					height: 28rpx;
					font-size: 20rpx;
					color: #c5c5c5;
				}
			}
		}
	}
}
</style>
