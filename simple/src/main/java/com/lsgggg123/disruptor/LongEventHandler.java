package com.lsgggg123.disruptor;

import com.lmax.disruptor.EventHandler;

public class LongEventHandler implements EventHandler<LongEvent> {

    @Override
    public void onEvent(LongEvent longEvent, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("consume longEvent, value: " + longEvent.getValue() + ", in thread: " + Thread.currentThread().getId());
    }
}