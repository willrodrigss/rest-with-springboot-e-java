package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiCOnfig {
	
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
			.info(new Info()
				.title("Monitoramento Turbinas Eólicas - RSD/PPGAS")
				.version("v1")
				.description("API de teste de gerenciamento de áudio das turbinas eólica")
				.termsOfService("")
				.license(
					new License()
						.name("Apache 2.0")
						.url("https://william.ppgas.com.br/api")
					)
				);
	}

}
