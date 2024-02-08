package com.lsgggg123.reactive.mono;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

@Slf4j
public class MonoWhenDemo {

    public static void main(String[] args) throws InterruptedException {
        Mono<Void> using = Mono.when(Mono.just(1), Mono.delay(Duration.of(1, ChronoUnit.SECONDS)), Mono.defer(() -> Mono.just(2)));
        using.subscribe(new CoreSubscriber<Void>() {
            @Override
            public void onSubscribe(Subscription subscription) {
                subscription.request(2);
            }

            @Override
            public void onNext(Void unused) {
                log.info("订阅者收到消息: {}", unused);
            }

            @Override
            public void onError(Throwable t) {
                log.error("订阅者遇到错误", t);
            }

            @Override
            public void onComplete() {
                log.info("订阅者完成订阅");
            }
        });

        TimeUnit.SECONDS.sleep(5);
    }
}