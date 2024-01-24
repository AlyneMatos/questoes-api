package com.example.vestibular.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI springOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme().name(securitySchemeName)
                                                                                    .type(SecurityScheme.Type.HTTP)
                                                                                    .scheme("bearer")
                                                                                    .bearerFormat("JWT"))
                )
                .info(new Info().title("PROJETO RESOLVA.COM")
                        .contact(contact())
                        .description("Projeto de resolução de questões e de provas de vestibulares.")
                        .version("1.0.0"));
    }
    private Contact contact() {
        return new Contact().name("Alyne Matos").url("https://linkedin.com/in/alynematos");
    }
}
