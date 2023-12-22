package com.ies.module.dc.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration

public class SwaggerConfig {

    @Bean
    public Docket app(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("DC-MODULE")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ies.module.dc.api.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
