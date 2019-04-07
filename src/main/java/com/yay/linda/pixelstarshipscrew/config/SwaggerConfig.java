package com.yay.linda.pixelstarshipscrew.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfo(
                        "PixelStarships Crew, Items, Rooms Helper",
                        "A Simple API to assist with PixelStarships strategic crew, items, room placement and assignment",
                        "1.0",
                        "termsOfServiceUrl",
                        new Contact("Linda Zheng", "github.com/yaylinda", "lindazheng1993@gmail.com"),
                        "license",
                        "licenseUrl",
                        new ArrayList<>()
                ));
    }
}