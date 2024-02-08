package com.lsgggg123.reactive;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;
import org.springframework.lang.NonNull;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.SignalType;

@Slf4j
public abstract class BasicSubscriber<T> extends BaseSubscriber<T> {
    @Override
    protected void hookOnSubscribe(@NonNull Subscription subscription) {
        request(1);
    }

    @Override
    protected void hookOnNext(@NonNull T value) {
        doOnNext(value);
        request(1);
    }

    protected abstract void doOnNext(T value);

    @Override
    protected void hookOnError(@NonNull Throwable throwable) {
        log.error("hookOnError", throwable);
    }

    @Override
    protected void hookFinally(@NonNull SignalType type) {
        log.info("BasicSubscriber finish");
    }
}
