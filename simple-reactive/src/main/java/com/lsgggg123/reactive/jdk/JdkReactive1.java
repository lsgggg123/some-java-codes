package com.lsgggg123.reactive.jdk;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

@Slf4j
public class JdkReactive1 {
    public static void main(String[] args) throws InterruptedException {
        try (SubmissionPublisher<String> publisher = new SubmissionPublisher<>()) {
            Flow.Subscriber<String> subscriber = new Flow.Subscriber<>() {

                private Flow.Subscription subscription;

                @Override
                public void onSubscribe(Flow.Subscription subscription) {
                    log.info("Subscriber onSubscribe()");
                    this.subscription = subscription;
                    subscription.request(1);
                }

                @Override
                public void onNext(String item) {
                    log.info("Subscriber onNext(): {}", item);
                    subscription.request(1);
                }

                @Override
                public void onError(Throwable throwable) {
                    log.error("Subscriber onError()", throwable);
                }

                @Override
                public void onComplete() {
                    log.info("Subscriber onComplete()");
                }
            };

            publisher.subscribe(subscriber);

            for (int i = 0; i < 10; i++) {
                publisher.submit("item" + i);
            }
        }

        Thread.sleep(100L);
    }
}
