/**
 * 用户登录、注册
 */

import request from "@/request/index.js"

export function wxlogin(data) {
	return request({
		url: '/token/wxAppletLogin',
		method: 'POST',
		data: data
	})
}

export function wxregister(data) {
	return request({
		url: '/token/wxAppletRegister',
		method: 'POST',
		data
	})
}

/**
 * 退出登录
 */
export function userLogout() {
	return request({
		url: '/token/logout',
		method: 'DELETE'
	}, isLoading = false)
}

/**
 * 用户信息
 */
export function getUserInfo() {
	return request({
		url: '/reservation/fruser/personal',
		method: 'GET'
	})
}

/**
 * 更新用户信息
 */
export function updatePersonInfo(data) {
	return request({
		url: "/reservation/fruser/edit",
		method: 'POST',
		data
	});
}