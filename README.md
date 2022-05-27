# 开箱即用的用户中心



## ✨特性

+ 🌈 开箱即用，配置简单。 
+ ⭐ 规范的代码和目录结构
+ 🌍 快速进行二次开发/功能拓展
+ 🛡 实现了用户管理



## 🔧 技术选型

### 后端

+ Java11
+ SpringBoot
+ MybatisPlus

### 存储

+ Redis 
+ Mysql

### 部署

- 前端：Nginx + Linux Server
- 后端：Docker + 自动化构建



## 📦 项目目录

base  --  存放请求和响应的封装类

config --  配置跨域，Mybatis, 请求拦截器

constans  -- 配置常量

controller -- 控制器

exception -- 异常封装

interceptor -- 请求拦截器

mapper  -- mybatis

model -- 存放实体类

service --- 业务层

untils -- 自行封装的工具类



## 🔥启动项目

1. 在本地mysql创建usercenter数据库

2. 将项目根目录下的sql/usercenter_.sql导入到创建好的usercenter数据库

3. 为Mybatis 配置数据库账号和密码：

   在src/resource/application.yml 中配置你的数据库账号和密码

   ![image-20220527101942517](https://xingqiu-tuchuang-1256524210.cos.ap-shanghai.myqcloud.com/886/image-20220527101942517.png)

4. 启动Redis

5. 启动UserBackendApplication。即可完整运行项目
