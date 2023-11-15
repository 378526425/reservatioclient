<template>
	<view class="page">
		<view class="tabs">
			<view :class="['tab-pane', active === tab.value ? 'tab-active bold' : 'regular']" v-for="tab in tabsList" :key="tab.value" @tap="handleTabClick(tab)">
				{{ tab.name }}
				<view v-if="active === tab.value" class="active-strip"></view>
			</view>
		</view>
		<view class="order-list" v-if="orderList.length > 0">
			<view class="order-item" v-for="order in orderList" :key="order.id">
				<view class="order-info">
					<image class="order-img" :src="order.preImg" @tap="handleOrderDetail(order.id)" mode="aspectFill"></image>
					<view class="order-detail">
						<view class="project-name bold" @tap="handleOrderDetail(order.id)">{{ order.title }}</view>
						<view class="order-price regular">价格：￥{{ order.price }}</view>
						<view class="create-time regular">创建时间：{{ order.createTime }}</view>
						<view class="reservate-time regular">预约时间：{{ order.startTime }}</view>
					</view>
				</view>
				<text class="order-status regular">{{ order.status | statusFormat }}</text>
				<view class="btns">
					<view class="btn btn-detail regular" @tap="handleOrderDetail(order.id)">预约详情</view>
					<view v-if="order.status === 'WAIT'" class="btn cancel-btn regular" @tap="handleCancelOrder(order.id)">取消预约</view>
					<view v-if="order.status === 'SERVEB'" class="btn cancel-btn regular" @tap="handleAgainOrder(order.id)">再次预约</view>
				</view>
			</view>
		</view>
		<view v-if="orderList.length === 0 && !loading" class="no-result">
			<image mode="aspectFill" class="no-order" src="/static/images/order/no-order.png"></image>
			<view class="no-result-text regular">暂无内容</view>
		</view>
		<uni-load-more v-if="orderList.length > 0" :status="returnLoadingText"></uni-load-more>
		<againReservate ref="againReservate" :order-id="orderId"></againReservate>
		<cancelReservate ref="cancelReservate" :order-id="orderId" @request="getList"></cancelReservate>
	</view>
</template>

<script>
import { getSystemConfig } from '@/api/home.js';
import { orderList } from '@/api/order.js';
export default {
	data() {
		return {
			loading: false,
			active: '',
			tabsList: [
				{ name: '全部', value: '', list: null, pageIndex: 1, total: 0 },
				{ name: '待服务', value: 'WAIT', list: null, pageIndex: 1, total: 0 },
				{ name: '已服务', value: 'SERVEB', list: null, pageIndex: 1, total: 0 },
				{ name: '过期/取消', value: 'INVALID', list: null, pageIndex: 1, total: 0 }
			],
			params: {
				pageIndex: 1,
				pageSize: 10,
				orderType: ''
			},
			orderList: [],
			total: 0,
			orderId: '',
			noMoreShow: false
		};
	},
	onLoad(option) {
		this.active = option.orderType || '';
		this.params.orderType = option.orderType || '';
		this.getList();
	},
	onReachBottom() {
		if (this.params.pageIndex * this.params.pageSize < this.total) {
			this.params.pageIndex += 1;
			this.tabsList.find((item) => item.value === this.active).pageIndex += 1;
			this.getList();
		} else {
			this.noMoreShow = true;
		}
	},
	onPullDownRefresh() {
		this.orderList = [];
		this.params.pageIndex = 1;
		this.tabsList.find((item) => item.value === this.active).pageIndex = 1;
		this.getList();
		uni.stopPullDownRefresh();
	},
	computed: {
		returnLoadingText() {
			if (this.loading) {
				return 'loading';
			} else if (!this.loading && this.noMoreShow) {
				return 'no-more';
			} else {
				return 'more';
			}
		}
	},
	methods: {
		getList() {
			this.loading = true;
			orderList(this.params).then((res) => {
				this.loading = false;
				this.total = res.data.totalCount;
				this.orderList.push(...res.data.rows);
				this.tabsList.find((item) => item.value === this.active).list = this.orderList;
				this.tabsList.find((item) => item.value === this.active).total = this.total;
				this.noMoreShow = this.params.pageIndex * this.params.pageSize > this.total;
			});
		},
		handleTabClick(tab) {
			this.active = tab.value;
			this.params.orderType = tab.value;
			const currentTab = this.tabsList.find((item) => item.value === this.active);
			if (currentTab.list) {
				this.params.pageIndex = currentTab.pageIndex;
				this.orderList = currentTab.list;
				this.total = currentTab.total;
			} else {
				this.params.pageIndex = 1;
				this.orderList = [];
				this.getList();
			}
		},
		handleOrderDetail(id) {
			// #ifdef MP-WEIXIN
			getSystemConfig().then((res) => {
				uni.requestSubscribeMessage({
					tmplIds: JSON.parse(res.data),
					success: (res) => {
						console.log(res);
					}
				});
			});
			// #endif
			uni.navigateTo({
				url: '/subpackages/order/detail?id=' + id
			});
		},
		// 取消预约
		handleCancelOrder(id) {
			this.orderId = id;
			this.$refs.cancelReservate.open();
		},
		// 再次预约
		handleAgainOrder(id) {
			// this.orderId = id;
			// this.$refs.againReservate.open();
			uni.navigateTo({
				url: '/subpackages/Home/reservate'
			});
		}
	}
};
</script>

