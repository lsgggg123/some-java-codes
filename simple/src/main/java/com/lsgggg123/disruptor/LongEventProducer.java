package com.lsgggg123.disruptor;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

public class LongEventProducer {
    public final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer byteBuffer) {
        long sequence = -1L;
        try {
            // 1.ringBuffer 事件队列 下一个槽
            sequence = ringBuffer.next();
            Long data = null;

            // 2.取出空的事件队列
            LongEvent longEvent = ringBuffer.get(sequence);
            data = byteBuffer.getLong(0);

            // 3.获取事件队列传递的数据
            longEvent.setValue(data);

            Thread.sleep(10);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        } finally {
            System.out.println("准备发送数据");
            // 4.发布事件
            ringBuffer.publish(sequence);
        }
    }
}