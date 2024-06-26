package com.service.servicecoupon.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("coupon-api")
                        .version("1.0")
                        .description("review-api swagger-ui 화면입니다."));
    }

    @Bean
    public GroupedOpenApi api() {
        String[] paths = {"/**"};
        String[] packagesToScan = {"com.service.servicecoupon"};
        return GroupedOpenApi.builder().group("springdoc-openapi")
                .pathsToMatch(paths)
                .packagesToScan(packagesToScan)
                .build();
    }
}