package com.zwb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient//表示这个是一个Eureka客户端
public class RedisApplication {
	public static void main(String[] args) {
		System.out.println("首页门户9004....."); 
		SpringApplication.run(RedisApplication.class, args);
	}
}
