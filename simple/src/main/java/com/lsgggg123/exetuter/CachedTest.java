package com.lsgggg123.exetuter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class CachedTest {

    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10), new ThreadFactory() {

        private int i = 0;

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            t.setName("cached-" + (i++));
            return t;
        }
    });

    private static final AtomicInteger ai = new AtomicInteger();

    // 结论，coreSize 为 0 和 coreSize 为 1 看起来行为一致
    public static void main(String[] args) throws InterruptedException {
        int allTaskCount = 10;

        CountDownLatch countDownLatch = new CountDownLatch(allTaskCount);

        for (int i = 0; i <= allTaskCount; i++) {
            THREAD_POOL_EXECUTOR.execute(() -> {
                try {
                    Thread.sleep(RandomUtils.nextInt(500, 1000));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                countDownLatch.countDown();
                log.info("Task[{}] finished.", ai.getAndIncrement());
            });
        }

        countDownLatch.await();
    }
}