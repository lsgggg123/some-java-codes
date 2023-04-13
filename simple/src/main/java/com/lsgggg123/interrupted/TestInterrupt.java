package com.lsgggg123.interrupted;

/**
 * 直接使用 ctrl + c 或者 kill 无法产生 InterruptedException
 */
public class TestInterrupt {
    public static void main(String[] args) throws Exception {
        Thread workerThread = new Thread(new Worker());
        workerThread.start();

        Thread.sleep(1000);
        workerThread.interrupt();
        
        Thread.sleep(1000);
        System.out.println("Main IsInterrupted: " + Thread.currentThread().isInterrupted());
    }

    public static class Worker implements Runnable {
        public void run() {
            System.out.println("Worker started.");
            try {
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
                System.out.println("Worker IsInterrupted: " + Thread.currentThread().isInterrupted());
            }
            System.out.println("Worker stopped.");
        }
    }
}