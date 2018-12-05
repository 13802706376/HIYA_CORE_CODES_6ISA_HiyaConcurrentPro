package com.hiya.concurrent.block;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 
BlockingQueue���ṩ��put()��take()���������Լ����������������ģʽ��ʵ�ֹ��̡�
��һ���̵Ļ���ԭ���ǣ�����������ˣ�put()�����ͻᱻ��������������ǿյģ�take()������������
�봫ͳ��wait()��notify()������ȣ�ʹ���������и��򵥣���������⡣
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
