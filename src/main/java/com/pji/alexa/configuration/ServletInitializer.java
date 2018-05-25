package com.pji.alexa.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@SpringBootApplication
@ServletComponentScan(basePackages = {"com.pji.alexa.configuration*"})
@ComponentScan(basePackages= {"com.pji.alexa.handler", 
		"com.pji.alexa.util",
		"com.pji.alexa.intents",
		"com.pji.alexa.helper",
"com.pji.alexa.services",
"com.pji.alexa.configuration",
"com.pji.alexa.model.v2"})
@EnableScheduling
@EnableRetry
public class ServletInitializer extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ServletInitializer.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(ServletInitializer.class, args);
	}
}
