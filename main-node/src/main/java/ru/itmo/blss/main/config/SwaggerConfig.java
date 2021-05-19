package ru.itmo.blss.main.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
@AllArgsConstructor
public class SwaggerConfig {

    @Bean
    public Docket externalApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ru.itmo.blss.main.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .groupName("default");
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Страна мам", "социальная сеть - http://www.stranamam.ru/",
                "1.0", null, null, null, null, Collections.emptyList());
    }
}
