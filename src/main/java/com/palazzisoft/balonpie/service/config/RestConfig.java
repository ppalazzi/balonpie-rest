package com.palazzisoft.balonpie.service.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.palazzisoft.balonpie.service")
@Import(DatabaseConfig.class)
public class RestConfig {

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}	
}
