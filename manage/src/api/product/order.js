import request from '@/utils/request'


//获取全部订单数据接口
export const reqOrderList = (page,limit,data) => request({url:'/reservationdevapi/reservation/admin/productorder/page',method:'get',params:{contacts:data.contacts,pageIndex:page,pageSize:limit,phone:data.phone,status:data.status,title:data.title}});


export const reqOrderedit = (id) => request({url:`/reservationdevapi/reservation/admin/productorder/service/order/${id}`,method:'post'});
