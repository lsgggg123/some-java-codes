package com.lsgggg123.interrupted;

/**
 * 直接使用 ctrl + c 或者 kill 无法产生 InterruptedException
 */
public class TestNoInterrupt {
    public static void main(String[] args) {
        try {
            Thread.sleep(60_000L);
        } catch (InterruptedException e) {
            System.out.println("捕获了 InterruptedException");
        }
    }
}