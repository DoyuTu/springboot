package com.doyutu.springbootschedule;

import com.doyutu.springbootschedule.spring.DynamicScheduleTasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringbootScheduleApplication {

	@Autowired
	DynamicScheduleTasks dynamicScheduleTasks;

	@GetMapping("/updateDynamicTask")
	private Object updateDynamicTask(){
		dynamicScheduleTasks.setCron("*/3 * * * * ?");
		return "SUCCESS";
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootScheduleApplication.class, args);
	}
}
