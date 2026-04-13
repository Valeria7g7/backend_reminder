package com.valeria.backend.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
@Configuration
public class CorsConfig implements WebMvcConfigurer{

	@Value("${app.frontend.url}")
	private String frontendtUrl;
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedOrigins(frontendtUrl)
		.allowedMethods("*")
		.allowedHeaders("*");
	}
}
