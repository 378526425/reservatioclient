<template>
	<view class="page">
		<view class="order-img">
			<image class="order-img" :src="orderInfo.preImg"></image>
		</view>
		<view class="product-info">
			<view class="product-title">{{ orderInfo.title }}</view>
			<view class="product-sku regular">{{ orderInfo.duration }}{{ orderInfo.timeUnit | timeUnitFormat }} {{ orderInfo.introduction }}</view>
			<view class="price">
				<view class="coupon-price bold">¥{{ orderInfo.price }}</view>
				<view class="del-price regular">¥{{ orderInfo.oriPrice }}</view>
			</view>
		</view>
		<view class="driver"></view>
		<view class="order-info">
			<view class="title">预约信息</view>
			<view class="order-item">
				<view class="label regular">预约门店</view>
				<view class="content bold">莱特妮斯沙坪坝店</view>
			</view>
			<view class="order-item">
				<view class="label regular">预约时间</view>
				<view class="content bold">{{ orderInfo.startTime }}</view>
			</view>
			<view class="order-item">
				<view class="label regular">创建时间</view>
				<view class="content bold">{{ orderInfo.createTime }}</view>
			</view>
			<view class="order-item">
				<view class="label regular">服务项目</view>
				<view class="content bold">{{ orderInfo.title }}</view>
			</view>
			<view class="order-item">
				<view class="label regular">服务人员</view>
				<view class="content bold">{{ orderInfo.personName }}</view>
			</view>
			<view class="order-item">
				<view class="label regular">预约人</view>
				<view class="content bold">{{ orderInfo.contacts }}</view>
			</view>
			<view class="order-item">
				<view class="label regular">手机号</view>
				<view class="content bold">{{ orderInfo.phone }}</view>
			</view>
			<view class="order-item">
				<view class="label regular">备注</view>
				<view class="content bold">{{ orderInfo.remark }}</view>
			</view>
			<view v-if="orderInfo.status === 'WAIT'" class="btn" @tap="handleCancelOrder">取消预约</view>
		</view>
		<cancelReservate ref="cancelReservate" :order-id="orderId" @request="getOrderDetail"></cancelReservate>
	</view>
</template>

<script>
import { orderDetail } from '@/api/order.js';
export default {
	data() {
		return {
			orderId: '',
			orderInfo: {}
		};
	},
	onLoad(options) {
		this.orderId = options.id;
		this.getOrderDetail();
	},
	methods: {
		getOrderDetail() {
			orderDetail(this.orderId).then((res) => {
				this.orderInfo = res.data;
			});
		},
		handleCancelOrder() {
			this.$refs.cancelReservate.open();
		}
	}
};
</script>

<style lang="scss" scoped>
.page {
	.order-img {
		width: 750rpx;
		height: 452rpx;
	}
	.product-info {
		padding: 50rpx 0 36rpx 32rpx;
		.product-title {
			font-size: 36rpx;
			font-family: PingFang SC-Heavy, PingFang SC;
			font-weight: 800;
			color: #333333;
		}
		.product-sku {
			font-size: 32rpx;
			color: #888888;
			line-height: 42rpx;
			margin: 20rpx 0 30rpx 0;
			display: inline-block;
			text-overflow: ellipsis;
			white-space: nowrap;
			overflow: hidden;
			width: 100%;
		}
		.price {
			display: flex;
			align-items: center;
			.coupon-price {
				font-size: 42rpx;
				color: #b93333;
				margin-right: 26rpx;
			}
			.del-price {
				font-size: 30rpx;
				color: #c5c5c5;
			}
		}
	}
	.driver {
		width: 750rpx;
		height: 30rpx;
		background: #f8f8f8;
		border-radius: 0rpx 0rpx 0rpx 0rpx;
		opacity: 1;
	}
	.order-info {
		padding: 40rpx 32rpx 0 32rpx;
		padding-bottom: calc(16rpx + env(safe-area-inset-bottom));
		.title {
			font-size: 36rpx;
			font-family: PingFang SC-Heavy, PingFang SC;
			font-weight: 800;
			color: #333333;
		}
		.order-item {
			margin-top: 52rpx;
			display: flex;
			align-items: center;
			.label {
				width: 128rpx;
				margin-right: 46rpx;
				font-size: 32rpx;
				color: #000000;
			}
			.content {
				font-size: 32rpx;
				color: #000000;
			}
		}
		.btn {
			height: 80rpx;
			background: #2e2e2e;
			border-radius: 52rpx 52rpx 52rpx 52rpx;
			opacity: 1;
			font-size: 30rpx;
			font-family: PingFang SC-Heavy, PingFang SC;
			font-weight: 800;
			color: #ffffff;
			line-height: 80rpx;
			text-align: center;
			margin-top: 40rpx;
		}
	}
}
</style>
