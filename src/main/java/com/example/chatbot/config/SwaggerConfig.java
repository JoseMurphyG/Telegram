package com.example.chatbot.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
Public class SwaggerConfig{

    @Bean
            Public OpenAPI springOpenAPI(){

        Return new OpenAPI()
                .info(new info().title("Telegram Bot Api"))
            .description("API para interactuar con Telegram y OpenAI")
            .version("v1.0")
            .license(new License().name("Apache 2.0").url("http://springdoc.org")))
            .externalDocs(new ExternalDocumentation()
            .description("Documentación del Proyecto")
            .url("https://example.com/docs"));
    }
}