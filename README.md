## SpringBoot模组
记录学习中的点点滴滴

### 开发环境
| 工具 | 版本或描述 |
|-----|-----|
| OS | Windows 7 & 10 |
| JDK | 1.8 |
| IDE | IntelliJ IDEA 2018.1 |
| Maven | 3.5.0 |

### 普通模组
* spring-boot-starter-parent v1.5.9.RELEASE
* spring-boot-starter-parent v2.0.1.RELEASE
---
| 模组名 | 功能或描述 |
|-------|-------|
| springboot | 公用依赖与LogBack |
| springboot-async | SpringAsync & SpringAsyncRestTemplate |
| springboot-cache | SpringCache & Caffeine & Redis|
| springboot-defaultexception | 统一异常处理 & 定制错误页 |
| springboot-schedule | SpringSchedule & Quartz简单使用 |
| springboot-mail | SpringMail |
| springboot-swagger | Swagger-ui & Swagger-bootstrap-ui |
| springboot-spider | WebMagic爬虫-网易云音乐列表、Runoob首页图片爬取下载 |
| springboot-quasar | Java纤程框架，需要配置VM（-javaagent:path-to-quasar-jar.jar）已搁置 |
| springboot-forkjoin | ForkJoin简单使用 |
| springboot-MybatisPlus | MybatisPlus 简单使用 |
| springboot-Netty | Netty 服务端与客户端NIO 简单使用 |
| springboot-WebSocket | Netty 服务端与HTML页面 简单交互 & Spring WebSocket & Spring SockJS & Spring STOMP |
| springboot-aop| AOP与DI简单实现 |
| springboot-docker| SpringBoot在Tomcat镜像war包方式构建Docker项目 |

---

### spring-cloud微服务模组功能简介
* spring-boot-starter-parent v1.5.9.RELEASE
* spring-cloud.version Dalston.SR4
---
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
| springboot-cloud-bus | bus-amqp | 40001 |config自动刷新配置 -v Edgware.RELEASE |
