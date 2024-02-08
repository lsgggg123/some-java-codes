package com.lsgggg123.reactive.flux;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;

public class FluxCreateDemo {
    public static void main(String[] args) {
        // generate once
        Flux.generate(sink -> {
            sink.next(1);
            sink.complete();
        });

        // generate many times
        Flux.generate(() -> 0, (state, sink) -> {
            if (state == 10) {
                sink.complete();
            } else {
                sink.next("a-" + state);
            }
            return state + 1;
        }).subscribe(System.out::println);

        // disposable
        Disposable disposable = Flux.create(sink -> {
            sink.next(1);
            sink.next(2);
            sink.next(3);
            sink.complete();
        }).subscribe(System.out::println);
        disposable.dispose();

        System.out.println("------------------------");

        // handle
        Flux.just(1, 2, 3, 4, 5).handle((t, sink) -> sink.next(t * 2)).subscribe(System.out::println);
    }
}
