package com.example.aeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer   // 启用注册中心Eureka
@SpringBootApplication
public class AEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AEurekaServerApplication.class, args);
    }

}
