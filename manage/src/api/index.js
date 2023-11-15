//将四个模块请求的接口函数统一暴露
import * as person from './product/person';
import * as type from './product/type';

//引入权限相关的接口文件
// import * as user from './acl/user';
// import role from './acl/role';
// import permission from './acl/permission'
//对外暴露
export default {
     person,
     type
    //  user,
    //  role,
    //  permission
}