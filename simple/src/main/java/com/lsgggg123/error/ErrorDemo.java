package com.lsgggg123.error;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ErrorDemo {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        executorService.scheduleAtFixedRate(() -> {
            throw new OutOfMemoryError();
        }, 1, 1, TimeUnit.SECONDS);

        System.out.println("afterMain");
    }
}