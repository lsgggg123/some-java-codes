package com.lsgggg123.disruptor;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class DisruptorMain {
    private static final AtomicInteger AI = new AtomicInteger();

    public static void main(String[] args) {
        // 1. 创建一个 threadFactory
        ThreadFactory threadFactory = r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            t.setName("disruptor-" + AI.getAndIncrement());
            return  t;
        };

        // 2.创建工厂
        EventFactory<LongEvent> eventFactory = new LongEventFactory();

        // 3.创建 ringBuffer 大小, 大小一定要是 2 的 N 次方
        int ringBufferSize = 1024 * 1024;

        // 4.创建 Disruptor
        Disruptor<LongEvent> disruptor = new Disruptor<>(eventFactory, ringBufferSize, threadFactory, ProducerType.SINGLE, new YieldingWaitStrategy());

        // 5.连接消费端方法
        disruptor.handleEventsWith(new LongEventHandler());

        // 6.启动
        disruptor.start();

        // 7.创建 RingBuffer 容器
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        // 8.创建生产者
        LongEventProducer producer = new LongEventProducer(ringBuffer);

        // 9.指定缓冲区大小
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);

        for (int i = 1; i <= 100; i++) {
            byteBuffer.putLong(0, i);
            producer.onData(byteBuffer);
        }

        // 10.关闭 disruptor
        disruptor.shutdown();
    }
}