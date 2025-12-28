# online-qa-spring

## 项目介绍
基于Spring生态重构的在线问答留言交互平台，核心采用Spring IOC和Spring MVC核心思想开发，使用SpringBoot快速整合技术栈，实现用户登录、验证码校验、问答帖子发布、帖子查看、留言回复等在线问答核心功能，架构分层清晰，组件解耦

## 技术栈
SpringBoot、Spring IOC、Spring MVC、Thymeleaf、JDK17

## 核心架构
- 采用Spring MVC三层架构：Controller层接收请求、Service层处理业务逻辑、数据层处理数据交互，视图层由Thymeleaf实现页面渲染。
- 基于Spring IOC容器 实现依赖注入，所有业务组件由容器统一管理，自动装配依赖对象，解耦业务代码，提升项目可维护性。

## 运行端口
8088

## 访问地址
服务器：http://10.100.164.7:8088/
