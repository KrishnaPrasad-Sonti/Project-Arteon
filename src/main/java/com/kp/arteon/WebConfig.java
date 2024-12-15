package com.kp.arteon;

	import org.springframework.context.annotation.Configuration;
	import org.springframework.web.servlet.config.annotation.CorsRegistry;
	import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

	@Configuration
	public class WebConfig implements WebMvcConfigurer 
	{

	    @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**")  // Allow all paths
	                .allowedOrigins("https://projectarteon.vercel.app/")  // Allow frontend (React) to access backend
	                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Allowed HTTP methods
	                .allowedHeaders("*")  // Allow all headers
	                .allowCredentials(true);  // Allow credentials if needed
	    }
	}


