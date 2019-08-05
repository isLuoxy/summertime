# SummerTime IM
基于 `SpringBoot 2.x` 搭建，使用 `netty` 作为底层通讯框架，`zookeeper` 作为注册中心

## 0.1
底层架构为 

涉及的模块有
- summertime-client：客户端模块
- summertime-server： 服务端模块
- summertime-route： 路由模块
- summertime-zookeeper：注册中心模块
- summertime-common：公共基础模块

路由模块整合注册中心模块，提供服务调用。客户端和服务端模块采用 `dubbo` 进行服务注册和服务发现调用。


### 功能实现
- 私聊 
- 群聊
- 推送功能
- 可视化界面

### 重构思路
使用 `dubbo` 替代 route ，实现服务注册、负载均衡、服务发现。详细优点查看 `dubbo` 详细介绍