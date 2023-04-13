package com.lsgggg123.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableAspectJAutoProxy
@SpringBootApplication(scanBasePackages = {"com.lsgggg123"})
public class SimpleApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SimpleApplication.class);
        application.run(args);
    }
}