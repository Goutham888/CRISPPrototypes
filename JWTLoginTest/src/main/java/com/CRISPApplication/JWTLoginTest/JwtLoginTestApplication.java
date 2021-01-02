package com.CRISPApplication.JWTLoginTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication//This combines the @EnableAutoConfiguration, @ComponentScan, and @Configuration
						//@ComponentScan scans packages for services and components
public class JwtLoginTestApplication {

	public static void main(String[] args) {//This is the main method that runs the whole app
		SpringApplication.run(JwtLoginTestApplication.class, args);
	}

}
