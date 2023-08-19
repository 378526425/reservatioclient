/**
 * 首页api
 */

import request from "@/request/index.js"

/**
 * 获取预览信息
 */
export function getHomeData() {
	return request({
		url: 'reservation/product/summary',
		method: "GET"
	})
}

/**
 * 查询系统配置
 */
export function getSystemConfig() {
	return request({
		url: "/msfast/sysconfig/value",
		method: 'GET',
		data: {
			code: 'Wx_Template'
		}
	})
}