package com.zwb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer //启用注册中心
public class Eureka1App {

    public static void main(String[] args) {
        System.out.println("注册中心8761...");
        SpringApplication.run(Eureka1App.class,args);
    }
}
