package com.xc.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig {


    @Bean
    public Docket userApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("用户")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xc.controller"))// 选择那些路径和api会生成document
                .paths(PathSelectors.any()) // 对所有路径进行监控
                .build()
                .apiInfo(userInfo());
    }

    private ApiInfo userInfo() {
        // 用ApiInfoBuilder进行定制
        return new ApiInfoBuilder()
                // 设置标题
                .title("")
                // 描述
                .description("")
                // 作者信息
                .contact("长江 长江 长江")
                // 版本
                .version("版本号:1.0.0")
                .build();
    }

}
