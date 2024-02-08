package com.lsgggg123.reactive.flux;

import com.lsgggg123.reactive.SimpleSubscriber;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class FluxLogDemo {
    public static void main(String[] args) {
        Flux.just(1, 2, 3).log().map(String::valueOf).log().subscribe(new SimpleSubscriber<>());
    }
}
