package com.company.FlightAPI.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerOpenAPIConfig {
    @Bean
    public OpenAPI swaggerAPI(
            @Value("${apiTitle}") String apiTitle,
            @Value("${apiDescription}") String apiDescription,
            @Value("${apiVersion}") String apiVersion

    ) {
        return new OpenAPI()
                .info(new Info()
                        .title(apiTitle)
                        .description(apiDescription)
                        .version(apiVersion));
    }
}