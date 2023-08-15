package com.lsgggg123.http2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.lsgggg123.http2"})
public class Http2Application {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Http2Application.class);
        application.run(args);
    }
}