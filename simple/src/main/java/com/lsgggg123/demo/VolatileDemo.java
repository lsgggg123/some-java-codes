package com.lsgggg123.demo;

public class VolatileDemo {
    
    private static volatile int i = 1;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (i <= 100) {
                if (i % 2 == 1) {
                    System.out.println(i + "@" + Thread.currentThread().getName());
                    i++;
                }
            }
        });

        Thread t2 = new Thread(() -> {
            while (i <= 100) {
                if (i % 2 == 0) {
                    System.out.println(i + "@" + Thread.currentThread().getName());
                    i++;
                }
            }
        });

        t1.start();
        t2.start();
    }
}