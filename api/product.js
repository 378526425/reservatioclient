// 产品api
import request from "@/request/index.js"

/**
 * 获取产品分类
 */
export function productCategory() {
	return request({
		url: '/reservation/product/allCategory',
		method: 'GET'
	})
}

/**
 * 获取优惠项目
 */
export function getCouponProductList(params) {
	return request({
		url: '/reservation/product/page',
		method: 'GET',
		data: params
	})
}

/**
 * 获取项目详情
 */
export function getProductDetail(id) {
	return request({
		url: `reservation/product/info/${id}`,
		method: 'GET'
	})
}

/**
 * 获取产品的服务人员
 */
export function getProductPersonnel(productId, data) {
	return request({
		url: `reservation/person/page/${productId}`,
		method: 'GET',
		data
	})
}