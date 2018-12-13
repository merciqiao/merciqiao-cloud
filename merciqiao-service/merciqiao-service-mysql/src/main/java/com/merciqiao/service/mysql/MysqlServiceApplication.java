package com.merciqiao.service.mysql;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//mybatis配置 扫描mapper
@MapperScan("com.merciqiao.service.mysql.mapper")


public class MysqlServiceApplication {
    public static void main(String[] args){
        SpringApplication.run(MysqlServiceApplication.class);
    }
}
