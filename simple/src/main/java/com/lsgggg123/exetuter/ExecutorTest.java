package com.lsgggg123.exetuter;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorTest {
    
    public static void main(String[] args) throws InterruptedException {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        scheduledThreadPoolExecutor.schedule(() -> {
            System.out.println("scheduled");
            scheduledThreadPoolExecutor.shutdownNow();
        }, 30, TimeUnit.SECONDS);
        
        Thread.sleep(300_000L);
    }
}