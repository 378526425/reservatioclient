import request from '@/utils/request'

//获取全部人员数据接口
export const reqPersonList = (page,limit,data) => request({url:'/reservationdevapi/reservation/admin/person/page',method:'get',params:{name:data.name,pageIndex:page,pageSize:limit}});


export const reqPersonDelete = (id) => request({url:`/reservationdevapi/reservation/admin/person/delete/${id}`,method:'delete'});


export const PersonAddorUpdate = (data) => request({url:`/reservationdevapi/reservation/admin/person/edit`,method:'post',data:data});

export const reqPersonAll = (data) => request({url:`/reservationdevapi/reservation/admin/person/all`,method:'get'});