package com.lsgggg123.reactive;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class MonoFlatMap {
    
    public static void main(String[] args) {
        Mono<String> mono = Mono.create(sink -> {
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            sink.success("mono.create");
        });
        
        mono.subscribe(s -> log.info("consumer 订阅者收到消息: {}", s));
    }
} 