package com.lsgggg123.reactive.jdk;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

@Slf4j
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
            log.error("MyProcessor#onError()", throwable);
        }

        @Override
        public void onComplete() {
            log.info("MyProcessor#onComplete()");
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
                    log.error("Subscriber onNext(): {}", item);
                    subscription.request(1);
                }

                @Override
                public void onError(Throwable throwable) {
                    log.error("Final Subscriber onError()", throwable);
                }

                @Override
                public void onComplete() {
                    log.info("Final Subscriber onComplete()");
                }
            });


            for (int i = 0; i < 10; i++) {
                publisher.submit("item" + i);
            }
        }

        Thread.sleep(100L);
    }
}
