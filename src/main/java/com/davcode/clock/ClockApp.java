package com.davcode.clock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.davcode.clock")
public class ClockApp {
    public static void main(String[] args) {
        SpringApplication.run(ClockApp.class,args);
    }
}
