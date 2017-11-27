package com.doyutu.springbootswagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SpringbootSwaggerApplication {

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				//请求头路径
				.pathMapping("/")
				.select()
				//swagger注解扫描api路径
				.apis(RequestHandlerSelectors.basePackage("com.doyutu.springbootswagger.controller"))
				.paths(PathSelectors.any())
				.build();
	}

	/**
	 * 	构建 api文档的详细信息函数
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				//页面标题
				.title("Spring Boot 测试使用 Swagger2 构建RESTful API")
				//服务url
				.termsOfServiceUrl("http://localhost:8081/")
				//作者
				.contact(new Contact("doyutu","",""))
				//版本号
				.version("1.0.0")
				//描述
				.description("API 描述")
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootSwaggerApplication.class, args);
	}
}
