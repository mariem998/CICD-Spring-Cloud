package com.my_project.formation;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class FormationApplication {
    public static void main(String[] args) {
        SpringApplication.run(FormationApplication.class, args);
    }
}
