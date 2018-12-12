package com.merciqiao.registercenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
//开启Eureka Server
@EnableEurekaServer
public class RegisterCenterApplication {
    public static void main(String[] args){
        SpringApplication.run(RegisterCenterApplication.class,args);
    }
}
