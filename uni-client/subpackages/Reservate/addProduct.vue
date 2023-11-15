<template>
	<view class="page">
		<view class="category-list">
			<scroll-view class="scroll-x" scroll-x>
				<!-- <view :class="['category', activeName === '' ? 'category-active bold' : '']" @tap="handleSelect('')">
					全部分类
					<view v-if="activeName === ''" class="slider"></view>
				</view> -->
				<view :class="['category', activeName === category.id ? 'category-active bold' : '']" v-for="category in categoryList" :key="category.id" @tap="handleSelect(category.id)">
					{{ category.name }}
					<view v-if="activeName === category.id" class="slider"></view>
				</view>
			</scroll-view>
		</view>
		<view class="project-list">
			<view class="project-item" v-for="product in productList" :key="product.id">
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
					<view class="select-btn regular" @tap="handleSelectProduct(product)">选择</view>
				</view>
			</view>
		</view>
		<uni-load-more v-if="productList.length > 0" :status="returnLoadingText"></uni-load-more>
	</view>
</template>

<script>
import { productCategory, getCouponProductList } from '@/api/product.js';
export default {
	data() {
		return {
			activeName: '',
			categoryList: [],
			params: {
				pageIndex: 1,
				pageSize: 10,
				categoryId: ''
			},
			total: 0,
			productList: [],
			loading: false,
			noMoreShow: false
		};
	},
	async onLoad() {
		await this.handleGetCategory();
		await this.handleGetProductList();
	},
	onReachBottom() {
		if (this.params.pageIndex * this.params.pageSize < this.total) {
			this.params.pageIndex += 1;
			this.handleGetProductList();
		} else {
			this.noMoreShow = true;
		}
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
		handleGetCategory() {
			productCategory().then((res) => {
				this.categoryList = res.data;
				this.categoryList.unshift({
					id: '',
					name: '全部分类'
				});
				this.categoryList.forEach((item) => {
					item['total'] = 0;
					item['list'] = null;
					item['pageIndex'] = 1;
				});
			});
		},
		handleSelect(type) {
			this.activeName = type;
			this.params.categoryId = type;
			const currentCategory = this.categoryList.find((item) => item.id === type);
			if (currentCategory) {
				if (currentCategory.list) {
					this.params.pageIndex = currentCategory.pageIndex;
					this.productList = currentCategory.list;
					this.total = currentCategory.total;
				} else {
					this.params.pageIndex = 1;
					this.productList = [];
					this.handleGetProductList();
				}
			}
		},
		handleGetProductList() {
			this.loading = true;
			getCouponProductList(this.params).then((res) => {
				this.loading = false;
				this.total = res.data.totalCount;
				this.productList = this.productList.concat(res.data.rows);
				this.categoryList.find((item) => item.id === this.activeName).list = this.productList;
				this.categoryList.find((item) => item.id === this.activeName).total = this.total;
			});
		},
		handleSelectProduct(product) {
			const pages = getCurrentPages();
			const prePage = pages[pages.length - 2].$vm;
			prePage.orderForm.productId = product.id;
			prePage.orderForm.productName = product.title;
			uni.navigateBack();
		}
	}
};
</script>

<style lang="scss" scoped>
.page {
	padding-top: 34rpx;
	padding-bottom: 10rpx;
}
.category-list {
	white-space: nowrap;
	width: 100%;
	padding-left: 74rpx;
	.category {
		display: inline-flex;
		text-align: center;
		font-size: 32rpx;
		color: #b2b2b2;
		margin-right: 68rpx;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		&:last-child {
			margin-right: 0;
		}
		.slider {
			margin-top: 8rpx;
			width: 40rpx;
			height: 10rpx;
			background: #000000;
			border-radius: 6rpx 6rpx 6rpx 6rpx;
			opacity: 1;
		}
	}
	.category-active {
		font-size: 36rpx;
		color: #000000;
	}
}
.project-list {
	width: 682rpx;
	margin: 52rpx auto 0;
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
			.select-btn {
				margin-top: 28rpx;
				width: 144rpx;
				height: 52rpx;
				background: #000000;
				border-radius: 26rpx 26rpx 26rpx 26rpx;
				opacity: 1;
				font-size: 24rpx;
				color: #ffffff;
				text-align: center;
				line-height: 52rpx;
			}
		}
	}
}
</style>
