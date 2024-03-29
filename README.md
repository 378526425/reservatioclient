# 简介
美容行业门店服务预约小程序，适用于线下个体户，有自己的品牌或者门店的商家，店主，做美容，美发，医美，按摩等服务性质行业的在线预约，管理，可实现客户在线查看，服务预约，到期提醒，商家在线管理服务项目，服务订单 ，处理服务 ，通知客户等，增强门店品牌效应，展示产品，提高服务产品营销传播途径，给客户带来信任感,降低销售阻力。
# 原文地址
https://www.wxmblog.com/archives/mei-rong-xing-ye-men-dian-fu-wu-yu-yue-xiao-cheng-xu
# 演示地址
## 小程序端小程序演示
微信扫描下方小程序码<br>
![gh_ccacf93dc844_258 (2)](https://www.wxmblog.com/upload/2023/09/gh_ccacf93dc844_258%20(2).jpg)
## H5演示地址
https://www.wxmblog.com/reservationapp
## 后台管理演示地址
http://1.14.110.117:6104<br>
账号：demo<br>
密码：123456
#  合作洽谈
专业后端，资深UI，前端，运维，产品，测试，项目管理俱全，项目经验丰富，如果你有软件开发需求可与我联系，微信：MMRWXM（请注明来意）
# 项目地址
## github
https://github.com/378526425/reservatioclient
## gitee
https://gitee.com/wxmr0/reservationapp
# 关于授权
✅允许
- 允许用于个人学习、毕业设计、教学案例、公益事业、商业使用
- 如果商用必须保留版权信息，即保留客户端个人中心-技术支持页面（作者信息），如需要删除该页，需联系作者取得作者授权，请自觉遵守使用协议
- 禁止将本项目的部分或全部代码以及基于此项目二次开发后的产品和资源进行任何形式的开源再发行（尤其上传GitHub,Gitee,插件市场等互联网公开场所）
- 禁止将本项目的代码和资源以及基于此项目二次开发后的产品和资源进行任何形式的出售，产生的一切任何后果责任由侵权者自负
- 推荐Watch、Star项目，获取项目第一时间更新，同时也是对项目最好的支持 <br>

# 项目清单
完整项目清单包含如下<br>
1 小程序端(uni-client)<br>
2 服务端(reservation-server)<br>
3 后台管理端(manage)<br>
# 项目结构
## 小程序端
```
platform
|--api api接口请求
|--components 公共组件
|--pages tab页面
|--request restful接口请求封装
|--static 静态资源
|--subpackages 分包页面
|--uni_modules 引用组件包
|--utils 工具包
|--......
```
## 服务端
```
platform
|--authority 权限认证
|--common 公共工具类
|--controller 控制层
|--dao 数据访问层 
|--entity 实体类
|--schedules 定时器
|--service 业务层
|--......
```
## 后台管理端
```
platform
|--mock 公共相关js
|--public 静态资源
|--src 核心业务
|--......
```
# 技术架构
## 小程序端
开发框架 uniapp<br>
小程序端使用uniapp开发，一次开发可垮三端平台，支持发布到微信等小程序平台，H5,app端。
## 服务端
开发语言： java<br>
开发框架： springboot,spring cloud alibaba 两种版本，设计时按微服务设计开发，鉴于微服务的复杂性，降低维护成本，改造了一个单体springboot版本，功能完全一样<br>
数据库：mysql redis<br>
文件存储:  minio<br>
短信通知：阿里云短信
## 后台管理端
开发框架： vue
# 功能模块
## 客户端
- 服务项目浏览
- 服务项目预约
- 服务即将到期微信内 短信提醒
- 修改个人头像昵称
- 个人中心
- 服务订单列表，待服务，已服务，已过期等
- 取消服务
- 登录 注销登录
- 微信好友 朋友圈分享
-  ......
## 后台管理端
- 人员管理
- 分类管理
- 产品管理
- 订单管理
- ......

# 页面展示
## 客户端
![Snipaste_2023-09-28_15-29-50](https://www.wxmblog.com/upload/2023/09/Snipaste_2023-09-28_15-29-50.png)
![Snipaste_2023-09-28_15-31-53](https://www.wxmblog.com/upload/2023/09/Snipaste_2023-09-28_15-31-53.png)
![4-1695822647320](https://www.wxmblog.com/upload/2023/09/4-1695822647320.PNG)
![5-1695822647224](https://www.wxmblog.com/upload/2023/09/5-1695822647224.PNG)
![6-1695822647326](https://www.wxmblog.com/upload/2023/09/6-1695822647326.PNG)
## 后台管理端
![1](https://www.wxmblog.com/upload/2023/09/1.PNG)
![2](https://www.wxmblog.com/upload/2023/09/2.PNG)
![3](https://www.wxmblog.com/upload/2023/09/3.PNG)
![5](https://www.wxmblog.com/upload/2023/09/5.PNG)
![3](https://www.wxmblog.com/upload/2023/09/3.png)
![2](https://www.wxmblog.com/upload/2023/09/2.png)
![1](https://www.wxmblog.com/upload/2023/09/1.png)
![6](https://www.wxmblog.com/upload/2023/09/6.PNG)
![4](https://www.wxmblog.com/upload/2023/09/4.PNG)
![10](https://www.wxmblog.com/upload/2023/09/10.PNG)
# 联系我们
## 作者微信
MMRWXM
## 	QQ交流群
651724227
# 其他优秀开源项目推荐
https://www.wxmblog.com/categories/open