// 订单api
import request from "@/request/index.js"

/**
 * 创建订单
 */
export function createOrder(data) {
	return request({
		url: 'reservation/productorder/add',
		method: 'POST',
		data: data
	}, true)
}

/**
 * 获取订单列表
 */
export function orderList(params) {
	return request({
		url: 'reservation/fruser/order/page',
		method: 'GET',
		data: params
	})
}

/**
 * 订单详情
 */
export function orderDetail(id) {
	return request({
		url: `reservation/productorder/detail/${id}`,
		method: 'GET'
	})
}

/**
 * 取消订单
 */
export function cancelOrder(id) {
	return request({
		url: `reservation/productorder/cancel/${id}`,
		method: 'GET'
	})
}