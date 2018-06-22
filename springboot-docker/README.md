## 构建环境
>- Ubuntu 18.04 LTS
>- Maven 3.3.9
>- Docker
并确保环境可正常使用。
## 使用说明
```
mvn clean package
mvn dockerfile:build
```
如果出现一下内容，则构建成功
```
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```

创建容器：
```
docker run -d -p 8888:8080 --name tomcat-demo doyutu/springboot-docker:0.0.1-SNAPSHOT
```

[本地浏览器访问 http://localhost:8080/springboot-docker](http://localhost:8080/springboot-docker) 
