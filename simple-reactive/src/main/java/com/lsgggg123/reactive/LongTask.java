package com.lsgggg123.reactive;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public class LongTask {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        List<Mono<String>> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Mono<String> task = Mono.defer(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    log.error("InterruptedException", e);
                }
                return Mono.just("task");
            }).subscribeOn(Schedulers.parallel());
            list.add(task);
        }

        List<String> block = Flux.merge(list).collectList().block();
        log.info("block result: {}", block);

        long duration = System.currentTimeMillis() - start;
        log.info("duration: {}", duration);
    }
}