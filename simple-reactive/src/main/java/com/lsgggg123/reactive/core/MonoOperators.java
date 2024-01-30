package com.lsgggg123.reactive.core;

import reactor.core.publisher.Mono;

public class MonoOperators {
    public static void main(String[] args) {
        cast();
    }

    private static void cast() {
        Mono<Number> cast = Mono.just(12).cast(Number.class);
        cast.subscribe(System.out::println);
    }

    static void as() {
        String as = Mono.just(12).as(im -> String.valueOf(im.block() + 1));
        System.out.println(as);
    }
}