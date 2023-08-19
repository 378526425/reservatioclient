<template>
	<view class="page">
		<form @submit="handleSubmit">
			<view class="form-item">
				<view class="label regular">个人头像</view>
				<view class="content">
					<button class="avatar-button" open-type="chooseAvatar" @chooseavatar="handleChooseAvatar"><image class="avatar" :src="avatarUrl"></image></button>
				</view>
			</view>
			<view class="form-item">
				<view class="label regular">昵称</view>
				<view class="content"><input v-model="nickname" type="nickname" name="nickname" class="nickname" /></view>
			</view>
			<button class="save" form-type="submit">保存</button>
		</form>
	</view>
</template>

<script>
import { getUserInfo, updatePersonInfo } from '@/api/user.js';
import { baseURl } from '@/request/index.js';
export default {
	data() {
		return {
			avatarUrl: '/static/images/my/avatar.png',
			nickname: ''
		};
	},
	onLoad() {
		getUserInfo().then(res => {
			const { nickName, headPortrait } = res.data;
			console.log(nickName, headPortrait);
			this.avatarUrl = headPortrait ? headPortrait : '/static/images/my/avatar.png';
			this.nickname = nickName;
		});
	},
	methods: {
		handleChooseAvatar(e) {
			uni.uploadFile({
				url: `${baseURl}/file/upload`,
				filePath: e.detail.avatarUrl,
				name: 'file',
				success: res => {
					const url = JSON.parse(res.data).data.url;
					this.avatarUrl = `${baseURl}/${url}`;
				}
			});
		},
		handleSubmit(e) {
			const userInfo = {
				headPortrait: this.avatarUrl,
				nickName: e.detail.value.nickname
			};
			updatePersonInfo(userInfo).then(res => {
				uni.navigateBack();
			});
		}
	}
};
</script>

<style lang="scss" scoped>
.page {
	padding: 38rpx 40rpx 0 38rpx;
	position: relative;
	height: 100vh;
}
.form-item {
	display: flex;
	align-items: center;
	justify-content: space-between;
	margin-bottom: 44rpx;
	.label {
		font-size: 32rpx;
		color: #000000;
	}
	.content {
		input {
			text-align: right;
			font-size: 32rpx;
		}
		.nickname {
			font-family: PingFang SC-Bold, PingFang SC;
			font-weight: bold;
			color: #000000;
		}
		.nickname-placeholder {
			font-size: 32rpx;
			font-family: PingFang SC-Regular, PingFang SC;
			font-weight: 400;
			color: #b7b7b7;
		}
	}
}
.avatar-button {
	width: 116rpx;
	height: 116rpx;
	border-radius: 50%;
	padding: 0;
	.avatar {
		width: 116rpx;
		height: 116rpx;
	}
}
.save {
	width: 686rpx;
	height: 80rpx;
	background: #2e2e2e;
	border-radius: 52rpx 52rpx 52rpx 52rpx;
	opacity: 1;
	font-size: 30rpx;
	font-family: PingFang SC-Heavy, PingFang SC;
	font-weight: 800;
	color: #ffffff;
	position: absolute;
	bottom: 78rpx;
}
</style>
