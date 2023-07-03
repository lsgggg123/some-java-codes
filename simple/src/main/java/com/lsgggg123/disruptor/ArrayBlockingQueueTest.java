package com.lsgggg123.disruptor;

import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<LongEvent> longQ = new ArrayBlockingQueue<>(32);
        System.out.println(longQ);
    }
}