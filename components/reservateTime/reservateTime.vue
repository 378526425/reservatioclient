<template>
	<view>
		<view v-if="show" class="mask" @tap="handleClick">
			<view class="popup" @tap.stop="handlePopupClick">
				<view class="title">选择时间</view>
				<view class="time-tree">
					<view class="left">
						<view :class="['item bold', currentIndex === index ? 'item-active' : '']" v-for="(day, index) in reservateTimeList" :key="index" @tap="handleSelectDay(day, index)">
							{{ day.week }} {{ day.monthDay }}
						</view>
					</view>
					<view class="right">
						<view class="times">
							<view
								:class="['time-item', currentTimeIndex === tIndex ? 'time-item-active' : '']"
								v-for="(time, tIndex) in currentDay.children"
								:key="tIndex"
								@tap="handleSelectTime(time, tIndex)"
							>
								<view class="bold" style="font-size: 32rpx; margin-bottom: 6rpx">{{ time | timeFilter }}</view>
								<view class="regular" style="font-size: 24rpx">可预约</view>
							</view>
						</view>
					</view>
				</view>
				<view class="confirm-btn" @tap="handleConfirm">确定</view>
			</view>
		</view>
	</view>
</template>

<script>
import { timeFormatter, generateTime } from '@/utils/index.js';
export default {
	name: 'reservateTime',
	filters: {
		timeFilter(val) {
			return val.split(':').slice(0, 2).join(':');
		}
	},
	data() {
		return {
			currentDay: {},
			currentIndex: 0,
			currentTime: '',
			currentTimeIndex: 0,
			reservateTimeList: [],
			show: false
		};
	},
	mounted() {
		const times = [];
		for (let i = 0; i < 7; i++) {
			const date = new Date();
			date.setHours(0, 0, 0, 0);
			times.push(date.getTime() + i * 86400000);
		}
		this.reservateTimeList = times.map((item) => timeFormatter(item));
		this.reservateTimeList.forEach((item) => {
			item.children = generateTime();
		});
		this.currentDay = this.reservateTimeList[0];
		this.currentTime = this.currentDay.children[0];
	},
	methods: {
		open() {
			this.show = true;
		},
		handleClick() {
			this.show = false;
		},
		handlePopupClick() {},
		handleSelectDay(day, index) {
			this.currentIndex = index;
			this.currentDay = day;
			this.currentTime = day.children[0];
			this.currentTimeIndex = 0;
		},
		handleSelectTime(time, index) {
			this.currentTime = time;
			this.currentTimeIndex = index;
		},
		handleConfirm() {
			this.show = false;
			this.$emit('confirm', this.currentDay.date + ' ' + this.currentTime);
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
}
.popup {
	width: 100%;
	height: 920rpx;
	background-color: #fff;
	border-radius: 48rpx 48rpx 0 0;
	animation-name: move;
	animation-duration: 0.5s;
	position: absolute;
	bottom: 0;
	padding: 44rpx 42rpx 42rpx 0;
	.title {
		font-size: 36rpx;
		font-family: PingFang SC-Heavy, PingFang SC;
		font-weight: 800;
		color: #333333;
		padding-left: 58rpx;
		padding-bottom: 10rpx;
	}
	.time-tree {
		display: flex;
		height: 698rpx;
		.left {
			width: 254rpx;
			.item {
				padding: 24rpx 34rpx 28rpx 40rpx;
				background-color: #f6f6f6;
			}
			.item-active {
				background-color: #fff !important;
			}
		}
		.right {
			width: calc(100% - 254rpx);
			padding-left: 28rpx;
			overflow-y: auto;
			.times {
				display: flex;
				flex-wrap: wrap;
				.time-item {
					width: 200rpx;
					height: 134rpx;
					border-radius: 30rpx;
					background-color: #f1f1f1;
					margin-right: 26rpx;
					margin-bottom: 16rpx;
					text-align: center;
					color: #000;
					display: flex;
					flex-direction: column;
					justify-content: center;
					&:nth-child(2n) {
						margin-right: 0;
					}
				}
				.time-item-active {
					color: #fff;
					background-color: #000;
				}
			}
		}
	}
	.confirm-btn {
		width: 676rpx;
		height: 80rpx;
		line-height: 80rpx;
		text-align: center;
		font-size: 30rpx;
		font-family: PingFang SC-Heavy, PingFang SC;
		font-weight: 800;
		color: #ffffff;
		background-color: #2e2e2e;
		border-radius: 52rpx;
		margin: 22rpx 0 0 32rpx;
	}
}
@keyframes move {
	from {
		position: absolute;
		bottom: -920rpx;
	}
	to {
		position: absolute;
		bottom: 0;
	}
}
</style>
