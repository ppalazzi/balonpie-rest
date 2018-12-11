package com.palazzisoft.balonpie.service.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"com.palazzisoft.balonpie.service.model"})  // scan JPA entities
@ComponentScan(basePackages="com.palazzisoft.balonpie.service")
public class BalonpieApplication {

    public static void main(String[] args) {
        SpringApplication.run(BalonpieApplication.class, args);
    }
}
