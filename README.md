## SpringBoot模组化
旨在记录学习的点点滴滴

## 开发环境
| 工具 | 版本或描述 |
|-----|-----|
| OS | Windows 7 & 10 |
| JDK | 1.8 |
| IDE | IntelliJ IDEA 2017.2.6 |
| Maven | 3.3.9 |

### 普通模组
| 模组名 | 功能或描述 |
|-------|-------|
| springboot | 除SpringCloud模组外的父模组，有模块共用的依赖和LogBack |
| springboot-async | SpringAsync & SpringAsyncRestTemplate |
| springboot-cache | SpringCache & Caffeine |
| springboot-defaultexception | 统一异常处理 & 定制错误页 |
| springboot-schedule | SpringSchedule & Quartz简单使用 |
| springboot-mail | SpringMail |

### spring-cloud微服务模组功能简介
| 模组名 | serviceId | port  | 功能或描述 |
| ---- | ---- | ---- | ---- |
| springboot-cloud-eureka | eureka  | 31001  | 服务发现 |
| springboot-cloud-eureka-eureka-client | eureka-client   | 32001 | 服务提供者 |
| springboot-cloud-ribbon | ribbon   | 33001 | ribbon服务消费者(负载均衡、熔断、监控仪表)  |
| springboot-cloud-feign | feign   | 34001 | feign服务消费者(负载均衡、熔断、配置中心、监控仪表) |
| springboot-cloud-hystrix | hystix   | 35001 | ribbon服务消费者（熔断） |
| springboot-cloud-turbine | turbine   | 36001 | Dashboard统一监控仪表盘 |
| springboot-cloud-config-server | config-server | 37001 | config配置中心提供者 |
| springboot-cloud-config-client | config-client | 38001 | config配置中心消费者 |
| springboot-cloud-zuul | zuul | 39001 | API网关zuul |
