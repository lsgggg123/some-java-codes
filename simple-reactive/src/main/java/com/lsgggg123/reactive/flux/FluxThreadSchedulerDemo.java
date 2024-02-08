package com.lsgggg123.reactive.flux;

import com.lsgggg123.reactive.SimpleSubscriber;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class FluxThreadSchedulerDemo {

    public static void main(String[] args) throws InterruptedException {
        // JDK Flow subscribe default on ForkJoinPool thread
        // Reactive core subscribe default on current thread

        Flux.range(1, 3)
                .map(i -> {
                    log.info("map1 {}", i);
                    return i - 1;
                })
                .subscribeOn(Schedulers.immediate()) // map 最后一次调用决定
                .map(i -> {
                    log.info("map2 {}", i);
                    return i * 2;
                })
                .publishOn(Schedulers.single()) // onNext onError onComplete
                .subscribe(new SimpleSubscriber<>());


        Thread.sleep(200L);
    }
}
