电商一期项目：针对服务器的一个开发
电商二期项目：Tomcat集群和分布式这种架构的项目
电商三期项目：基于springboot框架的微信点餐项目
演进到微服务架构

v1.0
项目开发采用四层架构
view->视图层
controller->控制层。接收前端的输入，并调用service层。
service->业务逻辑层。处理业务逻辑并调用dao层
dao->持久层  操作数据库 MyBatis
接口隔离原则-》扩展
·Dao接口和Dao的实现类
  service接口和service实现类

vo -->view  object(value object)vo这个类是显示在视图层的类

db-->pojo-->vo-->显示出来所看到的

mybatis-generator插件：根据数据库里的表结构自动的生成pojo里的实体类，dao接口，以及自动生成mabatis的mapper映射文件

###接口测试的插件
postman软件
restlet插件

电商项目用到的开发工具及软件
1、jdk
2、安装mysql
3、开发工具：IntelliJ IDEA
4、基于maven构建
5、git做版本管理
6、前后端分离项目——》nginx 
json介绍

需求
前台
  购买
    商品->首页、商品列表、商品详情
    购物车->商品添加到购物车、更改购物车中商品的数量、删除商品、全选、取消全选、单选、结算
    订单
      下单：订单确认(地址管理)，订单提交
      订单中心：订单列表、订单详情、取消订单
    地址：crud
    支付：支付宝
  用户体系
    登录
    注册
    修改密码
    ...
    
 后台
   管理员登录
   商品管理：添加商品、修改、商品上下架
   品类管理：查看、添加
   订单管理：查看、发货
   
   数据表结构设计
   1、用户表(用户名唯一、MD5加密)
   2、类别表、无限层级表结构
      id  name     parent_id
      1   电子产品    0
      2   手机        1
      3   华为手机    2
      4   小米手机    2
      5   meta20 pro  3
      ...
      查询电子产品下所有的子类别？
        递归查询
   
  3、商品详情表
  4、购物车表
  5、支付信息表
  6、订单表
  7、订单明细表
  8、收货地址表
  
 ###搭建ssm框架
 1、pom.xml
 2、添加配置文件
    spring配置文件
    springmvc配合文件
    mybatis配置文件
    web.xml
  3、使用框架
  
  ###找回密码接口
  1、校验username -->查询找回密码问题
  2、前端，提交问题答案
  3、校验答案 -->修改密码
  
  ###迭代器开发-线上部署
  1、在阿里云服务器上建库，建表
  2、修改代码中数据库的连接参数
  3、项目打成war包
  4、将war包上传到阿里云服务器的tomcat/webapps
  5、访问测试
  
  ###类别模块
  ###1.功能介绍
        获取节点
        增加节点
        修改名称
        获取分类
        递归子节点ID
   ###2.学习目标
         如何设计及封装无限层级的树状数据结构
         递归算法的设计思想
         如何处理复杂对象重拍
         重写hashcode和equals的注意事项