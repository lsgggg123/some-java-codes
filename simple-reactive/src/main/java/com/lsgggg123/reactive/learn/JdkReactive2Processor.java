package com.lsgggg123.reactive.learn;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

public class JdkReactive2Processor {

    /**
     * 中间操作处理器
     */
    static class MyProcessor extends SubmissionPublisher<String> implements Flow.Subscriber<String> {

        private Flow.Subscription subscription;

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            this.subscription = subscription;
            this.subscription.request(1);
        }

        @Override
        public void onNext(String item) {
            item = item + "@process";
            submit(item);
            this.subscription.request(1);
        }

        @Override
        public void onError(Throwable throwable) {
            System.out.println("MyProcessor#onError(): " + throwable.getMessage());
        }

        @Override
        public void onComplete() {
            System.out.println("MyProcessor#onComplete()");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        try (SubmissionPublisher<String> publisher = new SubmissionPublisher<>()) {
            MyProcessor processor = new MyProcessor();

            publisher.subscribe(processor);

            processor.subscribe(new Flow.Subscriber<>() {

                private Flow.Subscription subscription;

                @Override
                public void onSubscribe(Flow.Subscription subscription) {
                    this.subscription = subscription;
                    this.subscription.request(1);
                }

                @Override
                public void onNext(String item) {
                    System.out.println("[" + Thread.currentThread().getName() + "] Subscriber onNext(): " + item);
                    subscription.request(1);
                }

                @Override
                public void onError(Throwable throwable) {
                    System.out.println("Final Subscriber onError(): " + throwable.getMessage());
                }

                @Override
                public void onComplete() {
                    System.out.println("Final Subscriber onComplete()");
                }
            });


            for (int i = 0; i < 10; i++) {
                publisher.submit("item" + i);
            }
        }

        Thread.sleep(100L);
    }
}
