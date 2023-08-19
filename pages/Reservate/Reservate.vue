<template>
	<view class="page">
		<view class="form reservate-shop">
			<view class="label regular">预约门店</view>
			<view class="content bold">莱特妮斯（沙坪坝店）</view>
		</view>
		<view class="form">
			<view class="label regular">到店时间</view>
			<view class="content bold">
				<view class="input" @tap="handleSelectTime">
					<input :class="[orderForm.startTime ? '' : 'input-placeholder']" v-model="orderForm.startTime" type="text" disabled placeholder="请选择" />
				</view>
				<image src="/static/images/reservate/arrow-right.png" style="width: 12rpx; height: 24rpx"></image>
			</view>
		</view>
		<view class="form">
			<view class="label regular">服务项目</view>
			<view class="content bold">
				<view class="input" @tap="handleSelectProduct">
					<input :class="[orderForm.productName ? '' : 'input-placeholder']" v-model="orderForm.productName" disabled type="text" placeholder="请选择" />
				</view>
				<image src="/static/images/reservate/arrow-right.png" style="width: 12rpx; height: 24rpx"></image>
			</view>
		</view>
		<view class="form">
			<view class="label regular">服务人员</view>
			<view class="content bold">
				<view class="input" @tap="handleSelectStaff">
					<input :class="[orderForm.personName ? '' : 'input-placeholder']" v-model="orderForm.personName" type="text" disabled placeholder="请选择" />
				</view>
				<image src="/static/images/reservate/arrow-right.png" style="width: 12rpx; height: 24rpx"></image>
			</view>
		</view>
		<view class="form">
			<view class="label regular">预约人</view>
			<view class="content bold">
				<view class="input"><input :class="[orderForm.contacts ? '' : 'input-placeholder']" v-model="orderForm.contacts" type="text" disabled placeholder="请输入" /></view>
			</view>
		</view>
		<view class="form phone">
			<view class="label regular">手机号</view>
			<view class="content bold">
				<view class="input"><input :class="[orderForm.phone ? '' : 'input-placeholder']" v-model="orderForm.phone" type="text" disabled placeholder="请输入" /></view>
			</view>
		</view>
		<view class="form remark">
			<view class="label regular">备注</view>
			<view class="content bold">
				<view class="input"><input :class="[orderForm.remark ? '' : 'input-placeholder']" v-model="orderForm.remark" type="text" placeholder="请输入（选填）" /></view>
			</view>
		</view>
		<view class="btn" @tap="handleNow">立即预约</view>
		<againReservate ref="againReservate" :text="text"></againReservate>
		<reservateTime ref="reservateTime" @confirm="handleConfirm"></reservateTime>
	</view>
</template>

<script>
import { getPersonnelInfo } from '@/api/reservate.js';
import { createOrder } from '@/api/order.js';
import { isLogin, goLogin } from '@/utils/index.js';
export default {
	data() {
		return {
			orderForm: {
				contacts: '预约人',
				personId: '',
				personName: '',
				phone: '18990909090',
				productId: '',
				productName: '',
				remark: '',
				startTime: ''
			},
			text: ''
		};
	},
	async onLoad() {
		// 获取用户信息补充
		const res = await getPersonnelInfo();
		this.orderForm.contacts = res.data.contacts;
		this.orderForm.phone = res.data.phone;
	},
	methods: {
		handleSelectTime() {
			this.$refs.reservateTime.open();
		},
		handleSelectStaff() {
			if (this.orderForm.productId) {
				uni.navigateTo({
					url: '/subpackages/Reservate/addStaff?id=' + this.orderForm.productId
				});
			} else {
				uni.showToast({
					icon: 'none',
					title: '请选择服务项目'
				});
			}
		},
		handleSelectProduct() {
			uni.navigateTo({
				url: '/subpackages/Reservate/addProduct'
			});
		},
		handleConfirm(val) {
			this.orderForm.startTime = val;
		},
		//立即预约
		handleNow() {
			if (!this.orderForm.startTime) {
				// return uni.showToast({
				// 	icon: 'none',
				// 	title: '请选择到店时间'
				// });
				this.text = '请选择到店时间';
				this.$refs.againReservate.open();
				return;
			}
			if (!this.orderForm.productId) {
				// return uni.showToast({
				// 	icon: 'none',
				// 	title: '请选择服务项目'
				// });
				this.text = '请选择服务项目';
				this.$refs.againReservate.open();
				return;
			}
			if (isLogin()) {
				return uni.showModal({
					title: '提示',
					content: '您还未登录，是否去登录',
					success: res => {
						if (res.confirm) {
							goLogin();
						}
					}
				});
			}
			const _data = {
				contacts: this.orderForm.contacts,
				personId: this.orderForm.personId,
				phone: this.orderForm.phone,
				productId: this.orderForm.productId,
				remark: this.orderForm.remark,
				startTime: this.orderForm.startTime
			};
			createOrder(_data).then(res => {
				if (res.code !== 200) {
					this.text = res.msg;
					this.$refs.againReservate.open();
				} else {
					uni.showToast({
						icon: 'none',
						title: '预约成功',
						duration: 1500
					});
					setTimeout(() => {
						uni.navigateTo({
							url: '/subpackages/order/order'
						});
					}, 1500);
				}
			});
		}
	}
};
</script>

<style lang="scss" scoped>
.page {
	padding: 0 32rpx;
}
.reservate-shop {
	border-bottom: 2rpx solid #f6f6f6;
	padding: 42rpx 0 44rpx 0 !important;
	margin-bottom: 42rpx;
}
.form {
	display: flex;
	align-items: center;
	padding: 10rpx 0 42rpx 0;
	.label {
		font-size: 32rpx;
		color: #000;
		width: 140rpx;
	}
	.content {
		margin-left: 42rpx;
		color: #000;
		display: flex;
		align-items: center;
		flex: 1;
		.input {
			flex: 1;
		}
	}
}
.phone {
	border-bottom: 2rpx solid #f6f6f6;
}
.remark {
	margin: 52rpx 0 44rpx 0;
}
.btn {
	height: 80rpx;
	border-radius: 52rpx;
	background-color: #2e2e2e;
	color: #fff;
	font-size: 30rpx;
	font-family: PingFang SC-Heavy, PingFang SC;
	font-weight: 800;
	display: flex;
	align-items: center;
	justify-content: center;
}
.input-placeholder {
	font-size: 32rpx;
	font-family: PingFang SC-Regular, PingFang SC;
	font-weight: 400;
	color: #b7b7b7;
}
</style>
