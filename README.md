# account-books-server
1. 记账系统
   - 记账数据持久化、统一管理
   - 报表统计
   - 账单提醒
   - 记账人员信息统一管理
   - 认证登录
---
2. 项目集成
- [X] springboot项目初始化
- [X] controller层全局异常
- [X] controller层统一返回值格式工具类
- [ ] controller层出入参打印，操作日志表记录
- [ ] 定时任务集成
- [ ] 记账表结构设计
  - [ ] 记账表【biz_xx】
  - [ ] 记账用户表【biz_xx】
  - [ ] 操作日志表【biz_xx】
  - [ ] 用户三方登录表【base_xx】
  - [ ] 消息记录表（账单提醒消息）【biz_xx】
  - [ ] TODO
- [ ] MybatisPlus集成
- [ ] Swagger2集成
- [ ] logback集成
- [ ] 登录及页面鉴权组件选型：如spring security
---
3. 项目分层目录
```
-----common（公共组件和配置项等）
-----base（基础信息表：字典、配置、人员等基础信息）
       |--同biz
-----biz（业务相关表）
       |--accountbooks
            |--domain
            |--dao
            |--service
                |--impl
                    |--xxxServiceImpl.class
                |--xxxService.class
            |--controller
            |--bo
            |--vo
            |--enums
```
---
4. 支持功能
- [X] 记录欠款、还款、部分还款业务 
- [X] 选择人员
- [ ] 微信登录
- [ ] 报表统计
- [ ] 账单提醒
- [ ] TODO

