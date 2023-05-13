package com.davcode.clock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@SpringBootApplication()
@ComponentScan(basePackages = {"com.davcode.clock.*"})
@EntityScan("com.davcode.clock.*")
@EnableJpaRepositories(basePackages = "com.davcode.clock.*")
public class ClockApp {

    public static void main(String[] args) {
        SpringApplication.run(ClockApp.class,args);
    }
}
