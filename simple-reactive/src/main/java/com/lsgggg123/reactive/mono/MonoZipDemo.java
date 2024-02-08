package com.lsgggg123.reactive.mono;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Slf4j
public class MonoZipDemo {

    public static void main(String[] args) {
        zipCombine();
        zipOnly();
    }

    static void zipCombine() {
        Mono<Integer> m1 = Mono.just(1);
        Mono<String> m2 = Mono.just("a");

        Mono<String> zip = Mono.zip(m1, m2, (i, s) -> s + i);
        zip.subscribe(s -> log.info("consumer 订阅者收到消息: {}", s));
    }


    static void zipOnly() {
        Mono<Integer> m1 = Mono.just(1);
        Mono<String> m2 = Mono.just("a");

        Mono<Tuple2<Integer, String>> zip = Mono.zip(m1, m2);
        zip.subscribe(s -> log.info("consumer 订阅者收到消息: {}, {}", s.getT1(), s.getT2()));
    }
}