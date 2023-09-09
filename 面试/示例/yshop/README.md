<h1 style="text-align: center">yshop-gin意象商城后台管理系统</h1>




#### 项目简介
yshop-gin基于当前流行技术组合的前后端商城管理系统： yshop-gin意象商城后台管理系统(目前版本v1.2)基于当前流行技术组合的前后端商城管理系统：
Gin+Gorm+Casbin+Jwt+Redis+Mysql8+Vue 的前后端分离电商管理系统，权限控制采用RBAC，支持商城商品加入购物车、下单、评价、支付（微信支付与余额支付）、搜索、地址管理、快递鸟查询、多级分类，商品管理、商品sku、图片素材、数据字典与数据权限管理，支持动态路由等

#### 项目文档（还在完善中...，小伙伴记得star）

 文档地址：[https://gitee.com/guchengwuyue/yshop-gin/wikis/pages](http://gitee.com/guchengwuyue/yshop-gin/wikis/pages)


#### 项目源码

|     |   后端源码  |   后台前端源码  |  PC商城前端源码  |
|---  |--- | --- | --- |
|  码云  |  https://gitee.com/guchengwuyue/yshop-gin   |  https://gitee.com/guchengwuyue/yshop-gin-vue   |  https://gitee.com/guchengwuyue/yshop-gin-pc   |
|  github   | https://github.com/guchengwuyue/yshop-gin  |  https://github.com/guchengwuyue/yshop-gin-vue | https://gitee.com/guchengwuyue/yshop-gin-pc   |

#### 已经实现pc商城功能：
- 首页轮播图
- 首页商品展示
- 商品详情及其sku
- 商品加入购物车
- 商品下单
- 商品支付（微信与余额支付）
- 商品个人订单管理
- 商品的收藏
- 商品的地址管理
- 商品的评价管理
- 快递鸟快递查询
- 商品分类等搜索
- 个人中心图像上传等


####  已经实现商城后台系统功能
- 用户管理：提供用户的相关配置 
- 角色管理：对权限与菜单进行分配，可根据部门设置角色的数据权限 
- 菜单管理：已实现菜单动态路由，后端可配置化 
- 部门管理：可配置系统组织架构，树形表格展示 
- 岗位管理：配置各个部门的职位 
- 字典管理：可维护常用一些固定的数据，如：状态，性别等 
- 日志管理：用户操日志记录 
- 素材管理：图片素材库 <br>
- 分类管理：商品多级分类 <br>
- sku管理：商品sku规则管理 <br>
- 商品管理：可以添加单规格或者多规格商品含有百度编辑器 <br>
- 微信公众号：可微信图文、微信菜单等 <br>
- 订单管理：对订单发货查看详情等操作
- 物流快递：实现了快递鸟基本查询功能

#### 详细结构

```
- app 应用模块
    - controllers 控制器模块
    - models 模型模块
    - service 服务模块
      - product_serive
      ......
- conf 公共配置
- docs swagger
- middleware 中间件
	- cors.go 
	......
- pkg 程序应用包
  - app
  - casbin
  - jwt
  .....
- routere 路由
- logs 日志存放
- runtime 资源目录
```
#### 配置、启动、部署
```

1、下载项目：git clone https://github.com/guchengwuyue/yshop-gin-vue
2、npm install
3、配置项目，路径：./env.development  与 ./env.production 
上面一个开发环境，一个是生产环境下的配置
ENV = 'development'
# 接口地址
VUE_APP_BASE_API  = 'http://localhost:8080'  //配置后端api即可

4、本地运行：npm run dev
5、线上部署：npm run build 然后上传./dist/下编译后的文件到web服务器即可
```

#### 功能说明
pc端效果图：

| ![输入图片说明](%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_202205071646055.jpg) |
|--|

| ![输入图片说明](%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_202205071646056.jpg) |
|--|

| ![输入图片说明](https://gitee.com/guchengwuyue/yshop-gin-book/raw/master/pc03.png "suicai.png") |
|--|

| ![输入图片说明](https://gitee.com/guchengwuyue/yshop-gin-book/raw/master/pc04.png "suicai.png") |
|--|

| ![输入图片说明](%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20220507164605.jpg) |
|--|

| ![输入图片说明](%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_202205071646051.jpg) |
|--|

| ![输入图片说明](%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_202205071646052.jpg) |
|--|

| ![输入图片说明](%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_202205071646053.jpg) |
|--|


| ![输入图片说明](%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_202205071646054.jpg) |
|--|

1、商品分类
| ![输入图片说明](https://images.gitee.com/uploads/images/2021/1009/110639_af31d4e3_477893.png "shop1.png") |
|--|
| ![输入图片说明](https://images.gitee.com/uploads/images/2021/1009/110741_52c0ec39_477893.png "fenlei2.png") |
|--|
2、商品sku
| ![输入图片说明](https://images.gitee.com/uploads/images/2021/1009/110820_ca7f0034_477893.png "guige1.png") |
|--|
| ![输入图片说明](https://images.gitee.com/uploads/images/2021/1009/110830_c5221dae_477893.png "guige2.png") |
3、商品管理
|  ![输入图片说明](https://images.gitee.com/uploads/images/2021/1009/110908_81785443_477893.png "shangpin1.png")|
|--|
| ![输入图片说明](https://images.gitee.com/uploads/images/2021/1009/110917_2dbcfe03_477893.png "shangpin2.png") |
| ![输入图片说明](https://images.gitee.com/uploads/images/2021/1009/110938_0aee30d6_477893.png "shangpin3.png") |
|--|
4、微信公众号
| ![输入图片说明](weixin00.png)  |
|--|

5、系统用户
| ![输入图片说明](https://images.gitee.com/uploads/images/2021/1009/111101_7cc3c7f0_477893.png "yonghu.png") |
|--|
6、系统角色
| ![输入图片说明](https://images.gitee.com/uploads/images/2021/1009/111136_bdc744be_477893.png "juese.png") |
|--|
7、系统菜单
| ![输入图片说明](https://images.gitee.com/uploads/images/2021/1009/111202_9ffbd62a_477893.png "caidan1.png") |
|--|
8、订单
| ![输入图片说明](https://gitee.com/guchengwuyue/yshop-gin-book/raw/master/h1.png "caidan1.png") |
|--|

#### 技术选型
* 1 后端使用技术
    * 1.1 gin
    * 1.2 jwt
    * 1.3 redis
    * 1.5 Mysql8
    * 1.6 Gorm
    * 1.7 copier
    * 1.8 ksuid
    * 1.9 Redis
    * 1.10 swagger
    * 1.11 Casbin
        
* 前端使用技术
    * 2.1 Vue 全家桶
    * 2.2 Element

#### 特别鸣谢

- go-gin-example:https://github.com/EDDYCJY/go-gin-example
- gorm:https://gorm.io/
- casbin:https://casbin.org/
- vue:https://github.com/vuejs/vue
- element:https://github.com/ElemeFE/element
- eladmin-web:https://github.com/elunez/eladmin-web

