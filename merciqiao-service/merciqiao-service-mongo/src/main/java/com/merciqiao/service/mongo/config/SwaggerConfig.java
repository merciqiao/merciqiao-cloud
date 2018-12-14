package com.merciqiao.service.mongo.config;

import com.google.common.collect.Sets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;

/**
 * Created by samli on 2017/11/9.
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    // 访问Swagger需要访问以下
    // http://IP:port/{context-path}/swagger-ui.html
    // 例如本机调试 http://localhost:8001/swagger-ui.html
    @Bean
    public Docket createRestApi() {

        HashSet producesSet = Sets.newHashSet("application/json");

        return new Docket(DocumentationType.SWAGGER_2)
                .produces(producesSet)
                .apiInfo(apiInfo())
                .select()
                //指明扫描的接口目录
                .apis(RequestHandlerSelectors.basePackage("com.merciqiao.service.mongo.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger2构建RESTful APIs")
                .version("1.0")
                .build();
    }
}
