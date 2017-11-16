package com.doyutu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.AsyncRestTemplate;

import java.nio.charset.StandardCharsets;

@SpringBootApplication
@Component
public class SpringbootAsyncApplication {

	@Bean
	public AsyncRestTemplate asyncRestTemplate() {
		AsyncRestTemplate asyncRestTemplate = new AsyncRestTemplate();
		/** 设置编码器为UTF-8 防止中文乱码，其RestTemplate默认编码为 ISO-8859-1
		 * @see org.springframework.web.client.RestTemplate
		 * @see org.springframework.http.converter.StringHttpMessageConverter.DEFAULT_CHARSET
		 */
		asyncRestTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
		return asyncRestTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAsyncApplication.class, args);
	}
}
