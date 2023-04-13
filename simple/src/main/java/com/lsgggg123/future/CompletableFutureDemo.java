package com.lsgggg123.future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CompletableFutureDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(CompletableFutureDemo.class);

    private static final String MESSAGE_START = "CompletableFuture [{}] start.";
    private static final String MESSAGE_END   = "CompletableFuture [{}] finish.";

    private static final AtomicInteger AI = new AtomicInteger();

    private static final ExecutorService EXECUTORS1 = Executors.newCachedThreadPool(r -> {
        Thread thread = new Thread(r);
        thread.setDaemon(true);
        thread.setName("custom-thread1-" + AI.getAndIncrement());
        return thread;
    });

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        LOGGER.info("main start");
        CompletableFuture<String> longTask1 = CompletableFuture.supplyAsync(() -> {
            String result = "long-task-1";
            LOGGER.info(MESSAGE_START, result);
            sleep(2500);
            LOGGER.info(MESSAGE_END, result);
            return result;
        });

        CompletableFuture<String> longTask2 = CompletableFuture.supplyAsync(() -> {
            String result = "long-task-2";
            LOGGER.info(MESSAGE_START, result);
            sleep(3000);
            LOGGER.info(MESSAGE_END, result);
            return result;
        });

        CompletableFuture<String> longTask3 = CompletableFuture.supplyAsync(() -> {
            String result = "long-task-3";
            LOGGER.info(MESSAGE_START, result);
            sleep(3000);
            LOGGER.info(MESSAGE_END, result);
            return result;
        });

        CompletableFuture<Void> allOf = CompletableFuture.allOf(longTask1, longTask2, longTask3);
        allOf.get();
        LOGGER.info("main finish");
    }

    public static void test() throws ExecutionException, InterruptedException {
        CompletableFuture<String> longTask1 = CompletableFuture.supplyAsync(() -> {
            String result = "long-task-1";
            LOGGER.info(MESSAGE_START, result);
            sleep(1000);
            LOGGER.info(MESSAGE_END, result);
            return result;
        });

        CompletableFuture<String> longTask2 = CompletableFuture.supplyAsync(() -> {
            String result = "long-task-2";
            LOGGER.info(MESSAGE_START, result);
            sleep(3000);
            LOGGER.info(MESSAGE_END, result);
            return result;
        });

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            String result = "SupplyAsync";
            LOGGER.info(MESSAGE_START, result);
            sleep(200);
            LOGGER.info(MESSAGE_END, result);
            return result;
        }, EXECUTORS1).thenApply(s -> {
            String result = s + "-thenApply";
            LOGGER.info(MESSAGE_START, result);
            sleep(100);
            LOGGER.info(MESSAGE_END, result);
            return result;
        }).thenAccept(s -> {
            String result = s + "-thenAccept";
            LOGGER.info(MESSAGE_START, result);
            sleep();
            LOGGER.info(MESSAGE_END, result);
        }).thenApplyAsync(s -> {
            String result = "thenApplyAsync";
            LOGGER.info(MESSAGE_START, result);
            sleep();
            LOGGER.info(MESSAGE_END, result);
            return result;
        }, EXECUTORS1).thenCompose(s -> {
            String result = s + "-thenCompose-outer";
            LOGGER.info(MESSAGE_START, result);
            sleep();
            LOGGER.info(MESSAGE_END, result);
            return CompletableFuture.supplyAsync(() -> {
                String resultInner = s + "-thenCompose-inner";
                LOGGER.info(MESSAGE_START, result);
                sleep();
                LOGGER.info(MESSAGE_END, result);
                return resultInner;
            }, EXECUTORS1);
        }).thenComposeAsync(s -> {
            String result = s + "-thenComposeAsync-outer";
            LOGGER.info(MESSAGE_START, result);
            sleep();
            LOGGER.info(MESSAGE_END, result);
            return CompletableFuture.supplyAsync(() -> {
                String resultInner = s + "-thenComposeAsync-inner";
                LOGGER.info(MESSAGE_START, result);
                sleep();
                LOGGER.info(MESSAGE_END, result);
                return resultInner;
            }, EXECUTORS1);
        }, EXECUTORS1).thenCombine(longTask1, (s, s2) -> {
            String result = s + "__@__" + s2;
            LOGGER.info(MESSAGE_START, result);
            LOGGER.info(MESSAGE_END, result);
            return result;
        }).applyToEither(longTask2, s -> {
            String result = s + "-applyToEither";
            LOGGER.info(MESSAGE_START, result);
            sleep(100);
            LOGGER.info(MESSAGE_END, result);
            return result;
        });

        String result = completableFuture.get();
        LOGGER.info("result: {}", result);
    }

    static void sleep() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}