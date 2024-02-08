package com.lsgggg123.reactive.mono;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

@Slf4j
public class MonoDeferDemo {

    public static void main(String[] args) {
        deferContextual();
    }

    static void defer() {
        Mono<String> deferMono = Mono.defer(() -> Mono.just("defer"));
        deferMono.subscribe(s -> log.info("consumer 订阅者收到消息: {}", s));
    }

    static void deferWithContext() {
        Context ctxOuter = Context.of("ctx", "secret");
        Mono<String> deferMono = Mono.deferContextual(context -> {
            Object ctx = context.get("ctx");
            return Mono.just("deferWithContext: " + ctx);
        });

        deferMono.subscribe(s -> log.info("consumer 订阅者收到消息: {}", s));
    }

    static void deferContextual() {
        String key = "k";
        Mono<String> deferMono = Mono.deferContextual(ctx -> Mono.just("hello " + ctx.get(key)));
                 // .contextWrite(ctx -> ctx.put(key, "World"));

        deferMono.subscribe(s -> log.info("consumer 订阅者收到消息: {}", s));
    }
}