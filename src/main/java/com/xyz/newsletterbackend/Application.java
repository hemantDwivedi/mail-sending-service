package com.xyz.newsletterbackend;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Mail Sending Service API", description = "Documentation for the Backend Mail Sending Service API", version = "1.0.0"))
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}