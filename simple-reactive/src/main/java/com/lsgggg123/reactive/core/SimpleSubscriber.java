package com.lsgggg123.reactive.core;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;
import reactor.core.CoreSubscriber;

@Slf4j
public class SimpleSubscriber implements CoreSubscriber<String> {
private Subscription subscription;
    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(String s) {
        log.info("订阅者收到消息: {}", s);
        subscription.request(2);
    }

    @Override
    public void onError(Throwable t) {
        log.error("订阅者遇到错误", t);
    }

    @Override
    public void onComplete() {
        log.info("订阅者完成订阅");
    }
}