package com.ies.module.admin.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {


    public static final String AUTHORIZATION_HEADER= "Authorization";

    private ApiKey apiKeys(){
        return new ApiKey("JWT", "Authorization", "header");
    }

    private List<SecurityContext> securityContexts(){
        return Arrays.asList(SecurityContext.builder().securityReferences(sRef()).build());
    }

    private List<SecurityReference> sRef(){

        AuthorizationScope scope = new AuthorizationScope("global","accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = scope;
        return Arrays.asList(new SecurityReference("JWT",authorizationScopes));
    }


    @Bean
    public Docket api() {


        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getInfo())
                .securityContexts(securityContexts())
                .securitySchemes(Arrays.asList(apiKeys()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ies.module.admin.api"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo getInfo(){
        return new ApiInfo("IES-ADMIN-MODULE",
                "This is a admin APIs",
                "1.0",
                "Terms of service",
                new Contact("Pramod Lad", "www.linkedin.com", "jainpramodlad@gmail.com"),
                "License of API",
                "API license URL",
                Collections.emptyList());

    }




}