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
-  个人学习使用
- 允许用于学习、毕设等
- 允许进行商业使用，但需要保留客户端个人中心-技术支持页面，如需要删除该页，需联系作者取得作者授权，请自觉遵守使用协议
- 推荐Watch、Star项目，获取项目第一时间更新，同时也是对项目最好的支持
# 项目清单
完整项目清单包含如下<br>
1 小程序端<br>
2 服务端<br>
3 后台管理端<br>
本次源码开源部分仅包含小程序端，不包括后端和运营端，如需要其他端源码请联系作者
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
开发框架 uniapp
小程序端使用uniapp开发，一次开发可垮三端平台，支持发布到微信等小程序平台，H5,app端。
## 服务端
开发语言： java
开发框架： springboot,spring cloud alibaba 两种版本，设计时按微服务设计开发，鉴于微服务的复杂性，降低维护成本，改造了一个单体springboot版本，功能完全一样
数据库：mysql redis
