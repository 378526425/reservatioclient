import request from '@/utils/request'

//获取全部订单数据接口
export const reqProductList = (page,limit,data) => request({url:'/reservationdevapi/reservation/admin/product/page',method:'get',params:{categoryId:data.categoryId,pageIndex:page,pageSize:limit,title:data.title}});


export const reqProductedit = (data) => request({url:'/reservationdevapi/reservation/admin/product/edit',method:'post',data:data});


export const reqProductdelete = (id) => request({url:`/reservationdevapi/reservation/admin/product/delete/${id}`,method:'delete'});


export const reqProductaddPerson = (personIds,productId) => request({url:'/reservationdevapi/reservation/admin/product/addPerson',method:'post',data:{personIds:personIds,productId:productId}});

export const reqProductInfo = (id) => request({url: `/reservationdevapi/reservation/admin/product/info/${id}`,method:'get'});

export const reqProductStatus = (data) => request({url:'/reservationdevapi/reservation/admin/product/status',method:'PUT',data:data});
