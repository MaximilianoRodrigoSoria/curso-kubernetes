package com.laboratory.msvc.courses.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Courses API",
                version = "1.0",
                description = "Documentation for endpoints in User API")
)
public class OpenApiConfig {
}

