package com.lsgggg123.reactive;

import reactor.core.publisher.Mono;

public class ReactiveCore1 {
    public static void main(String[] args) {
        Mono<Integer> just = Mono.just(1).doOnTerminate(() -> {
            System.out.println("doOnTerminate");
        });

        just.subscribe(System.out::println);


    }
}