<style lang="scss" scoped>
.page {
	background-color: #fbfbfb;
	/* #ifdef MP-WEIXIN */
	min-height: 100vh;
	/* #endif */
	/* #ifdef H5 */
	min-height: calc(100vh - 44px - env(safe-area-inset-top));
	/* #endif */
	padding-bottom: calc(20rpx + env(safe-area-inset-bottom));
}
.tabs {
	display: flex;
	align-items: center;
	justify-content: space-around;
	padding: 26rpx 38rpx 32rpx 82rpx;
	.tab-pane {
		font-size: 32rpx;
		color: #b2b2b2;
		margin-bottom: 8rpx;
	}
	.tab-active {
		font-size: 36rpx !important;
		color: #000000 !important;
	}
	.active-strip {
		width: 40rpx;
		height: 10rpx;
		background: #000000;
		border-radius: 6rpx 6rpx 6rpx 6rpx;
		opacity: 1;
		margin: auto;
	}
}
.order-list {
	padding: 0 42rpx 0 32rpx;
	.order-item {
		padding: 30rpx 32rpx 30rpx 30rpx;
		background-color: #ffffff;
		border-radius: 24rpx;
		margin-bottom: 32rpx;
		position: relative;
		.order-status {
			position: absolute;
			top: 40rpx;
			right: 36rpx;
			font-size: 24rpx;
			color: #cccccc;
		}
		.order-info {
			display: flex;
			.order-img {
				width: 182rpx;
				height: 182rpx;
				margin-right: 24rpx;
				border-radius: 12rpx;
			}
			.order-detail {
				view {
					margin-bottom: 10rpx;
					&:last-child {
						margin-bottom: 0;
					}
				}
				.project-name {
					color: #333333;
					font-size: 28rpx;
				}
				.order-price,
				.create-time,
				.reservate-time {
					font-size: 24rpx;
					color: #888888;
				}
			}
		}
		.btns {
			display: flex;
			align-items: center;
			justify-content: flex-end;
			margin-top: 26rpx;
			.btn {
				width: 154rpx;
				height: 52rpx;
				border-radius: 26rpx 26rpx 26rpx 26rpx;
				opacity: 1;
				border: 2rpx solid #000000;
				font-size: 24rpx;
				display: flex;
				align-items: center;
				justify-content: center;
				margin-right: 20rpx;
				&:last-child {
					margin-right: 0;
				}
			}
			.btn-detail {
				background: #000000;
				color: #ffffff;
			}
			.cancel-btn {
				color: #000000;
			}
		}
	}
}
.no-result {
	display: flex;
	align-items: center;
	flex-direction: column;
	.no-order {
		width: 398rpx;
		height: 215rpx;
		margin: 196rpx 0 88rpx 0;
	}
	.no-result-text {
		text-align: center;
		font-size: 36rpx;
		color: #d6d6d6;
	}
}
</style>
