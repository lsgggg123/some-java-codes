package com.lsgggg123.reactive.mono;

import com.lsgggg123.reactive.SimpleSubscriber;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Mono;

@Slf4j
public class MonoCreateDemo {
    
    public static void main(String[] args) {
        Hooks.onEachOperator("demo-hook", publisher -> {
            log.info("function identity");
            return publisher;
        });

        Mono<String> mono = Mono.create(sink -> {
            sink.success("mono.create");
            sink.success("sink.success");
            sink.success("sink.finish");
        });
        
        mono.subscribe(new SimpleSubscriber());
        mono.subscribe(s -> log.info("consumer 订阅者收到消息: {}", s));
    }
}