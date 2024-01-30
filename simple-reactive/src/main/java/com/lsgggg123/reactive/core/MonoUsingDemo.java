package com.lsgggg123.reactive.core;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class MonoUsingDemo {
    
    public static void main(String[] args) {
        Mono<String> using = Mono.using(() -> "using", Mono::just, System.out::println);
        using.subscribe(s -> log.info("consumer 订阅者收到消息: {}", s));
    }
}