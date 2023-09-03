<template>
	<view>
		<view v-if="show" class="mask">
			<view class="mask-content">
				<image mode="aspectFill" class="clock" src="/static/images/order/clock.png"></image>
				<view class="mask-title bold">{{ text }}</view>
				<view class="mask-btn"><view class="btn reset-submit" @tap="close">确定</view></view>
			</view>
		</view>
	</view>
</template>

<script>
export default {
	name: 'againReservate',
	props: {
		orderId: {
			type: [String, Number],
			default: ''
		},
		text: {
			type: String,
			default: ''
		}
	},
	data() {
		return {
			show: false,
			type: 0
		};
	},
	methods: {
		open(type = 0) {
			this.type = type;
			this.show = true;
		},
		close() {
			if (this.type === 1) {
				this.$emit('link');
			}
			this.show = false;
		}
	}
};
</script>

<style lang="scss" scoped>
.mask {
	background-color: rgba(0, 0, 0, 0.6);
	position: fixed;
	z-index: 999;
	top: 0;
	left: 0;
	bottom: 0;
	right: 0;
	display: flex;
	// align-items: center;
	justify-content: center;
	padding-top: 50%;
	.mask-content {
		width: 474rpx;
		height: 454rpx;
		background: #ffffff;
		border-radius: 32rpx 32rpx 32rpx 32rpx;
		opacity: 1;
		border: 2rpx solid #707070;
		padding: 50rpx 42rpx;
		.clock {
			width: 223.91rpx;
			height: 112.67rpx;
			margin: 32rpx 82rpx 36rpx;
		}
		.mask-title {
			font-size: 28rpx;
			color: #343434;
			text-align: center;
		}
		.mask-btn {
			margin-top: 46rpx;
			.btn {
				width: 388rpx;
				height: 68rpx;
				border-radius: 82rpx 82rpx 82rpx 82rpx;
				opacity: 1;
				font-family: PingFang SC-Bold, PingFang SC;
				font-weight: bold;
				display: flex;
				align-items: center;
				justify-content: center;
			}
			.reset-submit {
				background: #333333;
				font-size: 24rpx;
				color: #ffffff;
				margin-bottom: 18rpx;
			}
			.back {
				font-size: 24rpx;
				color: #333333;
				border: 2rpx solid #333333;
			}
		}
	}
}
</style>
