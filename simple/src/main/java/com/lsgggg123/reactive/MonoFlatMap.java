package com.lsgggg123.reactive;

import com.lsgggg123.util.Uninterruptible;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

@Slf4j
public class MonoFlatMap {
    
    public static void main(String[] args) {
        Mono<String> mono = Mono.create(sink -> {
            Uninterruptible.sleepUninterruptible(2, TimeUnit.SECONDS);
            sink.success("mono.create");
        });
        
        mono.subscribe(s -> log.info("consumer 订阅者收到消息: {}", s));
    }
} 