package com.lsgggg123.reactive;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class ReactiveCore1 {
    public static void main(String[] args) {
        Mono<Integer> just = Mono.just(1).doOnTerminate(() -> {
            log.info("doOnTerminate");
        });

        just.subscribe(new SimpleSubscriber<>());
    }
}
