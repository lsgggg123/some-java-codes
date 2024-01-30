package com.lsgggg123.reactive.core;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

@Slf4j
public class MonoDelayDemo {
    
    public static void main(String[] args) throws InterruptedException {
        Mono<Long> delay = Mono.delay(Duration.of(3, ChronoUnit.SECONDS));

        delay.subscribe(s -> log.info("consumer 订阅者收到消息: {}", s));
        
        TimeUnit.SECONDS.sleep(60);
    }

}