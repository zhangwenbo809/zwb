package com.zwb;

import cn.dev33.satoken.SaManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

 // 标注启动 sa-token
@SpringBootApplication
@EnableEurekaClient //启用注册中心
public class SaTokenDemoApplication {
        public static void main(String[] args) throws JsonProcessingException {
            SpringApplication.run(SaTokenDemoApplication.class, args); // run-->
            System.out.println("启动成功：sa-token配置如下：" + SaManager.getConfig());
        }
    }

