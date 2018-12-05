package com.hiya.concurrent.block;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 
BlockingQueue中提供了put()和take()方法，可以极大简化生产者消费者模式的实现过程。
这一过程的基本原理是，如果队列满了，put()方法就会被阻塞；如果队列是空的，take()方法会阻塞。
与传统的wait()和notify()方法相比，使用阻塞队列更简单，更便于理解。
 * @author zjq
 *
 */
public class PCBlockQueueClient
{
    public static void main(String[] args)
    {
            BlockingQueue<Integer> sharedQueue = new LinkedBlockingQueue<Integer>();
            Thread producer = new Thread(new PCProducer(sharedQueue));
            Thread consumer =new Thread( new PCConsumer(sharedQueue));

            producer.start();
            consumer.start();
    }
}
