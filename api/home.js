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