package com.klu.scholarship;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.klu.scholarship") // ✅ VERY IMPORTANT
public class ScholarshipBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScholarshipBackendApplication.class, args);
    }
}