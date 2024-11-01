package com.mutant.mutant.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Mutant API", version = "1.0", description = "API para detectar mutantes"))
public class SwaggerConfig {
}