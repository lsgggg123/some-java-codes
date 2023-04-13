package com.lsgggg123.interrupted;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestShutDownHookTerm {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1_000L);
                    System.out.println("call");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("InterruptedException");
                }
            }
        });
        executorService.execute(thread);
        
        Runtime.getRuntime().addShutdownHook(new Thread(executorService::shutdownNow));
    }
}