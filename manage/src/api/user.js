import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/reservationdevapi/token/admin/login',
    method: 'post',
    data
  })
}

export function logout() {
  return request({
    url: '/reservationdevapi/token/logout',
    method: 'delete'
  })
}
