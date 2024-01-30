package com.lsgggg123.reactive.learn;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

public class JdkReactive {
    public static void main(String[] args) throws InterruptedException {
        try (SubmissionPublisher<String> publisher = new SubmissionPublisher<>()) {
            Flow.Subscriber<String> subscriber = new Flow.Subscriber<>() {

                private Flow.Subscription subscription;

                @Override
                public void onSubscribe(Flow.Subscription subscription) {
                    System.out.println("Subscriber onSubscribe()");
                    this.subscription = subscription;
                    subscription.request(1);
                }

                @Override
                public void onNext(String item) {
                    System.out.println("[" + Thread.currentThread().getName() + "] Subscriber onNext(): " + item);
                    subscription.request(1);
                }

                @Override
                public void onError(Throwable throwable) {
                    System.out.println("Subscriber onError(): " + throwable.getMessage());
                }

                @Override
                public void onComplete() {
                    System.out.println("Subscriber onComplete()");
                }
            };

            publisher.subscribe(subscriber);

            for (int i = 0; i < 10; i++) {
                publisher.submit("item" + i);
            }
        }

        Thread.sleep(2000L);
    }
}
