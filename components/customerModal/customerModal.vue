<template>
	<view class="mask">
		<slot></slot>
	</view>
</template>

<script>
export default {
	name: 'customerModal',
	data() {
		return {};
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
	align-items: center;
	justify-content: center;
}
</style>
