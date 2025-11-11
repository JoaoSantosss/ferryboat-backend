package com.ferryboat.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {
	
	@Bean
	public OpenAPI customizeOpenApi() {
		
		final String schemeName = "JWT";
		
		SecurityScheme securityScheme = new SecurityScheme()
                .name(schemeName)
                .type(SecurityScheme.Type.HTTP)  
                .scheme("bearer")                
                .bearerFormat("JWT");
		
		Components components = new Components()
				.addSecuritySchemes(schemeName, securityScheme);
		
		SecurityRequirement requirement = new SecurityRequirement()
				.addList(schemeName);
		
		return new OpenAPI().components(components).addSecurityItem(requirement);
	}

}