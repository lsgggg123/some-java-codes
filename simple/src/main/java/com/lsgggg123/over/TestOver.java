package com.lsgggg123.over;

public class TestOver {
    public static void main(String[] args) {
        System.out.println("main is start");
        Thread thread = new Thread(() -> {
            try {
                System.out.println("child thread is start");
                Thread.sleep(10000L);
                System.out.println("child thread is over");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        thread.setDaemon(true);
        thread.start();
        System.out.println("main is over");
    }
}