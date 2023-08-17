<template>
	<view class="page">
		<view class="staff-list">
			<view class="staff-item" v-for="personnel in personnelList" :key="personnel.id">
				<view style="margin-bottom: -5px">
					<image :src="personnel.preImg" mode="aspectFill" style="width: 320rpx; height: 318rpx; border-radius: 24rpx 24rpx 0 0"></image>
				</view>
				<view class="content">
					<view class="staff-name bold">{{ personnel.name }}</view>
					<view class="staff-draft regular">{{ personnel.introduction }}</view>
					<view class="select-btn regular" @tap="handleSelect(personnel)">选择</view>
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
import { getProductPersonnel } from '@/api/product.js';
export default {
	data() {
		return {
			productId: '',
			params: {
				pageIndex: 1,
				pageSize: 10
			},
			total: 0,
			personnelList: [],
			loading: false,
			noMoreShow: false
		};
	},
	onLoad(option) {
		this.productId = option.id;
		this.handleGetPersonnelList();
	},
	onReachBottom() {
		if (this.params.pageIndex * this.params.pageSize < this.total) {
			this.params.pageIndex += 1;
			this.handleGetPersonnelList();
		} else {
			this.noMoreShow = true;
		}
	},
	methods: {
		handleGetPersonnelList() {
			this.loading = true;
			getProductPersonnel(this.productId, this.params).then((res) => {
				this.loading = false;
				this.total = res.data.totalCount;
				this.personnelList = this.personnelList.concat(res.data.rows);
			});
		},
		handleSelect(personnel) {
			const pages = getCurrentPages();
			const prePage = pages[pages.length - 2].$vm;
			prePage.orderForm.personId = personnel.id;
			prePage.orderForm.personName = personnel.name;
			uni.navigateBack();
		}
	}
};
</script>

<style lang="scss" scoped>
.page {
	padding: 34rpx 32rpx;
}
.staff-list {
	display: flex;
	flex-wrap: wrap;
	.staff-item {
		width: 320rpx;
		margin-bottom: 34rpx;
		&:nth-child(2n + 1) {
			margin-right: 42rpx;
		}
		.content {
			background-color: #f6f6f6;
			padding: 30rpx 0 36rpx 34rpx;
			border-radius: 0 0 24rpx 24rpx;
			.staff-name {
				font-size: 28rpx;
				color: #333333;
			}
			.staff-draft {
				font-size: 24rpx;
				color: #888;
				margin: 16rpx 0 30rpx 0;
				overflow: hidden;
				text-overflow: ellipsis;
				display: -webkit-box;
				-webkit-box-orient: vertical;
				-webkit-line-clamp: 2;
			}
			.select-btn {
				width: 144rpx;
				height: 52rpx;
				background-color: #000000;
				font-size: 24rpx;
				color: #fff;
				border-radius: 26rpx;
				display: flex;
				align-items: center;
				justify-content: center;
			}
		}
	}
}
.no-more {
	font-size: 32rpx;
	color: #dcdcdc;
	text-align: center;
}
</style>
