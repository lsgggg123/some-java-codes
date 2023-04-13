package com.lsgggg123.interrupted;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadTest {

    public static void main(String[] args) {
        final ExecutorService executorService = Executors.newSingleThreadExecutor();
        
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                doRun();
                if (Thread.currentThread().isInterrupted()) {
                    // 比如做些清理动作
                    System.out.println("happen interrupt");
                }
            }

            private void doRun() {
                System.out.println("doRunning");
                try {
                    Thread.sleep(100_000);
                    System.out.println("running out");
                } catch (InterruptedException e) {
                    System.out.println("interrupt inner");
                    Thread.currentThread().interrupt();
                }
            }
        });
        
        Runtime.getRuntime().addShutdownHook(new Thread(executorService::shutdownNow));
    }
}