package com.mobifone.transmission;

import java.util.Collection;
import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class TransmissionApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransmissionApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
						.allowedOrigins("http://localhost:5173", "http://localhost:3000","http://10.24.15.189:3000")
						.allowedHeaders("*")
						.allowCredentials(true);
			}
		};
	}

}
