// 预约api
import request from "@/request/index.js"

/**
 * 获取用户信息补充
 */
export function getPersonnelInfo() {
	return request({
		url: 'reservation/productorder/historyOrder',
		method: 'GET'
	})
}