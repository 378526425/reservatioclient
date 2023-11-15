import request from '@/utils/request'

//获取全部人员数据接口
export const reqTypeList = (page,limit) => request({url:'/reservationdevapi/reservation/admin/category/page',method:'get',params:{pageIndex:page,pageSize:limit}});

export const reqTypedelete = (id) => request({url:`/reservationdevapi/reservation/admin/category/delete/${id}`,method:'delete'});

export const reqTypeeditorAdd = (data) => request({url:'/reservationdevapi/reservation/admin/category/edit',method:'post',data:data});

export const reqTypeAllList = (page,limit) => request({url:'/reservationdevapi/reservation/product/allCategory',method:'get'});

